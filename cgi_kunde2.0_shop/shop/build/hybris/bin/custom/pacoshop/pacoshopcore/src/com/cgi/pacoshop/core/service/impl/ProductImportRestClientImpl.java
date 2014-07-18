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
package com.cgi.pacoshop.core.service.impl;

import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.model.SingleProductList;
import com.cgi.pacoshop.core.model.SubscriptionProductList;
import com.cgi.pacoshop.core.service.RestClient;
import com.cgi.pacoshop.core.service.RestClientProperties;
import com.cgi.pacoshop.core.service.RestRetryService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.cgi.pacoshop.core.util.LogHelper.debug;


/**
 * Product import REST client
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v dec 24, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@Component
public class ProductImportRestClientImpl implements RestClient
{
	final static Logger LOG = Logger.getLogger(ProductImportRestClientImpl.class);

	@Resource
	private RestTemplate restTemplateProductSingle;
	@Resource
	private RestTemplate restTemplateProductSubscription;
	@Resource
	private RestClientProperties clientProperties;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private RestRetryService restRetryService;

	@Resource
	private PacoshopRequestFactory pacoshopRequestFactory;

	/**
	 * Default constructor.
	 */
	public ProductImportRestClientImpl()
	{
	}

	/**
	 * Enrich rest template with jackson message converter.
	 */
	private RestTemplate enrichRestTemplate(final RestTemplate restTemplate)
	{
		restTemplate.setRequestFactory(pacoshopRequestFactory);

      final HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
		//		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		return restTemplate;
	}

	/**
	 * Method cal GET request trough http on RESTfull service.
	 * 
	 * @return collection of Single Products
	 */
	@Override
	public Collection<SingleProductDTO> getSingleProduct()
	{

		final String url = shopConfigurationService.getSimpleProductUrl();
		final int connectTimeout = shopConfigurationService.getSimpleProductCallTimeoutConnect();
		final int readTimeout = shopConfigurationService.getSimpleProductCallTimeoutRead();
		final int retryNum = shopConfigurationService.getSimpleProductCallRetry();

		debug(LOG, "getSingleProduct() call with url[" + url + "] connectTimeout[" + Integer.valueOf(connectTimeout)
				  + "] readTimeout[" + Integer.valueOf(readTimeout) + "] retryNum[" + Integer.valueOf(retryNum) + "]");

		final Collection<SingleProductDTO> resultList = new ArrayList<SingleProductDTO>();
		final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
		{
			@Override
			public Object doAction(final RestTemplate restTemplate)
			{
				return restTemplate.getForObject(url, SingleProductList.class);
			}

			@Override
			public void onSuccess(final Object result)
			{
				resultList.addAll((Collection<SingleProductDTO>) result);
			}
		};
		// make a rest call with retry
		restRetryService.callWithRetry(enrichRestTemplate(restTemplateProductSingle), connectTimeout, readTimeout, retryNum,
				restCall);
		return resultList;
	}

	/**
	 * Method cal GET request trough http on RESTfull service.
	 * 
	 * @return collection of Subscription Products
	 */
	@Override
	public Collection<? extends SingleProductDTO> getSubscriptionProducts()
	{

		final String url = shopConfigurationService.getSubscriptionProductUrl();
		final int connectTimeout = shopConfigurationService.getSubscriptionProductCallTimeoutConnect();
		final int readTimeout = shopConfigurationService.getSubscriptionProductCallTimeoutRead();
		final int retry = shopConfigurationService.getSubscriptionProductCallRetry();

		debug(LOG, "getSubscriptionProducts() call with url[" + url + "] connectTimeout[" + Integer.valueOf(connectTimeout)
					+ "] readTimeout[" + Integer.valueOf(readTimeout) + "] retryNum[" + Integer.valueOf(retry) + "]");

		final Collection<? extends SingleProductDTO> resultList = new ArrayList();
		final RestRetryService.AbstractRestRetryCall restCall = new RestRetryService.AbstractRestRetryCall()
		{
			@Override
			public Object doAction(final RestTemplate restTemplate)
			{
				return restTemplate.getForObject(url, SubscriptionProductList.class);
			}

			@Override
			public void onSuccess(final Object result)
			{
				resultList.addAll((Collection) result);
			}
		};
		// make a rest call with retry
		restRetryService.callWithRetry(enrichRestTemplate(restTemplateProductSubscription), connectTimeout, readTimeout, retry,
				restCall);
		return resultList;
	}
}
