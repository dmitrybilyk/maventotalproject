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
package com.cgi.pacoshop.storefront.filters.uagmock;

/**
 * UagMockFilter - mock for the user access gateway
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Apr 23, 2014
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public final class UagWsTokenGenerator
{
	private UagWsTokenGenerator()
	{
		//not called
	}

	/**
	 * Generate token for current user.
	 *
	 * @param userId User identity.
	 * @return Special token.
	 */
	public static String generate(final String userId)
	{
		return Integer.toString(userId.hashCode());
	}
}
