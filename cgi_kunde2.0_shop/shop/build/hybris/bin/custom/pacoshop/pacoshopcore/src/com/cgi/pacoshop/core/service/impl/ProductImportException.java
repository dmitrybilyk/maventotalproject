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
package com.cgi.pacoshop.core.service.impl;


/**
 * Exception for diagnostics and logging the Product import errors.
 *
 * @module hybris
 * @version 1.0v Jan 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductImportException extends RuntimeException
{
	/**
	 * Default public constructor.
	 */
	public ProductImportException()
	{
		super();
	}

	/**
	 * Public constructor.
	 *
	 * @param message Error message
	 */
	public ProductImportException(final String message)
	{
		super(message);
	}

	/**
	 * Public constructor that allows to produce formatted error messages.
	 *
	 * @param message Error message
	 * @param objects Error message parameters
	 */
	public ProductImportException(final String message, final Object... objects)
	{
		super(String.format(message, objects));
	}

	/**
	 * Public constructor.
	 *
	 * @param message Error message
	 * @param cause Exception cause the error
	 */
	public ProductImportException(final String message, final Throwable cause)
	{
		super(message, cause);
	}

	/**
	 * Public constructor.
	 *
	 * @param cause Exception cause the error
	 */
	public ProductImportException(final Throwable cause)
	{
		super(cause);
	}

	/**
	 * Public constructor.
	 *
	 * @param message Error message
	 * @param cause Exception cause the error
	 * @param enableSuppression If suppression is enabled.
	 * @param writableStackTrace If stacktrace is writable.
	 */
	protected ProductImportException(final String message, final Throwable cause, final boolean enableSuppression,
												final boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
