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
package com.cgi.pacoshop.mock.servicebus.model;

import java.util.List;

/**
 * Entity for single productList
 *
 * @module MockServiceBus
 * @version 1.0v Dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright	2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ResponseSingleProduct
{
	private List<SingleProduct> productList;

	/**
	 * Default constructor.
	 */
	public ResponseSingleProduct()
	{
		super();
	}

	/**
	 * Setter for productList.
	 * @param productListParam productList
	 */
	public void setProductList(final List<SingleProduct> productListParam)
	{
		this.productList = productListParam;
	}

	/**
	 * Getter for productList.
	 * @return productList
	 */
	public List<SingleProduct> getProductList()
	{
		return productList;
	}
}
