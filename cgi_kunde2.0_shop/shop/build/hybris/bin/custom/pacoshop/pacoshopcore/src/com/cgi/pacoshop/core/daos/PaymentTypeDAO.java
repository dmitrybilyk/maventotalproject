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
package com.cgi.pacoshop.core.daos;


import com.cgi.pacoshop.core.model.PaymentTypeModel;
import java.util.List;

/**
 * DAO for managing the PaymentTypeModel objects.
 *
 * @module pacoshopcore
 * @version 1.0v Jan 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author  @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface PaymentTypeDAO
{
	/**
	 * Find all payment types.
	 *
	 * @return the list
	 */
	List<PaymentTypeModel> findAllPaymentTypes();
}
