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
package com.cgi.pacoshop.core.util.sap.subscription;


/**
 * Subscription Types for Subscription Products.
 *
 * @module build
 * @version 1.0v Feb 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public enum SubscriptionType
{
	/**
	 * Enum value of Subscription type NORMAL. "Normal"/simple subscriptions.
	 */
	NORMAL("normal"),

	/**
	 * Enum value of Subscription type SWR. Sample subscriptions with automatic renewal.
	 */
	SWR("swr"),

	/**
	 * Enum value of Subscription type SNOR. Sample subscriptions without automatic renewal.
	 */
	SNOR("snor");

	/** The code of this enum.*/
	private final String code;

	/**
	 * Creates a new enum value for this enum type.
	 *
	 * @param code the enum value code
	 */
	private SubscriptionType(final String code)
	{
		this.code = code.intern();
	}


	/**
	 * Gets the code of this enum value.
	 *
	 * @return code of value
	 */
	public String getCode()
	{
		return this.code;
	}
}
