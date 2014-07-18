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
package com.cgi.pacoshop.storefront.filters.uag;


import com.cgi.pacoshop.core.service.ShopConfigurationService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * Wrapper around the HttpServletRequest to decode a specific headers which comes from UAG in encoded state.
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
public class UagRequestDecoderWrapper extends HttpServletRequestWrapper
{
	private final static Logger LOG = Logger.getLogger(UagRequestDecoderWrapper.class.getName());

	private final Collection<String>  uagHeaderNames;
	private final String encoding;

	/**
	 * @param request the servlet request
	 * @param uagHeaderNames the list of header names which should be decoded
	 * @param encoding encoding used by UAG to encode the header values
	 */
	public UagRequestDecoderWrapper(final HttpServletRequest request, final Collection<String> uagHeaderNames,
											  final String encoding)
	{
		super(request);
		this.uagHeaderNames = uagHeaderNames;
		this.encoding = encoding;
	}

	/**
	 * Returns the decoded header value in a case if the specified headerName is one of UAG-specific headers.
	 *
	 * @param headerName header name
	 * @return the decoded header value if it comes from UAG or raw header value otherwise.
	 */
	@Override
	public String getHeader(final String headerName)
	{
		String rawValue = super.getHeader(headerName);
		if (uagHeaderNames.contains(headerName.toLowerCase()))
		{
			if (!StringUtils.isEmpty(rawValue))
			{
				try
				{
					return URLDecoder.decode(rawValue, encoding);
				}
				catch (UnsupportedEncodingException e)
				{
					error(LOG, "Invalid URLEncoder encoding: %s. Check the %s configuration key.",
							encoding, ShopConfigurationService.CONFIG_KEY_UAG_HEADER_ENCODING);
				}
			}
		}
		return rawValue;
	}

}
