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
package com.cgi.pacoshop.mock.servicebus.service.impl;

import com.cgi.pacoshop.mock.servicebus.model.ResponseSingleProduct;
import com.cgi.pacoshop.mock.servicebus.model.ResponseSubscriptionProduct;
import com.cgi.pacoshop.mock.servicebus.service.ExcelReader;
import com.cgi.pacoshop.mock.servicebus.service.RestProduct;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Implementation for RestSingleProduct interface Class should be use for retrieving information from Service Bus for
 * single product
 * 
 * @module MockServiceBus
 * @version 1.0v Jan 15, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@cgi.com>
 * @link http://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 * 
 * 
 */
public class RestProductImpl implements RestProduct
{
    private final static Logger LOG = Logger.getLogger(RestProductImpl.class);

    @Autowired
    private ExcelReader excelReader;

    @Override
    public String getSingleProduct(final int delay) throws InterruptedException
    {
        final ResponseSingleProduct obj = new ResponseSingleProduct();
        obj.setProductList(excelReader.getSingleProduct());
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        //Add delay for testing purpose
        Thread.sleep(delay);
        return gson.toJson(obj.getProductList());
    }

    @Override
    public String getSubscriptionProduct(final int delay) throws InterruptedException
    {
        final ResponseSubscriptionProduct responseSubscriptionProduct = new ResponseSubscriptionProduct();
        responseSubscriptionProduct.setSubscriptionProductList(excelReader.getSubscriptionProduct());
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
        Thread.sleep(delay);
        return gson.toJson(responseSubscriptionProduct.getSubscriptionProductList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.mock.servicebus.service.RestProduct#createSingleOrder(javax.ws.rs.core.HttpHeaders, byte[])
     */
    @Override
    public Response createSingleOrder(final HttpHeaders headers, final byte[] in, final int delay) throws InterruptedException
    {
        Thread.sleep(delay);
        return Response.status(Response.Status.CREATED).build();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.cgi.pacoshop.mock.servicebus.service.RestProduct#createSingleOrder(javax.ws.rs.core.HttpHeaders, byte[])
     */
    @Override
    public Response createPeriodicsOrder(final HttpHeaders headers, final byte[] in, final int delay) throws InterruptedException
    {
        Thread.sleep(delay);
        return Response.status(Response.Status.CREATED).build();

    }

    @Override
    public Response getProductError(final int code)
    {
        return Response.status(code).build();
    }

    /**
     * Gets excel reader.
     * 
     * @return the excel reader
     */
    public ExcelReader getExcelReader()
    {
        return excelReader;
    }

    /**
     * Sets excel reader.
     * 
     * @param excelReaderParam
     *            the excel reader
     */
    public void setExcelReader(final ExcelReader excelReaderParam)
    {
        this.excelReader = excelReaderParam;
    }
}
