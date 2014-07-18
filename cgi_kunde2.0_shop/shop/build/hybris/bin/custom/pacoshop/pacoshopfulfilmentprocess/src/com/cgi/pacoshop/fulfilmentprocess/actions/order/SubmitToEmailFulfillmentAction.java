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
package com.cgi.pacoshop.fulfilmentprocess.actions.order;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.info;

import com.cgi.pacoshop.fulfilmentprocess.events.EmailFulfillmentEvent;

import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;


/**
 * Action that process an order via the Email Fulfillment interface.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubmitToEmailFulfillmentAction extends AbstractProceduralAction<OrderProcessModel>
{
    private static final Logger LOGGER = Logger.getLogger(SubmitToEmailFulfillmentAction.class);

    private EventService eventService;

    @Override
    public void executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {
        getEventService().publishEvent(new EmailFulfillmentEvent(orderProcessModel));
        info(LOGGER, "Process: " + orderProcessModel.getCode() + " in step " + getClass());

    }

    /**
     * @return the eventService
     */
    public EventService getEventService()
    {
        return eventService;
    }

    /**
     * @param eventService
     *            the eventService to set
     */
    public void setEventService(final EventService eventService)
    {
        this.eventService = eventService;
    }

}
