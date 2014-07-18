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
 * The product lacks mandatory data, for example the price information ("Der Checkout-Link bezieht sich auf ein Produkt.
 * zu dem invalide Daten vorliegen")
 * Set http status 404
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Jun 13, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductQuantityNotAllowedException extends AbstractNotFoundException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.invalid.product.quantity";

	/**
	 * Constructor.
	 * @param message name of duplicated parameters
	 */
	public ProductQuantityNotAllowedException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setAdditionalInformation(message);
	}
}
