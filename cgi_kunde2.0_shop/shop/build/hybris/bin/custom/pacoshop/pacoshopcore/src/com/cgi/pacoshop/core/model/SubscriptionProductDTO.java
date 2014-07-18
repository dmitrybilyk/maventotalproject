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
package com.cgi.pacoshop.core.model;


import com.cgi.pacoshop.core.service.CustomJsonStringTrimmerDeserializer;
import java.util.ArrayList;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * SubscriptionProductDTO used by SAP-MSD web client.
 *
 * @module pacoshopcore
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class SubscriptionProductDTO extends SingleProductDTO
{
	private boolean     onlineOffer;
	private boolean     mandatoryAddress;
	private boolean     mandatoryPhone;
	private boolean     mandatoryMobile;
	private boolean     mandatoryEmail;
	private boolean     mandatoryOptIn;
	private boolean     otherBrokerAllowed;
	private String bonusProduct;
	private Price bonusProductExtraPayment;
	private Price bonusAmount;
	private Integer bonusMiles;
	private String bonusDescription;
	private String bonusPicture;
	private Integer     communicatedBillingFrequencySample;
	private Integer     communicatedBillingFrequencyConsequence;
	private Integer     bookedBillingFrequencySample;
	private Integer     bookedBillingFrequencyConsequence;
	private Price       communicatedPriceSample;
	private Price       communicatedPriceConsequence;
	private Price       bookedPriceSample;
	private Price       bookedPriceConsequence;
	private boolean     termOfServiceRenewalSample;
	private boolean     termOfServiceRenewalConsequence;
	private Integer     discountQuarter;
	private Integer     discountHalfYear;
	private Integer     discountYear;
	private Integer     frequencySample;
	private Integer     frequencyConsequence;
	private ProductUnit unit;
	private ArrayList<String> validPayments; // list of allowed payment methods
	private ArrayList<String> validDeliveryCountries; // list of allowed delivery countries

	/**
	 * Is online offer.
	 *
	 * @return the boolean
	 */
	public boolean isOnlineOffer()
	{
		return onlineOffer;
	}

	/**
	 * Sets online offer.
	 *
	 * @param onlineOffer the online offer
	 */
	public void setOnlineOffer(final boolean onlineOffer)
	{
		this.onlineOffer = onlineOffer;
	}

	/**
	 * Is mandatory address.
	 *
	 * @return the boolean
	 */
	public boolean isMandatoryAddress()
	{
		return mandatoryAddress;
	}

	/**
	 * Sets mandatory address.
	 *
	 * @param mandatoryAddress the mandatory address
	 */
	public void setMandatoryAddress(final boolean mandatoryAddress)
	{
		this.mandatoryAddress = mandatoryAddress;
	}

	/**
	 * Is mandatory phone.
	 *
	 * @return the boolean
	 */
	public boolean isMandatoryPhone()
	{
		return mandatoryPhone;
	}

	/**
	 * Sets mandatory phone.
	 *
	 * @param mandatoryPhone the mandatory phone
	 */
	public void setMandatoryPhone(final boolean mandatoryPhone)
	{
		this.mandatoryPhone = mandatoryPhone;
	}

	/**
	 * Is mandatory mobile.
	 *
	 * @return the boolean
	 */
	public boolean isMandatoryMobile()
	{
		return mandatoryMobile;
	}

	/**
	 * Sets mandatory mobile.
	 *
	 * @param mandatoryMobile the mandatory mobile
	 */
	public void setMandatoryMobile(final boolean mandatoryMobile)
	{
		this.mandatoryMobile = mandatoryMobile;
	}

	/**
	 * Is mandatory email.
	 *
	 * @return the boolean
	 */
	public boolean isMandatoryEmail()
	{
		return mandatoryEmail;
	}

	/**
	 * Sets mandatory email.
	 *
	 * @param mandatoryEmail the mandatory email
	 */
	public void setMandatoryEmail(final boolean mandatoryEmail)
	{
		this.mandatoryEmail = mandatoryEmail;
	}

	/**
	 * Is mandatory opt in.
	 *
	 * @return the boolean
	 */
	public boolean isMandatoryOptIn()
	{
		return mandatoryOptIn;
	}

	/**
	 * Sets mandatory opt in.
	 *
	 * @param mandatoryOptIn the mandatory opt in
	 */
	public void setMandatoryOptIn(final boolean mandatoryOptIn)
	{
		this.mandatoryOptIn = mandatoryOptIn;
	}

	/**
	 * Is other broker allowed.
	 *
	 * @return the boolean
	 */
	public boolean isOtherBrokerAllowed()
	{
		return otherBrokerAllowed;
	}

	/**
	 * Sets other broker allowed.
	 *
	 * @param otherBrokerAllowed the other broker allowed
	 */
	public void setOtherBrokerAllowed(final boolean otherBrokerAllowed)
	{
		this.otherBrokerAllowed = otherBrokerAllowed;
	}

	/**
	 * Gets communicated billing frequency sample.
	 *
	 * @return the communicated billing frequency sample
	 */
	public Integer getCommunicatedBillingFrequencySample()
	{
		return communicatedBillingFrequencySample;
	}

	/**
	 * Sets communicated billing frequency sample.
	 *
	 * @param communicatedBillingFrequencySample the communicated billing frequency sample
	 */
	public void setCommunicatedBillingFrequencySample(final Integer communicatedBillingFrequencySample)
	{
		this.communicatedBillingFrequencySample = communicatedBillingFrequencySample;
	}

	/**
	 * Gets communicated billing frequency consequence.
	 *
	 * @return the communicated billing frequency consequence
	 */
	public Integer getCommunicatedBillingFrequencyConsequence()
	{
		return communicatedBillingFrequencyConsequence;
	}

	/**
	 * Sets communicated billing frequency consequence.
	 *
	 * @param communicatedBillingFrequencyConsequence the communicated billing frequency consequence
	 */
	public void setCommunicatedBillingFrequencyConsequence(final Integer communicatedBillingFrequencyConsequence)
	{
		this.communicatedBillingFrequencyConsequence = communicatedBillingFrequencyConsequence;
	}

	/**
	 * Gets booked billing frequency sample.
	 *
	 * @return the booked billing frequency sample
	 */
	public Integer getBookedBillingFrequencySample()
	{
		return bookedBillingFrequencySample;
	}

	/**
	 * Sets booked billing frequency sample.
	 *
	 * @param bookedBillingFrequencySample the booked billing frequency sample
	 */
	public void setBookedBillingFrequencySample(final Integer bookedBillingFrequencySample)
	{
		this.bookedBillingFrequencySample = bookedBillingFrequencySample;
	}

	/**
	 * Gets booked billing frequency consequence.
	 *
	 * @return the booked billing frequency consequence
	 */
	public Integer getBookedBillingFrequencyConsequence()
	{
		return bookedBillingFrequencyConsequence;
	}

	/**
	 * Sets booked billing frequency consequence.
	 *
	 * @param bookedBillingFrequencyConsequence the booked billing frequency consequence
	 */
	public void setBookedBillingFrequencyConsequence(final Integer bookedBillingFrequencyConsequence)
	{
		this.bookedBillingFrequencyConsequence = bookedBillingFrequencyConsequence;
	}

	/**
	 * Gets communicated price sample.
	 *
	 * @return the communicated price sample
	 */
	public Price getCommunicatedPriceSample()
	{
		return communicatedPriceSample;
	}

	/**
	 * Sets communicated price sample.
	 *
	 * @param communicatedPriceSample the communicated price sample
	 */
	public void setCommunicatedPriceSample(final Price communicatedPriceSample)
	{
		this.communicatedPriceSample = communicatedPriceSample;
	}

	/**
	 * Gets communicated price consequence.
	 *
	 * @return the communicated price consequence
	 */
	public Price getCommunicatedPriceConsequence()
	{
		return communicatedPriceConsequence;
	}

	/**
	 * Sets communicated price consequence.
	 *
	 * @param communicatedPriceConsequence the communicated price consequence
	 */
	public void setCommunicatedPriceConsequence(final Price communicatedPriceConsequence)
	{
		this.communicatedPriceConsequence = communicatedPriceConsequence;
	}

	/**
	 * Gets booked price sample.
	 *
	 * @return the booked price sample
	 */
	public Price getBookedPriceSample()
	{
		return bookedPriceSample;
	}

	/**
	 * Sets booked price sample.
	 *
	 * @param bookedPriceSample the booked price sample
	 */
	public void setBookedPriceSample(final Price bookedPriceSample)
	{
		this.bookedPriceSample = bookedPriceSample;
	}

	/**
	 * Gets booked price consequence.
	 *
	 * @return the booked price consequence
	 */
	public Price getBookedPriceConsequence()
	{
		return bookedPriceConsequence;
	}

	/**
	 * Sets booked price consequence.
	 *
	 * @param bookedPriceConsequence the booked price consequence
	 */
	public void setBookedPriceConsequence(final Price bookedPriceConsequence)
	{
		this.bookedPriceConsequence = bookedPriceConsequence;
	}

	/**
	 * Is term of service renewal sample.
	 *
	 * @return the boolean
	 */
	public boolean isTermOfServiceRenewalSample()
	{
		return termOfServiceRenewalSample;
	}

	/**
	 * Sets term of service renewal sample.
	 *
	 * @param termOfServiceRenewalSample the term of service renewal sample
	 */
	public void setTermOfServiceRenewalSample(final boolean termOfServiceRenewalSample)
	{
		this.termOfServiceRenewalSample = termOfServiceRenewalSample;
	}

	/**
	 * Is term of service renewal consequence.
	 *
	 * @return the boolean
	 */
	public boolean isTermOfServiceRenewalConsequence()
	{
		return termOfServiceRenewalConsequence;
	}

	/**
	 * Sets term of service renewal consequence.
	 *
	 * @param termOfServiceRenewalConsequence the term of service renewal consequence
	 */
	public void setTermOfServiceRenewalConsequence(final boolean termOfServiceRenewalConsequence)
	{
		this.termOfServiceRenewalConsequence = termOfServiceRenewalConsequence;
	}

	/**
	 * Gets discount quarter.
	 *
	 * @return the discount quarter
	 */
	public Integer getDiscountQuarter()
	{
		return discountQuarter;
	}

	/**
	 * Sets discount quarter.
	 *
	 * @param discountQuarter the discount quarter
	 */
	public void setDiscountQuarter(final Integer discountQuarter)
	{
		this.discountQuarter = discountQuarter;
	}

	/**
	 * Gets discount half year.
	 *
	 * @return the discount half year
	 */
	public Integer getDiscountHalfYear()
	{
		return discountHalfYear;
	}

	/**
	 * Sets discount half year.
	 *
	 * @param discountHalfYear the discount half year
	 */
	public void setDiscountHalfYear(final Integer discountHalfYear)
	{
		this.discountHalfYear = discountHalfYear;
	}

	/**
	 * Gets discount year.
	 *
	 * @return the discount year
	 */
	public Integer getDiscountYear()
	{
		return discountYear;
	}

	/**
	 * Sets discount year.
	 *
	 * @param discountYear the discount year
	 */
	public void setDiscountYear(final Integer discountYear)
	{
		this.discountYear = discountYear;
	}

	/**
	 * Gets frequency sample.
	 *
	 * @return the frequency sample
	 */
	public Integer getFrequencySample()
	{
		return frequencySample;
	}

	/**
	 * Sets frequency sample.
	 *
	 * @param frequencySample the frequency sample
	 */
	public void setFrequencySample(final Integer frequencySample)
	{
		this.frequencySample = frequencySample;
	}

	/**
	 * Gets frequency consequence.
	 *
	 * @return the frequency consequence
	 */
	public Integer getFrequencyConsequence()
	{
		return frequencyConsequence;
	}

	/**
	 * Sets frequency consequence.
	 *
	 * @param frequencyConsequence the frequency consequence
	 */
	public void setFrequencyConsequence(final Integer frequencyConsequence)
	{
		this.frequencyConsequence = frequencyConsequence;
	}

	/**
	 * Gets unit.
	 *
	 * @return the unit
	 */
	public ProductUnit getUnit()
	{
		return unit;
	}

	/**
	 * Sets unit.
	 *
	 * @param unit the unit
	 */
	public void setUnit(final ProductUnit unit)
	{
		this.unit = unit;
	}

	/**
	 * Gets valid payments.
	 *
	 * @return the valid payments
	 */
	public ArrayList<String> getValidPayments()
	{
		return validPayments;
	}

	/**
	 * Sets valid payments.
	 *
	 * @param validPayments the valid payments
	 */
	public void setValidPayments(final ArrayList<String> validPayments)
	{
		this.validPayments = validPayments;
	}

	/**
	 * Gets valid delivery countries.
	 *
	 * @return the valid delivery countries
	 */
	public ArrayList<String> getValidDeliveryCountries()
	{
		return validDeliveryCountries;
	}

	/**
	 * Sets valid delivery countries.
	 *
	 * @param validDeliveryCountries the valid delivery countries
	 */
	public void setValidDeliveryCountries(final ArrayList<String> validDeliveryCountries)
	{
		this.validDeliveryCountries = validDeliveryCountries;
	}

	/**
	 * Gets bonus product.
	 *
	 * @return the bonus product
	 */
	public String getBonusProduct()
	{
		return bonusProduct;
	}

	/**
	 * Sets bonus product.
	 *
	 * @param bonusProduct the bonus product
	 */
	public void setBonusProduct(final String bonusProduct)
	{
		this.bonusProduct = bonusProduct;
	}

	/**
	 * Gets bonus product extra payment.
	 *
	 * @return the bonus product extra payment
	 */
	public Price getBonusProductExtraPayment()
	{
		return bonusProductExtraPayment;
	}

	/**
	 * Sets bonus product extra payment.
	 *
	 * @param bonusProductExtraPayment the bonus product extra payment
	 */
	public void setBonusProductExtraPayment(final Price bonusProductExtraPayment)
	{
		this.bonusProductExtraPayment = bonusProductExtraPayment;
	}

	/**
	 * Gets bonus amount.
	 *
	 * @return the bonus amount
	 */
	public Price getBonusAmount()
	{
		return bonusAmount;
	}

	/**
	 * Sets bonus amount.
	 *
	 * @param bonusAmount the bonus amount
	 */
	public void setBonusAmount(final Price bonusAmount)
	{
		this.bonusAmount = bonusAmount;
	}

	/**
	 * Gets bonus miles.
	 *
	 * @return the bonus miles
	 */
	public Integer getBonusMiles()
	{
		return bonusMiles;
	}

	/**
	 * Sets bonus miles.
	 *
	 * @param bonusMiles the bonus miles
	 */
	public void setBonusMiles(final Integer bonusMiles)
	{
		this.bonusMiles = bonusMiles;
	}

	/**
	 * Gets bonus description.
	 *
	 * @return the bonus description
	 */
	public String getBonusDescription()
	{
		return bonusDescription;
	}

	/**
	 * Sets bonus description.
	 *
	 * @param bonusDescription the bonus description
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setBonusDescription(final String bonusDescription)
	{
		this.bonusDescription = bonusDescription;
	}

	/**
	 * Gets bonus picture.
	 *
	 * @return the bonus picture
	 */
	public String getBonusPicture()
	{
		return bonusPicture;
	}

	/**
	 * Sets bonus picture.
	 *
	 * @param bonusPicture the bonus picture
	 */
	@JsonDeserialize(using = CustomJsonStringTrimmerDeserializer.class)
	public void setBonusPicture(final String bonusPicture)
	{
		this.bonusPicture = bonusPicture;
	}
}
