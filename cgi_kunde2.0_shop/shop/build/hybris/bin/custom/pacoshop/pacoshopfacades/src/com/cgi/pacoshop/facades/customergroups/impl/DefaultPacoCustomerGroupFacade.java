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
package com.cgi.pacoshop.facades.customergroups.impl;


import static com.cgi.pacoshop.core.util.LogHelper.debug;

import de.hybris.platform.commercefacades.customergroups.CustomerGroupFacade;
import de.hybris.platform.commercefacades.user.data.UserGroupData;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.cgi.pacoshop.core.service.sso.SSOCustomerService;
import com.cgi.pacoshop.facades.customergroups.PacoCustomerGroupFacade;


/**
 * Assign user registered or visitor customer group.
 * 
 * @module build
 * @version 1.0v Feb 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class DefaultPacoCustomerGroupFacade implements PacoCustomerGroupFacade
{

	private static final Logger LOG = Logger.getLogger(DefaultPacoCustomerGroupFacade.class);


	@Resource
	private ConfigurationService configurationService;

	@Resource
	private CustomerGroupFacade customerGroupFacade;

	@Resource
	private SSOCustomerService ssoCustomerService;


	@Override
	public void addUserToCustomerSubgroup(final String userIdType, final String userUid)
	{
		// Get the uid of the user group the customer should be assigned to
		final String customerGroupId = ssoCustomerService.getUserGroupForSSOUserType(userIdType);

		debug(LOG, "Adding customer with id [%s] and id type [%s] to user group [%s]", userUid, userIdType, customerGroupId);

		// Add the user to the group
		customerGroupFacade.addUserToCustomerGroup(customerGroupId, userUid);

		// Remove the user from all other customer groups he might be a member of
		final List<UserGroupData> customerGroups = customerGroupFacade.getCustomerGroupsForUser(userUid);

		for (final UserGroupData customerGroup : customerGroups)
		{
			if (!customerGroup.getUid().equalsIgnoreCase(customerGroupId))
			{
				customerGroupFacade.removeUserFromCustomerGroup(customerGroup.getUid(), userUid);

				debug(LOG, "Remove user [%s] from customer group [%s]", userUid, customerGroup.getUid());
			}
		}
	}

	@Override
	public boolean isUserBelongsToUserType(final String userType, final String userUid)
	{
		final String customerGroupId = ssoCustomerService.getUserGroupForSSOUserType(userType);
		final List<UserGroupData> customerGroups = customerGroupFacade.getCustomerGroupsForUser(userUid);
		for (final UserGroupData customerGroup : customerGroups)
		{
			if (customerGroup.getUid().equalsIgnoreCase(customerGroupId))
			{
				debug(LOG, "userType [%s] of user [%s] is corresponds to the customer group [%s]",
						userType, userUid, customerGroup.getUid());
				return true;
			}
		}
		debug(LOG, "userType [%s] of user [%s] does not matches any customer group of this user", userType, userUid);
		return false;
	}

}
