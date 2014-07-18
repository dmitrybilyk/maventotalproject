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
package com.cgi.pacoshop.storefront.controllers.cms;

import de.hybris.platform.acceleratorcms.model.components.PurchasedProductReferencesComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import com.cgi.pacoshop.facades.suggestion.SimpleSuggestionFacade;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for CMS PurchasedProductReferencesComponent
 */
@Controller("PurchasedProductReferencesComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.PurchasedProductReferencesComponent)
public class PurchasedProductReferenceComponentController extends
		AbstractCMSComponentController<PurchasedProductReferencesComponentModel>
{
	@Resource(name = "simpleSuggestionFacade")
	private SimpleSuggestionFacade simpleSuggestionFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final PurchasedProductReferencesComponentModel component)
	{
		final List<ProductData> products = simpleSuggestionFacade
				.getReferencesForPurchasedInCategory(component.getCategory().getCode(), component.getProductReferenceTypes(),
						component.isFilterPurchased(), component.getMaximumNumberProducts());

		model.addAttribute("title", component.getTitle());
		model.addAttribute("productReferences", products);
	}

	@Override
	protected String getView(final PurchasedProductReferencesComponentModel component)
	{
		return ControllerConstants.Views.Cms.ComponentPrefix
				+ StringUtils.lowerCase(PurchasedProductReferencesComponentModel._TYPECODE);
	}
}
