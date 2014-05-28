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
package com.learn.formgroups.formdtoimpl;

/**
 * Implementation of FormDTO
 *
 *
 * @module build
 * @version 1.0v Jan 21, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class PaymentInfoFormDTO extends CheckoutFormDTO
{
	private String paymentMethod;
	private String firstName;
	private String lastName;
	private String cartNumber;
	private String validThru;
	private String dummyField2;



	/**
	 * Cart Number getter.
	 * @return cart number.
	 */
	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	/**
	 * First name setter.
	 * @param paymentMethod - new first name.
	 */
	public void setPaymentMethod(final String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Cart Number getter.
	 * @return cart number.
	 */
	public String getValidThru()
	{
		return validThru;
	}

	/**
	 * First name setter.
	 * @param validThru - new first name.
	 */
	public void setValidThru(final String validThru)
	{
		this.validThru = validThru;
	}

	/**
	 * Cart Number getter.
	 * @return cart number.
	 */
	public String getCartNumber()
	{
		return cartNumber;
	}

	/**
	 * Cart number setter.
	 * @param cartNumber - new cart number.
	 */
	public void setCartNumber(final String cartNumber)
	{
		this.cartNumber = cartNumber;
	}

	/**
	 * First name getter.
	 * @return first name.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * First name setter.
	 * @param firstName - new first name.
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Last name getter.
	 * @return - last name.
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Last Name setter.
	 * @param lastName - new last name.
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * DummyField2 getter.
	 * @return DummyField2 dummy field 2
	 */
	public String getDummyField2()
	{
		return dummyField2;
	}

	/**
	 * DummyField2 setter.
	 * @param dummyField2 - new DummyField2.
	 */
	public void setDummyField2(final String dummyField2)
	{
		this.dummyField2 = dummyField2;
	}


}
