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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.service.RestRetryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


/**
 * Rest retry mechanism that implements retry logic on rest calls
 *
 * @module pacoshopcore
 * @version 1.0v dec 18, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleksandr Fedorov <alexandr.fedorov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see  "https://wiki.hybris.com/"
 */
public class RestRetryServiceImpl implements RestRetryService
{
	private static final Logger LOG = LoggerFactory.getLogger(RestRetryServiceImpl.class);

	@Override
	public void callWithRetry(final RestTemplate restTemplate, final int connectTimeout, final int readTimeout,
									  final int numRetry, final AbstractRestRetryCall call)
	{
		LOG.debug("callWithRetry() connectTimeout={}, readTimeout={}, numRetry={}",
					 new Object[]{connectTimeout, readTimeout, numRetry});

		// set timeout params to request factory
		// important: the same request factory used on consequent queries
		// each restTemplate spring bean has own request factory (with specific params)
		Assert.isInstanceOf(PacoshopRequestFactory.class, restTemplate.getRequestFactory());
		PacoshopRequestFactory requestFactory = (PacoshopRequestFactory) restTemplate.getRequestFactory();

		requestFactory.setConnectTimeout(connectTimeout);
		requestFactory.setReadTimeout(readTimeout);

		// error handling for retry calls
		int retryNum = 0;
		boolean isCallFailed;
		do
		{
			isCallFailed = false;
			LOG.debug("callWithRetry() attempt={}, trying to call rest", retryNum + 1);
			// call method by rest template
			try
			{
				final Object result = call.doAction(restTemplate);
				call.onSuccess(result);
				LOG.debug("callWithRetry() attempt={} -> success call", retryNum + 1);
			}
			catch (final RestClientException exception)
			{
				// check that exception is IOException, excluding error with either format or business logic
				// (more precisely - SocketTimeoutException SocketException)
				if (exception.getCause() != null && exception.getCause() instanceof IOException)
				{
					isCallFailed = true;
					retryNum++;
					LOG.warn("callWithRetry() attempt={} -> call failed, error={}", retryNum, exception);

					// rethrow last exception to be caught by caller code
					if (retryNum == numRetry)
					{
						LOG.warn("callWithRetry() max number of retry attempt reached, rethrowing exception");
						throw exception;
					}
				}
				else
				{
					LOG.warn("callWithRetry() RestClientException cause is not IOException, rethrowing exception further");
					throw exception;
				}
			}
		}
		while (isCallFailed && retryNum < numRetry);
	}
}
