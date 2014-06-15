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

/**
 * Class that represents Dynamic Fields for Customer.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 09, 2014
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CustomerDynamicField extends AbstractFieldParser
{
	private final static String FIELDS_PATTERN = "id,name,alias,value,refId";

	/**
	 * Field identity.
	 */
	private String id;

	/**
	 * Field alias.
	 */
	private String alias;

	/**
	 * Field value.
	 */
	private String value;

	/**
	 * Field name.
	 */
	private String name;

	/**
	 * Field reference id.
	 */
	private String refId;

	/**
	 * Create object and set it's data.
	 *
	 * @param data Object data.
	 * @throws NoSuchFieldException   No such field in this object.
	 * @throws IllegalAccessException Field cannot be accessed in this object.
	 */
	public CustomerDynamicField(final String data) throws NoSuchFieldException, IllegalAccessException
	{
		this.setData(data);
	}

	/**
	 * Get string pattern.
	 *
	 * @return Pattern string, example: "someField1,someField2";
	 */
	@Override
	protected String getPattern()
	{
		return FIELDS_PATTERN;
	}

	/**
	 * Get identity.
	 *
	 * @return Id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set Identity.
	 *
	 * @param id Field identity.
	 */
	public void setId(final String id)
	{
		this.id = id;
	}

	/**
	 * Get field alias.
	 *
	 * @return Get field alias.
	 */
	public String getAlias()
	{
		return alias;
	}


	/**
	 * Get value related to field.
	 *
	 * @return Related value.
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Get field name.
	 *
	 * @return Field name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get reference id.
	 *
	 * @return Reference id.
	 */
	public String getRefId()
	{
		return refId;
	}
}
