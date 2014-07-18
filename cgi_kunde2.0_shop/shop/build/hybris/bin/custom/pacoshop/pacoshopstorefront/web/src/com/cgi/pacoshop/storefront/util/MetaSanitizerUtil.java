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
package com.cgi.pacoshop.storefront.util;

import de.hybris.platform.catalog.model.KeywordModel;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility class for sanitizing up content that will appear in HTML meta tags.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01, 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public final class MetaSanitizerUtil
{
	private MetaSanitizerUtil()
	{
	}

	/**
	 * Takes a List of KeywordModels and returns a comma separated list of keywords as String.
	 *
	 * @param keywords List of KeywordModel objects.
	 * @return String of comma separated keywords.
	 */
	public static String sanitizeKeywords(final List<KeywordModel> keywords)
	{
		if (keywords != null && !keywords.isEmpty())
		{
			// Remove duplicates
			final Set<String> keywordSet = new HashSet<String>(keywords.size());
			for (final KeywordModel kw : keywords)
			{
				keywordSet.add(kw.getKeyword());
			}

			// Format keywords, join with comma
			final StringBuilder stringBuilder = new StringBuilder();
			for (final String kw : keywordSet)
			{
				stringBuilder.append(kw).append(',');
			}
			if (stringBuilder.length() > 0)
			{
				// Remove last comma
				return stringBuilder.substring(0, stringBuilder.length() - 1);
			}
		}
		return "";
	}

	/**
	 * Takes a string of words, removes duplicates and returns a comma separated list of keywords as a String.
	 *
	 * @param keywords Keywords to be sanitized.
	 * @return String of comma separated keywords.
	 */
	public static String sanitizeKeywords(final String keywords)
	{
		final String clean = (StringUtils.isNotEmpty(keywords) ? Jsoup.parse(keywords).text() : ""); // Clean html
		final String[] words = StringUtils.split(clean.replace("\"", "")); // Clean quotes

		// Remove duplicates
		StringBuilder noDupes = new StringBuilder();
		for (final String word : words)
		{
			if (noDupes.indexOf(word) == -1)
			{
				noDupes.append(word);
				noDupes.append(',');
			}
		}

		if (noDupes.length() > 0)
		{
			noDupes.deleteCharAt(noDupes.length() - 1);
		}
		return noDupes.toString();
	}

	/**
	 * Removes all HTML tags and double quotes and returns a String.
	 *
	 * @param description Description to be sanitized.
	 * @return String object
	 */
	public static String sanitizeDescription(final String description)
	{
		if (StringUtils.isNotEmpty(description))
		{
			final String clean = Jsoup.parse(description).text();
			return clean.replace("\"", "");
		}
		else
		{
			return "";
		}
	}
}
