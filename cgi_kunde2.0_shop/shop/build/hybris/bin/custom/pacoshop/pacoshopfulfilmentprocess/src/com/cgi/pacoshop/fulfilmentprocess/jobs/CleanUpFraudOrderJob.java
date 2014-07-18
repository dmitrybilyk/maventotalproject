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
package com.cgi.pacoshop.fulfilmentprocess.jobs;

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.apache.log4j.Logger;


/**
 * CronJob periodically send CleanUpEvent for <b>order-process</b> processes which are in action <b>waitForCleanUp</b>
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CleanUpFraudOrderJob extends AbstractJobPerformable<CronJobModel>
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(CleanUpFraudOrderJob.class);
	private BusinessProcessService businessProcessService;


	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		final String processDefinitionName = PacoshopFulfilmentProcessConstants.ORDER_PROCESS_NAME;
		final String processCurrentAction = "waitForCleanUp";
		final List<BusinessProcessModel> processes = getAllProcessByDefinitionAndCurrentAction(processDefinitionName,
																															processCurrentAction);

		final String eventNameSuffix = "_CleanUpEvent";
		for (final BusinessProcessModel bpm : processes)
		{
			//${process.code}_CleanUpEvent
			final String eventName = bpm.getCode() + eventNameSuffix;
			businessProcessService.triggerEvent(eventName);
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * OOTB.
	 *
	 * @param processDefinitionName
	 *            OOTB.
	 * @param processCurrentAction
	 *            OOTB.
	 * @return OOTB.
	 */
	protected List<BusinessProcessModel> getAllProcessByDefinitionAndCurrentAction(final String processDefinitionName,
																											 final String processCurrentAction)
	{
		final String query = "select {bp.PK} "
				  + "from {BusinessProcess AS bp  JOIN ProcessTask AS pt ON {bp.pk} = {pt.process} } "
				  + "WHERE {bp.processDefinitionName} = ?processDefinitionName and {pt.action} = ?processCurrentAction";

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
		searchQuery.addQueryParameter("processDefinitionName", processDefinitionName);
		searchQuery.addQueryParameter("processCurrentAction", processCurrentAction);
		final SearchResult<BusinessProcessModel> processes = flexibleSearchService.search(searchQuery);
		return processes.getResult();
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *            the businessProcessService to set
	 */
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}
}
