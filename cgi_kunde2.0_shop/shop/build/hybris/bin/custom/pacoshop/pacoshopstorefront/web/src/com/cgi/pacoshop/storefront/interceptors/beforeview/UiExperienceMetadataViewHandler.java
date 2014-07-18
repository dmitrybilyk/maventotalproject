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
package com.cgi.pacoshop.storefront.interceptors.beforeview;

import de.hybris.platform.acceleratorservices.enums.UiExperienceLevel;
import de.hybris.platform.acceleratorservices.storefront.data.MetaElementData;
import de.hybris.platform.acceleratorservices.uiexperience.UiExperienceService;
import com.cgi.pacoshop.storefront.interceptors.BeforeViewHandler;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


/**
 * Adds meta tags to help guide the device for the current UI Experience.
 *
 * @module storefront
 * @version 1.0v Jan 01, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Jan Grathwohl <jan.grathwohl@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class UiExperienceMetadataViewHandler implements BeforeViewHandler
{
	@Resource(name = "uiExperienceService")
	private UiExperienceService uiExperienceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cgi.pacoshop.storefront.interceptors.BeforeViewHandler#beforeView(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
			  throws Exception
	{

		if (modelAndView != null && modelAndView.getModel().containsKey("metatags"))
		{

			final List<MetaElementData> metaelements = ((List<MetaElementData>) modelAndView.getModel().get("metatags"));
			final UiExperienceLevel currentUiExperienceLevel = uiExperienceService.getUiExperienceLevel();
			if (UiExperienceLevel.DESKTOP.equals(currentUiExperienceLevel))
			{

				// Provide some hints to mobile browser even though this is not the mobile site -->
				metaelements.add(createMetaElement("HandheldFriendly", "True"));
				metaelements.add(createMetaElement("MobileOptimized", "970"));
				metaelements.add(createMetaElement("viewport", "width=device-width, initial-scale=1"));
			}
			else if (UiExperienceLevel.MOBILE.equals(currentUiExperienceLevel))
			{
				// Provide some hints to mobile browser even though this is not the mobile site -->
				metaelements.add(createMetaElement("HandheldFriendly", "True"));
				metaelements.add(createMetaElement("MobileOptimized", "320"));
				metaelements
						  .add(createMetaElement("viewport",
														 "width=device-width, target-densitydpi=160, initial-scale=1.0, maximum-scale=1.0, "
																	+ "minimum-scale=1.0, user-scalable=no"));
				metaelements.add(createMetaElement("format-detection", "telephone=no"));
			}
		}

	}

	/**
	 *
	 * @param name String
	 * @param content String
	 * @return meta element
	 */
	protected MetaElementData createMetaElement(final String name, final String content)
	{
		final MetaElementData element = new MetaElementData();
		element.setName(name);
		element.setContent(content);
		return element;
	}
}
