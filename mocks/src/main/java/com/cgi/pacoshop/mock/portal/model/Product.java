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
package com.cgi.pacoshop.mock.portal.model;


import com.cgi.pacoshop.mock.portal.service.CDATAAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Entity for Product type.
 *
 *
 * @module portal-com.cgi.pacoshop.mock
 * @version 1.0v Feb 04, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@XmlRootElement(name = "product")
public class Product
{

	private String productId;

	private String offerOrigin;

	private String externalOfferId;

	private String name;

	private String summary;

	private String description;

	private String url;

	private Integer quantity;

	/**
	 * Default constructor.
	 */
	public Product()
	{
	}

	/**
	 * .
	 * @return id.
	 */
	@XmlElement(name = "id")
	public String getProductId()
	{
		return productId;
	}

	/**
	 * lol.
	 * @param productId id.
	 */

	public void setProductId(final String productId)
	{
		this.productId = productId;
	}

	/**
	 * .
	 * @return l.
	 */
	@XmlElement(name = "name")
	public String getName()
	{
		return name;
	}

	/**
	 * .
	 * @param name qw
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * .
	 * @return lol.
	 */
	@XmlElement(name = "summary")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	public String getSummary()
	{
		return summary;
	}

	/**
	 * .
	 * @param summary sd.
	 */
	public void setSummary(final String summary)
	{
		this.summary = summary;
	}

	/**
	 * .
	 * @return as.
	 */
	@XmlElement(name = "description")
	@XmlJavaTypeAdapter(value = CDATAAdapter.class)
	public String getDescription()
	{
		return description;
	}

	/**
	 * .
	 * @param description as.
	 */
	public void setDescription(final String description)
	{
		this.description = description;
	}

	/**
	 * .
	 * @return g.
	 */
	@XmlElement(name = "imageurl")
	public String getUrl()
	{
		return url;
	}

	/**
	 * .
	 * @param url ur.
	 */
	public void setUrl(final String url)
	{
		this.url = url;
	}

	/**
	 * a.
	 * @return a.
	 */
	@XmlElement(name = "quantity")
	public Integer getQuantity()
	{
		return quantity;
	}

	/**
	 * .
	 * @param quantity 3.
	 */
	public void setQuantity(final Integer quantity)
	{
		this.quantity = quantity;
	}

	/**
	 *
	 * @return offerOrigin.
	 */
	@XmlElement(name = "offerorigin")
	public String getOfferOrigin()
	{
		return offerOrigin;
	}

	/**
	 *
	 * @param offerOrigin the offerOrigin.
	 */
	public void setOfferOrigin(final String offerOrigin)
	{
		this.offerOrigin = offerOrigin;
	}

	/**
	 *
	 * @return externalOfferId.
	 */
	@XmlElement(name = "externalofferid")
	public String getExternalOfferId()
	{
		return externalOfferId;
	}

	/**
	 *
	 * @param externalOfferId the externalOfferId.
	 */
	public void setExternalOfferId(final String externalOfferId)
	{
		this.externalOfferId = externalOfferId;
	}

}

