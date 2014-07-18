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

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Represents one {@link AbstractOrderEntryModel} sent to CA.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class CAOrderInterfaceEntry implements Serializable
{

    private static final long serialVersionUID = 1880518470204366559L;
    private String clientCustomerId;
    private String consigneeCustomerId;
    private String invoiceRecipientCustomerId;
    private String productId;
    private String productOrigin;
    private String contentPlatformId;
    private String contentId;
    private String contentTitle;
    private String contentUrl;
    private String orderRequestId;
    private String orderRequestPositionId;
    private Date orderDate;
    private Long orderSize;
    private Date validFrom;
    private Date validTo;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the clientCustomerId
     */
    public String getClientCustomerId()
    {
        return clientCustomerId;
    }

    /**
     * @param clientCustomerId
     *            the clientCustomerId to set
     */
    public void setClientCustomerId(final String clientCustomerId)
    {
        this.clientCustomerId = clientCustomerId;
    }

    /**
     * @return the consigneeCustomerId
     */
    public String getConsigneeCustomerId()
    {
        return consigneeCustomerId;
    }

    /**
     * @param consigneeCustomerId
     *            the consigneeCustomerId to set
     */
    public void setConsigneeCustomerId(final String consigneeCustomerId)
    {
        this.consigneeCustomerId = consigneeCustomerId;
    }

    /**
     * @return the invoiceRecipientCustomerId
     */
    public String getInvoiceRecipientCustomerId()
    {
        return invoiceRecipientCustomerId;
    }

    /**
     * @param invoiceRecipientCustomerId
     *            the invoiceRecipientCustomerId to set
     */
    public void setInvoiceRecipientCustomerId(final String invoiceRecipientCustomerId)
    {
        this.invoiceRecipientCustomerId = invoiceRecipientCustomerId;
    }

    /**
     * @return the productId
     */
    public String getProductId()
    {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(final String productId)
    {
        this.productId = productId;
    }

    /**
     * @return the productOrigin
     */
    public String getProductOrigin()
    {
        return productOrigin;
    }

    /**
     * @param productOrigin
     *            the productOrigin to set
     */
    public void setProductOrigin(final String productOrigin)
    {
        this.productOrigin = productOrigin;
    }

    /**
     * @return the contentPlatformId
     */
    public String getContentPlatformId()
    {
        return contentPlatformId;
    }

    /**
     * @param contentPlatformId
     *            the contentPlatformId to set
     */
    public void setContentPlatformId(final String contentPlatformId)
    {
        this.contentPlatformId = contentPlatformId;
    }

    /**
     * @return the contentId
     */
    public String getContentId()
    {
        return contentId;
    }

    /**
     * @param contentId
     *            the contentId to set
     */
    public void setContentId(final String contentId)
    {
        this.contentId = contentId;
    }

    /**
     * @return the contentTitle
     */
    public String getContentTitle()
    {
        return contentTitle;
    }

    /**
     * @param contentTitle
     *            the contentTitle to set
     */
    public void setContentTitle(final String contentTitle)
    {
        this.contentTitle = contentTitle;
    }

    /**
     * @return the contentUrl
     */
    public String getContentUrl()
    {
        return contentUrl;
    }

    /**
     * @param contentUrl
     *            the contentUrl to set
     */
    public void setContentUrl(final String contentUrl)
    {
        this.contentUrl = contentUrl;
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
     * @return the orderRequestPositionId
     */
    public String getOrderRequestPositionId()
    {
        return orderRequestPositionId;
    }

    /**
     * @param orderRequestPositionId
     *            the orderRequestPositionId to set
     */
    public void setOrderRequestPositionId(final String orderRequestPositionId)
    {
        this.orderRequestPositionId = orderRequestPositionId;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate()
    {
        if (orderDate == null)
        {
            return null;
        }
        return new Date(orderDate.getTime());
    }

    /**
     * @param orderDate
     *            the orderDate to set
     */
    public void setOrderDate(final Date orderDate)
    {
        if (orderDate != null)
        {
            this.orderDate = new Date(orderDate.getTime());
        }
        else
        {
            this.orderDate = null;
        }
    }

    /**
     * @return the orderSize
     */
    public Long getOrderSize()
    {
        return orderSize;
    }

    /**
     * @param orderSize
     *            the orderSize to set
     */
    public void setOrderSize(final Long orderSize)
    {
        this.orderSize = orderSize;
    }

    /**
     * @return the validFrom
     */
    public Date getValidFrom()
    {
        if (validFrom != null)
        {
            return new Date(validFrom.getTime());
        }

        return null;
    }

    /**
     * @param validFrom
     *            the validFrom to set
     */
    public void setValidFrom(final Date validFrom)
    {
        if (validFrom != null)
        {
            this.validFrom = new Date(validFrom.getTime());
        }
        else
        {
            this.validFrom = null;
        }
    }

    /**
     * @return the validTo
     */
    public Date getValidTo()
    {
        if (validTo != null)
        {
            return new Date(validTo.getTime());
        }

        return null;
    }

    /**
     * @param validTo
     *            the validTo to set
     */
    public void setValidTo(final Date validTo)
    {
        if (validTo != null)
        {
            this.validTo = new Date(validTo.getTime());
        }
        else
        {
            this.validTo = null;
        }
    }
}
