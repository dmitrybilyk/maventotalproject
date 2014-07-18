/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.checkout.dynamic.session;

import com.cgi.pacoshop.facades.user.data.Title2Data;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import java.util.Collection;
import java.util.List;

/**
 * Session cache stores commonly used data per user session (e.g
 * titles, countries, currencies, etc.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @version 1.0v Mar 21, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 * @see "https://wiki.hybris.com/"
 */
public interface SessionCache
{
	/**
	 * Salutation titles.
	 * @return Salutation titles.
	 */
	List<TitleData> getSalutationTitles();

	/**
	 * Title2 titles.
	 * @return Salutation titles.
	 */
	List<Title2Data> getTitles2();

	/**
	 * Countries.
	 * @param order the order
	 * @param filterByCountriesInOrderItems the filter by countries in order items
	 * @return Countries countries
	 */
	Collection<CountryData> getCountries(final AbstractOrderModel order, final boolean filterByCountriesInOrderItems);

	/**
	 * Gets default country iso code.
	 *
	 * @return the default country iso code
	 */
	String getDefaultCountryIsoCode();
}
