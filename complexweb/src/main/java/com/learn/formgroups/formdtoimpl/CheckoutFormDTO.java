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
package com.learn.formgroups.formdtoimpl;


import com.learn.formgroups.FormDTO;

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
	private String title;
	private String salutation;
	private String firstName;
	private String lastName;
	private String street;
	private String zip;
	private String city;
	private String country;
	private String email;
	private String mobile;
	private String phone;
	private String birthDateDay;
	private String birthDateMonth;
	private String birthDateYear;
	private String company;
	private String positionCompany;
	private String additionalStreet;
	private String graduationDay;
	private String graduationMonth;
	private String graduationYear;
	private String milesAndMoreNumber;
	private String dummyField;
	private String aboNumber;
	private Date validatedDate;

	private String referralSalutation;
	private String referralTitle;
	private String referralFirstName;
	private String referralLastName;
	private String referralZip;
	private String referralCity;
	private String referralCountry;
	private String referralAdditionalAddress;
	private String referralStreetAndNumber;

	private String lastNameForMonetaryBonus;
	private String firstNameForMonetaryBonus;
	private String ibanBACfirstName;
	private String ibanBAClastName;
	private String kontonummerBLZfirstName;
	private String kontonummerBLZlastName;
	private String bic;
	private String iban;
	private String accountNumber;
	private String banIdNumber;
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
	 * Mobile getter.
	 *
	 * @return mobile.
	 */
	public String getMobile()
	{
		return mobile;
	}

	/**
	 * Mobile setter.
	 *
	 * @param mobileParam - new mobile.
	 */
	public void setMobile(final String mobileParam)
	{
		this.mobile = mobileParam;
	}

	/**
	 * Phone getter.
	 *
	 * @return phone.
	 */
	public String getPhone()
	{
		return phone;
	}

	/**
	 * Phone setter.
	 *
	 * @param phoneParam - new Phone.
	 */
	public void setPhone(final String phoneParam)
	{
		this.phone = phoneParam;
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
	public String getReferralStreetAndNumber()
	{
		return referralStreetAndNumber;
	}

	/**
	 * Salutation setter.
	 *
	 * @param referralStreetAndNumber - abo number.
	 */
	public void setReferralStreetAndNumber(final String referralStreetAndNumber)
	{
		this.referralStreetAndNumber = referralStreetAndNumber;
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

}
