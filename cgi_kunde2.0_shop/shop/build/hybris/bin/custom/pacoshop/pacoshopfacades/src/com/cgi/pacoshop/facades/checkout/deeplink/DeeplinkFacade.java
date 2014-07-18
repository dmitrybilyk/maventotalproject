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
package com.cgi.pacoshop.facades.checkout.deeplink;


import com.cgi.pacoshop.core.model.ProductDTO;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;


/**
 * Facade to provide the logic for the Deeplink controller
 *
 * @module hybris - pacoshopfacades
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public interface DeeplinkFacade
{

	/**
	 * Find and validate a product in online catalog of current website.
     * The product is INVALID when:
     * it does not exist in the hybris catalogue belonging to the site
     * it is not active
     * it should not be sold online yet ("onlineDate" <= current date <= "offlineDate")
     * when bought with a prohibited amount ("minimumQuantity" <= number of product instances in the cart <= "maximumQuantity").
     * if the subscription price plan is missing
	 * @param productDTOList            - productDTO in hybris
	 * @return productModel in OnlineCatalog of current Website
	 */
	List<ProductModel> findProductInOnlineCatalogOfCurrentWebsite(List<ProductDTO> productDTOList);


	/**
	 * Create cart with products.
	 *
	 * @param products            the products
	 * @param redirectUrl            - - redirectUrl from external system hybris
	 * @param contentId            - - contentId from external system
	 * @param contentPlatformId            - - contentPlatformId from external system
	 * @param contentTitle            - - contentTitle from external system
	 * @param bonusImageUrl            - - bonusImageUrl from external system
	 * @param productImageUrl            - - productImageUrl from external system
	 * @return  - cart with products
	 */
	CartModel createCartWithProducts(List<ProductModel> products, String redirectUrl, String contentId, String contentPlatformId,
                                     String contentTitle, String bonusImageUrl, String productImageUrl);


	/**
	 * For a valid "Kombinangebot", all products should be subscription.
	 *
	 *
	 * @param products            the products
	 * @return true /false
	 */
	boolean isValidKombiAbo(final List<? extends ProductModel> products);





}
