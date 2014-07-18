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

import com.cgi.pacoshop.fulfilmentprocess.actions.order.OrderFulfillmentType;

import de.hybris.platform.core.model.order.AbstractOrderModel;


/**
 * Defines the orderRoutingService. This service analyze an order and direct which fulfillment shall be used.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface OrderRoutingService
{
	/**
	 * Analyzes an order and defines which fulfillment type the order belong to.
	 *
	 * @param order
	 *            the order to analyze.
	 * @return the orderFulfillmentType associated, or null if the order doesn't match any criteria.
	 */
	OrderFulfillmentType analyzeOrder(AbstractOrderModel order);
}
