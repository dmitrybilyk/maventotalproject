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
package com.cgi.pacoshop.core.refdata;


import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.model.VATGroupModel;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import java.util.Map;

/**
 * Reference data provider contains convenience methods for finding
 * common data elements that are used across the application
 * (e.g. Catalog and CatalogVersion objects, ProductTypes, PaymentTypes,
 * VATGroups, etc
 *
 * @module pacoshopcore
 * @version 1.0v Jan 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Trubin <andrey.trubin@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface ReferenceDataProviderService
{
	/**
	 * Returns Kunde2.0 shop product catalog model.
	 * @return Returns Kunde2.0 shop product catalog model
	 */
	CatalogModel getProductCatalogModel();

	/**
	 * Returns Kunde2.0 shop product catalog online version model.
	 * @return Returns Kunde2.0 shop product catalog online version model.
	 */
	CatalogVersionModel getOnlineCatalogVersionModel();

	/**
	 * Returns map of VAT rates (Double) to VATGroupModel objects.
	 * @return Returns map of VAT rates (Double) to VATGroupModel objects.
	 */
	Map<Double, VATGroupModel> getVATGroupsMap();

	/**
	 * Returns map of product type keys (now externalId, offerOrigin) to ProductTypeModel objects.
	 * @return map of product type keys (now externalId, offerOrigin) to ProductTypeModel objects.
	 */
	Map<ProductTypeKey, ProductTypeModel> getProductTypesMap();

	/**
	 * Returns map of externalId (String) to PaymentTypeModel objects.
	 * @return map of externalId (String) to PaymentTypeModel objects.
	 */
	Map<String, PaymentTypeModel> getPaymentTypesMap();

	/**
	 * Returns map of currency code (String) to CurrencyModel objects.
	 * @return map of currency code (String) to CurrencyModel objects.
	 */
	Map<String, CurrencyModel> getCurrencyModelMap();
}
