/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.cgi.pacoshop.storefront.filters;

import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.servicebus.ServiceBusBusinessPartnerService;
import com.cgi.pacoshop.core.service.sso.SSOCustomerService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import static com.cgi.pacoshop.core.util.LogHelper.info;

import com.cgi.pacoshop.core.service.user.PacoUserService;
import com.cgi.pacoshop.facades.customergroups.PacoCustomerGroupFacade;
import com.cgi.pacoshop.storefront.security.AutoLoginStrategy;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.customergroups.CustomerGroupFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * This filter inspects the url and give access to site or not. Also it automatically log in user to hybris if userId in
 * request different from current hybris user. Also it creates a user if it is not exist in hybris.
 *
 * @author Oleg Erofeev
 * @author Oleksandr Fedorov
 */
public class UserLoginFilter extends OncePerRequestFilter
{
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserLoginFilter.class);

    private static final String UI_RESOURCES                       = "/_ui/";
    private static final String PREVIEW_CONTENT_PATH               = "/preview-content";
    private static final String THANKYOUPAGE_URL                   = "/thankyou";
    private static final String ERRORPAGE_URL                      = "/error";
    private static final String PAYMENT_EXTENSION_CONTROLLERS_PATH = "/checkout/payment";
    private static final int    DEFAULT_HTTP_STATUS                = 400;

    private ModelService   modelService;
    private UserService    userService;
    private CustomerFacade customerFacade;

    @Resource(name = "autoLoginStrategy")
    private AutoLoginStrategy autoLoginStrategy;

    @Resource
    private ConfigurationService configurationService;

    @Resource
    private PacoCustomerGroupFacade pacoCustomerGroupFacade;

    @Resource
    private ShopConfigurationService shopConfigurationService;

    @Resource
    private PacoUserService pacoUserService;

    /**
     * Performs auto login for userId if it is different from currentHybrisUserId. Create a new user if it is absent in
     * hybris system
     *
     * @param request
     *           request object
     * @param response
     *           response object
     * @param filterChain
     *           filter chain
     * @throws ServletException
     *            on error
     * @throws IOException
     *            on error
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException
    {
        // if this is resource url
        if (request.getRequestURI().contains(UI_RESOURCES)
                || request.getRequestURI().contains(PREVIEW_CONTENT_PATH)
                || request.getRequestURI().contains(THANKYOUPAGE_URL)
                || request.getRequestURI().contains(PAYMENT_EXTENSION_CONTROLLERS_PATH))
        {
            filterChain.doFilter(request, response);
            return;
        }

        // check customerId in request
        final String customerIdHeaderName = getProperty(ShopConfigurationService.CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_ID);
        debug(LOG, "customerIdHeaderName: {%s}", customerIdHeaderName);
        final String checkUserId = request.getHeader(customerIdHeaderName);
        if (checkUserId == null || checkUserId.isEmpty())
        {
            String messageTmpl = "no cgi-customerid request header";
            info(LOG, messageTmpl);
            response.sendError(DEFAULT_HTTP_STATUS, messageTmpl);
            return;
        }
        final String userId = checkUserId.toLowerCase();

        // check cgi-idtype in request
        final String customerIdTypeHeader = getProperty(ShopConfigurationService.CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_ID_TYPE);
        debug(LOG, "customerIdTypeHeader: {%s}", customerIdTypeHeader);
        final String userIdType = request.getHeader(customerIdTypeHeader);
        if (userIdType == null || userIdType.isEmpty())
        {
            String messageTmpl = "no cgi-idtype request header";
            info(LOG, messageTmpl);
            response.sendError(DEFAULT_HTTP_STATUS, messageTmpl);
            return;
        }

        // get hybris current user id
        final String currentHybrisUserId = userService.getCurrentUser().getUid();
        debug(LOG, "Request userId={%s}, userType={%s}, current Hybris session userId={%s}.",
				  userId, userIdType, currentHybrisUserId);
        // compare the current session user with the user from request by userId and userIdType fields
        // if not the same then reset user data stored in session
        if (! ( userId.equalsIgnoreCase(currentHybrisUserId)
					&& pacoCustomerGroupFacade.isUserBelongsToUserType(userIdType, currentHybrisUserId)))
		{
            // Remove previously anonymous user from session
            final JaloSession currentSession = JaloSession.getCurrentSession();
            final Object anonymousSubmitted = currentSession.getAttribute(FormElementGroupConstants.ANONYMOUS_SUBMITTED);
            if (anonymousSubmitted != null)
            {
                currentSession.removeAttribute(FormElementGroupConstants.ANONYMOUS_SUBMITTED);
            }

            debug(LOG, "Request userId={%s} does not match currentHybrisUserId={%s}, trying to autologin", userId,
					currentHybrisUserId);

			// check if user exist -> creates and updates every time in get request in checkout flow
            final String ssoUserFilterAccountIdKey = shopConfigurationService.getSsoUserFilterAccountId();
            final String ssoPlatformIdKey = shopConfigurationService.getSSOPlatformIdKey();
            final String ssoPlatformIdVal = shopConfigurationService.getSSOPlatformIdVal();

				// check cgi-ws--token
				final String uagWsTokenKey = shopConfigurationService.getUagWsToken();
				debug(LOG, "UAG Header auth token key: {%s}", uagWsTokenKey);
				final String uagWsTokenValue = request.getHeader(uagWsTokenKey);

				debug(LOG, "UAG Header auth token: {%s}", uagWsTokenValue);
				debug(LOG, "UAG UserId {'%s'} : {%s}", ssoUserFilterAccountIdKey, userId);
				debug(LOG, "UAG Platform {'%s'} : {%s}", ssoPlatformIdKey, ssoPlatformIdVal);
            // Create or update the customer (this will invoke SSO and ServiceBus,
            // update a user in Hybris and attach business partner ids
            final UserModel userFromRequest = pacoUserService.createAndUpdateCustomer(userIdType, new HashMap<String, String>()
            {
                {
                    put(ssoUserFilterAccountIdKey, userId);
                    put(uagWsTokenKey, uagWsTokenValue);
                    put(ssoPlatformIdKey, ssoPlatformIdVal);
                }
            });

            pacoCustomerGroupFacade.addUserToCustomerSubgroup(userIdType, userId);

			// login user using anonymously
			final boolean loggedIn = autoLoginStrategy.login(userId, request, response);
			// login to hybris system
			if (loggedIn)
			{
				debug(LOG, "Succesfully logged in. Setting hybris currentUser={%s} and login to customer facade", userId);
				// after successful login - set user to hybris session
				userService.setCurrentUser(userFromRequest);
				getCustomerFacade().loginSuccess(); // need already set userService.setCurrentUser
			}
		}
		else
		{
			// even if the user did not change, still re-populate the Spring security context
			autoLoginStrategy.login(userId, request, response);
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Forward request to specific url.
	 *
	 * @param forwardURI
	 *           url to forward
	 * @param request
	 *           request
	 * @param response
	 *           response
	 * @throws ServletException
	 *            on error
	 * @throws IOException
	 *            on error
	 */
	private void forwardTo(final String forwardURI, final HttpServletRequest request, final HttpServletResponse response)
			  throws ServletException, IOException
	{
		getServletContext().getRequestDispatcher(forwardURI).forward(request, response);
	}

	/**
	 * Gets user service.
	 *
	 * @return the userService
	 */
	protected UserService getUserService()
	{
		return userService;
	}

	/**
	 * Sets user service.
	 *
	 * @param userServiceArg
	 *           the user service
	 */
	@Required
	public void setUserService(final UserService userServiceArg)
	{
		this.userService = userServiceArg;
	}

	/**
	 * Gets customer facade.
	 *
	 * @return the customerFacade
	 */
	protected CustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	/**
	 * Sets customer facade.
	 *
	 * @param customerFacadeArg
	 *           the customer facade
	 */
	@Required
	public void setCustomerFacade(final CustomerFacade customerFacadeArg)
	{
		this.customerFacade = customerFacadeArg;
	}

	/**
	 * Gets auto login strategy.
	 *
	 * @return the autoLoginStrategy
	 */
	protected AutoLoginStrategy getAutoLoginStrategy()
	{
		return autoLoginStrategy;
	}

	/**
	 * Gets model service.
	 *
	 * @return the model service
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * Sets model service.
	 *
	 * @param modelServiceArg
	 *           the model service
	 */
	@Required
	public void setModelService(final ModelService modelServiceArg)
	{
		this.modelService = modelServiceArg;
	}

	private String getProperty(final String property)
	{
		return configurationService.getConfiguration().getString(property);
	}
}
