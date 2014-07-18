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
package com.cgi.pacoshop.core.jobs;


import com.cgi.pacoshop.core.service.ImportProductService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * A cron job used to import products from Service Bus and save them into hybris.
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 13, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/dashboard.action"
 *
 */
public class SingleProductImportJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(SingleProductImportJob.class);

	@Resource
	private ImportProductService importProductService;

	@Override
	public PerformResult perform(final CronJobModel cronJobModel)
	{
		LOG.info("****************** Simple Product import started ************************");
		try
		{
			importProductService.importSingleProduct();
		}
		catch (Exception e)
		{
			LOG.error("Failed to execute import job. Cause is: " + e.getMessage(), e);
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
		}
		finally
		{
			LOG.info("******************Simple Product import finished ************************");
		}

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}
}
