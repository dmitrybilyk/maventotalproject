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
package com.cgi.pacoshop.core.service;

import org.springframework.web.client.RestTemplate;

/**
 * Rest retry mechanism that implements retry logic on rest calls.
 *
 * @module pacoshopcore
 * @version 1.0v Dec 18, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleksandr Fedorov <oleksandr.fedorov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public interface RestRetryService
{
	/**
	 * Calls rest service with retry logic.
	 * @param restTemplate spring rest template class that has own request factory
	 *                       (which will be configured by connect/read timeout params)
	 * @param connectTimeout connection timeout in ms
	 * @param readTimeout read timeout in ms
	 * @param numRetry number of retry calls
	 * @param call anonymous class that do actual call
	 */
	void callWithRetry(RestTemplate restTemplate, int connectTimeout, int readTimeout, int numRetry, AbstractRestRetryCall call);

	/***
	 * Class that used as a wrapper for retry call.
	 */
	abstract class AbstractRestRetryCall
	{
		public abstract Object doAction(RestTemplate restTemplate);

		public abstract void onSuccess(Object result);
	}
}
