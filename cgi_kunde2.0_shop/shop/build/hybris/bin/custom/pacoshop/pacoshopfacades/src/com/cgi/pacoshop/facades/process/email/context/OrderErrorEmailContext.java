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
package com.cgi.pacoshop.facades.process.email.context;


import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.model.ProcessTaskLogModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Required;

/**
 * This class used like context for showing data in email
 * this context for order error email
 *
 * @module build
 * @version 1.0v Mar 24, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OrderErrorEmailContext extends AbstractEmailContext<OrderProcessModel>
{

	private Converter<OrderModel, OrderData> orderConverter;
	private OrderData                        orderData;

	private String                           stepName;
	private String                           errorMessage;

	@Override
	public void init(final OrderProcessModel orderProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(orderProcessModel, emailPageModel);
		orderData = getOrderConverter().convert(orderProcessModel.getOrder());
		stepName = getName(orderProcessModel);
		errorMessage = orderProcessModel.getEndMessage();
	}

	@Override
	protected BaseSiteModel getSite(final OrderProcessModel orderProcessModel)
	{
		return orderProcessModel.getOrder().getSite();
	}

	@Override
	protected CustomerModel getCustomer(final OrderProcessModel orderProcessModel)
	{
		return (CustomerModel) orderProcessModel.getOrder().getUser();
	}

	/**
	 * get EmailLanguage.
	 * @param orderProcessModel like orderProcessModel.
	 * @return LanguageModel like LanguageModel.
	 */
	@Override
	protected LanguageModel getEmailLanguage(final OrderProcessModel orderProcessModel)
	{
		return orderProcessModel.getOrder().getLanguage();
	}

	/**
	 * get name.
	 * @param orderProcessModel like orderProcessModel.
	 * @return name.
	 */
	private String getName(final OrderProcessModel orderProcessModel)
	{
		String result = "";
		for (ProcessTaskLogModel log : orderProcessModel.getTaskLogs())
		{
			result = log.getActionId();
		}
		return result;
	}

	/**
	 * get stepName.
	 * @return stepName like stepName.
	 */
	public String getStepName()
	{
		return stepName;
	}

	/**
	 * set stepName.
	 * @param stepName like stepName.
	 */
	public void setStepName(final String stepName)
	{
		this.stepName = stepName;
	}

	/**
	 * get errorMessage.
	 * @return errorMessage like errorMessage.
	 */
	public String getErrorMessage()
	{
		return errorMessage;
	}

	/**
	 * set errorMessage.
	 * @param errorMessage like errorMessage.
	 */
	public void setErrorMessage(final String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	/**
	 * set orderConverter.
	 * @param orderConverter like orderConverter.
	 */
	@Required
	public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	/**
	 * get OrderConverter.
	 * @return Converter<OrderModel, OrderData> .
	 */
	protected Converter<OrderModel, OrderData> getOrderConverter()
	{
		return orderConverter;
	}

	/**
	 * get OrderData.
	 * @return OrderData like OrderData.
	 */
	public OrderData getOrder()
	{
		return orderData;
	}

}
