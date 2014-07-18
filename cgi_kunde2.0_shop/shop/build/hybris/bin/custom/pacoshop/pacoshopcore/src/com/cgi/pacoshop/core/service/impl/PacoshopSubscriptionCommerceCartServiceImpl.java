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
package com.cgi.pacoshop.core.service.impl;

import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.subscription.impl.DefaultSubscriptionCommerceCartService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.cgi.pacoshop.core.service.PacoshopSubscriptionCommerceCartService;


/**
 * Implementation class of PacoshopSubscriptionCommerceCartService.
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v Mar 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class PacoshopSubscriptionCommerceCartServiceImpl extends DefaultSubscriptionCommerceCartService implements
		PacoshopSubscriptionCommerceCartService
{
	private static final Logger LOG = Logger.getLogger(PacoshopSubscriptionCommerceCartServiceImpl.class);
	private static final String EMPTY_STRING = "";

	private ModelService modelService;

	@Override
	public CommerceCartModification addToCart(final CartModel cart, final ProductModel product)
			throws CommerceCartModificationException
	{
		final CommerceCartModification cartModification = super.addToCart(cart, product, 1L, product.getUnit(), true, null);
		debug(LOG, "Add product [%s]", product.getCode());
		return cartModification;
	}

	@Override
	public List<CommerceCartModification> addProductsToCart(final CartModel cart, final List<ProductModel> products,
			final String redirectUrl, final String redirectUrlDesc, final String contentId, final String contentPlatformId,
			final String contentTitle,
			final String bonusImageUrl, final String productImageUrl)
	{
		final List<CommerceCartModification> cartModifications = addProductToCart(cart, products, contentId, contentPlatformId,
				contentTitle, bonusImageUrl, productImageUrl);

		cart.setRedirectUrl(redirectUrl);
		cart.setRedirectUrlDescription(redirectUrlDesc);
		cart.setContentPlatformId(contentPlatformId);
		getModelService().save(cart);

		return cartModifications;
	}


	/**
	 * Adds products collection to a cart.
	 * @param cart Cart object
	 * @param products Products to add
	 * @param contentId Content id from deeplink
	 * @param contentPlatformId Content platform id
	 * @param contentTitle Content title
	 * @param bonusImageUrl URL of bonus image
	 * @param productImageUrl URL of product
	 * @return Collection of CommerceCartModification objects
	 */
	protected List<CommerceCartModification> addProductToCart(final CartModel cart, final List<? extends ProductModel> products,
			final String contentId, final String contentPlatformId, final String contentTitle, final String bonusImageUrl,
			final String productImageUrl)
	{
		//one bonusImage for Kombi.
		String changedBonusImageUrl = bonusImageUrl;
		final List<CommerceCartModification> cartModifications = new ArrayList();
		if (CollectionUtils.isNotEmpty(products))
		{
			for (final ProductModel product : products)
			{
				try
				{
					final CommerceCartModification commerceCartModification = addToCart(cart, product);
					cartModifications.add(commerceCartModification);

					// Set entry attributes
					final AbstractOrderEntryModel entry = commerceCartModification.getEntry();
					entry.setBonusImageUrl(changedBonusImageUrl);
					entry.setProductImageUrl(productImageUrl);
					entry.setContentTitle(contentTitle);
					entry.setContentId(contentId);
					entry.setContentPlatformId(contentPlatformId);
					getModelService().save(entry);

					if (bonusImageUrl != null)
					{
						changedBonusImageUrl = EMPTY_STRING;
					}
				}
				catch (final CommerceCartModificationException e)
				{
					error(LOG, "CommerceCartModificationException is caught while adding prodcut [%s]", e, product.getCode());
				}
			}
		}
		return cartModifications;
	}

	/**
	 * @return the modelService
	 */
	@Override
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}
}
