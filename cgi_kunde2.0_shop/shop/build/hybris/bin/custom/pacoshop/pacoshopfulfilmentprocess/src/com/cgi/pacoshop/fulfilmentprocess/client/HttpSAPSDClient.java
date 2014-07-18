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
package com.cgi.pacoshop.fulfilmentprocess.client;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPSDOrderEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntityPopulator;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * Implementation of the create_single_order of the SAP interface.
 * 
 * @module hybris - pacoshopfulfilmentprocess
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class HttpSAPSDClient implements SAPClient
{

    private static final Logger LOGGER = Logger.getLogger(HttpSAPSDClient.class);

    private SAPSDOrderEntityPopulator sapsdOrderEntityPopulator;

    private ConfigurationService configurationService;


    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.client.SAPClient#send(de.hybris.platform.core.model.order.AbstractOrderModel)
     */
    @Override
    public ClientStatus send(final OrderModel order)
    {
        ClientStatus status = null;
        try
        {
            final SAPSDOrderEntity sapSDOrderEntity = sapsdOrderEntityPopulator.populate(order);
            final HttpResponse response = doSend(sapSDOrderEntity);
            status = processResponse(response);
        }
        catch (final Exception e)
        {
            LOGGER.error("An exception arise when sending the order[" + order + "] to SAPSD", e);
            return ClientStatus.FAIL;
        }

        return status;
    }


    /**
     * Process the returns from the rest call.
     * 
     * @param response
     *            the response object containing the body and status.
     * @return the clientStatus.
     */
    private ClientStatus processResponse(final HttpResponse response) throws SAPException
    {
        if (response != null && response.getStatus() == HttpStatus.CREATED.value())
        {
            debug(LOGGER, "SAP response: %s", response);
        }
        else
        {
            if (response != null)
            {
                throw new SAPException("Error processing the response. " + response);
            }
            else
            {
                throw new SAPException("Error processing the response. The response is null!");
            }

        }
        return ClientStatus.SUCCESS;
    }


    /**
     * @param order
     * @return
     */
    private HttpResponse doSend(final SAPSDOrderEntity content) throws SAPException
    {

        final String uri = configurationService.getConfiguration().getString(
                PacoshopFulfilmentProcessConstants.INTERFACE.SERVICEBUS_SINGLEORDERS_CREATE_URL);
        final int connectionTimeout = configurationService.getConfiguration().getInt(
                PacoshopFulfilmentProcessConstants.INTERFACE.SERVICEBUS_SINGLEORDERS_CREATE_CONNECTION_TIMEOUT);
        final int socketTimeout = configurationService.getConfiguration().getInt(
                PacoshopFulfilmentProcessConstants.INTERFACE.SERVICEBUS_SINGLEORDERS_CREATE_SOCKET_TIMEOUT);

        HttpResponse httpResponse = null;

        ResponseEntity<String> result = null;
        final RestTemplate restTemplate = new RestTemplate();

        final SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(socketTimeout);
        try
        {
            debug(LOGGER, "Executing call[%s] with socket timeout[%s], connection timeout[%s] and content [%s]", uri,
                    Integer.valueOf(socketTimeout), Integer.valueOf(connectionTimeout), content);
            result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<SAPSDOrderEntity>(content), String.class);
            httpResponse = new HttpResponse(result.getStatusCode().value(), result.getBody());

        }
        catch (final HttpClientErrorException e)
        {
            /**
             * 
             * If we get a HTTP Exception display the error message
             */

            LOGGER.error("Error for request uri [" + uri + "] with [" + content + "]:  " + e);
            if (e.getStatusCode() != null)
            {
                httpResponse = new HttpResponse(e.getStatusCode().value(), e.getResponseBodyAsString());
            }
            else
            {
                httpResponse = new HttpResponse(0, e.getResponseBodyAsString());
            }


        }
        catch (final HttpServerErrorException hsee)
        {
            LOGGER.error("Error for request uri [" + uri + "] with [" + content + "]:  " + hsee);
            if (hsee.getStatusCode() != null)
            {
                httpResponse = new HttpResponse(hsee.getStatusCode().value(), hsee.getResponseBodyAsString());
            }
            else
            {
                httpResponse = new HttpResponse(0, hsee.getResponseBodyAsString());
            }
        }
        catch (final Exception e)
        {
            LOGGER.error("Error for request uri [" + uri + "] with [" + content + "]:  " + e);

        }
        return httpResponse;

    }


    /**
     * @param configurationService
     *            the configurationService to set
     */
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }



    /**
     * @param sapsdOrderEntityPopulator
     *            the sapsdOrderEntityPopulator to set
     */
    public void setSapsdOrderEntityPopulator(final SAPSDOrderEntityPopulator sapsdOrderEntityPopulator)
    {
        this.sapsdOrderEntityPopulator = sapsdOrderEntityPopulator;
    }

}
