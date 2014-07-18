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
package com.cgi.pacoshop.storefront.renderer;

import de.hybris.platform.acceleratorcms.component.renderer.CMSComponentRenderer;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.enums.LinkTargets;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import com.cgi.pacoshop.storefront.tags.Functions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.springframework.beans.factory.annotation.Required;

import static com.cgi.pacoshop.core.util.LogHelper.debug;

/**
 * Renderer for cms link component.
 *
 * @module core
 * @version 1.0v May 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CMSLinkComponentRenderer implements CMSComponentRenderer<CMSLinkComponentModel>
{
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CMSLinkComponentRenderer.class);
	private Converter<ProductModel, ProductData> productUrlConverter;
	private Converter<CategoryModel, CategoryData> categoryUrlConverter;

	/**
	 *
	 * @return productUrlConverter
	 *
	 */
	protected Converter<ProductModel, ProductData> getProductUrlConverter()
	{
		return productUrlConverter;
	}

	/**
	 *
	 * @param productUrlConverter - setter for productUrlConverter
	 */
	@Required
	public void setProductUrlConverter(final Converter<ProductModel, ProductData> productUrlConverter)
	{
		this.productUrlConverter = productUrlConverter;
	}

	/**
	 *
	 * @return categoryUrlConverter
	 */
	protected Converter<CategoryModel, CategoryData> getCategoryUrlConverter()
	{
		return categoryUrlConverter;
	}

	/**
	 *
	 * @param categoryUrlConverter - setter for categoryUrlConverter
	 */
	@Required
	public void setCategoryUrlConverter(final Converter<CategoryModel, CategoryData> categoryUrlConverter)
	{
		this.categoryUrlConverter = categoryUrlConverter;
	}

	/**
	 *
	 * @param component - cmslinkcomponent
	 * @return url
	 */
	protected String getUrl(final CMSLinkComponentModel component)
	{
		// Call the function getUrlForCMSLinkComponent so that this code is only in one place
		return Functions.getUrlForCMSLinkComponent(component, getProductUrlConverter(), getCategoryUrlConverter());
	}

	/**
	 *
	 * @param pageContext The page context to render into
	 * @param component The component to render
	 * @throws ServletException - servlet exception
	 * @throws IOException - io exception
	 */
	@Override
	public void renderComponent(final PageContext pageContext, final CMSLinkComponentModel component) throws ServletException,
																																				IOException
	{
		try
		{
			final String url = getUrl(component);
			final String encodedUrl = UrlSupport.resolveUrl(url, null, pageContext);

			final JspWriter out = pageContext.getOut();

			if (encodedUrl == null || encodedUrl.isEmpty())
			{
				// <span class="empty-nav-item">${component.linkName}</span>
				out.write("<span class=\"empty-nav-item\">");
				out.write(component.getLinkName());
				out.write("</span>");
			}
			else
			{
				final Boolean isLink = component.getLink();

				if (isLink)
				{
					// <link href="${encodedUrl}" rel="${rel}" charset="${charset}" type="${mimeType}"
					// ${component.styleAttributes} title="${component.linkName}">
					out.write("<link href=\"");
					out.write(encodedUrl);
					out.write("\" ");

					out.write(" rel=\"");
					out.write(component.getRel());
					out.write("\" ");

					if (StringUtils.isNotEmpty(component.getCharset()))
					{
						out.write(" charset=\"");
						out.write(component.getCharset());
						out.write("\" ");
					}

					if (StringUtils.isNotEmpty(component.getMimeType()))
					{
						out.write(" type=\"");
						out.write(component.getMimeType());
						out.write("\" ");
					}
				}
				else
				{
					// <a href="${encodedUrl}" ${component.styleAttributes} title="${component.linkName}" ${component.target == null
					// || component.target == 'SAMEWINDOW' ? '' : 'target="_blank"'}>${component.linkName}</a>
					out.write("<a href=\"");
					out.write(encodedUrl);
					out.write("\" ");

					if (component.getTarget() != null && !LinkTargets.SAMEWINDOW.equals(component.getTarget()))
					{
						out.write(" target=\"_blank\"");
					}
					out.write(">");
					out.write(component.getLinkName());
					out.write("</a");
				}

				out.write(" title=\"");
				out.write(component.getLinkName());
				out.write("\" ");

				// Write additional attributes onto the link
				if (component.getStyleAttributes() != null)
				{
					out.write(component.getStyleAttributes());
				}

				out.write(">");
			}
		}
		catch (final JspException ignore)
		{
			debug(LOG, "%s", ignore);
		}
	}
}
