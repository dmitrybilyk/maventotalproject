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
package com.cgi.pacoshop.fulfilmentprocess.actions.order;


import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.events.OrderConfirmationEvent;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.EventService;
import javax.annotation.Resource;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;

/**
 * This action send an Confirmation email containing payment information about the order .
 *
 * @module shop
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SendConfirmationEmailAction extends AbstractProceduralAction<OrderProcessModel>
{
	private static final Logger LOGGER = Logger.getLogger(SendConfirmationEmailAction.class);

	@Resource
	private ConfigurationService configurationService;

	private EventService         eventService;

	/**
	 * Execution action.
	 * @param orderProcessModel is process model.
	 * @throws Exception like Exception
	 */
	@Override
	public void executeAction(final OrderProcessModel orderProcessModel) throws Exception
	{
		if (sendEmail(orderProcessModel))
		{
			getEventService().publishEvent(new OrderConfirmationEvent(orderProcessModel));
		}

		LOGGER.info("Process: " + orderProcessModel.getCode() + " in step " + getClass());
	}

	/**
	 *
	 * @param orderProcessModel the orderProcessModel.
	 * @return boolean result.
	 */
	private boolean sendEmail(final OrderProcessModel orderProcessModel)
	{
		for (AbstractOrderEntryModel orderEntry : orderProcessModel.getOrder().getEntries())
		{
			final String typeCode = orderEntry.getProduct().getProductType().getCode();
			if (typeCode.equals(getProperty(PacoshopFulfilmentProcessConstants.ProductTypes.DIGITAL_ABO))
					  || typeCode.equals(getProperty(PacoshopFulfilmentProcessConstants.ProductTypes.GOOD))
					  || typeCode.equals(getProperty(PacoshopFulfilmentProcessConstants.ProductTypes.NEWS_LETTER))
					  || typeCode.equals(getProperty(PacoshopFulfilmentProcessConstants.ProductTypes.PRINT_ABO)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param key the property key.
	 * @return string value.
	 */
	private String getProperty(final String key)
	{
		final Configuration configuration = configurationService.getConfiguration();
		return configuration.getString(key);
	}

	/**
	 * eventService.
	 * @param eventService like eventService
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
