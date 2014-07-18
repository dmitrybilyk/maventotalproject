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
package com.cgi.pacoshop.core.checkout.dynamic.impl;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;

import com.cgi.pacoshop.core.model.PhoneNumberModel;
import java.util.Calendar;
import java.util.Date;

/**
 * Default implementation of FormDTO interface. Base entity for storing data from checkout steps.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v dec 30, 2013
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class CheckoutFormDTO implements FormDTO
{
	static final int FIELD_LENGTH = 80;
	private boolean differentInvoiceAddress;
	private boolean differentShipmentAddress;
	private boolean invoiceWanted;
	private String  title;
	private String  salutation;
	private String  firstName;
	private String  lastName;
	private String  street;
	private String  houseNumber;
	private String  zip;
	private String  city;
	private String  country;
	private String  email;
	// phone numbers
	private String  phonePrefixHome;
	private String  phoneNumberHome;
	private String  phoneExtensionHome;
	private String  phonePrefixBusiness;
	private String  phoneNumberBusiness;
	private String  phoneExtensionBusiness;
	private String  mobilePrefix;
	private String  mobileNumber;
	//private String mobile;
	//private String phone;
	private String  birthDateDay;
	private String  birthDateMonth;
	private String  birthDateYear;
	private String  company;
	private String  positionCompany;
	private String  additionalStreet;
	private String  graduationDay;
	private String  graduationMonth;
	private String  graduationYear;
	private String  milesAndMoreNumber;
	private String  dummyField;
	private String  aboNumber;
	private Date    validatedDate;

	private String referralSalutation;
	private String referralTitle;
	private String referralFirstName;
	private String referralLastName;
	private String referralZip;
	private String referralCity;
	private String referralCountry;
	private String referralAdditionalAddress;
	private String referralStreet;
	private String referralHouseNumber;

	private String  lastNameForMonetaryBonus;
	private String  firstNameForMonetaryBonus;
	private String  ibanBACfirstName;
	private String  ibanBAClastName;
	private String  kontonummerBLZfirstName;
	private String  kontonummerBLZlastName;
	private String  bic;
	private String  iban;
	private String  accountNumber;
	private String  banIdNumber;
	private boolean tabIbanBic;
	private boolean tabKontonummerBlz;


	/**
	 * Constructor.
	 */
	public CheckoutFormDTO()
	{

	}

	/**
	 * Validated date getter.
	 *
	 * @return validatedDate.
	 */
	public Date getValidatedDate()
	{
		if (validatedDate != null)
		{
			return new Date(validatedDate.getTime());
		}

		return null;
	}

	/**
	 * Validated date setter.
	 *
	 * @param validatedDateParam - new validatedDate.
	 */
	public void setValidatedDate(final Date validatedDateParam)
	{
		if (validatedDate != null)
		{
			this.validatedDate = new Date(validatedDate.getTime());
		}
		else
		{
			this.validatedDate = null;
		}
	}

	/**
	 * Title getter.
	 *
	 * @return title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Title setter.
	 *
	 * @param titleParam - new title.
	 */
	public void setTitle(final String titleParam)
	{
		this.title = titleParam;
	}

	/**
	 * Firstname getter.
	 *
	 * @return firstname.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * FirstName setter.
	 *
	 * @param firstNameParam - new firstname.
	 */
	public void setFirstName(final String firstNameParam)
	{
		this.firstName = firstNameParam;
	}

	/**
	 * Lastname getter.
	 *
	 * @return - lastname.
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Lastname setter.
	 *
	 * @param lastNameParam - new lastName .
	 */
	public void setLastName(final String lastNameParam)
	{
		this.lastName = lastNameParam;
	}

	/**
	 * Street getter.
	 *
	 * @return street.
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Street setter.
	 *
	 * @param streetParam - new street.
	 */
	public void setStreet(final String streetParam)
	{
		this.street = streetParam;
	}

	/**
	 * House number getter.
	 * @return houseNumber.
	 */
	public String getHouseNumber()
	{
		return houseNumber;
	}

	/**
	 * House number setter.
	 * @param houseNumber - the new house number.
	 */
	public void setHouseNumber(final String houseNumber)
	{
		this.houseNumber = houseNumber;
	}

	/**
	 * PLZ_ZIP getter.
	 *
	 * @return - PLZ_ZIP code.
	 */
	public String getZip()
	{
		return zip;
	}

	/**
	 * PLZ_ZIP setter.
	 *
	 * @param zipParam - new PLZ_ZIP code.
	 */
	public void setZip(final String zipParam)
	{
		this.zip = zipParam;
	}

	/**
	 * City getter.
	 *
	 * @return City.
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * City setter.
	 *
	 * @param cityParam - new city.
	 */
	public void setCity(final String cityParam)
	{
		this.city = cityParam;
	}

	/**
	 * Country getter.
	 *
	 * @return country.
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Country setter.
	 *
	 * @param countryParam - new country.
	 */
	public void setCountry(final String countryParam)
	{
		this.country = countryParam;
	}

	/**
	 * Email getter.
	 *
	 * @return email.
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Email setter.
	 *
	 * @param emailParam - new email.
	 */
	public void setEmail(final String emailParam)
	{
		this.email = emailParam;
	}

	/**
	 * InvoiceWanted getter.
	 *
	 * @return InvoiceWanted.
	 */
	public boolean isInvoiceWanted()
	{
		return invoiceWanted;
	}

	/**
	 * InvoiceWanted setter.
	 *
	 * @param invoiceWantedParam - new InvoiceWanted value.
	 */
	public void setInvoiceWanted(final boolean invoiceWantedParam)
	{
		this.invoiceWanted = invoiceWantedParam;
	}

	/**
	 * Company getter.
	 *
	 * @return company.
	 */
	public String getCompany()
	{
		return company;
	}

	/**
	 * Company setter.
	 *
	 * @param companyParam - new company.
	 */
	public void setCompany(final String companyParam)
	{
		this.company = companyParam;
	}

	/**
	 * Position Company getter.
	 *
	 * @return - position company.
	 */
	public String getPositionCompany()
	{
		return positionCompany;
	}

	/**
	 * Position company setter.
	 *
	 * @param positionCompanyParam - new position company.
	 */
	public void setPositionCompany(final String positionCompanyParam)
	{
		this.positionCompany = positionCompanyParam;
	}

	/**
	 * Additional street getter.
	 *
	 * @return additional street.
	 */
	public String getAdditionalStreet()
	{
		return additionalStreet;
	}

	/**
	 * Additional Street setter.
	 *
	 * @param additionalStreetParam - new additional Street.
	 */
	public void setAdditionalStreet(final String additionalStreetParam)
	{
		this.additionalStreet = additionalStreetParam;
	}

	/**
	 * Birthday Date getter.
	 *
	 * @return birthday.
	 */
	public String getBirthDateDay()
	{
		return birthDateDay;
	}

	/**
	 * Birthday date setter.
	 *
	 * @param birthDateDayParam - new birthday date.
	 */
	public void setBirthDateDay(final String birthDateDayParam)
	{
		this.birthDateDay = birthDateDayParam;
	}

	/**
	 * BirthMonth getter.
	 *
	 * @return - birthday month.
	 */
	public String getBirthDateMonth()
	{
		return birthDateMonth;
	}

	/**
	 * BirthMonth setter.
	 *
	 * @param birthDateMonthParam - new birthday month.
	 */
	public void setBirthDateMonth(final String birthDateMonthParam)
	{
		this.birthDateMonth = birthDateMonthParam;
	}

	/**
	 * Birthday year getter.
	 *
	 * @return - birthday year.
	 */
	public String getBirthDateYear()
	{
		return birthDateYear;
	}

	/**
	 * Birthday Year setter.
	 *
	 * @param birthDateYearParam - new birthday year.
	 */
	public void setBirthDateYear(final String birthDateYearParam)
	{
		this.birthDateYear = birthDateYearParam;
	}

	/**
	 * Miles and more number getter.
	 *
	 * @return - miles and more number as String.
	 */
	public String getMilesAndMoreNumber()
	{
		return milesAndMoreNumber;
	}

	/**
	 * Miles and More Number setter.
	 *
	 * @param milesAndMoreNumberParam - new Miles and More Number string.
	 */
	public void setMilesAndMoreNumber(final String milesAndMoreNumberParam)
	{
		this.milesAndMoreNumber = milesAndMoreNumberParam;
	}

	/**
	 * The getter of Day part of student graduation date value.
	 * Would be used to determine when the student offer subscription should be ended.
	 *
	 * @return the Day value of student graduation date in form of String.
	 */
	public String getGraduationDay()
	{
		return graduationDay;
	}

	/**
	 * The setter of Day part of student graduation date value.
	 * Would be used to determine when the student offer subscription should be ended.
	 *
	 * @param graduationDay - new Day part value of student graduation date.
	 */
	public void setGraduationDay(final String graduationDay)
	{
		this.graduationDay = graduationDay;
	}

	/**
	 * The getter of Month part of student graduation date value.
	 * Would be used to determine when the student offer subscription should be ended.
	 *
	 * @return the Month value of student graduation date in form of String.
	 */
	public String getGraduationMonth()
	{
		return graduationMonth;
	}

	/**
	 * The setter of Day part of student graduation date value.
	 * Would be used to determine when the student offer subscription should be ended.
	 *
	 * @param graduationMonth - new Day part value of student graduation date.
	 */
	public void setGraduationMonth(final String graduationMonth)
	{
		this.graduationMonth = graduationMonth;
	}

	/**
	 * The getter of Year part of student graduation date value.
	 * Would be used to determine when the student offer subscription should be ended.
	 *
	 * @return the Year value of student graduation date in form of String.
	 */
	public String getGraduationYear()
	{
		return graduationYear;
	}

	/**
	 * The setter of Day part of student graduation date value.
	 * Would be used to determine when the student offer subscription should be ended.
	 *
	 * @param graduationYear - new Day part value of student graduation date.
	 */
	public void setGraduationYear(final String graduationYear)
	{
		this.graduationYear = graduationYear;
	}

	/**
	 * Check is Different Invoice Address set.
	 * @return true if Different Invoice Address used.
	 */
	public boolean isDifferentInvoiceAddress()
	{
		return differentInvoiceAddress;
	}

	/**
	 * Set is Different Invoice Address used.
	 *
	 * @param differentInvoiceAddressParam - is Different Invoice Address should be used.
	 */
	public void setDifferentInvoiceAddress(final boolean differentInvoiceAddressParam)
	{
		this.differentInvoiceAddress = differentInvoiceAddressParam;
	}

	/**
	 * Check is Different Shipment Address set.
	 *
	 * @return true if Different Shipment Address used.
	 */
	public boolean isDifferentShipmentAddress()
	{
		return differentShipmentAddress;
	}

	/**
	 * Set is Different Shipment Address used.
	 *
	 * @param differentShipmentAddressParam - is Different Invoice Shipment should be used.
	 */
	public void setDifferentShipmentAddress(final boolean differentShipmentAddressParam)
	{
		this.differentShipmentAddress = differentShipmentAddressParam;
	}

	/**
	 * @return dummy field.
	 */
	public String getDummyField()
	{
		return dummyField;
	}

	/**
	 * @param dummyFieldParam - new dummyFieldParam
	 */
	public void setDummyField(final String dummyFieldParam)
	{
		this.dummyField = dummyFieldParam;
	}

	/**
	 * Salutation getter.
	 *
	 * @return salutation.
	 */
	public String getSalutation()
	{
		return salutation;
	}

	/**
	 * Salutation setter.
	 *
	 * @param salutationParam - new salutation.
	 */
	public void setSalutation(final String salutationParam)
	{
		this.salutation = salutationParam;
	}

	/**
	 * @return abo number.
	 */
	public String getAboNumber()
	{
		return aboNumber;
	}

	/**
	 * Salutation setter.
	 *
	 * @param aboNumber - abo number.
	 */
	public void setAboNumber(final String aboNumber)
	{
		this.aboNumber = aboNumber;
	}

	/**
	 * @return referral salutation.
	 */
	public String getReferralSalutation()
	{

		return referralSalutation;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralSalutation - referralSalutation.
	 */
	public void setReferralSalutation(final String referralSalutation)
	{
		this.referralSalutation = referralSalutation;
	}

	/**
	 * @return referral firstname.
	 */
	public String getReferralFirstName()
	{
		return referralFirstName;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralFirstName - referralFirstName.
	 */
	public void setReferralFirstName(final String referralFirstName)
	{
		this.referralFirstName = referralFirstName;
	}


	/**
	 * @return referral lastname.
	 */
	public String getReferralLastName()
	{
		return referralLastName;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralLastName - referralLastName.
	 */
	public void setReferralLastName(final String referralLastName)
	{
		this.referralLastName = referralLastName;
	}


	/**
	 * @return referral zip.
	 */
	public String getReferralZip()
	{
		return referralZip;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralZip - referralZip.
	 */
	public void setReferralZip(final String referralZip)
	{
		this.referralZip = referralZip;
	}

	/**
	 * @return referral city.
	 */
	public String getReferralCity()
	{
		return referralCity;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralCity - referralCity.
	 */
	public void setReferralCity(final String referralCity)
	{
		this.referralCity = referralCity;
	}


	/**
	 * @return referral country.
	 */
	public String getReferralCountry()
	{
		return referralCountry;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralCountry - referralCountry.
	 */
	public void setReferralCountry(final String referralCountry)
	{
		this.referralCountry = referralCountry;
	}


	/**
	 * @return referral additional address.
	 */
	public String getReferralAdditionalAddress()
	{
		return referralAdditionalAddress;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralAdditionalAddress - referralAdditionalAddress.
	 */
	public void setReferralAdditionalAddress(final String referralAdditionalAddress)
	{
		this.referralAdditionalAddress = referralAdditionalAddress;
	}

	/**
	 * @return referral additional street and number.
	 */
	public String getReferralStreet()
	{
		return referralStreet;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralStreet - abo number.
	 */
	public void setReferralStreet(final String referralStreet)
	{
		this.referralStreet = referralStreet;
	}

	/**
	 * @return referral house number.
	 */
	public String getReferralHouseNumber()
	{
		return referralHouseNumber;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralHouseNumber - abo number.
	 */
	public void setReferralHouseNumber(final String referralHouseNumber)
	{
		this.referralHouseNumber = referralHouseNumber;
	}

	/**
	 * @return title.
	 */
	public String getReferralTitle()
	{
		return referralTitle;
	}

	/**
	 * @param referralTitle - new validatedDate.
	 */
	public void setReferralTitle(final String referralTitle)
	{
		this.referralTitle = referralTitle;
	}


	/**
	 * @return last name for monetary bonus..
	 */
	public String getLastNameForMonetaryBonus()
	{
		return lastNameForMonetaryBonus;
	}


	/**
	 * @param lastNameForMonetaryBonus - last name for monetary bonus.
	 */
	public void setLastNameForMonetaryBonus(final String lastNameForMonetaryBonus)
	{
		this.lastNameForMonetaryBonus = lastNameForMonetaryBonus;
	}


	/**
	 * @return first name for monetary bonus..
	 */
	public String getFirstNameForMonetaryBonus()
	{
		return firstNameForMonetaryBonus;
	}

	/**
	 * @param firstNameForMonetaryBonus - last name for monetary bonus.
	 */
	public void setFirstNameForMonetaryBonus(final String firstNameForMonetaryBonus)
	{
		this.firstNameForMonetaryBonus = firstNameForMonetaryBonus;
	}


	/**
	 * @return bic
	 */
	public String getBic()
	{
		return bic;
	}

	/**
	 * @param bic - last name for monetary bonus.
	 */
	public void setBic(final String bic)
	{
		this.bic = bic;
	}


	/**
	 * @return iban
	 */
	public String getIban()
	{
		return iban;
	}

	/**
	 * @param iban - last name for monetary bonus.
	 */
	public void setIban(final String iban)
	{
		this.iban = iban;
	}


	/**
	 * @return account number
	 */
	public String getAccountNumber()
	{
		return accountNumber;
	}


	/**
	 * @param accountNumber - account number.
	 */
	public void setAccountNumber(final String accountNumber)
	{
		this.accountNumber = accountNumber;
	}


	/**
	 * @return ban id number
	 */
	public String getBanIdNumber()
	{
		return banIdNumber;
	}


	/**
	 * @param banIdNumber - ban id number
	 */
	public void setBanIdNumber(final String banIdNumber)
	{
		this.banIdNumber = banIdNumber;
	}

	/**
	 * @return firstname from the second tab
	 */
	public String getIbanBACfirstName()
	{
		return ibanBACfirstName;
	}

	/**
	 * @param ibanBACfirstName - firstname from the first tab
	 */
	public void setIbanBACfirstName(final String ibanBACfirstName)
	{
		this.ibanBACfirstName = ibanBACfirstName;
	}

	/**
	 * @return lastname from the second tab
	 */
	public String getIbanBAClastName()
	{
		return ibanBAClastName;
	}

	/**
	 * @param ibanBAClastName - lastname from the first tab
	 */
	public void setIbanBAClastName(final String ibanBAClastName)
	{
		this.ibanBAClastName = ibanBAClastName;
	}

	/**
	 * @return firstname from the second tab
	 */
	public String getKontonummerBLZfirstName()
	{
		return kontonummerBLZfirstName;
	}

	/**
	 * @param kontonummerBLZfirstName - firstname from the second tab
	 */
	public void setKontonummerBLZfirstName(final String kontonummerBLZfirstName)
	{
		this.kontonummerBLZfirstName = kontonummerBLZfirstName;
	}


	/**
	 * @return lastname from the second tab
	 */
	public String getKontonummerBLZlastName()
	{
		return kontonummerBLZlastName;
	}

	/**
	 * @param kontonummerBLZlastName - lastname from the second tab
	 */
	public void setKontonummerBLZlastName(final String kontonummerBLZlastName)
	{
		this.kontonummerBLZlastName = kontonummerBLZlastName;
	}

	/**
	 *
	 * @return tabIbanBic - to show first tab for IBAN/BIC
	 */
	public boolean isTabIbanBic()
	{
		return tabIbanBic;
	}

	/**
	 *
	 * @param tabIbanBic - to show first tab for IBAN/BIC
	 */
	public void setTabIbanBic(final boolean tabIbanBic)
	{
		this.tabIbanBic = tabIbanBic;
	}

	/**
	 *
	 * @return - to show second tab Kontonummer/BLZ
	 */
	public boolean isTabKontonummerBlz()
	{
		return tabKontonummerBlz;
	}

	/**
	 *
	 * @param tabKontonummerBlz - to show second tab Kontonummer/BLZ
	 */
	public void setTabKontonummerBlz(final boolean tabKontonummerBlz)
	{
		this.tabKontonummerBlz = tabKontonummerBlz;
	}

	/**
	 * Prefix for home phone number.
	 * @return phonePrefixHome - as part of home phone number.
	 */
	public String getPhonePrefixHome()
	{
		return phonePrefixHome;
	}

	/**
	 *
	 * @param phonePrefixHome - as part of home phone number.
	 */
	public void setPhonePrefixHome(final String phonePrefixHome)
	{
		this.phonePrefixHome = phonePrefixHome;
	}

	/**
	 * Number for home phone number.
	 * @return phoneNumberHome - as part of home phone number.
	 */
	public String getPhoneNumberHome()
	{
		return phoneNumberHome;
	}

	/**
	 *
	 * @param phoneNumberHome - as part of home phone number.
	 */
	public void setPhoneNumberHome(final String phoneNumberHome)
	{
		this.phoneNumberHome = phoneNumberHome;
	}

	/**
	 * Extension for home phone number.
	 * @return phoneExtensionHome - as part of home phone number.
	 */
	public String getPhoneExtensionHome()
	{
		return phoneExtensionHome;
	}

	/**
	 *
	 * @param phoneExtensionHome - as part of home phone number.
	 */
	public void setPhoneExtensionHome(final String phoneExtensionHome)
	{
		this.phoneExtensionHome = phoneExtensionHome;
	}

	/**
	 * Prefix for business phone number.
	 * @return phonePrefixBusiness - as part of business phone number.
	 */
	public String getPhonePrefixBusiness()
	{
		return phonePrefixBusiness;
	}

	/**
	 *
	 * @param phonePrefixBusiness - as part of business phone number.
	 */
	public void setPhonePrefixBusiness(final String phonePrefixBusiness)
	{
		this.phonePrefixBusiness = phonePrefixBusiness;
	}

	/**
	 * Number for business phone number.
	 * @return phoneNumberBusiness - as part of business phone number.
	 */
	public String getPhoneNumberBusiness()
	{
		return phoneNumberBusiness;
	}

	/**
	 *
	 * @param phoneNumberBusiness - as part of business phone number.
	 */
	public void setPhoneNumberBusiness(final String phoneNumberBusiness)
	{
		this.phoneNumberBusiness = phoneNumberBusiness;
	}

	/**
	 * Extension for business phone number.
	 * @return phoneExtensionBusiness - as part of business phone number.
	 */
	public String getPhoneExtensionBusiness()
	{
		return phoneExtensionBusiness;
	}

	/**
	 *
	 * @param phoneExtensionBusiness - as part of business phone number.
	 */
	public void setPhoneExtensionBusiness(final String phoneExtensionBusiness)
	{
		this.phoneExtensionBusiness = phoneExtensionBusiness;
	}

	/**
	 * Prefix for mobile phone number.
	 * @return mobilePrefix - as part of mobile phone number.
	 */
	public String getMobilePrefix()
	{
		return mobilePrefix;
	}

	/**
	 *
	 * @param mobilePrefix - as part of mobile phone number.
	 */
	public void setMobilePrefix(final String mobilePrefix)
	{
		this.mobilePrefix = mobilePrefix;
	}

	/**
	 * Number for mobile phone number.
	 * @return mobileNumber - as part of mobile phone number.
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 *
	 * @param mobileNumber - as part of mobile phone number.
	 */
	public void setMobileNumber(final String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	/**
     *
     * @param dateOfBirth Date of birth
     */
    public void setDateOfBirth(final Date dateOfBirth)
    {
        if (dateOfBirth != null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateOfBirth);

            setBirthDateDay(String.format(FormElementGroupConstants.DAY_DATE_FORMAT, calendar.get(Calendar.DAY_OF_MONTH)));
            setBirthDateMonth(String.format(FormElementGroupConstants.MONTH_DATE_FORMAT, calendar.get(Calendar.MONTH) + 1));
            setBirthDateYear(String.format(FormElementGroupConstants.YEAR_DATE_FORMAT, calendar.get(Calendar.YEAR)));
        }
        else
        {
            setBirthDateDay(null);
            setBirthDateMonth(null);
            setBirthDateYear(null);
        }
    }

	/**
	 * Set home phone number.
	 * @param phoneNumber the phone number.
	 */
	public void setHomePhoneNumber(final PhoneNumberModel phoneNumber)
	{
		if (phoneNumber != null)
		{
			setPhonePrefixHome(phoneNumber.getPrefix());
			setPhoneExtensionHome(phoneNumber.getExtension());
			setPhoneNumberHome(phoneNumber.getNumber());
		}
	}

	/**
	 * Set business phone number.
	 * @param phoneNumber the phone number.
	 */
	public void setBusinessPhoneNumber(final PhoneNumberModel phoneNumber)
	{
		if (phoneNumber != null)
		{
			setPhonePrefixBusiness(phoneNumber.getPrefix());
			setPhoneExtensionBusiness(phoneNumber.getExtension());
			setPhoneNumberBusiness(phoneNumber.getNumber());
		}
	}

	/**
	 * Set mobile phone number.
	 * @param phoneNumber the phone number.
	 */
	public void setMobilePhoneNumber(final PhoneNumberModel phoneNumber)
	{
		if (phoneNumber != null)
		{
			setMobilePrefix(phoneNumber.getPrefix());
			setMobileNumber(phoneNumber.getNumber());
		}
	}
}
