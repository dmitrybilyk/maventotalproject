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
package com.cgi.pacoshop.core.service.sso.impl;


import com.cgi.pacoshop.core.model.ResponsePlatformConfiguration;
import com.cgi.pacoshop.core.model.ResponseTermsVersionAssignment;
import com.cgi.pacoshop.core.model.TermVersionModel;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.sso.SSOPlatformRestClient;
import com.cgi.pacoshop.core.service.sso.SSOTermsVersionService;
import com.cgi.pacoshop.core.service.term.TermVersionService;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import static com.cgi.pacoshop.core.util.LogHelper.info;
import static com.cgi.pacoshop.core.util.LogHelper.warn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Implementation of SSOTermVersionService.
 *
 * @module pacoshopcore
 * @version 1.0v Jun 26, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SSOTermsVersionServiceImpl implements SSOTermsVersionService
{
	private static final Logger LOG = Logger.getLogger(SSOTermsVersionServiceImpl.class);

	@Resource
	private SSOPlatformRestClient ssoPlatformRestClient;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private TermVersionService termVersionService;

	@Override
	public TermVersionModel importTermsVersions()
	{
		final String platformId = shopConfigurationService.getSSOPlatformIdVal();

		ResponsePlatformConfiguration responsePlatformConfiguration = null;
		try
		{
			responsePlatformConfiguration = ssoPlatformRestClient.getPlatformConfigurationData(platformId);
		}
		catch (final HttpClientErrorException exception)
		{
			error(LOG, "Http client exception, platformId=%s, status= %s", exception, platformId, exception.getStatusCode());
		}
		catch (final HttpServerErrorException exception)
		{
			error(LOG, "Http server exception, platformId=%s, status= %s", exception, platformId, exception.getStatusCode());
		}
		catch (final Exception exception)
		{
			error(LOG, "Error occurred while getting platform configuration data from SSO, platformId=%s", exception, platformId);
		}

		if (responsePlatformConfiguration != null)
		{
			if (responsePlatformConfiguration.getPlatformId() != null)
			{
				updateAndSaveTermsVersion(platformId, responsePlatformConfiguration);
			}
			else
			{
				warn(LOG, "Couldn't import platform configuration with terms version by next platformId=%s", platformId);
			}
		}

		return null;
	}

	private void updateAndSaveTermsVersion(final String platformId,
														final ResponsePlatformConfiguration responsePlatformConfiguration)
	{
		List<ResponseTermsVersionAssignment> importedList = responsePlatformConfiguration.getTermsVersionAssignments();

		Assert.notNull(importedList, "Response TermsVersion assignment list is null");
		Assert.isTrue(!importedList.isEmpty(), "Response TermsVersion assignments are empty");

		info(LOG, "Terms version import summary:");
		info(LOG, "  %d terms version were imported", importedList.size());

		final Set<ResponseTermsVersionAssignment> responseTermsVersionAssignmentSet
				  = convertToSetWithUniqueTermVersionId(importedList);

		List<TermVersionModel> termVersionModels = new ArrayList<>(responseTermsVersionAssignmentSet.size());

		List<TermVersionModel> newTermVersionModels = new ArrayList<>();
		List<TermVersionModel> modifiedTermVersionModels = new ArrayList<>();

		List<TermVersionModel> existingTermVersionModels = new ArrayList<>(termVersionService.findAll());

		for (ResponseTermsVersionAssignment responseTermsVersionAssignment : responseTermsVersionAssignmentSet)
		{
			TermVersionModel termsVersion = null;

			if (!checkIfTermVersionAlreadyExist(responseTermsVersionAssignment, existingTermVersionModels)
					  && !checkIfTermVersionAlreadyExist(responseTermsVersionAssignment, newTermVersionModels))
			{
				termsVersion = termVersionService.create();
				termsVersion.setTermsVersionId(responseTermsVersionAssignment.getTermsVersionId());
				newTermVersionModels.add(termsVersion);
			}
			else
			{
				termsVersion = termVersionService.get(responseTermsVersionAssignment.getTermsVersionId());
				modifiedTermVersionModels.add(termsVersion);
			}

			termsVersion.setMandatory(responseTermsVersionAssignment.getMandatory());
			termsVersion.setTermsId(responseTermsVersionAssignment.getTermsId());
			termsVersion.setTermsVersionIdentifier(responseTermsVersionAssignment.getTermsVersionIdentifier());
			termsVersion.setUrl(responseTermsVersionAssignment.getUrl());
			termsVersion.setScope(responseTermsVersionAssignment.getScope());
			termsVersion.setType(responseTermsVersionAssignment.getType());
			termsVersion.setVersion(responseTermsVersionAssignment.getVersion());
			termsVersion.setName(responseTermsVersionAssignment.getName());
			termsVersion.setOrderByValue(responseTermsVersionAssignment.getOrderByValue());
			termVersionModels.add(termsVersion);
		}

		info(LOG, "  %d terms version were processed", responseTermsVersionAssignmentSet.size());
		info(LOG, "  %d new terms version", newTermVersionModels.size());
		info(LOG, "  %d modified terms version", modifiedTermVersionModels.size());

		termVersionService.saveAll(termVersionModels);
	}

	private boolean checkIfTermVersionAlreadyExist(final ResponseTermsVersionAssignment termsVersionAssignment,
														 final Collection<TermVersionModel> existingTermVersionModels)
	{
		for (TermVersionModel model : existingTermVersionModels)
		{
			if (model.getTermsVersionId().equals(termsVersionAssignment.getTermsVersionId()))
			{
				return true;
			}
		}
		return false;
	}

	private Set<ResponseTermsVersionAssignment> convertToSetWithUniqueTermVersionId(
			  final List<ResponseTermsVersionAssignment> importedList)
	{
		Set<ResponseTermsVersionAssignment> responseTermsVersionAssignmentSet = new HashSet<>(importedList);
		Set<String> notUniqueTermVersionIds = new HashSet<>();

		if (responseTermsVersionAssignmentSet.size() != importedList.size())
		{
			//counts occurrences of unique ids in list, if > 1 -> list has elements with not unique termVersionId
			for (ResponseTermsVersionAssignment unique : responseTermsVersionAssignmentSet)
			{
				int count = 0;
				String currentTermVersionId = unique.getTermsVersionId();
				for (ResponseTermsVersionAssignment notUnique : importedList)
				{
					if (currentTermVersionId.equals(notUnique.getTermsVersionId()))
					{
						count++;
						if (count > 1)
						{
							notUniqueTermVersionIds.add(currentTermVersionId);
							break;
						}
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			for (String notUniqueTermVersionId : notUniqueTermVersionIds)
			{
				sb.append(notUniqueTermVersionId);
				sb.append(" ");
			}
			error(LOG, "Imported TermsVersionAssignments contain TermsVersion with same termVersionId = { %s}. "
					  + "The first occurrences will be imported", sb.toString());
		}

		return responseTermsVersionAssignmentSet;
	}
}
