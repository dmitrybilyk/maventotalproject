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
package com.cgi.pacoshop.core.exceptions.deeplink.status400;


/**
 * Is thrown when some mandatory data is missing for online articles
 *
 * contentId, contentPlatformId, contentTitle or redirectUrl are missing for
 * products of type online article ("Fehlende Angaben zum Online-Artikel")
 *
 * Return HTTP status 400.
 *
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Apr 18, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public final class MissingMandatoryParamsForOnlineArticleException
		  extends AbstractBadRequestException
{

	private static String EXCEPTION_MESSAGE =
			  "checkout.multi.errorPage.exceptions.mandatorydata.missing.onlinearticle";

	/**
	 * Instantiates a new Missing mandatory parameters for online article exception.
	 *
	 * @param message - addtional excepton message
	 */
	public MissingMandatoryParamsForOnlineArticleException(final String message)
	{
		super(EXCEPTION_MESSAGE);
		setAdditionalInformation(message);
    }





}
