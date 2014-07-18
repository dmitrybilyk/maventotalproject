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
import de.hybris.platform.acceleratorfacades.order.impl.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.address.AddressVerificationFacade;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import com.cgi.pacoshop.core.checkout.flow.CheckoutFlowStrategy;
import com.cgi.pacoshop.core.enums.CheckoutFlowEnum;
import com.cgi.pacoshop.facades.flow.CheckoutFlowFacade;
import com.cgi.pacoshop.storefront.controllers.pages.AbstractPageController;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;


/**
 * Base controller for all page controllers. Provides common functionality for all page controllers.
 */
public abstract class AbstractCheckoutController extends AbstractPageController
{
	protected static final String REDIRECT_URL_ORDER_CONFIRMATION = REDIRECT_PREFIX + "/checkout/orderConfirmation/";

	@Resource(name = "userFacade")
	private PacoUserFacade userFacade;

	@Resource(name = "checkoutFlowFacade")
	private CheckoutFlowFacade checkoutFlowFacade;

	@Resource(name = "addressVerificationFacade")
	private AddressVerificationFacade addressVerificationFacade;

	@Resource(name = "checkoutFlowStrategy")
	private CheckoutFlowStrategy checkoutFlowStrategy;

	@Resource(name = "i18NFacade")
	private I18NFacade i18NFacade;

	@Resource(name = "acceleratorCheckoutFacade")
	private AcceleratorCheckoutFacade checkoutFacade;

	@Resource(name = "checkoutCustomerStrategy")
	private CheckoutCustomerStrategy checkoutCustomerStrategy;

	protected PacoUserFacade getUserFacade()
	{
		return userFacade;
	}

	protected CheckoutFlowFacade getCheckoutFlowFacade()
	{
		return checkoutFlowFacade;
	}

	protected AddressVerificationFacade getAddressVerificationFacade()
	{
		return addressVerificationFacade;
	}

	protected CheckoutFlowStrategy getCheckoutFlowStrategy()
	{
		return checkoutFlowStrategy;
	}

	protected I18NFacade getI18NFacade()
	{
		return i18NFacade;
	}

	protected AcceleratorCheckoutFacade getCheckoutFacade()
	{
		return checkoutFacade;
	}

	protected CheckoutCustomerStrategy getCheckoutCustomerStrategy()
	{
		return checkoutCustomerStrategy;
	}

	/**
	 * Checks if there are any items in the cart.
	 *
	 * @return returns true if items found in cart.
	 */
	protected boolean hasValidCart()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		boolean validCart = cartData.getEntries() != null && !cartData.getEntries().isEmpty();
		if (validCart)
		{
			final CheckoutFlowEnum checkoutFlowEnum = checkoutFlowStrategy.getCheckoutFlow();
			if (CheckoutFlowEnum.SINGLE.equals(checkoutFlowEnum) && hasPickUpItems())
			{
				validCart = false;
			}
		}

		return validCart;
	}


	/**
	 * Returns true if cart contains any pick up entries
	 */
	protected boolean hasPickUpItems()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		if (CollectionUtils.isNotEmpty(cartData.getEntries()))
		{
			for (final OrderEntryData entry : cartData.getEntries())
			{
				if (entry.getDeliveryPointOfService() != null)
				{
					return true;
				}
			}
		}
		return false;
	}


	/**
	 * Returns true if cart contains any shipping entries
	 */
	protected boolean hasShippingItems()
	{
		final CartData cartData = getCheckoutFlowFacade().getCheckoutCart();
		if (CollectionUtils.isNotEmpty(cartData.getEntries()))
		{
			for (final OrderEntryData entry : cartData.getEntries())
			{
				if (entry.getDeliveryPointOfService() == null)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return True if cart is null or does not have deliveryAddress and if order has shipping items otherwise returns
	 *         false
	 */
	protected boolean hasNoDeliveryAddress()
	{
		final boolean hasShipping = hasShippingItems();
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		return hasShipping && (cartData == null || cartData.getDeliveryAddress() == null);
	}

	/**
	 * @return True if cart is null or does not have deliveryMode and if order has shipping items otherwise returns false
	 */
	protected boolean hasNoDeliveryMode()
	{
		final boolean hasShipping = hasShippingItems();
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		return hasShipping && (cartData == null || cartData.getDeliveryMode() == null);
	}

	/**
	 * @return True if cart is null or does not have paymentInfo and if order has shipping items otherwise returns false
	 */
	protected boolean hasNoPaymentInfo()
	{
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		return (cartData == null || cartData.getPaymentInfo() == null);
	}

	protected boolean isAddressIdChanged(final AddressData cartCheckoutDeliveryAddress, final AddressData selectedAddressData)
	{
		return (cartCheckoutDeliveryAddress == null || !selectedAddressData.getId().equals(cartCheckoutDeliveryAddress.getId()));
	}

	protected List<? extends AddressData> getDeliveryAddresses(final AddressData selectedAddressData)
	{
		List<AddressData> deliveryAddresses = null;
		if (selectedAddressData != null)
		{
			deliveryAddresses = getCheckoutFacade().getSupportedDeliveryAddresses(true);

			if (deliveryAddresses == null || deliveryAddresses.isEmpty())
			{
				deliveryAddresses = Collections.singletonList(selectedAddressData);
			}
			else if (!isAddressOnList(deliveryAddresses, selectedAddressData))
			{
				deliveryAddresses.add(selectedAddressData);
			}
		}

		return deliveryAddresses == null ? Collections.<AddressData> emptyList() : deliveryAddresses;
	}

	protected boolean isAddressOnList(final List<AddressData> deliveryAddresses, final AddressData selectedAddressData)
	{
		if (deliveryAddresses == null || selectedAddressData == null)
		{
			return false;
		}

		for (final AddressData address : deliveryAddresses)
		{
			if (address.getId().equals(selectedAddressData.getId()))
			{
				return true;
			}
		}

		return false;
	}


	protected String redirectToOrderConfirmationPage(final OrderData orderData)
	{
		return REDIRECT_URL_ORDER_CONFIRMATION
				+ (getCheckoutCustomerStrategy().isAnonymousCheckout() ? orderData.getGuid() : orderData.getCode());
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

	public static class CheckoutSteps
	{
		private final String stepName;
		private final String url;

		public CheckoutSteps(final String stepName, final String url)
		{
			this.stepName = stepName;
			this.url = url;
		}

		public String getStepName()
		{
			return stepName;
		}

		public String getUrl()
		{
			return url;
		}
	}
}
