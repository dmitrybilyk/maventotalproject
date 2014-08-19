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

package com.cgi.pacoshop.core.service.productdtopopulation.impl;


import com.cgi.pacoshop.core.checkout.dynamic.ProductDTOPopulationUtility;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.productdtopopulation.AbstractProductDTOPopulationService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import java.util.Collections;

/**
 * Populate dto single products
 *
 *
 * @module hybris
 * @version 1.0v Mar 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SingleProductDTOPopulationService extends AbstractProductDTOPopulationService
{
	/**
	 * Populate the target instance with values from the source instance. Test
	 *
	 * @param orderEntryModel the source object
	 * @param productDTO the target to fillTheCashMachine
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException if an error occurs
	 */
	@Override
	public void populate(final AbstractOrderEntryModel orderEntryModel, final ProductDTO productDTO) throws ConversionException
	{
		super.populate(orderEntryModel, productDTO);
		//find the latest price
		productDTO.setPrice(ProductDTOPopulationUtility.getLatestPriceRow(orderEntryModel));
		productDTO.setBonuses(Collections.<ProductDTO>emptyList());
	}
}
