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
package com.cgi.pacoshop.core.checkout.dynamic;


import static com.cgi.pacoshop.core.util.LogHelper.*;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import static org.apache.commons.lang.StringUtils.EMPTY;
import static org.apache.commons.lang.StringUtils.isEmpty;
import static wiremock.org.apache.commons.lang.StringUtils.isNotEmpty;
import org.apache.log4j.Logger;

/**
 * Utility class
 *
 * @module hybris
 * @version 1.0v Mar 24, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */

public final class ProductDTOPopulationUtility
{

	private static final Logger LOG = Logger.getLogger(ProductDTOPopulationUtility.class);


	@Resource
	private ConfigurationService configurationService;

	private Configuration configuration;

	private ProductDTOPopulationUtility()
	{
	}

	/**
	 * For a valid "Kombinangebot", all products should be subscription.
	 *
	 *
	 * @param products the products
	 * @return true /false
	 */
	public static boolean isValidKombiAbo(final List<? extends ProductModel> products)
	{
		//remove duplicates
		final Set<? extends ProductModel> uniqueProducts = new HashSet<>(products);
		boolean allSubscriptionProducts = true;
		boolean atLeastOneSubscriptionProduct = false;
		for (final ProductModel product : uniqueProducts)
		{
			final boolean isSubscriptionProduct = product instanceof SubscriptionProductModel;
			info(LOG, String.format("product %s is a subscription product: %b", product.getCode(), isSubscriptionProduct));
			if (isSubscriptionProduct && !atLeastOneSubscriptionProduct)
			{
				atLeastOneSubscriptionProduct = true;
			}
			allSubscriptionProducts = allSubscriptionProducts && isSubscriptionProduct;
		}
		//if there'are 2 products, valid deeplink URL should contain 2 subscriptions products
		return allSubscriptionProducts && atLeastOneSubscriptionProduct;
	}


	/**
	 * Get the most current price row.
	 * @param orderEntryModel the order entry model
	 * @return latest price row
	 */
	public static Double getLatestPriceRow(final AbstractOrderEntryModel orderEntryModel)
	{
		PriceRowModel returnPriceRowModel = null;
		try
		{
			Date latestDt = null;
			Collection<PriceRowModel> europe1Prices = orderEntryModel.getProduct().getEurope1Prices();
			Iterator<PriceRowModel> priceRowModelIterator = europe1Prices.iterator();

			if (europe1Prices.size() > 1)
			{
				while (priceRowModelIterator.hasNext())
				{
					PriceRowModel currentPriceRowModel = priceRowModelIterator.next();
					if (latestDt == null)
					{
						latestDt = currentPriceRowModel.getModifiedtime();
					}
					if ((currentPriceRowModel.getModifiedtime().equals(latestDt)
							  || currentPriceRowModel.getModifiedtime().after(latestDt)))
					{
						returnPriceRowModel = currentPriceRowModel;
					}
				}
			}
			else
			{
				returnPriceRowModel = priceRowModelIterator.next();
			}
		}
		catch (Exception e)
		{
			error(LOG, String.format("exception getting product price. Reason: %s", e));
		}
		return returnPriceRowModel != null ? returnPriceRowModel.getPrice() : null;
	}

	/**
	 * the product picture (if available- as contained in the cart [source was the deeplink (productImageUrl)]
	 * or, if not present in the cart, from the catalogue [media],
	 * if not present there either, do not display).
	 *
	 * @param orderEntryModel the order entry model
	 * @param mode - the strategy where to get the picture from: product or bonus
	 * @return  - url.
	 */
	public static String getImageUrl(final AbstractOrderEntryModel orderEntryModel, final MODE mode)
	{
		String imageUrl = getImageURLFromDeeplink(orderEntryModel, mode);
		return isEmpty(imageUrl) ? getImageURLFromCatalogue(orderEntryModel, mode) : imageUrl;
	}

	/**
	 * @param orderEntryModel - product in the cart.
	 * @param mode
	 * @return * product image url.For now - the first one associated with the product
	 */
	private static String getImageURLFromCatalogue(final AbstractOrderEntryModel orderEntryModel,
																  final MODE mode)
	{
		return (mode == MODE.PRODUCT) ? geProductImageUrl(orderEntryModel)
				  : (mode == MODE.BONUS) ? getBonusImageUrl(orderEntryModel)
				  : "";
	}

	private static String getImageURLFromDeeplink(final AbstractOrderEntryModel orderEntryModel,
																 final MODE mode)
	{
		return  (mode == MODE.PRODUCT) ? orderEntryModel.getProductImageUrl()
				  : (mode == MODE.BONUS) ? orderEntryModel.getBonusImageUrl()
				  : "";
	}

	private static String geProductImageUrl(final AbstractOrderEntryModel orderEntryModel)
	{
		try
		{
			for (final MediaContainerModel galleryImage : orderEntryModel.getProduct().getGalleryImages())
			{
				final Collection<MediaModel> medias = galleryImage.getMedias();
				for (final MediaModel media : medias)
				{
					return media.getURL();
				}
			}
		}
		catch (Exception e)
		{
			warn(LOG, String.format("exception getting product image from catalogue. Reason: %s", e.getMessage()));
		}
		return "";
	}

	private static String getBonusImageUrl(final AbstractOrderEntryModel orderEntryModel)
	{
		if (orderEntryModel.getProduct() instanceof SubscriptionProductModel)
		{
			SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) orderEntryModel.getProduct();
			MediaModel bonusImage = subscriptionProductModel.getBonusImage();
			if (isNotNull(bonusImage))
			{
				return bonusImage.getURL();
			}
		}
		return "";
	}

	/**
	 * This method returns description for product.
	 * @param orderEntryModel the order entry model.
	 * @param locale the needed locale.
	 * @return product description.
	 */
	public static String getProductDescription(final AbstractOrderEntryModel orderEntryModel, final Locale locale)
	{
		String result = orderEntryModel.getSummary();
		if (isEmpty(result))
		{
			result = orderEntryModel.getProduct().getSummary(locale);
		}
		return result;
	}

	/**
	 * This method returns name for product.
	 * @param orderEntryModel the order entry model.
	 * @param locale the needed locale.
	 * @return product description.
	 */
	public static String getProductName(final AbstractOrderEntryModel orderEntryModel, final Locale locale)
	{
		String result = orderEntryModel.getContentTitle();
		if (StringUtils.isEmpty(result))
		{
			result = orderEntryModel.getProduct().getName(locale);
		}
		return result;
	}

	/**
	 * Getter for configuration properties.

	 * @param key - property name.
	 * @return  - property value in configuration config.
	 */
	public String getConfigurationProperty(final String key)
	{
		return getConfiguration().getString(key);
	}

	private Configuration getConfiguration()
	{
		if (configuration == null)
		{
			configuration = configurationService.getConfiguration();
		}
		return configuration;
	}


	private static boolean isNotNull(final Object o)
	{
		return o != null;
	}
	/**
	 * The enum MODE.
	 */
	public static enum MODE
	{
		/**
		 * The BONUS.
		 */
		BONUS,
		/**
		 * The PRODUCT.
		 */
		PRODUCT
	}

}
