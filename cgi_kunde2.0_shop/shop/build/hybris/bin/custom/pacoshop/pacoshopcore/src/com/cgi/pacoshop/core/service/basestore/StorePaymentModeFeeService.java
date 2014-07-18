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
package com.cgi.pacoshop.core.service.basestore;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.store.BaseStoreModel;

import com.cgi.pacoshop.core.model.StorePaymentModeFeeModel;


/**
 * Service for working with the StorePaymentModeFees that can be added to a BaseStore to specify additional fees for
 * specific payment modes.
 * 
 * @author Jan Grathwohl
 * 
 */
public interface StorePaymentModeFeeService
{

    /**
     * Finds the payment mode fee applicable to the given store and payment mode
     * 
     * @param store
     * @param paymentMode
     * @return The StorePaymentModeFeeModel matching the given store and payment mode, or null if there is no existing
     *         payment mode fee matching those values.
     */
    StorePaymentModeFeeModel findFeeForStoreAndPaymentMode(final BaseStoreModel store, final PaymentModeModel paymentMode);

}
