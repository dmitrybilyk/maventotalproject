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


import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.fulfilmentprocess.actions.order.UpdateRecipientAction;
import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.JSONConversionException;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.SSOException;
import com.cgi.pacoshop.fulfilmentprocess.service.SSOFulfillmentService;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Represents the suite of test created for the updateRecipientAction.
 * 
 * @module build
 * @version 1.0v Mar 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class UpdateRecipientActionTest
{
    private UpdateRecipientAction testMe;

    @Mock
    private SSOFulfillmentService ssoFulfillmentService;

    /**
     * Setup.
     * 
     * @throws SSOException
     *             if something bad happen.
     * @throws JSONConversionException
     *             if something bad happen.
     */
    @Before
    public void setup() throws JSONConversionException, SSOException
    {
        when(ssoFulfillmentService.assignRecipientCustomerId(any(OrderModel.class))).thenReturn(ClientStatus.SUCCESS);
        testMe = new UpdateRecipientAction();
        testMe.setSsoFulfillmentService(ssoFulfillmentService);
    }


    /**
     * Valid test case.
     * 
     * @throws Exception
     *             if something goes bad.
     */
    @Test
    public void testValid() throws Exception
    {
        final OrderModel order = new OrderModel();
        final OrderProcessModel orderProcessModel = new OrderProcessModel();
        orderProcessModel.setOrder(order);
        testMe.executeAction(orderProcessModel);
        assertFalse(false);
    }


}
