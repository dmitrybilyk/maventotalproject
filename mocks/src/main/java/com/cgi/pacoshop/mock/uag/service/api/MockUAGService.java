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
package com.cgi.pacoshop.mock.uag.service.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Simple REST com.cgi.pacoshop.mock to calculate the deeplink fingerprint value.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module mocks
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "wiki.hybris.com"
 */
@Path("/")
public interface MockUAGService
{
	/**
	 * Calculates the deeplink fingerprint value.
	 *
	 * @param url used to calculate the SHA1 checksum value in form of HEX String (fingerprint). URL should contain the
	 *            'fingerprintsecretno' param as mandatory string.
	 * @return plain text response with an fingerprint hex value string.
	 */
	@GET
	@Path("fingerprint")
	@Produces("text/html")
	String getFingerprint(@QueryParam("url") String url);
}
