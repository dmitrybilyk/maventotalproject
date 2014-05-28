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
package com.learn.formgroups.dtos;


import com.learn.formgroups.adapter.CDATAAdapter;
import com.learn.formgroups.enums.OfferOrigin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;


/**
 * ProductDto entity for alternative deeplink interface.
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@XmlRootElement(name = "product")
public class ProductDTO
{
    private List<ProductDTO> bonuses;
    private String productId;
    private String externalOfferId;
    private String offerOriginCode;
	 private boolean useId;

    private String name;
    private String description;
    private String summary;
    private String url;
    private Integer quantity;
    private Double price;
    private Double additionalPayment;
    private String subscriptionDescription;
    private Double subscriptionPrice;
    private String currency;
    private String deliveryPeriod;
    private Double pricePerUnit;
    private String unit;
    private String billingFrequency;
    private String billingFrequencyUnit;
    private String actualBilledUnit;
    private String actualBilledPrice;
    private String actualBilledBillingFrequency;
    private boolean subscriptionInfoDisplayed;
    private boolean termsOfServiceRenewal;
    private String externalProductId;
    private OfferOrigin offerOrigin;

    /**
     * Gets actual billed billing frequency.
     * 
     * @return the actual billed billing frequency
     */
    public String getActualBilledBillingFrequency()
    {
        return actualBilledBillingFrequency;
    }

    /**
     * Sets actual billed billing frequency.
     * 
     * @param actualBilledBillingFrequency
     *            the actual billed billing frequency
     */
    public void setActualBilledBillingFrequency(final String actualBilledBillingFrequency)
    {
        this.actualBilledBillingFrequency = actualBilledBillingFrequency;
    }

    /**
     * Gets unit frequency.
     * 
     * @return the unit frequency
     */
    public String getBillingFrequency()
    {
        return billingFrequency;
    }

    /**
     * Sets unit frequency.
     * 
     * @param billingFrequency
     *            the unit frequency
     */
    public void setBillingFrequency(final String billingFrequency)
    {
        this.billingFrequency = billingFrequency;
    }


    /**
     * Gets billing frequency unit.
     * 
     * @return the billing frequency unit
     */
    public String getBillingFrequencyUnit()
    {
        return billingFrequencyUnit;
    }

    /**
     * Sets billing frequency unit.
     * 
     * @param billingFrequencyUnit
     *            the billing frequency unit
     */
    public void setBillingFrequencyUnit(final String billingFrequencyUnit)
    {
        this.billingFrequencyUnit = billingFrequencyUnit;
    }


    /**
     * Is terms of service renewal.
     * 
     * @return the boolean
     */
    public boolean isTermsOfServiceRenewal()
    {
        return termsOfServiceRenewal;
    }

    /**
     * Sets terms of service renewal.
     * 
     * @param termsOfServiceRenewal
     *            the terms of service renewal
     */
    public void setTermsOfServiceRenewal(final boolean termsOfServiceRenewal)
    {
        this.termsOfServiceRenewal = termsOfServiceRenewal;
    }

    /**
     * Is subscription info displayed.
     * 
     * @return the boolean
     */
    public boolean isSubscriptionInfoDisplayed()
    {
        return subscriptionInfoDisplayed;
    }

    /**
     * Sets subscription info displayed.
     * 
     * @param subscriptionInfoDisplayed
     *            the subscription info displayed
     */
    public void setSubscriptionInfoDisplayed(final boolean subscriptionInfoDisplayed)
    {
        this.subscriptionInfoDisplayed = subscriptionInfoDisplayed;
    }

    /**
     * Sets currency.
     * 
     * @param currency
     *            the currency
     */
    public void setCurrency(final String currency)
    {
        this.currency = currency;
    }

    /**
     * Gets delivery period.
     * 
     * @return the delivery period
     */
    public String getDeliveryPeriod()
    {
        return deliveryPeriod;
    }

    /**
     * Sets delivery period.
     * 
     * @param deliveryPeriod
     *            the delivery period
     */
    public void setDeliveryPeriod(final String deliveryPeriod)
    {
        this.deliveryPeriod = deliveryPeriod;
    }

    /**
     * Gets price per unit.
     * 
     * @return the price per unit
     */
    public Double getPricePerUnit()
    {
        return pricePerUnit;
    }

    /**
     * Sets price per unit.
     * 
     * @param pricePerUnit
     *            the price per unit
     */
    public void setPricePerUnit(final Double pricePerUnit)
    {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Gets unit.
     * 
     * @return the unit
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     * Sets unit.
     * 
     * @param unit
     *            the unit
     */
    public void setUnit(final String unit)
    {
        this.unit = unit;
    }

    /**
     * Gets actual billed unit.
     * 
     * @return the actual billed unit
     */
    public String getActualBilledUnit()
    {
        return actualBilledUnit;
    }

    /**
     * Sets actual billed unit.
     * 
     * @param actualBilledUnit
     *            the actual billed unit
     */
    public void setActualBilledUnit(final String actualBilledUnit)
    {
        this.actualBilledUnit = actualBilledUnit;
    }

    /**
     * Gets actual billed price.
     * 
     * @return the actual billed price
     */
    public String getActualBilledPrice()
    {
        return actualBilledPrice;
    }

    /**
     * Sets actual billed price.
     * 
     * @param actualBilledPrice
     *            the actual billed price
     */
    public void setActualBilledPrice(final String actualBilledPrice)
    {
        this.actualBilledPrice = actualBilledPrice;
    }


    /**
     * Gets currency.
     * 
     * @return the currency
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     * Gets subscription price.
     * 
     * @return the subscription price
     */
    public Double getSubscriptionPrice()
    {
        return subscriptionPrice;
    }

    /**
     * Sets subscription price.
     * 
     * @param subscriptionPrice
     *            the subscription price
     */
    public void setSubscriptionPrice(final Double subscriptionPrice)
    {
        this.subscriptionPrice = subscriptionPrice;
    }

    /**
     * Gets additional payment.
     * 
     * @return the additional payment
     */
    public Double getAdditionalPayment()
    {
        return additionalPayment;
    }

    /**
     * Sets additional payment.
     * 
     * @param additionalPayment
     *            the additional payment
     */
    public void setAdditionalPayment(final Double additionalPayment)
    {
        this.additionalPayment = additionalPayment;
    }

    /**
     * Gets addtional text.
     * 
     * @return the addtional text
     */
    public String getSubscriptionDescription()
    {
        return subscriptionDescription;
    }

    /**
     * Sets addtional text.
     * 
     * @param subscriptionDescription
     *            the addtional text
     */
    public void setSubscriptionDescription(final String subscriptionDescription)
    {
        this.subscriptionDescription = subscriptionDescription;
    }

    /**
     * Gets bonuses.
     * 
     * @return the bonuses
     */
    public List<ProductDTO> getBonuses()
    {
        return bonuses;
    }

    /**
     * Sets bonuses.
     * 
     * @param bonuses
     *            the bonuses
     */
    public void setBonuses(final List<ProductDTO> bonuses)
    {
        this.bonuses = bonuses;
    }

    /**
     * Gets price.
     * 
     * @return the price
     */
    public Double getPrice()
    {
        return price;
    }

    /**
     * Sets price.
     * 
     * @param price
     *            the price
     */
    public void setPrice(final Double price)
    {
        this.price = price;
    }

    /**
     * .
     * 
     * @return id. product id
     */

    public String getProductId()
    {
        return productId;
    }

    /**
     * lol.
     * 
     * @param productId
     *            id.
     */
    @XmlElement(name = "id")
    public void setProductId(final String productId)
    {
        this.productId = productId;
    }

    /**
     * .
     * 
     * @return l. name
     */

    public String getName()
    {
        return name;
    }

    /**
     * .
     * 
     * @param name
     *            qw
     */
    @XmlElement(name = "name")
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * .
     * 
     * @return lol. summary
     */

    public String getSummary()
    {
        return summary;
    }

    /**
     * .
     * 
     * @param summary
     *            sd.
     */
    @XmlElement(name = "summary")
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    public void setSummary(final String summary)
    {
        this.summary = summary;
    }

    /**
     * .
     * 
     * @return as. description
     */

    public String getDescription()
    {
        return description;
    }

    /**
     * .
     * 
     * @param description
     *            as.
     */
    @XmlElement(name = "description")
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * .
     * 
     * @return g. url
     */

    public String getUrl()
    {
        return url;
    }

    /**
     * .
     * 
     * @param url
     *            ur.
     */
    @XmlElement(name = "imageurl")
    public void setUrl(final String url)
    {
        this.url = url;
    }

    /**
     * a.
     * 
     * @return a. quantity
     */

    public Integer getQuantity()
    {
        return quantity;
    }

    /**
     * .
     * 
     * @param quantity
     *            3.
     */
    @XmlElement(name = "quantity")
    public void setQuantity(final Integer quantity)
    {
        this.quantity = quantity;
    }

    /**
     * @return the externalProductId
     */
    public String getExternalProductId()
    {
        return externalProductId;
    }

    /**
     * @param externalProductId
     *            the externalProductId to set
     */
    public void setExternalProductId(final String externalProductId)
    {
        this.externalProductId = externalProductId;
    }

    /**
     * @return the offerOrigin
     */
    public OfferOrigin getOfferOrigin()
    {
        return offerOrigin;
    }

    /**
     * @param offerOrigin
     *            the offerOrigin to set
     */
    public void setOfferOrigin(final OfferOrigin offerOrigin)
    {
        this.offerOrigin = offerOrigin;
    }

	/**
	 *
	 * @return the externalOfferId.
	 */
	public String getExternalOfferId()
	{
		return externalOfferId;
	}

	/**
	 *
	 * @param externalOfferId the externalOfferId.
	 */
	@XmlElement(name = "offerid")
	public void setExternalOfferId(final String externalOfferId)
	{
		this.externalOfferId = externalOfferId;
	}

	/**
	 *
	 * @return offerOriginCode.
	 */
	public String getOfferOriginCode()
	{
		return offerOriginCode;
	}

	/**
	 *
	 * @param offerOriginCode the offerOriginCode.
	 */
	@XmlElement(name = "offerorigin")
	public void setOfferOriginCode(final String offerOriginCode)
	{
		this.offerOriginCode = offerOriginCode;
	}

	/**
	 *
	 * @return useId.
	 */
	public boolean getUseId()
	{
		return useId;
	}

	/**
	 *
	 * @param useId the useId.
	 */
	public void setUseId(final boolean useId)
	{
		this.useId = useId;
	}
}
