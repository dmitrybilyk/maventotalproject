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

package com.cgi.pacoshop.core.service.productdtopopulation.impl;

import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.enums.SubscriptionPhase;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.productdtopopulation.AbstractProductDTOPopulationService;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import com.cgi.pacoshop.core.util.sap.PriceDisplayingFormatter;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.subscriptionservices.enums.TermOfServiceRenewal;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;
import de.hybris.platform.subscriptionservices.model.BillingTimeModel;
import de.hybris.platform.subscriptionservices.model.RecurringChargeEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionPricePlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionTermModel;
import javax.annotation.Resource;
import org.apache.commons.configuration.Configuration;
import static org.springframework.util.Assert.notNull;

/**
 * Populate dto for subscription products.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 26, 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class SubscriptionProductDTOPopulationService extends AbstractProductDTOPopulationService
{

	private static final String EMPTY_STRING = "";

	@Resource
	private ConfigurationService configurationService;

	@Override
	public void populate(final AbstractOrderEntryModel orderEntryModel, final ProductDTO productDTO) throws ConversionException
	{
		super.populate(orderEntryModel, productDTO);
		try
		{
			populateSubscriptionProductInformation(orderEntryModel, productDTO);
		}
		catch (Exception e)
		{
			error(getLog(),
					String.format(
							  "Exception while populating subscription productdto for productid: %s. Reason: %s",
							  orderEntryModel.getCode(), e.getMessage()), e
			);
		}
		for (AbstractProductDTOPopulationService abstractProductDTOPopulationService : getProductDTOPopulationServices())
		{
			abstractProductDTOPopulationService.populate(orderEntryModel, productDTO);
		}
	}

	private void populateSubscriptionProductInformation(final AbstractOrderEntryModel orderEntry, final ProductDTO productDTO)
	{
		SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) orderEntry.getProduct();
		for (PriceRowModel priceRowModel : subscriptionProductModel.getEurope1Prices())
		{
			if (priceRowModel instanceof SubscriptionPricePlanModel
					  && !subscriptionProductModel.getProductType().getCode().
					  equals(getConfigurationProperty(FormElementGroupConstants.NEWS_LETTER)))
			{
				SubscriptionPricePlanModel pricePlanModel = (SubscriptionPricePlanModel) priceRowModel;
				SubscriptionInfoHolder subscriptionInfoHolder = getSubscriptionProductDescription(pricePlanModel);
				productDTO.setSubscriptionInfoDisplayed(true);
				productDTO.setPrice(subscriptionInfoHolder.getPrice());
				productDTO.setCurrency(pricePlanModel.getCurrency().getSymbol());
				//============================================
				if (subscriptionProductModel.getSubscriptionTerm().getTermOfServiceRenewal() == TermOfServiceRenewal.AUTO_RENEWING)
				{
					productDTO.setTermsOfServiceRenewal(true);
				}
				productDTO.setDeliveryPeriod(subscriptionProductModel.getSubscriptionTerm().getTermOfServiceNumber().toString());
				productDTO.setPricePerUnit(subscriptionInfoHolder.getPricePerUnit());
				productDTO.setPricePerUnitWithCurrency(PriceDisplayingFormatter.formatPriceWithSpace(
						  subscriptionInfoHolder.getPricePerUnit(),
						  productDTO.getCurrency()));
				productDTO.setBillingFrequency(subscriptionInfoHolder.getBillingFrequency());
				productDTO.setBillingFrequencyUnit(
						  (subscriptionInfoHolder.getBillingFrequencyUnit()
									 == null) ? EMPTY_STRING : subscriptionInfoHolder.getBillingFrequencyUnit().toLowerCase()
				);
				productDTO.setUnit(
						  (pricePlanModel.getUnit().getCode()
									 == null) ? EMPTY_STRING : pricePlanModel.getUnit().getCode().toLowerCase());
				productDTO.setActualBilledPrice(subscriptionInfoHolder.getActualBilledPrice());
				productDTO.setActualBilledPriceWithCurrency(PriceDisplayingFormatter.formatPriceWithSpace(
						  subscriptionInfoHolder.getActualBilledPrice(), productDTO.getCurrency()));
				productDTO.setActualBilledUnit(
						  (subscriptionInfoHolder.getActualBilledUnit()
									 == null) ? EMPTY_STRING : subscriptionInfoHolder.getActualBilledUnit().toLowerCase()
				);
				productDTO.setActualBilledBillingFrequency(subscriptionInfoHolder.getActualBillingFrequency());
			}
		}
	}

	private SubscriptionInfoHolder getSubscriptionProductDescription(final SubscriptionPricePlanModel pricePlanModel)
	{
		SubscriptionInfoHolder subscriptionPriceHolder = new SubscriptionInfoHolder();
		RecurringChargeEntryModel chargeEntryModel = null;
		if (pricePlanModel.getRecurringChargeEntries() != null && pricePlanModel.getRecurringChargeEntries().size() > 1)
		{
			for (RecurringChargeEntryModel recurringChargeEntryModel : pricePlanModel.getRecurringChargeEntries())
			{
				if (recurringChargeEntryModel.getSubscriptionPhase().equals(SubscriptionPhase.CONSECUTIVE))
				{
					chargeEntryModel = recurringChargeEntryModel;
				}
			}
		}
		else
		{
			chargeEntryModel = pricePlanModel.getRecurringChargeEntries().iterator().next();
		}
		notNull(chargeEntryModel, "missing recurring charge entry");
		notNull(chargeEntryModel.getPrice(), "missing price in charge entry");
		subscriptionPriceHolder.setPricePerUnit(chargeEntryModel.getCommunicatedPrice());
		BillingFrequencyModel communicatedBillingFrequency = chargeEntryModel.getCommunicatedBillingFrequency();
		if (communicatedBillingFrequency != null)
		{
			subscriptionPriceHolder.setPrice(chargeEntryModel.getCommunicatedPrice());
			notNull(communicatedBillingFrequency.getUnit(), "missing unit in communicated billing frequency");
			subscriptionPriceHolder.setBillingFrequencyUnit(communicatedBillingFrequency.getUnit().getCode());
			notNull(communicatedBillingFrequency.getExternalFrequency(),
					  "missing external frequency in communicated billing frequency");
			subscriptionPriceHolder.setBillingFrequency(communicatedBillingFrequency.getExternalFrequency().toString());
			BillingTimeModel billingTime = chargeEntryModel.getBillingTime();
			if (billingTime != null)
			{
				if (billingTime.getUnit() != null)
				{
					subscriptionPriceHolder.setActualBilledUnit(billingTime.getUnit().getCode());
				}
				subscriptionPriceHolder.setActualBillingFrequency(billingTime.getExternalFrequency().toString());
			}
			subscriptionPriceHolder.setActualBilledPrice(chargeEntryModel.getPrice());
		}
		else
		{
			subscriptionPriceHolder.setPrice(chargeEntryModel.getPrice());
			if (pricePlanModel.getProduct() instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel product = (SubscriptionProductModel) pricePlanModel.getProduct();
				SubscriptionTermModel subscriptionTerm = product.getSubscriptionTerm();
				notNull(subscriptionTerm, "missing subscription term");
				notNull(subscriptionTerm.getBillingPlan(), "missing billing plan");
				BillingFrequencyModel billingFrequency = subscriptionTerm.getBillingPlan().getBillingFrequency();
				notNull(billingFrequency, "missing billing frequency in billing plan");
				notNull(billingFrequency.getUnit(), "missing unit in billing frequency");
				subscriptionPriceHolder.setBillingFrequencyUnit(billingFrequency.getUnit().getCode());
				subscriptionPriceHolder.setBillingFrequency(billingFrequency.getExternalFrequency().toString());
			}
		}


		return subscriptionPriceHolder;
	}

	private String getConfigurationProperty(final String key)
	{
		final Configuration configuration = configurationService.getConfiguration();
		return configuration.getString(key);
	}

	/**
	 * this is a helper class.
	 */
	private static class SubscriptionInfoHolder
	{
		private String        deliveryPeriod;
		private Double        pricePerUnit;
		private String        billingFrequencyUnit;
		private Double        actualBilledPrice;
		private String        actualBilledUnit;
		private Double        price;
		private CurrencyModel currencyModel;
		private String        billingFrequency;
		private String        actualBilledBillingFrequency;

		/**
		 * Gets actual billing frequency.
		 *
		 * @return the actual billing frequency
		 */
		public String getActualBillingFrequency()
		{
			return actualBilledBillingFrequency;
		}

		/**
		 * Sets actual billing frequency.
		 *
		 * @param actualBilledBillingFrequency the actual billing frequency
		 */
		public void setActualBillingFrequency(final String actualBilledBillingFrequency)
		{
			this.actualBilledBillingFrequency = actualBilledBillingFrequency;
		}

		/**
		 * Gets billing frequency.
		 *
		 * @return the billing frequency
		 */
		public String getBillingFrequency()
		{
			return billingFrequency;
		}

		/**
		 * Sets billing frequency.
		 *
		 * @param billingFrequency the billing frequency
		 */
		public void setBillingFrequency(final String billingFrequency)
		{
			this.billingFrequency = billingFrequency;
		}

		/**
		 * Gets delivery period.
		 *

		 * @return the delivery period
		 */
		public String getDeliveryPeriod()
		{
			return deliveryPeriod;
		}

		/**
		 * Sets delivery period.
		 *
		 * @param deliveryPeriod the delivery period
		 */
		public void setDeliveryPeriod(final String deliveryPeriod)
		{
			this.deliveryPeriod = deliveryPeriod;
		}

		/**
		 * Gets price per unit.
		 *
		 * @return the price per unit
		 */
		public Double getPricePerUnit()
		{
			return pricePerUnit;
		}

		/**
		 * Sets price per unit.
		 *
		 * @param pricePerUnit the price per unit
		 */
		public void setPricePerUnit(final Double pricePerUnit)
		{
			this.pricePerUnit = pricePerUnit;
		}

		/**
		 * Gets unit model.
		 *
		 * @return the unit model
		 */
		public String getBillingFrequencyUnit()
		{
			return billingFrequencyUnit;
		}

		/**
		 * Sets unit model.
		 *
		 * @param unit the unit
		 */
		public void setBillingFrequencyUnit(final String unit)
		{
			this.billingFrequencyUnit = unit;
		}

		/**
		 * Gets actual billed price.
		 *
		 * @return the actual billed price
		 */
		public Double getActualBilledPrice()
		{
			return actualBilledPrice;
		}

		/**
		 * Sets actual billed price.
		 *
		 * @param actualBilledPrice the actual billed price
		 */
		public void setActualBilledPrice(final Double actualBilledPrice)
		{
			this.actualBilledPrice = actualBilledPrice;
		}

		/**
		 * Gets actual billed unit.
		 *
		 * @return the actual billed unit
		 */
		public String getActualBilledUnit()
		{
			return actualBilledUnit;
		}

		/**
		 * Sets actual billed unit.
		 *
		 * @param actualBilledUnit the actual billed unit
		 */
		public void setActualBilledUnit(final String actualBilledUnit)
		{
			this.actualBilledUnit = actualBilledUnit;
		}

		/**
		 * Gets price.
		 *
		 * @return the price
		 */
		public Double getPrice()
		{
			return price;
		}

		/**
		 * Sets price.
		 *
		 * @param price the price
		 */
		public void setPrice(final Double price)
		{
			this.price = price;
		}

		/**
		 * Gets currency model.
		 *
		 * @return the currency model
		 */
		public CurrencyModel getCurrencyModel()
		{
			return currencyModel;
		}

		/**
		 * Sets currency model.
		 *
		 * @param currencyModel the currency model
		 */
		public void setCurrencyModel(final CurrencyModel currencyModel)
		{
			this.currencyModel = currencyModel;
		}
	}
}
