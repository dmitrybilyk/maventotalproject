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
package com.cgi.pacoshop.fulfilmentprocess.client;

import de.hybris.platform.core.model.order.OrderModel;


/**
 * Definition of the SSORegisterOptinClient.
 *
 * @module pacoshopfullfilmentprocess
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public interface SSORegisterAcceptedTermsClient
{

	/**
	 * Send the request to SSO.
	 *
	 * @param order
	 *            the order to process.
	 * @return the clientStatus.
	 */
	ClientStatus send(final OrderModel order);

}
