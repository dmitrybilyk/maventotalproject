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
package com.cgi.pacoshop.core.service;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;


/**
 * Custom parser for json date deserialization. We should receive date in ISO 8601.
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 * 
 * 
 */
public class CustomJsonDateDeserializer extends JsonDeserializer<Date>
{
	@Override
	public Date deserialize(final JsonParser jsonparser, final DeserializationContext deserializationcontext) throws IOException
	{

		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		final String date = jsonparser.getText();
		try
		{
			return format.parse(date);
		}
		catch (final ParseException e)
		{
			throw new RuntimeException(e);
		}

	}

}
