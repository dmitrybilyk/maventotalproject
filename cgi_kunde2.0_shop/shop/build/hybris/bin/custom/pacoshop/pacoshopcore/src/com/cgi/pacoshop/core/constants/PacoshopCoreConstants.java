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
package com.cgi.pacoshop.core.constants;

/**
 * Global class for all PacoshopCore constants. You can add global constants for your extension into this class.
 */
public final class PacoshopCoreConstants extends GeneratedPacoshopCoreConstants
{
    public static final String EXTENSIONNAME = "pacoshopcore";

    private PacoshopCoreConstants()
    {
        //empty
    }

    public static class EmailErrorSending
    {
        public static final String RETRY_SENDING = "email.order.error.sending.retry.count";
        public static final String DELAY_BETWEEN_RETRIES = "email.order.error.sending.delay";
        public static final String SEND_TO = "email.order.error.sending.send.to";

    }

    public static class EmailFulfillmentSending
    {
        public static final String RETRY_SENDING = "email.fulfillment.sending.retry.count";
        public static final String DELAY_BETWEEN_RETRIES = "email.fulfillment.sending.delay";
        public static final String SEND_TO = "email.fulfillment.send.to";
        public static final String MAIN_PROCESS_CODE = "mainProcessCode";
        public static final String EMAIL_FULFILLMENT_FINISHED_SUFFIX = "_emailFulfillmentFinished";
    }

    public static class CustomerAddressFields
    {
        public static final String SALUTATION = "salutation";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
        public static final String PHONE_NUMBER = "phone";
        public static final String MOBILE_PHONE_NUMBER = "mobile";
        public static final String LINE_1 = "street";
        public static final String LINE_2 = "additional-street";
        public static final String STREET_AND_NUMBER = "street-number";
        public static final String PLZ_ZIP = "zip";
        public static final String CITY_ORT = "city";
        public static final String COUNTRY = "country";
        public static final String BIRTH_DATE = "birthDateYear";
        public static final String TITLE = "title";
        public static final String COMPANY = "company";
        public static final String ROLE_IN_COMPANY = "roleInCompany";
    }

	 public interface MessageKey
	 {
		public interface BankAccountKeys
		{
			public static final String ACCOUNT_CROWDED = "payment.bankaccount.excessive.data";
			public static final String BANK_NUMBER_LENGTH_INVALID = "payment.bankaccount.bankCode.length.invalid";
			public static final String ACCOUNT_NUMBER_LENGTH_INVALID = "payment.accountCode.length.invalid";
			public static final String BIC_NUMBER_LENGTH_INVALID = "payment.bic.length.ivalid";
		}
	 }
}
