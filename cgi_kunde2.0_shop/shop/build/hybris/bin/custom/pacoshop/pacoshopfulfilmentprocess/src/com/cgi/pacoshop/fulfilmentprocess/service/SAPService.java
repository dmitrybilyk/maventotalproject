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

import de.hybris.platform.core.model.order.OrderModel;


/**
 * Encapsulates the interface to the SAP sub-system.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface SAPService
{

    /**
     * Sends a SAP create single order call based on the passed order.
     * 
     * @param order
     *            the order to process.
     * @return the status of the request.
     */
    ClientStatus sendSAPSD(OrderModel order);

    /**
     * Sends a SAP create periodic order call based on the passed order.
     * 
     * @param order
     *            the order to process.
     * @return the status of the request.
     */
    ClientStatus sendSAPMD(OrderModel order);
}
