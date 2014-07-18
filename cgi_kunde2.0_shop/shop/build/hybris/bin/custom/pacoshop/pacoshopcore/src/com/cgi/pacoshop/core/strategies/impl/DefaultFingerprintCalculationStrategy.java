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
package com.cgi.pacoshop.core.strategies.impl;


import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.strategies.FingerprintCalculationStrategy;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * Strategy to calculate fingerprint for deeplink security.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 10, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultFingerprintCalculationStrategy implements FingerprintCalculationStrategy
{
	protected static final Logger   LOG                           = Logger.getLogger(DefaultFingerprintCalculationStrategy.class);

	private final static   String[] DEEPLINK_PARAMETERS_TO_CALCULATE = {"productid", "contentid", "contentplatformid"
			  , "contenttitle", "contenturl", "productimageurl", "bonusimageurl", "productid2", "rendermode", "redirecturl",
			  "offerid ", "offerorigin ", "offerid2", "offerorigin2", "campaignid", "fingerprintsecretno"};

	private Set<String> deeplinkParametersToCalculate;

	/**
	 *
	 */
	public DefaultFingerprintCalculationStrategy()
	{
		this.deeplinkParametersToCalculate = new HashSet<>(Arrays.asList(DEEPLINK_PARAMETERS_TO_CALCULATE));
	}

	/**
	 *
	 * @param request - HttpServletRequest
	 * @param fingerprintSecret - fingerprintSecret parameter from local DB
	 * @return calculated fingerprint(40-digit string) with shaHex algorithm
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String calculateDeeplinkFingerprint(final HttpServletRequest request, final String fingerprintSecret)
	{
		final HashMap<String, Object> parametersWoFingerprint = new HashMap<String, Object>(request.getParameterMap());

		if (parametersWoFingerprint.containsKey(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM))
		{
			parametersWoFingerprint.remove(ShopConfigurationService.FINGERPRINT_REQUEST_PARAM);
		}

		// collect a fingerprint source string to calculate SHA1 checksum
		List<String> paramNames = new ArrayList<>(parametersWoFingerprint.keySet());
		Collections.sort(paramNames);
		final StringBuilder fingerprintSource = new StringBuilder(fingerprintSecret);
		for (String paramName : paramNames)
		{
			if (allowedToCalculate(paramName))
			{
				fingerprintSource.append(":").append(paramName).append("=").append(request.getParameter(paramName));
			}
		}

		final String fingerprintSourceStr = fingerprintSource.toString();
		debug(LOG, "Fingerprintsource = { %s } ", fingerprintSourceStr);

		final String calculatedFingerprint = DigestUtils.shaHex(fingerprintSourceStr);
		debug(LOG, "Calculated fingerprintsource with shaHex = { %s }", calculatedFingerprint);

		return calculatedFingerprint;
	}

	private boolean allowedToCalculate(final String deeplinkParameter)
	{
		return getDeeplinkParametersToCalculate().contains(deeplinkParameter);
	}

	/**
	 *
	 * @return deeplinkParametersToCalculate
	 */
	public Set<String> getDeeplinkParametersToCalculate()
	{
		return deeplinkParametersToCalculate;
	}

	/**
	 *
	 * @param deeplinkParametersToCalculate - deeplinkParametersToCalculate
	 */
	public void setDeeplinkParametersToCalculate(final Set<String> deeplinkParametersToCalculate)
	{
		this.deeplinkParametersToCalculate = deeplinkParametersToCalculate;
	}
}

