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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import java.util.Collection;


/**
 * Rest Interface for single offer product
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *	@see "https://wiki.hybris.com/dashboard.action"
 *
 */
public interface RestClient
{
	/**
	 * Method allows us to get Collection of Single Product in JSON format.
	 * @return Collection of SingleProductDTO.class
	 */
	 Collection<SingleProductDTO> getSingleProduct();

	/**
	 * Method for Subscription Product.
	 * @return Collection of Subscription Products.
	 */
	Collection<? extends SingleProductDTO> getSubscriptionProducts();
}
