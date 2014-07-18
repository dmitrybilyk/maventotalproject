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
package com.cgi.pacoshop.fulfilmentprocess.actions.order;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.info;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;

import org.apache.log4j.Logger;


/**
 * This action is to check result of email fulfillment process.
 * 
 * @module pacoshopfulfillmentprocess
 * @version 1.0v 22.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class CheckEmailFulfillmentAction extends AbstractSimpleDecisionAction<OrderProcessModel>
{
    private static final Logger LOGGER = Logger.getLogger(CheckEmailFulfillmentAction.class);
    private ModelService modelService;

    @Override
    public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {
        info(LOGGER, "Process: " + orderProcessModel.getCode() + " in step " + getClass());

        final OrderStatus status = orderProcessModel.getOrder().getStatus();
        //TODO check if there are other status need to be checked.
        if (OrderStatus.ORDER_PROCESSING_FAILED.equals(status))
        {
            return Transition.NOK;
        }
        else
        {
            final OrderModel order = orderProcessModel.getOrder();
            order.setStatus(OrderStatus.COMPLETED);
            getModelService().save(order);
        }

        return Transition.OK;
    }

    /**
     * @return the modelService
     */
    @Override
    public ModelService getModelService()
    {
        return modelService;
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    @Override
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

}
