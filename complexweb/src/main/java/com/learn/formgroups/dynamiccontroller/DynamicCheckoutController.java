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
package com.learn.formgroups.dynamiccontroller;


import com.learn.formgroups.CartContentsFragmentDTO;
import com.learn.formgroups.CheckoutStep;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.lang.StringUtils.isNotEmpty;


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
@SessionAttributes(value = {"orderid", "redirecturl" }, types = {CartContentsFragmentDTO.class, String.class })
public class DynamicCheckoutController extends AbstractCheckoutController
{
	private static final String ERROR_PAGE_URL       = "/error";
	private static final String ATTR_EXCEPTION       = "exception";
	private static final String CURRENT_STEP_ATTR    = "currentstep";
	private static final int    MONTHS_NUMBER        = 12;
	private static final int    HALF_OF_YEAR         = 6;
	private static final String DYNAMIC_CHECKOUT_URL = "/dynamiccheckout";
	private static final String THANK_YOU_PAGE_URL   = "/thankyou";
	private static final String FORM                 = "form";
	private static final String MODEL_REDIRECT_URL   = "redirecturl";
	private static final String PARAM_ORDERID        = "orderguid";
	private static final String ERRORPAGE_NAME       = "errorpage";


//	@Resource
//	private DynamicCheckoutFacade dynamicCheckoutFacade;
//
//	@Resource
//	private CommerceCartService commerceCartService;
//
//	@Resource
//	private CartService cartService;
//
//	@Resource (name = "paymentExtFacade")
//	private PaymentExtFacade paymentExtFacade;


	/**
	 * Create entity.
	 *
	 * @param cartContentsFragmentDTO the cart contents fragment dTO
	 * @return the form dTO
	 */
	@ModelAttribute
	public CartContentsFragmentDTO createCartContentsDTO(final CartContentsFragmentDTO cartContentsFragmentDTO)
	{
//		return dynamicCheckoutFacade.populateCartContentsFragment(cartContentsFragmentDTO, "");
        return cartContentsFragmentDTO;
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
//		dynamicCheckoutFacade.populateCommonStepData(model);
//		dynamicCheckoutFacade.populateSpecificStepData(model);
		final CheckoutStep checkoutStep = (CheckoutStep) model.asMap().get(CURRENT_STEP_ATTR);
//		final Map<String, Object> pageModelValues = dynamicCheckoutFacade.populatePageModelData(checkoutStep);
//		model.addAllAttributes(pageModelValues);
//		final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(checkoutStep.getCMSPageLabel());
//		setUpMetaDataForContentPage(model, contentPageForLabelOrId);
//		return getViewForPage(contentPageForLabelOrId);
        return "userDataPagee";
	}

	/**
	 * Redirect to a specific checkout step.
	 *
	 * @param model - mvc model
	 * @param checkoutStepName - name
	 * @return  - cms page id linked to the checkout step
//	 * @throws de.hybris.platform.cms2.exceptions.CMSItemNotFoundException - cms exception
	 */
	@RequestMapping(value = "/dynamiccheckout/{checkoutStepName}", method = RequestMethod.GET)
	public String displaySpecificStep(final Model model, final @PathVariable @NotNull String checkoutStepName)


	{
//		debug(LOG, "Specific checkout step with id [%s] requested", checkoutStepName);
//		Assert.notNull(dynamicCheckoutFacade, "DynamicCheckoutFacade isn't injected");
		final List<CheckoutStep> neededSteps = dynamicCheckoutFacade.getNeededSteps();
		//todo re-desing might be needed to always display first not completed step.
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
		dynamicCheckoutFacade.populateCommonStepData(model);
		model.addAttribute("currentstep", nextStep);

		dynamicCheckoutFacade.populateSpecificStepDataForSpecificStep(model, nextStep);
		final Map<String, Object> pageModelValues = dynamicCheckoutFacade.populatePageModelData(nextStep);
		model.addAllAttributes(pageModelValues);
		final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(nextStep.getCMSPageLabel());
		setUpMetaDataForContentPage(model, contentPageForLabelOrId);
		return getViewForPage(contentPageForLabelOrId);
	}

	/**
	 * saves billing address.
	 *
	 * @return  - redirects to DCF
	 */

	@RequestMapping("/saveBillingAddress")
	public String saveBillingAddress()
	{
		dynamicCheckoutFacade.saveBillingAddress();
		return REDIRECT_PREFIX + DYNAMIC_CHECKOUT_URL;
	}

	/**
	 * Create entity.
	 *
	 * @param stepName - step name
	 * @return  - form dto.
	 */
	@ModelAttribute("form")
	public FormDTO createEntity(final String stepName)
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
		final CheckoutStep currentCheckoutStep = dynamicCheckoutFacade.getCheckoutStep(formDTO);
		if (!dynamicCheckoutFacade.validateForm(formDTO, currentCheckoutStep, bindingResult))
		{
			dynamicCheckoutFacade.populateCommonStepData(model);
			dynamicCheckoutFacade.populateSpecificStepDataForSpecificStep(model, currentCheckoutStep);
			final Map<String, Object> pageModelValues = dynamicCheckoutFacade.populatePageModelData(currentCheckoutStep);
			model.addAllAttributes(pageModelValues);
			final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(currentCheckoutStep.getCMSPageLabel());
			setUpMetaDataForContentPage(model, contentPageForLabelOrId);
			model.addAttribute(BindingResult.class.getName() + ".form", bindingResult);
			model.addAttribute("displayErrors", bindingResult.hasErrors());
			return getViewForPage(contentPageForLabelOrId);
		}
		dynamicCheckoutFacade.saveFormToCart(formDTO);
		commerceCartService.recalculateCart(cartService.getSessionCart());

		//********* Triggering the payment *********//
		if (currentCheckoutStep.isLastStep())
		{

			try
			{
				if (cartService.getSessionCart().getTotalPrice() > 0)
				{
					final String paymentUrl = paymentExtFacade.initPayment(request.getRemoteAddr(), request.getHeader("user-agent"));
					if (paymentUrl != null)
					{
						return REDIRECT_PREFIX + paymentUrl;
					}
				}
				final OrderData orderData = dynamicCheckoutFacade.submitOrder(session);
				boolean cartContainsOnlineArticleProduct = dynamicCheckoutFacade.cartContainsOnlineArticleProduct(orderData);
				String redirectUrl = (String) model.asMap().get(MODEL_REDIRECT_URL);
				if (isNotEmpty(redirectUrl) && cartContainsOnlineArticleProduct)
				{
					return REDIRECT_PREFIX + redirectUrl;
				}
				StringBuilder thankyouPageurl = new StringBuilder();
				thankyouPageurl.append(REDIRECT_PREFIX);
				thankyouPageurl.append(THANK_YOU_PAGE_URL);
				thankyouPageurl.append("?");
				thankyouPageurl.append(PARAM_ORDERID);
				thankyouPageurl.append("=");
				thankyouPageurl.append(orderData.getGuid());
				thankyouPageurl.append("&");
				thankyouPageurl.append(MODEL_REDIRECT_URL);
				thankyouPageurl.append("=");
				thankyouPageurl.append(redirectUrl);

				return thankyouPageurl.toString();
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
		}
		return REDIRECT_PREFIX + DYNAMIC_CHECKOUT_URL;
	}

	/**
	 * Thank you page.
	 *
	 * redirecturl empty(only for non onlinearticles) --> show thankyou
	 * redirect url not empty --->
	 * a) online article --> redirecturl
	 * b) others --> show thankyou
	 *
	 * @param model the model
	 * @param orderId the order id
	 * @param redirectUrl the redirect url
	 * @return thank you page.
	 * @throws CMSItemNotFoundException the cMS item not found exception
	 */

	@RequestMapping(value = THANK_YOU_PAGE_URL, method = RequestMethod.GET)
	public String thankYouPage(final Model model, final @RequestParam(PARAM_ORDERID) String orderId,
										final @RequestParam(MODEL_REDIRECT_URL) String redirectUrl)
			  throws CMSItemNotFoundException
	{
		String returnView = "";
		info(LOG, "Thank you page is displayed");
		CartContentsFragmentDTO cartContentsFragmentDTO = new CartContentsFragmentDTO();
		dynamicCheckoutFacade.populateCartContentsFragment(cartContentsFragmentDTO, orderId);
		model.addAttribute(cartContentsFragmentDTO);
		final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(dynamicCheckoutFacade.getThankYouCMSLabel());
		returnView = getViewForPage(contentPageForLabelOrId);
		return returnView;
	}


	/**
	 * Create titles.
	 *
	 * @return  - collection of titles
	 */
	@ModelAttribute("titles")
	public Collection<TitleData> getTitles()
	{
		return getUserFacade().getTitles();
	}


	/**
	 * Create countries.
	 *
	 * @return  - collection of countries
	 */
	@ModelAttribute("countries")
	public Collection<CountryData> getCountries()
	{
		return getCheckoutFacade().getDeliveryCountries();
	}


	/**
	 * Create country data map.
	 *
	 * @return  - country data map
	 */
	@ModelAttribute("countryDataMap")
	public Map<String, CountryData> getCountryDataMap()
	{
		final Map<String, CountryData> countryDataMap = new HashMap<>();
		for (final CountryData countryData : getCountries())
		{
			countryDataMap.put(countryData.getIsocode(), countryData);
		}
		return countryDataMap;
	}


	/**
	 * Create billing countries.
	 *
	 * @return  - billing countries list
	 */
	@ModelAttribute("billingCountries")
	public Collection<CountryData> getBillingCountries()
	{
		return getCheckoutFacade().getBillingCountries();
	}


	/**
	 * Create months list.
	 *
	 * @return  - months list
	 */
	@ModelAttribute("months")
	public List<SelectOption> getMonths()
	{
		final List<SelectOption> months = new ArrayList<>();
		final String nullNumber = "0";
		for (Integer currentMonth = 1; currentMonth <= MONTHS_NUMBER; currentMonth++)
		{
			final StringBuilder monthOptionValue = new StringBuilder();
			final int ten = 10;
			if (currentMonth < ten)
			{
				monthOptionValue.append(nullNumber);
			}
			monthOptionValue.append(currentMonth);
			months.add(new SelectOption(currentMonth.toString(), monthOptionValue.toString()));

		}

		return months;
	}


	/**
	 * Create start years list.
	 *
	 * @return  - years list
	 */
	@ModelAttribute("startYears")
	public List<SelectOption> getStartYears()
	{
		final List<SelectOption> startYears = new ArrayList<>();
		final Calendar calender = new GregorianCalendar();
		for (int i = calender.get(Calendar.YEAR); i > (calender.get(Calendar.YEAR) - HALF_OF_YEAR); i--)
		{
			startYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
		}

		return startYears;
	}


	/**
	 * Create expire years list.
	 *
	 * @return  - years list
	 */
	@ModelAttribute("expiryYears")
	public List<SelectOption> getExpiryYears()
	{
		final List<SelectOption> expiryYears = new ArrayList<>();
		final Calendar calender = new GregorianCalendar();

		final int elevenNumber = 11;
		for (int i = calender.get(Calendar.YEAR); i < (calender.get(Calendar.YEAR) + elevenNumber); i++)
		{
			expiryYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
		}

		return expiryYears;
	}

	/**
	 * It processes errors that can occur in the deeplink. The exceptions have a common parent class.
	 *
	 *
	 * @param exception the exception
	 * @param request the request
	 * @return error page cms label
	 * @throws java.io.IOException the iO exception
	 * @throws CMSItemNotFoundException the cms exception
	 * @see
	 */
	@ExceptionHandler(PacoShopWebException.class)
	public String handlePacoshopWebException(final PacoShopWebException exception,
														  final HttpServletRequest request) throws IOException, CMSItemNotFoundException
	{
		final String message = String.format("Exception %s was raised. Forwarding to errorpage", exception.getClass().getName());
		request.setAttribute(ATTR_EXCEPTION, exception);
		LogHelper.debug(LOG, message);
		return FORWARD_PREFIX + ERROR_PAGE_URL;
	}

}
