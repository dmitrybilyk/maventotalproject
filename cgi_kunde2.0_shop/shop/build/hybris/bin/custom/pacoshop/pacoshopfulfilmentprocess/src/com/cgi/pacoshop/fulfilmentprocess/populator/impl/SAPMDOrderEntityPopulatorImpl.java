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
package com.cgi.pacoshop.fulfilmentprocess.populator.impl;

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.hybris.payment.core.services.PaymentExtService;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPMDOrderEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPAccountPopulator;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPAddressPopulator;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPMDOrderEntityPopulator;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPOrderEntryEntityPopulator;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import javax.annotation.Resource;

import static org.apache.commons.lang.StringUtils.isEmpty;
import org.apache.log4j.Logger;


/**
 * Implementation of the populator for the SAP create periodic order population.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module hybris - pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class SAPMDOrderEntityPopulatorImpl implements SAPMDOrderEntityPopulator
{

    private static final Logger LOGGER = Logger.getLogger(SAPMDOrderEntityPopulatorImpl.class);

	 private static final int ZERO = 0;

    @Resource
    private SAPAddressPopulator sapAddressPopulator;

    @Resource
    private SAPOrderEntryEntityPopulator sapOrderEntryEntityPopulator;

    @Resource
    private SAPAccountPopulator sapAccountPopulator;

    @Resource
    private PaymentExtService paymentExtService;

    /**
     * 
     * @see com.cgi.pacoshop.fulfilmentprocess.populator.SAPMDOrderEntityPopulator#populate(de.hybris.platform.core.model
     *      .order.AbstractOrderModel)
     * 
     * @param order
     *            Abstract order to use
     * @return SAPMDOrderEntity object
     * @throws com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException
     *             thrown on failure
     */
    @Override
    public SAPMDOrderEntity populate(final OrderModel order) throws SAPException
    {
        if (order == null)
        {
            throw new SAPException("Called the populator with a null order.");
        }
        final SAPMDOrderEntity result = new SAPMDOrderEntity();
        try
        {
            result.setOrderRequestId(order.getCode());
            result.setOrderEntries(sapOrderEntryEntityPopulator.populateAll(order));

            if (order.getCustomerAddress() != null)
            {
                result.setClient(sapAddressPopulator.populate(order.getCustomerAddress()));
            }
            else
            {
                throw new SAPException("There is no customer addresss defined in order: [" + order.getCode() + "]");
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


            // TODO where does that comes from.
            result.setOrderOrigin(null);

            // TODO where does that comes from?
            result.setOrderId(null);

            result.setStudentGraduationDate(order.getStudentGradutationDate());

            if (order.getBonusRecipientAddress() != null)
            {
                result.setMilesAndMoresNumber(order.getBonusRecipientAddress().getMilesAndMoreNumber());
            }
            else
            {
                result.setMilesAndMoresNumber(order.getCustomerAddress().getMilesAndMoreNumber());
            }

            if (order.getBonusRecipientPaymentInfo() != null)
            {
                result.setBonusAccount(sapAccountPopulator.populate((PacoDebitPaymentInfoModel) order
                        .getBonusRecipientPaymentInfo()));
            }

            if (order.getDeliveryStartDate() != null)
            {
                result.setValidFrom(order.getDeliveryStartDate());
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
                    if (PacoshopFulfilmentProcessConstants.PAYMENT_WIRECARD.equalsIgnoreCase(order.getPaymentInfo()
                            .getPaymentMethod().getPsp().getCode()))
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
                result.setPspPaymentId(result.getPspTransactionId());
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
        debug(LOGGER, "Return of the create periodics order population: order %s, return %s", order, result);
        return result;
    }

}
