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
 * Wrapper entity for Collection Subscription Products
 *
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class ResponseSubscriptionProduct
{
	private List<SubscriptionProduct> subscriptionProductList;

	/**
	 * Default constructor.
	 */
	public ResponseSubscriptionProduct()
	{
		super();
	}

	/**
	 * Setter for subscriptionProductList.
	 * @param subscriptionProductListParam subscriptionProductList
	 */
	public void setSubscriptionProductList(final List<SubscriptionProduct> subscriptionProductListParam)
	{
		this.subscriptionProductList = subscriptionProductListParam;
	}

	/**
	 * Getter for subscriptionProductList.
	 * @return subscriptionProductList
	 */
	public List<SubscriptionProduct> getSubscriptionProductList()
	{
		return subscriptionProductList;
	}
}
