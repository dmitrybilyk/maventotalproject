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
package com.cgi.pacoshop.core.service.servicebus.impl;


import com.cgi.pacoshop.core.exceptions.PLZValidationException;
import com.cgi.pacoshop.core.model.BusinessPartnerIdModel;
import com.cgi.pacoshop.core.model.PhoneNumberModel;
import com.cgi.pacoshop.core.model.ResponseBusinessPartner;
import com.cgi.pacoshop.core.model.ResponseDynamicFields;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.BusinessPartnerIdService;
import com.cgi.pacoshop.core.service.CustomerAddressService;
import com.cgi.pacoshop.core.service.PLZValidationService;
import com.cgi.pacoshop.core.service.PacoAddressService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.Title2Service;
import com.cgi.pacoshop.core.service.servicebus.ServiceBusBusinessPartnerService;
import com.cgi.pacoshop.core.service.servicebus.ServiceBusRestClient;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import static com.cgi.pacoshop.core.util.LogHelper.warn;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.AddressService;
import de.hybris.platform.servicelayer.user.UserService;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

/**
 * ServiceBus for businessPartnerId implementation.
 *
 * @module build
 * @version 1.0v Mar 11, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ServiceBusBusinessPartnerServiceImpl implements ServiceBusBusinessPartnerService
{
	private static final Logger LOG = Logger.getLogger(ServiceBusBusinessPartnerServiceImpl.class);

	@Resource
	private ServiceBusRestClient     serviceBusRestClient;
	@Resource
	private ModelService             modelService;
	@Resource
	private CommonI18NService        commonI18NService;
	@Resource
	private UserService              userService;
	@Resource
	private Title2Service            title2Service;
	@Resource
	private AddressService           addressService;
	@Resource
	private PacoAddressService       pacoAddressService;
	@Resource
	private PLZValidationService     plzValidationService;
	@Resource
	private BusinessPartnerIdService businessPartnerIdService;
	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private CustomerAddressService customerAddressService;

	@Override
	public void updateBusinessPartners(final UserModel userModel, final Map<String, String> parameters)
	{
		if (userModel instanceof CustomerModel)
		{
            debug(LOG, "Updating business partner ids for user={%s}...", userModel.getUid());

            final CustomerModel customerModel = (CustomerModel) userModel;
			final String customerUid = customerModel.getUid();
			List<BusinessPartnerIdModel> businessPartnerIdModels =
					  businessPartnerIdService.findBusinessPartnerIdsForCustomer(customerUid);
			//remove old business partner addresses, leave only main address and shop address
			for (BusinessPartnerIdModel businessPartnerIdModel : businessPartnerIdModels)
			{

				try
				{
					AddressModel addressModel = pacoAddressService.getAddressForBusinessPartnerId(businessPartnerIdModel.getId());
					if (addressModel != null)
					{
						modelService.remove(addressModel);
					}
				}
				catch (Exception e)
				{
					error(LOG, "Exception removing existing sap address", e);
				}

			}
			Collection<ResponseBusinessPartner> responseBusinessPartners = null;
			try
			{
				responseBusinessPartners = serviceBusRestClient.getBusinessPartnersData(businessPartnerIdModels, parameters);
			}
			catch (HttpClientErrorException exception)
			{
				error(LOG, "Http client exception, userId=%s, status= %s", exception,
						customerUid, exception.getStatusCode());
			}
			catch (HttpServerErrorException exception)
			{
				error(LOG, "Http server exception, userId=%s, status= %s", exception,
						customerUid, exception.getStatusCode());
			}
			catch (Exception exception)
			{
				error(LOG, "Error occurred while getting user data from ServiceBus, userId=%s", exception, customerUid);
			}

			if (responseBusinessPartners != null && !responseBusinessPartners.isEmpty())
			{
				for (final ResponseBusinessPartner responseBusinessPartner : responseBusinessPartners)
				{
					try
					{
						AddressModel businessPartnerAddress =
								  getBusinessPartnerAddress(responseBusinessPartner.getBusinessPartnerId(), customerModel);

						//Generates PLZ exception if plz is not valid that cancel save of address for this businessPartnerId.
						plzValidationService.validate(responseBusinessPartner.getPostalCode(),
																responseBusinessPartner.getCountry().toLowerCase());

						businessPartnerAddress.setBusinessPartnerId(responseBusinessPartner.getBusinessPartnerId());
						businessPartnerAddress.setPostalcode(responseBusinessPartner.getPostalCode());

						if (StringUtils.isNotEmpty(responseBusinessPartner.getEmail()))
						{
							businessPartnerAddress.setEmail(responseBusinessPartner.getEmail());
						}
						if (StringUtils.isNotEmpty(responseBusinessPartner.getFirstname()))
						{
							businessPartnerAddress.setFirstname(responseBusinessPartner.getFirstname());
						}
						if (StringUtils.isNotEmpty(responseBusinessPartner.getLastname()))
						{
							businessPartnerAddress.setLastname(responseBusinessPartner.getLastname());
						}
						if (StringUtils.isNotEmpty(responseBusinessPartner.getStreet()))
						{
							businessPartnerAddress.setLine1(responseBusinessPartner.getStreet());
						}
						if (StringUtils.isNotEmpty(responseBusinessPartner.getStreetNumber()))
						{
							businessPartnerAddress.setLine2(responseBusinessPartner.getStreetNumber());
						}
						if (StringUtils.isNotEmpty(responseBusinessPartner.getAddressSuffix()))
						{
								businessPartnerAddress.setLine3(responseBusinessPartner.getAddressSuffix());
						}

						businessPartnerAddress.setBusinessNumber(getBusinessPhoneNumberModel(responseBusinessPartner));
						businessPartnerAddress.setHomeNumber(getHomePhoneNumberModel(responseBusinessPartner));
						businessPartnerAddress.setMobileNumber(getMobilePhoneModel(responseBusinessPartner));

						if (StringUtils.isNotEmpty(responseBusinessPartner.getCity()))
						{
							businessPartnerAddress.setTown(responseBusinessPartner.getCity());
						}

						businessPartnerAddress.setTitle(getTitleModel(responseBusinessPartner.getSalutation()));

						if (StringUtils.isNotEmpty(responseBusinessPartner.getTitle()))
						{
							// businessPartnerAddress.setTitle2(responseBusinessPartner.getTitle());
							businessPartnerAddress.setTitle2(getTitle2Model(responseBusinessPartner.getTitle()));
						}
						else
						{
							businessPartnerAddress.setTitle2(null);
						}

						if (responseBusinessPartner.getBirthday() != null)
						{
							try
							{
								businessPartnerAddress.setDateOfBirth(responseBusinessPartner.getBirthday());
							}
							catch (NumberFormatException e)
							{
								warn(LOG, "String is no valid for parsing to date, value to parse={%s}",
									  responseBusinessPartner.getBirthday());
							}
						}

						//no check for null because of implicit checking in plz validation
						try
						{
							businessPartnerAddress.setCountry(getCountryModel(responseBusinessPartner.getCountry()));
						}
						catch (UnknownIdentifierException e)
						{
							warn(LOG, "No country was found, countryId=%s", responseBusinessPartner.getCountry());
						}


						businessPartnerAddress.setLastUpdate(new Date());
						customerAddressService.saveCustomerAddressWithLimit(businessPartnerAddress, customerModel);
					}
					catch (PLZValidationException e)
					{
						error(LOG, "Not valid postal code {%s} for country {%s}", responseBusinessPartner.getPostalCode(),
								responseBusinessPartner.getCountry());
						removeBusinessPartnerIdFromCustomer(businessPartnerIdModels, responseBusinessPartner.getBusinessPartnerId());
					}
				}
			}
		}
	}

	private void removeBusinessPartnerIdFromCustomer(final List<BusinessPartnerIdModel> businessPartnerIdModels,
																	 final String currentBusinessPartnerId)
	{
		for (BusinessPartnerIdModel businessPartnerIdModel : businessPartnerIdModels)
		{
			if (businessPartnerIdModel.getId().equals(currentBusinessPartnerId))
			{
				modelService.remove(businessPartnerIdModel);
			}
		}
	}

	private AddressModel getBusinessPartnerAddress(final String businessPartnerId, final CustomerModel customerModel)
	{
		AddressModel businessPartnerAddress = null;
		try
		{
			businessPartnerAddress =
					  pacoAddressService.getAddressForBusinessPartnerId(businessPartnerId);
		}
		catch (UnknownIdentifierException e)
		{
			debug(LOG, "No business partner found in hybris by userId=%s", businessPartnerId);
		}

		if (businessPartnerAddress != null && !businessPartnerAddress.getOwner().getPk().equals(customerModel.getPk()))
		{
			modelService.remove(businessPartnerAddress);
		}

		if (businessPartnerAddress == null || modelService.isRemoved(businessPartnerAddress))
		{
			//TODO - correct?
			businessPartnerAddress = new AddressModel();
		}
		return businessPartnerAddress;
	}

	private TitleModel getTitleModel(final String code)
	{
		TitleModel titleModel = null;
		try
		{
			titleModel = userService.getTitleForCode(code);
		}
		catch (UnknownIdentifierException e)
		{
			error(LOG, "No title found in hybris for code=%s , new one will be created", code);
			titleModel = modelService.create(TitleModel.class);
			titleModel.setCode(code);
			titleModel.setName(code);
			modelService.save(titleModel);
		}
		return titleModel;
	}

	private Title2Model getTitle2Model(final String code)
	{
		Title2Model title2Model = null;
		try
		{
			title2Model = title2Service.getTitle2ForCode(code);
		}
		catch (ModelNotFoundException e)
		{
			error(LOG, "No title2 found in hybris for code=%s , new one will be created", code);
			title2Model = modelService.create(Title2Model.class);
			title2Model.setCode(code);
			title2Model.setName(code);
			title2Model.setIndex(title2Service.getNewTitle2Index());
			modelService.save(title2Model);
		}
		return title2Model;
	}

	private CountryModel getCountryModel(final String isoCode)
	{
		return commonI18NService.getCountry(isoCode);
	}

	private PhoneNumberModel getMobilePhoneModel(final ResponseBusinessPartner responseBusinessPartner)
	{
		final PhoneNumberModel mobilePhone = modelService.create(PhoneNumberModel.class);
		if (StringUtils.isNotEmpty(responseBusinessPartner.getMobilePrefix()))
		{
			mobilePhone.setPrefix(responseBusinessPartner.getMobilePrefix());
		}
		if (StringUtils.isNotEmpty(responseBusinessPartner.getMobileNumber()))
		{
			mobilePhone.setNumber(responseBusinessPartner.getMobileNumber());
		}
		return mobilePhone;
	}

	private PhoneNumberModel getHomePhoneNumberModel(final ResponseBusinessPartner responseBusinessPartner)
	{
		final PhoneNumberModel homePhone = modelService.create(PhoneNumberModel.class);
		if (StringUtils.isNotEmpty(responseBusinessPartner.getPhonePrefixHome()))
		{
			homePhone.setPrefix(responseBusinessPartner.getPhonePrefixHome());
		}
		if (StringUtils.isNotEmpty(responseBusinessPartner.getPhoneExtensionHome()))
		{
			homePhone.setExtension(responseBusinessPartner.getPhoneExtensionHome());
		}
		if (StringUtils.isNotEmpty(responseBusinessPartner.getPhoneNumberHome()))
		{
			homePhone.setNumber(responseBusinessPartner.getPhoneNumberHome());
		}
		return homePhone;
	}

	private PhoneNumberModel getBusinessPhoneNumberModel(final ResponseBusinessPartner responseBusinessPartner)
	{
		final PhoneNumberModel businessPhone = modelService.create(PhoneNumberModel.class);
		if (StringUtils.isNotEmpty(responseBusinessPartner.getPhonePrefixBusiness()))
		{
			businessPhone.setPrefix(responseBusinessPartner.getPhonePrefixBusiness());
		}
		if (StringUtils.isNotEmpty(responseBusinessPartner.getPhoneExtensionBusiness()))
		{
			businessPhone.setExtension(responseBusinessPartner.getPhoneExtensionBusiness());
		}
		if (StringUtils.isNotEmpty(responseBusinessPartner.getPhoneNumberBusiness()))
		{
			businessPhone.setNumber(responseBusinessPartner.getPhoneNumberBusiness());
		}
		return businessPhone;
	}
}
