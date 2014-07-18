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
package com.cgi.pacoshop.core.service.sso;


import java.util.Map;

import com.cgi.pacoshop.core.model.ResponseCustomer;


/**
 * Interface for customer rest service.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface SSOCustomerRestClient
{
	/**
	 * 
	 * @param id
	 *           the id
	 * @return ResponseCustomer from SSO
	 */
	ResponseCustomer getUserData(Map<String, String> id);
}
