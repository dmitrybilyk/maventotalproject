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
package com.cgi.pacoshop.core.strategies.impl;

import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.VATGroupService;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.order.strategies.calculation.impl.DefaultFindDeliveryCostStrategy;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.PriceValue;
import javax.annotation.Resource;

/**
 * Pacoshop implementation of {@link de.hybris.platform.order.strategies.calculation.FindDeliveryCostStrategy}.
 *
 * @author symmetrics - a CGI Group brand <oleg.erofeev@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v March 7, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 */
public class PacoshopFindDeliveryCostStrategy extends DefaultFindDeliveryCostStrategy
{
	private static final long serialVersionUID = 1827023260919750139L;

	@Resource
	private transient VATGroupService vatGroupService;

	@Resource
	private transient ShopConfigurationService shopConfigurationService;

	@Override
	public PriceValue getDeliveryCost(final AbstractOrderModel order)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("order", order);
		return new PriceValue(order.getCurrency().getIsocode(), order.getDeliveryCost(), order.getNet());
	}

}
