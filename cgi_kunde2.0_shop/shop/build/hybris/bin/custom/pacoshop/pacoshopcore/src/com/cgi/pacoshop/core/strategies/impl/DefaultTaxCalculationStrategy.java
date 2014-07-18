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
package com.cgi.pacoshop.core.strategies.impl;


import com.cgi.pacoshop.core.strategies.TaxCalculationStrategy;
import de.hybris.platform.core.CoreAlgorithms;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import de.hybris.platform.util.PriceValue;

/**
 * Tax calculation strategy
 *
 * @module shop
 * @version 1.0v May 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultTaxCalculationStrategy implements TaxCalculationStrategy
{
	private static final int    ZERO        = 0;
	private static final int    ROUND_DIGIT = 2;
	private static final int    PERCENT     = 100;

	@Override
	public double calculateTaxEachIncluded(final double cost, final double vatRate)
	{
		validateParameterNotNull(cost, "Parameter 'cost' must not be null");
		validateParameterNotNull(vatRate, "Parameter 'cost' must not be null");

		double result = ZERO;
		if (cost != ZERO)
		{
			final double taxValue = cost - (cost * PERCENT) / (PERCENT + vatRate);
			result = CoreAlgorithms.round(taxValue, Math.max(ROUND_DIGIT, ZERO));
		}
		return result;
	}
}
