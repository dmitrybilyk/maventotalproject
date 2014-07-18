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
import com.cgi.pacoshop.fulfilmentprocess.model.SAPMDOrderEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPMDOrderEntityPopulator;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


/**
 * Implementation of the create_periodic_order of the SAP interface.
 * 
 * @module hybris - pacoshopfulfilmentprocess
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class HttpSAPMDClient implements SAPClient
{

    private static final Logger LOGGER = Logger.getLogger(HttpSAPMDClient.class);

    private SAPMDOrderEntityPopulator sapmdOrderEntityPopulator;

    private ConfigurationService configurationService;

    private DateFormat dateFormat = null;

    /**
     * @param dateFormatString
     *            the dateFormatString to set
     */
    public void setDateFormatString(final String dateFormatString)
    {
        this.dateFormat = new SimpleDateFormat(dateFormatString);
    }


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
            final SAPMDOrderEntity sapMDOrderEntity = sapmdOrderEntityPopulator.populate(order);
            final HttpResponse response = doSend(sapMDOrderEntity);
            status = processResponse(response);
        }
        catch (final Exception e)
        {
            LOGGER.error("An exception arise when sending the order[" + order + "] to SAPMSD", e);
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
    private HttpResponse doSend(final SAPMDOrderEntity content) throws SAPException
    {

        final String uri = configurationService.getConfiguration().getString(
                PacoshopFulfilmentProcessConstants.INTERFACE.SERVICEBUS_PERIODICORDERS_CREATE_URL);
        final int connectionTimeout = configurationService.getConfiguration().getInt(
                PacoshopFulfilmentProcessConstants.INTERFACE.SERVICEBUS_PERIODICORDERS_CREATE_CONNECTION_TIMEOUT);
        final int socketTimeout = configurationService.getConfiguration().getInt(
                PacoshopFulfilmentProcessConstants.INTERFACE.SERVICEBUS_PERIODICORDERS_CREATE_SOCKET_TIMEOUT);

        HttpResponse httpResponse = null;

        ResponseEntity<String> result = null;
        final RestTemplate restTemplate = new RestTemplate();

        final List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        for (final HttpMessageConverter<?> current : converters)
        {
            if (current instanceof MappingJacksonHttpMessageConverter)
            {
                final MappingJacksonHttpMessageConverter converter = (MappingJacksonHttpMessageConverter) current;
                converter.getObjectMapper().setDateFormat(dateFormat);
            }
        }

        final SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(socketTimeout);
        try
        {
            debug(LOGGER, "Executing call[%s] with socket timeout[%s], connection timeout[%s] and content [%s]", uri,
                    Integer.valueOf(socketTimeout), Integer.valueOf(connectionTimeout), content);
            result = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<SAPMDOrderEntity>(content), String.class);
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
     * @param sapmdOrderEntityPopulator
     *            the sapmdOrderEntityPopulator to set
     */
    public void setSapmdOrderEntityPopulator(final SAPMDOrderEntityPopulator sapmdOrderEntityPopulator)
    {
        this.sapmdOrderEntityPopulator = sapmdOrderEntityPopulator;
    }


}
