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
package com.cgi.pacoshop.core.process.email.actions;


import com.cgi.pacoshop.core.service.ShopConfigurationService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.task.RetryLaterException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.fulfilmentprocess.actions.order.AbstractRetryableOrderModelAction;


/**
 * This is action for sending error email to admin
 * 
 * 
 * @module build
 * @version 1.0v Mar 23, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class SendOrderErrorEmailAction extends AbstractRetryableOrderModelAction
{
	private static final Logger LOGGER = Logger.getLogger(SendOrderErrorEmailAction.class);

	private EmailService emailService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	/**
	 * get EmailService.
	 *
	 * @return EmailService like EmailService
	 */
	protected EmailService getEmailService()
	{
		return emailService;
	}

	/**
	 * set EmailService.
	 *
	 * @param emailService
	 *            like emailService.
	 */
	@Required
	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}

	@Override
	public Transition executeAction(final OrderProcessModel orderProcessModel) throws RetryLaterException,
																												 Exception
	{
		try
		{
			final int retries = getConfig().getInt(PacoshopCoreConstants.EmailErrorSending.RETRY_SENDING);
			final int delay = getConfig().getInt(PacoshopCoreConstants.EmailErrorSending.DELAY_BETWEEN_RETRIES);
			final String sendTo = getConfig().getString(PacoshopCoreConstants.EmailErrorSending.SEND_TO);

			debug(LOGGER, "SendOrderErrorEmailAction , sendTo: %s, delay: %s, retries: %s", sendTo, Integer.valueOf(delay),
					Integer.valueOf(retries));

			final List<EmailMessageModel> unsentEmails = send(orderProcessModel.getEmails(), sendTo);
			debug(LOGGER, "unsent emails: %s", Integer.valueOf(unsentEmails.size()));

			if (!unsentEmails.isEmpty())
			{
				LOGGER.info("retries: " + retries + ", delay: " + delay + ", sendTo: " + sendTo);
				return handleException(null, orderProcessModel);
			}

		}
		catch (final Exception e)
		{
			return handleException(e, orderProcessModel);
		}

		return Transition.OK;
    }

    private Configuration getConfig()
    {
        return shopConfigurationService.getServiceConfiguration();
    }

    private List<EmailMessageModel> send(final List<EmailMessageModel> emailsForSending, final String sendTo)
    {
        final List<EmailMessageModel> unsentEmails = new ArrayList<EmailMessageModel>();
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

}
