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
package com.cgi.pacoshop.storefront.security.web.access.channel;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.springframework.security.web.access.channel.RetryWithHttpEntryPoint;

import com.cgi.pacoshop.storefront.constants.WebConstants;


/**
 *	UAGCompliantRetryWithHttpEntryPoint.
 *
 * @module storefront
 * @version 1.0v Jan 01, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Jan Grathwohl <jan.grathwohl@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class UAGCompliantRetryWithHttpEntryPoint extends RetryWithHttpEntryPoint
{


	/**
	 * Overrides the method to always return the UAG's http port, as specified in the platform configuration.
	 * 
	 * @param mapFromPort Integer param
	 * @see WebConstants#CONFIG_KEY_UAG_HTTP_PORT
	 * @return mapFromPort
	 */
	@SuppressWarnings("boxing")
	@Override
	protected Integer getMappedPort(final Integer mapFromPort)
	{
		final ConfigurationService configurationService = Registry.getApplicationContext().getBean("configurationService",
				ConfigurationService.class);

		final String uagHttpPort = configurationService.getConfiguration().getString(WebConstants.CONFIG_KEY_UAG_HTTP_PORT);

		return Integer.parseInt(uagHttpPort);
	}

}
