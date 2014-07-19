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
package com.cgi.pacoshop.mock.sso.model;

/**
 * Interface for User and Platform entity. Used in Excel reader to make common approach for reading data.
 *
 * @module sso mock
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface SSOEntity
{
	/**
	 * Set user data.
	 *
	 * @param fieldName User or platform field name.
	 * @param value     Field value.
	 * @throws NoSuchFieldException   No such field in user object.
	 * @throws IllegalAccessException Field can not be accessed in user object.
	 */
	void setData(final String fieldName, final Object value) throws NoSuchFieldException, IllegalAccessException;
}
