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
package com.cgi.pacoshop.storefront.forms.payment.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import com.cgi.pacoshop.storefront.forms.payment.MerchantCallbackUrlStrategy;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


@Component("merchantCallbackUrlStrategy")
public class DefaultMerchantCallbackUrlStrategy implements MerchantCallbackUrlStrategy
{
	@Resource
	private ConfigurationService configService;

	@Override
	public String getUrl()
	{
		return configService.getConfiguration().getString("merchant.callback.url", "");
	}
}
