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
package com.cgi.pacoshop.fulfilmentprocess.populator.impl;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.hybris.payment.core.services.PaymentExtService;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPSDOrderEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPAccountPopulator;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPAddressPopulator;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPOrderEntryEntityPopulator;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntityPopulator;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import javax.annotation.Resource;

import static org.apache.commons.lang.StringUtils.isEmpty;
import org.apache.log4j.Logger;


/**
 * Implementation of the populator for the SAP create single order population.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPSDOrderEntityPopulatorImpl implements SAPSDOrderEntityPopulator
{

	private static final Logger LOGGER = Logger.getLogger(SAPSDOrderEntityPopulatorImpl.class);
	private static final int ZERO = 0;

	@Resource
	private SAPAddressPopulator sapAddressPopulator;

	@Resource
	private SAPOrderEntryEntityPopulator sapOrderEntryEntityPopulator;

	@Resource
	private SAPAccountPopulator sapAccountPopulator;

	@Resource
	private PaymentExtService paymentExtService;


	/*
	  * (non-Javadoc)
	  *
	  * @see
	  * com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntityPopulator#populate(de.hybris.platform.core.model
	  * .order.AbstractOrderModel)
	  */
	@Override
	public SAPSDOrderEntity populate(final OrderModel order) throws SAPException
	{
		if (order == null)
		{
			throw new SAPException("Called the populator with a null order.");
		}
		final SAPSDOrderEntity result = new SAPSDOrderEntity();
		try
		{
			result.setOrderRequestId(order.getCode());
			result.setOrderEntries(sapOrderEntryEntityPopulator.populateAll(order));

			if (order.getCustomerAddress() != null)
			{
				result.setClient(sapAddressPopulator.populate(order.getCustomerAddress()));
			}

			if (order.getPaymentAddress() != null)
			{
				result.setInvoiceRecipient(sapAddressPopulator.populate(order.getPaymentAddress()));
			}

			if (order.getDeliveryAddress() != null)
			{
				result.setConsignee(sapAddressPopulator.populate(order.getDeliveryAddress()));
			}
			else
			{
				result.setConsignee(sapAddressPopulator.populate(order.getCustomerAddress()));
			}

			if (order.getTotalPrice() > ZERO)
			{
				result.setPspMethod(order.getPaymentMode().getCode());
			}

			if (order.getPaymentInfo() != null)
			{
				if (order.getPaymentInfo().getPaymentMethod() != null
						  && order.getPaymentInfo().getPaymentMethod().getPsp() != null)
				{
					if (PacoshopFulfilmentProcessConstants.PAYMENT_WIRECARD.equalsIgnoreCase(
                            order.getPaymentInfo().getPaymentMethod().getPsp().getCode()))
					{
						result.setPspId(PacoshopFulfilmentProcessConstants.PAYMENT_WIRECARD);
					}
					else
					{
						result.setPspId(PacoshopFulfilmentProcessConstants.PAYMENT_SAP);
					}
				}
			}

			final PaymentTransactionEntryModel transaction = paymentExtService.findTransaction(order,
																														  PaymentTransactionType.CAPTURE);
			if (transaction != null)
            {
                result.setPspTransactionId(transaction.getRequestToken());
            }

            if (order.getPaymentInfo() instanceof DebitPaymentInfoModel)
            {
                result.setPspAccount(sapAccountPopulator.populate((DebitPaymentInfoModel) order.getPaymentInfo()));
            }
            else if (order.getPaymentInfo() instanceof PacoDebitPaymentInfoModel)
            {
                result.setPspAccount(sapAccountPopulator.populate((PacoDebitPaymentInfoModel) order.getPaymentInfo()));
            }

        }
        catch (final Exception e)
        {
            LOGGER.error("Catch exception during population.", e);
            throw new SAPException("Catch exception while population of order[" + order.getCode() + "]", e);
        }
        debug(LOGGER, "Return of the create single order population: order %s, return %s", order, result);
        return result;
    }


    /**
     * @param paymentExtService
     *            the paymentExtService to set
     */
    public void setPaymentExtService(final PaymentExtService paymentExtService)
    {
        this.paymentExtService = paymentExtService;
    }

}
