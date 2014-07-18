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

import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.warn;

import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.access.channel.InsecureChannelProcessor;
import org.springframework.security.web.access.channel.RetryWithHttpEntryPoint;

import com.cgi.pacoshop.storefront.constants.WebConstants;


/**
 *	UAGCompliantInsecureChannelProcessor.
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
public class UAGCompliantInsecureChannelProcessor extends InsecureChannelProcessor
{


	private static final Logger LOG = Logger.getLogger(UAGCompliantInsecureChannelProcessor.class);


	/**
	 * UAGCompliantInsecureChannelProcessor constructor.
	 */
	public UAGCompliantInsecureChannelProcessor()
	{
		super();

		setEntryPoint(new UAGCompliantRetryWithHttpEntryPoint());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.access.channel.InsecureChannelProcessor#decide(org.springframework.security.web
	 * .FilterInvocation, java.util.Collection)
	 */
	@Override
	public void decide(final FilterInvocation invocation, final Collection<ConfigAttribute> config) throws IOException,
			ServletException
	{
		final ConfigurationService configurationService = Registry.getApplicationContext().getBean("configurationService",
				ConfigurationService.class);
		final Configuration configuration = configurationService.getConfiguration();

		final String headerName = configuration.getProperty(WebConstants.CONFIG_KEY_UAG_HEADER_NAME_FORWARDED_PROTO).toString();
		final String insecureProtocol = configuration.getProperty(WebConstants.CONFIG_KEY_UAG_HEADER_FORWARDED_PROTO_HTTP)
				.toString();

		for (final ConfigAttribute attribute : config)
		{
			if (supports(attribute))
			{
				final String header = invocation.getHttpRequest().getHeader(headerName);

				if (header != null)
				{
					debug(LOG, "Forward protocol header with name [%s] has value [%s]", headerName, header);

					if (!header.equalsIgnoreCase(insecureProtocol))
					{
						getEntryPoint().commence(invocation.getRequest(), invocation.getResponse());
					}
				}
				else
				{
					warn(LOG, "Forward protocol header is missing - no header with name [%s] found in request", headerName);
				}
			}
		}
	}


	/**
	 *
	 * @param portMapper PortMapper
	 */
	public void setPortMapper(final PortMapper portMapper)
	{
		final RetryWithHttpEntryPoint entryPoint = (RetryWithHttpEntryPoint) getEntryPoint();
		entryPoint.setPortMapper(portMapper);
	}

}
