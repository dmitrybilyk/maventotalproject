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


import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import static com.cgi.pacoshop.core.util.LogHelper.info;

import com.google.common.collect.Sets;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeValueModel;
import de.hybris.platform.deliveryzone.model.ZoneModel;
import de.hybris.platform.order.ZoneDeliveryModeService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Populate ProductModel with specific to subscription product fields.
 *
 * @module build
 * @version 1.0v May 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oksana Arkhypova <oksana.arhypova@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubscriptionProductDeliveryCountriesReversePopulator
		  implements Populator<SubscriptionProductDTO, SubscriptionProductModel>
{
	private static final Logger LOG             = Logger.getLogger(SubscriptionProductDeliveryCountriesReversePopulator.class);
	private static final double MINIMUM         = 1d;
	private static final double COST            = 99.99d;
	private static final String EURO            = "EUR";
	private static final String DELIVERY_PREFIX = "delivery";
	private static final String ZONE_PREFIX     = "zone";
	@Resource
	private ZoneDeliveryModeService zoneDeliveryModeService;
	@Resource
	private ModelService            modelService;
	@Resource
	private CommonI18NService       commonI18NService;


	@Override
	public void populate(final SubscriptionProductDTO dto, final SubscriptionProductModel model) throws ConversionException,
																																		 ProductImportException
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");
		info(LOG, "Setting delivery countries product %s", model.getCode());
		if (CollectionUtils.isEmpty(dto.getValidDeliveryCountries()))
		{
			error(LOG, "Subscription product import: No countries defined for product %s.", model.getCode());
		}

		if (model.getDeliveryModes() != null)
		{
			modelService.removeAll(model.getDeliveryModes());
		}

		if (CollectionUtils.isNotEmpty(dto.getValidDeliveryCountries()))
		{
			model.setDeliveryModes(Sets.newHashSet(createDeliveryModeModel(dto, model.getCode())));
		}

		modelService.save(model);
	}

	private DeliveryModeModel createDeliveryModeModel(final SubscriptionProductDTO dto, final String productCode)
	{
		Set<CountryModel> countriesModels = getCountriesModelsFromDTO(dto);

		ZoneModel zoneModel = createZoneModel(countriesModels, getZoneCode(productCode));

		return createDeliveryModel(zoneModel);
	}

	private Set<CountryModel> getCountriesModelsFromDTO(final SubscriptionProductDTO dto)
	{
		final Set<String> validDeliveryCountries = new HashSet<>();
		//delivery countries --> toUppercase
		CollectionUtils.collect(dto.getValidDeliveryCountries(), new Transformer()
		{
			@Override
			public Object transform(final Object obj)
			{
				return obj.toString().toUpperCase();
			}
		}, validDeliveryCountries);
		//create country model from iso code
		Set<CountryModel> countries = new HashSet<>();
		for (String validDeliveryCountryIsoCode : validDeliveryCountries)
		{
			CountryModel country;
			try
			{
				country = commonI18NService.getCountry(validDeliveryCountryIsoCode);
			}
			catch (UnknownIdentifierException e)
			{
				debug(LOG, e.getMessage());
				continue;
			}
			if (country == null || StringUtils.isEmpty(country.getIsocode()))
			{
				debug(LOG, "Delivery country [%s] is NOT VALID", validDeliveryCountryIsoCode);
				continue;
			}
			countries.add(country);
		}
		return countries;
	}

	private ZoneModel createZoneModel(final Set<CountryModel> countries, final String zoneCode)
	{
		ZoneModel returnZone;
		try
		{
			returnZone = zoneDeliveryModeService.getZoneForCode(zoneCode);
			debug(LOG, "Existing zone %s found, removing it", zoneCode);
			modelService.remove(returnZone);
		}
		catch (UnknownIdentifierException e)
		{
			debug(LOG, "ZoneModel not found creating new one" + e.getMessage());
		}
		returnZone = modelService.create(ZoneModel.class);
		returnZone.setCode(zoneCode);
		returnZone.setCountries(countries);
		modelService.save(returnZone);
		return returnZone;
	}

	private String getZoneCode(final String productCode)
	{
		StringBuilder zoneCode = new StringBuilder(ZONE_PREFIX);
		zoneCode.append("_").append(productCode);
		return zoneCode.toString();
	}

	private ZoneDeliveryModeModel createDeliveryModel(final ZoneModel zoneModel)
	{
		CurrencyModel currency = commonI18NService.getCurrency(EURO);

		//***********ZoneDeliveryModeModel******************************
		String deliveryModeCode = String.format("%s_%s", DELIVERY_PREFIX, zoneModel.getCode());
		ZoneDeliveryModeModel newDeliveryModeModel;
		try
		{
			newDeliveryModeModel = (ZoneDeliveryModeModel) zoneDeliveryModeService.getDeliveryModeForCode(deliveryModeCode);
		}
		catch (UnknownIdentifierException e)
		{
			debug(LOG, e.getMessage());
			newDeliveryModeModel = modelService.create(ZoneDeliveryModeModel.class);
			newDeliveryModeModel.setCode(deliveryModeCode);
			newDeliveryModeModel.setActive(true);
			newDeliveryModeModel.setNet(true);
		}
		//***********zoneDeliveryModeValueModel*************************
		ZoneDeliveryModeValueModel zoneDeliveryModeValueModel;
		try
		{
			modelService.save(newDeliveryModeModel);
			zoneDeliveryModeValueModel =
					  zoneDeliveryModeService.getDeliveryValue(zoneModel, currency, MINIMUM, newDeliveryModeModel);
		}
		catch (UnknownIdentifierException e)
		{
			debug(LOG, e.getMessage());
			zoneDeliveryModeValueModel = modelService.create(ZoneDeliveryModeValueModel.class);
			zoneDeliveryModeValueModel.setValue(COST);
			zoneDeliveryModeValueModel.setMinimum(MINIMUM);
			zoneDeliveryModeValueModel.setCurrency(currency);
			zoneDeliveryModeValueModel.setDeliveryMode(newDeliveryModeModel);
		}
		zoneDeliveryModeValueModel.setZone(zoneModel);
		//***********zoneDeliveryModeValueModelValues*******************
		final Set<ZoneDeliveryModeValueModel> zoneDeliveryModeValueModelValues = new HashSet<>();
		zoneDeliveryModeValueModelValues.add(zoneDeliveryModeValueModel);
		newDeliveryModeModel.setValues(zoneDeliveryModeValueModelValues);

		modelService.save(newDeliveryModeModel);
		modelService.save(zoneDeliveryModeValueModel);
		return newDeliveryModeModel;
	}

}
