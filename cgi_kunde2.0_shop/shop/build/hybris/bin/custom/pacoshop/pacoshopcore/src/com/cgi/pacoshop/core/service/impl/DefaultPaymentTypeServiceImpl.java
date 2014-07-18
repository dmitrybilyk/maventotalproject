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


import com.cgi.pacoshop.core.daos.PaymentTypeDAO;
import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.core.service.PaymentTypeService;
import com.cgi.pacoshop.fulfilmentprocess.exceptions.PaymentMethodException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * Implementation of PaymentTypeService interface
 *
 * @module hybris
 * @version 1.0v Jan 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultPaymentTypeServiceImpl implements PaymentTypeService
{
	@Resource
	private PaymentTypeDAO paymentTypeDAO;

	@Override
	public Map<String, PaymentTypeModel> findAndMapAllPaymentTypes()
	{
		final List<PaymentTypeModel> allPaymentTypes = paymentTypeDAO.findAllPaymentTypes();
		Map<String, PaymentTypeModel> result = new HashMap<String, PaymentTypeModel>();

		for (PaymentTypeModel paymentTypeModel : allPaymentTypes)
		{
			result.put(paymentTypeModel.getExternalId(), paymentTypeModel);
		}

		return result;
	}

	@Override
	public List<PaymentTypeModel> getAllowedPaymentType(final ArrayList<String> paymentmethod) throws PaymentMethodException
	{
		Map<String, PaymentTypeModel> map = findAndMapAllPaymentTypes();
		List<PaymentTypeModel> list = new ArrayList<>();
		for (String payment : paymentmethod)
		{
			if (map.containsKey(payment))
			{
				list.add(map.get(payment));
			}
			else
			{
				throw new PaymentMethodException("Payment method doesn't exist in Hybris");
			}
		}
		return list;
	}
}

