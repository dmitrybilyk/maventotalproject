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


import de.hybris.platform.subscriptionservices.model.BillingEventModel;

/**
 * DAO for managing the BillingEventModel objects.
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
public interface BillingEventDao
{
	/**
	 * Returns for the given BillingEvent <code>code</code> the {@link BillingEventModel}.
	 *
	 * @param code
	 *           the BillingEvent <code>code</code>
	 * @return a {@link BillingEventModel}
	 */
	BillingEventModel findBillingEventByCode(final String code);
}
