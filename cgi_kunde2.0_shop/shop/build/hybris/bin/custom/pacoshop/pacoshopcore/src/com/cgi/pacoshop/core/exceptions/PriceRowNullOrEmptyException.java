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
package com.cgi.pacoshop.core.exceptions;


/**
 * If Price from Import product is null or empty throw this exception
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 17, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see        'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PriceRowNullOrEmptyException extends RuntimeException
{
	/**
	 * Constructor.
	 * @param message - error message.
	 */
	public PriceRowNullOrEmptyException(final String message)
	{
		super(message);
	}
}
