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
 * Exception is throwing if VAT rate doen't exist in Hybris or if more than one  differrent Vat Rate for same value
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 08, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class VATRateNotFoundException extends RuntimeException
{
	/**
	 * Constructor.
	 * @param message - error message.
	 */
	public VATRateNotFoundException(final String message)
	{
		super(message);
	}
}
