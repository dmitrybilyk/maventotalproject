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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.cgi.pacoshop.fulfilmentprocess.model.SAPAccount;
import com.cgi.pacoshop.fulfilmentprocess.populator.impl.SAPAccountPopulatorImpl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Represents the suite of tests related to the SAPAccountPopulatorImpl.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SAPAccountPopulatorImplTest
{

    private SAPAccountPopulatorImpl testMe;


    /**
     * Setup.
     */
    @Before
    public void setUp()
    {
        Logger.getLogger(SAPAccountPopulatorImpl.class).setLevel(Level.DEBUG);
        testMe = new SAPAccountPopulatorImpl();
    }

    /**
     * Test with null values.
     */
    @Test
    public void testNull()
    {
        final SAPAccount result = testMe.populate((PacoDebitPaymentInfoModel) null);
        assertNull(result);
    }

    /**
     * Test valid.
     */
    @Test
    public void testValid()
    {
        final DebitPaymentInfoModel debitPaymentInfoModel = new DebitPaymentInfoModel();
        debitPaymentInfoModel.setAccountNumber("accountNumber");
        debitPaymentInfoModel.setBank("bank");
        debitPaymentInfoModel.setBankIDNumber("bankNumber");
        debitPaymentInfoModel.setBaOwner("accountOwner");

        final SAPAccount result = testMe.populate(debitPaymentInfoModel);
        assertEquals("accountNumber", result.getAccountNumber());
        assertEquals("bank", result.getBankName());
        assertEquals("bankNumber", result.getBankCode());
        assertEquals("accountOwner", result.getAccountHolder());
    }


    /**
     * Test valid.
     */
    @Test
    public void testValidPacoDebitPaymentInfoModel()
    {
        final PacoDebitPaymentInfoModel pacoDebitPaymentInfoModel = new PacoDebitPaymentInfoModel();
        pacoDebitPaymentInfoModel.setAccountNumber("accountNumber");
        pacoDebitPaymentInfoModel.setFirstName("Firstname");
        pacoDebitPaymentInfoModel.setLastName("Lastname");
        pacoDebitPaymentInfoModel.setBankIdNumber("bankNumber");
        pacoDebitPaymentInfoModel.setBIC("bic");
        pacoDebitPaymentInfoModel.setIBAN("iban");

        final SAPAccount result = testMe.populate(pacoDebitPaymentInfoModel);
        assertEquals("accountNumber", result.getAccountNumber());
        assertEquals("bankNumber", result.getBankCode());
        assertEquals("bic", result.getSwiftBic());
        assertEquals("iban", result.getIban());
        assertEquals("Firstname Lastname", result.getAccountHolder());
    }

}
