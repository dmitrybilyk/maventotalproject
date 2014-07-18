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
package com.cgi.pacoshop.facades.checkout.deeplink;


import com.cgi.pacoshop.core.model.OrderDTO;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;

/**
 *  Facade to provide the logic for the Alternative Deeplink controller.
 * .
 *
 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public interface DeeplinkCallbackFacade
{
	/**
	 * Methods allows to retrieve url from hybris based on portalId.
	 * @param portalId string identifier for portal ID.
	 * @return String Url for callback;
	 */
	String getPortalUrl(String portalId);

	/**
	 * methods for creating shopping cart.
	 * @param productList Collection of ProductModel that exist in Hybris.
	 * @param orderDTO OrderDTO from callbackService
	 * @return CartModel
	 */
	CartModel createCartWithProducts(List<ProductModel> productList, OrderDTO orderDTO);

	/**
	 * method retrieves list of product based on OrderDTO.
	 * @param orderDTO  OrderDTO from callbackService
	 * @return collection of ProductModel
	 */
	List<ProductModel> getProductModels(OrderDTO orderDTO);

	/**
	 * Method call calculation service.
	 * @param cartModel created and saved CartModel
	 */
	void calculateCart(CartModel cartModel);
}
