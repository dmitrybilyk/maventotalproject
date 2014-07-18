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

import com.cgi.pacoshop.storefront.constants.WebConstants;
import com.cgi.pacoshop.storefront.web.wrappers.UrlEncodeHttpRequestWrapper;
import de.hybris.platform.acceleratorfacades.urlencoder.UrlEncoderFacade;
import de.hybris.platform.acceleratorfacades.urlencoder.data.UrlEncoderData;
import de.hybris.platform.acceleratorfacades.urlencoder.data.UrlEncoderPatternData;
import de.hybris.platform.servicelayer.session.SessionService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * This filter inspects the url and inject the url attributes if any for that CMSSite. Calls facades to fetch the list
 * of attributes and encode them in the URL.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class UrlEncoderFilter extends OncePerRequestFilter
{
	/**
	 * Default logger.
	 */
	private static final Logger LOG = Logger.getLogger(UrlEncoderFilter.class.getName());
	private UrlEncoderFacade urlEncoderFacade;
	private SessionService sessionService;

	/**
	 * Do internal filter.
	 *
	 * @param request     Http request object.
	 * @param response    Http response object.
	 * @param filterChain Filter chain.
	 * @throws ServletException Servlet exception.
	 * @throws IOException      IO exception.
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
									final FilterChain filterChain) throws ServletException, IOException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug(" The incoming URL : [" + request.getRequestURL().toString() + "]");
		}
		final List<UrlEncoderData> urlEncodingAttributes = getUrlEncoderFacade().variablesForUrlEncoding();
		if (urlEncodingAttributes != null && !urlEncodingAttributes.isEmpty())
		{
			final HttpSession session = request.getSession(false);
			final UrlEncoderPatternData patternData = getUrlEncoderFacade().patternForUrlEncoding(request.getRequestURL().toString(),
					request.getContextPath(), (session != null && session.isNew()));
			final String patternBeforeProcessing = getSessionService().getAttribute(WebConstants.URL_ENCODING_ATTRIBUTES);
			final String pattern = "/" + patternData.getPattern();
			if (!StringUtils.equalsIgnoreCase(patternBeforeProcessing, pattern))
			{
				if (patternData.isRedirectRequired())
				{
					String redirectUrl = StringUtils.replace(request.getRequestURL().toString(), patternBeforeProcessing, pattern);
					final String queryString = request.getQueryString();
					if (StringUtils.isNotBlank(queryString))
					{
						redirectUrl = redirectUrl + "?" + URLEncoder.encode(queryString, "UTF-8");
						response.sendRedirect(redirectUrl);
					}
				}
				else
				{
					getUrlEncoderFacade().updateUrlEncodingData();
				}
			}
			getSessionService().setAttribute(WebConstants.URL_ENCODING_ATTRIBUTES, pattern);
			final UrlEncodeHttpRequestWrapper wrappedRequest = new UrlEncodeHttpRequestWrapper(request, patternData.getPattern());
			wrappedRequest.setAttribute(WebConstants.URL_ENCODING_ATTRIBUTES, pattern);
			wrappedRequest.setAttribute("originalContextPath", request.getContextPath());
			if (LOG.isDebugEnabled())
			{
				LOG.debug("ContextPath=[" + wrappedRequest.getContextPath() + "]" + " Servlet Path= ["
						+ wrappedRequest.getServletPath() + "]" + " Request Url= [" + wrappedRequest.getRequestURL() + "]");
			}
			filterChain.doFilter(wrappedRequest, response);
		}
		else
		{
			if (LOG.isDebugEnabled())
			{
				LOG.debug(" No URL attributes defined");
			}
			request.setAttribute(WebConstants.URL_ENCODING_ATTRIBUTES, "");
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * Get url encoder facade.
	 *
	 * @return Encode facade.
	 */
	protected UrlEncoderFacade getUrlEncoderFacade()
	{
		return urlEncoderFacade;
	}

	/**
	 * Set encoder facade.
	 *
	 * @param urlEncoderFacade Set encoder facade.
	 */
	@Required
	public void setUrlEncoderFacade(final UrlEncoderFacade urlEncoderFacade)
	{
		this.urlEncoderFacade = urlEncoderFacade;
	}

	/**
	 * Get session service.
	 *
	 * @return Serssion service.
	 */
	protected SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * Set session service.
	 *
	 * @param sessionService Session service.
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}
