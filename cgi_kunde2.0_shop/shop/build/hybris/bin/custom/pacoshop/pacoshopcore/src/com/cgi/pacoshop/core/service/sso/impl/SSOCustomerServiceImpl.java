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
package com.cgi.pacoshop.core.service.sso.impl;

import com.cgi.pacoshop.core.model.PhoneNumberModel;
import com.cgi.pacoshop.core.model.ResponseTermAccepted;
import com.cgi.pacoshop.core.model.TermAcceptedModel;

import com.cgi.pacoshop.core.model.TermVersionModel;
import com.cgi.pacoshop.core.service.sso.SSOCustomerRestClient;
import com.cgi.pacoshop.core.service.term.TermVersionService;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;

import static com.cgi.pacoshop.core.util.LogHelper.warn;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.AddressService;
import de.hybris.platform.servicelayer.user.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import com.cgi.pacoshop.core.model.ResponseCustomer;
import com.cgi.pacoshop.core.model.ResponseDynamicFields;
import com.cgi.pacoshop.core.service.BusinessPartnerIdService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.sso.SSOCustomerService;


/**
 * Implementation of SSO customer Service and businessPartnerId
 * 
 * @module pacoshopcore
 * @version 1.0v Feb 07, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class SSOCustomerServiceImpl implements SSOCustomerService
{
	private static final Logger LOG = Logger.getLogger(SSOCustomerServiceImpl.class);

	@Resource
	private SSOCustomerRestClient    ssoCustomerRestClient;
	@Resource
	private ModelService             modelService;
	@Resource
	private CommonI18NService        commonI18NService;
	@Resource
	private UserService              userService;
	@Resource
	private AddressService           addressService;
	@Resource
	private BusinessPartnerIdService businessPartnerIdService;
	@Resource
	private ShopConfigurationService shopConfigurationService;
	@Resource
	private TermVersionService       termVersionService;
	@Resource
	private ConfigurationService     configurationService;


	@Override
	public void updateCustomer(final UserModel userModel, final Map<String, String> parameters)
	{
		if (userModel instanceof CustomerModel)
		{
			debug(LOG, "Updating the User from SSO for userId = {%s}...", userModel.getUid());

			final CustomerModel customerModel = (CustomerModel) userModel;
			ResponseCustomer responseCustomer = null;
			final String customerUid = parameters.get(shopConfigurationService.getSsoUserFilterAccountId());

			try
			{
				responseCustomer = ssoCustomerRestClient.getUserData(parameters);
			}
			catch (final HttpClientErrorException exception)
			{
				error(LOG, "Http client exception, userId=%s, status= %s", exception, customerUid, exception.getStatusCode());
			}
			catch (final HttpServerErrorException exception)
			{
				error(LOG, "Http server exception, userId=%s, status= %s", exception, customerUid, exception.getStatusCode());
			}
			catch (final Exception exception)
			{
				error(LOG, "Error occurred while getting user data from SSO, userId=%s", exception, customerUid);
			}
			if (responseCustomer != null)
			{
				try
				{
					updateAndSaveCustomerModel(customerModel, responseCustomer, customerUid);
				}
				catch (ModelSavingException exception)
				{
					error(LOG, "Error occurred while saving a customerModel, customerUid=%s", exception, customerUid);
				}
			}
		}
	}

	private void updateAndSaveCustomerModel(final CustomerModel customerModel, final ResponseCustomer responseCustomer,
														 final String customerUid) throws ModelSavingException
	{
		final ResponseDynamicFields dynamicFields = responseCustomer.getDynamicFields();

		customerModel.setName(customerUid);

		if (dynamicFields != null)
		{
			if (StringUtils.isNotEmpty(dynamicFields.getFirstName()))
			{
				customerModel.setFirstName(dynamicFields.getFirstName());
			}
			if (StringUtils.isNotEmpty(dynamicFields.getLastName()))
			{
				customerModel.setLastName(dynamicFields.getLastName());
			}

			final AddressModel mainAddressModel = removeAndCreateAddress(customerModel);

			if (StringUtils.isNotEmpty(responseCustomer.getEmail()))
			{
				mainAddressModel.setEmail(responseCustomer.getEmail());
				customerModel.setCustomerEmail(responseCustomer.getEmail());
			}
			if (StringUtils.isNotEmpty(dynamicFields.getFirstName()))
			{
				mainAddressModel.setFirstname(dynamicFields.getFirstName());
			}
			if (StringUtils.isNotEmpty(dynamicFields.getLastName()))
			{
				mainAddressModel.setLastname(dynamicFields.getLastName());
			}
			if (StringUtils.isNotEmpty(dynamicFields.getStreet()))
			{
				mainAddressModel.setLine1(dynamicFields.getStreet());
			}
			if (StringUtils.isNotEmpty(dynamicFields.getStreetNumber()))
			{
				mainAddressModel.setLine2(dynamicFields.getStreetNumber());
			}
			if (StringUtils.isNotEmpty(dynamicFields.getPostalCode()))
			{
				mainAddressModel.setPostalcode(dynamicFields.getPostalCode());
			}

			mainAddressModel.setBusinessNumber(getBusinessPhoneNumberModel(dynamicFields));
			mainAddressModel.setHomeNumber(getHomePhoneNumberModel(dynamicFields));
			mainAddressModel.setMobileNumber(getMobilePhoneModel(dynamicFields));

			if (StringUtils.isNotEmpty(dynamicFields.getCity()))
			{
				mainAddressModel.setTown(dynamicFields.getCity());
			}

			mainAddressModel.setTitle(getTitleModel(dynamicFields.getTitle()));

			DateFormat ssoDateFormat = new SimpleDateFormat(shopConfigurationService.getSsoDateFormat());
			if (StringUtils.isNotEmpty(dynamicFields.getDateOfBirth()))
			{
				try
				{
					mainAddressModel.setDateOfBirth(ssoDateFormat.parse(dynamicFields.getDateOfBirth()));
				}
				catch (final ParseException e)
				{
					error(LOG, String.format("Could not parse the dateOfBirth value \"%s\", valid dateOfBirth format is \"%s\"",
													 dynamicFields.getDateOfBirth(), ssoDateFormat), e);
				}
			}
			else
			{
				debug(LOG, "SSO.readAccount dynamic field dateOfBirth value is null");
			}

			if (StringUtils.isNotEmpty(dynamicFields.getCountry()))
			{
				try
				{
					mainAddressModel.setCountry(getCountryModel(dynamicFields.getCountry()));
				}
				catch (final UnknownIdentifierException e)
				{
					error(LOG, "No country was found, countryId={%s}", dynamicFields.getCountry());
				}
			}
			else
			{
				debug(LOG, "SSO.readAccount dynamic field country value is null");
			}
			modelService.save(mainAddressModel);
			customerModel.setMainAddress(mainAddressModel);

			String optInTermName = shopConfigurationService.getOptInTermName();
			TermVersionModel optInTermVersion = termVersionService.getLatestTermVersionByName(optInTermName);
			if (optInTermVersion == null)
			{
				error(LOG, "No OptIn terms found in TermVersion table in DB. optInTermName = \"%s\"", optInTermName);
			}
			else
			{
				List<ResponseTermAccepted> responseTerms = responseCustomer.getTerms();
				if (responseTerms != null)
				{
					Set<TermAcceptedModel> termsAccepted = new HashSet<>();
					TermAcceptedModel optInTermAccepted = new TermAcceptedModel();
					optInTermAccepted.setTermsVersion(optInTermVersion);
					//if no optInTermVersion.confirmed info is found in responseTerms then the customer hasn't accepted OptIn term yet
					optInTermAccepted.setConfirmed(false);
					for (ResponseTermAccepted responseTerm : responseTerms)
					{
						if (optInTermName.equals(responseTerm.getName()) && responseTerm.isConfirmed()
								  && responseTerm.getVersion() >= optInTermVersion.getVersion())
						{
							optInTermAccepted.setConfirmed(true);
							break;
						}
					}
					termsAccepted.add(optInTermAccepted);
					customerModel.setTermsAccepted(termsAccepted);
				}
			}
		}
		customerModel
				  .setBusinessPartnerIds(getBusinessPartnerIdModels(customerModel, responseCustomer.getBusinessPartners()));
		modelService.save(customerModel);
	}

	private PhoneNumberModel getMobilePhoneModel(final ResponseDynamicFields dynamicFields)
	{
		final PhoneNumberModel mobilePhone = modelService.create(PhoneNumberModel.class);
		if (StringUtils.isNotEmpty(dynamicFields.getMobilePrefix()))
		{
			mobilePhone.setPrefix(dynamicFields.getMobilePrefix());
		}
		if (StringUtils.isNotEmpty(dynamicFields.getMobileNumber()))
		{
			mobilePhone.setNumber(dynamicFields.getMobileNumber());
		}
		return mobilePhone;
	}

	private PhoneNumberModel getHomePhoneNumberModel(final ResponseDynamicFields dynamicFields)
	{
		final PhoneNumberModel homePhone = modelService.create(PhoneNumberModel.class);
		if (StringUtils.isNotEmpty(dynamicFields.getPhonePrefixHome()))
		{
			homePhone.setPrefix(dynamicFields.getPhonePrefixHome());
		}
		if (StringUtils.isNotEmpty(dynamicFields.getPhoneExtensionHome()))
		{
			homePhone.setExtension(dynamicFields.getPhoneExtensionHome());
		}
		if (StringUtils.isNotEmpty(dynamicFields.getPhoneNumberHome()))
		{
			homePhone.setNumber(dynamicFields.getPhoneNumberHome());
		}
		return homePhone;
	}

	private PhoneNumberModel getBusinessPhoneNumberModel(final ResponseDynamicFields dynamicFields)
	{
		final PhoneNumberModel businessPhone = modelService.create(PhoneNumberModel.class);
		if (StringUtils.isNotEmpty(dynamicFields.getPhonePrefixBusiness()))
		{
			businessPhone.setPrefix(dynamicFields.getPhonePrefixBusiness());
		}
		if (StringUtils.isNotEmpty(dynamicFields.getPhoneExtensionBusiness()))
		{
			businessPhone.setExtension(dynamicFields.getPhoneExtensionBusiness());
		}
		if (StringUtils.isNotEmpty(dynamicFields.getPhoneNumberBusiness()))
		{
			businessPhone.setNumber(dynamicFields.getPhoneNumberBusiness());
		}
		return businessPhone;
	}

	private AddressModel removeAndCreateAddress(final CustomerModel customerModel)
	{
		if (customerModel.getMainAddress() != null)
		{
			modelService.remove(customerModel.getMainAddress());
			modelService.refresh(customerModel);
		}
		return addressService.createAddressForUser(customerModel);
	}

	private Set<BusinessPartnerIdModel> getBusinessPartnerIdModels(final CustomerModel customer, final List<String> idList)
	{
		final Set<BusinessPartnerIdModel> businessPartnerIdModels = new HashSet<>();

		Set<String> newSsoIds = new HashSet<>();
		List<BusinessPartnerIdModel> oldIdModels = null;

		if (idList == null || idList.isEmpty())
		{
			oldIdModels = businessPartnerIdService.findBusinessPartnerIdsForCustomer(customer.getUid());
		}
		else
		{
			newSsoIds.addAll(idList);
			oldIdModels = businessPartnerIdService.findBusinessPartnerIds(newSsoIds);
		}

		if (oldIdModels != null && !oldIdModels.isEmpty())
		{
			// remove old sso business partner ids that could belong to other customers
			for (final BusinessPartnerIdModel oldIdModel : oldIdModels)
			{
				modelService.remove(oldIdModel);
			}
		}

		// create a new model for current customer
		for (final String newSsoId : newSsoIds)
		{
			final BusinessPartnerIdModel newIdModel = modelService.create(BusinessPartnerIdModel.class);
			newIdModel.setCustomer(customer);
			newIdModel.setId(newSsoId);
			businessPartnerIdModels.add(newIdModel);
		}

		return businessPartnerIdModels;
	}

	private TitleModel getTitleModel(final String code)
	{
		if (StringUtils.isNotEmpty(code))
		{
			try
			{
				return userService.getTitleForCode(code);
			}
			catch (final UnknownIdentifierException e)
			{
				error(LOG, "No title found in hybris for code={%s}", code);
			}
		}
		else
		{
			debug(LOG, "SSO.readAccount dynamic field title value is null");
		}
		return userService.getAnonymousUser().getTitle();
	}


	@Override
	public String getUserGroupForSSOUserType(final String ssoUserType)
	{
		if (ssoUserType == null)
		{
			throw new IllegalArgumentException("User Type must never be null!");
		}

		// Read the expected (valid) headers values from the configuration
		final String userTypeRegisteredCustomer = configurationService.getConfiguration().getString(
				ShopConfigurationService.CONFIG_KEY_UAG_HEADER_VALUE_CUSTOMER_ID_TYPE_REGISTERED);

		final String userTypeVisitor = configurationService.getConfiguration().getString(
				ShopConfigurationService.CONFIG_KEY_UAG_HEADER_VALUE_CUSTOMER_ID_TYPE_VISITOR);

		if (ssoUserType.equalsIgnoreCase(userTypeRegisteredCustomer))
		{
			return configurationService.getConfiguration().getString(
                 ShopConfigurationService.CONFIG_KEY_REGISTERED_CUSTOMER_GROUP_UID);
		}
		else if (ssoUserType.equalsIgnoreCase(userTypeVisitor))
		{
			return configurationService.getConfiguration().getString(ShopConfigurationService.CONFIG_KEY_VISITOR_CUSTOMER_GROUP_UID);
		}
		else
		{
			throw new IllegalArgumentException(String.format("[%s] is not configured as a valid user id type", ssoUserType));
		}
	}



	private CountryModel getCountryModel(final String isoCode)
	{
		return commonI18NService.getCountry(isoCode);
	}



}
