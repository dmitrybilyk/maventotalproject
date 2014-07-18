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
package com.cgi.pacoshop.fulfilmentprocess.test.actions;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.fulfilmentprocess.actions.order.SubmitToEmailFulfillmentAction;
import com.cgi.pacoshop.fulfilmentprocess.events.EmailFulfillmentEvent;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.task.RetryLaterException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * This is the test for SubmitToEmailFulfillmentAction.
 * 
 * @module pacoshopfulfilmentprocess
 * @version 1.0v 24.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SubmitToEmailFulfillmentActionTest
{
    private SubmitToEmailFulfillmentAction action;

    @Mock
    private EventService eventService;

    /**
     * Setup method.
     * 
     * @throws java.lang.Exception
     *             the {@link Exception}.
     */
    @Before
    public void setUp() throws Exception
    {
        action = new SubmitToEmailFulfillmentAction();
        action.setEventService(eventService);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             {@link Exception}.
     * @throws RetryLaterException
     *             the {@link RetryLaterException}.
     */
    @Test
    public void test() throws RetryLaterException, Exception
    {
        final OrderProcessModel orderProcessModel = mock(OrderProcessModel.class);
        when(orderProcessModel.getCode()).thenReturn("testOrderProcess");
        action.executeAction(orderProcessModel);

        verify(eventService).publishEvent(new EmailFulfillmentEvent(any(OrderProcessModel.class)));
    }

}
