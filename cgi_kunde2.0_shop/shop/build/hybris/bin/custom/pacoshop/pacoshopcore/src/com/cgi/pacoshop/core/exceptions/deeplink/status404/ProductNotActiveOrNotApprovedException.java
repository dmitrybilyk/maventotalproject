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
package com.cgi.pacoshop.core.exceptions.deeplink.status404;


/**
 * The product is not active or not approved ("Der Checkout-Link bezieht sich auf ein Produkt mit ungeklärtem Status")
 * Set http status 404
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
public class ProductNotActiveOrNotApprovedException extends AbstractNotFoundException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.invalid.status.product";

	/**
	 * Constructor.
	 * @param productId the product id
	 */
	public ProductNotActiveOrNotApprovedException(final String productId)
	{
		super(EXCEPTION_MESSAGE);
		setProductId(productId);
	}
}
