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
package com.cgi.pacoshop.fulfilmentprocess.test;

import com.cgi.pacoshop.fulfilmentprocess.impl.DefaultCheckOrderService;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;

import java.util.Arrays;
import java.util.Collections;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;


/**
 * OOTB test.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@UnitTest
public class DefaultCheckOrderServiceTest
{
	private final DefaultCheckOrderService defaultCheckOrderService = new DefaultCheckOrderService();

	private OrderModel order;


	/**
	 * Setup.
	 *
	 * @throws Exception
	 *             if something bad happen.
	 */
	@Before
	public void setUp() throws Exception
	{
		order = new OrderModel();
		order.setCalculated(Boolean.TRUE);
		order.setEntries(Arrays.<AbstractOrderEntryModel>asList(new OrderEntryModel()));
		order.setDeliveryAddress(new AddressModel());
		order.setPaymentInfo(new PaymentInfoModel());
	}

	/**
	 * Test check.
	 */
	@Test
	public void testCheck()
	{
		Assertions.assertThat(defaultCheckOrderService.check(order)).isTrue();
	}

	/**
	 * Test not calculated.
	 */
	@Test
	public void testNotCalculated()
	{
		order.setCalculated(Boolean.FALSE);
		Assertions.assertThat(defaultCheckOrderService.check(order)).isTrue();
	}

	/**
	 * Test no entries.
	 */
	@Test
	public void testNoEntries()
	{
		order.setEntries(Collections.EMPTY_LIST);
		Assertions.assertThat(defaultCheckOrderService.check(order)).isTrue();
	}

	/**
	 * Test no payment info.
	 */
	@Test
	public void testNoPaymentInfo()
	{
		order.setPaymentInfo(null);
		Assertions.assertThat(defaultCheckOrderService.check(order)).isTrue();
	}
}
