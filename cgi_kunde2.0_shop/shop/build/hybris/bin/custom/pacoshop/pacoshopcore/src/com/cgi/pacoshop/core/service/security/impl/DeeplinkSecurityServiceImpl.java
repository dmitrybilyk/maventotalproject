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


import com.cgi.pacoshop.core.exceptions.deeplink.status400.DeeplinkNotSecureException;
import com.cgi.pacoshop.core.exceptions.deeplink.status403.DeeplinkCompromizedException;
import com.cgi.pacoshop.core.model.FingerprintSecretModel;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.security.DeeplinkFingerprintSecretService;
import com.cgi.pacoshop.core.service.security.DeeplinkSecurityService;
import com.cgi.pacoshop.core.strategies.FingerprintCalculationStrategy;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

/**
 * Service gets fingerprint parameters from request and DB and calls util class for making comparison between fingerprint hashes.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class DeeplinkSecurityServiceImpl implements DeeplinkSecurityService
{
	private static final Logger LOG = Logger.getLogger(DeeplinkSecurityServiceImpl.class);
	//@Resource
	private DeeplinkFingerprintSecretService deeplinkFingerprintSecretService;

	private FingerprintCalculationStrategy fingerprintCalculationStrategy;

	/**
	 * Validates fingerprint from portal with calculated hybris fingerprint.
	 *
	 * @param request - httpServletRequest
	 */
	@Override
	public void validate(final HttpServletRequest request)
	{
		final String portalFingerpint = request.getParameter(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM);
		final String fingerprintSecretNo = request.getParameter(ShopConfigurationService.FINGERPRINT_SECRET_NO_REQUEST_PARAM);

		if (StringUtils.isEmpty(portalFingerpint) || StringUtils.isEmpty(fingerprintSecretNo))
		{
			String messageTmpl =
					  String.format(
								 "Mandatory parameters for deeplink: fingerprint = {%s} fingerprintsecretno = {%s}",
								 portalFingerpint, fingerprintSecretNo);
			throw new DeeplinkNotSecureException(messageTmpl);
		}

		final FingerprintSecretModel hybrisFingerprintSecretModel =
				  getDeeplinkFingerprintSecretService().getFingerprintSecretByNumber(fingerprintSecretNo);

		if (hybrisFingerprintSecretModel == null)
		{
			String messageTmpl = String.format("Can't find fingerprint for fingerprintsecretno = { %s }", fingerprintSecretNo);
			error(LOG, messageTmpl);
			throw new DeeplinkCompromizedException(messageTmpl);
		}

		final String hybrisFingerprintSecret = hybrisFingerprintSecretModel.getSecret();

		final String hybrisFingerprint =
				  getFingerprintCalculationStrategy().calculateDeeplinkFingerprint(request, hybrisFingerprintSecret);

		if (!portalFingerpint.equals(hybrisFingerprint))
		{
			String messageTmpl = String.format("Portal fingerprint is not valid = { %s }", portalFingerpint);
			error(LOG, messageTmpl);
			throw new DeeplinkCompromizedException(messageTmpl);
		}
	}

	/**
	 * Gets deeplink fingerprint secret service.
	 *
	 * @return deeplinkFingerprintSecretService deeplink fingerprint secret service
	 */
	public DeeplinkFingerprintSecretService getDeeplinkFingerprintSecretService()
	{
		return deeplinkFingerprintSecretService;
	}

	/**
	 * Sets deeplink fingerprint secret service.
	 *
	 * @param deeplinkFingerprintSecretService - service for fingerprint secret.
	 */
	@Required
	public void setDeeplinkFingerprintSecretService(final DeeplinkFingerprintSecretService deeplinkFingerprintSecretService)
	{
		this.deeplinkFingerprintSecretService = deeplinkFingerprintSecretService;
	}


	/**
	 * Gets fingerprint calculation strategy.
	 *
	 * @return instance of FingerprintCalculationStrategy
	 */
	public FingerprintCalculationStrategy getFingerprintCalculationStrategy()
	{
		return fingerprintCalculationStrategy;
	}

	/**
	 * Sets fingerprint calculation strategy.
	 *
	 * @param fingerprintCalculationStrategy - FingerprintCalculationStrategy
	 */
	@Required
	public void setFingerprintCalculationStrategy(final FingerprintCalculationStrategy fingerprintCalculationStrategy)
	{
		this.fingerprintCalculationStrategy = fingerprintCalculationStrategy;
	}
}
