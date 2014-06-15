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
package com.cgi.pacoshop.mock.address.service.impl;


import com.cgi.pacoshop.mock.address.model.AddressResponse;
import com.cgi.pacoshop.mock.address.model.DeliveryAddress;
import com.cgi.pacoshop.mock.address.service.AddressValidationService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;


/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @version 1.0v Apr 04, 2014
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
@Service("AddressValidationService")
public class DefaultAddressValidationService implements AddressValidationService
{
	public static final String NONE_RESPONSE      = "NONE";
	public static final String AGENT_RESPONSE     = "AGENT";
	public static final String FORWARDER_RESPONSE = "FORWARDER";
	public static final String VOUCHER_RESPONSE   = "VOUCHER";
	public static final String POST_RESPONSE      = "POST";

	private static final Logger LOGGER = Logger.getLogger(DefaultAddressValidationService.class);

	@Override
	public AddressResponse validateAddress(final String offerOrigin, final String offerId, final DeliveryAddress deliveryAddress,
														final int delay)	throws JAXBException, InterruptedException
	{
		if (LOGGER.isDebugEnabled())
		{
			LOGGER.debug("Requesting delivery address check for product with offer ID: " + offerId
										+ " and offer origin: " + offerOrigin);
			LOGGER.debug("Delay = " + delay);
		}

		if (delay > 0)
		{
			Thread.sleep(delay);
		}

		if (deliveryAddress.getAddressSuffix().equals(AGENT_RESPONSE))
		{
			return new AddressResponse(AGENT_RESPONSE);
		}
		if (deliveryAddress.getAddressSuffix().equals(FORWARDER_RESPONSE))
		{
			return new AddressResponse(FORWARDER_RESPONSE);
		}
		if (deliveryAddress.getAddressSuffix().equals(VOUCHER_RESPONSE))
		{
			return new AddressResponse(VOUCHER_RESPONSE);
		}
		if (deliveryAddress.getAddressSuffix().equals(NONE_RESPONSE))
		{
			return new AddressResponse(NONE_RESPONSE);
		}
		return new AddressResponse(POST_RESPONSE);
	}

	@Override
	public Response createAddressCheckError(final int code) throws Exception
	{
		return Response.status(code).build();
	}

}
