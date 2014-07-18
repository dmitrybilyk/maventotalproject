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
package com.cgi.pacoshop.fulfilmentprocess.populator.impl;


import com.cgi.pacoshop.core.model.TermAcceptedModel;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.SSOException;
import com.cgi.pacoshop.fulfilmentprocess.model.AcceptedTermEntity;
import com.cgi.pacoshop.fulfilmentprocess.model.AcceptedTermListEntity;
import com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest;
import com.cgi.pacoshop.fulfilmentprocess.populator.SSORegisterAcceptedTermsPopulator;
import com.cgi.pacoshop.fulfilmentprocess.util.LogHelper;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * Populator implementation for the SSORegisterAcceptedTermsRequest.
 *
 * @module pacoshopfulfilmentprocess
 * @version 1.0v Feb 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see  https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SSORegisterAcceptedTermsPopulatorImpl implements SSORegisterAcceptedTermsPopulator
{
	public static final  String CONFIG_KEY_SSO_PLATFORM_ID_VALUE = "sso.user.filter.platform.id.value";
	private static final Logger LOG = Logger.getLogger(SSORegisterAcceptedTermsPopulatorImpl.class);

	@Resource
	private ConfigurationService configurationService;

	@Override
	public SSORegisterAcceptedTermsRequest populate(final AbstractOrderModel order) throws SSOException
	{
		if (order == null)
		{
			LogHelper.debug(LOG, "Called the SSORegisterAcceptedTermsPopulator with a null order.");
			throw new SSOException("Called the SSORegisterAcceptedTermsPopulator with a null order.");
		}

		SSORegisterAcceptedTermsRequest result = new SSORegisterAcceptedTermsRequest();

		if (isAcceptedTermsExist(order))
		{
			UserModel customer = order.getUser();
			if (customer == null)
			{
				LogHelper.debug(LOG, "Called the SSORegisterAcceptedTermsPopulator with a null accountId value within order.");
				throw new SSOException("Called the SSORegisterAcceptedTermsPopulator with a null accountId value within order.");
			}
			result.setAccountId(customer.getUid());
			result.setPlatformId(configurationService.getConfiguration().getString(CONFIG_KEY_SSO_PLATFORM_ID_VALUE));
			//populate terms
			Set<TermAcceptedModel> orderTerms = order.getTermsAccepted();
			List<AcceptedTermEntity> requestTerms = new ArrayList<>(orderTerms.size());
			for (TermAcceptedModel orderTerm : orderTerms)
			{
				AcceptedTermEntity requestTerm = new AcceptedTermEntity();
				requestTerm.setTermsVersionId(orderTerm.getTermsVersion().getTermsVersionId());
				requestTerm.setConfirmed(orderTerm.getConfirmed());
				requestTerms.add(requestTerm);
			}
			AcceptedTermListEntity acceptedTermListEntity = new AcceptedTermListEntity();
			acceptedTermListEntity.setTerms(requestTerms);
			result.setTerms(acceptedTermListEntity);
			//mark request object as populated
			result.setPopulated(true);
		}

		return result;
	}

	private boolean isAcceptedTermsExist(final AbstractOrderModel order)
	{
		if (order.getTermsAccepted() == null || order.getTermsAccepted().isEmpty())
		{
			LogHelper.debug(LOG, "SSORegisterAcceptedTermsPopulator.isAcceptedTermsExist() order doesn't contain any accepted term");
			return false;
		}
		return true;
	}
}
