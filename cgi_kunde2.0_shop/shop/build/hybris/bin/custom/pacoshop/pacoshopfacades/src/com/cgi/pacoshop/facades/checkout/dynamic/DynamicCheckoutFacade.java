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
package com.cgi.pacoshop.facades.checkout.dynamic;


import com.cgi.pacoshop.core.checkout.dynamic.CartContentsFragmentDTO;
import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;
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
	 * Saves form data to cart model and re-calculates the cart (taxes, delivery, etc.).
	 *
	 * @param dto - data transfer object.
     * @throws CalculationException When cart cannot be recalculated
	 */
	void saveFormToCart(FormDTO dto) throws CalculationException;

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
	 * Gets step.
	 *
	 * @param stepName stepName
	 * @return checkoutStep step
	 */
	CheckoutStep getStep(String stepName);

	/**
	 * * returns <key, value> map.
	 * that contains data specific to the step
	 *
	 *
     * @param neededSteps Steps to supply to Spring MVC model
     * @param step step
     * @return <key, value> map
	 */
	Map<String, Object> populatePageModelData(List<CheckoutStep> neededSteps, final CheckoutStep step);
}
