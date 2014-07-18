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
package com.cgi.pacoshop.storefront.security.impl;

import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.customergroups.CustomerGroupFacade;
import de.hybris.platform.commercefacades.user.data.UserGroupData;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.cgi.pacoshop.storefront.security.AutoLoginStrategy;
import com.cgi.pacoshop.storefront.security.GUIDCookieStrategy;


/**
 * Default implementation of {@link AutoLoginStrategy}
 */
public class DefaultAutoLoginStrategy implements AutoLoginStrategy
{
	private static final Logger LOG = Logger.getLogger(DefaultAutoLoginStrategy.class);

	private static final String CUSTOMER_GROUP_UID = "customergroup";
	private static final String ANONYMOUS_TOKEN = "guestuser";

	private AuthenticationManager authenticationManager;
	private CustomerFacade customerFacade;
	private GUIDCookieStrategy guidCookieStrategy;
	private RememberMeServices rememberMeServices;

	@Resource
	private CustomerGroupFacade customerGroupFacade;


	@Override
	public void login(final String username, final String password, final HttpServletRequest request,
			final HttpServletResponse response)
	{
		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		token.setDetails(new WebAuthenticationDetails(request));
		try
		{
			final Authentication authentication = getAuthenticationManager().authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//getCustomerFacade().loginSuccess();
			getGuidCookieStrategy().setCookie(request, response);
			getRememberMeServices().loginSuccess(request, response, token);
		}
		catch (final Exception e)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Failure during autoLogin", e);
		}
	}

	/**
	 * Method login user by anonymous token
	 * 
	 * @param userUid
	 *           user Id
	 * @param request
	 *           request object
	 * @param response
	 *           response object
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Override
	public boolean login(final String userUid, final HttpServletRequest request, final HttpServletResponse response)
	{
		final List<GrantedAuthority> authorities = new ArrayList<>();

		final List<UserGroupData> customerGroups = customerGroupFacade.getCustomerGroupsForUser(userUid);
		for (final UserGroupData customerGroup : customerGroups)
		{
			authorities.add(new GrantedAuthorityImpl("ROLE_" + customerGroup.getUid().toUpperCase()));
		}

		final AnonymousAuthenticationToken token = new AnonymousAuthenticationToken(ANONYMOUS_TOKEN, userUid, authorities);
		token.setDetails(new WebAuthenticationDetails(request));
		try
		{
			final Authentication authentication = getAuthenticationManager().authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			try
			{
				getGuidCookieStrategy().setCookie(request, response);
			}
			catch (final IllegalStateException e)
			{
				LOG.warn("Cookie was not set to request");
			}
		}
		catch (final Exception e)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Failure during autoLogin", e);
			return false;
		}
		return true;
	}

	/**
	 * Gets authentication manager.
	 * 
	 * @return the authentication manager
	 */
	protected AuthenticationManager getAuthenticationManager()
	{
		return authenticationManager;
	}

	/**
	 * Sets authentication manager.
	 * 
	 * @param authenticationManager
	 *           the authentication manager
	 */
	@Required
	public void setAuthenticationManager(final AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	/**
	 * Gets customer facade.
	 * 
	 * @return the customer facade
	 */
	protected CustomerFacade getCustomerFacade()
	{
		return customerFacade;
	}

	/**
	 * Sets customer facade.
	 * 
	 * @param customerFacade
	 *           the customer facade
	 */
	@Required
	public void setCustomerFacade(final CustomerFacade customerFacade)
	{
		this.customerFacade = customerFacade;
	}

	/**
	 * Gets guid cookie strategy.
	 * 
	 * @return the guid cookie strategy
	 */
	protected GUIDCookieStrategy getGuidCookieStrategy()
	{
		return guidCookieStrategy;
	}

	/**
	 * Sets guid cookie strategy.
	 * 
	 * @param guidCookieStrategy
	 *           the guid cookie strategy
	 */
	@Required
	public void setGuidCookieStrategy(final GUIDCookieStrategy guidCookieStrategy)
	{
		this.guidCookieStrategy = guidCookieStrategy;
	}

	/**
	 * Gets remember me services.
	 * 
	 * @return the remember me services
	 */
	protected RememberMeServices getRememberMeServices()
	{
		return rememberMeServices;
	}

	/**
	 * Sets remember me services.
	 * 
	 * @param rememberMeServices
	 *           the remember me services
	 */
	@Required
	public void setRememberMeServices(final RememberMeServices rememberMeServices)
	{
		this.rememberMeServices = rememberMeServices;
	}
}
