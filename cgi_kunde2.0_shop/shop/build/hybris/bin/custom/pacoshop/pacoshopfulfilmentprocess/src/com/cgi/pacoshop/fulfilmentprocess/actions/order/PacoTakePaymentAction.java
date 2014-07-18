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
package com.cgi.pacoshop.fulfilmentprocess.actions.order;


import com.cgi.hybris.payment.core.action.TakePaymentExtAction;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;


/**
 * Extended TakePayment Action with error message.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @version 1.0v Jun 19, 2014
 * @module bin
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see https://wiki.hybris.com/
 */
public class PacoTakePaymentAction extends TakePaymentExtAction
{
	@Override
	public Transition executeAction(final OrderProcessModel process)
	{
		Transition result = super.executeAction(process);

		if (result.equals(Transition.NOK))
		{
			process.setEndMessage(" Business process step: TakePayment \n"
																 + " Error Message: The payment transaction capture has failed.");
			modelService.save(process);
		}

		return result;
	}
}
