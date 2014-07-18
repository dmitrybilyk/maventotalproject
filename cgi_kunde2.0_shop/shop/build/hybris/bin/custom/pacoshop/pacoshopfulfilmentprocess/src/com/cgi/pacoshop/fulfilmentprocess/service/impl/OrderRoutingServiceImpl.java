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
package com.cgi.pacoshop.fulfilmentprocess.service.impl;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.fulfilmentprocess.actions.order.OrderFulfillmentType;
import com.cgi.pacoshop.fulfilmentprocess.service.OrderRoutingService;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;

import org.apache.log4j.Logger;


/**
 * Represents the default implementation of the OrderRoutingService.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class OrderRoutingServiceImpl implements OrderRoutingService
{

    private static final Logger LOGGER = Logger.getLogger(OrderRoutingServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.service.OrderRoutingService#analyzeOrder(de.hybris.platform.core.model.order
     * .AbstractOrderModel)
     */
    @Override
    public OrderFulfillmentType analyzeOrder(final AbstractOrderModel order)
    {

        final ProductModel product = fetchFirstProduct(order);
        if (product == null)
        {
            LOGGER.error("No product were found in the order[" + order + "]");
            return null;
        }

        final ProductImportSource productImportSource = product.getImportSource();
        if (ProductImportSource.SERVICE_BUS_SINGLE_PRODUCTS == productImportSource)
        {
            debug(LOGGER, "Returning transition: " + OrderFulfillmentType.TRANSITION_SAPSD.getValue());
            return OrderFulfillmentType.TRANSITION_SAPSD;
        }
        else if (ProductImportSource.SERVICE_BUS_PERIODIC_PRODUCTS == productImportSource)
        {
            debug(LOGGER, "Returning transition: " + OrderFulfillmentType.TRANSITION_SAPMSD.getValue());
            return OrderFulfillmentType.TRANSITION_SAPMSD;
        }
        else if (ProductImportSource.HYBRIS_COCKPITS == productImportSource)
        {
            debug(LOGGER, "Returning transition: " + OrderFulfillmentType.TRANSITION_EMAIL.getValue());
            return OrderFulfillmentType.TRANSITION_EMAIL;
        }
        else
        {
            LOGGER.error("The productImportSource [" + productImportSource + "] for the order[" + order
                    + "] is not recognized By the system!");
            return null;
        }
    }

    /**
     * Returns the first product found in the order.
     * 
     * @param order
     *            the order.
     * @return the first product found in the order or null.
     */
    private ProductModel fetchFirstProduct(final AbstractOrderModel order)
    {
        if (order != null && order.getEntries() != null && order.getEntries().size() != 0)
        {
			   return getProductIfTheSameSource(order);
        }
        else
        {
            LOGGER.error("Cannot fetch product from the order[" + order + "]");
        }
        return null;
    }

	private ProductModel getProductIfTheSameSource(final AbstractOrderModel order)
	{
		ProductModel result = order.getEntries().get(0).getProduct();
		for (AbstractOrderEntryModel entryModel : order.getEntries())
		{
			if (!isEqual(entryModel.getProduct().getImportSource(), result.getImportSource()))
			{
				return null;
			}
		}
		return result;
	}

	private boolean isEqual(final ProductImportSource source1, final ProductImportSource source2)
	{
		return (source1 != null) ? source1.equals(source2) : source2 == null;
	}

}
