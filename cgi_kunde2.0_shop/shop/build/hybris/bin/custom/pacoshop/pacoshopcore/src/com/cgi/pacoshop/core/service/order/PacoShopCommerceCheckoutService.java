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
package com.cgi.pacoshop.core.service.order;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.subscriptionservices.subscription.impl.DefaultSubscriptionCommerceCheckoutService;


/**
 * Paco customizations of the commerce checkout service.
 * 
 * @author Jan Grathwohl
 * 
 */
public class PacoShopCommerceCheckoutService extends DefaultSubscriptionCommerceCheckoutService
{

    /*
     * Extends the default implementation of the method to trigger a recalculation of the cart after the payment info
     * has been set. This is needed because the selected payment mode has an effect on the payment fees for the order.
     */
    @Override
    public boolean setPaymentInfo(final CartModel cartModel, final PaymentInfoModel paymentInfoModel)
    {
        ServicesUtil.validateParameterNotNull(cartModel, "Cart model cannot be null");
        ServicesUtil.validateParameterNotNull(paymentInfoModel, "payment info model cannot be null");

        cartModel.setPaymentInfo(paymentInfoModel);

        // Setting calculate to FALSE will trigger the recalculation later
        cartModel.setCalculated(Boolean.FALSE);

        getModelService().save(cartModel);
        getModelService().refresh(cartModel);
        getCommerceCartCalculationStrategy().calculateCart(cartModel);
        return true;
    }

}
