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
import java.util.List;
import java.util.Map;
import org.springframework.util.Assert;

/**
 * FEG that shows different consignee allowed checkbox.
 * Also this FEG adds 'isAllDigital' flag to show different labels either for physical and kombi or for digital products.
 *
 * @module hybris - pacoshopacoshopcore
 * @version 1.1v Jun 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ievgen Azarenkov <ievgen.azarenkov@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class DifferentShipmentAddressFormElementGroup extends AbstractFormElementGroup
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
			if (isPrintGift(productModel) || isDigitalDownloadGift(productModel))
			{
				return true;
			}
		}
		return false;
	}

	private boolean isPrintGift(final ProductModel productModel)
	{
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);

		return ((productModel.getProductType().getCode().equals(printAbo)
				  && productModel.getDifferingConsigneeAllowed()));
	}

	private boolean isDigitalDownloadGift(final ProductModel productModel)
	{
		final String download = getConfigurationProperty(FormElementGroupConstants.DOWNLOAD);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		return ((productModel.getProductType().getCode().equals(digitalAbo)
				  || productModel.getProductType().getCode().equals(download))
				  && productModel.getDifferingConsigneeAllowed());
	}

	private boolean isAllDigital(final CartModel cart)
	{
		Assert.notNull(cart, "cart is null");

		boolean isAllDigital = true;

		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			isAllDigital = isAllDigital && isDigitalDownloadGift(productModel);
		}
		return isAllDigital;
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
		super.populateFormFromCart(dto, cart);

		if (dto instanceof CheckoutFormDTO)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			final Boolean additionalShippingAddressWanted = cart.getAdditionalShippingAddressWanted();
			if (additionalShippingAddressWanted != null)
			{
				checkoutFormDTO.setDifferentShipmentAddress(additionalShippingAddressWanted);
			}
		}
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.DIFFERENT_SHIPMENT_ADDRESS_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveDifferentShipmentAddressAllowed((CheckoutFormDTO) dto, cart);
	}

	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		Map<String, Object> model = super.populatePageModelData(cartModel);
		model.put(FormElementGroupConstants.DIFFERENT_SHIPMENT_ADDRESS_IS_ALL_DIGITAL, isAllDigital(cartModel));
		return model;
	}
}
