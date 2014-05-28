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
package com.learn.formgroups.dtos;


/**
 * DTO for tax value.
 *
 * @module shop
 * @version 1.0v Apr 23, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class TaxValueDTO
{
	private Double value;
	private Double appliedValue;

	/**
	 *
	 * @return the value.
	 */
	public Double getValue()
	{
		return value;
	}

	/**
	 * Set the value.
	 * @param value the value.
	 */
	public void setValue(final Double value)
	{
		this.value = value;
	}

	/**
	 *
	 * @return the appliedValue.
	 */
	public Double getAppliedValue()
	{
		return appliedValue;
	}

	/**
	 * Set applied value.
	 * @param appliedValue the appliedValue.
	 */
	public void setAppliedValue(final Double appliedValue)
	{
		this.appliedValue = appliedValue;
	}
}
