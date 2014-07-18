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
package com.cgi.pacoshop.core.service.term;


import com.cgi.pacoshop.core.model.TermVersionModel;
import java.util.Collection;

/**
 * Provide access to TermVersion entities on the services layer.
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
public interface TermVersionService
{
	/**
	 * Create new persisted TermVersionModel instance.
	 *
	 * @return new persisted TermVersionModel instance
	 */
	TermVersionModel create();

	/**
	 * Find single TermVersion entity instance by its termVersionId value.
	 *
	 * @param termsVersionId - a termVersion identifier
	 * @return TermVersionModel instance
	 */
	TermVersionModel get(String termsVersionId);

	/**
	 * Save all persisted termsVersionModel values to DB.
	 *
	 * @param termsVersionModels collection of TermVersionModel persisted values.
	 */
	void saveAll(Collection<TermVersionModel> termsVersionModels);

	/**
	 *
	 * @return Collection of all TermVersionModel
	 */
	Collection<TermVersionModel> findAll();

	/**
	 * Collect the terms with specified termName and return the latest one.
	 *
	 * @param termName filter by TermVersions.name field in DB
	 * @return the latest TermVersion based on TermVersion.version field and filtered by TermVersion.name value.
	 */
	TermVersionModel getLatestTermVersionByName(String termName);
}
