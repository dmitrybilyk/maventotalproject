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
package com.cgi.pacoshop.storefront.controllers.checkout.dynamic;


import com.cgi.hybris.payment.core.facades.PaymentExtFacade;
import com.cgi.hybris.payment.core.services.PaymentExtException;
import com.cgi.pacoshop.core.checkout.dynamic.CartContentsFragmentDTO;
import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import com.cgi.pacoshop.core.exceptions.dynamic.CartNotExistException;
import com.cgi.pacoshop.core.util.LogHelper;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.info;

import com.cgi.pacoshop.facades.checkout.dynamic.CartContentsPopulationFacade;
import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;
import com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade;
import com.cgi.pacoshop.storefront.controllers.pages.checkout.AbstractCheckoutController;
import com.cgi.pacoshop.storefront.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.exceptions.CalculationException;
import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import de.hybris.platform.util.localization.Localization;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Dynamic Checkout FrameWork entry point Accepts 3 types of requests: Proceed to the next step Display a specific
 * checkout step Save the data entered in a checkout step IMPORTANT!!!!! The session cart is cleared after the order is
 * placed
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@Controller
@SessionAttributes(value = {"orderid", "redirecturl" }, types = {CartContentsFragmentDTO.class, java.lang.String.class })
public class DynamicCheckoutController extends AbstractCheckoutController
{
	private static final String CURRENT_STEP_ATTR    = "currentstep";
	private static final String DYNAMIC_CHECKOUT_URL = "/dynamiccheckout";
	private static final String MODEL_REDIRECT_URL   = "redirecturl";
	private static final String BINDING_RESULT_FORM  = BindingResult.class.getName() + ".form";
	private static final String DISPLAY_ERRORS       = "displayErrors";
	private static final String USER_AGENT           = "user-agent";

	@Resource
	private DynamicCheckoutFacade dynamicCheckoutFacade;

	@Resource
	private CartService cartService;

	@Resource
	private CheckoutCompletionFacade checkoutCompletionFacade;

	@Resource
	private CartContentsPopulationFacade cartContentsPopulationFacade;


	/**
	 * Create CartContentsFragmentDTO for product information block.
	 * Note: this is not ideal design solution to keep this call in DynamicCheckoutController, but
	 * seems represents good compromise.
	 *
	 * @param cartContentsFragmentDTO the cart contents fragment dTO
	 * @return the form dTO
	 */
	@ModelAttribute
	public CartContentsFragmentDTO createCartContentsDTO(final CartContentsFragmentDTO cartContentsFragmentDTO)
	{
		return cartContentsPopulationFacade.populateCartContentsFragment(cartContentsFragmentDTO, "");
	}

	/**
	 * Find the next checkout step to be displayed.
	 *
	 * @param model - - mvc model.
	 * @return cms id of the next step
	 * @throws Exception - exception
	 */
	@RequestMapping(value = DYNAMIC_CHECKOUT_URL, method = RequestMethod.GET)
	public String proceedToNextStep(final Model model) throws Exception
	{
		List<CheckoutStep> neededSteps = dynamicCheckoutFacade.getNeededSteps();
		final CheckoutStep currentStep = (CheckoutStep) model.asMap().get(CURRENT_STEP_ATTR);

		final CheckoutStep step =
				  currentStep != null ? currentStep : dynamicCheckoutFacade.getNextDisplayedStep(neededSteps);

		return proceedToStep(model, neededSteps, step);
	}

	/**
	 * Redirect to a specific checkout step.
	 *
	 * @param model - mvc model
	 * @param checkoutStepName - name
	 * @return  - cms page id linked to the checkout step
	 * @throws de.hybris.platform.cms2.exceptions.CMSItemNotFoundException - cms exception
	 */
	@RequestMapping(value = "/dynamiccheckout/{checkoutStepName}", method = RequestMethod.GET)
	public String displaySpecificStep(final Model model, final @PathVariable @NotNull String checkoutStepName)
			  throws CMSItemNotFoundException

	{
		debug(LOG, "Specific checkout step with id [%s] requested", checkoutStepName);
		final List<CheckoutStep> neededSteps = dynamicCheckoutFacade.getNeededSteps();

		debug(LOG, "checkoutSteps needed: [%s]", neededSteps);
		CheckoutStep nextStep = null;
		for (final CheckoutStep step : neededSteps)
		{
			if (!step.isCompleted() || step.getStepName().equals(checkoutStepName))
			{
				debug(LOG, "checkout steps found [%s]", checkoutStepName);
				nextStep = step;
				nextStep.setCompleted(false);
				break;
			}
		}
		if (nextStep == null)
		{
			throw new CMSItemNotFoundException(String.format("checkout steps not found:  [%s]", checkoutStepName));
		}
		return proceedToStep(model, neededSteps, nextStep);
	}

	/**
	 * Create FormDTO for particular step identifiedy b.
	 *
	 * @param stepName - step name
	 * @return  - form dto.
	 */
	@ModelAttribute("form")
	public FormDTO getFormDTOForStep(final String stepName)
	{
		if (isNotEmpty(stepName))
		{
			return dynamicCheckoutFacade.getStep(stepName).getForm();
		}
		return null;
	}

	/**
	 * Form submission.
	 *
	 * @param formDTO - form dto
	 * @param bindingResult - binding result
	 * @param model - mvc model
	 * @param request - http request
	 * @param session - session
	 * @param redirectModel - redirect attributes
	 * @return  - redirect to proceed to the next step
	 * @throws CMSItemNotFoundException - exception
	 * @throws InvalidCartException - exception
	 * @throws CalculationException - exception
	 */

	@RequestMapping(value = "/formsubmit", method = RequestMethod.POST)
	public String formSubmission(@ModelAttribute("form")
										  final FormDTO formDTO,
										  final BindingResult bindingResult,
										  final Model model,
										  final HttpServletRequest request,
										  final HttpSession session,
										  final RedirectAttributes redirectModel) throws CMSItemNotFoundException,
																										 InvalidCartException, CalculationException
	{
		String result = null;

		final CheckoutStep currentCheckoutStep = dynamicCheckoutFacade.getCheckoutStep(formDTO);
		boolean isValid = dynamicCheckoutFacade.validateForm(formDTO, currentCheckoutStep, bindingResult);
		if (isValid)
		{
			dynamicCheckoutFacade.saveFormToCart(formDTO);

			// Trigger completion of checkout
			if (currentCheckoutStep.isLastStep())
			{
				return completeCheckout(request, model, redirectModel);
			}

			result = REDIRECT_PREFIX + DYNAMIC_CHECKOUT_URL;
		}
		else
		{
			// disable reload data from Cart if validation has failed
			currentCheckoutStep.setPopulateFromCart(false);
			model.addAttribute(BINDING_RESULT_FORM, bindingResult);
			model.addAttribute(DISPLAY_ERRORS, bindingResult.hasErrors());

			List<CheckoutStep> neededSteps = dynamicCheckoutFacade.getNeededSteps();
			result = proceedToStep(model, neededSteps, currentCheckoutStep);
		}

		return result;
	}

	private String proceedToStep(final Model model, final List<CheckoutStep> neededSteps, final CheckoutStep step)
			  throws CMSItemNotFoundException
	{
		Assert.notNull(neededSteps, "neededSteps must be provided");
		Assert.notEmpty(neededSteps, "neededSteps must not be empty");
		Assert.notNull(step, "step must be provided");

		final Map<String, Object> pageModelValues = dynamicCheckoutFacade.populatePageModelData(neededSteps, step);
		model.addAllAttributes(pageModelValues);
		storeCmsPageInModel(model, getContentPageForLabelOrId(step.getCMSPageLabel()));
		final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(step.getCMSPageLabel());
		setUpMetaDataForContentPage(model, contentPageForLabelOrId);
		return getViewForPage(contentPageForLabelOrId);
	}

	private String completeCheckout(final HttpServletRequest request, final Model model, final RedirectAttributes redirectModel)
			  throws InvalidCartException
	{
		String result = REDIRECT_PREFIX + DYNAMIC_CHECKOUT_URL;

		try
		{
			String paymentUrl = checkoutCompletionFacade.submitPayment(
					  cartService.getSessionCart(), request.getRemoteAddr(), request.getHeader(USER_AGENT));
			if (paymentUrl != null)
			{
				result = REDIRECT_PREFIX + paymentUrl;
			}
			else
			{
				if (!checkoutCompletionFacade.hasCheckoutCart())
				{
					throw new CartNotExistException("Can't do submit with empty cart");
				}
				final OrderData orderData = checkoutCompletionFacade.submitOrder(request.getSession());
				String modelRedirectUrl = (String) model.asMap().get(MODEL_REDIRECT_URL);
				result = REDIRECT_PREFIX + checkoutCompletionFacade.getCompletionRedirectUrl(orderData, modelRedirectUrl);
			}
		}
		catch (final PaymentExtException e)
		{
			LOG.error("payment initialisation failed", e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
													 Localization.getLocalizedString("checkout.error.authorization.failed"));
		}
		catch (final Exception e)
		{
			LOG.error("Failed to place Order", e);
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.ERROR_MESSAGES_HOLDER,
													 Localization.getLocalizedString("checkout.placeOrder.failed"));
		}

		return result;
	}
}
