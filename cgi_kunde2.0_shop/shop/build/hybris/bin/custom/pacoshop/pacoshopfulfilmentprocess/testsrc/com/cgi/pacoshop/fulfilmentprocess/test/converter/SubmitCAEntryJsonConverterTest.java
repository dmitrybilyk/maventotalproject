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
package com.cgi.pacoshop.fulfilmentprocess.test.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.fulfilmentprocess.converter.JsonConverter;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.JSONConversionException;
import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;

import de.hybris.bootstrap.annotations.UnitTest;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateMidnight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Test pertaining to the {@link JsonConverter}.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@UnitTest
@RunWith(MockitoJUnitRunner.class)
public class SubmitCAEntryJsonConverterTest
{

    @Mock
    private ObjectMapper mockMapper;

    private JsonConverter testMe;

    /**
     * Setup.
     */
    @Before
    public void setup()
    {
        testMe = new JsonConverter();
        testMe.setDateFormatString("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        testMe.setObjectMapper(mockMapper);
    }

    /**
     * Test with null value.
     */
    @Test
    public void testNull()
    {
        final CAOrderInterfaceEntry caOrderInterfaceEntry = null;
        try
        {
            testMe.convert(caOrderInterfaceEntry);
            assertFalse(true);
        }
        catch (final JSONConversionException e)
        {
            assertTrue(true);
        }

    }

    /**
     * Test a valid format.
     * 
     * @throws JSONConversionException
     *             if it wasn't valid.
     */
    @Test
    public void testFormatValid() throws JSONConversionException
    {
        final CAOrderInterfaceEntry entry = new CAOrderInterfaceEntry();
        testMe.setObjectMapper(new ObjectMapper());
        final DateMidnight oneDate = new DateMidnight(1980, 8, 18);
        entry.setClientCustomerId("clientCustomerId");
        entry.setConsigneeCustomerId("consigneeCustomerId");
        entry.setInvoiceRecipientCustomerId("invoiceRecipientCustomerId");
        entry.setProductId("productId");
        entry.setContentPlatformId("contentPlatformId");
        entry.setContentId("contentId");
        entry.setContentTitle("contentTitle");
        entry.setContentUrl("");
        entry.setOrderRequestId("orderRequestId");
        entry.setOrderRequestPositionId("orderRequestPositionId");
        entry.setOrderDate(oneDate.toDate());
        entry.setOrderSize(Long.valueOf(1));
        entry.setValidFrom(oneDate.toDate());

        final String testResult = testMe.convert(entry);
        assertNotNull(testResult);
        assertEquals("{\"clientCustomerId\":\"clientCustomerId\",\"consigneeCustomerId\":\"consigneeCustomerId\""
                + ",\"invoiceRecipientCustomerId\":\"invoiceRecipientCustomerId\",\"productId\":\"productId\","
                + "\"productOrigin\":null,\"contentPlatformId\":"
                + "\"contentPlatformId\",\"contentId\":\"contentId\",\"contentTitle\":\"contentTitle\",\"contentUrl\""
                + ":\"\",\"orderRequestId\":\"orderRequestId\",\"orderRequestPositionId\":\"orderRequestPositionId\","
                + "\"orderDate\":\"1980-08-18T00:00:00.000+0200\",\"orderSize\":1,\"validFrom\":"
                + "\"1980-08-18T00:00:00.000+0200\",\"validTo\":null}", testResult);
    }

    /**
     * Test the exception cases.
     */
    @Test
    public void testExceptions()
    {

        try
        {
            when(mockMapper.writeValueAsString(any())).thenThrow(new JsonGenerationException("Whatever"));
            testMe.setObjectMapper(mockMapper);
            testMe.convert(new CAOrderInterfaceEntry());
        }
        catch (final JSONConversionException e)
        {
            assertTrue(true);
        }
        catch (final JsonGenerationException e)
        {
            assertFalse(true);
        }
        catch (final JsonMappingException e)
        {
            assertFalse(true);
        }
        catch (final IOException e)
        {
            assertFalse(true);
        }

        try
        {
            mockMapper = Mockito.mock(ObjectMapper.class);
            when(mockMapper.writeValueAsString(any())).thenThrow(new JsonMappingException("Whatever"));
            testMe.setObjectMapper(mockMapper);
            testMe.convert(new CAOrderInterfaceEntry());
        }
        catch (final JSONConversionException e)
        {
            assertTrue(true);
        }
        catch (final JsonGenerationException e)
        {
            assertFalse(true);
        }
        catch (final JsonMappingException e)
        {
            assertFalse(true);
        }
        catch (final IOException e)
        {
            assertFalse(true);
        }

        try
        {
            mockMapper = Mockito.mock(ObjectMapper.class);
            when(mockMapper.writeValueAsString(any())).thenThrow(new IOException("Whatever"));
            testMe.setObjectMapper(mockMapper);
            testMe.convert(new CAOrderInterfaceEntry());
        }
        catch (final JSONConversionException e)
        {
            assertTrue(true);
        }
        catch (final JsonGenerationException e)
        {
            assertFalse(true);
        }
        catch (final JsonMappingException e)
        {
            assertFalse(true);
        }
        catch (final IOException e)
        {
            assertFalse(true);
        }

    }

}
