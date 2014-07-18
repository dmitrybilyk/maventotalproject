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
package com.cgi.pacoshop.core.populator;


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.model.DeliveryAddress;
import de.hybris.platform.core.model.user.AddressModel;


/**
 * Defines the populator for the Delivery Address population.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public interface DeliveryAddressPopulator
{
	/**
	 * Populate a {@link DeliveryAddress} based on a {@link AddressModel}.
	 *
	 * @param address
	 *            the address to use.
	 * @return the {@link DeliveryAddress}.
	 */
	DeliveryAddress populate(AddressModel address);

	/**
	 * Populate a {@link DeliveryAddress} based on a {@link CheckoutFormDTO}.
	 *
	 * @param formDTO
	 *            the address to use.
	 * @return the {@link DeliveryAddress}.
	 */
	DeliveryAddress populate(CheckoutFormDTO formDTO);

	/**
	 * Populate a {@link DeliveryAddress} based on a {@link ShipmentInfoFormDTO}.
	 *
	 * @param formDTO
	 *            the address to use.
	 * @return the {@link DeliveryAddress}.
	 */
	DeliveryAddress populate(ShipmentInfoFormDTO formDTO);
}
