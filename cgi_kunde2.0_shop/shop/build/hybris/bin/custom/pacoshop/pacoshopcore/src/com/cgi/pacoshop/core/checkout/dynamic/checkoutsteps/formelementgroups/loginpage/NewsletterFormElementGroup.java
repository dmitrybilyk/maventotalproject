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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.loginpage;


import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;

/**
 * FEG for newsletter.
 *
 * @module kunde_new
 * @version 1.0v Jun 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class NewsletterFormElementGroup extends AbstractFormElementGroup
{
	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.NEWSLETTER_FORM_ELEMENT_GROUP);
	}

	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (isNewsletter(productModel))
			{
				return true;
			}
		}
		return false;
	}

	private boolean isNewsletter(final ProductModel productModel)
	{
		final String newsletter = getConfigurationProperty(FormElementGroupConstants.NEWS_LETTER);
		return productModel.getProductType().getCode().equals(newsletter);
	}
}
