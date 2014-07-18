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
package com.cgi.pacoshop.core.strategies;

import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.VATGroupService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;

import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.order.strategies.calculation.FindPaymentCostStrategy;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.PriceValue;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.cgi.hybris.payment.core.model.PaymentMethodModel;
import com.cgi.pacoshop.core.model.StorePaymentModeFeeModel;
import com.cgi.pacoshop.core.service.basestore.StorePaymentModeFeeService;


/**
 * Calculates the payment fee, based on the StorePaymentModeFees that are configured on the Base Store.
 *
 * @author Jan Grathwohl
 *
 */
public class PacoShopFindPaymentCostStrategy implements FindPaymentCostStrategy
{

	private static final Logger LOG         = Logger.getLogger(PacoShopFindPaymentCostStrategy.class);
	private static final int    ZERO        = 0;

	@Resource
	private VATGroupService vatGroupService;

	@Resource
	private StorePaymentModeFeeService storePaymentModeFeeService;

	@Resource
	private ShopConfigurationService shopConfigurationService;


	@Override
	public PriceValue getPaymentCost(final AbstractOrderModel order)
	{
		return getPaymentCostValue(order);
	}

	private PriceValue getPaymentCostValue(final AbstractOrderModel order)
	{
		if (order.getParent() != null)
		{
			debug(LOG, "Not calculating payment cost for order [%s] because it is a child cart", order.getCode());
			return new PriceValue(order.getCurrency().getIsocode(), ZERO, order.getNet());
		}

		final BaseStoreModel store = order.getStore();

		debug(LOG, "Calculating payment cost for AbstractOrder [%s], store [%s]", order.getCode(), store.getUid());

		if (order.getPaymentInfo() != null && order.getPaymentInfo().getPaymentMethod() != null)
		{
			// Get the used payment mode from the order
			final PaymentMethodModel paymentMethod = order.getPaymentInfo().getPaymentMethod();
			final PaymentModeModel paymentMode = paymentMethod.getMode();

			debug(LOG, "Payment mode for order [%s] is [%s]", order.getCode(), paymentMode.getCode());


			// Find the fee for this store and payment mode
			final StorePaymentModeFeeModel paymentModeFee = storePaymentModeFeeService.findFeeForStoreAndPaymentMode(store,
																																						paymentMode);

			if (paymentModeFee == null)
			{
				debug(LOG, "No StorePaymentModeFee found for store [%s], payment mode [%s] - setting payment cost to 0",
						store.getUid(), paymentMode.getCode());

				return new PriceValue(order.getCurrency().getIsocode(), ZERO, order.getNet());
			}
			else
			{
				debug(LOG,
						"Found payment mode fee with store [%s], payment mode [%s], description [%s], amount [%s], currency [%s]",
						paymentModeFee.getStore().getUid(), paymentModeFee.getPaymentMode().getCode(),
						paymentModeFee.getDescription(), paymentModeFee.getAmount(), paymentModeFee.getCurrency().getIsocode());

				return new PriceValue(paymentModeFee.getCurrency().getIsocode(), paymentModeFee.getAmount(), order.getNet());
			}
		}
		else
		{
			debug(LOG, "Order [%s] does not have a selected payment mode yet", order.getCode());

			return new PriceValue(order.getCurrency().getIsocode(), ZERO, order.getNet());
		}
	}

}
