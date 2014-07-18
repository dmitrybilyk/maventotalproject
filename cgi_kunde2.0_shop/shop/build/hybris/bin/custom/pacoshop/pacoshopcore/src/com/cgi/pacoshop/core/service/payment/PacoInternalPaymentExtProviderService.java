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
package com.cgi.pacoshop.core.service.payment;


import com.cgi.hybris.payment.core.constants.HybrispaymentcoreConstants;
import com.cgi.hybris.payment.core.model.PaymentMethodModel;
import com.cgi.hybris.payment.core.services.impl.InternalPaymentExtProviderService;
import com.cgi.hybris.payment.core.validation.ValidationForm;
import com.cgi.hybris.payment.core.validation.Validator;
import com.cgi.hybris.payment.core.validation.rules.FieldsMandatoryValidator;
import com.cgi.hybris.payment.core.validation.rules.IntegerValidator;
import com.cgi.hybris.payment.core.validation.rules.LengthValidator;
import com.cgi.hybris.payment.core.validation.rules.MandatoryValidator;
import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.validation.rules.AlphanumericalValidator;
import com.cgi.pacoshop.core.validation.rules.FieldsExcessiveValidator;
import com.cgi.pacoshop.core.validation.rules.IBANValidator;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;


/**
 * AbstractPaymentExtProviderService implementation with override validation for direct debit.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Jul 04, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class PacoInternalPaymentExtProviderService extends InternalPaymentExtProviderService
{
	private static final Logger LOG = Logger.getLogger(PacoInternalPaymentExtProviderService.class);

	private final List<Validator> directDebitValidators = Arrays.<Validator>asList(
			  new MandatoryValidator(HybrispaymentcoreConstants.UserData.BankAccount.ACCOUNT_OWNER,
											 HybrispaymentcoreConstants.MessageKey.BankAccountKeys.ACCOUNT_OWNER_INVALID),
			  new MandatoryValidator(HybrispaymentcoreConstants.UserData.BankAccount.BANK_NAME,
											 HybrispaymentcoreConstants.MessageKey.BankAccountKeys.BANK_NAME_INVALID),
			  new FieldsMandatoryValidator(HybrispaymentcoreConstants.MessageKey.BankAccountKeys.ACCOUNT_INVALID,
													 new String[]{HybrispaymentcoreConstants.UserData.BankAccount.ACCOUNT_NUMBER,
																HybrispaymentcoreConstants.UserData.BankAccount.BANK_NUMBER},
													 new String[]{HybrispaymentcoreConstants.UserData.BankAccount.IBAN,
																HybrispaymentcoreConstants.UserData.BankAccount.BIC}),
			  new FieldsExcessiveValidator(PacoshopCoreConstants.MessageKey.BankAccountKeys.ACCOUNT_CROWDED,
													 new String[]{HybrispaymentcoreConstants.UserData.BankAccount.ACCOUNT_NUMBER,
																HybrispaymentcoreConstants.UserData.BankAccount.BANK_NUMBER},
													 new String[]{HybrispaymentcoreConstants.UserData.BankAccount.IBAN,
																HybrispaymentcoreConstants.UserData.BankAccount.BIC}),
			  new LengthValidator(HybrispaymentcoreConstants.UserData.BankAccount.BANK_NUMBER,
										 PacoshopCoreConstants.MessageKey.BankAccountKeys.BANK_NUMBER_LENGTH_INVALID,
										 Integer.valueOf(8), Integer.valueOf(8)),
			  new IntegerValidator(HybrispaymentcoreConstants.UserData.BankAccount.BANK_NUMBER,
										  HybrispaymentcoreConstants.MessageKey.BankAccountKeys.BANK_NUMBER_INVALID),
			  new LengthValidator(HybrispaymentcoreConstants.UserData.BankAccount.ACCOUNT_NUMBER,
										 PacoshopCoreConstants.MessageKey.BankAccountKeys.ACCOUNT_NUMBER_LENGTH_INVALID,
										 Integer.valueOf(1), Integer.valueOf(10)),
			  new IntegerValidator(HybrispaymentcoreConstants.UserData.BankAccount.ACCOUNT_NUMBER,
										  HybrispaymentcoreConstants.MessageKey.BankAccountKeys.ACCOUNT_NUMBER_INVALID),
			  new IBANValidator(HybrispaymentcoreConstants.UserData.BankAccount.IBAN,
									  HybrispaymentcoreConstants.MessageKey.BankAccountKeys.IBAN_INVALID),
			  new LengthValidator(HybrispaymentcoreConstants.UserData.BankAccount.BIC,
										 PacoshopCoreConstants.MessageKey.BankAccountKeys.BIC_NUMBER_LENGTH_INVALID,
										 Integer.valueOf(8), Integer.valueOf(11)),
			  new AlphanumericalValidator(HybrispaymentcoreConstants.UserData.BankAccount.BIC,
													HybrispaymentcoreConstants.MessageKey.BankAccountKeys.BIC_INVALID)
	);

	/**
	 * ctor.
	 */
	public PacoInternalPaymentExtProviderService()
	{
		super();
	}

	/**
	 * Validates direct debit.
	 *
	 * @param paymentMethod payment method;
	 * @param form validation form;
	 */
	@Override
	protected void validateDirectDebit(final PaymentMethodModel paymentMethod, final ValidationForm form)
	{
		runValidation(form, directDebitValidators);
	}


}
