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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.customerdatapage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.model.Title2Model;
import static com.cgi.pacoshop.core.util.LogHelper.debug;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 * The type Download product type form element group.
 * @module hybris - pacoshopacoshopcore
 * @version 1.0v Dec 30, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ievgen Azarenkov <ievgen.azarenkov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public class DownloadProductTypeFormElementGroup extends AbstractMandatoryAddressFormElementGroup
{
    private static final Logger LOG = Logger.getLogger(DownloadProductTypeFormElementGroup.class);

    /**
     * Method to determine whether the  formelementgroup needs to be displayed in the page based on product types
     * in the cart and choices made by the customer in the checkout flow.
     * @param cart - cart object linked to a current session
     * @return true if the formelementgroup is required in current checkout flow
     */
    @Override
    public boolean isDisplayed(final CartModel cart)
    {
        Assert.notNull(cart, "cart is null");

        List<AbstractOrderEntryModel> entries = cart.getEntries();
        for (AbstractOrderEntryModel entry : entries)
        {
            ProductModel productModel = entry.getProduct();
            if (isDownload(productModel))
            {
                return true;
            }

        }
        return false;
    }

    private boolean isDownload(final ProductModel productModel)
    {
        final String download = getConfigurationProperty(FormElementGroupConstants.DOWNLOAD);
        return productModel.getProductType().getCode().equals(download);
    }

	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		Boolean result = cart.getShowStepForDownload();
		debug(LOG, "%s.isPrefilled: !cart.getShowStepForDownload() = %b", "DownloadProductTypeFormElementGroup",
				!result);
		return !result;
	}


    @Override
    public boolean validate(final FormDTO dto, final BindingResult bindingResult)
    {
        return getValidationService().validateCustomerDataDownloadPage((CheckoutFormDTO) dto, bindingResult);
    }

    /**
     * Mandatory customer data is.
     * Salutation
     * Surname
     * Last Name
     * E-Mail
     * Street
     * Postal Code
     * Town
     * Country
     *
     * @param dto - Form data transfer object.
     * @param cart - cart object linked to a current session.
     */
    @Override
    public void populateFormFromCart(final FormDTO dto, final CartModel cart)
    {
        super.populateFormFromCart(dto, cart);

        final AddressModel customerAddress = cart.getCustomerAddress();
        if (dto instanceof CheckoutFormDTO && customerAddress != null)
		{
			//=======================mandatory data start==========================================
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			final TitleModel title = customerAddress.getTitle();
			if (title != null)
			{
				checkoutFormDTO.setSalutation(title.getCode());
			}
			final Title2Model title2 = customerAddress.getTitle2();
			if (title2 != null)
			{
				checkoutFormDTO.setTitle(title2.getCode());
			}
			checkoutFormDTO.setFirstName(customerAddress.getFirstname());
			checkoutFormDTO.setLastName(customerAddress.getLastname());
			if (isCustomerAddressInDeliveryCountries(cart))
			{
				//https://jira.symmetrics.de/browse/KS-364:
				//In case the Customer is logged in and we have a prefilling of
				// the customer address based on the profile information,
				// the system needs to validate whether the prefilled information is compatible with the list of available countries.
				// In case it is not (e.g. customer has got a French address but product in cart can only be delivered in Germany),
				// the system does NOT prefill the customer address on the cart and does not jump over the customer page.
				//=======================customer address start==========================================
				checkoutFormDTO.setEmail(customerAddress.getEmail());
				checkoutFormDTO.setMobilePhoneNumber(customerAddress.getMobileNumber());
				checkoutFormDTO.setHomePhoneNumber(customerAddress.getHomeNumber());
				checkoutFormDTO.setBusinessPhoneNumber(customerAddress.getBusinessNumber());
				checkoutFormDTO.setAdditionalStreet(customerAddress.getLine3());
				checkoutFormDTO.setStreet(customerAddress.getLine1());
				checkoutFormDTO.setHouseNumber(customerAddress.getLine2());
				checkoutFormDTO.setZip(customerAddress.getPostalcode());
				checkoutFormDTO.setCity(customerAddress.getTown());
				final CountryModel country = customerAddress.getCountry();
				if (country != null)
				{
					checkoutFormDTO.setCountry(country.getIsocode());
				}
				//=======================customer address end==========================================
			}
			//=======================mandatory data end==========================================
			final Date dateOfBirth = customerAddress.getDateOfBirth();
            checkoutFormDTO.setDateOfBirth(dateOfBirth);
		}
	}

	/**
	 * Mandatory customer data is.
	 * Salutation
	 * Surname
	 * Last Name
	 * E-Mail
	 * Street
	 * Postal Code
	 * Town
	 * Country
	 * @param cart - cart object linked to a current session.
	 * @param customerModel - Customer Data.
	 */
	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart, final CustomerModel customerModel)
	{
		getCartService().prefillAndSaveCustomerAddressToCart(customerModel, cart);
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return getAddressMandatoryFieldNames();
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.DOWNLOAD_PRODUCT_TYPE_FORM_ELEMENT_GROUP);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveCustomerDataForDownload((CheckoutFormDTO) dto, cart);
	}

	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		Map<String, Object> model = super.populatePageModelData(cartModel);
		//special case for download - it's delivery countries = all countries
		model.put(FormElementGroupConstants.DELIVERY_COUNTRIES_MODEL_ATTR, getAllCountries(cartModel));
		return model;
	}

}
