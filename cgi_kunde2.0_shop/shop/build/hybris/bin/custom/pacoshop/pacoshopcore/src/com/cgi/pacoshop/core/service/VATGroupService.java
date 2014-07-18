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
package com.cgi.pacoshop.core.service;

import com.cgi.pacoshop.core.exceptions.VATRateNotFoundException;
import com.cgi.pacoshop.core.model.VATGroupModel;

import java.util.Map;

/**
 * VATGroup service layer
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
public interface VATGroupService
{
	/**
	 * Method searching for VATrate in Hybris (all possible VATrate already imported to Hybris database). If VAT rate
	 * doesn't exist
	 *
	 * @param vatRate - vat rate.
	 * @return VAT group model.
	 * @throws VATRateNotFoundException in case if VAT Rate was not found.
	 */
	VATGroupModel findVatGroup(int vatRate) throws VATRateNotFoundException;

	/**
	 * Find and map all vAT groups.
	 *
	 * @return the map
	 */
	Map<Double, VATGroupModel> findAndMapAllVATGroups();
}
