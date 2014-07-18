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
package com.cgi.pacoshop.core.service;


import com.cgi.pacoshop.core.util.LogHelper;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


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

	@Resource
	private ConfigurationService configurationService;
	private int                  platformCallTimeoutConnect;

	/**
	 * Get service configuration object.
	 *
	 * @return Configuration object.
	 */
	public Configuration getServiceConfiguration()
	{
		return configurationService.getConfiguration();
	}

	/**
	 * Allows to directly retrieve the property value from the configuration.
	 *
	 * @param propertyName Name of the configuration property
	 * @return Value of configuration property
	 * @throws NoSuchElementException Thrown when configuration property does not exist
	 */
	public String getProperty(final String propertyName) throws NoSuchElementException
	{
		try
		{
			return configurationService.getConfiguration().getString(propertyName);
		}
		catch (NoSuchElementException e)
		{
			LogHelper.error(LOG, "Property \"{%s}\" not found in Hybris configuration", propertyName);
			throw e;
		}
	}


    /**
     * Gets the REST URL of SAP-SD interface for importing single products.
     *
     * @return Simple product import url
     */
    public String getSimpleProductUrl()
    {
        return getProperty(CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_URL);
    }

    /**
     * Gets the REST URL of SAP-SD interface for importing subscription products.
     *
     * @return Subscription product import url
     */
    public String getSubscriptionProductUrl()
    {
        return getProperty(CONFIG_KEY_SERVICEBUS_GET_PERIODIC_OFFERS_URL);
    }

    /**
     * Gets single product cluster.
     *
     * @return Single product cluster name
     */
    public String getSingleProductCluster()
    {
        return getProperty(CONFIG_KEY_PRODUCT_CLUSTER_SINGLE);
    }


	/**
	 * Gets subscription product cluster.
	 *
	 * @return Subscription product cluster name
     */
    public String getSubscriptionProductCluster()
    {
        return getProperty(CONFIG_KEY_PRODUCT_CLUSTER_SUBSCRIPTION);
    }

    /**
     * Gets digital product class.
     *
     * @return Digital product class name
     */
    public String getDigitalProductClass()
    {
        return getProperty(CONFIG_KEY_PRODUCT_CLASS_DIGITAL);
    }

    /**
     * Gets online article product group.
     *
     * @return Online article product group name
     */
    public String getOnlineArticleProductGroup()
    {
        return getProperty(CONFIG_KEY_PRODUCT_GROUP_ONLINEARTICLE);
    }

    /**
     * Gets product catalog name.
     *
     * @return Product catalog name
     */
    public String getProductCatalogName()
    {
        return getProperty(CONFIG_KEY_IMPORT_PRODUCT_CATALOG_NAME);
    }

    /**
     * Gets product catalog online version.
     *
     * @return Product Catalog online version
     */
    public String getProductCatalogOnlineVersion()
    {
        return getProperty(CONFIG_KEY_IMPORT_PRODUCT_CATALOG_VERSION_NAME);
    }

    /**
     * Gets media container model qualifier.
     *
     * @return Media container model qualifier
     */
    public String getMediaContainerModelQualifier()
    {
        return getProperty(CONFIG_KEY_MEDIA_CONTAINER_MODEL_QUALIFIER);
    }

    /**
     * Gets media container product image.
     *
     * @return Media container product image.
     */
    public String getMediaContainerProductImage()
    {
        return getProperty(CONFIG_KEY_MEDIA_CONTAINER_PRODUCT_IMAGE);
    }

	/**
	 * Gets single product unit name.
	 *
	 * @return Media container product bonus image.
	 */
	public String getMediaContainerProductBonusImage()
	{
		return getProperty(CONFIG_KEY_MEDIA_CONTAINER_PRODUCT_BONUS_IMAGE);
	}


    /**
     *
     * @return Single product default unit name
     */
    public String getSingleProductUnitName()
    {
        return getProperty(CONFIG_KEY_IMPORT_PRODUCT_UNIT_NAME);
    }

    /**
     * Gets single product unit type name.
     *
     * @return Single product default unit type
     */
    public String getSingleProductUnitTypeName()
    {
        return getProperty(CONFIG_KEY_IMPORT_PRODUCT_UNIT_TYPE_NAME);
    }

    /**
     * Gets simple product call timeout connect.
     *
     * @return the simple product call timeout connect
     */
    public int getSimpleProductCallTimeoutConnect()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_TIMEOUT_CONNECT);
    }

    /**
     * Gets simple product call timeout read.
     *
     * @return the simple product call timeout read
     */
    public int getSimpleProductCallTimeoutRead()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_TIMEOUT_READ);
    }

    /**
     * Gets simple product call retry.
     *
     * @return the simple product call retry
     */
    public int getSimpleProductCallRetry()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_SINGLE_OFFERS_RETRY);
    }

    /**
     * Gets subscription product call timeout connect.
     *
     * @return the subscription product call timeout connect
     */
    public int getSubscriptionProductCallTimeoutConnect()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_PERIODIC_OFFERS_TIMEOUT_CONNECT);
    }

    /**
     * Gets subscription product call timeout read.
     *
     * @return the subscription product call timeout read
     */
    public int getSubscriptionProductCallTimeoutRead()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_PERIODIC_OFFERS_TIMEOUT_READ);
    }

    /**
     * Gets subscription product call retry.
     *
     * @return the subscription product call retry
     */
    public int getSubscriptionProductCallRetry()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICE_BUS_GET_PERIODIC_OFFERS_RETRY);
    }

    /**
     * Gets a termOfServiceFrequency for import based on unit.
     *
     * @param unit
     *            unit value.
     * @return termOfServiceFrequency for unit.
     */
    public String getSubscriptionTermOfServiceFrequency(final String unit)
    {
        return getProperty(CONFIG_KEY_IMPORT_PRODUCT_SUBSCRIPTION_TERM_UNIT + unit).toUpperCase();
    }

    /**
     *
     *
     * @return uri template for SSO client
     */
    public String getSsoClientUriTemplate()
    {
        return getProperty(CONFIG_KEY_SSO_READ_ACCOUNT_URL);
    }

	/**
	 *
	 *
	 * @return uri template for SSO platform
	 */
	public String getConfigKeySsoClientPlatformUriTemplate()
	{
		return getProperty(CONFIG_KEY_SSO_CLIENT_PLATFORM_URI_TEMPLATE);
	}

    /**
     *
     *
     * @return accountId identifier for SSO client
     */
    public String getSsoUserFilterAccountId()
    {
        // sso.user.filter.account.id
        return getProperty(CONFIG_KEY_SSO_USER_FILTER_ACCOUNT_ID_KEY);
    }

    /**
     *
     *
     * @return date of birth identifier for SSO client
     */
    public String getSsoDynamicFieldsDateOfBirth()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_DATE_OF_BIRTH);
    }

	 /**
     *
     *
     * @return country identifier for SSO client
     */
    public String getSsoDynamicFieldsCountry()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_COUNTRY);
    }

    /**
     *
     *
     * @return city identifier for SSO client
     */
    public String getSsoDynamicFieldsCity()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_CITY);
    }

    /**
     *
     *
     * @return postalcode identifier for SSO client
     */
    public String getSsoDynamicFieldsPostalcode()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_POSTAL_CODE);
    }

    /**
     *
     *
     * @return street identifier for SSO client
     */
    public String getSsoDynamicFieldsStreet()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_STREET);
    }

    /**
     *
     *
     * @return lastname identifier for SSO client
     */
    public String getSsoDynamicFieldsLastname()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_LASTNAME);
    }

    /**
     *
     *
     * @return firstname identifier for SSO client
     */
    public String getSsoDynamicFieldsFirstname()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_FIRSTNAME);
    }

    /**
     *
     *
     * @return title identifier for SSO client
     */
    public String getSsoDynamicFieldsTitle()
    {
        return getProperty(CONFIG_KEY_SSO_DYNAMIC_FIELDS_TITLE);
    }

    /**
     *
     *
     * @return connect timeout identifier for SSO customer client
     */
    public int getCustomerCallTimeoutConnect()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SSO_READ_ACCOUNT_TIMEOUT_CONNECT);
    }

    /**
     *
     *
     * @return read timeout identifier for SSO customer client
     */
    public int getCustomerCallTimeoutRead()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SSO_READ_ACCOUNT_TIMEOUT_READ);
    }

    /**
     *
     *
     * @return read Max quantity customers addresses
     */
    public int getCustomerAddressLimit()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_CUSTOMER_ADDRESS_LIMIT);
    }

    /**
     *
     * @return uri template for business partner rest client to servicebus
     */
    public String getServiceBusBusinessPartnerUriTemplate()
    {
        return getProperty(CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_URL);
    }

    /**
     *
     * @return connect timeout for business partner rest client
     */
    public int getBusinessPartnerCallTimeoutConnect()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_TIMEOUT_CONNECT);
    }

    /**
     *
     * @return read timeout for business partner rest client
     */
    public int getBusinessPartnerCallTimeoutRead()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_TIMEOUT_READ);
    }

    /**
     *
     * @return business partner id for rest client template uri
     */
    public String getBusinessPartnerId()
    {
        return getProperty(CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_ID);
    }

    /**
     *
     * @return retry for business partner rest client
     */
    public int getBusinessPartnerCallRetry()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_RETRY);
    }

    /**
     *
     * @return enables update by servicebus business partner rest service
     */
    public boolean isServicebusBusinessPartnerAndSsoClientEnable()
    {
        return configurationService.getConfiguration().getBoolean(CONFIG_KEY_SERVICEBUS_UPDATE_SSO_CLIENT_ENABLE);
    }

    /**
     *
     * @return enables update by servicebus business partner rest service
     */
    public boolean isServicebusBusinessPartnerEnable()
    {
        return configurationService.getConfiguration().getBoolean(CONFIG_KEY_SERVICEBUS_GET_BUSINESS_PARTNER_ENABLE);
    }

    /**
     *
     * @return VAT rate for delivery
     */
    public double getDeliveryVatRate()
    {
        return configurationService.getConfiguration().getDouble(CONFIG_KEY_DELIVERY_VAT_RATE);
    }

    /**
     *
     * @return Flag that indicated if Rest clients have to trust the servers with invalid certificates. Note that this
     *         option has to be used with care and on test systems only.
     */
    public boolean getSecurityTrustAllHostnames()
    {
        return configurationService.getConfiguration().getBoolean(CONFIG_KEY_SECURITY_TRUST_ALL_HOSTNAMES);
    }

    /**
     * Getter for uri to SAP delivery address check interface.
     *
     * @return uri with to parameters offerOrigin and offerID
     */
    public String getDeliveryAddressCheckUri()
    {
        return configurationService.getConfiguration().getString(CONFIG_KEY_SERVICEBUS_CHECK_DELIVERY_ADDRESS_URL);
    }

    /**
     * Getter for read timeout to SAP delivery address check interface.
     *
     * @return read timeout for delivery address check rest client
     */
    public int getDeliveryAddressCheckReadTimeout()
    {
        return configurationService.getConfiguration().getInt(CONFIG_KEY_SERVICEBUS_CHECK_DELIVERY_ADDRESS_TIMEOUT_READ);
    }

    /**
     *
     * @return Flag that indicated if Rest clients have to trust the servers with invalid certificates. Note that this
     *         option has to be used with care and on test systems only.
     */
    public boolean isDeeplinkSecurityFingerprintEnabled()
    {
        return configurationService.getConfiguration().getBoolean(CONFIG_KEY_SECURITY_DEEPLINK_FINGERPRINT_ENABLE);
    }

    /**
     * @return an SSO service date format pattern.
     */
    public String getSsoDateFormat()
    {
        return getProperty(SSO_DATE_FORMAT);
    }

    /**
     * @return a TermName name which is a currently chosen to be displayed like an Otp-in checkbox on the summary page.
     */
    public String getOptInTermName()
    {
        return getProperty(CONFIG_KEY_OPT_IN_TERM_NAME);
    }

    /**
     * @return Default country ISO code.
     */
    public String getDefaultCountryIsoCode()
    {
        return getProperty(CONFIG_KEY_CHECKOUT_DEFAULT_COUNTRY_ISO_CODE);
    }

	/**
	 * Gets user group UID for visitor.
	 *
	 * @return the user group UID for visitor
	 */
	public String getUserGroupUIDForVisitor()
	{
		return getProperty(CONFIG_KEY_VISITOR_CUSTOMER_GROUP_UID);
	}

    /**
     * Gets UAG ws token.
     *
     * @return UAG ws token in configuration
     */
    public String getUagWsToken()
    {
        return getProperty(CONFIG_KEY_UAG_HEADER_NAME_WS_TOKEN_KEY);
    }

    /**
     * Gets SSO platform id.
     *
     * @return SSO platform id
     */
    public String getSSOPlatformIdKey()
    {
        return getProperty(CONFIG_KEY_SSO_PLATFORM_ID_KEY);
    }

    /**
     * Gets SSO platform id value.
     * @return SSO platform id value
     */
    public String getSSOPlatformIdVal()
    {
        return getProperty(CONFIG_KEY_SSO_PLATFORM_ID_VALUE);
    }

	/**
	 *
	 * @return connect timeout for read platform interface
	 */
	public int getPlatformCallTimeoutConnect()
	{
		return configurationService.getConfiguration().getInt(CONFIG_KEY_PLATFORM_CALL_TIMEOUT_CONNECT);
	}

	/**
	 *
	 * @return read timeout for read platform interface
	 */
	public int getPlatformCallTimeoutRead()
	{
		return configurationService.getConfiguration().getInt(CONFIG_KEY_PLATFORM_CALL_TIMEOUT_READ);
	}
}
