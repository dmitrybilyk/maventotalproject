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


import com.cgi.pacoshop.core.daos.BusinessPartnerIdDAO;
import com.cgi.pacoshop.core.exceptions.BusinessProductIdForCustomerNotFoundException;
import com.cgi.pacoshop.core.jalo.BusinessPartnerId;
import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import com.cgi.pacoshop.core.model.VATGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * Here goes 1 line.
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
public class BusinessPartnerIdDAOImpl implements BusinessPartnerIdDAO
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<BusinessPartnerIdModel> findBusinessPartnerIdsForCustomer(final String customerId)
            throws BusinessProductIdForCustomerNotFoundException
	{

		String query = String.format("SELECT {%s},{%s} FROM {%s AS b JOIN %s AS c ON {b: %s}={c: %s}} WHERE {c: %s} = ?customerId",
											  BusinessPartnerIdModel.PK, BusinessPartnerId.ID, BusinessPartnerIdModel._TYPECODE,
											  CustomerModel._TYPECODE,
											  BusinessPartnerIdModel.CUSTOMER, CustomerModel.PK, CustomerModel.UID);
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
		flexQuery.addQueryParameter("customerId", customerId);
		List<BusinessPartnerIdModel> businessPartnerIdModels =
				  flexibleSearchService.<BusinessPartnerIdModel>search(flexQuery).getResult();
		if (businessPartnerIdModels.isEmpty())
		{
			throw new BusinessProductIdForCustomerNotFoundException("No BusinessProductIds for customer were found");
		}
		return businessPartnerIdModels;
	}

	@Override
	public List<BusinessPartnerIdModel> findBusinessPartnerIds(final Set<String> ids) throws
																												 BusinessProductIdForCustomerNotFoundException
	{
		final String joinedIDs = StringUtils.join(ids, ",");
		if (StringUtils.isEmpty(joinedIDs))
		{
			throw new BusinessProductIdForCustomerNotFoundException("Set of ids is empty or has empty params");
		}
		final String query = String.format("SELECT {%s},{%s} FROM {%s AS b} WHERE {b: %s} IN (%s)",
											  BusinessPartnerIdModel.PK, BusinessPartnerId.ID, BusinessPartnerIdModel._TYPECODE,
											  BusinessPartnerId.ID, joinedIDs);
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
		List<BusinessPartnerIdModel> businessPartnerIdModels =
				  flexibleSearchService.<BusinessPartnerIdModel>search(flexQuery).getResult();
		if (businessPartnerIdModels.isEmpty())
		{
			throw new BusinessProductIdForCustomerNotFoundException("No BusinessProductIds were found");
		}
		return businessPartnerIdModels;
	}

	/**
	 * Getter.
	 * @return flexibleSearchService flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * Setter.
	 * @param flexibleSearchService flexibleSearchService
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
