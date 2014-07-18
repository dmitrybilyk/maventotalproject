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
package com.cgi.pacoshop.facades.checkout.cart.impl;


import com.cgi.pacoshop.facades.checkout.cart.PacoCartFacade;
import de.hybris.platform.commercefacades.order.impl.DefaultCartFacade;
import de.hybris.platform.order.CartService;
import javax.annotation.Resource;

/**
 * Implementation for {@link PacoCartFacade}
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
public class DefaultPacoCartFacade extends DefaultCartFacade implements PacoCartFacade
{

	@Resource
	private CartService cartService;

	@Override
	public void removeSessionCart()
	{
		cartService.removeSessionCart();
	}
}
