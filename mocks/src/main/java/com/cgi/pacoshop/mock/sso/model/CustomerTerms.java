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
package com.cgi.pacoshop.mock.sso.model;

/**
 * Class that represents terms for Customer.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Jan 09, 2014
 * @module com.cgi.pacoshop.mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class CustomerTerms extends AbstractFieldParser
{
	private static final String SSO_MOCK_DATA_TERMS_CELL_PATTERN =
			  "name,termsType,termsVersionId,versionIdentifier,confirmed,refId,version,url";

	/**
	 * Field name.
	 */
	private String name;

	/**
	 * Field terms type.
	 */
	private String termsType;

	/**
	 * Field of version id.
	 */
	private String termsVersionId;

	/**
	 * Field version identifier.
	 */
	private String versionIdentifier;

	/**
	 * Field confirmed.
	 */
	private Boolean confirmed;

	/**
	 * Field reference id.
	 */
	private String refId;

	/**
	 * Version of the customer term. Used to detect the latest version of each termType group.
	 */
	private Double version;

	/**
	 * Terms document location URL. Used to create a link for the Opt-in term checkbox label.
	 */
	private String url;

	/**
	 * Create object and set it's data.
	 *
	 * @param data Object data.
	 * @throws NoSuchFieldException   No such field in this object.
	 * @throws IllegalAccessException Field cannot be accessed in this object.
	 */
	public CustomerTerms(final String data) throws NoSuchFieldException, IllegalAccessException
	{
		this.setData(data);
	}

	/**
	 * Get string pattern.
	 *
	 * @return Pattern string, example: "someField1,someField2";
	 */
	@Override
	protected String getPattern()
	{
		return SSO_MOCK_DATA_TERMS_CELL_PATTERN;
	}

	/**
	 * Get name.
	 *
	 * @return Name.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get terms type.
	 *
	 * @return Terms type.
	 */
	public String getTermsType()
	{
		return termsType;
	}

	/**
	 * Terms version id.
	 *
	 * @return Version id.
	 */
	public String getTermsVersionId()
	{
		return termsVersionId;
	}

	/**
	 * Get version identifier.
	 *
	 * @return Version identifier.
	 */
	public String getVersionIdentifier()
	{
		return versionIdentifier;
	}

	/**
	 * Check if field is confirmed.
	 *
	 * @return Confirmed flag.
	 */
	public Boolean getConfirmed()
	{
		return confirmed;
	}

	/**
	 * Reference id.
	 *
	 * @return Reference identity.
	 */
	public String getRefId()
	{
		return refId;
	}

	/**
	 * Terms version number.
	 *
	 * @return version of this CustomerTerms object.
	 */
	public Double getVersion()
	{
		return version;
	}

	/**
	 * Terms document location URL.
	 *
	 * @return URL String value where the Terms document is located.
	 */
	public String getUrl()
	{
		return url;
	}
}
