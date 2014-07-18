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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.summarypage;

import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import de.hybris.platform.core.model.order.CartModel;

/**
 * Form Element Group as additional condition for showing customerInfo at summary page.
 *
 * @module pacoshopcore
 * @version 1.0v May 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CustomerInfoSummaryPageFormElementGroup extends AbstractFormElementGroup
{
	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		return showOnlyIfInvoiceWantedCheckedOrNotExist(cart);
	}

	private boolean showOnlyIfInvoiceWantedCheckedOrNotExist(final CartModel cart)
	{
		return cart.getInvoiceWanted() != null ? cart.getInvoiceWanted() : true;
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.CUSTOMER_INFO_SUMMARY_PAGE_FORM_ELEMENT_GROUP);
	}
}
