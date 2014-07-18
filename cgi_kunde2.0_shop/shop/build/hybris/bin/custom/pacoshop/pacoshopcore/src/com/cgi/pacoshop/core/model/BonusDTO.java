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
package com.cgi.pacoshop.core.model;


/**
 * BonusDTO
 * 
 * @module pacoshopcore
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 * 
 * 
 */
public class BonusDTO
{
    private String parentProductName;
    private String bonusId;
    private String bonusProduct;
    private Price bonusProductExtraPayment;
    private Price bonusAmount;
    private Integer bonusMiles;
    private String bonusDescription;
    private String bonusPicture;

    /**
     * @return the parentProductName
     */
    public String getParentProductName()
    {
        return parentProductName;
    }

    /**
     * @param parentProductName
     *            the parentProductName to set
     */
    public void setParentProductName(final String parentProductName)
    {
        this.parentProductName = parentProductName;
    }

    /**
     * @return the bonusId
     */
    public String getBonusId()
    {
        return bonusId;
    }

    /**
     * @param bonusId
     *            the bonusId to set
     */
    public void setBonusId(final String bonusId)
    {
        this.bonusId = bonusId;
    }

    /**
     * Gets bonus product.
     * 
     * @return the bonus product
     */
    public String getBonusProduct()
    {
        return bonusProduct;
    }

    /**
     * Sets bonus product.
     * 
     * @param bonusProduct
     *            the bonus product
     */
    public void setBonusProduct(final String bonusProduct)
    {
        this.bonusProduct = bonusProduct;
    }

    /**
     * Gets bonus product extra payment.
     * 
     * @return the bonus product extra payment
     */
    public Price getBonusProductExtraPayment()
    {
        return bonusProductExtraPayment;
    }

    /**
     * Sets bonus product extra payment.
     * 
     * @param bonusProductExtraPayment
     *            the bonus product extra payment
     */
    public void setBonusProductExtraPayment(final Price bonusProductExtraPayment)
    {
        this.bonusProductExtraPayment = bonusProductExtraPayment;
    }

    /**
     * Gets bonus amount.
     * 
     * @return the bonus amount
     */
    public Price getBonusAmount()
    {
        return bonusAmount;
    }

    /**
     * Sets bonus amount.
     * 
     * @param bonusAmount
     *            the bonus amount
     */
    public void setBonusAmount(final Price bonusAmount)
    {
        this.bonusAmount = bonusAmount;
    }

    /**
     * Gets bonus miles.
     * 
     * @return the bonus miles
     */
    public Integer getBonusMiles()
    {
        return bonusMiles;
    }

    /**
     * Sets bonus miles.
     * 
     * @param bonusMiles
     *            the bonus miles
     */
    public void setBonusMiles(final Integer bonusMiles)
    {
        this.bonusMiles = bonusMiles;
    }

    /**
     * Gets bonus description.
     * 
     * @return the bonus description
     */
    public String getBonusDescription()
    {
        return bonusDescription;
    }

    /**
     * Sets bonus description.
     * 
     * @param bonusDescription
     *            the bonus description
     */
    public void setBonusDescription(final String bonusDescription)
    {
        this.bonusDescription = bonusDescription;
    }

    /**
     * Gets bonus picture.
     * 
     * @return the bonus picture
     */
    public String getBonusPicture()
    {
        return bonusPicture;
    }

    /**
     * Sets bonus picture.
     * 
     * @param bonusPicture
     *            the bonus picture
     */
    public void setBonusPicture(final String bonusPicture)
    {
        this.bonusPicture = bonusPicture;
    }
}
