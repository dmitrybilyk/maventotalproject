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
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * List deserializer for field assigments.
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
public class FieldAssignmentListDeserializer extends JsonDeserializer<List<ResponseFieldAssignment>>
{
	static final TypeReference<List<String>> LIST_TYPE_REFERENCE = new TypeReference<List<String>>()
	{
	};

	private static final String FIELD_ID_FIELD_NAME = "fieldId";
	private static final String FIELD_NAME_FIELD_NAME = "fieldName";
	private static final String ORDER_BY_VALUE_FIELD_NAME = "orderByValue";
	private static final String FORWARD_TO_PLATFORM_FIELD_NAME = "forwardToPlatform";
	private static final String MANDATORY_JSON_FIELD_NAME = "mandatory";
	private static final String HIDDEN_FIELD_NAME = "hidden";
	private static final String SHOW_ON_REGISTRATION_FIELD_NAME = "showOnRegistration";
	private static final String TYPE_FIELD_NAME = "type";
	private static final String MIN_FIELD_NAME = "min";
	private static final String MAX_FIELD_NAME = "max";
	private static final String VALUES_FIELD_NAME = "values";
	private static final String SCOPE_FIELD_NAME = "scope";

	@Override
	public List<ResponseFieldAssignment> deserialize(final JsonParser jsonParser,
																	 final DeserializationContext deserializationContext) throws
																																			IOException,
																																			JsonProcessingException
	{
		JsonNode fieldListNode = jsonParser.readValueAsTree();
		List<ResponseFieldAssignment> fieldAssignments = new ArrayList<>();

		Iterator<JsonNode> iterator = fieldListNode.getElements();
		while (iterator.hasNext())
		{
			JsonNode fieldNode = iterator.next();
			ResponseFieldAssignment fieldAssignment = new ResponseFieldAssignment();

			fieldAssignment.setFieldId(fieldNode.get(FIELD_ID_FIELD_NAME).getTextValue());
			fieldAssignment.setFieldName(fieldNode.get(FIELD_NAME_FIELD_NAME).getTextValue());
			fieldAssignment.setOrderByValue(fieldNode.get(ORDER_BY_VALUE_FIELD_NAME).getIntValue());
			fieldAssignment.setForwardToPlatform(fieldNode.get(FORWARD_TO_PLATFORM_FIELD_NAME).getBooleanValue());
			fieldAssignment.setMandatory(fieldNode.get(MANDATORY_JSON_FIELD_NAME).getBooleanValue());
			fieldAssignment.setHidden(fieldNode.get(HIDDEN_FIELD_NAME).getBooleanValue());
			fieldAssignment.setShowOnRegistration(fieldNode.get(SHOW_ON_REGISTRATION_FIELD_NAME).getBooleanValue());
			fieldAssignment.setType(fieldNode.get(TYPE_FIELD_NAME).getTextValue());
			fieldAssignment.setMin(fieldNode.get(MIN_FIELD_NAME).getTextValue());
			fieldAssignment.setMax(fieldNode.get(MAX_FIELD_NAME).getTextValue());

			ObjectMapper mapper = new ObjectMapper();
			List<String> values = mapper.readValue(fieldNode.get(VALUES_FIELD_NAME), LIST_TYPE_REFERENCE);
			fieldAssignment.setValues(values);

			fieldAssignment.setScope(fieldNode.get(SCOPE_FIELD_NAME).getTextValue());

			fieldAssignments.add(fieldAssignment);
		}
		return fieldAssignments;
	}
}
