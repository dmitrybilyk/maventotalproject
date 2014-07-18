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
package com.cgi.pacoshop.core.daos;


import com.cgi.pacoshop.core.exceptions.deeplink.status400.PortalCallbackException;

/**
 * Dao service for getting data from Hybris for deeplink callback interface.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface DeeplinkCallbackDAO
{
	/**
	 * Method get callback url based on portalId, if doesn't exist throw exception.
	 * @param portalId string
	 * @return String url for callback;
	 * @throws com.cgi.pacoshop.core.exceptions.deeplink.status400.PortalCallbackException if portalId doesn't exist in Hybris.
	 */
	String getDeeplinkCallbackUrl(String portalId) throws PortalCallbackException;
}

