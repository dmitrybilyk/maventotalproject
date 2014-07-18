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
package com.cgi.pacoshop.fulfilmentprocess.test.populator;

import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPOrderEntryEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.impl.SAPOrderEntryEntityPopulatorImpl;
import com.cgi.pacoshop.fulfilmentprocess.test.util.TestOrderGenerator;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Represents the test for the {@link SAPOrderEntryEntityPopulatorImpl}.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module hybris - pacoshopcore
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SAPOrderEntryEntityPopulatorImplTest
{
	private SAPOrderEntryEntityPopulatorImpl testMe;

	/**
	 * Setup.
	 */
	@Before
	public void setUp()
	{
		Logger.getLogger(SAPOrderEntryEntityPopulatorImpl.class).setLevel(Level.DEBUG);
		testMe = new SAPOrderEntryEntityPopulatorImpl();
	}

	/**
	 * Test the null scenario.
	 *
	 * @throws SAPException if something bad happened.
	 */
	@Test
	public void testNull() throws SAPException
	{
		final SAPOrderEntryEntity result = testMe.populate(null);
		assertNull(result);
		List<SAPOrderEntryEntity> results = testMe.populateAll(null);
		assertNull(results);
		final OrderModel order = TestOrderGenerator.createTestOrder(0);
		results = testMe.populateAll(order);
		assertNull(results);
	}

	/**
	 * Test of the valid scenario.
	 *
	 * @throws SAPException if something bad happened.
	 */
	@Test
	public void testValid() throws SAPException
	{
		final OrderModel order = TestOrderGenerator.createTestOrder(2);
		final List<SAPOrderEntryEntity> results = testMe.populateAll(order);
		assertNotNull(results);
		assertEquals(2, results.size());
		final SAPOrderEntryEntity result1 = results.get(0);
		assertEquals("entry.code-1", result1.getOrderRequestPositionId());
		assertEquals(Long.valueOf(1).longValue(), result1.getOrderSize());
		assertEquals("CRM", result1.getProductOrigin());
		assertEquals("externalOfferId-1", result1.getProductId());
		assertEquals("productOwner-1", result1.getProductOwner());
		assertEquals("productClass-1", result1.getProductClass());
		assertEquals("productGroup-1", result1.getProductGroup());
		final SAPOrderEntryEntity result2 = results.get(1);
		assertEquals("entry.code-2", result2.getOrderRequestPositionId());
		assertEquals(Long.valueOf(1).longValue(), result2.getOrderSize());
		assertEquals("CRM", result2.getProductOrigin());
		assertEquals("externalOfferId-2", result2.getProductId());
		assertEquals("productOwner-2", result2.getProductOwner());
		assertEquals("productClass-2", result2.getProductClass());
		assertEquals("productGroup-2", result2.getProductGroup());
	}
}
