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


import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * If the customer enters the system with a URL which was syntactically invalid, the system shows status code 400
 * ("Sorry! Error: 400. Bad request. Die Anfrage-Nachricht war fehlerhaft aufgebaut.
 * The system gives the html status and in a second line the detailed information (this depends on the situation).
 *
 * Whenever you the exception is thrown, Spring will return the http status 400.
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Apr 18, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@ResponseStatus (HttpStatus.BAD_REQUEST)
public abstract class AbstractBadRequestException extends PacoShopWebException
{
	private static final String FAMILY_MESSAGE = "checkout.multi.errorPage.exceptions.badrequest";
	private static final int    HTTP_STATUS    = 400;

	/**
	 * Instantiates a new Bad request exception.
	 * @param message the message
	 */
	public AbstractBadRequestException(final String message)
	{
		super(message);
		setFamilyExceptionMessage(FAMILY_MESSAGE);
		setHttpStatus(HTTP_STATUS);
	}

}
