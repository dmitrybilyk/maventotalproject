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
package com.cgi.pacoshop.fulfilmentprocess.model;


import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Request to SSO service to register Opt-in and other terms which was accepted by user.
 *
 * @module pacoshopfulfilmentprocess
 * @version 1.0v Feb 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SSORegisterAcceptedTermsRequest implements Serializable
{
	private static final long serialVersionUID = 5299914391870263484L;

	/**
	 * Customer ID.
	 */
	private String accountId;

	/**
	 * Platform ID.
	 */
	private String platformId;

	/**
	 * Accepted terms list. Used as a body of POST request.
	 */
	private AcceptedTermListEntity terms;

	/**
	 * Indicate if SSORegisterAcceptedTermsRequest population process passed successfully and this request should be send to SSO.
	 */
	private boolean populated = false;

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Getter for the accountId field.
	 *
	 * @return customer ID value.
	 */
	public String getAccountId()
	{
		return accountId;
	}

	/**
	 * Setter for the accountId field.
	 *
	 * @param accountId customer ID value.
	 */
	public void setAccountId(final String accountId)
	{
		this.accountId = accountId;
	}

	/**
	 * Getter for the platformId field.
	 *
	 * @return platform ID value.
	 */
	public String getPlatformId()
	{
		return platformId;
	}

	/**
	 * Setter for the platformId field.
	 *
	 * @param platformId customer ID value.
	 */
	public void setPlatformId(final String platformId)
	{
		this.platformId = platformId;
	}

	/**
	 * Getter for the terms field.
	 *
	 * @return list of terms object.
	 */
	public AcceptedTermListEntity getTerms()
	{
		return terms;
	}

	/**
	 * Setter for the terms field.
	 *
	 * @param terms list of terms object.
	 */
	public void setTerms(final AcceptedTermListEntity terms)
	{
		this.terms = terms;
	}

	/**
	 * Getter for the populated field.
	 *
	 * @return the populated
	 */
	public boolean isPopulated()
	{
		return populated;
	}

	/**
	 * Setter for the populated field.
	 *
	 * @param populated the populated to set
	 */
	public void setPopulated(final boolean populated)
	{
		this.populated = populated;
	}


}
