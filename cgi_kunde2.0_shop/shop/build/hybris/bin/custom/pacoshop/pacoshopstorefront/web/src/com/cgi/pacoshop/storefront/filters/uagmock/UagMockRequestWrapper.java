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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


/**
 * UagMockFilter - mock for the user access gateway
 * 
 * @module pacoshopstorefront
 * @version 1.0v Jan 16, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class UagMockRequestWrapper extends HttpServletRequestWrapper
{

	private final Map<String, String> httpHeaderMap;
	private final int httpPort;

	/**
	 * 
	 * @param request
	 *           the servlet request
	 * @param pHttpPort
	 *           the port on which the server listens for insecure plain http requests
	 * 
	 */
	public UagMockRequestWrapper(final HttpServletRequest request, final int pHttpPort)
	{
		super(request);

		this.httpHeaderMap = new HashMap<>();

		this.httpPort = pHttpPort;
	}


	@Override
	public boolean isSecure()
	{
		return false;
	}

	/**
	 * 
	 * @param name
	 *           - http header name
	 * @param value
	 *           - http header value
	 */
	public void addHttpHeader(final String name, final String value)
	{
		httpHeaderMap.put(name, value);
	}

	/**
	 * 
	 * @param name
	 *           - get parameter from http headers
	 * @return - value of http header
	 */
	@Override
	public String getParameter(final String name)
	{
		String paramValue = super.getParameter(name);
		if (paramValue == null)
		{
			paramValue = httpHeaderMap.get(name);
		}
		return paramValue;
	}

	/**
	 * 
	 * @return header names
	 */
	@Override
	public Enumeration getHeaderNames()
	{
		final HttpServletRequest request = (HttpServletRequest) getRequest();
		final List<String> list = new ArrayList<>();
		for (final Enumeration e = request.getHeaderNames(); e.hasMoreElements();)
		{
			list.add(e.nextElement().toString());
		}
		for (final String s : httpHeaderMap.keySet())
		{
			list.add(s);
		}
		return Collections.enumeration(list);
	}

	/**
	 * 
	 * @param name
	 *           - header name
	 * @return test
	 */
	@Override
	public String getHeader(final String name)
	{
		final Object value = httpHeaderMap.get(name);
		if (value != null)
		{
			return value.toString();
		}
		else
		{
			return ((HttpServletRequest) getRequest()).getHeader(name);
		}
	}

	/**
	 * 
	 * @return - return 9001
	 */
	@Override
	public int getServerPort()
	{
		return this.httpPort;
	}

	/**
	 * 
	 * @return return http
	 */
	@Override
	public String getScheme()
	{
		return "http";
	}
}
