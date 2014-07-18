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
package com.cgi.pacoshop.fulfilmentprocess.populator.impl;


import com.cgi.pacoshop.fulfilmentprocess.exceptions.SSOException;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import org.apache.log4j.Logger;
import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module build
 * @version 1.0v Feb 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SSOAccountPopulatorImpl implements com.cgi.pacoshop.fulfilmentprocess.populator.SSOAccountPopulator
{

	private static final Logger LOG = Logger.getLogger(SSOAccountPopulatorImpl.class);

	@Override
	public SSOAccountRequest populate(final AbstractOrderModel order) throws SSOException
	{
		if (order == null)
		{
			debug(LOG, "Called the SSOAccountPopulator with a null order.");
			throw new SSOException("Called the SSOAccountPopulator with a null order.");
		}

		SSOAccountRequest result = new SSOAccountRequest();

		if (deliveryEmailExist(order))
		{
			result.setEmail(order.getDeliveryAddress().getEmail());
			result.setPopulated(true);
		}

		return result;
	}

	private boolean deliveryEmailExist(final AbstractOrderModel order)
	{
		if (order.getDeliveryAddress() == null)
		{
			debug(LOG, "SSOAccountPopulator, Delivery Address is null");
			return false;
		}

		if (order.getDeliveryAddress().getEmail() == null || order.getDeliveryAddress().getEmail().isEmpty())
		{
			debug(LOG, "SSOAccountPopulator, Delivery Email is empty or null");
			return false;
		}

		return true;
	}
}
