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
package com.ownmocks.sso;

import com.google.gson.Gson;
import com.ownmocks.sso.api.SSOService;
import com.ownmocks.sso.model.PlatformEntity;
import com.ownmocks.sso.model.TermsAcceptedList;
import com.ownmocks.sso.model.UserEntity;
import com.ownmocks.sso.reader.ExcelReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * SSO Service implementation.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Azarenkov Eugene <azarenkov.eugene@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module mocks
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see <a href="https://wiki.hybris.com/">wiki.hybris.com</a>
 */
@Service("SSOService")
public class SSOServiceImpl implements SSOService
{
	private static final Logger LOG = LoggerFactory.getLogger(SSOServiceImpl.class.getName());

	private static final int USER_ID_INDEX    = 0;
	private static final int USER_EMAIL_INDEX = 4;

	private static final String SSO_AUTHORIZATION_HEADER               = "x-authorization";
	private static final String SSO_AUTHORIZATION_HEADER_DEFAULT_TOKEN = "wsTokenDefault";
	private static final String SSO_AUTHORIZATION_HEADER_ADMIN_TOKEN   = "wsTokenAdmin";

	private static final int PLATFORM_ID_INDEX = 0;

	@Context
	private HttpHeaders request;

	@Context
	private HttpServletResponse response;

	@Value("${sso.file.accounts}")
	private String ssoFileAccounts;

	@Value("${sso.file.platforms}")
	private String ssoFilePlatforms;

	/**
	 * Set SSO file for user accounts.
	 *
	 * @param ssoFileAccounts Sso file object.
	 */
	public void setSsoFileAccounts(final String ssoFileAccounts)
	{
		this.ssoFileAccounts = ssoFileAccounts;
	}

	/**
	 * Set SSO file for platform configurations.
	 *
	 * @param ssoFilePlatforms Sso file object.
	 */
	public void setSsoFilePlatforms(final String ssoFilePlatforms)
	{
		this.ssoFilePlatforms = ssoFilePlatforms;
	}

	/**
	 * Method for implementation of behavior described in  acceptance criteria.
	 *
	 * @param accountId User Id to import.
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @return Response.
	 * @throws java.io.IOException Can not write the output.
	 * @throws InterruptedException Response has been interrupted.
	 */
	public Response readAccount(final String accountId,
										 final String platformId,
										 final int delay) throws IOException, InterruptedException
	{
		final String userId = accountId.toLowerCase();
		final String wsToken = request.getRequestHeaders().getFirst(SSO_AUTHORIZATION_HEADER);

		LOG.debug("readAccount() accountId={}, wsToken={}, platformId={}", userId, wsToken, platformId);
		LOG.debug("readAccount() delay={}", delay);
		LOG.debug("readAccount() ssoFileAccounts={}", ssoFileAccounts);

		Thread.sleep(delay);

		// Check required params.
		if (accountId == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		// Check token.
		String wsTokenCheck = Integer.toString(userId.hashCode());
		if (StringUtils.isEmpty(wsToken)
				  || (!wsToken.equals(wsTokenCheck) && !wsToken.equals(SSO_AUTHORIZATION_HEADER_DEFAULT_TOKEN)))
		{
			return Response.status(Response.Status.FORBIDDEN).build();
		}

		try
		{
			return Response.ok(ExcelReader.createUserExcelReader().
					  getInfoByRow(USER_ID_INDEX, userId, ssoFileAccounts, response)).build();
		}
		catch (final NoSuchElementException e)
		{
			return Response.ok(new UserEntity()).build();
		}
	}

	@Override
	public Response getProductError(final int code)
	{
		return Response.status(code).build();
	}

	/**
	 * Response for GET request with params.
	 *
	 * @param email Input param for searching account.
	 * @param delay Processing delay.
	 * @return Should return Error Status Code .Method for throwing server error with appropriate status code.
	 * @throws Exception on error.
	 */
	@Override
	public Response searchAccount(final String email, final int delay) throws Exception
	{
		final String userEmail = email.toLowerCase();

		LOG.debug("searchAccount() email={}", userEmail);
		LOG.debug("searchAccount() delay={}", delay);
		LOG.debug("searchAccount() ssoFileAccounts={}", ssoFileAccounts);

		Thread.sleep(delay);
		if (userEmail == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try
		{
			// we will return a single user but in form of list
			List<UserEntity> userAsList = new ArrayList<>();
			UserEntity userEntity = (UserEntity) ExcelReader.createUserExcelReader().getInfoByRow(USER_EMAIL_INDEX, userEmail,
																															  ssoFileAccounts, response);
			userAsList.add(userEntity);
			return Response.ok(userAsList).build();
		}
		catch (final NoSuchElementException e)
		{
			List<UserEntity> userAsList = new ArrayList<>();
			userAsList.add(new UserEntity());
			return Response.ok(userAsList).build();
		}
	}

	/**
	 * Updates the customer account with the TermsAcceptedList (initially created for the Opt-in term).
	 * @param accountId User Id to import.
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @param responseStatus optional fake param which allows to simulate different error codes like 404 etc.
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @param terms POST request body. JSON object.
	 *
	 * @return status error code.
	 *
	 * @throws InterruptedException in a case when the Thread.sleep(delay) command would be interrupted.
	 *
	 * see KS-1545 user story for details
	 */
	@Override
	public Response writeAccount(final String accountId, final String platformId, final Integer responseStatus,
										  final Integer delay, final TermsAcceptedList terms) throws InterruptedException
	{
		Gson gson = new Gson();
		final String wsToken = request.getRequestHeaders().getFirst(SSO_AUTHORIZATION_HEADER);
		LOG.debug("writeAccount() accountid={}, platformId={}, wsToken={}, terms={}",
					 accountId, platformId, wsToken, gson.toJson(terms));

		if (delay != null)
		{
			Thread.sleep(delay);
		}
		if (responseStatus != null)
		{
			return Response.status(responseStatus).build();
		}
		final String userId = accountId.toLowerCase();

		// Check required params.
		if (StringUtils.isEmpty(accountId) || terms == null || terms.getTerms().isEmpty())
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		// Check token.
		if (StringUtils.isEmpty(wsToken) || !wsToken.equals(SSO_AUTHORIZATION_HEADER_ADMIN_TOKEN))
		{
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		return Response.noContent().build();
	}

	/**
	 * The readPlatform method of the SSO API provides access to the platform's configuration in SSO.
	 *
	 * @param platformId Identifier of the platform, i.e. the application (which is this case is the shop application).
	 * @param delay delay just for testing. There is could be passed delay time im millisecond.
	 * @return User object in JSON.
	 * @throws InterruptedException When delay interrupts.
	 * @throws java.io.IOException When problems with file reading.
	 */
	@Override
	public Response readPlatform(final String platformId, final int delay) throws IOException, InterruptedException
	{
		try
		{
			//get error by code
			int code = Integer.parseInt(platformId);
			return Response.status(code).build();
		}
			catch (NumberFormatException e)
		{
			LOG.debug("readPlatform() Code cant be parsed. Initiating readPlatform import...");
		}

		LOG.debug("readPlatform() platformId={}", platformId);
		LOG.debug("readPlatform() delay={}", delay);
		LOG.debug("readPlatform() ssoFilePlatforms={}", ssoFilePlatforms);

		Thread.sleep(delay);

		if (platformId == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try
		{
			return Response.ok(ExcelReader.createPlatformExcelReader().
					  getInfoByRow(PLATFORM_ID_INDEX, platformId, ssoFilePlatforms, response)).build();
		}
		catch (final NoSuchElementException e)
		{
			return Response.ok(new PlatformEntity()).build();
		}
	}
}
