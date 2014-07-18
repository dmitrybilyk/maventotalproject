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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.util.Assert;

/**
 * Utility class for media container, product and subscription plans identifiers generation
 *
 * @module hybris
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public final class KeyGenerationUtils
{

	public static final String MEDIA_KEY_FORMAT    = "%s%s%s";
	public static final String PRODUCT_CODE_FORMAT = "%s:%s";
	public static final String SUBS_TERM           = "subsTerm";
	public static final String CONS_SUBS_TERM = "consSubsTerm";

	private KeyGenerationUtils()
	{
	}

	/**
	 * Builds the string key for the Media object attached to a Product.
	 *
	 * @param externalOfferId product external offer id
	 * @param offerOrigin product offer origin
	 * @param mcmQualifier media container qualifier
	 * @return Unique key for the MediaModel
	 */
	public static String getMediaKeyForProduct(final String externalOfferId, final String offerOrigin, final String mcmQualifier)
	{
		Assert.notNull(externalOfferId);
		Assert.notNull(offerOrigin);
		Assert.notNull(mcmQualifier);

		return String.format(MEDIA_KEY_FORMAT, offerOrigin, externalOfferId, mcmQualifier);
	}

	/**
	 * Builds the string key for the Product object.
	 *
	 * @param dto SingleProductDTO or its subclass
	 * @return Unique product code
	 */
	public static String getProductCode(final SingleProductDTO dto)
	{
		Assert.notNull(dto);

		return String.format(PRODUCT_CODE_FORMAT, dto.getOfferOrigin(), dto.getOfferId());
	}

	/**
	 * Builds the string key for the SubscriptionTerm object.
	 *
	 * @param dto SingleProductDTO or its subclass
	 * @return Unique SubscriptionTerm object code
	 */
	public static String getSubscriptionTermId(final SubscriptionProductDTO dto)
	{
		Assert.notNull(dto);
		return SUBS_TERM + getProductCode(dto);
	}

	/**
	 * Builds the string key for the SubscriptionTerm object.
	 *
	 * @param dto SingleProductDTO or its subclass
	 * @return Unique SubscriptionTerm object code
	 */
	public static String getConsequitiveSubscriptionTermId(final SubscriptionProductDTO dto)
	{
		Assert.notNull(dto);
		return CONS_SUBS_TERM + getProductCode(dto);
	}
}
