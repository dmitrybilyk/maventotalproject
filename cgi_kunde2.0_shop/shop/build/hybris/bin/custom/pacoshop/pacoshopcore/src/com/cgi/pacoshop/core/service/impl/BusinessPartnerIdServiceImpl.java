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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.daos.BusinessPartnerIdDAO;
import com.cgi.pacoshop.core.exceptions.BusinessProductIdForCustomerNotFoundException;
import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import com.cgi.pacoshop.core.service.BusinessPartnerIdService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import static com.cgi.pacoshop.core.util.LogHelper.debug;

/**
 * BusinessPartnerIdServiceImpl.
 *
 * @module build
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class BusinessPartnerIdServiceImpl implements BusinessPartnerIdService
{
	private static final Logger LOG = Logger.getLogger(BusinessPartnerIdServiceImpl.class);

	@Resource
	private BusinessPartnerIdDAO businessPartnerIdDAO;

	@Override
	public List<BusinessPartnerIdModel> findBusinessPartnerIdsForCustomer(final String customerId)
	{
		try
		{
			return businessPartnerIdDAO.findBusinessPartnerIdsForCustomer(customerId);
		}
		catch (BusinessProductIdForCustomerNotFoundException e)
		{
			debug(LOG, "%s", e.getMessage());
		}
		return new ArrayList<>();
	}

	@Override
	public List<BusinessPartnerIdModel> findBusinessPartnerIds(final Set<String> ids)
	{
		try
		{
			return businessPartnerIdDAO.findBusinessPartnerIds(ids);
		}
		catch (BusinessProductIdForCustomerNotFoundException e)
		{
			debug(LOG, "%s", e.getMessage());
		}
		return new ArrayList<>();
	}


}
