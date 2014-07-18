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
package com.cgi.pacoshop.facades.user.impl;


import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.Title2Service;
import com.cgi.pacoshop.core.service.user.PacoUserService;
import com.cgi.pacoshop.facades.user.PacoUserFacade;
import com.cgi.pacoshop.facades.user.data.Title2Data;
import de.hybris.platform.commercefacades.user.impl.DefaultUserFacade;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implementation of {@link com.cgi.pacoshop.facades.user.PacoUserFacade} for working with user.
 *
 * @module shop
 * @version 1.0v Jul 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Vekshin <alexey.vekshin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultPacoUserFacade extends DefaultUserFacade implements PacoUserFacade
{
	@Resource
	private Title2Service title2Service;

	@Resource
	private PacoUserService pacoUserService;

	private Converter<Title2Model, Title2Data> title2Converter;

	@Override
	public List<Title2Data> getTitles2()
	{
		List<Title2Data> title2List = new ArrayList<>();
		title2List.add(new Title2Data());
		title2List.addAll(Converters.convertAll(getTitle2Service().getAllTitles2(), getTitle2Converter()));
		return title2List;
	}

	@Override
	public void removeAnonymousSubmittedAttribute()
	{
		getPacoUserService().removeAnonymousSubmittedAttribute();
	}

	/**
	 * Gets Title2Service.
	 *
	 * @return the title2Service
	 */
	public Title2Service getTitle2Service()
	{
		return title2Service;
	}

	/**
	 * Gets title2Converter.
	 *
	 * @return the title2Converter
	 */
	public Converter<Title2Model, Title2Data> getTitle2Converter()
	{
		return title2Converter;
	}

	/**
	 * Sets title2Converter.
	 *
	 * @param title2Converter
	 *            the Title2 Converter
	 */
	@Required
	public void setTitle2Converter(final Converter<Title2Model, Title2Data> title2Converter)
	{
		this.title2Converter = title2Converter;
	}

	/**
	 * Gets PacoUserService.
	 *
	 * @return the pacoUserService
	 */
	public PacoUserService getPacoUserService()
	{
		return pacoUserService;
	}
}
