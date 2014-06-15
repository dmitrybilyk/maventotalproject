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
package com.cgi.pacoshop.mock.address.model;


/**
 * Class which reproduce response for address validation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 04, 2014
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class AddressResponse
{
	private String delivery;

	/**
	 * Constructor.
	 * @param delivery delivery
	 */
	public AddressResponse(final String delivery)
	{
		this.delivery = delivery;
	}
	/**
	 * Getter for Delivery.
	 * @return delivery
	 */
	public String getDelivery()
	{
		return delivery;
	}

	/**
	 * Setter for delivery.
	 * @param delivery delivery
	 */
	public void setDelivery(final String delivery)
	{
		this.delivery = delivery;
	}
}
