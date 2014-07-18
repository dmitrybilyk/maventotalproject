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
package com.cgi.pacoshop.core.service.evaluator;


import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.model.ProductTypesRestrictionModel;
import de.hybris.platform.cms2.servicelayer.data.RestrictionData;
import de.hybris.platform.cms2.servicelayer.services.evaluator.CMSRestrictionEvaluator;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Restricts component for a collection of product types.
 *
 * @module core
 * @version 1.0v Jun 13, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ProductTypesCMSRestrictionEvaluator implements CMSRestrictionEvaluator<ProductTypesRestrictionModel>
{
	private static final String ORDERGUID                = "orderguid";

	@Resource
	private BaseStoreService       baseStoreService;

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private CartService cartService;

	@Override
	public boolean evaluate(final ProductTypesRestrictionModel productTypesRestrictionModel, final RestrictionData restrictionData)
	{
		final Set<ProductTypeModel> productTypes = productTypesRestrictionModel.getProductTypes();
		final String orderGUID = getCurrentRequest().getParameter(ORDERGUID);

		if (orderGUID != null)
		{
			final OrderModel orderModel = getOrderModelByGUID(orderGUID);

			if (orderModel != null)
			{
				return evaluateProductTypes(orderModel, productTypes);
			}
		}
		else
		{
			final CartModel cartModel = cartService.getSessionCart();

			if (cartModel != null)
			{
				return evaluateProductTypes(cartModel, productTypes);
			}
		}

		return false;
	}

	private boolean evaluateProductTypes(final AbstractOrderModel model, final Set<ProductTypeModel> productTypes)
	{
		List<AbstractOrderEntryModel> entries = model.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (productTypes.contains(productModel.getProductType()))
			{
				return true;
			}
		}
		return false;
	}

	private static HttpServletRequest getCurrentRequest()
	{
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	private OrderModel getOrderModelByGUID(final String orderId)
	{
		final BaseStoreModel baseStoreModel = baseStoreService.getCurrentBaseStore();
		return customerAccountService.getOrderDetailsForGUID(orderId, baseStoreModel);
	}
}
