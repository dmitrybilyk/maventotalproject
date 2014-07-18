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


import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.CashBonusModel;
import com.cgi.pacoshop.core.model.MilesAndMoreBonusModel;
import com.cgi.pacoshop.core.model.Price;
import com.cgi.pacoshop.core.model.ProductBonusModel;
import com.cgi.pacoshop.core.model.ProductUnit;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.KeyGenerationUtils;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.PriceRowCreator;
import com.cgi.pacoshop.core.util.sap.UnitCreator;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * Populator for subscription product bonuses field.
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
public class BonusesReversePopulator implements Populator<SubscriptionProductDTO, SubscriptionProductModel>
{
	@Resource
	private ModelService modelService;

	@Resource
	private MediaService mediaService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private PriceRowCreator priceRowCreator;

	@Override
	public void populate(final SubscriptionProductDTO dto,
								final SubscriptionProductModel model) throws
																				  ConversionException, ProductImportException
	{
		/* Remove old BonusModels */
		if (model.getBonuses() != null)
		{
			for (BonusModel bonus : model.getBonuses())
			{
				if (bonus instanceof ProductBonusModel)
				{
					// We are not going to remove the price row if it did not exist
					PriceRowModel additionalPaymentPriceRow = ((ProductBonusModel) bonus).getAdditionalPayment();
					if (additionalPaymentPriceRow != null)
					{
						modelService.remove(additionalPaymentPriceRow);
					}
				}
			}
			modelService.removeAll(model.getBonuses());
		}

		/* Populate Bonuses */
		List<BonusModel> bonuses = bonusToModel(dto, model);
		model.setBonuses(bonuses);
		model.setBonusDescription(dto.getBonusDescription());
		model.setBonusImage(getBonusMedia(model.getCatalogVersion(), dto));
	}

	private List<BonusModel> bonusToModel(final SubscriptionProductDTO dto, final ProductModel productModel)
			  throws  ProductImportException
	{
		Assert.notNull(dto);
		Assert.notNull(productModel);

		List<BonusModel> bonuses = new ArrayList<>();

		if (dto.getBonusMiles() != null && dto.getBonusMiles() > 0)
		{
			MilesAndMoreBonusModel milesAndMoreBonusModel = modelService.create(MilesAndMoreBonusModel.class);
			milesAndMoreBonusModel.setMiles(dto.getBonusMiles());
			milesAndMoreBonusModel.setCatalogVersion(productModel.getCatalogVersion());
			bonuses.add(milesAndMoreBonusModel);
		}

		if (dto.getBonusAmount() != null && !dto.getBonusAmount().getAmount().isNaN() && dto.getBonusAmount().getAmount() > 0)
		{
			CashBonusModel cashBonusModel = modelService.create(CashBonusModel.class);
			cashBonusModel.setBonusAmount(dto.getBonusAmount().getAmount());
			cashBonusModel.setCurrency(priceRowCreator.getCurrencyModel(dto.getBonusAmount().getCurrency()));
			cashBonusModel.setCatalogVersion(productModel.getCatalogVersion());
			bonuses.add(cashBonusModel);
		}

		if (dto.getBonusProduct() != null && !dto.getBonusProduct().isEmpty())
		{
			ProductBonusModel productBonusModel = modelService.create(ProductBonusModel.class);

			// Create price row and additional payment ONLY in case if bonusExtraPayment field is not empty
			if (dto.getBonusProductExtraPayment() != null)
			{
				PriceRowModel priceRowModel = priceRowCreator.getOrCreatePriceRow(productModel.getCatalogVersion(),
																										dto.getBonusProductExtraPayment());
				priceRowModel.setOwner(productBonusModel);
				productBonusModel.setAdditionalPayment(priceRowModel);
			}

			productBonusModel.setProductDescription(dto.getBonusProduct());
			productBonusModel.setCatalogVersion(productModel.getCatalogVersion());
			bonuses.add(productBonusModel);
		}
		return bonuses;
	}

	private MediaModel getBonusMedia(final CatalogVersionModel catalogVersion, final SubscriptionProductDTO dto)
			  throws ProductImportException
	{
		Assert.notNull(catalogVersion);

		String mediaKey = KeyGenerationUtils.getMediaKeyForProduct(
				  dto.getOfferOrigin(), dto.getOfferId(), shopConfigurationService.getMediaContainerProductBonusImage());
		MediaModel bonusMedia;

		try
		{
			bonusMedia = mediaService.getMedia(mediaKey);

			if (!StringUtils.isEmpty(dto.getBonusPicture()))
			{
				bonusMedia.setURL(dto.getBonusPicture());

				try
				{
					modelService.save(bonusMedia);
				}
				catch (ModelSavingException e)
				{
					String msg = String.format("Cannot save the MediaModel object for bonusPicture identified by"
																+ "offerOrigin=%s, externalOfferId=%s.", dto.getOfferOrigin(), dto.getOfferId());
					throw new ProductImportException(msg, e);
				}
			}
			else
			{
				return null;
			}
		}
		catch (UnknownIdentifierException e)
		{
			bonusMedia = modelService.create(MediaModel.class);
			bonusMedia.setURL(dto.getBonusPicture());
			bonusMedia.setCatalogVersion(catalogVersion);
			bonusMedia.setCode(mediaKey);
		}
		return bonusMedia;
	}
}
