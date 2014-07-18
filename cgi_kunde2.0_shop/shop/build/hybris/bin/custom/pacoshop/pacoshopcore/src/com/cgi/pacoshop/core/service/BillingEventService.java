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

import de.hybris.platform.subscriptionservices.model.BillingEventModel;

/**
 * Service for managing BillingEventModels.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 12, 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public interface BillingEventService
{
	/**
	 * Returns for the given BillingEvent <code>code</code> the {@link BillingEventModel} from database.
	 *
	 * @param code the BillingEvent <code>code</code>
	 * @return a {@link BillingEventModel}
	 */
	BillingEventModel findBillingEventByCode(final String code);

	/**
	 * Returns for the given BillingEvent <code>code</code> the {@link BillingEventModel} from database
	 * or creates new one.
	 *
	 * @param code the BillingEvent <code>code</code>
	 * @return a {@link BillingEventModel}
	 */
	BillingEventModel getOrCreateBillingEvent(final String code);
}
