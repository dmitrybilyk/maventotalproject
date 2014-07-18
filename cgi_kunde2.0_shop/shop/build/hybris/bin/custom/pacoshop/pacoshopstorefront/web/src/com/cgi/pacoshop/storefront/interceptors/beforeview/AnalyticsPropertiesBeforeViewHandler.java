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
package com.cgi.pacoshop.storefront.interceptors.beforeview;

import com.cgi.pacoshop.storefront.controllers.ThirdPartyConstants;
import com.cgi.pacoshop.storefront.interceptors.BeforeViewHandler;
import de.hybris.platform.acceleratorservices.config.HostConfigService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.util.config.ConfigIntf;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Analytics properties behaviour for view handler.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class AnalyticsPropertiesBeforeViewHandler implements BeforeViewHandler
{
	// HashMap used to cache jirafe properties from config.properties. Not Using Cache implementations as it is
	// a simple,non growable cache s
	private static Map JIRAFE_MAP_CACHE;

	private static final String JIRAFE_API_URL = "jirafeApiUrl";
	private static final String JIRAFE_API_TOKEN = "jirafeApiToken";
	private static final String JIRAFE_APPLICATION_ID = "jirafeApplicationId";
	private static final String JIRAFE_VERSION = "jirafeVersion";
	private static final String ANALYTICS_TRACKING_ID = "googleAnalyticsTrackingId";
	private static final String JIRAFE_DATA_URL = "jirafeDataUrl";
	private static final String JIRAFE_SITE_ID = "jirafeSiteId";
	private static final String JIRAFE_PREFIX = "jirafe";
	private static final String GOOGLE_PREFIX = "googleAnalyticsTrackingId";

	@Resource(name = "hostConfigService")
	private HostConfigService hostConfigService;
	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;
	// Listener - listens to changes on the frontend and update the MapCache.
	private ConfigIntf.ConfigChangeListener cfgChangeListener;

	/**
	 * Before view.
	 *
	 * @param request      Current HTTP request.
	 * @param response     Current HTTP response.
	 * @param modelAndView The <code>ModelAndView</code> that the handler returned.
	 */
	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
	{
		// Create the change listener and register it to listen when the config properties are changed in the platform
		if (cfgChangeListener == null)
		{
			registerConfigChangeLister();
		}
		final String serverName = request.getServerName();
		// Add config properties for google analytics
		addHostProperty(serverName, modelAndView, ThirdPartyConstants.Google.ANALYTICS_TRACKING_ID, ANALYTICS_TRACKING_ID);
		// Add config properties for jirafe analytics
		addHostProperty(serverName, modelAndView, ThirdPartyConstants.Jirafe.API_URL, JIRAFE_API_URL);
		addHostProperty(serverName, modelAndView, ThirdPartyConstants.Jirafe.API_TOKEN, JIRAFE_API_TOKEN);
		addHostProperty(serverName, modelAndView, ThirdPartyConstants.Jirafe.APPLICATION_ID, JIRAFE_APPLICATION_ID);
		addHostProperty(serverName, modelAndView, ThirdPartyConstants.Jirafe.VERSION, JIRAFE_VERSION);
		addHostProperty(serverName, modelAndView, ThirdPartyConstants.Jirafe.DATA_URL, JIRAFE_DATA_URL);

		// Lookup a currency specific jirafe site id first, and only if it is missing fallback to the default site id
		final String currencyIso = commonI18NService.getCurrentCurrency().getIsocode().toLowerCase();
		final String currSpecKey = ThirdPartyConstants.Jirafe.SITE_ID + "." + currencyIso;
		final String nonSpecKey = ThirdPartyConstants.Jirafe.SITE_ID;
		if (JIRAFE_MAP_CACHE.get(currSpecKey) == null)
		{
			final String currencySpecificJirafeSiteId = hostConfigService.getProperty(currSpecKey, serverName);
			JIRAFE_MAP_CACHE.put(currSpecKey, currencySpecificJirafeSiteId);
		}
		if (JIRAFE_MAP_CACHE.get(currSpecKey) != null
				&& org.apache.commons.lang.StringUtils.isNotBlank(JIRAFE_MAP_CACHE.get(currSpecKey).toString()))
		{
			modelAndView.addObject(JIRAFE_SITE_ID, JIRAFE_MAP_CACHE.get(currSpecKey));
		}
		else
		{
			// Fallback to the non-currency specific value
			if (JIRAFE_MAP_CACHE.get(nonSpecKey) == null)
			{
				final String jirafeSiteId = hostConfigService.getProperty(ThirdPartyConstants.Jirafe.SITE_ID, serverName);
				JIRAFE_MAP_CACHE.put(nonSpecKey, jirafeSiteId);
			}
			modelAndView.addObject(JIRAFE_SITE_ID, JIRAFE_MAP_CACHE.get(nonSpecKey));
		}
	}

	/**
	 * Register config change lister.
	 */
	protected void registerConfigChangeLister()
	{
		final ConfigIntf config = Registry.getMasterTenant().getConfig();
		cfgChangeListener = new ConfigChangeListener();
		config.registerConfigChangeListener(cfgChangeListener);
	}

	/**
	 * Add host property.
	 *
	 * @param serverName   Server name.
	 * @param modelAndView Model and view.
	 * @param configKey    Configuration key.
	 * @param modelKey     Model key.
	 */
	protected void addHostProperty(final String serverName, final ModelAndView modelAndView, final String configKey,
								   final String modelKey)
	{
		/*
		 * Changes made to cache the jirafe properties files in a HashMap. The first time the pages are accessed the
		 * values are read from the properties file & written on to a cache and the next time onwards it is accessed from
		 * the cache.
		 */
		if (JIRAFE_MAP_CACHE == null)
		{
			JIRAFE_MAP_CACHE = new HashMap();
		}
		if (JIRAFE_MAP_CACHE.get(configKey) == null)
		{
			JIRAFE_MAP_CACHE.put(configKey, hostConfigService.getProperty(configKey, serverName));
		}
		modelAndView.addObject(modelKey, JIRAFE_MAP_CACHE.get(configKey));
	}

	/**
	 * Config change listener.
	 */
	protected static class ConfigChangeListener implements ConfigIntf.ConfigChangeListener
	{
		@Override
		public void configChanged(final String key, final String newValue)
		{
			// Config Listener listen to changes on the platform config and updates the cache.
			if (key.startsWith(JIRAFE_PREFIX) || key.startsWith(GOOGLE_PREFIX))
			{
				JIRAFE_MAP_CACHE.remove(key);
				JIRAFE_MAP_CACHE.put(key, newValue);
			}
		}
	}
}
