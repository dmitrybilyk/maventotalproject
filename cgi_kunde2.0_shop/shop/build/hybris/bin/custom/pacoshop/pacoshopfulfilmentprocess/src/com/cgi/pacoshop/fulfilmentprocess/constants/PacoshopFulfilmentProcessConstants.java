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
package com.cgi.pacoshop.fulfilmentprocess.constants;



/**
 * Order Fulfillment constants class.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public final class PacoshopFulfilmentProcessConstants extends GeneratedPacoshopFulfilmentProcessConstants
{
    public static final String CONSIGNMENT_SUBPROCESS_END_EVENT_NAME = "ConsignmentSubprocessEnd";
    public static final String ORDER_PROCESS_NAME = "order-process";
    public static final String CONSIGNMENT_SUBPROCESS_NAME = "consignment-process";
    public static final String WAIT_FOR_WAREHOUSE = "WaitForWarehouse";
    public static final String CONSIGNMENT_PICKUP = "ConsignmentPickup";
    public static final String CONSIGNMENT_COUNTER = "CONSIGNMENT_COUNTER";
    public static final String PARENT_PROCESS = "PARENT_PROCESS";
    public static final String PAYMENT_WIRECARD = "wirecard";
    public static final String PAYMENT_SAP = "SAP";
    public static final String ACTION_ORDER_ROUTING = "orderRouting";

    /**
     * Constants of the order fulfillment.
     * 
     * @module hybris - pacoshopcore
     * @version 1.0v Mar 04 2014
     * @author symmetrics - a CGI Group brand <info@symmetrics.de>
     * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
     * @link http://www.symmetrics.de/
     * @see https://wiki.hybris.com/
     * @copyright 2014 symmetrics - a CGI Group brand
     */
    public interface INTERFACE
    {

		 /**
		  * CA url.
		  */
		 String CA_URL                    = "ca.send.order.url";
		 /**
		  * CA connection timeout.
		  */
		 String CA_CONNECTION_TIMEOUT     = "ca.send.order.timeout.connect";
		 /**
		  * CA socket timeout.
		  */
		 String CA_SO_TIMEOUT             = "ca.send.order.timeout.read";
		 /**
		  * CA payment status success.
		  */
		 String CA_PAYMENT_STATUS_SUCCESS = "ca.send.order.payment.status.success";
		 /**
		  * CA payment status fail.
		  */
		 String CA_PAYMENT_STATUS_FAIL    = "ca.send.order.payment.status.fail";



		 /**
		  * SSO register accepted terms URL.
		  */
		 String SSO_WRITE_ACCOUNT_URL 							   = "sso.write.account.url";
	 	 /**
		  * SSO authentication token header.
		  */
		 String SSO_WRITE_ACCOUNT_AUTH_TOKEN_HEADER 			   = "sso.write.account.auth.header";
		 /**
		  * SSO authentication token value.
		  */
		 String SSO_WRITE_ACCOUNT_AUTH_TOKEN 					   = "sso.write.account.wsToken";
		 /**
		  * SSO read timeout.
		  */
		 String SSO_WRITE_ACCOUNT_READ_TIMEOUT 				       = "sso.write.account.timeout.read";
		 /**
		  * SSO connection timeout.
		  */
		 String SSO_WRITE_ACCOUNT_CONNECTION_TIMEOUT 			   = "sso.write.account.timeout.connect";

		 /**
		  * sso URL.
		  */
		 String SSO_SEARCH_ACCOUNT_URL 							   = "sso.search.account.url";
		 /**
		  * SSO read timeout.
		  */
		 String SSO_SEARCH_ACCOUNT_READ_TIMEOUT 				   = "sso.search.account.timeout.read";
		 /**
		  * SSO connection timeout.
		  */
		 String SSO_SEARCH_ACCOUNT_CONNECTION_TIMEOUT 			   = "sso.search.account.timeout.connect";
		 /**
		  * SSO authentication token value.
		  */
		 String SSO_SEARCH_ACCOUNT_AUTH_TOKEN_HEADER 			   = "sso.search.account.auth.header";
		 /**
		  * SSO authentication token value.
		  */
		 String SSO_SEARCH_ACCOUNT_AUTH_TOKEN 			   = "sso.search.account.wsToken";

		 /**
		  * SAP MD url.
		  */
		 String SERVICEBUS_PERIODICORDERS_CREATE_URL  = "servicebus.create.periodic.orders.url";
		 /**
		  * SAP MD retry delay.
		  */
		 String SERVICEBUS_PERIODICORDERS_RETRY_DELAY = "servicebus.create.periodic.orders.delay";
		 /**
		  * SAP MD retry max.
		  */
		 String SERVICEBUS_PERIODICORDERS_RETRY_MAX   = "servicebus.create.periodic.orders.retry";

		 /**
		  * SAP SD Connection timeout.
		  */
		 String SERVICEBUS_PERIODICORDERS_CREATE_CONNECTION_TIMEOUT = "servicebus.create.periodic.orders.timeout.connect";
		 /**
		  * SAP SD socket timeout.
		  */
		 String SERVICEBUS_PERIODICORDERS_CREATE_SOCKET_TIMEOUT     = "servicebus.create.periodic.orders.timeout.read";
		 /**
		  * SAP SD url.
		  */
		 String SERVICEBUS_SINGLEORDERS_CREATE_URL                  = "servicebus.create.single.orders.url";
		 /**
		  * SAP SD retry delay.
		  */
		 String SERVICEBUS_SINGLEORDERS                             = "servicebus.create.single.orders.delay";
		 /**
		  * SAP SD retry max.
		  */
		 String SERVICEBUS_SINGLEORDERS_RETRY_MAX                   = "servicebus.create.single.orders.retry";

		 /**
		  * SAP SD Connection timeout.
		  */
		 String SERVICEBUS_SINGLEORDERS_CREATE_CONNECTION_TIMEOUT = "servicebus.create.single.orders.timeout.connect";
		 /**
		  * SAP SD socket timeout.
		  */
		 String SERVICEBUS_SINGLEORDERS_CREATE_SOCKET_TIMEOUT     = "servicebus.create.single.orders.timeout.read";

		 /**
		  * Default retry delay for all calls.
		  */
		 Integer DEFAULT_RETRY_DELAY = Integer.valueOf(30000);
		 /**
		  * Default number of retry for all calls.
		  */
		 Integer DEFAULT_RETRY_MAX   = Integer.valueOf(3);


		 /**
		  * Making checkstyle happy.
		  */
		 void checkstyleFix();
	 }

	/**
	 * Product types.
	 */
	public static class ProductTypes
	{

		public static final String PRINT_ABO = "abo.print";

		public static final String DIGITAL_ABO = "abo.digital";

		public static final String NEWS_LETTER = "news.letter";

		public static final String GOOD = "good";

		public static final String ONLINE_ARTICLE = "onlinearticle";

		public static final String DOWNLOAD = "download";

	}
}
