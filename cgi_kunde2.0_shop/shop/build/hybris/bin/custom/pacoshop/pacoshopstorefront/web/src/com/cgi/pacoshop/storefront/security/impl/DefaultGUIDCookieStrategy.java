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

import com.cgi.pacoshop.storefront.interceptors.beforecontroller.RequireHardLoginBeforeControllerHandler;
import com.cgi.pacoshop.storefront.security.GUIDCookieStrategy;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;
import org.springframework.web.util.CookieGenerator;


/**
 * Default implementation of {@link GUIDCookieStrategy}
 *

 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultGUIDCookieStrategy implements GUIDCookieStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultGUIDCookieStrategy.class);

	private final SecureRandom  random;
	private final MessageDigest sha;

	private CookieGenerator cookieGenerator;

	/**
	 * Instantiates a new Default gUID cookie strategy.
	 *
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public DefaultGUIDCookieStrategy() throws NoSuchAlgorithmException
	{
		random = SecureRandom.getInstance("SHA1PRNG");
		sha = MessageDigest.getInstance("SHA-1");
		Assert.notNull(random);
		Assert.notNull(sha);
	}

	@Override
	public void setCookie(final HttpServletRequest request, final HttpServletResponse response)
	{
		// hybris is running against nginx, all https requests nginx transform to https
		/*
		if (!request.isSecure())
		{
			// We must not generate the cookie for insecure requests, otherwise there is not point doing this at all
			throw new IllegalStateException("Cannot set GUIDCookie on an insecure request!");
		}
		*/

		final String guid = createGUID();

		getCookieGenerator().addCookie(response, guid);
		request.getSession().setAttribute(RequireHardLoginBeforeControllerHandler.SECURE_GUID_SESSION_KEY, guid);

		if (LOG.isInfoEnabled())
		{
			LOG.info("Setting guid cookie and session attribute: " + guid);
		}
	}

	@Override
	public void deleteCookie(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (!request.isSecure())
		{
			LOG.error("Cannot remove secure GUIDCookie during an insecure request. I should have been called from a secure page.");
		}
		else
		{
			// Its a secure page, we can delete the cookie
			getCookieGenerator().removeCookie(response);
		}
	}

	/**
	 * Create gUID.
	 *
	 * @return the string
	 */
	protected String createGUID()
	{
		final String randomNum = String.valueOf(getRandom().nextInt());
		final byte[] result = getSha().digest(randomNum.getBytes(Charset.forName("UTF-8")));
		return String.valueOf(Hex.encodeHex(result));
	}

	/**
	 * Gets cookie generator.
	 *
	 * @return the cookie generator
	 */
	protected CookieGenerator getCookieGenerator()
	{
		return cookieGenerator;
	}

	/**
	 * Sets cookie generator.
	 *
	 * @param cookieGenerator            the cookieGenerator to set
	 */
	@Required
	public void setCookieGenerator(final CookieGenerator cookieGenerator)
	{
		this.cookieGenerator = cookieGenerator;
	}

	/**
	 * Gets random.
	 *
	 * @return the random
	 */
	protected SecureRandom getRandom()
	{
		return random;
	}

	/**
	 * Gets sha.
	 *
	 * @return the sha
	 */
	protected MessageDigest getSha()
	{
		return sha;
	}
}
