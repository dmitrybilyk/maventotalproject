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


import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductPriceNotDefinedException;
import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.PriceRowCreator;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Populator for single product price attribute.
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
public class PriceRowSingleProductReversePopulator implements Populator<SingleProductDTO, ProductModel>
{
	@Resource
	private ReferenceDataProviderService referenceDataProviderService;

	@Resource
	private PriceRowCreator priceRowCreator;

	@Override
	public void populate(final SingleProductDTO dto, final ProductModel model) throws ConversionException, ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");

		final CatalogVersionModel onlineCatalogVersionModel = referenceDataProviderService.getOnlineCatalogVersionModel();
		try
		{
			PriceRowModel priceRow = priceRowCreator.getOrCreatePriceRow(onlineCatalogVersionModel, dto.getPrice());
			priceRow.setProduct(model);
			model.setEurope1Prices(Arrays.asList(priceRow));
		}
		catch (ProductPriceNotDefinedException e)
		{
			throw new ProductImportException("Price is not defined", e);
		}
	}

}
