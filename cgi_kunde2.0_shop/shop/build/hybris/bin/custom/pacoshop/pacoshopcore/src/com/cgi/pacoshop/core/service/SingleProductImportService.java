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


import com.cgi.pacoshop.core.model.SingleProductDTO;
import de.hybris.platform.core.model.product.ProductModel;

/**
 * Interface that defines methods to create single product from import interface.
 *
 * @module build
 * @version 1.0v Feb 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface SingleProductImportService
{
	/**
	 * Method that calls from import service to create single product model from data transfer object.
	 * @param dto SingleProductDTO or its subclass.
	 * @param model ProductModel object.
	 */
	void createProduct(final SingleProductDTO dto, final ProductModel model);
}
