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
package com.cgi.pacoshop.core.checkout.dynamic.session.impl;

import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.session.SessionCache;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.Title2Service;
import com.cgi.pacoshop.core.service.user.PacoUserService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import com.cgi.pacoshop.facades.user.data.Title2Data;
import de.hybris.platform.commercefacades.i18n.I18NFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.commerceservices.util.AbstractComparator;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeModel;
import de.hybris.platform.deliveryzone.model.ZoneDeliveryModeValueModel;
import de.hybris.platform.deliveryzone.model.ZoneModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;


/**
 * Session cache stores commonly used data per user session (e.g
 * titles, countries, currencies, etc.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @version 1.0v Mar 21, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 * @see "https://wiki.hybris.com/"
 */
public class DefaultSessionCache implements SessionCache
{
	private static Logger LOG = Logger.getLogger(DefaultSessionCache.class);
	@Resource
	private UserFacade                           userFacade;
	@Resource
	private CartFacade                           cartFacade;
	@Resource
	private Converter<CountryModel, CountryData> countryConverter;
	@Resource
	private I18NFacade                           i18NFacade;
	@Resource
	private ConfigurationService                 configurationService;
	@Resource
	private Converter<Title2Model, Title2Data>   title2Converter;
	@Resource
	private Title2Service                        title2Service;

	@Override
	public List<TitleData> getSalutationTitles()
	{
		return userFacade.getTitles();
	}

	@Override
	public List<Title2Data> getTitles2()
	{
		Collection<Title2Model> titles2 = this.title2Service.getAllTitles2();
		List<Title2Data> titles2Data = new ArrayList<>();
		titles2Data.add(new Title2Data());
		titles2Data.addAll(Converters.convertAll(titles2, title2Converter));
		return titles2Data;
	}

	@Override
	public Collection<CountryData> getCountries(final AbstractOrderModel order, final boolean filterByCountriesInOrderItems)
	{
		if (!filterByCountriesInOrderItems)
		{
			return cartFacade.getDeliveryCountries();
		}
		final Set<CountryDataWrapper> deliveryCountries = new TreeSet<>();
		final List<AbstractOrderEntryModel> entries = order.getEntries();
		if (CollectionUtils.isEmpty(entries))
		{
			return Collections.emptyList();
		}
		Iterator<AbstractOrderEntryModel> abstractOrderEntryModelIterator = entries.iterator();
		deliveryCountries.addAll(getCountryDataWrapperCollection(abstractOrderEntryModelIterator.next()));
		while (abstractOrderEntryModelIterator.hasNext())
		{
			//get intersection of countries in the order
			deliveryCountries.retainAll(getCountryDataWrapperCollection(abstractOrderEntryModelIterator.next()));
		}
		debug(LOG, "Delivery countries for order code %s: %d", order.getCode(), deliveryCountries.size());
		if (deliveryCountries.size() == 0)
		{
			error(LOG, "No delivery countries for the order code %s", order.getCode());
			deliveryCountries.add(new CountryDataWrapper(i18NFacade.getCountryForIsocode(getDefaultCountryIsoCode())));
		}

		return convertCountryDataWrapper(deliveryCountries);
	}

	@SuppressWarnings("unchecked")
	private Collection<CountryData> convertCountryDataWrapper(final Set<CountryDataWrapper> deliveryCountries)
	{
		return CollectionUtils.collect(deliveryCountries, new Transformer()
		{
			@Override
			public Object transform(final Object o)
			{
				return ((CountryDataWrapper) o).getCountryData();
			}
		});
	}

	private Collection<CountryDataWrapper> getCountryDataWrapperCollection(final AbstractOrderEntryModel orderEntryModel)
	{
		if (orderEntryModel == null || orderEntryModel.getProduct() == null)
		{
			return Collections.emptySet();
		}
		final Set<CountryDataWrapper> result = new HashSet<>();
		for (CountryData countryData : getDeliveryCountriesInOrderEntry(orderEntryModel))
		{
			result.add(new CountryDataWrapper(countryData));
		}
		return result;
	}

	private Collection<CountryData> getDeliveryCountriesInOrderEntry(final AbstractOrderEntryModel orderEntry)
	{
		final Set<CountryData> result = new HashSet<>();
		final ProductModel product = orderEntry.getProduct();
		if (product == null)
		{
			return Collections.emptyList();
		}
		for (final DeliveryModeModel deliveryModeModel : product.getDeliveryModes())
		{
			if (deliveryModeModel instanceof ZoneDeliveryModeModel)
			{
				final ZoneDeliveryModeModel modeModel = (ZoneDeliveryModeModel) deliveryModeModel;
				for (final ZoneDeliveryModeValueModel zoneDeliveryModeValueModel : modeModel.getValues())
				{
					final ZoneModel zone = zoneDeliveryModeValueModel.getZone();
					if (zone != null)
					{
						result.addAll(Converters.convertAll(zone.getCountries(),
																		countryConverter));
					}
				}
			}
		}
		debug(LOG, "delivery countries for product %s: %d", product.getCode(), result.size());
		return result;
	}


	/**
	 * Gets user facade.
	 *
	 * @return the user facade
	 */
	public UserFacade getUserFacade()
	{
		return userFacade;
	}

	/**
	 * Sets user facade.
	 *
	 * @param userFacade
	 *            the user facade
	 */
	public void setUserFacade(final UserFacade userFacade)
	{
        this.userFacade = userFacade;
    }

    /**
     * Gets cart facade.
     *
     * @return the cart facade
     */
    public CartFacade getCartFacade()
    {
        return cartFacade;
    }

    /**
     * Sets cart facade.
     *
     * @param cartFacade
     *            the cart facade
     */
    public void setCartFacade(final CartFacade cartFacade)
    {
        this.cartFacade = cartFacade;
    }

    /**
     * Gets country converter.
     *
     * @return the country converter
     */
    public Converter<CountryModel, CountryData> getCountryConverter()
    {
        return countryConverter;
    }

    /**
     * Sets country converter.
     *
     * @param countryConverter
     *            the country converter
     */
    public void setCountryConverter(final Converter<CountryModel, CountryData> countryConverter)
    {
        this.countryConverter = countryConverter;
    }

    /**
     * Gets i 18 n facade.
     *
     * @return the i 18 n facade
     */
    public I18NFacade getI18NFacade()
    {
        return i18NFacade;
    }

    /**
     * Sets i 18 n facade.
     *
     * @param i18NFacade
     *            the i 18 n facade
     */
    public void setI18NFacade(final I18NFacade i18NFacade)
    {
        this.i18NFacade = i18NFacade;
    }

    /**
     * Gets configuration service.
     *
     * @return the configuration service
     */
    public ConfigurationService getConfigurationService()
    {
        return configurationService;
    }

    /**
     * Sets configuration service.
     *
     * @param configurationService
     *            the configuration service
     */
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }

	/**
	 * Sets Title2Converter.
	 *
	 * @param title2Converter
	 *            the Title2Converter
	 */
	public void setTitle2Converter(final Converter<Title2Model, Title2Data> title2Converter)
	{
		this.title2Converter = title2Converter;
	}

	/**
	 * Sets Title2Service.
	 *
	 * @param title2Service
	 *            the PacoUserService
	 */
	public void setTitle2Service(final Title2Service title2Service)
	{
		this.title2Service = title2Service;
	}

    @Override
    public String getDefaultCountryIsoCode()
    {
        final Configuration configuration = configurationService.getConfiguration();
        assert configuration != null;
        return configuration.getString(FormElementGroupConstants.DEFAULT_COUNTRY_ISO_CODE_PROPERTY);
    }
/**
* Allows CountryData instances to be used in collections which require hashcode, equals & comparable implementation.
*/
private final static class CountryDataWrapper implements Comparable<CountryDataWrapper>
{
	private static final Comparator<CountryData> COUNTRY_COMPARATOR = new AbstractComparator<CountryData>()
	{
		@Override
		public int compareInstances(final CountryData country1, final CountryData country2)
		{
			int result = (country1.getName() != null && country2.getName() != null) ? country1.getName().compareToIgnoreCase(
					  country2.getName()) : BEFORE;
			if (EQUAL == result)
			{
				result = country1.getIsocode().compareToIgnoreCase(country2.getIsocode());
			}
			return result;
		}
	};

	private final static int BASE = 32;
	private CountryData countryData;


	private CountryDataWrapper(final CountryData countryData)
	{
		this.countryData = countryData;
	}

	public CountryData getCountryData()
	{
		return countryData;
	}


	@Override
	public boolean equals(final Object obj)
	{
		return obj instanceof CountryDataWrapper && countryData.getIsocode().equalsIgnoreCase(
				  ((CountryDataWrapper) obj).countryData.getIsocode());
	}

	@Override
	public int hashCode()
	{
		int result = countryData.getName().hashCode();
		result = BASE * result + countryData.getIsocode().hashCode();
		return result;
	}

	@Override
	public int compareTo(final CountryDataWrapper other)
	{
		return COUNTRY_COMPARATOR.compare(this.countryData, other.countryData);
	}
}


}
