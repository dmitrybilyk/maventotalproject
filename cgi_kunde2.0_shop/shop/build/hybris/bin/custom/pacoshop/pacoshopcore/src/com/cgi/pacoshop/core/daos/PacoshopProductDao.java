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
package com.cgi.pacoshop.core.daos;


import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.enums.ProductImportSource;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;
import java.util.Collection;
import java.util.List;

/**
 * Base Service for persistence layer
 *
 *
 * @module Pacoshopcore
 * @version 1.0v Dec 05, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see          "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public interface PacoshopProductDao extends ProductDao
{

	/**
	 * Returns for the given product <code>code</code> the {@link de.hybris.platform.core.model.product.ProductModel} collection.
	 *
	 * @param externalOfferId
	 *           the product <code>code</code>
	 * @return a List of {@link de.hybris.platform.core.model.product.ProductModel}s
	 */
	Collection<ProductModel> findSingleProductsByExternalOfferId(final String externalOfferId);

	/**
	 *  Returns for the given product <code>externalOfferId</code> the
	 *  {@link de.hybris.platform.core.model.product.ProductModel} collection.
	 *
	 * @param catalogVersion the version of catalog.
	 * @param externalOfferId the externalOfferId.
	 * @param offerOrigin the offerOriginCode.
	 * @return Collection of ProductModel.
	 */
	Collection<ProductModel> findProductsByOffer(final CatalogVersionModel catalogVersion,
																final String externalOfferId, final OfferOrigin offerOrigin);

	/**
	 * Returns for the given product <code>code</code> the {@link ProductModel} collection.
	 *
	 * @param code
	 *           the product <code>code</code>
	 * @param catalogVersion - catalog version.
	 * @return a {@link ProductModel}
	 */
	List<ProductModel> findProductsByCode(final CatalogVersionModel catalogVersion, final String code);

	/**
	 * Get existing  products stored in the database of the shop.
	 *
	 * @return a list of SAP SD products
	 * @see com.cgi.pacoshop.core.daos.PacoshopProductDao
	 * @param catalogVersion - catalog version.
	 */
	Collection<ProductModel> findSingleProducts(final String catalogVersion);

	/**
	 * Get existing  products stored in the database of the shop.
	 *
	 * @return a list of SAP SD products
	 * @see com.cgi.pacoshop.core.daos.PacoshopProductDao
	 * @param catalogVersion - catalog version.
	 * @param importSource - source from were product comes.
	 */
	Collection<ProductModel> findProductsByImportSource(String catalogVersion, ProductImportSource importSource);
}
