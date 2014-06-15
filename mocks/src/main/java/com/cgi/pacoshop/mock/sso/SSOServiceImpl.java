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
package com.cgi.pacoshop.mock.sso;

import com.cgi.pacoshop.mock.sso.api.SSOService;
import com.cgi.pacoshop.mock.sso.model.CustomerDynamicField;
import com.cgi.pacoshop.mock.sso.model.CustomerTerms;
import com.cgi.pacoshop.mock.sso.model.TermsAcceptedList;
import com.cgi.pacoshop.mock.sso.model.UserEntity;
import com.google.gson.Gson;
import org.apache.cxf.common.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * SSO Service implementation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Azarenkov Eugene <azarenkov.eugene@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Apr 03, 2014
 * @module mocks
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see <a href="https://wiki.hybris.com/">wiki.hybris.com</a>
 */
@Service("SSOService")
public class SSOServiceImpl implements SSOService
{
	private static final Logger LOG = LoggerFactory.getLogger(SSOServiceImpl.class.getName());

	private static final int ERROR_CODE_400 = 400;
	private static final int ERROR_CODE_403 = 403;
	private static final int ERROR_CODE_404 = 404;

	private static final String DELIMS = "[,]+";
	private static final String OBJECT_DELIMITER = "[|]+";

	private static final String DATE_FORMAT = "dd.MM.yyyy";

	private static final int USER_ID_INDEX = 0;
	private static final int USER_EMAIL_INDEX = 4;

	private static final String SSO_AUTHORIZATION_HEADER = "x-authorization";
	private static final String SSO_AUTHORIZATION_HEADER_DEFAULT_TOKEN = "wsTokenDefault";
	private static final String SSO_AUTHORIZATION_HEADER_ADMIN_TOKEN = "wsTokenAdmin";

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

	@Context
	private HttpHeaders request;

	@Context
	private HttpServletResponse response;

	@Value("${sso.file}")
	private String ssoFile;

	/**
	 * Set SSO file.
	 *
	 * @param ssoFile Sso file object.
	 */
	public void setSsoFile(final String ssoFile)
	{
		this.ssoFile = ssoFile;
	}

	/**
	 * Method for implementation of behavior described in  acceptance criteria.
	 *
	 * @param accountId User Id to import.
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @return Response.
	 * @throws java.io.IOException Can not write the output.
	 * @throws InterruptedException Response has been interrupted.
	 */
	public Response readAccount(final String accountId,
								final String platformId,
								final int delay) throws IOException, InterruptedException
	{
		final String userId = accountId.toLowerCase();
		final String wsToken = request.getRequestHeaders().getFirst(SSO_AUTHORIZATION_HEADER);

		LOG.debug("readAccount() accountId={}, wsToken={}, platformId={}", userId, wsToken, platformId);
		LOG.debug("readAccount() delay={}", delay);
		LOG.debug("readAccount() ssoFile={}", ssoFile);

		Thread.sleep(delay);

		// Check required params.
		if (accountId == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		// Check token.
		String wsTokenCheck = Integer.toString(userId.hashCode());
		if (StringUtils.isEmpty(wsToken)
				  || (!wsToken.equals(wsTokenCheck) && !wsToken.equals(SSO_AUTHORIZATION_HEADER_DEFAULT_TOKEN)))
		{
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try
		{
			return Response.ok(getInfoByRow(USER_ID_INDEX, userId)).build();
		}
		catch (final NoSuchElementException e)
		{
			return Response.ok(new UserEntity()).build();
		}
	}

	@Override
	public Response getProductError(final int code)
	{
		return Response.status(code).build();
	}

	/**
	 * Response for GET request with params.
	 *
	 * @param email Input param for searching account.
	 * @param delay Processing delay.
	 * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
	 * @throws Exception on error.
	 */
	@Override
	public Response searchAccount(final String email, final int delay) throws Exception
	{
		final String userEmail = email.toLowerCase();

		LOG.debug("searchAccount() email={}", userEmail);
		LOG.debug("searchAccount() delay={}", delay);
		LOG.debug("searchAccount() ssoFile={}", ssoFile);

		Thread.sleep(delay);
		if (userEmail == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try
		{
			return Response.ok(getInfoByRow(USER_EMAIL_INDEX, userEmail)).build();
		}
		catch (final NoSuchElementException e)
		{
			return Response.ok(new UserEntity()).build();
		}
	}

	/**
	 * Updates the customer account with the TermsAcceptedList (initially created for the Opt-in term).
	 * @param accountId User Id to import.
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @param responseStatus optional fake param which allows to simulate different error codes like 404 etc.
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @param terms POST request body. JSON object.
	 *
	 * @return status error code.
	 *
	 * @throws InterruptedException in a case when the Thread.sleep(delay) command would be interrupted.
	 *
	 * see KS-1545 user story for details
	 */
	@Override
	public Response writeAccount(final String accountId, final String platformId, final Integer responseStatus,
										  final Integer delay, final TermsAcceptedList terms) throws InterruptedException
	{
		Gson gson = new Gson();
		final String wsToken = request.getRequestHeaders().getFirst(SSO_AUTHORIZATION_HEADER);
		LOG.debug("writeAccount() accountid={}, platformId={}, wsToken={}, terms={}",
					 accountId, platformId, wsToken, gson.toJson(terms));

		if (delay != null)
		{
			Thread.sleep(delay);
		}
		if (responseStatus != null)
		{
			return Response.status(responseStatus).build();
		}
		final String userId = accountId.toLowerCase();

		// Check required params.
		if (StringUtils.isEmpty(accountId) || terms == null || terms.getTerms().isEmpty())
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		// Check token.
		if (StringUtils.isEmpty(wsToken) || !wsToken.equals(SSO_AUTHORIZATION_HEADER_ADMIN_TOKEN))
		{
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		return Response.noContent().build();
	}

    /**
	 * Method for parsing xlsx document(using apache poi lib) and getting info for user by row index and row value.
	 *
	 * @param rowIndex Row index.
	 * @param rowValue Row value.
	 * @return Object in file.
	 */
	private Object getInfoByRow(final int rowIndex, final String rowValue)
	{
		final UserEntity userEntity = new UserEntity();

		try (FileInputStream inputStream = new FileInputStream(new File(ssoFile).getAbsolutePath()))
		{

			boolean userFound = false;
			final XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			final XSSFSheet sheet = workbook.getSheetAt(0);
			final XSSFRow rootRow = sheet.getRow(0);
			final Iterator<Row> rowIterator = sheet.iterator();
			if (!rowIterator.hasNext())
			{
				//if xlsx file is empty, return 404
				response.sendError(ERROR_CODE_404);
			}
			rowIterator.next();
			while (rowIterator.hasNext())
			{
				final Row row = rowIterator.next();
				if (row.getCell(rowIndex) != null && row.getCell(rowIndex).getStringCellValue().equals(rowValue))
				{
					final Iterator<Cell> cellIterator = row.iterator();
					while (cellIterator.hasNext())
					{
						Cell currentCell = cellIterator.next();
						parseCell(userEntity, rootRow.getCell(currentCell.getColumnIndex()).getStringCellValue(), currentCell);
					}
					userFound = true;
					break;
				}
			}

			//if user with supplied id is not found then return 400
			if (!userFound)
			{
				response.sendError(ERROR_CODE_400);
			}

		}
		catch (IOException ioe)
		{
			LOG.error("File" + ioe.getMessage() + " not found");
			throw new RuntimeException("File" + ioe.getMessage() + " not found");
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage(), e);
		}

		return userEntity;
	}

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
	private void parseCell(final UserEntity userEntity,
						   final String columnName,
						   final Cell cell) throws NoSuchFieldException, IllegalAccessException, ParseException
	{
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String value = cell.getStringCellValue().trim();

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
