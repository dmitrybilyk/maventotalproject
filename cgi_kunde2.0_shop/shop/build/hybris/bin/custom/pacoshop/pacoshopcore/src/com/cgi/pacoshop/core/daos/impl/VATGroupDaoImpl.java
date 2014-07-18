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


import com.cgi.pacoshop.core.daos.VATGroupDao;
import com.cgi.pacoshop.core.exceptions.VATRateNotFoundException;
import com.cgi.pacoshop.core.model.VATGroupModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

/**
 * VatGroup entity dao layer. Implementation of VATGroupDao.
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 24, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class VATGroupDaoImpl implements VATGroupDao
{

	private FlexibleSearchService flexibleSearchService;

	/**
	 * Getter.
	 * @return flexibleSearchService flexibleSearchService
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

	@Override
	public VATGroupModel findVATGroup(final int vatRate)
	{
		String query = String.format("SELECT {%s} FROM {%s} WHERE {%s} = ?vatRate ", VATGroupModel.PK, VATGroupModel._TYPECODE,
											  VATGroupModel.VATRATE);
		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(query);
		flexQuery.addQueryParameter("vatRate", Double.valueOf(vatRate));
		List<VATGroupModel> list = flexibleSearchService.<VATGroupModel>search(flexQuery).getResult();
		if (list.size() > 1 || list.size() == 0)
		{
			throw new VATRateNotFoundException("VAT rate  doesn't belong to know value");
		}
		return list.get(0);

	}

	@Override
	public List<VATGroupModel> getAllVATGroups()
	{
		final FlexibleSearchQuery flexQuery =
				  new FlexibleSearchQuery(String.format("select {%s} FROM {%s}", VATGroupModel.PK, VATGroupModel._TYPECODE));
		return flexibleSearchService.<VATGroupModel>search(flexQuery).getResult();
	}
}
