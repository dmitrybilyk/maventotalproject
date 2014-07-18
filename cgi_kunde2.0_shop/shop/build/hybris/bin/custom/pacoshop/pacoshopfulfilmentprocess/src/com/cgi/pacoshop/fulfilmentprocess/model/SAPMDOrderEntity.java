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
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * This is the rest representation of the sap create periodic order.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPMDOrderEntity implements Serializable
{
    /**
     *
     */
    private static final long serialVersionUID = -3300232923589452464L;
    @JsonProperty(value = "orderRequestId")
    private String orderRequestId;
    @JsonProperty(value = "orderOrigin")
    private String orderOrigin;
    @JsonProperty(value = "orderId")
    private String orderId;
    @JsonProperty(value = "orderPositionList")
    private List<SAPOrderEntryEntity> orderEntries;
    @JsonProperty(value = "client")
    private SAPAddress client;
    @JsonProperty(value = "invoiceRecipient")
    private SAPAddress invoiceRecipient;
    @JsonProperty(value = "consignee")
    private SAPAddress consignee;
    @JsonProperty(value = "studentGraduationDate")
    private Date studentGraduationDate;
    @JsonProperty(value = "milesAndMoreNumber")
    private String milesAndMoresNumber;
    @JsonProperty(value = "bonusAccount")
    private SAPAccount bonusAccount;
    @JsonProperty(value = "validFrom")
    private Date validFrom;
    @JsonProperty(value = "pspId")
    private String pspId;
    @JsonProperty(value = "pspPaymentId")
    private String pspPaymentId;
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
     * @return the orderOrigin
     */
    public String getOrderOrigin()
    {
        return orderOrigin;
    }

    /**
     * @param orderOrigin
     *            the orderOrigin to set
     */
    public void setOrderOrigin(final String orderOrigin)
    {
        this.orderOrigin = orderOrigin;
    }

    /**
     * @return the orderId
     */
    public String getOrderId()
    {
        return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(final String orderId)
    {
        this.orderId = orderId;
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
     * @return the studentGraduationDate
     */
    public Date getStudentGraduationDate()
    {
        return studentGraduationDate;
    }

    /**
     * @param studentGraduationDate
     *            the studentGraduationDate to set
     */
    public void setStudentGraduationDate(final Date studentGraduationDate)
    {
        this.studentGraduationDate = studentGraduationDate;
    }

    /**
     * @return the milesAndMoresNumber
     */
    public String getMilesAndMoresNumber()
    {
        return milesAndMoresNumber;
    }

    /**
     * @param milesAndMoresNumber
     *            the milesAndMoresNumber to set
     */
    public void setMilesAndMoresNumber(final String milesAndMoresNumber)
    {
        this.milesAndMoresNumber = milesAndMoresNumber;
    }

    /**
     * @return the bonusAccount
     */
    public SAPAccount getBonusAccount()
    {
        return bonusAccount;
    }

    /**
     * @param bonusAccount
     *            the bonusAccount to set
     */
    public void setBonusAccount(final SAPAccount bonusAccount)
    {
        this.bonusAccount = bonusAccount;
    }

    /**
     * @return the validFrom
     */
    public Date getValidFrom()
    {
        return validFrom;
    }

    /**
     * @param validFrom
     *            the validFrom to set
     */
    public void setValidFrom(final Date validFrom)
    {
        this.validFrom = validFrom;
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
     * @return the pspPaymentId
     */
    public String getPspPaymentId()
    {
        return pspPaymentId;
    }

    /**
     * @param pspPaymentId
     *            the pspPaymentId to set
     */
    public void setPspPaymentId(final String pspPaymentId)
    {
        this.pspPaymentId = pspPaymentId;
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
