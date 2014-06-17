package com.learn.bases.compare.copmarator.dateandstringcomparator;

import java.util.Date;

/**
 * Created by bid on 6/17/14.
 */
public class Human
{
	private String businessPartnerId;
	private Date   birthDate;

	public String getBusinessPartnerId()
	{
		return businessPartnerId;
	}

	public void setBusinessPartnerId(final String businessPartnerId)
	{
		this.businessPartnerId = businessPartnerId;
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(final Date birthDate)
	{
		this.birthDate = birthDate;
	}
}
