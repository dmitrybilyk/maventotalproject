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


import de.hybris.platform.util.TaxValue;
import java.util.Collection;

/**
 * The tax information displayed in login, summary and thankyou pages
 *
 * @module hybris
 * @version 1.0v Mar 27, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class OrderTotalsDTO
{
	private Double                  totalTax;
	private Collection<TaxValueDTO> totalTaxValues;
	private Double                  deliveryTax;
	private Double                  deliveryCost;
	private Double                  totalPrice;
	private Double                  paymentCost;
	private Double                  subTotal;
	private String                  totalPriceForShowing;
	private String                  deliveryCostForShowing;
	private String                  deliveryTaxForShowing;
	private String                  subTotalForShowing;
	private String                  paymentCostForShowing;

	private String currency;

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
	 * Sets currency.
	 *
	 * @param currency the currency
	 */
	public void setCurrency(final String currency)
	{
		this.currency = currency;
	}

	/**
	 * Gets total price.
	 *
	 * @return the total price
	 */
	public Double getTotalPrice()
	{
		return totalPrice;
	}

	/**
	 * Sets total price.
	 *
	 * @param totalPrice the total price
	 */
	public void setTotalPrice(final Double totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	/**
	 * Gets delivery value.
	 *
	 * @return the delivery value
	 */
	public Double getDeliveryCost()
	{
		return deliveryCost;
	}

	/**
	 * Sets delivery value.
	 *
	 * @param deliveryCost the delivery value
	 */
	public void setDeliveryCost(final Double deliveryCost)
	{
		this.deliveryCost = deliveryCost;
	}

	/**
	 * Gets delivery tax.
	 *
	 * @return the delivery tax
	 */
	public Double getDeliveryTax()
	{
		return deliveryTax;
	}

	/**
	 * Sets delivery tax.
	 *
	 * @param deliveryTax the delivery tax
	 */
	public void setDeliveryTax(final Double deliveryTax)
	{
		this.deliveryTax = deliveryTax;
	}

	/**
	 * Gets total tax values.
	 *
	 * @return the total tax values
	 */
	public Collection<TaxValueDTO> getTotalTaxValues()
	{
		return totalTaxValues;
	}

	/**
	 * Sets total tax values.
	 *
	 * @param totalTaxValues the total tax values
	 */
	public void setTotalTaxValues(final Collection<TaxValueDTO> totalTaxValues)
	{
		this.totalTaxValues = totalTaxValues;
	}

	/**
	 * Gets total tax.
	 *
	 * @return the total tax
	 */
	public Double getTotalTax()
	{
		return totalTax;
	}

	/**
	 * Sets total tax.
	 *
	 * @param totalTax the total tax
	 */
	public void setTotalTax(final Double totalTax)
	{
		this.totalTax = totalTax;
	}

	/**
	 * Gets payment cost.
	 *
	 * @return the payment cost
	 */
	public Double getPaymentCost()
	{
		return paymentCost;
	}

	/**
	 * Sets payment cost.
	 *
	 * @param paymentCost the payment cost
	 */
	public void setPaymentCost(final Double paymentCost)
	{
		this.paymentCost = paymentCost;
	}

	/**
	 * Gets subTotal.
	 * @return the subTotal
	 */
	public Double getSubTotal()
	{
		return this.subTotal;
	}

	/**
	 * Sets subTotal.
	 *
	 * @param subTotal the subTotal
	 */
	public void setSubTotal(final Double subTotal)
	{
		this.subTotal = subTotal;
	}

	/**
	 *
	 * @return totalPriceForShowing
	 */
	public String getTotalPriceForShowing()
	{
		return totalPriceForShowing;
	}

	/**
	 *
	 * @param totalPriceForShowing the totalPriceForShowing.
	 */
	public void setTotalPriceForShowing(final String totalPriceForShowing)
	{
		this.totalPriceForShowing = totalPriceForShowing;
	}

	/**
	 *
	 * @return deliveryCostForShowing
	 */
	public String getDeliveryCostForShowing()
	{
		return deliveryCostForShowing;
	}

	/**
	 *
	 * @param deliveryCostForShowing the deliveryCostForShowing.
	 */
	public void setDeliveryCostForShowing(final String deliveryCostForShowing)
	{
		this.deliveryCostForShowing = deliveryCostForShowing;
	}

	/**
	 *
	 * @return deliveryTaxForShowing
	 */
	public String getDeliveryTaxForShowing()
	{
		return deliveryTaxForShowing;
	}

	/**
	 *
	 * @param deliveryTaxForShowing the deliveryTaxForShowing.
	 */
	public void setDeliveryTaxForShowing(final String deliveryTaxForShowing)
	{
		this.deliveryTaxForShowing = deliveryTaxForShowing;
	}

	/**
	 *
	 * @return subTotalForShowing
	 */
	public String getSubTotalForShowing()
	{
		return subTotalForShowing;
	}

	/**
	 *
	 * @param subTotalForShowing the subTotalForShowing.
	 */
	public void setSubTotalForShowing(final String subTotalForShowing)
	{
		this.subTotalForShowing = subTotalForShowing;
	}

	/**
	 *
	 * @return paymentCostForShowing
	 */
	public String getPaymentCostForShowing()
	{
		return paymentCostForShowing;
	}

	/**
	 *
	 * @param paymentCostForShowing the paymentCostForShowing.
	 */
	public void setPaymentCostForShowing(final String paymentCostForShowing)
	{
		this.paymentCostForShowing = paymentCostForShowing;
	}

}
