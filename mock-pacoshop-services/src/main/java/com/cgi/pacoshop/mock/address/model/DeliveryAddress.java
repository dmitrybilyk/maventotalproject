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
package com.cgi.pacoshop.mock.address.model;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;


/**
 * Class which describes address object for validation via SAP;
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Apr 03, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see 'https://wiki.hybris.com/'
 */
public class DeliveryAddress implements Serializable
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
	private String birthday;

	// phone numbers
	private String phonePrefixHome;
	private String phoneNumberHome;
	private String phoneExtensionHome;
	private String phonePrefixBusiness;
	private String phoneNumberBusiness;
	private String phoneExtensionBusiness;
	private String mobilePrefix;
	private String mobileNumber;

	/**
	 * Getter for salutation.
	 *
	 * @return salutation.
	 */
	public String getSalutation()
	{
		return salutation;
	}

	/**
	 * Setter for salutation.
	 *
	 * @param salutation salutation.
	 */
	public void setSalutation(final String salutation)
	{
		this.salutation = salutation;
	}

	/**
	 * Getter for title.
	 *
	 * @return title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Setter for title.
	 *
	 * @param title title.
	 */
	public void setTitle(final String title)
	{
		this.title = title;
	}

	/**
	 * Getter for firstname.
	 *
	 * @return firstname.
	 */
	public String getFirstname()
	{
		return firstname;
	}

	/**
	 * Setter for firstname.
	 *
	 * @param firstname firstname.
	 */
	public void setFirstname(final String firstname)
	{
		this.firstname = firstname;
	}

	/**
	 * Getter for lastname.
	 *
	 * @return lastname.
	 */
	public String getLastname()
	{
		return lastname;
	}

	/**
	 * Setter for lastname.
	 *
	 * @param lastname lastname.
	 */
	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}

	/**
	 * Getter for function.
	 *
	 * @return function.
	 */
	public String getFunction()
	{
		return function;
	}

	/**
	 * Setter for function.
	 *
	 * @param function function.
	 */
	public void setFunction(final String function)
	{
		this.function = function;
	}

	/**
	 * Getter for company.
	 *
	 * @return company.
	 */
	public String getCompany()
	{
		return company;
	}

	/**
	 * Setter for company.
	 *
	 * @param company company.
	 */
	public void setCompany(final String company)
	{
		this.company = company;
	}

	/**
	 * Getter for email.
	 *
	 * @return email.
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Setter for email.
	 *
	 * @param email email.
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Getter for email2.
	 *
	 * @return email2.
	 */
	public String getEmail2()
	{
		return email2;
	}

	/**
	 * Setter for email2.
	 *
	 * @param email2 email2.
	 */
	public void setEmail2(final String email2)
	{
		this.email2 = email2;
	}

	/**
	 * Getter for birthday.
	 *
	 * @return birthday.
	 */
	public String getBirthday()
	{
		return birthday;
	}

	/**
	 * Setter for birthday.
	 *
	 * @param birthday birthday.
	 */
	public void setBirthday(final String birthday)
	{
		this.birthday = birthday;
	}

	/**
	 * Getter for address suffix.
	 *
	 * @return address suffix
	 */
	public String getAddressSuffix()
	{
		return addressSuffix;
	}

	/**
	 * Setter for address suffix.
	 *
	 * @param addressSuffix address suffix.
	 */
	public void setAddressSuffix(final String addressSuffix)
	{
		this.addressSuffix = addressSuffix;
	}

	/**
	 * Getter for street.
	 *
	 * @return street
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 * Setter for street.
	 *
	 * @param street street
	 */
	public void setStreet(final String street)
	{
		this.street = street;
	}

	/**
	 * Street number getter.
	 * @return streetNumber.
	 */
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 * Street number setter.
	 * @param streetNumber - the new street number.
	 */
	public void setStreetNumber(final String streetNumber)
	{
		this.streetNumber = streetNumber;
	}

	/**
	 * Getter for country.
	 *
	 * @return country.
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Setter for country.
	 *
	 * @param country country
	 */
	public void setCountry(final String country)
	{
		this.country = country;
	}

	/**
	 * Setter for postal code.
	 * @param postalCode postal code
	 */
	public void setPostalCode(final String postalCode)
	{
		this.postalCode = postalCode;
	}

	/**
	 * Getter for postal code.
	 * @return postal code
	 */
	public String getPostalCode()
	{
		return this.postalCode;
	}

	/**
	 * Getter for city.
	 * @return city.
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Setter for city.
	 * @param city city
	 */
	public void setCity(final String city)
	{
		this.city = city;
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
}

