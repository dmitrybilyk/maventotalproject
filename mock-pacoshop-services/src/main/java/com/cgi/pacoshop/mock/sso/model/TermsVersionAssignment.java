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


/**
 * Here goes 1 line.
 *
 * @module kunde_new
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class TermsVersionAssignment extends AbstractFieldParser
{
	private static final String SSO_MOCK_DATA_TERMS_VERSION_ASSIGNMENT_CELL_PATTERN =
			  "mandatory,termsId,termsVersionId,termsVersionIdentifier,url,scope,type,version,name,orderByValue";

	private Boolean mandatory;
	private String termsId;
	private String termsVersionId;
	private String termsVersionIdentifier;
	private String url;
	private String scope;
	private String type;
	private Integer version;
	private String name;
	private Integer orderByValue;

	/**
	 * Create object and set it's data.
	 *
	 * @param data Object data.
	 * @throws NoSuchFieldException   No such field in this object.
	 * @throws IllegalAccessException Field cannot be accessed in this object.
	 */
	public TermsVersionAssignment(final String data) throws NoSuchFieldException, IllegalAccessException
	{
		this.setData(data);
	}

	@Override
	protected String getPattern()
	{
		return SSO_MOCK_DATA_TERMS_VERSION_ASSIGNMENT_CELL_PATTERN;
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
	 * @return String termsId
	 */
	public String getTermsId()
	{
		return termsId;
	}

	/**
	 *
	 * @return String value termsVersionId
	 */
	public String getTermsVersionId()
	{
		return termsVersionId;
	}

	/**
	 *
	 * @return String value termsVersionIdentifier
	 */
	public String getTermsVersionIdentifier()
	{
		return termsVersionIdentifier;
	}

	/**
	 *
	 * @return String value url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 *
	 * @return String value scope
	 */
	public String getScope()
	{
		return scope;
	}

	/**
	 *
	 * @return String value type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 *
	 * @return Integer value version
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 *
	 * @return String value name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 * @return Integer value orderByValue
	 */
	public Integer getOrderByValue()
	{
		return orderByValue;
	}
}
