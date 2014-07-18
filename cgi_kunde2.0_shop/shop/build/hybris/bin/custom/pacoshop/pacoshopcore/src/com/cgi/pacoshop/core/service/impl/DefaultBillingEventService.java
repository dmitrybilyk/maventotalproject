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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.daos.BillingEventDao;
import com.cgi.pacoshop.core.service.BillingEventService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.BillingEventModel;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service for managing BillingEventModel. Implements BillingEvent Service.
 *
 * @module build
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultBillingEventService implements BillingEventService
{
	@Resource
	private BillingEventDao billingEventDao;

	@Resource
	private ModelService modelService;

	@Override
	@Transactional
	public BillingEventModel findBillingEventByCode(final String code)
	{
		return billingEventDao.findBillingEventByCode(code);
	}

	@Override
	public BillingEventModel getOrCreateBillingEvent(final String code)
	{
		Assert.notNull(code, "Code for BillingEvent cannot be null");

		BillingEventModel billingEventModelModel;
		try
		{
			billingEventModelModel = findBillingEventByCode(code);
		}
		catch (ModelNotFoundException exception)
		{
			billingEventModelModel = modelService.create(BillingEventModel.class);
			billingEventModelModel.setCode(code);
		}
		return billingEventModelModel;
	}
}
