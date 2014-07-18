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


import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import com.cgi.pacoshop.fulfilmentprocess.actions.order.AbstractRetryableOrderModelAction;
import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.task.RetryLaterException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

/**
 * Action for sending confirmation email ro customer
 *
 * @module shop
 * @version 1.0v Apr 02, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SendOrderConfirmationEmailAction extends AbstractRetryableOrderModelAction
{
	private static final Logger LOGGER = Logger.getLogger(SendOrderConfirmationEmailAction.class);

	private EmailService emailService;

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
		Assert.notNull(orderProcessModel, "OrderProcessModel can't be null");
		Assert.notNull(orderProcessModel.getOrder(), "Order can't be null");
		Assert.notNull(orderProcessModel.getOrder().getCustomerAddress(), "CustomerAddress can't be null");

		final String sendTo = orderProcessModel.getOrder().getCustomerAddress().getEmail();
		send(orderProcessModel.getEmails(), sendTo);

		return Transition.OK;
	}

	private List<EmailMessageModel> send(final List<EmailMessageModel> emailsForSending, final String sendTo)
	{
		final List<EmailMessageModel> unsentEmails = new ArrayList<EmailMessageModel>();
		for (final EmailMessageModel email : emailsForSending)
		{
			email.getToAddresses().get(0).setEmailAddress(sendTo);
			email.getToAddresses().get(0).setDisplayName(sendTo);
			if (!getEmailService().send(email))
			{
				unsentEmails.add(email);
			}
		}

		return unsentEmails;
	}

}
