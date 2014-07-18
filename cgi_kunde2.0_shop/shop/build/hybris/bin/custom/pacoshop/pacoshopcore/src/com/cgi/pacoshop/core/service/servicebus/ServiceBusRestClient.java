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
package com.cgi.pacoshop.core.service.servicebus;


import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import com.cgi.pacoshop.core.model.ResponseBusinessPartner;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface for ServiceBusRestClient - via rest gets business partner address data in JSON format.
 *
 * @module build
 * @version 1.0v Mar 10, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface ServiceBusRestClient
{
	/**
	 *
	 * @param parameters the ids
	 * @param businessPartnerIdModels the businessPartnerIdModels
	 * @return ResponseBusinessPartner from SSO
	 */
	Collection<ResponseBusinessPartner> getBusinessPartnersData(List<BusinessPartnerIdModel> businessPartnerIdModels,
																					Map<String, String> parameters);
}
