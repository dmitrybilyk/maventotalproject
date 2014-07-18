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
package com.cgi.pacoshop.core.checkout.dynamic.impl;


/**
 * FormDTO for summary checkout step.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 21, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SummaryStepFormDTO extends ShipmentInfoFormDTO
{
	private boolean agb;

	/**
	 * OptIn checkbox has 3 states: true - checked, false - unchecked, null - hidden.
	 */
	private Boolean optIn;

	/**
	 *	Check AGB.
	 * @return agb actual value.
	 */
	public boolean isAgb()
	{
		return agb;
	}

	/**
	 * agb setter.
	 * @param agbParam new agb;
	 */
	public void setAgb(final boolean agbParam)
	{
		this.agb = agbParam;
	}

	/**
	 * Check Opt-in flag.
	 * @return Opt-in actual value.
	 */
	public Boolean getOptIn()
	{
		return optIn;
	}

	/**
	 * Specifying the Opt-in checkbox state.
	 * @param optInParam is new state of Opt-in checkbox. true - checked, false - unchecked, null - hidden.
	 */
	public void setOptIn(final Boolean optInParam)
	{
		this.optIn = optInParam;
	}
}
