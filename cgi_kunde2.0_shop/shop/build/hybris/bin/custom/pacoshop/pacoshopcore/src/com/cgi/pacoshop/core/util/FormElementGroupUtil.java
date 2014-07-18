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
package com.cgi.pacoshop.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 * This class contain some util methods for different descendants of AbstractFormElementGroup class, its validation, etc.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public final class FormElementGroupUtil
{
	private static final String ONE_OR_TWO_DIGIT_STRING_REGEX = "[0-9]{1,2}";
	private static final String FOUR_DIGIT_STRING_REGEX       = "[0-9]{4}";

	// next two lines moved here just in order to remove the "magic strings"
	private static final String SOME_DD_MM_YYYY_DATE_FORMAT_PATTERN = "dd.MM.yyyy";
	private static final String SOME_DD_MM_YYYY_DATE_STRING_FORMAT = "%s.%s.%s";

	private FormElementGroupUtil()
	{

	}

	/**
	 * Assembles the single Date object from the three separated String values.
	 * The date parser format is dd.MM.yyyy. All three parameters should be not empty.
	 *
	 * @param day String value in dd format.
	 * @param month String value in MM format.
	 * @param year String value in yyyy format.
	 * @return Date object in a case of the success or null if all String parameters is empty..
	 * @throws java.text.ParseException in a case when day or month or year is not an empty String but they could
	 */
	public static Date parseDate(final String day, final String month, final String year) throws ParseException
	{
		if (StringUtils.isEmpty(day) && StringUtils.isEmpty(month) && StringUtils.isEmpty(year))
		{
			return null;
		}
		//check that a day value is in dd format, a month value is in MM format and an year value  is in yyyy format
		if (!day.matches(ONE_OR_TWO_DIGIT_STRING_REGEX))
		{
			throw new ParseException(String.format("the day value \"%s\" doesn't corresponds to dd format", day), 0);
		}
		if (!month.matches(ONE_OR_TWO_DIGIT_STRING_REGEX))
		{
			throw new ParseException(String.format("the month value \"%s\" doesn't corresponds to MM format", month), 0);
		}
		if (!year.matches(FOUR_DIGIT_STRING_REGEX))
		{
			throw new ParseException(String.format("the day value \"%s\" doesn't corresponds to yyyy format", year), 0);
		}
		//check that day, month and year could be parsed as a valid date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SOME_DD_MM_YYYY_DATE_FORMAT_PATTERN);
		simpleDateFormat.setLenient(false);
		Date date = simpleDateFormat.parse(String.format(SOME_DD_MM_YYYY_DATE_STRING_FORMAT, day, month, year));
		return date;
	}

}
