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
package com.learn.formgroups;

import com.learn.formgroups.dtos.OrderTotalsDTO;
import com.learn.formgroups.dtos.ProductDTO;
import com.learn.formgroups.formdtoimpl.SummaryStepFormDTO;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * The fragment showing the cart contents to the page, including price communication for subscription products. This
 * presentation is re-used on summary page and thank you page, which needs to be accounted for in implementation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 21, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 * @see "https://wiki.hybris.com/"
 */
@Scope("session")
public class CartContentsFragmentDTO implements FormDTO
{
	private List<ProductDTO> products;
	private OrderTotalsDTO totals;
	private String orderCode;
	private SummaryStepFormDTO form;
//	private PaymentInfoData paymentInfo;
	private boolean downloadButtonDisplayed;

	/**
	 * Get form object.
	 *
	 * @return Current order form.
	 */
	public SummaryStepFormDTO getForm()
	{
		return form;
	}

	/**
	 * Set form object.
	 *
	 * @param form Form object.
	 */
	public void setForm(final SummaryStepFormDTO form)
	{
		this.form = form;
	}

	/**
	 * Get payment info.
	 *
	 * @return Current payment info.
	 */
//	public PaymentInfoData getPaymentInfo()
//	{
//		return paymentInfo;
//	}

	/**
	 * Set payment object.
	 *
	 * @param paymentInfo Payment info.
	 */
//	public void setPaymentInfo(final PaymentInfoData paymentInfo)
//	{
//		this.paymentInfo = paymentInfo;
//	}

	/**
	 * Gets order code.
	 *
	 * @return the order code
	 */
	public String getOrderCode()
	{
		return orderCode;
	}

	/**
	 * Sets order code.
	 *
	 * @param orderCode the order code
	 */
	public void setOrderCode(final String orderCode)
	{
		this.orderCode = orderCode;
	}

	/**
	 * Gets totals.
	 *
	 * @return the totals
	 */
	public OrderTotalsDTO getTotals()
	{
		if (totals == null)
		{
			totals = new OrderTotalsDTO();
		}
		return totals;
	}

	/**
	 * Sets totals.
	 *
	 * @param totals the totals
	 */
	public void setTotals(final OrderTotalsDTO totals)
	{
		this.totals = totals;
	}

	/**
	 * Gets products.
	 *
	 * @return the products
	 */
	public List<ProductDTO> getProducts()
	{
		return products;
	}

	/**
	 * Sets products.
	 *
	 * @param products the products
	 */
	public void setProducts(final List<ProductDTO> products)
	{
		this.products = products;
	}

	/**
	 * Gets isDownloadButtonDisplayed.
	 *
	 * @return the isDownloadButtonDisplayed
	 */
	public boolean isDownloadButtonDisplayed()
	{
		return downloadButtonDisplayed;
	}

	/**
	 * Sets isDownloadButtonDisplayed.
	 *
	 * @param downloadButtonDisplayed the downloadButtonDisplayed.
	 */
	public void setDownloadButtonDisplayed(final boolean downloadButtonDisplayed)
	{
		this.downloadButtonDisplayed = downloadButtonDisplayed;
	}
}
