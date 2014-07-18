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
package com.cgi.pacoshop.facades.flow;

import de.hybris.platform.commercefacades.order.CheckoutFacade;
import com.cgi.pacoshop.core.enums.CheckoutFlowEnum;
import com.cgi.pacoshop.core.enums.CheckoutPciOptionEnum;


/**
 * CheckoutFlowFacade interface extends the {@link CheckoutFacade}. The CheckoutFlowFacade supports resolving the
 * {@link CheckoutFlowEnum} for the current request.
 * 
 * @since 4.6
 * @spring.bean checkoutFacade
 */
public interface CheckoutFlowFacade extends CheckoutFacade
{
	CheckoutFlowEnum getCheckoutFlow();

	CheckoutPciOptionEnum getSubscriptionPciOption();
}
