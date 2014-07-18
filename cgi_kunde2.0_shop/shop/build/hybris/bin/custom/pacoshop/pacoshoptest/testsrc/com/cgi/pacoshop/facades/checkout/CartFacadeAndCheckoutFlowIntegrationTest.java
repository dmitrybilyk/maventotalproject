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
package com.cgi.pacoshop.facades.checkout;


import com.cgi.pacoshop.AbstractServicelayerTransactionalTest;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.model.OrderDTO;
import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.service.CallbackRestService;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkCallbackFacade;
import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkFacade;
import com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Integration test for cart processing.
 *
 * @module pacoshopfacade
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@IntegrationTest
public class CartFacadeAndCheckoutFlowIntegrationTest extends AbstractServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(CartFacadeAndCheckoutFlowIntegrationTest.class);

	private static final String  ONLINE_ARTICLE_PRODUCT_ID                         = "productOnlineArticle";
	private static final String  PRODUCT_CATALOG                                   = "kundeProductCatalog";
	private static final String  ONLINE                                            = "Online";
	private static final String  ONLINE_ARTICLE_PRODUCT_TYPE                       = "ONLINEARTIKEL";
	private static final String  DEFAULT_STRING                                    = "test";
	private static final boolean DISPLAYED                                         = true;
	private static final boolean NOT_DISPLAYED                                     = false;
	private static final int     NOT_COMPLETED_CHECKOUT_STEPS_QUANTITY             = 1;
	private static final int     NOT_COMPLETED_CHECKOUT_STEPS_QUANTITY_CART_FACADE = 5;
	private static final String  PORTAL_ID                                         = "1";
	private static final String  CART_ID                                           = "1";

	@Resource
	private DeeplinkFacade         deeplinkFacade;
	@Resource
	private DynamicCheckoutFacade  dynamicCheckoutFacade;
	@Resource
	private CatalogVersionService  catalogVersionService;
	@Resource
	private CMSSiteService         cmsSiteService;
	@Resource
	private ModelService           modelService;
	@Resource
	private DeeplinkCallbackFacade deeplinkCallbackFacade;
	@Resource
	private CallbackRestService    callbackRestService;

	private ProductModel productModel;

	/**
	 *
	 * Setups Product with ProductType - ONLINEARTIKEL. Sets sessions catalog version and current site.
	 *
	 * @throws Exception
	 *            java.lang.Exception
	 *
	 */
	@Before
	public void setUp() throws Exception
	{
		super.importData();
		createCoreData();
		importCsv("/pacoshoptest/import/testProducts.impex", "utf-8");

		for (final CMSSiteModel cmsSiteModel : cmsSiteService.getSites())
		{
			final CatalogModel catalogModel = cmsSiteModel.getDefaultCatalog();
			if (catalogModel.getId().equals(PRODUCT_CATALOG))
			{
				for (final CatalogVersionModel versionModel : cmsSiteModel.getDefaultCatalog().getCatalogVersions())
				{
					if (versionModel.getVersion().equals(ONLINE))
					{
						catalogVersionService.setSessionCatalogVersion(PRODUCT_CATALOG, ONLINE);
						cmsSiteService.setCurrentSite(cmsSiteModel);
						break;
					}
				}
			}
		}
	}

	/**
	 *
	 * Test that ONLINEARTIKEL on DCF has one checkoutstep with no displayed formELementGroups.
	 *
	 */
	@Test
	public void testCartFacade()
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setUseId(Boolean.TRUE);
		productDTO.setProductId(ONLINE_ARTICLE_PRODUCT_ID);
		final List<ProductModel> products =
				  deeplinkFacade.findProductInOnlineCatalogOfCurrentWebsite(Arrays.asList(productDTO));
		assertNotNull("Product should be in DB", products);
		assertEquals("Product should be ONLINEARTIKEL", ONLINE_ARTICLE_PRODUCT_TYPE,
						 products.get(0).getProductType().getCode());

		final CartModel cartModel =
				  deeplinkFacade.createCartWithProducts(products, DEFAULT_STRING, DEFAULT_STRING,
																	 DEFAULT_STRING, DEFAULT_STRING,
																	 DEFAULT_STRING, DEFAULT_STRING);
		assertNotNull("Cart should be created", cartModel);

		final List<CheckoutStep> checkoutSteps = dynamicCheckoutFacade.getNeededSteps();
		assertNotNull("Not null checkoutsteps", checkoutSteps);

		int notCompletedSteps = 0;

		// checkout has five steps
		assertEquals("Checkout has two steps", NOT_COMPLETED_CHECKOUT_STEPS_QUANTITY_CART_FACADE, checkoutSteps.size());
		for (final CheckoutStep checkoutStep : checkoutSteps)
		{
			LOG.info("CheckoutStep : " + checkoutStep.getStepName());
			LOG.info("Completed : " + checkoutStep.isCompleted());

			if (!checkoutStep.isCompleted())
			{
				notCompletedSteps++;
				assertTrue("There should be not more than five uncompleted steps",
							  NOT_COMPLETED_CHECKOUT_STEPS_QUANTITY_CART_FACADE >= notCompletedSteps);
			}
		}
	}

	/**
	 * Test for shippingCalculationTest.
	 */
	// this test should be actualized WRT to current business-rules
	@Test
	@Ignore
	public void shippingCalculationTest()
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setUseId(Boolean.TRUE);
		productDTO.setProductId(ONLINE_ARTICLE_PRODUCT_ID);
		List<ProductModel> productModel =
                deeplinkFacade.findProductInOnlineCatalogOfCurrentWebsite(Arrays.asList(productDTO));
		assertNotNull("Product should be in DB", productModel);
		assertEquals("Product should be ONLINEARTIKEL", ONLINE_ARTICLE_PRODUCT_TYPE,
                productModel.get(0).getProductType().getCode());

		final OrderDTO orderDTO = callbackRestService.getOrderDTO(deeplinkCallbackFacade.getPortalUrl(PORTAL_ID), CART_ID);

		final CartModel cartModel = deeplinkCallbackFacade.createCartWithProducts(productModel, orderDTO);
		assertNotNull("Cart should be created", cartModel);
		final List<CheckoutStep> checkoutSteps = dynamicCheckoutFacade.getNeededSteps();
		assertNotNull("Not null checkoutsteps", checkoutSteps);

		int notCompletedSteps = 0;

		for (final CheckoutStep checkoutStep : checkoutSteps)
		{
			LOG.info("CheckoutStep : " + checkoutStep.getStepName());
			LOG.info("Completed : " + checkoutStep.isCompleted());

			if (!checkoutStep.isCompleted())
			{
				notCompletedSteps++;
				assertEquals("There should be only one NOT completed step", NOT_COMPLETED_CHECKOUT_STEPS_QUANTITY, notCompletedSteps);
			}
			else
			{
				assertEquals("CheckoutStep should contain NO displayed formElementGroups", NOT_DISPLAYED, dynamicCheckoutFacade
						.getDisplayMappings(checkoutStep).containsValue(DISPLAYED));
			}
		}

	}


	/**
	 *
	 * Clean method.
	 *
	 */
	@After
	public void clean()
	{
		if (productModel != null)
		{
			modelService.remove(productModel.getPk());
		}
	}
}
