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
package com.cgi.pacoshop.storefront.controllers;

import com.cgi.pacoshop.core.model.SSORegisterLightBoxComponentModel;
import de.hybris.platform.acceleratorcms.model.components.CategoryFeatureComponentModel;
import de.hybris.platform.acceleratorcms.model.components.DynamicBannerComponentModel;
import de.hybris.platform.acceleratorcms.model.components.FooterComponentModel;
import de.hybris.platform.acceleratorcms.model.components.MiniCartComponentModel;
import de.hybris.platform.acceleratorcms.model.components.NavigationBarComponentModel;
import de.hybris.platform.acceleratorcms.model.components.ProductFeatureComponentModel;
import de.hybris.platform.acceleratorcms.model.components.ProductReferencesComponentModel;
import de.hybris.platform.acceleratorcms.model.components.PurchasedProductReferencesComponentModel;
import de.hybris.platform.acceleratorcms.model.components.SubCategoryListComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSImageComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;

import com.cgi.pacoshop.core.model.HeaderAuthComponentModel;
import com.cgi.pacoshop.core.model.SSOLoginIFrameComponentModel;
import com.cgi.pacoshop.core.model.SSORegisterFormComponentModel;


/**
 * Frontend controller constants.
 * 
 * @version 1.0v May 6, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 */
public interface ControllerConstants
{
	/**
	 * Class with action name constants
	 */
	interface Actions
	{
		interface Cms
		{
			String _Prefix = "/view/";
			String _Suffix = "Controller";

			/**
			 * Default CMS component controller
			 */
			String DefaultCMSComponent = _Prefix + "DefaultCMSComponentController";

			/**
			 * CMS components that have specific handlers
			 */
			String PurchasedProductReferencesComponent = _Prefix + PurchasedProductReferencesComponentModel._TYPECODE + _Suffix;
			String ProductReferencesComponent          = _Prefix + ProductReferencesComponentModel._TYPECODE + _Suffix;
			String ProductCarouselComponent            = _Prefix + ProductCarouselComponentModel._TYPECODE + _Suffix;
			String MiniCartComponent                   = _Prefix + MiniCartComponentModel._TYPECODE + _Suffix;
			String ProductFeatureComponent             = _Prefix + ProductFeatureComponentModel._TYPECODE + _Suffix;
			String CategoryFeatureComponent            = _Prefix + CategoryFeatureComponentModel._TYPECODE + _Suffix;
			String NavigationBarComponent              = _Prefix + NavigationBarComponentModel._TYPECODE + _Suffix;
			String CMSLinkComponent                    = _Prefix + CMSLinkComponentModel._TYPECODE + _Suffix;
			String DynamicBannerComponent              = _Prefix + DynamicBannerComponentModel._TYPECODE + _Suffix;
			String FooterComponent                     = _Prefix + FooterComponentModel._TYPECODE + _Suffix;
			String SubCategoryListComponent            = _Prefix + SubCategoryListComponentModel._TYPECODE + _Suffix;
			String SSOLoginIFrameComponent             = _Prefix + SSOLoginIFrameComponentModel._TYPECODE + _Suffix;
			String SSORegisterFormComponent            = _Prefix + SSORegisterFormComponentModel._TYPECODE + _Suffix;
			String SSORegisterLightBoxComponent        = _Prefix + SSORegisterLightBoxComponentModel._TYPECODE + _Suffix;
			String CMSImageComponent                   = _Prefix + CMSImageComponentModel._TYPECODE + _Suffix;
			String HeaderAuthComponent                 = _Prefix + HeaderAuthComponentModel._TYPECODE + _Suffix;
		}
	}

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		interface Cms
		{
			String ComponentPrefix = "cms/";
		}

		interface Pages
		{
			interface Account
			{
				String AccountLoginPage            = "pages/account/accountLoginPage";
				String AccountHomePage             = "pages/account/accountHomePage";
				String AccountOrderHistoryPage     = "pages/account/accountOrderHistoryPage";
				String AccountOrderPage            = "pages/account/accountOrderPage";
				String AccountProfilePage          = "pages/account/accountProfilePage";
				String AccountProfileEditPage      = "pages/account/accountProfileEditPage";
				String AccountProfileEmailEditPage = "pages/account/accountProfileEmailEditPage";
				String AccountChangePasswordPage   = "pages/account/accountChangePasswordPage";
				String AccountAddressBookPage      = "pages/account/accountAddressBookPage";
				String AccountEditAddressPage      = "pages/account/accountEditAddressPage";
				String AccountPaymentInfoPage      = "pages/account/accountPaymentInfoPage";
				String AccountRegisterPage         = "pages/account/accountRegisterPage";
			}

			interface Checkout
			{
				String CheckoutRegisterPage     = "pages/checkout/checkoutRegisterPage";
				String CheckoutConfirmationPage = "pages/checkout/checkoutConfirmationPage";
			}

			interface SingleStepCheckout
			{
				String CheckoutLoginPage   = "pages/checkout/single/checkoutLoginPage";
				String CheckoutSummaryPage = "pages/checkout/single/checkoutSummaryPage";
			}

			interface MultiStepCheckout
			{
				String CheckoutLoginPage          = "pages/checkout/multi/checkoutLoginPage";
				String AddEditDeliveryAddressPage = "pages/checkout/multi/addEditDeliveryAddressPage";
				String ChooseDeliveryMethodPage   = "pages/checkout/multi/chooseDeliveryMethodPage";
				String ChoosePickupLocationPage   = "pages/checkout/multi/choosePickupLocationPage";
				String AddPaymentMethodPage       = "pages/checkout/multi/addPaymentMethodPage";
				String CheckoutSummaryPage        = "pages/checkout/multi/checkoutSummaryPage";
				String HostedOrderPageErrorPage   = "pages/checkout/multi/hostedOrderPageErrorPage";
				String HostedOrderPostPage = "pages/checkout/multi/hostedOrderPostPage";
				String SilentOrderPostPage = "pages/checkout/multi/silentOrderPostPage";
			}

			interface Password
			{
				String PasswordResetChangePage = "pages/password/passwordResetChangePage";
			}

			interface Error
			{
				String ErrorNotFoundPage = "pages/error/errorNotFoundPage";
			}

			interface Cart
			{
				String CartPage = "pages/cart/cartPage";
			}

			interface StoreFinder
			{
				String StoreFinderSearchPage = "pages/storeFinder/storeFinderSearchPage";
				String StoreFinderDetailsPage = "pages/storeFinder/storeFinderDetailsPage";
				String StoreFinderViewMapPage = "pages/storeFinder/storeFinderViewMapPage";
			}

			interface Misc
			{
				String MiscRobotsPage = "pages/misc/miscRobotsPage";
			}

			interface Guest
			{
				String GuestOrderPage = "pages/guest/guestOrderPage";
				String GuestOrderErrorPage = "pages/guest/guestOrderErrorPage";
			}

			interface Product
			{
				String WriteReview = "pages/product/writeReview";
			}
		}

		interface Fragments
		{
			interface Cart
			{
				String AddToCartPopup = "fragments/cart/addToCartPopup";
				String MiniCartPanel = "fragments/cart/miniCartPanel";
				String MiniCartErrorPanel = "fragments/cart/miniCartErrorPanel";
				String CartPopup = "fragments/cart/cartPopup";
			}

			interface Account
			{
				String CountryAddressForm = "fragments/address/countryAddressForm";
			}

			interface Checkout
			{
				String TermsAndConditionsPopup = "fragments/checkout/termsAndConditionsPopup";
				String BillingAddressForm = "fragments/checkout/billingAddressForm";
			}

			interface SingleStepCheckout
			{
				String DeliveryAddressFormPopup = "fragments/checkout/single/deliveryAddressFormPopup";
				String PaymentDetailsFormPopup = "fragments/checkout/single/paymentDetailsFormPopup";
			}

			interface Password
			{
				String PasswordResetRequestPopup = "fragments/password/passwordResetRequestPopup";
				String ForgotPasswordValidationMessage = "fragments/password/forgotPasswordValidationMessage";
			}

			interface Product
			{
				String QuickViewPopup = "fragments/product/quickViewPopup";
				String ZoomImagesPopup = "fragments/product/zoomImagesPopup";
				String ReviewsTab = "fragments/product/reviewsTab";
				String StorePickupSearchResults = "fragments/product/storePickupSearchResults";
			}
		}
	}
}
