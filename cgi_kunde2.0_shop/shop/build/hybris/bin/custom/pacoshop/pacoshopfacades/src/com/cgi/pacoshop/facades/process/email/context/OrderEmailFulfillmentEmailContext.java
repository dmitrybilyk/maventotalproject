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
package com.cgi.pacoshop.facades.process.email.context;

import com.cgi.pacoshop.core.model.BonusDTO;
import com.cgi.pacoshop.facades.populators.OrderEmailFulfillmentEmailPopulator;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PacoDebitPaymentInfoModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;


/**
 * Velocity context for a order email fulfillment email.
 *
 * @module pacoshopfacades
 * @version 1.0v 22.04.2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Rui Shan <rui.shan@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class OrderEmailFulfillmentEmailContext extends OrderNotificationEmailContext
{
	private OrderData                 orderData;
	private AddressModel              customerAddress;
	private AddressModel              deliveryAddress;
	private AddressModel              invoiceAddress;
	private String                    existingCustomerId;
	private String                    milesAndMoreId;
	private Date                      studentGraduationDate;
	private PaymentModeModel          paymentMode;
	private PaymentStatus             paymentStatus;
	private String                    paymentMethod;
	private List<BonusDTO>            bonuses;
	private AddressModel              bonusRecepientAddress;
	private PacoDebitPaymentInfoModel bonusRecepientPaymentInfo;
	private String 						 debitAccountOwner;
	private String                    debitBankName;
	private String                    debitIban;
	private String                    debitBic;
	private String                    debitBankCode;
	private String                    debitAccountCode;

	private Converter<OrderModel, OrderData> orderConverter;

	@Resource
	private OrderEmailFulfillmentEmailPopulator orderEmailFulfillmentEmailPopulator;

	@Override
	public void init(final OrderProcessModel orderProcessModel, final EmailPageModel emailPageModel)
	{
		super.init(orderProcessModel, emailPageModel);
		orderData = getOrderConverter().convert(orderProcessModel.getOrder());
		getOrderEmailFulfillmentEmailPopulator().populate(orderProcessModel.getOrder(), this);
	}

	/**
	 * @return the orderConverter
	 */
	@Override
	public Converter<OrderModel, OrderData> getOrderConverter()
	{
		return orderConverter;
	}

	/**
	 * @param orderConverter
	 *           the orderConverter to set
	 */
	@Override
	public void setOrderConverter(final Converter<OrderModel, OrderData> orderConverter)
	{
		this.orderConverter = orderConverter;
	}

	/**
	 * @return the orderData
	 */
	public OrderData getOrderData()
	{
		return orderData;
	}

	/**
	 * @param orderData
	 *           the orderData to set
	 */
	public void setOrderData(final OrderData orderData)
	{
		this.orderData = orderData;
	}

	/**
	 * @return the customerAddress
	 */
	public AddressModel getCustomerAddress()
	{
		return customerAddress;
	}

	/**
	 * @param customerAddress
	 *           the customerAddress to set
	 */
	public void setCustomerAddress(final AddressModel customerAddress)
	{
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the invoiceAddress
	 */
	public AddressModel getInvoiceAddress()
	{
		return invoiceAddress;
	}

	/**
	 * @param invoiceAddress
	 *           the invoiceAddress to set
	 */
	public void setInvoiceAddress(final AddressModel invoiceAddress)
	{
		this.invoiceAddress = invoiceAddress;
	}

	/**
	 * @return the existingCustomerId
	 */
	public String getExistingCustomerId()
	{
		return existingCustomerId;
	}

	/**
	 * @param existingCustomerId
	 *           the existingCustomerId to set
	 */
	public void setExistingCustomerId(final String existingCustomerId)
	{
		this.existingCustomerId = existingCustomerId;
	}

	/**
	 * @return the milesAndMoreId
	 */
	public String getMilesAndMoreId()
	{
		return milesAndMoreId;
	}

	/**
	 * @param milesAndMoreId
	 *           the milesAndMoreId to set
	 */
	public void setMilesAndMoreId(final String milesAndMoreId)
	{
		this.milesAndMoreId = milesAndMoreId;
	}

	/**
	 * @return the studentGraduationDate
	 */
	public Date getStudentGraduationDate()
	{
		return studentGraduationDate;
	}

	/**
	 * @param studentGraduationDate
	 *           the studentGraduationDate to set
	 */
	public void setStudentGraduationDate(final Date studentGraduationDate)
	{
		this.studentGraduationDate = studentGraduationDate;
	}

	/**
	 * @return the paymentMode
	 */
	public PaymentModeModel getPaymentMode()
	{
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *           the paymentMode to set
	 */
	public void setPaymentMode(final PaymentModeModel paymentMode)
	{
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentStatus
	 */
	public PaymentStatus getPaymentStatus()
	{
		return paymentStatus;
	}

	/**
	 * @param paymentStatus
	 *           the paymentStatus to set
	 */
	public void setPaymentStatus(final PaymentStatus paymentStatus)
	{
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the orderEmailFulfillmentEmailPopulator
	 */
	public OrderEmailFulfillmentEmailPopulator getOrderEmailFulfillmentEmailPopulator()
	{
		return orderEmailFulfillmentEmailPopulator;
	}

	/**
	 * @param orderEmailFulfillmentEmailPopulator
	 *           the orderEmailFulfillmentEmailPopulator to set
	 */
	public void setOrderEmailFulfillmentEmailPopulator(
			  final OrderEmailFulfillmentEmailPopulator orderEmailFulfillmentEmailPopulator)
	{
		this.orderEmailFulfillmentEmailPopulator = orderEmailFulfillmentEmailPopulator;
	}

	/**
	 * @return the bonuses
	 */
	public List<BonusDTO> getBonuses()
	{
		return bonuses;
	}

	/**
	 * @param bonuses
	 *           the bonuses to set
	 */
	public void setBonuses(final List<BonusDTO> bonuses)
	{
		this.bonuses = bonuses;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	/**
	 * @param paymentMethod
	 *           the paymentMethod to set
	 */
	public void setPaymentMethod(final String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	/**
	 *
	 * @return еру deliveryAddress
	 */
	public AddressModel getDeliveryAddress()
	{
		return deliveryAddress;
	}

	/**
	 *
	 * @param deliveryAddress the deliveryAddress.
	 */
	public void setDeliveryAddress(final AddressModel deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	/**
	 *
	 * @return referral address
	 */
	public AddressModel getBonusRecepientAddress()
	{
		return bonusRecepientAddress;
	}

	/**
	 * @param bonusRecepientAddress set the refferal address
	 */
	public void setBonusRecepientAddress(final AddressModel bonusRecepientAddress)
	{
		this.bonusRecepientAddress = bonusRecepientAddress;
	}

	/**
	 *
	 * @return the bonus recepient payment info
	 */
	public PacoDebitPaymentInfoModel getBonusRecepientPaymentInfo()
	{
		return bonusRecepientPaymentInfo;
	}

	/**
	 *
	 * @param bonusRecepientPaymentInfo the bonus recepient paynment info
	 */
	public void setBonusRecepientPaymentInfo(final PacoDebitPaymentInfoModel bonusRecepientPaymentInfo)
	{
		this.bonusRecepientPaymentInfo = bonusRecepientPaymentInfo;
	}

	/**
	 * @param debitAccountOwner account owner name
	 */
	public void setDebitAccountOwner(final String debitAccountOwner)
	{
		this.debitAccountOwner = debitAccountOwner;
	}

	/**
	 *
	 * @return account owner name.
	 */
	public String getDebitAccountOwner()
	{
		return debitAccountOwner;
	}

	/**
	 *
	 * @return bank name.
	 */
	public String getDebitBankName()
	{
		return debitBankName;
	}

	/**
	 *
	 * @param debitBankName bank name
	 */
	public void setDebitBankName(final String debitBankName)
	{
		this.debitBankName = debitBankName;
	}

	/**
	 *
	 * @return IBAN number;
	 */
	public String getDebitIban()
	{
		return debitIban;
	}

	/**
	 *
	 * @param debitIban IBAN number;
	 */
	public void setDebitIban(final String debitIban)
	{
		this.debitIban = debitIban;
	}

	/**
	 *
	 * @return BIC number.
	 */
	public String getDebitBic()
	{
		return debitBic;
	}

	/**
	 *
	 * @param debitBic BIC number
	 */
	public void setDebitBic(final String debitBic)
	{
		this.debitBic = debitBic;
	}

	/**
	 *
	 * @return bank code.
	 */
	public String getDebitBankCode()
	{
		return debitBankCode;
	}

	/**
	 *
	 * @param debitBankCode bank code
	 */
	public void setDebitBankCode(final String debitBankCode)
	{
		this.debitBankCode = debitBankCode;
	}

	/**
	 *
	 * @return account code.
	 */
	public String getDebitAccountCode()
	{
		return debitAccountCode;
	}

	/**
	 *
	 * @param debitAccountCode account code
	 */
	public void setDebitAccountCode(final String debitAccountCode)
	{
		this.debitAccountCode = debitAccountCode;
	}
}
