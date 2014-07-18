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
package com.cgi.pacoshop.storefront.security.cookie;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test for cookie generation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01, 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class EnhancedCookieGeneratorTest
{
	private static final String JSESSIONID = "JSESSIONID";
	private static final int NEVER_EXPIRES = -1;
	private final EnhancedCookieGenerator cookieGenerator = new EnhancedCookieGenerator();

	@Mock
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
		cookieGenerator.setCookieDomain("what a domain");
		cookieGenerator.setCookieMaxAge(Integer.valueOf(NEVER_EXPIRES));

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	/**
	 * Test client side cookies.
	 */
	@Test
	public void testClientSideCookieDefaultPath()
	{
		cookieGenerator.setCookieName(JSESSIONID);
		cookieGenerator.setHttpOnly(false); //client side
		cookieGenerator.addCookie(response, "cookie_monster");

		final Cookie expectedCookie = new Cookie(JSESSIONID, "cookie_monster");
		expectedCookie.setPath("/");
		expectedCookie.setSecure(false);
		expectedCookie.setMaxAge(NEVER_EXPIRES);
		expectedCookie.setDomain("what a domain");

		Mockito.verify(response).addCookie(Mockito.argThat(new CookieArgumentMatcher(expectedCookie)));
		assertNoHeaderAdjustments();
	}

	/**
	 * Test client side cookies for dynamic path.
	 */
	@Test
	public void testClientSideCookieDynamicPath()
	{
		cookieGenerator.setCookieName(JSESSIONID);
		cookieGenerator.setHttpOnly(false); //client side
		cookieGenerator.setCookieSecure(true);
		cookieGenerator.setUseDefaultPath(false);

		BDDMockito.given(request.getContextPath()).willReturn("/some_path");
		cookieGenerator.addCookie(response, "cookie_monster");

		final Cookie expectedCookie = new Cookie(JSESSIONID, "cookie_monster");
		expectedCookie.setPath("/some_path");
		expectedCookie.setSecure(true);
		expectedCookie.setMaxAge(NEVER_EXPIRES);
		expectedCookie.setDomain("what a domain");

		Mockito.verify(response).addCookie(Mockito.argThat(new CookieArgumentMatcher(expectedCookie)));
		assertNoHeaderAdjustments();
	}

	/**
	 * Test server cookies for default path.
	 */
	@Test
	public void testServerSideCookieDefaultPath()
	{
		cookieGenerator.setCookieName("guid");
		cookieGenerator.setHttpOnly(true); //server side

		BDDMockito.given(request.getContextPath()).willReturn("/some_path");
		cookieGenerator.addCookie(response, "cookie_monster");

		final Cookie expectedCookie = new Cookie("guid", "cookie_monster");
		expectedCookie.setPath("/");
		expectedCookie.setSecure(false);
		expectedCookie.setMaxAge(NEVER_EXPIRES);
		expectedCookie.setDomain("what a domain");

		Mockito.verify(response).addCookie(Mockito.argThat(new CookieArgumentMatcher(expectedCookie)));
		Mockito.verify(response).addHeader(EnhancedCookieGenerator.HEADER_COOKIE,
				"guid=cookie_monster; Domain=\"what a domain\"; Path=/; HttpOnly");
	}

	/**
	 * Test server cookies for dynamic path.
	 */
	@Test
	public void testServerSideCookieDynamicPath()
	{
		cookieGenerator.setCookieName(JSESSIONID);
		cookieGenerator.setHttpOnly(true); //server side
		cookieGenerator.setUseDefaultPath(false);

		BDDMockito.given(request.getContextPath()).willReturn("/some_path");
		cookieGenerator.addCookie(response, "cookie_monster");

		final Cookie expectedCookie = new Cookie(JSESSIONID, "cookie_monster");
		expectedCookie.setPath("/some_path");
		expectedCookie.setSecure(false);
		expectedCookie.setMaxAge(NEVER_EXPIRES);
		expectedCookie.setDomain("what a domain");

		Mockito.verify(response).addCookie(Mockito.argThat(new CookieArgumentMatcher(expectedCookie)));
		Mockito.verify(response).addHeader(EnhancedCookieGenerator.HEADER_COOKIE,
				"JSESSIONID=cookie_monster; Domain=\"what a domain\"; Path=/; HttpOnly");
	}

	private void assertNoHeaderAdjustments()
	{
		Mockito.verify(response, Mockito.times(0)).addHeader(Mockito.anyString(), Mockito.anyString());
	}

	/**
	 * Cookies agruments matcher class.
	 */
	private static class CookieArgumentMatcher extends ArgumentMatcher<Cookie>
	{
		private final Cookie expectedCookie;

		CookieArgumentMatcher(final Cookie cookie)
		{
			this.expectedCookie = cookie;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.mockito.ArgumentMatcher#matches(java.lang.Object)
		 */
		@Override
		public boolean matches(final Object argument)
		{
			if (argument instanceof Cookie)
			{
				final Cookie givenCookie = (Cookie) argument;
				if (givenCookie.getSecure() == expectedCookie.getSecure()
						&& givenCookie.getMaxAge() == expectedCookie.getMaxAge()
						&& givenCookie.getName().equals(expectedCookie.getName())
						&& (givenCookie.getPath().equals(expectedCookie.getPath())
						|| givenCookie.getPath().equals(expectedCookie.getPath()))
						&& givenCookie.getValue().equals(expectedCookie.getValue())
						&& (givenCookie.getDomain().equals(expectedCookie.getDomain())
						|| givenCookie.getDomain().equals(expectedCookie.getDomain())))
				{
					return true;
				}
				Assert.fail("Expected \n[" + ToStringBuilder.reflectionToString(expectedCookie) + "]\n but got \n["
						+ ToStringBuilder.reflectionToString(argument) + "]");
			}
			return false;
		}
	}
}
