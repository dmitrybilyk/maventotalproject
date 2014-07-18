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


import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;

/**
 *
 * @module shop
 * @version 1.0v Apr 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PaymentInAdvanceFormElementGroup extends AbstractFormElementGroup
{
    /**
     * Determines if one of the products contains prepayOnly flag.
     *
     * @param cartModel Cart
     * @return true if product has prepayOnly flag set and false otherwise
     */
    private static boolean isProductPrepayOnly(final CartModel cartModel)
    {
        for (final AbstractOrderEntryModel abstractOrderEntryModel : cartModel.getEntries())
        {
            final ProductModel productModel = abstractOrderEntryModel.getProduct();
            if (productModel.getPrepayOnly() != null && productModel.getPrepayOnly())
            {
                return true;
            }
        }

        return false;
    }

    @Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.PAYMENT_IN_ADVANCE_FORM_ELEMENT_GROUP);
	}

    @Override
    public boolean isDisplayed(final CartModel cartModel)
    {
        return isProductPrepayOnly(cartModel);
    }
}
