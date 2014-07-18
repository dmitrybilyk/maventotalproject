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
package com.cgi.pacoshop.fulfilmentprocess.test.service;

import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.fulfilmentprocess.actions.order.OrderFulfillmentType;
import com.cgi.pacoshop.fulfilmentprocess.service.impl.OrderRoutingServiceImpl;
import com.cgi.pacoshop.fulfilmentprocess.test.util.TestOrderGenerator;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Test the OrderRouting.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module hybris - pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class OrderRoutingServiceImplTest
{

	private OrderRoutingServiceImpl testMe;

	/**
	 * This method sets up the tests.
	 */
	@Before
	public void setUp()
	{
		testMe = new OrderRoutingServiceImpl();
		Logger.getLogger(OrderRoutingServiceImpl.class).setLevel(Level.DEBUG);
	}

	/**
	 * This method check that the class react well with null values.
	 */
	@Test
	public void analyzeOrderNull()
	{
		OrderFulfillmentType result = testMe.analyzeOrder(null);
		assertNull(result);
		final OrderModel order = new OrderModel();
		result = testMe.analyzeOrder(order);
		assertNull(result);
	}

	/**
	 * This method checks that the class behave normal under normal condition.
	 */
	@Test
	public void analyzeOrderSuccess()
	{
		final OrderModel order = TestOrderGenerator.createTestOrder(1);

		//by default the mock order will return null to getProduct.getImportSource.
		OrderFulfillmentType result = testMe.analyzeOrder(order);
		assertNull(null);

		when(order.getEntries().get(0).getProduct().getImportSource())
				.thenReturn(ProductImportSource.SERVICE_BUS_SINGLE_PRODUCTS);
		result = testMe.analyzeOrder(order);
		assertEquals(OrderFulfillmentType.TRANSITION_SAPSD, result);

		when(order.getEntries().get(0).getProduct().getImportSource()).thenReturn(
				ProductImportSource.SERVICE_BUS_PERIODIC_PRODUCTS);
		result = testMe.analyzeOrder(order);
		assertEquals(OrderFulfillmentType.TRANSITION_SAPMSD, result);

		when(order.getEntries().get(0).getProduct().getImportSource()).thenReturn(ProductImportSource.HYBRIS_COCKPITS);
		result = testMe.analyzeOrder(order);
		assertEquals(OrderFulfillmentType.TRANSITION_EMAIL, result);
	}
}
