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

import com.cgi.pacoshop.core.enums.ProductClass;
import com.cgi.pacoshop.core.enums.ProductGroup;
import com.cgi.pacoshop.core.enums.ProductOwner;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * This is the rest representation of the SAP create single order orderEntry list element.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPSDOrderEntryEntity implements Serializable
{

	/**
	 *
	 */
	private static final long serialVersionUID = 2515178103531477722L;
	private String       orderRequestPositionId;
	private long         orderSize;
	private String       productOrigin;
	private String       productId;
	private ProductOwner productOwner;
	private ProductClass productClass;
	private ProductGroup productGroup;

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
	 * @return the orderSize
	 */
	public long getOrderSize()
	{
		return orderSize;
	}

	/**
	 * @param orderSize
	 *            the orderSize to set
	 */
	public void setOrderSize(final long orderSize)
	{
		this.orderSize = orderSize;
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
	 * @return the productOwner
	 */
	public ProductOwner getProductOwner()
	{
		return productOwner;
	}

	/**
	 * @param productOwner
	 *            the productOwner to set
	 */
	public void setProductOwner(final ProductOwner productOwner)
	{
		this.productOwner = productOwner;
	}

	/**
	 * @return the productClass
	 */
	public ProductClass getProductClass()
	{
		return productClass;
	}

	/**
	 * @param productClass
	 *            the productClass to set
	 */
	public void setProductClass(final ProductClass productClass)
	{
		this.productClass = productClass;
	}

	/**
	 * @return the productGroup
	 */
	public ProductGroup getProductGroup()
	{
		return productGroup;
	}

	/**
	 * @param productGroup
	 *            the productGroup to set
	 */
	public void setProductGroup(final ProductGroup productGroup)
	{
		this.productGroup = productGroup;
	}


}
