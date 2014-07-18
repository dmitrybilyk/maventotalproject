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
import com.cgi.pacoshop.core.model.Title2Model;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
  * The type Good print digital product type form element group.
 * @module hybris - pacoshopacoshopcore
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <ievgen.azarenkov@symmetrics.de>
 * @version 1.0v Jan 10, 2014
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see 'https://wiki.hybris.com/'.
 */
public class GoodPrintDigitalProductTypeFormElementGroup extends AbstractMandatoryAddressFormElementGroup
{
    private static final Logger LOG = Logger.getLogger(GoodPrintDigitalProductTypeFormElementGroup.class);

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
			if (isGoodPrintDigital(productModel))
			{
				return true;
			}

		}
		return false;
	}

	private boolean isGoodPrintDigital(final ProductModel productModel)
	{
		final String good = getConfigurationProperty(FormElementGroupConstants.GOOD);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);

        String productCode = productModel.getProductType().getCode();
        return ((productCode.equals(good) || productCode.equals(digitalAbo) || productCode.equals(printAbo)));
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		Set<String> mandatoryFieldNames = new HashSet<>(getAddressMandatoryFieldNames());
		mandatoryFieldNames.addAll(getProductSpecificMandatoryFields(cart));
		return mandatoryFieldNames;
	}

	/**
	 * Mandatory customer data is.
	 * Salutation
	 * Surname
	 * Last Name
	 * E-Mail
	 * Street
	 * Postal Code
	 * Town
	 * Country
	 *
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
			//=======================mandatory data start==========================================
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			final TitleModel title = customerAddress.getTitle();
			if (title != null)
			{
				checkoutFormDTO.setSalutation(title.getCode());
			}
			final Title2Model title2 = customerAddress.getTitle2();
			if (title2 != null)
			{
				checkoutFormDTO.setTitle(title2.getCode());
			}
			checkoutFormDTO.setFirstName(customerAddress.getFirstname());
			checkoutFormDTO.setLastName(customerAddress.getLastname());
			if (isCustomerAddressInDeliveryCountries(cart))
			{
				//https://jira.symmetrics.de/browse/KS-364:
				//In case the Customer is logged in and we have a prefilling of
				// the customer address based on the profile information,
				// the system needs to validate whether the prefilled information is compatible with the list of available countries.
				// In case it is not (e.g. customer has got a French address but product in cart can only be delivered in Germany),
				// the system does NOT prefill the customer address on the cart and does not jump over the customer page.
				//=======================customer address start==========================================
				checkoutFormDTO.setEmail(customerAddress.getEmail());
				checkoutFormDTO.setMobilePhoneNumber(customerAddress.getMobileNumber());
				checkoutFormDTO.setBusinessPhoneNumber(customerAddress.getBusinessNumber());
				checkoutFormDTO.setHomePhoneNumber(customerAddress.getHomeNumber());
				checkoutFormDTO.setAdditionalStreet(customerAddress.getLine3());
				checkoutFormDTO.setStreet(customerAddress.getLine1());
				checkoutFormDTO.setHouseNumber(customerAddress.getLine2());
				checkoutFormDTO.setZip(customerAddress.getPostalcode());
				checkoutFormDTO.setCity(customerAddress.getTown());
				final CountryModel country = customerAddress.getCountry();
				if (country != null)
				{
					checkoutFormDTO.setCountry(country.getIsocode());
				}
				//=======================customer address end==========================================
			}
			//=======================mandatory data end==========================================
			final Date dateOfBirth = customerAddress.getDateOfBirth();
            checkoutFormDTO.setDateOfBirth(dateOfBirth);
			//=======================GoodPrintDigitalProductTypeFormElementGroup specific data start=======================
			checkoutFormDTO.setCompany(customerAddress.getCompany());
			checkoutFormDTO.setPositionCompany(customerAddress.getRoleInCompany());
			//=======================GoodPrintDigitalProductTypeFormElementGroup specific data end=======================
		}
	}

	/**
	 * Mandatory customer data is.
	 * Salutation
	 * Surname
	 * Last Name
	 * E-Mail
	 * Street
	 * Postal Code
	 * Town
	 * Country
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
	 * Street
	 * Postal Code
	 * Town
	 * Country
	 * @param cart - cart object linked to a current session.
	 * @return true if a FEG has been prefilled from the cart.
	 */
	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		final AddressModel customerAddress = cart.getCustomerAddress();
		boolean customerAddressInDeliveryCountries = isCustomerAddressInDeliveryCountries(cart);
		boolean addressPrefilled = customerAddressInDeliveryCountries && isAddressPrefilled(customerAddress);
		debug(LOG,
				"isPrefilled: customerAddressInDeliveryCountries && isAddressPrefilled(customerAddress): %b.",
				addressPrefilled);
		return addressPrefilled;
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
        boolean result =
                getValidationService().validateCustomerDataGoodPrintDigitalAbo((CheckoutFormDTO) dto, bindingResult);
        return result && super.validate(dto, bindingResult);
    }


	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.GOOD_PRINT_DIGITAL_PRODUCT_TYPE_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveCustomerDataForGoodPrintDigital((CheckoutFormDTO) dto, cart);
	}
	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		Map<String, Object> model = super.populatePageModelData(cartModel);
		model.put(FormElementGroupConstants.DELIVERY_COUNTRIES_MODEL_ATTR, getDeliveryCountries(cartModel));
		return model;
	}
}
