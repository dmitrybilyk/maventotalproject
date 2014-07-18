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
package com.cgi.pacoshop.fulfilmentprocess.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * This is the rest representation of the SAP create single order call.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPSDOrderEntity implements Serializable
{
    /**
	 *
	 */
    private static final long serialVersionUID = 2631222423571536776L;

    @JsonProperty(value = "orderRequestId")
    private String orderRequestId;
    @JsonProperty(value = "orderPositionList")
    private List<SAPOrderEntryEntity> orderEntries;
    @JsonProperty(value = "client")
    private SAPAddress client;
    @JsonProperty(value = "invoiceRecipient")
    private SAPAddress invoiceRecipient;
    @JsonProperty(value = "consignee")
    private SAPAddress consignee;
    @JsonProperty(value = "pspId")
    private String pspId;
    @JsonProperty(value = "pspTransactionId")
    private String pspTransactionId;
    @JsonProperty(value = "pspMethod")
    private String pspMethod;
    @JsonProperty(value = "pspAccount")
    private SAPAccount pspAccount;


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
     * @return the orderRequestId
     */
    public String getOrderRequestId()
    {
        return orderRequestId;
    }

    /**
     * @param orderRequestId
     *            the orderRequestId to set
     */
    public void setOrderRequestId(final String orderRequestId)
    {
        this.orderRequestId = orderRequestId;
    }

    /**
     * @return the orderEntries
     */
    public List<SAPOrderEntryEntity> getOrderEntries()
    {
        return orderEntries;
    }

    /**
     * @param orderEntries
     *            the orderEntries to set
     */
    public void setOrderEntries(final List<SAPOrderEntryEntity> orderEntries)
    {
        this.orderEntries = orderEntries;
    }

    /**
     * @return the client
     */
    public SAPAddress getClient()
    {
        return client;
    }

    /**
     * @param client
     *            the client to set
     */
    public void setClient(final SAPAddress client)
    {
        this.client = client;
    }

    /**
     * @return the invoiceRecipient
     */
    public SAPAddress getInvoiceRecipient()
    {
        return invoiceRecipient;
    }

    /**
     * @param invoiceRecipient
     *            the invoiceRecipient to set
     */
    public void setInvoiceRecipient(final SAPAddress invoiceRecipient)
    {
        this.invoiceRecipient = invoiceRecipient;
    }

    /**
     * @return the consignee
     */
    public SAPAddress getConsignee()
    {
        return consignee;
    }

    /**
     * @param consignee
     *            the consignee to set
     */
    public void setConsignee(final SAPAddress consignee)
    {
        this.consignee = consignee;
    }

    /**
     * @return the pspId
     */
    public String getPspId()
    {
        return pspId;
    }

    /**
     * @param pspId
     *            the pspId to set
     */
    public void setPspId(final String pspId)
    {
        this.pspId = pspId;
    }

    /**
     * @return the pspTransactionId
     */
    public String getPspTransactionId()
    {
        return pspTransactionId;
    }

    /**
     * @param pspTransactionId
     *            the pspTransactionId to set
     */
    public void setPspTransactionId(final String pspTransactionId)
    {
        this.pspTransactionId = pspTransactionId;
    }

    /**
     * @return the pspMethod
     */
    public String getPspMethod()
    {
        return pspMethod;
    }

    /**
     * @param pspMethod
     *            the pspMethod to set
     */
    public void setPspMethod(final String pspMethod)
    {
        this.pspMethod = pspMethod;
    }

    /**
     * @return the pspAccount
     */
    public SAPAccount getPspAccount()
    {
        return pspAccount;
    }

    /**
     * @param pspAccount
     *            the pspAccount to set
     */
    public void setPspAccount(final SAPAccount pspAccount)
    {
        this.pspAccount = pspAccount;
    }


}
