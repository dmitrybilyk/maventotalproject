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
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.service.BillingFrequencyService;
import com.cgi.pacoshop.core.service.BillingPlanService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.subscription.SubscriptionType;
import com.cgi.pacoshop.core.util.sap.subscription.SubscriptionTypeQualifier;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.enums.TermOfServiceFrequency;
import de.hybris.platform.subscriptionservices.enums.TermOfServiceRenewal;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;
import de.hybris.platform.subscriptionservices.model.BillingPlanModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionTermModel;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Populator for subscription product terms field.
 *
 * @module build
 * @version 1.0v Feb 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SubscriptionTermsReversePopulator implements Populator<SubscriptionProductDTO, SubscriptionProductModel>
{
	public static final String NORMAL_ABO_SUFFIX              = "-Normal Abo";
	public static final String SAMPLE_ABO_SUFFIX              = "-Sample Abo";
	public static final String COMPLEX_ABO_SAMPLE_SUFFIX      = "-Complex Abo-Sample";
	public static final String COMPLEX_ABO_CONSEQUENCE_SUFFIX = "-Complex Abo-Consequence";
	public static final String NORMAL_FLAT_SUBSCRIPTION       = "Normal Flat Subscription";
	public static final String SAMPLE_FLAT_SUBSCRIPTION       = "Sample Flat Subscription";
	public static final String DISTINGUISHED_SAMPLE_PERIOD    = "Distinguished Sample Period";
	public static final String CONSECUTIVE_PERIOD             = "Consecutive Period";
	public static final String BILLINGPLAN                    = "BillingPlan";
	@Resource
	private ModelService              modelService;
	@Resource
	private ShopConfigurationService  shopConfigurationService;
	@Resource
	private SubscriptionTypeQualifier subscriptionTypeQualifier;
	@Resource
	private BillingPlanService        billingPlanService;
	@Resource
	private BillingFrequencyService   billingFrequencyService;

	@Override
	public void populate(final SubscriptionProductDTO dto, final SubscriptionProductModel model) throws ConversionException
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(model, "Parameter SubscriptionProductModel cannot be null.");

		final SubscriptionType subscriptionType = subscriptionTypeQualifier.determineSubscriptionType(dto);
		final String productCode = model.getCode();

		/* Remove old Subscription Terms */
		if (model.getSubscriptionTerm() != null)
		{
			modelService.remove(model.getSubscriptionTerm());
		}
		if (model.getConsecutiveSubscriptionTerm() != null)
		{
			modelService.remove(model.getConsecutiveSubscriptionTerm());
		}

		SubscriptionTermModel subscriptionTermModel = null;
		SubscriptionTermModel consecutiveSubscriptionTermModel = null;

		if (subscriptionType == null)
		{
			String msg = String.format("Cannot import product In case bookedPriceConsequence AND bookedPriceSample are empty "
														  + "offerOrigin=%s, externalOfferId=%s.", dto.getOfferOrigin(), dto.getOfferId());
			throw new ProductImportException(msg);
		}

		BillingPlanModel billingPlanModel = billingPlanService.getOrCreateBillingPlan(BILLINGPLAN + "-" + productCode);

		if (subscriptionType == SubscriptionType.NORMAL)
		{
			subscriptionTermModel = modelService.create(SubscriptionTermModel.class);
			subscriptionTermModel.setId(productCode + NORMAL_ABO_SUFFIX);
			subscriptionTermModel.setName(NORMAL_FLAT_SUBSCRIPTION);
			TermOfServiceRenewal consequenceTermRenewal = getTermOfServiceRenewal(dto.isTermOfServiceRenewalConsequence());
			subscriptionTermModel.setTermOfServiceRenewal(consequenceTermRenewal);
			subscriptionTermModel.setTermOfServiceFrequency(TermOfServiceFrequency.valueOf(
					  shopConfigurationService.getSubscriptionTermOfServiceFrequency(dto.getUnit().toString().toLowerCase())));
			subscriptionTermModel.setTermOfServiceNumber(dto.getFrequencyConsequence());

			billingPlanModel.setBillingFrequency(getStandartBillingFrequency(dto, model.getUnit()));
		}
		if (subscriptionType == SubscriptionType.SNOR)
		{
			subscriptionTermModel = modelService.create(SubscriptionTermModel.class);
			subscriptionTermModel.setId(productCode + SAMPLE_ABO_SUFFIX);
			subscriptionTermModel.setName(SAMPLE_FLAT_SUBSCRIPTION);
			TermOfServiceRenewal consequenceTermRenewal = getTermOfServiceRenewal(dto.isTermOfServiceRenewalSample());
			subscriptionTermModel.setTermOfServiceRenewal(consequenceTermRenewal);
			subscriptionTermModel.setTermOfServiceFrequency(TermOfServiceFrequency.valueOf(
					  shopConfigurationService.getSubscriptionTermOfServiceFrequency(dto.getUnit().toString().toLowerCase())));
			subscriptionTermModel.setTermOfServiceNumber(dto.getFrequencySample());

			billingPlanModel.setBillingFrequency(getSampleBillingFrequency(dto, model.getUnit()));
		}
		if (subscriptionType == SubscriptionType.SWR)
		{
			subscriptionTermModel = modelService.create(SubscriptionTermModel.class);
			subscriptionTermModel.setId(productCode + COMPLEX_ABO_SAMPLE_SUFFIX);
			subscriptionTermModel.setName(DISTINGUISHED_SAMPLE_PERIOD);
			TermOfServiceRenewal termRenewal = getTermOfServiceRenewal(dto.isTermOfServiceRenewalSample());
			subscriptionTermModel.setTermOfServiceRenewal(termRenewal);
			subscriptionTermModel.setTermOfServiceFrequency(TermOfServiceFrequency.valueOf(
					  shopConfigurationService.getSubscriptionTermOfServiceFrequency(dto.getUnit().toString().toLowerCase())));
			subscriptionTermModel.setTermOfServiceNumber(dto.getFrequencySample());

			consecutiveSubscriptionTermModel = modelService.create(SubscriptionTermModel.class);
			consecutiveSubscriptionTermModel.setId(productCode + COMPLEX_ABO_CONSEQUENCE_SUFFIX);
			consecutiveSubscriptionTermModel.setName(CONSECUTIVE_PERIOD);
			TermOfServiceRenewal consequenceTermRenewal = getTermOfServiceRenewal(dto.isTermOfServiceRenewalConsequence());
			consecutiveSubscriptionTermModel.setTermOfServiceRenewal(consequenceTermRenewal);
			consecutiveSubscriptionTermModel.setTermOfServiceFrequency(TermOfServiceFrequency.valueOf(
					  shopConfigurationService.getSubscriptionTermOfServiceFrequency(dto.getUnit().toString().toLowerCase())));
			consecutiveSubscriptionTermModel.setTermOfServiceNumber(dto.getFrequencyConsequence());

			billingPlanModel.setBillingFrequency(getSampleBillingFrequency(dto, model.getUnit()));
			billingPlanModel.setConsecutiveBillingFrequency(getConsecutiveBillingFrequency(dto, model.getUnit()));
		}
		modelService.save(billingPlanModel);
		subscriptionTermModel.setBillingPlan(billingPlanModel);
		model.setSubscriptionTerm(subscriptionTermModel);
		model.setConsecutiveSubscriptionTerm(consecutiveSubscriptionTermModel);
	}

	private BillingFrequencyModel getStandartBillingFrequency(final SubscriptionProductDTO dto, final UnitModel unit)
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(unit, "Parameter UnitModel cannot be null.");

		BillingFrequencyModel standartBillingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(
				  dto.getBookedBillingFrequencyConsequence(),
				  unit,
				  SubscriptionPhase.STANDART);
		return standartBillingFrequencyModel;
	}

	private BillingFrequencyModel getSampleBillingFrequency(final SubscriptionProductDTO dto, final UnitModel unit)
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(unit, "Parameter UnitModel cannot be null.");

		BillingFrequencyModel sampleBillingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(
				  dto.getBookedBillingFrequencySample(),
				  unit,
				  SubscriptionPhase.SAMPLE);
		return sampleBillingFrequencyModel;
	}

	private BillingFrequencyModel getConsecutiveBillingFrequency(final SubscriptionProductDTO dto, final UnitModel unit)
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(unit, "Parameter UnitModel cannot be null.");

		BillingFrequencyModel consecutiveBillingFrequencyModel = billingFrequencyService.getOrCreateBillingFrequency(
				  dto.getBookedBillingFrequencyConsequence(),
				  unit,
				  SubscriptionPhase.CONSECUTIVE);
		return consecutiveBillingFrequencyModel;
	}

	private TermOfServiceRenewal getTermOfServiceRenewal(final boolean termFromDto)
	{
		return termFromDto ? TermOfServiceRenewal.AUTO_RENEWING : TermOfServiceRenewal.NON_RENEWING;
	}
}
