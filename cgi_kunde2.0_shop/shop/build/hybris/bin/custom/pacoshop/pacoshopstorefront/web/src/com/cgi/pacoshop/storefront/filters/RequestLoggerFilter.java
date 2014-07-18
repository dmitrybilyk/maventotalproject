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

import com.google.common.base.Stopwatch;
import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.Enumeration;

/**
 * RequestLoggerFilter - log additional info about request.
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
public class RequestLoggerFilter extends OncePerRequestFilter
{
	private static final Logger LOG = Logger.getLogger(RequestLoggerFilter.class.getName());

	@Override
	public void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
								 final FilterChain filterChain) throws IOException, ServletException
	{
		if (LOG.isDebugEnabled())
		{
			final String requestDetails = buildRequestDetails(request);

			if (LOG.isDebugEnabled())
			{
				LOG.debug(requestDetails + "Begin");
			}

			logHeaders(request);
			logCookies(request);

			final ResponseWrapper wrappedResponse = new ResponseWrapper(response);

			final Stopwatch stopwatch = new Stopwatch();
			stopwatch.start();
			try
			{
				filterChain.doFilter(request, wrappedResponse);
			}
			finally
			{
				stopwatch.stop();
				final int status = wrappedResponse.getStatus();

				if (status != 0)
				{
					LOG.debug(requestDetails + stopwatch.toString() + " (" + status + ")");
				}
				else
				{
					LOG.debug(requestDetails + stopwatch.toString());
				}
			}

			return;
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Log headers of request.
	 *
	 * @param httpRequest Http request.
	 */
	protected void logHeaders(final HttpServletRequest httpRequest)
	{
		final Enumeration headerNames = httpRequest.getHeaderNames();
		while (headerNames.hasMoreElements())
		{
			String key = (String) headerNames.nextElement();
			String value = httpRequest.getHeader(key);

			LOG.debug(String.format("HEADER Name: [%s] Value: [%s]", key, value));
		}
	}

	/**
	 * Log cookies of request.
	 *
	 * @param httpRequest Http request.
	 */
	protected void logCookies(final HttpServletRequest httpRequest)
	{
		final Cookie[] cookies = httpRequest.getCookies();
		if (cookies != null)
		{
			for (final Cookie cookie : cookies)
			{
				LOG.debug(
						String.format(
								"COOKIE Name: [%s] Path: [%s] Value: [%s]",
								cookie.getName(),
								cookie.getPath(),
								cookie.getValue()
						)
				);
			}
		}
	}

	/**
	 * Build request details.
	 *
	 * @param request Http request.
	 * @return Request details.
	 */
	protected String buildRequestDetails(final HttpServletRequest request)
	{
		String queryString = request.getQueryString();
		if (queryString == null)
		{
			queryString = "";
		}

		final String requestUri = request.getRequestURI();

		final String securePrefix = request.isSecure() ? "s" : " ";
		final String methodPrefix = request.getMethod().substring(0, 1);

		return securePrefix + methodPrefix + " [" + requestUri + "] [" + queryString + "] ";
	}

	/**
	 * Response wrapper.
	 */
	protected static class ResponseWrapper extends HttpServletResponseWrapper
	{
		private int status;

		/**
		 * Response wrapper constructor.
		 * @param response Response object.
		 */
		public ResponseWrapper(final HttpServletResponse response)
		{
			super(response);
		}

		@Override
		public void setStatus(final int status)
		{
			super.setStatus(status);
			this.status = status;
		}

		/**
		 * Get status.
		 * @return Status text.
		 */
		public int getStatus()
		{
			return status;
		}

		@Override
		public void sendError(final int status, final String msg) throws IOException
		{
			super.sendError(status, msg);
			this.status = status;
		}

		@Override
		public void sendError(final int status) throws IOException
		{
			super.sendError(status);
			this.status = status;
		}
	}
}
