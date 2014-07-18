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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.PaymentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import org.springframework.validation.BindingResult;

/**
 * Interface for Validation each field from FormDTO
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 30, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Dmitry Popov<dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public interface FormValidationService
{
	int FIELD_LENGTH = 80;

	/**
	 * Validate title.
	 * @param title - title to validate.
	 * @return true if title is valid.
	 */
	boolean validateTitle(String title);

	/**
	 * Validate firma.
	 * @param firma - firma length must be not longer than 80.
	 * @return true if firma is valid.
	 */
	boolean validateFirma(String firma);

	/**
	 * Validate position.
	 * @param position - position length must be not longer than 80.
	 * @return true if position is valid.
	 */
	boolean validatePosition(String position);

	/**
	 * Validate additionalStreet.
	 * @param additionalStreet - additionalStreet length must be not longer than 80.
	 * @return true if additionalStreet is valid.
	 */
	boolean validateAdditionalStreet(String additionalStreet);

	/**
	 * Validate firstName.
	 * @param firstName - firstName to validate.
	 * @return true if firstName is valid.
	 */
	boolean validateFirstName(String firstName);

	/**
	 * Validate lastName.
	 * @param lastName - lastName to validate.
	 * @return true if lastName is valid.
	 */
	boolean validateLastName(String lastName);

	/**
	 * Validate street.
	 * @param street - street to validate.
	 * @return true if street is valid.
	 */
	boolean validateStreet(String street);

   /**
	 * Validate house number.
	 * @param houseNumber - houseNumber to validate.
	 * @return true if houseNumber is valid.
	 */
	boolean validateHouseNumber(String houseNumber);

	/**
	 * Validate dto.
	 *
	 *
	 * @param zip - dto to validate.
	 * @param isoCountry Country ISO code.
	 * @return true if dto is valid.
	 */
	boolean validateZip(String zip, final String isoCountry);

	/**
	 * Validate city.
	 * @param city - city to validate.
	 * @return true if city is valid.
	 */
	boolean validateCity(String city);

	/**
	 * Validate country.
	 * @param country - country to validate.
	 * @return true if country is valid.
	 */
	boolean validateCountry(String country);

	/**
	 * Validate email.
	 * @param email - email to validate.
	 * @return true if email is valid.
	 */
	boolean validateEmail(String email);

	/**
	 * Validate mobile.
	 * @param prefix - prefix to validate.
	 * @param phone - phone to validate.
	 * @return true if mobile is valid.
	 */
	boolean validateMobile(String prefix, String phone);

	/**
	 * Validate phone.
	 * @param prefix - prefix to validate.
	 * @param extension - extension to validate.
	 * @param phone - phone to validate.
	 * @return true if phone is valid.
	 */
	boolean validatePhone(String prefix, String extension, String phone);

	/**
	 * Validate dto.
	 * @param dto - dto to validate.
	 * @return true if dto is valid.
	 */
	boolean validateBirthDate(final CheckoutFormDTO dto);

	/**
	 * Validate Customer data download page.
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validateCustomerDataDownloadPage(final CheckoutFormDTO dto, final BindingResult bindingResult);

	/**
	 * Validate customer data good print digital abo.
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validateSummaryDownloadPage(final SummaryStepFormDTO dto, final BindingResult bindingResult);

	/**
	 * Validate OptIn parameter.
	 * @param dto - form data transfer object.
	 * @return - true is valid
	 */
	boolean validateOptIn(final SummaryStepFormDTO dto);

	/**
	 * Validate BIC field for Cash Bonus. Must be Integer (8-11).
	 * @param dto form data transfer object.
	 * @return - true if form is valid.
	 */
	boolean validateBIC(CheckoutFormDTO dto);

	/**
	 * Validate IBAN field for Cash Bonus. Must be Integer (22).
	 * @param dto form data transfer object.
	 * @return - true if form is valid.
	 */
	boolean validateIBAN(CheckoutFormDTO dto);

	/**
	 * Validate field for type. Must be Integer.
	 * @param field incoming value of the field.
	 * @return - true if form is valid.
	 */
	boolean validateForNumber(String field);

	/**
	 * Validate customer data good print digital abo.
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validateCustomerDataGoodPrintDigitalAbo(final CheckoutFormDTO dto, final BindingResult bindingResult);

	/**
	 * Validate  print digital product.
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return - true if form is valid.
	 */
	boolean validateGoodPrintDigitalProduct(final ShipmentInfoFormDTO dto, final BindingResult bindingResult);

	/**
	 * Validate Mandatory address fields.
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validateMandatoryAddress(final CheckoutFormDTO dto, final BindingResult bindingResult);


	/**
	 * Validate miles and more number.
	 *
	 * @param milesAndMoreNumber - miles and more number.
	 * @param bindingResult - binding results for errors.
	 * @return - true if form is valid.
	 */
	boolean validateMilesAndMore(final String milesAndMoreNumber, final BindingResult bindingResult);


	/**
	 * Validate miles and more number.
	 *
	 * @param formDTO - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validatePhysicalBonusWithReferral(final CheckoutFormDTO formDTO, final BindingResult bindingResult);


	/**
	 * Validate monetary bonus.
	 *
	 * @param formDTO - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validateMonetaryBonus(final CheckoutFormDTO formDTO, final BindingResult bindingResult);

	/**
	 * Validate mandatory field.
	 *
	 * @param mandatoryFieldValue - form data transfer object
	 * @return  - true if field is not empty
	 */
	boolean validateMandatoryField(final String mandatoryFieldValue);

	/**
	 * Validate abo number.
	 *
	 * @param aboNumber - abo number
	 * @param bindingResult - spring binding result
	 * @return  - if aboNumber is not empty then it returns true
	 */
	boolean validateAboNumber(final String aboNumber, final BindingResult bindingResult);

	/**
	 * Validate the graduation date fields on the Student Offer sections.
	 * The graduation date is mandatory field and it should be greater then current system day/time.
	 *
	 * @param formDTO - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return true if dto is valid.
	 */
	boolean validateStudentGraduationDate(final CheckoutFormDTO formDTO, final BindingResult bindingResult);

	/**
	 * Validate the customer info, when product type is newsletter.
	 * The fields are mandatory.
	 *
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return true if dto is valid.
	 */
	boolean validateNewsletterProductType(final CheckoutFormDTO dto, final BindingResult bindingResult);

	//*----------------------------------SHIPMENT STEP START--------------------------------------------------------------


	/**
	 * Validate shipping start delivery date.
	 *
	 * @param dto - form data transfer object.
	 * @param bindingResult - binding results for errors.
	 * @return  - true if form is valid.
	 */
	boolean validateShippingStartDeliveryDate(final ShipmentInfoFormDTO dto, final BindingResult bindingResult);

	/**
	 * Validate different shipment address.
	 *
	 * @param formDTO the form dTO
	 * @param bindingResult the binding result
	 * @return the boolean
	 */
	boolean validateDifferentDigitalDeliveryAddress(final ShipmentInfoFormDTO formDTO, final BindingResult bindingResult);


	/**
	 * Validate different physical delivery address.
	 *
	 * @param formDTO the form dTO
	 * @param bindingResult the binding result
	 * @return the boolean
	 */
	boolean validateDifferentPhysicalDeliveryAddress(final ShipmentInfoFormDTO formDTO, final BindingResult bindingResult);


//*----------------------------------SHIPMENT STEP END--------------------------------------------------------------

	/**
	 * Validates payments.
	 *
	 * @param bindingResult result with errors
	 * @return validation result.
	 */
	boolean validatePayment(final BindingResult bindingResult);

}
