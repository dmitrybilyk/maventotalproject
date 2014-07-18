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
package com.cgi.pacoshop.fulfilmentprocess.service.impl;

import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.client.SAPClient;
import com.cgi.pacoshop.fulfilmentprocess.service.SAPService;

import de.hybris.platform.core.model.order.OrderModel;

import javax.annotation.Resource;


/**
 * Represents the default implementation of the SAPService.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPServiceImpl implements SAPService
{


    @Resource
    private SAPClient sapSDClient;

    @Resource
    private SAPClient sapMDClient;

    /*
     * 
     * @see com.cgi.pacoshop.fulfilmentprocess.service.SAPService#sendSAPSD(de.hybris.platform.core.model.order.
     * AbstractOrderModel)
     */
    @Override
    public ClientStatus sendSAPSD(final OrderModel order)
    {
        return sapSDClient.send(order);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.fulfilmentprocess.service.SAPService#sendSAPMD(de.hybris.platform.core.model.order.
     * AbstractOrderModel)
     */
    @Override
    public ClientStatus sendSAPMD(final OrderModel order)
    {
        return sapMDClient.send(order);
    }

}
