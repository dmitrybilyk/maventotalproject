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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.customerdatapage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractDeliveryAddressValidatingFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;

import static com.cgi.pacoshop.core.util.LogHelper.warn;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 * The type Abstract mandatory address form element group.
 * @module cgi_kunde2.0_shop
 * @version 1.0v Feb 28, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public abstract class
        AbstractMandatoryAddressFormElementGroup extends AbstractDeliveryAddressValidatingFormElementGroup
{
    private static final Logger LOG = Logger.getLogger(AbstractMandatoryAddressFormElementGroup.class);

	/**
	 * Is address prefilled.
	 *
	 * @param addressModel - address model
	 * @return true if address is prefilled
	 */
	protected boolean isAddressPrefilled(final AddressModel addressModel)
	{
		if (addressModel != null)
		{
			if (StringUtils.isNotEmpty(addressModel.getFirstname())
					  && StringUtils.isNotEmpty(addressModel.getLastname())
					  && StringUtils.isNotEmpty(addressModel.getTown())
					  && StringUtils.isNotEmpty(addressModel.getLine1())
					  && StringUtils.isNotEmpty(addressModel.getLine2())
					  && StringUtils.isNotEmpty(addressModel.getPostalcode())
					  && addressModel.getCountry() != null)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		Assert.notNull(dto);
		boolean result = super.validate(dto, bindingResult);
		if (dto instanceof CheckoutFormDTO)
		{
			result = result && getValidationService().validateMandatoryAddress((CheckoutFormDTO) dto, bindingResult);
		}
		return result;
	}
}
