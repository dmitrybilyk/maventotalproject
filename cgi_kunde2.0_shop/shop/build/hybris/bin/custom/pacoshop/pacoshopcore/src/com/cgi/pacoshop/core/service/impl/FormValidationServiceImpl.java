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
package com.cgi.pacoshop.core.service.impl;


import com.cgi.hybris.payment.core.constants.HybrispaymentcoreConstants;
import com.cgi.hybris.payment.core.data.PaymentInfoData;
import com.cgi.hybris.payment.core.data.PaymentModeData;
import com.cgi.hybris.payment.core.facades.PaymentExtFacade;
import com.cgi.hybris.payment.core.facades.impl.DefaultProxyCheckoutFacade;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import com.cgi.pacoshop.core.service.FormValidationService;
import com.cgi.pacoshop.core.service.PLZValidationService;
import com.cgi.pacoshop.core.util.FormElementGroupUtil;
import com.cgi.pacoshop.core.util.LogHelper;
import com.cgi.pacoshop.core.util.MccSiteUrlHelper;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;

import de.hybris.platform.util.localization.Localization;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;

/**
 * Implementation of FormValidationService
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 30, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http :www.symmetrics.de/
 * @see  "https:wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public class FormValidationServiceImpl implements FormValidationService
{

	private static final Logger  LOG                    = Logger.getLogger(FormValidationServiceImpl.class);
	private static final String  EMAIL_PATTERN          =
			  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String  PREFIX_PATTERN         = "[+]?[\\d{1,}][( )?\\d{1,}]+";
	private static final String  PHONE_PATTERN          = "[\\d{0,}][( )-?\\d{1,}]+";
	private static final Integer YEARS_INFIMUM          = 1900;
	private static final Integer MAX_YEAR_LIMIT         = 2099;
	private static final String  MILES_AND_MORE_PATTERN = "99\\d{13}|22\\d{13}";
	private static final String  BIC_PATTERN            = "^[a-zA-Z0-9]{8,11}$";
	private static final String  NUMBER_PATTERN         = "^-?\\d+$";
	private static final String  DELIVERY_DATE_REGEX_PATTERN   = "^[\\d]{1,2}.[\\d]{1,2}.[\\d]{4}$";
	private static final int     LAST_HOUR_OF_DAY       = 23;
	private static final int     LAST_MINUTE_OF_HOUR    = 59;
	private static final int     LAST_SECOND_OF_MINUTE  = 59;
	private static final int     LAST_MILISECOND_OF_SECOND = 999;


	@Resource
	private PLZValidationService plzValidationService;

	@Resource
	private CheckoutStepService checkoutStepService;

	@Resource
	private ConfigurationService configurationService;

	@Resource
	private MccSiteUrlHelper mccSiteUrlHelper;

	@Resource
	private DefaultProxyCheckoutFacade defaultProxyCheckoutFacade;

	@Resource
	private PaymentExtFacade paymentExtFacade;

	/**
	 * Gets checkout step service.
	 *
	 * @return the checkout step service.
	 */
	public CheckoutStepService getCheckoutStepService()
	{
		return checkoutStepService;
	}

	/**
	 * Sets checkout step service.
	 *
	 * @param checkoutStepService the checkout step service.
	 */
	public void setCheckoutStepService(final CheckoutStepService checkoutStepService)
	{
		this.checkoutStepService = checkoutStepService;
	}


	@Override
	public boolean validateMandatoryField(final String mandatoryFieldValue)
	{
		return StringUtils.isNotEmpty(mandatoryFieldValue);
	}

	@Override
	public boolean validateTitle(final String title)
	{
		return title != null && title.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateFirma(final String firma)
	{
		return firma != null && firma.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validatePosition(final String position)
	{
		return position != null && position.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateAdditionalStreet(final String additionalStreet)
	{
		return additionalStreet != null && additionalStreet.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateFirstName(final String firstName)
	{
		return !StringUtils.isEmpty(firstName) && firstName.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateLastName(final String lastName)
	{
		return !StringUtils.isEmpty(lastName) && lastName.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateStreet(final String street)
	{
		return !StringUtils.isEmpty(street) && street.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateHouseNumber(final String houseNumber)
	{
		return !StringUtils.isEmpty(houseNumber) && houseNumber.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateZip(final String zip, final String isoCountry)
	{
		return (zip != null && isoCountry != null)
				  && plzValidationService.validateZipCode(zip.toLowerCase().trim(), isoCountry.toLowerCase().trim());
	}

	@Override
	public boolean validateCity(final String city)
	{
		return !city.isEmpty() && city.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateCountry(final String country)
	{
		return !StringUtils.isEmpty(country) && country.length() < FIELD_LENGTH;
	}

	@Override
	public boolean validateEmail(final String email)
	{
		if (email == null)
		{
			return false;
		}
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private boolean validateMobile(final String prefix, final String phone,  final boolean isMandatory)
	{
		boolean mobilePhoneValid = true;
		boolean mobilePhoneNotEmpty = !isEmptyNumber(prefix, prefix, phone);

		if (mobilePhoneNotEmpty)
		{
			mobilePhoneValid = validateMobile(prefix, phone);
		}

		return isMandatory ? mobilePhoneValid && mobilePhoneNotEmpty : mobilePhoneValid;
	}

	@Override
	public boolean validateMobile(final String prefix, final String phone)
	{
		return validateString(prefix, PREFIX_PATTERN)
				  && validateString(phone, PHONE_PATTERN);
	}

	private boolean validatePhones(final CheckoutFormDTO dto, final boolean isMandatory)
	{
		boolean homePhoneValid = true, businessPhoneValid = true;
		boolean homePhoneNotEmpty = !isPhoneHomeEmpty(dto),
				  businessPhoneNotEmpty = !isPhoneBusinessEmpty(dto);

		if (homePhoneNotEmpty)
		{
			homePhoneValid = validatePhoneHome(dto);
		}
		if (businessPhoneNotEmpty)
		{
			businessPhoneValid = validatePhoneBusiness(dto);
		}

		if (isMandatory)
		{
			return (homePhoneNotEmpty || businessPhoneNotEmpty) && homePhoneValid && businessPhoneValid;
		}
		else
		{
			return homePhoneValid && businessPhoneValid;
		}
	}

	private boolean isPhoneBusinessEmpty(final CheckoutFormDTO dto)
	{
		return isEmptyNumber(dto.getPhonePrefixBusiness(), dto.getPhoneExtensionBusiness(),	dto.getPhoneNumberBusiness());
	}

	private boolean isEmptyNumber(final String prefix, final String extension, final String number)
	{
		return StringUtils.isEmpty(prefix)
				  && StringUtils.isEmpty(extension)
				  && StringUtils.isEmpty(number);
	}

	@Override
	public boolean validatePhone(final String prefix, final String extension, final String number)
	{
		return  validateString(prefix, PREFIX_PATTERN)
				  && validateString(extension, PHONE_PATTERN)
				  && validateString(number, PHONE_PATTERN);
	}

	private boolean validateString(final String string, final String regex)
	{
		return string != null && string.matches(regex);
	}

	@Override
	public boolean validateBirthDate(final CheckoutFormDTO dto)
	{
		try
		{
			Date date = FormElementGroupUtil.parseDate(dto.getBirthDateDay(), dto.getBirthDateMonth(), dto.getBirthDateYear());
			if (date == null)
			{
				return true;
			}
			dto.setValidatedDate(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return false;
		}
		return Integer.parseInt(dto.getBirthDateYear()) > YEARS_INFIMUM
				  && Integer.parseInt(dto.getBirthDateYear()) < MAX_YEAR_LIMIT;
	}

	@Override
	public boolean validateCustomerDataDownloadPage(final CheckoutFormDTO dto, final BindingResult bindingResult)
	{
		if (dto.isInvoiceWanted())
		{
			validateAddress(dto, bindingResult);
		}

		return !bindingResult.hasErrors();
	}

	@Override
	public boolean validateSummaryDownloadPage(final SummaryStepFormDTO dto, final BindingResult bindingResult)
	{
		final Map<String, Boolean> stepMandatoryFieldNames =
				  getCheckoutStepService().getStepMandatoryFieldNames(checkoutStepService.getCheckoutStep(dto));

		String fieldName = FormElementGroupConstants.OPTIN;
		if (stepMandatoryFieldNames.containsKey(fieldName) && validateOptIn(dto))
		{
			bindingResult.rejectValue("optIn", "validation.error.optIn");
		}
		if (!dto.isAgb())
		{
			bindingResult.rejectValue("agb", "validation.error.agb");
		}
		return !bindingResult.hasErrors();
	}

	@Override
	public boolean validateOptIn(final SummaryStepFormDTO dto)
	{
		return dto.getOptIn() != null && !dto.getOptIn();
	}

	@Override
	public boolean validateBIC(final CheckoutFormDTO dto)
	{
		return dto.getBic().matches(BIC_PATTERN);
	}

	@Override
	public boolean validateIBAN(final CheckoutFormDTO dto)
	{
		return IBANCheckDigit.IBAN_CHECK_DIGIT.isValid(dto.getIban());
	}

	@Override
	public boolean validateForNumber(final String field)
	{
		return field.matches(NUMBER_PATTERN);
	}


	//the option is to create method that will contain the logic from the method above
	//and reuse it in this method and in the method above
	//but it seems to me that if we will do like that for each customer data page this class will look ugly
	//so we need to think how to refactor all this stuff
	@Override
	public boolean validateCustomerDataGoodPrintDigitalAbo(final CheckoutFormDTO dto, final BindingResult bindingResult)
	{
		//get mandatory fields current from checkout step
		validateAddress(dto, bindingResult);

		return !bindingResult.hasErrors();
	}

	@Override
	public boolean validateGoodPrintDigitalProduct(final ShipmentInfoFormDTO dto, final BindingResult bindingResult)
	{
		String fieldName = FormElementGroupConstants.BILLING_SALUTATION;
		if (StringUtils.isEmpty(dto.getBillingSalutation()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.title");
		}
		fieldName = FormElementGroupConstants.BILLING_FIRST_NAME;
		if (!validateFirstName(dto.getBillingFirstName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.firstName");
		}
		fieldName = FormElementGroupConstants.BILLING_LAST_NAME;
		if (!validateLastName(dto.getBillingLastName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.lastName");
		}
		fieldName = FormElementGroupConstants.BILLING_STREET;
		if (!validateStreet(dto.getBillingStreet()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.street");
		}
		fieldName = FormElementGroupConstants.BILLING_HOUSE_NUMBER;
		if (!validateHouseNumber(dto.getBillingStreet()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.houseNumber");
		}
		fieldName = FormElementGroupConstants.BILLING_PLZ_ZIP;
		if (!validateZip(dto.getBillingZip(), dto.getBillingCountry()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.zip");
		}
		fieldName = FormElementGroupConstants.BILLING_CITY_ORT;
		if (!validateCity(dto.getBillingCity()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.city");

		}
		fieldName = FormElementGroupConstants.BILLING_EMAIL;
		if (!validateEmail(dto.getBillingEmail()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.email");
		}
		fieldName = FormElementGroupConstants.BILLING_COUNTRY;
		if (!validateCountry(dto.getBillingCountry()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.country");

		}
		return !bindingResult.hasErrors();
	}

	@Override
	public boolean validateMandatoryAddress(final CheckoutFormDTO dto, final BindingResult bindingResult)
	{
		validateAddress(dto, bindingResult);

		return !bindingResult.hasErrors();
	}

	private void validateAddress(final CheckoutFormDTO dto, final BindingResult bindingResult)
	{
		final Map<String, Boolean> stepMandatoryFieldNames =
				  getCheckoutStepService().getStepMandatoryFieldNames(checkoutStepService.getCheckoutStep(dto));
		String fieldName = FormElementGroupConstants.TITLE;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateTitle(dto.getTitle()) || (
				  !StringUtils.isEmpty(dto.getTitle()) && !validateTitle(dto.getTitle())))
		{
			bindingResult.rejectValue(fieldName, "validation.error.title");
		}
		fieldName = FormElementGroupConstants.FIRST_NAME;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateFirstName(dto.getFirstName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.firstName");
		}
		fieldName = FormElementGroupConstants.LAST_NAME;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateLastName(dto.getLastName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.lastName");
		}
		fieldName = FormElementGroupConstants.COMPANY;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateFirma(dto.getCompany()) || (
				  !StringUtils.isEmpty(dto.getCompany()) && !validateFirma((dto.getCompany()))))
		{
			bindingResult.rejectValue(fieldName, "validation.error.company");
		}
		fieldName = FormElementGroupConstants.POSITION;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validatePosition(dto.getPositionCompany()) || (
				  !StringUtils.isEmpty(dto.getPositionCompany()) && !validatePosition((dto.getPositionCompany()))))
		{
			bindingResult.rejectValue(fieldName, "validation.error.position");
		}
		fieldName = FormElementGroupConstants.EMAIL;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateEmail(dto.getEmail()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.email");
		}
		fieldName = FormElementGroupConstants.BIRTH_DATE;
		if (!validateBirthDate(dto))
		{
			bindingResult.rejectValue(fieldName, "validation.error.birthdate");
		}

		fieldName = FormElementGroupConstants.HOME_PHONE_NUMBER;
		if (!validatePhones(dto, stepMandatoryFieldNames.keySet().contains(fieldName)))
		{
			final boolean phoneHomeEmpty = isPhoneHomeEmpty(dto), phoneBusinessEmpty = isPhoneBusinessEmpty(dto);
			if (phoneHomeEmpty && phoneBusinessEmpty)
			{
				bindingResult.rejectValue(fieldName, "validation.error.phoneNumberHome");
				bindingResult.rejectValue(FormElementGroupConstants.BUSINESS_PHONE_NUMBER, "validation.error.phoneNumberBusiness");
			}
			else
			{
				if (!phoneHomeEmpty && !validatePhoneHome(dto))
				{
					bindingResult.rejectValue(fieldName, "validation.error.phoneNumberHome");
				}
				if (!phoneBusinessEmpty && !validatePhoneBusiness(dto))
				{
					bindingResult.rejectValue(FormElementGroupConstants.BUSINESS_PHONE_NUMBER, "validation.error.phoneNumberBusiness");
				}
			}
			if (!bindingResult.hasFieldErrors(fieldName)
					  && !bindingResult.hasFieldErrors(FormElementGroupConstants.BUSINESS_PHONE_NUMBER))
			{
				bindingResult.rejectValue(fieldName, "validation.error.phoneNumberHome");
			}
		}

		fieldName = FormElementGroupConstants.MOBILE_PHONE_NUMBER;
		if (!validateMobile(dto.getMobilePrefix(), dto.getMobileNumber(), stepMandatoryFieldNames.keySet().contains(fieldName)))
		{
			bindingResult.rejectValue(fieldName, "validation.error.mobileNumber");
		}
		fieldName = FormElementGroupConstants.ADDITIONAL_STREET;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateAdditionalStreet(dto.getAdditionalStreet()) || (
				  !StringUtils.isEmpty(dto.getAdditionalStreet()) && !validateAdditionalStreet(dto.getAdditionalStreet())))
		{
			bindingResult.rejectValue(fieldName, "validation.error.additionalStreet");
		}
		fieldName = FormElementGroupConstants.STREET;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateStreet(dto.getStreet()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.street");
		}
		fieldName = FormElementGroupConstants.HOUSE_NUMBER;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateHouseNumber(dto.getHouseNumber()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.houseNumber");
		}
		fieldName = FormElementGroupConstants.PLZ_ZIP;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateZip(dto.getZip(), dto.getCountry()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.zip");
		}
		fieldName = FormElementGroupConstants.CITY_ORT;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateCity(dto.getCity()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.city");
		}
		fieldName = FormElementGroupConstants.COUNTRY;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateCountry(dto.getCountry()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.country");
		}
	}

	private boolean validatePhoneHome(final CheckoutFormDTO dto)
	{
		return validatePhone(dto.getPhonePrefixHome(), dto.getPhoneExtensionHome(), dto.getPhoneNumberHome());
	}

	private boolean validatePhoneBusiness(final CheckoutFormDTO dto)
	{
		return validatePhone(dto.getPhonePrefixBusiness(), dto.getPhoneExtensionBusiness(),	dto.getPhoneNumberBusiness());
	}

	private boolean isPhoneHomeEmpty(final CheckoutFormDTO dto)
	{
		return isEmptyNumber(dto.getPhonePrefixHome(), dto.getPhoneExtensionHome(), dto.getPhoneNumberHome());
	}

	@Override
	public boolean validateShippingStartDeliveryDate(final ShipmentInfoFormDTO dto, final BindingResult bindingResult)
	{
		LOG.debug("validateShippingStartDeliveryDate() isDeliveryStart=" + dto.isDeliveryStart()
								+ ", deliveryDate=" + dto.getDeliveryStartDate());
		// validate if checkbox 'delivery now' is empty
		if (!dto.isDeliveryStart())
		{
			// if delivery date is entered
			if (dto.getDeliveryStartDate() != null)
			{
				if (!isDeliveryStartDateCorrect(dto.getDeliveryStartDateStr()))
				{
					bindingResult.rejectValue(FormElementGroupConstants.DELIVERY_START_DATE,
													  "validation.error.delivered.date.wrong.format");
					LOG.debug("validateShippingStartDeliveryDate() hasErrors=" + bindingResult.hasErrors());
					return !bindingResult.hasErrors();
				}
				CMSSiteModel currentSite = mccSiteUrlHelper.getCurrentSite();
				// reload configuration latest, start date
				final int earliestDays = (isNotEmpty(currentSite.getDeliveryStartDateEarliest()))
						  ? currentSite.getDeliveryStartDateEarliest()
						  : getInt(FormElementGroupConstants.AVAILABLE_START_DATE_EARLIEST);
				final int latestDays = (isNotEmpty(currentSite.getDeliveryStartDateLatest()))
						  ? currentSite.getDeliveryStartDateLatest()
						  : getInt(FormElementGroupConstants.AVAILABLE_START_DATE_LATEST);

				final Calendar earliestDate = Calendar.getInstance();
				earliestDate.add(Calendar.DATE, earliestDays);
				adjustToStartOfDay(earliestDate);
				final Calendar latestDate = Calendar.getInstance();
				latestDate.add(Calendar.DATE, latestDays);
				adjustToEndOfDay(latestDate);
				LOG.debug("validateShippingStartDeliveryDate() earliestDate=" + earliestDate.getTime()
										+ ", latestDate=" + latestDate.getTime()
										+ ", deliveryDate=" + dto.getDeliveryStartDate());

				if (dto.getDeliveryStartDate().after(latestDate.getTime()))
				{
					bindingResult.rejectValue(FormElementGroupConstants.DELIVERY_START_DATE, "validation.error.deliveredLater");
				}
				if (dto.getDeliveryStartDate().before(earliestDate.getTime()))
				{
					bindingResult.rejectValue(FormElementGroupConstants.DELIVERY_START_DATE, "validation.error.deliveredEarlier");
				}
				LOG.debug("validateShippingStartDeliveryDate() hasErrors=" + bindingResult.hasErrors());
			}
			else
			{
				// if no delivery now and no date -> error
				bindingResult.rejectValue(FormElementGroupConstants.DELIVERY_START_DATE, "validation.error.deliveredNotSpecified");
			}
		}

		return !bindingResult.hasErrors();
	}

	private boolean isNotEmpty(final Integer value)
	{
		return value != null && value.intValue() > 0;
	}

	private int getInt(final String key)
	{
		return configurationService.getConfiguration().getInt(key);
	}

	@Override
	public boolean validateMilesAndMore(final String milesAndMoreNumber, final BindingResult bindingResult)

	{
		if (milesAndMoreNumber == null || !milesAndMoreNumber.matches(MILES_AND_MORE_PATTERN))
		{
			bindingResult.rejectValue(FormElementGroupConstants.MILES_AND_MORE_NUMBER, "validation.error.milesandmore");
			return false;
		}

		return true;
	}

	@Override
	public boolean validateAboNumber(final String aboNumber, final BindingResult bindingResult)
	{
		if (!validateMandatoryField(aboNumber))
		{
			bindingResult.rejectValue(FormElementGroupConstants.ABO_NUMBER, "validation.error.abonumber");
		}

		return !bindingResult.hasErrors();
	}

	/**
	 * Validate different physical delivery address.
	 *
	 *
	 * @param dto the form dTO
	 * @param bindingResult the binding result
	 * @return the boolean
	 */
	@Override
	public boolean validateDifferentPhysicalDeliveryAddress(
			  final ShipmentInfoFormDTO dto,
			  final BindingResult bindingResult)
	{
		String fieldName = FormElementGroupConstants.NEW_SHIPMENT_SALUTATION;
		if (StringUtils.isEmpty(dto.getNewShipmentSalutation()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.title");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_FIRST_NAME;
		if (!validateFirstName(dto.getNewShipmentFirstName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.firstName");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_LAST_NAME;
		if (!validateLastName(dto.getNewShipmentLastName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.lastName");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_STREET;
		if (!validateStreet(dto.getNewShipmentStreet()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.street");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_HOUSE_NUMBER;
		if (!validateHouseNumber(dto.getNewShipmentHouseNumber()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.houseNumber");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_PLZ_ZIP;
		if (!validateZip(dto.getNewShipmentZip(), dto.getNewShipmentCountry()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.zip");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_CITY_ORT;
		if (!validateCity(dto.getNewShipmentCity()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.city");

		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_COUNTRY;
		if (!validateCountry(dto.getNewShipmentCountry()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.country");

		}

		return !bindingResult.hasErrors();
	}

	/**
	 * Validate different shipment address.
	 *
	 *
	 * @param dto the form dTO
	 * @param bindingResult the binding result
	 * @return the boolean
	 */
	@Override
	public boolean validateDifferentDigitalDeliveryAddress(final ShipmentInfoFormDTO dto,
																			 final BindingResult bindingResult)
	{
		String fieldName = FormElementGroupConstants.NEW_SHIPMENT_SALUTATION;
		if (StringUtils.isEmpty(dto.getNewShipmentSalutation()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.title");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_FIRST_NAME;
		if (!validateFirstName(dto.getNewShipmentFirstName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.firstName");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_LAST_NAME;
		if (!validateLastName(dto.getNewShipmentLastName()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.lastName");
		}
		fieldName = FormElementGroupConstants.NEW_SHIPMENT_EMAIL;
		if (!validateEmail(dto.getNewShipmentEmail()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.email");
		}
		return !bindingResult.hasErrors();
	}

	@Override
	public boolean validatePhysicalBonusWithReferral(final CheckoutFormDTO formDTO, final BindingResult bindingResult)
	{

		boolean isValid = true;
		if (!plzValidationService.validateZipCode(formDTO.getReferralZip().toLowerCase().trim(),
																formDTO.getReferralCountry().toLowerCase().trim()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_ZIP, "validation.error.zip");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralFirstName()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_FIRSTNAME, "validation.error.firstName");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralLastName()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_LASTNAME, "validation.error.lastName");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralCity()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_CITY, "validation.error.city");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralStreet()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_STREET, "validation.error.street");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralHouseNumber()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_HOUSE_NUMBER, "validation.error.houseNumber");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralCountry()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_LAND, "validation.error.country");
			isValid = false;
		}

		if (!validateMandatoryField(formDTO.getReferralSalutation()))
		{
			bindingResult.rejectValue(FormElementGroupConstants.REFERRAL_SALUTATION, "validation.error.emptyfield");
			isValid = false;
		}

		return isValid;
	}

	@Override
	public boolean validateMonetaryBonus(final CheckoutFormDTO formDTO, final BindingResult bindingResult)
	{
		final boolean firstTabFilled =
				  StringUtils.isNotEmpty(formDTO.getIbanBACfirstName())
							 || StringUtils.isNotEmpty(formDTO.getIbanBAClastName())
							 || StringUtils.isNotEmpty(formDTO.getIban())
							 || StringUtils.isNotEmpty(formDTO.getBic());

		final boolean secondTabFilled =
				  StringUtils.isNotEmpty(formDTO.getKontonummerBLZfirstName())
							 || StringUtils.isNotEmpty(formDTO.getKontonummerBLZlastName())
							 || StringUtils.isNotEmpty(formDTO.getAccountNumber())
							 || StringUtils.isNotEmpty(formDTO.getBanIdNumber());
		//all is OK only if validation
		if (firstTabFilled && secondTabFilled)
		{
			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.BIC, formDTO.getBic()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.BIC, "validation.error.useonetab");
			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.IBAN, formDTO.getIban()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.IBAN, "validation.error.useonetab");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.IBAN_BAC_FIRST_NAME,
																formDTO.getIbanBACfirstName()))
			{

				bindingResult.rejectValue(FormElementGroupConstants.IBAN_BAC_FIRST_NAME, "validation.error.useonetab");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.IBAN_BAC_LAST_NAME,
																formDTO.getIbanBAClastName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.IBAN_BAC_LAST_NAME, "validation.error.useonetab");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.ACCOUNT_NUM, formDTO.getAccountNumber()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.ACCOUNT_NUM, "validation.error.useonetab");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.BAN_ID_NUMBER, formDTO.getBanIdNumber()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.BAN_ID_NUMBER, "validation.error.useonetab");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.KONTONUMMER_BLZ_LAST_NAME,
																formDTO.getKontonummerBLZlastName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.KONTONUMMER_BLZ_LAST_NAME, "validation.error.useonetab");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.KONTONUMMER_BLZ_FIRST_NAME,
																formDTO.getKontonummerBLZfirstName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.KONTONUMMER_BLZ_FIRST_NAME, "validation.error.useonetab");

			}
		}

		else if (firstTabFilled || (!firstTabFilled && !secondTabFilled))
		{
			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.BIC, formDTO.getBic()) || !validateBIC(
					  formDTO))
			{
				bindingResult.rejectValue(FormElementGroupConstants.BIC, "validation.error.bic");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.IBAN, formDTO.getIban())
					  || !validateIBAN(formDTO))
			{
				bindingResult.rejectValue(FormElementGroupConstants.IBAN,
												  HybrispaymentcoreConstants.MessageKey.BankAccountKeys.IBAN_INVALID);

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.IBAN_BAC_FIRST_NAME,
																formDTO.getIbanBACfirstName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.IBAN_BAC_FIRST_NAME, "validation.error.iban.bac.first.name");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.IBAN_BAC_LAST_NAME,
																formDTO.getIbanBAClastName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.IBAN_BAC_LAST_NAME, "validation.error.iban.bac.last.name");

			}
			formDTO.setTabIbanBic(firstTabFilled);
		}

		else if (secondTabFilled)
		{
			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.ACCOUNT_NUM, formDTO.getAccountNumber())
					  || !validateForNumber(formDTO.getAccountNumber()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.ACCOUNT_NUM, "validation.error.account.num");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.BAN_ID_NUMBER, formDTO.getBanIdNumber())
					  || !validateForNumber(formDTO.getBanIdNumber()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.BAN_ID_NUMBER, "validation.error.ban.id.number");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.KONTONUMMER_BLZ_LAST_NAME,
																formDTO.getKontonummerBLZlastName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.KONTONUMMER_BLZ_LAST_NAME, "validation.error.iban.bac.last.name");

			}

			if (!fieldIsNotEmptyOrAlreadyValidated(bindingResult, FormElementGroupConstants.KONTONUMMER_BLZ_FIRST_NAME,
																formDTO.getKontonummerBLZfirstName()))
			{
				bindingResult.rejectValue(FormElementGroupConstants.KONTONUMMER_BLZ_FIRST_NAME,
												  "validation.error.iban.bac.first.name");

			}
			formDTO.setTabKontonummerBlz(secondTabFilled);
		}

		return !bindingResult.hasErrors();
	}

	private boolean fieldIsNotEmptyOrAlreadyValidated(final BindingResult bindingResult, final String field,
																	  final String paramName)
	{
		return StringUtils.isNotEmpty(paramName) || bindingResult.getFieldError(field) != null;
	}

	/**
	 * Validate the graduation date fields on the Student Offer sections.
	 * The graduation date is mandatory field and it should be greater then current system day/time.
	 *
	 * @param formDTO - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return true if dto is valid.
	 */
	@Override
	public boolean validateStudentGraduationDate(final CheckoutFormDTO formDTO, final BindingResult bindingResult)
	{
		boolean validationPassed = true;
		String day = formDTO.getGraduationDay();
		String month = formDTO.getGraduationMonth();
		String year = formDTO.getGraduationYear();
		Date graduationDate = null;
		boolean graduationDateValidationFailed = false;
		try
		{
			graduationDate = FormElementGroupUtil.parseDate(day, month, year);
		}
		catch (ParseException e)
		{
			// incorrect text values in day, month, year fields
			bindingResult.rejectValue(FormElementGroupConstants.STUDENT_GRADUATION_YEAR,
											  "validation.error.studentGraduationDate.format");
			validationPassed = false;
			graduationDateValidationFailed = true;
		}
		if (!graduationDateValidationFailed && graduationDate == null)
		{
			// all of the day, month and year fields are empty
			bindingResult.rejectValue(FormElementGroupConstants.STUDENT_GRADUATION_YEAR,
											  "validation.error.studentGraduationDate.empty");
			validationPassed = false;
			graduationDateValidationFailed = true;
		}
		if (!graduationDateValidationFailed && graduationDate.getTime() <= System.currentTimeMillis())
		{
			// the graduation date should strictly point to the future date, not to past.
			bindingResult.rejectValue(FormElementGroupConstants.STUDENT_GRADUATION_YEAR,
											  "validation.error.studentGraduationDate.expired");
			validationPassed = false;
			graduationDateValidationFailed = true;
		}

		return validationPassed;
	}

	/**
	 * Validate the User Form at Customer Page for product type NEWSLETTER.
	 * The  Anrede , Vorname, Name, Email-adress are mandatory fields.
	 *
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return true if dto is valid.
	 */
	@Override
	public boolean validateNewsletterProductType(final CheckoutFormDTO dto, final BindingResult bindingResult)
	{
		final Map<String, Boolean> stepMandatoryFieldNames =
				  getCheckoutStepService().getStepMandatoryFieldNames(checkoutStepService.getCheckoutStep(dto));
		String fieldName = FormElementGroupConstants.EMAIL;
		if (stepMandatoryFieldNames.keySet().contains(fieldName) && !validateEmail(dto.getEmail()))
		{
			bindingResult.rejectValue(fieldName, "validation.error.email");
		}

		return !bindingResult.hasErrors();
	}

	@Override
	public boolean validatePayment(final BindingResult bindingResult)
	{
		CartData cart = defaultProxyCheckoutFacade.getCheckoutCart();
		if (cart.getPaymentInfo() != null)
		{
			String paymentModeType = ((PaymentInfoData) cart.getPaymentInfo()).getPaymentModeType();

			for (final PaymentModeData paymentModeData : paymentExtFacade.getPaymentContainerData().getModes())
			{
				if (paymentModeData.getType().equals(paymentModeType))
				{
					return true;
				}
			}
			LogHelper.error(LOG, "Invalid payment mode %s for cart with code %s", paymentModeType, cart.getCode());
			bindingResult.reject(Localization.getLocalizedString("checkout.error.authorization.failed"));

			return false;
		}
		else
		{
			return true;
		}
	}



	/**
	 * Checks the entered delivery date corresponds to date format regex pattern "dd.MM.yyyy".
	 * @param deliveryStartDateString delivery date in String format entered to date picker input field .
	 * @return boolean value determined if delivery date correct or not
	 */
	private boolean isDeliveryStartDateCorrect(final String deliveryStartDateString)
	{
		return StringUtils.isNotEmpty(deliveryStartDateString) && deliveryStartDateString.matches(DELIVERY_DATE_REGEX_PATTERN);
	}

	/**
	 * Adjust the specified calendar date value to the start of day.
	 *
	 * @param calendar specified date value.
	 */
	private void adjustToStartOfDay(final Calendar calendar)
	{
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * Adjust the specified calendar date value to the end of day.
	 *
	 * @param calendar specified date value.
	 */
	private void adjustToEndOfDay(final Calendar calendar)
	{
		calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR_OF_DAY);
		calendar.set(Calendar.MINUTE, LAST_MINUTE_OF_HOUR);
		calendar.set(Calendar.SECOND, LAST_SECOND_OF_MINUTE);
		calendar.set(Calendar.MILLISECOND, LAST_MILISECOND_OF_SECOND);
	}

}
