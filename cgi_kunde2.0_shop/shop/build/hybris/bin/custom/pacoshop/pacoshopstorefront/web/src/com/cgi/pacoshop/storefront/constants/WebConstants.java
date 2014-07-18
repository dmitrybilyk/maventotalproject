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
package com.cgi.pacoshop.storefront.constants;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import javax.annotation.Resource;
import org.apache.commons.configuration.Configuration;

/**
 * Constants used in the Web tier
 */
public final class WebConstants
{
	@Resource
	private ConfigurationService configurationService;

	private WebConstants()
	{
		//empty
	}

	public static final String MODEL_KEY_ADDITIONAL_BREADCRUMB = "additionalBreadcrumb";

	public static final String BREADCRUMBS_KEY = "breadcrumbs";

	public static final String CONTINUE_URL = "session_continue_url";

	public static final String CART_RESTORATION = "cart_restoration";

	public static final String ANONYMOUS_CHECKOUT = "anonymous_checkout";

	public static final String URL_ENCODING_ATTRIBUTES = "encodingAttributes";

	public static final String LANGUAGE_ENCODING = "languageEncoding";

	public static final String CURRENCY_ENCODING = "currencyEncoding";

	public static final String FORBIDDEN_PAGE = "/WEB-INF/views/desktop/pages/error/serverError.jsp";

	public static final String FORM_ATTRIBUTE = "form";

	/** Name of the UAG header that contains information about the original (forwarded) protocol **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_FORWARDED_PROTO = "uag.header.forwardedproto.name";

	/** Value of the forwarded protocol header in case original protocol was http **/
	public static final String CONFIG_KEY_UAG_HEADER_FORWARDED_PROTO_HTTP = "uag.header.forwardedproto.http";

	/** Value of the forwarded protocol header in case original protocol was https **/
	public static final String CONFIG_KEY_UAG_HEADER_FORWARDED_PROTO_HTTPS = "uag.header.forwardedproto.https";

	/** Port on which the UAG listens to http requests **/
	public static final String CONFIG_KEY_UAG_HTTP_PORT = "uag.http.port";

	/** Port on which the UAG listens to https requests **/
	public static final String CONFIG_KEY_UAG_HTTPS_PORT = "uag.https.port";

	//public static final String ACCESS_PAGE = "/desktop/pages/checkout/checkoutConfirmationPage";
}
