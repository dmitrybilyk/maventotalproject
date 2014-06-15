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
package com.cgi.pacoshop.mock.uag;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Util class for MockUAGServlet. Able to calculate the deeplink fingerprint value.
 *
 * @module mocks
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see <a href="https://wiki.hybris.com/">wiki.hybris.com</a>
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 */
public class MockUAGUtil
{
	// list of constants to serve the MockUAGServlet, MockUAGUtil and all relative JSP pages
	public static final  String ULR_PARAM_WS_TOKEN              = "cgi-ws-token";
	public static final  String ULR_PARAM_FINGERPRINT           = "fingerprint";
	public static final  String ULR_PARAM_FINGERPRINT_SECRET_NO = "fingerprintsecretno";
	public static final  String ULR_PARAM_USER_ID               = "cgi-customerid";
	public static final  String ULR_PARAM_USER_EMAIL            = "cgi-customer-email";
	public static final  String ULR_PARAM_USER_TYPE             = "cgi-idtype";
	// field names for the uag.jsp page form
	public static final  String FORM_ITEM_WS_TOKEN              = "wsToken";
	public static final  String FORM_ITEM_FINGERPRINT_SECRET_NO = "fingerprintSecretNo";
	public static final  String FORM_ITEM_REDIRECT_URL          = "redirectURL";
	public static final  String FORM_ITEM_USER_ID               = "userId";
	public static final  String FORM_ITEM_USER_EMAIL            = "userEmail";
	public static final  String FORM_ITEM_USER_TYPE             = "userType";
	// default values
	public static final  int    DEFAULT_FINGERPRINT_SECRET_NO   = 1;
	// magic numbers relative to the fingerprint secret file
	private static final int    SHEET_NO                        = 0;
	private static final int    FIRST_DATA_ROW_INDEX            = 1;
	private static final int    SECRET_ID_COL_INDEX             = 0;
	private static final int    SECRET_VALUE_COL_INDEX          = 1;

	private final static Logger LOGGER = Logger.getLogger(MockUAGUtil.class.getName());

	private final static String[] DEEPLINK_PARAMETERS_TO_CALCULATE = {"productid", "contentid", "contentplatformid"
			  , "contenttitle", "contenturl", "productimageurl", "bonusimageurl", "productid2", "rendermode", "redirecturl",
			  "offerid ", "offerorigin ", "offerid2", "offerorigin2", "campaignid", "fingerprintsecretno"};

	private static String FINGERPRINT_SECRET_FILE_NAME;

	/**
	 * Used to setup the FINGERPRINT_SECRET_FILE_NAME value from Spring context.
	 * This value is read from the deeplink.fingerprint.secret.file property of the com.cgi.pacoshop.mock-pacoshop-default.propeties file.
	 *
	 * @param fingerprintSecretFileName the filename of the .xls file where the fingerprint secret values are stored.
	 */
	public void setFingerprintSecretFileName(final String fingerprintSecretFileName)
	{
		MockUAGUtil.FINGERPRINT_SECRET_FILE_NAME = fingerprintSecretFileName;
	}

	/**
	 * Calculate the SHA1 checksum of specified url params. The checksum calculation algorithm is following:
	 *   Take the secret from the database that was identified by the fingerPrintSecretNo parameter.
	 *   Then append the parameters included in the link as "key=value" pairs, sorted alphabetically by parameter name.
	 *   Include all parameters from the URL, EXCEPT the "fingerprint" parameter itself.
	 *   The different parts of the hash (the secret and the key=value pairs with the parameter names are separated by colons.
	 *
	 * Example:
	 *   For the simplest possible case, where the link contains only the 3 mandatory parameters for productId,
	 *   fingerprint and fingerPrintSecretNo, the combined string to be hashed looks something like this:
	 *       72389thesecretreadfromthedatabase83902:fingerprintSecretNo=36:productId=7283968923
	 *   Then this string is encrypted as an SHA-1 hash, and this hash is sent as value of the fingerprint parameter.
	 *
	 * @param url correct url with the mandatory numeric (long) param "fingerprintSecretNo".
	 * @return SHA1 checksum String value or null in a case of incorrect url.
	 */
	public static String calculateDeeplinkFingerprint(final String url)
	{
		// parse and validate the url value
		URI uri;
		try
		{
			uri = new URI(url);
		}
		catch (URISyntaxException e)
		{
			LOGGER.error(String.format("Incorrect url. URL: %s.", url), e);
			return null;
		}
		List<NameValuePair> params = URLEncodedUtils.parse(uri, "UTF-8");

		// validate mandatory param "fingerprintSecretNo"
		Long fingerprintSecretNo = null;
		for (NameValuePair param : params)
		{
			if (ULR_PARAM_FINGERPRINT_SECRET_NO.equalsIgnoreCase(param.getName()))
			{
				try
				{
					fingerprintSecretNo = Long.parseLong(param.getValue());
					break;
				}
				catch (NumberFormatException e)
				{
					LOGGER.error(String.format("The " + ULR_PARAM_FINGERPRINT_SECRET_NO + " value isn't Long. URL: %s", url), e);
					return null;
				}
			}
		}
		if (fingerprintSecretNo == null)
		{
			LOGGER.error(String.format("The " + ULR_PARAM_FINGERPRINT_SECRET_NO + " param isn't found. URL: %s", url));
			return null;
		}

		// exclude the fingerprint parameter itself from the params list
		for (NameValuePair param : params)
		{
			if (ULR_PARAM_FINGERPRINT.equalsIgnoreCase(param.getName()))
			{
				params.remove(param);
				break;
			}
		}

		// calculate deeplink fingerprint with gathered params.
		// Note that TreeMap stores items sorted in the natural order of its keys. It will speedup the paramMap keys sorting.
		Map<String, String> paramMap = new TreeMap<>();
		for (NameValuePair param : params)
		{
			paramMap.put(param.getName(), param.getValue());
		}
		return calculateDeeplinkFingerprint(fingerprintSecretNo, paramMap);
	}

	/**
	 * Calculate the SHA1 checksum of specified url params. The checksum calculation algorithm is following:
	 *   Take the secret from the database that was identified by the fingerPrintSecretNo parameter.
	 *   Then append the parameters included in the link as "key=value" pairs, sorted alphabetically by parameter name.
	 *   Include all parameters from the URL, EXCEPT the "fingerprint" parameter itself.
	 *   The different parts of the hash (the secret and the key=value pairs with the parameter names are separated by colons.
	 *
	 * Example:
	 *   For the simplest possible case, where the link contains only the 3 mandatory parameters for productId,
	 *   fingerprint and fingerPrintSecretNo, the combined string to be hashed looks something like this:
	 *	      72389thesecretreadfromthedatabase83902:fingerprintSecretNo=36:productId=7283968923
	 *   Then this string is encrypted as an SHA-1 hash, and this hash is sent as value of the fingerprint parameter.
	 *
	 * @param fingerprintSecretNo identifier of fingerprint secret value which is stored in DB.
	 * @param params deeplink url params stored in Map by its keys.
	 * @return SHA1 checksum String value or null in a case of incorrect url.
	 */
	public static String calculateDeeplinkFingerprint(final long fingerprintSecretNo, final Map<String, String> params)
	{
		String fingerprintSecret = getFingerprintSecret(fingerprintSecretNo);
		if (fingerprintSecret == null)
		{
			LOGGER.error(String.format("The fingerprintSecret isn't found in XML. "
											 + ULR_PARAM_FINGERPRINT_SECRET_NO + ": %d", fingerprintSecretNo));
			return null;
		}

		// collect a fingerprint source string to calculate SHA1 checksum
		StringBuilder fingerprintSource = new StringBuilder(fingerprintSecret);
		List<String> paramNames = new ArrayList<>(params.keySet());
		final Set<String> deeplinkParametersToCalculate = new HashSet<>(Arrays.asList(DEEPLINK_PARAMETERS_TO_CALCULATE));
		Collections.sort(paramNames);
		for (String paramName : paramNames)
		{
			if (deeplinkParametersToCalculate.contains(paramName))
			{
				fingerprintSource.append(":").append(paramName).append("=").append(params.get(paramName));
			}
		}

		// calculate SHA1 checksum
		String fingerprint = DigestUtils.shaHex(fingerprintSource.toString());
		return fingerprint;
	}

	/**
	 * then open this .xls file and read the value of secret with id == fingerprintSecretNo.
	 *
	 * The real fingerprint values should be stored in DB, but this is just a MOCK.
	 *
	 * @param fingerprintSecretNo id of fingerprint secret value which is stored in an .xls file.
	 * @return the fingerprint secret value stored in an .xls file by its id.
	 */
	public static String getFingerprintSecret(final long fingerprintSecretNo)
	{
		Workbook workbook = null;
		try
		{
			workbook = Workbook.getWorkbook(new File(FINGERPRINT_SECRET_FILE_NAME));
			Sheet sheet = workbook.getSheet(SHEET_NO);

			int rowCount = sheet.getRows();
			for (int rowIndex = FIRST_DATA_ROW_INDEX; rowIndex < rowCount; rowIndex++)
			{
				String secretIdValue = sheet.getCell(SECRET_ID_COL_INDEX, rowIndex).getContents().trim();
				try
				{
					Long secretId = Long.parseLong(secretIdValue);
					if (fingerprintSecretNo == secretId)
					{
						return sheet.getCell(SECRET_VALUE_COL_INDEX, rowIndex).getContents();
					}
				}
				catch (NumberFormatException e)
				{
					LOGGER.warn(String.format("The %s contain broken secretId value in the cell [%d, %d]. "
													+ "Cell value is \"%s\".", FINGERPRINT_SECRET_FILE_NAME, 0, rowIndex, secretIdValue));
				}
			}
		}
		catch (final IOException e)
		{
			LOGGER.error(String.format("The %s is not found.", FINGERPRINT_SECRET_FILE_NAME), e);
		}
		catch (final BiffException e)
		{
			LOGGER.error(String.format("The %s is broken.", FINGERPRINT_SECRET_FILE_NAME), e);
		}
		finally
		{
			if (workbook != null)
			{
				workbook.close();
			}
		}
		// nothing found
		return null;
	}
}
