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
package com.cgi.pacoshop.fulfilmentprocess.client;

/**
 * Represents the status of a request to CA.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <p.b@symmetrics.de>
 * @version 1.0v Jan 10, 2014
 * @module hybris - pacoshofulfilmentprocess
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see 'https://wiki.hybris.com/'
 */
public enum CAClientStatus
{
	SUCCESS, PARTIAL, FAIL
}
