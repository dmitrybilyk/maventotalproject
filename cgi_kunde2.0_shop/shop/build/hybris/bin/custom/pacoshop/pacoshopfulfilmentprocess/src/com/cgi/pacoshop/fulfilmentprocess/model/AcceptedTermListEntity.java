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
import java.util.List;

/**
 * Represents the list of terms confirmed by user to be sent to SSO.
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
public class AcceptedTermListEntity implements Serializable
{
	private static final long serialVersionUID = 5694694079224344352L;

	private List<AcceptedTermEntity> terms;

	/**
	 * Getter for the terms field.
	 *
	 * @return list of TermAccepted objects.
	 */
	public List<AcceptedTermEntity> getTerms()
	{
		return terms;
	}

	/**
	 * Setter for the terms field.
	 *
	 * @param terms list of TermAccepted objects.
	 */
	public void setTerms(final List<AcceptedTermEntity> terms)
	{
		this.terms = terms;
	}
}
