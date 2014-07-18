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
package com.cgi.pacoshop.core.service.user;


import com.cgi.pacoshop.core.model.Title2Model;
import de.hybris.platform.core.model.user.UserModel;
import java.util.Collection;
import java.util.Map;


/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @version 1.0v Jun 02, 2014
 * @see https://wiki.hybris.com/
 */
public interface PacoUserService
{
	/**
	 * Creates or updates UserModel
	 *
	 * @param userIdType Type of user id
	 * @param userParameters Parameters
	 * @return Created or updated UserModel object
	 */
	UserModel createAndUpdateCustomer(final String userIdType, final Map<String, String> userParameters);

	/**
	 * The method to remove information about submitted anonymous from JaloSession
	 */
	void removeAnonymousSubmittedAttribute();
}
