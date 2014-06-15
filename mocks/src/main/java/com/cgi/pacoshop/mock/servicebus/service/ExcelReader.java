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
package com.cgi.pacoshop.mock.servicebus.service;

import com.cgi.pacoshop.mock.servicebus.model.Price;
import com.cgi.pacoshop.mock.servicebus.model.SingleProduct;
import com.cgi.pacoshop.mock.servicebus.model.SubscriptionProduct;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Excel reader. Class parse excel sheet with test data and return it as collection of single product entity
 * 
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * 
 * 
 */
public class ExcelReader
{
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    private static final int COL_1 = 0;
    private static final int COL_2 = 1;
    private static final int COL_3 = 2;
    private static final int COL_4 = 3;
    private static final int COL_5 = 4;
    private static final int COL_6 = 5;
    private static final int COL_7 = 6;
    private static final int COL_8 = 7;
    private static final int COL_9 = 8;
    private static final int COL_10 = 9;
    private static final int COL_11 = 10;
    private static final int COL_12 = 11;
    private static final int COL_13 = 12;
    private static final int COL_14 = 13;
    private static final int COL_15 = 14;
    private static final int COL_16 = 15;
    private static final int COL_17 = 16;
    private static final int COL_18 = 17;
    private static final int COL_19 = 18;
    private static final int COL_20 = 19;
    private static final int COL_21 = 20;
    private static final int COL_22 = 21;
    private static final int COL_23 = 22;
    private static final int COL_24 = 23;
    private static final int COL_25 = 24;
    private static final int COL_26 = 25;
    private static final int COL_27 = 26;
    private static final int COL_28 = 27;
    private static final int COL_29 = 28;
    private static final int COL_30 = 29;
    private static final int COL_31 = 30;
    private static final int COL_32 = 31;
    private static final int COL_33 = 32;
    private static final int COL_34 = 33;
    private static final int COL_35 = 34;
    private static final int COL_36 = 35;
    private static final int COL_37 = 36;
    private static final int COL_38 = 37;
    private static final int COL_39 = 38;
    private static final int COL_40 = 39;
    private static final int COL_41 = 40;
    private static final int COL_42 = 41;
    private static final int COL_43 = 42;
    private static final int COL_44 = 43;
    private static final int COL_45 = 44;
    private static final int COL_46 = 45;
    private static final int COL_47 = 46;
    private static final int COL_48 = 47;
    private static final int COL_49 = 48; //delivery counties
    private static final int COL_50 = 49;
    private static final int COL_51 = 50;
    private static final int COL_52 = 51; // price
    private static final int COL_53 = 52; // currency
    private static final String DELIM = ",";

    private static Logger LOG = LoggerFactory.getLogger(ExcelReader.class);
    private int sheetNumber = 0;
    private String singleProductFileName;
    private String periodicProductFileName;

    /**
     * Default constructor.
     */
    public ExcelReader()
    {
        super();
    }

    /**
     * Setter singleProductFileName.
     * 
     * @param singleProductFileNameParam
     *            singleProductFileNameParam
     */
    public void setSingleProductFileName(final String singleProductFileNameParam)
    {
        this.singleProductFileName = singleProductFileNameParam;
    }

    /**
     * Setter sheetNumber.
     * 
     * @param sheetNumberParam
     *            sheetNumberParam
     */
    public void setSheetNumber(final int sheetNumberParam)
    {
        this.sheetNumber = sheetNumberParam;
    }

    /**
     * Getter periodicProductFileName.
     * 
     * @return periodicProductFileName
     */
    public String getPeriodicProductFileName()
    {
        return periodicProductFileName;
    }

    /**
     * Setter periodicProductFileName.
     * 
     * @param periodicProductFileNameParam
     *            periodicProductFileNameParam
     */
    public void setPeriodicProductFileName(final String periodicProductFileNameParam)
    {
        this.periodicProductFileName = periodicProductFileNameParam;
    }

    /**
     * Get single product.
     * 
     * @return list of single products
     */
    public List<SingleProduct> getSingleProduct()
    {
        LOG.debug("getSingleProduct() singleProductFileName=" + singleProductFileName);

        List<SingleProduct> listSingleProduct = new ArrayList<>();

        Workbook workbook = null;
        Sheet sheet;
        final Path path = Paths.get(singleProductFileName);

        try
        {
            workbook = Workbook.getWorkbook(new File(String.valueOf(path)));
            sheet = workbook.getSheet(sheetNumber);
            listSingleProduct = readSheet(sheet);
        }
        catch (final IOException e)
        {
            LOG.error("Cannot find or open " + path.toAbsolutePath(), e);
        }
        catch (final BiffException e)
        {
            LOG.error("Cannot get workbook from file " + path.toAbsolutePath(), e);
        }
        catch (final ParseException e)
        {
            LOG.error("Probably date field in wrong format, they should be in 2008-02-01T09:00:22+05 (ISO 8601 date format) ", e);
        }
        finally
        {
            if (workbook != null)
            {
                workbook.close();
            }
        }

        return listSingleProduct;
    }

    /**
     * Get list of subscription products.
     * 
     * @return list of subscription products
     */
    public List<SubscriptionProduct> getSubscriptionProduct()
    {
        LOG.debug("getSubscriptionProduct() periodicProductFileName=" + periodicProductFileName);

        List<SubscriptionProduct> periodicProductList = new ArrayList<>();

        Workbook workbook = null;
        Sheet sheet;
        final Path path = Paths.get(periodicProductFileName);

        try
        {
            workbook = Workbook.getWorkbook(new File(String.valueOf(path)));
            sheet = workbook.getSheet(sheetNumber);
            periodicProductList = readSubscriptionProductSheet(sheet);
        }
        catch (final IOException e)
        {
            LOG.error("Cannot find or open " + path.toAbsolutePath(), e);
        }
        catch (final BiffException e)
        {
            LOG.error("Cannot get workbook from file " + path.toAbsolutePath(), e);
        }
        catch (final ParseException e)
        {
            LOG.error(
                    "Probably some date field in wrong format, they should be in 2008-02-01T09:00:22+05 (ISO 8601 date format) ",
                    e);
        }
        finally
        {
            if (workbook != null)
            {
                workbook.close();
            }
        }

        return periodicProductList;
    }

    private List<SubscriptionProduct> readSubscriptionProductSheet(final Sheet sheet) throws ParseException
    {
        Assert.notNull(sheet, "Sheet object must not be null");
        final List<SubscriptionProduct> listProduct = new ArrayList<>();

        for (int i = 1; i < sheet.getRows(); i++)
        {
            final SubscriptionProduct subscriptionProduct = new SubscriptionProduct();
            subscriptionProduct.setOfferOrigin(sheet.getCell(COL_1, i).getContents());
            subscriptionProduct.setOfferId(sheet.getCell(COL_2, i).getContents());
            subscriptionProduct.setOfferDescription(sheet.getCell(COL_3, i).getContents());
            subscriptionProduct.setOfferPicture(sheet.getCell(COL_4, i).getContents());
            subscriptionProduct.setProductOrigin(sheet.getCell(COL_5, i).getContents());
            subscriptionProduct.setProductId(sheet.getCell(COL_6, i).getContents());
            subscriptionProduct.setProductOwner(sheet.getCell(COL_7, i).getContents());
            subscriptionProduct.setProductClass(sheet.getCell(COL_8, i).getContents());
            subscriptionProduct.setProductGroup(sheet.getCell(COL_9, i).getContents());
            subscriptionProduct.setStudentOffer(Boolean.parseBoolean(sheet.getCell(COL_10, i).getContents()));
            subscriptionProduct.setClientOffer(Boolean.parseBoolean(sheet.getCell(COL_11, i).getContents()));
            subscriptionProduct.setInvoiceRecipientOffer(Boolean.parseBoolean(sheet.getCell(COL_12, i).getContents()));
            subscriptionProduct.setConsigneeOffer(Boolean.parseBoolean(sheet.getCell(COL_13, i).getContents()));
            subscriptionProduct.setOnlineOffer(Boolean.parseBoolean(sheet.getCell(COL_14, i).getContents()));
            subscriptionProduct.setMandatoryAddress(Boolean.parseBoolean(sheet.getCell(COL_15, i).getContents().trim()));
            subscriptionProduct.setMandatoryPhone(Boolean.parseBoolean(sheet.getCell(COL_16, i).getContents().trim()));
            subscriptionProduct.setMandatoryMobile(Boolean.parseBoolean(sheet.getCell(COL_17, i).getContents().trim()));
            subscriptionProduct.setMandatoryEmail(Boolean.parseBoolean(sheet.getCell(COL_18, i).getContents().trim()));
            subscriptionProduct.setMandatoryOptIn(Boolean.parseBoolean(sheet.getCell(COL_19, i).getContents().trim()));
            subscriptionProduct.setMinOrder(Integer.parseInt(sheet.getCell(COL_20, i).getContents()));
            subscriptionProduct.setMaxOrder(Integer.parseInt(sheet.getCell(COL_21, i).getContents()));
            subscriptionProduct.setOtherInvoiceRecipientAllowed(Boolean.parseBoolean(sheet.getCell(COL_22, i).getContents()));
            subscriptionProduct.setOtherConsigneeAllowed(Boolean.parseBoolean(sheet.getCell(COL_23, i).getContents()));
            subscriptionProduct.setOtherBrokerAllowed(Boolean.parseBoolean(sheet.getCell(COL_24, i).getContents()));
            subscriptionProduct.setBonusProduct(sheet.getCell(COL_25, i).getContents());
            subscriptionProduct.setBonusProductExtraPayment(Price.parsePrice(sheet.getCell(COL_26, i).getContents()));
            subscriptionProduct.setBonusAmount(Price.parsePrice(sheet.getCell(COL_27, i).getContents()));
            subscriptionProduct.setBonusMiles(parseInt(sheet.getCell(COL_28, i).getContents()));
            subscriptionProduct.setBonusDescription(sheet.getCell(COL_29, i).getContents());
            subscriptionProduct.setBonusPicture(sheet.getCell(COL_30, i).getContents());
            subscriptionProduct.setCommunicatedBillingFrequencySample(Integer.parseInt(sheet.getCell(COL_31, i).getContents()));
            subscriptionProduct.setCommunicatedBillingFrequencyConsequence(Integer.parseInt(sheet.getCell(COL_32, i)
                    .getContents()));
            subscriptionProduct.setBookedBillingFrequencySample(Integer.parseInt(sheet.getCell(COL_33, i).getContents()));
            subscriptionProduct.setBookedBillingFrequencyConsequence(Integer.parseInt(sheet.getCell(COL_34, i).getContents()));
            subscriptionProduct.setCommunicatedPriceSample(Price.parsePrice(sheet.getCell(COL_35, i).getContents()));
            subscriptionProduct.setCommunicatedPriceConsequence(Price.parsePrice(sheet.getCell(COL_36, i).getContents()));
            subscriptionProduct.setBookedPriceSample(Price.parsePrice(sheet.getCell(COL_37, i).getContents()));
            subscriptionProduct.setBookedPriceConsequence(Price.parsePrice(sheet.getCell(COL_38, i).getContents()));
            subscriptionProduct.setVat(Integer.parseInt(sheet.getCell(COL_39, i).getContents()));
            subscriptionProduct.setTermOfServiceRenewalSample(Boolean.parseBoolean(sheet.getCell(COL_40, i).getContents()));
            subscriptionProduct.setTermOfServiceRenewalConsequence(Boolean.parseBoolean(sheet.getCell(COL_41, i).getContents()));
            subscriptionProduct.setDiscountQuarter(Integer.parseInt(sheet.getCell(COL_42, i).getContents()));
            subscriptionProduct.setDiscountHalfYear(Integer.parseInt(sheet.getCell(COL_43, i).getContents()));
            subscriptionProduct.setDiscountYear(Integer.parseInt(sheet.getCell(COL_44, i).getContents()));
            subscriptionProduct.setFrequencySample(Integer.parseInt(sheet.getCell(COL_45, i).getContents()));
            subscriptionProduct.setFrequencyConsequence(Integer.parseInt(sheet.getCell(COL_46, i).getContents()));
            subscriptionProduct.setUnit(sheet.getCell(COL_47, i).getContents());

            subscriptionProduct.setValidPayments(toList(sheet.getCell(COL_48, i).getContents()));
            subscriptionProduct.setValidDeliveryCountries(toList(sheet.getCell(COL_49, i).getContents()));

            final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            subscriptionProduct.setValidFrom(sdf.parse(sheet.getCell(COL_50, i).getContents()));
            subscriptionProduct.setValidTo(sdf.parse(sheet.getCell(COL_51, i).getContents()));

            // set price and currency
            Double price = null;
            try
            {
                price = Double.valueOf(sheet.getCell(COL_52, i).getContents());
            }
            catch (final NumberFormatException e)
            {
                LOG.trace("readSheet() failed to parse COL_20");
            }
            subscriptionProduct.setPrice(sheet.getCell(COL_53, i).getContents(), price);

            listProduct.add(subscriptionProduct);
        }

        return listProduct;
    }

    private List<SingleProduct> readSheet(final Sheet sheet) throws ParseException
    {
        Assert.notNull(sheet, "Sheet object must not be null");
        final List<SingleProduct> listProduct = new ArrayList<>();

        for (int i = 1; i < sheet.getRows(); i++)
        {
            final SingleProduct singleProduct = new SingleProduct();
            singleProduct.setOfferOrigin(sheet.getCell(COL_1, i).getContents());
            singleProduct.setOfferId(sheet.getCell(COL_2, i).getContents());
            singleProduct.setOfferDescription(sheet.getCell(COL_3, i).getContents());
            singleProduct.setOfferPicture(sheet.getCell(COL_4, i).getContents());
            singleProduct.setProductOrigin(sheet.getCell(COL_5, i).getContents());
            singleProduct.setProductId(sheet.getCell(COL_6, i).getContents());
            singleProduct.setProductOwner(sheet.getCell(COL_7, i).getContents());
            singleProduct.setProductClass(sheet.getCell(COL_8, i).getContents());
            singleProduct.setProductGroup(sheet.getCell(COL_9, i).getContents());
            singleProduct.setPrepayOnly(Boolean.parseBoolean(sheet.getCell(COL_10, i).getContents()));
            singleProduct.setStudentOffer(Boolean.parseBoolean(sheet.getCell(COL_11, i).getContents()));
            singleProduct.setClientOffer(Boolean.parseBoolean(sheet.getCell(COL_12, i).getContents()));
            singleProduct.setInvoiceRecipientOffer(Boolean.parseBoolean(sheet.getCell(COL_13, i).getContents()));
            singleProduct.setConsigneeOffer(Boolean.parseBoolean(sheet.getCell(COL_14, i).getContents()));
            Integer min = null;
            try
            {
                min = Integer.valueOf(sheet.getCell(COL_15, i).getContents());
            }
            catch (final NumberFormatException e)
            {
                LOG.trace("readSheet() failed to parse COL_15");
            }
            singleProduct.setMinOrder(min);
            Integer max = null;
            try
            {
                max = Integer.valueOf(sheet.getCell(COL_16, i).getContents());
            }
            catch (final NumberFormatException e)
            {
                LOG.trace("readSheet() failed to parse COL_16");
            }
            singleProduct.setMaxOrder(max);
            singleProduct.setOtherInvoiceRecipientAllowed(Boolean.parseBoolean(sheet.getCell(COL_17, i).getContents()));
            singleProduct.setOtherConsigneeAllowed(Boolean.parseBoolean(sheet.getCell(COL_18, i).getContents()));
            Integer vat = null;
            try
            {
                vat = Integer.valueOf(sheet.getCell(COL_19, i).getContents());
            }
            catch (final NumberFormatException e)
            {
                LOG.trace("readSheet() failed to parse COL_19");
            }
            singleProduct.setVat(vat);
            Double price = null;
            try
            {
                price = Double.valueOf(sheet.getCell(COL_20, i).getContents());
            }
            catch (final NumberFormatException e)
            {
                LOG.trace("readSheet() failed to parse COL_20");
            }
            singleProduct.setPrice(sheet.getCell(COL_23, i).getContents(), price);

            final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            singleProduct.setValidFrom(sdf.parse(sheet.getCell(COL_21, i).getContents()));
            singleProduct.setValidTo(sdf.parse(sheet.getCell(COL_22, i).getContents()));

            listProduct.add(singleProduct);
        }
        return listProduct;
    }

    private Integer parseInt(final String arg)
    {
        if (StringUtils.isEmpty(arg))
        {
            return null;
        }
        else
        {
            return Integer.parseInt(arg);
        }
    }

    private static List<String> toList(final String str)
    {
        final List<String> result = new ArrayList<String>();

        if (StringUtils.isNotEmpty(str))
        {
            final StrTokenizer tokenizer = new StrTokenizer(str, DELIM);
            tokenizer.setIgnoreEmptyTokens(true);

            result.addAll(tokenizer.getTokenList());
        }

        return result;
    }
}
