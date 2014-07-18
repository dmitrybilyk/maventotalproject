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
 * SAPException.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPException extends Exception
{
	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message field.
	 */
	public SAPException(final String message)
	{
		super(message);
	}

	/**
	 * Contructor.
	 *
	 * @param message
	 *            the message field.
	 * @param exception
	 *            the exception that created the exception.
	 */
	public SAPException(final String message, final Throwable exception)
	{
		super(message, exception);
	}
}
