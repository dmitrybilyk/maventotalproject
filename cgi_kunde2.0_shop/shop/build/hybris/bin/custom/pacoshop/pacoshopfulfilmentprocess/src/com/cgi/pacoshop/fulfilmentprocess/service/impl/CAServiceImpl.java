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
package com.cgi.pacoshop.fulfilmentprocess.service.impl;

import com.cgi.pacoshop.fulfilmentprocess.client.CAClient;
import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAException;
import com.cgi.pacoshop.fulfilmentprocess.service.CAService;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * Represents the default implementation of the CAService.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CAServiceImpl implements CAService
{

    private CAClient caClient;


    @Override
    public ClientStatus sendOrder(final OrderModel order) throws CAException
    {
        return caClient.send(order);
    }


    /**
     * @param caClient
     *            the caClient to set
     */
    public void setCaClient(final CAClient caClient)
    {
        this.caClient = caClient;
    }


}
