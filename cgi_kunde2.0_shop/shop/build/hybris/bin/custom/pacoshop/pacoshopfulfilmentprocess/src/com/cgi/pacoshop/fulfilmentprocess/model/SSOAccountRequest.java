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
package com.cgi.pacoshop.fulfilmentprocess.model;


import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Here goes 1 line. Here goes 2 line.
 * 
 * @module build
 * @version 1.0v Feb 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Ierofeiev <oleg.ierofeiev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class SSOAccountRequest implements Serializable
{

    private static final long serialVersionUID = -7939223536042927150L;

    private String email;
    private String sapGpNumbers;
    private String dynamicFields;

    private boolean populated = false;

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email)
    {
        this.email = email;
    }

    /**
     * @return the sapGpNumbers
     */
    public String getSapGpNumbers()
    {
        return sapGpNumbers;
    }

    /**
     * @param sapGpNumbers
     *            the sapGpNumbers to set
     */
    public void setSapGpNumbers(final String sapGpNumbers)
    {
        this.sapGpNumbers = sapGpNumbers;
    }

    /**
     * @return the dynamicFields
     */
    public String getDynamicFields()
    {
        return dynamicFields;
    }

    /**
     * @param dynamicFields
     *            the dynamicFields to set
     */
    public void setDynamicFields(final String dynamicFields)
    {
        this.dynamicFields = dynamicFields;
    }

    /**
     * @return the populated
     */
    public boolean isPopulated()
    {
        return populated;
    }

    /**
     * @param populated
     *            the populated to set
     */
    public void setPopulated(final boolean populated)
    {
        this.populated = populated;
    }


}
