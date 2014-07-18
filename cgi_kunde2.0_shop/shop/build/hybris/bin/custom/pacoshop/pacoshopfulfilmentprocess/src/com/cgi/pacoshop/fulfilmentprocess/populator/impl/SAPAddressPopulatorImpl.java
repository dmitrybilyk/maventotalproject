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

import static com.cgi.pacoshop.fulfilmentprocess.util.LogHelper.debug;

import com.cgi.pacoshop.fulfilmentprocess.model.SAPAddress;
import com.cgi.pacoshop.fulfilmentprocess.populator.SAPAddressPopulator;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.log4j.Logger;


/**
 * Implementation of the populator for the SAP Address population.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SAPAddressPopulatorImpl implements SAPAddressPopulator
{
	private static final Logger LOGGER = Logger.getLogger(SAPAddressPopulatorImpl.class);
	private static final String SERVICEBUS_DATE_FORMAT = "servicebus.date.format";

	@Resource
	private ConfigurationService configurationService;

	/*
	  * (non-Javadoc)
	  *
	  * @see
	  * com.cgi.pacoshop.fulfilmentprocess.populator.SAPAddressPopulator#populate(de.hybris.platform.core.model.user.
	  * AddressModel)
	  */
	@Override
	public SAPAddress populate(final AddressModel address)
	{
		if (address == null)
		{
			debug(LOGGER, "Populator called with a null address.");
			return null;
		}

		final SAPAddress result = new SAPAddress();
		if (address.getTitle() != null)
		{
			result.setSalutation(address.getTitle().getName());
		}

		if (address.getTitle2() != null)
		{
			result.setTitle(address.getTitle2().getCode());
		}
		result.setFirstname(address.getFirstname());
		result.setLastname(address.getLastname());
		result.setAddressSuffix(address.getLine3());
		result.setStreetNumber(address.getLine2());
		result.setStreet(address.getLine1());
		result.setPostalCode(address.getPostalcode());
		result.setCity(address.getTown());
		result.setFunction(address.getRoleInCompany());
		result.setCompany(address.getCompany());

		//todo: confirm that we use the name and not the ISO code there.
		if (address.getCountry() != null)
		{
			result.setCountry(address.getCountry().getName());
		}
		result.setEmail(address.getEmail());
		result.setBirthday(getStringOfDateInFormat(address.getDateOfBirth()));

		if (address.getHomeNumber() != null)
		{
			result.setPhonePrefixHome(address.getHomeNumber().getPrefix());
			result.setPhoneExtensionHome(address.getHomeNumber().getExtension());
			result.setPhoneNumberHome(address.getHomeNumber().getNumber());
		}

		if (address.getBusinessNumber() != null)
		{
			result.setPhonePrefixBusiness(address.getBusinessNumber().getPrefix());
			result.setPhoneExtensionBusiness(address.getBusinessNumber().getExtension());
			result.setPhoneNumberBusiness(address.getBusinessNumber().getNumber());
		}

		if (address.getMobileNumber() != null)
		{
			result.setMobilePrefix(address.getMobileNumber().getPrefix());
			result.setMobileNumber(address.getMobileNumber().getNumber());
		}

		debug(LOGGER, "The populator returned [%s] for address[%s]", result, address);
		return result;
	}

	private String getStringOfDateInFormat(final Date date)
	{
		String result = "";
		if (date != null)
		{
			final String dateFormat = configurationService.getConfiguration().getString(SERVICEBUS_DATE_FORMAT);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			result = simpleDateFormat.format(date);
		}
		return result;
	}

	/**
	 * @param configurationService
	 *            the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}
}
