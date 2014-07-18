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
package com.cgi.pacoshop.core.service.sso;


import de.hybris.platform.core.model.user.UserModel;

import java.util.Map;


/**
 * SSO customer service - uses SSOClient to get customer and updates the existing customer.
 * 
 * @module pacoshopcore
 * @version 1.0v Feb 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public interface SSOCustomerService
{
    /**
     * @param userModel
     *            - customer to update
     * @param userParams
     *            - userParams of the user - accountId(=userId), platformId and wsToken
     */
    void updateCustomer(UserModel userModel, Map<String, String> userParams);


    /**
     * Determines which user group a Customer must be assigned to in Hybris depending on the user type received from
     * SSO.
     * 
     * @param ssoUserType
     *            The user type, as received from the SSO/UAG
     * @return The uid of the Hybris user group the customer must be assigned to
     */
    String getUserGroupForSSOUserType(String ssoUserType);


}
