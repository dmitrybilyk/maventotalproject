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
package com.cgi.pacoshop.core.service.security;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface for DeeplinkSecurityServiceImpl.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface DeeplinkSecurityService
{
	/**
	 * Validates fingerprint from portal with calculated hybris fingerprint.
	 *
	 * @param request - httpServletRequest
	 */
	void validate(HttpServletRequest request);
}
