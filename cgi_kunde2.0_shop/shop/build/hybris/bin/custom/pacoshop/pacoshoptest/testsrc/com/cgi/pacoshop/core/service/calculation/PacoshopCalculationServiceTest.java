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
package com.cgi.pacoshop.core.service.calculation;


import com.cgi.pacoshop.AbstractServicelayerTransactionalTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.InvoicePaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.order.CalculationService;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.exceptions.CalculationException;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collections;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.cgi.hybris.payment.core.model.PayOnDeliveryPaymentInfoModel;
import com.cgi.hybris.payment.core.model.PaymentMethodModel;
import com.cgi.hybris.payment.core.services.PaymentExtService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;


/**
 * Test class for {@link com.cgi.pacoshop.core.service.order.PacoshopCalculationService}
 * 
 * @version 1.0v Mar 06, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * 
 * 
 */
@IntegrationTest
public class PacoshopCalculationServiceTest extends AbstractServicelayerTransactionalTest
{

    private ProductModel productA, productB;

    private CartModel cartModel;

    private CurrencyModel curr;

    @Resource
    private CalculationService calculationService;

    @Resource
    private CommerceCartService commerceCartService;

    @Resource
    private ProductService productService;

    @Resource
    private ModelService modelService;

    @Resource
    private CommonI18NService commonI18NService;

    @Resource
    private UserService userService;

    @Resource
    private CartService cartService;

    @Resource
    private BaseStoreService baseStoreService;

    @Resource
    private CommerceCheckoutService commerceCheckoutService;

    @Resource
    private PaymentExtService paymentExtService;

    @Resource
    private transient ShopConfigurationService shopConfigurationService;

    /**
     * Setup the test.
     * 
     * @throws Exception
     *             thrown on failure
     * 
     */
    @Before
    public void setUp() throws Exception
    {

        createCoreData();
		  super.importData();
        importCsv("/pacoshoptest/import/testPacoshopCalculationService.impex", "utf-8");

        productA = productService.getProductForCode("pA");
        productB = productService.getProductForCode("pB");

        final CustomerModel customerModel = modelService.create(CustomerModel.class);
        customerModel.setUid("testCustomer");
        customerModel.setGroups(Collections.singleton((PrincipalGroupModel) userService.getUserGroupForUID("customergroup")));
        customerModel.setName("test Customer");
        customerModel.setCustomerID("testCustomerID");
        customerModel.setLoginDisabled(false);

        curr = commonI18NService.getCurrency("EUR");

        cartModel = modelService.create(CartModel.class);
        cartModel.setCode("cart calc test");
        cartModel.setUser(customerModel);
        cartModel.setCurrency(curr);
        cartModel.setDate(new Date());
        cartModel.setNet(Boolean.FALSE);

        final BaseStoreModel baseStore = baseStoreService.getBaseStoreForUid("kunde1");
        cartModel.setStore(baseStore);

        modelService.saveAll(customerModel, cartModel);
    }

    /**
     * Test calculation with delivery cost.
     * 
     * @throws CalculationException
     *             thrown on failure
     */
    @Test
    public void testCalculateCartWithDeliveryCost() throws CalculationException
    {
        final double deliveryCost = 77.99;
        final double productAPrice = productA.getEurope1Prices().iterator().next().getPrice();
        cartModel.setDeliveryCost(deliveryCost);
        cartService.addNewEntry(cartModel, productA, 1, productA.getUnit());
        modelService.save(cartModel);
        calculationService.calculate(cartModel);
        assertTrue(cartModel.getCalculated().booleanValue());
        assertEquals(deliveryCost + productAPrice, cartModel.getTotalPrice(), 0.001);
        assertEquals(deliveryCost, cartModel.getDeliveryCost(), 0.0001);

        final double zeroDeliveryCost = 0;
        final double productBPrice = productB.getEurope1Prices().iterator().next().getPrice();
        cartModel.setDeliveryCost(zeroDeliveryCost);
        cartService.addNewEntry(cartModel, productB, 1, productB.getUnit());
        modelService.save(cartModel);
        calculationService.recalculate(cartModel);
        assertEquals(productBPrice + productAPrice, cartModel.getTotalPrice(), 0.001);
        assertEquals(zeroDeliveryCost, cartModel.getDeliveryCost(), 0.0001);

        // payment cost should be 0
        assertEquals(0, cartModel.getPaymentCost(), 0.0001);

    }

    /**
     * Tests cart with delivery cost in commerce cart.
     * 
     * @throws CalculationException
     *             thrown on failure
     */
    @Test
    public void testCalculateCartWithDeliveryCostCommerceCart() throws CalculationException
    {
        final double deliveryCost = 77.99;
        final double productAPrice = productA.getEurope1Prices().iterator().next().getPrice();
        cartModel.setDeliveryCost(deliveryCost);
        cartService.addNewEntry(cartModel, productA, 1, productA.getUnit());
        modelService.save(cartModel);
        commerceCartService.calculateCart(cartModel);
        assertTrue(cartModel.getCalculated().booleanValue());
        assertEquals(deliveryCost + productAPrice, cartModel.getTotalPrice(), 0.01);
        assertEquals(deliveryCost, cartModel.getDeliveryCost(), 0.0001);

        final double zeroDeliveryCost = 0;
        final double productBPrice = productB.getEurope1Prices().iterator().next().getPrice();
        cartModel.setDeliveryCost(zeroDeliveryCost);
        cartService.addNewEntry(cartModel, productB, 1, productB.getUnit());
        modelService.save(cartModel);
        commerceCartService.recalculateCart(cartModel);
        assertEquals(productBPrice + productAPrice, cartModel.getTotalPrice(), 0.001);
        assertEquals(zeroDeliveryCost, cartModel.getDeliveryCost(), 0.0001);

        // payment cost should be 0
        assertEquals(0, cartModel.getPaymentCost(), 0.0001);
    }

    /**
     * Test calculation tax for delivery/shipping cost.
     * 
     * @throws CalculationException
     *             thrown on failure
     */
    @Test
    public void testCalculateTaxForDeliveryCost() throws CalculationException
    {
        final int roundDigit = 2;
        final double deliveryCost = 77.99;
        final double productAPrice = productA.getEurope1Prices().iterator().next().getPrice();
        cartModel.setDeliveryCost(deliveryCost);
        cartService.addNewEntry(cartModel, productA, 1, productA.getUnit());
        modelService.save(cartModel);
        calculationService.calculate(cartModel);

        final double productAVatRate = productA.getVatGroup().getVatRate();
        double productATax = productAPrice - (productAPrice * 100) / (100 + productAVatRate);
        productATax = CoreAlgorithms.round(productATax, Math.max(roundDigit, 0));

        final double deliveryAVatRate = shopConfigurationService.getDeliveryVatRate();
        double deliveryATax = deliveryCost - (deliveryCost * 100) / (100 + deliveryAVatRate);
        deliveryATax = CoreAlgorithms.round(deliveryATax, Math.max(roundDigit, 0));

        assertTrue(cartModel.getCalculated().booleanValue());
        assertEquals(deliveryATax + productATax, cartModel.getTotalTax(), 0.0001);

        // payment cost should be 0
        assertEquals(0, cartModel.getPaymentCost(), 0.0001);
    }


    /**
     * Tests cart with delivery cost in commerce cart.
     * 
     * @throws CalculationException
     *             thrown on failure
     */
    @Test
    public void testCalculateTaxForDeliveryCostCommerceCart() throws CalculationException
    {
        final int roundDigit = 2;
        final double deliveryCost = 77.99;
        final double productAPrice = productA.getEurope1Prices().iterator().next().getPrice();
        cartModel.setDeliveryCost(deliveryCost);
        cartService.addNewEntry(cartModel, productA, 1, productA.getUnit());
        modelService.save(cartModel);
        commerceCartService.calculateCart(cartModel);

        final double productAVatRate = productA.getVatGroup().getVatRate();
        double productATax = productAPrice - (productAPrice * 100) / (100 + productAVatRate);
        productATax = CoreAlgorithms.round(productATax, Math.max(roundDigit, 0));

        final double deliveryAVatRate = shopConfigurationService.getDeliveryVatRate();
        double deliveryATax = deliveryCost - (deliveryCost * 100) / (100 + deliveryAVatRate);
        deliveryATax = CoreAlgorithms.round(deliveryATax, Math.max(roundDigit, 0));

        assertTrue(cartModel.getCalculated().booleanValue());
        assertEquals(deliveryATax + productATax, cartModel.getTotalTax(), 0.0001);

        // payment cost should be 0
        assertEquals(0, cartModel.getPaymentCost(), 0.0001);
    }


    /**
     * Tests that payment fees are correctly determined and added in the cart calculation.
     * 
     * @throws CalculationException
     */
    @Test
    public void testPaymentFeeCalculation() throws CalculationException
    {
        final double deliveryCost = 6.5;
        final double payOnDeliveryFee = 0.0; // configured in system importData impex files for site kunde1
        final double productAPrice = productA.getEurope1Prices().iterator().next().getPrice();
        final double orderTotal = productAPrice + deliveryCost;

        cartModel.setDeliveryCost(deliveryCost);
        cartService.addNewEntry(cartModel, productA, 1, productA.getUnit());
        modelService.save(cartModel);

        commerceCartService.calculateCart(cartModel);
        assertTrue(cartModel.getCalculated().booleanValue());

        assertEquals(orderTotal, cartModel.getTotalPrice(), 0.01);
        assertEquals(deliveryCost, cartModel.getDeliveryCost(), 0.0001);
        assertEquals(0, cartModel.getPaymentCost(), 0.0001);

        // Add a payment info for "pay on delivery" --> should result in 5 EUR payment fee added
        PaymentInfoModel paymentInfo = modelService.create(PayOnDeliveryPaymentInfoModel.class);
        PaymentMethodModel paymentMethod = paymentExtService.getPaymentMethodModelByCode("internal_payondelivery");
        paymentInfo.setPaymentMethod(paymentMethod);
        paymentInfo.setUser(cartModel.getUser());
        paymentInfo.setCode("payondelivery_" + System.nanoTime());
        modelService.save(paymentInfo);
        commerceCheckoutService.setPaymentInfo(cartModel, paymentInfo);

        commerceCartService.recalculateCart(cartModel);

        // check paymentCost and order total
        assertEquals(payOnDeliveryFee, cartModel.getPaymentCost(), 0.0001);
        assertEquals(orderTotal + payOnDeliveryFee, cartModel.getTotalPrice(), 0.0001);


        // Replace the pay on delivery with another payment method and recalculate --> payment fee should be 0 again
        paymentInfo = modelService.create(InvoicePaymentInfoModel.class);
        paymentMethod = paymentExtService.getPaymentMethodModelByCode("internal_invoice");
        paymentInfo.setPaymentMethod(paymentMethod);
        paymentInfo.setUser(cartModel.getUser());
        paymentInfo.setCode("invoice_" + System.nanoTime());
        modelService.save(paymentInfo);
        commerceCheckoutService.setPaymentInfo(cartModel, paymentInfo);

        commerceCartService.recalculateCart(cartModel);

        // payment fee should now be 0 again
        assertEquals(0, cartModel.getPaymentCost(), 0.001);
        assertEquals(orderTotal, cartModel.getTotalPrice(), 0.001);
    }

}
