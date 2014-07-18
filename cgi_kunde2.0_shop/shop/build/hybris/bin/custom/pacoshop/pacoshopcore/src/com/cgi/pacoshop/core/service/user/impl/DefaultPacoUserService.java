/*
* [y] hybris Platform
*
* Copyright (c) 2000-2014 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.service.user.impl;


import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.daos.Title2Dao;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.user.PacoUserService;
import com.cgi.pacoshop.core.service.servicebus.ServiceBusBusinessPartnerService;
import com.cgi.pacoshop.core.service.sso.SSOCustomerService;
import com.cgi.pacoshop.core.util.LogHelper;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.tx.TransactionBody;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.cgi.pacoshop.core.util.LogHelper.debug;


/**
 * User service that allows creating new users in Hybris by getting
 * necessary information from SSO and attaching business partner ids
 * from ServiceBus.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @version 1.0v Jun 02, 2014
 * @see https://wiki.hybris.com/
 */
public class DefaultPacoUserService implements PacoUserService
{
    private static final Logger LOG = Logger.getLogger(DefaultPacoUserService.class);

    @Resource
    private ShopConfigurationService shopConfigurationService;

    @Resource
    private UserService userService;

    @Resource
    private SSOCustomerService ssoCustomerService;

    @Resource
    private ModelService modelService;

    @Resource
    private ServiceBusBusinessPartnerService serviceBusBusinessPartnerService;

    public synchronized UserModel createAndUpdateCustomer(final String userIdType, final Map<String, String> userParameters)
    {
        UserModel result = null;

        try
        {
            result = (UserModel) Transaction.current().execute(new TransactionBody()
            {
                public Object execute()
                {
                    return createAndUpdateCustomerInternal(userIdType, userParameters);
                }
            }
            );
        }
        catch (Exception e)
        {
            LogHelper.error(LOG, e.getMessage(), e);
        }

        return result;
    }

	@Override
	public void removeAnonymousSubmittedAttribute()
	{
		JaloSession.getCurrentSession().removeAttribute(FormElementGroupConstants.ANONYMOUS_SUBMITTED);
	}

	public UserModel createAndUpdateCustomerInternal(final String userIdType, final Map<String, String> userParameters)
    {
        String userIdKey = shopConfigurationService.getSsoUserFilterAccountId();
        final String userId = userParameters.get(userIdKey);

        // If user exists - just return it. Or create if it does not.
        UserModel user = getOrCreateUser(userId);

		 // check cgi-ws-token
		 final String uagWsTokenKey = shopConfigurationService.getUagWsToken();
		 final String uagWsTokenValue = userParameters.get(uagWsTokenKey);
		 if (StringUtils.isEmpty(uagWsTokenValue))
		 {
			 // if token is missing - we log error and skip request
			 error(LOG, "no {%s} request header", uagWsTokenKey);
		 }
		 else
		 {
			 // Update the customer with the details from SSO
			 // and also attach the business partner ids
			 updateCustomerFromSSO(user, userParameters);
		 }

        // Refresh user from storage (with its business partner ids and customer details)
        // before returning to a caller
        modelService.refresh(user);

        return user;
    }

    private void updateCustomerFromSSO(final UserModel user, final Map<String, String> userParameters)
    {
        boolean isPartnerAndSsoClientEnabled = shopConfigurationService.isServicebusBusinessPartnerAndSsoClientEnable();
        debug(LOG, "SSO client and ServiceBus Business Partner services enabled = {%s}", isPartnerAndSsoClientEnabled);

        if (isPartnerAndSsoClientEnabled)
        {
            // update customer
            ssoCustomerService.updateCustomer(user, userParameters);

            // Retrieve and attach business partner ids to the customer account
            boolean isBusinessPartnerEnabled = shopConfigurationService.isServicebusBusinessPartnerEnable();
            debug(LOG, "Servicebus Business Partner service enabled = {%s}", isBusinessPartnerEnabled);
            if (isBusinessPartnerEnabled)
            {
                updateBusinessPartnerIdsFromServiceBus(user, userParameters);
            }
        }
    }

    private void updateBusinessPartnerIdsFromServiceBus(final UserModel user, final Map<String, String> userParameters)
    {
        // populate WS token for accessing SSO
        HashMap<String, String> wsToken = new HashMap<>();
        String uagWSToken = shopConfigurationService.getUagWsToken();
        wsToken.put(uagWSToken, userParameters.get(uagWSToken));

        // Attach business partner ids to a user
        serviceBusBusinessPartnerService.updateBusinessPartners(user, wsToken);
    }

    private UserModel getOrCreateUser(final String userId)
    {
        UserModel user = null;

        try
        {
            user = userService.getUserForUID(userId);
            debug(LOG, "Found user for userId = {%s} in Hybris database", userId);
        }
        catch (final UnknownIdentifierException e)
        {
            debug(LOG, "No user found in Hybris by userId = {%s}. New user will be created.", userId);
        }

        if (user == null)
        {
            user = modelService.create(CustomerModel.class);
            user.setUid(userId);
            user.setName(userId);
            modelService.save(user);
            debug(LOG, "Created a new user in Hybris for userId = {%s}", userId);
        }

        return user;
    }
}
