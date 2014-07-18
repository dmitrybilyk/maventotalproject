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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.pacoshop.core.daos.VATGroupDao;
import com.cgi.pacoshop.core.model.VATGroupModel;
import com.cgi.pacoshop.core.service.VATGroupService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * Implementation of VATGroupService
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 24, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class VATGroupServiceImpl implements VATGroupService
{
	@Resource
	private VATGroupDao vatGroupDao;

	@Override
	public VATGroupModel findVatGroup(final int vatRate)
	{
		return vatGroupDao.findVATGroup(vatRate);
	}

	@Override
	public Map<Double, VATGroupModel> findAndMapAllVATGroups()
	{
		Map<Double, VATGroupModel> result = new HashMap<Double, VATGroupModel>();
		final List<VATGroupModel> vatGroupModelList = vatGroupDao.getAllVATGroups();
		for (VATGroupModel model : vatGroupModelList)
		{
			result.put(model.getVatRate(), model);
		}

		return result;
	}
}
