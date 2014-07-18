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
package com.cgi.pacoshop.core.checkout.dynamic.impl.comparators;

import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentAddressDTO;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.lang.StringUtils;

/**
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmmitry Bilyk <dmitry.bilyk@symmetrics.de>
 * @version 1.0v Jun 17, 2014
 * @link http://www.symmetrics.de/
 **/

public class AddressDTOLastUpdateDateComparator implements Comparator<ShipmentAddressDTO>, Serializable
{
	private static final int GREATER = 1;
	private static final int LOWER = -1;
	private static final int EQUAL = 0;
	@Override
	public int compare(final ShipmentAddressDTO address1, final ShipmentAddressDTO address2)
	{


		if (StringUtils.isNotEmpty(address1.getBusinessPartnerId()) && StringUtils.isEmpty(address2.getBusinessPartnerId()))
		{
			return LOWER;
		}
		if (StringUtils.isEmpty(address1.getBusinessPartnerId()) && StringUtils.isNotEmpty(address2.getBusinessPartnerId()))
		{
			return GREATER;
		}

		if(address1.getLastUpdateDate() != null && address2.getLastUpdateDate() != null){
				return address1.getLastUpdateDate().before(address2.getLastUpdateDate()) ? GREATER : LOWER;
		}

		return EQUAL;
	}
}
