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
package com.cgi.pacoshop.core.exceptions.deeplink.status404;


/**
 * The product does not exist in the hybris online catalogue belonging to the site ("Der Checkout-Link bezieht sich auf ein nicht katalogisiertes Produkt")
 * Set http status 404
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Apr 22, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductNotExistsInOnlineCatalogException extends AbstractNotFoundException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.notfound.onlinecatalog.product";

	/**
	 * Instantiates a new Product not exists in online catalog exception.
	 *
	 * @param productId the product id
	 */
	public ProductNotExistsInOnlineCatalogException(final String productId)
	{
		super(EXCEPTION_MESSAGE);
		setProductId(productId);
	}
}
