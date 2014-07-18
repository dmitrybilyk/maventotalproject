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
package com.cgi.pacoshop.facades.checkout.dynamic;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.InvalidCartException;

import javax.servlet.http.HttpSession;

/**
 * Facade to complete the checkout flow with triggering the payment and order fulfillment
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @version 1.0v May 12, 2014
 * @module hybris - pacoshopfacades
 * @link http ://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 */
public interface CheckoutCompletionFacade
{
 	/**
	 * Check if checkout cart exist.
	 *
	 * @return true if cart exists
	 */
	boolean hasCheckoutCart();

	/**
     * Triggers the order submission business process actions.
     *
     * @param session Session to put created order id
     * @return Order data object
     * @throws InvalidCartException if something bad happens
     */
    OrderData submitOrder(HttpSession session) throws InvalidCartException;

    /**
     * Initiates payment.
     *
     * @param cart Cart model object
     * @param remoteAddress IP address of originating request
     * @param userAgent User agent HTTP header
     * @return Redirect URL or null if redirect should not happen
     */
    String submitPayment(CartModel cart, String remoteAddress, String userAgent);

    /**
     * Gets url for completing the checkout.
     *
     * @param orderData Order data to use to determine the redirect url (w/o any prefix)
     * @param modelRedirectUrl Redirect URL got from MVC model
     * @return redirectUrl (w/o prefix)
     */
    String getCompletionRedirectUrl(OrderData orderData, String modelRedirectUrl);

    /**.
     * Saves billing address into PaymentInfoModel
     * depending on presense of customerAddress or billingAddress
     * in a cart
     */
    void saveBillingAddress();
}
