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
 * POJO used to handle request data in SSOService.writeAccount() method.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @version 1.0v May 06, 2014
 * @module mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class TermAccepted
{

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
