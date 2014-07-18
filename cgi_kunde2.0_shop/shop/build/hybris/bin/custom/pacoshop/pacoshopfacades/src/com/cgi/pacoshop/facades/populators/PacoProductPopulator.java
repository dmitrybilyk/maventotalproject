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
package com.cgi.pacoshop.facades.populators;


import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.util.Assert;


/**
 * Extends default product populator with product Type Population.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 14, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class PacoProductPopulator implements Populator<ProductModel, ProductData>
{
	@Override
	public void populate(final ProductModel source, final ProductData target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		if (source.getProductType() != null)
		{
			target.setProductType(source.getProductType().getCode());
		}
	}
}
