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
 * Action that process an order via the msd interface (SAP periodic order).
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubmitToSAPMSDAction extends AbstractRetryableOrderModelAction
{

    private static final Logger LOGGER = Logger.getLogger(SubmitToSAPMSDAction.class);

    @Resource
    private ModelService modelService;

    @Resource
    private SAPService sapService;


    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.processengine.action.AbstractSimpleDecisionAction#executeAction(de.hybris.platform.processengine
     * .model.BusinessProcessModel)
     */
    @Override
    public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {


        if (orderProcessModel == null || orderProcessModel.getOrder() == null)
        {
            LOGGER.error("Cannot process submission to SAP MD since the orderProcessModel[" + orderProcessModel
                    + "] or the order is null");
            throw new IllegalArgumentException("Cannot process submission to SAP MD since the orderProcessModel["
                    + orderProcessModel + "] or the order is null");
        }

        info(LOGGER, "Process: %s in step %s", orderProcessModel.getCode(), getClass());

        try
        {


            final OrderModel order = orderProcessModel.getOrder();

            debug(LOGGER, "Processing actiont SubmitToSAPMSD with order[%s]", order);

            final ClientStatus status = sapService.sendSAPMD(order);
            if (ClientStatus.SUCCESS != status)
            {
                orderProcessModel.setEndMessage(" Business process step: SubmitToSAPMSD \n Error Message: ClientStatus - "
                        + status.name());
                modelService.save(orderProcessModel);
                return handleException(null, orderProcessModel);
            }
            else
            {
                orderProcessModel.setEndMessage(" Business process step: SubmitToSAPMSD \n" + " SUCCESS");
                modelService.save(orderProcessModel);
            }
        }
        catch (final Exception e)
        {
            orderProcessModel.setEndMessage(" Business process step: SubmitToSAPMSD \n" + " Error Message: Exception: " + e);
            modelService.save(orderProcessModel);
            return handleException(e, orderProcessModel);
        }
        return Transition.OK;
    }

}
