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

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.subscriptionservices.subscription.SubscriptionCommerceCartService;

import java.util.List;


/**
 * Service extends subscription commerce cart service.
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
public interface PacoshopSubscriptionCommerceCartService extends SubscriptionCommerceCartService
{
	/**
	 * Add product to cart.
	 * 
	 * @param cart
	 *           the shopping cart
	 * @param product
	 *           the product to be added to cart.
	 * @return list of {@link CommerceCartModification}.
	 * @throws CommerceCartModificationException
	 *            the {@link CommerceCartModificationException}
	 */
	CommerceCartModification addToCart(final CartModel cart, final ProductModel product) throws CommerceCartModificationException;

	/**
	 * Add products to cart.
	 * 
	 * @param cart
	 *           the shopping cart.
	 * @param products
	 *           the products to be added to cart.
	 * @param redirectUrl
	 *           the redirect url.
	 * @param redirectUrlDesc
	 *           the redirect url description.
	 * @param contentId
	 *           the content id.
	 * @param contentPlatformId
	 *           the content platform id.
	 * @param contentTitle
	 *           the content title.
	 * @param bonusImageUrl
	 *           the bonus image url.
	 * @param productImageUrl
	 *           the product image url.
	 * @return list of {@link CommerceCartModification}.
	 */
	List<CommerceCartModification> addProductsToCart(final CartModel cart, final List<ProductModel> products,
			final String redirectUrl, final String redirectUrlDesc, final String contentId, final String contentPlatformId,
			final String contentTitle,
			final String bonusImageUrl, final String productImageUrl);
}
