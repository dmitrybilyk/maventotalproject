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
package com.cgi.pacoshop.core.refdata;


/**
 * Combination of fields that uniquelly identify the {@link com.cgi.pacoshop.core.model.ProductTypeModel} object
 *
 * @module pacoshopcore
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductTypeKey
{
	/**
	 * The constant SEED.
	 */
	public static final int SEED = 31;
	private String productGroup;
	private String productClass;
	private String productCluster;

	/**
	 * Default constructor.
	 */
	public ProductTypeKey()
	{
	}

	/**
	 * Constructs ProductType key using group, class cluster.
	 * @param productGroup group
	 * @param productClass class
	 * @param productCluster cluster
	 */
	public ProductTypeKey(final String productGroup, final String productClass, final String productCluster)
	{
		this.productGroup = productGroup;
		this.productClass = productClass;
		this.productCluster = productCluster;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		final ProductTypeKey that = (ProductTypeKey) o;

		if (productClass != null ? !productClass.equals(that.productClass) : that.productClass != null)
		{
			return false;
		}
		if (productCluster != null ? !productCluster.equals(that.productCluster) : that.productCluster != null)
		{
			return false;
		}
		if (productGroup != null ? !productGroup.equals(that.productGroup) : that.productGroup != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = productGroup != null ? productGroup.hashCode() : 0;
		result = SEED * result + (productClass != null ? productClass.hashCode() : 0);
		result = SEED * result + (productCluster != null ? productCluster.hashCode() : 0);
		return result;
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
	public void setProductGroup(final String productGroup)
	{
		this.productGroup = productGroup;
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
	public void setProductClass(final String productClass)
	{
		this.productClass = productClass;
	}

	/**
	 * Gets product cluster.
	 *
	 * @return the product cluster
	 */
	public String getProductCluster()
	{
		return productCluster;
	}

	/**
	 * Sets product cluster.
	 *
	 * @param productCluster the product cluster
	 */
	public void setProductCluster(final String productCluster)
	{
		this.productCluster = productCluster;
	}
}
