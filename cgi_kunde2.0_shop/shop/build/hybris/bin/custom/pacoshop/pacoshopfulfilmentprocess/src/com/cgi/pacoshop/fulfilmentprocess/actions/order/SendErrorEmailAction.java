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

import com.cgi.pacoshop.fulfilmentprocess.events.OrderErrorEvent;

import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.event.EventService;

import org.apache.log4j.Logger;


/**
 * This action send an error email containing information about the order in which the error occured.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SendErrorEmailAction extends AbstractProceduralAction<OrderProcessModel>
{
    private static final Logger LOGGER = Logger.getLogger(SendErrorEmailAction.class);

    private EventService eventService;

    /**
     * Execution action.
     * 
     * @param orderProcessModel
     *            is process model.
     * @throws Exception
     *             like Exception
     */
    @Override
    public void executeAction(final OrderProcessModel orderProcessModel) throws Exception
    {
        getEventService().publishEvent(new OrderErrorEvent(orderProcessModel));

        LOGGER.info("Process: " + orderProcessModel.getCode() + " in step " + getClass());
    }

    /**
     * eventService.
     * 
     * @param eventService
     *            like eventService
     */
    public void setEventService(final EventService eventService)
    {
        this.eventService = eventService;
    }

    /**
     * 
     * @return EventService like EventService.
     */
    public EventService getEventService()
    {
        return eventService;
    }

}
