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
package com.cgi.pacoshop.core.checkout.dynamic.impl;


import java.util.Date;

/**
 * DTO for shipment address.
 *
 * @module build
 * @version 1.0v Feb 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ShipmentAddressDTO
{

	private String salutation;
	private String firstname;
	private String lastname;
	private String email;
	private String street;
	private String houseNumber;
	private String additionalStreet;

	private String postalcode;
	private String town;
	private String country;

	private String title;
	private String titleCode;
	private String company;
	private String countryCode;

	private String businessPartnerId;
	private Date lastUpdateDate;


    /**
     *
     * @return the firstname
     */
	public String getFirstname()
	{
		return firstname;
	}

    /**
     *
     * @param firstname the firstname
     */
	public void setFirstname(final String firstname)
	{
		this.firstname = firstname;
	}

    /**
     *
     * @return lastname
     */
	public String getLastname()
	{
		return lastname;
	}

    /**
     *
     * @param lastname the lastname
     */
	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}

    /**
     *
     * @return postalcode
     */
	public String getPostalcode()
	{
		return postalcode;
	}

    /**
     *
     * @param postalcode the postalcode
     */
	public void setPostalcode(final String postalcode)
	{
		this.postalcode = postalcode;
	}

    /**
     *
     * @return town
     */
	public String getTown()
	{
		return town;
	}

    /**
     *
     * @param town the town
     */
	public void setTown(final String town)
	{
		this.town = town;
	}

    /**
     *
     * @return country
     */
	public String getCountry()
	{
		return country;
	}

    /**
     *
     * @param country the country
     */
	public void setCountry(final String country)
	{
		this.country = country;
	}

	/**
	 *
	 * @return the salutation.
	 */
	public String getSalutation()
	{
		return salutation;
	}

	/**
	 *
	 * @param salutation the salutation.
	 */
	public void setSalutation(final String salutation)
	{
		this.salutation = salutation;
	}

	/**
	 *
	 * @return the title.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 *
	 * @param title the title.
	 */
	public void setTitle(final String title)
	{
		this.title = title;
	}

   /**
    *
    * @return the titleCode.
    */
   public String getTitleCode()
   {
      return titleCode;
   }

   /**
    *
    * @param titleCode the code of Title.
    */
   public void setTitleCode(final String titleCode)
   {
      this.titleCode = titleCode;
   }


	/**
	 *
	 * @return the street.
	 */
	public String getStreet()
	{
		return street;
	}

	/**
	 *
	 * @param street the street.
	 */
	public void setStreet(final String street)
	{
		this.street = street;
	}

	/**
	 * Gets houseNumber.
	 * @return houseNumber.
	 */
	public String getHouseNumber()
	{
		return houseNumber;
	}

	/**
	 * Sets houseNumber.
	 * @param houseNumber - the new house number.
	 */
	public void setHouseNumber(final String houseNumber)
	{
		this.houseNumber = houseNumber;
	}

	/**
	 *
	 * @return the email.
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 *
	 * @param email the email.
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 *
	 * @return the company.
	 */
	public String getCompany()
	{
		return company;
	}

	/**
	 *
	 * @param company the company.
	 */
	public void setCompany(final String company)
	{
		this.company = company;
	}

	/**
	 *
	 * @return the countryCode.
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 *
	 * @param countryCode the countryCode.
	 */
	public void setCountryCode(final String countryCode)
	{
		this.countryCode = countryCode;
	}

	/**
	 * Gets additional address.
	 *
	 * @return the additional address
	 */
	public String getAdditionalStreet()
	{
		return additionalStreet;
	}

	/**
	 * Sets additional address.
	 *
	 * @param additionalStreet
	 *           the additional address
	 */
	public void setAdditionalStreet(final String additionalStreet)
	{
		this.additionalStreet = additionalStreet;
	}

	/**
	 * Gets the business partner id.
	 *
	 * @return the business partner id
	 */
	public String getBusinessPartnerId()
	{
		return businessPartnerId;
	}

	/**
	 * Sets businessPartnerId.
	 *
	 * @param businessPartnerId
	 *           the business partner id
	 */
	public void setBusinessPartnerId(final String businessPartnerId)
	{
		this.businessPartnerId = businessPartnerId;
	}

	/**
	 * Gets the last update date.
	 *
	 * @return the last update date
	 */
	public Date getLastUpdateDate()
	{
		return lastUpdateDate;
	}

	/**
	 * Sets the lastUpdateDate.
	 *
	 * @param lastUpdateDate
	 *           the last update date
	 */
	public void setLastUpdateDate(final Date lastUpdateDate)
	{
		this.lastUpdateDate = lastUpdateDate;
	}
}
