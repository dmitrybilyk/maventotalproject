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


import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.enums.ProductImportSource;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import java.util.Collection;
import java.util.List;

/**
 * Service to read and update Pacoshop {@link ProductModel ProductModel}s
 *
 * @module Pacoshopcore
 * @version 1.0v Dec 05, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see          "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public interface PacoshopProductService extends ProductService
{

	/**
	 * Returns the Product with the specified externalOfferId. As default the search uses the current session user, the current
	 * session language and the current active catalog versions (which are stored at the session in the attribute
	 * {@link de.hybris.platform.catalog.constants.CatalogConstants#SESSION_CATALOG_VERSIONS SESSION_CATALOG_VERSIONS}).
	 * For modifying the search session context see {@link de.hybris.platform.servicelayer.search.FlexibleSearchQuery
	 * FlexibleSearchQuery}.
	 *
	 * @param externalOfferId
	 *           the externalOfferId of the Product
	 * @return the Product with the specified code.
	 */
	List<ProductModel> findProductByExternalOfferId(String externalOfferId);

	/**
	 *  Returns for the given product <code>externalOfferId</code> the
	 *  {@link de.hybris.platform.core.model.product.ProductModel} collection.
	 *
	 * @param catalogVersion the version of catalog.
	 * @param externalOfferId the externalOfferId.
	 * @param offerOrigin the offerOriginCode.
	 * @return ProductModel.
	 */
	ProductModel findProductsByOffer(CatalogVersionModel catalogVersion, String externalOfferId, OfferOrigin offerOrigin);

	/**
	 * Find product by product code and catalog version.
	 * @param catalogVersion
	 *           the CatalogVersion of the Product
	 *
	 * @param code
	 *           the code of the Product
	 *
	 * @return product model.
	 */
	ProductModel getProductForCode(CatalogVersionModel catalogVersion, String code);

	/**
	 * Find Products by catalog version.
	 * @param catalogVersion
	 *           the CatalogVersion of the Product
	 * @return List of products.
	 */
	Collection<ProductModel> findProducts(String catalogVersion);

	/**
	 * Find Products by catalog version and importSource.
	 * @param catalogVersion
	 *           the CatalogVersion of the Product
	 * @param importSource
	 * 			 importSource of the product
	 * @return List of products.
	 */
	Collection<ProductModel> findProductsByImportSource(String catalogVersion, ProductImportSource importSource);

}
