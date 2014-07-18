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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import java.util.Map;
import org.springframework.validation.BindingResult;

import java.util.Set;

/**
 * FormElementGroup Represents logically grouped input fields in JSP page. Examples: addressFormElement,
 * paymentInformationFormElement
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @version 1.0v dec 12, 2013
 * @module hybris - pacoshopacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public interface FormElementGroup
{
	/**
	 * Method to determine whether the  formelementgroup needs to be displayed in the page based on product types in the
	 * cart and choices made by the customer in the checkout flow.
	 *
	 * @param cart - cart object linked to a current session
	 *
	 * @return true if the formelementgroup is required in current checkout flow
	 */
	boolean isDisplayed(CartModel cart);

	/**
	 * The method determines  which controls of the group are ed to be mandatory.
	 *
	 * @param cart - session cart.
	 *
	 * @return the list of names of HTML input controls that will be highlighted in the frontend when the whole form
	 * element group is rendered.
	 */
	Set<String> getMandatoryFieldNames(final CartModel cart);

	/**
	 * True if the form element group has been prefilled FROM CUSTOMER PROFILE.
	 *
	 * @param cart - cart object linked to a current session.
	 *
	 * @return true if from is Prefilled.
	 */
	boolean isPrefilled(final CartModel cart);

	/**
	 * TDB later.
	 *
	 * @param dto  - Form data transfer object.
	 * @param cart - cart object linked to a current session.
	 */
	void populateFormFromCart(FormDTO dto, CartModel cart);

	/**
	 * TDB later.
	 *
	 * @param dto           - Form data transfer object.
	 * @param bindingResult - cart object linked to a current session.
	 *
	 * @return true if from is valid.
	 */
	boolean validate(FormDTO dto, BindingResult bindingResult);

	/**
	 * Copy information from customer profile, which is either updated from SSO, or taken from hybris. The information
	 * Should be copied ONLY ONCE!!!!
	 *
	 * @param cart          - cart object linked to a current session.
	 * @param customerModel - Customer Data.
	 */
	void prefillCartFromCustomerProfile(CartModel cart, CustomerModel customerModel);

	/**
	 * Determine a unique id that is logically linked to a step.
	 *
	 * @return unique identifier of a step
	 */
	String getKey();

	/**
	 * TDB later.
	 *
	 * @param dto       - Form data transfer object.
	 * @param cartModel - Cart model.
	 */
	void saveFormToCart(FormDTO dto, CartModel cartModel);


	/**
	 *
	 * @param cartModel - Cart model.
	 * @return - map that contains form element group's specific data
	 *
	 */
	Map<String, Object> populatePageModelData(final CartModel cartModel);

}
