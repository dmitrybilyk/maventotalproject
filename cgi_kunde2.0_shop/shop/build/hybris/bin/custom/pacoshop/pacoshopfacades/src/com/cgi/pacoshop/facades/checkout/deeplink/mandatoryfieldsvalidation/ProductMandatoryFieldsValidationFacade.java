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
package com.cgi.pacoshop.facades.checkout.deeplink.mandatoryfieldsvalidation;


import com.cgi.pacoshop.core.exceptions.deeplink.PacoShopWebException;
import com.cgi.pacoshop.core.model.ProductDTO;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;

/**
 * Facade to provide the logic for the product mandatory fields validation
 *
 * @module hybris - pacoshopfacades
 * @version 1.0v dec 12, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
public interface ProductMandatoryFieldsValidationFacade
{

	/**
	 * Validate mandatory fields in products from the deeplink.
	 *
	 * The product is INVALID when:
	 * it does not exist in the hybris catalogue belonging to the site
	 * it is not active
	 * it should not be sold online yet ("onlineDate" <= current date <= "offlineDate")
	 * when bought with a prohibited amount ("minimumQuantity" <= number of product instances in the cart <= "maximumQuantity").
	 * if the subscription price plan is missing
	 *
	 * @param product product in online catalog
	 * @param requestedProductSpecification product data from cart.
	 * @return list of wrongs in a product)
	 */
	List<PacoShopWebException> validateProduct(ProductModel product, ProductDTO requestedProductSpecification);

}
