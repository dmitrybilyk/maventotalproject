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
package com.cgi.pacoshop.facades.customergroups;


/**
 * Here goes 1 line.
 *
 * @module build
 * @version 1.0v Feb 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface PacoCustomerGroupFacade
{
	/**
	 *
	 * @param userType - should belong to registeredcustomergroup or visitorcustomergroup.
	 * @param userUid - user uid
	 */
	void addUserToCustomerSubgroup(String userType, String userUid);

	/**
	 * Check if user belongs to user group which is corresponds to specified userType.
	 *
	 * @param userType - a user type, as received from the SSO/UAG
	 * @param userUid - user uid
	 * @return true if the user group which is corresponds to specified userType is contained in user's group list,
	 * false otherwise.
	 */
	boolean isUserBelongsToUserType(String userType, String userUid);
}
