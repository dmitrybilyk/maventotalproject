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


import de.hybris.platform.subscriptionservices.model.BillingPlanModel;

/**
 * Service for managing BillingPlanModels.
 *
 * @module build
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface BillingPlanService
{
	/**
	 * Returns {@link de.hybris.platform.subscriptionservices.model.BillingPlanModel} from DB, for BillingPlan <code>id</code>.
	 *
	 * @param id
	 *           the BillingPlan <code>id</code>
	 * @return a {@link de.hybris.platform.subscriptionservices.model.BillingPlanModel}
	 */
	BillingPlanModel findBillingPlanById(final String id);

	/**
	 * Returns {@link de.hybris.platform.subscriptionservices.model.BillingPlanModel} from DB for BillingPlan <code>id</code>.
	 * or creates new one.
	 *
	 * @param id
	 *           the BillingPlan <code>id</code>
	 * @return a {@link de.hybris.platform.subscriptionservices.model.BillingPlanModel}
	 */
	BillingPlanModel getOrCreateBillingPlan(final String id);
}
