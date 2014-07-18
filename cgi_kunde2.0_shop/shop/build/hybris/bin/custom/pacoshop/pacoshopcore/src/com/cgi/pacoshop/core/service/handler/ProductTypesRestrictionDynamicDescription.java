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
package com.cgi.pacoshop.core.service.handler;


import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.model.ProductTypesRestrictionModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.localization.Localization;
import java.util.Set;

/**
 * Dynamic handler for CampaignIdRestriction description attribute.
 *
 * @module core
 * @version 1.0v May 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductTypesRestrictionDynamicDescription implements DynamicAttributeHandler<String, ProductTypesRestrictionModel>
{
	private static final String DESCRIPTION = "type.ProductTypesRestriction.description.text";

	@Override
	public String get(final ProductTypesRestrictionModel model)
	{
		final Set<ProductTypeModel> productTypes = model.getProductTypes();

		final StringBuilder result = new StringBuilder();
		if (productTypes != null && !productTypes.isEmpty())
		{
			final String localizedString = Localization.getLocalizedString(DESCRIPTION);
			result.append(localizedString);

			for (ProductTypeModel productType : productTypes)
			{
				result.append(" ").append(productType.getCode());
			}
		}
		return result.toString();
	}

	@Override
	public void set(final ProductTypesRestrictionModel model, final String value)
	{
		throw new UnsupportedOperationException();
	}
}
