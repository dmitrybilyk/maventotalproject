/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.customerdatapage;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import java.util.List;
import org.springframework.util.Assert;

/**
 *
 *
 *
 *
 * @module hybris - pacoshopacoshopcore
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ievgen Azarenkov <ievgen.azarenkov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class DifferentInvoiceAddressFormElementGroup extends AbstractFormElementGroup
{
	/**
	 * Method to determine whether the  formelementgroup needs to be displayed in the page based on product types in the cart
	 * and choices made by the customer in the checkout flow.
	 *
	 * @param cart - cart object linked to a current session
	 * @return true if the formelementgroup is required in current checkout flow
	 */
	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		Assert.notNull(cart, "cart is null");
		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (isDifferentInvoiceAddress(productModel))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Copy information from customer profile, which is either updated from SSO, or taken from hybris.
	 * The information Should be copied ONLY ONCE!!!!
	 *

	 * @param cart - cart object linked to a current session.
	 * @param customerModel - Customer Data.
	 */
	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart,
															 final CustomerModel customerModel)
	{

	}

	private boolean isDifferentInvoiceAddress(final ProductModel productModel)
	{
		return productModel.getDifferingInvoiceRecipientAllowed();
	}


	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
        super.populateFormFromCart(dto, cart);

		if (dto instanceof CheckoutFormDTO)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			final Boolean additionalInvoiceAddressWanted = cart.getAdditionalInvoiceAddressWanted();
			if (additionalInvoiceAddressWanted != null)
			{
				checkoutFormDTO.setDifferentInvoiceAddress(additionalInvoiceAddressWanted);
			}
		}
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.DIFFERENT_INVOICE_ADDRESS_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
        if (dto instanceof CheckoutFormDTO)
        {
		    getCartService().saveDifferentInvoiceAddressAllowed((CheckoutFormDTO) dto, cart);
        }
	}
}
