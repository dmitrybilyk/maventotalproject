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

import com.cgi.pacoshop.core.model.Title2Model;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.configuration.Configuration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.fulfilmentprocess.model.SAPAddress;
import com.cgi.pacoshop.fulfilmentprocess.populator.impl.SAPAddressPopulatorImpl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.TitleModel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Represents the test case for the SAPAddressPopulatorImpl.
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
public class SAPAddressPopulatorImplTest
{
    private SAPAddressPopulatorImpl testMe;

    /**
     * Setting up.
     */
    @Before
    public void setUp()
    {
        testMe = new SAPAddressPopulatorImpl();
        Logger.getLogger(SAPAddressPopulatorImpl.class).setLevel(Level.DEBUG);
		  ConfigurationService configurationService = mock(ConfigurationService.class);
		  Configuration configuration = mock(Configuration.class);
		  when(configurationService.getConfiguration()).thenReturn(configuration);
		  when(configuration.getString("servicebus.date.format")).thenReturn("yyyy-MM-dd");
		  testMe.setConfigurationService(configurationService);
    }


    /**
     * Test the null case scenario.
     */
    @Test
    public void testNull()
    {
        SAPAddress result;
        result = testMe.populate(null);
        assertNull(result);
    }


    /**
     * Test for the valid scenario.
     */
    @Test
    public void testValid()
    {
        final AddressModel address = new AddressModel();
        final TitleModel title = mock(TitleModel.class);
        when(title.getName()).thenReturn("Dr. Phd. Master. Awesome. Godlike.");
        final CountryModel country = mock(CountryModel.class);
        final DateMidnight today = new DateMidnight("2017-11-11");

        when(country.getName()).thenReturn("The great independant nation of CANADA!");
        address.setTitle(title);
		  final Title2Model title2 = mock(Title2Model.class);
		  when(title2.getCode()).thenReturn("Title2");
        address.setTitle2(title2);
        address.setFirstname("firstname");
        address.setLastname("lastname");
        address.setLine3("suffix");
        address.setLine2("streetNumber");
        address.setLine1("street");
        address.setPostalcode("postalCode");
        address.setTown("city");
        address.setCountry(country);
        address.setEmail("awesome@unbelivable.me");
        address.setDateOfBirth(today.toDate());

        final SAPAddress result = testMe.populate(address);
        assertEquals(address.getTitle().getName(), result.getSalutation());
        assertEquals(address.getTitle2().getCode(), result.getTitle());
        assertEquals(address.getFirstname(), result.getFirstname());
        assertEquals(address.getLastname(), result.getLastname());
        assertEquals(address.getLine3(), result.getAddressSuffix());
        assertEquals(address.getLine2(), result.getStreetNumber());
        assertEquals(address.getLine1(), result.getStreet());
        assertEquals(address.getPostalcode(), result.getPostalCode());
        assertEquals(address.getTown(), result.getCity());
        assertEquals(address.getCountry().getName(), result.getCountry());
        assertEquals(address.getEmail(), result.getEmail());

        DateMidnight date = new DateMidnight(result.getBirthday());
        assertEquals(today.toDate(), date.toDate());
    }


}
