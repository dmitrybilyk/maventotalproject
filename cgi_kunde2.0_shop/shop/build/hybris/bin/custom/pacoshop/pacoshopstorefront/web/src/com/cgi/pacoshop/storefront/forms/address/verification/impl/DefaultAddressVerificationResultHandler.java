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
package com.cgi.pacoshop.storefront.forms.address.verification.impl;

import com.cgi.pacoshop.storefront.controllers.util.GlobalMessages;
import com.cgi.pacoshop.storefront.forms.address.verification.AddressVerificationResultHandler;
import de.hybris.platform.commercefacades.address.data.AddressVerificationErrorField;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.address.AddressFieldType;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Set;

/**
 * Default implementation of {@link AddressVerificationResultHandler}.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01, 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
@Component("addressVerificationResultHandler")
public class DefaultAddressVerificationResultHandler implements AddressVerificationResultHandler
{
	@Override
	public boolean handleResult(final AddressVerificationResult verificationResult, final AddressData newAddress,
								final Model model, final RedirectAttributes redirectModel,
								final Errors bindingResult, final boolean customerBypass,
								final String successMsg)
	{
		if (verificationResult != null)
		{
			if (isResultUnknown(verificationResult))
			{
				// Unknown error. Services are likely down.
				return false;
			}

			if (resultHasSuggestedAddresses(verificationResult))
			{
				model.addAttribute("inputAddress", newAddress);
				model.addAttribute("customerBypass", Boolean.valueOf(customerBypass));
				model.addAttribute("suggestedAddresses", verificationResult.getSuggestedAddresses());
				model.addAttribute("saveInAddressBook", Boolean.valueOf(newAddress.isVisibleInAddressBook()));
				return true;
			}
			else if (resultHasErrors(verificationResult))
			{
				addFieldErrorsToBindingResult(verificationResult.getErrors(), bindingResult);
				GlobalMessages.addErrorMessage(model, "address.error.formentry.invalid");
				return true;
			}
			else
			{
				// The address was successfully verified by Cybersource. Inform the user and proceed.
				GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER, successMsg);
				return false;
			}
		}

		return false;
	}

	/**
	 * Check if result has errors.
	 *
	 * @param verificationResult Verification result.
	 * @return Result flag.
	 */
	protected boolean resultHasErrors(final AddressVerificationResult verificationResult)
	{
		return verificationResult.getErrors() != null && !verificationResult.getErrors().isEmpty();
	}

	/**
	 * Check uf result has suggested addresses.
	 *
	 * @param verificationResult Verification result.
	 * @return Result flag.
	 */
	protected boolean resultHasSuggestedAddresses(final AddressVerificationResult verificationResult)
	{
		return verificationResult.getSuggestedAddresses() != null && !verificationResult.getSuggestedAddresses().isEmpty();
	}

	/**
	 * Is result unknown?
	 *
	 * @param verificationResult Verification result.
	 * @return Result flag.
	 */
	protected boolean isResultUnknown(final AddressVerificationResult verificationResult)
	{
		return verificationResult.getDecision() != null
				&& verificationResult.getDecision().equals(AddressVerificationDecision.UNKNOWN);
	}

	/**
	 * Add field error.
	 *
	 * @param fieldErrors Field errors.
	 * @param bindingResult Binding result.
	 */
	protected void addFieldErrorsToBindingResult(final Map<String, AddressVerificationErrorField> fieldErrors,
												 final Errors bindingResult)
	{
		Set<Map.Entry<String, AddressVerificationErrorField>> errors = fieldErrors.entrySet();
		for (final Map.Entry<String, AddressVerificationErrorField> error : errors)
		{
			switch (AddressFieldType.lookup(fieldErrors.get(error.getKey()).getName()))
			{
				case TITLE_CODE:
					bindingResult.rejectValue("titleCode", "address.title.invalid");
					break;
				case FIRST_NAME:
					bindingResult.rejectValue("firstName", "address.firstName.invalid");
					break;
				case LAST_NAME:
					bindingResult.rejectValue("lastName", "address.lastName.invalid");
					break;
				case ADDRESS_LINE1:
					bindingResult.rejectValue("line1", "address.line1.invalid");
					break;
				case ADDRESS_LINE2:
					bindingResult.rejectValue("line2", "address.line2.invalid");
					break;
				case CITY:
					bindingResult.rejectValue("townCity", "address.townCity.invalid");
					break;
				case REGION:
					bindingResult.rejectValue("regionIso", "address.regionIso.invalid");
					break;
				case ZIP_CODE:
					bindingResult.rejectValue("postcode", "address.postcode.invalid");
					break;
				case COUNTRY:
					bindingResult.rejectValue("countryIso", "address.country.invalid");
					break;
				default:
					break;
			}
		}
	}
}
