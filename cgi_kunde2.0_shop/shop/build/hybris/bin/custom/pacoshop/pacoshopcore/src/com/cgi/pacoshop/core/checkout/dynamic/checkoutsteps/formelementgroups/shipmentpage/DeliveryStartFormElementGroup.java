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
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CartService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

/**
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ievgen Azarenkov <ievgen.azarenkov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DeliveryStartFormElementGroup extends AbstractAddressAwareFormElementGroup
{
	private static final Logger LOG = LoggerFactory.getLogger(DeliveryStartFormElementGroup.class);

    @Resource
    private CartService cartService;

	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		// check whether product is PRINT ABO or a DIGITAL ABO (if any of products has such type -> display this section)
		for (final AbstractOrderEntryModel abstractOrderEntryModel : cart.getEntries())
		{
			final String productTypeCode = abstractOrderEntryModel.getProduct().getProductType().getCode();
			LOG.debug("isDisplayed() productTypeCode={}", productTypeCode);
			if (printAbo.equals(productTypeCode) || digitalAbo.equals(productTypeCode))
			{
				LOG.debug("isDisplayed() delivery start section is diplayed");
				return true;
			}
		}

		LOG.debug("isDisplayed() delivery start section is not diplayed");
		return false;
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
        super.populateFormFromCart(dto, cart);

		if (dto instanceof ShipmentInfoFormDTO)
		{
			ShipmentInfoFormDTO shipmentStepFormDTO = (ShipmentInfoFormDTO) dto;

			final AddressModel deliveryAddress = cart.getDeliveryAddress();
			if (deliveryAddress != null)
			{
				shipmentStepFormDTO.setFirstName(deliveryAddress.getFirstname());
				shipmentStepFormDTO.setLastName(deliveryAddress.getLastname());
			}
			shipmentStepFormDTO.setDeliveryStart(isDeliveryStartChecked(cart));
			if (!isDeliveryStartChecked(cart))
			{
				shipmentStepFormDTO.setDeliveryStartDate(cart.getDeliveryStartDate());
			}
		}
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		if (dto instanceof ShipmentInfoFormDTO)
		{
			setDeliveryStartChecked(((ShipmentInfoFormDTO) dto).isDeliveryStart());
		}
		return getValidationService().validateShippingStartDeliveryDate((ShipmentInfoFormDTO) dto, bindingResult);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveDeliveryDateData((ShipmentInfoFormDTO) dto, cart);
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.DELIVERY_START_FORM_ELEMENT_GROUP);
	}

	private boolean isDeliveryStartChecked(final CartModel cart)
	{
		Object checkboxChecked = cart.getDeliveryNow();
		return (checkboxChecked == null) || Boolean.parseBoolean(checkboxChecked.toString());
	}

	private void setDeliveryStartChecked(final boolean deliveryStart)
	{
		cartService.getSessionCart().setDeliveryNow(deliveryStart);
	}

}
