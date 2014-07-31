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
package runner.sso.model;


import java.util.NoSuchElementException;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Convenience service for getting the parameter values from the config/local.properties Pacoshop services must inject
 * this service as the resource and simply call its methods in one shot <br/>
 * Upon adding new configuration parameter in local.properties you should add the property key constant in this class
 * and a method that retrieves the value by the property key <br/>
 * Note: the values of the configuration parameters initialize ONCE, during the application startup. If you changed the
 * value in config/local.properties file you have to restart the application
 *
 * @module pacoshopcore
 * @version 1.0v Jan 18, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @link http ://www.symmetrics.de/
 * @see ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@Component
public class ShopConfigurationService
{
    //flag that indicates wheter it is needed to check payment status or not (callback issue)
	public static final String CONFIG_KEY_WIRECARD_CALLBACK_CHECK_STATUS               = "wirecard.callback.checkstatus";

	//Subscription terms service frequency
	public static final String CONFIG_KEY_IMPORT_PRODUCT_SUBSCRIPTION_TERM_UNIT = "import.product.subscription.term.unit.";

	public static final String CONFIG_KEY_SERVICEBUS_UPDATE_SSO_CLIENT_ENABLE =
			  "servicebus.update.sso.client.enable";
	public static final String CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_ENABLE                =
			  "servicebus.get.business.partner.enable";

	/** Fingerprint request parameter. **/
	public static final String FINGERPRINT_REQUEST_PARAM = "fingerprint";

	/** Fingerprintsecretno request parameter. **/
	public static final String FINGERPRINT_SECRET_NO_REQUEST_PARAM = "fingerprintsecretno";

	/** . SSO platform id key **/
	public static final String CONFIG_KEY_SSO_PLATFORM_ID_KEY = "sso.user.filter.platform.id.key";

	/** . SSO platform id default value **/
	public static final String CONFIG_KEY_SSO_PLATFORM_ID_VALUE = "sso.user.filter.platform.id.value";

	/** . AccountId identifier for SSO **/
	public static final String CONFIG_KEY_SSO_USER_FILTER_ACCOUNT_ID_KEY = "sso.user.filter.account.id.key";

	/** . Name of the UAG header that contains information about customerId **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_ID = "uag.header.customer_id";

	/** . Name of the UAG header that contains information about customerId **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_ID_TYPE = "uag.header.customer_id_type";

	/** Value of the UAG id-type header for visitors. **/
	public static final String CONFIG_KEY_UAG_HEADER_VALUE_CUSTOMER_ID_TYPE_VISITOR = "uag.header.customer_id_type.value.visitor";

	/** Value of the UAG id-type header for registered users. **/
	public static final String CONFIG_KEY_UAG_HEADER_VALUE_CUSTOMER_ID_TYPE_REGISTERED =
			  "uag.header.customer_id_type.value.registered";

	/** Name of the UAG header that contains information about customer email. **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_CUSTOMER_EMAIL = "uag.header.customer.email";

	/** Name of one of the UAG headers. **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_CONTENTACCESS = "uag.header.contentaccess";

	/** Name of the UAG header for storing a ws token value. **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_WS_TOKEN_KEY = "uag.header.ws.token";

	/** Name of the UAG header. IP address of the user for which the request has been forwarded by the UAG. **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_X_FORWARDED_FOR = "uag.header.forwardedfor.name";

	/** Name of the UAG header. Original protocol (http or https) of the request that was forwarded to the shop by the UAG. **/
	public static final String CONFIG_KEY_UAG_HEADER_NAME_X_FORWARDED_PROTO = "uag.header.forwardedproto.name";

	/** The UAG header values are comes in encoded state and should be decoded. **/
	public static final String CONFIG_KEY_UAG_HEADER_ENCODING = "uag.header.encoding";

	/** . Name of the group that contains customers that are registered **/
	public static final String CONFIG_KEY_REGISTERED_CUSTOMER_GROUP_UID = "customergroup.registered";

	/** . Name of the group that contains customers that are visitors **/
	public static final String CONFIG_KEY_VISITOR_CUSTOMER_GROUP_UID = "customergroup.visitor";

	public static final String SSO_DATE_FORMAT = "sso.read.account.date.format";

	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_JSON_NODE_ID             = "sso.dynamicfields.jsonnode.id";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_JSON_NODE_ALIAS          = "sso.dynamicfields.jsonnode.alias";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_JSON_NODE_VALUE          = "sso.dynamicfields.jsonnode.value";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_TITLE                    = "sso.dynamicfields.title";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_FIRSTNAME                = "sso.dynamicfields.firstname";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_LASTNAME                 = "sso.dynamicfields.lastname";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_STREET                   = "sso.dynamicfields.street";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_STREET_NUMBER                   = "sso.dynamicfields.street.number";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_POSTAL_CODE               = "sso.dynamicfields.postalcode";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_CITY                     = "sso.dynamicfields.city";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_COUNTRY                  = "sso.dynamicfields.country";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_PREFIX_HOME        = "sso.dynamicfields.phone.home.prefix";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_NUMBER_HOME        = "sso.dynamicfields.phone.home.number";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_EXTENSION_HOME     = "sso.dynamicfields.phone.home.extension";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_PREFIX_BUSINESS    = "sso.dynamicfields.phone.business.prefix";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_EXTENSION_BUSINESS =
			  "sso.dynamicfields.phone.business.extension";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_PHONE_NUMBER_BUSINESS    = "sso.dynamicfields.phone.business.number";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_MOBILE_PREFIX            = "sso.dynamicfields.mobile.prefix";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_MOBILE_NUMBER            = "sso.dynamicfields.mobile.number";
	public static final String CONFIG_KEY_SSO_DYNAMIC_FIELDS_DATE_OF_BIRTH            = "sso.dynamicfields.dataofbirth";


	// Template for uri that goes SSO for some platform information
	public static final String CONFIG_KEY_SSO_CLIENT_PLATFORM_URI_TEMPLATE = "sso.read.platform.url";

	// Client header for authentication (wsToken)
	public static final  String CONFIG_KEY_SSO_READ_ACCOUNT_AUTH_HEADER = "sso.read.account.auth.header";
	// Template for uri that goes SSO for some user
	public static final String CONFIG_KEY_SSO_READ_ACCOUNT_URL  = "sso.read.account.url";

	// Template for uri that goes to SAP for delivery address check
	public static final String CONFIG_KEY_SERVICEBUS_CHECK_DELIVERY_ADDRESS_URL          =
			  "servicebus.check.delivery.address.url";
	public static final String CONFIG_KEY_SERVICEBUS_CHECK_DELIVERY_ADDRESS_TIMEOUT_READ =
			  "servicebus.check.delivery.address.timeout.read";

	// Catalog names and versions property keys
	public static final String CONFIG_KEY_IMPORT_PRODUCT_CATALOG_NAME                              = "import.product.catalog.name";
	public static final String CONFIG_KEY_IMPORT_PRODUCT_CATALOG_VERSION_NAME                      =
           "import.product.catalog.version.name";
	// Paths to SAP-SD and SAP-MSD REST web services property keys
	public static final String CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_URL                         =
           "servicebus.get.single.offers.url";
	public static final String CONFIG_KEY_SERVICEBUS_GET_PERIODIC_OFFERS_URL                       =
           "servicebus.get.periodic.offers.url";
	// connection params for calls to SAP-SD and SAP-MSD
	public static final String CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_TIMEOUT_CONNECT       =
			  "servicebus.get.single.offers.timeout.connect";
	public static final String CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_TIMEOUT_READ          =
			  "servicebus.get.single.offers.timeout.read";
	public static final String CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_RETRY                 =
			  "servicebus.get.single.offers.retry";
	public static final String CONFIG_KEY_SERVICEBUS_GET_PERIODIC_OFFERS_TIMEOUT_CONNECT =
			  "servicebus.get.periodic.offers.timeout.connect";
	public static final String CONFIG_KEY_SERVICEBUS_GET_PERIODIC_OFFERS_TIMEOUT_READ    =
			  "servicebus.get.periodic.offers.timeout.read";
	public static final String CONFIG_KEY_SERVICE_BUS_GET_PERIODIC_OFFERS_RETRY           =
			  "servicebus.get.periodic.offers.retry";
	// Product clusters property keys
	public static final String CONFIG_KEY_PRODUCT_CLUSTER_SINGLE                          = "product.cluster.single";
	public static final String CONFIG_KEY_PRODUCT_CLUSTER_SUBSCRIPTION                    = "product.cluster.subscription";
	// Product groups property keys
	public static final String CONFIG_KEY_PRODUCT_GROUP_ONLINEARTICLE                          = "product.group.onlinearticle";
	// Product classes property keys
	public static final String CONFIG_KEY_PRODUCT_CLASS_DIGITAL                                = "product.class.digital";
	// Media container property keys
	public static final String CONFIG_KEY_MEDIA_CONTAINER_PRODUCT_IMAGE             = "import.product.image";
	public static final String CONFIG_KEY_MEDIA_CONTAINER_PRODUCT_BONUS_IMAGE       = "import.product.bonus.image";
	public static final String CONFIG_KEY_MEDIA_CONTAINER_MODEL_QUALIFIER           =
			  "import.product.media.container.model.qualifier";
	// Units for single products
	public static final String CONFIG_KEY_IMPORT_PRODUCT_UNIT_NAME                  = "import.product.unit.name";
	public static final String CONFIG_KEY_IMPORT_PRODUCT_UNIT_TYPE_NAME             = "import.product.unit.type.name";
	public static final String CONFIG_KEY_SSO_READ_ACCOUNT_TIMEOUT_CONNECT             =
			  "sso.read.account.timeout.connect";
	public static final String CONFIG_KEY_SSO_READ_ACCOUNT_TIMEOUT_READ                =
			  "sso.read.account.timeout.read";

	public static final String CONFIG_KEY_PLATFORM_CALL_TIMEOUT_CONNECT             =
			  "sso.read.platform.timeout.connect";
	public static final String CONFIG_KEY_PLATFORM_CALL_TIMEOUT_READ                =
			  "sso.read.platform.timeout.read";

	public static final String CONFIG_KEY_CUSTOMER_ADDRESS_LIMIT = "customer.address.limit";

	public static final String CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_URL =
			  "servicebus.get.business.partner.url";

	public static final String CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_TIMEOUT_CONNECT =
			  "servicebus.get.business.partner.timeout.connect";
	public static final String CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_TIMEOUT_READ    =
			  "servicebus.get.business.partner.timeout.read";

	public static final String CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_RETRY = "servicebus.get.business.partner.retry";

	public static final String CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_ID = "servicebus.get.business.partner.key";

	public static final String CONFIG_KEY_DELIVERY_VAT_RATE = "checkout.delivery.vat.rate";

	public static final String CONFIG_KEY_SECURITY_TRUST_ALL_HOSTNAMES = "security.trust.all.hostnames";

	public static final String CONFIG_KEY_SECURITY_DEEPLINK_FINGERPRINT_ENABLE = "security.deeplink.fingerprint.enable";

	public static final String CONFIG_KEY_OPT_IN_TERM_NAME = "terms.optin.term.name";

	public static final String CONFIG_KEY_CHECKOUT_DEFAULT_COUNTRY_ISO_CODE = "checkout.default.country.isocode";

	private static final Logger LOG = Logger.getLogger(ShopConfigurationService.class);

}
