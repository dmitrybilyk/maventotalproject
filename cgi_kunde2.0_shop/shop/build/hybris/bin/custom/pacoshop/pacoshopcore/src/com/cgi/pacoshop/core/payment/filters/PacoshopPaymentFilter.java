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
package com.cgi.pacoshop.core.payment.filters;


import static com.cgi.pacoshop.core.util.LogHelper.debug;
import static com.cgi.pacoshop.core.util.LogHelper.error;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.cgi.hybris.payment.core.filter.PaymentExtMethodFilter;
import com.cgi.hybris.payment.core.model.PaymentMethodModel;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.model.PaymentTypeModel;
import com.google.common.collect.Sets;


/**
 * 
 * @module cgi_kunde2.0_shop
 * @version 1.0v Mar 24, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class PacoshopPaymentFilter implements PaymentExtMethodFilter
{

    private static final Logger LOG = Logger.getLogger(PacoshopPaymentFilter.class);

    @Resource
    private ConfigurationService configurationService;

    private boolean isPhysical(final ProductModel productModel)
    {
        final String good = configurationService.getConfiguration().getString(FormElementGroupConstants.GOOD);
        return productModel.getProductType().getCode().equals(good);
    }

    private Set<PaymentTypeModel> getProductTypeLevelPaymentTypes(final ProductModel product)
    {
        final Set<PaymentTypeModel> paymentTypes = Sets.newHashSet();
        if (product.getProductType() == null)
        {
            error(LOG, "product with code %s has no product type (productType is null) - cannot determine list of payment types",
                    product.getCode());
        }
        else if (product.getProductType().getPaymentTypes() == null)
        {
            error(LOG, "product with code %s has no payment types (paymentTypes is null) ",
                    product.getCode());
        }
        else
        {
            paymentTypes.addAll(product.getProductType().getPaymentTypes());
        }
        return paymentTypes;
    }

    private Set<PaymentTypeModel> getProductLevelPaymentTypes(final SubscriptionProductModel productModel)
    {
        final Set<PaymentTypeModel> paymentTypes = Sets.newHashSet();
        if (CollectionUtils.isEmpty(productModel.getPaymentTypes()))
        {
            error(LOG, "subscription product with code %s has empty list of payment types",
                    productModel.getCode());
        }
        else
        {
            paymentTypes.addAll(productModel.getPaymentTypes());
        }
        return paymentTypes;
    }

    private Set<PaymentTypeModel> getAllowedPaymentTypes(final CartModel cart)
    {
        boolean allSubscription = true;
        boolean allSingle = true;
        boolean allPhysical = true;
        Set<PaymentTypeModel> result = Sets.newHashSet();
        for (final AbstractOrderEntryModel currentEntry : cart.getEntries())
        {
            final ProductModel currentProduct = currentEntry.getProduct();
            allSubscription = allSubscription && (currentProduct instanceof SubscriptionProductModel);
            allSingle =
                    allSingle && !(currentProduct instanceof SubscriptionProductModel);
            allPhysical = allPhysical && isPhysical(currentProduct);
        }


        //case with few physical goods
        //cart contains few single products that are physical goods
        //if all products are physical goods then intersection of their payment types
        //equals to the payment types of GOODS product type
        if (allSingle && allPhysical)
        {
            debug(LOG, "cart with code %s contains %d physical goods", cart.getCode(), cart.getEntries().size());
            final Set<PaymentTypeModel> physicalProductsPaymentTypes = Sets.newHashSet();
            physicalProductsPaymentTypes.addAll(getProductTypeLevelPaymentTypes(cart.getEntries().get(0).getProduct()));

            if (CollectionUtils.isEmpty(cart.getAllowedPaymentType()))
            {
                debug(LOG, "cart with code %s doesn't contain callback payment types", cart.getCode());
                result = physicalProductsPaymentTypes;
            }
            else
            {
                physicalProductsPaymentTypes.retainAll(cart.getAllowedPaymentType());
                result = physicalProductsPaymentTypes;
            }
        }
        //case when cart contains one single product
        else if (allSingle && cart.getEntries().size() == 1)
        {
            debug(LOG, "cart with code %s contains one single product", cart.getCode());
            final ProductModel singleProduct = cart.getEntries().get(0).getProduct();

            result = getProductTypeLevelPaymentTypes(singleProduct);
        }

        //case when cart contains only two subscription products
        if (allSubscription)
        {
            debug(LOG, "cart with code %s contains %d subscription products", cart.getCode(), cart.getEntries().size());
            boolean isChanged = false;

            for (final AbstractOrderEntryModel currentEntry : cart.getEntries())
            {
                final SubscriptionProductModel product = (SubscriptionProductModel) currentEntry.getProduct();
                final Set<PaymentTypeModel> currentProductLevelPaymentTypes = Sets
                        .newHashSet(getProductLevelPaymentTypes(product));
                final Set<PaymentTypeModel> currentProductTypeLevelPaymentTypes = Sets
                        .newHashSet(getProductTypeLevelPaymentTypes(
                        product));

                if (!isChanged)
                {
                	isChanged = true;
						result.addAll(currentProductTypeLevelPaymentTypes);
                }
					else
					{
						result.retainAll(currentProductTypeLevelPaymentTypes);
					}
					if (CollectionUtils.isNotEmpty(currentProductLevelPaymentTypes))
					{
						result.retainAll(currentProductLevelPaymentTypes);
					}
				}

        }

        return result;
    }

    @Override
	 public boolean isAllowed(final PaymentMethodModel paymentMethodModel, final CartModel cartModel)
    {
        final Set<PaymentTypeModel> allowedPaymentTypes = getAllowedPaymentTypes(cartModel);
        debug(LOG, "allowed payment types for cart with code %s are %s", cartModel.getCode(), allowedPaymentTypes.toString());
        for (final PaymentTypeModel paymentTypeModel : allowedPaymentTypes)
        {
            if (paymentTypeModel.getPaymentMode() != null)
            {
                if (paymentTypeModel.getPaymentMode().getCode().equals(paymentMethodModel.getMode().getCode()))
                {
                    return true;
                }
            }
            else
            {
                error(LOG, "payment type with code %s doesn't has payment mode", paymentTypeModel.getCode());
            }
        }
        return false;
    }

    /**
     * Setter for configuration service used in Unit Test.
     * 
     * @param configurationService
     *            the configurationService
     */
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }

}
