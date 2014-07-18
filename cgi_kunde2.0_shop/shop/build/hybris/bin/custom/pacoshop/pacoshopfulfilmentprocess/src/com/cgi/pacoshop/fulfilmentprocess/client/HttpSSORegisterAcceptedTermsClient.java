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

import com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest;
import com.cgi.pacoshop.fulfilmentprocess.populator.SSORegisterAcceptedTermsPopulator;
import de.hybris.platform.core.model.order.OrderModel;
import org.apache.log4j.Logger;


/**
 * Implementation of the SSO optIN client.
 *
 * @module pacoshopfulfilmentprocess
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class HttpSSORegisterAcceptedTermsClient implements SSORegisterAcceptedTermsClient
{
	private static final Logger LOG = Logger.getLogger(HttpSSORegisterAcceptedTermsClient.class);

	private SSORegisterAcceptedTermsPopulator ssoRegisterAcceptedTermsPopulator;

	private SSORegisterAcceptedTermsRestClient ssoRegisterAcceptedTermsRestClient;

	/*
	  * (non-Javadoc)
	  *
	  * @see
	  * com.cgi.pacoshop.fulfilmentprocess.client.SSOSearchAccountClient#send(de.hybris.platform.core.model.order.OrderModel)
	  */
	@Override
	public ClientStatus send(final OrderModel order)
	{
		try
		{
			final SSORegisterAcceptedTermsRequest request = ssoRegisterAcceptedTermsPopulator.populate(order);

			if (request != null && request.isPopulated())
			{
				return ssoRegisterAcceptedTermsRestClient.registerAcceptedTerms(request);
			}
		}
		catch (final Exception e)
		{
			LOG.error("An exception arise when updating the accountId[" + order + "] from SSO", e);
			return ClientStatus.FAIL;
		}

		return null;
	}

	/**
	 * Used by spring to populate the ssoRegisterAcceptedTermsPopulator property.
	 *
	 * @param ssoRegisterAcceptedTermsPopulator SSORegisterAcceptedTermsPopulator instance bean.
	 */
	public void setSsoRegisterAcceptedTermsPopulator(final SSORegisterAcceptedTermsPopulator ssoRegisterAcceptedTermsPopulator)
	{
		this.ssoRegisterAcceptedTermsPopulator = ssoRegisterAcceptedTermsPopulator;
	}

	/**
	 * Used by spring to populate the ssoRegisterAcceptedTermsRestClient property.
	 *
	 * @param ssoRegisterAcceptedTermsRestClient SSORegisterAcceptedTermsRestClient instance bean.
	 */
	public void setSsoRegisterAcceptedTermsRestClient(final SSORegisterAcceptedTermsRestClient ssoRegisterAcceptedTermsRestClient)
	{
		this.ssoRegisterAcceptedTermsRestClient = ssoRegisterAcceptedTermsRestClient;
	}
}
