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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.shipmentpage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractAddressAwareFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.model.Title2Model;
import com.google.common.collect.Sets;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.TitleModel;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Set;


/**
 * The type Good print digital different invoce address form element group.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ievgen Azarenkov <ievgen.azarenkov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 06, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 */
public class GoodPrintDigitalDifferentInvoceAddressFormElementGroup extends AbstractAddressAwareFormElementGroup
{
	private static final Set<String> MANDATORY_ADDRESS_FIELDS =
			  Sets.newHashSet(FormElementGroupConstants.BILLING_SALUTATION,
									FormElementGroupConstants.BILLING_FIRST_NAME,
									FormElementGroupConstants.BILLING_LAST_NAME,
									FormElementGroupConstants.BILLING_STREET,
									FormElementGroupConstants.BILLING_PLZ_ZIP,
									FormElementGroupConstants.BILLING_CITY_ORT,
									FormElementGroupConstants.BILLING_COUNTRY,
									FormElementGroupConstants.BILLING_EMAIL);

	/**
	 * Check if product is displayed in cart.
	 *
	 * @param cart Cart object linked to a current session.
	 * @return Displayed flag.
	 */
	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		Assert.notNull(cart, "cart is null");
		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (isDownload(productModel) && isAdditionalInvoiceAddressWanted(cart))
			{
				return false;
			}

		}
		return isAdditionalInvoiceAddressWanted(cart);
	}

	private boolean isDownload(final ProductModel productModel)
	{
		final String download = getConfigurationProperty(FormElementGroupConstants.DOWNLOAD);
		return productModel.getProductType().getCode().equals(download);
	}

	private boolean isAdditionalInvoiceAddressWanted(final CartModel cart)
	{
		return cart.getAdditionalInvoiceAddressWanted() != null && cart.getAdditionalInvoiceAddressWanted();
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		return getValidationService().validateGoodPrintDigitalProduct((ShipmentInfoFormDTO) dto, bindingResult);
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.GOOD_PRINT_DIGITAL_DIFFERENT_INVOCE_ADDRESS_FORM_ELEMENT_GROUP);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveInvoiceAddressForGoodPrintDigital((ShipmentInfoFormDTO) dto, cart);
	}

	/**
	 * TDB later.
	 *
	 * @param dto  - Form data transfer object.
	 * @param cart - cart object linked to a current session.
	 */
	@Override
	public void populateFormFromCart(final FormDTO dto,
												final CartModel cart)
	{
		super.populateFormFromCart(dto, cart);

		final AddressModel paymentAddress = cart.getPaymentAddress();
		if (dto instanceof ShipmentInfoFormDTO && paymentAddress != null)
		{
			//=======================mandatory data start==========================================
			ShipmentInfoFormDTO shipmentInfoFormDTO = (ShipmentInfoFormDTO) dto;
			final TitleModel title = paymentAddress.getTitle();
			if (title != null)
			{
				shipmentInfoFormDTO.setBillingSalutation(title.getCode());
			}
			shipmentInfoFormDTO.setBillingFirstName(paymentAddress.getFirstname());
			shipmentInfoFormDTO.setBillingLastName(paymentAddress.getLastname());
			shipmentInfoFormDTO.setBillingEmail(paymentAddress.getEmail());

			final Title2Model title2 = paymentAddress.getTitle2();
			if (title2 != null)
			{
				shipmentInfoFormDTO.setBillingTitle(title2.getCode());
			}
			
			shipmentInfoFormDTO.setBillingStreet(paymentAddress.getLine1());
			shipmentInfoFormDTO.setBillingHouseNumber(paymentAddress.getLine2());
			shipmentInfoFormDTO.setBillingAdditionalStreet(paymentAddress.getLine3());
			shipmentInfoFormDTO.setBillingZip(paymentAddress.getPostalcode());
			shipmentInfoFormDTO.setBillingCity(paymentAddress.getTown());
			final CountryModel country = paymentAddress.getCountry();
			if (country != null)
			{
				shipmentInfoFormDTO.setBillingCountry(paymentAddress.getCountry().getIsocode());
			}
			shipmentInfoFormDTO.setBillingCompany(paymentAddress.getCompany());
		}
	}

	/**
	 * Get address mandatory fields.
	 *
	 * @return Fields list.
	 */
    @Override
	protected Set<String> getAddressMandatoryFieldNames()
	{
		return MANDATORY_ADDRESS_FIELDS;
	}


	/**
	 * If prefilled?
	 *
	 * @param cartModel Cart model.
	 * @return Prefilled flag.
	 */
	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		AddressModel addressModel = cartModel.getPaymentAddress();
		if (addressModel != null)
		{
			if ((addressModel.getTitle() != null)
					  && StringUtils.isNotEmpty(addressModel.getFirstname())
					  && StringUtils.isNotEmpty(addressModel.getLastname())
					  && StringUtils.isNotEmpty(addressModel.getLine1())
					  && StringUtils.isNotEmpty(addressModel.getLine2())
					  && StringUtils.isNotEmpty(addressModel.getPostalcode())
					  && StringUtils.isNotEmpty(addressModel.getTown())
					  && (addressModel.getCountry() != null)
					  && StringUtils.isNotEmpty(addressModel.getEmail()))
			{
				return true;
			}

		}
		return false;
	}

	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		Map<String, Object> model = super.populatePageModelData(cartModel);
		model.put(FormElementGroupConstants.ALL_COUNTRIES_MODEL_ATTR, getAllCountries(cartModel));
		return model;
	}
}
