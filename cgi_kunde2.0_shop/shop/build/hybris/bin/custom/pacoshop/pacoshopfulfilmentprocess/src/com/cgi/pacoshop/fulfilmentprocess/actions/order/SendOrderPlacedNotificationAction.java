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

import de.hybris.platform.orderprocessing.events.OrderPlacedEvent;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.event.EventService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Action that sents the OrderPlacedNotification.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SendOrderPlacedNotificationAction extends AbstractProceduralAction<OrderProcessModel>
{
	private static final Logger LOG = Logger.getLogger(SendOrderPlacedNotificationAction.class);

	private EventService eventService;

	@Override
	public void executeAction(final OrderProcessModel process)
	{


		getEventService().publishEvent(new OrderPlacedEvent(process));
		LOG.info("Process: " + process.getCode() + " in step " + getClass());
	}

	/**
	 * Gets the eventService.
	 *
	 * @return the eventService.
	 */
	protected EventService getEventService()
	{
		return eventService;
	}

	/**
	 * Sets the eventService.
	 *
	 * @param eventService
	 *            the eventService.
	 */
	@Required
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}
}
