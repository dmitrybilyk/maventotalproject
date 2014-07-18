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

import com.cgi.pacoshop.core.exceptions.ProductTypeException;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.service.ProductTypeService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Used to determine product Type.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 19, 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class ProductTypeQualifier
{
	@Resource
	private ProductTypeService productTypeService;

	/**
	 * Finds the ProductTypeModel by combination of class, group and cluster.
	 *
	 * @param dto            product data transfer object.
	 * @param productCluster Product cluster
	 * @return ProductTypeModel
	 * @throws ProductImportException if Product type cannot be found
	 */
	public ProductTypeModel getProductType(final SingleProductDTO dto, final String productCluster)
			throws ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");
		Assert.notNull(productCluster);

		ProductTypeModel productTypeModel = null;
		try
		{
			productTypeModel = productTypeService.findProductType(dto.getProductClass(), dto.getProductGroup(), productCluster);
		}
		catch (ProductTypeException e)
		{
			String msg = String.format(
					"Cannot find ProductType that corresponds to productClass = %s, productGroup = %s, productCluster = %s.",
					dto.getProductClass(), dto.getProductGroup(), productCluster);
			throw new ProductImportException(msg, e);
		}

		return productTypeModel;
	}
}
