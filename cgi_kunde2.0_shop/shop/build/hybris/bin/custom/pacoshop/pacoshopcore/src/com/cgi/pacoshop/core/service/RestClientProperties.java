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
package com.cgi.pacoshop.core.service;


import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

/**
 * Helper class for storage and inject properties into RestClient for establishing http connection
 * Constructor's arguments populate in pacoshopcore-spring config
 *
 * @module pacoshopcore
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *	@see "https://wiki.hybris.com/dashboard.action"
 *
 */
public class RestClientProperties
{
	public static final String CONFIG_KEY_REST_URL = "rest.url";
	private ConfigurationService configurationService;

	/**
	 * Default constructor.
	 */
	public RestClientProperties()
	{
	}

	/**
	 * configurationService Setter.
	 * @param configurationServiceParam - configurationService to be set.
	 */
	@Required
	public void setConfigurationService(final ConfigurationService configurationServiceParam)
	{
		this.configurationService = configurationServiceParam;
	}

	/**
	 * Get URL from configuration file.
	 * @return URL from configuration file.
	 */
	public String getUrl()
	{
		Assert.notNull(CONFIG_KEY_REST_URL, "Rest URL is null");
		return configurationService.getConfiguration().getString(CONFIG_KEY_REST_URL);
	}

}
