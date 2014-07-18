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
package com.cgi.pacoshop.facades.populators;

import com.cgi.pacoshop.core.model.ProductDTO;
import com.cgi.pacoshop.core.strategies.CompareAddressesStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.DebitPaymentInfoModel;
import de.hybris.platform.core.model.user.AddressModel;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import com.cgi.pacoshop.core.model.BonusDTO;
import com.cgi.pacoshop.core.model.BonusModel;
import com.cgi.pacoshop.core.model.CashBonusModel;
import com.cgi.pacoshop.core.model.MilesAndMoreBonusModel;
import com.cgi.pacoshop.core.model.Price;
import com.cgi.pacoshop.core.model.ProductBonusModel;
import com.cgi.pacoshop.facades.process.email.context.OrderEmailFulfillmentEmailContext;
import org.apache.commons.lang.StringUtils;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;


/**
 * Populator for OrderEmailFulfillmentEmailContext.
 *
 * @module pacoshopfacades
 * @version 1.0v 24.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OrderEmailFulfillmentEmailPopulator implements Populator<OrderModel, OrderEmailFulfillmentEmailContext>
{
	private static final String EMPTY_STRING = "";

	@Resource
	private CompareAddressesStrategy compareAddressesStrategy;

	@Override
	public void populate(final OrderModel order, final OrderEmailFulfillmentEmailContext context) throws ConversionException
	{
		validateParameterNotNull(order, "Parameter order must not be null");
		validateParameterNotNull(context, "Parameter context must not be null");

		context.setCustomerAddress(order.getCustomerAddress());
		context.setDeliveryAddress(order.getDeliveryAddress());
		context.setInvoiceAddress(getPaymentAddress(order));
		context.setExistingCustomerId(order.getExistingSubscriptionId());
		context.setMilesAndMoreId(getMilesAndMoreNumber(order));
		context.setStudentGraduationDate(order.getStudentGradutationDate());
		context.setPaymentMode(order.getPaymentMode());
		context.setPaymentStatus(order.getPaymentStatus());

		// get referral info
		context.setBonusRecepientAddress(order.getBonusRecipientAddress());
		context.setBonusRecepientPaymentInfo(order.getBonusRecipientPaymentInfo());

		final PaymentInfoModel paymentInfo = order.getPaymentInfo();
		if (paymentInfo != null && paymentInfo.getPaymentMethod() != null)
		{
			context.setPaymentMethod(paymentInfo.getPaymentMethod().getCode());

			if (paymentInfo instanceof DebitPaymentInfoModel)
			{
				populateDebitInfo(paymentInfo, context);
			}
		}
		populateProducts(order, context);
		populateBonuses(order, context);

	}

	private String getMilesAndMoreNumber(final OrderModel order)
	{
		AddressModel address1 = order.getBonusRecipientAddress(),
				  		 address2 = order.getCustomerAddress();

		String mamNumber1 = (address1 != null) ? address1.getMilesAndMoreNumber() : EMPTY_STRING,
				  mamNumber2 = (address2 != null) ? address2.getMilesAndMoreNumber() : EMPTY_STRING;

		return StringUtils.isNotEmpty(mamNumber1) ? mamNumber1
				  : StringUtils.isNotEmpty(mamNumber2) ? mamNumber2
				  : EMPTY_STRING;
	}

	private AddressModel getPaymentAddress(final OrderModel order)
	{
		AddressModel customerAddress = order.getCustomerAddress(),
				  paymentAddress = order.getPaymentAddress();

		return compareAddressesStrategy.compareAddresses(customerAddress, paymentAddress) ? null : paymentAddress;
	}

	/**
	 * Populate products information.
	 *
	 * @param order
	 *           the {@link OrderModel}
	 * @param context
	 *           the {@link OrderEmailFulfillmentEmailContext}
	 */
	private void populateProducts(final OrderModel order, final OrderEmailFulfillmentEmailContext context)
	{
		List<AbstractOrderEntryModel> orderEntryModelList = order.getEntries();
		List<ProductDTO> productDTOs = isNotEmpty(orderEntryModelList) ? new ArrayList<ProductDTO>() : Collections.EMPTY_LIST;
		for (AbstractOrderEntryModel orderEntryModel : orderEntryModelList)
		{
			ProductModel productModel = orderEntryModel.getProduct();
			ProductDTO productDTO = new ProductDTO();
			populateProducts(productDTO, productModel);
			productDTOs.add(productDTO);
		}
		context.setProductDTOs(productDTOs);
	}

	private void populateProducts(final ProductDTO productDTO, final ProductModel productModel)
	{
		productDTO.setName(productModel.getName());
		productDTO.setProductId(productModel.getCode());
		productDTO.setExternalProductId(productModel.getExternalProductId());
		productDTO.setOfferOrigin(productModel.getOfferOrigin());
	}

	/**
	 * Populate bonuses information.
	 *
	 * @param order
	 *           the {@link OrderModel}
	 * @param context
	 *           the {@link OrderEmailFulfillmentEmailContext}
	 */
	private void populateBonuses(final OrderModel order, final OrderEmailFulfillmentEmailContext context)
	{
		final List<BonusDTO> bonuses = new ArrayList<>();
		final List<AbstractOrderEntryModel> entries = order.getEntries();
		if (isNotEmpty(entries))
		{
			for (final AbstractOrderEntryModel entry : entries)
			{
				final ProductModel product = entry.getProduct();
				if (product instanceof SubscriptionProductModel)
				{
					final List<BonusModel> bonusModels = ((SubscriptionProductModel) product).getBonuses();
					if (isNotEmpty(bonusModels))
					{
						for (final BonusModel bonus : bonusModels)
						{
							final BonusDTO bonusDTO = new BonusDTO();
							bonusDTO.setBonusId(bonus.getId());
							bonusDTO.setParentProductName(product.getName());
							if (bonus instanceof CashBonusModel)
							{
								final Price bonusAmount = new Price();
								bonusAmount.setAmount(((CashBonusModel) bonus).getBonusAmount());
								bonusAmount.setCurrency(((CashBonusModel) bonus).getCurrency().getIsocode());
								bonusDTO.setBonusAmount(bonusAmount);
							}
							else if (bonus instanceof MilesAndMoreBonusModel)
							{
								bonusDTO.setBonusMiles(((MilesAndMoreBonusModel) bonus).getMiles());
							}
							else if (bonus instanceof ProductBonusModel)
							{
								bonusDTO.setBonusProduct(((ProductBonusModel) bonus).getProductDescription());
								final PriceRowModel price = ((ProductBonusModel) bonus).getAdditionalPayment();
								if (price != null)
								{
									final Price bonusExtraAmount = new Price();
									bonusExtraAmount.setAmount(price.getPrice());
									bonusExtraAmount.setCurrency(price.getCurrency().getIsocode());
									bonusDTO.setBonusProductExtraPayment(bonusExtraAmount);
								}
							}
							bonuses.add(bonusDTO);
						}
					}
				}
			}
		}
		context.setBonuses(bonuses);
	}

	private void populateDebitInfo(final PaymentInfoModel paymentInfo, final OrderEmailFulfillmentEmailContext context)
	{
		DebitPaymentInfoModel debitPaymentInfoModel = (DebitPaymentInfoModel) paymentInfo;
		context.setDebitAccountOwner(debitPaymentInfoModel.getBaOwner());
		context.setDebitBankName(debitPaymentInfoModel.getBank());
		context.setDebitAccountCode(debitPaymentInfoModel.getAccountNumber());
		context.setDebitBankCode(debitPaymentInfoModel.getBankIDNumber());
		context.setDebitIban(debitPaymentInfoModel.getIban());
		context.setDebitBic(debitPaymentInfoModel.getBic());
	}
}
