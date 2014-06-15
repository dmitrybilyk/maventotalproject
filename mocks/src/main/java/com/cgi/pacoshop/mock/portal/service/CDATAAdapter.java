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
package com.cgi.pacoshop.mock.portal.service;


import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Here goes 1 line.
 * Here goes 2 line.
 *
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @version 1.0v Feb 10, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class CDATAAdapter extends XmlAdapter<String, String>
{
	@Override
	public String marshal(final String v) throws Exception
	{
		return "<![CDATA[" + v + "]]>";
	}

	@Override
	public String unmarshal(final String v) throws Exception
	{
		return v;
	}
}
