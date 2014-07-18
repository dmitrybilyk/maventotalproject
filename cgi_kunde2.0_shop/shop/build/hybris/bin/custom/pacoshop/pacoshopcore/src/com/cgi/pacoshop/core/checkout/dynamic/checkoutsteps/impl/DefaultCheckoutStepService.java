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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.FormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.session.SessionCache;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.util.LogHelper;
import static com.cgi.pacoshop.core.util.LogHelper.*;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.springframework.stereotype.Service;

/**
 * Default implementation of the {@link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService}
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Dec 17, 2013
 * @module hybris - pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@Service
public class DefaultCheckoutStepService implements CheckoutStepService
{
	private static final Logger LOG = Logger.getLogger(DefaultCheckoutStepService.class);

	private List<CheckoutStep> checkoutSteps;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private CartService          cartService;

	@Resource
	private HashMap<String, String> formsAndStepAssociations;

	@Resource
	private SessionCache sessionCache;


	/**
	 * Checkout Steps setter.
	 *
	 * @param checkoutStepsParam - new Checkout steps list.
	 */
	public void setCheckoutSteps(final List<CheckoutStep> checkoutStepsParam)
	{
		this.checkoutSteps = checkoutStepsParam;
	}

	/**
	 * Get checkout steps list.
	 *
	 * @return List of checkout steps.
	 */
	public List<CheckoutStep> getCheckoutSteps()
	{
		return this.checkoutSteps;
	}

	/**
	 * Determine checkout steps needed in the current checkout flow. The list of steps should be injected in
	 * implementations via Spring CDI. Then it is a matter of iterating formelementgroups in each step and including the
	 * step in the results in case at least one element is required. if all formelements have been already prefilled
	 * from the cart - then the checkout step should not be in the list.
	 *
	 * @param sessionCart - cart object linked to a current session
	 * @return the list of checkout steps needed in the current checkout flow
	 */
	@SuppressFBWarnings ({"VA_FORMAT_STRING_BAD_CONVERSION" })
	@Override
	public List<CheckoutStep> getNeededSteps(final CartModel sessionCart)
	{
		assertNotNull(sessionCart);
		List<CheckoutStep> neededSteps = new ArrayList<>();
		for (CheckoutStep checkoutStep : getCheckoutSteps())
		{
			boolean allFormElementsPrefilled = true;
			boolean atLeastOneFormElementIsDisplayed = false;
			for (FormElementGroup formElementGroup : checkoutStep.getElements())
			{
				boolean formElementGroupDisplayed = formElementGroup.isDisplayed(sessionCart);
				if (formElementGroupDisplayed)
				{
					atLeastOneFormElementIsDisplayed = true;
					if (!formElementGroup.isPrefilled(sessionCart))
					{
						if (sessionCart.getUser() instanceof CustomerModel && isUserNotVisitor(sessionCart.getUser()))
						{
							formElementGroup.prefillCartFromCustomerProfile(sessionCart, (CustomerModel) sessionCart.getUser());
						}
					}
					boolean formElementGroupJustPrefilledFromCart = formElementGroup.isPrefilled(sessionCart);
					allFormElementsPrefilled = allFormElementsPrefilled && formElementGroupJustPrefilledFromCart;
				}
			}
			if (atLeastOneFormElementIsDisplayed && !allFormElementsPrefilled)
			{
				checkoutStep.setCompleted(false);
			}
			else
			{
				checkoutStep.setCompleted(true);

			}
			neededSteps.add(checkoutStep);
		}
		LOG.debug(String.format("needed steps count: %d", neededSteps.size()));
		return neededSteps;
	}

	private boolean isUserNotVisitor(final UserModel userModel)
	{
		String visitorGroup = shopConfigurationService.getUserGroupUIDForVisitor();
		for (PrincipalGroupModel group : userModel.getGroups())
		{
			if (group.getUid().equals(visitorGroup))
			{
				return false;
			}
		}
		return true;
	}


	/**
	 * Determine the next checkout step to be rendered.
	 *
	 * @param neededSteps - previously determined checkout steps needed in the current checkout flow
	 * @return the first not completed step. If all are completed - return the last one.
	 */
	@Override
	public CheckoutStep getNextDisplayedStep(
			  final List<? extends CheckoutStep> neededSteps)
	{
		assertNotNull(neededSteps);
		assertTrue(neededSteps.size() > 0);
		for (CheckoutStep step : neededSteps)
		{
			if (!step.isCompleted())
			{
				debug(LOG, "getNextDisplayedStep() returning step %s", step.getStepName());
				debug(LOG, "*************FORMELEMENTGROUPS START*******************************************");
				for (FormElementGroup formElementGroup : step.getElements())
				{
					logFormElementGroup(formElementGroup);
				}
				debug(LOG, "*************FORMELEMENTGROUPS END*******************************************");
				return step;
			}
			else
			{
				debug(LOG, "Skipping step %s", step.getStepName());
			}
		}
		debug(LOG, "getNextDisplayedStep() returning last step");
		return neededSteps.get(neededSteps.size() - 1);
	}

	private void logFormElementGroup(final FormElementGroup formElementGroup)
	{
		boolean displayed = formElementGroup.isDisplayed(cartService.getSessionCart());
		boolean prefilled = formElementGroup.isPrefilled(cartService.getSessionCart());
		if (displayed && !prefilled)
		{
			debug(LOG, "%s: displayed: %b prefilled: %b", formElementGroup.getKey(), displayed, prefilled);
		}
	}

	@Override
	public CheckoutStep getStep(final String stepName)
	{
		for (CheckoutStep step : getCheckoutSteps())
		{
			if (step.getStepName().equals(stepName))
			{
				return step;
			}
		}

		return null;
	}

	/**
	 * Gets mandatory field names.
	 *
	 * @param checkoutStep the step.
	 * @return the mandatory field names.
	 */
	@Override
	public Map<String, Boolean> getStepMandatoryFieldNames(final CheckoutStep checkoutStep)
	{
		final Map<String, Boolean> mandatoryFields = new HashMap<>();
		for (final FormElementGroup formElementGroup : checkoutStep.getElements())
		{
			if (formElementGroup.isDisplayed(cartService.getSessionCart()))
			{
				for (final String currentField : formElementGroup.getMandatoryFieldNames(cartService.getSessionCart()))
				{
					mandatoryFields.put(currentField, true);
				}
			}
		}
		return mandatoryFields;
	}

	/**
	 * Gets checkout step by form dto.
	 *
	 * @param formDTO the dto.
	 * @return the checkout step.
	 */
	@Override
	public CheckoutStep getCheckoutStep(final FormDTO formDTO)
	{
		return getStep(formsAndStepAssociations.get(formDTO.getClass().getName()));
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
	 * Sets shop configuration service.
	 * @param shopConfigurationService like shopConfigurationService.
	 */
	public void setShopConfigurationService(final ShopConfigurationService shopConfigurationService)
	{
		this.shopConfigurationService = shopConfigurationService;
	}
}
