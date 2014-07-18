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
package com.cgi.pacoshop.core.exceptions.deeplink.status403;


/**
 * Deeplink exception for invalid fingerprints.
 * The system gives the html status and in a second line the detailed information ("Achtung! Manipulierte Daten im Checkout-Link").
 * Set http status 403
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Apr 18, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class DeeplinkCompromizedException extends AbstractForbiddenException
{
	private static final String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.security.compromised.deeplink";

	/** Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *

	 * @param productId the product id
	 */
	public DeeplinkCompromizedException(final String productId)
	{

		super(EXCEPTION_MESSAGE);
		setProductId(productId);

	}


}
