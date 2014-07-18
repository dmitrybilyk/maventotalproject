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
package com.cgi.pacoshop.core.populator.impl;

import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.facades.user.data.Title2Data;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import org.springframework.util.Assert;

/**
 * Converter implementation for {@link com.cgi.pacoshop.core.model.Title2Model} as source and {@link com.cgi.pacoshop.facades.user.data.Title2Data} as target type.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Vekshin <alexey.vekshin@symmetrics.de>
 * @version 1.0v July 7, 2014
 * @module shop
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class Title2Populator extends AbstractPopulatingConverter<Title2Model, Title2Data>
{
	@Override
	protected Title2Data createTarget()
	{
		return new Title2Data();
	}

	@Override
	public void populate(final Title2Model source, final Title2Data target)
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");

		target.setCode(source.getCode());
		target.setName(source.getName());

		super.populate(source, target);
	}
}
