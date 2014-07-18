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
package com.cgi.pacoshop.facades.populators;


import de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;
import org.springframework.util.Assert;


/**
 * Extends default OrderData populator with redirectUrlPopulation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 14, 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class PacoOrderPopulator extends AbstractOrderPopulator<OrderModel, OrderData>
{

	@Override
	public void populate(final OrderModel source, final OrderData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setRedirectUrl(source.getRedirectUrl());
	}
}
