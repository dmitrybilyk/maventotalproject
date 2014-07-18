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


import com.cgi.pacoshop.core.service.CustomerAddressService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.strategies.CompareAddressesStrategy;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.info;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Save customer address to hybris and take into account Configurable number of addresses. +Review
 *
 *
 * @module hybris
 * @version 1.0v Apr 03, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.erofeev@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CustomerAddressServiceImpl implements CustomerAddressService
{
	private static final Logger LOG = Logger.getLogger(CustomerAddressServiceImpl.class);
	private static final int FIRST_INDEX = 0;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private ModelService modelService;

	@Resource
	private CompareAddressesStrategy compareAddressesStrategy;

	/**
	 * Save customer address to hybris and take into account Configurable number of addresses.
	 * The shop address should never overwrite sso or sap addresses
	 *  @param newAddress - address to save to the customer.
	 * @param user - customer.
	 */
	@Override
	public void saveCustomerAddressWithLimit(final AddressModel newAddress, final UserModel user)
	{
		if (user instanceof CustomerModel)
		{
			CustomerModel customer = (CustomerModel) user;
			if (!addressExistInUser(newAddress, customer))
			{
				saveAddressWithLimit(newAddress, customer);
			}
			else
			{
				info(LOG, " Can't save new address to customer. Address [ %s ] already exist and will be updated", newAddress);
			}
		}

	}

	/**
	 * Check exist this address in users addresses list or not.
	 * @param newAddress - address to save to the customer.
	 * @param customer - customer.
	 * @return true if address exist in customers addresses.
	 */
	private boolean addressExistInUser(final AddressModel newAddress, final CustomerModel customer)
	{
		for (AddressModel address : customer.getAddresses())
		{
			if (compareAddressesByFields(newAddress, address))
			{
				address.setLastUpdate(new Date());
				modelService.save(address);
				return true;
			}
		}
		return false;
	}

	/**
	 * Save customer address to hybris and take into account Configurable number of addresses.
	 * The shop address should never overwrite sso or sap addresses
	 * @param newAddress - address to save to the customer.
	 * @param customer - customer.
	 */
	private void saveAddressWithLimit(final AddressModel newAddress, final CustomerModel customer)
	{
		int customerAddressLimit = shopConfigurationService.getCustomerAddressLimit();
		final AddressModel mainAddress = customer.getMainAddress();
		//addresses - RandomAccessUnmodifiableList
		Collection<AddressModel> addresses = customer.getAddresses();
		List<AddressModel> persistentAddresses = new ArrayList<>();
		List<AddressModel> shopAddresses = new ArrayList<>();
		//1 - COUNT NUMBER OF SHOP AND SSO AND SAP ADDRESSES
		//2 - IF THERE'S PLACE FOR THAT - ADD A SHOP ADDRESS
		for (AddressModel address : addresses)
		{
			if (compareAddressesByFields(address, mainAddress) || isSAPAddress(address))
			{
				persistentAddresses.add(address);
			}
			else
			{
				shopAddresses.add(address);
			}
		}
		if (persistentAddresses.size() < customerAddressLimit)
		{
			if (shopAddresses.size() + persistentAddresses.size() >= customerAddressLimit)
			{
				removeOldest(shopAddresses);
			}

			newAddress.setLastUpdate(new Date());
			shopAddresses.add(newAddress);
		}
		//addresses - RandomAccessUnmodifiableList
		customer.setAddresses(Collections.unmodifiableList(ListUtils.union(persistentAddresses, shopAddresses)));

		newAddress.setOwner(customer);
		modelService.save(customer);
	}

	/**
	 * Method removes oldest address by LastUpdate date or the first element.
	 * @param shopAddresses list of addresses
	 */
	private void removeOldest(final List<AddressModel> shopAddresses)
	{
		AddressModel addressOldest = new AddressModel();
		//prevents NPE
		addressOldest.setLastUpdate(new Date());
		for (AddressModel address:shopAddresses)
		{
			if (address.getLastUpdate() != null && address.getLastUpdate().before(addressOldest.getLastUpdate()))
			{
				addressOldest = address;
			}
		}

		if (shopAddresses.contains(addressOldest))
		{
			shopAddresses.remove(addressOldest);
		}
		else
		{
			shopAddresses.remove(FIRST_INDEX);
		}
	}

	/**
	 * Method compares two addresses by fields.
	 * @param firstAddress first address for comparing.
	 * @param secondAddress second address for comparing.
	 * @return if addresses are equal then return true
	 */
	@Override
	public boolean compareAddressesByFields(final AddressModel firstAddress,
														 final AddressModel secondAddress)
	{
		boolean result = compareAddressesStrategy.compareAddresses(firstAddress, secondAddress);
		debug(LOG, "Comparing addresses. Result: %b ", result);
		return result;
	}

	/**
	 * The shop addresses never replace sap address.
	 * @param address
	 * @return
	 */

	private boolean isSAPAddress(final AddressModel address)
	{
		return StringUtils.isNotEmpty(address.getBusinessPartnerId());
	}

}
