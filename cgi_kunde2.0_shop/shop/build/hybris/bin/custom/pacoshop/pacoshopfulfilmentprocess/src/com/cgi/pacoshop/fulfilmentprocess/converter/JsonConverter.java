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
package com.cgi.pacoshop.fulfilmentprocess.converter;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.fulfilmentprocess.exceptions.JSONConversionException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;



/**
 * Converter to transform a {@link CAOrderInterfaceEntry} into a Json string.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class JsonConverter
{

    private static final Logger LOGGER = Logger.getLogger(JsonConverter.class);

    private ObjectMapper objectMapper;

    private DateFormat dateFormat = null;

    /**
     * @param dateFormatString
     *            the dateFormatString to set
     */
    public void setDateFormatString(final String dateFormatString)
    {
        this.dateFormat = new SimpleDateFormat(dateFormatString);
    }

    /**
     * Convert a {@link Object} into a JSON string.
     * 
     * @param entry
     *            the entry to convert.
     * @return the JSON string.
     * @throws JSONConversionException
     *             if the conversion failed.
     */
    public String convert(final Object entry) throws JSONConversionException
    {
        String result = null;
        if (entry == null)
        {
            debug(LOGGER, "Called convert with an null object");

            throw new JSONConversionException("Invoke the converter with a null object.", null);
        }

        try
        {
            debug(LOGGER, "About to convert entry[" + entry + "] to JSON");

            this.objectMapper.setDateFormat(this.dateFormat);

            result = this.objectMapper.writeValueAsString(entry);

            debug(LOGGER, "Conversion result:[" + result + "]");

        }
        catch (final JsonGenerationException jge)
        {
            throw new JSONConversionException("Cannot convert the object to JSON", jge);
        }
        catch (final JsonMappingException jme)
        {
            throw new JSONConversionException("Cannot convert the object to JSON", jme);
        }
        catch (final IOException e)
        {
            throw new JSONConversionException("Cannot convert the object to JSON", e);
        }

        return result;
    }

    /**
     * Converts JSONToSSOAccountResponse.
     * 
     * @param json
     *            the string.
     * @return the SSOAccountResponse.
     * @throws JSONConversionException
     *             if an exception occurred during conversion.
     */
    public SSOAccountResponse convertJsonToObject(final String json) throws JSONConversionException
    {
        if (json == null)
        {
            debug(LOGGER, "Called convert with an empty json");

            throw new JSONConversionException("Invoke the converter with a null json.", null);
        }

        try
        {
            debug(LOGGER, "About to convert entry[" + json + "] to Object");

            this.objectMapper.setDateFormat(this.dateFormat);

            final SSOAccountResponse result = this.objectMapper.readValue(json, SSOAccountResponse.class);

            debug(LOGGER, "Conversion result:[" + result + "]");

            return result;
        }
        catch (final JsonGenerationException jge)
        {
            throw new JSONConversionException("Cannot convert the JSON to object", jge);
        }
        catch (final JsonMappingException jme)
        {
            throw new JSONConversionException("Cannot convert the JSON to object", jme);
        }
        catch (final IOException e)
        {
            throw new JSONConversionException("Cannot convert the JSON to object", e);
        }

    }

    /**
     * @param objectMapper
     *            the objectMapper to set
     */
    public void setObjectMapper(final ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

}
