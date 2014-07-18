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
package com.cgi.pacoshop.core.model;


import com.cgi.pacoshop.core.service.CustomJsonListOfStringTrimmerDeserializer;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Response for field assignment.
 *
 * @module pacoshopcore
 * @version 1.0v Jun 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFieldAssignment
{
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
	@JsonDeserialize(using = CustomJsonListOfStringTrimmerDeserializer.class)
	private List<String> values;
	private String       scope;

	/**
	 *
	 * @return fieldId
	 */
	public String getFieldId()
	{
		return fieldId;
	}

	/**
	 *
	 * @param fieldId fieldId
	 */
	public void setFieldId(final String fieldId)
	{
		this.fieldId = fieldId;
	}

	/**
	 *
	 * @return fieldName
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 *
	 * @param fieldName fieldName
	 */
	public void setFieldName(final String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 *
	 * @return orderByValue
	 */
	public Integer getOrderByValue()
	{
		return orderByValue;
	}

	/**
	 *
	 * @param orderByValue orderByValue
	 */
	public void setOrderByValue(final Integer orderByValue)
	{
		this.orderByValue = orderByValue;
	}

	/**
	 *
	 * @return forwardToPlatform
	 */
	public Boolean getForwardToPlatform()
	{
		return forwardToPlatform;
	}

	/**
	 *
	 * @param forwardToPlatform forwardToPlatform
	 */
	public void setForwardToPlatform(final Boolean forwardToPlatform)
	{
		this.forwardToPlatform = forwardToPlatform;
	}

	/**
	 *
	 * @return mandatory
	 */
	public Boolean getMandatory()
	{
		return mandatory;
	}

	/**
	 *
	 * @param mandatory mandatory
	 */
	public void setMandatory(final Boolean mandatory)
	{
		this.mandatory = mandatory;
	}

	/**
	 *
	 * @return hidden
	 */
	public Boolean getHidden()
	{
		return hidden;
	}

	/**
	 *
	 * @param hidden hidden
	 */
	public void setHidden(final Boolean hidden)
	{
		this.hidden = hidden;
	}

	/**
	 *
	 * @return showOnRegistration
	 */
	public Boolean getShowOnRegistration()
	{
		return showOnRegistration;
	}

	/**
	 *
	 * @param showOnRegistration showOnRegistration
	 */
	public void setShowOnRegistration(final Boolean showOnRegistration)
	{
		this.showOnRegistration = showOnRegistration;
	}

	/**
	 *
	 * @return type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 *
	 * @param type type
	 */
	public void setType(final String type)
	{
		this.type = type;
	}

	/**
	 *
	 * @return min
	 */
	public String getMin()
	{
		return min;
	}

	/**
	 *
	 * @param min min
	 */
	public void setMin(final String min)
	{
		this.min = min;
	}

	/**
	 *
	 * @return max
	 */
	public String getMax()
	{
		return max;
	}

	/**
	 *
	 * @param max max
	 */
	public void setMax(final String max)
	{
		this.max = max;
	}

	/**
	 *
	 * @return values
	 */
	public List<String> getValues()
	{
		return values;
	}

	/**
	 *
	 * @param values values
	 */
	public void setValues(final List<String> values)
	{
		this.values = values;
	}

	/**
	 *
	 * @return scope
	 */
	public String getScope()
	{
		return scope;
	}

	/**
	 *
	 * @param scope scope
	 */
	public void setScope(final String scope)
	{
		this.scope = scope;
	}
}
