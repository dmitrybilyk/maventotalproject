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

import com.cgi.pacoshop.fulfilmentprocess.service.SSOFulfillmentService;

import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractProceduralAction;
import de.hybris.platform.task.RetryLaterException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;


/**
 * Update the consignee recipient with the SAPid returned.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module pacoshopfulfilmentprocess
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class UpdateRecipientAction extends AbstractProceduralAction<OrderProcessModel>
{

    /**
     * Default logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UpdateRecipientAction.class);


    @Resource
    private SSOFulfillmentService ssoFulfillmentService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.processengine.action.AbstractProceduralAction#executeAction(de.hybris.platform.processengine
     * .model.BusinessProcessModel)
     */
    @Override
    public void executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {
        if (LOGGER.isInfoEnabled())
        {
            LOGGER.info("Process: " + orderProcessModel.getCode() + " in step " + getClass());
        }

        ssoFulfillmentService.assignRecipientCustomerId(orderProcessModel.getOrder());

    }

    /**
     * @return the ssoFulfillmentService
     */
    public SSOFulfillmentService getSsoFulfillmentService()
    {
        return ssoFulfillmentService;
    }

    /**
     * @param ssoFulfillmentService
     *            the ssoFulfillmentService to set
     */
    public void setSsoFulfillmentService(final SSOFulfillmentService ssoFulfillmentService)
    {
        this.ssoFulfillmentService = ssoFulfillmentService;
    }
}
