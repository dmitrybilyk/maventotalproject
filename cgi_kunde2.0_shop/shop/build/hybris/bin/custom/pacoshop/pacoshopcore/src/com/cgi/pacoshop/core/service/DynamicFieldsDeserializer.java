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
package com.cgi.pacoshop.core.service;

import de.hybris.platform.core.Registry;

import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.cgi.pacoshop.core.model.ResponseDynamicFields;


/**
 * Deserializer for dynamic fields. Gets from JsonNode with key from configuration service.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 12, 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class DynamicFieldsDeserializer extends JsonDeserializer<ResponseDynamicFields>
{
    private static final Logger LOG = Logger.getLogger(DynamicFieldsDeserializer.class);


    /**
     * Deserialise received results.
     * 
     * @param jsonParser
     *            Json parser object.
     * @param deserializationContext
     *            Current context.
     * @return Deserialised dynamic fields.
     * @throws IOException
     *             Json reading errors.
     */
    @Override
    public ResponseDynamicFields deserialize(final JsonParser jsonParser,
            final DeserializationContext deserializationContext) throws
            IOException
    {
        final JsonNode jsonNode = jsonParser.readValueAsTree();
        final ResponseDynamicFields dynamicFields = new ResponseDynamicFields();

        final String jsonNodeId = getSettingAsString(ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_JSON_NODE_ALIAS);
        final String jsonNodeValue = getSettingAsString(ShopConfigurationService.CONFIG_KEY_SSO_DYNAMIC_FIELDS_JSON_NODE_VALUE);

        final Iterator<JsonNode> iterator = jsonNode.iterator();
        while (iterator.hasNext())
        {
            final JsonNode currentNode = iterator.next();
            try
            {
                final JsonNode idNode = currentNode.get(jsonNodeId);
                final JsonNode valueNode = currentNode.get(jsonNodeValue);
                if (idNode != null && valueNode != null)
                {
                    dynamicFields.setData(idNode.getTextValue(), valueNode.getTextValue());
                }
            }
            catch (final IllegalAccessException e)
            {
                LOG.debug("Error in JSON deserialization of the customer dynamic fields", e);
            }
        }

        return dynamicFields;
    }

    /**
     * Get setting as string from service configuration.
     * 
     * @param settingKey
     *            Setting key.
     * @return Get the setting value as string.
     */
    private String getSettingAsString(final String settingKey)
    {
        return Registry.getApplicationContext()
                .getBean(ShopConfigurationService.class)
                .getServiceConfiguration().getString(settingKey);
    }
}
