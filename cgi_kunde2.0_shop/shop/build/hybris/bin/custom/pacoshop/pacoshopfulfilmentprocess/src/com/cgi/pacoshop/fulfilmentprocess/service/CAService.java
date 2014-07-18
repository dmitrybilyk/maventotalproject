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
package com.cgi.pacoshop.fulfilmentprocess.service;

import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAException;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * Represents the service responsible to send order to the CA third party system.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface CAService
{


    /**
     * Parse and send the order entry relevant to the CA service.
     * 
     * @param order
     *            the order to process.
     * @return ClientSTatus the status.
     * @throws CAException
     *             if an exception occurred.
     */
    ClientStatus sendOrder(OrderModel order) throws CAException;

}
