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

import com.cgi.pacoshop.core.model.SSORegisterFormComponentModel;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import com.cgi.pacoshop.storefront.util.ReturnUrlUtil;
import de.hybris.platform.cms2.misc.UrlUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 */
@Controller("SSORegisterFormComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.SSORegisterFormComponent)
public class SSORegisterFormComponentController
		  extends AbstractCMSComponentController<SSORegisterFormComponentModel>
{
	private static final String BASE_URL                  = "baseUrl";
	private static final String WHOLE_URL                 = "wholeUrl";
	private static final String TEXT                      = "text";
	private static final String LABEL                     = "label";
	private static final String ID                        = "id";

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final SSORegisterFormComponentModel component)
	{
		model.addAttribute(BASE_URL, component.getBaseUrl());
		model.addAttribute(TEXT, component.getText());
		model.addAttribute(LABEL, component.getLabel());
		model.addAttribute(ID, component.getId());
		model.addAttribute(WHOLE_URL, component.getBaseUrl() + "?service=" + ReturnUrlUtil.getReturnUrl(request));
	}
}
