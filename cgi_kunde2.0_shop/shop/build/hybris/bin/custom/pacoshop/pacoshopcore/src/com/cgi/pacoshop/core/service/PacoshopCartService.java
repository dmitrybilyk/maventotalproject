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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.PaymentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.CustomerModel;
import java.util.Date;

// FIXME: this service should be spread out between form element groups and should not exist separately.
/**
 * Interface service layer for saving cart
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 08, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface PacoshopCartService
{
	/**
	 * Copies customer data from CheckoutFormDTO to CartModel object.
	 *
	 * @param dto - entity from frontend
	 * @param cartModel current session cartmodel
	 */
	void saveCustomerDataForDownload(final CheckoutFormDTO dto, final CartModel cartModel);

	/**
	 *	Copies summary step data from SummaryStepFormDTO to CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveSummaryStep(SummaryStepFormDTO dto, CartModel cartModel);

	/**
	 * Copies data from CheckoutFormDTO to CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveCustomerDataForGoodPrintDigital(final CheckoutFormDTO dto, final CartModel cartModel);

	/**
	 * Method is saving different Invoice (Payment) address into the cart.
	 *
	 * @param dto - specific FormDTO for Shipment Page step
	 * @param cartModel current session cartmodel
	 */
	void saveInvoiceAddressForGoodPrintDigital(ShipmentInfoFormDTO dto, CartModel cartModel);

	/**
	 * Method is saving different shipment address into the cart.
	 *
	 * @param dto - specific FormDTO for Shipment Page step
	 * @param cartModel current session cartmodel
	 */
	void saveDifferentShipmentAddress(ShipmentInfoFormDTO dto, CartModel cartModel);

	/**
	 * Saves different shipment address checkbox state to CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveDifferentShipmentAddressAllowed(CheckoutFormDTO dto, CartModel cartModel);

	/**
	 * Aves different invoice address checkbox state to CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveDifferentInvoiceAddressAllowed(CheckoutFormDTO dto, CartModel cartModel);


	/**
	 * Save the existing subscription id into CartModel object and to the customer object.
	 *
	 * @param aboNumber - subscription id
	 * @param cartModel current session cartmodel
	 */
	void saveAboNumber(final String aboNumber, final CartModel cartModel);

	/**
	 * Save shipment data into CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveDeliveryDateData(final ShipmentInfoFormDTO dto, final CartModel cartModel);

	/**
	 * Saves customer information for physical bonus into CartModel.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveCustomerInfoForPhysicalBonus(final CheckoutFormDTO dto, final CartModel cartModel);

	/**
	 * Saves payment information for the monetary bonus into CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void savePaymentInfoForMonetaryBonus(final CheckoutFormDTO dto, final CartModel cartModel);


	/**
	 * Saves miles and more number into CartModel's object bonusRecipientAddress attribute.
	 *
	 * @param milesAndMoreNumber - miles&More number
	 * @param cartModel - cart
	 */
	void saveMilesAndMoreNumberToBonusRecipientAddress(final String milesAndMoreNumber, final CartModel cartModel);

	/**
	 * Saves miles and more number into CartModel's object customerAddress attribute.
	 *
	 * @param milesAndMoreNumber - miles&More number
	 * @param cartModel - cart
	 */
	void saveMilesAndMoreNumberToCustomerAddress(final String milesAndMoreNumber, final CartModel cartModel);

	/**
	 * Save student graduation date to the Customer Profile (CustomerModel object) and to the CartModel.
	 *
	 * @param studentGraduationDate - the date when student will be graduated, so it's student subscription will end
	 * @param cartModel - cart
	 */
	void saveStudentGraduationDateToCustomerProfileAndCart(final Date studentGraduationDate, final CartModel cartModel);

	/**
	 * Copies data from CheckoutFormDTO to CartModel object.
	 *
	 * @param dto - specific FormDTO for summary step
	 * @param cartModel current session cartmodel
	 */
	void saveCustomerDataForNewsletter(final CheckoutFormDTO dto, final CartModel cartModel);

	/**
	 * Get the actual customer address version and clone it to cart. In a case when cart already contains a customerAddress
	 * object then it would be reused and all values would be copied to it.
	 *
	 * @param customerModel - CustomerModel object from were we will copy customer address value.
	 * @param cartModel - current session cart model
	 */
	void prefillAndSaveCustomerAddressToCart(final CustomerModel customerModel, final CartModel cartModel);
}
