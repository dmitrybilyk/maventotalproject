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
package com.cgi.pacoshop.mock.sso.model;


import java.util.List;

/**
 * FiledAssignment entity for readPlatform interface.
 *
 * @module sso mock
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class FieldAssignment extends AbstractFieldParser
{
	private static final String SSO_MOCK_DATA_FIELD_ASSIGNMENT_CELL_PATTERN =
			  "fieldId,fieldName,orderByValue,forwardToPlatform,mandatory,hidden,showOnRegistration,type,min,max,values,scope";

	private String       fieldId;
	private String       fieldName;
	private Integer      orderByValue;
	private Boolean      forwardToPlatform;
	private Boolean      mandatory;
	private Boolean      hidden;
	private Boolean      showOnRegistration;
	private String       type;
	private String       min;
	private String       max;
	private List<String> values;
	private String       scope;

	/**
	 * Create object and set it's data.
	 *
	 * @param data Object data.
	 * @throws NoSuchFieldException   No such field in this object.
	 * @throws IllegalAccessException Field cannot be accessed in this object.
	 */
	public FieldAssignment(final String data) throws NoSuchFieldException, IllegalAccessException
	{
		this.setData(data);
	}

	@Override
	protected String getPattern()
	{
		return SSO_MOCK_DATA_FIELD_ASSIGNMENT_CELL_PATTERN;
	}

	/**
	 *
	 * @return String value fieldId
	 */
	public String getFieldId()
	{
		return fieldId;
	}

	/**
	 *
	 * @return String value fieldId
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 *
	 * @return Integer value orderByValue
	 */
	public Integer getOrderByValue()
	{
		return orderByValue;
	}

	/**
	 *
	 * @return Boolean value forwardToPlatform
	 */
	public Boolean getForwardToPlatform()
	{
		return forwardToPlatform;
	}

	/**
	 *
	 * @return Boolean value mandatory
	 */
	public Boolean getMandatory()
	{
		return mandatory;
	}

	/**
	 *
	 * @return Boolean value hidden
	 */
	public Boolean getHidden()
	{
		return hidden;
	}

	/**
	 *
	 * @return Boolean value showOnRegistration
	 */
	public Boolean getShowOnRegistration()
	{
		return showOnRegistration;
	}

	/**
	 *
	 * @return String value fieldId
	 */
	public String getType()
	{
		return type;
	}

	/**
	 *
	 * @return String value min
	 */
	public String getMin()
	{
		return min;
	}

	/**
	 *
	 * @return String value max
	 */
	public String getMax()
	{
		return max;
	}

	/**
	 *
	 * @return List of String values
	 */
	public List<String> getValues()
	{
		return values;
	}

	/**
	 *
	 * @return String value scope
	 */
	public String getScope()
	{
		return scope;
	}
}
