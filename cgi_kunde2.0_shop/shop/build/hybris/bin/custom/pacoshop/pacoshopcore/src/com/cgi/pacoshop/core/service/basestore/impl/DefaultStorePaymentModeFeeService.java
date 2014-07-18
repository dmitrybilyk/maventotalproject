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
package com.cgi.pacoshop.core.service.basestore.impl;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.store.BaseStoreModel;

import com.cgi.pacoshop.core.model.StorePaymentModeFeeModel;
import com.cgi.pacoshop.core.service.basestore.StorePaymentModeFeeService;


/**
 * Default implementation of {@link StorePaymentModeFeeService}.
 * 
 * @author Jan Grathwohl
 * 
 */
public class DefaultStorePaymentModeFeeService implements StorePaymentModeFeeService
{

    @Override
    public StorePaymentModeFeeModel findFeeForStoreAndPaymentMode(final BaseStoreModel store, final PaymentModeModel paymentMode)
    {
        for (final StorePaymentModeFeeModel fee : store.getPaymentModeFees())
        {
            if (fee.getPaymentMode().equals(paymentMode))
            {
                return fee;
            }
        }

        return null;
    }

}
