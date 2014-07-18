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
package com.cgi.pacoshop.core.strategies.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.strategies.impl.DefaultCreateOrderFromCartStrategy;
import java.util.List;


/**
 * Order number creation strategy that takes the designated order code
 * if it exists in order OR generates one if it does not
 *
 * @module pacoshopcore
 * @version 1.0v Jan 30, 2014
 * @author Oleg Erofeev <oleg.erofeev@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 */
public class PacoShopCreateOrderFromCartStrategy extends DefaultCreateOrderFromCartStrategy
{

	@Override
	public OrderModel createOrderFromCart(final CartModel cart) throws InvalidCartException
	{
		final OrderModel result = super.createOrderFromCart(cart);
		generateAndPutCodeForOrderEntries(result);
		return result;
	}

	private void generateAndPutCodeForOrderEntries(final OrderModel order)
	{
		final List<AbstractOrderEntryModel> orderEntryModels = order.getEntries();
		final String orderCode = order.getCode();
		for (int i = 0; i < orderEntryModels.size(); i++)
		{
			String orderEntryCode = new StringBuilder().append(orderCode).append("-").append(i + 1).toString();
			orderEntryModels.get(i).setCode(orderEntryCode);
		}
	}

	@Override
	protected String generateOrderCode(final CartModel cart)
	{
        return cart.getDesignatedOrderCode() != null ? cart.getDesignatedOrderCode() : super.generateOrderCode(cart);
	}
}
