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


import com.cgi.pacoshop.fulfilmentprocess.exceptions.SSOException;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest;
import de.hybris.platform.core.model.order.AbstractOrderModel;


/**
 * Populator for the SSORegisterAcceptedTermsRequest.
 * 
 * @module pacoshopfulfilmentprocess
 * @version 1.0v Feb 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public interface SSORegisterAcceptedTermsPopulator
{

    /**
     * Populate {@link com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest}.
     *
     * @param order
     *            the order to uses.
     * @return the {@link com.cgi.pacoshop.fulfilmentprocess.model.SSORegisterAcceptedTermsRequest} populated.
     * @throws com.cgi.pacoshop.fulfilmentprocess.exceptions.SSOException
     *             if the population failed.
     */
    SSORegisterAcceptedTermsRequest populate(AbstractOrderModel order) throws SSOException;


}
