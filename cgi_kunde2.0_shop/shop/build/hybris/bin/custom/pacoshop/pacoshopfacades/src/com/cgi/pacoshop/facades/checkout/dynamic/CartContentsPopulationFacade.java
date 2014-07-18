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

import com.cgi.pacoshop.core.checkout.dynamic.CartContentsFragmentDTO;

/**
 * Facade to help to supply the product information data to login, summary and thank you pages
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @version 1.0v May 13, 2014
 * @module hybris - pacoshopfacades
 * @link http ://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 */
public interface CartContentsPopulationFacade
{
    /**
     * * Fill the dto to display products in login, summary and thankyou pages.
     *
     * @param cartContentsFragmentDTO the cart contents fragment dTO
     * @param orderId The id of the order
     * @return the CartContentsFragmentDTO.
     */
    CartContentsFragmentDTO populateCartContentsFragment(
            CartContentsFragmentDTO cartContentsFragmentDTO, String orderId);
}
