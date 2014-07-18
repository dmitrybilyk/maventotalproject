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
package com.cgi.pacoshop.core.daos;

import com.cgi.pacoshop.core.exceptions.BusinessProductIdForCustomerNotFoundException;
import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;

import java.util.List;
import java.util.Set;

/**
 * Business partner, DAO.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 12, 2014
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public interface BusinessPartnerIdDAO
{
	/**
	 * Finds list of BusinessPartnerIdModels for customer.
	 *
	 * @param customerId - id of customer
	 * @return list of BusinessPartnerIdModel
	 * @throws BusinessProductIdForCustomerNotFoundException if some errors occurs
	 */
	List<BusinessPartnerIdModel> findBusinessPartnerIdsForCustomer(final String customerId)
			throws BusinessProductIdForCustomerNotFoundException;

	/**
	 * Finds list of BusinessPartnerIdModels for customer.
	 *
	 * @param ids - ids to search
	 * @return list of BusinessPartnerIdModel
	 * @throws BusinessProductIdForCustomerNotFoundException if some errors occurs
	 */
	List<BusinessPartnerIdModel> findBusinessPartnerIds(Set<String> ids)
			throws BusinessProductIdForCustomerNotFoundException;
}
