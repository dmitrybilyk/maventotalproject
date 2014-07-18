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
package com.cgi.pacoshop.fulfilmentprocess.client;


import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountResponse;

/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module build
 * @version 1.0v Mar 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface SSOSearchRestClient
{

	/**
	 *
	 * @param ssoAccountRequest
	 *           inside we have email and other parameters
	 * @return SSOAccountResponse from SSO
	 */
	SSOAccountResponse getUserFromSSO(SSOAccountRequest ssoAccountRequest);

}
