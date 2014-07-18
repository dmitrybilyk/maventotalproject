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
package com.cgi.pacoshop.core.util.sap.subscription;


import com.cgi.pacoshop.core.model.Price;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.service.impl.ProductImportException;

/**
 * Class that helps to determine subscription type of periodic product.
 *
 * @module build
 * @version 1.0v Feb 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SubscriptionTypeQualifier
{
	/**
	 * Determine subscription type of product.
	 * @param dto Data object for product
	 * @return type of subscription.
	 */
	public SubscriptionType determineSubscriptionType(final SubscriptionProductDTO dto)
	{
		final Price bookedPriceSample = dto.getBookedPriceSample(),
						bookedPriceConsequence = dto.getBookedPriceConsequence();
		if (isNull(bookedPriceSample))
		{
			if (isNull(bookedPriceConsequence))
			{
				throw new ProductImportException("BookedPriceSample AND BookedPriceConsequence can't both be null");
			}
			else
			{
				return SubscriptionType.NORMAL;
			}
		}
		else
		{
			if (isNull(bookedPriceConsequence))
			{
				return SubscriptionType.SNOR;
			}
			else
			{
				checkPricesCombinations(bookedPriceSample, bookedPriceConsequence);
				return SubscriptionType.SWR;
			}
		}
	}

	private void checkPricesCombinations(final Price bookedPriceSample, final Price bookedPriceConsequence)
	{
		if (bookedPriceSample.getAmount() == 0 && bookedPriceConsequence.getAmount() > 0)
		{
			throw new ProductImportException("BookedPriceSample AND BookedPriceConsequence are in wrong combination values. "
														+ "SimplePrice = [ %s ], ConsequencePrice = [ %s ]",
												 bookedPriceSample.getAmount(), bookedPriceConsequence.getAmount());
		}
	}

	private boolean isNull(final Price price)
	{
		return price == null;
	}
}
