///*
// * [y] hybris Platform
// *
// * Copyright (c) 2000-2013 hybris AG
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of hybris
// * ("Confidential Information"). You shall not disclose such Confidential
// * Information and shall use it only in accordance with the terms of the
// * license agreement you entered into with hybris.
// *
// *
// */
//
//package com.learn.formgroups.dynamic.impl;
//
//
//import com.learn.formgroups.CartModel;
//import com.learn.formgroups.CheckoutStep;
//import com.learn.formgroups.FormDTO;
//import com.learn.formgroups.FormElementGroup;
//import com.learn.formgroups.dynamic.DynamicCheckoutFacade;
//import org.apache.log4j.Logger;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.util.*;
//
//import static com.learn.formgroups.util.LogHelper.debug;
//import static com.learn.formgroups.util.LogHelper.error;
//import static org.apache.commons.lang.StringUtils.isEmpty;
//import static org.apache.commons.lang.StringUtils.isNotEmpty;
//
///**
// *
// *
// * @module hybris - pacoshopfacades
// * @version 1.0v dec 12, 2013
// * @author symmetrics - a CGI Group brand <info@symmetrics.de>
// * @author Dmitry Popov <dmitry.popov@symmetrics.de>
// * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
// * @link http ://www.symmetrics.de/
// * @see  "https://wiki.hybris.com/"
// * @copyright 2013 symmetrics - a CGI Group brand
// */
//public class DefaultDynamicCheckoutFacade implements DynamicCheckoutFacade
//{
//
//	private static final String MANDATORY_FIELDS   = "mandatoryFields";
//	private static final String COUNTRIES          = "countries";
//	private static final String TITLES             = "titles";
//	private static final String STEP_NAME          = "stepName";
//	private static final String FORM               = "form";
//	private static final String DISPLAY_MAPPINGS   = "displaymappings";
//	private static final String DISPLAY_BREADCRUMB = "displaybreadcrumb";
//	private static final String TAX                = "tax";
//	private static final String SUM_WO_TAX         = "sumWithoutTax";
//	private static final String PODUCT_TITLE       = "productDescription";
//	private static final String CARD               = "card";
//	private static final String MODEL_ORDER_ID     = "orderid";
//	private static final String MODEL_REDIRECT_URL = "redirecturl";
//
//	private static final String DISPLAY_ANONYMOUS = "displayAnonymous";
//	private static final String LOGIN_STEP_NAME   = "login";
//
//	private static final String CART_NUMBER      = "number";
//	private static final String CART_OWNER       = "owner";
//	private static final String CART_VALID_MONTH = "validMonth";
//	private static final String CART_VALID_YEAR  = "validYear";
//
//	private static final String STEPS       = "steps";
//	private static final String CURRENTSTEP = "currentstep";
//	private static final Logger LOG         = Logger.getLogger(DefaultDynamicCheckoutFacade.class);
//
//	private static final String CONFIG_THANKYOUPAGE_CMS_LABEL = "dcf.thankoyupage.cms.label";
//
//	/* Names in modal for urls to specific checkout steps */
//	private static final String PATH_TO_CUSTUMER_PAGE = "pathToCustomerPage";
//	private static final String PATH_TO_SHIPMENT_PAGE = "pathToShipmentPage";
//	private static final String PATH_TO_PAYMENT_PAGE  = "pathToPaymentPage";
//
//	private static final String PATH_TO_DYNAMICCHECKOUT = "/dynamiccheckout/";
//	private static final int    INT_ZERO                = 0;
//
//	private static final String TAB_IBAN_BIC        = "tabIbanBic";
//	private static final String TAB_KONTONUMMER_BLZ = "tabKontonummerBlz";
//
//
////	@Resource
////	private CartService                                                 cartService;
////	@Resource
////	private CheckoutFacade                                              checkoutFacade;
////	@Resource
////	private CheckoutStepService                                         checkoutStepService;
////	@Resource
////	private UserFacade                                                  userFacade;
////	@Resource
////	private CartFacade                                                  cartFacade;
////	@Resource
////	private ConfigurationService                                        configurationService;
////	@Resource
////	private ProductDTOPopulationServiceLocator<AbstractOrderEntryModel> productDTOPopulationServiceLocator;
////	@Resource
////	private DeliveryAddressValidationService                            deliveryAddressValidationService;
////	@Resource
////	private CustomerAccountService                                      customerAccountService;
////	@Resource
////	private BaseStoreService                                            baseStoreService;
////	@Resource
////	private Converter<PaymentInfoModel, PaymentInfoData>                extCorePaymentInfoConverter;
////	@Resource
////	private ModelService                                                modelService;
//
//
//	/**
////	 * Sets cart service.
////	 *
////	 * @param cartService cartService
////	 */
////	public void setCartService(final CartService cartService)
////	{
////		this.cartService = cartService;
////	}
////
////	/**
////	 * Sets checkout step service.
////	 *
////	 * @param checkoutStepService - checkoutStepService.
////	 */
////	public void setCheckoutStepService(final CheckoutStepService checkoutStepService)
////	{
////		this.checkoutStepService = checkoutStepService;
////	}
////
////	/**
////	 * Sets checkout facade.
////	 *
////	 * @param pCheckoutFacade CheckoutFacade
////	 */
////	public void setCheckoutFacade(final CheckoutFacade pCheckoutFacade)
////	{
////		this.checkoutFacade = pCheckoutFacade;
////	}
////
////	/**
////	 * Sets cart facade.
////	 *
////	 * @param pCartFacade CartFacade
////	 */
////	public void setCartFacade(final CartFacade pCartFacade)
////	{
////		this.cartFacade = pCartFacade;
////	}
////
////	/**
////	 * @param formDTO FormDTO
////	 * @return checkoutStep
////	 */
////	public CheckoutStep getCheckoutStep(final FormDTO formDTO)
////	{
////		return checkoutStepService.getCheckoutStep(formDTO);
////	}
////
////
////	/**
////	 * Setting product types to session as the cart will be cleared after order submission.
////	 *
////	 * @param session
////	 *           - http session.
////	 * @return OrderData
////	 * @throws de.hybris.platform.order.InvalidCartException
////	 *            - exception
////	 */
////	@Override
////	public OrderData submitOrder(final HttpSession session) throws InvalidCartException
////	{
////		final OrderData orderData = checkoutFacade.placeOrder();
////		session.setAttribute(MODEL_ORDER_ID, orderData.getCode());
////		return orderData;
////	}
//
//	/**
//	 *
//	 * @param stepName
//	 *           stepName
//	 * @return checkoutStep
//	 */
//	@Override
//	public CheckoutStep getStep(final String stepName)
//	{
//		return checkoutStepService.getStep(stepName);
//	}
//
//	/**
//	 * Delegate to a corresponding method of a {@link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService}.
//	 *
//	 * @return the list of checkout steps needed in the current checkout flow.
//	 */
//	@Override
//	public List<CheckoutStep> getNeededSteps()
//	{
//		return checkoutStepService.getNeededSteps(cartService.getSessionCart());
//	}
//
//	/**
//	 * Delegate to a corresponding method of a {@link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService}.
//	 *
//	 * @param neededSteps
//	 *           - previously determined checkout steps needed in the current checkout flow.
//	 * @return the first not completed step.
//	 */
//	@Override
//	public CheckoutStep getNextDisplayedStep(final List<? extends CheckoutStep> neededSteps)
//	{
//		return checkoutStepService.getNextDisplayedStep(neededSteps);
//	}
//
//	/**
//	 * TBD later.
//	 *
//	 * @param dto
//	 *           -quality gate.
//	 * @param checkoutStep
//	 *           quality gate.
//	 * @param bindingResult
//	 *           - quality gate.
//	 * @return quality gate.
//	 */
//	@Override
//	public boolean validateForm(final FormDTO dto, final CheckoutStep checkoutStep, final BindingResult bindingResult)
//	{
//		boolean validationPassed = true;
//		for (final FormElementGroup formElementGroup : checkoutStep.getElements())
//		{
//			if (formElementGroup.isDisplayed(cartService.getSessionCart()))
//			{
//				validationPassed = formElementGroup.validate(dto, bindingResult) && validationPassed;
//			}
//		}
//		// Delivery address SAP validation
//		if (validationPassed && deliveryAddressValidationService != null)
//		{
//			validationPassed = deliveryAddressValidationService.checkDeliveryAddress(checkoutStep, bindingResult);
//		}
//		return validationPassed;
//	}
//
//	/**
//	 *
//	 * @param dto DTO
//	 */
//	@Override
//	public void saveFormToCart(final FormDTO dto)
//	{
//		final CheckoutStep appropriateStep = checkoutStepService.getCheckoutStep(dto);
//		for (final FormElementGroup formElementGroup : appropriateStep.getElements())
//		{
//			final CartModel cartModel = cartService.getSessionCart();
//			if (formElementGroup.isDisplayed(cartModel))
//			{
//				formElementGroup.saveFormToCart(dto, cartModel);
//			}
//		}
//	}
//
//
//	@Override
//	public Map<String, Boolean> getDisplayMappings(final CheckoutStep step)
//	{
//		final Map<String, Boolean> displayMappings = new HashMap<>();
//		for (final FormElementGroup formElementGroup : step.getDisplayedElements())
//		{
//			if (formElementGroup.isDisplayed(cartService.getSessionCart()))
//			{
//				displayMappings.put(formElementGroup.getKey(), true);
//			}
//			else
//			{
//				displayMappings.put(formElementGroup.getKey(), false);
//			}
//		}
//		return displayMappings;
//	}
//
//	private Map getCreditCard()
//	{
//		final Map<String, String> map = new HashMap<>();
//		final CartModel cart = cartService.getSessionCart();
//		final CreditCardPaymentInfoModel paymentInfoModel;
//		if (cart.getPaymentInfo() instanceof CreditCardPaymentInfoModel)
//		{
//			paymentInfoModel = (CreditCardPaymentInfoModel) cart.getPaymentInfo();
//
//			map.put(CART_NUMBER, paymentInfoModel.getNumber());
//			map.put(CART_OWNER, paymentInfoModel.getCcOwner());
//			map.put(CART_VALID_MONTH, paymentInfoModel.getValidToMonth());
//			map.put(CART_VALID_YEAR, paymentInfoModel.getValidToYear());
//		}
//		return map;
//	}
//
//	private List<String> getProductTypesFromCart()
//	{
//		final List<String> productTypes = new ArrayList<>();
//		for (final AbstractOrderEntryModel abstractOrderEntryModel : cartService.getSessionCart().getEntries())
//		{
//			productTypes.add(abstractOrderEntryModel.getProduct().getProductType().getCode());
//		}
//		return productTypes;
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	private String getSumeWithoutTax()
//	{
//		final double price = getPrice();
//		return String.format("%.2f", price - cartService.getSessionCart().getTotalTax());
//	}
//
//	/**
//	 *
//	 * @param formDTO
//	 * @return show the IBAN/BIC tab
//	 */
//	private boolean getTabIbanBic(final FormDTO formDTO)
//	{
//		CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) formDTO;
//		return checkoutFormDTO.isTabIbanBic();
//	}
//
//	/**
//	 *
//	 * @param formDTO
//	 * @return show the Kontonummer/BLZ tab
//	 */
//	private boolean getTabKontonummerBlz(final FormDTO formDTO)
//	{
//		CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) formDTO;
//		return checkoutFormDTO.isTabKontonummerBlz();
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	private String getSimpleTax()
//	{
//		return String.format("%.2f", cartService.getSessionCart().getTotalTax());
//	}
//
//	/**
//	 *
//	 * @return
//	 */
//	private Double getPrice()
//	{
//		return cartService.getSessionCart().getSubtotal();
//	}
//
//
//	/**
//	 * Should populate step by data that will be passed through all steps.
//	 *
//	 * @param model
//	 *           -mvc model
//	 */
//	@Override
//	public void populateCommonStepData(final Model model)
//	{
//		final CheckoutStep nextStep = getNextDisplayedStep(getNeededSteps());
//		model.addAttribute(CARD, getCreditCard());
//		//TODO I think we should not pass steps instances but we can pass some boolean array instead
//		model.addAttribute(STEPS, getNeededSteps());
//		model.addAttribute(CURRENTSTEP, nextStep);
//		model.addAttribute(DISPLAY_BREADCRUMB, isBreadcrumbDisplayed());
//
//	}
//
//	/**
//	 *
//	 * @param model  - mvc model.
//	 */
//	@Override
//	public void populateSpecificStepData(final Model model)
//	{
//		final List<CheckoutStep> neededSteps = getNeededSteps();
//		final CheckoutStep nextStep = getNextDisplayedStep(neededSteps);
//		final Map<String, Boolean> mandatoryFields = checkoutStepService.getStepMandatoryFieldNames(nextStep);
//		final FormDTO form = checkoutStepService.populateFormFromCart(nextStep);
//		final Map<String, Boolean> displayMappings = getDisplayMappings(nextStep);
//
//		model.addAttribute(MANDATORY_FIELDS, mandatoryFields);
//		model.addAttribute(DISPLAY_MAPPINGS, displayMappings);
//		model.addAttribute(FORM, form);
//		model.addAttribute(STEP_NAME, nextStep.getStepName());
//		model.addAllAttributes(getStepsPath());
//
//		final String productRedirectUrl = getProductRedirectUrl();
//		if (!isEmpty(productRedirectUrl))
//		{
//			model.addAttribute(MODEL_REDIRECT_URL, productRedirectUrl);
//		}
//	}
//
//	@Override
//	public void populateSpecificStepDataForSpecificStep(final Model model, final CheckoutStep nextStep)
//	{
//		final Map<String, Boolean> mandatoryFields = checkoutStepService.getStepMandatoryFieldNames(nextStep);
//		final FormDTO form = checkoutStepService.populateFormFromCart(nextStep);
//		final Map<String, Boolean> displayMappings = getDisplayMappings(nextStep);
//
//		model.addAttribute(MANDATORY_FIELDS, mandatoryFields);
//		model.addAttribute(DISPLAY_MAPPINGS, displayMappings);
//		model.addAttribute(FORM, form);
//		model.addAttribute(STEP_NAME, nextStep.getStepName());
//		model.addAttribute(TAB_IBAN_BIC, getTabIbanBic(form));
//		model.addAttribute(TAB_KONTONUMMER_BLZ, getTabKontonummerBlz(form));
//
//		final String productRedirectUrl = getProductRedirectUrl();
//		if (!isEmpty(productRedirectUrl))
//		{
//			model.addAttribute(MODEL_REDIRECT_URL, productRedirectUrl);
//		}
//	}
//
//	/**
//	 *
//	 * @return url to be redirected to after purchase
//	 */
//	@Override
//	public String getProductRedirectUrl()
//	{
//		return cartService.getSessionCart().getRedirectUrl();
//	}
//
//	/**
//	 * Gets thank you CMS label.
//	 *
//	 * @return the thank you CMS label
//	 */
//	@Override
//	public String getThankYouCMSLabel()
//	{
//		return getConfigurationProperty(CONFIG_THANKYOUPAGE_CMS_LABEL);
//	}
//
//	@Override
//	public boolean cartContainsOnlineArticleProduct(final OrderData orderData)
//	{
//		final String productName = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);
//
//		for (final OrderEntryData entries : orderData.getEntries())
//		{
//			if (entries.getProduct() != null)
//			{
//				if (entries.getProduct().getProductType().equalsIgnoreCase(productName))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * Used for showing breadcrumb.
//	 *
//	 * @return is onlineArticle value
//	 */
//	@Override
//	public boolean isBreadcrumbDisplayed()
//	{
//		for (final AbstractOrderEntryModel abstractOrderEntryModel : cartService.getSessionCart().getEntries())
//		{
//			final ProductModel product = abstractOrderEntryModel.getProduct();
//			if (product.getProductType().getCode()
//					  .equals(getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE)))
//			{
//				return false;
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * Fill the dto to display products in login, summary and thankyou pages.
//	 * @param cartContentsFragmentDTO the cart contents fragment dTO
//	 * @param orderId - order id.
//	 * @return the CartContentsFragmentDTO.
//	 */
//	@Override
//	public CartContentsFragmentDTO populateCartContentsFragment(final CartContentsFragmentDTO cartContentsFragmentDTO,
//																					final String orderId)
//	{
//		AbstractOrderModel source;
//		//get list of products in the cart
//		List<ProductDTO> productDTOs;
//		OrderModel orderModel = null;
//		if (isNotEmpty(orderId))
//		{
//			final BaseStoreModel baseStoreModel = baseStoreService.getCurrentBaseStore();
//			orderModel = customerAccountService.getOrderDetailsForGUID(orderId, baseStoreModel);
//			if (orderModel == null)
//			{
//				throw new UnknownIdentifierException(
//						  "Order with orderGUID " + orderId + " not found for current user in current BaseStore");
//			}
//			cartContentsFragmentDTO.setOrderCode(orderModel.getCode());
//			source = orderModel;
//		}
//		else
//		{
//			source = getCart();
//		}
//
//		List<AbstractOrderEntryModel> entries = source.getEntries();
//		if (!entries.isEmpty())
//		{
//			productDTOs = productDTOPopulationServiceLocator.populate(entries);
//
//			cartContentsFragmentDTO.setProducts(productDTOs);
//			// Set taxes and totals.
//			OrderTotalsDTO totals = cartContentsFragmentDTO.getTotals();
//			totals.setTotalPrice(source.getTotalPrice());
//			totals.setDeliveryCost(source.getDeliveryCost());
//			totals.setTotalTaxValues(getTotalTaxValuesForShowing(source));
//			totals.setPaymentCost(source.getPaymentCost());
//			totals.setTotalTax(source.getTotalTax());
//
//			cartContentsFragmentDTO.setDownloadButtonDisplayed(isDownloadButtonDisplayed(orderModel));
//			totals.setSubTotal(source.getSubtotal() - source.getTotalTax());
//			if (source.getCurrency() != null)
//			{
//				totals.setCurrency(source.getCurrency().getSymbol());
//			}
//		}
//
//		if (orderModel != null)
//		{
//			SummaryInfoFormDTO form = new SummaryInfoFormDTO();
//			populateCustomerDataFromOrder(form, orderModel);
//			populateDeliveryDataFromOrder(form, orderModel);
//			populateBillingDataFromOrder(form, orderModel);
//
//			cartContentsFragmentDTO.setForm(form);
//
//			PaymentInfoModel paymentInfo = orderModel.getPaymentInfo();
//			if (paymentInfo != null)
//			{
//				cartContentsFragmentDTO.setPaymentInfo(extCorePaymentInfoConverter.convert(paymentInfo));
//			}
//		}
//
//		return cartContentsFragmentDTO;
//	}
//
//	private Collection<TaxValueDTO> getTotalTaxValuesForShowing(final AbstractOrderModel sessionCart)
//	{
//		Collection<TaxValue> cartList = sessionCart.getTotalTaxValues();
//		Collection<TaxValueDTO> resultList = new ArrayList<TaxValueDTO>();
//		for (TaxValue taxValue : cartList)
//		{
//			if (existInResultList(taxValue, resultList))
//			{
//				updateResult(taxValue, resultList);
//			}
//			else
//			{
//				addTaxInResult(taxValue, resultList);
//			}
//
//		}
//		return resultList;
//	}
//
//	private void addTaxInResult(final TaxValue taxValue, final Collection<TaxValueDTO> resultList)
//	{
//		TaxValueDTO result = new TaxValueDTO();
//		result.setValue(taxValue.getValue());
//		result.setAppliedValue(taxValue.getAppliedValue());
//		resultList.add(result);
//	}
//
//	private void updateResult(final TaxValue taxValue, final Collection<TaxValueDTO> resultList)
//	{
//		TaxValueDTO result = getTaxValueDTObyValue(taxValue.getValue(), resultList);
//		Double taxSumm = result.getAppliedValue() + taxValue.getAppliedValue();
//		result.setAppliedValue(taxSumm);
//	}
//
//	private boolean existInResultList(final TaxValue taxValue, final Collection<TaxValueDTO> resultList)
//	{
//		return getTaxValueDTObyValue(taxValue.getValue(), resultList) != null;
//	}
//
//	private TaxValueDTO getTaxValueDTObyValue(final Double value, final Collection<TaxValueDTO> taxList)
//	{
//		TaxValueDTO result = null;
//		for (TaxValueDTO taxValueDTO : taxList)
//		{
//			if (taxValueDTO.getValue().compareTo(value) == INT_ZERO)
//			{
//				result = taxValueDTO;
//			}
//		}
//		debug(LOG, "Check, if 'null' then TaxValue not exist in result list, result: [%s]", result);
//		return result;
//	}
//
//	private boolean isDownloadButtonDisplayed(final OrderModel orderModel)
//	{
//		if (orderModel != null && orderModel.getDeliveryAddress() == null)
//		{
//			for (final AbstractOrderEntryModel abstractOrderEntryModel : orderModel.getEntries())
//			{
//				final ProductModel product = abstractOrderEntryModel.getProduct();
//				if (product.getProductType().getCode()
//						  .equals(getConfigurationProperty(FormElementGroupConstants.DOWNLOAD)))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	private String getConfigurationProperty(final String key)
//	{
//		return configurationService.getConfiguration().getString(key);
//	}
//
//	private Map<String, String> getStepsPath()
//	{
//		Map<String, String> pathMap = new HashMap<>();
//		pathMap.put(PATH_TO_CUSTUMER_PAGE, PATH_TO_DYNAMICCHECKOUT
//				  + getConfigurationProperty(FormElementGroupConstants.CUSTOMER_STEP_NAME));
//		pathMap.put(PATH_TO_PAYMENT_PAGE, PATH_TO_DYNAMICCHECKOUT
//				  + getConfigurationProperty(FormElementGroupConstants.PAYMENT_STEP_NAME));
//		pathMap.put(PATH_TO_SHIPMENT_PAGE, PATH_TO_DYNAMICCHECKOUT
//				  + getConfigurationProperty(FormElementGroupConstants.SHIPMENT_STEP_NAME));
//		return pathMap;
//	}
//
//	@Override
//	public void saveBillingAddress()
//	{
//		final CartModel cart = getCart();
//		final PaymentInfoModel paymentInfoModel = cart.getPaymentInfo();
//
//		cart.setPrepayOnly(isProductPrepayOnly(cart));
//		getModelService().save(cart);
//
//		if (paymentInfoModel != null)
//		{
//			final AddressModel paymentAddress = cart.getPaymentAddress();
//			final AddressModel customerAddress = cart.getCustomerAddress();
//			final AddressModel billingAddress;
//
//			if (paymentAddress != null)
//			{
//				billingAddress = getModelService().clone(paymentAddress);
//			}
//			else if
//					  (customerAddress != null)
//			{
//				billingAddress = getModelService().clone(customerAddress);
//			}
//			else
//			{
//				error(LOG, "cart with code %s has no paymentAddress, no customerAddress - can't save billingAddress", cart.getCode());
//				return;
//			}
//
//			billingAddress.setOwner(paymentInfoModel);
//			paymentInfoModel.setBillingAddress(billingAddress);
//
//			getModelService().saveAll(new Object[]{billingAddress, paymentInfoModel});
//		}
//		else
//		{
//			throw new RuntimeException(
//					  "Failed to save billing address on the payment info, because he payment info is null. "
//								 + "You need to save the payment info on the cart first."
//			);
//
//		}
//	}
//
//	/**
//	 * Determines if one of the products contains prepayOnly flag.
//	 *
//	 * @param cartModel Cart
//	 * @return true if product has prepayOnly flag set and false otherwise
//	 */
//	private static boolean isProductPrepayOnly(final CartModel cartModel)
//	{
//		for (final AbstractOrderEntryModel abstractOrderEntryModel : cartModel.getEntries())
//		{
//			final ProductModel productModel = abstractOrderEntryModel.getProduct();
//			if (productModel.getPrepayOnly() != null && productModel.getPrepayOnly())
//			{
//				return true;
//			}
//		}
//
//        return false;
//    }
//
//	/**
//	 * Populate customer data for summary info form.
//	 *
//	 * @param form       Form DTO.
//	 * @param orderModel Order model.
//	 */
//	private void populateCustomerDataFromOrder(final SummaryInfoFormDTO form, final OrderModel orderModel)
//	{
//		CustomerModel user = (CustomerModel) orderModel.getUser();
//
//		AddressModel customerAddress = user.getMainAddress();
//		if (customerAddress != null)
//		{
//			final TitleModel title = customerAddress.getTitle();
//			if (title != null)
//			{
//				form.setSalutation(title.getCode());
//			}
//			final CountryModel country = customerAddress.getCountry();
//			if (country != null)
//			{
//				form.setCountry(country.getName());
//			}
//
//			form.setEmail(customerAddress.getEmail());
//			form.setFirstName(customerAddress.getFirstname());
//			form.setLastName(customerAddress.getLastname());
//			form.setCompany(customerAddress.getCompany());
//			form.setStreet(customerAddress.getLine1());
//			form.setAdditionalStreet(customerAddress.getLine2());
//			form.setZip(customerAddress.getPostalcode());
//			form.setCity(customerAddress.getTown());
//			form.setMobile(customerAddress.getCellphone());
//			form.setPhone(customerAddress.getPhone1());
//		}
//	}
//
//	/**
//	 * Populate delivery data for summary info form.
//	 *
//	 * @param form       Form DTO.
//	 * @param orderModel Order model.
//	 */
//	private void populateDeliveryDataFromOrder(final SummaryInfoFormDTO form, final OrderModel orderModel)
//	{
//		// Set delivery data.
//		final AddressModel deliveryAddress = orderModel.getDeliveryAddress();
//		final Boolean additionalShippingAddressWanted = orderModel.getAdditionalShippingAddressWanted();
//		if (additionalShippingAddressWanted != null && additionalShippingAddressWanted && deliveryAddress != null)
//		{
//			final TitleModel title = deliveryAddress.getTitle();
//			if (title != null)
//			{
//				form.setNewShipmentSalutation(title.getCode());
//			}
//			final CountryModel country = deliveryAddress.getCountry();
//			if (country != null)
//			{
//				form.setNewShipmentCountry(country.getName());
//			}
//
//			form.setNewShipmentFirstName(deliveryAddress.getFirstname());
//			form.setNewShipmentLastName(deliveryAddress.getLastname());
//			form.setNewShipmentEmail(deliveryAddress.getEmail());
//			form.setNewShipmentTitle(deliveryAddress.getTitle2());
//			form.setNewShipmentStreet(deliveryAddress.getLine1());
//			form.setNewShipmentAdditionalStreet(deliveryAddress.getLine2());
//			form.setNewShipmentZip(deliveryAddress.getPostalcode());
//			form.setNewShipmentCity(deliveryAddress.getTown());
//			form.setNewShipmentCompany(deliveryAddress.getCompany());
//		}
//
//		//////////////////////////
//		// Set delivery date data.
//		//////////////////////////
//		if (orderModel.getDeliveryNow() != null)
//		{
//			form.setDeliveryStart(orderModel.getDeliveryNow());
//			form.setDeliveryDateBlock(true);
//		}
//		else
//		{
//			if (orderModel.getDeliveryStartDate() != null)
//			{
//				form.setDeliveryStartDate(orderModel.getDeliveryStartDate());
//				form.setDeliveryDateBlock(true);
//			}
//		}
//	}
//
//	/**
//	 * Populate billing data for summary form.
//	 *
//	 * @param form       Form DTO.
//	 * @param orderModel Order model.
//	 */
//	private void populateBillingDataFromOrder(final SummaryInfoFormDTO form, final OrderModel orderModel)
//	{
//		// Set billing data.
//		final AddressModel paymentAddress = orderModel.getPaymentAddress();
//		final Boolean additionalInvoiceAddressWanted = orderModel.getAdditionalInvoiceAddressWanted();
//		if (additionalInvoiceAddressWanted != null && additionalInvoiceAddressWanted && paymentAddress != null)
//		{
//			final TitleModel title = paymentAddress.getTitle();
//			if (title != null)
//			{
//				form.setBillingSalutation(title.getCode());
//			}
//			final CountryModel country = paymentAddress.getCountry();
//			if (country != null)
//			{
//				form.setBillingCountry(country.getName());
//			}
//
//			form.setBillingFirstName(paymentAddress.getFirstname());
//			form.setBillingLastName(paymentAddress.getLastname());
//			form.setBillingEmail(paymentAddress.getEmail());
//			form.setBillingTitle(paymentAddress.getTitle2());
//			form.setBillingStreet(paymentAddress.getLine1());
//			form.setBillingAdditionalStreet(paymentAddress.getLine2());
//			form.setBillingZip(paymentAddress.getPostalcode());
//			form.setBillingCity(paymentAddress.getTown());
//			form.setBillingCompany(paymentAddress.getCompany());
//		}
//	}
//
//	/**
//	 * Gets cart.
//	 *
//	 * @return  - session cart
//	 */
//	protected CartModel getCart()
//	{
//		return cartService.getSessionCart();
//	}
//
//
//	/**
//	 * Gets model service.
//	 *
//	 * @return  - model service
//	 */
//	protected ModelService getModelService()
//	{
//		return modelService;
//	}
//
//
//	@Override
//	public Map<String, Object> populatePageModelData(final CheckoutStep step)
//	{
//		final Map<String, Object> pageModelValues = new HashMap<>();
//		for (final FormElementGroup formElementGroup : step.getElements())
//		{
//			if (formElementGroup.isDisplayed(cartService.getSessionCart()))
//			{
//				pageModelValues.putAll(formElementGroup.populatePageModelData(getCart()));
//			}
//		}
//		return pageModelValues;
//	}
//}
