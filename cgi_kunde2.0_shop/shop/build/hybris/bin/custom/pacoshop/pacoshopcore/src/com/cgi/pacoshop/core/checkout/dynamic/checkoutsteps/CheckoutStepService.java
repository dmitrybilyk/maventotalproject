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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import de.hybris.platform.core.model.order.CartModel;
import java.util.List;
import java.util.Map;

/**
 * A service to retrieve checkout steps needed in a checkout workflow
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public interface CheckoutStepService
{

	/**
	 * @param checkoutStep - checkout step
	 * @return - populated FormDTO instance
	 */
	FormDTO populateFormFromCart(final CheckoutStep checkoutStep);

	/**
	 * Determine checkout steps needed in the current checkout flow.
	 * if all formelements have been already prefilled from the cart - then the checkout step should not be in the list.
	 * @param sessionCart - cart object linked to a current session
	 * @return the list of checkout steps needed in the current checkout flow
	 */
	List<CheckoutStep> getNeededSteps(final CartModel sessionCart);

	/**
	 * Determine the next checkout step to be rendered.
	 * @param neededSteps - previously determined checkout steps needed in the current checkout flow
	 * @return the first not completed step
	 */
	CheckoutStep getNextDisplayedStep(
			  List<? extends CheckoutStep> neededSteps);

	/**
	 * Step Name Getter.
	 * @param stepName Step name to get.
	 * @return  - CheckoutStep by name.
	 */
	CheckoutStep getStep(String stepName);

	/**
	 * Gets mandatory field names.
	 *
	 * @param checkoutStep the step.
	 * @return the mandatory field names.
	 */
	Map<String, Boolean> getStepMandatoryFieldNames(final CheckoutStep checkoutStep);

	/**
	 * Gets checkout step by form dto.
	 *
	 * @param formDTO the dto.
	 * @return the checkout step.
	 */
	CheckoutStep getCheckoutStep(FormDTO formDTO);

}
