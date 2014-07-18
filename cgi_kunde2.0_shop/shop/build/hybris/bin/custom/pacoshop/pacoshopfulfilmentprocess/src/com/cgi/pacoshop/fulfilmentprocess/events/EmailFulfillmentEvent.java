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
package com.cgi.pacoshop.fulfilmentprocess.events;

import de.hybris.platform.orderprocessing.events.OrderProcessingEvent;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;


/**
 * This is the event for starting email fulfillment process
 * 
 * @module pacoshopfulfilmentprocess
 * @version 1.0v 17.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class EmailFulfillmentEvent extends OrderProcessingEvent
{
    private static final long serialVersionUID = 1L;

    /**
     * Order Email Fulfillment Event.
     * 
     * @param process
     *            the {@link OrderProcessModel}.
     */
    public EmailFulfillmentEvent(final OrderProcessModel process)
    {
        super(process);
    }

}
