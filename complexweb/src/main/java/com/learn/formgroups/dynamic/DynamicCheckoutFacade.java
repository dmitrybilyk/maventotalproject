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
package com.learn.formgroups.dynamic;


import com.cgi.pacoshop.core.checkout.dynamic.CartContentsFragmentDTO;
import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.order.InvalidCartException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Facade to provide the logic for the Dynamic Checkout Framework
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v dec 12, 2013
 * @module hybris - pacoshopfacades
 * @link http ://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 */
public interface DynamicCheckoutFacade
{
	/**
	 * Gets needed steps.
	 *
	 * @return the list of checkout steps needed in the current checkout flow
	 */
	List<CheckoutStep> getNeededSteps();

	/**
	 * Gets next displayed step.
	 *
	 * @param neededSteps - previously determined checkout steps needed in the current checkout flow
	 * @return the first not completed step
	 */
	CheckoutStep getNextDisplayedStep(List<? extends CheckoutStep> neededSteps);

	/**
	 * TBD later.
	 *
	 * @param dto           - quality gate.
	 * @param checkoutStep  - quality gate.
	 * @param bindingResult - quality gate.
	 * @return - true/false.
	 */
	boolean validateForm(FormDTO dto, CheckoutStep checkoutStep, BindingResult bindingResult);

	/**
	 * TBD later.
	 *
	 * @param dto - data transfer object.
	 */
	void saveFormToCart(FormDTO dto);

	/**
	 * Populate specific step data for specific step.
	 * @param model - mvc model
	 * @param nextStep - specific step
	 */
	void populateSpecificStepDataForSpecificStep(final Model model, final CheckoutStep nextStep);


	/**
	 * Gets display mappings.
	 *
	 * @param step checkoutStep
	 * @return map display mappings
	 */
	Map<String, Boolean> getDisplayMappings(CheckoutStep step);

	/**
	 * Gets checkout step.
	 *
	 * @param formDTO FormDTO
	 * @return checkoutstep checkout step
	 */
	CheckoutStep getCheckoutStep(FormDTO formDTO);

	/**
	 * This method creates order and copy data from shopping card.
	 *
	 * @param session - tbd.
	 * @return - quality gate.
	 * @throws InvalidCartException - exception.
	 */
	OrderData submitOrder(final HttpSession session) throws InvalidCartException;

	/**
	 * Gets step.
	 *
	 * @param stepName stepName
	 * @return checkoutStep step
	 */
	CheckoutStep getStep(String stepName);

	/**
	 * Populate specific step data.
	 *
	 * @param model - mvc model
	 */

	void populateSpecificStepData(Model model);

	/**
	 * Populate common step data.
	 *
	 * @param model -mvc model
	 */
	void populateCommonStepData(Model model);

	/**
	 * Gets product redirect url.
	 *
	 * @return url to be redirected to after purchase.
	 */
	String getProductRedirectUrl();

	/**
	 * Method to determine whether the  thankyou needs to be displayed in the page based on product types in the order
	 * and choices made by the customer in the checkout flow.
	 *
	 * @param orderData order Data
	 * @return the boolean.
	 */
	boolean cartContainsOnlineArticleProduct(final OrderData orderData);

	/**
	 * Gets thank you CMS label.
	 *
	 * @return the thank you CMS label
	 */
	String getThankYouCMSLabel();

	/**
	 * Used for showing breadcrumb.
	 *
	 * @return is onlineArticle value
	 */
	boolean isBreadcrumbDisplayed();

	/**
	 * * Fill the dto to display products in login, summary and thankyou pages.
	 *
	 * @param cartContentsFragmentDTO the cart contents fragment dTO
	 * @param orderId The id of the order
	 * @return the CartContentsFragmentDTO.
	 */
	CartContentsFragmentDTO populateCartContentsFragment(final CartContentsFragmentDTO cartContentsFragmentDTO,
                                                         final String orderId);


	/**
	 * Saves the billing address.
	 *
	 */
	void saveBillingAddress();

	/**
	 * * returns <key, value> map.
	 * that contains data specific to the step
	 *
	 * @param step step
	 * @return <key, value> map
	 */
	Map<String, Object> populatePageModelData(final CheckoutStep step);

}
