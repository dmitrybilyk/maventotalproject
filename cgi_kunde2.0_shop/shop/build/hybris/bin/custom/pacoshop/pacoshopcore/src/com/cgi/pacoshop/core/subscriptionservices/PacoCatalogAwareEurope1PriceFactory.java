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
package com.cgi.pacoshop.core.subscriptionservices;


import de.hybris.platform.europe1.constants.GeneratedEurope1Constants;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.subscriptionservices.jalo.ExtendedCatalogAwareEurope1PriceFactory;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for getting the price rows
 *
 * @version 1.0v Feb 17, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev.doe@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 *
 *
 */
public class PacoCatalogAwareEurope1PriceFactory extends ExtendedCatalogAwareEurope1PriceFactory
{

	@Override
	public Collection getProductPriceRowsFast(final SessionContext ctx, final Product product, final EnumerationValue productGroup)
	{
		if (product == null)
		{
			throw new JaloInvalidParameterException("cannot find price rows without product ", 0);
		}
		StringBuilder query = new StringBuilder();

		query.append("SELECT {pr:").append(Item.PK).append("} FROM  {").append(GeneratedEurope1Constants.TC.PRICEROW).append(
				  " AS pr }");

		query.append("WHERE ");
		query.append("{pr:").append("productMatchQualifier").append("} IN ( ?product ");
		if (productGroup != null)
		{
			query.append(" , ?pg ");
		}
		query.append(") ");
		Map values = new HashMap();
		values.put("product", Long.valueOf(product.getPK().getLongValue()));
		if (productGroup != null)
		{
			values.put("pg", Long.valueOf(productGroup.getPK().getLongValue()));
		}

		return getSession().getFlexibleSearch()
				  .search(ctx, query.toString(), values, Collections.singletonList(PriceRow.class), true, true, 0, -1).getResult();
	}

}
