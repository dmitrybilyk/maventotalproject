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


import com.cgi.pacoshop.core.model.TermVersionModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;

/**
 * Implementation of TermVersionDAO interface.
 *
 * @module hybris
 * @version 1.0v Jun 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class TermVersionDAOImpl implements com.cgi.pacoshop.core.daos.TermVersionDAO
{
	@Resource
	private FlexibleSearchService flexibleSearchService;

	@Resource
	private ModelService modelService;

	@Override
	public TermVersionModel findByTermsVersionId(final String termsVersionId)
	{
		FlexibleSearchQuery query =
				  new FlexibleSearchQuery(String.format("SELECT {%s} FROM {%s} WHERE {%s}=?termsVersionId", TermVersionModel.PK,
																	 TermVersionModel._TYPECODE, TermVersionModel.TERMSVERSIONID));
		query.addQueryParameter("termsVersionId", termsVersionId);
		return flexibleSearchService.searchUnique(query);
	}

	@Override
	public List<TermVersionModel> findAll()
	{
		final FlexibleSearchQuery flexQuery =
				  new FlexibleSearchQuery(String.format("SELECT {%s} FROM {%s}", TermVersionModel.PK, TermVersionModel._TYPECODE));
		return flexibleSearchService.<TermVersionModel>search(flexQuery).getResult();
	}

	@Override
	public void saveAll(final Collection<TermVersionModel> termsVersionModels)
	{
		modelService.saveAll(termsVersionModels);
	}

	@Override
	public List<TermVersionModel> getTermsByName(final String termName)
	{
		final FlexibleSearchQuery flexQuery =
				  new FlexibleSearchQuery(String.format("SELECT {%s} FROM {%s} WHERE {%s} = '%s' ORDER BY {%s} DESC",
																	 TermVersionModel.PK, TermVersionModel._TYPECODE,
																	 TermVersionModel.NAME, termName, TermVersionModel.VERSION));
		return flexibleSearchService.<TermVersionModel>search(flexQuery).getResult();
	}

}
