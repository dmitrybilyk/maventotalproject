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
package com.cgi.pacoshop.core.strategies.impl;


import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.strategies.calculation.impl.FindPricingWithCurrentPriceFactoryStrategy;
import de.hybris.platform.subscriptionservices.model.OneTimeChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.price.SubscriptionCommercePriceService;
import de.hybris.platform.util.PriceValue;
import de.hybris.platform.util.TaxValue;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Required;


/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module cgi_kunde_shop
 * @version 1.0v Mar 14, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oksana Arkhypova <oksana.arkhypova@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class PacoShopTaxingWithPriceFactoryStrategy extends FindPricingWithCurrentPriceFactoryStrategy
{

	public static final String SAMPLE = "SAMPLE";
	private SubscriptionCommercePriceService commercePriceService;

	@Override
	public PriceValue findBasePrice(final AbstractOrderEntryModel entry) throws CalculationException
	{
		final ProductModel product = entry.getProduct();
		final AbstractOrderModel order = entry.getOrder();
		if (product instanceof SubscriptionProductModel)
		{
			final SubscriptionPricePlanModel pricePlan = getCommercePriceService().getSubscriptionPricePlanForEntry(entry);
			if (pricePlan.getOneTimeChargeEntries().size() == 1)
			{
				for (final OneTimeChargeEntryModel chargeEntry : pricePlan.getOneTimeChargeEntries())
				{
					return createPriceValueForOneTimeChargeEntry(order, chargeEntry);
				}
			}
			else
			{
				for (final OneTimeChargeEntryModel chargeEntry : pricePlan.getOneTimeChargeEntries())
				{
					if (chargeEntry.getBillingEvent().getSubscriptionPhase().getCode().equals(SAMPLE))
					{
						return createPriceValueForOneTimeChargeEntry(order, chargeEntry);
					}
				}
			}


		}
		else
		{
			for (final PriceRowModel productPriceRow : product.getEurope1Prices())
			{
				if (productPriceRow.getCurrency().getIsocode().equals(order.getCurrency().getIsocode()))
				{
					return createPriceValueForPriceRow(order, productPriceRow);
				}
			}


		}

		return super.findBasePrice(entry);
	}

	@Override
	public Collection findTaxValues(final AbstractOrderEntryModel entry) throws CalculationException
	{
		final AbstractOrderModel order = entry.getOrder();

		return Arrays.asList(createTaxValue(entry, order));
	}

	/**
	 * Gets commerce price service.
	 *
	 * @return the commerce price service
	 */
	protected SubscriptionCommercePriceService getCommercePriceService()
	{
		return commercePriceService;
	}

	/**
	 * Sets commerce price service.
	 *
	 * @param commercePriceService - the commerce price service.
	 */
	@Required
	public void setCommercePriceService(final SubscriptionCommercePriceService commercePriceService)
	{
		this.commercePriceService = commercePriceService;
	}

	private PriceValue createPriceValueForPriceRow(final AbstractOrderModel order, final PriceRowModel productPriceRow)
	{
		return new PriceValue(order.getCurrency().getIsocode(), productPriceRow.getPrice().doubleValue(), order.getNet()
				  .booleanValue());
	}

	private PriceValue createPriceValueForOneTimeChargeEntry(final AbstractOrderModel order,
																				final OneTimeChargeEntryModel chargeEntry)
	{
		return new PriceValue(order.getCurrency().getIsocode(), chargeEntry.getPrice().doubleValue(), order.getNet()
				  .booleanValue());
	}

	private TaxValue createTaxValue(final AbstractOrderEntryModel entry, final AbstractOrderModel order)
	{
		Double vatRate = entry.getProduct().getVatGroup().getVatRate();
		return new TaxValue(entry.getProduct().getVatGroup().getId(), vatRate,
								  order.getNet().booleanValue(), order.getCurrency().getIsocode());
	}

}
