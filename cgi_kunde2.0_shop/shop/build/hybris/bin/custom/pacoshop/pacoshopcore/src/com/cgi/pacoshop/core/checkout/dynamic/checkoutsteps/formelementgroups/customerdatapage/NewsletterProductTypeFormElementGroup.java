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
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.util.LogHelper;
import com.google.common.collect.Sets;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
  * The type Newsletter product type form element group.
 * @module hybris - pacoshopacoshopcore
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oksana Arkhypova < Oksana.Arkhypova@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class NewsletterProductTypeFormElementGroup extends AbstractMandatoryAddressFormElementGroup
{
    private static Logger LOG = Logger.getLogger(NewsletterProductTypeFormElementGroup.class);

	/**
	 * Method to determine whether the  formelementgroup needs to be displayed in the page based on product types
	 * in the cart and choices made by the customer in the checkout flow.
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
			if (isNewsletterType(productModel))
			{
				return true;
			}

		}
		return false;
	}

	private boolean isNewsletterType(final ProductModel productModel)
	{
		final String newsletter = getConfigurationProperty(FormElementGroupConstants.NEWS_LETTER);
		return ((productModel.getProductType().getCode().equals(newsletter)));
	}

	/**
	 * Mandatory customer data is.
	 * Salutation
	 * Surname
	 * Last Name
	 * E-Mail
	 * @param dto - Form data transfer object.
	 * @param cart - cart object linked to a current session.
	 */
	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
        super.populateFormFromCart(dto, cart);

		final AddressModel customerAddress = cart.getCustomerAddress();
		if (dto instanceof CheckoutFormDTO && customerAddress != null)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			final TitleModel title = customerAddress.getTitle();
			if (title != null)
			{
				checkoutFormDTO.setSalutation(title.getCode());
			}
			checkoutFormDTO.setFirstName(customerAddress.getFirstname());
			checkoutFormDTO.setLastName(customerAddress.getLastname());
			checkoutFormDTO.setEmail(customerAddress.getEmail());
		}
	}

	/**
	 * Mandatory customer data is.
	 * Salutation
	 * Surname
	 * Last Name
	 * E-Mail
	 * @param cart - cart object linked to a current session.
	 * @param customerModel - Customer Data.
	 */
	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart, final CustomerModel customerModel)
	{
		getCartService().prefillAndSaveCustomerAddressToCart(customerModel, cart);
	}

	/**
	 * Mandatory customer data is.
	 * Salutation
	 * Surname
	 * Last Name
	 * E-Mail
	 * @param cart - cart object linked to a current session.
	 * @return true if a FEG has been prefilled from the cart.
	 */
	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		AddressModel addressModel = cart.getCustomerAddress();
		if (addressModel != null)
		{
			LogHelper.debug(LOG,
								 "NewsletterProductTypeFormElementGroup.isPrefilled(): fn = %s, ln= %s, title = %s, email = %s",
								 addressModel.getFirstname(),
								 addressModel.getLastname(), addressModel.getTitle(), addressModel.getEmail());
			if (addressModel.getEmail() != null
				 && StringUtils.isNotEmpty(addressModel.getEmail()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		return getValidationService().validateNewsletterProductType((CheckoutFormDTO) dto, bindingResult);
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return Sets.newHashSet(FormElementGroupConstants.EMAIL);
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.NEWSLETTER_PRODUCT_TYPE_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveCustomerDataForNewsletter((CheckoutFormDTO) dto, cart);
	}
}
