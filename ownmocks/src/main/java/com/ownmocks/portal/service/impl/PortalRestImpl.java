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
package com.ownmocks.portal.service.impl;


import com.ownmocks.portal.service.PortalRest;
import com.ownmocks.portal.servlet.MockPortalServlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;

/**
 * Simple rest service for callback from deeplink. Currently getting data from uploaded files.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 04, 2014
 * @module mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class PortalRestImpl implements PortalRest
{
	@Context
	private ServletContext context;

	@Override
	public String getCartEntries(final String cartId) throws JAXBException
	{
		String returnValue = "";
		FileReader file = null;

		try
		{
			file = new FileReader(getFilePath(cartId));
			BufferedReader reader = new BufferedReader(file);
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				returnValue += line + "\n";
			}
		}
		catch (Exception e)
		{
			returnValue = "Error, while reading portal specification...";
		}
		finally
		{
			if (file != null)
			{
				try
				{
					file.close();
				}
				catch (IOException e)
				{
					returnValue += " Error, closing file...";
				}
			}
		}
		return returnValue;
	}

	/**
	 * Get real path to file.
	 *
	 * @param cartId Portal cartId.
	 * @return String
	 */
	protected String getFilePath(final String cartId)
	{
		return getPortalsDirectory() + File.separator + cartId + ".xml";
	}

	/**
	 * Get portals directory path.
	 *
	 * @return String
	 */
	protected String getPortalsDirectory()
	{
		return context.getRealPath(context.getInitParameter(MockPortalServlet.PORTALS_LOCATION)) + File.separator;
	}
}


