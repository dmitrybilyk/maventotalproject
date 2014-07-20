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
package com.ownmocks.servicebus.model;

import org.apache.cxf.common.util.StringUtils;

/**
 * Entity for Price.
 *
 * @module MockServiceBus
 * @version 1.0v Jan 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.symmetrics.de/display/KS/Product+import+interface
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class Price
{
	private static final String SPLIT_CONSTANT = " ";
	private String currency;
	private Double amount;

	/**
	 * Constructor.
	 * @param currencyParam currency
	 * @param amountParam amount
	 */
	public Price(final String currencyParam, final Double amountParam)
	{
		this.currency = currencyParam;
		this.amount = amountParam;
	}

	/**
	 * Getter for currency.
	 * @return currency
	 */
	public String getCurrency()
	{
		return currency;
	}

	/**
	 * Getter for amount.
	 * @return amount
	 */
	public Double getAmount()
	{
		return amount;
	}

	/**
	 * Parse price.
	 * @param price price as string
	 * @return price as object
	 */
	public static Price parsePrice(final String price)
	{
		if (StringUtils.isEmpty(price))
		{
			return null;
		}
		else
		{
			String[] data = price.split(SPLIT_CONSTANT);
			return new Price(data[1], Double.parseDouble(data[0]));
		}
	}
}
