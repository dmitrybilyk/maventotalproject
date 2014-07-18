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


import com.cgi.pacoshop.core.enums.SubscriptionPhase;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.subscriptionservices.model.BillingFrequencyModel;

/**
 * Service for managing BillingFrequencyModels.
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
public interface BillingFrequencyService
{
	/**
	 * Returns for the given BillingFrequency <code>code</code> the
	 * {@link de.hybris.platform.subscriptionservices.model.BillingFrequencyModel} from database.
	 *
	 * @param code
	 *           the BillingFrequency <code>code</code>
	 * @return a {@link de.hybris.platform.subscriptionservices.model.BillingFrequencyModel}
	 */
	BillingFrequencyModel findBillingFrequencyByCode(final String code);


	/**
	 * Returns for the given BillingFrequency <code>code</code> the
	 * {@link de.hybris.platform.subscriptionservices.model.BillingFrequencyModel} from database.
	 * @param externalFrequency the externalFrequency
	 * @param unit model unit
	 * @param subscriptionPhase the SubscriptionPhase
	 * @return a {@link de.hybris.platform.subscriptionservices.model.BillingFrequencyModel}
	 */
	BillingFrequencyModel findBillingFrequency(final Integer externalFrequency, final UnitModel unit,
															 final SubscriptionPhase subscriptionPhase);
	/**
	 * Returns for the given BillingFrequency <code>code</code> the
	 * {@link de.hybris.platform.subscriptionservices.model.BillingFrequencyModel} from database or creates new one.
	 * @param externalFrequency the externalFrequency
	 * @param unit model unit
	 * @param subscriptionPhase the SubscriptionPhase
	 * @return a {@link de.hybris.platform.subscriptionservices.model.BillingFrequencyModel}
	 */
	BillingFrequencyModel getOrCreateBillingFrequency(final Integer externalFrequency, final UnitModel unit,
																	  final SubscriptionPhase subscriptionPhase);
}
