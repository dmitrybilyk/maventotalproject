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

import com.cgi.pacoshop.core.service.CustomJsonDateDeserializer;
import com.cgi.pacoshop.core.service.CustomJsonStringTrimmerDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Base entity for imported Single Product from SAP.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v dec 12, 2013
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class SingleProductDTO implements Serializable
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
	private int minOrder;
	private int maxOrder;
	private boolean otherInvoiceRecipientAllowed;
	private boolean otherConsigneeAllowed;
	private int vat;
	private Price price;
	private Date validFrom;
	private Date validTo;

	/**
	 * Gets price.
	 *
	 * @return the price
	 */
	public Price getPrice()
	{
		return price;
	}

	/**
	 * Sets price.
	 *
	 * @param price the price
	 */
	public void setPrice(final Price price)
	{
		this.price = price;
	}

	/**
	 * Gets vat.
	 *
	 * @return the vat
	 */
	public Integer getVat()
	{
		return vat;
	}

	/**
	 * Sets vat.
	 *
	 * @param vat the vat
	 */
	public void setVat(final Integer vat)
	{
		this.vat = vat;
	}

	/**
	 * Gets valid from.
	 *
	 * @return the valid from
	 */
	public Date getValidFrom()
	{
		if (validFrom == null)
		{
			return null;
		}
		return new Date(validFrom.getTime());
	}

	/**
	 * Sets valid from.
	 *
	 * @param validFrom the valid from
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
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
	 * Gets valid until.
	 *
	 * @return the valid until
	 */
	public Date getValidTo()
	{
		if (validTo == null)
		{
			return null;
		}
		return new Date(validTo.getTime());
	}

	/**
	 * Sets valid until.
	 *
	 * @param validTo the valid until
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
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

	/**
	 * Gets offer origin.
	 *
	 * @return the offer origin
	 */
	public String getOfferOrigin()
	{
		return offerOrigin;
	}

	/**
	 * Sets offer origin.
	 *
	 * @param offerOrigin the offer origin
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setOfferOrigin(final String offerOrigin)
	{
		this.offerOrigin = offerOrigin;
	}

	/**
	 * Gets offer id.
	 *
	 * @return the offer id
	 */
	public String getOfferId()
	{
		return offerId;
	}

	/**
	 * Sets offer id.
	 *
	 * @param offerId the offer id
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setOfferId(final String offerId)
	{
		this.offerId = offerId;
	}

	/**
	 * Gets offer description.
	 *
	 * @return the offer description
	 */
	public String getOfferDescription()
	{
		return offerDescription;
	}

	/**
	 * Sets offer description.
	 *
	 * @param offerDescription the offer description
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setOfferDescription(final String offerDescription)
	{
		this.offerDescription = offerDescription;
	}

	/**
	 * Gets product origin.
	 *
	 * @return the product origin
	 */
	public String getProductOrigin()
	{
		return productOrigin;
	}

	/**
	 * Sets product origin.
	 *
	 * @param productOrigin the product origin
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setProductOrigin(final String productOrigin)
	{
		this.productOrigin = productOrigin;
	}

	/**
	 * Gets product id.
	 *
	 * @return the product id
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * Sets product id.
	 *
	 * @param productId the product id
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setProductId(final String productId)
	{
		this.productId = productId;
	}

	/**
	 * Gets product owner.
	 *
	 * @return the product owner
	 */
	public String getProductOwner()
	{
		return productOwner;
	}

	/**
	 * Sets product owner.
	 *
	 * @param productOwner the product owner
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setProductOwner(final String productOwner)
	{
		this.productOwner = productOwner;
	}

	/**
	 * Gets product class.
	 *
	 * @return the product class
	 */
	public String getProductClass()
	{
		return productClass;
	}

	/**
	 * Sets product class.
	 *
	 * @param productClass the product class
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setProductClass(final String productClass)
	{
		this.productClass = productClass;
	}

	/**
	 * Gets product group.
	 *
	 * @return the product group
	 */
	public String getProductGroup()
	{
		return productGroup;
	}

	/**
	 * Sets product group.
	 *
	 * @param productGroup the product group
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setProductGroup(final String productGroup)
	{
		this.productGroup = productGroup;
	}

	/**
	 * Is prepay only.
	 *
	 * @return the boolean
	 */
	public boolean isPrepayOnly()
	{
		return prepayOnly;
	}

	/**
	 * Sets prepay only.
	 *
	 * @param prepayOnly the prepay only
	 */
	public void setPrepayOnly(final boolean prepayOnly)
	{
		this.prepayOnly = prepayOnly;
	}

	/**
	 * Is student offer.
	 *
	 * @return the boolean
	 */
	public boolean isStudentOffer()
	{
		return studentOffer;
	}

	/**
	 * Sets student offer.
	 *
	 * @param studentOffer the student offer
	 */
	public void setStudentOffer(final boolean studentOffer)
	{
		this.studentOffer = studentOffer;
	}

	/**
	 * Is client offer.
	 *
	 * @return the boolean
	 */
	public boolean isClientOffer()
	{
		return clientOffer;
	}

	/**
	 * Sets client offer.
	 *
	 * @param clientOffer the client offer
	 */
	public void setClientOffer(final boolean clientOffer)
	{
		this.clientOffer = clientOffer;
	}

	/**
	 * Is invoice recipient offer.
	 *
	 * @return the boolean
	 */
	public boolean isInvoiceRecipientOffer()
	{
		return invoiceRecipientOffer;
	}

	/**
	 * Sets invoice recipient offer.
	 *
	 * @param invoiceRecipientOffer the invoice recipient offer
	 */
	public void setInvoiceRecipientOffer(final boolean invoiceRecipientOffer)
	{
		this.invoiceRecipientOffer = invoiceRecipientOffer;
	}

	/**
	 * Gets offer picture.
	 *
	 * @return the offer picture
	 */
	public String getOfferPicture()
	{
		return offerPicture;
	}

	/**
	 * Sets offer picture.
	 *
	 * @param offerPicture the offer picture
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setOfferPicture(final String offerPicture)
	{
		this.offerPicture = offerPicture;
	}

	/**
	 * Is consignee offer.
	 *
	 * @return the boolean
	 */
	public boolean isConsigneeOffer()
	{
		return consigneeOffer;
	}

	/**
	 * Sets consignee offer.
	 *
	 * @param consigneeOffer the consignee offer
	 */
	public void setConsigneeOffer(final boolean consigneeOffer)
	{
		this.consigneeOffer = consigneeOffer;
	}

	/**
	 * Is other invoice recipient allowed.
	 *
	 * @return the boolean
	 */
	public boolean isOtherInvoiceRecipientAllowed()
	{
		return otherInvoiceRecipientAllowed;
	}

	/**
	 * Sets other invoice recipient allowed.
	 *
	 * @param otherInvoiceRecipientAllowed the other invoice recipient allowed
	 */
	public void setOtherInvoiceRecipientAllowed(final boolean otherInvoiceRecipientAllowed)
	{
		this.otherInvoiceRecipientAllowed = otherInvoiceRecipientAllowed;
	}

	/**
	 * Is other consignee allowed.
	 *
	 * @return the boolean
	 */
	public boolean isOtherConsigneeAllowed()
	{
		return otherConsigneeAllowed;
	}

	/**
	 * Sets other consignee allowed.
	 *
	 * @param otherConsigneeAllowed the other consignee allowed
	 */
	public void setOtherConsigneeAllowed(final boolean otherConsigneeAllowed)
	{
		this.otherConsigneeAllowed = otherConsigneeAllowed;
	}

	/**
	 * Gets min order.
	 *
	 * @return the min order
	 */
	public Integer getMinOrder()
	{
		return minOrder;
	}

	/**
	 * Sets min order.
	 *
	 * @param minOrder the min order
	 */
	public void setMinOrder(final Integer minOrder)
	{
		this.minOrder = minOrder;
	}

	/**
	 * Gets max order.
	 *
	 * @return the max order
	 */
	public Integer getMaxOrder()
	{
		return maxOrder;
	}

	/**
	 * Sets max order.
	 *
	 * @param maxOrder the max order
	 */
	public void setMaxOrder(final Integer maxOrder)
	{
		this.maxOrder = maxOrder;
	}
}
