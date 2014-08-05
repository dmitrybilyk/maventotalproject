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
package com.ownmocks.sso.model;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

/**
 * Representation of the user object.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Azarenkov Eugene <azarenkov.eugene@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 04, 2014
 * @module mock-pacoshop-services
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class UserEntity implements SSOEntity
{
	private String accountId;

	private Timestamp activationIdDeliveryDate;

	private Timestamp deletionDate;

	private String deletionReason;

	private String email;

	private String emailConfirmationStatus;

	private List<String> classification;

	private String emailUnconfirmed;

	private Timestamp firstLoginCompleted;

	private Boolean imported;

	private Integer numberFailedLogins;

	private String registrationBaseUrl;

	private Timestamp registrationDate;

	private String registrationServiceUrl;

	private String registrationIdProvider;

	private List<String> businessPartners;

	private Timestamp temporarilyBlockedUntil;

	private String status;

	private String platformId;

//	private List<CustomerDynamicField> dynamicFields;

	private List<CustomerTerms> terms;

	private List<String> deviceIds;

	private Timestamp validUntil;

	/**
	 * Set user data.
	 *
	 * @param fieldName User field name.
	 * @param value     Field value.
	 * @throws NoSuchFieldException   No such field in user object.
	 * @throws IllegalAccessException Field can not be accessed in user object.
	 */
	@Override
	public void setData(final String fieldName, final Object value) throws NoSuchFieldException, IllegalAccessException
	{
		Field field = this.getClass().getDeclaredField(fieldName);
		field.set(this, value);
	}

	/**
	 * Account id.
	 *
	 * @return Account id.
	 */
	public String getAccountId()
	{
		return accountId;
	}

	/**
	 * Get activation delivery date.
	 *
	 * @return Activation delivery date.
	 */
	public Timestamp getActivationIdDeliveryDate()
	{
		return activationIdDeliveryDate;
	}

	/**
	 * Get deletion date.
	 *
	 * @return Deletion date.
	 */
	public Timestamp getDeletionDate()
	{
		return deletionDate;
	}

	/**
	 * Deletion reason.
	 * @return Deletion reason.
	 */
	public String getDeletionReason()
	{
		return deletionReason;
	}

	/**
	 * Get email.
	 *
	 * @return Email string.
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Get email confirmation status.
	 *
	 * @return Confirmation status.
	 */
	public String getEmailConfirmationStatus()
	{
		return emailConfirmationStatus;
	}

	/**
	 * Get classification.
	 *
	 * @return Classifications list.
	 */
	public List<String> getClassification()
	{
		return classification;
	}

	/**
	 * Check if email is confirmed.
	 *
	 * @return Confirmed email.
	 */
	public String getEmailUnconfirmed()
	{
		return emailUnconfirmed;
	}

	/**
	 * Get first login timestamp.
	 *
	 * @return First login timestamp.
	 */
	public Timestamp getFirstLoginCompleted()
	{
		return firstLoginCompleted;
	}

	/**
	 * Get imported flag.
	 *
	 * @return Imported flag.
	 */
	public Boolean getImported()
	{
		return imported;
	}

	/**
	 * Get failed logins number.
	 *
	 * @return Failed logins count.
	 */
	public Integer getNumberFailedLogins()
	{
		return numberFailedLogins;
	}

	/**
	 * Get registration base url.
	 *
	 * @return Registration base url.
	 */
	public String getRegistrationBaseUrl()
	{
		return registrationBaseUrl;
	}

	/**
	 * Registration date.
	 *
	 * @return Registration date.
	 */
	public Timestamp getRegistrationDate()
	{
		return registrationDate;
	}

	/**
	 * Registration service url.
	 * @return Registration service url.
	 */
	public String getRegistrationServiceUrl()
	{
		return registrationServiceUrl;
	}

	/**
	 * Get registration id prodvider.
	 *
	 * @return Registration id provider.
	 */
	public String getRegistrationIdProvider()
	{
		return registrationIdProvider;
	}

	/**
	 * Get business partners list.
	 *
	 * @return Business partner list.
	 */
	public List<String> getBusinessPartners()
	{
		return businessPartners;
	}

	/**
	 * Get temporary blocked date (until).
	 *
	 * @return Temporary blocked date.
	 */
	public Timestamp getTemporarilyBlockedUntil()
	{
		return temporarilyBlockedUntil;
	}

	/**
	 * Get status.
	 *
	 * @return Status.
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Get platform id.
	 *
	 * @return Platform id.
	 */
	public String getPlatformId()
	{
		return platformId;
	}

	/**
	 * Get dynamics fields list.
	 *
	 * @return Dynamic fields list.
//	 */
//	public List<CustomerDynamicField> getDynamicFields()
//	{
//		return dynamicFields;
//	}

	/**
	 * Get terms.
	 *
	 * @return Get terms list.
	 */
	public List<CustomerTerms> getTerms()
	{
		return terms;
	}

	/**
	 * Get device Ids.
	 *
	 * @return Device ids.
	 */
	public List<String> getDeviceIds()
	{
		return deviceIds;
	}

	/**
	 * Get valid until date.
	 *
	 * @return Valid until date.
	 */
	public Timestamp getValidUntil()
	{
		return validUntil;
	}
}
