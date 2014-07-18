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


import de.hybris.platform.subscriptionservices.model.BillingPlanModel;

/**
 * DAO for managing the BillingPlanModel objects.
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
public interface BillingPlanDao
{
	/**
	 * Returns for the given BillingPlan <code>id</code> the
	 * {@link de.hybris.platform.subscriptionservices.model.BillingPlanModel}.
	 *
	 * @param id
	 *           the BillingPlan <code>id</code>
	 * @return a {@link de.hybris.platform.subscriptionservices.model.BillingPlanModel}
	 */
	BillingPlanModel findBillingPlanById(final String id);
}
