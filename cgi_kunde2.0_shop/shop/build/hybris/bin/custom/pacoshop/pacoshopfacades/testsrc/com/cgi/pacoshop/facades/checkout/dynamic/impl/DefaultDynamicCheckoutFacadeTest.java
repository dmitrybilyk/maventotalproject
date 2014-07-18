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
package com.cgi.pacoshop.facades.checkout.dynamic.impl;


import com.cgi.pacoshop.core.checkout.AbstractDynamicCheckoutFrameworkMockFactory;
import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.FormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl.DefaultCheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.collections.CollectionUtils;
import static org.junit.Assert.assertTrue;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.validation.BindingResult;


/**
 * DefaultDynamicCheckoutFacadeTest - junit test for dynamic checkout facade.
 *
 *
 * @module hybris - pacoshopfacades
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
@UnitTest
public class DefaultDynamicCheckoutFacadeTest
{

	private final DefaultDynamicCheckoutFacade facade = new DefaultDynamicCheckoutFacade();
	private CheckoutStepService mockCheckoutStepService;
	private CartService         mockCartService;
    private CommerceCartService mockCommerceCartService;
    private ConfigurationService mockConfigurationService;

    /**
	 * Setup.
	 *
	 */
	@Before
	public void setUp()
	{
		mockCheckoutStepService = mock(CheckoutStepService.class);
		mockCartService = mock(CartService.class);
        mockCommerceCartService = mock(CommerceCartService.class);
        mockConfigurationService = mock(ConfigurationService.class);

        Configuration mockConfiguration = mock(Configuration.class);
        when(mockConfigurationService.getConfiguration()).thenReturn(mockConfiguration);

		facade.setCartService(mockCartService);
		facade.setCheckoutStepService(mockCheckoutStepService);
        facade.setCommerceCartService(mockCommerceCartService);
        facade.setConfigurationService(mockConfigurationService);
	}

	/**
	 * Check that a corresponding method of a service is called.
	 *
	 * @throws Exception
	 *            java.lang.Exception
	 */
	@Test
	public void testGetNeededSteps() throws Exception
	{
		facade.getNeededSteps();
		verify(mockCheckoutStepService).getNeededSteps(mockCartService.getSessionCart());

	}

	/**
	 * Check that a corresponding method of a service is called with the same list of checkout steps.
	 *
	 * @throws Exception
	 *            java.lang.Exception
	 */
	@Test
	public void testGetNextDisplayedStep() throws Exception
	{
		final List<CheckoutStep> listOfMockedSteps = AbstractDynamicCheckoutFrameworkMockFactory
				  .createMockStepsNotCompletedDisplayed();
		facade.getNextDisplayedStep(listOfMockedSteps);
		final ArgumentCaptor<List> arg = ArgumentCaptor.forClass(List.class);
		verify(mockCheckoutStepService).getNextDisplayedStep(arg.capture());
		assertTrue(CollectionUtils.isEqualCollection(listOfMockedSteps, arg.getValue()));

	}


	/**
	 * Checks if the populateFormFromCart is invoked in the appropriate FormElementGroups.
	 *
	 * @throws Exception
	 *            java.lang.Exception
	 */
	@Test
	public void testPopulateFormFromCart() throws Exception
	{
		final CheckoutStep checkoutStepMock = Mockito.mock(CheckoutStep.class);
		final FormDTO formDTOMock = Mockito.mock(FormDTO.class);
		final CartModel cartModelMock = Mockito.mock(CartModel.class);
		final CartService cartServiceMock = Mockito.mock(CartService.class);
		final FormElementGroup displayableFormElementGroupMock = AbstractDynamicCheckoutFrameworkMockFactory
				  .createDisplayedElementGroup();
		final FormElementGroup notDisplayableFormElementGroupMock = AbstractDynamicCheckoutFrameworkMockFactory
				  .createNotDisplayedElementGroup();

		final List<FormElementGroup> formElementGroupsMock = new ArrayList<>();
		formElementGroupsMock.add(displayableFormElementGroupMock);
		formElementGroupsMock.add(notDisplayableFormElementGroupMock);

		when(checkoutStepMock.getElements()).thenReturn(formElementGroupsMock);
		when(checkoutStepMock.getForm()).thenReturn(formDTOMock);
		when(cartServiceMock.getSessionCart()).thenReturn(cartModelMock);

		facade.setCartService(cartServiceMock);

		mockCheckoutStepService.populateFormFromCart(checkoutStepMock);

		Mockito.verify(notDisplayableFormElementGroupMock, Mockito.never()).populateFormFromCart(Matchers.eq(formDTOMock),
																															  Matchers.eq(cartModelMock));
	}


	/**
	 * Validation form test.
	 *
	 * @throws Exception
	 *            java.lang.Exception
	 */
	@Test
	public void testValidateForm() throws Exception
	{
		final CheckoutStep stepThatShouldPassValidation = AbstractDynamicCheckoutFrameworkMockFactory
				  .createStepThatWillPassValidation();
		assertTrue(facade.validateForm(mock(FormDTO.class), stepThatShouldPassValidation, mock(BindingResult.class)));

		final CheckoutStep stepThatShouldNotPassValidation = AbstractDynamicCheckoutFrameworkMockFactory
				  .createStepThatWillNotPassValidation();
		assertFalse(facade.validateForm(mock(FormDTO.class), stepThatShouldNotPassValidation, mock(BindingResult.class)));
	}

	/**
	 * Save form test.
	 *
	 * @throws Exception
	 *            java.lang.Exception
	 */
	@Test
	public void testSaveFormToCart() throws Exception
	{
		final DefaultCheckoutStep checkoutStep = new DefaultCheckoutStep();

		final List<FormElementGroup> formElementGroupMocks = new ArrayList<>();
		formElementGroupMocks.add(AbstractDynamicCheckoutFrameworkMockFactory.createDisplayedElementGroup());
		formElementGroupMocks.add(AbstractDynamicCheckoutFrameworkMockFactory.createDisplayedElementGroup());
		formElementGroupMocks.add(AbstractDynamicCheckoutFrameworkMockFactory.createNotDisplayedElementGroup());

		checkoutStep.setFormElementGroups(formElementGroupMocks);

		final FormDTO formDTOMock = Mockito.mock(CheckoutFormDTO.class);
		final CartModel cartModelMock = Mockito.mock(CartModel.class);
		final HashMap<String, CheckoutStep> formsAndStepsAssociations = Mockito.mock(HashMap.class);
		final CartService cartServiceMock = Mockito.mock(CartService.class);

		when(cartServiceMock.getSessionCart()).thenReturn(cartModelMock);
		when(formsAndStepsAssociations.get(any())).thenReturn(checkoutStep);


		when(mockCheckoutStepService.getCheckoutStep(any(FormDTO.class))).thenReturn(checkoutStep);


		facade.setCartService(cartServiceMock);

		facade.saveFormToCart(formDTOMock);

		for (final FormElementGroup formElementGroup : checkoutStep.getElements())
		{
			if (formElementGroup.isDisplayed(Mockito.mock(CartModel.class)))
			{
				Mockito.verify(formElementGroup).saveFormToCart(Matchers.eq(formDTOMock), Matchers.eq(cartModelMock));
			}
			else
			{
				Mockito.verify(formElementGroup, Mockito.never())
						  .saveFormToCart(Matchers.eq(formDTOMock), Matchers.eq(cartModelMock));
			}
		}
	}

	/**
	 *
	 * Checks if fetching data from FEGs works well.
	 */
	@Test
	public void populatePageModelData()
	{
		final DefaultCheckoutStep checkoutStep = new DefaultCheckoutStep();
		final String valueForDisplayedFEG = "value";
		final String valueForNotDisplayedFEG = "differentValue";
		final List<FormElementGroup> formElementGroupMocks = new ArrayList<>();
		final FormElementGroup displayedFormElementGroup =
				  AbstractDynamicCheckoutFrameworkMockFactory.createDisplayedElementGroup();
		final FormElementGroup notDisplayedFormElementGroup =
				  AbstractDynamicCheckoutFrameworkMockFactory.createNotDisplayedElementGroup();

		final CartModel cartModelMock = Mockito.mock(CartModel.class);
		formElementGroupMocks.add(displayedFormElementGroup);
		formElementGroupMocks.add(notDisplayedFormElementGroup);
		checkoutStep.setFormElementGroups(formElementGroupMocks);

		final Map<String, Object> mapForDisplayedFEG = new HashMap<>();
		mapForDisplayedFEG.put(valueForDisplayedFEG, true);

		final Map<String, Object> mapForNotDisplayedFEG = new HashMap<>();
		mapForNotDisplayedFEG.put(valueForNotDisplayedFEG, false);

		Mockito.when(displayedFormElementGroup.populatePageModelData(Mockito.any(CartModel.class))).thenReturn(mapForDisplayedFEG);
		Mockito.when(notDisplayedFormElementGroup.populatePageModelData(Mockito.any(CartModel.class))).thenReturn(
				  mapForNotDisplayedFEG);


        CheckoutStep mockChekoutStep1 = mock(CheckoutStep.class);
        CheckoutStep mockChekoutStep2 = mock(CheckoutStep.class);

        when(mockCartService.getSessionCart()).thenReturn(cartModelMock);
        when(mockCheckoutStepService.getNeededSteps(mockCartService.getSessionCart())).
                thenReturn(Arrays.asList(mockChekoutStep1, mockChekoutStep2));

        List<CheckoutStep> neededSteps = mockCheckoutStepService.getNeededSteps(mockCartService.getSessionCart());
        final Map<String, Object> pageModelValues = facade.populatePageModelData(neededSteps, checkoutStep);

		Mockito.verify(displayedFormElementGroup).populatePageModelData(cartModelMock);
		Mockito.verify(notDisplayedFormElementGroup, Mockito.never()).populatePageModelData(cartModelMock);

		assertEquals(pageModelValues.containsKey(valueForDisplayedFEG), true);
		assertEquals(pageModelValues.containsKey(valueForNotDisplayedFEG), false);
	}

}
