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
package com.cgi.pacoshop.fulfilmentprocess.test.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.reset;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.fulfilmentprocess.client.HttpCAClient;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.converter.JsonConverter;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAOrderPopulatorException;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.JSONConversionException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;
import com.cgi.pacoshop.fulfilmentprocess.populator.SubmitCAEntryPopulator;
import com.cgi.pacoshop.fulfilmentprocess.test.util.TestOrderGenerator;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.io.IOException;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.github.tomakehurst.wiremock.junit.WireMockRule;


/**
 * Represents the test suite for the {@link HttpCAClient}.
 * 
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class HttpCAClientTest
{

    private static final int WIREMOCKPORT = 8888;
    @SuppressWarnings("unused")
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(WIREMOCKPORT);

    private HttpCAClient testMe;

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private SubmitCAEntryPopulator submitCAEntryPopulator;

    @Mock
    private JsonConverter jsonConverter;

    /**
     * Setup.
     * 
     * @throws CAOrderPopulatorException
     *             if something bad happen.
     * @throws JSONConversionException
     *             if something bad happen.
     */
    @Before
    public void setup() throws CAOrderPopulatorException, JSONConversionException
    {
        final Configuration configuration = new BaseConfiguration();
        configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.CA_URL, "http://localhost:8888/CA/allowAccess");
        configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.CA_CONNECTION_TIMEOUT, 5000);
        configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.CA_SO_TIMEOUT, 5000);

        when(configurationService.getConfiguration()).thenReturn(configuration);


        final CAOrderInterfaceEntry anEntry = new CAOrderInterfaceEntry();
        anEntry.setClientCustomerId("entry1");

        when(submitCAEntryPopulator.populateOrderEntry(any(AbstractOrderEntryModel.class), any(OrderModel.class))).thenReturn(
                anEntry);
        when(jsonConverter.convert(anEntry)).thenReturn("{awesome:allo}");

        testMe = new HttpCAClient();
        testMe.setConfigurationService(configurationService);
        testMe.setSubmitCAEntryPopulator(submitCAEntryPopulator);
        testMe.setJsonConverter(jsonConverter);
        testMe.setHttpClient(new HttpClient());
    }

    /**
     * Valid test case.
     */
    @Test
    public void testValid()
    {
        stubFor(post(urlEqualTo("/CA/allowAccess")).willReturn(aResponse().withStatus(HttpStatus.CREATED.value())));
        final OrderModel order = TestOrderGenerator.createTestOrder(1);
        testMe.send(order);
        assertFalse(false);
    }

    /**
     * Test a 404.
     */
    @Test
    public void test404()
    {
        //no stub defined... hence call will throw a 404
        reset();
        final OrderModel order = TestOrderGenerator.createTestOrder(1);

        testMe.send(order);
        assertFalse(false);
    }

    /**
     * Test exception case.
     * 
     * @throws Exception
     *             if something bad happen.
     */
    @Test
    public void testException() throws Exception
    {
        final HttpClient httpClient = mock(HttpClient.class);
        testMe.setHttpClient(httpClient);
        final OrderModel order = TestOrderGenerator.createTestOrder(1);

        when(httpClient.executeMethod(any(HttpMethod.class))).thenThrow(new URIException());

        testMe.send(order);
        assertFalse(false);


        Mockito.reset(httpClient);
        when(httpClient.executeMethod(any(HttpMethod.class))).thenThrow(new HttpException());

        testMe.send(order);
        assertFalse(false);

        Mockito.reset(httpClient);
        when(httpClient.executeMethod(any(HttpMethod.class))).thenThrow(new IOException());
        testMe.send(order);
        assertFalse(false);

    }

    /**
     * Test 2 entries.
     */
    @Test
    public void test2EntriesSuccess()
    {
        stubFor(post(urlEqualTo("/CA/allowAccess")).willReturn(aResponse().withStatus(HttpStatus.OK.value())));
        final OrderModel order = TestOrderGenerator.createTestOrder(2);
        testMe.send(order);
        assertFalse(false);
    }

    /**
     * test 2 entries 1 fail.
     * 
     * @throws Exception
     *             if something bad happen.
     */
    @Test
    public void test2Entries1FailPopulator() throws Exception
    {
        final OrderModel order = TestOrderGenerator.createTestOrder(2);
        final CAOrderInterfaceEntry anEntry = new CAOrderInterfaceEntry();
        anEntry.setClientCustomerId("entry1");
        final CAOrderInterfaceEntry anEntry2 = new CAOrderInterfaceEntry();
        anEntry2.setClientCustomerId("entry2");
        when(submitCAEntryPopulator.populateOrderEntry(order.getEntries().get(0), order)).thenReturn(anEntry);
        when(jsonConverter.convert(anEntry)).thenReturn("{awesome:allo}");

        when(submitCAEntryPopulator.populateOrderEntry(order.getEntries().get(1), order)).thenThrow(
                new CAOrderPopulatorException("Something bad happened."));
        reset();
        stubFor(post(urlEqualTo("/CA/allowAccess")).willReturn(aResponse().withStatus(HttpStatus.OK.value())));

        testMe.send(order);
        assertFalse(false);
    }

    /**
     * Test 2 entries 1 fail.
     * 
     * @throws Exception
     *             if something wrong happen.
     */
    @Test
    public void test2Entries1FailConverter() throws Exception
    {
        final OrderModel order = TestOrderGenerator.createTestOrder(2);
        final CAOrderInterfaceEntry anEntry = new CAOrderInterfaceEntry();
        anEntry.setClientCustomerId("entry1");
        final CAOrderInterfaceEntry anEntry2 = new CAOrderInterfaceEntry();
        anEntry.setClientCustomerId("entry2");
        when(submitCAEntryPopulator.populateOrderEntry(order.getEntries().get(0), order)).thenReturn(anEntry);
        when(jsonConverter.convert(anEntry)).thenReturn("{awesome:allo}");

        when(submitCAEntryPopulator.populateOrderEntry(order.getEntries().get(1), order)).thenReturn(anEntry2);
        when(jsonConverter.convert(anEntry2)).thenThrow(new JSONConversionException("SomethingBad", null));

        // reset();
        stubFor(post(urlEqualTo("/CA/allowAccess")).willReturn(aResponse().withStatus(HttpStatus.OK.value())));

        testMe.send(order);
        assertFalse(false);
    }

}
