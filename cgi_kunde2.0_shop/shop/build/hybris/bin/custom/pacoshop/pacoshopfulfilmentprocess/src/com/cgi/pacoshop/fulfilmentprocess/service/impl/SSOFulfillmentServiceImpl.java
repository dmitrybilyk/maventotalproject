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
package com.cgi.pacoshop.fulfilmentprocess.service.impl;

import com.cgi.pacoshop.fulfilmentprocess.client.ClientStatus;
import com.cgi.pacoshop.fulfilmentprocess.client.SSORegisterAcceptedTermsClient;
import com.cgi.pacoshop.fulfilmentprocess.client.SSOSearchAccountClient;
import com.cgi.pacoshop.fulfilmentprocess.client.SearchAccountStatus;
import com.cgi.pacoshop.fulfilmentprocess.service.SSOFulfillmentService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;
import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.error;


/**
 * Represents the default implementation of the SSOFulfillmentService.
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
public class SSOFulfillmentServiceImpl implements SSOFulfillmentService
{

    /**
     * Default logger.
     */
    private static final Logger LOGGER = Logger.getLogger(SSOFulfillmentServiceImpl.class);
    private SSOSearchAccountClient         ssoSearchAccountClient;
    private SSORegisterAcceptedTermsClient ssoRegisterAcceptedTermsClient;
    private ModelService                   modelService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.service.SSOService#assignRecipientCustomerId(de.hybris.platform.core.model
     * .order.OrderModel)
     */
    @Override
    public ClientStatus assignRecipientCustomerId(final OrderModel order)
    {
        final SearchAccountStatus status = ssoSearchAccountClient.send(order);

        if (status != null && ClientStatus.SUCCESS == status.getClientStatus())
        {
            final AddressModel deliveryAddress = order.getDeliveryAddress();
            deliveryAddress.setSsoCustomerId(status.getCustomerId());

            order.setDeliveryAddress(deliveryAddress);
            modelService.saveAll(deliveryAddress, order);
        }

        if (status != null)
        {
            debug(LOGGER, "Call to SSO for searching client. Result: %s", status.getClientStatus().name());
        }

        return ClientStatus.SUCCESS;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.cgi.pacoshop.fulfilmentprocess.service.SSOService#assignRecipientCustomerId(de.hybris.platform.core.model
     * .order.OrderModel)
     */
   @Override
   public ClientStatus registerAcceptedTerms(final OrderModel order)
   {
      //Story: https://jira.symmetrics.de/browse/KS-252
      final ClientStatus status = ssoRegisterAcceptedTermsClient.send(order);

      if (status == null)
      {
         debug(LOGGER, "registerAcceptedTerms() here is no terms to be sent to SSO.");
      }
      else if (ClientStatus.SUCCESS != status)
      {
         error(LOGGER, "Call to SSO for register Opt-in client. Result: %s.", status.toString());
      }
      else
      {
         debug(LOGGER, "registerAcceptedTerms(). Result: SUCCESS.");
      }

      return status;
    }

    /**
     * @param ssoSearchAccountClient
     *            the ssoSearchAccountClient to set
     */
    public void setSsoSearchAccountClient(final SSOSearchAccountClient ssoSearchAccountClient)
    {
        this.ssoSearchAccountClient = ssoSearchAccountClient;
    }

    /**
     * @param ssoRegisterAcceptedTermsClient
     *            the ssoRegisterOptinClient to set
     */
    public void setSsoRegisterAcceptedTermsClient(final SSORegisterAcceptedTermsClient ssoRegisterAcceptedTermsClient)
    {
        this.ssoRegisterAcceptedTermsClient = ssoRegisterAcceptedTermsClient;
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }
}
