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

import com.cgi.pacoshop.core.model.SSOLoginIFrameComponentModel;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 
 */
@Controller("SSOLoginIFrameComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.SSOLoginIFrameComponent)
public class SSOLoginIFrameComponentController extends AbstractCMSComponentController<SSOLoginIFrameComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final SSOLoginIFrameComponentModel component)
	{
		model.addAttribute("height",component.getHeight());
		model.addAttribute("width",component.getWidth());
		model.addAttribute("frameborder",component.getFrameborder());
		model.addAttribute("src",component.getSrc());
		model.addAttribute("id",component.getId());
		model.addAttribute("title",component.getTitle());
		model.addAttribute("description",component.getDescription());
		model.addAttribute("baseUrl",component.getBaseUrl());
		model.addAttribute("wholeUrl",component.getBaseUrl() + "?service=" + "successpageurl");//???
	}
}
