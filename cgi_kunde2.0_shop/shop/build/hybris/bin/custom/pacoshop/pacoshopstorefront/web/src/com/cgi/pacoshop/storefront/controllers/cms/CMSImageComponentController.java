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
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.jalo.contents.components.CMSImageComponent;
import de.hybris.platform.cms2.misc.UrlUtils;
import de.hybris.platform.cms2.model.contents.components.CMSImageComponentModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import java.net.URLEncoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller for CMSImage component.
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
@Controller("CMSImageComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.CMSImageComponent)
public class CMSImageComponentController
		  extends AbstractCMSComponentController<CMSImageComponentModel>
{
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model, final CMSImageComponentModel component)
	{
		if (component.getMedia() != null)
		{
			model.addAttribute(MediaModel.URL, component.getMedia().getURL());
			model.addAttribute(MediaModel.URL2, component.getMedia().getURL2());
			model.addAttribute(MediaModel.ALTTEXT, component.getMedia().getAltText());
		}
	}
}
