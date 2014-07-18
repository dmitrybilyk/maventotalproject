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
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractDeliveryAddressValidatingFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.google.common.collect.Sets;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ShipmentPageDigitalDifferentShipmentAddressFormElementGroup extends AbstractDeliveryAddressValidatingFormElementGroup
{
    private static final Set<String> MANDATORY_FIELD_NAMES =
            Sets.newHashSet(FormElementGroupConstants.NEW_SHIPMENT_EMAIL,
                            FormElementGroupConstants.NEW_SHIPMENT_SALUTATION,
                            FormElementGroupConstants.NEW_SHIPMENT_FIRST_NAME,
                            FormElementGroupConstants.NEW_SHIPMENT_LAST_NAME);

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
			if (isDifferentShipmentAddressNeeded(productModel, cart))
			{
				return true;
			}

		}
		return false;

	}


	private boolean isDifferentShipmentAddressNeeded(final ProductModel productModel, final CartModel cart)
	{
		return isDigitalProduct(productModel)
				  && cart.getAdditionalShippingAddressWanted() != null && cart.getAdditionalShippingAddressWanted();
	}

	private boolean isDigitalProduct(final ProductModel productModel)
	{
		final String download = getConfigurationProperty(FormElementGroupConstants.DOWNLOAD);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		return ((productModel.getProductType().getCode().equals(digitalAbo)
				  || productModel.getProductType().getCode().equals(download))
				  && productModel.getDifferingConsigneeAllowed());
	}


	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		boolean result = getValidationService().validateDifferentDigitalDeliveryAddress(
                (ShipmentInfoFormDTO) dto, bindingResult);
        return result && super.validate(dto, bindingResult);
	}

	/**
	 * TDB later.

	 * @param cart - cart object linked to a current session.
	 * @param customerModel - Customer Data.
	 */
	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart,
															 final CustomerModel customerModel)
	{

	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
        super.populateFormFromCart(dto, cart);

		final AddressModel deliveryAddress = cart.getDeliveryAddress();
		if (dto instanceof ShipmentInfoFormDTO && deliveryAddress != null)
		{
			ShipmentInfoFormDTO shipmentInfoFormDTO = (ShipmentInfoFormDTO) dto;
			final TitleModel title = deliveryAddress.getTitle();
			if (title != null)
			{
				shipmentInfoFormDTO.setNewShipmentSalutation(title.getCode());
			}
			shipmentInfoFormDTO.setNewShipmentFirstName(deliveryAddress.getFirstname());
			shipmentInfoFormDTO.setNewShipmentLastName(deliveryAddress.getLastname());
			shipmentInfoFormDTO.setNewShipmentEmail(deliveryAddress.getEmail());
		}
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(
				  FormElementGroupConstants.DIGITAL_DIFFERENT_SHIPMENT_ADDRESS_ON_SHIPMENT_PAGE_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveDifferentShipmentAddress((ShipmentInfoFormDTO) dto, cart);
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return MANDATORY_FIELD_NAMES;
	}

	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		AddressModel addressModel = cartModel.getDeliveryAddress();
		if (addressModel != null)
		{
			if (StringUtils.isNotEmpty(addressModel.getEmail())
					  && StringUtils.isNotEmpty(addressModel.getFirstname())
					  && StringUtils.isNotEmpty(addressModel.getLastname())
					  && StringUtils.isNotEmpty(addressModel.getTitle().getCode()))
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
		model.put(FormElementGroupConstants.DELIVERY_COUNTRIES_MODEL_ATTR, getDeliveryCountries(cartModel));
		return model;
	}

}
