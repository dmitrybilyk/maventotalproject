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
package runner.sso.model;


import java.io.IOException;
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
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class CustomJsonStringTrimmerDeserializer extends JsonDeserializer<String>
{
	@Override
	public String deserialize(final JsonParser jsonparser,
									final DeserializationContext deserializationcontext) throws IOException
	{
		return jsonparser.getText().trim();
	}
}
