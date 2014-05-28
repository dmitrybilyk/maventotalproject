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
package com.learn.formgroups;

//import com.cgi.pacoshop.core.checkout.dynamic.session.SessionCache;
//import com.cgi.pacoshop.core.service.FormValidationService;
//import com.cgi.pacoshop.core.service.PacoshopCartService;
import com.google.common.collect.Maps;
//import de.hybris.platform.commercefacades.user.data.CountryData;
//import de.hybris.platform.commercefacades.user.data.TitleData;
//import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
//import de.hybris.platform.core.model.product.ProductModel;
//import de.hybris.platform.servicelayer.config.ConfigurationService;
//import de.hybris.platform.servicelayer.model.ModelService;
//import de.hybris.platform.servicelayer.user.AddressService;
//import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
//import org.apache.commons.configuration.Configuration;
//import org.apache.log4j.Logger;
import org.apache.commons.configuration.Configuration;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import java.util.*;


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
public abstract class AbstractFormElementGroup implements FormElementGroup
{

//	private static final Logger LOG = Logger.getLogger(AbstractFormElementGroup.class);
	private static final String TITLES               = "titles";


	private static final List<String> ADDRESS_MANDATORY_FIELD_NAMES =
			  Arrays.asList(FormElementGroupConstants.SALUTATION,
								 FormElementGroupConstants.FIRST_NAME,
								 FormElementGroupConstants.LAST_NAME,
								 FormElementGroupConstants.STREET_AND_NUMBER,
								 FormElementGroupConstants.PLZ_ZIP,
								 FormElementGroupConstants.CITY_ORT,
								 FormElementGroupConstants.COUNTRY,
								 FormElementGroupConstants.EMAIL);

//	@Resource
//	private SessionCache sessionCache;
//
//	@Resource
//	private FormValidationService formValidationService;
//
//	@Resource
//	private PacoshopCartService pacoshopCartService;
//
//	@Resource
//	private ConfigurationService configurationService;
//
//	@Resource
//	private ModelService modelService;
//
//	@Resource
//	private AddressService addressService;

	/**
	 * Gets session cache.
	 *
	 * @return the session cache
	 */
//	public SessionCache getSessionCache()
//	{
//		return sessionCache;
//	}

	/**
	 * Sets session cache.
	 *
	 * @param sessionCache the session cache
	 */
//	public void setSessionCache(final SessionCache sessionCache)
//	{
//		this.sessionCache = sessionCache;
//	}




	/**
	 * Gets log.
	 *
	 * @return the log
	 */
//	public static Logger getLog()
//	{
//		return LOG;
//	}

	/**
	 * Gets address service.
	 *
	 * @return the address service
	 */
//	public AddressService getAddressService()
//	{
//		return addressService;
//	}

	/**
	 * Getter for configuration properties.

	 * @param key - property name.
	 * @return  - property value in configuration config.
	 */
	protected String getConfigurationProperty(final String key)
	{
        return key;
//		final Configuration configuration = configurationService.getConfiguration();
//		return configuration.getString(key);
	}

	/**
	 * ValidationService getter.
	 * @return  - ValidationService.
	 */
//	protected FormValidationService getValidationService()
//	{
//		return formValidationService;
//	}

	/**
	 * Copy information from customer profile, which is either updated from SSO, or taken from hybris.
	 * The information Should be copied ONLY ONCE!!!!
	 *
	 * @param cart - cart object linked to a current session.
	 * @param customerModel - Customer Data.
	 */
	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart,
															 final CustomerModel customerModel)
	{

	}

	/**
	 * CartService getter.
	 * @return CartService. cart service
	 */
//	protected PacoshopCartService getCartService()
//	{
//		return pacoshopCartService;
//	}

	/**
	 * ModelService getter.
	 * @return ModelService. model service
	 */
//	public ModelService getModelService()
//	{
//		return modelService;
//	}

	/**
	 * Check for mandatory properties in products.
	 * note: for now, address fields are  always mandatory
	 * @param cart - session cart.
	 * @return true /false.
	 */
	protected List<String> getProductSpecificMandatoryFields(final CartModel cart)
	{
//		List<AbstractOrderEntryModel> entries = cart.getEntries();
		List<String> productSpecificMandatoryFields = new ArrayList<String>();
//
//		for (AbstractOrderEntryModel entry : entries)
//		{
//			ProductModel productModel = entry.getProduct();
//			if (productModel instanceof SubscriptionProductModel)
//			{
//				SubscriptionProductModel subscriptionProductModel = (SubscriptionProductModel) productModel;
//				if (subscriptionProductModel.getMandatoryAddress())
//				{
//					productSpecificMandatoryFields.addAll(getAddressMandatoryFieldNames());
//				}
//				if (subscriptionProductModel.getMandatoryPhone())
//				{
//					productSpecificMandatoryFields.add(FormElementGroupConstants.PHONE_NUMBER);
//				}
//				if (subscriptionProductModel.getMandatoryMobile())
//				{
//					productSpecificMandatoryFields.add(FormElementGroupConstants.MOBILE_PHONE_NUMBER);
//				}
//				if (subscriptionProductModel.getMandatoryEmail())
//				{
//					productSpecificMandatoryFields.add(FormElementGroupConstants.EMAIL);
//				}
//			}
//		}
		return productSpecificMandatoryFields;
	}

	/**
	 * Gets address mandatory field names.
	 *
	 * @return the list of fields that are always mandatory for guter, print abo and digital abo products
	 */
	protected List<String> getAddressMandatoryFieldNames()
	{
		return ADDRESS_MANDATORY_FIELD_NAMES;
	}

	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		return true;
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		return true;
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
	}

	@Override
	public abstract String getKey();

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
	{
	}


	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return Collections.EMPTY_SET;
	}


	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		//FIXME MOVE TO RELEVANT FORMELEMENTGROUP
		Map<String, Object> result = Maps.newHashMap();
//		result.put(TITLES, getSalutationTitles());

		return result;
	}


	/**
	 * Gets salutation titles.
	 *
	 * @return the salutation titles
	 */
//	protected List<TitleData> getSalutationTitles()
//	{
//		return sessionCache.getSalutationTitles();
//	}


	/**
	 * Gets delivery countries.
	 *
	 * @param cartModel the cart model
	 * @return delivery countries
	 */
//	protected List<CountryData> getDeliveryCountries(final CartModel cartModel)
//	{
//		return sessionCache.getCountries(cartModel, true);
//	}

	/**
	 * Gets all countries.
	 *
	 * @param cartModel the cart model
	 * @return the all countries
	 */
//	protected List<CountryData> getAllCountries(final CartModel cartModel)
//	{
//		return sessionCache.getCountries(cartModel, false);
//	}
}
