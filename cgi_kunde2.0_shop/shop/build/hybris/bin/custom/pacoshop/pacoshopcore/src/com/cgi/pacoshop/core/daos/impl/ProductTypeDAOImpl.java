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
package com.cgi.pacoshop.core.daos.impl;


import com.cgi.pacoshop.core.daos.ProductTypeDAO;
import com.cgi.pacoshop.core.exceptions.ProductTypeException;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of ProductTypeDAO interface
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 03, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductTypeDAOImpl implements ProductTypeDAO
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public ProductTypeModel findProductType(final String productClass, final String productGroup, final String productCluster)
	{
		String query = "SELECT {p:pk} "
				  + "FROM "
				  + "{"
				  + "     ProductType AS p "
				  + "LEFT JOIN ProductCluster AS t ON {p:productCluster} = {t.PK} "
				  + "LEFT JOIN ProductGroup AS g ON {p:productGroup} = {g.PK} "
				  + "LEFT JOIN ProductClass AS c ON {p:productClass} = {c.PK} "
				  + ""
				  + "} WHERE "
				  + "    {t.code} = ?productCluster "
				  + "and {c.code} = ?productClass ";

		if (productGroup != null && !productGroup.isEmpty())
		{
			query += " and {g.code} =?productGroup ";
		}
		else
		{
			query += " and {p:productGroup} IS NULL";
		}

		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
		flexQuery.addQueryParameter("productClass", productClass);
		if (productGroup != null && !productGroup.isEmpty())
		{
			flexQuery.addQueryParameter("productGroup", productGroup);
		}
		flexQuery.addQueryParameter("productCluster", productCluster);

		List<ProductTypeModel> list = flexibleSearchService.<ProductTypeModel>search(flexQuery).getResult();
		if (list.size() == 0)
		{
			throw new ProductTypeException(
					  String.format("Cannot find ProductType for productClass=%s, productGroup=%s, productCluster=%s",
										 productClass, productGroup, productCluster));
		}

		if (list.size() > 1)
		{
			throw new ProductTypeException(
					  String.format("Found more than one ProductType for productClass=%s, productGroup=%s, productCluster=%s",
										 productClass, productGroup, productCluster));
		}

		return list.get(0);
	}

	@Override
	public List<ProductTypeModel> findAllProductTypes()
	{
		final FlexibleSearchQuery flexQuery =
				  new FlexibleSearchQuery(String.format("select {%s} FROM {%s}", ProductTypeModel.PK, ProductTypeModel._TYPECODE));
		return flexibleSearchService.<ProductTypeModel>search(flexQuery).getResult();
	}

	/**
	 * Getter.
	 * @return flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * Setter.
	 * @param flexibleSearchService flexibleSearchService
	 */
	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
