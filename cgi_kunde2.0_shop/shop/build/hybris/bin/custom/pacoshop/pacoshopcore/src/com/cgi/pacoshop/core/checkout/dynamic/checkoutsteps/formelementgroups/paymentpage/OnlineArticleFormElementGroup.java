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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.paymentpage;


import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import de.hybris.platform.core.model.product.ProductModel;

/**
 * FormElementGroup that implements logic for Online Article.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Jan 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OnlineArticleFormElementGroup extends AbstractPaymentFormElementGroup
{

	@Override
	public boolean isNeededProductType(final ProductModel productModel)
	{
		final String download = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);
		return productModel.getProductType().getCode().equals(download);
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE_FORM_ELEMENT_GROUP);
	}

}
