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
package com.cgi.pacoshop.core.service.user.impl;

import de.hybris.platform.commerceservices.customer.CustomerEmailResolutionService;
import de.hybris.platform.core.model.user.CustomerModel;

/**
 * Replacement for the de.hybris.platform.commerceservices.customer.impl.DefaultCustomerEmailResolutionService class.
 * Resolve a customer.contactEmail attribute using the customer.customerEmail attribute value.
 *
 * @module pacoshopcore
 * @version 1.0v Jun 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexey Tarasyuk <alexey.tarasyuk@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PacoCustomerEmailResolutionService implements CustomerEmailResolutionService
{
	@Override
	public String getEmailForCustomer(final CustomerModel customerModel)
	{
		return customerModel.getCustomerEmail();
	}
}
