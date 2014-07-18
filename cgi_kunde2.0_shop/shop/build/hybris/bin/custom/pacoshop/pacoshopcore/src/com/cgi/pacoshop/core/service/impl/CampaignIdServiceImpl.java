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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.service.CampaignIdService;
import de.hybris.platform.jalo.JaloSession;

/**
 * CampaignIdService - sets and gets campaignId to Session.
 *
 * @module core
 * @version 1.0v May 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CampaignIdServiceImpl implements CampaignIdService
{
	private static final String CAMPAIGN_ID = "campaignId";

	@Override
	public void save(final String campaignId)
	{
		getSession().setAttribute(CAMPAIGN_ID, campaignId);
	}

	@Override
	public String getCampaignId()
	{
		if (getSession().getAttribute(CAMPAIGN_ID) != null)
		{
			return getSession().getAttribute(CAMPAIGN_ID).toString();
		}
		return null;
	}

	private JaloSession getSession()
	{
		return JaloSession.getCurrentSession();
	}
}
