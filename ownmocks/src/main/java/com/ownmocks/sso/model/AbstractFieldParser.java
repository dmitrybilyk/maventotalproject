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
package com.ownmocks.sso.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String parser, allows to set values from string to object.
 *
 * @module mock-pacoshop-services
 * @version 1.0v Apr 16, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public abstract class AbstractFieldParser
{
	private static final Logger LOG = LoggerFactory.getLogger(AbstractFieldParser.class.getName());
	private static final String FIELD_NAME_END_WITH_LIST = "List";
	private static final String NULL = "null";

	/**
	 * Get string pattern.
	 *
	 * @return Pattern string, example: "someField1,someField2";
	 */
	protected abstract String getPattern();

	/**
	 * Get string delimiter.
	 *
	 * @return delimiter for items inside single cell of XLSX file.
	 */
	protected String getDelimiter()
	{
		return ",";
	}

	/**
	 * Get string delimiter.
	 *
	 * @return delimiter for items inside list if multiple lists are placed inside single cell in XLSX file.
	 */
	protected String getListDelimiter()
	{
		return ";";
	}

	/**
	 * Get string delimiter.
	 *
	 * @return Delimiter.
	 */
	protected String getListOpen()
	{
		return "[";
	}

	/**
	 * Get string delimiter.
	 *
	 * @return Delimiter.
	 */
	protected String getListClosed()
	{
		return "]";
	}

	/**
	 * Set data from input string.
	 *
	 * @param data Values in string.
	 * @throws NoSuchFieldException   No such field in this object.
	 * @throws IllegalAccessException Field cannot be accessed in this object.
	 */
	public void setData(final String data) throws NoSuchFieldException, IllegalAccessException
	{
		String[] fields = getPattern().split(getDelimiter());
		String[] values = data.split(getDelimiter());

		for (int i = 0; i < fields.length; i++)
		{
			Field field = this.getClass().getDeclaredField(fields[i]);
			field.setAccessible(true);

			if (checkOnNull(values, i))
			{
				field.set(this, null);
				continue;
			}

			Object value = values[i];

			if (field.getType().getName().endsWith(FIELD_NAME_END_WITH_LIST))
			{
				String subValue = StringUtils.substringBetween(value.toString(), getListOpen(), getListClosed());
				if (subValue.isEmpty())
				{
					value = Collections.EMPTY_LIST;
				}
				else
				{
					String [] subValues = subValue.split(getListDelimiter());
					value = Arrays.asList(subValues);
				}
			}
			else
			{
				try
				{
					Constructor valueFromStrConstructor = field.getType().getDeclaredConstructor(String.class);
					value = valueFromStrConstructor.newInstance(value);
				}
				catch (Exception e)
				{
					LOG.error(String.format("Couldn't create a %s class instance from a String value \"%s\".",
													field.getType().getName(), value), e);
				}
			}
			field.set(this, value);
		}
	}

	private static boolean checkOnNull(final String [] values, final int index)
	{
		return values.length < index + 1
				  || values[index] == null
				  || String.valueOf(values[index]).isEmpty()
				  || values[index].equals(NULL);
	}
}
