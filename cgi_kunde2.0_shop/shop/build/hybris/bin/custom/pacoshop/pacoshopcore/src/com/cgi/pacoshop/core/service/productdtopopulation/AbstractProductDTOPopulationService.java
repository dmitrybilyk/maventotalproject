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

package com.cgi.pacoshop.core.service.productdtopopulation;


import static com.cgi.pacoshop.core.util.LogHelper.debug;
import de.hybris.platform.europe1.model.PriceRowModel;
import static org.springframework.util.Assert.notNull;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cgi.pacoshop.core.checkout.dynamic.ProductDTOPopulationUtility;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.productdtopopulation.impl.DefaultProductDTOPopulationServiceLocator;


/**
 * The type Abstract product dTO population service.
 * 
 * @module hybris
 * @version 1.0v Mar 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public abstract class AbstractProductDTOPopulationService implements Populator<AbstractOrderEntryModel, ProductDTO>
{
    private static final Logger LOG = Logger.getLogger(DefaultProductDTOPopulationServiceLocator.class);
    /**
     * The Population service list.
     */
    private List<AbstractProductDTOPopulationService> productDTOPopulationServices;

    @Resource
    private I18NService i18NService;


    /**
     * Gets log.
     * 
     * @return the log
     */
    public static Logger getLog()
    {

        return LOG;
    }

    /**
     * Gets product dTO population services.
     * 
     * @return the product dTO population services
     */
    public List<AbstractProductDTOPopulationService> getProductDTOPopulationServices()
    {
        return productDTOPopulationServices;
    }

    /**
     * Sets product dTO population services.
     * 
     * @param productDTOPopulationServices
     *            the product dTO population services
     */
    public void setProductDTOPopulationServices(final List<AbstractProductDTOPopulationService> productDTOPopulationServices)
    {
        this.productDTOPopulationServices = productDTOPopulationServices;
    }


    /**
     * Populate the target instance with values from the source instance.
     * 
     * @param orderEntryModel
     *            the source object
     * @param productDTO
     *            the target to fillTheCashMachine
     * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
     *             if an error occurs
     */
    @Override
    public void populate(final AbstractOrderEntryModel orderEntryModel, final ProductDTO productDTO) throws ConversionException
    {
		 debug(LOG, String.format("Creating productdto from product id: %s", orderEntryModel.getCode()));
		 notNull(productDTO);
		 notNull(orderEntryModel);
		 notNull(orderEntryModel.getProduct());
		 final Locale locale = i18NService.getCurrentLocale();
		 final String imageUrl = ProductDTOPopulationUtility
					.getImageUrl(orderEntryModel, ProductDTOPopulationUtility.MODE.PRODUCT);
		 final String productName = ProductDTOPopulationUtility.getProductName(orderEntryModel, locale);
		 final String description = ProductDTOPopulationUtility.getProductDescription(orderEntryModel, locale);

		 productDTO.setUrl(imageUrl);
		 productDTO.setName(productName);
		 productDTO.setDescription(description);

		 productDTO.setQuantity(orderEntryModel.getQuantity().intValue());

		 for (PriceRowModel prices : orderEntryModel.getProduct().getEurope1Prices())
		 {
			 if (prices.getPrice() != null)
			 {
				 productDTO.setCurrency(prices.getCurrency().getSymbol().toString());
			 }

		 }
		 productDTO.setProductId(orderEntryModel.getProduct().getCode());

		 productDTO.setExternalProductId(orderEntryModel.getProduct().getExternalProductId());
		 productDTO.setOfferOrigin(orderEntryModel.getProduct().getOfferOrigin());
	 }

}
