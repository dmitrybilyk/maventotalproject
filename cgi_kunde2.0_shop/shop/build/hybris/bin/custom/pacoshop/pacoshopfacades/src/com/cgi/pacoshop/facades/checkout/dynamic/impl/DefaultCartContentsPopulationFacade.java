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
package com.cgi.pacoshop.facades.checkout.dynamic.impl;

import com.cgi.hybris.payment.core.data.PaymentInfoData;
import com.cgi.pacoshop.core.checkout.dynamic.CartContentsFragmentDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryInfoFormDTO;
import com.cgi.pacoshop.core.exceptions.dynamic.OrderNotFoundException;
import com.cgi.pacoshop.core.model.OrderTotalsDTO;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.model.TaxValueDTO;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.CustomerAddressService;
import com.cgi.pacoshop.core.service.productdtopopulation.ProductDTOPopulationServiceLocator;
import com.cgi.pacoshop.core.util.sap.PriceDisplayingFormatter;
import com.cgi.pacoshop.facades.checkout.dynamic.CartContentsPopulationFacade;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.util.TaxValue;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * Facade to populate the CartContentsFragmentDTO from order.
 * The information from that object will be displayed on login, summary and thankyou pages.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@symmetrics.de>
 * @version 1.0v May 12, 2014
 * @module hybris - pacoshopfacades
 * @link http ://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 */
public class DefaultCartContentsPopulationFacade implements CartContentsPopulationFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultCartContentsPopulationFacade.class);

	private static final String NO_TAX = "NO_TAX";

	private static final String NEWSLETTER = "NEWSLETTER";

	@Resource
	private CartService cartService;

	@Resource
	private ProductDTOPopulationServiceLocator<AbstractOrderEntryModel> productDTOPopulationServiceLocator;

	@Resource
	private CustomerAccountService customerAccountService;

	@Resource
	private BaseStoreService baseStoreService;

	@Resource
	private Converter<PaymentInfoModel, PaymentInfoData> extCorePaymentInfoConverter;

	@Resource
	private ConfigurationService configurationService;

	@Resource
	private CustomerAddressService customerAddressService;

	@Resource
	private CMSSiteService cmsSiteService;

	/**
	 * Fill the dto to display products in login, summary and thankyou pages.
	 * @param cartContentsFragmentDTO the cart contents fragment dTO
	 * @param orderId - order id.
	 * @return the CartContentsFragmentDTO.
	 */
	@Override
	@ModelAttribute
	public CartContentsFragmentDTO populateCartContentsFragment(
			  final CartContentsFragmentDTO cartContentsFragmentDTO, final String orderId)
	{
		AbstractOrderModel source;
		//get list of products in the cart
		List<ProductDTO> productDTOs;
		OrderModel orderModel = null;
		if (isNotEmpty(orderId))
		{
			final BaseStoreModel baseStoreModel = baseStoreService.getCurrentBaseStore();
			orderModel = customerAccountService.getOrderDetailsForGUID(orderId, baseStoreModel);
			if (orderModel == null)
			{
				throw new OrderNotFoundException(
						  "Order with orderGUID " + orderId + " not found for current user in current BaseStore");
			}
			cartContentsFragmentDTO.setOrderCode(orderModel.getCode());
			source = orderModel;
		}
		else
		{
			source = cartService.getSessionCart();
		}

		List<AbstractOrderEntryModel> entries = source.getEntries();
		if (!entries.isEmpty())
		{
			productDTOs = productDTOPopulationServiceLocator.populate(entries);

			cartContentsFragmentDTO.setProducts(productDTOs);
			// Set taxes and totals.
			OrderTotalsDTO totals = cartContentsFragmentDTO.getTotals();
			totals.setTotalPrice(source.getTotalPrice());
			totals.setDeliveryCost(source.getDeliveryCost());
			totals.setTotalTaxValues(getTotalTaxValuesForShowing(source));
			totals.setPaymentCost(source.getPaymentCost());
			totals.setTotalTax(source.getTotalTax());

			cartContentsFragmentDTO.setDownloadButtonDisplayed(isDownloadButtonDisplayed(orderModel));
			totals.setSubTotal(source.getSubtotal() - source.getTotalTax());
			if (source.getCurrency() != null)
			{
				totals.setCurrency(source.getCurrency().getSymbol());
			}
			populateProductForShowing(productDTOs);
			populateTotalsForShowing(totals);
		}

		// if redirect URL is not provided  the URL from CMSSiteServiceWill be used
		// in other case will be used redirectURL attribute for download button or 'Mehr entdecken' link
		if (StringUtils.isEmpty(source.getRedirectUrl()))
		{
			cartContentsFragmentDTO.setRedirectUrl(cmsSiteService.getCurrentSite().getSiteOwnerValue());
			cartContentsFragmentDTO.setRedirectUrlDescription(cmsSiteService.getCurrentSite().getSiteOwnerDescription());
		}
		else
		{
			cartContentsFragmentDTO.setRedirectUrl(source.getRedirectUrl());
			cartContentsFragmentDTO.setRedirectUrlDescription(source.getRedirectUrlDescription());
		}

		if (orderModel != null)
		{
			SummaryInfoFormDTO form = new SummaryInfoFormDTO();
			populateCustomerDataFromOrder(form, orderModel);
			populateDeliveryDataFromOrder(form, orderModel);
			populateBillingDataFromOrder(form, orderModel);

			cartContentsFragmentDTO.setForm(form);

			// customer info block shows Only If Invoice Wanted Checked Or Not Exist
			// same condition in CustomerInfoSummaryPageFormElementGroup
			cartContentsFragmentDTO.setCustomerInfoDisplayed(
					  orderModel.getInvoiceWanted() != null ? orderModel.getInvoiceWanted() : true);

			PaymentInfoModel paymentInfo = orderModel.getPaymentInfo();
			if (paymentInfo != null)
			{
				cartContentsFragmentDTO.setPaymentInfo(extCorePaymentInfoConverter.convert(paymentInfo));
			}
		}
		return cartContentsFragmentDTO;
	}

	/**
	 * Set prices with space for products.
	 * @param productDTOs
	 */
	private void populateProductForShowing(final List<ProductDTO> productDTOs)
	{
		for (ProductDTO product : productDTOs)
		{
			if (product.getBonuses() != null)
			{
				for (ProductDTO bonuses : product.getBonuses())
				{
					bonuses.setAdditionalPaymentWithCurrency(PriceDisplayingFormatter.formatPriceWithSpace(
							  bonuses.getAdditionalPayment(),
							  bonuses.getCurrency()));
				}
			}
			product.setPriceWithCurrency(PriceDisplayingFormatter.formatPriceWithSpace(product.getPrice(), product.getCurrency()));
		}
	}

	/**
	 * Set prices with space between price and currency symbol.
	 * @param totals
	 */
	private void populateTotalsForShowing(final OrderTotalsDTO totals)
	{
		totals.setTotalPriceForShowing(PriceDisplayingFormatter.formatPriceWithSpace(totals.getTotalPrice(), totals.getCurrency()));
		totals.setSubTotalForShowing(PriceDisplayingFormatter.formatPriceWithSpace(totals.getSubTotal(), totals.getCurrency()));
		totals.setDeliveryCostForShowing(PriceDisplayingFormatter.formatPriceWithSpace(totals.getDeliveryCost(),
																												 totals.getCurrency()));
		totals.setPaymentCostForShowing(
				  PriceDisplayingFormatter.formatPriceWithSpace(totals.getPaymentCost(), totals.getCurrency()));
		for (TaxValueDTO taxValue : totals.getTotalTaxValues())
		{
			taxValue.setAppliedValueForShowing(PriceDisplayingFormatter.formatPriceWithSpace(taxValue.getAppliedValue(),
																													 totals.getCurrency()));
		}
	}

	// FIXME: create populator class later
	private Collection<TaxValueDTO> getTotalTaxValuesForShowing(final AbstractOrderModel sessionCart)
	{
		Collection<TaxValue> cartList = sessionCart.getTotalTaxValues();
		Collection<TaxValueDTO> resultList = new ArrayList<>();
		for (TaxValue taxValue : cartList)
		{
			if (existInResultList(taxValue, resultList))
			{
				updateResult(taxValue, resultList);
			}
			else
			{
				if (!taxValue.getCode().equals(NO_TAX))
				{
					addTaxInResult(taxValue, resultList);
				}
			}
		}
		return resultList;
	}

	// FIXME: create populator class later
	private void addTaxInResult(final TaxValue taxValue, final Collection<TaxValueDTO> resultList)
	{
		TaxValueDTO result = new TaxValueDTO();
		result.setValue(taxValue.getValue());
		result.setAppliedValue(taxValue.getAppliedValue());
		resultList.add(result);
	}

	// FIXME: create populator class later
	private void updateResult(final TaxValue taxValue, final Collection<TaxValueDTO> resultList)
	{
		TaxValueDTO result = getTaxValueDTObyValue(taxValue.getValue(), resultList);
		Double taxSumm = result.getAppliedValue() + taxValue.getAppliedValue();
		result.setAppliedValue(taxSumm);
	}

	// FIXME: create populator class later
	private boolean existInResultList(final TaxValue taxValue, final Collection<TaxValueDTO> resultList)
	{
		return getTaxValueDTObyValue(taxValue.getValue(), resultList) != null;
	}

	// FIXME: create populator class later
	private TaxValueDTO getTaxValueDTObyValue(final Double value, final Collection<TaxValueDTO> taxList)
	{
		TaxValueDTO result = null;
		for (TaxValueDTO taxValueDTO : taxList)
		{
			if (taxValueDTO.getValue().compareTo(value) == 0)
			{
				result = taxValueDTO;
			}
		}
		debug(LOG, "Check, if 'null' then TaxValue not exist in result list, result: [%s]", result);
		return result;
	}

	// FIXME: create populator class later
	private boolean isDownloadButtonDisplayed(final OrderModel orderModel)
	{
		//KS-2547: In case of a digital product which is supposed to be consumed by the customer him/herself
		// (there is no deviating shipment address) and there is a specified returnUrl in the cart,
		// the system displays a link for the download (see wireframe). "
		if (
				orderModel != null
						&& orderModel.getDeliveryAddress() == null
						&& StringUtils.isNotEmpty(orderModel.getRedirectUrl())
				)
		{
			for (final AbstractOrderEntryModel abstractOrderEntryModel : orderModel.getEntries())
			{
				final ProductModel product = abstractOrderEntryModel.getProduct();
				if (product.getDigital() && !product.getProductType().getCode().equals(NEWSLETTER))
				{
					return true;
				}
			}
		}
		return false;
	}

	// FIXME: create populator class later

	/**
	 * Populate customer data for summary info form.
	 *
	 * @param form       Form DTO.
	 * @param orderModel Order model.
	 */
	private void populateCustomerDataFromOrder(final SummaryInfoFormDTO form, final OrderModel orderModel)
	{
		AddressModel customerAddress = orderModel.getCustomerAddress();
		if (customerAddress != null)
		{
			final TitleModel title = customerAddress.getTitle();
			if (title != null)
			{
				form.setSalutation(title.getCode());
			}
			final CountryModel country = customerAddress.getCountry();
			if (country != null)
			{
				form.setCountry(country.getName());
			}

			form.setEmail(customerAddress.getEmail());
			form.setFirstName(customerAddress.getFirstname());
			form.setLastName(customerAddress.getLastname());
			form.setCompany(customerAddress.getCompany());
			form.setStreet(customerAddress.getLine1());
			form.setHouseNumber(customerAddress.getLine2());
			form.setAdditionalStreet(customerAddress.getLine3());
			form.setZip(customerAddress.getPostalcode());
			form.setCity(customerAddress.getTown());
			form.setMobilePhoneNumber(customerAddress.getMobileNumber());
			form.setBusinessPhoneNumber(customerAddress.getBusinessNumber());
			form.setHomePhoneNumber(customerAddress.getHomeNumber());
		}
	}

	// FIXME: create populator class later

	/**
	 * Populate delivery data for summary info form.
	 *
	 * @param form       Form DTO.
	 * @param orderModel Order model.
	 */
	private void populateDeliveryDataFromOrder(final SummaryInfoFormDTO form, final OrderModel orderModel)
	{
		// Set delivery data.
		final AddressModel deliveryAddress = orderModel.getDeliveryAddress();
		final Boolean additionalShippingAddressWanted = orderModel.getAdditionalShippingAddressWanted();
		if (additionalShippingAddressWanted != null && additionalShippingAddressWanted && deliveryAddress != null)
		{
			final TitleModel title = deliveryAddress.getTitle();
			if (title != null)
			{
				form.setNewShipmentSalutation(title.getCode());
			}
			final CountryModel country = deliveryAddress.getCountry();
			if (country != null)
			{
				form.setNewShipmentCountry(country.getName());
			}

			form.setNewShipmentFirstName(deliveryAddress.getFirstname());
			form.setNewShipmentLastName(deliveryAddress.getLastname());
			form.setNewShipmentEmail(deliveryAddress.getEmail());

			final Title2Model title2 = deliveryAddress.getTitle2();
			if (title2 != null)
			{
				form.setNewShipmentTitle(title2.getCode());
			}

			form.setNewShipmentStreet(deliveryAddress.getLine1());
			form.setNewShipmentHouseNumber(deliveryAddress.getLine2());
			form.setNewShipmentAdditionalStreet(deliveryAddress.getLine3());
			form.setNewShipmentZip(deliveryAddress.getPostalcode());
			form.setNewShipmentCity(deliveryAddress.getTown());
			form.setNewShipmentCompany(deliveryAddress.getCompany());
		}

		//////////////////////////
		// Set delivery date data.
		//////////////////////////
		if (orderModel.getDeliveryNow() != null)
		{
			form.setDeliveryStart(orderModel.getDeliveryNow());
			form.setDeliveryDateBlock(true);
		}
		else
		{
			if (orderModel.getDeliveryStartDate() != null)
			{
				form.setDeliveryStartDate(orderModel.getDeliveryStartDate());
				form.setDeliveryDateBlock(true);
			}
		}
	}

	// FIXME: create populator class later

	/**
	 * Populate billing data for summary form.
	 *
	 * @param form       Form DTO.
	 * @param orderModel Order model.
	 */
	private void populateBillingDataFromOrder(final SummaryInfoFormDTO form, final OrderModel orderModel)
	{
		// Set billing data.
		final AddressModel paymentAddress = orderModel.getPaymentAddress();
		final AddressModel customerAddress = orderModel.getCustomerAddress();

		final Boolean additionalInvoiceAddressWanted = orderModel.getAdditionalInvoiceAddressWanted();
		if (paymentAddress != null
				&& additionalInvoiceAddressWanted != null
				&& additionalInvoiceAddressWanted
				&& !customerAddressService.compareAddressesByFields(paymentAddress, customerAddress)
				)
		{
			final TitleModel title = paymentAddress.getTitle();
			if (title != null)
			{
				form.setBillingSalutation(title.getCode());
			}
			final CountryModel country = paymentAddress.getCountry();
			if (country != null)
			{
				form.setBillingCountry(country.getName());
			}

			form.setBillingFirstName(paymentAddress.getFirstname());
			form.setBillingLastName(paymentAddress.getLastname());
			form.setBillingEmail(paymentAddress.getEmail());
			final Title2Model title2 = paymentAddress.getTitle2();
			if (title2 != null)
			{
				form.setBillingTitle(title2.getCode());
			}

			form.setBillingStreet(paymentAddress.getLine1());
			form.setBillingHouseNumber(paymentAddress.getLine2());
			form.setBillingAdditionalStreet(paymentAddress.getLine3());
			form.setBillingZip(paymentAddress.getPostalcode());
			form.setBillingCity(paymentAddress.getTown());
			form.setBillingCompany(paymentAddress.getCompany());
		}
	}
}
