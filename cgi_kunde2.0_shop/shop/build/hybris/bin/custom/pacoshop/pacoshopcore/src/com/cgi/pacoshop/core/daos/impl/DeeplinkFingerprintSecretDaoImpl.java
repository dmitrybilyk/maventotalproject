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
package com.cgi.pacoshop.core.daos.impl;


import com.cgi.pacoshop.core.daos.DeeplinkFingerprintSecretDao;
import com.cgi.pacoshop.core.model.FingerprintSecretModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of DeeplinkFingerprintSecretDao.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de> 
 * @version 1.0v Apr 02, 2014
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DeeplinkFingerprintSecretDaoImpl implements DeeplinkFingerprintSecretDao
{
	@Resource
	private FlexibleSearchService flexibleSearchService;

	/**
	 * Get secret number for fingerprint.
	 *
	 * @param fingerPrintSecretNo - fingerPrintSecretNo
	 * @return FingerprintSecretModel
	 */
	public FingerprintSecretModel getFingerprintSecretByNumber(final String fingerPrintSecretNo)
	{
		String queryString = String.format("SELECT {%s} FROM {%s} WHERE {%s}=?fingerPrintSecretNo",
				FingerprintSecretModel.PK,
				FingerprintSecretModel._TYPECODE,
				FingerprintSecretModel.FINGERPRINTSECRETNO);
		FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("fingerPrintSecretNo", fingerPrintSecretNo);
		return flexibleSearchService.searchUnique(query);
	}

	/**
	 *
	 * @return flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * flexibleSearchService setter.
	 *
	 * @param flexibleSearchServiceParam - flexibleSearchService to be set.
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchServiceParam)
	{
		this.flexibleSearchService = flexibleSearchServiceParam;
	}
}
