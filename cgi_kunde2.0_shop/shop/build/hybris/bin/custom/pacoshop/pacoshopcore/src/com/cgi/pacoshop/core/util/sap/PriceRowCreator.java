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
package com.cgi.pacoshop.core.util.sap;


import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductPriceNotDefinedException;
import com.cgi.pacoshop.core.model.Price;
import com.cgi.pacoshop.core.model.ProductUnit;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Class that creates PriceModel and Currency Model for imported products.
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
public class PriceRowCreator
{
	@Resource
	private UnitCreator unitCreator;

	@Resource
	private CommonI18NService commonI18NService;

	@Resource
	private ModelService      modelService;

	/**
	 *	Gets from the Hybris database or creates the PriceRow object.
	 *
	 * @param catalogVersion Cagalog version
	 * @param price Price
	 * @return PriceRowModel Price row model
	 */
	public PriceRowModel getOrCreatePriceRow(final CatalogVersionModel catalogVersion, final Price price)
	{
		Assert.notNull(catalogVersion);
		Assert.notNull(price);

		if (price == null || price.getAmount() == null
				  || price.getAmount().isNaN())
		{
			throw new ProductPriceNotDefinedException("");
		}

		final UnitModel unitModel = unitCreator.getOrCreateUnit(ProductUnit.STUECK);
		PriceRowModel priceRow = modelService.create(PriceRowModel.class);
		priceRow.setUnit(unitModel);
		priceRow.setCatalogVersion(catalogVersion);
		priceRow.setPrice(price.getAmount());
		priceRow.setCurrency(getCurrencyModel(price.getCurrency()));
		priceRow.setNet(false);

		return priceRow;
	}

    /**
     *
     * @param currency the currency
     * @return CurrencyModel
     */
	public CurrencyModel getCurrencyModel(final String currency)
	{
		Assert.notNull(currency);

		CurrencyModel currencyModel;
		try
		{
			currencyModel = commonI18NService.getCurrency(currency);
		}
		catch (final UnknownIdentifierException e)
		{
			String msg = String.format("Currency %s does not exist in Hybris database.", currency);
			throw new ProductImportException(msg, e);
		}

		return currencyModel;
	}
}
