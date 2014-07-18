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


import com.cgi.pacoshop.core.model.OrderDTO;

/**
 * RestService for calback to the external portal for full order information.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface CallbackRestService
{
	/**
	 * Method get OrderDTO entity  from external portals, based on url and cartId.
	 * @param url url for external portal.
	 * @param cartId unique cart identifier for external portals.
	 * @return OrderDTO - full order information.
	 */
	OrderDTO getOrderDTO(String url, String cartId);
}
