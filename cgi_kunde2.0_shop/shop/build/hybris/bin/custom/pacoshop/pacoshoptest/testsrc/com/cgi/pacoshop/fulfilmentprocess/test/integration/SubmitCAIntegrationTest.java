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
package com.cgi.pacoshop.fulfilmentprocess.test.integration;

import com.cgi.pacoshop.AbstractServicelayerTransactionalTest;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import de.hybris.platform.testframework.HybrisJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.impl.DefaultCatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkFacade;
import com.cgi.pacoshop.fulfilmentprocess.client.HttpCAClient;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.github.tomakehurst.wiremock.junit.WireMockRule;


/**
 * Integration test of the SubmitCA.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@IntegrationTest
@RunWith(HybrisJUnit4ClassRunner.class)
public class SubmitCAIntegrationTest extends AbstractServicelayerTransactionalTest
{
	public static final int WIRE_MOCK_PORT = 8888;
	@Rule
	public static WireMockRule WIRE_MOCK_RULE = new WireMockRule(WIRE_MOCK_PORT);


	private static final int CA_CONNECTION_TIMEOUT = 5000;
	private static final int CA_SO_TIMEOUT = 5000;
	private static final int STATUS = 200;

	private static final Logger LOG = Logger.getLogger(SubmitCAIntegrationTest.class);




	@Resource
	private DeeplinkFacade deeplinkFacade;

	@Resource
	private ModelService modelService;

	@Resource
	private DefaultProductService defaultProductService;

	@Resource
	private DefaultCatalogVersionService defaultCatalogVersionService;

	@Resource
	private I18NService i18nService;

	@Resource
	private BaseSiteService baseSiteService;

	@Mock
	private HttpSession httpSession;

	@Resource(name = "caClient")
	private HttpCAClient caClient;

	@Mock
	private ConfigurationService configurationService;

    @Resource
    private CheckoutCompletionFacade checkoutCompletionFacade;

	/**
	 * Constructor.
	 */
	public SubmitCAIntegrationTest()
	{
		Registry.setCurrentTenant(Registry.getTenantByID("junit"));
	}

	@Override
	@Before
	public void importData() throws Exception
	{
		super.importData();
		MockitoAnnotations.initMocks(this);

		i18nService.setCurrentJavaCurrency(Currency.getInstance("EUR"));
		final Configuration configuration = new BaseConfiguration();
		configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.CA_URL, "http://localhost:8888/CA/allowAccess");
		configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.CA_CONNECTION_TIMEOUT,
				Integer.valueOf(CA_CONNECTION_TIMEOUT));
		configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.CA_SO_TIMEOUT, Integer.valueOf(CA_SO_TIMEOUT));

		when(configurationService.getConfiguration()).thenReturn(configuration);
		caClient.setConfigurationService(configurationService);
		stubFor(post(urlEqualTo("/CA/allowAccess")).willReturn(aResponse().withStatus(STATUS)));
	}

	/**
	 * Success scenario testing.
	 *
	 * @throws Exception
	 *            trown on test failure
	 */
	@Test
	public void testGreen() throws Exception
	{
		final BaseSiteModel site = baseSiteService.getBaseSiteForUID("kunde1");
		baseSiteService.setCurrentBaseSite(site, true);
		final String productId = "product1";
		final CatalogVersionModel catalogVersionModel = defaultCatalogVersionService.getCatalogVersion("kundeProductCatalog",
				"Staged");
		final ProductModel productModel = defaultProductService.getProductForCode(catalogVersionModel, productId);
		final List<ProductModel> products = new ArrayList<ProductModel>();
		products.add(productModel);
		LOG.debug("Fetch product1");
		final CartModel cartModel = deeplinkFacade.createCartWithProducts(products, null, "contentId", "contentPlatformId",
				"contentTitle", "bonusImageUrl", "productImageUrl");
		LOG.debug(String.format("product id: %s added to cart %s", productId, cartModel.getCode()));


		modelService.save(cartModel);
		LOG.debug("cart saved");
		LOG.debug("Got the current session.");
		checkoutCompletionFacade.submitOrder(httpSession);
		LOG.debug("Returned from the submitOrder");


		assertTrue(true);
	}
}
