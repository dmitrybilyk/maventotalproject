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


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Business partner address fields.
 *
 * @module com.cgi.pacoshop.mock-pacoshop-services
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
	private String postalCode;
	private String city;
	private String country;
	private String email;
	private String email2;
	private String phone;
	private String phone2;
	private String mobil;
	private String mobil2;
	private Date   birthday;

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

	/**Gets mobil2.
	 @return Value of mobil2.*/
	public String getMobil2()
	{
		return mobil2;
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

	/**Sets new mobil.
	 @param mobil New value of mobil.*/
	public void setMobil(final String mobil)
	{
		this.mobil = mobil;
	}

	/**Gets street.
	 @return Value of street.*/
	public String getStreet()
	{
		return street;
	}

	/**Sets new phone.
	 @param phone New value of phone.*/
	public void setPhone(final String phone)
	{
		this.phone = phone;
	}

	/**Gets function.
	 @return Value of function.*/
	public String getFunction()
	{
		return function;
	}

	/**Sets new phone2.
	 @param phone2 New value of phone2.*/
	public void setPhone2(final String phone2)
	{
		this.phone2 = phone2;
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

	/**Gets mobil.
	 @return Value of mobil.*/
	public String getMobil()
	{
		return mobil;
	}

	/**Gets title.
	 @return Value of title.*/
	public String getTitle()
	{
		return title;
	}

	/**Gets phone.
	 @return Value of phone.*/
	public String getPhone()
	{
		return phone;
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

	/**Sets new mobil2.
	 @param mobil2 New value of mobil2.*/
	public void setMobil2(final String mobil2)
	{
		this.mobil2 = mobil2;
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

	/**Gets phone2.
	 @return Value of phone2.*/
	public String getPhone2()
	{
		return phone2;
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
}
