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
package com.cgi.pacoshop.core.service.evaluator;


import com.cgi.pacoshop.core.model.CampaignIdRestrictionModel;
import com.cgi.pacoshop.core.service.CampaignIdService;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;

/**
 * Evaluates css link to external resource by deeplink parameter campaignId.
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
public class CampaignIdCMSRestrictionEvaluator implements CMSRestrictionEvaluator<CampaignIdRestrictionModel>
{
	@Resource
	private CampaignIdService campaignIdService;

	@Override
	public boolean evaluate(final CampaignIdRestrictionModel campaignIdRestrictionModel, final RestrictionData restrictionData)
	{
		final String sessionCampaignId = campaignIdService.getCampaignId();

		return StringUtils.isNotEmpty(sessionCampaignId)
				  && campaignIdRestrictionModel.getCampaignId().equals(campaignIdService.getCampaignId());
	}
}
