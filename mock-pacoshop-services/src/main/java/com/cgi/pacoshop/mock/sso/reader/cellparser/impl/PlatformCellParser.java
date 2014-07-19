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
package com.cgi.pacoshop.mock.sso.reader.cellparser.impl;

import com.cgi.pacoshop.mock.sso.model.FieldAssignment;
import com.cgi.pacoshop.mock.sso.model.SSOEntity;
import com.cgi.pacoshop.mock.sso.model.TermsVersionAssignment;
import com.cgi.pacoshop.mock.sso.reader.cellparser.CellParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Cell parser for sso_mock_platforms excel file.
 *
 * @module sso mock
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PlatformCellParser implements CellParser
{
	private static final String SSO_DATA_TERMS_VERSION_ASSIGNMENTS = "termsVersionAssignments";
	private static final String SSO_DATA_FIELD_FIELD_ASSIGNMENTS = "fieldAssignments";

	private static final String OBJECT_DELIMITER = "[|]+";

	@Override
	public void parseCell(final SSOEntity platformEntity, final String columnName, final Cell cell) throws NoSuchFieldException,
																																		IllegalAccessException,
																																		ParseException
	{
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String value = cell.getStringCellValue().trim();

		if (columnName.isEmpty())
		{
			return;
		}

		if (value.isEmpty())
		{
			platformEntity.setData(columnName, null);
			return;
		}

		switch (columnName)
		{
			case SSO_DATA_TERMS_VERSION_ASSIGNMENTS:
				String[] termsVersionData = value.split(OBJECT_DELIMITER);
				List<TermsVersionAssignment> termsVersions = new ArrayList<>();
				for (String data : termsVersionData)
				{
					termsVersions.add(new TermsVersionAssignment(data.trim()));
				}
				platformEntity.setData(columnName, termsVersions);
				break;

			case SSO_DATA_FIELD_FIELD_ASSIGNMENTS:
				String[] fieldAssignmentsData = value.split(OBJECT_DELIMITER);
				List<FieldAssignment> fieldAssignments = new ArrayList<>();
				for (String data : fieldAssignmentsData)
				{
					fieldAssignments.add(new FieldAssignment(data.trim()));
				}
				platformEntity.setData(columnName, fieldAssignments);
				break;

			default:
				if (value.equals("true") || value.equals("false"))
				{
					platformEntity.setData(columnName, Boolean.parseBoolean(value));
				}
				else
				{
					platformEntity.setData(columnName, value);
				}
				break;
		}

	}
}
