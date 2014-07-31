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
package runner.sso.model.term.impl;


import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import runner.sso.model.term.TermVersionService;

/**
 * Implementation of TermVersionService interface.
 *
 * @module hybris
 * @version 1.0v Jun 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class TermVersionServiceImpl implements TermVersionService
{
	private static final Logger LOG = Logger.getLogger(TermVersionServiceImpl.class);

	@Resource
	private TermVersionDAO termVersionDAO;

	@Resource
	private ModelService modelService;

	@Override
	public TermVersionModel create()
	{
		return modelService.create(TermVersionModel.class);
	}

	@Override
	public TermVersionModel get(final String termsVersionId)
	{
		try
		{
			return termVersionDAO.findByTermsVersionId(termsVersionId);
		}
		catch (ModelNotFoundException exception)
		{
			debug(LOG, "TermVersion with termsVersionId = %s in not found", termsVersionId);
			return null;
		}
	}

	@Override
	public void saveAll(final Collection<TermVersionModel> termsVersionModels)
	{
		termVersionDAO.saveAll(termsVersionModels);
	}

	@Override
	public TermVersionModel getLatestTermVersionByName(final String termsType)
	{
		List<TermVersionModel> allTermsByTypeSorted = termVersionDAO.getTermsByName(termsType);
		if (!allTermsByTypeSorted.isEmpty())
		{
			// return first item from the result list
			return allTermsByTypeSorted.get(0);
		}
		return null;
	}

	@Override
	public Collection<TermVersionModel> findAll()
	{
		return termVersionDAO.findAll();
	}
}
