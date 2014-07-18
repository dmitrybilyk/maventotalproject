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
package com.cgi.pacoshop.fulfilmentprocess.test.util;

import com.cgi.pacoshop.core.enums.FulfillmentType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.enums.ProductClass;
import com.cgi.pacoshop.core.enums.ProductGroup;
import com.cgi.pacoshop.core.enums.ProductOwner;
import com.cgi.pacoshop.core.model.ProductTypeModel;

import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateMidnight;


/**
 * Facilitate the creation of test order.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public final class TestOrderGenerator
{

    private static final Double ONEHUNDRED = new Double(100.00);

    private TestOrderGenerator()
    {
        //private constructor
    }

    /**
     * Creates a test user.
     * 
     * @param uid
     *            the uid of the user to creates.
     * @return the {@link UserModel}.
     */
    public static UserModel createTestUser(final String uid)
    {
        final UserModel user = mock(UserModel.class);
        when(user.getUid()).thenReturn("uid:user-" + uid);
        return user;
    }

    /**
     * Creates a test product.
     * 
     * @param uid
     *            the uid of the testProduct to be created.
     * @return the {@link ProductModel}.
     */
    public static ProductModel createTestProduct(final String uid)
    {
        final ProductModel product = mock(ProductModel.class);
        when(product.getExternalProductId()).thenReturn("externalProductId-" + uid);
        when(product.getExternalOfferId()).thenReturn("externalOfferId-" + uid);
        when(product.getCode()).thenReturn("productCode-" + uid);
        when(product.getName()).thenReturn("productName-" + uid);
        when(product.getName(Locale.GERMANY)).thenReturn("productName-" + uid);
        final OfferOrigin offerOrigin = mock(OfferOrigin.class);
        when(offerOrigin.getCode()).thenReturn("CRM");
        when(product.getOfferOrigin()).thenReturn(offerOrigin);
        final ProductOwner productOwner = mock(ProductOwner.class);
        when(productOwner.getCode()).thenReturn("productOwner-" + uid);
        when(product.getProductOwner()).thenReturn(productOwner);
        final ProductTypeModel productType = new ProductTypeModel();

        final ProductClass productClass = mock(ProductClass.class);
        when(productClass.getCode()).thenReturn("productClass-" + uid);
        productType.setProductClass(productClass);

		 final ProductGroup productGroup = mock(ProductGroup.class);
        when(productGroup.getCode()).thenReturn("productGroup-" + uid);
        productType.setProductGroup(productGroup);

        when(product.getProductType()).thenReturn(productType);

		 final FulfillmentType fulfillmentType = mock(FulfillmentType.class);
		 when(product.getFulfillmentType()).thenReturn(fulfillmentType);
		 when(fulfillmentType.getCode()).thenReturn("fulfilmentType-" + uid);

		 return product;
    }

    /**
     * Creates a {@link AbstractOrderEntryModel}.
     * 
     * @param uid
     *            the uid of the {@link AbstractOrderEntryModel}.
     * @return the {@link AbstractOrderEntryModel} created.
     */
    public static AbstractOrderEntryModel createTestAbstractOrderEntryModel(final String uid)
    {
        final AbstractOrderEntryModel entry = new AbstractOrderEntryModel();
        entry.setProduct(TestOrderGenerator.createTestProduct(uid));
        entry.setContentPlatformId("contentPlatformId-" + uid);
        entry.setContentId("contentId-" + uid);
        entry.setContentTitle("contentTitle-" + uid);
        entry.setCode("entry.code-" + uid);
        entry.setQuantity(Long.valueOf(1));
        entry.setTotalPrice(ONEHUNDRED);
        return entry;
    }

    /**
     * Creates a test {@link OrderModel}.
     * 
     * @param numberOfEntries
     *            the number of entries to create in the order.
     * @return the {@link OrderModel}
     */
    public static OrderModel createTestOrder(final int numberOfEntries)
    {
        final DateMidnight oneDate = new DateMidnight(1980, 8, 18);
        final OrderModel order = new OrderModel();
        order.setUser(TestOrderGenerator.createTestUser("1"));
        order.setCode("order.code-1");
        order.setDate(oneDate.toDate());
        final CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setIsocode("EUR");
        order.setCurrency(currencyModel);

        final List<AbstractOrderEntryModel> entries = new ArrayList<>();
        for (int i = 1; i <= numberOfEntries; i++)
        {
            entries.add(TestOrderGenerator.createTestAbstractOrderEntryModel(Integer.toString(i)));
        }
        order.setEntries(entries);
        return order;
    }

    /**
     * Creates a test {@link OrderModel}.
     * 
     * @param email
     *            the email in delivery address in the order.
     * @return the {@link OrderModel}
     */
    public static OrderModel createTestOrderWithDeliveryAddress(final String email)
    {
        final DateMidnight oneDate = new DateMidnight(1980, 8, 18);
        final OrderModel order = new OrderModel();
        final AddressModel deliveryAddress = new AddressModel();
        deliveryAddress.setEmail(email);
        deliveryAddress.setOwner(order);
        order.setDeliveryAddress(deliveryAddress);
        order.setUser(TestOrderGenerator.createTestUser("1"));
        order.setCode("order.code-1");
        order.setDate(oneDate.toDate());
        final CurrencyModel currencyModel = new CurrencyModel();
        currencyModel.setIsocode("EUR");
        order.setCurrency(currencyModel);

        final List<AbstractOrderEntryModel> entries = new ArrayList<>();
        entries.add(TestOrderGenerator.createTestAbstractOrderEntryModel(Integer.toString(1)));

        order.setEntries(entries);
        return order;
    }
}
