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
package com.cgi.pacoshop.core.process.email.actions;

import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.info;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.model.BusinessProcessParameterModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.fulfilmentprocess.actions.order.AbstractRetryableOrderModelAction;


/**
 * This is the action to send email fulfillment email to admin.
 * 
 * @module pacoshopcore
 * @version 1.0v 22.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class SendOrderEmailFulfillmentEmailAction extends AbstractRetryableOrderModelAction
{
    private static final Logger LOGGER = Logger.getLogger(SendOrderEmailFulfillmentEmailAction.class);
    private EmailService emailService;
    private BusinessProcessService businessProcessService;

    @Override
    public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {
        final Transition transition = handleAction(orderProcessModel);
        final Object processCode = getContextParameterValueByName(orderProcessModel,
                  PacoshopCoreConstants.EmailFulfillmentSending.MAIN_PROCESS_CODE);
        if (processCode != null)
        {
           getBusinessProcessService().triggerEvent(
                 processCode.toString() + PacoshopCoreConstants.EmailFulfillmentSending.EMAIL_FULFILLMENT_FINISHED_SUFFIX);
        }
        return transition;
    }

    /**
     * Find context parameter value by its name.
     * 
     * @param orderProcessModel
     *            the {@link OrderProcessModel}.
     * @param name
     *            the context parameter name.
     * @return context parameter value name or null if not found.
     */
    private Object getContextParameterValueByName(final OrderProcessModel orderProcessModel, final String name)
    {
        final Collection<BusinessProcessParameterModel> parameters = orderProcessModel.getContextParameters();
        if (CollectionUtils.isNotEmpty(parameters))
        {
            for (final BusinessProcessParameterModel parameter : parameters)
            {
                if (name.equals(parameter.getName()))
                {
                    return parameter.getValue();
                }
            }
        }

        return null;
    }

    /**
     * Handles action to send fulfillment email.
     * 
     * @param orderProcessModel
     *            the {@link OrderProcessModel}.
     * @return the {@link Transition}.
     * @throws RetryLaterException
     *             the {@lnk RetryLaterException}.
     * @throws Exception
     *             the {@lnk Exception}.
     */
    private Transition handleAction(final OrderProcessModel orderProcessModel) throws RetryLaterException, Exception
    {
        try
        {
            final int retries = getConfig().getInt(PacoshopCoreConstants.EmailFulfillmentSending.RETRY_SENDING);
            final int delay = getConfig().getInt(PacoshopCoreConstants.EmailFulfillmentSending.DELAY_BETWEEN_RETRIES);
            String sendTo = getConfig().getString(PacoshopCoreConstants.EmailFulfillmentSending.SEND_TO);
            // use site specific email fulfillment address
            final BaseSiteModel site = orderProcessModel.getOrder().getSite();
            if (site != null && site instanceof CMSSiteModel)
            {
                final String emailAddress = ((CMSSiteModel) site).getFulfillmentEmail();
                if (StringUtils.isNotBlank(emailAddress))
                {
                    sendTo = emailAddress;
                }
            }

            debug(LOGGER, "SendOrderEmailFulfillmentEmail action, sendTo: %s, delay: %s, retries: %s", sendTo,
                    Integer.valueOf(delay), Integer.valueOf(retries));

            final List<EmailMessageModel> unsentEmails = send(orderProcessModel.getEmails(), sendTo);
            debug(LOGGER, "unsent emails: %s", Integer.valueOf(unsentEmails.size()));

            if (!unsentEmails.isEmpty())
            {
                info(LOGGER, "retries: " + retries + ", delay: " + delay + ", sendTo: " + sendTo);
                return handleException(null, orderProcessModel);
            }

        }
		  catch (final Exception e)
		  {
			  return handleException(e, orderProcessModel);
		  }

        return Transition.OK;
    }

    /**
     * Send emails.
     * 
     * @param emailsForSending
     *            the emails to be sent.
     * @param sendTo
     *            the email send address.
     * @return unsent emails.
     */
    private List<EmailMessageModel> send(final List<EmailMessageModel> emailsForSending, final String sendTo)
    {
        final List<EmailMessageModel> unsentEmails = new ArrayList<>();
        for (final EmailMessageModel email : emailsForSending)
        {
            email.getToAddresses().get(0).setEmailAddress(sendTo);
            if (!getEmailService().send(email))
            {
                unsentEmails.add(email);
            }
        }

        return unsentEmails;
    }

    /**
     * @return the emailService
     */
    public EmailService getEmailService()
    {
        return emailService;
    }

    /**
     * @param emailService
     *            the emailService to set
     */
    public void setEmailService(final EmailService emailService)
    {
        this.emailService = emailService;
    }

    /**
     * Get configuration.
     * 
     * @return {@link Configuration}
     */
    private Configuration getConfig()
    {
        return getConfigurationService().getConfiguration();
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
