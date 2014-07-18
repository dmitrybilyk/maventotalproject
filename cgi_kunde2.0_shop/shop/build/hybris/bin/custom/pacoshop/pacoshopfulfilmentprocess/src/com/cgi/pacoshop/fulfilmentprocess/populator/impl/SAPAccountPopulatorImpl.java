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

import com.cgi.pacoshop.fulfilmentprocess.model.SAPAccount;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPAccountPopulator;

import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;

import org.apache.log4j.Logger;

import wiremock.org.apache.commons.lang.StringUtils;


/**
 * Implementation of the populator for the SAP Account population.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPAccountPopulatorImpl implements SAPAccountPopulator
{
    private static final Logger LOGGER = Logger.getLogger(SAPAccountPopulatorImpl.class);

    @Override
    public SAPAccount populate(final DebitPaymentInfoModel debitPaymentInfo)
    {
        if (debitPaymentInfo == null)
        {
            debug(LOGGER, "Called the populator with a null debitPaymentInfo");
            return null;
        }
        final SAPAccount sapAccount = new SAPAccount();
        sapAccount.setAccountHolder(debitPaymentInfo.getBaOwner());
        sapAccount.setAccountNumber(debitPaymentInfo.getAccountNumber());
        sapAccount.setBankCode(debitPaymentInfo.getBankIDNumber());
        sapAccount.setBankName(debitPaymentInfo.getBank());
        sapAccount.setIban(debitPaymentInfo.getIban());
        sapAccount.setSwiftBic(debitPaymentInfo.getBic());

        debug(LOGGER, "Result from the populator:[%s]", sapAccount);
        return sapAccount;
    }

    @Override
    public SAPAccount populate(final PacoDebitPaymentInfoModel debitPaymentInfo)
    {
        if (debitPaymentInfo == null)
        {
            debug(LOGGER, "Called the populator with a null debitPaymentInfo");
            return null;
        }
        final SAPAccount sapAccount = new SAPAccount();
        String accountHolder = null;
        if (debitPaymentInfo.getFirstName() != null)
        {
            accountHolder = debitPaymentInfo.getFirstName();
        }

        if (debitPaymentInfo.getLastName() != null)
        {
            if (StringUtils.isNotEmpty(accountHolder))
            {
                accountHolder += " " + debitPaymentInfo.getLastName();
            }
            else
            {
                accountHolder = debitPaymentInfo.getLastName();
            }
        }
        sapAccount.setAccountHolder(accountHolder);
        sapAccount.setAccountNumber(debitPaymentInfo.getAccountNumber());
        sapAccount.setBankCode(debitPaymentInfo.getBankIdNumber());

        //that is the model. This is not a mistake.
        sapAccount.setBankName(null);
        sapAccount.setIban(debitPaymentInfo.getIBAN());
        sapAccount.setSwiftBic(debitPaymentInfo.getBIC());

        debug(LOGGER, "Result from the populator:[%s]", sapAccount);
        return sapAccount;
    }

}
