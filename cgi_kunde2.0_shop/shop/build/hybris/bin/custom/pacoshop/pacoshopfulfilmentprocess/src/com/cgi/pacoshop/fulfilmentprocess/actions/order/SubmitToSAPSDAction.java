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

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;
import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.info;

import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.service.SAPService;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;


/**
 * Action that process an order via the SAP single order fulfillment.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubmitToSAPSDAction extends AbstractRetryableOrderModelAction
{

    private static final Logger LOGGER = Logger.getLogger(SubmitToSAPSDAction.class);

    @Resource
    private ModelService modelService;

    @Resource
    private SAPService sapService;

    /*
     * @see
     * de.hybris.platform.processengine.action.AbstractSimpleDecisionAction#executeAction(de.hybris.platform.processengine
     * .model.BusinessProcessModel)
     */
    @Override
    public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {
        if (orderProcessModel == null || orderProcessModel.getOrder() == null)
        {
            LOGGER.error("Cannot process submission to SAP SD since the orderProcessModel[" + orderProcessModel
                    + "] or the order is null");
            throw new IllegalArgumentException("Cannot process submission to SAP SD since the orderProcessModel["
                    + orderProcessModel + "] or the order is null");
        }

        info(LOGGER, "Process: %s in step %s", orderProcessModel.getCode(), getClass());

        try
        {

            final OrderModel order = orderProcessModel.getOrder();

            debug(LOGGER, "Processing actiont SubmitToSAPSD with order[%s]", order);

            final ClientStatus status = sapService.sendSAPSD(order);
            if (ClientStatus.SUCCESS != status)
            {
                orderProcessModel.setEndMessage("Business process step: SubmitToSAPSD \n" + " Error Message: ClientStatus - "
                        + status.name());
                modelService.save(orderProcessModel);
                return handleException(null, orderProcessModel);
            }
            orderProcessModel.setEndMessage("Business process step: SubmitToSAPSD end with SUCCESS");
            modelService.save(orderProcessModel);
        }
        catch (final Exception e)
        {
            orderProcessModel.setEndMessage("Business process step: SubmitToSAPSD \n" + " Exception: " + e);
            modelService.save(orderProcessModel);
            return handleException(e, orderProcessModel);
        }
        return Transition.OK;
    }


}
