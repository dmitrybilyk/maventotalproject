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
package com.learn.formgroups.formdtoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Shipment form.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ievgen Azarenkov <ievgen.azarenkov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 11, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 * @see "https://wiki.hybris.com/"
 */
public class ShipmentInfoFormDTO extends PaymentInfoFormDTO
{
	/**
	 * The DELIVERY _ dATE _ fORMAT _ sTR.
	 */
	public static final String DELIVERY_DATE_FORMAT_STR = "dd.MM.yyyy";
	private static final Logger LOG =
			LoggerFactory.getLogger(ShipmentInfoFormDTO.class);
	private static final String DELIVERY_DATE_FORMAT = "dd.MM.yyyy";
	private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>()
	{
		@Override
		protected SimpleDateFormat initialValue()
		{
			return new SimpleDateFormat(DELIVERY_DATE_FORMAT_STR);
		}
	};

	private String salutation;
	private String title;
	private String firstName;
	private String lastName;
	private String company;
	private String street;
	private String zip;
	private String city;
	private String email;
	private String country;
	private String additionalStreet;
	private boolean deliveryStart;
	private Date deliveryStartDate;
	private boolean deliveryDateBlock;
	private List<ShipmentAddressDTO> addressModels;

	/**
	 * *. Shipment Address For Physical ***************
	 */
	private String newShipmentSalutation;
	private String newShipmentTitle;
	private String newShipmentFirstName;
	private String newShipmentLastName;
	private String newShipmentCompany;
	private String newShipmentStreet;
	private String newShipmentZip;
	private String newShipmentCity;
	private String newShipmentAdditionalStreet;
	private String newShipmentCountry;

	/**
	 * *. Shipment Address For Digital ***************
	 */
	private String newShipmentEmail;

	/**
	 * *. Billing Address  ***************
	 */
	private String billingSalutation;
	private String billingTitle;
	private String billingFirstName;
	private String billingLastName;
	private String billingStreet;
	private String billingAdditionalStreet;
	private String billingZip;
	private String billingCountry;
	private String billingCity;
	private String billingEmail;
	private String billingCompany;


	/**
	 * Gets billing company.
	 *
	 * @return the billing company
	 */
	public String getBillingCompany()
	{
		return billingCompany;
	}

	/**
	 * Sets billing company.
	 *
	 * @param billingCompany the billing company
	 */
	public void setBillingCompany(final String billingCompany)
	{
		this.billingCompany = billingCompany;
	}

	/**
	 * Gets billing salutation.
	 *
	 * @return the billing salutation
	 */
	public String getBillingSalutation()
	{
		return billingSalutation;
	}

	/**
	 * Sets billing salutation.
	 *
	 * @param billingSalutation the billing salutation
	 */
	public void setBillingSalutation(final String billingSalutation)
	{
		this.billingSalutation = billingSalutation;
	}

	/**
	 * Gets billing title.
	 *
	 * @return the billing title
	 */
	public String getBillingTitle()
	{
		return billingTitle;
	}

	/**
	 * Sets billing title.
	 *
	 * @param billingTitle the billing title
	 */
	public void setBillingTitle(final String billingTitle)
	{
		this.billingTitle = billingTitle;
	}

	/**
	 * Gets billing email.
	 *
	 * @return the billing email
	 */
	public String getBillingEmail()
	{
		return billingEmail;
	}

	/**
	 * Sets billing email.
	 *
	 * @param billingEmail the billing email
	 */
	public void setBillingEmail(final String billingEmail)
	{
		this.billingEmail = billingEmail;
	}

	/**
	 * Gets billing first name.
	 *
	 * @return the billing first name
	 */
	public String getBillingFirstName()
	{
		return billingFirstName;
	}

	/**
	 * Sets billing first name.
	 *
	 * @param billingFirstName the billing first name
	 */
	public void setBillingFirstName(final String billingFirstName)
	{
		this.billingFirstName = billingFirstName;
	}

	/**
	 * Gets billing last name.
	 *
	 * @return the billing last name
	 */
	public String getBillingLastName()
	{
		return billingLastName;
	}

	/**
	 * Sets billing last name.
	 *
	 * @param billingLastName the billing last name
	 */
	public void setBillingLastName(final String billingLastName)
	{
		this.billingLastName = billingLastName;
	}

	/**
	 * Gets billing street.
	 *
	 * @return the billing street
	 */
	public String getBillingStreet()
	{
		return billingStreet;
	}

	/**
	 * Sets billing street.
	 *
	 * @param billingStreet the billing street
	 */
	public void setBillingStreet(final String billingStreet)
	{
		this.billingStreet = billingStreet;
	}

	/**
	 * Gets billing additional street.
	 *
	 * @return the billing additional street
	 */
	public String getBillingAdditionalStreet()
	{
		return billingAdditionalStreet;
	}

	/**
	 * Sets billing additional street.
	 *
	 * @param billingAdditionalStreet the billing additional street
	 */
	public void setBillingAdditionalStreet(final String billingAdditionalStreet)
	{
		this.billingAdditionalStreet = billingAdditionalStreet;
	}

	/**
	 * Gets billing zip.
	 *
	 * @return the billing zip
	 */
	public String getBillingZip()
	{
		return billingZip;
	}

	/**
	 * Sets billing zip.
	 *
	 * @param billingZip the billing zip
	 */
	public void setBillingZip(final String billingZip)
	{
		this.billingZip = billingZip;
	}

	/**
	 * Gets billing country.
	 *
	 * @return the billing country
	 */
	public String getBillingCountry()
	{
		return billingCountry;
	}

	/**
	 * Sets billing country.
	 *
	 * @param billingCountry the billing country
	 */
	public void setBillingCountry(final String billingCountry)
	{
		this.billingCountry = billingCountry;
	}

	/**
	 * Gets billing city.
	 *
	 * @return the billing city
	 */
	public String getBillingCity()
	{
		return billingCity;
	}

	/**
	 * Sets billing city.
	 *
	 * @param billingCity the billing city
	 */
	public void setBillingCity(final String billingCity)
	{
		this.billingCity = billingCity;
	}

	/**
	 * Gets country.
	 *
	 * @return the country
	 */
	@Override
	public String getCountry()
	{
		return country;
	}

	/**
	 * Sets country.
	 *
	 * @param country the country
	 */
	@Override
	public void setCountry(final String country)
	{
		this.country = country;
	}

	/**
	 * Gets additional address.
	 *
	 * @return the additional address
	 */
	@Override
	public String getAdditionalStreet()
	{
		return additionalStreet;
	}

	/**
	 * Sets additional address.
	 *
	 * @param additionalStreet the additional address
	 */
	@Override
	public void setAdditionalStreet(final String additionalStreet)
	{
		this.additionalStreet = additionalStreet;
	}

	/**
	 * Gets salutation.
	 *
	 * @return the salutation
	 */
	@Override
	public String getSalutation()
	{
		return salutation;
	}

	/**
	 * Sets salutation.
	 *
	 * @param salutation the salutation
	 */
	@Override
	public void setSalutation(final String salutation)
	{
		this.salutation = salutation;
	}

	/**
	 * Gets first name.
	 *
	 * @return the first name
	 */
	@Override
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Sets first name.
	 *
	 * @param firstName the first name
	 */
	@Override
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Gets last name.
	 *
	 * @return the last name
	 */
	@Override
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Sets last name.
	 *
	 * @param lastName the last name
	 */
	@Override
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Gets company.
	 *
	 * @return the company
	 */
	@Override
	public String getCompany()
	{
		return company;
	}

	/**
	 * Sets company.
	 *
	 * @param company the company
	 */
	@Override
	public void setCompany(final String company)
	{
		this.company = company;
	}

	/**
	 * Gets street.
	 *
	 * @return the street
	 */
	@Override
	public String getStreet()
	{
		return street;
	}

	/**
	 * Sets street.
	 *
	 * @param street the street
	 */
	@Override
	public void setStreet(final String street)
	{
		this.street = street;
	}

	/**
	 * Gets zip.
	 *
	 * @return the zip
	 */
	@Override
	public String getZip()
	{
		return zip;
	}

	/**
	 * Sets zip.
	 *
	 * @param zip the zip
	 */
	@Override
	public void setZip(final String zip)
	{
		this.zip = zip;
	}

	/**
	 * Gets city.
	 *
	 * @return the city
	 */
	@Override
	public String getCity()
	{
		return city;
	}

	/**
	 * Sets city.
	 *
	 * @param city the city
	 */
	@Override
	public void setCity(final String city)
	{
		this.city = city;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	@Override
	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	@Override
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	@Override
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets title.
	 *
	 * @param title the title
	 */
	@Override
	public void setTitle(final String title)
	{
		this.title = title;
	}

	/**
	 * Gets delivery start date.
	 *
	 * @return the delivery start date
	 */
	public Date getDeliveryStartDate()
	{
		if (deliveryStartDate == null)
		{
			return null;
		}
		return new Date(deliveryStartDate.getTime());
	}

	/**
	 * Sets delivery start date.
	 *
	 * @param deliveryStartDate the delivery start date
	 */
	public void setDeliveryStartDate(final Date deliveryStartDate)
	{
		if (deliveryStartDate != null)
		{
			this.deliveryStartDate = new Date(deliveryStartDate.getTime());
		}
		else
		{
			this.deliveryStartDate = null;
		}
	}

	/**
	 * Is delivery start.
	 *
	 * @return the boolean
	 */
	public boolean isDeliveryStart()
	{
		return deliveryStart;
	}

	/**
	 * Sets delivery start.
	 *
	 * @param deliveryStart the delivery start
	 */
	public void setDeliveryStart(final boolean deliveryStart)
	{
		this.deliveryStart = deliveryStart;
	}


	/**** Shipment Address For Physical ****************/
	/**
	 * Gets new shipment salutation.
	 *
	 * @return newShipmentSalutation new shipment salutation
	 */
	public String getNewShipmentSalutation()
	{
		return newShipmentSalutation;
	}

	/**
	 * Sets new shipment salutation.
	 *
	 * @param newShipmentSalutation the newShipmentSalutation
	 */
	public void setNewShipmentSalutation(final String newShipmentSalutation)
	{
		this.newShipmentSalutation = newShipmentSalutation;
	}

	/**
	 * Gets new shipment title.
	 *
	 * @return the newShipmentTitle
	 */
	public String getNewShipmentTitle()
	{
		return newShipmentTitle;
	}

	/**
	 * Sets new shipment title.
	 *
	 * @param newShipmentTitle the newShipmentTitle
	 */
	public void setNewShipmentTitle(final String newShipmentTitle)
	{
		this.newShipmentTitle = newShipmentTitle;
	}

	/**
	 * Gets new shipment first name.
	 *
	 * @return the newShipmentFirstName
	 */
	public String getNewShipmentFirstName()
	{
		return newShipmentFirstName;
	}

	/**
	 * Sets new shipment first name.
	 *
	 * @param newShipmentFirstName the newShipmentFirstName
	 */
	public void setNewShipmentFirstName(final String newShipmentFirstName)
	{
		this.newShipmentFirstName = newShipmentFirstName;
	}

	/**
	 * Gets new shipment last name.
	 *
	 * @return the newShipmentLastName
	 */
	public String getNewShipmentLastName()
	{
		return newShipmentLastName;
	}

	/**
	 * Sets new shipment last name.
	 *
	 * @param newShipmentLastName the newShipmentLastName
	 */
	public void setNewShipmentLastName(final String newShipmentLastName)
	{
		this.newShipmentLastName = newShipmentLastName;
	}

	/**
	 * Gets new shipment company.
	 *
	 * @return the newShipmentCompany
	 */
	public String getNewShipmentCompany()
	{
		return newShipmentCompany;
	}

	/**
	 * Sets new shipment company.
	 *
	 * @param newShipmentCompany the newShipmentCompany
	 */
	public void setNewShipmentCompany(final String newShipmentCompany)
	{
		this.newShipmentCompany = newShipmentCompany;
	}

	/**
	 * Gets new shipment street.
	 *
	 * @return the newShipmentStreet
	 */
	public String getNewShipmentStreet()
	{
		return newShipmentStreet;
	}

	/**
	 * Sets new shipment street.
	 *
	 * @param newShipmentStreet the newShipmentStreet
	 */
	public void setNewShipmentStreet(final String newShipmentStreet)
	{
		this.newShipmentStreet = newShipmentStreet;
	}

	/**
	 * Gets new shipment zip.
	 *
	 * @return the newShipmentZip
	 */
	public String getNewShipmentZip()
	{
		return newShipmentZip;
	}

	/**
	 * Sets new shipment zip.
	 *
	 * @param newShipmentZip the newShipmentZip
	 */
	public void setNewShipmentZip(final String newShipmentZip)
	{
		this.newShipmentZip = newShipmentZip;
	}

	/**
	 * Gets new shipment city.
	 *
	 * @return the newShipmentCity
	 */
	public String getNewShipmentCity()
	{
		return newShipmentCity;
	}

	/**
	 * Sets new shipment city.
	 *
	 * @param newShipmentCity the newShipmentCity
	 */
	public void setNewShipmentCity(final String newShipmentCity)
	{
		this.newShipmentCity = newShipmentCity;
	}

	/**
	 * Gets new shipment additional street.
	 *
	 * @return the newShipmentAdditionalStreet
	 */
	public String getNewShipmentAdditionalStreet()
	{
		return newShipmentAdditionalStreet;
	}

	/**
	 * Sets new shipment additional street.
	 *
	 * @param newShipmentAdditionalStreet the newShipmentAdditionalStreet
	 */
	public void setNewShipmentAdditionalStreet(final String newShipmentAdditionalStreet)
	{
		this.newShipmentAdditionalStreet = newShipmentAdditionalStreet;
	}

	/**
	 * Gets new shipment country.
	 *
	 * @return the newShipmentCountry
	 */
	public String getNewShipmentCountry()
	{
		return newShipmentCountry;
	}

	/**
	 * Sets new shipment country.
	 *
	 * @param newShipmentCountry the newShipmentCountry
	 */
	public void setNewShipmentCountry(final String newShipmentCountry)
	{
		this.newShipmentCountry = newShipmentCountry;
	}

	/**
	 * Gets new shipment email.
	 *
	 * @return the newShipmentEmail
	 */
	public String getNewShipmentEmail()
	{
		return newShipmentEmail;
	}

	/**
	 * Sets new shipment email.
	 *
	 * @param newShipmentEmail the newShipmentEmail
	 */
	public void setNewShipmentEmail(final String newShipmentEmail)
	{
		this.newShipmentEmail = newShipmentEmail;

	}

	/**
	 * Gets delivery start date str.
	 *
	 * @return the DeliveryStartDateStr
	 */
	public String getDeliveryStartDateStr()
	{
		return (deliveryStartDate == null) ? "" : SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().format(deliveryStartDate);
	}

	/**
	 * Sets delivery start date str.
	 *
	 * @param deliveryStartDateStr the deliveryStartDateStr
	 */
	public void setDeliveryStartDateStr(final String deliveryStartDateStr)
	{
		try
		{
			deliveryStartDate = SIMPLE_DATE_FORMAT_THREAD_LOCAL.get().parse(deliveryStartDateStr);
		}
		catch (final ParseException e)
		{
			LOG.warn("setDeliveryStartDateStr() parse error: %s", e);
			deliveryStartDate = null;
		}
	}

	/**
	 * Gets address models.
	 *
	 * @return the getAddressModels
	 */
	public List<ShipmentAddressDTO> getAddressModels()
	{
		if (addressModels != null)
		{
			return addressModels;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Sets address models.
	 *
	 * @param addressModels the addressModels
	 */
	public void setAddressModels(final List<ShipmentAddressDTO> addressModels)
	{
		this.addressModels = addressModels;
	}

	/**
	 * Check delivery date block.
	 *
	 * @return the boolean.
	 */
	public boolean isDeliveryDateBlock()
	{
		return deliveryDateBlock;
	}

	/**
	 * Set delivery date block.
	 *
	 * @param deliveryDateBlock - test.
	 */
	public void setDeliveryDateBlock(final boolean deliveryDateBlock)
	{
		this.deliveryDateBlock = deliveryDateBlock;
	}
}
