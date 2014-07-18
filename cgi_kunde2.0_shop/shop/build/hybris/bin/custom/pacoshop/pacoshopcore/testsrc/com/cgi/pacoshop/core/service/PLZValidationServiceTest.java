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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.service.impl.PLZValidationServiceImpl;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for PLZValidationServiceImpl.
 * 
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @version 1.0v Jan 30 , 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 *
 */
public class PLZValidationServiceTest
{


	public static final String CONFIG_KEY = "plz.validation.rule.";

	public static final String DE = "de";
	public static final String AT = "at";

	public static final String AT_REGEXP = "[0-9]{4}";
	public static final String DE_REGEXP = "[0-9]{5}";

    private static ConfigurationService CONFIGURATIONSERVICE = mock(ConfigurationService.class);
    private static Configuration CONFIGURATION = mock(Configuration.class);

	private PLZValidationServiceImpl validationService;

	private final CheckoutFormDTO testCase1 = mock(CheckoutFormDTO.class);
	private final CheckoutFormDTO testCase2 = mock(CheckoutFormDTO.class);

    /**
     *
     * @throws Exception if Exception occurs
     */
	@Before
	public void setUp() throws Exception
	{

		when(CONFIGURATION.getString(CONFIG_KEY + DE)).thenReturn(DE_REGEXP);
		when(CONFIGURATION.getString(CONFIG_KEY + AT)).thenReturn(AT_REGEXP);

		when(CONFIGURATIONSERVICE.getConfiguration()).thenReturn(CONFIGURATION);

		validationService = new PLZValidationServiceImpl();
		validationService.setConfigurationService(CONFIGURATIONSERVICE);

		when(testCase1.getZip()).thenReturn("32805");
		when(testCase1.getCountry()).thenReturn("DE");

		when(testCase2.getZip()).thenReturn("test");
		when(testCase2.getCountry()).thenReturn("Ukraine");

	}


	private CheckoutFormDTO createCheckoutFormDTO(final String plz, final String country)
	{
		final CheckoutFormDTO form = new CheckoutFormDTO();
		form.setZip(plz);
		form.setCountry(country);
		return form;
	}

    /**
     *
     * @throws Exception if exception occurs
     */
	@Test
	public void testCase1ValidData() throws Exception
	{
		// Test for two different countries
		assertTrue(validationService.validate(createCheckoutFormDTO("73839", "DE")));
		assertTrue(validationService.validate(createCheckoutFormDTO("8439", "AT")));

		// Now change the rule for one country and verify that the new validation rule is applied
		when(CONFIGURATION.getString(CONFIG_KEY + DE)).thenReturn("[0-9]{3}");
		assertTrue(validationService.validate(createCheckoutFormDTO("321", "DE")));

		// If there is no validation rule for a country, the validation always returns true
		assertTrue(validationService.validate(createCheckoutFormDTO("7380", "CH")));
		assertTrue(validationService.validate(createCheckoutFormDTO("ABC876", "CH")));
	}

    /**
     *
     * @throws Exception if exception occurs
     */
	@Test
	public void testCase1inValidData() throws Exception
	{
		assertFalse(validationService.validate(createCheckoutFormDTO("738", "DE")));
		assertFalse(validationService.validate(createCheckoutFormDTO("7384", "DE")));
		assertFalse(validationService.validate(createCheckoutFormDTO("84395", "AT")));
	}


}
