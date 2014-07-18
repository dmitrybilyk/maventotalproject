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
package com.cgi.pacoshop.core.strategies.impl;


import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.strategies.CompareAddressesStrategy;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.TitleModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Implementation of strategy for comparing addresses.
 *
 * @module shop
 * @version 1.0v Apr 09, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class DefaultCompareAddressesStrategy implements CompareAddressesStrategy
{
    private static final Logger LOG = Logger.getLogger(DefaultCompareAddressesStrategy.class);


    /**
     * Compare Addresses.
     * @param firstAddress first address.
     * @param secondAddress second address.
     * @return true if addresses are equal.
     */
    @Override
    public boolean compareAddresses(final AddressModel firstAddress,
                                              final AddressModel secondAddress)
    {
        boolean result = (firstAddress != null && secondAddress != null)
                  ? compare(firstAddress, secondAddress) : (firstAddress == null && secondAddress == null);
        debug(LOG, "Comparing addresses. Result: %b ", result);
        return result;
    }

    /**
     * Compare Addresses.
     * @param firstAddress first address.
     * @param secondAddress second address.
     * @return true if addresses are equal.
     */
    private boolean compare(final AddressModel firstAddress, final AddressModel secondAddress)
    {
        debug(LOG, "Compare addresses: first[ %s ] , second[ %s ]", firstAddress, secondAddress);

        return (isTitlesEqual(firstAddress.getTitle(), secondAddress.getTitle(),
                                 PacoshopCoreConstants.CustomerAddressFields.SALUTATION)
                  && isEqual(firstAddress.getFirstname(), secondAddress.getFirstname(),
                                 PacoshopCoreConstants.CustomerAddressFields.FIRST_NAME)
                  && isEqual(firstAddress.getLastname(), secondAddress.getLastname(),
                                 PacoshopCoreConstants.CustomerAddressFields.LAST_NAME)
                  && isEqual(firstAddress.getEmail(), secondAddress.getEmail(),
                                 PacoshopCoreConstants.CustomerAddressFields.EMAIL)
                  && isTitles2Equal(firstAddress.getTitle2(), secondAddress.getTitle2(),
                                 PacoshopCoreConstants.CustomerAddressFields.TITLE)
                  && isEqual(firstAddress.getCompany(), secondAddress.getCompany(),
                                 PacoshopCoreConstants.CustomerAddressFields.COMPANY)
                  && isEqual(firstAddress.getLine1(), secondAddress.getLine1(),
                                 PacoshopCoreConstants.CustomerAddressFields.LINE_1)
                  && isEqual(firstAddress.getLine2(), secondAddress.getLine2(),
                                 PacoshopCoreConstants.CustomerAddressFields.LINE_2)
                  && isEqual(firstAddress.getPostalcode(), secondAddress.getPostalcode(),
                                 PacoshopCoreConstants.CustomerAddressFields.PLZ_ZIP)
                  && isEqual(firstAddress.getTown(), secondAddress.getTown(),
                                 PacoshopCoreConstants.CustomerAddressFields.CITY_ORT)
                  && isCountryEqual(firstAddress.getCountry(), secondAddress.getCountry(),
                                 PacoshopCoreConstants.CustomerAddressFields.COUNTRY)
        ) ? true : false;
    }

    /**
     * Compare countries.
     * @param country1 first country.
     * @param country2 second country.
     * @param fieldName field name for log.
     * @return true if countries are equal.
     */
    private boolean isCountryEqual(final CountryModel country1, final CountryModel country2, final String fieldName)
    {
        boolean result = (country1 != null && country2 != null)
                  ? isEqual(country1.getName(), country2.getName(), fieldName) : (country1 == null && country2 == null);
        debug(LOG, "Comparing field: %s, result: %b ", fieldName, result);
        return result;
    }

    /**
     * Compare titles.
     * @param title1 first title.
     * @param title2 second title.
     * @param fieldName field name for log.
     * @return true if titles are equal.
     */
    private boolean isTitlesEqual(final TitleModel title1, final TitleModel title2, final String fieldName)
    {
        boolean result = (title1 != null && title2 != null)
                  ? isEqual(title1.getName(), title2.getName(), fieldName) : (title1 == null && title2 == null);
        debug(LOG, "Comparing field: %s, result: %b ", fieldName, result);
        return result;
    }

	/**
	 * Compare titles.
	 * @param title1 first title.
	 * @param title2 second title.
	 * @param fieldName field name for log.
	 * @return true if titles are equal.
	 */
	private boolean isTitles2Equal(final Title2Model title1, final Title2Model title2, final String fieldName)
	{
		boolean result = (title1 != null && title2 != null)
				  ? isEqual(title1.getName(), title2.getName(), fieldName) : (title1 == null && title2 == null);
		debug(LOG, "Comparing field: %s, result: %b ", fieldName, result);
		return result;
	}

    /**
     * Compare strings (fields).
     * @param value1 first value.
     * @param value2 second value.
     * @param fieldName field name for log.
     * @return true if field are equal.
     */
    private boolean isEqual(final String value1, final String value2, final String fieldName)
    {
        boolean result = (StringUtils.isNotEmpty(value1)) ? value1.equals(value2) : (StringUtils.isEmpty(value2));
        debug(LOG, "Comparing field: %s, value_1: %s, value_2: %s, result: %b ", fieldName, value1, value2, result);
        return result;
    }

}
