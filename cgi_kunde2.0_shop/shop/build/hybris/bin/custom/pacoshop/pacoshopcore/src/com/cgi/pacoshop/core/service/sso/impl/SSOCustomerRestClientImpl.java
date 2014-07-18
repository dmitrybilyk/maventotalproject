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
package com.cgi.pacoshop.core.service.sso.impl;

import com.cgi.pacoshop.core.model.ResponseCustomer;
import com.cgi.pacoshop.core.service.RestRetryService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.PacoshopRequestFactory;
import com.cgi.pacoshop.core.service.sso.SSOCustomerRestClient;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import static com.cgi.pacoshop.core.util.LogHelper.warn;
import java.net.URI;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

/**
 * SSO Rest client.
 *
 * @module pacoshopcore
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 06, 2014
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SSOCustomerRestClientImpl implements SSOCustomerRestClient
{
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SSOCustomerRestClientImpl.class);

	@Resource
	private ShopConfigurationService shopConfigurationService;
	@Resource
	private RestRetryService         restRetryService;
	@Resource
	private PacoshopRequestFactory   pacoshopRequestFactory;

	/**
	 * Enrich rest template with jackson message converter.
	 */
	private RestTemplate enrichRestTemplate(final RestTemplate restTemplate)
	{
		restTemplate.setRequestFactory(pacoshopRequestFactory);
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate;
	}

	@Override
	public ResponseCustomer getUserData(final Map<String, String> parameters)
	{
		final String wsTokenKey = shopConfigurationService.getServiceConfiguration().
				  getString(ShopConfigurationService.CONFIG_KEY_UAG_HEADER_NAME_WS_TOKEN_KEY);
		final String wsToken = parameters.get(wsTokenKey);
		parameters.remove(wsTokenKey); // Remove it from parameters.

		final UriTemplate uriTemplate = new UriTemplate(shopConfigurationService.getSsoClientUriTemplate());
		final URI uri = uriTemplate.expand(parameters);
		debug(LOG, "REST call to URI={%s}", uri);

		final int connectTimeout = shopConfigurationService.getCustomerCallTimeoutConnect();
		final int readTimeout = shopConfigurationService.getCustomerCallTimeoutRead();

		final ResponseCustomer[] resultResponseCustomer = new ResponseCustomer[1];
		final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
		{
			@Override
			public Object doAction(final RestTemplate restTemplate)
			{
				HttpHeaders headers = new HttpHeaders();
				String headerTokenName = shopConfigurationService
						  .getServiceConfiguration()
						  .getString(ShopConfigurationService.CONFIG_KEY_SSO_READ_ACCOUNT_AUTH_HEADER);
				headers.set(headerTokenName, wsToken);
				HttpEntity<ResponseCustomer> entity = new HttpEntity<>(headers);
				ResponseEntity<ResponseCustomer> responseEntity;

				try
				{
					// print debug info with headers and parameters
					debug(LOG, "Header auth token {'%s'}: {%s}", headerTokenName, wsToken);
					// print request parameters
					debug(LOG, "Call parameters:");
					if (LOG.isDebugEnabled())
					{
						for (Map.Entry<String, String> param : parameters.entrySet())
						{
							debug(LOG, "Param {'%s'} : {%s}", param.getKey(), param.getValue());
						}
					}

					responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseCustomer.class);
					return responseEntity.getBody();
				}
				catch (final ResourceAccessException exception)
				{
					error(LOG, "SSO Request -> call failed with error: %s", exception, exception.getMessage());
				}
				catch (final HttpServerErrorException exception)
				{
					warn(LOG, "Header token '{%s}': {%s}", headerTokenName, wsToken);
					error(LOG, "SSO Request -> call failed with error: %s", exception, exception.getMessage());
				}

				return null;
			}

			@Override
			public void onSuccess(final Object result)
			{
				resultResponseCustomer[0] = (ResponseCustomer) result;
			}
		};
		// make a rest call with retry
		restRetryService.callWithRetry(enrichRestTemplate(new RestTemplate()), connectTimeout, readTimeout, 0, restCall);
		return resultResponseCustomer[0];
	}
}
