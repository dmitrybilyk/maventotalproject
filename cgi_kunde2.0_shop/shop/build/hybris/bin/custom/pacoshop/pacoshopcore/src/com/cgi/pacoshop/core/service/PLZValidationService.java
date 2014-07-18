/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.service;

import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;

/**
 * PLZ_ZIP validation Service
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 30, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public interface PLZValidationService
{
	/**
	 * Check is form valid.
	 * @param dto - Form data transfer object to validate.
	 * @return true if form valid.
	 */
	boolean validate(final CheckoutFormDTO dto);

	/**
	 * Check if form is valid and throws errors.
	 * @param zip - zipcode
	 * @param countryCode - country code
	 */
	void validate(String zip, String countryCode);


	/**
	 * Check is form valid.
	 * @param zip - zipcode
	 * @param countryCode - country code
	 * @return true if form valid.
	 */
	boolean validateZipCode(final String zip, final String countryCode);
}
