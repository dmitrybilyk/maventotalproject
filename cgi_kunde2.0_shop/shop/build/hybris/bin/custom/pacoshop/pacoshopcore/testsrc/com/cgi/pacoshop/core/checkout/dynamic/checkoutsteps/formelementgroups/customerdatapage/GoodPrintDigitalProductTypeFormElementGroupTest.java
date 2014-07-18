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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.customerdatapage;

import com.cgi.pacoshop.core.checkout.AbstractDynamicCheckoutFrameworkMockFactory;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl.DefaultCheckoutStepService;
import com.cgi.pacoshop.core.checkout.dynamic.impl.CheckoutFormDTO;
import com.cgi.pacoshop.core.model.PhoneNumberModel;
import com.cgi.pacoshop.core.service.FormValidationService;
import com.cgi.pacoshop.core.service.impl.FormValidationServiceImpl;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.subscriptionservices.model.SubscriptionProductModel;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Check validation for subscription products. Only those fields set to be mandatory for a product should be validated.
 * (go to hmc--> subscription product --> administration)
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 10, 2014
 * @module pacoshopcore
 * @link http ://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see ://wiki.hybris.com/
 */
@UnitTest
public class GoodPrintDigitalProductTypeFormElementGroupTest
{
	@Mock
	private static CheckoutStepService CHECKOUT_STEP_SERVICE;
	@Mock
	private static AbstractFormElementGroup FORM_ELEMENT_GROUP;

	private static Set<String> PRODUCT_SPECIFIC_MANDATORY_FIELDNAMES = new HashSet<String>();
	private static Set<String> ADDRESS_MANDATORY_FIELDS = new HashSet<String>();

	@Mock
	private BindingResult bindingResult;
	@Mock
	private SubscriptionProductModel subscriptionProductModel;
	@Mock
	private CartModel cartModel;
	@Mock
	private FormValidationService formValidationService;
	@Mock
	private CheckoutFormDTO mockDTO;

	/**
	 * Setup test data.
	 *
	 * @throws Exception if occurs exception
	 */
	@Before
	public void setUp() throws Exception
	{
		bindingResult = new MapBindingResult(getBindingProperties(), "test");
	}

	/**
	 * Sets up mocks.
	 */
	@BeforeClass
	public static void setUpMocks()
	{
		FORM_ELEMENT_GROUP = new GoodPrintDigitalProductTypeFormElementGroup();
		ADDRESS_MANDATORY_FIELDS.addAll(getAddressMandatoryFieldNames());
		PRODUCT_SPECIFIC_MANDATORY_FIELDNAMES.addAll(getPRODUCTSPECIFICMANDATORYFIELDNAMES());
		CHECKOUT_STEP_SERVICE = mock(DefaultCheckoutStepService.class);
	}

	private static Map<?, ?> getBindingProperties()
	{
		final HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
		stringStringHashMap.put("phone", "phone is not valid");
		stringStringHashMap.put("mobile", "mobile is not valid");
		return stringStringHashMap;
	}

	private static FormValidationService setUpNoProductSpecificMandatoryFieldsFormValidationService()
	{
		FormValidationServiceImpl validationService = new FormValidationServiceImpl();
		when(CHECKOUT_STEP_SERVICE.getStepMandatoryFieldNames(any(CheckoutStep.class))).thenReturn(
				createMandatoryFieldsMap(false));

		validationService.setCheckoutStepService(CHECKOUT_STEP_SERVICE);

		return validationService;
	}

	private static FormValidationService setUpProductSpecificMandatoryFieldsFormValidationService()
	{
		FormValidationServiceImpl validationService = new FormValidationServiceImpl();
		when(CHECKOUT_STEP_SERVICE.getStepMandatoryFieldNames(any(CheckoutStep.class))).thenReturn(
				createMandatoryFieldsMap(true));

		validationService.setCheckoutStepService(CHECKOUT_STEP_SERVICE);
		return validationService;
	}


	private static List<String> getAddressMandatoryFieldNames()
	{
		return Arrays.asList(FormElementGroupConstants.SALUTATION,
				FormElementGroupConstants.FIRST_NAME,
				FormElementGroupConstants.LAST_NAME,
				FormElementGroupConstants.STREET,
				FormElementGroupConstants.HOUSE_NUMBER,
				FormElementGroupConstants.CITY_ORT,
				FormElementGroupConstants.COUNTRY,
				FormElementGroupConstants.EMAIL);
	}

	private static List<String> getPRODUCTSPECIFICMANDATORYFIELDNAMES()
	{
		return Arrays.asList(FormElementGroupConstants.HOME_PHONE_NUMBER,
									FormElementGroupConstants.BUSINESS_PHONE_NUMBER,
									FormElementGroupConstants.MOBILE_PHONE_NUMBER);
	}

	private static Map<String, Boolean> createMandatoryFieldsMap(final boolean addProductSpecificFields)
	{
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		for (String s : getAddressMandatoryFieldNames())
		{
			resultMap.put(s, true);
		}
		if (addProductSpecificFields)
		{
			for (String s : getPRODUCTSPECIFICMANDATORYFIELDNAMES())
			{
				resultMap.put(s, true);
			}
		}
		return resultMap;
	}

	private static CheckoutFormDTO createCheckoutDTO(final PhoneNumberModel mobile,
																	 final PhoneNumberModel homePhone,
																	 final PhoneNumberModel businessPhone,
													 final String email)
	{
		final CheckoutFormDTO form = mock(CheckoutFormDTO.class);
		final String homePhonePrefix = homePhone.getPrefix(),
				  		 homePhoneExtension = homePhone.getExtension(),
				  		 homePhoneNumber = homePhone.getNumber(),
						 businessPhonePrefix = businessPhone.getPrefix(),
				  		 businessPhoneExtension = businessPhone.getExtension(),
				  		 businessPhoneNumber = businessPhone.getNumber(),
						 mobilePhonePrefix = mobile.getPrefix(),
				  		 mobilePhoneNumber = mobile.getNumber();

		when(form.getZip()).thenReturn("333333");
		when(form.getCountry()).thenReturn("Ukraine");
		when(form.getFirstName()).thenReturn("first name");
		when(form.getTitle()).thenReturn("mr");
		when(form.getSalutation()).thenReturn("mr");
		when(form.getLastName()).thenReturn("last name");
		when(form.getStreet()).thenReturn("street");
		when(form.getHouseNumber()).thenReturn("L203");
		when(form.getCity()).thenReturn("city");
		when(form.getBirthDateDay()).thenReturn("");
		when(form.getBirthDateMonth()).thenReturn("");
		when(form.getBirthDateYear()).thenReturn("");
		when(form.getAdditionalStreet()).thenReturn("test");

		when(form.getPhonePrefixHome()).thenReturn(homePhonePrefix);
		when(form.getPhoneExtensionHome()).thenReturn(homePhoneExtension);
		when(form.getPhoneNumberHome()).thenReturn(homePhoneNumber);

		when(form.getPhonePrefixBusiness()).thenReturn(businessPhonePrefix);
		when(form.getPhoneExtensionBusiness()).thenReturn(businessPhoneExtension);
		when(form.getPhoneNumberBusiness()).thenReturn(businessPhoneNumber);

		when(form.getMobilePrefix()).thenReturn(mobilePhonePrefix);
		when(form.getMobileNumber()).thenReturn(mobilePhoneNumber);

		when(form.getEmail()).thenReturn(email);

		return form;
	}

	private static PhoneNumberModel createPhoneNumber(final String prefix, final String extension, final String number)
	{
		final PhoneNumberModel result = mock(PhoneNumberModel.class);

		when(result.getPrefix()).thenReturn(prefix);
		when(result.getExtension()).thenReturn(extension);
		when(result.getNumber()).thenReturn(number);

		return result;
	}

	/**
	 * Only address related fields should be mandatory.
	 */
	@Test
	public void testnoProductSpecificMandatoryFields()
	{
		subscriptionProductModel = AbstractDynamicCheckoutFrameworkMockFactory.createMockDigitalABOWithNoMandatoryFields();
		cartModel = AbstractDynamicCheckoutFrameworkMockFactory.createMockCartWithSubscriptionProduct(subscriptionProductModel);
		final Set<String> mandatoryFieldNames = FORM_ELEMENT_GROUP.getMandatoryFieldNames(cartModel);

		assertFalse(mandatoryFieldNames.containsAll(PRODUCT_SPECIFIC_MANDATORY_FIELDNAMES));

		assertTrue(mandatoryFieldNames.containsAll(ADDRESS_MANDATORY_FIELDS));

	}

	/**
	 * should return address mandatory fields and product specific mandatory fields - phone and email.
	 */
	@Test
	public void testProductSpecificMandatoryFields()
	{
		subscriptionProductModel = AbstractDynamicCheckoutFrameworkMockFactory.createMockPrintABOWithMandatoryFields();
		cartModel = AbstractDynamicCheckoutFrameworkMockFactory.createMockCartWithSubscriptionProduct(subscriptionProductModel);
		final Set<String> mandatoryFieldNames = FORM_ELEMENT_GROUP.getMandatoryFieldNames(cartModel);

		assertTrue(mandatoryFieldNames.containsAll(PRODUCT_SPECIFIC_MANDATORY_FIELDNAMES));
		assertTrue(mandatoryFieldNames.containsAll(ADDRESS_MANDATORY_FIELDS));
	}

	/**
	 * Test validation with product specific mandatory fields. phone and mobile phone are empty and SHOULD be validated.
	 * <p/> testcase 2: <p/> mobile phone is not valid and is  mandatory in a product. expected: mobile and phone SHOULD
	 * be validated <p/> test data - in mockDTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidationWithProductSpecificMandatoryFieldstestCase2() throws Exception
	{
		formValidationService = setUpProductSpecificMandatoryFieldsFormValidationService();
		PhoneNumberModel homePhone = createPhoneNumber("not valid", " phone", " number");
		PhoneNumberModel businessPhone = createPhoneNumber("not valid", " business", " number");
		PhoneNumberModel mobilePhone = createPhoneNumber("not valid", " mobile", " number");

		mockDTO = createCheckoutDTO(mobilePhone, homePhone, businessPhone, "email@gmail.com");
		assertFalse(formValidationService.validateCustomerDataGoodPrintDigitalAbo(mockDTO, bindingResult));
		assertTrue(bindingResult.getErrorCount() == 3);
	}

	/**
	 * Test validation with product specific mandatory fields. phone and mobile phone are empty and SHOULD be validated.
	 * <p/> testcase 3: <p/> phone is not valid and is  mandatory in a product. expected: mobile and phone SHOULD  be
	 * validated <p/> test data - in mockDTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidationWithProductSpecificMandatoryFieldstestCase3() throws Exception
	{
		formValidationService = setUpProductSpecificMandatoryFieldsFormValidationService();
		PhoneNumberModel homePhone = createPhoneNumber("not valid", " phone", " number");
		PhoneNumberModel businessPhone = createPhoneNumber("not valid", " business", " number");
		PhoneNumberModel mobilePhone = createPhoneNumber("+49", null, "33333(3-3)33");

		mockDTO = createCheckoutDTO(mobilePhone, homePhone, businessPhone, "email@gmail.com");
		assertFalse(formValidationService.validateCustomerDataGoodPrintDigitalAbo(mockDTO, bindingResult));
		assertTrue(bindingResult.getErrorCount() == 2);
	}

	/**
	 * Test validation with product specific mandatory fields. phone and mobile phone are empty and SHOULD be validated.
	 * <p/> testcase 4: <p/> email is always mandatory for subscription products expected: email should be validated
	 * <p/> test data - in mockDTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidationWithProductSpecificMandatoryFieldstestCase4() throws Exception
	{
		formValidationService = setUpProductSpecificMandatoryFieldsFormValidationService();
		PhoneNumberModel homePhone = createPhoneNumber("+58", "0(2-5)8", "5522-5588-1144");
		PhoneNumberModel businessPhone = createPhoneNumber("+589", "0(2-85)", "5522-1144");
		PhoneNumberModel mobilePhone = createPhoneNumber("+49", null, "33333(3-3)33");

		mockDTO = createCheckoutDTO(mobilePhone, homePhone, businessPhone, "not valid email");
		assertFalse(formValidationService.validateCustomerDataGoodPrintDigitalAbo(mockDTO, bindingResult));
		assertTrue(bindingResult.getErrorCount() == 1);
	}

	/**
	 * Test validation with product specific mandatory fields. phone and mobile phone are empty and SHOULD be validated.
	 * <p/> testcase 4: <p/> email is always mandatory for subscription products expected: email should be validated
	 * <p/> test data - in mockDTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidationWithProductSpecificMandatoryFieldstestCase5() throws Exception
	{
		formValidationService = setUpProductSpecificMandatoryFieldsFormValidationService();
		PhoneNumberModel homePhone = createPhoneNumber("+58", "0(2-5)8", "5522-5588-1144");
		PhoneNumberModel businessPhone = createPhoneNumber("not valid", " business", " number");
		PhoneNumberModel mobilePhone = createPhoneNumber("+49", null, "33333(3-3)33");

		mockDTO = createCheckoutDTO(mobilePhone, homePhone, businessPhone, "not valid email");
		assertFalse(formValidationService.validateCustomerDataGoodPrintDigitalAbo(mockDTO, bindingResult));
		assertTrue(bindingResult.getErrorCount() == 2);
	}

	/**
	 * Test validation with product specific mandatory fields. phone and mobile phone are empty and SHOULD be validated.
	 * <p/> testcase 4: <p/> email is always mandatory for subscription products expected: email should be validated
	 * <p/> test data - in mockDTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidationWithProductSpecificMandatoryFieldstestCase6() throws Exception
	{
		formValidationService = setUpProductSpecificMandatoryFieldsFormValidationService();
		PhoneNumberModel homePhone = createPhoneNumber("not valid", " phone", " number");
		PhoneNumberModel businessPhone = createPhoneNumber("+589", "0(2-85)", "5522-1144");
		PhoneNumberModel mobilePhone = createPhoneNumber("+49", null, "33333(3-3)33");

		mockDTO = createCheckoutDTO(mobilePhone, homePhone, businessPhone, "not valid email");
		assertFalse(formValidationService.validateCustomerDataGoodPrintDigitalAbo(mockDTO, bindingResult));
		assertTrue(bindingResult.getErrorCount() == 2);
	}

	/**
	 * Test validation with product specific mandatory fields. phone and mobile phone are empty and SHOULD be validated.
	 * <p/> testcase 4: <p/> email is always mandatory for subscription products expected: email should be validated
	 * <p/> test data - in mockDTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testValidationWithProductSpecificMandatoryFieldstestCase7() throws Exception
	{
		formValidationService = setUpProductSpecificMandatoryFieldsFormValidationService();
		PhoneNumberModel homePhone = createPhoneNumber("+58", "0(2-5)8", "5522-5588-1144");
		PhoneNumberModel businessPhone = createPhoneNumber("+589", "0(2-85)", "5522-1144");
		PhoneNumberModel mobilePhone = createPhoneNumber("+49", null, "33333(3-3)33");

		mockDTO = createCheckoutDTO(mobilePhone, homePhone, businessPhone, "email@gmail.com");
		assertTrue(formValidationService.validateCustomerDataGoodPrintDigitalAbo(mockDTO, bindingResult));
		assertTrue(bindingResult.getErrorCount() == 0);
	}
}
