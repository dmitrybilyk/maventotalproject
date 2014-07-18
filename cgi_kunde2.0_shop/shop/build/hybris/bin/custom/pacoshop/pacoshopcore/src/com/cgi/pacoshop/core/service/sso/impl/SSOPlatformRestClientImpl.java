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


import com.cgi.pacoshop.core.model.ResponsePlatformConfiguration;
import com.cgi.pacoshop.core.service.RestRetryService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.PacoshopRequestFactory;
import com.cgi.pacoshop.core.service.sso.SSOPlatformRestClient;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import java.net.URI;
import javax.annotation.Resource;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

/**
 * Implementation of SSOPlatformRestClient.
 *
 * @module pacoshopcore
 * @version 1.0v Jul 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SSOPlatformRestClientImpl implements SSOPlatformRestClient
{
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SSOPlatformRestClientImpl.class);

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
	public ResponsePlatformConfiguration getPlatformConfigurationData(final String platformId)
	{
		final UriTemplate uriTemplate = new UriTemplate(shopConfigurationService.getConfigKeySsoClientPlatformUriTemplate());
		final URI uri = uriTemplate.expand(platformId);

		final int connectTimeout = shopConfigurationService.getPlatformCallTimeoutConnect();
		final int readTimeout = shopConfigurationService.getPlatformCallTimeoutRead();

		debug(LOG, "getPlatformConfigurationData() call with url[" + uri + "] connectTimeout[" + Integer.valueOf(connectTimeout)
				  + "] readTimeout[" + Integer.valueOf(readTimeout));

		final ResponsePlatformConfiguration[] responsePlatformConfiguration = new ResponsePlatformConfiguration[1];
		final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
		{
			@Override
			public Object doAction(final RestTemplate restTemplate)
			{
				try
				{
					return restTemplate.getForObject(uri, ResponsePlatformConfiguration.class);
				}
				catch (final ResourceAccessException exception)
				{
					error(LOG, "SSO Request -> call failed with error: %s", exception, exception.getMessage());
				}
				return null;
			}

			@Override
			public void onSuccess(final Object result)
			{
				responsePlatformConfiguration[0] = (ResponsePlatformConfiguration) result;
			}
		};

		restRetryService.callWithRetry(enrichRestTemplate(new RestTemplate()), connectTimeout, readTimeout, 0, restCall);
		return responsePlatformConfiguration[0];
	}
}
