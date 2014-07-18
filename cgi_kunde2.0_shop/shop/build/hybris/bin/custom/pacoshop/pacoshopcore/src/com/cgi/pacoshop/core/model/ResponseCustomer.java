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
import com.cgi.pacoshop.core.service.CustomJsonStringTrimmerDeserializer;
import com.cgi.pacoshop.core.service.DynamicFieldsDeserializer;
import com.cgi.pacoshop.core.service.term.TermAcceptedListDeserializer;
import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * ResponseCustomer for imprort userdata via rest client from SSO.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCustomer implements Serializable
{
	private static final long serialVersionUID = 4073497957695884490L;

	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	private String accountId;

	@JsonDeserialize(using = DynamicFieldsDeserializer.class)
	private ResponseDynamicFields dynamicFields;

	@JsonDeserialize(using = CustomJsonListOfStringTrimmerDeserializer.class)
	private List<String> businessPartners;

	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	private String email;

	@JsonDeserialize(using = TermAcceptedListDeserializer.class)
	private List<ResponseTermAccepted> terms;

	/**
	 *	empty constructor.
	 */
	public ResponseCustomer()
	{
		//
	}

	/**
	 *	For json desirialization constructor.
	 *
	 * @param dynamicFields the dynamicFields
	 *
	 */
	public ResponseCustomer(final ResponseDynamicFields dynamicFields)
	{
		this.dynamicFields = dynamicFields;
	}

	/**
	 * Gets dynamicFields.
	 *
	 * @return the dynamicFields
	 */
	public ResponseDynamicFields getDynamicFields()
	{
		return dynamicFields;
	}

	/**
	 * Sets dynamicFields.
	 *
	 * @param dynamicFields the dynamicFields
	 */
	public void setDynamicFields(final ResponseDynamicFields dynamicFields)
	{
		this.dynamicFields = dynamicFields;
	}

	/**
	 * Gets accountId.
	 *
	 * @return the accountId
	 */
	public String getAccountId()
	{
		return accountId;
	}

	/**
	 * Sets accountId.
	 *
	 * @param accountId the accountId
	 */
	public void setAccountId(final String accountId)
	{
		this.accountId = accountId;
	}

	/**
	 * Gets businessPartners.
	 *
	 * @return the businessPartners
	 */
	public List<String> getBusinessPartners()
	{
		return businessPartners;
	}

	/**
	 * Sets businessPartners.
	 *
	 * @param businessPartners the businessPartners
	 */
	public void setBusinessPartners(final List<String> businessPartners)
	{
		this.businessPartners = businessPartners;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Gets termsAccepted values list.
	 *
	 * @return the list of termsAccepted values.
	 */
	public List<ResponseTermAccepted> getTerms()
	{
		return terms;
	}

	/**
	 * Gets termsAccepted values list.
	 *
	 * @param termsAccepted  the list of termsAccepted values.
	 */
	public void setTerms(final List<ResponseTermAccepted> termsAccepted)
	{
		this.terms = termsAccepted;
	}
}
