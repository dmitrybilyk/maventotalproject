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
package com.cgi.pacoshop.storefront.controllers.checkout.deeplink;


import static com.cgi.pacoshop.core.util.LogHelper.debug;
import com.cgi.pacoshop.facades.checkout.cart.PacoCartFacade;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkCallbackFacade;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkFacade;
import com.cgi.pacoshop.facades.user.PacoUserFacade;
import com.cgi.pacoshop.storefront.controllers.pages.AbstractPageController;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cgi.pacoshop.core.exceptions.deeplink.status400.DuplicateParametersInDeeplinkException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.InvalidKombiAboException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.NoProductsSpecifiedException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.PortalCallbackInvalidCartException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.PortalCallbackMissingMandatoryParamsException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.TooManyProductsInDeeplinkException;
import com.cgi.pacoshop.core.model.OrderDTO;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.CallbackRestService;
import com.cgi.pacoshop.core.service.CampaignIdService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.security.DeeplinkSecurityService;
import com.cgi.pacoshop.core.util.LogHelper;


/**
 * Creation of a shopping cart based on a deeplink into the checkout for one product.
 * 
 * 1. For a deeplink URL of format http://exampledomain.com/startcheckout?productIds=xy, a shopping cart containg a
 * product with code=xy is created. 2. The cart is created for the correct site (the site specified in the deeplink
 * through exampledomain.com). 3. In case the productID does not refer to a valid (=existing and active) product in the
 * hybris catalogue associated with the site, an error message is shown to the user. 3. In case there is an existing
 * user session already in the system, upon receiving a deeplink and creating the cart, the old cart is overridden.
 * 
 * 
 * @version 1.0v Dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 */
@Controller
@RequestMapping(method =
{ RequestMethod.GET })
public class DeeplinkController extends AbstractPageController
{

	private static final Logger LOG = Logger.getLogger(DeeplinkController.class);

	private static final String DYNAMIC_CHECKOUT_URL = "/dynamiccheckout";


	@Resource
	private DeeplinkFacade deeplinkFacade;
	@Resource
	private ModelService modelService;
	@Resource
	private DeeplinkSecurityService deeplinkSecurityService;
	@Resource
	private DeeplinkCallbackFacade deeplinkCallbackFacade;
	@Resource
	private PacoCartFacade           pacoCartFacade;
	@Resource
	private CallbackRestService      callbackRestService;
	@Resource
	private ShopConfigurationService shopConfigurationService;
	@Resource
	private CampaignIdService campaignIdService;
	@Resource
	private PacoUserFacade pacoUserFacade;


	/**
	 * Creation of a shopping cart based on a deeplink into the checkout for one product.
	 * 
	 * @param productId
	 *           the product id
	 * @param offerid
	 *           the external offer id
	 * @param offerorigin
	 *           the offer origin code
	 * @param productId2
	 *           the product id for second product
	 * @param offerid2
	 *           the external offer id for second product
	 * @param offerorigin2
	 *           the offer origin code for second product
	 * @param contentPlatformId
	 *           the content platform id
	 * @param contentId
	 *           the content id
	 * @param contentTitle
	 *           the content title
	 * @param productImageUrl
	 *           the product image url
	 * @param bonusImageUrl
	 *           the bonus image url
	 * @param redirectUrl
	 *           the redirect url
	 * @param campaignId
	 *           the campaign Id
	 * @param request
	 *           the HttpServletRequest
	 * @return the string
	 */
	@RequestMapping("/startcheckout")
	public String createShoppingCart(final @RequestParam(value = "productid", required = false) String productId,
												final @RequestParam(value = "offerid", required = false) String offerid,
												final @RequestParam(value = "offerorigin", required = false) String offerorigin,

												final @RequestParam(value = "productid2", required = false) String productId2,
												final @RequestParam(value = "offerid2", required = false) String offerid2,
												final @RequestParam(value = "offerorigin2", required = false) String offerorigin2,

												final @RequestParam(value = "contentplatformid", required = false) String contentPlatformId,
												final @RequestParam(value = "contentid", required = false) String contentId,
												@RequestParam(value = "contenttitle", required = false) String contentTitle,
												final @RequestParam(value = "productimageurl", required = false) String productImageUrl,
												final @RequestParam(value = "bonusimageurl", required = false) String bonusImageUrl,
												final @RequestParam(value = "redirecturl", required = false) String redirectUrl,
												final @RequestParam(value = "campaignid", required = false) String campaignId,
												final HttpServletRequest request)
	{
		pacoCartFacade.removeSessionCart();
		pacoUserFacade.removeAnonymousSubmittedAttribute();

		validateParametersDuplicates(request);
		final Boolean deeplinkSecurityEnable = shopConfigurationService.isDeeplinkSecurityFingerprintEnabled();
		if (deeplinkSecurityEnable)
		{
			deeplinkSecurityService.validate(request);
		}

		final List<ProductDTO> productDTOs = new ArrayList<>();

		productDTOs.add(createProductDTO(productId, offerid, offerorigin));

		if (isNotEmpty(productId2) || isNotEmpty(offerid2) || isNotEmpty(offerorigin2))
		{
			productDTOs.add(createProductDTO(productId2, offerid2, offerorigin2));
		}

		if (CollectionUtils.isEmpty(productDTOs))
		{
			throw new NoProductsSpecifiedException();
		}

		// contentTitle might be Base64 encoded
		// TODO Remove this again after the product review - portal has to use proper url encoding instead of this
		// See https://jira.symmetrics.de/browse/KS-2993
		if (contentTitle != null && contentTitle.endsWith("="))
		{
			contentTitle = decodeBase64String(contentTitle);
		}

		debug(LOG, "Incoming params - contentId : %s, contentPlatformId : %s, contentTitle : %s, "
				+ "bonusImageUrl: %s, productImageUrl: %s, redirectUrl: %s", contentId, contentPlatformId, contentTitle,
				bonusImageUrl, productImageUrl, redirectUrl);

		List<ProductModel> products;
		CartModel cartModel;

		//if there's more than two product ids in the deeplink - error
		if (productDTOs.size() > 2)
		{
			throw new TooManyProductsInDeeplinkException();
		}

		products = deeplinkFacade.findProductInOnlineCatalogOfCurrentWebsite(productDTOs);

		if (products.size() > 1 && !deeplinkFacade.isValidKombiAbo(products))
		{
			final ProductModel productModel1 = products.get(0);
			final ProductModel productModel2 = products.get(1);
			final String message = String.format(
					"Invalid kombi-abo offer: 2 subscription products are expected. Product1 %s - %s, productid2 %s - %s ",
					productModel1.getCode(), productModel1.getProductType().getCode(), productModel2.getCode(), productModel2
							.getProductType().getCode());
			throw new InvalidKombiAboException(message);
		}
		//If product does exist, add it to the cart and forward the request to the checkout
		//will throw corresponding instance of PacoShopWebException in case of errors
		cartModel = deeplinkFacade.createCartWithProducts(products, redirectUrl, contentId, contentPlatformId, contentTitle,
				bonusImageUrl, productImageUrl);

		debug(LOG, "product id: %s added to cart %s", productDTOs, cartModel.getCode());
		modelService.save(cartModel);

		campaignIdService.save(campaignId);

		LogHelper.info(LOG, "forwarding to dynamic checkout");
		return FORWARD_PREFIX + DYNAMIC_CHECKOUT_URL;
	}


	private static String decodeBase64String(final String encoded)
	{
		// TODO Remove this method again when not needed anymore
		final byte[] decodedBytes = Base64.decode(encoded);
		try
		{
			return new String(decodedBytes, "UTF-8");
		}
		catch (final UnsupportedEncodingException e)
		{
			error(LOG, "Could not decode Base64 string [%s]", e, encoded);
			return null;
		}
	}


	private static void validateParametersDuplicates(final HttpServletRequest request)
	{
		final Set<String> parametersNames = request.getParameterMap().keySet();
		final List<String> duplicatedParamsList = new ArrayList<>();
		for (final String parameterName : parametersNames)
		{
			final String[] parameterValues = request.getParameterValues(parameterName);
			if (parameterValues != null && parameterValues.length > 1)
			{
				duplicatedParamsList.add(parameterName);
			}
		}
		if (duplicatedParamsList.size() > 0)
		{
			error(LOG, "Duplicate parameters: {%s}", duplicatedParamsList);
			throw new DuplicateParametersInDeeplinkException(duplicatedParamsList.toString());
		}
	}

	private static ProductDTO createProductDTO(final String productId, final String externalOfferId, final String offerOriginCode)
	{
		final ProductDTO productDTO = new ProductDTO();

		checkParamsAndSetUseId(productDTO, productId, offerOriginCode, externalOfferId);

		productDTO.setProductId(productId);
		productDTO.setExternalOfferId(externalOfferId);
		productDTO.setOfferOriginCode(offerOriginCode);

		return productDTO;
	}


	/**
	 * Creation of a shopping cart based on a deeplink into the checkout for one or butch of the product.
	 * 
	 * @param cartId
	 *           String cartId for callback.
	 * @param portalId
	 *           portalId
	 * @return String the String
	 */
	@RequestMapping("/callbackcheckout")
	public String createShoppingCart(final @RequestParam(value = "cartid", required = false) String cartId,
			final @RequestParam(value = "portalid", required = false) String portalId)
	{
		pacoCartFacade.removeSessionCart();
		pacoUserFacade.removeAnonymousSubmittedAttribute();

		if (StringUtils.isEmpty(cartId) || StringUtils.isEmpty(portalId))
		{
			final String message = String.format("Missing mandatory params for callback cartid = %s, portalid = %s", cartId,
					portalId);
			throw new PortalCallbackMissingMandatoryParamsException(message);
		}

		final String portalUrl = deeplinkCallbackFacade.getPortalUrl(portalId);
		final OrderDTO orderDTO = callbackRestService.getOrderDTO(portalUrl, cartId);

		if (isOrderNullOrEmpty(orderDTO))
		{
			final String message = String.format("Callback with cartid = %s, portalid = %s returned an undefined or empty cart",
					cartId, portalId);
			throw new PortalCallbackInvalidCartException(message);
		}

		checkOrderDTO(orderDTO);

		//retrieves productModels of product based on OrderDTO
		final List<ProductModel> productModels = deeplinkCallbackFacade.getProductModels(orderDTO);
		//validate mandatory date: dates, price, status

		//If products do exist, add it to the cart and forward the request to the checkout
		final CartModel cartModel = deeplinkCallbackFacade.createCartWithProducts(productModels, orderDTO);

		modelService.save(cartModel);

		deeplinkCallbackFacade.calculateCart(cartModel);


		return FORWARD_PREFIX + DYNAMIC_CHECKOUT_URL;
	}

	private static void checkOrderDTO(final OrderDTO orderDTO)
	{
		for (final ProductDTO product : orderDTO.getCartentries())
		{
			final String productId = product.getProductId();
			final String offerOrigin = product.getOfferOriginCode();
			final String offerId = product.getExternalOfferId();

			checkParamsAndSetUseId(product, productId, offerOrigin, offerId);
		}
	}


	private static void checkParamsAndSetUseId(final ProductDTO product, final String productId, final String offerOrigin,
			final String offerId)
	{
		if (isNotEmpty(productId) && isEmpty(offerId) && isEmpty(offerOrigin))
		{
			product.setUseId(true);
		}
		else if (isEmpty(productId) && isNotEmpty(offerId) && isNotEmpty(offerOrigin))
		{
			product.setUseId(false);
		}
		else
		{
			final String message = String.format("No products specified. "
					+ "Should be (productId = %s ) OR (offerOrigin = %s, offerId = %s)", productId, offerOrigin, offerId);
			throw new NoProductsSpecifiedException(message);
		}
	}

	private static boolean isOrderNullOrEmpty(final OrderDTO orderDTO)
	{
		return (orderDTO == null || CollectionUtils.isEmpty(orderDTO.getCartentries()));
	}

}
