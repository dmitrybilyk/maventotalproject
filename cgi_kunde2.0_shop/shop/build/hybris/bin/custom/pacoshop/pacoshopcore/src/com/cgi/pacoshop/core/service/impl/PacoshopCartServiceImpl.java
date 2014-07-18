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


import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.ShipmentInfoFormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.impl.SummaryStepFormDTO;
import com.cgi.pacoshop.core.exceptions.CountryNotFoundException;
import com.cgi.pacoshop.core.model.PhoneNumberModel;
import com.cgi.pacoshop.core.model.TermAcceptedModel;
import com.cgi.pacoshop.core.model.Title2Model;
import com.cgi.pacoshop.core.service.CustomerAddressService;
import com.cgi.pacoshop.core.service.PacoshopCartService;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import com.cgi.pacoshop.core.service.Title2Service;
import com.cgi.pacoshop.core.util.FormElementGroupUtil;
import static com.cgi.pacoshop.core.util.LogHelper.error;

import com.cgi.pacoshop.core.util.LogHelper;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.AddressService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.tx.TransactionBody;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/**
 * Implementation of PacoshopCartService
 *
 *
 * @module pacoshopcore
 * @version 1.0v Jan 08, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@SuppressFBWarnings(
		  {"VA_FORMAT_STRING_BAD_CONVERSION" })
public class PacoshopCartServiceImpl implements PacoshopCartService
{
	private static final Logger LOG = Logger.getLogger(PacoshopCartServiceImpl.class);

	@Resource
	private ModelService             modelService;
	@Resource
	private AddressService           addressService;
	@Resource
	private DeliveryService          deliveryService;
	@Resource
	private UserService              userService;
	@Resource
	private CustomerAddressService   customerAddressService;
	@Resource
	private ShopConfigurationService shopConfigurationService;
	@Resource
	private Title2Service            title2Service;


	@Override
	public void saveCustomerDataForDownload(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		// TODO move 'if (!dto.isInvoiceWanted())' into formelementgroup
		//only for download type, at summary page is needed
		cartModel.setInvoiceWanted(dto.isInvoiceWanted());
		//for other types
		cartModel.setAdditionalInvoiceAddressWanted(dto.isInvoiceWanted());
		if (dto.isInvoiceWanted())
		{
			final AddressModel customerAddress = getCustomerAddressFromDTO(dto, cartModel);
			saveCustomerAddress(customerAddress, cartModel.getUser());
			cartModel.setCustomerAddress(customerAddress);
		}
		cartModel.setShowStepForDownload(false);
		modelService.save(cartModel);
	}


	@Override
	public void saveCustomerDataForGoodPrintDigital(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		AddressModel addressModel;
		if (dto instanceof ShipmentInfoFormDTO)
		{
			addressModel = getCustomerAddressFromDTO(dto, cartModel);
			addressModel.setShippingAddress(true);
			cartModel.setDeliveryAddress(addressModel);
		}
		else
		{
			addressModel = getCustomerAddressFromDTO(dto, cartModel);
			cartModel.setCustomerAddress(addressModel);
		}

		saveCustomerAddress(addressModel, cartModel.getUser());
		modelService.save(cartModel);
	}

	@Override
	public void saveInvoiceAddressForGoodPrintDigital(final ShipmentInfoFormDTO dto, final CartModel cartModel)
	{
		final AddressModel addressModel = getPaymentAddressModel(dto, cartModel);
		saveCustomerAddress(addressModel, cartModel.getUser());
		addressModel.setBillingAddress(cartModel.getAdditionalInvoiceAddressWanted());
		cartModel.setPaymentAddress(addressModel);
		PaymentInfoModel paymentInfo = cartModel.getPaymentInfo();
		if (paymentInfo != null)
		{
			paymentInfo.setBillingAddress(addressModel);
			modelService.save(paymentInfo);
		}
		modelService.saveAll(addressModel, cartModel);

	}

	@Override
	public void saveDifferentShipmentAddress(final ShipmentInfoFormDTO dto, final CartModel cartModel)
	{
		final AddressModel addressModel = getShipmentAddressModel(dto, cartModel);
		addressModel.setShippingAddress(cartModel.getAdditionalShippingAddressWanted());
		saveCustomerAddress(addressModel, cartModel.getUser());
		cartModel.setDeliveryAddress(addressModel);
		modelService.save(cartModel);

	}

	@Override
	public void saveDifferentShipmentAddressAllowed(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		String empty = "";
		boolean differentShipmentAddressWanted = dto.isDifferentShipmentAddress();
		cartModel.setAdditionalShippingAddressWanted(differentShipmentAddressWanted);
		if (!differentShipmentAddressWanted)
		{
			cartModel.setDeliveryAddress(null);
			if (dto instanceof ShipmentInfoFormDTO)
			{
				ShipmentInfoFormDTO shipmentInfoFormDTO = (ShipmentInfoFormDTO) dto;
				shipmentInfoFormDTO.setNewShipmentSalutation(empty);
				shipmentInfoFormDTO.setNewShipmentTitle(empty);
				shipmentInfoFormDTO.setNewShipmentFirstName(empty);
				shipmentInfoFormDTO.setNewShipmentLastName(empty);
				shipmentInfoFormDTO.setNewShipmentStreet(empty);
				shipmentInfoFormDTO.setNewShipmentAdditionalStreet(empty);
				shipmentInfoFormDTO.setNewShipmentZip(empty);
				shipmentInfoFormDTO.setNewShipmentCity(empty);
				shipmentInfoFormDTO.setNewShipmentCountry(empty);
				shipmentInfoFormDTO.setNewShipmentEmail(empty);
				shipmentInfoFormDTO.setNewShipmentCompany(empty);
			}
		}
		modelService.save(cartModel);
	}

	@Override
	public void saveDifferentInvoiceAddressAllowed(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		String empty = "";
		boolean additionalInvoiceAddressWanted = dto.isDifferentInvoiceAddress();
		cartModel.setAdditionalInvoiceAddressWanted(additionalInvoiceAddressWanted);
		if (!additionalInvoiceAddressWanted)
		{
			cartModel.setPaymentAddress(null);
			if (dto instanceof ShipmentInfoFormDTO)
			{
				ShipmentInfoFormDTO shipmentInfoFormDTO = (ShipmentInfoFormDTO) dto;
				shipmentInfoFormDTO.setBillingSalutation(empty);
				shipmentInfoFormDTO.setBillingTitle(empty);
				shipmentInfoFormDTO.setBillingFirstName(empty);
				shipmentInfoFormDTO.setBillingLastName(empty);
				shipmentInfoFormDTO.setBillingStreet(empty);
				shipmentInfoFormDTO.setBillingAdditionalStreet(empty);
				shipmentInfoFormDTO.setBillingZip(empty);
				shipmentInfoFormDTO.setBillingCity(empty);
				shipmentInfoFormDTO.setBillingCountry(empty);
				shipmentInfoFormDTO.setBillingEmail(empty);
				shipmentInfoFormDTO.setBillingCompany(empty);
			}
		}
		modelService.save(cartModel);
	}

	@Override
	public void saveDeliveryDateData(final ShipmentInfoFormDTO dto, final CartModel cartModel)
	{
		// set delivery now flag
		cartModel.setDeliveryNow(dto.isDeliveryStart());
		// if deliveryNow=false, set delivery start date
		cartModel.setDeliveryStartDate(dto.getDeliveryStartDate());

		modelService.save(cartModel);
	}

	@Override
	public void saveSummaryStep(final SummaryStepFormDTO dto, final CartModel cartModel)
	{
		if (dto.isAgb())
		{
			cartModel.setAgb(Boolean.TRUE);
			// save opt-in flag
			Boolean optInDTOValue = dto.getOptIn();
			// the optInDTOValue could be null in case when Opt-in checkbox was not shown to user on the summary step form.
			if (optInDTOValue != null)
			{
				if (optInDTOValue)
				{
					// If opt-in term is accepted by user then we will get this term from customerModel and save into cartModel.
					// All other terms in cartModel should stay untouched.
					String optInTermName = shopConfigurationService.getOptInTermName();
					Assert.notNull(optInTermName, "the terms.optin.termtype.name configuration value isn't found");
					UserModel customerModel = cartModel.getUser();
					if (customerModel instanceof CustomerModel)
					{
						Set<TermAcceptedModel> customerTerms = ((CustomerModel) customerModel).getTermsAccepted();
						if (customerTerms != null)
						{
							for (TermAcceptedModel customerTerm : customerTerms)
							{
								if (optInTermName.equals(customerTerm.getTermsVersion().getName()))
								{
									Set<TermAcceptedModel> cartTerms = cartModel.getTermsAccepted();
									if (cartTerms != null && !cartTerms.isEmpty())
									{
										cartTerms = new HashSet<>(cartTerms);
									}
									else
									{
										cartTerms = new HashSet<>();
									}
									//get opt-in term from cart if it already stored in cart
									TermAcceptedModel optInTerm = customerTerm;
									for (TermAcceptedModel cartTerm : cartTerms)
									{
										if (cartTerm.getTermsVersion().getTermsVersionId() != null
												  && cartTerm.getTermsVersion().equals(customerTerm.getTermsVersion()))
										{
											optInTerm = cartTerm;
											break;
										}
									}
									optInTerm.setConfirmed(true);
									modelService.save(optInTerm);
									cartTerms.add(optInTerm);
									cartModel.setTermsAccepted(cartTerms);
									break;
								}
							}
						}
					}
				}
				else
				{
					// if the optin term is already present in the cart, we should remove it from the cart accepted terms
					String optInTermName = shopConfigurationService.getOptInTermName();
					Assert.notNull(optInTermName, "the terms.optin.termtype.name configuration value isn't found");
					Set<TermAcceptedModel> cartTerms = cartModel.getTermsAccepted();
					if (cartTerms != null)
					{
						for (TermAcceptedModel cartTerm : cartTerms)
						{
							if (optInTermName.equals(cartTerm.getTermsVersion().getType()))
							{
								cartTerms = new HashSet<>(cartTerms);
								cartTerms.remove(cartTerm);
								cartModel.setTermsAccepted(cartTerms);
								break;
							}
						}
					}
				}
			}
			modelService.save(cartModel);
		}
	}

	private TitleModel getTitleModel(final String salutation)
	{
		return userService.getTitleForCode(salutation);
	}

	private Title2Model getTitle2Model(final String title2)
	{
		title2Service.getAllTitles2();
		if (StringUtils.isEmpty(title2))
		{
			return null;
		}
		else
		{
			return title2Service.getTitle2ForCode(title2);
		}
	}

	private AddressModel getCustomerAddressFromDTO(final CheckoutFormDTO dto, final CartModel cartModel)
	{
        AddressModel customerAddress =  cartModel.getCustomerAddress() != null
                ? cartModel.getCustomerAddress() : addressService.createAddressForOwner(cartModel);

      customerAddress.setMobileNumber(
				  createNumberIfNotExist(customerAddress.getMobileNumber(),
												 dto.getMobilePrefix(), null, dto.getMobileNumber()));
		customerAddress.setHomeNumber(
				  createNumberIfNotExist(customerAddress.getHomeNumber(),
												 dto.getPhonePrefixHome(), dto.getPhoneExtensionHome(), dto.getPhoneNumberHome())
		);
		customerAddress.setBusinessNumber(
				  createNumberIfNotExist(customerAddress.getBusinessNumber(),
												 dto.getPhonePrefixBusiness(), dto.getPhoneExtensionBusiness(), dto.getPhoneNumberBusiness())
		);

		customerAddress.setDateOfBirth(getBirthDate(dto.getBirthDateDay(), dto.getBirthDateMonth(), dto.getBirthDateYear()));

		if (StringUtils.isNotEmpty(dto.getSalutation()))
		{
			customerAddress.setTitle(getTitleModel(dto.getSalutation()));
		}
		if (StringUtils.isNotEmpty(dto.getTitle()))
		{
			customerAddress.setTitle2(getTitle2Model(dto.getTitle()));
		}
		else
		{
			customerAddress.setTitle2(null);
		}
		customerAddress.setFirstname(dto.getFirstName());
		customerAddress.setLastname(dto.getLastName());
		customerAddress.setLine1(dto.getStreet());
		customerAddress.setLine2(dto.getHouseNumber());
		customerAddress.setLine3(dto.getAdditionalStreet());
		customerAddress.setPostalcode(dto.getZip());
		customerAddress.setTown(dto.getCity());
		if (StringUtils.isNotEmpty(dto.getCountry()))
		{
			customerAddress.setCountry(getCountryModel(dto.getCountry()));
		}
		customerAddress.setEmail(dto.getEmail());
		customerAddress.setCompany(dto.getCompany());
		customerAddress.setRoleInCompany(dto.getPositionCompany());

		modelService.save(customerAddress);

      return customerAddress;
	}

	private PhoneNumberModel createNumberIfNotExist(final PhoneNumberModel phoneNumber, final String prefix,
																	final String extension, final String number)
	{
		PhoneNumberModel result = null;
		if (StringUtils.isNotEmpty(prefix) || StringUtils.isNotEmpty(extension) || StringUtils.isNotEmpty(number))
		{
			result = phoneNumber != null ? phoneNumber : modelService.<PhoneNumberModel>create(PhoneNumberModel.class);

			result.setPrefix(prefix);
			result.setExtension(extension);
			result.setNumber(number);
		}
		return result;
	}

	private AddressModel getPaymentAddressModel(final ShipmentInfoFormDTO dto, final CartModel cartModel)
	{
		final AddressModel addressModel = addressService.createAddressForOwner(cartModel);
		if (!StringUtils.isEmpty(dto.getBillingSalutation()))
		{
			addressModel.setTitle(getTitleModel(dto.getBillingSalutation()));
		}
		if (StringUtils.isNotEmpty(dto.getBillingTitle()))
		{
			addressModel.setTitle2(getTitle2Model(dto.getBillingTitle()));
		}
		else
		{
			addressModel.setTitle2(null);
		}
		addressModel.setFirstname(dto.getBillingFirstName());
		addressModel.setLastname(dto.getBillingLastName());
		addressModel.setLine1(dto.getBillingStreet());
		addressModel.setLine2(dto.getBillingHouseNumber());
		addressModel.setLine3(dto.getBillingAdditionalStreet());
		addressModel.setPostalcode(dto.getBillingZip());
		addressModel.setTown(dto.getBillingCity());
		if (!StringUtils.isEmpty(dto.getBillingCountry()))
		{
			addressModel.setCountry(getCountryModel(dto.getBillingCountry()));
		}
		addressModel.setEmail(dto.getBillingEmail());
		addressModel.setCompany(dto.getBillingCompany());
		return addressModel;
	}

	private Date getBirthDate(final String birthDateDay, final String birthDateMonth, final String birthDateYear)
	{
		try
		{
			return FormElementGroupUtil.parseDate(birthDateDay, birthDateMonth, birthDateYear);
		}
		catch (ParseException e)
		{
			error(LOG, "Can't parse date: birthDateDay - %s, birthDateMonth - %s, birthDateYear - %s",
					birthDateDay, birthDateMonth, birthDateYear);
			return null;
		}
	}


	private AddressModel getShipmentAddressModel(final ShipmentInfoFormDTO dto, final CartModel cartModel)
	{
		final AddressModel addressModel = addressService.createAddressForOwner(cartModel);
		if (!StringUtils.isEmpty(dto.getNewShipmentSalutation()))
		{
			addressModel.setTitle(getTitleModel(dto.getNewShipmentSalutation()));
		}
		if (StringUtils.isNotEmpty(dto.getNewShipmentTitle()))
		{
			addressModel.setTitle2(getTitle2Model(dto.getNewShipmentTitle()));
		}
		else
		{
			addressModel.setTitle2(null);
		}
		addressModel.setFirstname(dto.getNewShipmentFirstName());
		addressModel.setLastname(dto.getNewShipmentLastName());
		addressModel.setLine1(dto.getNewShipmentStreet());
		addressModel.setLine2(dto.getNewShipmentHouseNumber());
		addressModel.setLine3(dto.getNewShipmentAdditionalStreet());
		addressModel.setPostalcode(dto.getNewShipmentZip());
		addressModel.setTown(dto.getNewShipmentCity());
		if (!StringUtils.isEmpty(dto.getNewShipmentCountry()))
		{
			addressModel.setCountry(getCountryModel(dto.getNewShipmentCountry()));
		}
		addressModel.setEmail(dto.getNewShipmentEmail());
		addressModel.setCompany(dto.getNewShipmentCompany());
		return addressModel;
	}

	private CountryModel getCountryModel(final String countryIso) throws CountryNotFoundException
	{
		return  deliveryService.getCountryForCode(countryIso);
	}

	@Override
	public void saveAboNumber(final String aboNumber, final CartModel cartModel)
	{
		cartModel.setExistingSubscriptionId(aboNumber);
		modelService.save(cartModel);
		if (cartModel.getUser() instanceof CustomerModel)
		{
			final CustomerModel customerModel = (CustomerModel) cartModel.getUser();
			customerModel.setExistingSubscriptionId(aboNumber);
			modelService.save(customerModel);
		}
		else
		{

			LOG.warn(String.format("User with uid %s is not a Customer, so we can't save aboNumber number to the Customer profile",
										  cartModel.getUser().getUid()));
		}
	}

	@Override
	public void saveCustomerInfoForPhysicalBonus(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		AddressModel bonusRecipientAddress = cartModel.getBonusRecipientAddress();
		if (bonusRecipientAddress == null)
		{
			bonusRecipientAddress = addressService.createAddressForOwner(cartModel);
			cartModel.setBonusRecipientAddress(bonusRecipientAddress);
		}

		bonusRecipientAddress.setFirstname(dto.getReferralFirstName());
		bonusRecipientAddress.setLastname(dto.getReferralLastName());
		if (StringUtils.isNotEmpty(dto.getReferralSalutation()))
		{
			bonusRecipientAddress.setTitle(getTitleModel(dto.getReferralSalutation()));
		}
		if (StringUtils.isNotEmpty(dto.getReferralTitle()))
		{
			bonusRecipientAddress.setTitle2(getTitle2Model(dto.getReferralTitle()));
		}
		else
		{
			bonusRecipientAddress.setTitle2(null);
		}
		if (StringUtils.isNotEmpty(dto.getReferralCountry()))
		{
			bonusRecipientAddress.setCountry(getCountryModel(dto.getReferralCountry()));
		}
		bonusRecipientAddress.setPostalcode(dto.getReferralZip());
		bonusRecipientAddress.setTown(dto.getReferralCity());
		bonusRecipientAddress.setLine1(dto.getReferralStreet());
		bonusRecipientAddress.setLine2(dto.getReferralHouseNumber());
		bonusRecipientAddress.setLine3(dto.getReferralAdditionalAddress());

		modelService.saveAll(bonusRecipientAddress, cartModel);
	}

	@Override
	public void savePaymentInfoForMonetaryBonus(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		PacoDebitPaymentInfoModel pacoDebitPaymentInfoModel;
		PaymentInfoModel paymentInfo = cartModel.getBonusRecipientPaymentInfo();

		if (paymentInfo == null)
		{
			paymentInfo = modelService.create(PacoDebitPaymentInfoModel.class);
		}

		if (paymentInfo instanceof PacoDebitPaymentInfoModel)
		{
			pacoDebitPaymentInfoModel = (PacoDebitPaymentInfoModel) paymentInfo;
			pacoDebitPaymentInfoModel.setFirstName(dto.getFirstNameForMonetaryBonus());
			pacoDebitPaymentInfoModel.setLastName(dto.getLastNameForMonetaryBonus());
			if (StringUtils.isNotEmpty(dto.getBic()) && StringUtils.isNotEmpty(dto.getIban())
					  && StringUtils.isNotEmpty(dto.getIbanBAClastName()) && StringUtils.isNotEmpty(dto.getIbanBACfirstName()))
			{
				pacoDebitPaymentInfoModel.setFirstName(dto.getIbanBACfirstName());
				pacoDebitPaymentInfoModel.setLastName(dto.getIbanBAClastName());
				pacoDebitPaymentInfoModel.setBIC(dto.getBic());
				pacoDebitPaymentInfoModel.setIBAN(dto.getIban());
			}
			else if (StringUtils.isNotEmpty(dto.getAccountNumber()) && StringUtils.isNotEmpty(dto.getBanIdNumber())
					  && StringUtils.isNotEmpty(dto.getKontonummerBLZlastName())
					  && StringUtils.isNotEmpty(dto.getKontonummerBLZfirstName()))
			{
				pacoDebitPaymentInfoModel.setFirstName(dto.getKontonummerBLZfirstName());
				pacoDebitPaymentInfoModel.setLastName(dto.getKontonummerBLZlastName());
				pacoDebitPaymentInfoModel.setAccountNumber(dto.getAccountNumber());
				pacoDebitPaymentInfoModel.setBankIdNumber(dto.getBanIdNumber());
			}
			//else LOG warn

			final UserModel cartUserModel = cartModel.getUser();
			// pacoDebitPaymentInfo.user - non-writable value
			if (pacoDebitPaymentInfoModel.getUser() == null)
			{
				pacoDebitPaymentInfoModel.setUser(cartUserModel);
			}
			pacoDebitPaymentInfoModel.setCode(((new StringBuilder(String.valueOf(cartUserModel.getUid()))).append("_").append(
					  UUID.randomUUID()).toString()));
			cartModel.setBonusRecipientPaymentInfo(pacoDebitPaymentInfoModel);
			modelService.saveAll(pacoDebitPaymentInfoModel, cartModel);
		}
		else
		{
			LOG.warn(String.format(

					  "Can't save paymentInfo for monetary bonus. Cart with guid %s has wrong paymentInfo type - %s instead %s type",
					  cartModel.getGuid(), paymentInfo.getClass().getName(), PacoDebitPaymentInfoModel.class.getName()));
		}
	}

	@Override
	public void saveMilesAndMoreNumberToBonusRecipientAddress(final String milesAndMoreNumber, final CartModel cartModel)
	{
		AddressModel bonusRecipientAddress = cartModel.getBonusRecipientAddress();
		if (bonusRecipientAddress == null)
		{
			bonusRecipientAddress = addressService.createAddressForOwner(cartModel);
			cartModel.setBonusRecipientAddress(bonusRecipientAddress);
		}
		bonusRecipientAddress.setMilesAndMoreNumber(milesAndMoreNumber);
		modelService.saveAll(bonusRecipientAddress, cartModel);
	}

	@Override
	public void saveMilesAndMoreNumberToCustomerAddress(final String milesAndMoreNumber, final CartModel cartModel)
	{
		AddressModel customerAddress = cartModel.getCustomerAddress();
		if (customerAddress == null)
		{
			customerAddress = addressService.createAddressForOwner(cartModel);
			cartModel.setCustomerAddress(customerAddress);
		}
		customerAddress.setMilesAndMoreNumber(milesAndMoreNumber);
		modelService.saveAll(customerAddress, cartModel);
	}


	private void saveCustomerAddress(final AddressModel customerAddress, final UserModel user)
	{
		final AddressModel customerAddressClone = modelService.clone(customerAddress, AddressModel.class);
		customerAddressService.saveCustomerAddressWithLimit(customerAddressClone, user);
	}


	@Override
	public void saveStudentGraduationDateToCustomerProfileAndCart(final Date studentGraduationDate, final CartModel cartModel)
	{
		final UserModel userModel = cartModel.getUser();
		CustomerModel customerModel;

		if (userModel instanceof CustomerModel)
		{
			customerModel = (CustomerModel) userModel;
			customerModel.setStudentGraduationDate(studentGraduationDate);
			cartModel.setStudentGradutationDate(studentGraduationDate);
			modelService.saveAll(customerModel, cartModel);
		}
		else
		{
			LOG.warn(String.format(
					  "User with uid %s is not a Customer, so we can't save student graduation date value to the Customer profile",
					  cartModel.getUser().getUid()));
		}
	}

	@Override
	public void saveCustomerDataForNewsletter(final CheckoutFormDTO dto, final CartModel cartModel)
	{
		AddressModel customerAddress = addressService.createAddressForOwner(cartModel);

		if (StringUtils.isNotEmpty(dto.getSalutation()))
		{
			customerAddress.setTitle(getTitleModel(dto.getSalutation()));
		}
		customerAddress.setFirstname(dto.getFirstName());
		customerAddress.setLastname(dto.getLastName());
		customerAddress.setEmail(dto.getEmail());

		cartModel.setCustomerAddress(customerAddress);
		modelService.save(customerAddress);
	}

	@Override
	public void prefillAndSaveCustomerAddressToCart(final CustomerModel customerModel, final CartModel cartModel)
	{
		try
		{
			Transaction.current().execute(new TransactionBody()
			{
				@Override
				public Object execute() throws Exception
				{
					AddressModel customerAddress = getAddressForPrefill(customerModel);
					if (customerAddress != null)
					{
						AddressModel addressInCart = cartModel.getCustomerAddress();
                        if (addressInCart != null)
                        {
						    modelService.remove(addressInCart);
                        }

                        addressInCart = modelService.clone(customerAddress);
						addressInCart.setOwner(cartModel);
						cartModel.setCustomerAddress(addressInCart);
						modelService.saveAll(cartModel, addressInCart);
					}
					return null;
				}
			});
		}
		catch (Exception e)
		{
            LogHelper.error(LOG, e.getMessage(), e);
		}
	}

	/**
	 * Gets main address for user, if there is no main address returns last address with businessPartnerId,
	 * if its null too returns shop address.
	 * @param customerModel customer model
	 * @return Address model
	 */
	private AddressModel getAddressForPrefill(final CustomerModel customerModel)
	{
		AddressModel returnAddress = customerModel.getMainAddress();
		if (returnAddress == null)
		{
			for (final AddressModel address : customerModel.getAddresses())
			{
				if (StringUtils.isNotEmpty(address.getBusinessPartnerId()))
				{
					returnAddress = address;
				}
				else if (returnAddress == null)
				{
					returnAddress = address;
				}
			}
		}
		return returnAddress;
	}
}
