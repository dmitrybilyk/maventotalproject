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


import java.util.List;

/**
 * The representation of the list of TermAccepted object. Used for the automatically parsing the SSOService.writeAccount() method
 * "term" parameter.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @version 1.0v May 06, 2014
 * @module mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class TermsAcceptedList
{
	private List<TermAccepted> terms;

	/**
	 * Getter for the terms field.
	 *
	 * @return list of TermAccepted objects.
	 */
	public List<TermAccepted> getTerms()
	{
		return terms;
	}

	/**
	 * Setter for the terms field.
	 *
	 * @param terms list of TermAccepted objects.
	 */
	public void setTerms(final List<TermAccepted> terms)
	{
		this.terms = terms;
	}
}
