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
package com.cgi.pacoshop.facades.checkout.deeplink.mandatoryfieldsvalidation.impl;


import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductIsNotOnlineException;
import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductNotActiveOrNotApprovedException;
import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductPriceNotDefinedException;
import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductQuantityNotAllowedException;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.util.LogHelper;
import com.cgi.pacoshop.facades.checkout.deeplink.mandatoryfieldsvalidation.ProductMandatoryFieldsValidationFacade;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Facade to provide the logic for the product mandatory fields validation
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @version 1.0v Feb 26, 2014
 * @module hybris
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 */
public class DefaultProductMandatoryFieldsValidationFacade implements ProductMandatoryFieldsValidationFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultProductMandatoryFieldsValidationFacade.class);

	@Override
	public List<PacoShopWebException> validateProduct(final ProductModel product, final ProductDTO requestedProductSpecification)
	{
		List<PacoShopWebException> exceptions = new ArrayList<>();
		Date today = new Date();
		if (!product.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED))
		{
			exceptions.add(new ProductNotActiveOrNotApprovedException(product.getCode()));
		}

		if (!product.getActive())
		{
			exceptions.add(new ProductNotActiveOrNotApprovedException(product.getCode()));
		}

		if (
				  requestedProductSpecification.getQuantity() != null
							 && (product.getMinOrderQuantity() > requestedProductSpecification.getQuantity()
							 || product.getMaxOrderQuantity() < requestedProductSpecification.getQuantity()))
		{
			String message =
					  String.format(
								 "Minimum Order Quantity: {%s}, Maximum Order Quantity: {%s}, Requested: {%s}",
								 product.getMinOrderQuantity(), product.getMaxOrderQuantity(),
								 requestedProductSpecification.getQuantity());
			exceptions.add(new ProductQuantityNotAllowedException(message));
		}

		final Collection<PriceRowModel> europe1Prices = product.getEurope1Prices();
		if (europe1Prices == null || europe1Prices.size() == 0)
		{
			LogHelper.error(LOG, "Product productId=%s does not contain PriceRow assigned", product.getCode());
			exceptions.add(new ProductPriceNotDefinedException(product.getCode()));
		}

		//exception if it should not be sold online yet ("onlineDate" <= current date <= "offlineDate")
		Date onlineDate = product.getOnlineDate();
		Date offlineDate = product.getOfflineDate();

		if (onlineDate != null)
		{
			onlineDate = DateUtils.truncate(onlineDate, Calendar.DAY_OF_MONTH);
		}

		if (offlineDate != null)
		{
			offlineDate = DateUtils.truncate(offlineDate, Calendar.DAY_OF_MONTH);
		}

		today = DateUtils.truncate(today, Calendar.DAY_OF_MONTH);

		if ((onlineDate != null && today.before(onlineDate)) || (offlineDate != null && today.after(offlineDate)))
		{
			exceptions.add(new ProductIsNotOnlineException(product.getCode()));
		}

		return exceptions;
	}
}
