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

import com.cgi.pacoshop.core.model.TermVersionModel;

/**
 * Calls rest client for terms versions import.
 *
 * @version 1.0v Jun 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 *
 */
public interface SSOTermsVersionService<T extends TermVersionModel>
{
	/**
	 * Method that calls from cronjob to import terms versions
	 * @return collection of terms versions
	 */
	T importTermsVersions();
}
