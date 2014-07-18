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
package com.cgi.pacoshop.core.populator.impl;


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.model.DeliveryAddress;
import com.cgi.pacoshop.core.populator.DeliveryAddressPopulator;
import de.hybris.platform.core.model.user.AddressModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;


/**
 * Implementation class of {@link com.cgi.pacoshop.core.populator.DeliveryAddressPopulator}
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see 'https://wiki.hybris.com/'
 */
public class DefaultDeliveryAddressPopulator implements DeliveryAddressPopulator
{
	private static final String DATE_DELIMITER = "-";

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public DeliveryAddress populate(final AddressModel address)
	{
		Assert.notNull(address, "address must not be null");

		DeliveryAddress result = new DeliveryAddress();

		if (address.getTitle() != null)
		{
			result.setSalutation(address.getTitle().getName());
		}

		if (address.getTitle2() != null)
		{
			result.setTitle(address.getTitle2().getName());
		}
		result.setFirstname(address.getFirstname());
		result.setLastname(address.getLastname());
		result.setFunction(address.getRoleInCompany());
		result.setCompany(address.getCompany());
		result.setStreet(address.getLine1());
		result.setStreetNumber(address.getLine2());
		result.setAddressSuffix(address.getLine3());
		result.setPostalCode(address.getPostalcode());
		result.setCity(address.getTown());

		if (address.getCountry() != null)
		{
			result.setCountry(address.getCountry().getIsocode());
		}

		result.setEmail(address.getEmail());

		result.setBusinessPhoneNumber(address.getBusinessNumber());
		result.setHomePhoneNumber(address.getHomeNumber());
		result.setMobilePhoneNumber(address.getMobileNumber());

		if (address.getDateOfBirth() != null)
		{
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			result.setBirthday(simpleDateFormat.format(address.getDateOfBirth()));
		}

		return result;
	}

	@Override
	public DeliveryAddress populate(final CheckoutFormDTO formDTO)
	{
		DeliveryAddress result = new DeliveryAddress();
		result.setSalutation(formDTO.getSalutation());
		result.setTitle(formDTO.getTitle());
		result.setFirstname(formDTO.getFirstName());
		result.setLastname(formDTO.getLastName());
		result.setFunction(formDTO.getPositionCompany());
		result.setCompany(formDTO.getCompany());
		result.setAddressSuffix(formDTO.getAdditionalStreet());
		result.setStreet(formDTO.getStreet());
		result.setStreetNumber(formDTO.getHouseNumber());
		result.setPostalCode(formDTO.getZip());
		result.setCity(formDTO.getCity());
		result.setCountry(formDTO.getCountry());
		result.setEmail(formDTO.getEmail());
		result.setPhonePrefixBusiness(formDTO.getPhonePrefixBusiness());
		result.setPhoneExtensionBusiness(formDTO.getPhoneExtensionBusiness());
		result.setPhoneNumberBusiness(formDTO.getPhoneNumberBusiness());
		result.setPhonePrefixHome(formDTO.getPhonePrefixHome());
		result.setPhoneExtensionHome(formDTO.getPhoneExtensionHome());
		result.setPhoneNumberHome(formDTO.getPhoneNumberHome());
		result.setMobilePrefix(formDTO.getMobilePrefix());
		result.setMobileNumber(formDTO.getMobileNumber());
		if (StringUtils.isNotEmpty(formDTO.getBirthDateYear()))
		{
			result.setBirthday(new StringBuilder().append(formDTO.getBirthDateYear()).append(DATE_DELIMITER)
											.append(formDTO.getBirthDateMonth()).append(DATE_DELIMITER)
											.append(formDTO.getBirthDateDay()).toString());
		}
		return result;
	}

	@Override
	public DeliveryAddress populate(final ShipmentInfoFormDTO formDTO)
	{
		DeliveryAddress result = new DeliveryAddress();
		result.setSalutation(formDTO.getNewShipmentSalutation());
		result.setTitle(formDTO.getNewShipmentTitle());
		result.setFirstname(formDTO.getNewShipmentFirstName());
		result.setLastname(formDTO.getNewShipmentLastName());
		result.setCompany(formDTO.getNewShipmentCompany());
		result.setAddressSuffix(formDTO.getNewShipmentAdditionalStreet());
		result.setStreet(formDTO.getNewShipmentStreet());
		result.setStreetNumber(formDTO.getNewShipmentHouseNumber());
		result.setPhonePrefixBusiness(formDTO.getNewShipmentPhonePrefixBusiness());
		result.setPhoneExtensionBusiness(formDTO.getNewShipmentPhoneExtensionBusiness());
		result.setPhoneNumberBusiness(formDTO.getNewShipmentPhoneNumberBusiness());
		result.setPhonePrefixHome(formDTO.getNewShipmentPhonePrefixHome());
		result.setPhoneExtensionHome(formDTO.getNewShipmentPhoneExtensionHome());
		result.setPhoneNumberHome(formDTO.getNewShipmentPhoneNumberHome());
		result.setMobilePrefix(formDTO.getNewShipmentMobilePrefix());
		result.setMobileNumber(formDTO.getNewShipmentMobileNumber());
		result.setPostalCode(formDTO.getNewShipmentZip());
		result.setCity(formDTO.getNewShipmentCity());
		result.setCountry(formDTO.getNewShipmentCountry());
		result.setEmail(formDTO.getNewShipmentEmail());
		return result;
	}
}
