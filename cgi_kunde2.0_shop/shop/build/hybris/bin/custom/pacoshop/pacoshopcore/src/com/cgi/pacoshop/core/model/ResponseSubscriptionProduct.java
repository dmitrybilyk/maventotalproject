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


import java.util.Collection;

/**
 *  Entity for wrapping json input from ServiceBus for SubscriptionProduct.
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 20, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class ResponseSubscriptionProduct
{
	private Collection<SubscriptionProductDTO> subscriptionProductList;

	/**
	 * Getter.
	 * @return Subscription product list
	 */
	public Collection<SubscriptionProductDTO> getSubscriptionProductList()
	{
		return subscriptionProductList;
	}

	/**
	 * Setter.
	 * @param subscriptionProductList Subscription product list
	 */
	public void setSubscriptionProductList(final Collection<SubscriptionProductDTO> subscriptionProductList)
	{
		this.subscriptionProductList = subscriptionProductList;
	}
}
