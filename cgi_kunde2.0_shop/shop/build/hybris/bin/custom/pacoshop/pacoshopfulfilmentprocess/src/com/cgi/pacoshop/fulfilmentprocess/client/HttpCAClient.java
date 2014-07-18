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
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAException;

import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;


/**
 * Represents the HTTP client implementation of the CAClient.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class HttpCAClient extends AbstractCAClient
{

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(HttpCAClient.class);


    private HttpClient httpClient;

    private ConfigurationService configurationService;

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.fulfilmentprocess.client.AbstractCAClient#doSend(java.lang.String)
     */
    @Override
    protected HttpResponse doSend(final String content) throws CAException
    {

        final String url = configurationService.getConfiguration().getString(PacoshopFulfilmentProcessConstants.INTERFACE.CA_URL);
        final PutMethod put = new PutMethod(url);


        HttpParams params = httpClient.getParams();
        if (params == null)
        {
            params = new HttpClientParams();
            httpClient.setParams((HttpClientParams) params);
        }
        params.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT,
                configurationService.getConfiguration()
                        .getInt(PacoshopFulfilmentProcessConstants.INTERFACE.CA_CONNECTION_TIMEOUT));
        params.setParameter(HttpConnectionParams.SO_TIMEOUT,
                configurationService.getConfiguration().getInt(PacoshopFulfilmentProcessConstants.INTERFACE.CA_SO_TIMEOUT));
        try
        {
            put.setRequestEntity(new StringRequestEntity(content, "application/json", "UTF-8"));

            // send the request
            debug(LOG, "CAClient call: " + url);

            final int httpStatus = httpClient.executeMethod(put);

            final String body = put.getResponseBodyAsString();
            put.releaseConnection();

            return new HttpResponse(httpStatus, body);

        }
        catch (final URIException e)
        {
            LOG.error("HttpCAClient: There is an error with the URI for HttpCAClient.", e);
            throw new CAException("HttpCAClient: There is an error with the URI for HttpCAClient.", e);
        }
        catch (final HttpException e)
        {
            LOG.error("HttpCAClient: There is an error with the Httprequest for HttpCAClient.", e);
            throw new CAException("HttpCAClient: There is an error with the Httprequest for HttpCAClient", e);
        }
        catch (final IOException e)
        {
            LOG.error("HttpCAClient: There is a general error with the HttpCAClient request.", e);
            throw new CAException("HttpCAClient: There is a general error with the HttpCAClient request.", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.fulfilmentprocess.client.AbstractCAClient#processResponse(java.lang.String)
     */
    @Override
    protected void processResponse(final HttpResponse response) throws CAException
    {
        if (response != null && response.getStatus() == HttpStatus.CREATED.value())
        {
            debug(LOG, "CA response: " + response);
        }
        else
        {
            if (response != null)
            {
                throw new CAException("Error processing the response. " + response);
            }
            else
            {
                throw new CAException("Error processing the response. The response is null!");
            }

        }

    }

    /**
     * @return the httpClient
     */
    public HttpClient getHttpClient()
    {
        return httpClient;
    }

    /**
     * @param httpClient
     *            the httpClient to set
     */
    public void setHttpClient(final HttpClient httpClient)
    {
        this.httpClient = httpClient;
    }

    /**
     * @param configurationService
     *            the configurationService to set
     */
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }

}
