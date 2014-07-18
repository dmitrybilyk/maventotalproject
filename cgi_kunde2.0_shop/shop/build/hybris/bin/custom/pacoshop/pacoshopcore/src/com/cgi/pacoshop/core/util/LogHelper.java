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
package com.cgi.pacoshop.core.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * Logging Utility to reduce boilerplate code in business classes.
 * 
 * @author Peter Sorensen
 * 
 */
public final class LogHelper
{

	private LogHelper()
	{

	}

	/**
	 * Formats and debugs messages if debug mode is enabled.
	 * 
	 * @param logger
	 *           the logger to which to debug
	 * @param messageTmpl
	 *           the message template to debug
	 * @param params
	 *           the values to place in message template
	 */
	public static void debug(final Logger logger, final String messageTmpl, final Object... params)
	{
		if (logger.isDebugEnabled())
		{
			String message;
			if (params != null)
			{
				message = String.format(messageTmpl, params);
			}
			else
			{
				message = messageTmpl;
			}
			logger.debug(message);
		}
	}

	/**
	 * Formats and writes messages if info mode is enabled.
	 * 
	 * @param logger
	 *           the logger to which to info
	 * @param messageTmpl
	 *           the message template to info
	 * @param params
	 *           the values to place in message template
	 */
	public static void info(final Logger logger, final String messageTmpl, final Object... params)
	{
		if (logger.isInfoEnabled())
		{
			String message;
			if (params != null)
			{
				message = String.format(messageTmpl, params);
			}
			else
			{
				message = messageTmpl;
			}
			logger.info(message);
		}
	}

	/**
	 * Formats and writes messages if error mode is enabled.
	 * 
	 * @param logger
	 *           the logger to which to error
	 * @param messageTmpl
	 *           the message template to error
	 * @param params
	 *           the values to place in message template
	 */
	@SuppressWarnings("deprecation")
	public static void error(final Logger logger, final String messageTmpl, final Object... params)
	{
		if (logger.isEnabledFor(Priority.ERROR))
		{
			String message;
			if (params != null)
			{
				message = String.format(messageTmpl, params);
			}
			else
			{
				message = messageTmpl;
			}
			logger.error(message);
		}
	}

	/**
	 * Formats and writes messages if warn mode is enabled.
	 * 
	 * @param logger
	 *           the logger to which to warn
	 * @param messageTmpl
	 *           the message template to warn
	 * @param throwable
	 *           the exception to log
	 * @param params
	 *           the values to place in message template
	 */
	public static void error(final Logger logger, final String messageTmpl, final Throwable throwable, final Object... params)
	{
		if (logger.isEnabledFor(Priority.ERROR))
		{
			String message;
			if (params != null)
			{
				message = String.format(messageTmpl, params);
			}
			else
			{
				message = messageTmpl;
			}
			logger.error(message, throwable);
		}
	}

	/**
	 * Formats and writes messages if warn mode is enabled.
	 * 
	 * @param logger
	 *           the logger to which to warn
	 * @param messageTmpl
	 *           the message template to warn
	 * @param params
	 *           the values to place in message template
	 */
	@SuppressWarnings("deprecation")
	public static void warn(final Logger logger, final String messageTmpl, final Object... params)
	{
		if (logger.isEnabledFor(Priority.WARN))
		{
			String message;
			if (params != null)
			{
				message = String.format(messageTmpl, params);
			}
			else
			{
				message = messageTmpl;
			}
			logger.warn(message);
		}
	}

	/**
	 * Formats and writes messages if warn mode is enabled.
	 * 
	 * @param logger
	 *           the logger to which to warn
	 * @param messageTmpl
	 *           the message template to warn
	 * @param throwable
	 *           the exception to log
	 * @param params
	 *           the values to place in message template
	 */
	public static void warn(final Logger logger, final String messageTmpl, final Throwable throwable, final Object... params)
	{
		if (logger.isEnabledFor(Priority.WARN))
		{
			String message;
			if (params != null)
			{
				message = String.format(messageTmpl, params);
			}
			else
			{
				message = messageTmpl;
			}
			logger.warn(message, throwable);
		}
	}


	/**
	 * Log response of a RestTemplate Web service call.
	 *
	 * @param <T>
	 *           Response Entity Type.
	 * @param logger
	 *           the logger.
	 * @param response
	 *           the response of the RestTemplate call.
	 * @param callName
	 *           The name of the Web service call.
	 */
	public static <T> void logResponse(final Logger logger, final ResponseEntity<T> response, final String callName)
	{

		if (response == null)
		{
			logger.error(callName + " not successful. Response null");
			return;
		}

		if (response.getStatusCode() == HttpStatus.OK)
		{
			debug(logger, "%s successful.", callName);
			LogHelper.printXMLObject(logger, Level.DEBUG, response.getBody());
			return;
		}

		logger.error("Response status code: " + response.getStatusCode());
		logger.error(response.getBody());
	}


	/**
	 * Prints a JAXB object in XML format.
	 * 
	 * @param logger
	 *           The logger to write the output to. Log level is info.
	 * @param level
	 *           The log level.
	 * @param object
	 *           A deserialised XML object.
	 */
	public static void printXMLObject(final Logger logger, final Level level, final Object object)
	{
		if (!logger.isEnabledFor(level))
		{
			return;
		}

		StringWriter writer = null;
		try
		{
			writer = new StringWriter();
			final JAXBContext context = JAXBContext.newInstance(object.getClass());
			context.createMarshaller().marshal(object, writer);
			final String xml = writer.toString();
			logger.log(level, xml);
			writer.flush();
			writer.close();
		}
		catch (final JAXBException e)
		{
			logger.error("JAXBException during printObject", e);
		}
		catch (final IOException e)
		{
			logger.error("IOException during printObject", e);
		}
		finally
		{
			IOUtils.closeQuietly(writer);
		}
	}

}
