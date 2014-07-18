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


import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.CashBonusModel;
import com.google.common.collect.Sets;


/**
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v Dec 16, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexander Ionov <alexander.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 * 
 * 
 */
public abstract class AbstractMonetaryBonusFormElementGroup extends AbstractFormElementGroup
{
    abstract boolean isReferral(final SubscriptionProductModel subscriptionProductModel);

    /**
     * Method to determine whether the formelementgroup needs to be displayed in the page based on product types in the
     * cart and choices made by the customer in the checkout flow.
     * 
     * @param cart
     *            - cart object linked to a current session
     * @return true if the formelementgroup is required in current checkout flow
     */
    @Override
    public boolean isDisplayed(final CartModel cart)
    {
        Assert.notNull(cart, "cart is null");

        final List<AbstractOrderEntryModel> entries = cart.getEntries();
        for (final AbstractOrderEntryModel entry : entries)
        {
            final ProductModel product = entry.getProduct();

            if (isMonetaryBonus(product))
            {
                return true;
            }
        }
        return false;
    }

    boolean isMonetaryBonus(final ProductModel product)
    {
        final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
        final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

        if ((product.getProductType().getCode().equals(printAbo)
                || product.getProductType().getCode().equals(digitalAbo))
                && product instanceof SubscriptionProductModel)
        {
            final SubscriptionProductModel subscriptionProduct = (SubscriptionProductModel) product;
            for (final BonusModel bonus : subscriptionProduct.getBonuses())
            {
                if (bonus instanceof CashBonusModel && isReferral(subscriptionProduct))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<String> getMandatoryFieldNames(final CartModel cart)
    {
        return Sets.newHashSet(
                getConfigurationProperty(FormElementGroupConstants.IBAN),
                getConfigurationProperty(FormElementGroupConstants.BIC),
                getConfigurationProperty(FormElementGroupConstants.BLZ),
                getConfigurationProperty(FormElementGroupConstants.ACCOUNT_NUMBER));
    }


    @Override
    public void populateFormFromCart(final FormDTO dto, final CartModel cart)
    {
        if (dto instanceof CheckoutFormDTO)
        {
            final CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;

            final PacoDebitPaymentInfoModel recipientPaymentInfo = cart.getBonusRecipientPaymentInfo();
            if (recipientPaymentInfo != null)
            {
                if (recipientPaymentInfo.getIBAN() != null)
                {
                    checkoutFormDTO.setIbanBACfirstName(recipientPaymentInfo.getFirstName());
                    checkoutFormDTO.setIbanBAClastName(recipientPaymentInfo.getLastName());
                    checkoutFormDTO.setIban(recipientPaymentInfo.getIBAN());
                    checkoutFormDTO.setBic(recipientPaymentInfo.getBIC());
                    checkoutFormDTO.setTabIbanBic(true);
                }
                else
                {
                    checkoutFormDTO.setAccountNumber(recipientPaymentInfo.getAccountNumber());
                    checkoutFormDTO.setBanIdNumber(recipientPaymentInfo.getBankIdNumber());
                    checkoutFormDTO.setKontonummerBLZfirstName(recipientPaymentInfo.getFirstName());
                    checkoutFormDTO.setKontonummerBLZlastName(recipientPaymentInfo.getLastName());
                    checkoutFormDTO.setTabKontonummerBlz(true);
                }
            }
        }
    }

    /**
     * Copy information from customer profile, which is either updated from SSO, or taken from hybris. The information
     * Should be copied ONLY ONCE!!!!
     * 
     * @param cart
     *            - cart object linked to a current session.
     * @param customerModel
     *            - Customer Data.
     */
    @Override
    public void prefillCartFromCustomerProfile(final CartModel cart,
            final CustomerModel customerModel)
    {

    }


    @Override
    public boolean isPrefilled(final CartModel cartModel)
    {
        return cartModel.getBonusRecipientPaymentInfo() != null;
    }

    @Override
    public void saveFormToCart(final FormDTO dto, final CartModel cart)
    {
        getCartService().savePaymentInfoForMonetaryBonus((CheckoutFormDTO) dto, cart);
    }

    @Override
    public boolean validate(final FormDTO dto, final BindingResult bindingResult)
    {
        return getValidationService().validateMonetaryBonus((CheckoutFormDTO) dto, bindingResult);
    }

    @Override
    public Map<String, Object> populatePageModelData(final CartModel cartModel)
    {
        final Map<String, Object> result = super.populatePageModelData(cartModel);

        // getBonusRecipientPaymentInfo(...) is always return PacoDebitPaymentInfoModel. See pacoshopcore-items.xml for details.
        final PacoDebitPaymentInfoModel recipientPaymentInfo = cartModel.getBonusRecipientPaymentInfo();
        if (recipientPaymentInfo != null)
        {
            if (recipientPaymentInfo.getIBAN() != null)
            {
                result.put(FormElementGroupConstants.TAB_IBAN_BIC, true);
            }
            else
            {
                result.put(FormElementGroupConstants.TAB_KONTONUMMER_BLZ, true);
            }
        }
        return result;
    }
}
