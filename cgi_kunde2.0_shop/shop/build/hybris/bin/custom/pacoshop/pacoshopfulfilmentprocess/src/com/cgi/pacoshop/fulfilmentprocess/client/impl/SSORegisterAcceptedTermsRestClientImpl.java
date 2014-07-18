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
import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.client.SSORegisterAcceptedTermsRestClient;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.model.AcceptedTermListEntity;
import com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

/**
 * SSO REST client implementation for the SSORegisterAcceptedTermsRequest.
 *
 * @module pacoshopfulfilmentprocess
 * @version 1.0v Mar 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SSORegisterAcceptedTermsRestClientImpl implements SSORegisterAcceptedTermsRestClient
{
	private static final Logger LOG                        = Logger.getLogger(SSORegisterAcceptedTermsRestClientImpl.class);
	private static final String ACCOUNT_ID_URL_PARAM_NAME  = "accountId";
	private static final String PLATFORM_ID_URL_PARAM_NAME = "platformId";

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

	@Override
	public ClientStatus registerAcceptedTerms(final SSORegisterAcceptedTermsRequest ssoRegisterAcceptedTermsRequest)
	{
		RestTemplate restTemplate = enrichRestTemplate(new RestTemplate());
		final UriTemplate uriTemplate = new UriTemplate(configurationService.getConfiguration().getString(
				  PacoshopFulfilmentProcessConstants.INTERFACE.SSO_WRITE_ACCOUNT_URL));
		final int readTimeout = configurationService.getConfiguration().getInt(
				  PacoshopFulfilmentProcessConstants.INTERFACE.SSO_WRITE_ACCOUNT_READ_TIMEOUT);

		final int connectionTimeout = configurationService.getConfiguration().getInt(
				  PacoshopFulfilmentProcessConstants.INTERFACE.SSO_WRITE_ACCOUNT_CONNECTION_TIMEOUT);

		String authTokenHeaderName = configurationService.getConfiguration().getString(
				  PacoshopFulfilmentProcessConstants.INTERFACE.SSO_WRITE_ACCOUNT_AUTH_TOKEN_HEADER);
		String authTokenHeaderValue = configurationService.getConfiguration().getString(
				  PacoshopFulfilmentProcessConstants.INTERFACE.SSO_WRITE_ACCOUNT_AUTH_TOKEN);

		final URI uri = uriTemplate.expand(putParameters(ssoRegisterAcceptedTermsRequest));

		((PacoshopRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(readTimeout);
		((PacoshopRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(connectionTimeout);

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add(authTokenHeaderName, authTokenHeaderValue);
		HttpEntity<AcceptedTermListEntity> request = new HttpEntity<>(ssoRegisterAcceptedTermsRequest.getTerms(), requestHeaders);

		debug(LOG, "SSO.writeAccount url=%s, accountId=%s, platdormId=%s, authToken=%s, readTimeout=%s, connectionTimeout=%s",
				uri, ssoRegisterAcceptedTermsRequest.getAccountId(), ssoRegisterAcceptedTermsRequest.getPlatformId(),
				authTokenHeaderValue, readTimeout, connectionTimeout);

		final ClientStatus response = restTemplate.postForObject(uri, request, ClientStatus.class);
		if (response == null)
		{
			debug(LOG, "SSO.writeAccount succeed.");

			// SSO returns HTTP status code 204 in a case of success request processing. So return ClientStatus.SUCCESS
			return ClientStatus.SUCCESS;
		}
		return response;
	}

	private Map<String, String> putParameters(final SSORegisterAcceptedTermsRequest ssoRegisterAcceptedTermsRequest)
	{
		final Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(ACCOUNT_ID_URL_PARAM_NAME, ssoRegisterAcceptedTermsRequest.getAccountId());
		parameters.put(PLATFORM_ID_URL_PARAM_NAME, ssoRegisterAcceptedTermsRequest.getPlatformId());

		return parameters;
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
