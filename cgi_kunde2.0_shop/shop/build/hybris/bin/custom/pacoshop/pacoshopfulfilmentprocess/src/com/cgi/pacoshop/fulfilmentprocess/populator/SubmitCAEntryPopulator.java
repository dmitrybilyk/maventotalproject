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
package com.cgi.pacoshop.fulfilmentprocess.populator;

import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAOrderPopulatorException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;


/**
 * Represents the populator for the {@link AbstractOrderEntryModel} to {@link CAOrderInterfaceEntry}.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface SubmitCAEntryPopulator
{

    /**
     * Create one {@link CAOrderInterfaceEntry} for the specified {@link AbstractOrderEntryModel} and
     * {@link AbstractOrderModel}.
     * 
     * @param entry
     *            the entry to proceed.
     * @param order
     *            the corresponding order for the entry.
     * @return the newly created {@link CAOrderInterfaceEntry}.
     * @throws CAOrderPopulatorException
     *             if the population was not possible.
     */

    CAOrderInterfaceEntry populateOrderEntry(AbstractOrderEntryModel entry, OrderModel order) throws CAOrderPopulatorException;

    /**
     * Create a list of {@link CAOrderInterfaceEntry} from an order. Returns only the {@link CAOrderInterfaceEntry} for
     * the valid {@link AbstractOrderEntryModel}.
     * 
     * @param order
     *            the order to process.
     * @return the list of CAOrderInterfaceEntry or empty list if the order didn't contains valid items for
     *         {@link CAOrderInterfaceEntry}.
     * @throws CAOrderPopulatorException
     *             if the population went wrong.
     * @deprecated todo: removed from the @Deprecated interface and from the test.
     */
    @Deprecated
    List<CAOrderInterfaceEntry> populateAllOrderEntries(OrderModel order) throws CAOrderPopulatorException;

}
