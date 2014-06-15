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
package com.cgi.pacoshop.mock.sso.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * String parser, allows to set values from string to object.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Apr 16, 2014
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public abstract class AbstractFieldParser
{
	private static final Logger LOG = LoggerFactory.getLogger(AbstractFieldParser.class.getName());

	/**
	 * Get string pattern.
	 *
	 * @return Pattern string, example: "someField1,someField2";
	 */
	protected abstract String getPattern();

	/**
	 * Get string delimiter.
	 *
	 * @return Delimiter.
	 */
	protected String getDelimiter()
	{
		return ",";
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

			if (values.length < i + 1 || values[i] == null || String.valueOf(values[i]).isEmpty())
			{
				field.set(this, null);
				continue;
			}

			Object value = values[i];
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
			field.set(this, value);
		}
	}
}
