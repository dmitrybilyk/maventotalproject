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

import org.codehaus.jackson.annotate.JsonProperty;

import wiremock.org.apache.commons.lang.builder.ToStringBuilder;


/**
 * This is the account representation in the REST interface of the platform.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPAccount implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = -7939223536042927150L;
    @JsonProperty(value = "accountHolder")
    private String accountHolder;
    @JsonProperty(value = "accountNumber")
    private String accountNumber;
    @JsonProperty(value = "bankCode")
    private String bankCode;
    @JsonProperty(value = "bankName")
    private String bankName;
    @JsonProperty(value = "iban")
    private String iban;
    @JsonProperty(value = "swiftBic")
    private String swiftBic;


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
     * @return the accountHolder
     */
    public String getAccountHolder()
    {
        return accountHolder;
    }

    /**
     * @param accountHolder
     *            the accountHolder to set
     */
    public void setAccountHolder(final String accountHolder)
    {
        this.accountHolder = accountHolder;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * @param accountNumber
     *            the accountNumber to set
     */
    public void setAccountNumber(final String accountNumber)
    {
        this.accountNumber = accountNumber;
    }


    /**
     * @return the bankName
     */
    public String getBankName()
    {
        return bankName;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public void setBankName(final String bankName)
    {
        this.bankName = bankName;
    }

    /**
     * @return the bankCode
     */
    public String getBankCode()
    {
        return bankCode;
    }

    /**
     * @param bankCode
     *            the bankCode to set
     */
    public void setBankCode(final String bankCode)
    {
        this.bankCode = bankCode;
    }

    /**
     * @return the iban
     */
    public String getIban()
    {
        return iban;
    }

    /**
     * @param iban
     *            the iban to set
     */
    public void setIban(final String iban)
    {
        this.iban = iban;
    }

    /**
     * @return the swiftBic
     */
    public String getSwiftBic()
    {
        return swiftBic;
    }

    /**
     * @param swiftBic
     *            the swiftBic to set
     */
    public void setSwiftBic(final String swiftBic)
    {
        this.swiftBic = swiftBic;
    }
}
