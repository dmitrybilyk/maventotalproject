/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
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

import com.cgi.pacoshop.core.exceptions.VATRateNotFoundException;
import com.cgi.pacoshop.core.model.VATGroupModel;

import java.util.List;

/**
 * Interface for VATGroup entity DAO layer Here goes 2 line.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v dec 24, 2013
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public interface VATGroupDao
{
	/**
	 * Methods searching VATGroup entity in Hybris database (all possible Vatrate already added to Hybis).
	 *
	 * @param vatRate int Vat rate value as a search condition
	 * @return VATGroupModel entity
	 * @throws VATRateNotFoundException if VatRate doesn't exist in Hybris or more than one VatRate with same value
	 */
	VATGroupModel findVATGroup(int vatRate) throws VATRateNotFoundException;

	/**
	 * Gets all vAT groups.
	 *
	 * @return the all vAT groups
	 */
	List<VATGroupModel> getAllVATGroups();
}
