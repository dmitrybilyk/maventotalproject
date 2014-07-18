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


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.FormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * Default implementation of the {@link com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade}
 *
 *
 * @module hybris - pacoshopfacades
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http ://www.symmetrics.de/
 * @see  "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public class DefaultDynamicCheckoutFacade implements DynamicCheckoutFacade
{
    private static final Logger LOG = Logger.getLogger(DefaultDynamicCheckoutFacade.class);

    private static final String MANDATORY_FIELDS   = "mandatoryFields";
    private static final String STEP_NAME          = "stepName";
    private static final String FORM               = "form";
    private static final String DISPLAY_MAPPINGS   = "displaymappings";
    private static final String DISPLAY_BREADCRUMB = "displaybreadcrumb";
    private static final String MODEL_REDIRECT_URL = "redirecturl";
    private static final String MODEL_REDIRECT_URL_DESCRIPTION = "redirecturldesc";

    private static final String STEPS       = "steps";
    private static final String CURRENTSTEP = "currentstep";

    /* Names in modal for urls to specific checkout steps */
    private static final String PATH_TO_CUSTUMER_PAGE = "pathToCustomerPage";
    private static final String PATH_TO_SHIPMENT_PAGE = "pathToShipmentPage";
    private static final String PATH_TO_PAYMENT_PAGE  = "pathToPaymentPage";

    private static final String PATH_TO_DYNAMICCHECKOUT = "/dynamiccheckout/";

    @Resource
    private CartService          cartService;
    @Resource
    private CheckoutStepService  checkoutStepService;
    @Resource
    private ConfigurationService configurationService;
    @Resource
    private ModelService         modelService;

    @Resource
    private CommerceCartService commerceCartService;

    /**
     * Sets cart service.
     *
     * @param cartService cartService
     */
    public void setCartService(final CartService cartService)
    {
        this.cartService = cartService;
    }

    /**
     * Sets checkout step service.
     *
     * @param checkoutStepService - checkoutStepService.
     */
    public void setCheckoutStepService(final CheckoutStepService checkoutStepService)
    {
        this.checkoutStepService = checkoutStepService;
    }

    /**
     * @param formDTO FormDTO
     * @return checkoutStep
     */
    public CheckoutStep getCheckoutStep(final FormDTO formDTO)
    {
        return checkoutStepService.getCheckoutStep(formDTO);
    }

    /**
     *
     * @param stepName
     *           stepName
     * @return checkoutStep
     */
    @Override
    public CheckoutStep getStep(final String stepName)
    {
        return checkoutStepService.getStep(stepName);
    }

    /**
     * Delegate to a corresponding method of a {@link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService}.
     *
     * @return the list of checkout steps needed in the current checkout flow.
     */
    @Override
    public List<CheckoutStep> getNeededSteps()
    {
        return checkoutStepService.getNeededSteps(cartService.getSessionCart());
    }

    /**
     * Delegate to a corresponding method of a {@link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService}.
     *
     * @param neededSteps
     *           - previously determined checkout steps needed in the current checkout flow.
     * @return the first not completed step.
     */
    @Override
    public CheckoutStep getNextDisplayedStep(final List<? extends CheckoutStep> neededSteps)
    {
        return checkoutStepService.getNextDisplayedStep(neededSteps);
    }

    /**
     * TBD later.
     *
     * @param dto
     *           -quality gate.
     * @param checkoutStep
     *           quality gate.
     * @param bindingResult
     *           - quality gate.
     * @return quality gate.
     */
    @Override
    public boolean validateForm(final FormDTO dto, final CheckoutStep checkoutStep, final BindingResult bindingResult)
    {
        boolean validationPassed = true;
        for (final FormElementGroup formElementGroup : checkoutStep.getElements())
        {
            if (formElementGroup.isDisplayed(cartService.getSessionCart()))
            {
                validationPassed = formElementGroup.validate(dto, bindingResult) && validationPassed;
            }
        }

        return validationPassed;
    }

    @Override
    public void saveFormToCart(final FormDTO dto) throws CalculationException
    {
        final CheckoutStep appropriateStep = checkoutStepService.getCheckoutStep(dto);
        final CartModel cartModel = cartService.getSessionCart();
        for (final FormElementGroup formElementGroup : appropriateStep.getElements())
        {
            if (formElementGroup.isDisplayed(cartModel))
            {
                formElementGroup.saveFormToCart(dto, cartModel);
            }
        }

        commerceCartService.recalculateCart(cartService.getSessionCart());
    }

    @Override
    public Map<String, Boolean> getDisplayMappings(final CheckoutStep step)
    {
        final Map<String, Boolean> displayMappings = new HashMap<>();
        for (final FormElementGroup formElementGroup : step.getElements())
        {
            if (formElementGroup.isDisplayed(cartService.getSessionCart()))
            {
                displayMappings.put(formElementGroup.getKey(), true);
            }
            else
            {
                displayMappings.put(formElementGroup.getKey(), false);
            }
        }
        return displayMappings;
    }

    /**
     *
     * @param checkoutStep
     *           - checkout step
     * @return - populated FormDTO instance
     */
    public FormDTO populateFormFromCart(final CheckoutStep checkoutStep)
    {

        final FormDTO formDTO = checkoutStep.getForm();
        for (final FormElementGroup formElementGroup : checkoutStep.getElements())
        {
            if (formElementGroup.isDisplayed(cartService.getSessionCart()))
            {
                formElementGroup.populateFormFromCart(formDTO, cartService.getSessionCart());
            }
        }
        return formDTO;
    }

    /**
     *
     * @return url to be redirected to after purchase
     */
    private String getProductRedirectUrl()
    {
        return cartService.getSessionCart().getRedirectUrl();
    }

	/**
     *
     * @return description for URL to be redirected to after purchase
     */
    private String getProductRedirectUrlDesc()
    {
        return cartService.getSessionCart().getRedirectUrlDescription();
    }

    private String getConfigurationProperty(final String key)
    {
        return configurationService.getConfiguration().getString(key);
    }

    // FIXME: no elegant way to move this from DynamicCheckoutFacade.
    private Map<String, String> getStepsPath()
    {
        Map<String, String> pathMap = new HashMap<>();
        pathMap.put(PATH_TO_CUSTUMER_PAGE, PATH_TO_DYNAMICCHECKOUT
                + getConfigurationProperty(FormElementGroupConstants.CUSTOMER_STEP_NAME));
        pathMap.put(PATH_TO_PAYMENT_PAGE, PATH_TO_DYNAMICCHECKOUT
                + getConfigurationProperty(FormElementGroupConstants.PAYMENT_STEP_NAME));
		pathMap.put(PATH_TO_SHIPMENT_PAGE, PATH_TO_DYNAMICCHECKOUT
				  + getConfigurationProperty(FormElementGroupConstants.SHIPMENT_STEP_NAME));
		return pathMap;
	}

	/**
	 * Gets model service.
	 *
	 * @return  - model service
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	@Override
	public Map<String, Object> populatePageModelData(final List<CheckoutStep> neededSteps, final CheckoutStep step)
	{
        // Check input parameters
        Assert.notNull(neededSteps, "neededSteps must be provided");
        Assert.notEmpty(neededSteps, "neededSteps must not be empty");
        Assert.notNull(step, "step must be provided");


        final Map<String, Object> pageModelValues = new HashMap<>();
        CartModel sessionCart = cartService.getSessionCart();

        // Iterate over the form element groups to populate their specific model data
        for (final FormElementGroup formElementGroup : step.getElements())
		{
			if (formElementGroup.isDisplayed(sessionCart))
			{
				pageModelValues.putAll(formElementGroup.populatePageModelData(sessionCart));
			}
		}

        // List of mandatory fields
        final Map<String, Boolean> mandatoryFields = checkoutStepService.getStepMandatoryFieldNames(step);
        pageModelValues.put(MANDATORY_FIELDS, mandatoryFields);

        // Map of displayed form element groups
        final Map<String, Boolean> displayMappings = getDisplayMappings(step);
        pageModelValues.put(DISPLAY_MAPPINGS, displayMappings);

		// form will be loaded if population from cart is enabled
		// this check implemented to prevent reload data from cart in case of failed validation
			if (step.isPopulateFromCart())
			{
				// Form
				final FormDTO form = populateFormFromCart(step);
				pageModelValues.put(FORM, form);
			}

        // Step data
        // FIXME: I think we should not pass steps instances but we can pass some boolean array instead
        pageModelValues.put(STEPS, neededSteps);
        pageModelValues.put(STEP_NAME, step.getStepName());
        pageModelValues.put(CURRENTSTEP, step);
        pageModelValues.putAll(getStepsPath());

        // This is far from beautiful, but at least does not depend on any particular
        // product type in DynamicCheckoutFacade
        pageModelValues.put(DISPLAY_BREADCRUMB,
                            AbstractFormElementGroup.isBreadcrumbDisplayed(sessionCart, configurationService));

        // Product redirect URL
        final String productRedirectUrl = getProductRedirectUrl();
        final String productRedirectUrlDesc = getProductRedirectUrlDesc();
        if (!isEmpty(productRedirectUrl))
        {
            pageModelValues.put(MODEL_REDIRECT_URL, productRedirectUrl);
            pageModelValues.put(MODEL_REDIRECT_URL_DESCRIPTION, productRedirectUrlDesc);
        }

        // This map will be added to a Spring MVC model create in controller
        return pageModelValues;
	}

    /**
     * Injects commerceCartService. Used by unit tests.
     *
     * @param commerceCartService commerceCartService object
     */
    public void setCommerceCartService(final CommerceCartService commerceCartService)
    {
        this.commerceCartService = commerceCartService;
    }

    /**
     * Injects configurationService. Used by unit tests.
     *
     * @param configurationService configuration service object
     */
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }
}
