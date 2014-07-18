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
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.List;

/**
 * Anonymous formElementGroup - group for displaying 'stay anonymous' form on loginpage.
 *
 * @module core
 * @version 1.0v Feb 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class AnonymousFormElementGroup extends AbstractFormElementGroup
{

	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		return isAnonymousEnabled(cart);
	}

	private boolean isAnonymousEnabled(final CartModel cart)
	{
		final List<AbstractOrderEntryModel> entries = cart.getEntries();
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		boolean isDigitalOrOnline = false;

		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (productModel instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) productModel;
				isDigitalOrOnline = productModel.getProductType().getCode().equals(digitalAbo)
						  || subscriptionProductModel.getOnlineOffer();
			}

			// if one product in cart evaluates to true, return true
			if (isDigitalOrOnline)
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.ANONYMOUS_FORM_ELEMENT_GROUP);
	}
}
