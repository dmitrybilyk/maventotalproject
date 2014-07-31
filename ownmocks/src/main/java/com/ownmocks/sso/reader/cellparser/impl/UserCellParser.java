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
package com.ownmocks.sso.reader.cellparser.impl;


import com.ownmocks.sso.model.CustomerDynamicField;
import com.ownmocks.sso.model.CustomerTerms;
import com.ownmocks.sso.model.SSOEntity;
import com.ownmocks.sso.reader.cellparser.CellParser;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Here goes 1 line.
 *
 * @module kunde_new
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class UserCellParser implements CellParser
{
	private static final String SSO_DATA_FIELD_CLASSIFICATION = "classification";
	private static final String SSO_DATA_FIELD_BUSINESS_PARTNERS = "businessPartners";
	private static final String SSO_DATA_FIELD_DEVICE_IDS = "deviceIds";
	private static final String SSO_DATA_FIELD_ACTIVATION_DELIVERY_DATE = "activationIdDeliveryDate";
	private static final String SSO_DATA_FIELD_DELETION_DATE = "deletionDate";
	private static final String SSO_DATA_FIELD_FIRST_LOGIN = "firstLoginCompleted";
	private static final String SSO_DATA_FIELD_REGISTRATION_DATE = "registrationDate";
	private static final String SSO_DATA_FIELD_VALID_UNTIL = "validUntil";
	private static final String SSO_DATA_FIELD_DYNAMIC_FIELDS = "dynamicFields";
	private static final String SSO_DATA_FIELD_TERMS = "terms";
	private static final String SSO_DATA_FIELD_FAILED_LOGINS = "numberFailedLogins";

	private static final String DELIMS = "[,]+";
	private static final String OBJECT_DELIMITER = "[|]+";

	private static final String DATE_FORMAT = "dd.MM.yyyy";

	/**
	 * Parse cell and set user entity data.
	 *
	 * @param userEntity User object.
	 * @param columnName Column data (user field).
	 * @param cell       Sheet cell object.
	 * @throws NoSuchFieldException   No such field in user object.
	 * @throws IllegalAccessException Access to field of user object not allowed.
	 * @throws java.text.ParseException         Date parse error.
	 */
	@Override
	public void parseCell(final SSOEntity userEntity,
						   final String columnName,
						   final Cell cell) throws NoSuchFieldException, IllegalAccessException, ParseException
	{
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String value = cell.getStringCellValue().trim();

		if (columnName.isEmpty())
		{
			return;
		}

		if (value.isEmpty())
		{
			userEntity.setData(columnName, null);
			return;
		}

		switch (columnName)
		{
			// List
			case SSO_DATA_FIELD_CLASSIFICATION:
			case SSO_DATA_FIELD_BUSINESS_PARTNERS:
			case SSO_DATA_FIELD_DEVICE_IDS:
				String[] valueParts = value.split(DELIMS);
				userEntity.setData(columnName, Arrays.asList(valueParts));
				break;

			// Date
			case SSO_DATA_FIELD_ACTIVATION_DELIVERY_DATE:
			case SSO_DATA_FIELD_DELETION_DATE:
			case SSO_DATA_FIELD_FIRST_LOGIN:
			case SSO_DATA_FIELD_REGISTRATION_DATE:
			case SSO_DATA_FIELD_VALID_UNTIL:
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				Date date = format.parse(value);
				Timestamp timestamp = new Timestamp(date.getTime());
				userEntity.setData(columnName, timestamp);
				break;

			// Dynamic fields
			case SSO_DATA_FIELD_DYNAMIC_FIELDS:
				String[] dynamicFieldsData = value.split(OBJECT_DELIMITER);
				List<CustomerDynamicField> dynamicFields = new ArrayList<>();
				for (String data : dynamicFieldsData)
				{
					dynamicFields.add(new CustomerDynamicField(data.trim()));
				}
				userEntity.setData(columnName, dynamicFields);
				break;

			// Terms
			case SSO_DATA_FIELD_TERMS:
				String[] termsData = value.split(OBJECT_DELIMITER);
				List<CustomerTerms> terms = new ArrayList<>();
				for (String data : termsData)
				{
					terms.add(new CustomerTerms(data.trim()));
				}
				userEntity.setData(columnName, terms);
				break;

			// Integer
			case SSO_DATA_FIELD_FAILED_LOGINS:
				Integer intValue = Integer.valueOf(value);
				userEntity.setData(columnName, intValue);
				break;

			// String
			default:
				if (value.equals("true") || value.equals("false"))
				{
					userEntity.setData(columnName, Boolean.parseBoolean(value));
				}
				else
				{
					userEntity.setData(columnName, value);
				}
				break;
		}
	}
}
