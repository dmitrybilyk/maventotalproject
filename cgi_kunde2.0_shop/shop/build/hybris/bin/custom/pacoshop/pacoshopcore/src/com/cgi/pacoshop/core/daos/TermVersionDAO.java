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
package com.cgi.pacoshop.core.daos;


import com.cgi.pacoshop.core.model.TermVersionModel;
import java.util.Collection;
import java.util.List;

/**
 * Provide access to TermVersion entities on the data access layer.
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
public interface TermVersionDAO
{
	/**
	 * Find single TermVersion entity instance by its termsVersionId value.
	 *
	 * @param termsVersionId - a termVersion identifier
	 * @return TermVersionModel instance
	 */
	TermVersionModel findByTermsVersionId(String termsVersionId);

	/**
	 * Collect all TermVersion entities stored in DB.
	 *
	 * @return list of TermVersionModel instances
	 */
	List<TermVersionModel> findAll();

	/**
	 * Save all persisted termsVersionModel values to DB.
	 *
	 * @param termsVersionModels collection of TermVersionModel persisted values.
	 */
	void saveAll(Collection<TermVersionModel> termsVersionModels);

	/**
	 * Query all TermVersion object filtering by name and sorted Desc by version.
	 *
	 * @param termName filter by TermVersions.name field in DB
	 * @return list of TermVersions with specified name and sorted Desc by version value.
	 */
	List<TermVersionModel> getTermsByName(String termName);
}
