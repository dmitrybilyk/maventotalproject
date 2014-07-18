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
package com.cgi.pacoshop.core.service.security.impl;


import com.cgi.pacoshop.core.daos.DeeplinkFingerprintSecretDao;
import com.cgi.pacoshop.core.model.FingerprintSecretModel;
import com.cgi.pacoshop.core.service.security.DeeplinkFingerprintSecretService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * Implementation of search service for DeeplinkFingerprintModel.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DeeplinkFingerprintSecretServiceImpl implements DeeplinkFingerprintSecretService
{
	private static final Logger LOG = Logger.getLogger(DeeplinkFingerprintSecretServiceImpl.class);
	@Resource
	private DeeplinkFingerprintSecretDao deeplinkFingerprintSecretDao;

	@Override
	public FingerprintSecretModel getFingerprintSecretByNumber(final String fingerPrintSecretNo)
	{
		try
		{
			return deeplinkFingerprintSecretDao.getFingerprintSecretByNumber(fingerPrintSecretNo);
		}
		catch (ModelNotFoundException e)
		{
			debug(LOG, "%s", e.getMessage());
		}
		return null;
	}
}
