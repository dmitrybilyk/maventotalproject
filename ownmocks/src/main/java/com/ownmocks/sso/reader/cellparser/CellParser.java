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
package com.ownmocks.sso.reader.cellparser;


import com.ownmocks.sso.model.SSOEntity;
import java.text.ParseException;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Interface for cellParsers.
 *
 * @module kunde_new
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface CellParser
{
	/**
	 *
	 * @param ssoEntity ssoEntity
	 * @param stringCellValue stringCellValue
	 * @param currentCell currentCell
	 * @throws NoSuchFieldException NoSuchFieldException
	 * @throws IllegalAccessException IllegalAccessException
	 * @throws java.text.ParseException ParseException
	 */
	void parseCell(final SSOEntity ssoEntity, final String stringCellValue, final Cell currentCell)
			  throws NoSuchFieldException, IllegalAccessException, ParseException;
}
