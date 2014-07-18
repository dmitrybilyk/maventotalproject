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
package com.cgi.pacoshop.core.checkout;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.FormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl.DefaultCheckoutStep;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.validation.BindingResult;


/**
 * Factory that creates various mock objects that are useful for testing the dynamic checkout framework
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */

public abstract class AbstractDynamicCheckoutFrameworkMockFactory
{
	/**
	 * create MockCart.
	 * @return CartModel. cart model
	 */
	public static CartModel createMockCart()
	{
		CartModel mock = mock(CartModel.class);
		List<AbstractOrderEntryModel> mockedEntries = createMockedEntries();
		when(mock.getEntries()).thenReturn(mockedEntries);
		return mock;
	}

	/**
	 * Create mock cart with subscription product.
	 *
	 * @param subscriptionProductModel the subscriptionProductModel.
	 * @return CartModel. cart model
	 */
	public static CartModel createMockCartWithSubscriptionProduct(final SubscriptionProductModel... subscriptionProductModel)
	{
		CartModel mock = mock(CartModel.class);

		List<AbstractOrderEntryModel> mockedEntries =  new ArrayList<>();

		for (SubscriptionProductModel productModel : subscriptionProductModel)
		{
			AbstractOrderEntryModel entry = mock(AbstractOrderEntryModel.class);
			when(entry.getProduct()).thenReturn(productModel);
			mockedEntries.add(entry);
		}
		when(mock.getEntries()).thenReturn(mockedEntries);
		return mock;
	}

	/**
	 * Create mock country model.
	 *
	 * @param isocode the isocode
	 * @return the country model
	 */
	public static CountryModel createMockCountryModel(final String isocode)
	{
		final CountryModel cm = mock(CountryModel.class);
		cm.setName(isocode);
		when(cm.getName()).thenReturn(isocode);
		when(cm.getIsocode()).thenReturn(isocode);
		return cm;
	}

	/**
	 * Create mock country data.
	 *
	 * @param isocode the isocode
	 * @return the country data
	 */
	public static CountryData createMockCountryData(final String isocode)
	{
		final CountryData cm = mock(CountryData.class);
		cm.setName(isocode);
		when(cm.getName()).thenReturn(isocode);
		when(cm.getIsocode()).thenReturn(isocode);
		return cm;
	}


	/**
	 * create Mocked Entries.
	 * @return list of AbstractOrderEntryModel.
	 */
	public static List<AbstractOrderEntryModel> createMockedEntries()
	{
		List<AbstractOrderEntryModel> mockedEntries = new ArrayList<>();
		AbstractOrderEntryModel entry1 = mock(AbstractOrderEntryModel.class);
		AbstractOrderEntryModel entry2 = mock(AbstractOrderEntryModel.class);
		AbstractOrderEntryModel entry3 = mock(AbstractOrderEntryModel.class);

		ProductModel mockPrintABO = createMockPrintABO();
		when(entry1.getProduct()).thenReturn(mockPrintABO);

		ProductModel mockDigitalABO = createMockDIgitalABO();
		when(entry1.getProduct()).thenReturn(mockDigitalABO);

		ProductModel mockDownload = createMockDOWNLOAD();
		when(entry1.getProduct()).thenReturn(mockDownload);


		mockedEntries.add(entry1);
		mockedEntries.add(entry2);
		mockedEntries.add(entry3);

		return mockedEntries;
	}

	/**
	 * Create mock steps mixed completed and not completed.
	 *
	 * @return list of  4 steps:
	 * 1- completed
	 * 2- not completed
	 * 3- not completed
	 * 4 - completed
	 */
	public static List<CheckoutStep> createMockStepsMixedCompletedAndNotCompleted()
	{
		List<CheckoutStep> theSteps = new ArrayList<>();
		CheckoutStep step1 = createCompletedNotDisplayedStep("1");
		CheckoutStep step2 = createNotCompletedStep("2");
		CheckoutStep step3 = createNotCompletedStep("3");
		CheckoutStep step4 = createCompletedNotDisplayedStep("4");
		theSteps.add(step1);
		theSteps.add(step2);
		theSteps.add(step3);
		theSteps.add(step4);
		return theSteps;
	}

	/**
	 * Create mock steps completed.
	 *
	 * @return list of completed steps
	 */

	public static List<CheckoutStep> createMockStepsCompleted()
	{
		List<CheckoutStep> theSteps = new ArrayList<>();
		CheckoutStep step1 = createCompletedNotDisplayedStep("1");
		CheckoutStep step2 = createCompletedNotDisplayedStep("2");
		CheckoutStep step3 = createCompletedDisplayedStep("3");
		CheckoutStep step4 = createCompletedNotDisplayedStep("4");
		theSteps.add(step1);
		theSteps.add(step2);
		theSteps.add(step3);
		theSteps.add(step4);
		return theSteps;
	}

	/**
	 * Create mock steps not completed displayed.
	 *
	 * @return list of not completed steps
	 */

	public static List<CheckoutStep> createMockStepsNotCompletedDisplayed()
	{
		List<CheckoutStep> theSteps = new ArrayList<>();
		CheckoutStep step1 = createNotCompletedDisplayedStep("1");
		CheckoutStep step2 = createNotCompletedDisplayedStep("2");
		CheckoutStep step3 = createNotCompletedDisplayedStep("3");
		CheckoutStep step4 = createNotCompletedDisplayedStep("4");
		theSteps.add(step1);
		theSteps.add(step2);
		theSteps.add(step3);
		theSteps.add(step4);
		return theSteps;
	}

	/**
	 * Create completed displayed step.
	 *
	 * @param s - step name
	 * @return  - completed and displayed step
	 */
	public static CheckoutStep createCompletedDisplayedStep(final String s)
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setCompleted(true);
		step.setStepName(s);


		List<FormElementGroup> formElementGroupList = new ArrayList<>();

		FormElementGroup e1 = createNotDisplayedElementGroup();
		FormElementGroup e2 = createDisplayedElementGroup();

		formElementGroupList.add(e1);
		formElementGroupList.add(e2);

		step.setFormElementGroups(formElementGroupList);

		return step;
	}


	/**
	 * create Completed Not Displayed Step.
	 * @param s step name
	 * @return checkout step.
	 */
	public static CheckoutStep createCompletedNotDisplayedStep(final String s)
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setCompleted(true);
		step.setStepName(s);


		List<FormElementGroup> formElementGroupList = new ArrayList<>();

		FormElementGroup e1 = createNotDisplayedElementGroup();
		FormElementGroup e2 = createNotDisplayedElementGroup();

		formElementGroupList.add(e1);
		formElementGroupList.add(e2);

		step.setFormElementGroups(formElementGroupList);

		return step;
	}

	/**
	 * create Not Completed And Not Displayed Step.
	 * @param s step name
	 * @return Checkout step.
	 */
	public static CheckoutStep createNotCompletedAndNotDisplayedStep(final String s)
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setCompleted(false);
		step.setStepName(s);

		List<FormElementGroup> formElementGroupList = new ArrayList<>();

		FormElementGroup e1 = createNotDisplayedElementGroup();
		FormElementGroup e2 = createDisplayedElementGroup();

		formElementGroupList.add(e1);
		formElementGroupList.add(e2);


		step.setFormElementGroups(formElementGroupList);

		return step;
	}

	/**
	 * create Not Completed Displayed Step.
	 * @param s step name
	 * @return Checkout step.
	 */
	public static CheckoutStep createNotCompletedDisplayedStep(final String s)
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setCompleted(false);
		step.setStepName(s);

		List<FormElementGroup> formElementGroupList = new ArrayList<>();

		FormElementGroup e1 = createDisplayedElementGroup();
		FormElementGroup e2 = createDisplayedElementGroup();
		FormElementGroup e3 = createNotDisplayedElementGroup();
		FormElementGroup e4 = createDisplayedElementGroup();

		formElementGroupList.add(e1);
		formElementGroupList.add(e2);
		formElementGroupList.add(e3);
		formElementGroupList.add(e4);

		step.setFormElementGroups(formElementGroupList);

		return step;
	}

	/**
	 * create Not Completed Step.
	 * @param s step name
	 * @return Checkout step.
	 */
	public static CheckoutStep createNotCompletedStep(final String s)
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setCompleted(false);
		step.setStepName(s);

		List<FormElementGroup> formElementGroupList = new ArrayList<>();

		FormElementGroup e1 = createNotDisplayedElementGroup();
		FormElementGroup e2 = createDisplayedElementGroup();
		FormElementGroup e3 = createNotDisplayedElementGroup();
		FormElementGroup e4 = createDisplayedElementGroup();

		formElementGroupList.add(e1);
		formElementGroupList.add(e2);
		formElementGroupList.add(e3);
		formElementGroupList.add(e4);

		step.setFormElementGroups(formElementGroupList);

		return step;
	}

	/**
	 * create Step That Will Pass Validation.
	 * @return Step That Will Pass Validation.
	 */
	public static CheckoutStep createStepThatWillPassValidation()
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setStepName("Step that should pass the validation");

		List<FormElementGroup> formElementGroups = new ArrayList<>();
		FormElementGroup e1 = createDisplayedElementGroup();
		FormElementGroup e2 = createDisplayedElementGroup();
		FormElementGroup e3 = createNotDisplayedElementGroup();

		when(e1.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(true);
		when(e2.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(true);
		when(e3.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(false);

		formElementGroups.add(e1);
		formElementGroups.add(e2);
		formElementGroups.add(e3);

		step.setFormElementGroups(formElementGroups);

		return step;
	}

	/**
	 * create Step That Will Not Pass Validation.
	 * @return Step That Will Not Pass Validation.
	 */
	public static CheckoutStep createStepThatWillNotPassValidation()
	{
		DefaultCheckoutStep step = new DefaultCheckoutStep();
		step.setStepName("Step that should not pass the validation");

		List<FormElementGroup> formElementGroups = new ArrayList<>();
		FormElementGroup e1 = createDisplayedElementGroup();
		FormElementGroup e2 = createDisplayedElementGroup();
		FormElementGroup e3 = createNotDisplayedElementGroup();
		FormElementGroup e4 = createNotDisplayedElementGroup();

		when(e1.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(true);
		when(e2.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(false);
		when(e3.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(true);
		when(e4.validate(any(FormDTO.class), any(BindingResult.class))).thenReturn(false);

		formElementGroups.add(e1);
		formElementGroups.add(e2);
		formElementGroups.add(e3);
		formElementGroups.add(e4);

		step.setFormElementGroups(formElementGroups);

		return step;
	}

	/**
	 * create Displayed Element Group.
	 * @return Displayed Element Group.
	 */
	public static FormElementGroup createDisplayedElementGroup()
	{
		FormElementGroup feg = mock(FormElementGroup.class);
		when(feg.isDisplayed(any(CartModel.class))).thenReturn(true);
		when(feg.isPrefilled(createMockCart())).thenReturn(false);
		return feg;
	}

	/**
	 * create Not Displayed Element Group.
	 * @return Not Displayed Element Group.
	 */
	public static FormElementGroup createNotDisplayedElementGroup()
	{
		FormElementGroup feg = mock(FormElementGroup.class);
		when(feg.isDisplayed(any(CartModel.class))).thenReturn(false);
		when(feg.isPrefilled(createMockCart())).thenReturn(true);
		return feg;
	}

	/**
	 * create Displayed And Not Prefilled Element Group.
	 * @return Displayed And Not Prefilled Element Group.
	 */
	public static FormElementGroup createDisplayedAndNotPrefilledElementGroup()
	{
		FormElementGroup feg = mock(FormElementGroup.class);
		when(feg.isDisplayed(any(CartModel.class))).thenReturn(true);
		when(feg.isPrefilled(createMockCart())).thenReturn(false);
		return feg;
	}

	/**
	 * create Displayed And Prefilled ElementGroup.
	 * @return Displayed And Prefilled ElementGroup.
	 */
	public static FormElementGroup createDisplayedAndPrefilledElementGroup()
	{
		FormElementGroup feg = mock(FormElementGroup.class);
		when(feg.isDisplayed(any(CartModel.class))).thenReturn(true);
		when(feg.isPrefilled(createMockCart())).thenReturn(true);
		return feg;
	}


	/*************************************MOCK PRODUCTS.*******************************************************************/
	/**
	 * create Mock Print ABO.
	 * @return Mock Print ABO.
	 */
	public static ProductModel createMockPrintABO()
	{
		SubscriptionProductModel mockProductModel = mock(SubscriptionProductModel.class);
		when(mockProductModel.getCode()).thenReturn("PRINT ABO");
		return mockProductModel;
	}

	/**
	 * Create mock digital aBO with no mandatory fields.
	 *
	 * @return the subscription product model
	 */
	public static SubscriptionProductModel createMockDigitalABOWithNoMandatoryFields()
	{
		SubscriptionProductModel mockProductModel = mock(SubscriptionProductModel.class);
		when(mockProductModel.getCode()).thenReturn("DIGITAL ABO");
		return mockProductModel;
	}

	/**
	 * Create mock digital aBO with no mandatory fields.
	 *
	 * @return the subscription product model
	 */
	public static SubscriptionProductModel createMockPrintABOWithMandatoryFields()
	{
		SubscriptionProductModel mockProductModel = mock(SubscriptionProductModel.class);
		when(mockProductModel.getCode()).thenReturn("PRINT ABO");

		when(mockProductModel.getMandatoryMobile()).thenReturn(true);
		when(mockProductModel.getMandatoryPhone()).thenReturn(true);

		return mockProductModel;
	}


	/**
	 * create Mock Digital ABO.
	 * @return Mock Digital ABO.
	 */
	public static ProductModel createMockDIgitalABO()
	{
		SubscriptionProductModel mockProductModel = mock(SubscriptionProductModel.class);
		when(mockProductModel.getCode()).thenReturn("DIGITAL ABO");
		return mockProductModel;
	}

	/**
	 * create Mock NEWSLETTER.
	 * @return Mock NEWSLETTER.
	 */
	public static ProductModel createMockNEWSLETTER()
	{
		SubscriptionProductModel mockProductModel = mock(SubscriptionProductModel.class);
		when(mockProductModel.getCode()).thenReturn("NEWSLETTER");
		return mockProductModel;
	}

	/**
	 * create Mock GÜTER.
	 * @return Mock GÜTER.
	 */
	public static ProductModel createMockGUTER()
	{
		ProductModel mockProductModel = mock(ProductModel.class);
		when(mockProductModel.getCode()).thenReturn("GÜTER");
		return mockProductModel;
	}

	/**
	 * create Mock DOWNLOAD.
	 * @return Mock DOWNLOAD.
	 */
	public static ProductModel createMockDOWNLOAD()
	{
		ProductModel mockProductModel = mock(ProductModel.class);
		when(mockProductModel.getCode()).thenReturn("DOWNLOAD_CONFIG_KEY");
		return mockProductModel;
	}

	/**
	 * create Mock ONLINEARTIKEL.
	 * @return Mock ONLINEARTIKEL.
	 */
	public static ProductModel createMockONLINEARTIKEL()
	{
		ProductModel mockProductModel = mock(ProductModel.class);
		when(mockProductModel.getCode()).thenReturn("ONLINEARTIKEL");
		return mockProductModel;
	}


	/********************************************************************************************************/


}
