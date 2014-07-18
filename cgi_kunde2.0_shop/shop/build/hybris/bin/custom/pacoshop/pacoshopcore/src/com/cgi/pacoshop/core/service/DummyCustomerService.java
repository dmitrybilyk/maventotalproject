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

import de.hybris.platform.core.model.user.CustomerModel;


/**
 * Represents a simple service returning the dummy customer defined in the system
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 08, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <phillipe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface DummyCustomerService
{
	/**
	 * Fetch the customer defined as dummy in the system.
	 * 
	 * @return either the dummy customer or null if not found.
	 */
	CustomerModel retrieveDummyCustomer();
}
