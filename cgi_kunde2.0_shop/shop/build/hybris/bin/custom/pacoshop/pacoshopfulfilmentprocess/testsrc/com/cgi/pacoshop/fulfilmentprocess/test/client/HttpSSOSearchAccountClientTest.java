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
package com.cgi.pacoshop.fulfilmentprocess.test.client;


import com.cgi.pacoshop.fulfilmentprocess.client.HttpSSOSearchAccountClient;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.converter.JsonConverter;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.JSONConversionException;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.SSOException;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountResponse;
import com.cgi.pacoshop.fulfilmentprocess.populator.SSOAccountPopulator;
import com.cgi.pacoshop.fulfilmentprocess.test.util.TestOrderGenerator;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * HttpSSOSearchAccount Client test case.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 09, 2014
 * @module build
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class HttpSSOSearchAccountClientTest
{

	private static final int TIMEOUT = 1000;


	private HttpSSOSearchAccountClient testMe;

	@Mock
	private SSOAccountPopulator ssoAccountPopulator;
	@Mock
	private ConfigurationService configurationService;
	@Mock
	private JsonConverter jsonConverter;
	@Mock
	private RestTemplate restTemplate;


	/**
	 * Setup.
	 *
	 * @throws SSOException            if something bad happen.
	 * @throws JSONConversionException if something bad happen.
	 */
	@Before
	public void setup() throws JSONConversionException, SSOException
	{
		final Configuration configuration = new BaseConfiguration();
		configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_URL,
				"http://localhost:8080/SSO/getAccountId");
		configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_CONNECTION_TIMEOUT, TIMEOUT);

		when(configurationService.getConfiguration()).thenReturn(configuration);


		final SSOAccountRequest ssoAccountRequest = new SSOAccountRequest();
		ssoAccountRequest.setEmail("test@email.com");
		when(ssoAccountPopulator.populate(any(OrderModel.class))).thenReturn(ssoAccountRequest);

		final SSOAccountResponse ssoAccountResponse = new SSOAccountResponse();
		ssoAccountResponse.setAccountId("ssoTestUserID");
		when(restTemplate.getForObject(any(URI.class), eq(SSOAccountResponse.class))).thenReturn(ssoAccountResponse);

		testMe = new HttpSSOSearchAccountClient();
		testMe.setSsoAccountPopulator(ssoAccountPopulator);
	}


	/**
	 * Valid test case.
	 *
	 * @throws IOException if something went bad.
	 */
	@Test
	public void testValid() throws IOException
	{
		final OrderModel order = TestOrderGenerator.createTestOrderWithDeliveryAddress("");

		testMe.send(order);
		assertFalse(false);
	}


	/**
	 * Test a empty email.
	 *
	 * @throws IOException if something went bad.
	 */
	@Test
	public void testServerNotResponse() throws IOException
	{
		when(restTemplate.getForObject(any(URI.class), eq(SSOAccountResponse.class))).thenReturn(null);
		final OrderModel order = TestOrderGenerator.createTestOrderWithDeliveryAddress("test@email.com");
		testMe.send(order);
		assertFalse(false);
	}


	/**
	 * Test exception case.
	 *
	 * @throws Exception if something bad happen.
	 */

	@Test
	public void testException() throws Exception
	{
		final RestTemplate restTemplate = mock(RestTemplate.class);
		final OrderModel order = TestOrderGenerator.createTestOrderWithDeliveryAddress("");

		Mockito.reset(restTemplate);
		when(restTemplate.getForObject(any(URI.class), eq(SSOAccountResponse.class))).thenThrow(
				new RestClientException("RestClientException from Unit test"));
		testMe.send(order);
		assertFalse(false);

	}
}
