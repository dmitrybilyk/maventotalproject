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

import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorcms.enums.NavigationBarMenuLayout;
import de.hybris.platform.acceleratorcms.model.components.FooterComponentModel;
import de.hybris.platform.acceleratorcms.model.components.NavigationBarComponentModel;
import de.hybris.platform.cms2.model.navigation.CMSNavigationNodeModel;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for Footer component.
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
@Controller("FooterComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.FooterComponent)
public class FooterComponentController extends AbstractCMSComponentController<FooterComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final FooterComponentModel component)
	{
		final boolean hasNavNodeWithChildren = component.getNavigationNodes() != null
				  && component.getNavigationNodes() != null
				  && !component.getNavigationNodes().isEmpty();

		if (hasNavNodeWithChildren)
		{
			model.addAttribute(CMSNavigationNodeModel.CHILDREN, component.getNavigationNodes().get(0).getChildren());
		}

		model.addAttribute(FooterComponentModel.VISIBLE, component.getVisible());
	}
}
