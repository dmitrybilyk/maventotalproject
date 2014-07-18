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


import com.cgi.pacoshop.core.model.OrderDTO;
import com.cgi.pacoshop.core.service.CallbackRestService;
import com.cgi.pacoshop.core.util.LogHelper;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of CalbackRestService.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CallbackRestServiceImpl implements CallbackRestService
{
	protected static final Logger LOG = Logger.getLogger(CallbackRestServiceImpl.class);

	@Resource
	private RestTemplate restTemplateCallback;

	@Override
	public OrderDTO getOrderDTO(final String url, final String cartId)
	{
		OrderDTO orderDTO = null;
		try
		{
			orderDTO = restTemplateCallback.getForObject(url + "?cartid=" + cartId, OrderDTO.class);
		}
		catch (Exception e)
		{
			LogHelper.error(LOG, "Error occurred during callBack for cart %s", e, cartId);
		}
		return orderDTO;
	}
}
