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
package com.cgi.pacoshop.fulfilmentprocess.events;


import de.hybris.platform.orderprocessing.events.OrderProcessingEvent;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;

/**
 * This is event for starting process Confirmation email sending
 *
 * @module build
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OrderConfirmationEvent extends OrderProcessingEvent
{
	private static final long serialVersionUID = -293422455711438189L;

	/**
	 * Order Error Event.
	 * @param process is process model.
	 * @throws de.hybris.platform.task.RetryLaterException
	 * @throws Exception
	 */
	public OrderConfirmationEvent(final OrderProcessModel process)
	{
		super(process);
	}
}
