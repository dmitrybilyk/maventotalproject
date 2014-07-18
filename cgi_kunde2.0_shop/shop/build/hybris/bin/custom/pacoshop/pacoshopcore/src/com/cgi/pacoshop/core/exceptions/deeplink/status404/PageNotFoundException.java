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
 * Page not found exception.
 *
 * @module kunde_new
 * @version 1.0v May 28, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PageNotFoundException extends AbstractNotFoundException
{
	private static String EXCEPTION_MESSAGE = "checkout.multi.errorPage.exceptions.pagenotfound";
	private static final int HTTP_STATUS = 404;

    /**
     * Default constructor for PageNotFoundException.
     * @param message message
     */
	public PageNotFoundException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setHttpStatus(HTTP_STATUS);
		setAdditionalInformation(message);
	}
}
