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


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.exceptions.PLZValidationException;
import com.cgi.pacoshop.core.service.PLZValidationService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Implementation of PLZValidationService. This class validate zip code depends on country.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @version 1.0v dec 30, 2013
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class PLZValidationServiceImpl implements PLZValidationService
{
	//
	//	private static final String ISO_CODE_GERMANY = "DE";
	//	private static final String PATERN  = "[0-9]{5}";
	//
	//	@Override
	//	public boolean validate(final CheckoutFormDTO dto)
	//	{
	//
	//
	//		if (dto.getCountry().equals(ISO_CODE_GERMANY))
	//		{
	//			String zipCode = dto.getZip().trim();
	//			Pattern pattern = Pattern.compile(PATERN);
	//			Matcher matcher = pattern.matcher(zipCode);
	//
	//
	//			return matcher.matches();
	//		}
	//		return true;
	//	}
    public static final String CONFIG_KEY = "plz.validation.rule.";
	@Resource
	private ConfigurationService configurationService;

    /**
     *
     * @param configurationService the configurationService
     */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}


	@Override
	public boolean validate(final CheckoutFormDTO dto)
	{

		if (dto.getCountry() != null)
		{
			return validateZipCode(dto.getZip().trim().toLowerCase(), dto.getCountry().trim().toLowerCase());

		}

		return true;
	}

	@Override
	public void validate(final String zip, final String countryCode) throws PLZValidationException
	{
		if (!validateZipCode(zip, countryCode))
		{
			throw new PLZValidationException(String.format("Postal code=%s for countryCode=%s is not valid", zip, countryCode));
		}
	}


	@Override
	public boolean validateZipCode(final String zip, final String countryCode)
	{
		if (StringUtils.isEmpty(zip) || StringUtils.isEmpty(countryCode))
		{
			return false;
		}

		final String regex = configurationService.getConfiguration().getString(CONFIG_KEY + countryCode);
		if (regex != null && !regex.isEmpty())
		{
			final Pattern pattern = Pattern.compile(regex);
			final Matcher matcher = pattern.matcher(zip);

			return matcher.matches();
		}

		return true;
	}


}
