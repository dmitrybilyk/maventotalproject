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


/**
 * Validator that checks field contains only alphanumerical digits.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Jul 08, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class AlphanumericalValidator extends AbstractFieldValidator
{
	private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";

	/**
	 * ctor.
	 * @param fieldName field name
	 * @param messageCode message code
	 */
	public AlphanumericalValidator(final String fieldName, final String messageCode)
	{
		super(fieldName, messageCode);
	}

	@Override
	public boolean isValid(final ValidationForm form)
	{
		String value = form.getFieldValue(this.fieldName);
		if (StringUtils.isNotBlank(value))
		{
			return value.matches(ALPHANUMERIC_PATTERN);
		}
		return true; // not check empty values for optional fields
	}
}
