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
package com.cgi.pacoshop.storefront.controllers.pages;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import com.cgi.pacoshop.storefront.breadcrumb.impl.ContentPageBreadcrumbBuilder;
import com.cgi.pacoshop.storefront.constants.WebConstants;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Simple CMS Content Page controller. Used only to preview CMS Pages. The DefaultPageController is used to serve
 * generic content pages.
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/preview-content")
public class PreviewContentPageController extends AbstractPageController
{
	@Resource
	private List<CheckoutStep> steps;

	@Resource(name = "contentPageBreadcrumbBuilder")
	private ContentPageBreadcrumbBuilder contentPageBreadcrumbBuilder;

	@RequestMapping(method = RequestMethod.GET, params =
			  {"uid"})
	public String get(@RequestParam(value = "uid") final String cmsPageUid, final Model model) throws CMSItemNotFoundException
	{
		final AbstractPageModel pageForRequest = getCmsPageService().getPageForId(cmsPageUid);
		storeCmsPageInModel(model, getCmsPageService().getPageForId(cmsPageUid));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(cmsPageUid));
		FormDTO formDTO = null;
		for (CheckoutStep checkoutStep : steps)
		{
			if (cmsPageUid.equals(checkoutStep.getCMSPageLabel()))
			{
				formDTO = checkoutStep.getForm();
			}
		}
		model.addAttribute(WebConstants.FORM_ATTRIBUTE, formDTO);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
								 contentPageBreadcrumbBuilder.getBreadcrumbs((ContentPageModel) pageForRequest));
		return getViewForPage(pageForRequest);
	}
}
