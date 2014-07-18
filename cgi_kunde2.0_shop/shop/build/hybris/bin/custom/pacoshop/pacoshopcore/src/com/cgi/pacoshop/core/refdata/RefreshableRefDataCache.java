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
package com.cgi.pacoshop.core.refdata;


/**
 * Provides the refreshRefDataCache() method that has to be
 * implemented by descendants to be able to reload the reference
 * data before some important processing, therefore ensuring
 * that the cached reference data items (e.g. VATGroupModels,
 * ProductTypes, PaymentTypes, etc.) are reloaded from the database.
 *
 * @module hybris
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface RefreshableRefDataCache
{
	/**
	 * Refreshes reference data cache.
	 */
	void refreshRefData();
}
