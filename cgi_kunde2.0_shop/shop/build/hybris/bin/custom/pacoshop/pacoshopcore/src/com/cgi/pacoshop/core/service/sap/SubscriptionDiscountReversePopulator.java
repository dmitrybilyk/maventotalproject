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
import com.cgi.pacoshop.core.model.BillingFrequencyDiscountModel;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.service.BillingFrequencyService;
import com.cgi.pacoshop.core.util.sap.subscription.SubscriptionType;
import com.cgi.pacoshop.core.util.sap.subscription.SubscriptionTypeQualifier;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Populator for subscription products discounts field.
 *
 * @module build
 * @version 1.0v Mar 13, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SubscriptionDiscountReversePopulator implements Populator<SubscriptionProductDTO, SubscriptionProductModel>
{
	public static final int EXTERNAL_FREQUENCY_QUARTER   = 3;
	public static final int EXTERNAL_FREQUENCY_HALF_YEAR = 6;
	public static final int EXTERNAL_FREQUENCY_YEAR      = 12;
	@Resource
	private ModelService              modelService;
	@Resource
	private SubscriptionTypeQualifier subscriptionTypeQualifier;
	@Resource
	private BillingFrequencyService   billingFrequencyService;

	@Override
	public void populate(final SubscriptionProductDTO dto, final SubscriptionProductModel model) throws ConversionException
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(model, "Parameter SubscriptionProductModel cannot be null.");

		if (model.getBillingFrequencyDiscounts() != null)
		{
			modelService.removeAll(model.getBillingFrequencyDiscounts());
		}

		List<BillingFrequencyDiscountModel> discountsList = new ArrayList<>();

		if (dto.getDiscountYear() != null && dto.getDiscountYear() != 0)
		{
			discountsList.add(createDiscountBillingFrequency(EXTERNAL_FREQUENCY_YEAR, dto.getDiscountYear(), dto, model.getUnit()));
		}
		if (dto.getDiscountHalfYear() != null && dto.getDiscountHalfYear() != 0)
		{
			discountsList.add(
					  createDiscountBillingFrequency(EXTERNAL_FREQUENCY_HALF_YEAR, dto.getDiscountHalfYear(), dto, model.getUnit()));
		}
		if (dto.getDiscountQuarter() != null && dto.getDiscountQuarter() != 0)
		{
			discountsList.add(
					  createDiscountBillingFrequency(EXTERNAL_FREQUENCY_QUARTER, dto.getDiscountQuarter(), dto, model.getUnit()));
		}

		model.setBillingFrequencyDiscounts(discountsList);
	}

	private BillingFrequencyDiscountModel createDiscountBillingFrequency(final int externalFrequency, final int discountPercents,
																								final SubscriptionProductDTO dto,
																								final UnitModel unit)
	{
		Assert.notNull(externalFrequency, "Parameter externalFrequency cannot be null.");
		Assert.notNull(discountPercents, "Parameter discountPercents cannot be null.");
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(unit, "Parameter UnitModel cannot be null.");

		BillingFrequencyDiscountModel billingFrequencyDiscountModel = modelService.create(BillingFrequencyDiscountModel.class);
		billingFrequencyDiscountModel.setDiscountPercents(discountPercents);

		final SubscriptionType subscriptionType = subscriptionTypeQualifier.determineSubscriptionType(dto);

		BillingFrequencyModel billingFrequencyModel = null;

		if (subscriptionType == SubscriptionType.NORMAL)
		{
			billingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(externalFrequency, unit,
																												 SubscriptionPhase.STANDART);
		}
		if (subscriptionType == SubscriptionType.SNOR)
		{
			billingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(externalFrequency, unit,
																												 SubscriptionPhase.SAMPLE);
		}
		if (subscriptionType == SubscriptionType.SWR)
		{
			billingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(externalFrequency, unit,
																												 SubscriptionPhase.CONSECUTIVE);
		}
		billingFrequencyDiscountModel.setBillingFrequency(billingFrequencyModel);
		modelService.save(billingFrequencyDiscountModel);
		return billingFrequencyDiscountModel;
	}
}
