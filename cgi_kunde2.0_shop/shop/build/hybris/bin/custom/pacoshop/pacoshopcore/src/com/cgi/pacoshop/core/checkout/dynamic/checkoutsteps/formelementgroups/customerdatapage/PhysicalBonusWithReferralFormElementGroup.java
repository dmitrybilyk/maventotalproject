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
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractAddressAwareFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.ProductBonusModel;
import com.cgi.pacoshop.core.model.Title2Model;
import com.google.common.collect.Sets;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;


/**
 * The type Physical bonus with referral form element group.
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class PhysicalBonusWithReferralFormElementGroup extends AbstractAddressAwareFormElementGroup
{

	private static final Set<String> MANDATORY_FIELDS =
			  Sets.newHashSet(FormElementGroupConstants.REFERRAL_ZIP,
									FormElementGroupConstants.REFERRAL_CITY,
									FormElementGroupConstants.REFERRAL_LAND,
									FormElementGroupConstants.REFERRAL_STREET,
									FormElementGroupConstants.REFERRAL_HOUSE_NUMBER,
									FormElementGroupConstants.REFERRAL_FIRSTNAME,
									FormElementGroupConstants.REFERRAL_LASTNAME,
									FormElementGroupConstants.REFERRAL_SALUTATION);

	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);
		for (final AbstractOrderEntryModel abstractOrderEntryModel : cart.getEntries())
		{
			final ProductModel productModel = abstractOrderEntryModel.getProduct();
			if ((productModel.getProductType().getCode().equals(printAbo) || productModel.getProductType().getCode()
					  .equals(digitalAbo))
					  && productModel instanceof SubscriptionProductModel)
			{
				final SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) productModel;
				for (final BonusModel bonusModel : subscriptionProductModel.getBonuses())
				{
					if (bonusModel instanceof ProductBonusModel
							  && ((SubscriptionProductModel) productModel).getReadersCanvassReaders())
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
		return MANDATORY_FIELDS;
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.PHYSICAL_BONUS_WITH_REFERRAL_FORM_ELEMENT_GROUP);
	}

	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		return isNotNull(cartModel.getBonusRecipientAddress());
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
	{
		getCartService().saveCustomerInfoForPhysicalBonus((CheckoutFormDTO) dto, cartModel);
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		return getValidationService().validatePhysicalBonusWithReferral((CheckoutFormDTO) dto, bindingResult);
	}

	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		Map<String, Object> model = super.populatePageModelData(cartModel);
		model.put(FormElementGroupConstants.ALL_COUNTRIES_MODEL_ATTR, getAllCountries(cartModel));
		return model;
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
		super.populateFormFromCart(dto, cart);

		if (dto instanceof CheckoutFormDTO)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			AddressModel bonusRecipientAddress = cart.getBonusRecipientAddress();
			if (isNotNull(bonusRecipientAddress))
			{
				checkoutFormDTO.setReferralFirstName(bonusRecipientAddress.getFirstname());
				checkoutFormDTO.setReferralLastName(bonusRecipientAddress.getLastname());

				final Title2Model title2 = bonusRecipientAddress.getTitle2();
				checkoutFormDTO.setReferralTitle(isNotNull(title2) ? bonusRecipientAddress.getTitle2().getCode() : StringUtils.EMPTY);

				TitleModel titleModel = bonusRecipientAddress.getTitle();
				checkoutFormDTO.setReferralSalutation((isNotNull(titleModel)) ? titleModel.getCode() : StringUtils.EMPTY);

				CountryModel countryModel = bonusRecipientAddress.getCountry();
				checkoutFormDTO.setReferralCountry((isNotNull(countryModel)) ? countryModel.getIsocode() : StringUtils.EMPTY);

				checkoutFormDTO.setReferralZip(bonusRecipientAddress.getPostalcode());
				checkoutFormDTO.setReferralCity(bonusRecipientAddress.getTown());
				checkoutFormDTO.setReferralStreet(bonusRecipientAddress.getLine1());
				checkoutFormDTO.setReferralHouseNumber(bonusRecipientAddress.getLine2());
				checkoutFormDTO.setReferralAdditionalAddress(bonusRecipientAddress.getLine3());
			}
		}
	}

	private boolean isNotNull(final Object object)
	{
		return object != null;
	}
}
