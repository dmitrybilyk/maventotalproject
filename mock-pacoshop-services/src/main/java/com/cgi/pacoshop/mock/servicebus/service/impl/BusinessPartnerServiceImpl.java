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
package com.cgi.pacoshop.mock.servicebus.service.impl;


import com.cgi.pacoshop.mock.servicebus.service.BusinessPartnerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module mock-pacoshop-services
 * @version 1.0v Feb 27, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class BusinessPartnerServiceImpl implements BusinessPartnerService
{
	private static final Integer BUSINESS_PARNTER_ID_INDEX = 0;
	private static final Integer SALUTATION_INDEX          = 1;
	private static final Integer TITLE_INDEX               = 2;
	private static final Integer FIRSTNAME_INDEX           = 3;
	private static final Integer LASTNAME_INDEX            = 4;
	private static final Integer FUNCTION_INDEX            = 5;
	private static final Integer COMPANY_INDEX             = 6;
	private static final Integer ADDRESSSUFFIX_INDEX       = 7;
	private static final Integer STREET_INDEX              = 8;
	private static final Integer STREET_NUMBER_INDEX       = 9;
	private static final Integer POSTALCODE_INDEX          = 10;
	private static final Integer CITY_INDEX                = 11;
	private static final Integer COUNTRY_INDEX             = 12;
	private static final Integer EMAIL_INDEX               = 13;
	private static final Integer EMAIL2_INDEX              = 14;
	private static final Integer PHONE_PREFIX_HOME         = 15;
	private static final Integer PHONE_NUMBER_HOME         = 16;
	private static final Integer PHONE_EXTENSION_HOME      = 17;
	private static final Integer PHONE_PREFIX_BUSINESS     = 18;
	private static final Integer PHONE_NUMBER_BUSINESS     = 19;
	private static final Integer PHONE_EXTENSION_BUSINESS  = 20;
	private static final Integer MOBILE_PREFIX             = 21;
	private static final Integer MOBILE_NUMBER             = 22;
	private static final Integer BIRTHDAY_INDEX            = 23;

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final Logger LOG = LoggerFactory.getLogger(BusinessPartnerServiceImpl.class.getName());

	private static final int ERROR_CODE_400 = 400;
	private static final int ERROR_CODE_403 = 403;
	private static final int ERROR_CODE_404 = 404;

	@Context
	private HttpServletResponse response;

	@Value("${servicebus.businesspartners.file}")
	private String busPartnerFile;

	@Override
	public Response getBusinessPartner(final String businessPartnerId, final int delay) throws IOException,
																															 InterruptedException
	{

		LOG.debug("getBusinessPartner() businessPartnerId={}", businessPartnerId);
		LOG.debug("getBusinessPartner() delay={}", delay);
		LOG.debug("getBusinessPartner() ssoFile={}", busPartnerFile);

		Response result = null;

		Thread.sleep(delay);

		if (businessPartnerId == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try
		{
			final BusinessPartnerData infoByBusinessPartnerId = getInfoByBusinessPartnerId(businessPartnerId.toLowerCase());
			if (infoByBusinessPartnerId == null)
			{
				response.sendError(ERROR_CODE_400);
			}
			else
			{
				result = Response.ok(convertBusinessPartnerDataToJson(infoByBusinessPartnerId)).build();
			}
		}
		catch (final NoSuchElementException e)
		{
			result = Response.ok(convertBusinessPartnerDataToJson(new BusinessPartnerData())).build();
		}

		return result;
	}

	private static String getRowString(final Row row, final Integer index)
	{
		String result = StringUtils.EMPTY;

		final Cell cell = row.getCell(index);
		if (cell != null)
		{
			cell.setCellType(Cell.CELL_TYPE_STRING);
			result = cell.getStringCellValue();
		}

		return result;
	}

	private BusinessPartnerData getInfoByBusinessPartnerId(final String businessPartnerId)
	{

		BusinessPartnerData businessPartnerData = null;

		try (FileInputStream inputStream = new FileInputStream(new File(busPartnerFile).getAbsolutePath()))
		{
			final XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			final XSSFSheet sheet = workbook.getSheetAt(0);
			final Iterator<Row> iterator1 = sheet.iterator();

			if (!iterator1.hasNext())
			{
				//if xlsx file is empty, return 404
				response.sendError(ERROR_CODE_404);
			}
			iterator1.next();

			while (iterator1.hasNext())
			{
				final Row row = iterator1.next();
				if (getRowString(row, BUSINESS_PARNTER_ID_INDEX) != null
						  && getRowString(row, BUSINESS_PARNTER_ID_INDEX).equals(businessPartnerId))

				{
					businessPartnerData = new BusinessPartnerData();

					businessPartnerData.setSalutation(getRowString(row, SALUTATION_INDEX));
					businessPartnerData.setTitle(getRowString(row, TITLE_INDEX));
					businessPartnerData.setFirstname(getRowString(row, FIRSTNAME_INDEX));
					businessPartnerData.setLastname(getRowString(row, LASTNAME_INDEX));
					businessPartnerData.setFunction(getRowString(row, FUNCTION_INDEX));
					businessPartnerData.setCompany(getRowString(row, COMPANY_INDEX));
					businessPartnerData.setAddressSuffix(getRowString(row, ADDRESSSUFFIX_INDEX));
					businessPartnerData.setStreet(getRowString(row, STREET_INDEX));
					businessPartnerData.setStreetNumber(getRowString(row, STREET_NUMBER_INDEX));
					businessPartnerData.setPostalCode(getRowString(row, POSTALCODE_INDEX));
					businessPartnerData.setCity(getRowString(row, CITY_INDEX));
					businessPartnerData.setCountry(getRowString(row, COUNTRY_INDEX));
					businessPartnerData.setEmail(getRowString(row, EMAIL_INDEX));
					businessPartnerData.setEmail2(getRowString(row, EMAIL2_INDEX));
					businessPartnerData.setPhonePrefixHome(getRowString(row, PHONE_PREFIX_HOME));
					businessPartnerData.setPhoneNumberHome(getRowString(row, PHONE_NUMBER_HOME));
					businessPartnerData.setPhoneExtensionHome(getRowString(row, PHONE_EXTENSION_HOME));
					businessPartnerData.setPhonePrefixBusiness(getRowString(row, PHONE_PREFIX_BUSINESS));
					businessPartnerData.setPhoneNumberBusiness(getRowString(row, PHONE_NUMBER_BUSINESS));
					businessPartnerData.setPhoneExtensionBusiness(getRowString(row, PHONE_EXTENSION_BUSINESS));
					businessPartnerData.setMobilePrefix(getRowString(row, MOBILE_PREFIX));
					businessPartnerData.setMobileNumber(getRowString(row, MOBILE_NUMBER));
					String birthdayStr = getRowString(row, BIRTHDAY_INDEX);
					businessPartnerData.setBirthday(new SimpleDateFormat(DATE_FORMAT).parse(birthdayStr));

					break;
				}
			}
		}
		catch (IOException e)
		{
			LOG.error("File" + e.getMessage() + " not found");
			throw new RuntimeException("File" + e.getMessage() + " not found");
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage(), e);
		}


		return businessPartnerData;
	}

	@Override
	public Response getBusinessPartnerIdError(final int code) throws Exception
	{
		return Response.status(code).build();
	}

	private String convertBusinessPartnerDataToJson(final BusinessPartnerData data)
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
		return gson.toJson(data);
	}
}
