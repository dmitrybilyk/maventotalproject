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
package com.cgi.pacoshop.fulfilmentprocess.exceptions;

import com.cgi.pacoshop.fulfilmentprocess.model.CAOrderInterfaceEntry;


/**
 * Represents an exception during population of a {@link CAOrderInterfaceEntry}.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CAOrderPopulatorException extends Exception
{

	/**
	 *
	 */
	private static final long serialVersionUID = -4417710672356793733L;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            the message.
	 */
	public CAOrderPopulatorException(final String message)
	{
		super(message);
	}

}
