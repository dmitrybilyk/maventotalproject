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
package com.cgi.pacoshop.core.exceptions.deeplink.status400;

/**
 * If url-callback worked and there are non-phisical product in the cart
 * "Im Checkout-Link wurde ein ungültiger Warenkorb übergeben"
 * Set http status 400
 *
 * @module pacoshopcore
 * @version 1.0v Apr 22, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PortalCallbackInvalidProductsInCartException extends AbstractBadRequestException
{

	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.non-physical.products";

	/**
	 * @param message the detail message. The detail message is saved for later retrieval by the
	 * method.
	 */
	public PortalCallbackInvalidProductsInCartException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setAdditionalInformation(message);
	}
}
