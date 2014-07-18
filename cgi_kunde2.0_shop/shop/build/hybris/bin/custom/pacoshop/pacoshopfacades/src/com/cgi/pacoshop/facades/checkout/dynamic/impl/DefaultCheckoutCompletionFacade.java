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

import com.cgi.hybris.payment.core.facades.PaymentExtFacade;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.util.LogHelper;
import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;

import de.hybris.platform.commercefacades.order.CheckoutFacade;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static org.apache.commons.lang.StringUtils.isNotEmpty;

/**
 * Implementation of {@link com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade}
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @version 1.0v May 12, 2014
 * @module hybris - pacoshopfacades
 * @link http ://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 */
public class  DefaultCheckoutCompletionFacade implements CheckoutCompletionFacade
{
    private static final Logger LOG = Logger.getLogger(DefaultCheckoutCompletionFacade.class);

    private static final String MODEL_ORDER_ID     = "orderid";
    private static final String THANK_YOU_PAGE_URL = "/thankyou";
    private static final String MODEL_REDIRECT_URL = "redirecturl";
    private static final String PARAM_ORDERID      = "orderguid";

    @Resource
    private PaymentExtFacade paymentExtFacade;

    @Resource
    private CheckoutFacade checkoutFacade;

    @Resource
    private ConfigurationService configurationService;

    @Resource
    private ModelService modelService;

    @Resource
    private CartService cartService;

	 @Override
	 public boolean hasCheckoutCart()
	 {
		return checkoutFacade.hasCheckoutCart();
	 }

    @Override
    public OrderData submitOrder(final HttpSession session) throws InvalidCartException
    {
        final OrderData orderData = checkoutFacade.placeOrder();
		  session.setAttribute(MODEL_ORDER_ID, orderData.getCode());
        return orderData;
    }

    @Override
    public String submitPayment(final CartModel cart, final String remoteAddress, final String userAgent)
    {
        return cart.getTotalPrice() > 0 ? paymentExtFacade.initPayment(remoteAddress, userAgent) : null;
    }

    @Override
    public String getCompletionRedirectUrl(final OrderData orderData, final String redirectUrl)
    {
        String result = null;

        boolean cartContainsOnlineArticleProduct = cartContainsOnlineArticleProduct(orderData);
        if (isNotEmpty(redirectUrl) && cartContainsOnlineArticleProduct)
        {
            result = redirectUrl;
        }
        else
        {
			StringBuilder resultBuilder = new StringBuilder()
					.append(THANK_YOU_PAGE_URL)
					.append("?")
					.append(PARAM_ORDERID)
					.append("=")
					.append(orderData.getGuid());

			if (isNotEmpty(redirectUrl) && orderData.getDeliveryAddress() == null)
			{
				resultBuilder.append("&").append(MODEL_REDIRECT_URL).append("=").append(redirectUrl);
			}
			result = resultBuilder.toString();
		}
        return result;
    }

	@Override
    public void saveBillingAddress()
    {
        final CartModel cart = cartService.getSessionCart();
        final PaymentInfoModel paymentInfoModel = cart.getPaymentInfo();

        cart.setPrepayOnly(isProductPrepayOnly(cart));
        modelService.save(cart);

        if (paymentInfoModel != null)
        {
            final AddressModel paymentAddress = cart.getPaymentAddress();
            final AddressModel customerAddress = cart.getCustomerAddress();
            final AddressModel billingAddress;

            if (paymentAddress != null)
            {
                billingAddress = modelService.clone(paymentAddress);
            }
            else if (customerAddress != null)
            {
                billingAddress = modelService.clone(customerAddress);
            }
            else
            {
                LogHelper.error(LOG, "cart with code %s has no paymentAddress, no customerAddress - can't save billingAddress",
                                cart.getCode());
                return;
            }

            billingAddress.setOwner(paymentInfoModel);
            paymentInfoModel.setBillingAddress(billingAddress);

            modelService.saveAll(billingAddress, paymentInfoModel);
        }
        else
        {
            throw new RuntimeException(
                    "Failed to save billing address on the payment info, because he payment info is null. "
                            + "You need to save the payment info on the cart first.");

        }
    }

    private boolean cartContainsOnlineArticleProduct(final OrderData orderData)
    {
        final String productName = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);

        for (final OrderEntryData entries : orderData.getEntries())
        {
            if (entries.getProduct() != null)
            {
                if (entries.getProduct().getProductType().equalsIgnoreCase(productName))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if one of the products contains prepayOnly flag.
     *
     * @param cartModel Cart
     * @return true if product has prepayOnly flag set and false otherwise
     */
    private static boolean isProductPrepayOnly(final CartModel cartModel)
    {
        for (final AbstractOrderEntryModel abstractOrderEntryModel : cartModel.getEntries())
        {
            final ProductModel productModel = abstractOrderEntryModel.getProduct();
            if (productModel.getPrepayOnly() != null && productModel.getPrepayOnly())
            {
                return true;
            }
        }

        return false;
    }

    private String getConfigurationProperty(final String key)
    {
        return configurationService.getConfiguration().getString(key);
    }
}
