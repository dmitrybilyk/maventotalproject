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


import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import com.cgi.pacoshop.core.util.sap.ProductTypeQualifier;
import com.cgi.pacoshop.core.util.sap.UnitCreator;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.util.Assert;

/**
 * Populate ProductModel with specific to subscription product fields.
 *
 * @module build
 * @version 1.0v Feb 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Artem Mageramov <artem.mageramov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class SubscriptionProductReversePopulator implements Populator<SubscriptionProductDTO, SubscriptionProductModel>
{
	@Resource
	private UnitCreator unitCreator;

	@Resource
	private ReferenceDataProviderService referenceDataProviderService;

	@Resource
	private ProductTypeQualifier productTypeQualifier;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Override
	public void populate(final SubscriptionProductDTO dto, final SubscriptionProductModel model) throws ConversionException,
																																		 ProductImportException
	{
		Assert.notNull(dto, "Parameter SubscriptionProductDTO cannot be null.");
		Assert.notNull(model, "Parameter ProductModel cannot be null.");

		model.setOnlineOffer(dto.isOnlineOffer());
		model.setClientOffer(dto.isClientOffer());
		model.setStudentOffer(dto.isStudentOffer());
		model.setConsigneeOffer(dto.isConsigneeOffer());
		model.setInvoiceRecipientOffer(dto.isInvoiceRecipientOffer());
		model.setReadersCanvassReaders(dto.isOtherBrokerAllowed());
		model.setMandatoryAddress(dto.isMandatoryAddress());
		model.setMandatoryPhone(dto.isMandatoryPhone());
		model.setMandatoryMobile(dto.isMandatoryMobile());
		model.setMandatoryEmail(dto.isMandatoryEmail());
		model.setMandatoryOptIn(dto.isMandatoryOptIn());
		model.setReadersCanvassReaders(dto.isOtherBrokerAllowed());
		model.setPrepayOnly(Boolean.FALSE);

		model.setProductType(productTypeQualifier.getProductType(dto, getImportInterfaceSubscriptionProductCluster()));

		UnitModel unitModel = unitCreator.getOrCreateUnit(dto.getUnit());
		model.setUnit(unitModel);

		// Add payment types to a product
		final Map<String, PaymentTypeModel> paymentTypesMap = referenceDataProviderService.getPaymentTypesMap();
		final List<PaymentTypeModel> paymentTypeModels = new ArrayList<PaymentTypeModel>();

		for (String paymentTypeName : dto.getValidPayments())
		{
			if (paymentTypesMap.containsKey(paymentTypeName))
			{
				paymentTypeModels.add(paymentTypesMap.get(paymentTypeName));
			}
			else
			{
				throw new ProductImportException(
						  String.format("PaymentType externalId=%s cannot be found in Hybris database", paymentTypeName));
			}
		}
		model.setPaymentTypes(paymentTypeModels);

		// Fake the supercategories. We are not using them anyway, but subsciption product will not save without that.
		List<CategoryModel> categoryModels = new ArrayList<CategoryModel>();
		model.setSupercategories(categoryModels);

		model.setImportSource(ProductImportSource.SERVICE_BUS_PERIODIC_PRODUCTS);
	}

	/**
	 * Returns the name of the product cluster that
	 * corresponds to the product import interface.
	 *
	 * @return Product cluster name
	 */
	private String getImportInterfaceSubscriptionProductCluster()
	{
		return shopConfigurationService.getSubscriptionProductCluster();
	}
}
