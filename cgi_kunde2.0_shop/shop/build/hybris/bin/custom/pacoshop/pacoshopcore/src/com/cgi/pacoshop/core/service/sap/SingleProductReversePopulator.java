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


import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.core.model.ProductUnit;
import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.ProductTypeQualifier;
import com.cgi.pacoshop.core.util.sap.UnitCreator;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Populate ProductModel with specific to single product fields.
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
public class SingleProductReversePopulator implements Populator<SingleProductDTO, ProductModel>
{
	@Resource
	private UnitCreator unitCreator;

	@Resource
	private ProductTypeQualifier productTypeQualifier;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Override
	public void populate(final SingleProductDTO dto, final ProductModel model) throws ConversionException,
																												 ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");

		model.setProductType(productTypeQualifier.getProductType(dto, getImportInterfaceSingleProductCluster()));

		model.setPrepayOnly(dto.isPrepayOnly());
		model.setUnit(unitCreator.getOrCreateUnit(ProductUnit.STUECK));
		model.setImportSource(ProductImportSource.SERVICE_BUS_SINGLE_PRODUCTS);
	}

	/**
	 * Returns the name of the product cluster that
	 * corresponds to the product import interface.
	 *
	 * @return Product cluster name
	 */
	private String getImportInterfaceSingleProductCluster()
	{
		return shopConfigurationService.getSingleProductCluster();
	}
}
