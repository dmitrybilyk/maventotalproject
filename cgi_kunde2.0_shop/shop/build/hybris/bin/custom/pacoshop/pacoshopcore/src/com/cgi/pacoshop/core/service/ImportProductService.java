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
package com.cgi.pacoshop.core.service;


import de.hybris.platform.core.model.product.ProductModel;
import java.util.Collection;

/**
 * Service layer interface for import products. This interface called from cronjob to import product
 *
 *
 * @module pacoshopcore
 * @version 1.0v dec 18, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see         "https://wiki.hybris.com/"
 * @copyright 2013 symmetrics - a CGI Group brand
 *
 * @param <T> Product model.
 */
public interface ImportProductService<T extends ProductModel>
{
	/**
	 * Method that called from cronjob to import single products.
	 * @return Collection of products.
	 */
	Collection<T> importSingleProduct();

	/**
	 * Method that called from cronjob to import subscription products.
	 * @return Collection of products.
	 */
	Collection<T> importSubscriptionProducts();
}
