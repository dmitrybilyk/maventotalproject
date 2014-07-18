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
package com.cgi.pacoshop.storefront.filters;


import com.cgi.pacoshop.core.service.ShopConfigurationService;
import static com.cgi.pacoshop.core.service.ShopConfigurationService.*;
import com.cgi.pacoshop.storefront.filters.uag.UagRequestDecoderWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * UAG is placing his data values to request headers in encoded state, so we need to decode all UAG-specific headers.
 *
 * @module pacoshopstorefront
 * @version 1.0v Jun 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class UagDecoderFilter extends OncePerRequestFilter
{
	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
											  final FilterChain filterChain) throws ServletException, IOException
	{
		// list of request headers which comes from UAG and which could be used in shop application.
		List<String> uagHeaders = new ArrayList<>();
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_ID).toLowerCase());
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_ID_TYPE).toLowerCase());
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_EMAIL).toLowerCase());
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_CONTENTACCESS).toLowerCase());
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_WS_TOKEN_KEY).toLowerCase());
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_X_FORWARDED_FOR).toLowerCase());
		uagHeaders.add(shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_NAME_X_FORWARDED_PROTO).toLowerCase());
		String encoding = shopConfigurationService.getProperty(CONFIG_KEY_UAG_HEADER_ENCODING);

		// decode any UAG header value if it exist in request
		UagRequestDecoderWrapper uagRequestDecoderWrapper = new UagRequestDecoderWrapper(request, uagHeaders, encoding);

		filterChain.doFilter(uagRequestDecoderWrapper, response);
	}
}
