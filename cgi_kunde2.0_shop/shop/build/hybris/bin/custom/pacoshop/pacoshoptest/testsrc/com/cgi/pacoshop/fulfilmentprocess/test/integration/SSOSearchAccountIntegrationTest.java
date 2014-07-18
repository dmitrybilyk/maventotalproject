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
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.facades.checkout.dynamic.CheckoutCompletionFacade;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.impl.DefaultCatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.testframework.HybrisJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.cgi.pacoshop.facades.checkout.deeplink.DeeplinkFacade;
import com.cgi.pacoshop.facades.checkout.dynamic.DynamicCheckoutFacade;
import com.cgi.pacoshop.fulfilmentprocess.client.SSOSearchAccountClient;
import com.cgi.pacoshop.fulfilmentprocess.client.SSOSearchRestClient;
import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountRequest;
import com.cgi.pacoshop.fulfilmentprocess.model.SSOAccountResponse;


/**
 * Integration test of the SubmitCA.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@IntegrationTest
//@RunWith(MockitoJUnitRunner.class)
@RunWith(HybrisJUnit4ClassRunner.class)
public class SSOSearchAccountIntegrationTest extends AbstractServicelayerTransactionalTest
{

    private static final Logger LOG = Logger.getLogger(SSOSearchAccountIntegrationTest.class);

    private static final int TIMEOUT = 5000;


    @Resource
    private DeeplinkFacade deeplinkFacade;

    @Resource
    private ModelService modelService;

    @Resource
    private DynamicCheckoutFacade dynamicCheckoutFacade;

    @Resource
    private DefaultProductService defaultProductService;

    @Resource
    private DefaultCatalogVersionService defaultCatalogVersionService;

    @Resource
    private I18NService i18nService;

    @Resource
    private BaseSiteService baseSiteService;

    @Resource(name = "userService")
    private UserService userService;

	/*
	 * @Mock private HttpSession httpSession;
	 */

    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Resource(name = "ssoSearchAccountClient")
    private SSOSearchAccountClient ssoSearchAccountClient;

    @Resource
    private CalculationService calculationService;

    @Resource
    private CheckoutCompletionFacade checkoutCompletionFacade;

	/*
	 * @Mock private ConfigurationService configurationService;
	 */

	/*
	 * @Mock private SSOAccountPopulator ssoAccountPopulator;
	 */
	/*
	 * @Mock private SSOSearchRestClient ssoSearchRestClient;
	 */

    /**
     * Constructor.
     */
    public SSOSearchAccountIntegrationTest()
    {
        Registry.setCurrentTenant(Registry.getTenantByID("junit"));
    }

    /**
     * Setup.
     *
     * @throws Exception
     *            if something wrong happened.
     */
    @Override
    @Before
    public void importData() throws Exception
    {
        super.importData();
        i18nService.setCurrentJavaCurrency(Currency.getInstance("EUR"));
        final Configuration configuration = new BaseConfiguration();
        configuration.addProperty(PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_URL,
                                  "http://localhost:8888/SSO/getAccountId");
        configuration.addProperty(
				PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_READ_TIMEOUT,
				Integer.valueOf(TIMEOUT)
		);
        configuration.addProperty(
				PacoshopFulfilmentProcessConstants.INTERFACE.SSO_SEARCH_ACCOUNT_CONNECTION_TIMEOUT,
				Integer.valueOf(TIMEOUT)
		);

        final SSOAccountResponse ssoAccountResponse = new SSOAccountResponse();
        ssoAccountResponse.setAccountId("testAccountId");

        final ConfigurationService configurationService = mock(ConfigurationService.class);
        when(configurationService.getConfiguration()).thenReturn(configuration);

        final SSOSearchRestClient ssoSearchRestClient = mock(SSOSearchRestClient.class);
        when(ssoSearchRestClient.getUserFromSSO(any(SSOAccountRequest.class))).thenReturn(ssoAccountResponse);

    }

    /**
     * Test the success flow.
     *
     * @throws Exception
     *            if something wrong happened.
     */
    @Test
    @Ignore
    public void testGreen() throws Exception
    {
        final BaseSiteModel site = baseSiteService.getBaseSiteForUID("kunde1");
        baseSiteService.setCurrentBaseSite(site, true);
        final String productId = "product2";
        final CatalogVersionModel catalogVersionModel = defaultCatalogVersionService.getCatalogVersion("kundeProductCatalog",
                                                                                                       "Staged");
        final ProductModel productModel = defaultProductService.getProductForCode(catalogVersionModel, productId);
        final List<ProductModel> products = new ArrayList<ProductModel>();
        products.add(productModel);
        LOG.debug("Fetch product2");
        final CartModel cartModel = deeplinkFacade.createCartWithProducts(products, null, "contentId", "contentPlatformId",
                                                                          "contentTitle", "bonusImageUrl", "productImageUrl");
        LOG.debug(String.format("product id: %s added to cart %s", productId, cartModel.getCode()));

        final AddressModel deliveryAddress = new AddressModel();
        deliveryAddress.setEmail("test@email.com");
        deliveryAddress.setFirstname("TestFirstName");
        deliveryAddress.setLastname("TestLastName");
        deliveryAddress.setTitle(userService.getTitleForCode("mr"));
        deliveryAddress.setOwner(cartModel);

        cartModel.setDeliveryAddress(deliveryAddress);

        modelService.saveAll(deliveryAddress, cartModel);
        LOG.debug("cart saved");
        LOG.debug("Got the current session.");

        final HttpSession httpSession = mock(HttpSession.class);


        calculationService.calculate(cartModel);
        final OrderData orderData = checkoutCompletionFacade.submitOrder(httpSession);
        LOG.debug("Returned from the submitOrder");

        final AddressModel addressModel = getDeliveryAddressModel(orderData);

        assertTrue(addressModel.getSsoCustomerId().equals("testAccountId"));

		assertTrue(true);
	}

	private AddressModel getDeliveryAddressModel(final OrderData orderData)
	{

		final FlexibleSearchQuery flexQuery = new FlexibleSearchQuery(String.format("select {PK} FROM {Order} where {code}=")
				.concat(orderData.getCode()));
		final List<OrderModel> orders = flexibleSearchService.<OrderModel> search(flexQuery).getResult();

		if (orders == null || orders.isEmpty())
		{
			LOG.error("No Order is defined. Order.code = " + orderData.getCode());
			return null;
		}
		if (orders.size() > 1)
		{
			LOG.warn("More than one order is specified , returning the first one found.");
			final StringBuffer strbuf = new StringBuffer("Here is the list of order that has the code == " + orderData.getCode());
			for (final OrderModel current : orders)
			{
				strbuf.append(current.getCode() + ", ");
			}
			LOG.warn(strbuf.toString());
		}

		if (LOG.isDebugEnabled())
		{
			LOG.debug("Returning dummy customer[" + orders.get(0) + "]");
		}
		return orders.get(0).getDeliveryAddress();
	}
}
