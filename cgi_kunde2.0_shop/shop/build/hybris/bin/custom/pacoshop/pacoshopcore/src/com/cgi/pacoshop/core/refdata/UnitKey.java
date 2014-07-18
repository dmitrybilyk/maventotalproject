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
package com.cgi.pacoshop.core.refdata;


/**
 * Unique key of {@link de.hybris.platform.core.model.product.UnitModel} object
 *
 * @module pacoshopcore
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class UnitKey
{
	public static final int SEED = 31;
	private String unitName;
	private String unitType;

	/**
	 * Getter.
	 * @return unit name
	 */
	public String getUnitName()
	{
		return unitName;
	}

	/**
	 * Setter.
	 * @param unitName Name of the unit
	 */
	public void setUnitName(final String unitName)
	{
		this.unitName = unitName;
	}

	/**
	 * Getter.
	 * @return Unit type
	 */
	public String getUnitType()
	{
		return unitType;
	}

	/**
	 * Setter.
	 * @param unitType Unit type
	 */
	public void setUnitType(final String unitType)
	{
		this.unitType = unitType;
	}

    /**
     * UnitKeys considered equal if unitName and unitType fields are equal.
     *
     * @param o UnitType
     * @return true if two unit types are equal
     */
	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final UnitKey unitKey = (UnitKey) o;

		if (unitName != null ? !unitName.equals(unitKey.unitName) : unitKey.unitName != null)
		{
			return false;
		}
		if (unitType != null ? !unitType.equals(unitKey.unitType) : unitKey.unitType != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = unitName != null ? unitName.hashCode() : 0;
		result = SEED * result + (unitType != null ? unitType.hashCode() : 0);
		return result;
	}
}
