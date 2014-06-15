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
package com.cgi.pacoshop.mock.uag.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MockPortalServlet allows to upload new portal specification. If new uploaded file with portal key already present -
 * it will be replaced.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Apr 07, 2014
 * @module UAG_mock
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class MockPortalServlet extends HttpServlet
{
	/**
	 * Portals location setting.
	 */
	public final static String PORTALS_LOCATION = "portalsLocation";

	/**
	 * Default logger.
	 */
	private final static Logger LOGGER = Logger.getLogger(MockPortalServlet.class.getName());

	/**
	 * Upload JSP page.
	 */
	private final static String PAGE_UPLOAD = "upload.jsp";

	/**
	 * Some minor init behaviour.
	 *
	 * @throws javax.servlet.ServletException Servlet Exception.
	 */
	@Override
	public void init() throws ServletException
	{
		LOGGER.debug("*****************PORTAL SERVLET************************");
	}

	/**
	 * Getting available list of portals.
	 *
	 * @param request  Request from client.
	 * @param response Response to client.
	 * @throws javax.servlet.ServletException Servlet Exception.
	 * @throws java.io.IOException      Missing file exception.
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("portals", getUploadedPortals());
		RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_UPLOAD);
		if (dispatcher != null)
		{
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Portal specification upload.
	 *
	 * @param request  Request from client.
	 * @param response Response to client.
	 * @throws javax.servlet.ServletException Servlet Exception.
	 * @throws java.io.IOException      Missing file exception.
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
						  final HttpServletResponse response) throws ServletException, IOException
	{
		// Process only if it's multipart content.
		if (ServletFileUpload.isMultipartContent(request))
		{
			try
			{
				List<FileItem> multiParts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				Map<String, Object> data = new HashMap<>();
				for (FileItem item : multiParts)
				{
					if (!item.isFormField())
					{
						data.put(item.getFieldName(), item);
					}
					else
					{
						data.put(item.getFieldName(), item.getString());
					}
				}

				if (!data.containsKey("portal_id") || ((String) data.get("portal_id")).isEmpty())
				{
					throw new Exception("Missing (or empty) portal Id.");
				}
				String portalId = (String) data.get("portal_id");

				if (!data.containsKey("portal_spec"))
				{
					throw new IOException("Missing file...");
				}

				// Check Directory.
				File directoryCheck = new File(getPortalsDirectory());
				if (!directoryCheck.exists())
				{
					directoryCheck.mkdirs();
				}

				// Write file.
				((FileItem) data.get("portal_spec")).write(new File(getFilePath(portalId)));

				// File uploaded successfully.
				request.setAttribute("message", "File Uploaded Successfully");
			}
			catch (Exception ex)
			{
				request.setAttribute("message", "File Upload Failed due to: " + ex);
			}

		}
		else
		{
			request.setAttribute("message", "Sorry, you must upload a file...");
		}

		request.setAttribute("portals", getUploadedPortals());
		request.getRequestDispatcher(PAGE_UPLOAD).forward(request, response);
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
		return getServletContext().getRealPath(getServletContext().getInitParameter(PORTALS_LOCATION)) + File.separator;
	}

	/**
	 * Get list of uploaded portals.
	 *
	 * @return List<String>
	 */
	protected List<String> getUploadedPortals()
	{
		List<String> result = new ArrayList<>();
		File directory = new File(getPortalsDirectory());
		File[] files = directory.listFiles();
		for (File file : files)
		{
			result.add(file.getName());
		}

		return result;
	}
}
