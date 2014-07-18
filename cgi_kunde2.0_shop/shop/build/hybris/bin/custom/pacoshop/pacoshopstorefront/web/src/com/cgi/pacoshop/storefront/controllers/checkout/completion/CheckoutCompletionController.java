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
package com.cgi.pacoshop.storefront.controllers.checkout.completion;

import com.cgi.pacoshop.core.checkout.dynamic.CartContentsFragmentDTO;
import com.cgi.pacoshop.facades.checkout.dynamic.CartContentsPopulationFacade;
import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;
import com.cgi.pacoshop.storefront.controllers.pages.checkout.AbstractCheckoutController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Controller used to complete the checkout and show the "Thank you" page.
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v may 13, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@Controller
public class CheckoutCompletionController extends AbstractCheckoutController
{
    private static final Logger LOG = Logger.getLogger(CheckoutCompletionController.class);

    private static final String CONFIG_THANKYOUPAGE_CMS_LABEL = "dcf.thankoyupage.cms.label";
    private static final String THANK_YOU_URL = "/thankyou";
    private static final String PARAM_ORDER_ID = "orderguid";

    @Resource
    private CartContentsPopulationFacade cartContentsPopulationFacade;

    @Resource
    private ConfigurationService configurationService;

    @Resource
    private CheckoutCompletionFacade checkoutCompletionFacade;

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
     * @return thank you page.
     * @throws de.hybris.platform.cms2.exceptions.CMSItemNotFoundException the cMS item not found exception
     */
    @RequestMapping (value = THANK_YOU_URL, method = RequestMethod.GET)
    public String proceedToLastStep(
            final Model model,
            final @RequestParam(PARAM_ORDER_ID) String orderId)
            throws CMSItemNotFoundException
    {
        CartContentsFragmentDTO cartContentsFragmentDTO = new CartContentsFragmentDTO();
        cartContentsPopulationFacade.populateCartContentsFragment(cartContentsFragmentDTO, orderId);
        model.addAttribute(cartContentsFragmentDTO);
		  model.addAttribute("toShowOnlyDeliveryPeriodInDescription", true);
        String thankYouPageLabel = getConfigurationProperty(CONFIG_THANKYOUPAGE_CMS_LABEL);
        final ContentPageModel contentPageForLabelOrId = getContentPageForLabelOrId(thankYouPageLabel);
        return getViewForPage(contentPageForLabelOrId);
    }

    private String getConfigurationProperty(final String key)
    {
        return configurationService.getConfiguration().getString(key);
    }
}
