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

package com.cgi.pacoshop.core.service.productdtopopulation.impl.kombi;


import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.productdtopopulation.AbstractProductDTOPopulationService;
import com.cgi.pacoshop.core.service.productdtopopulation.impl.DefaultProductDTOPopulationServiceLocator;
import static com.cgi.pacoshop.core.util.LogHelper.warn;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * Utility class to delegate to corresponding dto population service.
 *
 * @module hybris
 * @version 1.0v Mar 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */

public class KombiAboProductDTOPopulationServiceLocator extends DefaultProductDTOPopulationServiceLocator
{

	@Resource
	private Map<String, AbstractProductDTOPopulationService> kombiAboDtoPopulationServices;


	@Override
	public List<ProductDTO> populate(final List<AbstractOrderEntryModel> orderEntries)
	{
		List<ProductDTO> productDTOs = new ArrayList<>();
		try
		{
			Double totalPrice = 0d;
			ProductDTO mainProduct = null;
			for (int i = 0; i < orderEntries.size(); i++)
			{
				AbstractProductDTOPopulationService productDTOPopulationService =
						  kombiAboDtoPopulationServices.get((String.valueOf(i)));
				ProductDTO productDTO = new ProductDTO();
				productDTOPopulationService.populate(orderEntries.get(i), productDTO);
				productDTOs.add(productDTO);
				totalPrice += productDTO.getPrice();
				if (i == 0)
				{
					mainProduct = productDTO;
				}
				else
				{
					//price should be displayed only for the 1st product as sum of all prices
					productDTO.setPrice(0d);
				}
			}
			//set price for the 1st product
			mainProduct.setPrice(totalPrice);

		}
		catch (Exception e)
		{
			warn(getLOG(), String.format("Exception populating product dto. Reason: %s", e.getMessage()));
		}
		return productDTOs;
	}

}
