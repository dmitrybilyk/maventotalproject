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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.customerdatapage;


import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;


/**
 *
 *
 * @module pacoshopcore
 * @version 1.0v Feb 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexander Ionov <alexander.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class MonetaryBonusReferralFormElementGroup extends AbstractMonetaryBonusFormElementGroup
{
	@Override
	boolean isReferral(final SubscriptionProductModel subscriptionProductModel)
	{
		return subscriptionProductModel.getReadersCanvassReaders();
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.MONETARY_BONUS_REFERRAL_FORM_ELEMENT_GROUP_NAME);
	}
}
