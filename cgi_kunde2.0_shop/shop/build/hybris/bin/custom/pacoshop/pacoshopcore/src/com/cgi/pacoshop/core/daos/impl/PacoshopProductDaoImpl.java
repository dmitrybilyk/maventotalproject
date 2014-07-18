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

package com.cgi.pacoshop.core.daos.impl;

import com.cgi.pacoshop.core.constants.GeneratedPacoshopCoreConstants;
import com.cgi.pacoshop.core.daos.PacoshopProductDao;
import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.enums.ProductImportSource;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.constants.CategoryConstants;
import de.hybris.platform.core.model.link.LinkModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Required;


/**
 * The {@link ProductModel} DAO.
 *
 * @module Pacoshopcore
 * @version 1.0v Dec 05, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see            "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class PacoshopProductDaoImpl extends DefaultProductDao implements PacoshopProductDao
{

	private FlexibleSearchService flexibleSearchService;


	/**
	 * Constructor.
	 * @param typecode - type code.
	 */
	public PacoshopProductDaoImpl(final String typecode)
	{
		super(typecode);
	}

	@Override
	public List<ProductModel> findProductsByCode(final CatalogVersionModel catalogVersion, final String code)
	{
		validateParameterNotNull(catalogVersion, "CatalogVersion must not be null!");
		validateParameterNotNull(code, "Product code must not be null!");

		String queryString =
				  String.format("SELECT {p: %s} FROM {%s as p} WHERE LOWER({p: %s})=?productCode AND {p: %s}=?catalogVersion",
													  ProductModel.PK, ProductModel._TYPECODE, ProductModel.CODE, ProductModel.CATALOGVERSION);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("productCode", code.toLowerCase());
		query.addQueryParameter("catalogVersion", catalogVersion);

		return flexibleSearchService.<ProductModel>search(query).getResult();
	}

	@Override
	public Collection<ProductModel> findSingleProductsByExternalOfferId(final String code)
	{
		String queryString = String.format("SELECT {p: %s } FROM {%s as p } WHERE {p: %s }=?externalOfferId ", ProductModel.PK,
													  ProductModel._TYPECODE, ProductModel.EXTERNALOFFERID);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("externalOfferId", code);

		return flexibleSearchService.<ProductModel>search(query).getResult();
	}

	@Override
	public Collection<ProductModel> findProductsByOffer(final CatalogVersionModel catalogVersion, final String externalOfferId,
																		 final OfferOrigin offerOrigin)
	{
		validateParameterNotNull(catalogVersion, "CatalogVersion must not be null!");
		validateParameterNotNull(externalOfferId, "externalOfferId must not be null!");
		validateParameterNotNull(offerOrigin, "offerOrigin must not be null!");

		final Map parameters = new HashMap();
		parameters.put(ProductModel.CATALOGVERSION, catalogVersion);
		parameters.put(ProductModel.EXTERNALOFFERID, externalOfferId);
		parameters.put(ProductModel.OFFERORIGIN, offerOrigin);

		return find(parameters);

	}

	/**
	 *
	 * @return flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * flexibleSearchService setter.
	 * @param flexibleSearchServiceParam - flexibleSearchService to be set.
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchServiceParam)
	{
		this.flexibleSearchService = flexibleSearchServiceParam;
	}

	@Override
	public Collection<ProductModel> findSingleProducts(final String catalogVersion)
	{
		String queryString =
				  String.format("SELECT {p: %s } FROM {%s AS p  JOIN %s AS c ON {p: %s} = {c: %s}} WHERE {c: %s } = ?catalogVersion",
									 ProductModel.PK, ProductModel._TYPECODE, CatalogVersionModel._TYPECODE, ProductModel.CATALOGVERSION,
									 CatalogVersionModel.PK, CatalogVersionModel.VERSION);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("catalogVersion", catalogVersion);
		return flexibleSearchService.<ProductModel>search(query).getResult();
	}

	@Override
	public Collection<ProductModel> findProductsByImportSource(final String catalogVersion, final ProductImportSource importSource)
	{
		String queryString =
				  String.format("SELECT {p: %s } FROM {%s AS p  JOIN %s AS c ON {p: %s} = {c: %s}} WHERE {c: %s } = ?catalogVersion"
												+ " AND {p: %s} = ?importSource",
									 ProductModel.PK, ProductModel._TYPECODE, CatalogVersionModel._TYPECODE, ProductModel.CATALOGVERSION,
									 CatalogVersionModel.PK, CatalogVersionModel.VERSION, ProductModel.IMPORTSOURCE);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("catalogVersion", catalogVersion);
		query.addQueryParameter("importSource", importSource);
		return flexibleSearchService.<ProductModel>search(query).getResult();
	}
}
