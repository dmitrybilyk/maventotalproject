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
package com.cgi.pacoshop.mock.uag.service.impl;


import com.cgi.pacoshop.mock.uag.MockUAGUtil;
import com.cgi.pacoshop.mock.uag.service.api.MockUAGService;
import org.springframework.stereotype.Service;

/**
 * Simple REST mock to calculate the deeplink fingerprint value.
 *
 * @module mocks
 * @version 1.0v Apr 03, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see <a href="https://wiki.hybris.com/">wiki.hybris.com</a>
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@Service("MockUAGService")
public class MockUAGServiceImpl implements MockUAGService
{
	/**
	 * Calculates the deeplink fingerprint value.
	 *
	 * @param url used to calculate the SHA1 checksum value in form of HEX String (fingerprint).
	 *            URL should contain the 'fingerprintsecretno' param as mandatory string.
	 * @return plain text response with an fingerprint hex value string.
	 */
	@Override
	public String getFingerprint(final String url)
	{
		return MockUAGUtil.calculateDeeplinkFingerprint(url);
	}
}
