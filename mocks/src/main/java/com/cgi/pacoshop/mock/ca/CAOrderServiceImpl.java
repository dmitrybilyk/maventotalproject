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
package com.cgi.pacoshop.mock.ca;

import com.cgi.pacoshop.mock.ca.api.CAOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;


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
@Service("CAOrderService")
public class CAOrderServiceImpl implements CAOrderService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CAOrderServiceImpl.class.getName());

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.com.cgi.pacoshop.mock.ca.api.CAOrderService#createOrder(javax.ws.rs.core.HttpHeaders, byte[])
     */
    @Override
    public Response createOrder(final HttpHeaders headers, final byte[] in, final int delay) throws InterruptedException
    {
        try
        {
            final String request = new String(in, "UTF-8");
            LOGGER.info("*************************************************************************************************");
            LOGGER.info("Received request: /ca/orders with headers:");
            LOGGER.info(headers.toString());
            LOGGER.info("with content:");
            LOGGER.info(request);
            LOGGER.info("*************************************************************************************************");
        }
        catch (final UnsupportedEncodingException uee)
        {
            LOGGER.error("UnsupportedEncodingException for the request.");
        }

        if (delay > 0)
        {
            Thread.sleep(delay);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.com.cgi.pacoshop.mock.ca.api.CAOrderService#createOrderError(int)
     */
    @Override
    public Response createOrderError(final int code) throws Exception
    {
        return Response.status(code).build();
    }

}
