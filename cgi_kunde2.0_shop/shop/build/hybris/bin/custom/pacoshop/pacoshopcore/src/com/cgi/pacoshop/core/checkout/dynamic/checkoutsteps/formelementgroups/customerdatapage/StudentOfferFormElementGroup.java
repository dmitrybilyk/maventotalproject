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
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.service.PacoshopCartService;
import com.cgi.pacoshop.core.util.FormElementGroupUtil;
import com.google.common.collect.Sets;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

/**
 * Form element group class for the Student Offer specific fields.
 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v dec 23, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <andrey.pilipenko@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 *
 */
public class StudentOfferFormElementGroup extends AbstractFormElementGroup
{
	private static final Logger LOG = Logger.getLogger(StudentOfferFormElementGroup.class);

	@Resource
	private PacoshopCartService pacoshopCartService;

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
		final String printAbo = getConfigurationProperty(FormElementGroupConstants.PRINT_ABO);
		final String digitalAbo = getConfigurationProperty(FormElementGroupConstants.DIGITAL_ABO);

		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if ((productModel.getProductType().getCode().equals(printAbo)
					  || productModel.getProductType().getCode().equals(digitalAbo))
					  && productModel instanceof SubscriptionProductModel)
			{
				SubscriptionProductModel subscriptionProduct = (SubscriptionProductModel) productModel;
				if (subscriptionProduct.getStudentOffer())
				{
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public Set<String> getMandatoryFieldNames(final CartModel cart)
	{
		return Sets.newHashSet(
				  getConfigurationProperty(FormElementGroupConstants.EMAIL),
				  getConfigurationProperty(FormElementGroupConstants.STUDENT_GRADUATION_DAY),
				  getConfigurationProperty(FormElementGroupConstants.STUDENT_GRADUATION_MONTH),
				  getConfigurationProperty(FormElementGroupConstants.STUDENT_GRADUATION_YEAR),
				  getConfigurationProperty(FormElementGroupConstants.SALUTATION),
				  getConfigurationProperty(FormElementGroupConstants.FIRST_NAME),
				  getConfigurationProperty(FormElementGroupConstants.LAST_NAME));
	}


	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{
		if (dto instanceof CheckoutFormDTO)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			final Date graduationDate = cart.getStudentGradutationDate();
			if (graduationDate != null)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(graduationDate);
				checkoutFormDTO.setGraduationDay(String.format(FormElementGroupConstants.DAY_DATE_FORMAT,
																			 calendar.get(Calendar.DAY_OF_MONTH)));
				checkoutFormDTO.setGraduationMonth(String.format(FormElementGroupConstants.MONTH_DATE_FORMAT,
																				 calendar.get(Calendar.MONTH) + 1));
				checkoutFormDTO.setGraduationYear(String.format(FormElementGroupConstants.YEAR_DATE_FORMAT,
																				calendar.get(Calendar.YEAR)));
			}
		}
	}

	@Override
	public void prefillCartFromCustomerProfile(final CartModel cart, final CustomerModel customerModel)
	{
		Date graduationDate = cart.getStudentGradutationDate();
		if (graduationDate == null)
		{
			graduationDate = customerModel.getStudentGraduationDate();
			if (graduationDate != null)
			{
				cart.setStudentGradutationDate(graduationDate);
				getModelService().save(cart);
			}
		}
	}

	@Override
	public boolean isPrefilled(final CartModel cartModel)
	{
		return cartModel.getStudentGradutationDate() != null;
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.STUDENT_OFFER_FORM_ELEMENT_GROUP_NAME);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cart)
	{
		if (dto instanceof CheckoutFormDTO)
		{
			CheckoutFormDTO checkoutFormDTO = (CheckoutFormDTO) dto;
			String day = checkoutFormDTO.getGraduationDay();
			String month = checkoutFormDTO.getGraduationMonth();
			String year = checkoutFormDTO.getGraduationYear();
			Date graduationDate = null;
			try
			{
				graduationDate = FormElementGroupUtil.parseDate(day, month, year);
			}
			catch (ParseException e)
			{
				LOG.warn("Can't save form to cart because the graduationDate field couldn't be parsed to Date object.");
			}
			pacoshopCartService.saveStudentGraduationDateToCustomerProfileAndCart(graduationDate, cart);
		}
		else
		{
			LOG.warn("Can't save form to cart because the FormDTO object is not a CheckoutFormDTO class instance.");
		}
	}

	@Override
	public boolean validate(final FormDTO dto, final BindingResult bindingResult)
	{
		if (dto instanceof CheckoutFormDTO)
		{
			return getValidationService().validateStudentGraduationDate((CheckoutFormDTO) dto, bindingResult);
		}
		else
		{
			LOG.warn("Can't save form to cart because the FormDTO object is not a CheckoutFormDTO class instance.");
			return false;
		}
	}
}
