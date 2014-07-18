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
package com.cgi.pacoshop.core.daos;


import com.cgi.pacoshop.core.exceptions.ProductTypeException;
import com.cgi.pacoshop.core.model.ProductTypeModel;

import java.util.List;

/**
 * Interface for DAO layer for retrieving ProductType based on input value from DTO. Here goes 2 line.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <joe.doe@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 03, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public interface ProductTypeDAO
{
	/**
	 * This method search product type. Product Types were previously imported into Hybris via Impex
	 *
	 * @param productClass   - product Class
	 * @param productGroup   - product Group
	 * @param productCluster - product Cluster
	 * @return ProductType Entity.
	 * @throws ProductTypeException if no or more than one Product Type found.
	 */
	ProductTypeModel findProductType(String productClass, String productGroup, String productCluster) throws ProductTypeException;

	/**
	 * Find all product types.
	 *
	 * @return the list
	 */
	List<ProductTypeModel> findAllProductTypes();
}
