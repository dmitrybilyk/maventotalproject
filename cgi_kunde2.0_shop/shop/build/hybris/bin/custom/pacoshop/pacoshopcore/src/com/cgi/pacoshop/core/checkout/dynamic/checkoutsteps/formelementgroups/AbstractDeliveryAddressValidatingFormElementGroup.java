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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import com.cgi.pacoshop.core.model.DeliveryAddress;
import com.cgi.pacoshop.core.populator.DeliveryAddressPopulator;
import com.cgi.pacoshop.core.service.DeliveryAddressValidationService;
import com.cgi.pacoshop.core.util.LogHelper;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CartService;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;

/**
 * Form element group that calls validation of delivery address,
 *
 * @module hybris - pacoshopacoshopcore
 * @version 1.0v dec 12, 2013
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public abstract class AbstractDeliveryAddressValidatingFormElementGroup extends AbstractAddressAwareFormElementGroup
{
    private static final Logger LOG = Logger.getLogger(AbstractDeliveryAddressValidatingFormElementGroup.class);

    @Resource
    private DeliveryAddressValidationService deliveryAddressValidationService;

    @Resource
    private DeliveryAddressPopulator deliveryAddressPopulator;

    @Resource
    private CartService cartService;

    @Override
    public boolean validate(final FormDTO dto, final BindingResult bindingResult)
    {
        CartModel cart = cartService.getSessionCart();

        final String printAboCode = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);

        for (final AbstractOrderEntryModel entry : cart.getEntries())
        {
            final ProductModel currentProduct = entry.getProduct();
            if (currentProduct.getProductType().getCode().equals(printAboCode))
            {
                DeliveryAddress deliveryAddress = getDeliveryAddressToValidate(dto);
                if (deliveryAddress != null)
                {
                    boolean productDeliverableToAddress = deliveryAddressValidationService.
                            productDeliverableToAddress(currentProduct, deliveryAddress);
                    if (!productDeliverableToAddress)
                    {
                        bindingResult.reject("validation.error.delivery.address");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Gets the delivery address object to validate.
     * @param formDTO Form dto to get the address from
     * @return Delivery address object to validate
     */
    protected DeliveryAddress getDeliveryAddressToValidate(final FormDTO formDTO)
    {
        DeliveryAddress deliveryAddress = null;

        if (formDTO instanceof SummaryStepFormDTO)
        {
            CartModel cart = cartService.getSessionCart();
            final AddressModel customerAddress = cart.getCustomerAddress();
            final AddressModel deliveryAddressModel =
                    cart.getDeliveryAddress() != null ? cart.getDeliveryAddress() : customerAddress;

            // if we did not get delivery address from cart AND from customer address
            // that means, that the customer address is null and customer address was not imported from SSO and ServiceBus
            // which indicates an error. Therefore we cannot use deliveryAddressPopulator because it expects not null
            // delivery address
            if (deliveryAddressModel != null)
            {
                deliveryAddress = deliveryAddressPopulator.populate(deliveryAddressModel);
            }
            else
            {
                LogHelper.error(LOG,
                    "Cannot determine delivery address. It is not defined in cart and the customer address is also empty");
            }
        }
        else if (formDTO instanceof ShipmentInfoFormDTO)
        {
            final ShipmentInfoFormDTO shipmentInfoFormDTO = (ShipmentInfoFormDTO) formDTO;
            deliveryAddress = deliveryAddressPopulator.populate(shipmentInfoFormDTO);
        }
        else if (formDTO instanceof CheckoutFormDTO)
        {
            final CheckoutFormDTO customerFormDTO = (CheckoutFormDTO) formDTO;
            if (!customerFormDTO.isDifferentShipmentAddress())
            {
                deliveryAddress = deliveryAddressPopulator.populate(customerFormDTO);
            }
        }

        return deliveryAddress;
    }
}
