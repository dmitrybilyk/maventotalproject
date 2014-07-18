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
package com.cgi.pacoshop.core.jalo;

import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.util.localization.Localization;

/**
 * Jalo class for CampaignIdRestriction- not auto-generated.
 *
 * @module kunde_new
 * @version 1.0v May 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CampaignIdRestriction extends GeneratedCampaignIdRestriction
{
	private static final String DESCRIPTION = "type.CampaignIdRestriction.description.text";

	@Deprecated
	@Override
	public String getDescription(final SessionContext ctx)
	{
		final String campaignId = getCampaignId(ctx);

		final StringBuilder result = new StringBuilder();
		if (campaignId != null)
		{
			final String localizedString = Localization.getLocalizedString(DESCRIPTION);
			result.append(localizedString);
			result.append(" ").append(campaignId);
		}
		return result.toString();
	}
}
