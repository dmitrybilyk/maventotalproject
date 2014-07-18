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
package com.cgi.pacoshop.core.payment.populators;


import com.cgi.hybris.payment.core.data.PaymentModeData;

import com.cgi.hybris.payment.core.populators.core.PaymentModePopulator;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Mar 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PacoshopPaymentModePopulator extends PaymentModePopulator
{
	@Override
	public void populate(final PaymentModeModel source, final PaymentModeData target) throws ConversionException

	{
		super.populate(source, target);
		target.setOrderPriority(source.getOrderPriority());
	}
}
