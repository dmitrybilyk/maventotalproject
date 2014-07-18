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
package com.cgi.pacoshop.fulfilmentprocess.actions.order;

import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.service.SSOFulfillmentService;

import com.cgi.pacoshop.fulfilmentprocess.util.LogHelper;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.task.RetryLaterException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;


/**
 * Action to register a specific customer into the SSO list of "optin".
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module hybris - pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class OptinSSOAction extends AbstractSimpleDecisionAction<OrderProcessModel>
{

	private static final Logger LOGGER = Logger.getLogger(OptinSSOAction.class);

	@Resource
	private SSOFulfillmentService ssoFulfillmentService;

	/*
	* (non-Javadoc)
	*
	* @see
	* de.hybris.platform.processengine.action.AbstractSimpleDecisionAction#executeAction(de.hybris.platform.processengine
	* .model.BusinessProcessModel)
	*/
	@Override
    public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
	{
		ClientStatus status = ssoFulfillmentService.registerAcceptedTerms(orderProcessModel.getOrder());
		Transition result = ClientStatus.SUCCESS == status ? Transition.OK : Transition.NOK;
		LogHelper.info(LOGGER, result.name());
		return result;
	}


}
