/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.model;


import java.io.Serializable;
import java.util.Collection;

/**
 * Entity for wrapping json input from ServiceBus
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 16, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class ResponseSingleProduct implements Serializable
{
	private Collection<SingleProductDTO> productList;

	/**
	 * Product list getter.
	 * @return Product list.
	 */
	public Collection<SingleProductDTO> getProductList()
	{
		return productList;
	}

	/**
	 * Product List setter.
	 * @param productListParam - new products list.
	 */
	public void setProductList(final Collection<SingleProductDTO> productListParam)
	{
		this.productList = productListParam;
	}
}
