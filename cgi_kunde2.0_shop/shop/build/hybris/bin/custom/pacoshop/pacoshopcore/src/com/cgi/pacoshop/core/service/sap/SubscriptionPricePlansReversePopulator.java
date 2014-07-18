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
package com.cgi.pacoshop.core.service.sap;


import com.cgi.pacoshop.core.enums.SubscriptionPhase;
import com.cgi.pacoshop.core.model.Price;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.service.BillingEventService;
import com.cgi.pacoshop.core.service.BillingFrequencyService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.PriceRowCreator;
import com.cgi.pacoshop.core.util.sap.subscription.SubscriptionType;
import com.cgi.pacoshop.core.util.sap.subscription.SubscriptionTypeQualifier;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.BillingEventModel;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;
import de.hybris.platform.subscriptionservices.model.OneTimeChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.RecurringChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Populates subscription product model with subscriptionPricePlans.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Feb 21, 2014
 * @module build
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class SubscriptionPricePlansReversePopulator implements Populator<SubscriptionProductDTO, SubscriptionProductModel>
{
	public static final String PAYNOW_CODE = "paynow";

	public static final Double DEFAULT_COMMUNICATED_PRICE = 0.0;

	@Resource
	private ModelService              modelService;
	@Resource
	private BillingEventService       billingEventService;
	@Resource
	private BillingFrequencyService   billingFrequencyService;
	@Resource
	private PriceRowCreator           priceRowCreator;
	@Resource
	private SubscriptionTypeQualifier subscriptionTypeQualifier;

	@Override
	public void populate(final SubscriptionProductDTO dto, final SubscriptionProductModel model) throws ConversionException,
																																		 ProductImportException
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(model, "Parameter SubscriptionProductModel cannot be null.");

		clearPricePlan(model);

		modelService.save(model);
		SubscriptionPricePlanModel subscriptionPricePlan = modelService.create(SubscriptionPricePlanModel.class);
		subscriptionPricePlan.setProduct(model);
		model.setEurope1Prices(Arrays.asList((PriceRowModel) subscriptionPricePlan));

		populateOneTimeChargeEntry(subscriptionPricePlan, dto);

		populateRecurringChargeEntry(subscriptionPricePlan, dto, model.getUnit());

	}

	private void clearPricePlan(final SubscriptionProductModel model)
	{
		Assert.notNull(model, "Parameter SubscriptionProductModel cannot be null.");

		if (model.getEurope1Prices() != null)
		{
			for (PriceRowModel priceRowModel : model.getEurope1Prices())
			{
				modelService.removeAll(((SubscriptionPricePlanModel) priceRowModel).getOneTimeChargeEntries());
				modelService.removeAll(((SubscriptionPricePlanModel) priceRowModel).getRecurringChargeEntries());
			}
			modelService.removeAll(model.getEurope1Prices());
		}
	}

	private void populateOneTimeChargeEntry(final SubscriptionPricePlanModel subscriptionPricePlan,
														 final SubscriptionProductDTO dto)
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(subscriptionPricePlan, "Parameter SubscriptionPricePlanModel cannot be null.");

		BillingEventModel billingEventModel = billingEventService.getOrCreateBillingEvent(PAYNOW_CODE);

		OneTimeChargeEntryModel oneTimeChargeEntryModel = modelService.create(OneTimeChargeEntryModel.class);
		oneTimeChargeEntryModel.setBillingEvent(billingEventModel);

		final SubscriptionType subscriptionType = subscriptionTypeQualifier.determineSubscriptionType(dto);
		if (subscriptionType.equals(SubscriptionType.NORMAL))
		{
			oneTimeChargeEntryModel.setPrice(dto.getBookedPriceConsequence().getAmount());
			subscriptionPricePlan.setCurrency(priceRowCreator.getCurrencyModel(dto.getBookedPriceConsequence().getCurrency()));
		}
		else
		{
			oneTimeChargeEntryModel.setPrice(dto.getBookedPriceSample().getAmount());
			subscriptionPricePlan.setCurrency(priceRowCreator.getCurrencyModel(dto.getBookedPriceSample().getCurrency()));
		}
		modelService.save(subscriptionPricePlan);
		oneTimeChargeEntryModel.setSubscriptionPricePlanOneTime(subscriptionPricePlan);
		subscriptionPricePlan.setOneTimeChargeEntries(Arrays.asList(oneTimeChargeEntryModel));
		modelService.save(oneTimeChargeEntryModel);
	}

	private void populateRecurringChargeEntry(final SubscriptionPricePlanModel subscriptionPricePlan,
															final SubscriptionProductDTO dto, final UnitModel unit)
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(subscriptionPricePlan, "Parameter SubscriptionPricePlanModel cannot be null.");
		Assert.notNull(unit, "Parameter UnitModel cannot be null.");

		final SubscriptionType subscriptionType = subscriptionTypeQualifier.determineSubscriptionType(dto);
		if (subscriptionType.equals(SubscriptionType.NORMAL))
		{
			RecurringChargeEntryModel recurringChargeEntryModel =
					  getRecurringChargeEntryModel(dto, unit, SubscriptionPhase.STANDART);

			recurringChargeEntryModel.setSubscriptionPricePlanRecurring(subscriptionPricePlan);
			subscriptionPricePlan.setRecurringChargeEntries(Arrays.asList(recurringChargeEntryModel));
			modelService.save(recurringChargeEntryModel);
		}
		if (subscriptionType.equals(SubscriptionType.SWR))
		{
			/* Creating Sample RecurringChargeEntryModel */
			RecurringChargeEntryModel sampleRecurringChargeEntryModel =
					  getRecurringChargeEntryModel(dto, unit, SubscriptionPhase.SAMPLE);
			sampleRecurringChargeEntryModel.setSubscriptionPricePlanRecurring(subscriptionPricePlan);
			modelService.save(sampleRecurringChargeEntryModel);

			/* Creating Consecutive RecurringChargeEntryModel */
			RecurringChargeEntryModel consecutiveRecurringChargeEntryModel =
					  getRecurringChargeEntryModel(dto, unit, SubscriptionPhase.CONSECUTIVE);
			consecutiveRecurringChargeEntryModel.setSubscriptionPricePlanRecurring(subscriptionPricePlan);
			modelService.save(consecutiveRecurringChargeEntryModel);

			subscriptionPricePlan.setRecurringChargeEntries(
					  Arrays.asList(consecutiveRecurringChargeEntryModel, sampleRecurringChargeEntryModel));
		}
		if (subscriptionType.equals(SubscriptionType.SNOR))
		{
			/* Creating Sample RecurringChargeEntryModel */
			RecurringChargeEntryModel sampleRecurringChargeEntryModel =
					  getRecurringChargeEntryModel(dto, unit, SubscriptionPhase.SAMPLE);
			sampleRecurringChargeEntryModel.setSubscriptionPricePlanRecurring(subscriptionPricePlan);
			modelService.save(sampleRecurringChargeEntryModel);

			subscriptionPricePlan.setRecurringChargeEntries(Arrays.asList(sampleRecurringChargeEntryModel));
		}
	}

	private RecurringChargeEntryModel getRecurringChargeEntryModel(final SubscriptionProductDTO dto, final UnitModel unit,
																						final SubscriptionPhase subscriptionPhase)
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");

		final Price bookedPrice, communicatedPrice;
		final Integer communicatedBillingFrequency;
		if (SubscriptionPhase.SAMPLE.equals(subscriptionPhase))
		{
			bookedPrice = dto.getBookedPriceSample();
			communicatedPrice = dto.getCommunicatedPriceSample();
			communicatedBillingFrequency = dto.getCommunicatedBillingFrequencySample();
		}
		else
		{
			bookedPrice = dto.getBookedPriceConsequence();
			communicatedPrice = dto.getCommunicatedPriceConsequence();
			communicatedBillingFrequency = dto.getCommunicatedBillingFrequencyConsequence();
		}

		RecurringChargeEntryModel recurringChargeEntryModel = modelService.create(RecurringChargeEntryModel.class);
		recurringChargeEntryModel.setPrice(bookedPrice.getAmount());		
		recurringChargeEntryModel.setCycleStart(1);
		recurringChargeEntryModel.setSubscriptionPhase(subscriptionPhase);

		if (communicatedPrice != null)
		{
			recurringChargeEntryModel.setCommunicatedPrice(communicatedPrice.getAmount());
		}
		else
		{
			recurringChargeEntryModel.setCommunicatedPrice(DEFAULT_COMMUNICATED_PRICE);
		}

		final BillingFrequencyModel billingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(
				  communicatedBillingFrequency,
				  unit,
				  subscriptionPhase);
		recurringChargeEntryModel.setCommunicatedBillingFrequency(billingFrequencyModel);

		return recurringChargeEntryModel;
	}
}
