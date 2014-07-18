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
package com.cgi.pacoshop.fulfilmentprocess.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * This is the address representation in the REST interface of the platform.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module pacoshopfulfilmentprocess
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class SAPAddress implements Serializable
{
    private static final long serialVersionUID = -8040779022150707329L;
    @JsonProperty(value = "salutation")
    private String salutation;
    @JsonProperty(value = "title")
    private String title;
    @JsonProperty(value = "firstname")
    private String firstname;
    @JsonProperty(value = "lastname")
    private String lastname;
    @JsonProperty(value = "function")
    private String function;
    @JsonProperty(value = "company")
    private String company;
    @JsonProperty(value = "addressSuffix")
    private String addressSuffix;
    @JsonProperty(value = "street")
    private String street;
	 @JsonProperty(value = "streetNumber")
    private String streetNumber;
    @JsonProperty(value = "postalCode")
    private String postalCode;
    @JsonProperty(value = "city")
    private String city;
    @JsonProperty(value = "country")
    private String country;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "email2")
    private String email2;

    @JsonProperty(value = "phonePrefixHome")
    private String phonePrefixHome;
    @JsonProperty(value = "phoneNumberHome")
    private String phoneNumberHome;
    @JsonProperty(value = "phoneExtensionHome")
    private String phoneExtensionHome;
    @JsonProperty(value = "phonePrefixBusiness")
    private String phonePrefixBusiness;
    @JsonProperty(value = "phoneNumberBusiness")
    private String phoneNumberBusiness;
    @JsonProperty(value = "phoneExtensionBusiness")
    private String phoneExtensionBusiness;
    @JsonProperty(value = "mobilePrefix")
    private String mobilePrefix;
    @JsonProperty(value = "mobileNumber")
    private String mobileNumber;
    @JsonProperty(value = "birthday")
    private String birthday;


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }


    /**
     * @return the birthday
     */
    public String getBirthday()
    {
        return this.birthday;
    }

    /**
     * @param birthday
     *            the birthday to set.
     */
    public void setBirthday(final String birthday)
    {
        this.birthday = birthday;
    }


    /**
     * @return the salutation
     */
    public String getSalutation()
    {
        return salutation;
    }


    /**
     * @param salutation
     *            the salutation to set
     */
    public void setSalutation(final String salutation)
    {
        this.salutation = salutation;
    }


    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }


    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title)
    {
        this.title = title;
    }


    /**
     * @return the firstname
     */
    public String getFirstname()
    {
        return firstname;
    }


    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstname(final String firstname)
    {
        this.firstname = firstname;
    }


    /**
     * @return the lastname
     */
    public String getLastname()
    {
        return lastname;
    }


    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(final String lastname)
    {
        this.lastname = lastname;
    }


    /**
     * @return the function
     */
    public String getFunction()
    {
        return function;
    }


    /**
     * @param function
     *            the function to set
     */
    public void setFunction(final String function)
    {
        this.function = function;
    }


    /**
     * @return the company
     */
    public String getCompany()
    {
        return company;
    }


    /**
     * @param company
     *            the company to set
     */
    public void setCompany(final String company)
    {
        this.company = company;
    }


    /**
     * @return the addressSuffix
     */
    public String getAddressSuffix()
    {
        return addressSuffix;
    }


    /**
     * @param addressSuffix
     *            the addressSuffix to set
     */
    public void setAddressSuffix(final String addressSuffix)
    {
        this.addressSuffix = addressSuffix;
    }


    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }


    /**
     * @param street
     *            the street to set
     */
    public void setStreet(final String street)
    {
        this.street = street;
    }

   /**
     * @return the streetNumber
     */
    public String getStreetNumber()
    {
        return streetNumber;
    }


    /**
     * @param streetNumber
     *            the streetNumber to set
     */
    public void setStreetNumber(final String streetNumber)
    {
        this.streetNumber = streetNumber;
    }


    /**
     * @return the postalCode
     */
    public String getPostalCode()
    {
        return postalCode;
    }


    /**
     * @param postalCode
     *            the postalCode to set
     */
    public void setPostalCode(final String postalCode)
    {
        this.postalCode = postalCode;
    }


    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }


    /**
     * @param city
     *            the city to set
     */
    public void setCity(final String city)
    {
        this.city = city;
    }


    /**
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }


    /**
     * @param country
     *            the country to set
     */
    public void setCountry(final String country)
    {
        this.country = country;
    }


    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }


    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }


    /**
     * @return the email2
     */
    public String getEmail2()
    {
        return email2;
    }


    /**
     * @param email2
     *            the email2 to set
     */
    public void setEmail2(final String email2)
    {
        this.email2 = email2;
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
