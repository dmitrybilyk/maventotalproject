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
package com.cgi.pacoshop.storefront.util;


import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 */
public class ReturnUrlUtil
{
	private static final String JAVAX_SERVLET_REQUEST_URI = "javax.servlet.forward.request_uri";

	private static final Logger LOG = Logger.getLogger(ReturnUrlUtil.class);

	public static String getReturnUrl(final HttpServletRequest request)
	{
		String queryString = request.getQueryString() != null
				  ? "?" + request.getQueryString()
				  : "";
		final String returnUrl = URLEncoder.encode(ReturnUrlUtil.extractHostInformationFromRequest(request)
																			+ request.getAttribute(JAVAX_SERVLET_REQUEST_URI)
																			+ queryString);
		debug(LOG, "generated returnUrl : {%s}", returnUrl);
		return returnUrl;
	}

	/**
	 * The method to get information about protocol, hostname and port.
	 * Implemented here because UrlUtils.extractHostInformationFromRequest method returns incorrect protocol value
	 * which leads to incorrect redirect from registration results page
	 *
	 * @param request HttpServletRequest
	 * @return host information in URL format
	 */
	private static String extractHostInformationFromRequest(HttpServletRequest request)
	{
		try
		{
			final URL url = new URL(request.getRequestURL().toString());
			final String portUrlFragment = url.getPort() < 0 ? "" : ":" + url.getPort();
			return url.getProtocol() + "://" + url.getHost() + portUrlFragment;
		}
		catch (MalformedURLException e)
		{
			error(LOG, "request url {%s} is malformed", request.getRequestURL().toString());
			return StringUtils.EMPTY;
		}
	}
}
