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

import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountResponse;
import com.cgi.pacoshop.fulfilmentprocess.populator.SSOAccountPopulator;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import org.apache.log4j.Logger;



/**
 * Implementation of the create_single_order of the SAP interface.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class HttpSSOSearchAccountClient implements SSOSearchAccountClient
{

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(HttpSSOSearchAccountClient.class);

    private SSOAccountPopulator ssoAccountPopulator;

    private SSOSearchRestClient ssoSearchRestClient;


    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.client.SSOSearchAccountClient#send(de.hybris.platform.core.model.order.OrderModel
     * )
     */
    @Override
    public SearchAccountStatus send(final AbstractOrderModel order)
    {
        try
        {
            final SSOAccountRequest ssoAccountRequest = ssoAccountPopulator.populate(order);

            if (ssoAccountRequest != null && ssoAccountRequest.isPopulated())
            {
                final SSOAccountResponse ssoAccountResponse = ssoSearchRestClient.getUserFromSSO(ssoAccountRequest);
                return createSearchAccountStatus(ClientStatus.SUCCESS, ssoAccountResponse.getAccountId(), null);
            }
        }
        catch (final Exception e)
        {
            LOG.error("An exception arise when updating the accountId[" + order + "] from SSO", e);
        }

        return createSearchAccountStatus(ClientStatus.FAIL, "", null);
    }

    private SearchAccountStatus createSearchAccountStatus(final ClientStatus clientStatus, final String accountId,
            final HttpResponse response)
    {
        return new SearchAccountStatus(clientStatus, accountId, response);
    }

    /**
     * @return the ssoAccountPopulator
     */
    public SSOAccountPopulator getSsoAccountPopulator()
    {
        return ssoAccountPopulator;
    }

    /**
     * @param ssoAccountPopulator
     *            the ssoAccountPopulator to set
     */
    public void setSsoAccountPopulator(final SSOAccountPopulator ssoAccountPopulator)
    {
        this.ssoAccountPopulator = ssoAccountPopulator;
    }

    /**
     * @return the ssoSearchRestClient
     */
    public SSOSearchRestClient getSsoSearchRestClient()
    {
        return ssoSearchRestClient;
    }

    /**
     * @param ssoSearchRestClient
     *            the ssoSearchRestClient to set
     */
    public void setSsoSearchRestClient(final SSOSearchRestClient ssoSearchRestClient)
    {
        this.ssoSearchRestClient = ssoSearchRestClient;
    }



}
