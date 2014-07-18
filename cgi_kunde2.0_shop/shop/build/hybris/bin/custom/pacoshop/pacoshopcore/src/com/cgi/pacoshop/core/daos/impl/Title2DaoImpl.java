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


import com.cgi.pacoshop.core.daos.Title2Dao;
import com.cgi.pacoshop.core.model.Title2Model;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of Title2Dao.
 * Here goes 2 line.
 *
 * @module shop
 * @version 1.0v Jul 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Vekshin <alexey.vekshin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class Title2DaoImpl implements Title2Dao
{
	private FlexibleSearchService flexibleSearchService;

	@Override
	public Title2Model findTitle2(final String title2Code)
	{
		validateParameterNotNull(title2Code, "Title2Code code must not be null!");

		String queryString =
				  String.format("SELECT {p: %s} FROM {%s as p} WHERE LOWER({p: %s})=LOWER(?title2Code)",
									 Title2Model.PK, Title2Model._TYPECODE, Title2Model.CODE);
		FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("title2Code", title2Code);
		return this.flexibleSearchService.searchUnique(query);
	}

	@Override
	public List<Title2Model> findAll()
	{
		String queryString = String.format("SELECT {p: %s} FROM {%s as p} ORDER BY {p: %s}",
													  Title2Model.PK, Title2Model._TYPECODE, Title2Model.INDEX);
		FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		return this.flexibleSearchService.<Title2Model>search(query).getResult();
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
