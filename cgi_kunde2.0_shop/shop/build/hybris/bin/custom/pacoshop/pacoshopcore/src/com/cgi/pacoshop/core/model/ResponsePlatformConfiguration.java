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


import com.cgi.pacoshop.core.service.CustomJsonStringTrimmerDeserializer;
import com.cgi.pacoshop.core.service.sso.deserializer.FieldAssignmentListDeserializer;
import com.cgi.pacoshop.core.service.sso.deserializer.TermsVersionAssignmentListDeserializer;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Response for terms version.
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
public class ResponsePlatformConfiguration
{
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	private String                               platformId;
	private String                               platformGroupId;
	private String                               mandantId;
	private String                               platformUrlPattern;
	private String                               name;
	private String                               staticResourcesPath;
	private String                               landingPage;
	private Boolean                              uagOverrideHostHeader;
	private String                               uagProxyTo;

	// Used jackson Auto-mapping. Remove comments on deserializers to use custom logic.
	//@JsonDeserialize(using = TermsVersionAssignmentListDeserializer.class)
	private List<ResponseTermsVersionAssignment> termsVersionAssignments;
	//@JsonDeserialize(using = FieldAssignmentListDeserializer.class)
	private List<ResponseFieldAssignment>        fieldAssignments;

	/**
	 *
	 * @return platformId
	 */
	public String getPlatformId()
	{
		return platformId;
	}

	/**
	 *
	 * @param platformId platformId
	 */
	public void setPlatformId(final String platformId)
	{
		this.platformId = platformId;
	}

	/**
	 *
	 * @return platformGroupId
	 */
	public String getPlatformGroupId()
	{
		return platformGroupId;
	}

	/**
	 *
	 * @param platformGroupId platformGroupId
	 */
	public void setPlatformGroupId(final String platformGroupId)
	{
		this.platformGroupId = platformGroupId;
	}

	/**
	 *
	 * @return mandantId
	 */
	public String getMandantId()
	{
		return mandantId;
	}

	/**
	 *
	 * @param mandantId mandantId
	 */
	public void setMandantId(final String mandantId)
	{
		this.mandantId = mandantId;
	}

	/**
	 *
	 * @return platformUrlPattern
	 */
	public String getPlatformUrlPattern()
	{
		return platformUrlPattern;
	}

	/**
	 *
	 * @param platformUrlPattern platformUrlPattern
	 */
	public void setPlatformUrlPattern(final String platformUrlPattern)
	{
		this.platformUrlPattern = platformUrlPattern;
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
	 * @return staticResourcesPath
	 */
	public String getStaticResourcesPath()
	{
		return staticResourcesPath;
	}

	/**
	 *
	 * @param staticResourcesPath staticResourcesPath
	 */
	public void setStaticResourcesPath(final String staticResourcesPath)
	{
		this.staticResourcesPath = staticResourcesPath;
	}

	/**
	 *
	 * @return landingPage
	 */
	public String getLandingPage()
	{
		return landingPage;
	}

	/**
	 *
	 * @param landingPage landingPage
	 */
	public void setLandingPage(final String landingPage)
	{
		this.landingPage = landingPage;
	}

	/**
	 *
	 * @return uagOverrideHostHeader
	 */
	public Boolean getUagOverrideHostHeader()
	{
		return uagOverrideHostHeader;
	}

	/**
	 *
	 * @param uagOverrideHostHeader uagOverrideHostHeader
	 */
	public void setUagOverrideHostHeader(final Boolean uagOverrideHostHeader)
	{
		this.uagOverrideHostHeader = uagOverrideHostHeader;
	}

	/**
	 *
	 * @return uagProxyTo
	 */
	public String getUagProxyTo()
	{
		return uagProxyTo;
	}

	/**
	 *
	 * @param uagProxyTo uagProxyTo
	 */
	public void setUagProxyTo(final String uagProxyTo)
	{
		this.uagProxyTo = uagProxyTo;
	}

	/**
	 *
	 * @return termsVersionAssignments
	 */
	public List<ResponseTermsVersionAssignment> getTermsVersionAssignments()
	{
		return termsVersionAssignments;
	}

	/**
	 *
	 * @param termsVersionAssignments termsVersionAssignments
	 */
	public void setTermsVersionAssignments(final List<ResponseTermsVersionAssignment> termsVersionAssignments)
	{
		this.termsVersionAssignments = termsVersionAssignments;
	}

	/**
	 *
	 * @return fieldAssignments
	 */
	public List<ResponseFieldAssignment> getFieldAssignments()
	{
		return fieldAssignments;
	}

	/**
	 *
	 * @param fieldAssignments fieldAssignments
	 */
	public void setFieldAssignments(final List<ResponseFieldAssignment> fieldAssignments)
	{
		this.fieldAssignments = fieldAssignments;
	}
}
