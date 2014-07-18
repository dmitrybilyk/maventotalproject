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
package com.cgi.pacoshop.core.service.productdtopopulation.impl;


import com.cgi.pacoshop.core.checkout.dynamic.ProductDTOPopulationUtility;
import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.CashBonusModel;
import com.cgi.pacoshop.core.model.MilesAndMoreBonusModel;
import com.cgi.pacoshop.core.model.ProductBonusModel;
import com.cgi.pacoshop.core.model.ProductDTO;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import static org.apache.commons.lang.StringUtils.isEmpty;


/**
 * Populate bonus dto for subscription products.
 *
 * @module hybris
 * @version 1.0v Mar 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class BonusProductDTOPopulationService extends SubscriptionProductDTOPopulationService
{
	private static final String EMPTY_STRING = "";

	@Resource
	private I18NService i18NService;

	@Override
	public void populate(final AbstractOrderEntryModel orderEntryModel, final ProductDTO productDTO) throws ConversionException
	{
		debug(getLog(), String.format("Creating productdto from bonus id: %s", orderEntryModel.getCode()));
		List<ProductDTO> bonuses = new ArrayList<>();
		ProductModel product = orderEntryModel.getProduct();
		Boolean isImagePopulated = false;
		Boolean isTitlePopulated = false;
		if (product instanceof SubscriptionProductModel)
		{
			SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) product;
			for (BonusModel bonusModel : subscriptionProductModel.getBonuses())
			{
				ProductDTO bonusDTO = new ProductDTO();
				//the bonus picture (if available- as contained in the cart [source was the deeplink (bonusImageUrl)]
				//or, if not present in the cart, from the catalogue [media], if not present there either, do not display)
				String imageURL = EMPTY_STRING;
				String deeplinkBonusImage = orderEntryModel.getBonusImageUrl();
				final Locale locale = i18NService.getCurrentLocale();
				final String description = orderEntryModel.getProduct().getDescription(locale);
				bonusDTO.setDescription((description != null) ? description : EMPTY_STRING);
				//the bonus title from the catalogue (bonusDescription in case of CashBonus and MilesAndMoreBonus,
				// in case of a product bonus: productDescription and if not present, bonusDescription)
				String bonusTitle = EMPTY_STRING;
				if (isCashOrMilesAndMore(bonusModel))
				{
					if (!isImagePopulated)
					{
						imageURL = populateBonusImage(orderEntryModel, deeplinkBonusImage);
						isImagePopulated = true;
					}

					if (!isTitlePopulated)
					{
						bonusTitle = subscriptionProductModel.getBonusDescription();
						isTitlePopulated = true;
					}
				}
				else
				{
					String title = ((ProductBonusModel) bonusModel).getProductDescription();
					bonusTitle =
							  isEmpty(title) ? subscriptionProductModel.getBonusDescription() : title;
					if (!isImagePopulated)
					{
						imageURL = populateBonusImage(orderEntryModel, deeplinkBonusImage);
						isImagePopulated = true;
					}

					ProductBonusModel productBonusModel = (ProductBonusModel) bonusModel;
					//in case of a productBonus with extra payment: the subline "mit Zuzahlung"
					// and the additionalPayment from the priceRow in the catalogue
					PriceRowModel additionalPayment = productBonusModel.getAdditionalPayment();
					if (additionalPayment != null)
					{
						bonusDTO.setAdditionalPayment(additionalPayment.getPrice());
						bonusDTO.setCurrency(additionalPayment.getCurrency().getSymbol());
					}
				}
				bonusDTO.setName(bonusTitle);
				bonusDTO.setUrl(imageURL);
				bonuses.add(bonusDTO);
			}
			productDTO.setBonuses(bonuses);
		}
	}

	/**
	 * First take image from deeplink. If it's not defined takes from catalog.
	 * @param orderEntryModel
	 * @param deeplinkBonusImage
	 * @return imageURL - bonus image.
	 */
	private String populateBonusImage(final AbstractOrderEntryModel orderEntryModel, final String deeplinkBonusImage)
	{
		final String imageURL;
		if (deeplinkBonusImage == null)
		{
			imageURL = ProductDTOPopulationUtility.getImageUrl(orderEntryModel, ProductDTOPopulationUtility.MODE.BONUS);
		}
		else if (deeplinkBonusImage.equals(EMPTY_STRING))
		{
			imageURL = EMPTY_STRING;
		}
		else
		{
			imageURL = deeplinkBonusImage;
		}
		return imageURL;
	}

	private boolean isCashOrMilesAndMore(final BonusModel bonusModel)
	{
		return bonusModel instanceof CashBonusModel || bonusModel instanceof MilesAndMoreBonusModel;
	}
}
