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
package com.cgi.pacoshop.fulfilmentprocess.populator.impl;

import com.cgi.pacoshop.fulfilmentprocess.exceptions.SAPException;
import com.cgi.pacoshop.fulfilmentprocess.model.SAPSDOrderEntryEntity;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntryEntityPopulator;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the populator for the SAP create single order population.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPSDOrderEntryEntityPopulatorImpl implements SAPSDOrderEntryEntityPopulator
{

	/*
	  * (non-Javadoc)
	  *
	  * @see
	  * com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntryEntityPopulator#populate(de.hybris.platform.core.
	  * model.order.AbstractOrderEntryModel)
	  */
	@Override
	public SAPSDOrderEntryEntity populate(final AbstractOrderEntryModel entry) throws SAPException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	  * (non-Javadoc)
	  *
	  * @see
	  * com.cgi.pacoshop.fulfilmentprocess.populator.SAPSDOrderEntryEntityPopulator#populateAll(de.hybris.platform.core
	  * .model.order.AbstractOrderModel)
	  */
	@Override
	public List<SAPSDOrderEntryEntity> populateAll(final AbstractOrderModel order) throws SAPException
	{
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
