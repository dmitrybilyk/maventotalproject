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
package com.cgi.pacoshop.fulfilmentprocess.model;


import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * Here goes 1 line. Here goes 2 line.
 * 
 * @module build
 * @version 1.0v Mar 04, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAccountResponse implements Serializable
{
    @JsonProperty(value = "accountId")
    private String accountId;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "status")
    private String status;

    /**
     * @return the accountId
     */
    public String getAccountId()
    {
        return accountId;
    }

    /**
     * @param accountId
     *            the accountId to set
     */
    public void setAccountId(final String accountId)
    {
        this.accountId = accountId;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status)
    {
        this.status = status;
    }


}
