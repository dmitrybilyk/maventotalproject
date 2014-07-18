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
package com.cgi.pacoshop.core.model;


import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonCreator;

/**
 * Product unit for subscription products
 *
 * @module hybris
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public enum ProductUnit
{
	/**
	 * The STUECK.
	 */STUECK("pieces", "pieces", 1d), /**
 * The WOCHE.
 */WOCHE("week", "duration", 1d), /**
 * The MONAT.
 */MONAT("month", "duration", 1d);

	private String code;
	private String unitType;
	private Double conversionFactor;

	ProductUnit(final String code, final String unitType, final Double conversionFactor)
	{
		this.code = code;
		this.unitType = unitType;
		this.conversionFactor = conversionFactor;
	}

	/**
	 * Called by Jackson to create the enum from string value.
	 * @param v Value
	 * @return ProductUnit that corresponds to the string value
	 */
	@JsonCreator
	public static ProductUnit fromValue(final String v)
	{
		if (StringUtils.isNotEmpty(v))
		{
			String trimmedVal = v.trim();
			for (ProductUnit productUnit : values())
			{
				if (productUnit.name().equals(trimmedVal))
				{
					return productUnit;
				}
			}
		}

		throw new IllegalArgumentException(String.format("Invalid string value passed to ProductUnit enum: '%s'", v));
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * Gets unit type.
	 *
	 * @return the unit type
	 */
	public String getUnitType()
	{
		return unitType;
	}

	/**
	 * Gets conversion factor.
	 *
	 * @return the conversion factor
	 */
	public Double getConversionFactor()
	{
		return conversionFactor;
	}
}
