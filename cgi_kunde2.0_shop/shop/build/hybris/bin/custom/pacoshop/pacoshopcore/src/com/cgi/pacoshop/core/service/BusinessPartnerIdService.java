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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.exceptions.BusinessProductIdForCustomerNotFoundException;
import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import java.util.List;
import java.util.Set;

/**
 * Service for Business Partners Ids .
 *
 * @module pacoshopcore
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface BusinessPartnerIdService
{
	/**
	 * Finds list of BusinessPartnerIdModels for customer.
	 *
	 * @param customerId - id of customer
	 * @return list of BusinessPartnerIdModel
	 */
	List<BusinessPartnerIdModel> findBusinessPartnerIdsForCustomer(String customerId);

	/**
	 * Finds list of BusinessPartnerIdModels for customer.
	 *
	 * @param ids - ids to search
	 * @return list of BusinessPartnerIdModel
	 */
	List<BusinessPartnerIdModel> findBusinessPartnerIds(Set<String> ids);
}
