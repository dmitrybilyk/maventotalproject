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
package com.cgi.pacoshop.storefront.controllers.cms;

import com.cgi.pacoshop.core.model.HeaderAuthComponentModel;
import com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import com.cgi.pacoshop.storefront.util.ReturnUrlUtil;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.cgi.pacoshop.core.util.LogHelper.error;


/**
 * Header auth component controller. Maps callback url.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v May 6, 2014
 * @module pacoshopstorefront
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@Controller("HeaderAuthComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.HeaderAuthComponent)
public class HeaderAuthComponentController extends AbstractCMSComponentController<HeaderAuthComponentModel>
{
	private static final Logger LOG = Logger.getLogger(HeaderAuthComponentController.class);

	private static final String HTTP_CALLBACK_URL_PARAMETER = "{callback}";

	private static final String ATTR_LOGIN_TITLE = "loginTitle";
	private static final String ATTR_LOGIN_URL = "loginUrl";
	private static final String ATTR_REGISTER_TITLE = "registerTitle";
	private static final String ATTR_REGISTER_URL = "registerUrl";
	private static final String ATTR_SALUTATION = "salutation";

	private static final String PARAM_TITLE = "{title}";
	private static final String PARAM_USER_FIRSTNAME = "{firstName}";
	private static final String PARAM_USER_LASTNAME = "{lastName}";

	private static final String DEFAULT_USER_NAME = "user";

	@Resource
	private UserService userService;

	@Resource
	private DynamicCheckoutFacade dynamicCheckoutFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final HeaderAuthComponentModel component)
	{
		final String returnUrl = ReturnUrlUtil.getReturnUrl(request);

		if (component.getLoginUrl() != null)
		{
			model.addAttribute(
					ATTR_LOGIN_URL,
					component.getLoginUrl().replace(HTTP_CALLBACK_URL_PARAMETER, returnUrl)
			);
		}

		if (component.getRegisterUrl() != null)
		{
			model.addAttribute(
					ATTR_REGISTER_URL,
					component.getRegisterUrl().replace(HTTP_CALLBACK_URL_PARAMETER, returnUrl)
			);
		}

		CustomerModel user = (CustomerModel) userService.getCurrentUser();
		if (user != null)
		{
			String title = "";
			TitleModel titleModel = user.getTitle();
			if (titleModel != null)
			{
				title = titleModel.getName();
			}

			String salutation = component.getSalutation();
			if (salutation != null)
			{
				String firstName = user.getFirstName() == null ? "" : user.getFirstName();
				String lastName = user.getLastName() == null ? "" : user.getLastName();

				if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName))
				{
					firstName = DEFAULT_USER_NAME;
				}

				salutation = salutation
						.replace(PARAM_TITLE, title)
						.replace(PARAM_USER_FIRSTNAME, firstName)
						.replace(PARAM_USER_LASTNAME, lastName)
						.replaceAll("\\s+", " ");
			}
			else
			{
				salutation = "";
			}

			model.addAttribute(ATTR_SALUTATION, salutation);
		}

		model.addAttribute(ATTR_LOGIN_TITLE, component.getLoginTitle());
		model.addAttribute(ATTR_REGISTER_TITLE, component.getRegisterTitle());
	}
}
