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
package com.cgi.pacoshop.mock.servicebus.service;

import com.cgi.pacoshop.mock.servicebus.model.SingleProduct;
import java.io.IOException;
import java.util.List;
import org.junit.Test;


/**
 * Simple parser test
 *
 *
 * @module MockServiceBus
 * @version 1.0v dec 13, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *	@see
 *
 */

public class ExcelTestReader
{
	/**
	 * Gets single product test.
	 *
	 * @throws java.io.IOException the iO exception
	 */
	@Test
	public void getSingleProductTest() throws IOException
	{
		ExcelReader excelReader = new ExcelReader();
		List<SingleProduct> list = excelReader.getSingleProduct();
		SingleProduct msh = list.get(0);
		System.out.print(list.size());
	}
}

