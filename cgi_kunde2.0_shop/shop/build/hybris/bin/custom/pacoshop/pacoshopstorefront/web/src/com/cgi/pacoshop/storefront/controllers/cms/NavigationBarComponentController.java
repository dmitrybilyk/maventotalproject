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

import de.hybris.platform.acceleratorcms.enums.NavigationBarMenuLayout;
import de.hybris.platform.acceleratorcms.model.components.NavigationBarComponentModel;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;

import de.hybris.platform.cms2.jalo.navigation.CMSNavigationNode;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for Navigation bar component.
 *
 * @module pacoshopcore
 * @version 1.0v Apr 24, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@Controller("NavigationBarComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.NavigationBarComponent)
public class NavigationBarComponentController extends AbstractCMSComponentController<NavigationBarComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final NavigationBarComponentModel component)
	{
		final boolean hasNavNodeWithChildren = component.getNavigationNode() != null
				  && component.getNavigationNode().getChildren() != null
				  && !component.getNavigationNode().getChildren().isEmpty();

		if (component.getDropDownLayout() != null)
		{
			model.addAttribute(NavigationBarComponentModel.DROPDOWNLAYOUT, component.getDropDownLayout().getCode().toLowerCase());
		}
		else if (hasNavNodeWithChildren)
		{
			// Component has children but not drop down layout, default to auto
			model.addAttribute(NavigationBarComponentModel.DROPDOWNLAYOUT, NavigationBarMenuLayout.AUTO.getCode().toLowerCase());
		}

		if (hasNavNodeWithChildren)
		{
			model.addAttribute(CMSNavigationNodeModel.CHILDREN, component.getNavigationNode().getChildren());
		}

		model.addAttribute(NavigationBarComponentModel.STYLECLASS, component.getStyleClass());
		model.addAttribute(NavigationBarComponentModel.LINK, component.getLink());
		model.addAttribute(NavigationBarComponentModel.VISIBLE, component.getVisible());
	}
}
