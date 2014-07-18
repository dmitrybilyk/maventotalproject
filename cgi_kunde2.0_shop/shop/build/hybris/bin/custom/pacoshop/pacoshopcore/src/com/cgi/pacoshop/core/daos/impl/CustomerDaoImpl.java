/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
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

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.cgi.pacoshop.core.daos.CustomerDao;


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
public class CustomerDaoImpl implements CustomerDao
{

	private static final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class);

	@Resource
	private FlexibleSearchService flexibleSearchService;


	/**
	 * Retrieves dummy customer.
	 *
	 * @return Dummy customer
	 */
	public CustomerModel retrieveDummyCustomer()
	{

		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(
				String.format("select {PK} FROM {Customer} where {dummy}=true"));
		final List<CustomerModel> users = flexibleSearchService.<CustomerModel> search(flexQuery).getResult();

		if (users == null || users.isEmpty())
		{
			LOGGER.error("No Dummy Customer is defined. Please ensure that one customer has the flag 'dummy' sets to true");
			return null;
		}
		if (users.size() > 1)
		{
			LOGGER.warn("More than one customer is specified as the dummy, returning the first one found.");
			final StringBuffer strbuf = new StringBuffer("Here is the list of user that has the dummy flag == true: [");
			for (final UserModel current : users)
			{
				strbuf.append(current.getUid() + ", ");
			}
			LOGGER.warn(strbuf.toString());
		}

		if (LOGGER.isDebugEnabled())
		{
			LOGGER.debug("Returning dummy customer[" + users.get(0) + "]");
		}
		return users.get(0);
	}



	/**
	 * Injects FlexibleSearchService.
	 *
	 * @param flexibleSearchService the flexibleSearchService to set
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
