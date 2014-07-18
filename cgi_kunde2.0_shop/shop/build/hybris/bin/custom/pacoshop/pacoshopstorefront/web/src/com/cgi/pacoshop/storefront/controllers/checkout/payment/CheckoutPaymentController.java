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
package com.cgi.pacoshop.storefront.controllers.checkout.payment;

import com.cgi.hybris.payment.core.constants.HybrispaymentcoreConstants;
import com.cgi.hybris.payment.core.enums.PaymentPspStatusTypeEnum;
import com.cgi.hybris.payment.core.facades.PaymentExtFacade;
import com.cgi.hybris.payment.core.services.PaymentExtException;
import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import com.cgi.pacoshop.core.exceptions.dynamic.CartNotExistException;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.util.LogHelper;
import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;
import com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade;
import com.cgi.pacoshop.storefront.controllers.pages.checkout.AbstractCheckoutController;
import com.cgi.pacoshop.storefront.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.util.localization.Localization;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static com.cgi.pacoshop.core.util.LogHelper.*;


/**
 * Payment Extensions Stuff
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@Controller
@RequestMapping(value = "/checkout/payment/storefront")
public class CheckoutPaymentController extends AbstractCheckoutController
{
	private static final String THANK_YOU_PAGE_URL = "/thankyou";

	private static final String DYNAMIC_CHECKOUT_SUMMARY_PAGE_URL = "/dynamiccheckout/summary";

	private static final String PARAM_ORDERID = "orderguid";

	private static final String MODEL_REDIRECT_URL = "redirecturl";

	private static final String REQUEST_HEADER_USER_AGENT = "user-agent";

	private static final String DYNAMIC_CHECKOUT_URL = "/dynamiccheckout";

	@Resource
	private PaymentExtFacade paymentExtFacade;


	@Resource
	private ConfigurationService configurationService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private CheckoutCompletionFacade checkoutCompletionFacade;

	/**
	 * Redirect from PSP to shop on failed payment.
	 *
	 * @param transactionId - transaction id number
	 * @param request - request
	 * @param redirectModel  - redirect model attributes
	 * @return redirect to checkout summary
	 */
	@RequestMapping(value = "/failure", method = RequestMethod.GET)
	public String paymentFailure(@RequestParam("id") final String transactionId, final HttpServletRequest request,
										  final RedirectAttributes redirectModel)
	{
		String message = "";
		warn(LOG, "returned from PSP with payment failed.");

		try
		{
			message = checkPaymentStatus(transactionId, PaymentPspStatusTypeEnum.FAILURE,
												  request.getHeader(REQUEST_HEADER_USER_AGENT));
		}
		catch (final Exception e)
		{
			error(LOG, "Error in payment failure", e);
		}

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
												 Localization.getLocalizedString(HybrispaymentcoreConstants.MessageKey.PAYMENT_FAILED),
												 new Object[]{message});
		return REDIRECT_PREFIX + DYNAMIC_CHECKOUT_SUMMARY_PAGE_URL;
	}

	/**
	 * redirect from PSP to shop on cancel from user.
	 *
	 * @param transactionId - transaction id number
	 * @param request - request
	 * @param redirectModel  - redirect model attributes
	 *
	 * @return redirect to checkout summary
	 */
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String paymentCanceled(@RequestParam("id") final String transactionId, final HttpServletRequest request,
											final RedirectAttributes redirectModel)
	{
		warn(LOG, "returned from PSP with payment cancelled.");
		try
		{
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
													 Localization.getLocalizedString(HybrispaymentcoreConstants.MessageKey.PAYMENT_CANCELED));
			checkPaymentStatus(transactionId, PaymentPspStatusTypeEnum.CANCELED, request.getHeader(REQUEST_HEADER_USER_AGENT));
		}
		catch (final Exception e)
		{
			error(LOG, "Error is payment cancel", e);
		}
		return REDIRECT_PREFIX + DYNAMIC_CHECKOUT_SUMMARY_PAGE_URL;
	}

	/**
	 * redirect from PSP to shop on success.
	 *
	 * @param transactionId - transaction unique id
	 * @param request - request
	 * @param redirectModel - redirect model attributes
	 * @param session - http session
	 *
	 * @return redirect to order confirmation page
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String paymentSuccess(@RequestParam("id")
										  final String transactionId,
										  final HttpServletRequest request,
										  final RedirectAttributes redirectModel,
										  final HttpSession session)
	{
		String result = null;
		info(LOG, "returned from PSP with Payment success.");
		try
		{
			checkPaymentStatus(transactionId, PaymentPspStatusTypeEnum.SUCCESS, request.getHeader(REQUEST_HEADER_USER_AGENT));
			final OrderData orderData = checkoutCompletionFacade.submitOrder(session);
			paymentExtFacade.updateOrderOnStatus(transactionId, orderData.getCode());
			String orderRedirectUrl = orderData.getRedirectUrl();

			result = REDIRECT_PREFIX + checkoutCompletionFacade.getCompletionRedirectUrl(orderData, orderRedirectUrl);
		}
		catch (final InvalidCartException | PaymentExtException e)
		{
			error(LOG, "Error in payment success", e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
													 HybrispaymentcoreConstants.MessageKey.PAYMENT_FAILED);
			return REDIRECT_PREFIX + DYNAMIC_CHECKOUT_SUMMARY_PAGE_URL;
		}

		return result;
	}


	/**
	 * Method checks the payment status.
	 * depending on configuration
	 * defined in the properties file
	 *
	 *
	 * @param transactionId
	 * @param paymentPspStatusTypeEnum
	 * @return payment status
	 */
	private String checkPaymentStatus(final String transactionId, final PaymentPspStatusTypeEnum paymentPspStatusTypeEnum,
												 final String userAgent)
	{
		boolean checkOfPaymentStatusIsNeeded = Boolean.parseBoolean(getProperty(
              ShopConfigurationService.CONFIG_KEY_WIRECARD_CALLBACK_CHECK_STATUS));
		if (checkOfPaymentStatusIsNeeded)
		{
			return paymentExtFacade.checkPaymentStatus(transactionId, paymentPspStatusTypeEnum, userAgent);
		}

		return StringUtils.EMPTY;
	}

	/**
	 * @param paymentExtFacade
	 *            the paymentExtFacade to set
	 */
	public void setPaymentExtFacade(final PaymentExtFacade paymentExtFacade)
	{
		this.paymentExtFacade = paymentExtFacade;
	}

	private String getProperty(final String property)
	{
		return configurationService.getConfiguration().getString(property);
	}

}
