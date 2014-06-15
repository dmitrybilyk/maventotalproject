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
package com.cgi.pacoshop.mock.portal.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Entity for Order type.
 *
 *
 * @module portal-com.cgi.pacoshop.mock
 * @version 1.0v Feb 04, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@XmlRootElement(name = "order")

public class Order
{

	private String cartId;

	private Double shippingCost;

	private ArrayList<Product> cartentries;

	private ArrayList<String> paymentmethods;

	/**
	 * Default constructor.
	 */
	public Order()
	{
	}

	/**
	 * .
	 * @return id.
	 */
	@XmlElement(name = "cartid")
	public String getCartId()
	{
		return cartId;
	}

	/**
	 * as.
	 * @param cartId id.
	 */
	public void setCartId(final String cartId)
	{
		this.cartId = cartId;
	}

	/**
	 * .
	 * @return s.
 	 */
	@XmlElement(name = "shippingcost")
	public Double getShippingCost()
	{
		return shippingCost;
	}

	/**
	 * .
	 * @param shippingCost lol.
	 */
	public void setShippingCost(final Double shippingCost)
	{
		this.shippingCost = shippingCost;
	}

	/**
	 * .
	 * @return common.
	 */
	@XmlElementWrapper(name = "cartentries")
	@XmlElement(name = "product")
	public ArrayList<Product> getCartentries()
	{
		return cartentries;
	}

	/**
	 * .
	 * @param cartentries a.
 	 */
	public void setCartentries(final ArrayList<Product> cartentries)
	{
		this.cartentries = cartentries;
	}

	/**
	 * .
	 * @return as.
	 */
	@XmlElementWrapper(name = "paymentmethods")
	@XmlElement(name = "paymentmethod")
	public ArrayList<String> getPaymentmethod()
	{
		return paymentmethods;
	}

	/**
	 * .
	 * @param paymentmethods a.
	 */
	public void setPaymentmethod(final ArrayList<String> paymentmethods)
	{
		this.paymentmethods = paymentmethods;
	}
}
