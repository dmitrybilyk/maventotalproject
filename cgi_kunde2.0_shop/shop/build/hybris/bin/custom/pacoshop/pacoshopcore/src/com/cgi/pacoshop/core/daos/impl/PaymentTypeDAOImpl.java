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


import com.cgi.pacoshop.core.daos.PaymentTypeDAO;
import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.core.model.VATGroupModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Required;

/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module hybris
 * @version 1.0v Jan 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PaymentTypeDAOImpl implements PaymentTypeDAO
{
	private FlexibleSearchService flexibleSearchService;

	/**
	 *
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

	@Override
	public List<PaymentTypeModel> findAllPaymentTypes()
	{
		final FlexibleSearchQuery flexQuery =
				  new FlexibleSearchQuery(String.format("select {%s} FROM {%s}", PaymentTypeModel.PK, PaymentTypeModel._TYPECODE));
		return flexibleSearchService.<PaymentTypeModel>search(flexQuery).getResult();
	}
}
