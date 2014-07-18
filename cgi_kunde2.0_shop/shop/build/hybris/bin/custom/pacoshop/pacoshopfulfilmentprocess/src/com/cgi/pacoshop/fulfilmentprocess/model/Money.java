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

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * This is the money representation of the REST interface.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class Money implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = 7039342679114586251L;
    private Double amount;
    private String currency;


    /**
     * Constructor.
     * 
     * @param amount
     *            the amount.
     * @param currency
     *            the ISO 4217 currency code (EUR)
     */
    public Money(final Double amount, final String currency)
    {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Default constructor.
     */
    public Money()
    {
        //Default Constructor.
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }



    /**
     * @return the amount
     */
    public Double getAmount()
    {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(final Double amount)
    {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(final String currency)
    {
        this.currency = currency;
    }

}
