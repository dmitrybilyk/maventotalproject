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


import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;

/**
 * Save customer address to hybris
 *
 * @module hybris
 * @version 1.0v Apr 03, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface CustomerAddressService
{
	/**
	 * Save shop address to hybris.
	 * The shop address should never overwrite sso or sap addresses.
	 * @param newAddress the address model
	 * @param userModel the customer model
	 */
	void saveCustomerAddressWithLimit(AddressModel newAddress, UserModel userModel);

	/**
	 * Method compares two addresses by fields.
	 *
	 * @param firstAddress first address for comparing.
	 * @param secondAddress second address for comparing.
	 * @return if addresses are equal then return true
	 */
	boolean compareAddressesByFields(AddressModel firstAddress, AddressModel secondAddress);
}
