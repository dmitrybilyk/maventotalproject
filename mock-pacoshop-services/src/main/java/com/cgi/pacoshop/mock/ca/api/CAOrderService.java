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
package com.cgi.pacoshop.mock.ca.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


/**
 * Interface class for Web Service.
 * 
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@Path("/")
public interface CAOrderService
{


    /**
     * 
     * CA create order call.
     * 
     * @param headers
     *            the headers
     * @param in
     *            the data
     * @param delay
     *            delay for which the request will wait before being transmitted.
     * @return a response.
     * @throws InterruptedException
     *             if the thread sleeps for too long.
     */
    @Path("orders")
    @PUT
    @Consumes("application/json;charset=UTF-8")
    Response createOrder(@Context final HttpHeaders headers, final byte[] in, @DefaultValue("0") @QueryParam("delay") int delay)
            throws InterruptedException;




    /**
     * CA order error.
     * 
     * @param code
     *            If integer param occur, then should return Status code equals param.
     * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
     * @throws Exception
     *             on error
     */
    @Path("/{a:orders}/{code}")
    @PUT
    @Consumes("application/json;charset=UTF-8")
    Response createOrderError(@PathParam("code") int code) throws Exception;

}
