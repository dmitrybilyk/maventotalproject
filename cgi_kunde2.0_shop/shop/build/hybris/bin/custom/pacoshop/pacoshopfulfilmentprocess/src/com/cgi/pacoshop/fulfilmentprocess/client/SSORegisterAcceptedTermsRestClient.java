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

import com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest;

/**
 * SSO REST client for the SSORegisterAcceptedTermsRequest.
 *
 * @module pacoshopfulfilmentprocess
 * @version 1.0v Mar 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface SSORegisterAcceptedTermsRestClient
{

	/**
	 * Register the terms accepted by the customer into SSO REST service.
	 *
	 * @param ssoRegisterAcceptedTermsRequest data object with all required data.
	 * @return SSO REST service response status code.
	 */
	ClientStatus registerAcceptedTerms(SSORegisterAcceptedTermsRequest ssoRegisterAcceptedTermsRequest);

}
