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
package com.cgi.pacoshop.core.payment.comparators;

import com.cgi.hybris.payment.core.data.PaymentModeData;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 26, 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class PacoshopPaymentModeComparator implements Comparator<PaymentModeData>, Serializable
{
	@Override
	public int compare(final PaymentModeData o1, final PaymentModeData o2)
	{
		return o1.getOrderPriority() - o2.getOrderPriority();
	}
}
