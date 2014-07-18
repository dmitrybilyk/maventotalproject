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
package com.cgi.pacoshop.storefront.security.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test for session+cache.
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
@UnitTest
public class WebHttpSessionRequestCacheUnitTest
{
	private final WebHttpSessionRequestCache cache = new WebHttpSessionRequestCache();

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	/**
	 * Prepare test.
	 */
	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test save request.
	 */
	@Test
	public void testSaveRequest()
	{

		BDDMockito.given(request.getRequestURL()).willReturn(new StringBuffer("dummy"));
		BDDMockito.given(request.getScheme()).willReturn("dummy");
		BDDMockito.given(request.getHeader("referer")).willReturn("some blah");

		cache.saveRequest(request, response);


		Mockito.verify(request.getSession()).setAttribute(Mockito.eq("SPRING_SECURITY_SAVED_REQUEST"),
				Mockito.argThat(new DefaultSavedRequestArgumentMatcher("some blah")));
	}

	/**
	 * Default saved request class.
	 */
	static class DefaultSavedRequestArgumentMatcher extends ArgumentMatcher<DefaultSavedRequest>
	{

		private final String url;

		DefaultSavedRequestArgumentMatcher(final String url)
		{
			this.url = url;
		}

		@Override
		public boolean matches(final Object argument)
		{
			if (argument instanceof DefaultSavedRequest)
			{
				final DefaultSavedRequest arg = (DefaultSavedRequest) argument;
				return url.equals(arg.getRedirectUrl());
			}
			return false;
		}
	}
}
