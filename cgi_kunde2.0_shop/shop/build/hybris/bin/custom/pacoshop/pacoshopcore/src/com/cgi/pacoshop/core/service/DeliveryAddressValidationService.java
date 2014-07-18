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


import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.model.DeliveryAddress;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.validation.BindingResult;


/**
 * Interface for delivery address validation
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public interface DeliveryAddressValidationService
{
	/**
	 * Validate if the product can be delivered to a delivery address.
	 *
	 * @param model Product
	 * @param deliveryAddress DeliveryAddress
	 * @return true if validation passed
	 */
	boolean productDeliverableToAddress(final ProductModel model, DeliveryAddress deliveryAddress);
}
