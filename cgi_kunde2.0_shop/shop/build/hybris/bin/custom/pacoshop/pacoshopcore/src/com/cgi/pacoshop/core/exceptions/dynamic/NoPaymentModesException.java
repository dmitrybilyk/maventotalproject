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


import com.cgi.pacoshop.core.exceptions.deeplink.status400.AbstractBadRequestException;


/**
 * Exception thrown when available methods is empty.
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Apr 22, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class NoPaymentModesException extends AbstractBadRequestException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.no.payment.methods";

	/**
	 * Constructor.
	 * @param message - error message.
	 */
	public NoPaymentModesException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setAdditionalInformation(message);
	}
}
