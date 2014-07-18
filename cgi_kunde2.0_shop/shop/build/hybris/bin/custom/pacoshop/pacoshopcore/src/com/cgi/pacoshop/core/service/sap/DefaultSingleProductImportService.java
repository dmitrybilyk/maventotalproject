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
import com.cgi.pacoshop.core.service.SingleProductImportService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Iterates over populators to fill productModel for single product from ProductDTO.
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
public class DefaultSingleProductImportService implements SingleProductImportService
{
	private List<Populator> populators;

	@Resource
	private ModelService modelService;

	/**
	 * Iterates through reverse populators and creates Product Model.
	 * @param dto SingleProductDTO or its subclass
	 * @param model ProductModel object
	 * @throws ProductImportException if some errors occurs during import
	 */
	public void createProduct(final SingleProductDTO dto, final ProductModel model) throws ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");

		for (Populator populator : getPopulators())
		{
			populator.populate(dto, model);
		}
		modelService.save(model);
	}

	/**
	 * Populators setter.
	 * @param populators populators for single product.
	 */
	public void setPopulators(final List<Populator> populators)
	{
		this.populators = populators;
	}

	/**
	 * Popublators getter.
	 * @return the list of populators.
	 */
	public List<Populator> getPopulators()
	{
		return this.populators;
	}
}
