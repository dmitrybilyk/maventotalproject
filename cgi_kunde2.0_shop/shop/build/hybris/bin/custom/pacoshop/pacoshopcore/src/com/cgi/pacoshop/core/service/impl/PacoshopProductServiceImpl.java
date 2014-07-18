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


import com.cgi.pacoshop.core.daos.PacoshopProductDao;
import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.core.service.PacoshopProductService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static java.lang.String.format;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

/**
 * Product service
 *
 * @module Pacoshopcore
 * @version 1.0v Dec 05, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see          "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class PacoshopProductServiceImpl extends DefaultProductService implements PacoshopProductService
{

	private PacoshopProductDao pacoshopProductDao;

	@Override
	public List<ProductModel> findProductByExternalOfferId(final String externalOfferId)
	{
		validateParameterNotNull(externalOfferId, "Parameter externalOfferId must not be null");

		final List<ProductModel> products =
				  (List<ProductModel>) pacoshopProductDao.findSingleProductsByExternalOfferId(externalOfferId);

		return products;
	}

	@Override
	public ProductModel findProductsByOffer(
			  final CatalogVersionModel catalogVersion, final String externalOfferId, final OfferOrigin offerOrigin)
	{
		validateParameterNotNull(catalogVersion, "Parameter catalogVersion must not be null");
		validateParameterNotNull(externalOfferId, "Parameter externalOfferId must not be null");
		validateParameterNotNull(offerOrigin, "Parameter offerOriginCode must not be null");

		final Collection<ProductModel> products =
				  pacoshopProductDao.findProductsByOffer(catalogVersion, externalOfferId, offerOrigin);

		validateIfSingleResult(
				  products,
				  format("Product with externalOfferId '%s' and offerOrigin '%s' and CatalogVersion '%s.%s' not found!",
							externalOfferId, offerOrigin, catalogVersion.getCatalog().getId(), catalogVersion.getVersion()),
				  format("ExternalOfferId '%s' and OfferOrigin '%s' and CatalogVersion '%s.%s' are not unique. %d products found!",
							externalOfferId, offerOrigin, catalogVersion.getCatalog().getId(),
							catalogVersion.getVersion(), Integer.valueOf(products.size())));

		return products.iterator().next();
	}

	@Override
	public ProductModel getProductForCode(final CatalogVersionModel catalogVersion, final String code)
	{
		final List<ProductModel> products = pacoshopProductDao.findProductsByCode(catalogVersion, code);

		validateIfSingleResult(
				  products,
				  format("Product with code '%s' and CatalogVersion '%s.%s' not found!", code, catalogVersion.getCatalog().getId(),
							catalogVersion.getVersion()),
				  format("Code '%s' and CatalogVersion '%s.%s' are not unique. %d products found!", code, catalogVersion.getCatalog()
							 .getId(), catalogVersion.getVersion(), Integer.valueOf(products.size())));

		return products.get(0);

	}

	@Override
	public Collection<ProductModel> findProducts(final String catalogVersion)
	{
		return pacoshopProductDao.findSingleProducts(catalogVersion);
	}

	@Override
	public Collection<ProductModel> findProductsByImportSource(final String catalogVersion, final ProductImportSource importSource)
	{
		return pacoshopProductDao.findProductsByImportSource(catalogVersion, importSource);
	}

	/**
	 * @return the pacoshopProductDao
	 */
	protected PacoshopProductDao getPacoshopProductDao()
	{
		return pacoshopProductDao;
	}

	/**
	 * PacoshopProductDaosetter.
	 * @param pacoshopProductDaoParam - new PacoshopProductDao.
	 */
	@Required
	public void setPacoshopProductDao(final PacoshopProductDao pacoshopProductDaoParam)
	{
		this.pacoshopProductDao = pacoshopProductDaoParam;
	}

}
