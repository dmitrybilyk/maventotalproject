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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.daos.Title2Dao;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.Title2Service;
import de.hybris.platform.servicelayer.model.ModelService;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;

/**
 * The implementation of {@link com.cgi.pacoshop.core.service.Title2Service}
 *
 * @module shop
 * @version 1.0v Jul 10, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Vekshin <alexey.vekshin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class Title2ServiceImpl implements Title2Service
{
	@Resource
	private ModelService modelService;

	@Resource
	private Title2Dao    title2Dao;

	@Override
	public Collection<Title2Model> getAllTitles2()
	{
		Collection<Title2Model> titles2 = title2Dao.findAll();
		return titles2;
	}

	@Override
	public Title2Model getTitle2ForCode(final String code)
	{
		validateParameterNotNull(code, "Parameter code must not be null");

		Title2Model title2 = title2Dao.findTitle2(code);
		return title2;
	}

	@Override
	public Integer getNewTitle2Index()
	{
		List<Title2Model> titles2 = this.title2Dao.findAll();
		// find last value for index property and increment it
		Integer lastIndex = 0;
		if (!titles2.isEmpty())
		{
			lastIndex = titles2.get(titles2.size() - 1).getIndex() + 1;
		}
		return lastIndex;
	}
}
