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
package com.cgi.pacoshop.core.exceptions.deeplink.status400;


/**
 * Thrown if neither a productId nor a cartId present ("Im Checkout-Link wurde kein Warenkorb übergeben")
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Apr 18, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class NoProductsSpecifiedException extends AbstractBadRequestException
{

	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.notspecified.products";


	/**
	 * Instantiates a new No products specified exception.
	 */
	public NoProductsSpecifiedException()
	{
		super(EXCEPTION_MESSAGE);
	}

	/**
	 * Instantiates a new No products specified exception.
	 * @param message the message
	 */
	public NoProductsSpecifiedException(final String message)
	{
		this();
		setAdditionalInformation(message);
	}


}
