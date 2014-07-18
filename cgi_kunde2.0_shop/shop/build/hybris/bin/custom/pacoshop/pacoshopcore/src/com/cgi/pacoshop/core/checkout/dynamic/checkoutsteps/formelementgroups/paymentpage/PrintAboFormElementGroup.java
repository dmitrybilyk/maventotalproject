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
 * Form element group for Print Abo product type
 * on payment page
 *
 * @module build
 * @version 1.0v Mar 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PrintAboFormElementGroup extends AbstractPaymentFormElementGroup
{

	@Override
	public boolean isNeededProductType(final ProductModel productModel)
	{
		final String download = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		return productModel.getProductType().getCode().equals(download);
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.PRINT_ABO_FORM_ELEMENT_GROUP);
	}

}

