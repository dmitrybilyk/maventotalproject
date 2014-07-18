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
package com.cgi.pacoshop.facades.checkout.cart;


import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;

/**
 * Facade for working with cart. Extends default cart fasade
 *
 * @module shop
 * @version 1.0v Jul 01, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Erofeev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface PacoCartFacade extends CartFacade
{

	/**
	 * If existing the current session cart is being detached from this session and removed. Afterwards it's no longer
	 * available.
	 *
	 * @see #getSessionCart()
	 */
	void removeSessionCart();

}
