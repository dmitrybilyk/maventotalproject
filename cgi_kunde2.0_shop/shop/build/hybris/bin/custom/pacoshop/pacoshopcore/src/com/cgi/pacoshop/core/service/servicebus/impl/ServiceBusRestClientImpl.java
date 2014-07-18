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
package com.cgi.pacoshop.core.service.servicebus.impl;

import static com.cgi.pacoshop.core.util.LogHelper.debug;

import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import com.cgi.pacoshop.core.model.ResponseBusinessPartner;
import com.cgi.pacoshop.core.service.RestRetryService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.PacoshopRequestFactory;
import com.cgi.pacoshop.core.service.servicebus.ServiceBusRestClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

/**
 * SSO Rest client.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ServiceBusRestClientImpl implements ServiceBusRestClient
{
	private static final Logger LOG = org.apache.log4j.Logger.getLogger(ServiceBusRestClientImpl.class);
	@Resource
	private RestTemplate             restTemplateBusinessPartner;
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
	public Collection<ResponseBusinessPartner> getBusinessPartnersData(final List<BusinessPartnerIdModel> businessPartnerIdModels,
																							 final Map<String, String> parameters)
	{
		final UriTemplate uriTemplate =
				  new UriTemplate(shopConfigurationService.getServiceBusBusinessPartnerUriTemplate());

		final int connectTimeout = shopConfigurationService.getBusinessPartnerCallTimeoutConnect();
		final int readTimeout = shopConfigurationService.getBusinessPartnerCallTimeoutRead();

		final Collection<ResponseBusinessPartner> resultResponseBusinessPartner = new ArrayList<>();

		for (final BusinessPartnerIdModel businessPartnerIdModel : businessPartnerIdModels)
		{
			final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
			{
				@Override
				public Object doAction(final RestTemplate restTemplate)
				{
					final Map<String, String> params = new HashMap<>(parameters);
					params.put(shopConfigurationService.getBusinessPartnerId(), businessPartnerIdModel.getId());
					final URI uri = uriTemplate.expand(params);
					debug(LOG, "REST call to URI={%s}", uri);
					Object result = restTemplate.getForObject(uri, ResponseBusinessPartner.class);
					((ResponseBusinessPartner) result).setBusinessPartnerId(businessPartnerIdModel.getId());
					return result;
				}

				@Override
				public void onSuccess(final Object result)
				{
					resultResponseBusinessPartner.add((ResponseBusinessPartner) result);
				}
			};
			// make a rest call with retry
			restRetryService.callWithRetry(enrichRestTemplate(restTemplateBusinessPartner), connectTimeout, readTimeout,
													 shopConfigurationService.getBusinessPartnerCallRetry(),
													 restCall);
		}
		return resultResponseBusinessPartner;
	}
}
