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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.paymentpage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.impl.PaymentInfoFormDTO;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import java.util.List;
import org.springframework.validation.BindingResult;

/**
 * Abstract Form element group for Payment Page
 *
 * @module build
 * @version 1.0v Mar 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public abstract class AbstractPaymentFormElementGroup extends AbstractFormElementGroup
{

	/**
	 * Method have to be implemented.
	 * @param productModel the productModel
	 * @return true if product have needed type
	 */
	public abstract boolean isNeededProductType(final ProductModel productModel);

	@Override
	public boolean isDisplayed(final CartModel cart)
	{

		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (isNeededProductType(productModel))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		if (cart.getPaymentInfo() instanceof CreditCardPaymentInfoModel)
		{
			CreditCardPaymentInfoModel paymentInfoModel = (CreditCardPaymentInfoModel) cart.getPaymentInfo();

			if (paymentInfoModel != null)
			{
				if (!paymentInfoModel.getCcOwner().isEmpty())
				{
					return true;
				}
				if (!paymentInfoModel.getNumber().isEmpty())
				{
					return true;
				}
				if (!paymentInfoModel.getValidToMonth().isEmpty())
				{
					return true;
				}
				if (!paymentInfoModel.getValidToYear().isEmpty())
				{
					return true;
				}
				if (!paymentInfoModel.getType().getCode().isEmpty())
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{

	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		return true;
	}

	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart, final CustomerModel customerModel)
	{

	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
	{
		
	}
}

