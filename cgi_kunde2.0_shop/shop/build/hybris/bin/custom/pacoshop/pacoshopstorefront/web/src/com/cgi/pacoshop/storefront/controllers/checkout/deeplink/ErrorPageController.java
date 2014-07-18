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
package com.cgi.pacoshop.storefront.controllers.checkout.deeplink;


import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import com.cgi.pacoshop.core.util.LogHelper;
import com.cgi.pacoshop.storefront.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * The error page controller. Done in hybris way.
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@Controller
@RequestMapping (method = {RequestMethod.GET, RequestMethod.POST })

public class ErrorPageController extends AbstractPageController
{
	private static final String ERROR_PAGE_TEMPLATE_NAME = "errorPage";
	private static final String ATTR_EXCEPTION           = "exception";

	/**
	 * It processes errors that can occur in the deeplink. The exceptions have a common parent class.
	 *
	 * @param response the response
	 * @param request the request
	 * @param model the model
	 * @return error page cms label
	 * @throws java.io.IOException the iO exception
	 * @throws CMSItemNotFoundException the cms exception
	 * @see
	 */
	@RequestMapping ("/error")
	public String showErrorPage(final HttpServletResponse response,
										 final HttpServletRequest request, final Model model) throws IOException, CMSItemNotFoundException
	{
		Object exception = request.getAttribute(ATTR_EXCEPTION);
		if (exception instanceof PacoShopWebException)
		{
			PacoShopWebException pacoShopWebException = (PacoShopWebException) exception;
			final String message = String.format("Status code: %s. Reason: %s", pacoShopWebException.getHttpStatus(),
															 pacoShopWebException.getMessage());
			LogHelper.error(LOG, message, exception);
			model.addAttribute("exceptionMessage", pacoShopWebException.getMessage());
			model.addAttribute("familyExceptionMessage", pacoShopWebException.getFamilyExceptionMessage());
			model.addAttribute("additionalInfo", pacoShopWebException.getAdditionalInformation());
			model.addAttribute("exceptionProductId", pacoShopWebException.getProductId());
			storeCmsPageInModel(model, getContentPageForLabelOrId(ERROR_PAGE_TEMPLATE_NAME));
			int httpStatus = pacoShopWebException.getHttpStatus();
			model.addAttribute("httpStatus", httpStatus);
			response.setStatus(httpStatus);
		}

		final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(ERROR_PAGE_TEMPLATE_NAME);
		return getViewForPage(contentPageForLabelOrId);
	}

}
