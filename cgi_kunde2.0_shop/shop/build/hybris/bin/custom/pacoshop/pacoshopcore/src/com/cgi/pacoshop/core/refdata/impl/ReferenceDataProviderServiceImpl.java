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
package com.cgi.pacoshop.core.refdata.impl;


import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.core.model.ProductTypeModel;
import com.cgi.pacoshop.core.model.VATGroupModel;
import com.cgi.pacoshop.core.refdata.ProductTypeKey;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.PaymentTypeService;
import com.cgi.pacoshop.core.service.ProductTypeService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.VATGroupService;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;

/**
 * Implementation of {@link com.cgi.pacoshop.core.refdata.ReferenceDataProviderService}.
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
public class ReferenceDataProviderServiceImpl implements ReferenceDataProviderService
{
	private static final Logger LOG = Logger.getLogger(ReferenceDataProviderServiceImpl.class);

	@Resource
	private CatalogService           catalogService;
	@Resource
	private CatalogVersionService    catalogVersionService;
	@Resource
	private CommonI18NService        commonI18NService;
	@Resource
	private VATGroupService          vatGroupService;
	@Resource
	private ProductTypeService       productTypeService;
	@Resource
	private ShopConfigurationService shopConfigurationService;
	@Resource
	private PaymentTypeService       paymentTypeService;

	@Override
	public CatalogModel getProductCatalogModel()
	{
		String productCatalogId = shopConfigurationService.getProductCatalogName();
		return catalogService.getCatalogForId(productCatalogId);
	}

	@Override
	public CatalogVersionModel getOnlineCatalogVersionModel()
	{
		String productCatalogOnlineVersionId = shopConfigurationService.getProductCatalogOnlineVersion();
		return catalogVersionService.getCatalogVersion(
                this.getProductCatalogModel().getId(), productCatalogOnlineVersionId);
	}

	@Override
	public Map<Double, VATGroupModel> getVATGroupsMap()
	{
		return vatGroupService.findAndMapAllVATGroups();
	}

	@Override
	public Map<ProductTypeKey, ProductTypeModel> getProductTypesMap()
	{
		return productTypeService.findAndMapAllProductTypes();
	}

	@Override
	public Map<String, PaymentTypeModel> getPaymentTypesMap()
	{
		return paymentTypeService.findAndMapAllPaymentTypes();
	}

	@Override
	public Map<String, CurrencyModel> getCurrencyModelMap()
	{
        Map<String, CurrencyModel> result = new HashMap<String, CurrencyModel>();
		final List<CurrencyModel> allCurrencies = commonI18NService.getAllCurrencies();
		for (CurrencyModel currencyModel : allCurrencies)
		{
            result.put(currencyModel.getName(), currencyModel);
		}

		return result;
	}
}
