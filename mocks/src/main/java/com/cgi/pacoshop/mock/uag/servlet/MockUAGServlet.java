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
package com.cgi.pacoshop.mock.uag.servlet;

import com.cgi.pacoshop.mock.uag.MockUAGUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MockUAGServlet class feeds the UAG Mock filter with the UserId and TypeID parameters in a request.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Azarenkov Eugene <azarenkov.eugene@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 15, 2014
 * @module UAG_mock
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class MockUAGServlet extends HttpServlet
{
	private final static Logger LOGGER = Logger.getLogger(MockUAGServlet.class.getName());

	@Override
	public void init() throws ServletException
	{
		LOGGER.debug("*****************UAG************************");
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
	{
		final String[] redirectUrls = req.getParameterValues(MockUAGUtil.FORM_ITEM_REDIRECT_URL);
		final String uagEmail = req.getParameter(MockUAGUtil.FORM_ITEM_USER_EMAIL);
		final String uagUserIdForm = req.getParameter(MockUAGUtil.FORM_ITEM_USER_ID);
		final String uagIdType = req.getParameter(MockUAGUtil.FORM_ITEM_USER_TYPE);
		final String fingerprintSecretNo = req.getParameter(MockUAGUtil.FORM_ITEM_FINGERPRINT_SECRET_NO);
		final String wsToken = req.getParameter(MockUAGUtil.FORM_ITEM_WS_TOKEN);
		String shopTargetUrl = getLastURL(redirectUrls);
		LOGGER.info("ShopTargetURL: " + shopTargetUrl);

		StringBuilder redirectToUrl = new StringBuilder(shopTargetUrl);
		//determine a parameters separator character
		redirectToUrl.append(shopTargetUrl.contains("?") ? "&" : "?");
		redirectToUrl.append(MockUAGUtil.ULR_PARAM_USER_ID + "=").append(uagUserIdForm);
		redirectToUrl.append("&" + MockUAGUtil.ULR_PARAM_USER_TYPE + "=").append(uagIdType);
		redirectToUrl.append("&" + MockUAGUtil.ULR_PARAM_USER_EMAIL + "=").append(uagEmail);
		if (!wsToken.isEmpty())
		{
			redirectToUrl.append("&" + MockUAGUtil.ULR_PARAM_WS_TOKEN + "=").append(wsToken);
		}
		redirectToUrl.append("&" + MockUAGUtil.ULR_PARAM_FINGERPRINT_SECRET_NO + "=").append(fingerprintSecretNo);
		String fingerprint = MockUAGUtil.calculateDeeplinkFingerprint(redirectToUrl.toString());
		redirectToUrl.append("&" + MockUAGUtil.ULR_PARAM_FINGERPRINT + "=").append(fingerprint);

		resp.sendRedirect(redirectToUrl.toString());
	}

	private String getLastURL(final String[] redirectUrls)
	{
		for (int i = redirectUrls.length - 1; i > 0; i--)
		{
			if (redirectUrls[i] != null && !redirectUrls[i].trim().equals(""))
			{
				return redirectUrls[i];
			}
		}
		return redirectUrls[0];

	}
}
