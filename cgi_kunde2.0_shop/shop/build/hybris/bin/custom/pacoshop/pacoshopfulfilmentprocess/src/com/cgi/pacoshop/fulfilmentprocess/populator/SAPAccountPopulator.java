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
package com.cgi.pacoshop.fulfilmentprocess.populator;

import com.cgi.pacoshop.fulfilmentprocess.model.SAPAccount;

import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;


/**
 * Defines the populator for the SAP Account population.
 * 
 * @module hybris - pacoshopfulfilmentprocess
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface SAPAccountPopulator
{

    /**
     * Populates a SAPAccount entity with a {@link DebitPaymentInfoModel}.
     * 
     * @param debitPaymentInfo
     *            the debitPaymentInfo model to use.
     * @return the created SAPAccount if the debitPaymentInfo wasn't null, null otherwise.
     */
    SAPAccount populate(DebitPaymentInfoModel debitPaymentInfo);

    /**
     * Populates a SAPAccount entity with a {@link PacoDebitPaymentInfoModel}.
     * 
     * @param debitPaymentInfo
     *            the debitPaymentInfo model to use.
     * @return the created SAPAccount if the debitPaymentInfo wasn't null, null otherwise.
     */
    SAPAccount populate(PacoDebitPaymentInfoModel debitPaymentInfo);
}
