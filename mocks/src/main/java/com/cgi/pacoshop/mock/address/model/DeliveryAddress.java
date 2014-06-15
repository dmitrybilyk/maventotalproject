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
 * @see https://wiki.hybris.com/
 */
public class DeliveryAddress implements Serializable
{
	@XmlAttribute(name = "address_suffix")
	private String addressSuffix;
	private String street;
	private String country;
	@XmlAttribute(name = "postal_code")
	private String postalCode;
	private String city;

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
}

