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
package com.cgi.pacoshop.mock.address.service;


import com.cgi.pacoshop.mock.address.model.DeliveryAddress;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;


/**
 * Address Validation service.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 04, 2014
 * @module mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
@Path("/")
@Produces("application/json;charset=UTF-8")
public interface AddressValidationService
{
	/**
	 * Response for POST request from server delivery address validation mechanism.
	 *
	 * @param offerOrigin     Product offer origin.
	 * @param offerId         Product offer ID.
	 * @param deliveryAddress delivery address.
	 * @param delay delay for which the request will wait before being transmitted.
	 * @return String - Address Validation result.
	 * @throws javax.xml.bind.JAXBException on error.
	 * @throws InterruptedException
	 *             if the thread sleeps for too long.
	 */
	@POST
	@Path("/{offerOrigin}/offer/{offerId}/deliverability")
	@Consumes("application/json;charset=UTF-8")
	String validateAddress(@PathParam("offerOrigin") String offerOrigin, @PathParam("offerId") String offerId,
											  @RequestBody DeliveryAddress deliveryAddress,
											  @DefaultValue("0") @QueryParam("delay") int delay) throws JAXBException, InterruptedException;

	/**
	 * Delivery address check error.
	 *
	 * @param code
	 *            If integer param occur, then should return Status code equals param.
	 * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
	 * @throws Exception
	 *             on error
	 */
	@Path("/{offerOrigin}/order/{offerId}/deliverability/{code}")
	@POST
	@Consumes("application/json;charset=UTF-8")
	Response createAddressCheckError(@PathParam("code") int code) throws Exception;
}
