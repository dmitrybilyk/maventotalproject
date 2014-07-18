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
package com.cgi.pacoshop.fulfilmentprocess.populator.impl;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPOrderEntryEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPOrderEntryEntityPopulator;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Implementation of the populator for the SAP create single order population.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPOrderEntryEntityPopulatorImpl implements SAPOrderEntryEntityPopulator
{
    private static final Logger LOGGER = Logger.getLogger(SAPOrderEntryEntityPopulatorImpl.class);


    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntryEntityPopulator#populate(de.hybris.platform.core.
     * model.order.AbstractOrderEntryModel)
     */
    @Override
    public SAPOrderEntryEntity populate(final AbstractOrderEntryModel entry) throws SAPException
    {
        if (entry == null)
        {
            debug(LOGGER, "The populator has been call with a null entry model.");
            return null;
        }

        final SAPOrderEntryEntity result = new SAPOrderEntryEntity();
        result.setOrderRequestPositionId(entry.getCode());
        result.setOrderSize(entry.getQuantity());
        if (entry.getProduct() == null)
        {
            throw new SAPException("AbstractOrderEntry [" + entry.getCode() + "] doesn't have a product assigned.....");
        }
        final ProductModel product = entry.getProduct();

        if (product.getOfferOrigin() != null)
        {
            result.setProductOrigin(product.getOfferOrigin().getCode());
        }

        result.setProductId(product.getExternalOfferId());

        if (product.getProductOwner() != null)
        {
            result.setProductOwner(product.getProductOwner().getCode());
        }

        if (product.getProductType() != null)
        {
            if (product.getProductType().getProductClass() != null)
            {
                result.setProductClass(product.getProductType().getProductClass().getCode());
            }
            if (product.getProductType().getProductGroup() != null)
            {
                result.setProductGroup(product.getProductType().getProductGroup().getCode());
            }

        }

        debug(LOGGER, "Returning [%s] for entry[%s]", result, entry.getCode());

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntryEntityPopulator#populateAll(de.hybris.platform.core
     * .model.order.AbstractOrderModel)
     */
    @Override
    public List<SAPOrderEntryEntity> populateAll(final AbstractOrderModel order) throws SAPException
    {
        if (order == null)
        {
            debug(LOGGER, "PopulateAll called with a null order");
            return null;
        }

        if (order.getEntries() == null || order.getEntries().size() == 0)
        {
            debug(LOGGER, "Called the populator with an order which doesn't contains entries[%s]", order);
            return null;
        }

        List<SAPOrderEntryEntity> entries = new ArrayList<SAPOrderEntryEntity>();
        for (final AbstractOrderEntryModel current : order.getEntries())
        {
            final SAPOrderEntryEntity result = populate(current);
            if (result != null)
            {
                entries.add(result);
            }
        }

        if (entries.size() == 0)
        {
            debug(LOGGER, "None of the orderEntry returned a valid result for order[%s]", order);
            entries = null;
        }

        return entries;
    }

}
