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


import org.apache.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAOrderPopulatorException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;
import com.cgi.pacoshop.fulfilmentprocess.populator.impl.SubmitCAEntryPopulatorImpl;
import com.cgi.pacoshop.fulfilmentprocess.test.util.TestOrderGenerator;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateMidnight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



/**
 * Test the submitCAEntryPopulator.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module hybris - pacoshopcore
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SubmitCAEntryPopulatorImplTest
{
	private SubmitCAEntryPopulatorImpl testMe;


	/**
	 * Setup.
	 */
	@Before
	public void setUp()
	{
		testMe = new SubmitCAEntryPopulatorImpl();

	}


	/**
	 * Test with null.
	 */
	@Test
	public void testPopulateOrderEntryWithNull()
	{
		try
		{
			testMe.populateOrderEntry(null, null);
			assertFalse(true);
		}
		catch (final CAOrderPopulatorException e)
		{
			assertFalse(false);
		}

		try
		{
			final OrderModel order = new OrderModel();
			testMe.populateOrderEntry(null, order);
			assertFalse(true);
		}
		catch (final CAOrderPopulatorException e)
		{
			assertFalse(false);
		}

		try
		{
			final AbstractOrderEntryModel entry = new AbstractOrderEntryModel();
			testMe.populateOrderEntry(entry, null);
			assertFalse(true);
		}
		catch (final CAOrderPopulatorException e)
		{
			assertFalse(false);
		}

	}

	/**
	 * Test with null.
	 */
	@Test
	public void testPopulateOrderEntryWithUserNull()
	{
		final OrderModel order = new OrderModel();
		final AbstractOrderEntryModel entry = new AbstractOrderEntryModel();
		final List<AbstractOrderEntryModel> entries = new ArrayList<>();
		entries.add(entry);
		order.setEntries(entries);
		try
		{
			testMe.populateOrderEntry(entry, order);
			assertFalse(true);
		}
		catch (final CAOrderPopulatorException e)
		{
			assertFalse(false);
		}
	}

	/**
	 * Test with product null.
	 */
	@Test
	public void testPopulateOrderEntryWithProductNull()
	{
		final OrderModel order = new OrderModel();
		final AbstractOrderEntryModel entry = new AbstractOrderEntryModel();
		final UserModel user = new UserModel();
        final List<AbstractOrderEntryModel> entries = new ArrayList<>();
        entries.add(entry);
        order.setEntries(entries);
        order.setUser(user);
        try
        {
            testMe.populateOrderEntry(entry, order);
            assertFalse(true);
        }
        catch (final CAOrderPopulatorException e)
        {
            assertFalse(false);
        }
    }

    /**
     * Test entry with success.
     */
    @Test
    public void testPopulateOrderEntryWithSuccess()
    {
        final OrderModel order = TestOrderGenerator.createTestOrder(1);
        final AbstractOrderEntryModel entry = order.getEntries().get(0);

        CAOrderInterfaceEntry result = null;
        try
        {
			  result = testMe.populateOrderEntry(entry, order);
           assertFalse(false);
        }
        catch (final CAOrderPopulatorException e)
        {
            assertFalse(true);
        }
        assertNotNull(result);
        assertEquals("uid:user-1", result.getClientCustomerId());
        assertEquals("uid:user-1", result.getConsigneeCustomerId());
        assertEquals(null, result.getInvoiceRecipientCustomerId());
        assertEquals("externalProductId-1", result.getProductId());
        assertEquals("contentPlatformId-1", result.getContentPlatformId());
        assertEquals("contentId-1", result.getContentId());
        assertEquals("contentTitle-1", result.getContentTitle());
        assertEquals(null, result.getContentUrl());
        assertEquals("order.code-1", result.getOrderRequestId());
        assertEquals("entry.code-1", result.getOrderRequestPositionId());
        final DateMidnight oneDate = new DateMidnight(1980, 8, 18);
        assertTrue(oneDate.toDate().equals(result.getOrderDate()));
        assertEquals(Long.valueOf(1), result.getOrderSize());
        assertEquals(null, result.getValidFrom());

    }

    /**
     * Test all order entries null.
     */
    @Test
    public void testPopulateAllOrderEntriesNull()
    {
        try
        {
            testMe.populateAllOrderEntries(null);
            assertTrue(false);
        }
        catch (final CAOrderPopulatorException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Test all order entries with no entries.
     */
    @Test
    public void testPopulateAllOrderEntriesWithNoEntries()
    {
        final OrderModel order = TestOrderGenerator.createTestOrder(0);
        List<CAOrderInterfaceEntry> results = null;
        try
        {
            results = testMe.populateAllOrderEntries(order);
            assertTrue(true);
        }
        catch (final CAOrderPopulatorException e)
        {
            assertTrue(false);
        }

        assertNotNull(results);

        assertTrue(results.isEmpty());

        order.setEntries(null);
        try
        {
            results = testMe.populateAllOrderEntries(order);
            assertTrue(true);
        }
        catch (final CAOrderPopulatorException e)
        {
            assertTrue(false);
        }

        assertNotNull(results);

        assertTrue(results.isEmpty());

    }

    /**
     * Test populate all order entries success.
     */
    @SuppressWarnings("null")
    @Test
    public void testPopulateAllOrderEntriesSuccess()
    {
        final OrderModel order = TestOrderGenerator.createTestOrder(2);
        List<CAOrderInterfaceEntry> results = null;
        try
        {
            results = testMe.populateAllOrderEntries(order);
            assertTrue(true);
        }
        catch (final CAOrderPopulatorException e)
        {
            assertTrue(false);
        }

        assertNotNull(results);
        assertTrue(results.size() == 2);

    }

}
