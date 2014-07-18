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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl;

import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.FormElementGroup;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.EmployeeModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for @link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl.DefaultCheckoutStepService.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Dec 17, 2013
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
public class DefaultCheckoutStepServiceTest
{
	private DefaultCheckoutStepService defaultCheckoutStepService;

	/**
	 * Init test cases.
	 */
	@Before
	public void setUp()
	{
		defaultCheckoutStepService = new DefaultCheckoutStepService();
	}

	/**
	 * Tests that if FEG.isPrefilled() - true then. FEG.prefillCartFromCustomerProfile() is never invoked
	 */
	@Test
	public void testCase1PrefillCartFromCustomerProfileTest()
	{
		List<CheckoutStep> checkoutStepsMocks = new ArrayList<CheckoutStep>();
		CheckoutStep checkoutStepMock = Mockito.mock(CheckoutStep.class);
		checkoutStepsMocks.add(checkoutStepMock);

		List<FormElementGroup> formElementGroupsMocks = new ArrayList<FormElementGroup>();
		FormElementGroup formElementGroupMock = Mockito.mock(FormElementGroup.class);
		CartModel cartModelMock = Mockito.mock(CartModel.class);
		CustomerModel customerProfileMock = Mockito.mock(CustomerModel.class);

		Mockito.when(cartModelMock.getUser()).thenReturn(customerProfileMock);
		Mockito.when(formElementGroupMock.isPrefilled(cartModelMock)).thenReturn(true);
		Mockito.when(formElementGroupMock.isDisplayed(cartModelMock)).thenReturn(true);

		formElementGroupsMocks.add(formElementGroupMock);

		Mockito.when(checkoutStepMock.getElements()).thenReturn(formElementGroupsMocks);

		defaultCheckoutStepService.setCheckoutSteps(checkoutStepsMocks);
		defaultCheckoutStepService.getNeededSteps(cartModelMock);

		Mockito.verify(formElementGroupMock, Mockito.never()).prefillCartFromCustomerProfile(cartModelMock, customerProfileMock);

	}

	/**
	 * Here is line 1. Tests that if cart.getUser() returns UserModel that is not a CustomerModel instance then
	 * FEG.prefillCartFromCustomerProfile() is never invoked
	 */
	@Test
	public void testCase2PrefillCartFromCustomerProfileTest()
	{
		List<CheckoutStep> checkoutStepsMocks = new ArrayList<CheckoutStep>();
		CheckoutStep checkoutStepMock = Mockito.mock(CheckoutStep.class);
		checkoutStepsMocks.add(checkoutStepMock);

		List<FormElementGroup> formElementGroupsMocks = new ArrayList<FormElementGroup>();
		FormElementGroup formElementGroupMock = Mockito.mock(FormElementGroup.class);
		CartModel cartModelMock = Mockito.mock(CartModel.class);
		EmployeeModel customerProfileMock = Mockito.mock(EmployeeModel.class);

		Mockito.when(cartModelMock.getUser()).thenReturn(customerProfileMock);

		Mockito.when(formElementGroupMock.isPrefilled(cartModelMock)).thenReturn(false);
		Mockito.when(formElementGroupMock.isDisplayed(cartModelMock)).thenReturn(true);

		formElementGroupsMocks.add(formElementGroupMock);

		Mockito.when(checkoutStepMock.getElements()).thenReturn(formElementGroupsMocks);

		defaultCheckoutStepService.setCheckoutSteps(checkoutStepsMocks);
		defaultCheckoutStepService.getNeededSteps(cartModelMock);

		Mockito.verify(formElementGroupMock, Mockito.never()).
				prefillCartFromCustomerProfile(Mockito.any(CartModel.class), Mockito.any(CustomerModel.class));
	}

	/**
	 * Here is line 1. Tests that if FEG.isPrefilled() - false and cart.getUser() returns a CustomerModel instance then
	 * FEG.prefillCartFromCustomerProfile() is invoked
	 */
	@Test
	public void testCase3PrefillCartFromCustomerProfileTest()
	{
		List<CheckoutStep> checkoutStepsMocks = new ArrayList<CheckoutStep>();
		CheckoutStep checkoutStepMock = Mockito.mock(CheckoutStep.class);
		checkoutStepsMocks.add(checkoutStepMock);

		List<FormElementGroup> formElementGroupsMocks = new ArrayList<FormElementGroup>();
		FormElementGroup formElementGroupMock = Mockito.mock(FormElementGroup.class);
		CartModel cartModelMock = Mockito.mock(CartModel.class);
		ShopConfigurationService shopConfigurationServiceMock = Mockito.mock(ShopConfigurationService.class);
		CustomerModel customerProfileMock = Mockito.mock(CustomerModel.class);
		PrincipalGroupModel principalGroupMock = Mockito.mock(PrincipalGroupModel.class);

		Mockito.when(cartModelMock.getUser()).thenReturn(customerProfileMock);
		Mockito.when(customerProfileMock.getGroups())
				  .thenReturn(new HashSet<PrincipalGroupModel>(Arrays.asList(principalGroupMock)));
		Mockito.when(principalGroupMock.getUid()).thenReturn("user_group");
		Mockito.when(shopConfigurationServiceMock.getUserGroupUIDForVisitor()).thenReturn("visitor_group");

		Mockito.when(formElementGroupMock.isPrefilled(cartModelMock)).thenReturn(false);
		Mockito.when(formElementGroupMock.isDisplayed(cartModelMock)).thenReturn(true);

		formElementGroupsMocks.add(formElementGroupMock);

		Mockito.when(checkoutStepMock.getElements()).thenReturn(formElementGroupsMocks);

		defaultCheckoutStepService.setShopConfigurationService(shopConfigurationServiceMock);
		defaultCheckoutStepService.setCheckoutSteps(checkoutStepsMocks);
		defaultCheckoutStepService.getNeededSteps(cartModelMock);

		Mockito.verify(formElementGroupMock).prefillCartFromCustomerProfile(
				Matchers.eq(cartModelMock),
				Matchers.eq(customerProfileMock)
		);
	}
}
