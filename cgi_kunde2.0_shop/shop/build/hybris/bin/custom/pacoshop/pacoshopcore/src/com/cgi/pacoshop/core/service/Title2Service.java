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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.model.Title2Model;
import java.util.Collection;

/**
 * The service to process Title2 items.
 *
 * @module shop
 * @version 1.0v Jul 10, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Vekshin <alexey.vekshin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface Title2Service
{
	/**
	 * The method to fetch all items for Title2.
	 * @return Collection<Title2Model> all title2 items.
	 */
	Collection<Title2Model> getAllTitles2();

	/**
	 * Get title2 item by code.
	 * @param code title2 code.
	 * @return title2 item with code or null.
	 */
	Title2Model getTitle2ForCode(String code);

	/**
	 * The method to fetch next value of title2 index.
	 * @return index for the new title2 item.
	 */
	Integer getNewTitle2Index();
}
