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


import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Response for terms version assignment.
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
public class ResponseTermsVersionAssignment
{
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
	 * @return termsId
	 */
	public String getTermsId()
	{
		return termsId;
	}

	/**
	 *
	 * @param termsId termsId
	 */
	public void setTermsId(final String termsId)
	{
		this.termsId = termsId;
	}

	/**
	 *
	 * @return termsVersionId
	 */
	public String getTermsVersionId()
	{
		return termsVersionId;
	}

	/**
	 *
	 * @param termsVersionId termsVersionId
	 */
	public void setTermsVersionId(final String termsVersionId)
	{
		this.termsVersionId = termsVersionId;
	}

	/**
	 *
	 * @return termsVersionIdentifier
	 */
	public String getTermsVersionIdentifier()
	{
		return termsVersionIdentifier;
	}

	/**
	 *
	 * @param termsVersionIdentifier termsVersionIdentifier
	 */
	public void setTermsVersionIdentifier(final String termsVersionIdentifier)
	{
		this.termsVersionIdentifier = termsVersionIdentifier;
	}

	/**
	 *
	 * @return url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 *
	 * @param url url
	 */
	public void setUrl(final String url)
	{
		this.url = url;
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
	 * @return version
	 */
	public Integer getVersion()
	{
		return version;
	}

	/**
	 *
	 * @param version version
	 */
	public void setVersion(final Integer version)
	{
		this.version = version;
	}

	/**
	 *
	 * @return name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 * @param name name
	 */
	public void setName(final String name)
	{
		this.name = name;
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

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final ResponseTermsVersionAssignment that = (ResponseTermsVersionAssignment) o;

		return termsVersionId.equals(that.termsVersionId);
	}

	@Override
	public int hashCode()
	{
		return termsVersionId.hashCode();
	}
}
