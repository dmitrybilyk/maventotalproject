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
package com.cgi.pacoshop.facades.checkout.deeplink.impl;


import com.cgi.pacoshop.core.checkout.dynamic.ProductDTOPopulationUtility;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import com.cgi.pacoshop.core.exceptions.deeplink.status400.MissingMandatoryParamsForOnlineArticleException;
import com.cgi.pacoshop.core.exceptions.deeplink.status404.ProductNotExistsInOnlineCatalogException;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.PacoshopProductService;
import com.cgi.pacoshop.core.service.PacoshopSubscriptionCommerceCartService;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkFacade;
import com.cgi.pacoshop.facades.checkout.deeplink.mandatoryfieldsvalidation.ProductMandatoryFieldsValidationFacade;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


//import de.hybris.platform.product.ProductService;

/**
 * Facade implementation to provide the logic for the Deeplink controller.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Dec 19, 2013
 * @module hybris
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see  "https://wiki.hybris.com/"
 */
public class DefaultDeeplinkFacade implements DeeplinkFacade
{

	private static final String ONLINE_CATALOG_VERSION = "online";
	private static final Logger LOG                    = Logger.getLogger(DefaultDeeplinkFacade.class);

	@Resource
	private CMSSiteService                          cmsSiteService;
	@Resource
	private PacoshopProductService                  pacoshopProductService;
	@Resource
	private CartService                             cartService;
	@Resource
	private CalculationService                      calculationService;
	@Resource
	private ConfigurationService                    configurationService;
	@Resource
	private ProductMandatoryFieldsValidationFacade  productMandatoryFieldsValidationFacade;
	@Resource
	private PacoshopSubscriptionCommerceCartService pacoshopSubscriptionCommerceCartService;


	/**
	 * For a valid "Kombinangebot", all products should be subscription.
	 *
	 * @param products the products
	 * @return true/false
	 */
	public boolean isValidKombiAbo(final List<? extends ProductModel> products)
	{
		return ProductDTOPopulationUtility.isValidKombiAbo(products);
	}
	/**
	 * Fill the dto to display products in login, summary and thankyou pages.
	 * @param cartContentsFragmentDTO the cart contents fragment dTO
	 */

	/**
	 * Validate mandatory fields in products from the deeplink. The product is INVALID when: it does not exist in the
	 * hybris catalogue belonging to the site it is not active it should not be sold online yet ("onlineDate" <= current
	 * date <= "offlineDate") when bought with a prohibited amount ("minimumQuantity" <= number of product instances in
	 * the cart <= "maximumQuantity"). if the subscription price plan is missing
	 * if a one of products is invalid - display error page with 404
	 * Will put every error to log and throw the first one of the first product
	 *
	 * in case of validation errors one a PacoShopWebException will be thrown
	 *
	 * @param productDTOList
	 *           products from the deeplink.
	 * @return true if all products are valid
	 */
	@Override
	public List<ProductModel> findProductInOnlineCatalogOfCurrentWebsite(final List<ProductDTO> productDTOList)
	{
		//Determine product catalog of current website
		final CMSSiteModel currentSite = cmsSiteService.getCurrentSite();
		LOG.debug(String.format("current site redirect url: %s", currentSite.getSiteOwnerValue()));
		final CatalogModel defaultCatalog = currentSite.getDefaultCatalog();
		LOG.debug(String.format("default catalog id: %s", defaultCatalog.getId()));
		// Find product in Online version of that catalog
		final List<ProductModel> products = new ArrayList<>();
		final Map<String, List<PacoShopWebException>> nestedExceptions = new HashMap<>();
		for (final CatalogVersionModel versionModel : defaultCatalog.getCatalogVersions())
		{
			if (versionModel.getVersion().equalsIgnoreCase(ONLINE_CATALOG_VERSION))
			{
				PacoShopWebException actualException = null;
				for (final ProductDTO product : productDTOList)
				{
					ProductModel productModel = null;
					String productId = null;
					try
					{
						productModel = findProduct(versionModel, product);
						productId = productModel.getCode();
						final List<PacoShopWebException> exceptions = productMandatoryFieldsValidationFacade
								.validateProduct(productModel, product);
						if (exceptions.size() > 0)
						{
							nestedExceptions.put(productId, exceptions);
						}
						if (!CollectionUtils.isEmpty(exceptions))
						{
							for (final Iterator<PacoShopWebException> iterator = exceptions.iterator(); iterator.hasNext();)
							{
								final PacoShopWebException next = iterator.next();
								if (actualException == null)
								{
									actualException = next;
								}
								error(LOG, String.format("Product %s", productModel.getCode()), next);
							}
						}
					}
					catch (final RuntimeException e)
					{
						if (productId == null && product != null)
						{
							productId = product.getProductId();
						}
						final ProductNotExistsInOnlineCatalogException exception = new ProductNotExistsInOnlineCatalogException(
								productId);
                        // this is ugly that exception.getMessage() returns localization key without any detailed information
                        // but if done in other way, it would require reworking the whole exception handing
                        // and ruin KS-305 implementation...
                        exception.setAdditionalInformation("Product not found: productid = " + productId);

						if (actualException == null)
						{
							actualException = exception;
						}
						if (nestedExceptions.containsKey(productId))
						{
							nestedExceptions.get(productId).add(exception);
						}
						else
						{
							final ArrayList<PacoShopWebException> exceptions = new ArrayList<>();
							exceptions.add(exception);
							nestedExceptions.put(productId, exceptions);
                            // this is ugly that exception.getMessage() returns localization key without any detailed information
                            // but if done in other way, it would require reworking the whole exception handing
                            // and ruin KS-305 implementation...
                            error(LOG, exception.getAdditionalInformation(), exception);
						}

					}
					if (!products.contains(productModel))
					{
						products.add(productModel);
					}
				}
				if (actualException != null)
				{
					actualException.setNestedExceptions(nestedExceptions);
					throw actualException;
				}
			}

		}
		return products;
	}

	private ProductModel findProduct(final CatalogVersionModel versionModel, final ProductDTO productDTO)
	{
		return (productDTO.getUseId()) ? findByCode(versionModel, productDTO) : findByOffer(versionModel, productDTO);
	}

	private ProductModel findByOffer(final CatalogVersionModel versionModel, final ProductDTO productDTO)
	{
		return pacoshopProductService
				  .findProductsByOffer(versionModel, productDTO.getExternalOfferId(),
											  OfferOrigin.valueOf(productDTO.getOfferOriginCode()));
	}

	private ProductModel findByCode(final CatalogVersionModel versionModel, final ProductDTO productDTO)
	{
		return pacoshopProductService.getProductForCode(versionModel, productDTO.getProductId());
	}


	/**
	 * Create a shopping cart associated with current session and put products into it. In case there is an existing user
	 * session already in the system, upon receiving a deeplink and creating the cart, the old cart is overridden.
	 *
	 * @param products
	 *           - products from the deeplink.
	 * @param redirectUrlFromDeepLink
	 *           - - redirectUrl from external system hybris
	 * @param contentId
	 *           - - contentId from external system
	 * @param contentPlatformId
	 *           - - contentPlatformId from external system
	 * @param contentTitle
	 *           - - contentTitle from external system
	 * @param bonusImageUrl
	 *           - - bonusImageUrl from external system
	 * @param productImageUrl
	 *           - - productImageUrl from external system
	 * @return - cart with products
	 */
	@Override
	public CartModel createCartWithProducts(final List<ProductModel> products, final String redirectUrlFromDeepLink,
			final String contentId, final String contentPlatformId, final String contentTitle, final String bonusImageUrl,
			final String productImageUrl)

	{
		//by this time is assumed that in case of 2 products and one of them is subscription one
		//the second one will also be a subscription one
		//## in case one of the products is a digital subscription (product type DIGITALABO),
		// the contentPlatformId parameter is specified in the deeplink
		final String redirectUrl = redirectUrlFromDeepLink;
		final String redirectUrlDesc = StringUtils.EMPTY;

		if (deepLinkContainsOnlineArticleProduct(products))
		{
			final String missingOnlineArticleParams = getMissingOnlineArticleParams(contentId, redirectUrl, contentPlatformId,
					contentTitle);
			if (StringUtils.isNotEmpty(missingOnlineArticleParams))
			{
				throw new MissingMandatoryParamsForOnlineArticleException(missingOnlineArticleParams);
			}
		}

		//If there is something in the cart, remove that first
        cartService.removeSessionCart();

		//Get the session's cart
		final CartModel cart = cartService.getSessionCart();

		// Add products to cart
		getPacoshopSubscriptionCommerceCartService().addProductsToCart(cart, products,
																							redirectUrl, redirectUrlDesc, contentId,
																							contentPlatformId, contentTitle,
																							bonusImageUrl, productImageUrl);

		/* Set DeliveryNow for Abo prudcts to true by default */
		if (deepLinkContainsAboProduct(products))
		{
			cart.setDeliveryNow(Boolean.TRUE);
		}

		//If cart is not automatically calculated, explicitly trigger re-calculation of cart
		try
		{
			calculateCart(cart);
		}
		catch (final CalculationException e)
		{
			LOG.error("Error occurred while re-calculating the cart", e);
		}
		return cart;
	}

	private String getMissingOnlineArticleParams(final String contentId, final String redirectUrl, final String contentPlatformId,
																final String contentTitle)
	{
		List<String> res = new ArrayList<String>();
		if (StringUtils.isEmpty(contentId))
		{
			res.add("contentid");
		}
		if (StringUtils.isEmpty(redirectUrl))
		{
			res.add("redirecturl");
		}
		if (StringUtils.isEmpty(contentTitle))
		{
			res.add("contenttitle");
		}

		if (StringUtils.isEmpty(contentPlatformId))
		{
			res.add("contentplatformid");
		}
		return res.size() > 0 ? res.toString() : "";
	}

	private void calculateCart(final CartModel cart) throws CalculationException
	{
		calculationService.calculate(cart);
	}

	private boolean deepLinkContainsOnlineArticleProduct(final List<? extends ProductModel> products)
	{
		for (final ProductModel product : products)
		{
			final String productType = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);
			if (product.getProductType() == null || product.getProductType().getCode() == null)
			{
				continue;
			}
			if (product.getProductType().getCode().equalsIgnoreCase(productType))
			{
				return true;
			}
		}
		return false;
	}

	private boolean deepLinkContainsAboProduct(final List<? extends ProductModel> products)
	{
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		for (final ProductModel product : products)
		{
			final String productTypeCode = product.getProductType().getCode();
			if (productTypeCode.equals(printAbo)
					  || productTypeCode.equals(digitalAbo))
			{
				return true;
			}
		}
		return false;
	}

	private String getConfigurationProperty(final String key)
	{
		final Configuration configuration = configurationService.getConfiguration();
		return configuration.getString(key);
	}

	/**
	 * Gets pacoshop subscription commerce cart service.
	 *
	 * @return the pacoshopSubscriptionCommerceCartService
	 */
	public PacoshopSubscriptionCommerceCartService getPacoshopSubscriptionCommerceCartService()
	{
		return pacoshopSubscriptionCommerceCartService;
	}

	/**
	 * Sets pacoshop subscription commerce cart service.
	 *
	 * @param pacoshopSubscriptionCommerceCartService the pacoshopSubscriptionCommerceCartService to set
	 */
	public void setPacoshopSubscriptionCommerceCartService(
			  final PacoshopSubscriptionCommerceCartService pacoshopSubscriptionCommerceCartService)
	{
		this.pacoshopSubscriptionCommerceCartService = pacoshopSubscriptionCommerceCartService;
	}


	/**
	 * Sets cms site service.
	 *
	 * @param cmsSiteService the CMSSiteService.
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * Gets cms site service.
	 *
	 * @return the cmsSiteService.
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}
}
