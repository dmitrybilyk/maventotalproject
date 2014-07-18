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
package com.cgi.pacoshop.fulfilmentprocess.test.beans;

import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.processengine.model.BusinessProcessModel;


/**
 * OOTB test.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class ActionExecution
{
	private final BusinessProcessModel process;
	@SuppressWarnings("rawtypes")
	private final AbstractAction       action;

	/**
	 * OOTB class.
	 *
	 * @param process
	 *            the process.
	 * @param action
	 *            the action.
	 */
	@SuppressWarnings("rawtypes")
	public ActionExecution(final BusinessProcessModel process, final AbstractAction action)
	{
		this.process = process;
		this.action = action;
	}

	/**
	 * OOTB class.
	 *
	 * @return the action.
	 */
	@SuppressWarnings("rawtypes")
	public AbstractAction getAction()
	{
		return action;
	}

	/**
	 * OOTB class.
	 *
	 * @return the process.
	 */
	public BusinessProcessModel getProcess()
	{
		return process;
	}

}
