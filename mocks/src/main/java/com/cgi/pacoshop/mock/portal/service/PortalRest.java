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
package com.cgi.pacoshop.mock.portal.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBException;

/**
 * Rest interface with all mappings for portal deeplink callback.
 *
 *
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @version 1.0v Feb 04, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@Produces("application/xml")
public interface PortalRest
{
	/**
	 * Response for GET request from deeplink mechanism with querie param cartId.
	 * @param cartId String cart ID
	 * @return CartEntries - full order from checkout.
	 * @throws javax.xml.bind.JAXBException on error.
	 */
	@GET
	@Path("/")
	String getCartEntries(@QueryParam("cartid") String cartId) throws JAXBException;

}
