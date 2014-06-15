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
package com.cgi.pacoshop.mock.sso.api;

import com.cgi.pacoshop.mock.sso.model.TermsAcceptedList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;


/**
 * Interface class for Web Service
 *
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Eugeniy Azarenkov <eugeniy.azarenkov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http://www.symmetrics.de/
 * @copyright	2013 symmetrics - a CGI Group brand
 */
@Path("/")
public interface SSOService
{

	/**
	 *  Response for GET request with User Id parameter.
	 * @param accountId User Id to import.
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @return User object in JSON.
	 * @throws InterruptedException When delay interrupts.
	 * @throws java.io.IOException When problems with file reading.
	 *
	 */
	@Path("readAccount")
	@GET
	@Produces("application/json;charset=UTF-8")
	Response readAccount(@QueryParam("accountId") String accountId, @QueryParam("platformId") String platformId,
                        @DefaultValue("0") @QueryParam("delay") int delay) throws IOException, InterruptedException;

	/**
	 * Response for GET request with params.
	 * @param code If integer param occur, then should return Status code equals param.
	 * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
	 * @throws Exception on error
	 */
	@GET
	@Path("/{a:readAccount|getaccount}/{code}")
	Response getProductError(@PathParam("code") int code) throws Exception;


	/**
	 * Response for GET request with params.
	 * @param email Input param for searching account.
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
	 * @throws Exception on error
	 */
	@GET
	@Path("getaccount")
	Response searchAccount(@QueryParam("email") String email, @QueryParam("delay") int delay) throws Exception;


	/**
	 * Updates the customer account with the TermsAcceptedList (initially created for the Opt-in term).
	 * @param accountId User Id to import.
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @param responseStatus optional fake param which allows to simulate different error codes like 404 etc.
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @param terms POST request body. JSON object.
	 *
	 * @return status error code.
	 *
	 * @throws InterruptedException in a case when the Thread.sleep(delay) command would be interrupted.
	 *
	 * see KS-1545 user story for details
	 */
	@POST
	@Path("accounts/{accountId}")
	@Consumes("application/json;charset=UTF-8")
	Response writeAccount(@PathParam("accountId") String accountId, @QueryParam("platformid") String platformId,
                         @QueryParam("code") Integer responseStatus, @QueryParam("delay") Integer delay,
                         TermsAcceptedList terms) throws InterruptedException;

}
