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
package com.cgi.pacoshop.core.jalo;

import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.util.localization.Localization;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * Restricts component for a collection of product types. NOT Auto-generated cause of custom dynamic description.
 *
 * @module core
 * @version 1.0v Jun 13, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductTypesRestriction extends GeneratedProductTypesRestriction
{
	private static final String DESCRIPTION = "type.ProductTypesRestriction.description.text";

	@Deprecated
	@Override
	public String getDescription(final SessionContext ctx)
	{
		final Set<ProductType> productTypes = getProductTypes(ctx);

		final StringBuilder result = new StringBuilder();
		if (productTypes != null && !productTypes.isEmpty())
		{
			final String localizedString = Localization.getLocalizedString(DESCRIPTION);
			result.append(localizedString);

			for (ProductType productType : productTypes)
			{
				result.append(" ").append(productType.getCode());
			}
		}
		return result.toString();
	}
	
}
