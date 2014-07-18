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
package com.cgi.pacoshop.fulfilmentprocess.jalo;

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;

import org.apache.log4j.Logger;


/**
 * OOTB processManager.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@SuppressWarnings("PMD")
public class PacoshopFulfilmentProcessManager extends GeneratedPacoshopFulfilmentProcessManager
{
	@SuppressWarnings("unused")
	private static Logger LOG = Logger.getLogger(PacoshopFulfilmentProcessManager.class.getName());

	/**
	 * Get the instance.
	 *
	 * @return the instance.
	 */
	public static final PacoshopFulfilmentProcessManager getInstance()
	{
		final ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (PacoshopFulfilmentProcessManager) em.getExtension(PacoshopFulfilmentProcessConstants.EXTENSIONNAME);
	}

}
