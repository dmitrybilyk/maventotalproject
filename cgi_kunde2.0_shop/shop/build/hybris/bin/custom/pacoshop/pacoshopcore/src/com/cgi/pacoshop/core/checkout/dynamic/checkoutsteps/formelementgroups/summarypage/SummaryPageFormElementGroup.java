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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.summarypage;


import com.cgi.hybris.payment.core.facades.impl.DefaultProxyCheckoutFacade;
import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractDeliveryAddressValidatingFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import com.cgi.pacoshop.core.model.TermAcceptedModel;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import com.cgi.pacoshop.core.util.MccSiteUrlHelper;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;


/**
 * Summary Form Element Group for Download page and OnlineArticle.
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SummaryPageFormElementGroup extends AbstractDeliveryAddressValidatingFormElementGroup
{
	private static final Logger LOG = Logger.getLogger(SummaryPageFormElementGroup.class);

	@Resource
	private DefaultProxyCheckoutFacade defaultProxyCheckoutFacade;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private MccSiteUrlHelper mccSiteUrlHelper;

	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		return true;
	}

	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		return (cart.getAgb() != null && cart.getAgb());
	}

	/**
	 * @param dto - Form data transfer object.id
	 * @param cart - cart object linked to a current session.
	 */
	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
		if (dto instanceof SummaryStepFormDTO)
		{
			SummaryStepFormDTO summaryStepFormDTO = (SummaryStepFormDTO) dto;
			populateShipmentAddress(summaryStepFormDTO, cart.getDeliveryAddress());
			populateUserInfo(summaryStepFormDTO, cart.getCustomerAddress());
			populateBillingAddress(summaryStepFormDTO, cart.getPaymentAddress());
			populateDeliveryDate(summaryStepFormDTO, cart);
			populateTerms(summaryStepFormDTO, cart);
			populatePaymentInfo(summaryStepFormDTO, cart.getPaymentInfo());

		}
	}

	private void populatePaymentInfo(final SummaryStepFormDTO summaryStepFormDTO, final PaymentInfoModel paymentInfo)
	{
		if (paymentInfo != null && paymentInfo instanceof DebitPaymentInfoModel)
		{
			summaryStepFormDTO.setPaymentMethod(paymentInfo.getPaymentMethod().toString());
			summaryStepFormDTO.setIban(((DebitPaymentInfoModel) paymentInfo).getIban());
			summaryStepFormDTO.setBic(((DebitPaymentInfoModel) paymentInfo).getBic());
			summaryStepFormDTO.setBankName(((DebitPaymentInfoModel) paymentInfo).getBank());
			summaryStepFormDTO.setAccountNumber(((DebitPaymentInfoModel) paymentInfo).getAccountNumber());
			summaryStepFormDTO.setBanIdNumber(((DebitPaymentInfoModel) paymentInfo).getBankIDNumber());
		}
	}

	private void populateUserInfo(final SummaryStepFormDTO summaryStepFormDTO, final AddressModel customerAddress)
	{
		if (customerAddress != null)
		{
			summaryStepFormDTO.setFirstName(customerAddress.getFirstname());
			summaryStepFormDTO.setLastName(customerAddress.getLastname());
			summaryStepFormDTO.setEmail(customerAddress.getEmail());
			summaryStepFormDTO.setZip(customerAddress.getPostalcode());
			summaryStepFormDTO.setCity(customerAddress.getTown());
			summaryStepFormDTO.setStreet(customerAddress.getLine1());
			summaryStepFormDTO.setHouseNumber(customerAddress.getLine2());
			summaryStepFormDTO.setAdditionalStreet(customerAddress.getLine3());

			final CountryModel country = customerAddress.getCountry();
			if (country != null)
			{
				summaryStepFormDTO.setCountry(country.getName());
			}
			summaryStepFormDTO.setMobilePhoneNumber(customerAddress.getMobileNumber());
			summaryStepFormDTO.setHomePhoneNumber(customerAddress.getHomeNumber());
			summaryStepFormDTO.setBusinessPhoneNumber(customerAddress.getBusinessNumber());
		}
	}

	private void populateBillingAddress(final SummaryStepFormDTO summaryStepFormDTO, final AddressModel paymentAddress)
	{
		if (paymentAddress != null)
		{

			summaryStepFormDTO.setBillingFirstName(paymentAddress.getFirstname());
			summaryStepFormDTO.setBillingLastName(paymentAddress.getLastname());
			summaryStepFormDTO.setBillingAdditionalStreet(paymentAddress.getLine3());
			summaryStepFormDTO.setBillingStreet(paymentAddress.getLine1());
			summaryStepFormDTO.setBillingHouseNumber(paymentAddress.getLine2());
			summaryStepFormDTO.setBillingCity(paymentAddress.getTown());
			summaryStepFormDTO.setBillingZip(paymentAddress.getPostalcode());
			CountryModel country = paymentAddress.getCountry();
			if (country != null)
			{
				summaryStepFormDTO.setBillingCountry(country.getName());
			}
			summaryStepFormDTO.setBillingEmail(paymentAddress.getEmail());
		}

	}

	private void populateDeliveryDate(final SummaryStepFormDTO summaryStepFormDTO, final CartModel cart)
	{
		if (cart.getDeliveryNow() != null)
		{
			summaryStepFormDTO.setDeliveryStart(cart.getDeliveryNow());
			summaryStepFormDTO.setDeliveryDateBlock(true);
		}
		else
		{
			if (cart.getDeliveryStartDate() != null)
			{
				summaryStepFormDTO.setDeliveryStartDate(cart.getDeliveryStartDate());
				summaryStepFormDTO.setDeliveryDateBlock(true);
			}
		}

		summaryStepFormDTO.setDeliveryStartDate(cart.getDeliveryStartDate());
	}

	private void populateShipmentAddress(final SummaryStepFormDTO summaryStepFormDTO, final AddressModel shipmentAddress)
	{
		if (shipmentAddress != null)
		{
			summaryStepFormDTO.setNewShipmentFirstName(shipmentAddress.getFirstname());
			summaryStepFormDTO.setNewShipmentLastName(shipmentAddress.getLastname());
			summaryStepFormDTO.setNewShipmentAdditionalStreet(shipmentAddress.getLine3());
			summaryStepFormDTO.setNewShipmentStreet(shipmentAddress.getLine1());
			summaryStepFormDTO.setNewShipmentHouseNumber(shipmentAddress.getLine2());
			summaryStepFormDTO.setNewShipmentZip(shipmentAddress.getPostalcode());
			summaryStepFormDTO.setNewShipmentCity(shipmentAddress.getTown());

			final CountryModel country = shipmentAddress.getCountry();
			if (country != null)
			{
				summaryStepFormDTO.setNewShipmentCountry(country.getName());
			}
			summaryStepFormDTO.setNewShipmentEmail(shipmentAddress.getEmail());
		}
	}

	private void populateTerms(final SummaryStepFormDTO summaryStepFormDTO,  final CartModel cart)
	{
		TermAcceptedModel optInTerm = getOptInTerm(cart.getTermsAccepted());
		if (optInTerm != null)
		{
			summaryStepFormDTO.setOptIn(optInTerm.getConfirmed());
		}
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		boolean isValidPayment = getValidationService().validatePayment(bindingResult);
      boolean result = getValidationService().validateSummaryDownloadPage((SummaryStepFormDTO) dto, bindingResult);
		return isValidPayment && result && super.validate(dto, bindingResult);
	}

	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart, final CustomerModel customerModel)
	{
		// opt-in term
		TermAcceptedModel optInTerm = getOptInTerm(customerModel.getTermsAccepted());
		if (optInTerm == null)
		{
			debug(LOG, "customerModel doesn't contain the opt-in term. Customer id = %s", customerModel.getCustomerID());
		}
		else if (optInTerm.getConfirmed())
		{
			debug(LOG, "the latest version of opt-in term is already accepted in a customer profile. Customer id = %s",
								 customerModel.getCustomerID());
		}
		else
		{
			Set<TermAcceptedModel> cartTerms = cart.getTermsAccepted();
			if (cartTerms == null || cartTerms == Collections.EMPTY_SET)
			{
				cartTerms = new HashSet<>(1);
			}
			else
			{
				cartTerms = new HashSet<>(cartTerms);
			}
			cartTerms.add(optInTerm);
			cart.setTermsAccepted(cartTerms);
		}
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(
				  FormElementGroupConstants.TERMS_OF_USE_DOWNLOAD_ONLINE_ARTIKLE_PRODUCT_TYPE_FORM_ELEMENT_GROUP);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
	{
		getCartService().saveSummaryStep((SummaryStepFormDTO) dto, cartModel);
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		Set<String> mandatoryFieldNames = new HashSet<>(getAddressMandatoryFieldNames());
		mandatoryFieldNames.addAll(getProductSpecificMandatoryFields(cart));
		return mandatoryFieldNames;
	}

	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		final Map<String, Object> pageModelValues = super.populatePageModelData(cartModel);
		final CartData cartData = defaultProxyCheckoutFacade.getCheckoutCart();
		pageModelValues.put(FormElementGroupConstants.SUMMARY_PAGE_PARAM_CART_DATA, cartData);
		// agb checkbox
		CMSSiteModel currentSite = mccSiteUrlHelper.getCurrentSite();
		String agreeTermsUrl = currentSite.getTermsAndConditionsLink();
		pageModelValues.put(FormElementGroupConstants.SUMMARY_PAGE_PARAM_AGREEMENT_TERMS_URL, agreeTermsUrl);
		String contradictionUrl = currentSite.getContradictionPolicyLink();
		pageModelValues.put(FormElementGroupConstants.SUMMARY_PAGE_PARAM_CONTRADICTION_URL, contradictionUrl);
		// Opt-in checkbox
		TermAcceptedModel optInTerm = getOptInTerm(cartModel.getTermsAccepted());
		if (optInTerm != null && !optInTerm.getConfirmed() && optInTerm.getTermsVersion() != null)
		{
			pageModelValues.put(FormElementGroupConstants.SUMMARY_PAGE_PARAM_OPT_IN_TERMS_URL, optInTerm.getTermsVersion().getUrl());
		}
		return pageModelValues;
	}

	/**
	 * Look for the opt-in term object in provided terms list.
	 * The opt-in term is identifying by the term name value which is configured in local.properties.
	 *
	 * @param terms set of terms where we are looking for the opt-in term.
	 * @return a desirable term object or a null if nothing was found.
	 */
	private TermAcceptedModel getOptInTerm(final Set<TermAcceptedModel> terms)
	{
		String optInTermName = shopConfigurationService.getOptInTermName();
		Assert.notNull(optInTermName, "the terms.optin.term.name configuration value isn't found");
		for (TermAcceptedModel term : terms)
		{
			if (term.getTermsVersion() != null && optInTermName.equals(term.getTermsVersion().getName()))
			{
				return term;
			}
		}
		return null;
	}
}
