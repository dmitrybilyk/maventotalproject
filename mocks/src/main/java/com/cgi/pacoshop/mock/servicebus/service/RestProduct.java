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
package com.cgi.pacoshop.mock.servicebus.service;


import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


/**
 * Rest Interface for single product
 * 
 * 
 * @module MockServiceBus
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@cgi.com>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see
 * 
 */

@Produces("application/json")
public interface RestProduct
{
    /**
     * Response for GET request without params.
     * 
     * @param delay
     *            just for testing. There is could be passed delay time im milisecond.
     * @return Collection SingleProduct objects in JSON.
     * @throws InterruptedException
     *             on error
     */
    @GET
    @Path("subscription")
    String getSubscriptionProduct(@DefaultValue("0") @QueryParam("delay") int delay) throws InterruptedException;


    /**
     * Response for GET request without params.
     * 
     * @param delay
     *            just for testing. There is could be passed delay time im milisecond.
     * @return Collection SubscriptionProducts objects in JSON.
     * @throws InterruptedException
     *             on error
     */
    @GET
    @Path("single")
    String getSingleProduct(@DefaultValue("0") @QueryParam("delay") int delay) throws InterruptedException;


    /**
     * Response for GET request with params.
     * 
     * @param code
     *            If integer param occur, then should return Status code equals param.
     * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
     * @throws Exception
     *             on error
     */
    @GET
    @Path("/{a:single|subscription|a:periodicorders}/{code}")
    Response getProductError(@PathParam("code") int code) throws Exception;

    /**
     * Create single order.
     * 
     * @param headers
     *            the headers.
     * @param in
     *            the post content.
     * @param delay
     *            the millisecond to wait until returning a response.
     * @return a response.
     * @throws InterruptedException
     *             if the delay failed.
     */
    @PUT
    @Path("singleorders/create")
    @Consumes("application/json;charset=UTF-8")
    Response createSingleOrder(@Context final HttpHeaders headers, final byte[] in,
                               @DefaultValue("0") @QueryParam("delay") int delay) throws InterruptedException;

    /**
     * Create periodic order.
     * 
     * @param headers
     *            the headers.
     * @param in
     *            the post content.
     * @param delay
     *            the millisecond to wait until returning a response.
     * @return a response.
     * @throws InterruptedException
     *             if the delay failed.
     */
    @PUT
    @Path("periodicorders/create")
    @Consumes("application/json;charset=UTF-8")
    Response createPeriodicsOrder(@Context final HttpHeaders headers, final byte[] in,
                                  @DefaultValue("0") @QueryParam("delay") int delay) throws InterruptedException;



}
