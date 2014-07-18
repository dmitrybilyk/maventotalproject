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

/**
 * Base entity for Single product price field
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 16, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public class Price implements Serializable
{

	private String currency;
	private Double amount;

	/**
	 * Gets currency.
	 *
	 * @return the currency
	 */
	public String getCurrency()
	{
		return currency;
	}

	/**
	 * Sets currency.
	 *
	 * @param currency the currency
	 */
	public void setCurrency(final String currency)
	{
		this.currency = currency;
	}

	/**
	 * Gets amount.
	 *
	 * @return the amount
	 */
	public Double getAmount()
	{
		return amount;
	}

	/**
	 * Sets amount.
	 *
	 * @param amount the amount
	 */
	public void setAmount(final Double amount)
	{
		this.amount = amount;
	}

	@Override
	public String toString()
	{
		return "Price{"
				  + "currency='" + currency + '\''
				  + ", amount=" + amount
				  + '}';
	}
}

