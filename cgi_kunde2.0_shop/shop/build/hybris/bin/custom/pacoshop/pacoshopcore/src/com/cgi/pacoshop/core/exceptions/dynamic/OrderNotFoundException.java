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
package com.cgi.pacoshop.core.exceptions.dynamic;

import com.cgi.pacoshop.core.exceptions.deeplink.status404.PageNotFoundException;

/**
 * Order not found exception. This is 404 error page.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jun 16, 2014
 * @module hybris - pacoshopstorefront
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 */
public class OrderNotFoundException extends PageNotFoundException
{
	/**
	 * Instantiates a new Bad request exception.
	 *
	 * @param message the message
	 */
	public OrderNotFoundException(final String message)
	{
		super(message);
	}
}
