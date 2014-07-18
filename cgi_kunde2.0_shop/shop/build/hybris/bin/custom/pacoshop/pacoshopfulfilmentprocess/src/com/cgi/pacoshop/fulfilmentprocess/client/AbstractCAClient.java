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
package com.cgi.pacoshop.fulfilmentprocess.client;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.fulfilmentprocess.converter.JsonConverter;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAException;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.CAOrderPopulatorException;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.JSONConversionException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;
import com.cgi.pacoshop.fulfilmentprocess.populator.SubmitCAEntryPopulator;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Represents the base client for CA.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public abstract class AbstractCAClient implements CAClient
{

    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(AbstractCAClient.class);


    private JsonConverter jsonConverter;

    private SubmitCAEntryPopulator submitCAEntryPopulator;


    /**
     * Extracts the CAOrderInfaceEntries from the order.
     * 
     * @param order
     *            the order that contains the entries.
     * @return the list of {@link CAOrderInterfaceEntry}.
     * @throws CAOrderPopulatorException
     *             if something went wrong.
     * @deprecated todo: To remove from the class and the test.
     */
    @Deprecated
    public List<CAOrderInterfaceEntry> extractCAOrderInterfaceEntries(final OrderModel order) throws CAOrderPopulatorException
    {
        return submitCAEntryPopulator.populateAllOrderEntries(order);
    }

    /**
     * Extracts one {@link CAOrderInterfaceEntry} from the {@link AbstractOrderModel}.
     * 
     * @param entry
     *            the {@link AbstractOrderEntryModel} to extract.
     * @param order
     *            the {@link AbstractOrderModel}.
     * @return the {@link CAOrderInterfaceEntry} or null if the entry is not part
     * @throws CAOrderPopulatorException
     *             if an error occurred while converting the entry.
     */
    public CAOrderInterfaceEntry extractCAOrderInterfaceEntry(final AbstractOrderEntryModel entry, final OrderModel order)
            throws CAOrderPopulatorException
    {
        return submitCAEntryPopulator.populateOrderEntry(entry, order);
    }


    /**
     * Convert one {@link CAOrderInterfaceEntry} to a JSON standard string.
     * 
     * @param entry
     *            the {@link CAOrderInterfaceEntry}.
     * @return the JSON string.
     * @throws JSONConversionException
     *             if the object cannot be converted.
     */
    public String convertCAOrderInterfaceEntryToJson(final CAOrderInterfaceEntry entry) throws JSONConversionException
    {
        return jsonConverter.convert(entry);
    }


    /**
     * Do send.
     * 
     * @param contents
     *            the contents
     * @return the string
     * @throws CAException
     *             the CAException.
     */
    protected abstract HttpResponse doSend(String contents) throws CAException;

    /**
     * Process response.
     * 
     * @param response
     *            the response
     * @throws CAException
     *             if CAException occurs
     */
    protected abstract void processResponse(HttpResponse response) throws CAException;


    /*
     * (non-Javadoc)
     * 
     * @see nz.co.telecom.core.order.RightNowClient#send(nz.co.telecom.core.services.data.RightNowOrder)
     */
    @Override
    public ClientStatus send(final OrderModel order)
    {
        boolean hasError = false;
        final List<CAOrderInterfaceEntry> entries = new ArrayList<CAOrderInterfaceEntry>();
        for (final AbstractOrderEntryModel current : order.getEntries())

        {
            try
            {
                final CAOrderInterfaceEntry entry = extractCAOrderInterfaceEntry(current, order);
                if (entry != null)
                {
                    entries.add(entry);
                }
            }
            catch (final CAOrderPopulatorException cope)
            {
                hasError = true;
                LOG.error("Entry[" + current + "] could not be converted to CAOrderInterfaceEntry", cope);
            }
        }



        for (final CAOrderInterfaceEntry currentEntry : entries)
        {
            try
            {
                debug(LOG, "Processing entry:[" + currentEntry + "]");

                final String json = convertCAOrderInterfaceEntryToJson(currentEntry);

                debug(LOG, "Processing json:[" + json + "]");

                final HttpResponse response = doSend(json);

                debug(LOG, "Response [" + response + "]");

                processResponse(response);
            }
            catch (final JSONConversionException jce)
            {
                hasError = true;
                LOG.error("Entry[" + currentEntry + "] could not be converted to json", jce);
            }
            catch (final CAException cae)
            {
                hasError = true;
                LOG.error("Entry[" + currentEntry + "] could not be converted an error occurred", cae);
            }
        }

        if (hasError)
        {
            return ClientStatus.FAIL;
        }

        return ClientStatus.SUCCESS;
    }

    /**
     * @param jsonConverter
     *            the jsonConverter to set
     */
    public void setJsonConverter(final JsonConverter jsonConverter)
    {
        this.jsonConverter = jsonConverter;
    }


    /**
     * @param submitCAEntryPopulator
     *            the submitCAEntryPopulator to set
     */
    public void setSubmitCAEntryPopulator(final SubmitCAEntryPopulator submitCAEntryPopulator)
    {
        this.submitCAEntryPopulator = submitCAEntryPopulator;
    }


}
