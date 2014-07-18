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

/**
 * A searchAccount status.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SearchAccountStatus
{
	private ClientStatus clientStatus;

	private String customerId;

	private HttpResponse httpResponse;


	/**
	 * Constructor.
	 */
	private SearchAccountStatus()
	{
		//private Constructor;
	}

	/**
	 * Constructor.
	 *
	 * @param clientStatus
	 *            the clientStatus.
	 * @param customerId
	 *            the customerId.
	 * @param httpResponse
	 *            the httpResponse.
	 */
	public SearchAccountStatus(final ClientStatus clientStatus, final String customerId, final HttpResponse httpResponse)
	{
		this.clientStatus = clientStatus;
		this.customerId = customerId;
		this.httpResponse = httpResponse;
	}

	/**
	 * @return the clientStatus
	 */
	public ClientStatus getClientStatus()
	{
		return clientStatus;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId()
	{
		return customerId;
	}

	/**
	 * @return the httpResponse
	 */
	public HttpResponse getHttpResponse()
	{
		return httpResponse;
	}
}
