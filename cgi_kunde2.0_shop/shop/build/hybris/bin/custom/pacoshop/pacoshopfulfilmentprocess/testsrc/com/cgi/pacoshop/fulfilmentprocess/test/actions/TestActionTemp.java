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
package com.cgi.pacoshop.fulfilmentprocess.test.actions;

import de.hybris.platform.core.Registry;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;


/**
 * OOTB test.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 * @param <T>
 *            the type of action.
 */
public class TestActionTemp<T extends BusinessProcessModel> extends AbstractAction<T>
{
	private static final Logger LOG = Logger.getLogger(TestActionTemp.class);

	private String  result         = "OK";
	private boolean throwException = false;


	/**
	 * Returns a result.
	 *
	 * @return result.
	 */
	public String getResult()
	{
		return result;
	}

	/**
	 * Set a result.
	 *
	 * @param result
	 *            result.
	 */
	public void setResult(final String result)
	{
		this.result = result;
	}


	/**
	 * Sets the exception to throw.
	 *
	 * @param throwException
	 *            the exception... to throw.
	 */
	public void setThrowException(final boolean throwException)
	{
		this.throwException = throwException;
	}

	@Override
	public String execute(final T process) throws Exception //NOPMD
	{
		// This call actually puts -this- into a queue.
		try
		{
			if (throwException)
			{
				throw new RuntimeException("Error");
			}
		}
		finally
		{
			LOG.debug("Useless message to ignore the checkstyle error.");
			//getQueueService().actionExecuted(getProcess(process), this);

		}

		LOG.info(result);
		return result;
	}

	@Override
	public Set<String> getTransitions()
	{
		final Set<String> res = new HashSet<>();
		res.add(result);
		return res;
	}

	/**
	 * OOTB method.
	 *
	 * @return the businessProcessService.
	 */
	@SuppressWarnings("static-method")
	protected BusinessProcessService getBusinessProcessService()
	{
		return (BusinessProcessService) Registry.getApplicationContext().getBean("businessProcessService");
	}
}
