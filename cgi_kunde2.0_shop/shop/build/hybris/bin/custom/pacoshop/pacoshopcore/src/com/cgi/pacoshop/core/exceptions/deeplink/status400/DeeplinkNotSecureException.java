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
 * Deeplink exception for invalid fingerprints.
 * fingerprint or fingerprintSecretNo missing ("Der Checkout-Link ist nicht abgesichert")
 * Set http status 400
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
public class DeeplinkNotSecureException extends AbstractBadRequestException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.security.missing.fingerprint";

	/**
	 * Instantiates a new Deeplink not secure exception.
	 *
	 * @param message the message
	 */
	public DeeplinkNotSecureException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setAdditionalInformation(message);
	}


}
