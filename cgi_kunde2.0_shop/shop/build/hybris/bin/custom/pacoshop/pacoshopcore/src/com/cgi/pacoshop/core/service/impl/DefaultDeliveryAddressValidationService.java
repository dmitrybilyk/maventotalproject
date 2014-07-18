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
import com.cgi.pacoshop.core.service.DeliveryAddressValidationService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.util.LogHelper;
import de.hybris.platform.core.model.product.ProductModel;
import org.apache.log4j.Logger;
import org.springframework.web.util.UriTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


/**
 * Implementation class of {@link com.cgi.pacoshop.core.service.DeliveryAddressValidationService}.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class DefaultDeliveryAddressValidationService implements DeliveryAddressValidationService
{
	private static final Logger LOG = Logger.getLogger(DefaultDeliveryAddressValidationService.class);

	private static final String NONE_RESPONSE = "\"NONE\"";

	private static final String OFFER_ORIGIN_PARAM = "offerOrigin";

	private static final String OFFER_ID_PARAM = "offerId";

	@Resource
	private DeliveryAddressValidationRestService deliveryAddressValidationRestService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

    @Override
	public boolean productDeliverableToAddress(final ProductModel product, final DeliveryAddress deliveryAddress)
	{
		Map<String, String> parameters = new HashMap<>();
		parameters.put(OFFER_ORIGIN_PARAM, product.getOfferOrigin().getCode());
		parameters.put(OFFER_ID_PARAM, product.getExternalOfferId());

		final UriTemplate uriTemplate = new UriTemplate(shopConfigurationService.getDeliveryAddressCheckUri());
		final URI uri = uriTemplate.expand(parameters);
		final int readTimeout = shopConfigurationService.getDeliveryAddressCheckReadTimeout();

		try
		{
			LogHelper.debug(LOG, "Checking address for deliverability the address [%s], url [%s]", deliveryAddress, uri);

			String response = deliveryAddressValidationRestService.validateAddress(uri, deliveryAddress, readTimeout);

			LogHelper.debug(LOG, "Deliverability check completed with response [%s]", response);

			if (response.equals(NONE_RESPONSE))
			{
				return false;
			}
		}
		catch (Exception e)
		{
			LogHelper.error(LOG, "An exception arise when checking address for deliverability the address [%s], url [%s]", e,
								 deliveryAddress, uri);
		}
		return true;
	}
}
