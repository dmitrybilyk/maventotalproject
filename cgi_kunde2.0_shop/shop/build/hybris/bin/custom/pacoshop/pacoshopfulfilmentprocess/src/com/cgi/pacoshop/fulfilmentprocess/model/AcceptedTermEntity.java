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

/**
 * Represents single {@link com.cgi.pacoshop.core.model.TermAcceptedModel} sent to SSO to register the confirmed terms.
 *
 * @module pacoshopfulfilmentprocess
 * @version 1.0v May 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class AcceptedTermEntity implements Serializable
{
	private static final long serialVersionUID = -6775429730052693448L;

	/**
	 * Unique ID of term.
	 */
	private String termsVersionId;

	/**
	 * Mention if this term accepted or not. Actually should be always true in this mock.
	 */
	private Boolean confirmed;

	/**
	 * Getter for the termsVersionId field.
	 *
	 * @return unique id of term.
	 */
	public String getTermsVersionId()
	{
		return termsVersionId;
	}

	/**
	 * Setter for the termsVersionId field.
	 *
	 * @param termsVersionId unique id of term.
	 */
	public void setTermsVersionId(final String termsVersionId)
	{
		this.termsVersionId = termsVersionId;
	}

	/**
	 * Getter for the confirmed field.
	 *
	 * @return true if the current term is accepted and false otherwise.
	 */
	public Boolean getConfirmed()
	{
		return confirmed;
	}

	/**
	 * Setter for the confirmed field.
	 *
	 * @param confirmed true if the current term is accepted and false otherwise.
	 */
	public void setConfirmed(final Boolean confirmed)
	{
		this.confirmed = confirmed;
	}
}
