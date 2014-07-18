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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.customerdatapage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;

/**
 *
 *
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 *
 *
 * *********************Field mapping***************************
 *
 * userdatapage: aboNumber
 *
 *
 */
public class AboOfferFormElementGroup extends AbstractFormElementGroup
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
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);
		for (final AbstractOrderEntryModel abstractOrderEntryModel : cart.getEntries())
		{
			ProductModel productModel = abstractOrderEntryModel.getProduct();
			if (isPrintOrDigitalAbo(printAbo, digitalAbo, productModel))
			{
				SubscriptionProductModel subscriptionProductModel = ((SubscriptionProductModel) productModel);
				if (subscriptionProductModel.getClientOffer() || subscriptionProductModel.getInvoiceRecipientOffer()
						  || subscriptionProductModel.getConsigneeOffer())
				{
					return true;
				}
			}
		}
		return false;
	}

    private boolean isPrintOrDigitalAbo(final String printAbo, final String digitalAbo, final ProductModel productModel)
    {
        final String code = productModel.getProductType().getCode();
        return (productModel instanceof SubscriptionProductModel && code.equals(printAbo) || code.equals(digitalAbo));
    }

    @Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
        super.populateFormFromCart(dto, cart);

		if (dto instanceof CheckoutFormDTO && StringUtils.isNotEmpty(cart.getExistingSubscriptionId()))
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			checkoutFormDTO.setAboNumber(cart.getExistingSubscriptionId());
		}
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
		cart.setExistingSubscriptionId(customerModel.getExistingSubscriptionId());
		getModelService().save(cart);
	}

	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		return StringUtils.isNotEmpty(cartModel.getExistingSubscriptionId());
	}
	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.ABO_OFFER_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
	{
		String aboNumber = ((CheckoutFormDTO) dto).getAboNumber();
		getCartService().saveAboNumber(aboNumber, cartModel);
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		String aboNumber = "";
		if (dto instanceof CheckoutFormDTO)
		{
			aboNumber = ((CheckoutFormDTO) dto).getAboNumber();
		}

		return getValidationService().validateAboNumber(aboNumber, bindingResult);
	}
}
