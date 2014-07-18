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
package com.cgi.pacoshop.fulfilmentprocess.exceptions;

/**
 * Represents an Exception while converting an object to a JSON String.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class JSONConversionException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = 2239072776452411710L;


	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message of the exception.
	 * @param cause
	 *            the cause of the exception.
	 */
	public JSONConversionException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

}
