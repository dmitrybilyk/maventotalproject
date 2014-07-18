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
package com.cgi.pacoshop.core.service.payment.filter.impl;


import com.cgi.hybris.payment.core.model.PaymentMethodModel;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.payment.filters.PacoshopPaymentFilter;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.Arrays;
import java.util.Collections;
import org.apache.commons.configuration.Configuration;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link PacoshopPaymentFilter} class.
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Mar 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@UnitTest
public class PacoshopPaymentFilterTest
{
	private static final String                   GOOD_PRODUCT_TYPE          = "good";
	private static final String                   DIGITAL_ABO_PRODUCT_TYPE   = "DIGITAL ABO";
	private static final String                   PRINT_ABO_PRODUCT_TYPE     = "PRINT ABO";
	private static final String                   PAYPAL_PAYMENT_MODE        = "paypal";
	private static final String                   PAYONDELIVERY_PAYMENT_MODE = "payondelivery";
	private static final String                   DIRECTDEBIT_PAYMENT_MODE   = "directdebit";
	private static final String                   GIROPAY_PAYMENT_MODE       = "giropay";
	private static final String                   INVOICE_PAYMENT_MODE       = "invoice";
	private static       ConfigurationService     CONFIGURATIONSERVICE       = mock(ConfigurationService.class);
	private static       Configuration            CONFIGURATION              = mock(Configuration.class);
	private              ProductModel             singleProduct1             = mock(ProductModel.class);
	private              ProductModel             singleProduct2             = mock(ProductModel.class);
	private              SubscriptionProductModel periodicProduct1           = mock(SubscriptionProductModel.class);
	private              SubscriptionProductModel             periodicProduct2           = mock(SubscriptionProductModel.class);
	private              ProductTypeModel         goodProductType            = mock(ProductTypeModel.class);
	private              ProductTypeModel         digitalAboProductType      = mock(ProductTypeModel.class);
	private              ProductTypeModel         printAboProductType        = mock(ProductTypeModel.class);
	private              CartModel                cart                       = mock(CartModel.class);
	private              AbstractOrderEntryModel  entryModelSingleProduct1   = mock(AbstractOrderEntryModel.class);
	private              AbstractOrderEntryModel  entryModelSingleProduct2   = mock(AbstractOrderEntryModel.class);
	private              AbstractOrderEntryModel  entryModelPeriodicProduct1 = mock(AbstractOrderEntryModel.class);
	private              AbstractOrderEntryModel  entryModelPeriodicProduct2 = mock(AbstractOrderEntryModel.class);
	private              PaymentMethodModel       paymentMethodPaypal        = mock(PaymentMethodModel.class);
	private              PaymentMethodModel       paymentMethodPayOnDelivery = mock(PaymentMethodModel.class);
	private              PaymentMethodModel       paymentMethodDirectDebit   = mock(PaymentMethodModel.class);
	private              PaymentMethodModel       paymentMethodInvoice       = mock(PaymentMethodModel.class);
	private              PaymentMethodModel       paymentMethodGiropay       = mock(PaymentMethodModel.class);
	private              PaymentTypeModel         paymentTypePaypal          = mock(PaymentTypeModel.class);
	private              PaymentTypeModel         paymentTypePayOnDelivery   = mock(PaymentTypeModel.class);
	private              PaymentTypeModel         paymentTypeDirectDebit     = mock(PaymentTypeModel.class);
	private              PaymentTypeModel         paymentTypeInvoice         = mock(PaymentTypeModel.class);
	private              PaymentTypeModel         paymentTypeGiropay         = mock(PaymentTypeModel.class);
	private              PaymentModeModel         paymentModePaypal          = mock(PaymentModeModel.class);
	private              PaymentModeModel         paymentModePayOnDelivery   = mock(PaymentModeModel.class);
	private              PaymentModeModel         paymentModeDirectDebit     = mock(PaymentModeModel.class);
	private              PaymentModeModel         paymentModeInvoice         = mock(PaymentModeModel.class);
	private              PaymentModeModel         paymentModeGiropay         = mock(PaymentModeModel.class);
	private              PacoshopPaymentFilter    pacoFilter                 = new PacoshopPaymentFilter();

	/**
	 *
	 * @throws Exception if Exception occurs
	 */
	@Before
	public void setUp() throws Exception
	{
		when(CONFIGURATIONSERVICE.getConfiguration()).thenReturn(CONFIGURATION);
		when(CONFIGURATION.getString(FormElementGroupConstants.GOOD)).thenReturn(GOOD_PRODUCT_TYPE);

		pacoFilter.setConfigurationService(CONFIGURATIONSERVICE);

		when(entryModelSingleProduct1.getProduct()).thenReturn(singleProduct1);
		when(entryModelSingleProduct2.getProduct()).thenReturn(singleProduct2);
		when(entryModelPeriodicProduct1.getProduct()).thenReturn(periodicProduct1);
		when(entryModelPeriodicProduct2.getProduct()).thenReturn(periodicProduct2);

		when(singleProduct1.getProductType()).thenReturn(goodProductType);
		when(singleProduct2.getProductType()).thenReturn(goodProductType);

		when(periodicProduct1.getProductType()).thenReturn(printAboProductType);
		when(periodicProduct2.getProductType()).thenReturn(digitalAboProductType);

		when(goodProductType.getCode()).thenReturn(GOOD_PRODUCT_TYPE);
		when(goodProductType.getPaymentTypes()).thenReturn(Arrays.asList(paymentTypePaypal, paymentTypePayOnDelivery));

		when(printAboProductType.getCode()).thenReturn(PRINT_ABO_PRODUCT_TYPE);
		when(printAboProductType.getPaymentTypes()).thenReturn(
				  Arrays.asList(paymentTypePaypal, paymentTypeGiropay, paymentTypeInvoice, paymentTypeDirectDebit));

		when(digitalAboProductType.getCode()).thenReturn(DIGITAL_ABO_PRODUCT_TYPE);
		when(digitalAboProductType.getPaymentTypes()).thenReturn(
				  Arrays.asList(paymentTypePaypal, paymentTypeGiropay, paymentTypeInvoice, paymentTypePayOnDelivery));

		when(paymentTypePaypal.getPaymentMode()).thenReturn(paymentModePaypal);
		when(paymentTypeDirectDebit.getPaymentMode()).thenReturn(paymentModeDirectDebit);
		when(paymentTypePayOnDelivery.getPaymentMode()).thenReturn(paymentModePayOnDelivery);
		when(paymentTypeInvoice.getPaymentMode()).thenReturn(paymentModeInvoice);
		when(paymentTypeGiropay.getPaymentMode()).thenReturn(paymentModeGiropay);

		when(paymentMethodPaypal.getMode()).thenReturn(paymentModePaypal);
		when(paymentMethodDirectDebit.getMode()).thenReturn(paymentModeDirectDebit);
		when(paymentMethodPayOnDelivery.getMode()).thenReturn(paymentModePayOnDelivery);
		when(paymentMethodInvoice.getMode()).thenReturn(paymentModeInvoice);
		when(paymentMethodGiropay.getMode()).thenReturn(paymentModeGiropay);

		when(paymentModePaypal.getCode()).thenReturn(PAYPAL_PAYMENT_MODE);
		when(paymentModeDirectDebit.getCode()).thenReturn(DIRECTDEBIT_PAYMENT_MODE);
		when(paymentModePayOnDelivery.getCode()).thenReturn(PAYONDELIVERY_PAYMENT_MODE);
		when(paymentModeInvoice.getCode()).thenReturn(INVOICE_PAYMENT_MODE);
		when(paymentModeGiropay.getCode()).thenReturn(GIROPAY_PAYMENT_MODE);
	}

	/**
	 * Test filter for one single product.
	 */
	@Test
	public void getAllowedPaymentTypesForSingleProduct()
	{
		when(cart.getEntries()).thenReturn(Arrays.asList(entryModelSingleProduct1));
		when(cart.getAllowedPaymentType()).thenReturn(Arrays.asList(paymentTypeDirectDebit, paymentTypePaypal));

		assertFalse(pacoFilter.isAllowed(paymentMethodDirectDebit, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodPaypal, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodPayOnDelivery, cart));
	}

	/**
	 * Test filter for two physical products.
	 */
	@Test
	public void getAllowedPaymentTypesForFewPhysicalProductsTest()
	{
		when(cart.getEntries()).thenReturn(Arrays.asList(entryModelSingleProduct1, entryModelSingleProduct2));
		when(cart.getAllowedPaymentType()).thenReturn(Arrays.asList(paymentTypeDirectDebit, paymentTypePaypal));

		assertFalse(pacoFilter.isAllowed(paymentMethodDirectDebit, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodPaypal, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodPayOnDelivery, cart));
	}

	/**
	 * Test filter for one periodic product with no payment types on product level.
	 */
	@Test
	public void getAllowedPaymentTypesForOneSubscriptionProductWithEmptyProductPayments()
	{
		when(cart.getEntries()).thenReturn(Arrays.asList(entryModelPeriodicProduct1));
		when(cart.getAllowedPaymentType()).thenReturn(Arrays.asList(paymentTypePayOnDelivery, paymentTypePaypal));
		when(periodicProduct1.getPaymentTypes()).thenReturn(Collections.EMPTY_LIST);

		assertTrue(pacoFilter.isAllowed(paymentMethodPaypal, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodGiropay, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodInvoice, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodDirectDebit, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodPayOnDelivery, cart));
	}

	/**
	 * Test filter for one periodic product with not empty payment types on product level.
	 */
	@Test
	public void getAllowedPaymentTypesForOneSubscriptionProductWithNotEmptyProductPayments()
	{
		when(cart.getEntries()).thenReturn(Arrays.asList(entryModelPeriodicProduct1));
		when(cart.getAllowedPaymentType()).thenReturn(Arrays.asList(paymentTypePayOnDelivery, paymentTypePaypal));
		when(periodicProduct1.getPaymentTypes()).thenReturn(Arrays.asList(paymentTypeGiropay, paymentTypeInvoice));

		assertFalse(pacoFilter.isAllowed(paymentMethodPaypal, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodGiropay, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodInvoice, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodDirectDebit, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodPayOnDelivery, cart));
	}

	/**
	 * Test filter for two periodic products with no payment types on product level.
	 */
	@Test
	public void getAllowedPaymentTypesForTwoSubscriptionProductsWithEmptyProductPayments()
	{
		when(cart.getEntries()).thenReturn(Arrays.asList(entryModelPeriodicProduct1, entryModelPeriodicProduct2));
		when(cart.getAllowedPaymentType()).thenReturn(Arrays.asList(paymentTypePayOnDelivery, paymentTypePaypal));
		when(periodicProduct1.getPaymentTypes()).thenReturn(Collections.EMPTY_LIST);
		when(periodicProduct2.getPaymentTypes()).thenReturn(Collections.EMPTY_LIST);

		assertTrue(pacoFilter.isAllowed(paymentMethodPaypal, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodGiropay, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodInvoice, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodDirectDebit, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodPayOnDelivery, cart));
	}

	/**
	 * Test filter for two periodic products with not empty payment types on product level.
	 */
	@Test
	public void getAllowedPaymentTypesForTwoSubscriptionProductsWithNotEmptyProductPayments()
	{
		when(cart.getEntries()).thenReturn(Arrays.asList(entryModelPeriodicProduct1));
		when(cart.getAllowedPaymentType()).thenReturn(Arrays.asList(paymentTypePayOnDelivery, paymentTypePaypal));
		when(periodicProduct1.getPaymentTypes()).thenReturn(
				  Arrays.asList(paymentTypeGiropay, paymentTypeInvoice, paymentTypePayOnDelivery));
		when(periodicProduct2.getPaymentTypes()).thenReturn(
				  Arrays.asList(paymentTypeGiropay, paymentTypeInvoice, paymentTypeDirectDebit));

		assertFalse(pacoFilter.isAllowed(paymentMethodPaypal, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodGiropay, cart));
		assertTrue(pacoFilter.isAllowed(paymentMethodInvoice, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodDirectDebit, cart));
		assertFalse(pacoFilter.isAllowed(paymentMethodPayOnDelivery, cart));
	}
}
