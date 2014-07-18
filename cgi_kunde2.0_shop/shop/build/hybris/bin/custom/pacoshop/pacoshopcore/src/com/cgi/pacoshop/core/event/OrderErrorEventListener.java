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
package com.cgi.pacoshop.core.event;


import com.cgi.pacoshop.fulfilmentprocess.events.OrderErrorEvent;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import org.springframework.beans.factory.annotation.Required;

/**
 * This is listener for OrderErrorEvent
 * if this event happens this class starts process orderErrorEmailProcess
 * each send email with error to admin
 *
 * @module build
 * @version 1.0v Mar 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OrderErrorEventListener extends AbstractSiteEventListener<OrderErrorEvent>
{

	private ModelService           modelService;
	private BusinessProcessService businessProcessService;

	/**
	 * get BusinessProcessService.
	 * @return BusinessProcessService like BusinessProcessService.
	 */
	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * businessProcessService.
	 * @param businessProcessService like businessProcessService.
	 */
	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 *
	 * @return ModelService like ModelService.
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 *
	 * @param modelService like modelService.
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final OrderErrorEvent orderPlacedEvent)
	{
		final OrderModel orderModel = orderPlacedEvent.getProcess().getOrder();
		final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
				  "orderErrorEmailProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
				  "orderErrorEmailProcess");
		orderProcessModel.setOrder(orderModel);
		orderProcessModel.setEndMessage(orderPlacedEvent.getProcess().getEndMessage());
		getModelService().save(orderProcessModel);
		getBusinessProcessService().startProcess(orderProcessModel);
	}


	@Override
	protected boolean shouldHandleEvent(final OrderErrorEvent event)
	{
		final OrderModel order = event.getProcess().getOrder();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
		final BaseSiteModel site = order.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return SiteChannel.B2C.equals(site.getChannel());
	}
}

