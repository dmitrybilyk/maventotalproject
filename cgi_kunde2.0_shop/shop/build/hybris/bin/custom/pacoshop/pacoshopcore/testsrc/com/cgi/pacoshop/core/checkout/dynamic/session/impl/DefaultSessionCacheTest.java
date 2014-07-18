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
package com.cgi.pacoshop.core.checkout.dynamic.session.impl;


import static com.cgi.pacoshop.core.checkout.AbstractDynamicCheckoutFrameworkMockFactory.createMockCountryData;
import static com.cgi.pacoshop.core.checkout.AbstractDynamicCheckoutFrameworkMockFactory.createMockCountryModel;
import com.google.common.collect.Sets;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.user.converters.populator.CountryPopulator;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeValueModel;
import de.hybris.platform.deliveryzone.model.ZoneModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.configuration.Configuration;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for DefaultSessionCache
 *
 * @module hybris
 * @version 1.0v May 08, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@UnitTest
public class DefaultSessionCacheTest
{
	//fr@eaking quality gate & findbugs
	private final static String DEFAULT_COUNTRY = "DE";
	private DefaultSessionCache sessionCache;
	private CartFacade          cartFacade;

	/**
	 * Setup.
	 *
	 */
	@Before

	public void setUp()
	{
		ConfigurationService                                   configurationService;
		Configuration                                          configuration;
		AbstractPopulatingConverter<CountryModel, CountryData> countryConverter;
		I18NFacade          i18NFacade;
		sessionCache = new DefaultSessionCache();
		cartFacade = mock(CartFacade.class);
		i18NFacade = mock(I18NFacade.class);
		configurationService = mock(ConfigurationService.class);
		configuration = mock(Configuration.class);
		//*************************************************************************************************
		countryConverter = new AbstractPopulatingConverter<CountryModel, CountryData>()
		{
			/**
			 * Override this method to create the instance of target type.
			 *
			 * @return the new target instance
			 */
			@Override
			protected CountryData createTarget()
			{
				return new CountryData();
			}
		};
		List<Populator<CountryModel, CountryData>> countryPopulators = new ArrayList<>();
		countryPopulators.add(new CountryPopulator());
		countryConverter.setPopulators(countryPopulators);
		//**************************************************************************************************
		CountryData mockDefaultCountryData = createMockCountryData(DEFAULT_COUNTRY);
		when(i18NFacade.getCountryForIsocode(anyString())).thenReturn(mockDefaultCountryData);
		when(configuration.getString(anyString())).thenReturn(DEFAULT_COUNTRY);
		when(configurationService.getConfiguration()).thenReturn(configuration);

		sessionCache.setConfigurationService(configurationService);
		sessionCache.setCartFacade(cartFacade);
		sessionCache.setI18NFacade(i18NFacade);
		sessionCache.setCountryConverter(countryConverter);
	}

	@Test
	public void testGetCountries()
	{
		//**********************************************PREPARATION START***********************
		Set<CountryModel> countries1 =
				  Sets.newHashSet(
							 Arrays.asList(createMockCountryModel("USA"), createMockCountryModel("UK"), createMockCountryModel("FR"),
												createMockCountryModel("ZW"))
				  );
		Set<CountryModel> countries2 = Sets.newHashSet(
				  Arrays.asList(createMockCountryModel("UA"), createMockCountryModel("ZW"), createMockCountryModel("USA")));
		Set<CountryModel> countries3 = Sets.newHashSet(
				  Arrays.asList(createMockCountryModel("PO")));
		//==============================================================================
		//endregion
		//region Order
		AbstractOrderEntryModel entry1 = mock(AbstractOrderEntryModel.class);
		AbstractOrderEntryModel entry2 = mock(AbstractOrderEntryModel.class);
		AbstractOrderEntryModel entry3 = mock(AbstractOrderEntryModel.class);
		//endregion
		//region DeliveryModeModels1
		Set<DeliveryModeModel> deliveryModeModels1 = new HashSet<>();
		ZoneDeliveryModeModel zoneDeliveryModeModel1 = new ZoneDeliveryModeModel();
		Set<ZoneDeliveryModeValueModel> zoneDeliveryModeValueModels1 = new HashSet<>();
		zoneDeliveryModeModel1.setValues(zoneDeliveryModeValueModels1);
		ZoneDeliveryModeValueModel zoneDeliveryModeValueModel = new ZoneDeliveryModeValueModel();
		zoneDeliveryModeValueModels1.add(zoneDeliveryModeValueModel);
		ZoneModel zoneModel1 = new ZoneModel();
		zoneDeliveryModeValueModel.setZone(zoneModel1);
		//*COUNTRIES*START
		zoneModel1.setCountries(countries1);
		//*COUNTRIES END*
		deliveryModeModels1.add(zoneDeliveryModeModel1);
		//endregion
		//region DeliveryModeModels2
		Set<DeliveryModeModel> deliveryModeModels2 = new HashSet<>();
		ZoneDeliveryModeModel zoneDeliveryModeModel2 = new ZoneDeliveryModeModel();
		Set<ZoneDeliveryModeValueModel> zoneDeliveryModeValueModels2 = new HashSet<>();
		zoneDeliveryModeModel2.setValues(zoneDeliveryModeValueModels2);
		ZoneDeliveryModeValueModel zoneDeliveryModeValueModel2 = new ZoneDeliveryModeValueModel();
		zoneDeliveryModeValueModels2.add(zoneDeliveryModeValueModel2);
		ZoneModel zoneModel2 = new ZoneModel();
		zoneDeliveryModeValueModel2.setZone(zoneModel2);
		//*COUNTRIES*START
		zoneModel2.setCountries(countries2);
		//*COUNTRIES END*
		deliveryModeModels2.add(zoneDeliveryModeModel2);
		//endregion
		//region DeliveryModeModels3
		Set<DeliveryModeModel> deliveryModeModels3 = new HashSet<>();
		ZoneDeliveryModeModel zoneDeliveryModeModel3 = new ZoneDeliveryModeModel();
		Set<ZoneDeliveryModeValueModel> zoneDeliveryModeValueModels3 = new HashSet<>();
		zoneDeliveryModeModel3.setValues(zoneDeliveryModeValueModels3);
		ZoneDeliveryModeValueModel zoneDeliveryModeValueModel3 = new ZoneDeliveryModeValueModel();
		zoneDeliveryModeValueModels3.add(zoneDeliveryModeValueModel3);
		ZoneModel zoneModel3 = new ZoneModel();
		zoneDeliveryModeValueModel3.setZone(zoneModel3);
		//*COUNTRIES*START
		zoneModel3.setCountries(countries3);
		//*COUNTRIES END*
		deliveryModeModels3.add(zoneDeliveryModeModel3);
		//endregion
		//region Products
		ProductModel productModel1 = mock(ProductModel.class);
		when(productModel1.getDeliveryModes()).thenReturn(deliveryModeModels1);
		when(productModel1.getCode()).thenReturn("product1");
		ProductModel productModel2 = mock(ProductModel.class);
		when(productModel2.getDeliveryModes()).thenReturn(deliveryModeModels2);
		when(productModel2.getCode()).thenReturn("product2");
		ProductModel productModel3 = mock(ProductModel.class);
		when(productModel3.getDeliveryModes()).thenReturn(deliveryModeModels3);
		when(productModel3.getCode()).thenReturn("product3");
		//endregion
		//region Entries
		when(entry1.getProduct()).thenReturn(productModel1);
		when(entry2.getProduct()).thenReturn(productModel2);
		when(entry3.getProduct()).thenReturn(productModel3);
		//endregion
//**********************************************PREPARATION END*****************************************************************
		CartModel cartModelMock;
		Collection<CountryData> actualResult;
		List<AbstractOrderEntryModel> inputOrderEntries;
		//testcase 1 - products with no common delivery countries
		inputOrderEntries = new ArrayList<>();
		inputOrderEntries.add(entry1);
		inputOrderEntries.add(entry3);
		cartModelMock = createCartModelMockWithItems(inputOrderEntries);
		actualResult = sessionCache.getCountries(cartModelMock, true);
		assertTrue("should return Germany if order entries contain no countries", actualResult.size() == 1);
		assertTrue("should return Germany if order entries contain no intersecting countries",
					  actualResult.iterator().next().getIsocode().equals(DEFAULT_COUNTRY));
		// ***
		//testcase 2 - cart with no items
		cartModelMock = createCartModelMockWithItems(new ArrayList<AbstractOrderEntryModel>());
		actualResult = sessionCache.getCountries(cartModelMock, true);
		assertTrue("Cart with no products should have no delivery countries :D", actualResult.size() == 0);
		// ***
		//testcase 3 - cart with one product
		inputOrderEntries = new ArrayList<>();
		inputOrderEntries.add(entry1);
		cartModelMock = createCartModelMockWithItems(inputOrderEntries);
		actualResult = sessionCache.getCountries(cartModelMock, true);
		assertTrue("Cart with one product should contain it's delivery countries", actualResult.size() == 4);
		boolean containsUsa = CollectionUtils.exists(actualResult, new Predicate()
		{
			@Override
			public boolean evaluate(final Object o)
			{
				return ((CountryData) o).getIsocode().equalsIgnoreCase("USA");
			}

		});
		assertTrue("Cart with one product should contain it's delivery countries", containsUsa);
		// ***
		//testcase 4 - cart with 2 products
		inputOrderEntries = new ArrayList<>();
		inputOrderEntries.add(entry1);
		inputOrderEntries.add(entry2);
		cartModelMock = createCartModelMockWithItems(inputOrderEntries);
		actualResult = sessionCache.getCountries(cartModelMock, true);
		boolean containsIntersectingCountry = CollectionUtils.exists(actualResult, new Predicate()
		{
			@Override
			public boolean evaluate(final Object o)
			{
				return ((CountryData) o).getIsocode().equalsIgnoreCase("ZW");
			}
		});
		assertTrue("Cart with 2 or more products should contain only intersecting delivery countries", actualResult.size() == 2);
		assertTrue("Cart with 2 or more products should contain only intersecting delivery countries", containsIntersectingCountry);
		// ***
		//testcase 5 - get all countries
		sessionCache.getCountries(cartModelMock, false);
		Mockito.verify(cartFacade, atLeastOnce()).getDeliveryCountries();
	}

	private CartModel createCartModelMockWithItems(final List<AbstractOrderEntryModel> orderItems)
	{
		final CartModel cartModelMock = Mockito.mock(CartModel.class);
		when(cartModelMock.getEntries()).thenReturn(orderItems);
		when(cartModelMock.getCode()).thenReturn("testcode");
		return cartModelMock;
	}

}
