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

import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAOrderPopulatorException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;
import com.cgi.pacoshop.fulfilmentprocess.populator.SubmitCAEntryPopulator;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.apache.log4j.Logger;


/**
 * Represents the populator for the {@link CAOrderInterfaceEntry} from the {@link AbstractOrderEntryModel}.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubmitCAEntryPopulatorImpl implements SubmitCAEntryPopulator
{

    private static final Logger LOGGER = Logger.getLogger(SubmitCAEntryPopulatorImpl.class);


    @Resource
    private ConfigurationService configurationService;

    @Override
    public CAOrderInterfaceEntry populateOrderEntry(final AbstractOrderEntryModel entry, final OrderModel order)
            throws CAOrderPopulatorException
    {
        debug(LOGGER, "Populating a new CAOrderIntefaceEntry with entry[" + entry + "] and order[" + order + "]");

        if (entry == null || order == null)
        {
            throw new CAOrderPopulatorException("Tried to populate an orderEntry with either entry[" + entry + "] or order["
                    + order + "] null");
        }

        final CAOrderInterfaceEntry result = new CAOrderInterfaceEntry();

        if (order.getUser() == null)
        {
            throw new CAOrderPopulatorException("There is no user associated with order[" + order + "]");
        }
        result.setClientCustomerId(order.getUser().getUid());


        //it's ok to compare simply the reference as the
        //deliveryAddress will be either the same (same instance) than the customerAddress or not.
        if (order.getDeliveryAddress() != null && !order.getDeliveryAddress().equals(order.getCustomerAddress()))
        {
            //if there's a delivery address different from the customerAddress, that imply that the recipient of the
            //online article is someone else than the customer.
            //In which case, we try to send the ssoCustomerId of that customer
            //sending null means that we didn't find a ssoId for that other benefactor.
            result.setConsigneeCustomerId(order.getDeliveryAddress().getSsoCustomerId());
        }
        else
        {
            result.setConsigneeCustomerId(order.getUser().getUid());
        }


        //https://wiki.symmetrics.de/pages/viewpage.action?pageId=175472723
        result.setInvoiceRecipientCustomerId(null);

        if (entry.getProduct() == null)
        {
            throw new CAOrderPopulatorException("There is no product associated with entry[" + entry + "] in order[" + order
                    + "]");
        }
        result.setProductId(entry.getProduct().getExternalProductId());
        result.setContentPlatformId(entry.getContentPlatformId());
        result.setContentId(entry.getContentId());
        result.setContentTitle(entry.getContentTitle());

        //todo: FIXME
        //This is left null on purpose as per specifications at 29.01.2014
        //https://wiki.symmetrics.de/pages/viewpage.action?pageId=175472723
        result.setContentUrl(null);

        result.setOrderRequestId(order.getCode());
        result.setOrderRequestPositionId(entry.getCode());
        result.setOrderDate(order.getDate());
        result.setOrderSize(entry.getQuantity());



        result.setValidFrom(null);
        if (order.getDeliveryStartDate() != null)
        {
            result.setValidFrom(order.getDeliveryStartDate());
        }
        result.setValidTo(null);

		 if (entry.getProduct().getFulfillmentType() == null)
		 {
			 throw new CAOrderPopulatorException("There is no fulfillmentType associagit ted with product[" + entry.getProduct()
																		+ "] in order[" + order + "]");
		 }
        result.setProductOrigin(entry.getProduct().getFulfillmentType().getCode());

        //
        //This is left null on purpose as per specifications
        //https://wiki.symmetrics.de/pages/viewpage.action?pageId=171048987
        result.setValidTo(null);

        debug(LOGGER, "Populated a new CAOrderIntefaceEntry with entry[" + entry + "] and order[" + order + "] result[" + result
                + "]");

        return result;
    }

    /**
     * @param configurationService
     *            the configurationService to set
     */
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }

    @Override
    public List<CAOrderInterfaceEntry> populateAllOrderEntries(final OrderModel order) throws CAOrderPopulatorException
    {
        if (order == null)
        {
            throw new CAOrderPopulatorException("Called the populateAllOrderEntries with a null order");
        }
        final List<CAOrderInterfaceEntry> results = new ArrayList<>();
        final List<AbstractOrderEntryModel> entries = order.getEntries();


        if (entries == null || entries.isEmpty())
        {
            return results;
        }

        for (final AbstractOrderEntryModel current : entries)
        {
            results.add(populateOrderEntry(current, order));
        }
        return results;
    }
}
