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


import com.cgi.pacoshop.core.daos.BillingPlanDao;
import com.cgi.pacoshop.core.service.BillingPlanService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.enums.BillingCycleType;
import de.hybris.platform.subscriptionservices.model.BillingPlanModel;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service for managing BillingPlanModel. Implements BillingPlan Service.
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
public class DefaultBillingPlanService implements BillingPlanService
{
	@Resource
	private BillingPlanDao billingPlanDao;

	@Resource
	private ModelService modelService;

	@Override
	@Transactional
	public BillingPlanModel findBillingPlanById(final String id)
	{
		return billingPlanDao.findBillingPlanById(id);
	}

	@Override
	public BillingPlanModel getOrCreateBillingPlan(final String id)
	{
		Assert.notNull(id, "Id for BillingPlan cannot be null");

		BillingPlanModel billingPlanModel;
		try
		{
			billingPlanModel = findBillingPlanById(id);
		}
		catch (ModelNotFoundException exception)
		{
			billingPlanModel = modelService.create(BillingPlanModel.class);
			billingPlanModel.setId(id);
			billingPlanModel.setBillingCycleType(BillingCycleType.SUBSCRIPTION_START);
		}
		return billingPlanModel;
	}
}
