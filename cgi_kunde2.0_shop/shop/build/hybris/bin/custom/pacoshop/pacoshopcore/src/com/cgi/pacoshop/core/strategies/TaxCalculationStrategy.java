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
package com.cgi.pacoshop.core.strategies;


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
public interface TaxCalculationStrategy
{
	/**
	 * Calculates tax each included into cost by vat rate.
	 * @param cost value where included tax.
	 * @param vatRate rate for current cost.
	 * @return tax value.
	 */
	double calculateTaxEachIncluded(double cost, double vatRate);

}
