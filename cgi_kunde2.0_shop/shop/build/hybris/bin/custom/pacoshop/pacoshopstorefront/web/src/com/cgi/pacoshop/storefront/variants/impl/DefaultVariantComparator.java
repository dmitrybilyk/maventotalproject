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
package com.cgi.pacoshop.storefront.variants.impl;

import org.apache.commons.lang.math.NumberUtils;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Default comparator for variant values.
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
public class DefaultVariantComparator implements Comparator<Object>, Serializable
{
	@Override
	public int compare(final Object variant1, final Object variant2)
	{
		if (variant1 instanceof Number)
		{
			final double number1 = ((Number) variant1).doubleValue();
			final double number2 = ((Number) variant2).doubleValue();
			return NumberUtils.compare(number1, number2);
		}
		else if (variant1 instanceof String)
		{
			final String string1 = (String) variant1;
			final String string2 = (String) variant2;
			return string1.compareTo(string2);
		}
		else
		{
			if (variant1 == null && variant2 == null)
			{
				return 0;
			}
			else if (variant1 == null)
			{
				return -1;
			}
			else if (variant2 == null)
			{
				return 1;
			}
			return variant1.toString().compareTo(variant2.toString());
		}
	}
}
