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
package com.cgi.pacoshop.core.model;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.platform.subscriptionservices.model.BillingTimeModel;
import de.hybris.platform.subscriptionservices.model.ChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.OneTimeChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.RecurringChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.model.UsageChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.UsageChargeModel;

import com.cgi.pacoshop.core.enums.SubscriptionPhase;


/**
 * 
 * AttributeHandler for dynamic attribute ChargeEntry.billingTime
 * 
 */
public class BillingTimeChargeEntryAttribute extends AbstractDynamicAttributeHandler<BillingTimeModel, ChargeEntryModel>
{
	@Override
	public BillingTimeModel get(final ChargeEntryModel model)
	{
		SubscriptionPricePlanModel subscriptionPricePlanModel = null;
		if (model instanceof OneTimeChargeEntryModel)
		{
			subscriptionPricePlanModel = ((OneTimeChargeEntryModel) model).getSubscriptionPricePlanOneTime();
		}
		else if (model instanceof RecurringChargeEntryModel)
		{
			subscriptionPricePlanModel = ((RecurringChargeEntryModel) model).getSubscriptionPricePlanRecurring();
		}
		else if (model instanceof UsageChargeEntryModel)
		{
			final UsageChargeModel usageChargeModel = ((UsageChargeEntryModel) model).getUsageCharge();
			if (usageChargeModel != null)
			{
				subscriptionPricePlanModel = usageChargeModel.getSubscriptionPricePlanUsage();
			}
		}

		// YTODO same code as in BillingFrequencyUsageChargeAttribute -> consider refactoring in DefaultBillingFrequencyService
		if (subscriptionPricePlanModel != null && subscriptionPricePlanModel.getProduct() instanceof SubscriptionProductModel)
		{
			final SubscriptionProductModel subscriptionProduct = (SubscriptionProductModel) subscriptionPricePlanModel.getProduct();

			if (subscriptionProduct.getSubscriptionTerm() != null
					&& subscriptionProduct.getSubscriptionTerm().getBillingPlan() != null)
			{
				if (model instanceof RecurringChargeEntryModel
						&& SubscriptionPhase.CONSECUTIVE.equals(((RecurringChargeEntryModel) model).getSubscriptionPhase()))
				{
					return subscriptionProduct.getSubscriptionTerm().getBillingPlan().getConsecutiveBillingFrequency();
				}
				else
				{
					return subscriptionProduct.getSubscriptionTerm().getBillingPlan().getBillingFrequency();
				}
			}
		}

		return null;
	}

	@Override
	public void set(final ChargeEntryModel model, final BillingTimeModel value)
	{
		super.set(model, value);
	}

}
