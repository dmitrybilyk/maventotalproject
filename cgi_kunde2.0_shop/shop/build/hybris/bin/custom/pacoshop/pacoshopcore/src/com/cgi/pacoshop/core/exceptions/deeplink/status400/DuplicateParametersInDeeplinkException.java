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
 * For a valid deeplink URL containing more than one parameter with the same name
 *
 * Return HTTP status 400.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jun 03, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Bilyk <dmitry.bilyk@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 */
public class DuplicateParametersInDeeplinkException extends AbstractBadRequestException
{
    private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.security.duplicate.parameters";

	/**
	 * Instantiates a new More than two products in deeplink exception.
    *
    * @param message name of duplicated parameters
	 */
	public DuplicateParametersInDeeplinkException(final String message)
	{
		super(EXCEPTION_MESSAGE);
        setAdditionalInformation(message);
	}
}
