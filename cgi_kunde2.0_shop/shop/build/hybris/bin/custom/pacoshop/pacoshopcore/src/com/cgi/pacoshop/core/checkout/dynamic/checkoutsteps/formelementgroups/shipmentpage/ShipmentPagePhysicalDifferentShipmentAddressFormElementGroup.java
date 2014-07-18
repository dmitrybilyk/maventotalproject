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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.shipmentpage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractDeliveryAddressValidatingFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentAddressDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.comparators.AddressDTOLastUpdateDateComparator;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.CustomerAddressService;
import com.google.common.collect.Sets;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 * FEG for showing Different Shipment Address.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 12, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class ShipmentPagePhysicalDifferentShipmentAddressFormElementGroup
        extends AbstractDeliveryAddressValidatingFormElementGroup
{

	private static final Set<String> MANDATORY_FIELD_NAMES = Sets.newHashSet(
			  Arrays.asList(FormElementGroupConstants.NEW_SHIPMENT_SALUTATION,
								 FormElementGroupConstants.NEW_SHIPMENT_FIRST_NAME,
								 FormElementGroupConstants.NEW_SHIPMENT_LAST_NAME,
								 FormElementGroupConstants.NEW_SHIPMENT_STREET,
								 FormElementGroupConstants.NEW_SHIPMENT_HOUSE_NUMBER,
								 FormElementGroupConstants.NEW_SHIPMENT_PLZ_ZIP,
								 FormElementGroupConstants.NEW_SHIPMENT_CITY_ORT,
								 FormElementGroupConstants.NEW_SHIPMENT_COUNTRY)
	);

	@Resource
	private CustomerAddressService customerAddressService;

	/**
	 * Method to determine whether the  formelementgroup needs to be displayed in the page based on product types in the cart
	 * and choices made by the customer in the checkout flow.
	 *
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
			if (isDifferentShipmentAddressNeeded(productModel, cart))
			{
				return true;
			}

		}
		return false;

	}


	private boolean isDifferentShipmentAddressNeeded(final ProductModel productModel, final CartModel cart)
	{
		return isPhysicalProduct(productModel)
				  && cart.getAdditionalShippingAddressWanted() != null && cart.getAdditionalShippingAddressWanted();
	}

	private boolean isPhysicalProduct(final ProductModel productModel)
	{
		final String good = getConfigurationProperty(FormElementGroupConstants.GOOD);
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);

		return ((productModel.getProductType().getCode().equals(good)
				  || productModel.getProductType().getCode().equals(printAbo))
				  && productModel.getDifferingConsigneeAllowed());
	}


	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		boolean result = getValidationService().
				  validateDifferentPhysicalDeliveryAddress((ShipmentInfoFormDTO) dto, bindingResult);

		return result && super.validate(dto, bindingResult);
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
		super.populateFormFromCart(dto, cart);

		ShipmentInfoFormDTO shipmentInfoFormDTO = (ShipmentInfoFormDTO) dto;

		populateAddressToFormDTO(cart.getDeliveryAddress(), shipmentInfoFormDTO);

		List<ShipmentAddressDTO> allShipmentAddresses = getShipmentAddresses(cart);
		if (allShipmentAddresses != null)
		{
			// split all potential shipment addresses to two lists according to its countryCode
			List<ShipmentAddressDTO> suitableShipmentAddresses = new ArrayList<>();
			List<ShipmentAddressDTO> unsuitableShipmentAddresses = new ArrayList<>();
			Collection<CountryData> deliveryCountries = getDeliveryCountries(cart);
			Assert.notNull(deliveryCountries);
			Set<String> suitableCountryCodes = new HashSet<>(deliveryCountries.size());
			for (CountryData deliveryCountry : deliveryCountries)
			{
				suitableCountryCodes.add(deliveryCountry.getIsocode());
			}
			for (ShipmentAddressDTO shipmentAddress : allShipmentAddresses)
			{
				if (suitableCountryCodes.contains(shipmentAddress.getCountryCode()))
				{
					suitableShipmentAddresses.add(shipmentAddress);
				}
				else
				{
					unsuitableShipmentAddresses.add(shipmentAddress);
				}
			}
			shipmentInfoFormDTO.setSuitableShipmentAddressModels(suitableShipmentAddresses);
			shipmentInfoFormDTO.setUnsuitableShipmentAddressModels(unsuitableShipmentAddresses);
		}

		final AddressModel paymentAddress = cart.getDeliveryAddress();
		if (paymentAddress != null)
		{
			final TitleModel title = paymentAddress.getTitle();
			if (title != null)
			{
				shipmentInfoFormDTO.setNewShipmentSalutation(title.getCode());
			}
			shipmentInfoFormDTO.setNewShipmentFirstName(paymentAddress.getFirstname());
			shipmentInfoFormDTO.setNewShipmentLastName(paymentAddress.getLastname());
			shipmentInfoFormDTO.setNewShipmentEmail(paymentAddress.getEmail());
			final Title2Model title2 = paymentAddress.getTitle2();
			if (title2 != null)
			{
				shipmentInfoFormDTO.setNewShipmentTitle(title2.getCode());
			}
			shipmentInfoFormDTO.setNewShipmentStreet(paymentAddress.getLine1());
			shipmentInfoFormDTO.setNewShipmentHouseNumber(paymentAddress.getLine2());
			shipmentInfoFormDTO.setNewShipmentAdditionalStreet(paymentAddress.getLine3());
			shipmentInfoFormDTO.setNewShipmentZip(paymentAddress.getPostalcode());
			shipmentInfoFormDTO.setNewShipmentCity(paymentAddress.getTown());
			shipmentInfoFormDTO.setNewShipmentCountry(getCountryCode(paymentAddress));
			shipmentInfoFormDTO.setNewShipmentCompany(paymentAddress.getCompany());
		}
	}

	private List<ShipmentAddressDTO> getShipmentAddresses(final CartModel cart)
	{
		List<ShipmentAddressDTO> addresses = new ArrayList<ShipmentAddressDTO>();
		UserModel user = cart.getUser();
		if (user instanceof CustomerModel)
		{
			CustomerModel customer = (CustomerModel) user;
			AddressModel mainAddress = customer.getMainAddress();
			for (AddressModel address : customer.getAddresses())
			{
				if (!customerAddressService.compareAddressesByFields(address, mainAddress))
				{
					ShipmentAddressDTO shipmentAddressDTO = new ShipmentAddressDTO();

					populateAddressToAddressDTO(address, shipmentAddressDTO);

					addresses.add(shipmentAddressDTO);

				}
			}
		}

		AddressDTOLastUpdateDateComparator addressDTOLastUpdateDateComparator = new AddressDTOLastUpdateDateComparator();
		Collections.sort(addresses, addressDTOLastUpdateDateComparator);

		return addresses;
	}

	private void populateAddressToAddressDTO(final AddressModel addressModel, final ShipmentAddressDTO shipmentAddressDTO)
	{
		shipmentAddressDTO.setFirstname(addressModel.getFirstname());
		shipmentAddressDTO.setCountry(getCountryName(addressModel));
		shipmentAddressDTO.setLastname(addressModel.getLastname());
		shipmentAddressDTO.setPostalcode(addressModel.getPostalcode());
		shipmentAddressDTO.setSalutation(addressModel.getTitle().getCode());
		Title2Model title2 = addressModel.getTitle2();
		if (title2 != null)
		{
			shipmentAddressDTO.setTitle(title2.getName());
         shipmentAddressDTO.setTitleCode(title2.getCode());
		}
		shipmentAddressDTO.setStreet(addressModel.getLine1());
		shipmentAddressDTO.setHouseNumber(addressModel.getLine2());
		shipmentAddressDTO.setAdditionalStreet(addressModel.getLine3());
		shipmentAddressDTO.setEmail(addressModel.getEmail());
		shipmentAddressDTO.setCompany(addressModel.getCompany());
		shipmentAddressDTO.setCountryCode(getCountryCode(addressModel));
		shipmentAddressDTO.setTown(addressModel.getTown());
		shipmentAddressDTO.setBusinessPartnerId(addressModel.getBusinessPartnerId());
		shipmentAddressDTO.setLastUpdateDate(addressModel.getLastUpdate());
	}

	private void populateAddressToFormDTO(final AddressModel addressModel, final ShipmentInfoFormDTO shipmentInfoFormDTO)
	{
		if (addressModel != null)
		{
			shipmentInfoFormDTO.setFirstName(addressModel.getFirstname());
			shipmentInfoFormDTO.setCountry(getCountryName(addressModel));
			shipmentInfoFormDTO.setLastName(addressModel.getLastname());
			shipmentInfoFormDTO.setZip(addressModel.getPostalcode());
			shipmentInfoFormDTO.setSalutation(addressModel.getTitle().getCode());
			Title2Model title2 = addressModel.getTitle2();
			if (title2 != null)
			{
				shipmentInfoFormDTO.setTitle(title2.getCode());
			}
			shipmentInfoFormDTO.setStreet(addressModel.getLine1());
			shipmentInfoFormDTO.setHouseNumber(addressModel.getLine2());
			shipmentInfoFormDTO.setAdditionalStreet(addressModel.getLine3());
			shipmentInfoFormDTO.setEmail(addressModel.getEmail());
			shipmentInfoFormDTO.setCompany(addressModel.getCompany());
			shipmentInfoFormDTO.setCountry(getCountryCode(addressModel));
			shipmentInfoFormDTO.setCity(addressModel.getTown());
		}
	}

	private String getCountryName(final AddressModel addressModel)
	{
		String countryName = StringUtils.EMPTY;
		if (addressModel.getCountry() != null)
		{
			countryName = addressModel.getCountry().getName();
		}
		return countryName;
	}

	private String getCountryCode(final AddressModel addressModel)
		{
			String countryCode = StringUtils.EMPTY;
			if (addressModel.getCountry() != null)
			{
				countryCode = addressModel.getCountry().getIsocode();
			}
			return countryCode;
		}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(
				  FormElementGroupConstants.PHYSICAL_DIFFERENT_SHIPMENT_ADDRESS_ON_SHIPMENT_PAGE_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		getCartService().saveDifferentShipmentAddress((ShipmentInfoFormDTO) dto, cart);
	}

	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return MANDATORY_FIELD_NAMES;
	}


	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		AddressModel addressModel = cartModel.getDeliveryAddress();
		if (addressModel != null)
		{
			if ((addressModel.getTitle() != null)
					  && StringUtils.isNotEmpty(addressModel.getFirstname())
					  && StringUtils.isNotEmpty(addressModel.getLastname())
					  && StringUtils.isNotEmpty(addressModel.getLine1())
					  && StringUtils.isNotEmpty(addressModel.getLine2())
					  && StringUtils.isNotEmpty(addressModel.getPostalcode())
					  && StringUtils.isNotEmpty(addressModel.getTown())
					  && (addressModel.getCountry() != null))
			{
				return true;
			}

		}
		return false;
	}
	@Override
	public Map<String, Object> populatePageModelData(final CartModel cartModel)
	{
		Map<String, Object> model = super.populatePageModelData(cartModel);
		model.put(FormElementGroupConstants.DELIVERY_COUNTRIES_MODEL_ATTR, getDeliveryCountries(cartModel));
		return model;
	}
}
