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
package com.cgi.pacoshop.facades.user;


import com.cgi.pacoshop.facades.user.data.Title2Data;
import de.hybris.platform.commercefacades.user.UserFacade;
import java.util.List;

/**
 * Facade for working with user. Extends default UserFacade
 *
 * @module shop
 * @version 1.0v Jul 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Vekshin <alexey.vekshin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface PacoUserFacade extends UserFacade
{
	/**
	 * Provide all localized titles2.
	 *
	 * @return List of {@link com.cgi.pacoshop.facades.user.data.Title2Data} objects
	 */
	List<Title2Data> getTitles2();

	/**
	 * The method to remove info about submitted Anonymous.
	 */
	void removeAnonymousSubmittedAttribute();
}
