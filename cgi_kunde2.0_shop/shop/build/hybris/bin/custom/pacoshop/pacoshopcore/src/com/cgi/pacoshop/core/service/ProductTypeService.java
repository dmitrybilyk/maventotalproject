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
package com.cgi.pacoshop.core.service;

import com.cgi.pacoshop.core.exceptions.ProductTypeException;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.refdata.ProductTypeKey;

import java.util.Map;

/**
 * Interface for ProductType entity that already has all possible combination in database All possible combination added
 * in database via IMPEX file on the initialize step.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Panov Maksim <joe.doe@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 03, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/
 */
public interface ProductTypeService
{
	/**
	 * Method for establish relatsion between Product and ProductType.
	 *
	 * @param productClass   come in from DTO field
	 * @param productGroup   com in from DTO field
	 * @param productCluster depends on type of product (single or subscription)
	 * @return Product Type.
	 * @throws ProductTypeException in case of error.
	 */
	ProductTypeModel findProductType(String productClass, String productGroup, String productCluster) throws ProductTypeException;

	/**
	 * Find and map all product types.
	 *
	 * @return the map
	 */
	Map<ProductTypeKey, ProductTypeModel> findAndMapAllProductTypes();
}
