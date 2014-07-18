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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.model.DeliveryAddress;
import com.cgi.pacoshop.core.service.DeliveryAddressValidationRestService;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;


/**
 * Implementation class of {@link com.cgi.pacoshop.core.service.DeliveryAddressValidationRestService}
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @version 1.0v Apr 04, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class DefaultDeliveryAddressValidationRestService implements DeliveryAddressValidationRestService
{
	protected static final Logger LOG = Logger.getLogger(CallbackRestServiceImpl.class);

	@Resource
	private RestTemplate restTemplateDeliveryAddressValidation;

	@Override
	public String validateAddress(final URI url, final DeliveryAddress deliveryAddress, final int readTimeout)
	{
		// set timeout params to request factory
		// important: the same request factory used on consequent queries
		// each restTemplate spring bean has own request factory (with specific params)
		Assert.isInstanceOf(PacoshopRequestFactory.class, restTemplateDeliveryAddressValidation.getRequestFactory());
		PacoshopRequestFactory requestFactory = (PacoshopRequestFactory) restTemplateDeliveryAddressValidation.getRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		return restTemplateDeliveryAddressValidation.postForObject(url, deliveryAddress, String.class);
	}
}
