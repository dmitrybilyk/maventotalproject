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
package com.cgi.pacoshop.core.validation.rules;


import com.cgi.hybris.payment.core.validation.ValidationForm;
import com.cgi.hybris.payment.core.validation.rules.AbstractFieldValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;


/**
 * Validates IBAN via apache commons library.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Jul 08, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class IBANValidator extends AbstractFieldValidator
{

	/**
	 * ctor.
	 * @param fieldName field name
	 * @param messageCode message code
	 */
	public IBANValidator(final String fieldName, final String messageCode)
	{
		super(fieldName, messageCode);
	}

	@Override
	public boolean isValid(final ValidationForm form)
	{
		String value = form.getFieldValue(this.fieldName);
		if (StringUtils.isNotBlank(value))
		{
			return IBANCheckDigit.IBAN_CHECK_DIGIT.isValid(value);
		}
		return true; // not check empty values for optional fields
	}
}
