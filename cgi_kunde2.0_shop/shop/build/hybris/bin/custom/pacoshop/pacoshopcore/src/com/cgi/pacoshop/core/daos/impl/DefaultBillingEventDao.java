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


import com.cgi.pacoshop.core.daos.BillingEventDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.subscriptionservices.model.BillingEventModel;
import org.springframework.beans.factory.annotation.Required;

/**
 * BillingEvent entity dao layer. Implementation of BillingEventDao.
 *
 * @module build
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultBillingEventDao implements BillingEventDao
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public BillingEventModel findBillingEventByCode(final String code)
	{
		FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {pk} FROM {BillingEvent} WHERE {code}=?code");
		query.addQueryParameter("code", code);
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
	 * @param flexibleSearchServiceParam - flexibleSearchService to be set.
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchServiceParam)
	{
		this.flexibleSearchService = flexibleSearchServiceParam;
	}
}
