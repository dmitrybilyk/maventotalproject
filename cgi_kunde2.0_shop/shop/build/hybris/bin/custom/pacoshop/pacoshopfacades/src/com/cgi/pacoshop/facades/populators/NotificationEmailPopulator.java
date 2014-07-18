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
package com.cgi.pacoshop.facades.populators;


import de.hybris.platform.servicelayer.i18n.I18NService;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.util.Locale;
import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.productdtopopulation.ProductDTOPopulationServiceLocator;
import com.cgi.pacoshop.facades.constants.PacoshopFacadesConstants;
import com.cgi.pacoshop.facades.process.email.context.OrderNotificationEmailContext;


/**
 * Populator for Notification Email context.
 * 
 * @module shop
 * @version 1.0v Apr 18, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class NotificationEmailPopulator implements Populator<OrderModel, OrderNotificationEmailContext>
{

	private static final String DATE_FORMAT_FOR_SHOWING = "email.confirmation.date.format.delivery";

	@Resource
	private ProductDTOPopulationServiceLocator<AbstractOrderEntryModel> productDTOPopulationServiceLocator;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private I18NService i18NService;

	/**
	 * Populate the target instance with values from the source instance.
	 *
	 *
	 *
	 * @param orderModel
	 *           the source object
	 * @param orderNotificationEmailContext
	 *           the target to fill
	 * @throws de.hybris.platform.servicelayer.dto.converter.ConversionException
	 *            if an error occurs
	 */
	@Override
	public void populate(final OrderModel orderModel, final OrderNotificationEmailContext orderNotificationEmailContext)
			  throws ConversionException
	{
		validateParameterNotNull(orderModel, "Parameter orderModel must not be null");
		validateParameterNotNull(orderNotificationEmailContext, "Parameter orderNotificationEmailContext must not be null");

		final AddressModel customerAddress = orderModel.getCustomerAddress();
		final AddressModel deliveryAddress = getDeliveryAddress(orderModel);

		if (customerAddress != null)
		{
			if (customerAddress.getTitle() != null)
			{
				orderNotificationEmailContext.setTitle(customerAddress.getTitle().getName());
			}
			orderNotificationEmailContext.setLastName(customerAddress.getLastname());
		}
		orderNotificationEmailContext.setStartAboDelivery(getDeliveryStartDate(orderModel));
		orderNotificationEmailContext.setDeliveryAddress(deliveryAddress);

		orderNotificationEmailContext.setHasPhysicalProduct(hasPhysicalProduct(orderModel));
		orderNotificationEmailContext.setHasToBeDeliveredProduct(hasToBeDeliveredProduct(orderModel));
		orderNotificationEmailContext.setHasDigitalProduct(hasDigitalProduct(orderModel));

		final List<ProductDTO> productDTOs = getProducts(orderModel);
		orderNotificationEmailContext.setOrderCode(orderModel.getCode());
		orderNotificationEmailContext.setProductDTOs(productDTOs);
	}

	private boolean hasDigitalProduct(final OrderModel order)
	{
		for (final AbstractOrderEntryModel orderEntryModel : order.getEntries())
		{
			if (orderEntryModel.getProduct().getProductType().getCode()
					  .equals(getProperty(PacoshopFacadesConstants.ProductTypes.DIGITAL_ABO))
					  || orderEntryModel.getProduct().getProductType().getCode()
							.equals(getProperty(PacoshopFacadesConstants.ProductTypes.NEWS_LETTER))
					|| orderEntryModel.getProduct().getProductType().getCode()
							.equals(getProperty(PacoshopFacadesConstants.ProductTypes.ONLINE_ARTICLE))
					|| orderEntryModel.getProduct().getProductType().getCode()
							.equals(getProperty(PacoshopFacadesConstants.ProductTypes.DOWNLOAD)))
			{
				return true;
			}
		}
		return false;
	}

    private boolean hasToBeDeliveredProduct(final OrderModel order)
    {
        for (final AbstractOrderEntryModel orderEntryModel : order.getEntries())
        {
            String code = orderEntryModel.getProduct().getProductType().getCode();
            if (code.equals(getProperty(PacoshopFacadesConstants.ProductTypes.DIGITAL_ABO))
               || code.equals(getProperty(PacoshopFacadesConstants.ProductTypes.GOOD))
               || code.equals(getProperty(PacoshopFacadesConstants.ProductTypes.PRINT_ABO)))
            {
                return true;
            }
        }
        return false;
    }

	private boolean hasPhysicalProduct(final OrderModel order)
	{
		for (final AbstractOrderEntryModel orderEntryModel : order.getEntries())
		{
			if (orderEntryModel.getProduct().getProductType().getCode()
					.equals(getProperty(PacoshopFacadesConstants.ProductTypes.GOOD))
					|| orderEntryModel.getProduct().getProductType().getCode()
							.equals(getProperty(PacoshopFacadesConstants.ProductTypes.PRINT_ABO)))
			{
				return true;
			}
		}
		return false;
	}

	private AddressModel getDeliveryAddress(final OrderModel orderModel)
	{
		final AddressModel customerAddress = orderModel.getCustomerAddress();
		final AddressModel result = orderModel.getDeliveryAddress();
		if (result == null)
		{
			return customerAddress;
		}

		final String email = result.getEmail();
		if (StringUtils.isEmpty(email))
		{
			result.setEmail(customerAddress.getEmail());
		}
		return result;
	}

	private String getDeliveryStartDate(final OrderModel orderModel)
	{
		String result = "";
		final Date date = orderModel.getDeliveryStartDate();
		if (date != null)
		{
			final Locale locale = i18NService.getCurrentLocale();
			final String dateFormat = getProperty(DATE_FORMAT_FOR_SHOWING);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, locale);
			result = simpleDateFormat.format(date);
		}
		return result;
	}

	private List<ProductDTO> getProducts(final OrderModel orderModel)
	{
		List<ProductDTO> productDTOs = Collections.EMPTY_LIST;
		final List<AbstractOrderEntryModel> entries = orderModel.getEntries();

		if (!entries.isEmpty())
		{
			productDTOs = productDTOPopulationServiceLocator.populate(entries);
		}

		return productDTOs;
	}

	/**
	 * 
	 * @param key
	 *           the property key.
	 * @return string value.
	 */
	private String getProperty(final String key)
	{
		final Configuration configuration = shopConfigurationService.getServiceConfiguration();
		return configuration.getString(key);
	}

}
