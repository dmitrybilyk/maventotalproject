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
package com.cgi.pacoshop.mock.servicebus.service.impl;


import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Business partner address fields.
 *
 * @module mock-pacoshop-services
 * @version 1.0v Feb 27, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessPartnerData
{
	private String salutation;
	private String title;
	private String firstname;
	private String lastname;
	private String function;
	private String company;
	private String addressSuffix;
	private String street;
	private String streetNumber;
	private String postalCode;
	private String city;
	private String country;
	private String email;
	private String email2;
	private Date   birthday;
	private String phonePrefixHome;
	private String phoneNumberHome;
	private String phoneExtensionHome;
	private String phonePrefixBusiness;
	private String phoneNumberBusiness;
	private String phoneExtensionBusiness;
	private String mobilePrefix;
	private String mobileNumber;

	/*public BusinessPartnerData()
	{
		//
	}*/

	/**Sets new title.
	 @param title New value of title.*/
	public void setTitle(final String title)
	{
		this.title = title;
	}

	/**Gets email2.
	 @return Value of email2.*/
	public String getEmail2()
	{
		return email2;
	}

	/**Sets new function.
	 @param function New value of function.*/
	public void setFunction(final String function)
	{
		this.function = function;
	}

	/**Sets new lastname.
	 @param lastname New value of lastname.*/
	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}


	/**Gets salutation.
	 @return Value of salutation.*/
	public String getSalutation()
	{
		return salutation;
	}

	/**Sets new postalCode.
	 @param postalCode New value of postalCode.*/
	public void setPostalCode(final String postalCode)
	{
		this.postalCode = postalCode;
	}

	/**Gets company.
	 @return Value of company.*/
	public String getCompany()
	{
		return company;
	}

	/**Gets street.
	 @return Value of street.*/
	public String getStreet()
	{
		return street;
	}


	/**Gets function.
	 @return Value of function.*/
	public String getFunction()
	{
		return function;
	}

	/**Gets postalCode.
	 @return Value of postalCode.*/
	public String getPostalCode()
	{
		return postalCode;
	}

	/**Sets new country.
	 @param country New value of country.*/
	public void setCountry(final String country)
	{
		this.country = country;
	}

	/**Sets new street.
	 @param street New value of street.*/
	public void setStreet(final String street)
	{
		this.street = street;
	}

	/**Sets new salutation.
	 @param salutation New value of salutation.*/
	public void setSalutation(final String salutation)
	{
		this.salutation = salutation;
	}

	/**Gets birthday.
	 @return Value of birthday.*/
	public Date getBirthday()
	{
		return birthday;
	}

	/**Gets firstname.
	 @return Value of firstname.*/
	public String getFirstname()
	{
		return firstname;
	}

	/**Gets title.
	 @return Value of title.*/
	public String getTitle()
	{
		return title;
	}

	/**Sets new company.
	 @param company New value of company.*/
	public void setCompany(final String company)
	{
		this.company = company;
	}

	/**Sets new addressSuffix.
	 @param addressSuffix New value of addressSuffix.*/
	public void setAddressSuffix(final String addressSuffix)
	{
		this.addressSuffix = addressSuffix;
	}

	/**Sets new email.
	 @param email New value of email.*/
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**Gets city.
	 @return Value of city.*/
	public String getCity()
	{
		return city;
	}

	/**Sets new firstname.
	 @param firstname New value of firstname.*/
	public void setFirstname(final String firstname)
	{
		this.firstname = firstname;
	}

	/**Gets country.
	 @return Value of country.*/
	public String getCountry()
	{
		return country;
	}

	/**Sets new birthday.
	 @param birthday New value of birthday.*/
	public void setBirthday(final Date birthday)
	{
		this.birthday = birthday;
	}

	/**Gets addressSuffix.
	 @return Value of addressSuffix.*/
	public String getAddressSuffix()
	{
		return addressSuffix;
	}

	/**Sets new email2.
	 @param email2 New value of email2.*/
	public void setEmail2(final String email2)
	{
		this.email2 = email2;
	}

	/**Gets lastname.
	 @return Value of lastname.*/
	public String getLastname()
	{
		return lastname;
	}

	/**Gets email.
	 @return Value of email.*/
	public String getEmail()
	{
		return email;
	}

	/**Sets new city.
	 @param city New value of city.*/
	public void setCity(final String city)
	{
		this.city = city;
	}

	/**
	 *
	 * @return phonePrefixHome
	 */
	public String getPhonePrefixHome()
	{
		return phonePrefixHome;
	}

	/**
	 *
	 * @param phonePrefixHome phonePrefixHome
	 */
	public void setPhonePrefixHome(final String phonePrefixHome)
	{
		this.phonePrefixHome = phonePrefixHome;
	}

	/**
	 *
	 * @return phoneNumberHome
	 */
	public String getPhoneNumberHome()
	{
		return phoneNumberHome;
	}

	/**
	 *
	 * @param phoneNumberHome phoneNumberHome
	 */
	public void setPhoneNumberHome(final String phoneNumberHome)
	{
		this.phoneNumberHome = phoneNumberHome;
	}

	/**
	 *
	 * @return phoneExtensionHome
	 */
	public String getPhoneExtensionHome()
	{
		return phoneExtensionHome;
	}

	/**
	 *
	 * @param phoneExtensionHome phoneExtensionHome
	 */
	public void setPhoneExtensionHome(final String phoneExtensionHome)
	{
		this.phoneExtensionHome = phoneExtensionHome;
	}

	/**
	 *
	 * @return phoneExtensionHome
	 */
	public String getPhonePrefixBusiness()
	{
		return phonePrefixBusiness;
	}

	/**
	 *
	 * @param phonePrefixBusiness phonePrefixBusiness
	 */
	public void setPhonePrefixBusiness(final String phonePrefixBusiness)
	{
		this.phonePrefixBusiness = phonePrefixBusiness;
	}

	/**
	 *
	 * @return phoneNumberBusiness
	 */
	public String getPhoneNumberBusiness()
	{
		return phoneNumberBusiness;
	}

	/**
	 *
	 * @param phoneNumberBusiness phoneNumberBusiness
	 */
	public void setPhoneNumberBusiness(final String phoneNumberBusiness)
	{
		this.phoneNumberBusiness = phoneNumberBusiness;
	}

	/**
	 *
	 * @return phoneExtensionBusiness
	 */
	public String getPhoneExtensionBusiness()
	{
		return phoneExtensionBusiness;
	}

	/**
	 *
	 * @param phoneExtensionBusiness phoneExtensionBusiness
	 */
	public void setPhoneExtensionBusiness(final String phoneExtensionBusiness)
	{
		this.phoneExtensionBusiness = phoneExtensionBusiness;
	}

	/**
	 *
	 * @return mobilePrefix
	 */
	public String getMobilePrefix()
	{
		return mobilePrefix;
	}

	/**
	 *
	 * @param mobilePrefix mobilePrefix
	 */
	public void setMobilePrefix(final String mobilePrefix)
	{
		this.mobilePrefix = mobilePrefix;
	}

	/**
	 *
	 * @return mobileNumber
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 *
	 * @param mobileNumber mobileNumber
	 */
	public void setMobileNumber(final String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	/**
	 *
	 * @return streetNumber
	 */
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 *
	 * @param streetNumber streetNumber
	 */
	public void setStreetNumber(final String streetNumber)
	{
		this.streetNumber = streetNumber;
	}
}
