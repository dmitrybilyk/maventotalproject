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
package com.cgi.pacoshop.facades.checkout.deeplink.impl;


import com.cgi.pacoshop.core.exceptions.deeplink.status400.NoProductsSpecifiedException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.PortalCallbackInvalidProductsInCartException;
import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductPriceNotDefinedException;
import com.cgi.pacoshop.core.model.OrderDTO;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.DeeplinkCallbackService;
import com.cgi.pacoshop.core.service.PaymentTypeService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.util.LogHelper;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkCallbackFacade;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkFacade;
import com.cgi.pacoshop.facades.constants.PacoshopFacadesConstants;
import de.hybris.platform.core.model.order.CartEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import org.apache.log4j.Logger;

/**
 * Implementation of AlternativeDeeplinFacade.
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
public class DeeplinkCallbackFacadeImpl implements DeeplinkCallbackFacade
{
	private static final Logger LOG = Logger.getLogger(DeeplinkCallbackFacadeImpl.class);

	private static final Integer DEFAULT_PRODUCT_QUANTITY = 1;

	@Resource
	private CartService              cartService;
	@Resource
	private CalculationService       calculationService;
	@Resource
	private DeeplinkCallbackService  deeplinkCallbackService;
	@Resource
	private PaymentTypeService       paymentTypeService;
	@Resource
	private DeeplinkFacade           deeplinkFacade;
	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Override
	public String getPortalUrl(final String portalId)
	{
		return deeplinkCallbackService.getDeeplinkCallbackUrl(portalId);
	}

	@Override
	public CartModel createCartWithProducts(final List<ProductModel> productList, final OrderDTO orderDTO)
	{
		List<String> notValidCallbackProductIds = new ArrayList<>();
		for (ProductModel productModel : productList)
		{
			if (!isValidCallbackProduct(productModel))
			{
				notValidCallbackProductIds.add(productModel.getCode());
			}
		}
		if (notValidCallbackProductIds.size() > 0)
		{
			throw new PortalCallbackInvalidProductsInCartException(notValidCallbackProductIds.toString());
		}

		cartService.removeSessionCart();
		final CartModel cart = cartService.getSessionCart();

		cart.setDeliveryCost(orderDTO.getShippingCost());

		cart.setAllowedPaymentType(paymentTypeService.getAllowedPaymentType(orderDTO.getPaymentmethod()));

		Map<String, ProductDTO> map = getStringProductDTOMap(orderDTO, productList);

		for (ProductModel productModel : productList)
		{
			String id = productModel.getCode().toLowerCase();
			checkPriceRow(productModel);
			final ProductDTO productDTO = map.get(id);
			Integer quantity = DEFAULT_PRODUCT_QUANTITY;
			if (productDTO.getQuantity() != null && productDTO.getQuantity() != 0)
			{
				quantity = productDTO.getQuantity();
			}
			final CartEntryModel cartEntryModel =
					  cartService.addNewEntry(cart, productModel, quantity, productModel.getUnit());

			cartEntryModel.setProductImageUrl(productDTO.getUrl());
			cartEntryModel.setContentTitle(productDTO.getName());
			cartEntryModel.setDescription(productDTO.getDescription());
			cartEntryModel.setSummary(productDTO.getSummary());
		}

		return cart;
	}

	//a cart returned from a portal by callback can only contain non-digital GUTER products
	private boolean isValidCallbackProduct(final ProductModel productModel)
	{
		final String validCallbackProductType = shopConfigurationService.getProperty(PacoshopFacadesConstants.ProductTypes.GOOD);
		boolean result =
				  !productModel.getDigital() && productModel.getProductType().getCode().equalsIgnoreCase(validCallbackProductType);
		debug(LOG, "Product %s is a valid callback product: %b", productModel.getCode(), result);
		return result;
	}

	@Override
	public List<ProductModel> getProductModels(final OrderDTO orderDTO)
	{
		return deeplinkFacade.findProductInOnlineCatalogOfCurrentWebsite(orderDTO.getCartentries());
	}

	@Override
	public void calculateCart(final CartModel cartModel)
	{
		try
		{
			calculationService.calculate(cartModel);
		}
		catch (CalculationException e)
		{
			LOG.error("Error occurred while re-calculating the cart");
			e.printStackTrace();
		}
	}

	private Map<String, ProductDTO> getStringProductDTOMap(final OrderDTO orderDTO, final List<ProductModel> productList)
	{
		Map<String, ProductDTO> map = new HashMap<>();
		for (ProductDTO productDTO : orderDTO.getCartentries())
		{
			String productId = productDTO.getProductId();
			if (isEmpty(productId))
			{
				productId = findProductIdByOffer(productList, productDTO);
			}

			if (isNotEmpty(productId))
			{
				map.put(productId.toLowerCase(), productDTO);
			}
			else
			{
				LogHelper.error(LOG, "Product not found by offer id '%s'.", productDTO.getExternalOfferId());
			}
		}
		return map;
	}

	private String findProductIdByOffer(final List<ProductModel> productList, final ProductDTO productDTO)
	{
		for (ProductModel product : productList)
		{
			if (product.getOfferOrigin().getCode().equals(productDTO.getOfferOriginCode())
					  && product.getExternalOfferId().equals(productDTO.getExternalOfferId()))
			{
				return product.getCode();
			}
		}
		throw new NoProductsSpecifiedException();
	}

	private void checkPriceRow(final ProductModel product)
	{
		if (product.getEurope1Prices().size() == 0)
		{
			LOG.error(" Product with code = " + product.getCode() + " doesn't have a Price Row ");
			throw new ProductPriceNotDefinedException("Product doesn't have PriceRow");
		}
	}
}
