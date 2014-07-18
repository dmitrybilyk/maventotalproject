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
package com.cgi.pacoshop.core.service.impl;

import static org.junit.Assert.assertEquals;

import de.hybris.platform.commerceservices.order.CommerceCartModification;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * Unit test of PacoshopSubscriptionCommerceCartServiceImpl.
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v Mar 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class PacoshopSubscriptionCommerceCartServiceImplTest
{
	@Mock
	private PacoshopSubscriptionCommerceCartServiceImpl cartService;

	@Mock
	private ModelService modelService;

	private CommerceCartModification modification;

	private CartModel cart;

	private ProductModel product;

	private List<ProductModel> products;

	private AbstractOrderEntryModel entry;

	/**
	 * Setting up the test.
	 *
	 * @throws Exception thrown on falure
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		Mockito.when(cartService.getModelService()).thenReturn(modelService);
		Mockito.doNothing().when(modelService).save(Mockito.anyObject());

		modification = new CommerceCartModification();
		entry = new AbstractOrderEntryModel();
		modification.setEntry(entry);

		cart = new CartModel();
		product = new ProductModel();

		products = new ArrayList();
		products.add(product);
		Mockito.when(cartService.addToCart(cart, product)).thenReturn(modification);
		Mockito.when(
				cartService.addProductsToCart(cart, products, "redirectUrl", "redirectUrlDesc",
														"contentId", "contentPlatformId", "contentTitle",
						"bonusImageUrl", "productImageUrl")).thenCallRealMethod();
		Mockito.when(
				cartService.addProductToCart(cart, products, "contentId", "contentPlatformId", "contentTitle", "bonusImageUrl",
						"productImageUrl")).thenCallRealMethod();

	}

	/**
	 * Test adding products to cart.
	 */
	@Test
	public void testAddProdcutsToCart()
	{
		cartService.addProductsToCart(cart, products, "redirectUrl", "redirectUrlDesc",
												"contentId", "contentPlatformId", "contentTitle",
				"bonusImageUrl", "productImageUrl");
		assertEquals("redirectUrl", cart.getRedirectUrl());
		assertEquals("redirectUrlDesc", cart.getRedirectUrlDescription());
		assertEquals("contentPlatformId", cart.getContentPlatformId());
		assertEquals("contentId", entry.getContentId());
		assertEquals("contentPlatformId", entry.getContentPlatformId());
		assertEquals("contentTitle", entry.getContentTitle());
		assertEquals("bonusImageUrl", entry.getBonusImageUrl());
		assertEquals("productImageUrl", entry.getProductImageUrl());
	}
}
