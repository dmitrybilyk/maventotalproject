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


import com.cgi.pacoshop.core.daos.PacoAddressDAO;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Custom address DAO for getting address by businessPartnerId.
 *
 * @module build
 * @version 1.0v Mar 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PacoAddressDAOImpl implements PacoAddressDAO
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public AddressModel getAddressForBusinessPartnerId(final String businessPartnerId)
	{
		String q = String.format("SELECT {%s} FROM {%s} WHERE {%s}=?businessPartnerId", AddressModel.PK, AddressModel._TYPECODE,
										 AddressModel.BUSINESSPARTNERID);
		FlexibleSearchQuery query = new FlexibleSearchQuery(q);
		query.addQueryParameter("businessPartnerId", businessPartnerId);
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
