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
package com.cgi.pacoshop.mock.servicebus.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * Entity for single productList
 * 
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * 
 * 
 */
public class SingleProduct
{
    private String offerOrigin;
    private String offerId;
    private String offerDescription;
    private String offerPicture;
    private String productOrigin;
    private String productId;
    private String productOwner;
    private String productClass;
    private String productGroup;
    private boolean prepayOnly;
    private boolean studentOffer;
    private boolean clientOffer;
    private boolean invoiceRecipientOffer;
    private boolean consigneeOffer;
    private Integer minOrder;
    private Integer maxOrder;
    private boolean otherInvoiceRecipientAllowed;
    private boolean otherConsigneeAllowed;
    private Integer vat;
    private Price price;
    private Date validFrom;
    private Date validTo;

    /**
     * Default constructor.
     */
    public SingleProduct()
    {
        super();
    }

    /**
     * Setter valid from.
     * 
     * @param validFromParam
     *            date valid from
     */
    public void setValidFrom(final Date validFromParam)
    {
        this.validFrom = validFromParam;
    }

    /**
     * Setter valid until.
     * 
     * @param validTo
     *            valid until param
     */
    public void setValidTo(final Date validTo)
    {
        this.validTo = validTo;
    }

    /**
     * Setter offerOrigin.
     * 
     * @param offerOriginParam
     *            offerOriginParam
     */
    public void setOfferOrigin(final String offerOriginParam)
    {
        this.offerOrigin = offerOriginParam;
    }

    /**
     * Setter offerId.
     * 
     * @param offerIdParam
     *            offerIdParam
     */
    public void setOfferId(final String offerIdParam)
    {
        this.offerId = offerIdParam;
    }

    /**
     * Setter offerDescription.
     * 
     * @param offerDescriptionParam
     *            offerDescriptionParam
     */
    public void setOfferDescription(final String offerDescriptionParam)
    {
        this.offerDescription = offerDescriptionParam;
    }

    /**
     * Setter offerPicture.
     * 
     * @param offerPictureParam
     *            offerPictureParam
     */
    public void setOfferPicture(final String offerPictureParam)
    {
        this.offerPicture = offerPictureParam;
    }

    /**
     * Setter productOrigin.
     * 
     * @param productOriginParam
     *            productOriginParam
     */
    public void setProductOrigin(final String productOriginParam)
    {
        this.productOrigin = productOriginParam;
    }

    /**
     * Setter productId.
     * 
     * @param productIdParam
     *            productIdParam
     */
    public void setProductId(final String productIdParam)
    {
        this.productId = productIdParam;
    }

    /**
     * Setter productOwner.
     * 
     * @param productOwnerParam
     *            productOwnerParam
     */
    public void setProductOwner(final String productOwnerParam)
    {
        this.productOwner = productOwnerParam;
    }

    /**
     * Setter productClass.
     * 
     * @param productClassParam
     *            productClassParam
     */
    public void setProductClass(final String productClassParam)
    {
        this.productClass = productClassParam;
    }

    /**
     * Setter productGroup.
     * 
     * @param productGroupParam
     *            productGroupParam
     */
    public void setProductGroup(final String productGroupParam)
    {
        if (StringUtils.isEmpty(productGroupParam))
        {
            // For an empty string, set the value to null
            this.productGroup = null;
        }
        else
        {
            this.productGroup = productGroupParam;
        }
    }

    /**
     * Setter prepayOnly.
     * 
     * @param prepayOnlyParam
     *            prepayOnlyParam
     */
    public void setPrepayOnly(final boolean prepayOnlyParam)
    {
        this.prepayOnly = prepayOnlyParam;
    }

    /**
     * Setter studentOffer.
     * 
     * @param studentOfferParam
     *            studentOfferParam
     */
    public void setStudentOffer(final boolean studentOfferParam)
    {
        this.studentOffer = studentOfferParam;
    }

    /**
     * Setter clientOffer.
     * 
     * @param clientOfferParam
     *            clientOfferParam
     */
    public void setClientOffer(final boolean clientOfferParam)
    {
        this.clientOffer = clientOfferParam;
    }

    /**
     * Setter invoiceRecipientOffer.
     * 
     * @param invoiceRecipientOfferParam
     *            invoiceRecipientOfferParam
     */
    public void setInvoiceRecipientOffer(final boolean invoiceRecipientOfferParam)
    {
        this.invoiceRecipientOffer = invoiceRecipientOfferParam;
    }

    /**
     * Setter consigneeOffer.
     * 
     * @param consigneeOfferParam
     *            consigneeOfferParam
     */
    public void setConsigneeOffer(final boolean consigneeOfferParam)
    {
        this.consigneeOffer = consigneeOfferParam;
    }

    /**
     * Setter minOrder.
     * 
     * @param minOrderParam
     *            minOrderParam
     */
    public void setMinOrder(final Integer minOrderParam)
    {
        this.minOrder = minOrderParam;
    }

    /**
     * Setter maxOrder.
     * 
     * @param maxOrderParam
     *            maxOrderParam
     */
    public void setMaxOrder(final Integer maxOrderParam)
    {
        this.maxOrder = maxOrderParam;
    }

    /**
     * Setter otherInvoiceRecipientAllowed.
     * 
     * @param otherInvoiceRecipientAllowedParam
     *            otherInvoiceRecipientAllowedParam
     */
    public void setOtherInvoiceRecipientAllowed(final boolean otherInvoiceRecipientAllowedParam)
    {
        this.otherInvoiceRecipientAllowed = otherInvoiceRecipientAllowedParam;
    }

    /**
     * Setter otherConsigneeAllowed.
     * 
     * @param otherConsigneeAllowedParam
     *            otherConsigneeAllowedParam
     */
    public void setOtherConsigneeAllowed(final boolean otherConsigneeAllowedParam)
    {
        this.otherConsigneeAllowed = otherConsigneeAllowedParam;
    }

    /**
     * Setter vat.
     * 
     * @param vatRateParam
     *            vatRateParam
     */
    public void setVat(final Integer vatRateParam)
    {
        this.vat = vatRateParam;
    }

    /**
     * Getter price.
     * 
     * @return price
     */
    public Price getPrice()
    {
        return price;
    }

    /**
     * Setter price.
     * 
     * @param currency
     *            currency
     * @param amount
     *            amount
     */
    public void setPrice(final String currency, final Double amount)
    {
        this.price = new Price(currency, amount);
    }


}
