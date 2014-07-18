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
package com.cgi.pacoshop.core.service.impl;


import static com.cgi.pacoshop.core.util.LogHelper.error;
import static com.cgi.pacoshop.core.util.LogHelper.info;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.ListUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cgi.pacoshop.core.enums.ProductImportSource;
import com.cgi.pacoshop.core.model.SingleProductDTO;
import com.cgi.pacoshop.core.model.SubscriptionProductDTO;
import com.cgi.pacoshop.core.refdata.ReferenceDataProviderService;
import com.cgi.pacoshop.core.service.ImportProductService;
import com.cgi.pacoshop.core.service.PacoshopProductService;
import com.cgi.pacoshop.core.service.RestClient;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.SingleProductImportService;
import com.cgi.pacoshop.core.service.SubscriptionProductImportService;


/**
 * Service Layer for import simple products from ServiceBus
 * 
 * 
 * @module pacoshopcore
 * @version 1.0v dec 18, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 * 
 */
public class ImportProductServiceImpl implements ImportProductService
{
	private static final Logger LOG = Logger.getLogger(ImportProductServiceImpl.class);

	@Resource
	private RestClient restClient;

	@Resource
	private PacoshopProductService pacoshopProductService;

	@Resource
	private ModelService modelService;

	@Resource
	private ReferenceDataProviderService referenceDataProviderService;

	@Resource
	private ShopConfigurationService shopConfigurationService;

	@Resource
	private SubscriptionProductImportService subscriptionProductImportService;

	@Resource
	private SingleProductImportService singleProductImportService;

	/**
	 * Import products by calling a method of a remote RESTfull webservice. The workflow is as follows:. 1 - connect to a
	 * ServiceBus service 2 - get products by calling a corresponding method of a remote webservice. 3 - for each product
	 * returned: 3.1 - if a product already exists in the database - it should be reused. 3.2. - set the active flag.
	 * Only the products returned by the service call should be active. Existing products that weren't in the response
	 * should be incative. 3 - save the products into the database.
	 * 
	 * @return parametrized list of actually imported products. Products that failed field validation should not be in
	 *         the list.
	 */
	@Override
	public Collection<ProductModel> importSingleProduct()
	{
		final Collection<SingleProductDTO> incomingProducts = restClient.getSingleProduct();
		return importProducts(incomingProducts, ProductImportSource.SERVICE_BUS_SINGLE_PRODUCTS);
	}

	@Override
	public Collection<ProductModel> importSubscriptionProducts()
	{
		final Collection<? extends SingleProductDTO> incomingProducts = restClient.getSubscriptionProducts();
		return importProducts(incomingProducts, ProductImportSource.SERVICE_BUS_PERIODIC_PRODUCTS);
	}

	private Collection<ProductModel> importProducts(final Collection<? extends SingleProductDTO> incomingProducts,
			final ProductImportSource importSource)
	{
		final List<ProductModel> newProductModels = new ArrayList<ProductModel>();
		final List<ProductModel> modifiedProductModels = new ArrayList<ProductModel>();
		final List<ProductModel> skippedProductModels = new ArrayList<ProductModel>();

		final Collection<ProductModel> existingProducts = pacoshopProductService.findProductsByImportSource(
				shopConfigurationService.getProductCatalogOnlineVersion(), importSource);

		final CatalogVersionModel catalogVersion = referenceDataProviderService.getOnlineCatalogVersionModel();

		ProductModel productModel = null;

		for (final SingleProductDTO dto : incomingProducts)
		{
			try
			{
				final String productCode = KeyGenerationUtils.getProductCode(dto);
				// Create new one only in case if it does not in a list of existing products AND
				// not in the list of just created products (can be the case if external system supplies
				// duplicate combinations of unique fields)
				if (!checkIfProductAlreadyExist(dto, existingProducts) && !checkIfProductAlreadyExist(dto, newProductModels))
				{
					if (dto.getClass() == SubscriptionProductDTO.class)
					{
						productModel = modelService.create(SubscriptionProductModel.class);
					}
					else
					{
						productModel = modelService.create(ProductModel.class);
					}

					createProduct(productModel, dto);
					newProductModels.add(productModel);
				}
				else
				{
					productModel = pacoshopProductService.getProductForCode(catalogVersion, productCode);

					checkProductTypesCompatibility(productModel, dto);

					createProduct(productModel, dto);
					modifiedProductModels.add(productModel);
				}
			}
			catch (final Exception e)
			{
				error(LOG, "Cannot import Product offerOrigin=%s, externalOfferId=%s. The error is: %s",
						e, dto.getOfferOrigin(), dto.getOfferId(), e.getMessage());
				if (productModel != null)
				{
					skippedProductModels.add(productModel);
				}
			}
		}

		List<ProductModel> inactiveProducts = ListUtils.subtract((List<ProductModel>) existingProducts, modifiedProductModels);
		inactiveProducts = ListUtils.subtract(inactiveProducts, skippedProductModels);

		for (final ProductModel inactiveProduct : inactiveProducts)
		{
			try
			{
				deactivateProduct(inactiveProduct);
			}
			catch (final ModelSavingException e)
			{
				error(LOG, "Cannot deactivate Product offerOrigin=%s, externalOfferId=%s. The error is: %s",
						e, inactiveProduct.getOfferOrigin(), inactiveProduct.getExternalOfferId(), e.getMessage());
			}
		}

		info(LOG, "Products import summary:");
		info(LOG, "  %d products were processed", incomingProducts.size());
		info(LOG, "  %d new products", newProductModels.size());
		info(LOG, "  %d modified products", modifiedProductModels.size());
		info(LOG, "  %d de-activated products", inactiveProducts.size());
		info(LOG, "  %d products skipped due to errors", skippedProductModels.size());

		return newProductModels;
	}

	private static void checkProductTypesCompatibility(final ProductModel productModel, final SingleProductDTO dto)
	{
		if ((productModel.getClass() == SubscriptionProductModel.class && dto.getClass() == SingleProductDTO.class))
		{
			throw new ProductImportException("Cannot convert existing Product's type from Subscription to Simple one");
		}

		if (productModel.getClass() == ProductModel.class && dto.getClass() == SubscriptionProductDTO.class)
		{
			throw new ProductImportException("Cannot convert existing Product's type from Single to Subscription one.");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor =
	{ ProductImportException.class, ModelSavingException.class })
	private void createProduct(final ProductModel productModel, final SingleProductDTO dto)
	{
		if (dto instanceof SubscriptionProductDTO)
		{
			final SubscriptionProductModel model = (SubscriptionProductModel) productModel;
			final SubscriptionProductDTO subscriptionProductDTO = (SubscriptionProductDTO) dto;
			subscriptionProductImportService.createProduct(subscriptionProductDTO, model);
		}
		else
		{
			singleProductImportService.createProduct(dto, productModel);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor =
	{ ModelSavingException.class })
	private void deactivateProduct(final ProductModel productModel)
	{
		if (productModel.getActive())
		{
			productModel.setActive(false);
		}

		modelService.save(productModel);
	}

	private boolean checkIfProductAlreadyExist(final SingleProductDTO dto, final Collection<ProductModel> existingProduct)
	{
		for (final ProductModel product : existingProduct)
		{
			final String productCode = KeyGenerationUtils.getProductCode(dto);
			if (productCode.equalsIgnoreCase(product.getCode()))
			{
				return true;
			}
		}
		return false;
	}
}
