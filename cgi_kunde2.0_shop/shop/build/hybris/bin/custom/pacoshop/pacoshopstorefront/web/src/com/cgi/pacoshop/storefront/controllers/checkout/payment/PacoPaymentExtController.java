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
package com.cgi.pacoshop.storefront.controllers.checkout.payment;

import com.cgi.hybris.payment.core.web.controller.PaymentExtController;
import com.cgi.hybris.payment.core.web.forms.BillingAddressForm;
import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @module shop
 * @version 1.0v Apr 16, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@Controller
@RequestMapping("/checkout/payment")
public class PacoPaymentExtController extends PaymentExtController
{
    private static final String DYNAMIC_CHECKOUT_URL = "/dynamiccheckout";

    @Resource
    private CheckoutCompletionFacade checkoutCompletionFacade;

    /**
     * Handles AJAX request from paymentPage.
     *
     * @param model - mvc model
     * @param billingAddressForm - name
     * @param bindingResult - bindingResult
     * @return  - redirects to DCF
     * @throws de.hybris.platform.cms2.exceptions.CMSItemNotFoundException - cms exception
     */
    @RequestMapping("/add-billing-address")
    public String saveBillingAddress(final Model model, final BillingAddressForm billingAddressForm,
                                     final BindingResult bindingResult) throws CMSItemNotFoundException
    {
        checkoutCompletionFacade.saveBillingAddress();
        return REDIRECT_PREFIX + DYNAMIC_CHECKOUT_URL;
    }
}
