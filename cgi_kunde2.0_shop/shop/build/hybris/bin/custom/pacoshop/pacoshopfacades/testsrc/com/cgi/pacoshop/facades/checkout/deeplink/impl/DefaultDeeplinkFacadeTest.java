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
package com.cgi.pacoshop.facades.checkout.deeplink.impl;


import com.cgi.pacoshop.core.checkout.AbstractDynamicCheckoutFrameworkMockFactory;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 *Test DefaultDeeplinkFacadeTest
 *
 *For a valid "Kombinangebot", all products should be subscription.
 *
 * @module hybris
 * @version 1.0v Feb 28, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class DefaultDeeplinkFacadeTest
{
	private static DefaultDeeplinkFacade DEEP_LINK_FACADE;
	private List<ProductModel> products = new ArrayList<>();


	/**
	 * Sets up.
	 */
	@BeforeClass
	public static void setUp()
	{
		DEEP_LINK_FACADE = new DefaultDeeplinkFacade();
	}

	/**
	 * Clean up.
	 */
	@After
	public void cleanUp()
	{
		products = new ArrayList<>();
	}


	/**
	 * Is valid kombi abo _ 1.
	 */
	@Test
	public void isValidKombiAbo1()
	{
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockDIgitalABO());
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockGUTER());

		Assert.assertFalse(DEEP_LINK_FACADE.isValidKombiAbo(products));
	}

	/**
	 * Is valid kombi abo _ 2.
	 */
	@Test
	public void isValidKombiAbo2()
	{
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockONLINEARTIKEL());
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockDOWNLOAD());

		Assert.assertFalse(DEEP_LINK_FACADE.isValidKombiAbo(products));
	}

	/**
	 * Is valid kombi abo _ 3.
	 */
	@Test
	public void isValidKombiAbo3()
	{
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockNEWSLETTER());
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockPrintABO());

		Assert.assertTrue(DEEP_LINK_FACADE.isValidKombiAbo(products));
	}

	/**
	 * Is valid kombi abo _ 4.
	 */
	@Test
	public void isValidKombiAbo4()
	{
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockONLINEARTIKEL());
		products.add(AbstractDynamicCheckoutFrameworkMockFactory.createMockPrintABO());

		Assert.assertFalse(DEEP_LINK_FACADE.isValidKombiAbo(products));
	}

	/**
	 * Is valid kombi abo _ 5.
	 */
	@Test
	public void isValidKombiAbo5()
	{

		final ProductModel mockPrintABO = AbstractDynamicCheckoutFrameworkMockFactory.createMockPrintABO();
		products.add(mockPrintABO);
		products.add(mockPrintABO);

		Assert.assertTrue(DEEP_LINK_FACADE.isValidKombiAbo(products));
	}



}
