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
package com.cgi.pacoshop.core.service.order;


import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.ProductBonusModel;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.VATGroupService;
import com.cgi.pacoshop.core.strategies.TaxCalculationStrategy;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.order.impl.DefaultCalculationService;
import de.hybris.platform.order.strategies.calculation.OrderRequiresCalculationStrategy;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.util.TaxValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;

/**
 * Calculation service. Used default service with replayed 'factor'
 *
 * @module shop
 * @version 1.0v Apr 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PacoShopCalculationService extends DefaultCalculationService
{
	private static final long serialVersionUID = 1827023260919750129L;

	private static final double DEFAULT_TAX_ADJUSTMENT_FACTOR = 1.0;
	private static final int    ZERO                          = 0;
	private static final String SHIPPING_TAX                  = "Shipping_TAX";
	private static final String PAYMENT_TAX                   = "Payment_TAX";

	@Resource
	private transient OrderRequiresCalculationStrategy orderRequiresCalculationStrategy;

	@Resource
	private transient VATGroupService vatGroupService;

	@Resource
	private transient ShopConfigurationService shopConfigurationService;

	@Resource
	private transient TaxCalculationStrategy taxCalculationStrategy;


	@Override
	public void calculateTotals(final AbstractOrderEntryModel entry, final boolean recalculate)
	{
		final boolean needCalculation = recalculate || orderRequiresCalculationStrategy.requiresCalculation(entry);
		super.calculateTotals(entry, recalculate);
		if (needCalculation && entry.getOrder().getParent() == null)
		{
			changeTotalPriceIfProductHasProductBonus(entry);
		}
	}


	/**
	 * Methos calculate total tax values.
	 * @param order the order.
	 * @param recalculate - the recalculate.
	 * @param digits - the digits.
	 * @param taxAdjustmentFactor - the taxAdjustmentFactor.
	 * @param taxValueMap - the taxValueMap.
	 * @return total taxes
	 */
	@Override
	protected double calculateTotalTaxValues(final AbstractOrderModel order,
														  final boolean recalculate, final int digits, final double taxAdjustmentFactor,
														  final Map<TaxValue, Map<Set<TaxValue>, Double>> taxValueMap)
	{
		if (recalculate || orderRequiresCalculationStrategy.requiresCalculation(order))
		{
			double totalTaxes =
					  super.calculateTotalTaxValues(order, recalculate, digits, DEFAULT_TAX_ADJUSTMENT_FACTOR, taxValueMap);

			final double vatRate = shopConfigurationService.getDeliveryVatRate();

			TaxValue shippingTax = calculateTaxValue(order, order.getDeliveryCost(), SHIPPING_TAX, vatRate);
			totalTaxes = addToTotalTaxes(order, totalTaxes, shippingTax);

			TaxValue paymentTax = calculateTaxValue(order, order.getPaymentCost(), PAYMENT_TAX, vatRate);
			totalTaxes = addToTotalTaxes(order, totalTaxes, paymentTax);

			return CoreAlgorithms.round(totalTaxes, Math.max(digits, ZERO));
		}
		else
		{
			return order.getTotalTax();
		}
	}

	private double addToTotalTaxes(final AbstractOrderModel order, final double totalTaxes, final TaxValue taxValue)
	{
		double result = totalTaxes;
		if (taxValue != null && taxValue.getAppliedValue() > ZERO)
		{
			Collection<TaxValue> taxValues = new ArrayList<TaxValue>(order.getTotalTaxValues());
			taxValues.add(taxValue);
			order.setTotalTaxValues(taxValues);
			result += taxValue.getAppliedValue();
		}
		return result;
	}

	private TaxValue calculateTaxValue(
			  final AbstractOrderModel order, final Double costValue, final String name, final double vatRate)
	{
		TaxValue result = null;
		if (costValue != null && costValue > ZERO)
		{
			if (!vatGroupService.findAndMapAllVATGroups().containsKey(vatRate))
			{
				throw new RuntimeException("PacoShopCalculationService: VAT Rate: " + vatRate + " - NOT exist");
			}

			double taxValue = taxCalculationStrategy.calculateTaxEachIncluded(costValue, vatRate);

			result = new TaxValue(name, vatRate, order.getNet(), taxValue, order.getCurrency().getIsocode());
		}
		return result;
	}


	private void changeTotalPriceIfProductHasProductBonus(final AbstractOrderEntryModel orderEntry)
	{
			ProductModel product = orderEntry.getProduct();
			if (product instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel subProduct = (SubscriptionProductModel) product;
				for (BonusModel bonus : subProduct.getBonuses())
				{
					if (bonus instanceof ProductBonusModel)
					{
						ProductBonusModel productBonus = (ProductBonusModel) bonus;
						if (productBonus.getAdditionalPayment() != null && Boolean.FALSE.equals(subProduct.getReadersCanvassReaders()))
						{
							Double productBonusPrice = productBonus.getAdditionalPayment().getPrice(),
									 orderEntryTotalPrice = orderEntry.getTotalPrice();

							Double newOrderEntryTotalPrice = productBonusPrice + orderEntryTotalPrice;

							orderEntry.setTotalPrice(newOrderEntryTotalPrice);
							calculateTotalTaxValues(orderEntry);
							getModelService().save(orderEntry);
						}
					}
				}
			}
	}
}
