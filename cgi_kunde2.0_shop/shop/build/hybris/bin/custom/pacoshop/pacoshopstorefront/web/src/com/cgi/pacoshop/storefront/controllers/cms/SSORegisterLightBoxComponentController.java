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

import com.cgi.pacoshop.core.model.SSORegisterLightBoxComponentModel;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import com.cgi.pacoshop.storefront.util.ReturnUrlUtil;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriTemplate;


/**
 * Controller for SSO register lightbox.
 *
 * @module pacoshopstorefront
 * @version 1.0v Jun 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@Controller("SSORegisterLightBoxComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.SSORegisterLightBoxComponent)
public class SSORegisterLightBoxComponentController
		  extends AbstractCMSComponentController<SSORegisterLightBoxComponentModel>
{
	private static final String REGISTER_URL = "registerUrl";
	private static final String TEXT         = "text";
	private static final String LABEL        = "label";
	private static final String HEADER       = "headerText";
	private static final String TIMEOUT      = "timeout";

	private static final String FIRST_NAME    = "FIRSTNAME";
	private static final String LAST_NAME     = "LASTNAME";
	private static final String EMAIL         = "EMAIL";
	private static final String DATE_OF_BIRTH = "DATEOFBIRTH";
	private static final String TITLE         = "TITLE";
	private static final String STREET        = "STREET";
	private static final String STREET_NUMBER = "STREET_NUMBER";
	private static final String PLZ           = "POSTALCODE";
	private static final String CITY          = "CITY";
	private static final String COUNTRY       = "COUNTRY";

	private static final String PHONE_PREFIX_HOME = "PHONE_PREFIX_HOME";
	private static final String PHONE_NUMBER_HOME = "PHONE_NUMBER_HOME";
	private static final String PHONE_EXT_HOME = "PHONE_EXT_HOME";
	private static final String PHONE_PREFIX_BUSINESS = "PHONE_PREFIX_BUSINESS";
	private static final String PHONE_NUMBER_BUSINESS = "PHONE_NUMBER_BUSINESS";
	private static final String PHONE_EXT_BUSINESS = "PHONE_EXT_BUSINESS";
	private static final String MOBILE_PREFIX = "MOBILE_PREFIX";
	private static final String MOBILE_NUMBER = "MOBILE_NUMBER";

	private static final String RETURN_URL = "RETURN_TO_HERE_URL";

	private static final String ORDERGUID                    = "orderguid";
	private static final String SSO_REGISTRATION_DATE_FORMAT = "sso.registration.date.format";

	@Resource
	private BaseStoreService       baseStoreService;
	@Resource
	private CustomerAccountService customerAccountService;
	@Resource
	private ConfigurationService   configurationService;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
									 final SSORegisterLightBoxComponentModel component)
	{
		HashMap<String, String> parameterMap = populateParameterMap(request);

		final UriTemplate uriTemplate = new UriTemplate(component.getRegisterUrl());
		final String registerUrl = uriTemplate.expand(parameterMap).toString();

		model.addAttribute(REGISTER_URL, registerUrl);
		model.addAttribute(TEXT, component.getText());
		model.addAttribute(LABEL, component.getLabel());
		model.addAttribute(HEADER, component.getHeader());
		model.addAttribute(TIMEOUT, component.getTimeout());
	}

	private HashMap<String, String> populateParameterMap(final HttpServletRequest request)
	{
		final String orderGUID = request.getParameter(ORDERGUID);

		OrderModel order = null;

		if (StringUtils.isNotEmpty(orderGUID))
		{
			order = getOrderModelByGUID(orderGUID);
		}

		AddressModel addressModel = null;

		if (order != null)
		{
			addressModel = order.getCustomerAddress();
		}

		final HashMap<String, String> parameterMap = new HashMap<>();

		parameterMap.put(RETURN_URL, ReturnUrlUtil.getReturnUrl(request));
		parameterMap.putAll(populateParameterMap(addressModel));

		return parameterMap;
	}

	private HashMap<String, String> populateParameterMap(final AddressModel addressModel)
	{
		final HashMap<String, String> parameterMap = new HashMap<>();

		if (addressModel != null)
		{
			final String email = URLEncoder.encode(checkOnNull(addressModel.getEmail()));
			parameterMap.put(EMAIL, email);
			parameterMap.put(FIRST_NAME, checkOnNull(addressModel.getFirstname()));
			parameterMap.put(LAST_NAME, checkOnNull(addressModel.getLastname()));
			parameterMap.put(DATE_OF_BIRTH, getStringOfDateInFormat(addressModel.getDateOfBirth()));
			parameterMap.put(STREET, checkOnNull(addressModel.getLine1()));
			parameterMap.put(STREET_NUMBER, checkOnNull(addressModel.getLine2()));
			parameterMap.put(PLZ, checkOnNull(addressModel.getPostalcode()));
			parameterMap.put(CITY, checkOnNull(addressModel.getTown()));
			if (addressModel.getHomeNumber() != null)
			{
				parameterMap.put(PHONE_PREFIX_HOME, checkOnNull(addressModel.getHomeNumber().getPrefix()));
				parameterMap.put(PHONE_EXT_HOME, checkOnNull(addressModel.getHomeNumber().getExtension()));
				parameterMap.put(PHONE_NUMBER_HOME, checkOnNull(addressModel.getHomeNumber().getNumber()));
			}
			else
			{
				parameterMap.put(PHONE_PREFIX_HOME, StringUtils.EMPTY);
				parameterMap.put(PHONE_EXT_HOME, StringUtils.EMPTY);
				parameterMap.put(PHONE_NUMBER_HOME, StringUtils.EMPTY);
			}
			if (addressModel.getBusinessNumber() != null)
			{
				parameterMap.put(PHONE_PREFIX_BUSINESS, checkOnNull(addressModel.getBusinessNumber().getPrefix()));
				parameterMap.put(PHONE_EXT_BUSINESS, checkOnNull(addressModel.getBusinessNumber().getExtension()));
				parameterMap.put(PHONE_NUMBER_BUSINESS, checkOnNull(addressModel.getBusinessNumber().getNumber()));
			}
			else
			{
				parameterMap.put(PHONE_PREFIX_BUSINESS, StringUtils.EMPTY);
				parameterMap.put(PHONE_EXT_BUSINESS, StringUtils.EMPTY);
				parameterMap.put(PHONE_NUMBER_BUSINESS, StringUtils.EMPTY);
			}
			if (addressModel.getMobileNumber() != null)
			{
				parameterMap.put(MOBILE_PREFIX, checkOnNull(addressModel.getMobileNumber().getPrefix()));
				parameterMap.put(MOBILE_NUMBER, checkOnNull(addressModel.getMobileNumber().getNumber()));
			}
			else
			{
				parameterMap.put(MOBILE_PREFIX, StringUtils.EMPTY);
				parameterMap.put(MOBILE_NUMBER, StringUtils.EMPTY);
			}
			if (addressModel.getTitle() != null)
			{
				parameterMap.put(TITLE, addressModel.getTitle().getName());
			}
			else
			{
				parameterMap.put(TITLE, StringUtils.EMPTY);
			}

			if (addressModel.getCountry() != null)
			{
				parameterMap.put(COUNTRY, addressModel.getCountry().getName());
			}
			else
			{
				parameterMap.put(COUNTRY, StringUtils.EMPTY);
			}
		}
		else
		{
			//need for uriTemplate.expand - map with keys but empty values
			parameterMap.put(EMAIL, StringUtils.EMPTY);
			parameterMap.put(FIRST_NAME, StringUtils.EMPTY);
			parameterMap.put(LAST_NAME, StringUtils.EMPTY);
			parameterMap.put(DATE_OF_BIRTH, StringUtils.EMPTY);
			parameterMap.put(TITLE, StringUtils.EMPTY);
			parameterMap.put(STREET, StringUtils.EMPTY);
			parameterMap.put(STREET_NUMBER, StringUtils.EMPTY);
			parameterMap.put(PLZ, StringUtils.EMPTY);
			parameterMap.put(CITY, StringUtils.EMPTY);
			parameterMap.put(COUNTRY, StringUtils.EMPTY);
		}

		return parameterMap;
	}

	private static String checkOnNull(final String param)
	{
		return StringUtils.isEmpty(param) ? StringUtils.EMPTY : param;
	}


	private OrderModel getOrderModelByGUID(final String orderId)
	{
		final BaseStoreModel baseStoreModel = baseStoreService.getCurrentBaseStore();
		return customerAccountService.getOrderDetailsForGUID(orderId, baseStoreModel);
	}

	private String getStringOfDateInFormat(final Date date)
	{
		String result = "";
		if (date != null)
		{
			final String dateFormat = configurationService.getConfiguration().getString(SSO_REGISTRATION_DATE_FORMAT);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			result = simpleDateFormat.format(date);
		}
		return result;
	}
}
