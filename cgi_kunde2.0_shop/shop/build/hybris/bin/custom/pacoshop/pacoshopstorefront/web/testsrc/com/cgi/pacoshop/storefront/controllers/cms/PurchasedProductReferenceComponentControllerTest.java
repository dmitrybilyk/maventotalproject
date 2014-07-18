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
package com.cgi.pacoshop.storefront.controllers.cms;

import com.cgi.pacoshop.facades.suggestion.SimpleSuggestionFacade;
import com.cgi.pacoshop.storefront.controllers.ControllerConstants;
import com.cgi.pacoshop.storefront.controllers.pages.AbstractPageController;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorcms.model.components.PurchasedProductReferencesComponentModel;
import de.hybris.platform.catalog.enums.ProductReferenceTypeEnum;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.impl.DefaultCMSComponentService;
import de.hybris.platform.commercefacades.product.data.ProductData;
import junit.framework.Assert;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link PurchasedProductReferenceComponentController}.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01, 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@UnitTest
public class PurchasedProductReferenceComponentControllerTest
{
	private static final String COMPONENT_UID = "componentUid";
	private static final String TEST_COMPONENT_UID = "componentUID";
	private static final String TEST_TYPE_CODE = PurchasedProductReferencesComponentModel._TYPECODE;
	private static final String TEST_TYPE_VIEW = ControllerConstants.Views.Cms.ComponentPrefix
			+ StringUtils.lowerCase(TEST_TYPE_CODE);
	private static final String TITLE = "title";
	private static final String TITLE_VALUE = "Accessories";
	private static final String PRODUCT_REFERENCES = "productReferences";
	private static final String COMPONENT = "component";
	private static final String CATEGORY_CODE = "CategoryCode";

	private PurchasedProductReferenceComponentController purchasedProductReferenceComponentController;

	@Mock
	private PurchasedProductReferencesComponentModel purchasedProductReferencesComponentModel;
	@Mock
	private Model model;
	@Mock
	private DefaultCMSComponentService cmsComponentService;
	@Mock
	private SimpleSuggestionFacade simpleSuggestionFacade;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	@Mock
	private ProductData productData = null;
	@Mock
	private CategoryModel categoryModel;

	private final List<ProductData> productDataList = Collections.singletonList(productData);

	/**
	 * Setup test.
	 */
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		purchasedProductReferenceComponentController = new PurchasedProductReferenceComponentController();
		purchasedProductReferenceComponentController.setCmsComponentService(cmsComponentService);
		ReflectionTestUtils
				.setField(purchasedProductReferenceComponentController, "simpleSuggestionFacade", simpleSuggestionFacade);
	}

	/**
	 * Test render component.
	 *
	 * @throws Exception Exception.
	 */
	@Test
	public void testRenderComponent() throws Exception
	{
		given(purchasedProductReferencesComponentModel.getMaximumNumberProducts()).willReturn(Integer.valueOf(1));
		given(purchasedProductReferencesComponentModel.getTitle()).willReturn(TITLE_VALUE);
		given(purchasedProductReferencesComponentModel.getProductReferenceTypes()).willReturn(
				Arrays.asList(ProductReferenceTypeEnum.ACCESSORIES));
		given(purchasedProductReferencesComponentModel.getCategory()).willReturn(categoryModel);
		given(categoryModel.getCode()).willReturn(CATEGORY_CODE);
		given(Boolean.valueOf(purchasedProductReferencesComponentModel.isFilterPurchased())).willReturn(Boolean.TRUE);

		given(
				simpleSuggestionFacade.getReferencesForPurchasedInCategory(Mockito.anyString(), Mockito.anyList(),
						Mockito.anyBoolean(), Mockito.<Integer>any())
		).willReturn(productDataList);

		final String viewName = purchasedProductReferenceComponentController.handleComponent(request, response, model,
				purchasedProductReferencesComponentModel);
		verify(model, Mockito.times(1)).addAttribute(TITLE, TITLE_VALUE);
		verify(model, Mockito.times(1)).addAttribute(PRODUCT_REFERENCES, productDataList);
		Assert.assertEquals(TEST_TYPE_VIEW, viewName);
	}

	/**
	 * Test render component UID.
	 *
	 * @throws Exception Exception.
	 */
	@Test
	public void testRenderComponentUid() throws Exception
	{
		given(request.getAttribute(COMPONENT_UID)).willReturn(TEST_COMPONENT_UID);
		given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willReturn(purchasedProductReferencesComponentModel);
		given(purchasedProductReferencesComponentModel.getMaximumNumberProducts()).willReturn(Integer.valueOf(1));
		given(purchasedProductReferencesComponentModel.getTitle()).willReturn(TITLE_VALUE);
		given(purchasedProductReferencesComponentModel.getProductReferenceTypes()).willReturn(
				Arrays.asList(ProductReferenceTypeEnum.ACCESSORIES));
		given(purchasedProductReferencesComponentModel.getCategory()).willReturn(categoryModel);
		given(categoryModel.getCode()).willReturn(CATEGORY_CODE);
		given(Boolean.valueOf(purchasedProductReferencesComponentModel.isFilterPurchased())).willReturn(Boolean.TRUE);
		given(
				simpleSuggestionFacade.getReferencesForPurchasedInCategory(Mockito.anyString(), Mockito.anyList(),
						Mockito.anyBoolean(), Mockito.<Integer>any())
		).willReturn(productDataList);

		final String viewName = purchasedProductReferenceComponentController.handleGet(request, response, model);
		verify(model, Mockito.times(1)).addAttribute(COMPONENT, purchasedProductReferencesComponentModel);
		verify(model, Mockito.times(1)).addAttribute(TITLE, TITLE_VALUE);
		verify(model, Mockito.times(1)).addAttribute(PRODUCT_REFERENCES, productDataList);
		Assert.assertEquals(TEST_TYPE_VIEW, viewName);
	}

	/**
	 * Test render component for not found.
	 *
	 * @throws Exception Exception.
	 */
	@Test(expected = AbstractPageController.HttpNotFoundException.class)
	public void testRenderComponentNotFound() throws Exception
	{
		given(request.getAttribute(COMPONENT_UID)).willReturn(null);
		given(request.getParameter(COMPONENT_UID)).willReturn(null);
		purchasedProductReferenceComponentController.handleGet(request, response, model);
	}

	/**
	 * Not found 2.
	 *
	 * @throws Exception Exception.
	 */
	@Test(expected = AbstractPageController.HttpNotFoundException.class)
	public void testRenderComponentNotFound2() throws Exception
	{
		given(request.getAttribute(COMPONENT_UID)).willReturn(null);
		given(request.getParameter(COMPONENT_UID)).willReturn(TEST_COMPONENT_UID);
		given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willReturn(null);
		purchasedProductReferenceComponentController.handleGet(request, response, model);
	}

	/**
	 * Not found 3.
	 *
	 * @throws Exception Exception.
	 */
	@Test(expected = AbstractPageController.HttpNotFoundException.class)
	public void testRenderComponentNotFound3() throws Exception
	{
		given(request.getAttribute(COMPONENT_UID)).willReturn(TEST_COMPONENT_UID);
		given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willReturn(null);
		given(cmsComponentService.getSimpleCMSComponent(TEST_COMPONENT_UID)).willThrow(new CMSItemNotFoundException(""));
		purchasedProductReferenceComponentController.handleGet(request, response, model);
	}
}
