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
package com.cgi.pacoshop.mock.servicebus.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity for single productList
 *
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright	2013 symmetrics - a CGI Group brand
 *
 *
 */
public class SubscriptionProduct extends SingleProduct
{
	private boolean onlineOffer;
	private boolean mandatoryAddress;
	private boolean mandatoryPhone;
	private boolean mandatoryMobile;
	private boolean mandatoryEmail;
	private boolean mandatoryOptIn;
	private boolean otherBrokerAllowed;
	private String  bonusProduct;
	private Price   bonusProductExtraPayment;
	private Price   bonusAmount;
	private Integer bonusMiles;
	private String  bonusDescription;
	private String  bonusPicture;
	private int     communicatedBillingFrequencySample;
	private int     communicatedBillingFrequencyConsequence;
	private int     bookedBillingFrequencySample;
	private int     bookedBillingFrequencyConsequence;
	private Price   communicatedPriceSample;
	private Price   communicatedPriceConsequence;
	private Price   bookedPriceSample;
	private Price   bookedPriceConsequence;
	private boolean termOfServiceRenewalSample;
	private boolean termOfServiceRenewalConsequence;
	private int     discountQuarter;
	private int     discountHalfYear;
	private int     discountYear;
	private int     frequencySample;
	private int     frequencyConsequence;
	private String  unit;

	private List<String> validPayments          = new ArrayList<String>();
	private List<String> validDeliveryCountries = new ArrayList<String>();


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
	 * @param onlineOfferParam the online offer param
	 */
	public void setOnlineOffer(final boolean onlineOfferParam)
	{
		this.onlineOffer = onlineOfferParam;
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
	 * @param mandatoryAddressParam the mandatory address param
	 */
	public void setMandatoryAddress(final boolean mandatoryAddressParam)
	{
		this.mandatoryAddress = mandatoryAddressParam;
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
	 * @param mandatoryPhoneParam the mandatory phone param
	 */
	public void setMandatoryPhone(final boolean mandatoryPhoneParam)
	{
		this.mandatoryPhone = mandatoryPhoneParam;
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
	 * @param mandatoryMobileParam the mandatory mobile param
	 */
	public void setMandatoryMobile(final boolean mandatoryMobileParam)
	{
		this.mandatoryMobile = mandatoryMobileParam;
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
	 * @param mandatoryEmailParam the mandatory email param
	 */
	public void setMandatoryEmail(final boolean mandatoryEmailParam)
	{
		this.mandatoryEmail = mandatoryEmailParam;
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
	 * @param mandatoryOptInParam the mandatory opt in param
	 */
	public void setMandatoryOptIn(final boolean mandatoryOptInParam)
	{
		this.mandatoryOptIn = mandatoryOptInParam;
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
	 * @param otherBrokerAllowedParam the other broker allowed param
	 */
	public void setOtherBrokerAllowed(final boolean otherBrokerAllowedParam)
	{
		this.otherBrokerAllowed = otherBrokerAllowedParam;
	}

	/**
	 * Is bonus product.
	 *
	 * @return the string
	 */
	public String isBonusProduct()
	{
		return bonusProduct;
	}

	/**
	 * Sets bonus product.
	 *
	 * @param bonusProductParam the bonus product param
	 */
	public void setBonusProduct(final String bonusProductParam)
	{
		this.bonusProduct = bonusProductParam;
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
	 * @param bonusProductExtraPaymentParam the bonus product extra payment param
	 */
	public void setBonusProductExtraPayment(final Price bonusProductExtraPaymentParam)
	{
		this.bonusProductExtraPayment = bonusProductExtraPaymentParam;
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
	 * @param bonusAmountParam the bonus amount param
	 */
	public void setBonusAmount(final Price bonusAmountParam)
	{
		this.bonusAmount = bonusAmountParam;
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
	 * @param bonusMilesParam the bonus miles param
	 */
	public void setBonusMiles(final Integer bonusMilesParam)
	{
		this.bonusMiles = bonusMilesParam;
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
	 * @param bonusDescriptionParam the bonus description param
	 */
	public void setBonusDescription(final String bonusDescriptionParam)
	{
		this.bonusDescription = bonusDescriptionParam;
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
	 * @param bonusPictureParam the bonus picture param
	 */
	public void setBonusPicture(final String bonusPictureParam)
	{
		this.bonusPicture = bonusPictureParam;
	}

	/**
	 * Gets communicated billing frequency sample.
	 *
	 * @return the communicated billing frequency sample
	 */
	public int getCommunicatedBillingFrequencySample()
	{
		return communicatedBillingFrequencySample;
	}

	/**
	 * Sets communicated billing frequency sample.
	 *
	 * @param communicatedBillingFrequencySampleParam the communicated billing frequency sample param
	 */
	public void setCommunicatedBillingFrequencySample(final int communicatedBillingFrequencySampleParam)
	{
		this.communicatedBillingFrequencySample = communicatedBillingFrequencySampleParam;
	}

	/**
	 * Gets communicated billing frequency consequence.
	 *
	 * @return the communicated billing frequency consequence
	 */
	public int getCommunicatedBillingFrequencyConsequence()
	{
		return communicatedBillingFrequencyConsequence;
	}

	/**
	 * Sets communicated billing frequency consequence.
	 *
	 * @param communicatedBillingFrequencyConsequenceParam the communicated billing frequency consequence
	 */
	public void setCommunicatedBillingFrequencyConsequence(final int communicatedBillingFrequencyConsequenceParam)
	{
		this.communicatedBillingFrequencyConsequence = communicatedBillingFrequencyConsequenceParam;
	}

	/**
	 * Gets booked billing frequency sample.
	 *
	 * @return the booked billing frequency sample
	 */
	public int getBookedBillingFrequencySample()
	{
		return bookedBillingFrequencySample;
	}

	/**
	 * Sets booked billing frequency sample.
	 *
	 * @param bookedBillingFrequencySampleParam the booked billing frequency sample
	 */
	public void setBookedBillingFrequencySample(final int bookedBillingFrequencySampleParam)
	{
		this.bookedBillingFrequencySample = bookedBillingFrequencySampleParam;
	}

	/**
	 * Gets booked billing frequency consequence.
	 *
	 * @return the booked billing frequency consequence
	 */
	public int getBookedBillingFrequencyConsequence()
	{
		return bookedBillingFrequencyConsequence;
	}

	/**
	 * Sets booked billing frequency consequence.
	 *
	 * @param bookedBillingFrequencyConsequenceParam the booked billing frequency consequence
	 */
	public void setBookedBillingFrequencyConsequence(final int bookedBillingFrequencyConsequenceParam)
	{
		this.bookedBillingFrequencyConsequence = bookedBillingFrequencyConsequenceParam;
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
	 * @param communicatedPriceSampleParam the communicated price sample
	 */
	public void setCommunicatedPriceSample(final Price communicatedPriceSampleParam)
	{
		this.communicatedPriceSample = communicatedPriceSampleParam;
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
	 * @param communicatedPriceConsequenceParam the communicated price consequence
	 */
	public void setCommunicatedPriceConsequence(final Price communicatedPriceConsequenceParam)
	{
		this.communicatedPriceConsequence = communicatedPriceConsequenceParam;
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
	 * @param bookedPriceSampleParam the booked price sample
	 */
	public void setBookedPriceSample(final Price bookedPriceSampleParam)
	{
		this.bookedPriceSample = bookedPriceSampleParam;
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
	 * @param bookedPriceConsequenceParam the booked price consequence
	 */
	public void setBookedPriceConsequence(final Price bookedPriceConsequenceParam)
	{
		this.bookedPriceConsequence = bookedPriceConsequenceParam;
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
	 * @param termOfServiceRenewalSampleParam the term of service renewal sample
	 */
	public void setTermOfServiceRenewalSample(final boolean termOfServiceRenewalSampleParam)
	{
		this.termOfServiceRenewalSample = termOfServiceRenewalSampleParam;
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
	 * @param termOfServiceRenewalConsequenceParam the term of service renewal consequence
	 */
	public void setTermOfServiceRenewalConsequence(final boolean termOfServiceRenewalConsequenceParam)
	{
		this.termOfServiceRenewalConsequence = termOfServiceRenewalConsequenceParam;
	}

	/**
	 * Gets discount quarter.
	 *
	 * @return the discount quarter
	 */
	public int getDiscountQuarter()
	{
		return discountQuarter;
	}

	/**
	 * Sets discount quarter.
	 *
	 * @param discountQuarterParam the discount quarter
	 */
	public void setDiscountQuarter(final int discountQuarterParam)
	{
		this.discountQuarter = discountQuarterParam;
	}

	/**
	 * Gets discount half year.
	 *
	 * @return the discount half year
	 */
	public int getDiscountHalfYear()
	{
		return discountHalfYear;
	}

	/**
	 * Sets discount half year.
	 *
	 * @param discountHalfYearParam the discount half year
	 */
	public void setDiscountHalfYear(final int discountHalfYearParam)
	{
		this.discountHalfYear = discountHalfYearParam;
	}

	/**
	 * Gets discount year.
	 *
	 * @return the discount year
	 */
	public int getDiscountYear()
	{
		return discountYear;
	}

	/**
	 * Sets discount year.
	 *
	 * @param discountYearParam the discount year
	 */
	public void setDiscountYear(final int discountYearParam)
	{
		this.discountYear = discountYearParam;
	}

	/**
	 * Gets frequency sample.
	 *
	 * @return the frequency sample
	 */
	public int getFrequencySample()
	{
		return frequencySample;
	}

	/**
	 * Sets frequency sample.
	 *
	 * @param frequencySampleParam the frequency sample
	 */
	public void setFrequencySample(final int frequencySampleParam)
	{
		this.frequencySample = frequencySampleParam;
	}

	/**
	 * Gets frequency consequence.
	 *
	 * @return the frequency consequence
	 */
	public int getFrequencyConsequence()
	{
		return frequencyConsequence;
	}

	/**
	 * Sets frequency consequence.
	 *
	 * @param frequencyConsequenceParam the frequency consequence
	 */
	public void setFrequencyConsequence(final int frequencyConsequenceParam)
	{
		this.frequencyConsequence = frequencyConsequenceParam;
	}

	/**
	 * Gets unit.
	 *
	 * @return the unit
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * Sets unit.
	 *
	 * @param unitParam the unit
	 */
	public void setUnit(final String unitParam)
	{
		this.unit = unitParam;
	}

	/**
	 * Gets valid payments.
	 *
	 * @return the valid payments
	 */
	public List<String> getValidPayments()
	{
		return validPayments;
	}

	/**
	 * Sets valid payments.
	 *
	 * @param validPaymentsParam the valid payments
	 */
	public void setValidPayments(final List<String> validPaymentsParam)
	{
		this.validPayments.clear();
		this.validPayments.addAll(validPaymentsParam);
	}

	/**
	 * Gets valid delivery countries.
	 *
	 * @return the valid delivery countries
	 */
	public List<String> getValidDeliveryCountries()
	{
		return validDeliveryCountries;
	}

	/**
	 * Sets valid delivery countries.
	 *
	 * @param validDeliveryCountriesParam the valid delivery countries
	 */
	public void setValidDeliveryCountries(final List<String> validDeliveryCountriesParam)
	{
		this.validDeliveryCountries.clear();
		this.validDeliveryCountries.addAll(validDeliveryCountriesParam);
	}
}
