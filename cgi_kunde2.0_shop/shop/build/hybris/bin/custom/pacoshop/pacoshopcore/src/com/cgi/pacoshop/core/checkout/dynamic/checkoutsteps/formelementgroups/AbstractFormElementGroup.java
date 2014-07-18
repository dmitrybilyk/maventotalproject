/*
* [y] hybris Platform
*
* Copyright (c) 2000-2013 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups;

import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.session.SessionCache;
import com.cgi.pacoshop.core.service.FormValidationService;
import com.cgi.pacoshop.core.service.PacoshopCartService;
import com.google.common.collect.Maps;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.commercefacades.user.data.TitleData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.AddressService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.*;
import javax.annotation.Resource;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;


/**
 * FormElementGroup
 * Represents logically grouped input fields in JSP page.
 * Examples: addressFormElement, paymentInformationFormElement
 *
 * @module hybris - pacoshopacoshopcore
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public abstract class AbstractFormElementGroup implements FormElementGroup
{

	private static final Logger LOG = Logger.getLogger(AbstractFormElementGroup.class);

	@Resource
	private FormValidationService formValidationService;

    @Resource
    private PacoshopCartService pacoshopCartService;

    @Resource
    private ConfigurationService configurationService;

    @Resource
    private ModelService modelService;

    @Resource
    private AddressService addressService;



    /**
     * Getter for configuration properties.

     * @param key - property name.
     * @return  - property value in configuration config.
     */
    protected String getConfigurationProperty(final String key)
    {
        final Configuration configuration = configurationService.getConfiguration();
        return configuration.getString(key);
    }

    /**
     * ValidationService getter.
     * @return  - ValidationService.
     */
    protected FormValidationService getValidationService()
    {
        return formValidationService;
    }

    /**
     * Copy information from customer profile, which is either updated from SSO, or taken from hybris.
     * The information Should be copied ONLY ONCE!!!!
     *
     * @param cart - cart object linked to a current session.
     * @param customerModel - Customer Data.
     */
    @Override
    public void prefillCartFromCustomerProfile(final CartModel cart, final CustomerModel customerModel)
    {

    }

    @Override
    public Map<String, Object> populatePageModelData(final CartModel cartModel)
    {
        return new HashMap<>();
    }

    /**
     * CartService getter.
     * @return CartService. cart service
     */
    protected PacoshopCartService getCartService()
    {
        return pacoshopCartService;
    }

    /**
     * ModelService getter.
     * @return ModelService. model service
     */
    public ModelService getModelService()
    {
        return modelService;
    }

    @Override
    public boolean isPrefilled(final CartModel cartModel)
    {
        return true;
    }

    @Override
    public boolean validate(final FormDTO dto, final BindingResult bindingResult)
    {
        return true;
    }

    @Override
    public void populateFormFromCart(final FormDTO dto, final CartModel cart)
    {
    }

    @Override
    public abstract String getKey();

    @Override
    public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
    {
    }

    @Override
    public Set<String> getMandatoryFieldNames(final CartModel cart)
    {
        return Collections.EMPTY_SET;
    }

    /**
     * Determines if the product is online article.
     * @param productModel product
     * @return true if product is online article and false otherwise
     */
    protected boolean isOnlineArtikel(final ProductModel productModel)
    {
        final String onlineArtikel = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);
        return productModel.getProductType().getCode().equals(onlineArtikel);
    }

    // FIXME: unclear where to move that. For now will stay here in DynamicCheckoutFacade.
    /**
     * Used for showing breadcrumb.
     *
     * @param cartModel Cart object
     * @param configurationService Configuration service to use internally
     *
     * @return true if breadcrumb should be displayed and false otherwise
     */
    public static boolean isBreadcrumbDisplayed(final CartModel cartModel, final ConfigurationService configurationService)
    {
        Assert.notNull(cartModel, "cartModel must not be empty");
        Assert.notNull(configurationService, "configurationService must not be empty");

        String onlineArticle = configurationService.getConfiguration().getString(FormElementGroupConstants.ONLINE_ARTICLE);

        // This is a compromise solution - no easy way to find a place for the logic
        // that shows/hides breadcrumb depending on product type
        for (final AbstractOrderEntryModel abstractOrderEntryModel : cartModel.getEntries())
        {
            final ProductModel product = abstractOrderEntryModel.getProduct();
            if (product.getProductType().getCode().equals(onlineArticle))
            {
                return false;
            }
        }
        return true;
    }
}
