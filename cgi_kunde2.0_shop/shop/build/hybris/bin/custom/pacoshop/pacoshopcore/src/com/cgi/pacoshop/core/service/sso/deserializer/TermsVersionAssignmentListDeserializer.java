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
package com.cgi.pacoshop.core.service.sso.deserializer;


import com.cgi.pacoshop.core.model.ResponseFieldAssignment;
import com.cgi.pacoshop.core.model.ResponseTermAccepted;
import com.cgi.pacoshop.core.model.ResponseTermsVersionAssignment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * List deserializer for termsVersionAssignments.
 *
 * @module pacoshopcore
 * @version 1.0v Jun 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class TermsVersionAssignmentListDeserializer extends JsonDeserializer<List<ResponseTermsVersionAssignment>>
{

	private static final String MANDATORY_JSON_FIELD_NAME = "mandatory";
	private static final String TERMS_ID_FIELD_NAME = "termsId";
	private static final String TERMS_VERSION_ID_FIELD_NAME = "termsVersionId";
	private static final String TERMS_VERSION_IDENTIFIER_FIELD_NAME = "termsVersionIdentifier";
	private static final String URL_FIELD_NAME = "url";
	private static final String SCOPE_FIELD_NAME = "scope";
	private static final String TYPE_FIELD_NAME = "type";
	private static final String VERSION_FIELD_NAME = "version";
	private static final String NAME_FIELD_NAME = "name";
	private static final String ORDER_BY_VALUE_FIELD_NAME = "orderByValue";

	@Override
	public List<ResponseTermsVersionAssignment> deserialize(final JsonParser jsonParser,
																			  final DeserializationContext deserializationContext)
			  throws IOException, JsonProcessingException
	{
		JsonNode termsVersionListNode = jsonParser.readValueAsTree();
		List<ResponseTermsVersionAssignment> termsVersionAssignments = new ArrayList<>();

		Iterator<JsonNode> iterator = termsVersionListNode.getElements();
		while (iterator.hasNext())
		{
			JsonNode termsVersionNode = iterator.next();
			ResponseTermsVersionAssignment termsVersionAssignment = new ResponseTermsVersionAssignment();


			termsVersionAssignment.setMandatory(termsVersionNode.get(MANDATORY_JSON_FIELD_NAME).getBooleanValue());
			termsVersionAssignment.setTermsId(termsVersionNode.get(TERMS_ID_FIELD_NAME).getTextValue());
			termsVersionAssignment.setTermsVersionId(termsVersionNode.get(TERMS_VERSION_ID_FIELD_NAME).getTextValue());
			termsVersionAssignment.setTermsVersionIdentifier(termsVersionNode.get(TERMS_VERSION_IDENTIFIER_FIELD_NAME)
																						.getTextValue());
			termsVersionAssignment.setUrl(termsVersionNode.get(URL_FIELD_NAME).getTextValue());
			termsVersionAssignment.setScope(termsVersionNode.get(SCOPE_FIELD_NAME).getTextValue());
			termsVersionAssignment.setType(termsVersionNode.get(TYPE_FIELD_NAME).getTextValue());
			termsVersionAssignment.setVersion(termsVersionNode.get(VERSION_FIELD_NAME).getIntValue());
			termsVersionAssignment.setName(termsVersionNode.get(NAME_FIELD_NAME).getTextValue());
			termsVersionAssignment.setOrderByValue(termsVersionNode.get(ORDER_BY_VALUE_FIELD_NAME).getIntValue());

			termsVersionAssignments.add(termsVersionAssignment);
		}
		return termsVersionAssignments;
	}
}
