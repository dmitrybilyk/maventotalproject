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


import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.productdtopopulation.AbstractProductDTOPopulationService;
import com.cgi.pacoshop.core.service.productdtopopulation.ProductDTOPopulationServiceLocator;
import com.cgi.pacoshop.core.service.productdtopopulation.impl.kombi.KombiAboProductDTOPopulationServiceLocator;
import com.cgi.pacoshop.core.util.LogHelper;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

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

public class DefaultProductDTOPopulationServiceLocator implements ProductDTOPopulationServiceLocator<AbstractOrderEntryModel>
{
	private static final Logger LOG                    = Logger.getLogger(DefaultProductDTOPopulationServiceLocator.class);
	private static final int    FIRST_PRODUCT          = 0;
	private static final int    SECOND_PRODUCT         = 1;
	private static final int    KOMBIABO_PRODUCS_LIMIT = 2;

	@Resource
	private KombiAboProductDTOPopulationServiceLocator       kombiAboProductDTOPopulationServiceLocator;
	@Resource
	private Map<String, AbstractProductDTOPopulationService> productDTOServices;


	/**

	 * Gets lOG.
	 *
	 * @return the lOG
	 */
	public Logger getLOG()
	{
		return LOG;
	}


	@Override
	public List<ProductDTO> populate(final List<AbstractOrderEntryModel> orderEntries)
	{
		List<ProductDTO> productDTOs = new ArrayList<>();
		try
		{
			if (isValidKombiAbo(orderEntries))
			{
				return kombiAboProductDTOPopulationServiceLocator.populate(orderEntries);
			}
			for (AbstractOrderEntryModel orderEntry : orderEntries)
			{
				AbstractProductDTOPopulationService productDTOPopulationService =
						  productDTOServices.get(orderEntry.getProduct().getClass().getSimpleName());
				ProductDTO productDTO = new ProductDTO();
				productDTOPopulationService.populate(orderEntry, productDTO);
				productDTOs.add(productDTO);
			}

		}
		catch (Exception e)
		{
			LogHelper.warn(LOG, String.format("Exception populating product dto. Reason: %s", e.getMessage()));
		}
		return productDTOs;
	}

	private boolean isValidKombiAbo(final List<AbstractOrderEntryModel> orderEntryModels)
	{
		try
		{
			return orderEntryModels.size() == KOMBIABO_PRODUCS_LIMIT && orderEntryModels.get(
					  FIRST_PRODUCT).getProduct() instanceof SubscriptionProductModel
					  && orderEntryModels.get(
					  SECOND_PRODUCT).getProduct() instanceof SubscriptionProductModel;
		}
		catch (Exception e)
		{
			return false;
		}
	}





}
