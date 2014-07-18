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
package com.cgi.pacoshop.core.jobs;


import com.cgi.pacoshop.core.service.sso.SSOTermsVersionService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * Import job for terms versions.
 *
 * @module pacoshop core
 * @version 1.0v Jun 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class TermsVersionImportJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(TermsVersionImportJob.class);

	@Resource
	private SSOTermsVersionService ssoTermsVersionService;

	@Override
	public PerformResult perform(final CronJobModel cronJobModel)
	{
		LOG.info("******************  Terms Versions import started ************************");
		try
		{
			ssoTermsVersionService.importTermsVersions();
		}
		catch (Exception e)
		{
			LOG.error("Failed to execute import job. Cause is: " + e.getMessage(), e);
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		finally
		{
			LOG.info("****************** Terms Versions import finished ************************");
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
}
