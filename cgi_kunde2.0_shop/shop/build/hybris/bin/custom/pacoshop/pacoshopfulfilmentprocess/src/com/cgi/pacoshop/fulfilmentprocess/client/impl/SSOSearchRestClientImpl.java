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
package com.cgi.pacoshop.fulfilmentprocess.client.impl;


import com.cgi.pacoshop.fulfilmentprocess.service.impl.PacoshopRequestFactory;
import com.cgi.pacoshop.fulfilmentprocess.client.SSOSearchRestClient;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountResponse;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;
import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.warn;
import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.error;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import org.apache.log4j.Logger;


/**
 * SSO REST client implementation for the SSOAccountRequest.
 * 
 * @module build
 * @version 1.0v Mar 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class SSOSearchRestClientImpl implements SSOSearchRestClient
{
	public static final String EMAIL_PARAM_NAME = "email";

	private static final Logger LOG = Logger.getLogger(SSOSearchRestClientImpl.class);
   private ConfigurationService configurationService;

   @Resource
   private PacoshopRequestFactory pacoshopRequestFactoryForFulfilment;

   /**
    * Enrich rest template with jackson message converter.
    */
   private RestTemplate enrichRestTemplate(final RestTemplate restTemplate)
   {
      restTemplate.setRequestFactory(pacoshopRequestFactoryForFulfilment);
      restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
      return restTemplate;
   }

   /**
     * @param ssoAccountRequest
     *            inside we have email and other parameters
     * @return SSOAccountResponse from SSO
     */
    @Override
    public SSOAccountResponse getUserFromSSO(final SSOAccountRequest ssoAccountRequest)
    {
        RestTemplate restTemplate = enrichRestTemplate(new RestTemplate());
        final UriTemplate uriTemplate = new UriTemplate(configurationService.getConfiguration().getString(
                PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_URL));
        final int readTimeout = configurationService.getConfiguration().getInt(
                PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_READ_TIMEOUT);
        final int connectionTimeout = configurationService.getConfiguration().getInt(
                PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_CONNECTION_TIMEOUT);
        final String authTokenHeaderName = configurationService.getConfiguration().getString(
                PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_AUTH_TOKEN_HEADER);
        final String authTokenHeaderValue = configurationService.getConfiguration().getString(
                PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_AUTH_TOKEN);

        final Map<String, String> parameters = new HashMap<String, String>();
        String email = ssoAccountRequest.getEmail();
        if (StringUtils.isEmpty(email))
        {
            error(LOG, "Email should not be null or empty.");
            return null;
        }
        parameters.put(EMAIL_PARAM_NAME, email);

        final URI uri = uriTemplate.expand(parameters);

        ((PacoshopRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(readTimeout);
        ((PacoshopRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(connectionTimeout);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(authTokenHeaderName, authTokenHeaderValue);
        HttpEntity<SSOAccountResponse[]> request = new HttpEntity<>(requestHeaders);

        debug(LOG, "SSO.searchAccount url=%s, email=%s, authToken=%s, readTimeout=%s, connectionTimeout=%s",
              uri, ssoAccountRequest.getEmail(), authTokenHeaderValue, readTimeout, connectionTimeout);

        try
        {
            ResponseEntity<SSOAccountResponse[]> responseCustomers = restTemplate.exchange(uri, HttpMethod.GET, request,
                                                                                           SSOAccountResponse[].class);
            if (responseCustomers.getBody().length == 0)
            {
                warn(LOG, "SSO.searchAccount no user with email=%s is found.");
                return null;
            }
            else if (responseCustomers.getBody().length > 1)
            {
                warn(LOG, "SSO.searchAccount return more than one user with email=%s.");
            }
            else
            {
                debug(LOG, "SSO.searchAccount request succeed.");
            }
            // only one user should be in SSO.searchAccount response
            return responseCustomers.getBody()[0];
        }
        catch (Exception e)
        {
            error(LOG, "An exception appears during the SSO.searchAccount call", e);
            return null;
        }
    }

    /**
     * @return the configurationService
     */
    public ConfigurationService getConfigurationService()
    {
        return configurationService;
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
