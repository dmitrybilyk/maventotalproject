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
package com.cgi.pacoshop.core.service.impl;


import de.hybris.platform.servicelayer.model.ModelService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cgi.pacoshop.core.daos.ProductTypeDAO;
import com.cgi.pacoshop.core.enums.ProductGroup;
import com.cgi.pacoshop.core.exceptions.ProductTypeException;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.refdata.ProductTypeKey;
import com.cgi.pacoshop.core.service.ProductTypeService;


/**
 * Realization of ProductTypeService
 * 
 * @module pacoshopcore
 * @version 1.0v Jan 03, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/
 * 
 * 
 */
public class ProductTypeServiceImpl implements ProductTypeService
{
	@Resource
	private ModelService modelService;

	@Resource
	private ProductTypeDAO productTypeDAO;

	@Override
	public ProductTypeModel findProductType(final String productClass, final String productGroup, final String productCluster)
			throws ProductTypeException
	{
		String actualProductGroup = null;

		if (productGroup != null)
		{
			actualProductGroup = (productGroup.equals(ProductGroup.DOWNLOAD.getCode())
					|| productGroup.equals(ProductGroup.NEWSLETTER.getCode()) || productGroup.equals(ProductGroup.ONLINEARTIKEL
					.getCode())) ? productGroup : null;
		}

		return productTypeDAO.findProductType(productClass, actualProductGroup, productCluster);
	}

	@Override
	public Map<ProductTypeKey, ProductTypeModel> findAndMapAllProductTypes()
	{
		final List<ProductTypeModel> productTypeModelList = productTypeDAO.findAllProductTypes();

		final Map<ProductTypeKey, ProductTypeModel> result = new HashMap<ProductTypeKey, ProductTypeModel>();

		for (final ProductTypeModel productTypeModel : productTypeModelList)
		{
			modelService.attach(productTypeModel);
			result.put(new ProductTypeKey(productTypeModel.getProductGroup().toString(), productTypeModel.getProductClass()
					.toString(), productTypeModel.getProductCluster().toString()), productTypeModel);
			modelService.detach(productTypeModel);
		}

		return result;
	}
}
