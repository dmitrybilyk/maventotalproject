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
package com.cgi.pacoshop.core.strategies;


import de.hybris.platform.core.model.user.AddressModel;


/**
 * Strategy for comparing addresses.
 * 
 * @module shop
 * @version 1.0v Apr 09, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public interface CompareAddressesStrategy
{

    /**
     * Compare Addresses.
     * 
     * @param firstAddress
     *            first address.
     * @param secondAddress
     *            second address.
     * @return true if addresses are equal.
     */
    boolean compareAddresses(final AddressModel firstAddress, final AddressModel secondAddress);

}
