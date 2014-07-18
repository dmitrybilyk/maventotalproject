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
 * Product Type Exception - means that product type doesn't exist in Hybris.
 * Here goes 2 line.
 *
 * @module pacoshopcore
 * @version 1.0v Jan 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see        " https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductTypeException extends RuntimeException
{
	/**
	 * Constructor.
	 * @param message - error message.
	 */
	public ProductTypeException(final String message)
	{
		super(message);
	}
}
