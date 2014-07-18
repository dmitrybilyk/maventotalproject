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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.session.SessionCache;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import com.cgi.pacoshop.facades.user.data.Title2Data;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import static org.apache.commons.lang.StringUtils.isEmpty;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.warn;
import org.springframework.util.Assert;


/**
 * FormElementGroup
 * Represents logically grouped input fields in JSP page.
 * Examples: addressFormElement, paymentInformationFormElement
 *
 * @module hybris - pacoshopacoshopcore
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public abstract class AbstractAddressAwareFormElementGroup extends AbstractFormElementGroup
{
	private static final Logger LOG = Logger.getLogger(AbstractAddressAwareFormElementGroup.class);

	private static final String COUNTRIES = "countries";
	private static final String TITLES    = "titles";
	private static final String TITLES2   = "titles2";

	private static final Set<String> ADDRESS_MANDATORY_FIELD_NAMES =
			  Collections.unmodifiableSet(Sets.newHashSet(FormElementGroupConstants.SALUTATION,
																		 FormElementGroupConstants.FIRST_NAME,
																		 FormElementGroupConstants.LAST_NAME,
																		 FormElementGroupConstants.STREET,
																		 FormElementGroupConstants.HOUSE_NUMBER,
																		 FormElementGroupConstants.PLZ_ZIP,
																		 FormElementGroupConstants.CITY_ORT,
																		 FormElementGroupConstants.COUNTRY,
																		 FormElementGroupConstants.EMAIL));

	@Resource
	private SessionCache sessionCache;

	/**
	 * Check for mandatory properties in products.
	 * note: for now, address fields are  always mandatory
	 * @param cart - session cart.
	 * @return true /false.
	 */
	protected List<String> getProductSpecificMandatoryFields(final CartModel cart)
	{
		List<AbstractOrderEntryModel> entries = cart.getEntries();
		List<String> productSpecificMandatoryFields = new ArrayList<>();

		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (productModel instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) productModel;
				if (subscriptionProductModel.getMandatoryAddress())
				{
					productSpecificMandatoryFields.addAll(getAddressMandatoryFieldNames());
				}
				if (subscriptionProductModel.getMandatoryPhone())
				{
					productSpecificMandatoryFields.add(FormElementGroupConstants.HOME_PHONE_NUMBER);
					productSpecificMandatoryFields.add(FormElementGroupConstants.BUSINESS_PHONE_NUMBER);
				}
				if (subscriptionProductModel.getMandatoryMobile())
				{
					productSpecificMandatoryFields.add(FormElementGroupConstants.MOBILE_PHONE_NUMBER);
				}
				if (subscriptionProductModel.getMandatoryEmail())
				{
					productSpecificMandatoryFields.add(FormElementGroupConstants.EMAIL);
				}
				if (subscriptionProductModel.getMandatoryOptIn())
				{
					productSpecificMandatoryFields.add(FormElementGroupConstants.OPTIN);
				}
			}
		}
		return productSpecificMandatoryFields;
	}

	/**
	 * Gets unmodifiable set of address mandatory field names.
	 *
	 * @return the list of fields that are always mandatory for guter, print abo and digital abo products
	 */
	protected Set<String> getAddressMandatoryFieldNames()
	{
		return ADDRESS_MANDATORY_FIELD_NAMES;
	}

	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		super.populatePageModelData(cartModel);

		Map<String, Object> result = Maps.newHashMap();

		Collection<CountryData> deliveryCountries = getDeliveryCountries(cartModel);
		result.put(COUNTRIES, deliveryCountries);
		result.put(TITLES, getSalutationTitles());
		result.put(TITLES2, getTitles2());

		return result;
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
		super.populateFormFromCart(dto, cart);
		final String defaultCountryIsoCode = sessionCache.getDefaultCountryIsoCode();
		//preselect countries only on customerdata and delivery pages
		if (CheckoutFormDTO.class.isInstance(dto) || ShipmentInfoFormDTO.class.isInstance(dto))
		{
			try
			{
				preselectDeliveryCountriesDTO((CheckoutFormDTO) dto,
														getDeliveryCountryIsoCodeToPreselect(cart, defaultCountryIsoCode));
				preselectAllCountriesDTO((CheckoutFormDTO) dto, defaultCountryIsoCode);
			}
			catch (Exception e)
			{
				error(LOG, "Unable to set default country");
			}
		}
	}

	/**
	 * Return Germany, if present in delivery country list. Otherwise return the first in the list
	 * @param cart
	 * @param defaultCountryIsoCode
	 */
	private String getDeliveryCountryIsoCodeToPreselect(final CartModel cart, final String defaultCountryIsoCode)
	{
		final Collection<CountryData> deliveryCountries = getDeliveryCountries(cart);
		Assert.isTrue(defaultCountryIsoCode.length() > 0);
		final Object defaultCountryFound = CollectionUtils.find(deliveryCountries, new Predicate()
		{
			@Override
			public boolean evaluate(final Object o)
			{
				return ((CountryData) o).getIsocode().equalsIgnoreCase(defaultCountryIsoCode);
			}
		});
		return defaultCountryFound == null ? (deliveryCountries.iterator().next()).getIsocode() : defaultCountryIsoCode;
	}

	/**
	 * Preselect the country in customerdata and shipment pages based on the following:
	 * CustomerDataPage: delivery country  - Germany, if present in the list, is preselected.
	 * DeliveryPage: separate delivery country  - Germany, if present in the list, is preselected.
	 * @param dto the dto
	 * @param countryIsoCode the country to preselect
	 * @link https ://jira.symmetrics.de/browse/KS-364
	 */
	protected void preselectDeliveryCountriesDTO(final CheckoutFormDTO dto, final String countryIsoCode)
	{
		Assert.notNull(countryIsoCode);
		Assert.notNull(dto);
		if (isEmpty(dto.getCountry()))
		{
			dto.setCountry(countryIsoCode);
		}
		if (dto instanceof ShipmentInfoFormDTO)
		{
			if (isEmpty(((ShipmentInfoFormDTO) dto).getNewShipmentCountry()))
			{
				((ShipmentInfoFormDTO) dto).setNewShipmentCountry(countryIsoCode);
			}
		}
	}

	/**
	 * Preselect the country in customerdata and shipment pages based on the following:
	 * CustomerDataPage: referral country  - Germany, if present in the list, is preselected.
	 * DeliveryPage: separate invoice country - preselect  Germany
	 * @param dto the dto
	 * @param countryIsoCode the country to preselect
	 * @link https ://jira.symmetrics.de/browse/KS-364
	 */
	protected void preselectAllCountriesDTO(final CheckoutFormDTO dto, final String countryIsoCode)
	{
		Assert.notNull(countryIsoCode);
		Assert.notNull(dto);
		if (isEmpty(dto.getReferralCountry()))
		{
			dto.setReferralCountry(countryIsoCode);
		}
		if (dto instanceof ShipmentInfoFormDTO)
		{
			if (isEmpty(((ShipmentInfoFormDTO) dto).getBillingCountry()))
			{
				((ShipmentInfoFormDTO) dto).setBillingCountry(countryIsoCode);
			}
		}
	}

	/**
	 * Salutation titles.
	 *
	 * @return Salutation titles
	 */
	protected List<TitleData> getSalutationTitles()
    {
        return sessionCache.getSalutationTitles();
    }

	/**
	 * Title2 titles.
	 *
	 * @return Salutation titles
	 */
	protected List<Title2Data> getTitles2()
	{
		return sessionCache.getTitles2();
	}

	/**
	 * Is customer address in delivery countries.
	 *
	 * @param cart the cart
	 * @return the boolean
	 */
	protected boolean isCustomerAddressInDeliveryCountries(final CartModel cart)
	{
		final AddressModel customerAddress = cart.getCustomerAddress();
		if (customerAddress == null || customerAddress.getCountry() == null)
		{
			debug(LOG, "Customer address is not yet prefilled");
			//the result of this method is ignored by returning true
			//in case of the first call when customeraddress is not yet in the cart
			return true;
		}
		Object found = CollectionUtils.find(getDeliveryCountries(cart), new Predicate()
														{
															@Override
															public boolean evaluate(final Object o)
															{
																return ((CountryData) o).getIsocode().equalsIgnoreCase(
																		  customerAddress.getCountry().getIsocode());
															}
														}
		);
		boolean result = found != null;
		if (!result)
		{
			warn(LOG, "Customer country: %s. Found in delivery countries: %b.", customerAddress.getCountry().getIsocode(), result);
		}
		return result;
	}

	/**
	 * Gets delivery countries.
	 *
	 * @param cartModel the cart model
	 * @return delivery countries
	 */
	protected Collection<CountryData> getDeliveryCountries(final CartModel cartModel)
    {
        return sessionCache.getCountries(cartModel, true);
    }

	/**
	 * Gets all countries.
	 *
	 * @param cartModel the cart model
	 * @return the all countries
	 */
	protected Collection<CountryData> getAllCountries(final CartModel cartModel)
    {
        return sessionCache.getCountries(cartModel, false);
    }
}
