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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.paymentpage;


import com.cgi.hybris.payment.core.data.CreditCardPaymentInfoData;
import com.cgi.hybris.payment.core.data.PaymentContainerData;
import com.cgi.hybris.payment.core.data.PaymentInfoData;
import com.cgi.hybris.payment.core.data.PaymentModeData;
import com.cgi.hybris.payment.core.facades.PaymentExtFacade;
import com.cgi.hybris.payment.core.facades.impl.DefaultProxyCheckoutFacade;
import com.cgi.hybris.payment.core.web.forms.BillingAddressForm;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.exceptions.dynamic.NoPaymentModesException;
import com.cgi.pacoshop.core.model.StorePaymentModeFeeModel;
import static com.cgi.pacoshop.core.util.LogHelper.error;
import de.hybris.platform.acceleratorfacades.order.impl.AcceleratorCheckoutFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.user.data.CountryData;
import de.hybris.platform.core.model.order.CartModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**

 *
 * @module cgi_kunde2.0_shop
 * @version 1.0v Mar 20, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Andrey Pilipenko <joe.doe@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class PaymentFormElementGroup extends AbstractFormElementGroup
{
    private static final Logger LOG = Logger.getLogger(PaymentFormElementGroup.class);

    private static final String MODEL_KEY_PAYMENT_CONTAINER       = "paymentContainer";
    private static final String MODEL_KEY_AVAILABLE_PAYMENT_INFOS = "availablePaymentInfos";
    private static final String MODEL_KEY_BILLING_ADDRESS_FORM    = "billingAddressForm";
    private static final String MODEL_KEY_PAYMENT_MODE_FEE_MAP    = "paymentModeFeeMap";

    private static final String BILLING_COUNTRIES = "billingCountries";
    private static final String MONTHS            = "months";
    private static final String START_YEARS       = "startYears";
    private static final String EXPIRY_YEARS      = "expiryYears";
    private static final int    MONTHS_NUMBER     = 12;
    private static final int    HALF_OF_YEAR      = 6;

    @Resource(name = "paymentExtFacade")
    private PaymentExtFacade paymentExtFacade;

    @Resource(name = "proxyCheckoutFacade")
    private DefaultProxyCheckoutFacade proxyCheckoutFacade;

    @Resource(name = "pacoshopPaymentModeComparator")
    private Comparator<PaymentModeData> paymentModeDataComparator;

    @Resource
    private PriceDataFactory priceDataFactory;

    @Resource(name = "acceleratorCheckoutFacade")
    private AcceleratorCheckoutFacade checkoutFacade;

    @Override
    public boolean isDisplayed(final CartModel cart)
    {
        return cart.getTotalPrice() > 0;
    }

    private void sortPaymentModeData(final PaymentContainerData paymentContainerData)
    {
        final List<PaymentModeData> sortedPaymentModes = new ArrayList<>(paymentContainerData.getModes());
        Collections.sort(sortedPaymentModes, paymentModeDataComparator);
        paymentContainerData.setModes(sortedPaymentModes);
    }

    @Override
    public Map<String, Object> populatePageModelData(final CartModel cartModel)
    {
        final Map<String, Object> model = super.populatePageModelData(cartModel);

        // Lists required for credit card data
        model.put(BILLING_COUNTRIES, getBillingCountries());
        model.put(MONTHS, getMonths());
        model.put(START_YEARS, getStartYears());
        model.put(EXPIRY_YEARS, getExpiryYears());

        final PaymentContainerData paymentContainerData = paymentExtFacade.getPaymentContainerData();
        paymentContainerData.setJsApiUrl(paymentExtFacade.getJsApiUrl());
        sortPaymentModeData(paymentContainerData);
        final CartData cartData = proxyCheckoutFacade.getCheckoutCart();

        if (CollectionUtils.isEmpty(paymentContainerData.getModes()))
        {
            error(LOG, "There are no available payments methods for cart with code %s", cartData.getCode());
            throw new NoPaymentModesException(cartData.getCode());
        }

        paymentContainerData.setPaymentInfo(getCleanPaymentInfoData(cartData));
        model.put(MODEL_KEY_PAYMENT_CONTAINER, paymentContainerData);
        final Collection<PaymentInfoData> availablePaymentInfos = paymentExtFacade.getAvailablePaymentInfos();
        model.put(MODEL_KEY_AVAILABLE_PAYMENT_INFOS, availablePaymentInfos);
        final BillingAddressForm billingAddressForm = new BillingAddressForm();
        model.put(MODEL_KEY_BILLING_ADDRESS_FORM, billingAddressForm);
        model.put(MODEL_KEY_PAYMENT_MODE_FEE_MAP, getPaymentFeeMap(cartModel));
        return model;
    }

    private CCPaymentInfoData getCleanPaymentInfoData(final CartData cartData)
    {
        final CCPaymentInfoData infoData = cartData.getPaymentInfo();

        if (infoData != null && infoData instanceof PaymentInfoData)
        {
            PaymentInfoData paymentInfoData = (PaymentInfoData) infoData;
            CreditCardPaymentInfoData creditCardPaymentInfoData = paymentInfoData.getCreditcard();
            if (creditCardPaymentInfoData != null)
            {
                creditCardPaymentInfoData.setExpMonth(null);
				creditCardPaymentInfoData.setExpYear(null);
				creditCardPaymentInfoData.setNo(null);
				paymentInfoData.setPaymentMethodCode(null);
			}
			return paymentInfoData;
		}

		return infoData;
	}

	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		return cart.getPaymentInfo() != null;
	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.PAYMENT_PAGE_FORM_ELEMENT_GROUP);
	}

	private Map<String, PriceData> getPaymentFeeMap(final CartModel cartModel)
	{
		Map <String, PriceData> result = new HashMap<>();
		for (final StorePaymentModeFeeModel storePaymentModeFeeModel : cartModel.getStore().getPaymentModeFees())
		{
			PriceData priceData = priceDataFactory.create(
					  PriceDataType.BUY, new BigDecimal(storePaymentModeFeeModel.getAmount()), storePaymentModeFeeModel.getCurrency());
			result.put(storePaymentModeFeeModel.getPaymentMode().getCode(), priceData);
		}
		return result;
	}

    // FIXME: Remove after moving that logic to AbstractFormElementGroup
    /**
     * Create billing countries.
     *
     * @return  - billing countries list
     */

    private Collection<CountryData> getBillingCountries()
    {
        return checkoutFacade.getBillingCountries();
    }

    /**
     * Create months list.
     *
     * @return  - months map
     */
    private Map<String, String> getMonths()
    {
        final Map<String, String> months = new LinkedHashMap<>();
        final String nullNumber = "0";
        for (Integer currentMonth = 1; currentMonth <= MONTHS_NUMBER; currentMonth++)
        {
            final StringBuilder monthOptionValue = new StringBuilder();
            final int ten = 10;
            if (currentMonth < ten)
            {
                monthOptionValue.append(nullNumber);
            }
            monthOptionValue.append(currentMonth);
            months.put(currentMonth.toString(), monthOptionValue.toString());
        }

        return months;
    }

    /**
     * Create start years list.
     *
     * @return  - years list
     */
    private Map<String, String> getStartYears()
    {
        final Map<String, String> startYears = new LinkedHashMap<>();
        final Calendar calender = new GregorianCalendar();
        for (int i = calender.get(Calendar.YEAR); i > (calender.get(Calendar.YEAR) - HALF_OF_YEAR); i--)
        {
            startYears.put(String.valueOf(i), String.valueOf(i));
        }

        return startYears;
    }


    /**
     * Create expire years list.
     *
     * @return  - years list
     */
    private Map<String, String> getExpiryYears()
    {
        final Map<String, String> expiryYears = new LinkedHashMap<>();
        final Calendar calender = new GregorianCalendar();

        final int elevenNumber = 11;
        for (int i = calender.get(Calendar.YEAR); i < (calender.get(Calendar.YEAR) + elevenNumber); i++)
        {
            expiryYears.put(String.valueOf(i), String.valueOf(i));
        }

        return expiryYears;
    }
}
