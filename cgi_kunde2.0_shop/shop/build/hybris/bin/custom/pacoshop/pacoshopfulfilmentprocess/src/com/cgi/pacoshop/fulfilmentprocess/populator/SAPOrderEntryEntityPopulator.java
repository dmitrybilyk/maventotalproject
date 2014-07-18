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

import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPOrderEntryEntity;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.util.List;


/**
 * Defines the populator for the SAP entry population.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface SAPOrderEntryEntityPopulator
{
    /**
     * Create a {@link SAPOrderEntryEntity} from a {@link AbstractOrderEntryModel}.
     * 
     * @param entry
     *            the {@link AbstractOrderEntryModel}.
     * @return the {@link SAPOrderEntryEntity}.
     * @throws SAPException
     *             if the conversion wasn't possible.
     */
    SAPOrderEntryEntity populate(AbstractOrderEntryModel entry) throws SAPException;

    /**
     * Create a List of {@link SAPOrderEntryEntity} from the {@link AbstractOrderModel}.
     * 
     * @param order
     *            the {@link AbstractOrderModel} to process.
     * @return the list of {@link SAPOrderEntryEntity}.
     * @throws SAPException
     *             if the conversion failed.
     */
    List<SAPOrderEntryEntity> populateAll(AbstractOrderModel order) throws SAPException;
}
