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
package com.cgi.pacoshop.core.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Price Row doesn't exist in product entity
 * If Price Row is not correct or doesn't exist, return proper error page
 *
 * @module pacoshopcore
 * @version 1.0v dec 26, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Product doesn't have Price Row")
public class ProductDontHavePriceRowException extends RuntimeException
{
	/**
	 * Constructor.
	 * @param message - error message.
	 */
	public ProductDontHavePriceRowException(final String message)
	{
		super(message);
	}
}
