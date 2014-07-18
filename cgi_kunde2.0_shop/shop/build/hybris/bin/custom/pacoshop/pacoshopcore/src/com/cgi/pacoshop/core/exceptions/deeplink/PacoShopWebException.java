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
package com.cgi.pacoshop.core.exceptions.deeplink;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Whenever you the exception is thrown, Spring will return the http status 404.
 *
 * @module hybris - pacoshopstorefront
 * @version 1.0v Dec 19, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public class PacoShopWebException extends RuntimeException
{

	private static final int HTTP_STATUS = 400;

	private int httpStatus = HTTP_STATUS;

	private String familyExceptionMessage;
	private String productId;
	private String additionalInformation;
	private Map<String, List<PacoShopWebException>> nestedExceptions = new HashMap<>();

	/**
	 * Instantiates a new Paco shop web exception.
	 *
	 * @param message the message
	 */
	public PacoShopWebException(final String message)
	{
		super(message);
	}

	/**
	 * Gets nested exceptions.
	 *
	 * @return the nested exceptions
	 */
	public Map<String, List<PacoShopWebException>> getNestedExceptions()
	{
		return nestedExceptions;
	}

	/**
	 * Sets nested exceptions.
	 *
	 * @param nestedExceptions the nested exceptions
	 */
	public void setNestedExceptions(final Map<String, List<PacoShopWebException>> nestedExceptions)
	{
		this.nestedExceptions = nestedExceptions;
	}

	/** Constructs a new runtime exception with the specified detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *

	 * @param message the detail message. The detail message is saved for
	 *          later retrieval by the
	 * method.
	 */

	/**
	 * Sets http status.
	 *
	 * @param httpStatus the http status
	 */
	public void setHttpStatus(final int httpStatus)
	{
		this.httpStatus = httpStatus;
	}

	/**
	 * Sets family exception message.
	 *
	 * @param familyExceptionMessage the family exception message
	 */
	public void setFamilyExceptionMessage(final String familyExceptionMessage)
	{
		this.familyExceptionMessage = familyExceptionMessage;
	}

	/**
	 * Sets product id.
	 *
	 * @param productId the product id
	 */
	public void setProductId(final String productId)
	{
		this.productId = productId;
	}

	/**
	 * Sets additional information.
	 *
	 * @param additionalInformation the additional information
	 */
	public void setAdditionalInformation(final String additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

	/**
	 * Gets http status.

	 *
	 * @return the http status
	 */
	public int getHttpStatus()
	{
		return httpStatus;
	}


	/**
	 * Gets general exception message.
	 *
	 * @return the general exception message
	 */
	public String getFamilyExceptionMessage()
	{
		return familyExceptionMessage;
	}

	/**
	 * Gets product id.
	 *
	 * @return the product id
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * Gets additionalInformation.
	 *
	 * @return the additionalInformation
	 */
	public String getAdditionalInformation()
	{
		return additionalInformation;
	}
}
