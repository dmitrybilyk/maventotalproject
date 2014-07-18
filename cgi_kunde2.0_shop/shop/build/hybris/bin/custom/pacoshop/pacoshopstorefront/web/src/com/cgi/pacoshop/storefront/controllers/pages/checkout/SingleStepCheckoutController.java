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
package com.cgi.pacoshop.storefront.controllers.pages.checkout;

import com.cgi.pacoshop.facades.user.PacoUserFacade;
import com.cgi.pacoshop.facades.user.data.Title2Data;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CardTypeData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.CartModificationData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.ZoneDeliveryModeData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.order.InvalidCartException;
import com.cgi.pacoshop.core.enums.CheckoutFlowEnum;
import com.cgi.pacoshop.storefront.annotations.RequireHardLogIn;
import com.cgi.pacoshop.storefront.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import com.cgi.pacoshop.storefront.constants.WebConstants;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import com.cgi.pacoshop.storefront.controllers.util.GlobalMessages;
import com.cgi.pacoshop.storefront.forms.AddressForm;
import com.cgi.pacoshop.storefront.forms.PaymentDetailsForm;
import com.cgi.pacoshop.storefront.forms.PlaceOrderForm;
import com.cgi.pacoshop.storefront.forms.validation.PaymentDetailsValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * SingleStepCheckoutController
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/checkout/single")
public class SingleStepCheckoutController extends AbstractCheckoutController
{
	protected static final Logger LOG = Logger.getLogger(SingleStepCheckoutController.class);

	private static final String SINGLE_STEP_CHECKOUT_SUMMARY_CMS_PAGE = "singleStepCheckoutSummaryPage";

	@Resource(name = "userFacade")
	private PacoUserFacade userFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private CheckoutFacade checkoutFacade;

	@Resource(name = "paymentDetailsValidator")
	private PaymentDetailsValidator paymentDetailsValidator;

	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "contentPageBreadcrumbBuilder")
	private ContentPageBreadcrumbBuilder contentPageBreadcrumbBuilder;

	@ModelAttribute("titles")
	public Collection<TitleData> getTitles()
	{
		return userFacade.getTitles();
	}

	@ModelAttribute("titles2")
	public Collection<Title2Data> getTitles2()
	{
		return userFacade.getTitles2();
	}

	@ModelAttribute("countries")
	public Collection<CountryData> getCountries()
	{
		return checkoutFacade.getDeliveryCountries();
	}

	@ModelAttribute("billingCountries")
	public Collection<CountryData> getBillingCountries()
	{
		return checkoutFacade.getBillingCountries();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String checkoutSummary()
	{
		if (hasValidCart() && CheckoutFlowEnum.SINGLE.equals(getCheckoutFlowFacade().getCheckoutFlow()))
		{
			return REDIRECT_PREFIX + "/checkout/single/summary";
		}
		return REDIRECT_PREFIX + "/cart";
	}

	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	@RequireHardLogIn
	public String checkoutSummary(final Model model, final RedirectAttributes redirectModel) throws CMSItemNotFoundException,
																																	CommerceCartModificationException
	{
		if (!hasValidCart() || CheckoutFlowEnum.MULTISTEP.equals(getCheckoutFlowFacade().getCheckoutFlow()))
		{
			// no items in the cart
			LOG.info("Missing, empty or unsupported cart");
			return FORWARD_PREFIX + "/cart";
		}

		//Validate the cart
		final List<CartModificationData> modifications = cartFacade.validateCartData();
		if (!modifications.isEmpty())
		{
			redirectModel.addFlashAttribute("validationData", modifications);

			// Invalid cart. Bounce back to the cart page.
			return REDIRECT_PREFIX + "/cart?validate=false";
		}

		// Try to set default delivery address and delivery mode
		checkoutFacade.setDeliveryAddressIfAvailable();
		checkoutFacade.setDeliveryModeIfAvailable();
		checkoutFacade.setPaymentInfoIfAvailable();

		final CartData cartData = checkoutFacade.getCheckoutCart();
		if (cartData.getEntries() != null && !cartData.getEntries().isEmpty())
		{
			for (final OrderEntryData entry : cartData.getEntries())
			{
				final String productCode = entry.getProduct().getCode();
				final ProductData product = productFacade.getProductForCodeAndOptions(productCode,
																											 Arrays.asList(ProductOption.BASIC,
																																ProductOption.PRICE));
				entry.setProduct(product);
			}
		}

		model.addAttribute("cartData", cartData);
		model.addAttribute("allItems", cartData.getEntries());
		model.addAttribute("deliveryAddress", cartData.getDeliveryAddress());
		model.addAttribute("deliveryMode", cartData.getDeliveryMode());
		model.addAttribute("paymentInfo", cartData.getPaymentInfo());

		model.addAttribute(new AddressForm());
		model.addAttribute(new PaymentDetailsForm());
		model.addAttribute(new PlaceOrderForm());

		storeCmsPageInModel(model, getContentPageForLabelOrId(SINGLE_STEP_CHECKOUT_SUMMARY_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(SINGLE_STEP_CHECKOUT_SUMMARY_CMS_PAGE));
		model.addAttribute("metaRobots", "no-index,no-follow");
		return ControllerConstants.Views.Pages.SingleStepCheckout.CheckoutSummaryPage;
	}

	@ResponseBody
	@RequestMapping(value = "/summary/getCheckoutCart.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public CartData getCheckoutCart()
	{
		return checkoutFacade.getCheckoutCart();
	}

	@ResponseBody
	@RequestMapping(value = "/summary/getDeliveryAddresses.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public List<? extends AddressData> getDeliveryAddresses()
	{
		final List<? extends AddressData> deliveryAddresses = checkoutFacade.getSupportedDeliveryAddresses(true);
		if (deliveryAddresses == null)
		{
			return Collections.<AddressData>emptyList();
		}
		for (final AddressData address : deliveryAddresses)
		{
			if (userFacade.isDefaultAddress(address.getId()))
			{
				address.setDefaultAddress(true);
				break;
			}
		}
		return deliveryAddresses;
	}

	@ResponseBody
	@RequestMapping(value = "/summary/setDefaultAddress.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public List<? extends AddressData> setDefaultAddress(@RequestParam(value = "addressId") final String addressId)
	{
		userFacade.setDefaultAddress(userFacade.getAddressForCode(addressId));
		return getDeliveryAddresses();
	}

	@ResponseBody
	@RequestMapping(value = "/summary/setDeliveryAddress.json", method = { RequestMethod.GET, RequestMethod.POST })
	@RequireHardLogIn
	public CartData setDeliveryAddress(@RequestParam(value = "addressId") final String addressId)
	{
		final AddressData addressData = userFacade.getAddressForCode(addressId);
		if (addressData != null && checkoutFacade.setDeliveryAddress(addressData))
		{
			return checkoutFacade.getCheckoutCart();
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/summary/getDeliveryModes.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public List<? extends DeliveryModeData> getDeliveryModes()
	{
		final List<? extends DeliveryModeData> deliveryModes = checkoutFacade.getSupportedDeliveryModes();
		return deliveryModes == null ? Collections.<ZoneDeliveryModeData> emptyList() : deliveryModes;
	}

	@ResponseBody
	@RequestMapping(value = "/summary/setDeliveryMode.json", method = RequestMethod.POST)
	@RequireHardLogIn
	public CartData setDeliveryMode(@RequestParam(value = "modeCode") final String modeCode)
	{

		if (checkoutFacade.setDeliveryMode(modeCode))
		{
			return checkoutFacade.getCheckoutCart();
		}
		else
		{
			return null;
		}
	}

	@RequestMapping(value = "/summary/getDeliveryAddressForm.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getDeliveryAddressForm(final Model model, @RequestParam(value = "addressId") final String addressId,
			@RequestParam(value = "createUpdateStatus") final String createUpdateStatus)
	{
		AddressData addressData = null;
		if (addressId != null && !addressId.isEmpty())
		{
			addressData = checkoutFacade.getDeliveryAddressForCode(addressId);
		}

		final AddressForm addressForm = new AddressForm();

		final boolean hasAddressData = addressData != null;
		if (hasAddressData)
		{
			addressForm.setAddressId(addressData.getId());
			addressForm.setTitleCode(addressData.getTitleCode());
			addressForm.setFirstName(addressData.getFirstName());
			addressForm.setLastName(addressData.getLastName());
			addressForm.setLine1(addressData.getLine1());
			addressForm.setLine2(addressData.getLine2());
			addressForm.setTownCity(addressData.getTown());
			addressForm.setPostcode(addressData.getPostalCode());
			addressForm.setCountryIso(addressData.getCountry().getIsocode());
			addressForm.setShippingAddress(Boolean.valueOf(addressData.isShippingAddress()));
			addressForm.setBillingAddress(Boolean.valueOf(addressData.isBillingAddress()));
		}

		model.addAttribute("edit", Boolean.valueOf(hasAddressData));
		model.addAttribute("noAddresses", Boolean.valueOf(userFacade.isAddressBookEmpty()));

		model.addAttribute(addressForm);
		model.addAttribute("createUpdateStatus", createUpdateStatus);
		return ControllerConstants.Views.Fragments.SingleStepCheckout.DeliveryAddressFormPopup;
	}

	@RequestMapping(value = "/summary/createUpdateDeliveryAddress.json", method = RequestMethod.POST)
	@RequireHardLogIn
	public String createUpdateDeliveryAddress(final Model model, @Valid final AddressForm form, final BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			model.addAttribute("edit", Boolean.valueOf(StringUtils.isNotBlank(form.getAddressId())));

			return ControllerConstants.Views.Fragments.SingleStepCheckout.DeliveryAddressFormPopup;
		}

		final boolean anonymousCheckout = getCheckoutCustomerStrategy().isAnonymousCheckout();
		// create delivery address and set it on cart
		final AddressData addressData = new AddressData();
		addressData.setId(form.getAddressId());
		addressData.setTitleCode(form.getTitleCode());
		addressData.setFirstName(form.getFirstName());
		addressData.setLastName(form.getLastName());
		addressData.setLine1(form.getLine1());
		addressData.setLine2(form.getLine2());
		addressData.setTown(form.getTownCity());
		addressData.setPostalCode(form.getPostcode());
		addressData.setCountry(getI18NFacade().getCountryForIsocode(form.getCountryIso()));

		addressData.setShippingAddress(Boolean.TRUE.equals(form.getShippingAddress())
				|| Boolean.TRUE.equals(form.getSaveInAddressBook()) || anonymousCheckout);
		addressData.setBillingAddress(Boolean.TRUE.equals(form.getBillingAddress()));

		addressData.setVisibleInAddressBook(Boolean.TRUE.equals(form.getSaveInAddressBook())
				|| StringUtils.isNotBlank(form.getAddressId()));
		addressData.setDefaultAddress(Boolean.TRUE.equals(form.getDefaultAddress()));

		if (StringUtils.isBlank(form.getAddressId()))
		{
			userFacade.addAddress(addressData);
		}
		else
		{
			userFacade.editAddress(addressData);
		}

		checkoutFacade.setDeliveryAddress(addressData);

		if (checkoutFacade.getCheckoutCart().getDeliveryMode() == null)
		{
			checkoutFacade.setDeliveryModeIfAvailable();
		}

		model.addAttribute("createUpdateStatus", "Success");
		model.addAttribute("addressId", addressData.getId());

		return REDIRECT_PREFIX + "/checkout/single/summary/getDeliveryAddressForm.json?addressId=" + addressData.getId()
				+ "&createUpdateStatus=Success";
	}

	@ResponseBody
	@RequestMapping(value = "/summary/getSavedCards.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public List<CCPaymentInfoData> getSavedCards()
	{
		final List<CCPaymentInfoData> paymentInfos = userFacade.getCCPaymentInfos(true);
		return paymentInfos == null ? Collections.<CCPaymentInfoData> emptyList() : paymentInfos;
	}

	@ResponseBody
	@RequestMapping(value = "/summary/setPaymentDetails.json", method = RequestMethod.POST)
	@RequireHardLogIn
	public CartData setPaymentDetails(@RequestParam(value = "paymentId") final String paymentId)
	{
		if (StringUtils.isNotBlank(paymentId) && checkoutFacade.setPaymentDetails(paymentId))
		{
			return checkoutFacade.getCheckoutCart();
		}

		return null;
	}

	@RequestMapping(value = "/summary/getPaymentDetailsForm.json", method = RequestMethod.GET)
	@RequireHardLogIn
	public String getPaymentDetailsForm(final Model model,
			@RequestParam(value = "createUpdateStatus") final String createUpdateStatus)
	{
		final PaymentDetailsForm paymentDetailsForm = new PaymentDetailsForm();
		final AddressForm addressForm = new AddressForm();
		paymentDetailsForm.setBillingAddress(addressForm);

		model.addAttribute("paymentInfoData", userFacade.getCCPaymentInfos(true));
		model.addAttribute(paymentDetailsForm);
		model.addAttribute("createUpdateStatus", createUpdateStatus);
		return ControllerConstants.Views.Fragments.SingleStepCheckout.PaymentDetailsFormPopup;
	}

	@RequestMapping(value = "/summary/createPaymentDetails.json", method = RequestMethod.POST)
	@RequireHardLogIn
	public String createUpdatePaymentDetails(final Model model, @Valid final PaymentDetailsForm form,
			final BindingResult bindingResult)
	{
		paymentDetailsValidator.validate(form, bindingResult);

		if (bindingResult.hasErrors())
		{
			return ControllerConstants.Views.Fragments.SingleStepCheckout.PaymentDetailsFormPopup;
		}

		final CCPaymentInfoData paymentInfoData = new CCPaymentInfoData();
		paymentInfoData.setId(form.getPaymentId());
		paymentInfoData.setCardType(form.getCardTypeCode());
		paymentInfoData.setAccountHolderName(form.getNameOnCard());
		paymentInfoData.setCardNumber(form.getCardNumber());
		paymentInfoData.setStartMonth(form.getStartMonth());
		paymentInfoData.setStartYear(form.getStartYear());
		paymentInfoData.setExpiryMonth(form.getExpiryMonth());
		paymentInfoData.setExpiryYear(form.getExpiryYear());
		paymentInfoData.setSaved(Boolean.TRUE.equals(form.getSaveInAccount())
				|| getCheckoutCustomerStrategy().isAnonymousCheckout());
		paymentInfoData.setIssueNumber(form.getIssueNumber());

		final AddressData addressData;
		if (Boolean.FALSE.equals(form.getNewBillingAddress()))
		{
			addressData = getCheckoutCart().getDeliveryAddress();
			if (addressData == null)
			{
				GlobalMessages.addErrorMessage(model, "checkout.paymentMethod.createSubscription.billingAddress.noneSelected");
				return ControllerConstants.Views.Fragments.SingleStepCheckout.PaymentDetailsFormPopup;
			}

			addressData.setBillingAddress(true); // mark this as billing address
		}
		else
		{
			final AddressForm addressForm = form.getBillingAddress();

			addressData = new AddressData();
			if (addressForm != null)
			{
				addressData.setId(addressForm.getAddressId());
				addressData.setTitleCode(addressForm.getTitleCode());
				addressData.setFirstName(addressForm.getFirstName());
				addressData.setLastName(addressForm.getLastName());
				addressData.setLine1(addressForm.getLine1());
				addressData.setLine2(addressForm.getLine2());
				addressData.setTown(addressForm.getTownCity());
				addressData.setPostalCode(addressForm.getPostcode());
				addressData.setCountry(getI18NFacade().getCountryForIsocode(addressForm.getCountryIso()));
				addressData.setShippingAddress(Boolean.TRUE.equals(addressForm.getShippingAddress()));
				addressData.setBillingAddress(Boolean.TRUE.equals(addressForm.getBillingAddress()));
			}
		}

		paymentInfoData.setBillingAddress(addressData);

		final CCPaymentInfoData newPaymentSubscription = checkoutFacade.createPaymentSubscription(paymentInfoData);
		if (newPaymentSubscription != null && StringUtils.isNotBlank(newPaymentSubscription.getSubscriptionId()))
		{
			if (Boolean.TRUE.equals(form.getSaveInAccount()) && userFacade.getCCPaymentInfos(true).size() <= 1)
			{
				userFacade.setDefaultPaymentInfo(newPaymentSubscription);
			}
			checkoutFacade.setPaymentDetails(newPaymentSubscription.getId());
		}
		else
		{
			GlobalMessages.addErrorMessage(model, "checkout.paymentMethod.createSubscription.failed");
			return ControllerConstants.Views.Fragments.SingleStepCheckout.PaymentDetailsFormPopup;
		}

		model.addAttribute("createUpdateStatus", "Success");
		model.addAttribute("paymentId", newPaymentSubscription.getId());

		return REDIRECT_PREFIX + "/checkout/single/summary/getPaymentDetailsForm.json?paymentId=" + newPaymentSubscription.getId()
				+ "&createUpdateStatus=Success";
	}

	@RequestMapping(value = "/termsAndConditions")
	@RequireHardLogIn
	public String getTermsAndConditions(final Model model) throws CMSItemNotFoundException
	{
		final ContentPageModel pageForRequest = getCmsPageService().getPageForLabel("/termsAndConditions");
		storeCmsPageInModel(model, pageForRequest);
		setUpMetaDataForContentPage(model, pageForRequest);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, contentPageBreadcrumbBuilder.getBreadcrumbs(pageForRequest));
		return ControllerConstants.Views.Fragments.Checkout.TermsAndConditionsPopup;
	}

	@RequestMapping(value = "/placeOrder")
	@RequireHardLogIn
	public String placeOrder(@ModelAttribute("placeOrderForm") final PlaceOrderForm placeOrderForm, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException, InvalidCartException,
			CommerceCartModificationException
	{
		boolean invalid = false;
		final String securityCode = placeOrderForm.getSecurityCode();
		final CartData cartData = checkoutFacade.getCheckoutCart();
		if (cartData.getDeliveryAddress() == null)
		{
			GlobalMessages.addErrorMessage(model, "checkout.deliveryAddress.notSelected");
			invalid = true;
		}

		if (cartData.getDeliveryMode() == null)
		{
			GlobalMessages.addErrorMessage(model, "checkout.deliveryMethod.notSelected");
			invalid = true;
		}

		if (cartData.getPaymentInfo() == null)
		{
			GlobalMessages.addErrorMessage(model, "checkout.paymentMethod.notSelected");
			invalid = true;
		}
		else if (StringUtils.isBlank(securityCode))
		{
			GlobalMessages.addErrorMessage(model, "checkout.paymentMethod.noSecurityCode");
			invalid = true;
		}

		if (!placeOrderForm.isTermsCheck())
		{
			GlobalMessages.addErrorMessage(model, "checkout.error.terms.not.accepted");
			invalid = true;
		}

		if (invalid)
		{
			return checkoutSummary(model, redirectModel);
		}

		if (!checkoutFacade.authorizePayment(securityCode))
		{
			return checkoutSummary(model, redirectModel);
		}

		final OrderData orderData = checkoutFacade.placeOrder();
		if (orderData == null)
		{
			GlobalMessages.addErrorMessage(model, "checkout.placeOrder.failed");
			return checkoutSummary(model, redirectModel);
		}

		return redirectToOrderConfirmationPage(orderData);
	}

	@ModelAttribute("cardTypes")
	public Collection<CardTypeData> getCardTypes()
	{
		return checkoutFacade.getSupportedCardTypes();
	}

	@ModelAttribute("months")
	public List<SelectOption> getMonths()
	{
		final List<SelectOption> months = new ArrayList<>();

		months.add(new SelectOption("1", "01"));
		months.add(new SelectOption("2", "02"));
		months.add(new SelectOption("3", "03"));
		months.add(new SelectOption("4", "04"));
		months.add(new SelectOption("5", "05"));
		months.add(new SelectOption("6", "06"));
		months.add(new SelectOption("7", "07"));
		months.add(new SelectOption("8", "08"));
		months.add(new SelectOption("9", "09"));
		months.add(new SelectOption("10", "10"));
		months.add(new SelectOption("11", "11"));
		months.add(new SelectOption("12", "12"));

		return months;
	}

	@ModelAttribute("startYears")
	public List<SelectOption> getStartYears()
	{
		final List<SelectOption> startYears = new ArrayList<>();
		final Calendar calender = new GregorianCalendar();

		for (int i = calender.get(Calendar.YEAR); i > (calender.get(Calendar.YEAR) - 6); i--)
		{
			startYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
		}

		return startYears;
	}

	@ModelAttribute("expiryYears")
	public List<SelectOption> getExpiryYears()
	{
		final List<SelectOption> expiryYears = new ArrayList<>();
		final Calendar calender = new GregorianCalendar();

		for (int i = calender.get(Calendar.YEAR); i < (calender.get(Calendar.YEAR) + 11); i++)
		{
			expiryYears.add(new SelectOption(String.valueOf(i), String.valueOf(i)));
		}

		return expiryYears;
	}

	/**
	 * Data class used to hold a drop down select option value. Holds the code identifier as well as the display name.
	 */
	public static class SelectOption
	{
		private final String code;
		private final String name;

		public SelectOption(final String code, final String name)
		{
			this.code = code;
			this.name = name;
		}

		public String getCode()
		{
			return code;
		}

		public String getName()
		{
			return name;
		}
	}
}
