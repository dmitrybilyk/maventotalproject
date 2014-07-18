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
package com.cgi.pacoshop.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.AbstractSiteEventListener;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.fulfilmentprocess.events.EmailFulfillmentEvent;


/**
 * This is the listener for EmailFulfillmentEvent. It will start orderEmailFulfillmentProcess which will send
 * fulfillment email to admin.
 * 
 * @module pacoshopcore
 * @version 1.0v 17.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class EmailFulfillmentEventListener extends AbstractSiteEventListener<EmailFulfillmentEvent>
{
    private ModelService modelService;
    private BusinessProcessService businessProcessService;

    @Override
    protected void onSiteEvent(final EmailFulfillmentEvent event)
    {
        final OrderModel orderModel = event.getProcess().getOrder();
        final OrderProcessModel orderProcessModel = (OrderProcessModel) getBusinessProcessService().createProcess(
                "orderEmailFulfillmentProcess-" + orderModel.getCode() + "-" + System.currentTimeMillis(),
                "orderEmailFulfillmentProcess");
        orderProcessModel.setOrder(orderModel);
        orderProcessModel.setEndMessage(event.getProcess().getEndMessage());
        getModelService().save(orderProcessModel);
        final BusinessProcessParameterModel bppm = new BusinessProcessParameterModel();
        bppm.setName(PacoshopCoreConstants.EmailFulfillmentSending.MAIN_PROCESS_CODE);
        bppm.setValue(event.getProcess().getCode());
        bppm.setProcess(orderProcessModel);
        getModelService().save(bppm);
        getBusinessProcessService().startProcess(orderProcessModel);
    }

    @Override
    protected boolean shouldHandleEvent(final EmailFulfillmentEvent event)
    {
        final OrderModel order = event.getProcess().getOrder();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order", order);
        final BaseSiteModel site = order.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        return SiteChannel.B2C.equals(site.getChannel());
    }

    /**
     * @return the modelService
     */
    public ModelService getModelService()
    {
        return modelService;
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }

    /**
     * @return the businessProcessService
     */
    public BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }

    /**
     * @param businessProcessService
     *            the businessProcessService to set
     */
    public void setBusinessProcessService(final BusinessProcessService businessProcessService)
    {
        this.businessProcessService = businessProcessService;
    }

}
