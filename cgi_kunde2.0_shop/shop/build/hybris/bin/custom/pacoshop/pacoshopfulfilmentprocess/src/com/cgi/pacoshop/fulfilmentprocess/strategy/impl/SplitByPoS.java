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
package com.cgi.pacoshop.fulfilmentprocess.strategy.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.strategy.AbstractSplittingStrategy;
import de.hybris.platform.storelocator.model.PointOfServiceModel;


/**
 * OOTB left there for future expension of the platform.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SplitByPoS extends AbstractSplittingStrategy
{
	@Override
	public Object getGroupingObject(final AbstractOrderEntryModel orderEntry)
	{
		return orderEntry.getDeliveryPointOfService();
	}

	@Override
	public void afterSplitting(final Object groupingObject, final ConsignmentModel consignmentModel)
	{
		consignmentModel.setDeliveryPointOfService((PointOfServiceModel) groupingObject);
	}
}
