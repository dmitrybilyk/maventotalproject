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


import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.PaymentMethodException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * PaymentTypeService provides methods for finding and managint the PaymentType objects
 *
 * @module hybris
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface PaymentTypeService
{

	/**
	 * Find and map all payment types.
	 *
	 * @return the map
	 */
	Map<String, PaymentTypeModel> findAndMapAllPaymentTypes();

	/**
	 * Method return PaymentType's from Hybris based on a collection payment methods from deeplink callback functionality.
	 * @param paymentmethod collection of ID payment method
	 * @return collection of existing PaymentType
	 * @throws PaymentMethodException if any PaymentType match existing one in Hybris
	 */
	List<PaymentTypeModel> getAllowedPaymentType(ArrayList<String> paymentmethod) throws PaymentMethodException;
}
