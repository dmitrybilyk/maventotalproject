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


import com.cgi.pacoshop.core.daos.BillingFrequencyDao;
import com.cgi.pacoshop.core.enums.SubscriptionPhase;
import com.cgi.pacoshop.core.service.BillingFrequencyService;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Service for managing BillingFrequencyModel. Implements BillingFrequency Service.
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
public class DefaultBillingFrequencyService implements BillingFrequencyService
{
	@Resource
	private BillingFrequencyDao billingFrequencyDao;

	@Resource
	private ModelService modelService;

	@Override
	@Transactional
	public BillingFrequencyModel findBillingFrequencyByCode(final String code)
	{
		return billingFrequencyDao.findBillingFrequencyByCode(code);
	}

	@Override
	@Transactional
	public BillingFrequencyModel findBillingFrequency(final Integer externalFrequency, final UnitModel unit,
															 final SubscriptionPhase subscriptionPhase)
	{
		return billingFrequencyDao.findBillingFrequency(externalFrequency, unit, subscriptionPhase);
	}

	@Override
	public BillingFrequencyModel getOrCreateBillingFrequency(final Integer externalFrequency, final UnitModel unit,
																				final SubscriptionPhase subscriptionPhase)
	{
		Assert.notNull(externalFrequency, "Parameter externalFrequency cannot be null");
		Assert.notNull(unit, "Parameter UnitModel cannot be null");
		Assert.notNull(subscriptionPhase, "Parameter subscriptionPhase cannot be null");

		final String code = externalFrequency + "-" + unit.getCode() + "-" + subscriptionPhase;

		BillingFrequencyModel billingFrequencyModel;
		try
		{
			billingFrequencyModel = findBillingFrequency(externalFrequency, unit, subscriptionPhase);
		}
		catch (ModelNotFoundException exception)
		{
			billingFrequencyModel = modelService.create(BillingFrequencyModel.class);
			billingFrequencyModel.setCode(code);
			billingFrequencyModel.setExternalFrequency(externalFrequency);
			billingFrequencyModel.setSubscriptionPhase(subscriptionPhase);
			billingFrequencyModel.setUnit(unit);
			modelService.save(billingFrequencyModel);
		}
		return billingFrequencyModel;
	}
}
