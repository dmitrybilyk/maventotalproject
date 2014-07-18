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
import com.cgi.hybris.payment.core.validation.rules.AbstractGlobalValidator;
import org.apache.commons.lang.StringUtils;


/**
 * Validation that only one of the fields set are filled.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Jul 04, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class FieldsExcessiveValidator extends AbstractGlobalValidator
{

	private final String[] fieldsEither;
	private final String[] fieldsOr;

	/**
	 * ctor.
	 * @param messageCode message code
	 * @param fieldsEither first fields set
	 * @param fieldsOr second fields set
	 */
	public FieldsExcessiveValidator(final String messageCode, final String[] fieldsEither, final String[] fieldsOr)
	{
		super(messageCode);
		this.fieldsEither = fieldsEither;
		this.fieldsOr = fieldsOr;
	}

	@Override
	public boolean isValid(final ValidationForm form)
	{
		return checkFields(form, fieldsEither) ^ checkFields(form, fieldsOr);
	}


	/**
	 *
	 * @param form validation form
	 * @param fields fields set
	 * @return true if one of the field are not empty.
	 */
	protected boolean checkFields(final ValidationForm form, final String[] fields)
	{
		if (fields != null)
		{
			for (final String field : fields)
			{
				final String value = form.getFieldValue(field);
				if (StringUtils.isNotBlank(value))
				{
					return true;
				}
			}
		}
		return false;
	}
}
