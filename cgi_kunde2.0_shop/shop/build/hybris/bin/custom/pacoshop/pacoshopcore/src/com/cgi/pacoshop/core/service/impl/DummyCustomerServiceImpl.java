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

import de.hybris.platform.core.model.user.CustomerModel;
import org.apache.log4j.Logger;
import com.cgi.pacoshop.core.daos.CustomerDao;
import com.cgi.pacoshop.core.service.DummyCustomerService;

/**
 * SRepresents a simple service returning the dummy customer defined in the system
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Philippe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DummyCustomerServiceImpl implements DummyCustomerService
{
	private static final Logger LOGGER = Logger.getLogger(DummyCustomerServiceImpl.class);


	private CustomerDao customerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cgi.pacoshop.core.service.DummyCustomerService#retrieveDummyCustomer()
	 */
	@Override
	public CustomerModel retrieveDummyCustomer()
	{

		return customerDao.retrieveDummyCustomer();
	}

	/**
	 * @param customerDao
	 *           the customerDao to set
	 */
	public void setCustomerDao(final CustomerDao customerDao)
	{
		this.customerDao = customerDao;
	}
}
