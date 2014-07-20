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
package runner.portal;


import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity Order from callback Rest Service.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@XmlRootElement(name = "order")
public class OrderDTO
{
	private String cartId;

	private Double shippingCost;

//	private ArrayList<ProductDTO> cartentries;

	private ArrayList<String> paymentmethods;


	/**
	 * .
	 * @return id.
	 */

	public String getCartId()
	{
		return cartId;
	}

	/**
	 * as.
	 * @param cartId id.
	 */
	@XmlElement(name = "cartid")
	public void setCartId(final String cartId)
	{
		this.cartId = cartId;
	}

	/**
	 * .
	 * @return s.
	 */

	public Double getShippingCost()
	{
		return shippingCost;
	}

	/**
	 * .
	 * @param shippingCost lol.
	 */
	@XmlElement(name = "shippingcost")
	public void setShippingCost(final Double shippingCost)
	{
		this.shippingCost = shippingCost;
	}

	/**
	 * .
	 * @return common.
	 */

//	public ArrayList<ProductDTO> getCartentries()
//	{
//		return cartentries;
//	}

	/**
	 * .
//	 * @param cartentries a.
	 */
//	@XmlElementWrapper(name = "cartentries")
//	@XmlElement(name = "product")
//	public void setCartentries(final ArrayList<ProductDTO> cartentries)
//	{
//		this.cartentries = cartentries;
//	}

	/**
	 * .
	 * @return as.
	 */

	public ArrayList<String> getPaymentmethod()
	{
		return paymentmethods;
	}

	/**
	 * .
	 * @param paymentmethods a.
	 */
	@XmlElementWrapper(name = "paymentmethods")
	@XmlElement(name = "paymentmethod")
	public void setPaymentmethod(final ArrayList<String> paymentmethods)
	{
		this.paymentmethods = paymentmethods;
	}
}
