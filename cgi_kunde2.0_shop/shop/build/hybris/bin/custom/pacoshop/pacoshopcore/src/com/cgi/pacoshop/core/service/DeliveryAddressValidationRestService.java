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


import com.cgi.pacoshop.core.model.DeliveryAddress;

import java.net.URI;


/**
 * Interface for delivery address validation using rest template.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 04, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public interface DeliveryAddressValidationRestService
{
	/**
	 * Invokes address validation on SAP service.
	 * @param url path to SAP service
	 * @param deliveryAddress delivery address to check
	 * @param readTimeout read timeout for rest service
	 * @return validation result.
	 */
	String validateAddress(URI url, DeliveryAddress deliveryAddress, int readTimeout);
}
