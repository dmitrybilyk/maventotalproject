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
package com.cgi.pacoshop.core.service.handler;


import com.cgi.pacoshop.core.jalo.CampaignIdRestriction;
import com.cgi.pacoshop.core.model.CampaignIdRestrictionModel;
import de.hybris.platform.acceleratorcms.model.restrictions.CMSUiExperienceRestrictionModel;
import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.servicelayer.type.TypeService;
import de.hybris.platform.util.localization.Localization;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

/**
 * Dynamic handler for CampaignIdRestriction description attribute.
 *
 * @module core
 * @version 1.0v May 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CampaignIdRestrictionDynamicDescription implements DynamicAttributeHandler<String, CampaignIdRestrictionModel>
{
	@Override
	public String get(final CampaignIdRestrictionModel model)
	{
		final String campaignId = model.getCampaignId();
		final StringBuilder result = new StringBuilder();
		if (campaignId != null)
		{
			final String localizedString = Localization.getLocalizedString("type.CampaignIdRestriction.description.text");
			result.append(localizedString);
			result.append(" ").append(campaignId);
		}
		return result.toString();
	}

	@Override
	public void set(final CampaignIdRestrictionModel model, final String value)
	{
		throw new UnsupportedOperationException();
	}
}
