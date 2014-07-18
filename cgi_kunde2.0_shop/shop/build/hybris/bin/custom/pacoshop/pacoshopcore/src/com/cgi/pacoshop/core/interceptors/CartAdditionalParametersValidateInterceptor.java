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
package com.cgi.pacoshop.core.interceptors;


import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;


/**
 * Intercepts and controls persistence of CartEntry when customer checkouts Online Article.
 * 
 * @module pacoshopcore
 * @version 1.0v Jan 31, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class CartAdditionalParametersValidateInterceptor implements ValidateInterceptor
{
	@Resource
	private ConfigurationService configurationService;

	@Override
	public void onValidate(final Object model, final InterceptorContext interceptorContext) throws InterceptorException
	{
		validateParameterNotNull(model, "Parameter model must not be null");

		if (model instanceof CartEntryModel)
		{
			final CartEntryModel entryModel = (CartEntryModel) model;

			if (hasOnlineArticle(entryModel) && entryModel.getOrder() != null)
			{
				final CartModel cartModel = entryModel.getOrder();

				if (!hasMandatoryUrlParameters(cartModel, entryModel))
				{
					throw new InterceptorException(String.format(
							"Invalid url for onlinearticle checkout, there are mandatory parameters - "
									+ "redirectUrl: %s, contentPlatformId: %s, contentTitle: %s, contentId: %s",
							cartModel.getRedirectUrl(), entryModel.getContentPlatformId(), entryModel.getContentTitle(),
							entryModel.getContentId()));
				}
			}
		}
	}

	private boolean hasOnlineArticle(final AbstractOrderEntryModel model)
	{
		final String onlineArticle = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);
		return model.getProduct().getProductType().getCode().equals(onlineArticle);
	}

	private boolean hasMandatoryUrlParameters(final CartModel cartModel, final AbstractOrderEntryModel entryModel)
	{
		return StringUtils.isNotEmpty(cartModel.getRedirectUrl()) && StringUtils.isNotEmpty(entryModel.getContentPlatformId())
				&& StringUtils.isNotEmpty(entryModel.getContentTitle()) && StringUtils.isNotEmpty(entryModel.getContentId());
	}

	private String getConfigurationProperty(final String key)
	{
		final Configuration configuration = configurationService.getConfiguration();
		return configuration.getString(key);
	}
}
