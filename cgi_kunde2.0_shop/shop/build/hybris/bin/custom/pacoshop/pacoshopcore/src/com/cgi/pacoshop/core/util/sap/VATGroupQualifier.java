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
package com.cgi.pacoshop.core.util.sap;


import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.model.VATGroupModel;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Used to determine VAT group for product from import service by rate.
 *
 * @module build
 * @version 1.0v Feb 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class VATGroupQualifier
{
	@Resource
	private ReferenceDataProviderService referenceDataProviderService;

	/**
	 * Returns VATGroupModel by dto.
	 * @param dto Product data transfer object.
	 * @return VATGroupModel
	 * @throws ProductImportException in case if VATGroup not found.
	 */
	public VATGroupModel getVatGroupByRate(final SingleProductDTO dto) throws ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");

		final Integer rate = dto.getVat();
		final Map<Double, VATGroupModel> vatRateModelMap = referenceDataProviderService.getVATGroupsMap();

		if (rate == null)
		{
			throw new ProductImportException("Cannot find VATGroup because the rate is not supplied");
		}

		VATGroupModel result = vatRateModelMap.get(Double.valueOf(rate));

		if (result == null)
		{
			throw new ProductImportException("Cannot find VATGroup for the rate %f", Double.valueOf(rate));
		}

		return result;
	}
}
