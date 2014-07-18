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
 * Represents the CA interface general Exception.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CAException extends Exception
{


	/**
	 *
	 */
	private static final long serialVersionUID = -5600488720991123249L;


	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message.
	 */
	public CAException(final String message)
	{
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message
	 * @param cause
	 *            the original exception that trigger this exception.
	 */
	public CAException(final String message, final Throwable cause)
	{
		super(message, cause);
	}


}
