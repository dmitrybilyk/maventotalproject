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


import java.lang.reflect.Field;
import java.util.List;

/**
 * PlatformEntity.
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
public class PlatformEntity implements SSOEntity
{
	private String                       platformId;
	private String                       platformGroupId;
	private String                       mandantId;
	private String                       platformUrlPattern;
	private String                       name;
	private String                       staticResourcesPath;
	private String                       landingPage;
	private Boolean                      uagOverrideHostHeader;
	private String                       uagProxyTo;
	private List<TermsVersionAssignment> termsVersionAssignments;
	private List<FieldAssignment>        fieldAssignments;


	/**
	 * Set user data.
	 *
	 * @param fieldName User field name.
	 * @param value     Field value.
	 * @throws NoSuchFieldException   No such field in user object.
	 * @throws IllegalAccessException Field can not be accessed in user object.
	 */
	@Override
	public void setData(final String fieldName, final Object value) throws NoSuchFieldException, IllegalAccessException
	{
		Field field = this.getClass().getDeclaredField(fieldName);
		field.set(this, value);
	}

	/**
	 *
	 * @return String value platformId
	 */
	public String getPlatformId()
	{
		return platformId;
	}

	/**
	 *
	 * @return String value platformGroupId
	 */
	public String getPlatformGroupId()
	{
		return platformGroupId;
	}

	/**
	 *
	 * @return  String value mandantId
	 */
	public String getMandantId()
	{
		return mandantId;
	}

	/**
	 *
	 * @return String value platformUrlPattern
	 */
	public String getPlatformUrlPattern()
	{
		return platformUrlPattern;
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
	 * @return String value staticResourcesPath
	 */
	public String getStaticResourcesPath()
	{
		return staticResourcesPath;
	}

	/**
	 *
	 * @return String value landingPage
	 */
	public String getLandingPage()
	{
		return landingPage;
	}

	/**
	 *
	 * @return Boolean value uagOverrideHostHeader
	 */
	public Boolean getUagOverrideHostHeader()
	{
		return uagOverrideHostHeader;
	}

	/**
	 *
	 * @return String value uagProxyTo
	 */
	public String getUagProxyTo()
	{
		return uagProxyTo;
	}

	/**
	 *
	 * @return List of TermsVersionAssignment
	 */
	public List<TermsVersionAssignment> getTermsVersionAssignments()
	{
		return termsVersionAssignments;
	}

	/**
	 *
	 * @return List of FieldAssignment
	 */
	public List<FieldAssignment> getFieldAssignments()
	{
		return fieldAssignments;
	}
}
