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
package com.cgi.pacoshop.core.daos.impl;


import com.cgi.pacoshop.core.daos.DeeplinkCallbackDAO;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.PortalCallbackException;
import com.cgi.pacoshop.core.model.PortalIdModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of DeeplinkCallbackDAO
 *
 *
 * @module pacoshopcore
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DeeplinkCallbackDAOImpl implements DeeplinkCallbackDAO
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public String getDeeplinkCallbackUrl(final String portalId) throws PortalCallbackException
	{
		String query = String.format("SELECT {%s} FROM {%s} WHERE {%s} = ?portalId ",
                                     PortalIdModel.PK, PortalIdModel._TYPECODE, PortalIdModel.PORTALID);
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
		flexQuery.addQueryParameter("portalId", portalId);

        List<PortalIdModel> list = flexibleSearchService.<PortalIdModel>search(flexQuery).getResult();

        if (list.size() == 0)
		{
			throw new PortalCallbackException(
                    "portalid = " + portalId + " doesn't exist in Hybris. Please check the configuration.");
		}

		return list.get(0).getPortalUrl();
	}

	/**
	 * Getter.
	 * @return flexibleSearchService flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * Setter.
	 * @param flexibleSearchService flexibleSearchService
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
