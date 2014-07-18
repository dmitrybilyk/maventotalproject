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
package com.cgi.pacoshop.core.service.sap;


import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

import org.springframework.util.Assert;

import com.cgi.pacoshop.core.enums.FulfillmentType;
import com.cgi.pacoshop.core.enums.OfferOrigin;
import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.core.enums.ProductOwner;
import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.KeyGenerationUtils;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.VATGroupQualifier;


/**
 * Populator for the basic mapping logic that both single and periodic products types have in common.
 * 
 * @module build
 * @version 1.0v Feb 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mgearmov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class CommonBasicFieldsReversePopulator implements Populator<SingleProductDTO, ProductModel>
{
	@Resource
	private ReferenceDataProviderService referenceDataProviderService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private VATGroupQualifier vatGroupQualifier;

	@Override
	public void populate(final SingleProductDTO dto, final ProductModel model) throws ConversionException, ProductImportException
	{
		Assert.notNull(dto, "Parameter SingleProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");

		final CatalogVersionModel onlineCatalogVersionModel = referenceDataProviderService.getOnlineCatalogVersionModel();

		model.setExternalOfferId(dto.getOfferId());
		model.setCode(KeyGenerationUtils.getProductCode(dto));
		model.setName(dto.getOfferDescription());
		model.setActive(Boolean.TRUE);
		model.setOnlineDate(dto.getValidFrom());
		model.setOfflineDate(dto.getValidTo());

		model.setExternalProductId(dto.getProductId());
		model.setOfferOrigin(OfferOrigin.valueOf(dto.getOfferOrigin()));
		model.setDigital(isDigital(dto.getProductClass()));
		model.setDummy(isDummy(dto.getProductGroup()));

		model.setFulfillmentType(FulfillmentType.valueOf(dto.getProductOrigin()));
		model.setProductOwner(ProductOwner.valueOf(dto.getProductOwner()));
		model.setMinOrderQuantity(dto.getMinOrder());
		model.setMaxOrderQuantity(dto.getMaxOrder());
		model.setDifferingInvoiceRecipientAllowed(dto.isOtherInvoiceRecipientAllowed());
		model.setDifferingConsigneeAllowed(dto.isOtherConsigneeAllowed());
		model.setVatGroup(vatGroupQualifier.getVatGroupByRate(dto));
		model.setCatalogVersion(onlineCatalogVersionModel);
		model.setApprovalStatus(ArticleApprovalStatus.APPROVED);

		model.setImportSource(ProductImportSource.SERVICE_BUS_SINGLE_PRODUCTS);
	}

	/**
	 * Determine if it is dummy product.
	 * 
	 * @param productGroup
	 *           Product group model
	 * @return If product group represents a dummy product
	 */
	protected boolean isDummy(final String productGroup)
	{
		final String onlineArticleProductGroup = shopConfigurationService.getOnlineArticleProductGroup();
		return productGroup != null && productGroup.trim().equals(onlineArticleProductGroup);
	}

	/**
	 * Determine if product is digital.
	 * 
	 * @param productClass
	 *           Product class
	 * @return If product class represents a digital product
	 */
	protected boolean isDigital(final String productClass)
	{
		Assert.notNull(productClass);

		final String digitalProductClass = shopConfigurationService.getDigitalProductClass();
		return productClass != null && productClass.trim().equals(digitalProductClass);
	}

}
