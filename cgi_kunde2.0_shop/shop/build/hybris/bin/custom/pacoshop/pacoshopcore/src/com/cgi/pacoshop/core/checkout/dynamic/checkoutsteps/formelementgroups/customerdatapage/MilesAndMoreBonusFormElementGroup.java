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
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractAddressAwareFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.MilesAndMoreBonusModel;
import com.google.common.collect.Sets;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 *
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 18, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class MilesAndMoreBonusFormElementGroup extends AbstractAddressAwareFormElementGroup
{

	public static final String EMPTY_STRING = "";

	/**
	 * Method to determine whether the  formelementgroup needs to be displayed in the page based on product types in
	 * the cart and choices made by the customer in the checkout flow.
	 * @param cart - cart object linked to a current session
	 * @return true if the formelementgroup is required in current checkout flow
	 */
	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		Assert.notNull(cart, "cart is null");
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		List<AbstractOrderEntryModel> entries = cart.getEntries();

		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if ((productModel.getProductType().getCode().equals(printAbo)
					  || productModel.getProductType().getCode().equals(digitalAbo))
					  && productModel instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) productModel;
				for (BonusModel bonus : subscriptionProductModel.getBonuses())
				{
					if (bonus instanceof MilesAndMoreBonusModel)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return Sets.newHashSet(
				  FormElementGroupConstants.MILES_AND_MORE_NUMBER);

	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
        super.populateFormFromCart(dto, cart);

		if (dto instanceof CheckoutFormDTO)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			checkoutFormDTO.setMilesAndMoreNumber(getMilesAndMoreNumber(cart));
		}
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.MILES_AND_MORE_BONUS_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		final List<AbstractOrderEntryModel> entries = cart.getEntries();
		boolean rcrCase = false;

		for (AbstractOrderEntryModel entry : entries)
		{
			if (entry.getProduct() instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) entry.getProduct();
				if (subscriptionProductModel.getReadersCanvassReaders())
				{
					rcrCase = true;
					break;
				}
			}
		}
		final String milesAndMoreNumber = ((CheckoutFormDTO) dto).getMilesAndMoreNumber();
		if (rcrCase)
		{
			getCartService().saveMilesAndMoreNumberToBonusRecipientAddress(milesAndMoreNumber, cart);
		}
		else
		{
			getCartService().saveMilesAndMoreNumberToCustomerAddress(milesAndMoreNumber, cart);
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

	}

	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		return StringUtils.isNotEmpty(getMilesAndMoreNumber(cartModel));
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		String milesAndMoreNumber = ((CheckoutFormDTO) dto).getMilesAndMoreNumber();
		return getValidationService().validateMilesAndMore(milesAndMoreNumber, bindingResult);
	}


	private String getMilesAndMoreNumber(final CartModel cartModel)
	{
		AddressModel address1 = cartModel.getBonusRecipientAddress(),
						 address2 = cartModel.getCustomerAddress();

		String mamNumber1 = (address1 != null) ? address1.getMilesAndMoreNumber() : EMPTY_STRING,
				 mamNumber2 = (address2 != null) ? address2.getMilesAndMoreNumber() : EMPTY_STRING;

		return StringUtils.isNotEmpty(mamNumber1) ? mamNumber1
				  : StringUtils.isNotEmpty(mamNumber2) ? mamNumber2
				  : EMPTY_STRING;
	}

}
