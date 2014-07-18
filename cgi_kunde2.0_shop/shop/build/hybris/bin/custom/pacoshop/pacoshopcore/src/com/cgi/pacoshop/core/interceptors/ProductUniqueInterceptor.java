/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/

package com.cgi.pacoshop.core.interceptors;

import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.service.PacoshopProductService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * This id interceptor for checking Product before saving.
 *
 * @module Pacoshopcore
 * @version 1.0v Dec 05, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see          https://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public class ProductUniqueInterceptor implements ValidateInterceptor
{

	private PacoshopProductService productService;

	/**
	 * Validates.
	 *
	 * @param model model
	 * @param ctx interceptor context
	 * @throws InterceptorException Non-unique
	 */
	@Override
	public void onValidate(final Object model, final InterceptorContext ctx) throws InterceptorException
	{
		validateParameterNotNull(model, "Parameter model must not be null");

		if (model instanceof ProductModel)
		{
			final ProductModel productModel = (ProductModel) model;

			if (isIdEmpty(productModel))
			{
				throw new InterceptorException("Both externalOffierId and offerOrigin fields must be not empty");
			}

			if (isSaveProcess(productModel) && productExist(productModel))
			{
				throw new InterceptorException("Product not unique!");
			}
		}
	}

	private static boolean isIdEmpty(final ProductModel productModel)
	{
		final String externalOfferId = productModel.getExternalOfferId();
		final OfferOrigin offerOrigin = productModel.getOfferOrigin();

		return StringUtils.isEmpty(externalOfferId) || offerOrigin == null || StringUtils.isEmpty(offerOrigin.getCode());
	}

	private boolean isSaveProcess(final ProductModel productModel)
	{
		return productModel.getPk() == null;
	}

	private boolean productExist(final ProductModel productModel)
	{
		final List<ProductModel> existingProductModels = productService.findProductByExternalOfferId(
				  productModel.getExternalOfferId());

		if (existingProductModels == null || existingProductModels.isEmpty())
		{
			return false;
		}

		for (ProductModel product : existingProductModels)
		{
			if (isEqual(productModel, product))
			{
				return true;
			}
		}

		return false;
	}

	private boolean isEqual(final ProductModel productModel, final ProductModel existingProductModel)
	{
		if (existingProductModel.getExternalOfferId()
				  .equals(productModel.getExternalOfferId())
				  && existingProductModel.getOfferOrigin().getCode()
				  .equals(productModel.getOfferOrigin().getCode())
				  && existingProductModel.getCatalogVersion().getPk()
				  .equals(productModel.getCatalogVersion().getPk()))
		{
			return true;
		}

		return false;
	}

	/**
	 * @return the productService
	 */
	protected PacoshopProductService getProductService()
	{
		return productService;
	}

	/**
	 * productService setter.
	 * @param productServiceParam - new productService;
	 */
	@Required
	public void setProductService(final PacoshopProductService productServiceParam)
	{
		this.productService = productServiceParam;
	}

}
