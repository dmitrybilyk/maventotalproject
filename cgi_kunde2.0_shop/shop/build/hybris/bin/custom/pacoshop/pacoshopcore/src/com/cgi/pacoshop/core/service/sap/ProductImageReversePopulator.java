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


import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.ProductTypeService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.KeyGenerationUtils;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.media.MediaContainerService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * Populator for the galleryImages product attribute.
 *
 * @module build
 * @version 1.0v Feb 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductImageReversePopulator implements Populator<SingleProductDTO, ProductModel>
{
	@Resource
	private ModelService modelService;

	@Resource
	private MediaContainerService mediaContainerService;

	@Resource
	private MediaService mediaService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private ReferenceDataProviderService referenceDataProviderService;

	@Override
	public void populate(final SingleProductDTO dto, final ProductModel model) throws ConversionException, ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");

		final CatalogVersionModel onlineCatalogVersionModel = referenceDataProviderService.getOnlineCatalogVersionModel();

		model.setGalleryImages(getMediaContainerModels(dto, onlineCatalogVersionModel));
	}

	/**
	 * Creates the MediaContainterModel.
	 *
	 * @param dto SingleProductDTO or its subclass
	 * @param catalogVersion Product catalog version
	 * @return List of MediaContainerModel objects
	 * @throws com.cgi.pacoshop.core.service.impl.ProductImportException If MediaContainer cannot be created
	 */
	private List<MediaContainerModel> getMediaContainerModels(
			  final SingleProductDTO dto, final CatalogVersionModel catalogVersion)
			  throws ProductImportException
	{
		Assert.notNull(dto);
		Assert.notNull(catalogVersion);

		MediaContainerModel mediaContainerModel;
		MediaModel mediaModel;

		String mcmKey = KeyGenerationUtils.getMediaKeyForProduct(
				  dto.getOfferOrigin(), dto.getOfferId(), shopConfigurationService.getMediaContainerModelQualifier());
		String mediaKey = KeyGenerationUtils.getMediaKeyForProduct(
				  dto.getOfferOrigin(), dto.getOfferId(), shopConfigurationService.getMediaContainerProductImage());

		try
		{
			mediaContainerModel = mediaContainerService.getMediaContainerForQualifier(mcmKey);
			mediaModel = mediaService.getMedia(mediaKey);

			if (!StringUtils.isEmpty(dto.getOfferPicture()))
			{
				mediaModel.setURL(dto.getOfferPicture());
				if (mediaModel.getMediaContainer() == null)
				{
					mediaModel.setMediaContainer(mediaContainerModel);
				}

				try
				{
					modelService.save(mediaModel);
				}
				catch (ModelSavingException e)
				{
					String msg = String.format("Cannot save the MediaModel object identified by offerOrigin=%s, externalOfferId=%s.",
														dto.getOfferOrigin(), dto.getOfferId());
					throw new ProductImportException(msg, e);
				}
			}
			else
			{
				mediaContainerService.removeMediaFromContainer(mediaContainerModel, Arrays.asList(mediaModel));
			}
		}
		catch (UnknownIdentifierException e)
		{
			mediaModel = modelService.create(MediaModel.class);
			mediaModel.setURL(dto.getOfferPicture());
			mediaModel.setCatalogVersion(catalogVersion);
			mediaModel.setCode(mediaKey);

			mediaContainerModel = modelService.create(MediaContainerModel.class);
			mediaContainerModel.setMedias(Arrays.asList(mediaModel));
			mediaContainerModel.setCatalogVersion(catalogVersion);
			mediaContainerModel.setQualifier(mcmKey);

			// Is it required to save the mediaModel and mediaContainerModel objects here?
		}

		return Arrays.asList(mediaContainerModel);
	}
}
