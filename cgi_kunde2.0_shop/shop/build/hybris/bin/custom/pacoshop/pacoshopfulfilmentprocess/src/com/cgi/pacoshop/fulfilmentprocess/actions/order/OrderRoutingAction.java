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

import com.cgi.pacoshop.fulfilmentprocess.service.OrderRoutingService;

import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.task.RetryLaterException;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;


/**
 * This action defined where the order needs to be processed.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class OrderRoutingAction extends AbstractAction<OrderProcessModel>
{
    private static final String TRANSITION_NOK = "NOK";

    private static final Logger LOGGER = Logger.getLogger(OrderRoutingAction.class);


    @Resource
    private OrderRoutingService orderRoutingService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.processengine.spring.Action#execute(de.hybris.platform.processengine.model.BusinessProcessModel
     * )
     */
    @Override
    public String execute(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {

        if (LOGGER.isInfoEnabled())
        {
            LOGGER.info("Process: " + orderProcessModel.getCode() + " in step " + getClass());
        }

        final OrderFulfillmentType orderFulfillmentType = orderRoutingService.analyzeOrder(orderProcessModel.getOrder());
        if (orderFulfillmentType == null)
        {
			   orderProcessModel.setEndMessage(" Business process step: OrderRouting \n"
													  + " Error Message: Error in product import source, exiting the process");
			   modelService.save(orderProcessModel);
            return TRANSITION_NOK;
        }
        else
        {
            return orderFulfillmentType.getValue();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.hybris.platform.processengine.spring.Action#getTransitions()
     */
    @Override
    public Set<String> getTransitions()
    {
        final Set<String> transitions = new HashSet<String>();
        transitions.add(OrderFulfillmentType.TRANSITION_EMAIL.getValue());
        transitions.add(TRANSITION_NOK);
        transitions.add(OrderFulfillmentType.TRANSITION_SAPMSD.getValue());
        transitions.add(OrderFulfillmentType.TRANSITION_SAPSD.getValue());
        return transitions;
    }

    /**
     * @param orderRoutingService
     *            the orderRoutingService to set
     */
    public void setOrderRoutingService(final OrderRoutingService orderRoutingService)
    {
        this.orderRoutingService = orderRoutingService;
    }

}
