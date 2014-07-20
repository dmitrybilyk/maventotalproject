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
package com.ownmocks.servicebus.service;


import java.io.IOException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * The mock for the service that retrieves customer addresses
 * by business partner ids.
 *
 * @version 1.0v Feb 27, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 *
 *
 */
@Produces("application/json")
public interface BusinessPartnerService
{
	/**
	 * Response for getBusinessPartnerId() call from ServiceBus interface.
	 *
	 * @param businessPartnerId ID of SAP business partner
	 * @param delay Delay supplied by the client to emulate the timeout
	 * @return Address of business partner
	 * @throws java.io.IOException
	 * @throws InterruptedException
	 */
	@Path("getBusinessPartner")
	@GET
	Response getBusinessPartner(@QueryParam("businessPartnerId") String businessPartnerId,
                               @DefaultValue("0") @QueryParam("delay") int delay) throws InterruptedException, IOException;


	/**
	 * Response for GET request with params.
	 * @param code If integer param occur, then should return Status code equals param.
	 * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
	 * @throws Exception on error
	 */
	@GET
	@Path("getBusinessPartner/{code}")
	Response getBusinessPartnerIdError(@PathParam("code") int code) throws Exception;
}
