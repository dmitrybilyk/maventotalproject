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
package com.cgi.pacoshop.storefront.filters.uagmock;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidParameterException;

import static com.cgi.pacoshop.core.util.LogHelper.*;
import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * UagMockFilter - mock for the user access gateway
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 16, 2014
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class UagMockFilter implements javax.servlet.Filter
{
	private static final String UAG_USER_ID                    = "cgi-customerid";
	private static final String UAG_ID_TYPE                    = "cgi-idtype";
	private static final String UAG_USER_EMAIL                 = "cgi-customer-email";
	private static final String UAG_CONTENT_ACCESS             = "cgi-contentaccess";
	private static final String UAG_WS_TOKEN                   = "cgi-ws-token";
	private static final String UAG_X_FORWARDED_FOR            = "X-Forwarded-For";
	private static final String UAG_X_FORWARDED_PROTO          = "X-Forwarded-Proto";
	private static final String HEADER_ENCODING                = "utf-8";

	private static final String SESSION_ATTR_USER_ID    = "UAGMockUserId";
	private static final String SESSION_ATTR_ID_TYPE    = "UAGMockIdType";
	private static final String SESSION_ATTR_USER_EMAIL = "UAGMockUserEmail";
	private static final String SESSION_ATTR_WS_TOKEN   = "UAGMockWSToken";

	private final static Logger LOG = Logger.getLogger(UagMockFilter.class.getName());

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException
	{
		info(LOG, "UAGMockFilter is active. We are apparently running on a test system without a real UAG proxy in place.");
	}

	@Override
	public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
			  throws IOException, ServletException
	{
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final HttpSession session = request.getSession();

		final UagMockRequestWrapper uagMockRequestWrapper =
				  new UagMockRequestWrapper((HttpServletRequest) servletRequest, request.getServerPort());

		try
		{
			String userId = request.getParameter(UAG_USER_ID);
			String idType = request.getParameter(UAG_ID_TYPE);
			String userEmail = request.getParameter(UAG_USER_EMAIL);
			String wsToken = request.getParameter(UAG_WS_TOKEN);

			if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(idType) && !StringUtils.isEmpty(userEmail))
			{
				// If the url parameters are in the request, take the values from there and store them in the session

				debug(LOG, "URL parameters are present in request");

				session.setAttribute(SESSION_ATTR_USER_ID, userId);
				session.setAttribute(SESSION_ATTR_ID_TYPE, idType);
				session.setAttribute(SESSION_ATTR_USER_EMAIL, userEmail);

				// Generate token if it isn't provided.
				if (StringUtils.isEmpty(wsToken))
				{
					wsToken = UagWsTokenGenerator.generate(userId);
				}
				session.setAttribute(SESSION_ATTR_WS_TOKEN, wsToken);
			}
			else
			{
				debug(LOG, "No URL headers present in request, falling back to values stored in session");

				// If not, take the stored values from the session
				userId = (String) session.getAttribute(SESSION_ATTR_USER_ID);
				idType = (String) session.getAttribute(SESSION_ATTR_ID_TYPE);
				userEmail = (String) session.getAttribute(SESSION_ATTR_USER_EMAIL);
				wsToken = (String) session.getAttribute(SESSION_ATTR_WS_TOKEN);
			}

			//CustomerID
			setRequestHeader(uagMockRequestWrapper, UAG_USER_ID, userId);
			//ID-Type Anonym (Visitor) or SSO Account
			setRequestHeader(uagMockRequestWrapper, UAG_ID_TYPE, idType);
			//E-Mail address of the customer
			setRequestHeader(uagMockRequestWrapper, UAG_USER_EMAIL, userEmail);
			//Is access to the content allowed or not (Possible return values are allowed and notallowed)
			setRequestHeader(uagMockRequestWrapper, UAG_CONTENT_ACCESS, "allowed");
			//Web service token for access to the REST API
			setRequestHeader(uagMockRequestWrapper, UAG_WS_TOKEN, wsToken);
			//IP address of the user for which the request has been forwarded by the UAG
			String ipAddress = request.getHeader(UAG_X_FORWARDED_FOR);

			if (ipAddress == null)
			{
				ipAddress = request.getHeader(UAG_X_FORWARDED_FOR.toUpperCase());
				if (ipAddress == null)
				{
					ipAddress = request.getRemoteAddr();
				}
			}

			setRequestHeader(uagMockRequestWrapper, UAG_X_FORWARDED_FOR, ipAddress);

			//Original protocol (http or https) of the request that was forwarded to the shop by the UAG
			setRequestHeader(uagMockRequestWrapper, UAG_X_FORWARDED_PROTO, getOriginalProtocol(request));
		}
		catch (final Exception e)
		{
			error(LOG, "exception occurred while setting request headers", e);
		}

		filterChain.doFilter(uagMockRequestWrapper, response);
	}


	private static String getOriginalProtocol(final HttpServletRequest request)
	{
		// For the test environment, we need to check if the request is forwarded by the nginx reverse proxy
		final String forwardedProto = request.getHeader(UAG_X_FORWARDED_PROTO);

		if (!StringUtils.isEmpty(forwardedProto))
		{
			// If there is already an existing X-Forward-Proto header, leave that one in place
			debug(LOG, "Found existing header for [%s] forwarded proto with value: [%s]", UAG_X_FORWARDED_PROTO, forwardedProto);

			return forwardedProto;
		}
		else
		{
			// If not, get the protocol from the original request
			return request.getScheme();
		}
	}


	private void setRequestHeader(final UagMockRequestWrapper uagMockRequestWrapper, final String headerName,
										 final String headerValue) throws UnsupportedEncodingException
	{
		debug(LOG, "setting header: %s = %s", headerName, headerValue);

		if (isEmpty(headerName))
		{
			throw new InvalidParameterException(String.format("invalid header name: %s", headerName));
		}

		uagMockRequestWrapper.addHttpHeader(headerName, URLEncoder.encode(headerValue, HEADER_ENCODING));
	}


	@Override
	public void destroy()
	{
		// nothing to do on destroy
	}
}
