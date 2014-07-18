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

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAException;
import com.cgi.pacoshop.fulfilmentprocess.service.CAService;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;


/**
 * Represents the Action of splitting the order to send the authorization entries to CA.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubmitCAEntriesAction extends AbstractSimpleDecisionAction<OrderProcessModel>
{

	private static final Logger LOGGER = Logger.getLogger(SubmitCAEntriesAction.class);

	private CAService caService;

	@Override
	public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
	{

		final OrderModel order = orderProcessModel.getOrder();

		if (order == null)
		{
			LOGGER.error("Missing the order, exiting the process");
			return Transition.NOK;
		}

		try
		{
			debug(LOGGER, "Processing the order[" + order + "]");

			final ClientStatus status = getCaService().sendOrder(order);

			if (ClientStatus.SUCCESS == status)
			{
				debug(LOGGER, "Transition OK for order[" + order + "]");
				return Transition.OK;
			}
			else
			{
				debug(LOGGER, "Transition NOK for order[" + order + "]");
				return Transition.NOK;
			}
		}
		catch (final CAException cae)
		{
			LOGGER.error("An error occured sending the order to CA order[" + order + "]", cae);
			return Transition.NOK;
		}
	}

	/**
	 * @return the caService
	 */
	public CAService getCaService()
	{
		return caService;
	}

	/**
	 * @param caService
	 *            the caService to set
	 */
	public void setCaService(final CAService caService)
	{
		this.caService = caService;
	}

}
