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

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.PortMapperImpl;
import org.springframework.security.web.access.channel.ChannelDecisionManagerImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Post-processes the ChannelDecisionManager to replace the default channel processors with our own, UAG-compliant
 * implementation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Jan Grathwohl <jan.grathwohl@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 01, 2014
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class ChannelProcessorsBeanPostProcessor implements BeanPostProcessor
{
	/**
	 * Default logger.
	 */
	private static final Logger LOG = Logger.getLogger(ChannelProcessorsBeanPostProcessor.class);

	/**
	 * Map with additional port mappings.
	 */
	private Map<String, String> portMappings;

	@Override
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException
	{

		if (bean instanceof ChannelDecisionManagerImpl)
		{
			LOG.info("Post-processing " + beanName + ": " + bean + " to replace default channel processors");

			final PortMapper newPortMapper = createNewPortMapper();

			final UAGCompliantSecureChannelProcessor secureChannelProcessor = new UAGCompliantSecureChannelProcessor();
			secureChannelProcessor.setPortMapper(newPortMapper);

			final UAGCompliantInsecureChannelProcessor insecureChannelProcessor = new UAGCompliantInsecureChannelProcessor();
			insecureChannelProcessor.setPortMapper(newPortMapper);


			((ChannelDecisionManagerImpl) bean)
					.setChannelProcessors(Arrays.asList(secureChannelProcessor, insecureChannelProcessor));

		}

		return bean;
	}

	@SuppressWarnings("boxing")
	private PortMapper createNewPortMapper()
	{
		final PortMapperImpl newPortMapper = new PortMapperImpl();

		final Map<String, String> newMapping = new HashMap<String, String>();

		// Keep the default mappings from the portmapper (add them to the new list again)
		final Map<Integer, Integer> defaultMappings = newPortMapper.getTranslatedPortMappings();
		final Set<Map.Entry<Integer, Integer>> entries = newPortMapper.getTranslatedPortMappings().entrySet();
		for (final Map.Entry<Integer, Integer> entry : entries)
		{
			Integer key = entry.getKey();
			newMapping.put(Integer.toString(key), Integer.toString(defaultMappings.get(key)));
		}

		// Then add the additional mappings
		for (final String key : portMappings.keySet())
		{
			newMapping.put(key, portMappings.get(key));
		}

		newPortMapper.setPortMappings(newMapping);

		return newPortMapper;
	}

	@Override
	public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException
	{
		return bean;
	}

	/**
	 * @return the portMappings
	 */
	public Map<String, String> getPortMappings()
	{
		return portMappings;
	}

	/**
	 * @param pPortMappings the portMappings to set
	 */
	public void setPortMappings(final Map<String, String> pPortMappings)
	{
		this.portMappings = pPortMappings;
	}
}
