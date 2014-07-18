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

package com.cgi.pacoshop.core.interceptors;

import com.cgi.pacoshop.core.service.BillingFrequencyService;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;
import org.springframework.beans.factory.annotation.Required;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

/**
 * Unique interceptor for billing frequency.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jun 13, 2014
 * @module Pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class BillingFrequencyUniqueInterceptor implements ValidateInterceptor
{
	private BillingFrequencyService billingFrequencyService;

	/**
	 * Validate.
	 *
	 * @param model model
	 * @param ctx   interceptor context
	 * @throws de.hybris.platform.servicelayer.interceptor.InterceptorException Non-unique
	 */
	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		validateParameterNotNull(model, "Parameter model must not be null");

		if (model instanceof BillingFrequencyModel)
		{
			final BillingFrequencyModel billingFrequencyModel = (BillingFrequencyModel) model;

			if (isSaveProcess(billingFrequencyModel) && isExist(billingFrequencyModel))
			{
				throw new InterceptorException("BillingFrequency not unique!");
			}
		}
	}

	private boolean isSaveProcess(final BillingFrequencyModel billingFrequencyModel)
	{
		return billingFrequencyModel.getPk() == null;
	}

	private boolean isExist(final BillingFrequencyModel billingFrequencyModel)
	{
		try
		{
			billingFrequencyService.findBillingFrequency(
					billingFrequencyModel.getExternalFrequency(),
					billingFrequencyModel.getUnit(),
					billingFrequencyModel.getSubscriptionPhase()
			);
		}
		catch (Exception exception)
		{
			return false;
		}

		return true;
	}

	/**
	 * @return the productService
	 */
	protected BillingFrequencyService getBillingFrequencyService()
	{
		return billingFrequencyService;
	}

	/**
	 * productService setter.
	 *
	 * @param billingFrequencyService - new productService;
	 */
	@Required
	public void setBillingFrequencyService(final BillingFrequencyService billingFrequencyService)
	{
		this.billingFrequencyService = billingFrequencyService;
	}
}
