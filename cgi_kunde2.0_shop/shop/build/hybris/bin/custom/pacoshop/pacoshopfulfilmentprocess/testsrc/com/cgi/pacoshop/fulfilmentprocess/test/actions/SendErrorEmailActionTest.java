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
package com.cgi.pacoshop.fulfilmentprocess.test.actions;


import com.cgi.pacoshop.fulfilmentprocess.actions.order.SendErrorEmailAction;
import com.cgi.pacoshop.fulfilmentprocess.events.OrderErrorEvent;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.event.EventService;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This is test for SendErrorEmailAction
 *
 * @module build
 * @version 1.0v Mar 24, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SendErrorEmailActionTest
{

	private SendErrorEmailAction testMe;

	@Mock
	private EventService eventService;

	/**
	 * Setup.
	 */
	@Before
	public void setup()
	{
		testMe = new SendErrorEmailAction();
		testMe.setEventService(eventService);
	}


	/**
	 * * Valid test case.
	 * @throws Exception like Exception.
	 */
	@Test
	public void testValid() throws Exception
	{
		OrderProcessModel orderProcessModel = mock(OrderProcessModel.class);
		when(orderProcessModel.getCode()).thenReturn("TestCode");
		testMe.executeAction(orderProcessModel);

		verify(eventService).publishEvent(new OrderErrorEvent(any(OrderProcessModel.class)));

		assertFalse(false);
	}

}
