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
package com.cgi.pacoshop.storefront.breadcrumb;

import java.util.List;

public interface ResourceBreadcrumbBuilder
{
	/**
	 *
	 * @param resourceKey String
	 * @return List of Breadcrumbs
	 * @throws java.lang.IllegalArgumentException - IllegalArgumentException
	 */
	List<Breadcrumb> getBreadcrumbs(String resourceKey) throws IllegalArgumentException;
}

