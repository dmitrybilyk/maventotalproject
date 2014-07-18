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
package com.cgi.pacoshop.core.daos;


import com.cgi.pacoshop.core.model.Title2Model;
import java.util.List;

/**
 * Interface for Title2 entity DAO layer.
 * Here goes 2 line.
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
public interface Title2Dao
{

	/**
	 * Methods searching Title2 entity in Hybris database.
	 *
	 * @param title2Code String code of Title2 entity as a search condition
	 * @return Title2Model entity
	 */
	Title2Model findTitle2(final String title2Code);

	/**
	 * Gets all Title2 items.
	 *
	 * @return the all Title2 items
	 */
	List<Title2Model> findAll();

}
