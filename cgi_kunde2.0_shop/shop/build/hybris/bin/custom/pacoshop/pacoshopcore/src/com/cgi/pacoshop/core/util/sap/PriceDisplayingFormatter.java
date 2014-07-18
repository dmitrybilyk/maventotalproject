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
package com.cgi.pacoshop.core.util.sap;


import java.text.NumberFormat;

/**
 *
 * @module hybris
 * @version 1.0v Jun 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oksana Arkhypova <oksana.arkhypova@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public final class PriceDisplayingFormatter
{
	private static final String  DEFAULT_EMPTY_PRICE_VALUE = "";
	private static final Integer NUMBER_OF_DIGITS          = 2;

    private PriceDisplayingFormatter()
    {
    }

	/**
	 * Add space between price and currency for displaying.
	 * @param price price.
	 * @param currency currency.
	 * @return priceWithCurrency
	 */
	public static String formatPriceWithSpace(final Double price, final String currency)
	{
		if (price == null || price == 0)
		{
			return DEFAULT_EMPTY_PRICE_VALUE;
		}
		else
		{
			NumberFormat numberFormat = NumberFormat.getInstance();
			numberFormat.setMinimumFractionDigits(NUMBER_OF_DIGITS);
			StringBuilder priceWithCurrency = new StringBuilder(numberFormat.format(price));
			priceWithCurrency.append(" ");
			priceWithCurrency.append(currency);
			return priceWithCurrency.toString();
		}
	}
}
