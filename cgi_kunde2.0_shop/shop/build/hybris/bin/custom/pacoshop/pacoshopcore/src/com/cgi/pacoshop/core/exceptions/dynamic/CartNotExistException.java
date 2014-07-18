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
package com.cgi.pacoshop.core.exceptions.dynamic;


import com.cgi.pacoshop.core.exceptions.deeplink.status404.AbstractNotFoundException;

/**
 * Exception thrown when cart not exist.
 *
 * @module shop
 * @version 1.0v Jun 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Erofeev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CartNotExistException extends AbstractNotFoundException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.cart.not.exist";

	/**
	 * Constructor.
	 * @param message - error message.
	 */
	public CartNotExistException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setAdditionalInformation(message);
	}
}
