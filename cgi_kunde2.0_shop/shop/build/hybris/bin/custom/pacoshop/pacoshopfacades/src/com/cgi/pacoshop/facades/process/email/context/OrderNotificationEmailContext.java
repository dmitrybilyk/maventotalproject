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
package com.cgi.pacoshop.facades.process.email.context;

import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.facades.populators.NotificationEmailPopulator;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Required;


/**
 * Velocity context for a order notification email.
 *
 * @module shop
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OrderNotificationEmailContext extends AbstractEmailContext<OrderProcessModel>
{
	private Converter<OrderModel, OrderData> orderConverter;
	private String                           title;
	private String                           lastName;
	private String                           startAboDelivery;
	private String                           orderCode;
	private AddressModel                     deliveryAddress;
	private boolean                          hasPhysicalProduct;
	private boolean                          hasToBeDeliveredProduct;
	private boolean                          hasDigitalProduct;
	private List<ProductDTO>                 productDTOs;

	@Resource
	private NotificationEmailPopulator notificationEmailPopulator;

	/**
	 * Init and populates data.
	 * @param orderProcessModel the orderProcessModel.
	 * @param emailPageModel the emailPageModel.
	 */
	@Override
	public void init(final OrderProcessModel orderProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(orderProcessModel, emailPageModel);
		notificationEmailPopulator.populate(orderProcessModel.getOrder(), this);
	}


	/**
	 * Get Site.
	 * @param orderProcessModel the orderProcessModel.
	 * @return the BaseSiteModel.
	 */
	@Override
	protected BaseSiteModel getSite(final OrderProcessModel orderProcessModel)
	{
		return orderProcessModel.getOrder().getSite();
	}

	/**
	 *
	 * @param orderProcessModel the orderProcessModel.
	 * @return the CustomerModel.
	 */
	@Override
	protected CustomerModel getCustomer(final OrderProcessModel orderProcessModel)
	{
		return (CustomerModel) orderProcessModel.getOrder().getUser();
	}

	/**
	 *
	 * @return the orderConverter.
	 */
	protected Converter<OrderModel, OrderData> getOrderConverter()
	{
		return orderConverter;
	}

	/**
	 *
	 * @param orderConverter the orderConverter.
	 */
	@Required
	public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	/**
	 *
	 * @param orderProcessModel the orderProcessModel.
	 * @return the LanguageModel.
	 */
	@Override
	protected LanguageModel getEmailLanguage(final OrderProcessModel orderProcessModel)
	{
		return orderProcessModel.getOrder().getLanguage();
	}

	/**
	 *
	 * @return the title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 *
	 * @param title the title.
	 */
	public void setTitle(final String title)
	{
		this.title = title;
	}

	/**
	 *
	 * @return the lastName.
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 *
	 * @param lastName the name.
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 *
	 * @return the startAboDelivery.
	 */
	public String getStartAboDelivery()
	{
		return startAboDelivery;
	}

	/**
	 *
	 * @param startAboDelivery the startAboDelivery.
	 */
	public void setStartAboDelivery(final String startAboDelivery)
	{
		this.startAboDelivery = startAboDelivery;
	}

	/**
	 *
	 * @return the deliveryAddress.
	 */
	public AddressModel getDeliveryAddress()
	{
		return deliveryAddress;
	}

	/**
	 *
	 * @param deliveryAddress the deliveryAddress.
	 */
	public void setDeliveryAddress(final AddressModel deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 *
	 * @return productDTOs list.
	 */
	public List<ProductDTO> getProductDTOs()
	{
		return productDTOs;
	}

    /**
     *
     * @return hasToBeDeliveredProduct
     */
    public boolean getHasToBeDeliveredProduct()
    {
        return hasToBeDeliveredProduct;
    }

    /**
     *
     * @param hasToBeDeliveredProduct this product should be physically delivered to customer shipment address.
     */
    public void setHasToBeDeliveredProduct(final boolean hasToBeDeliveredProduct)
    {
        this.hasToBeDeliveredProduct = hasToBeDeliveredProduct;
    }

    /**
	 *
	 * @param productDTOs the list
	 */
	public void setProductDTOs(final List<ProductDTO> productDTOs)
	{
		this.productDTOs = productDTOs;
	}

	/**
	 *
	 * @return the hasPhysicalProduct.
	 */
	public boolean getHasPhysicalProduct()
	{
		return hasPhysicalProduct;
	}

	/**
	 *
	 * @param hasPhysicalProduct the hasPhysicalProduct.
	 */
	public void setHasPhysicalProduct(final boolean hasPhysicalProduct)
	{
		this.hasPhysicalProduct = hasPhysicalProduct;
	}

	/**
	 *
	 * @return the hasDigitalProduct.
	 */
	public boolean getHasDigitalProduct()
	{
		return hasDigitalProduct;
	}

	/**
	 *
	 * @param hasDigitalProduct the hasDigitalProduct.
	 */
	public void setHasDigitalProduct(final boolean hasDigitalProduct)
	{
		this.hasDigitalProduct = hasDigitalProduct;
	}

	/**
	 *
	 * @return the orderCode.
	 */
	public String getOrderCode()
	{
		return orderCode;
	}

	/**
	 *
	 * @param orderCode the orderCode.
	 */
	public void setOrderCode(final String orderCode)
	{
		this.orderCode = orderCode;
	}
}
