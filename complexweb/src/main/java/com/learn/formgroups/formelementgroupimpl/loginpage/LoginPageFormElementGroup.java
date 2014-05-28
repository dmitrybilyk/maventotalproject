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
package com.learn.formgroups.formelementgroupimpl.loginpage;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.AbstractFormElementGroup;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.constants.FormElementGroupConstants;
import com.cgi.pacoshop.core.service.ShopConfigurationService;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.jalo.JaloSession;

import java.util.List;
import java.util.Set;

/**
 * Login formElementGroup.
 *
 * @module core
 * @version 1.0v Feb 19, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class LoginPageFormElementGroup extends AbstractFormElementGroup
{
	private static final String SUBMITTED = "anonymous.submitted";


	@Override
	public boolean isDisplayed(final CartModel cart)
	{
		List<AbstractOrderEntryModel> entries = cart.getEntries();
		for (AbstractOrderEntryModel entry : entries)
		{
			ProductModel productModel = entry.getProduct();
			if (isAsAnonymousVisitorSubmitted(cart) && !isOnlineArticle(productModel))
			{
				return true;
			}
		}
		return false;
	}

	private boolean isOnlineArticle(final ProductModel productModel)
	{
		final String onlineArticle = getConfigurationProperty(FormElementGroupConstants.ONLINE_ARTICLE);
		return productModel.getProductType().getCode().equals(onlineArticle);
	}

	private boolean isAsAnonymousVisitorSubmitted(final CartModel cartModel)
	{
		final UserModel user = cartModel.getUser();
		Set<PrincipalGroupModel> principalGroupModels = user.getGroups();
		for (PrincipalGroupModel groupModel : principalGroupModels)
		{
			if (groupModel.getUid().equals(getConfigurationProperty(ShopConfigurationService.VISITOR_CUSTOMER_GROUP_UID))
					  && !isAsAnonymousSubmitted())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isPrefilled(final CartModel cart)
	{
		return isAsAnonymousSubmitted();
	}

	@Override
	public void populateFormFromCart(final FormDTO dto, final CartModel cart)
	{

	}

	@Override
	public String getKey()
	{
		return getConfigurationProperty(FormElementGroupConstants.LOGIN_PAGE_FORM_ELEMENT_GROUP);
	}

	@Override
	public void saveFormToCart(final FormDTO dto, final CartModel cartModel)
	{
		getSession().setAttribute(SUBMITTED, true);
	}

	private boolean isAsAnonymousSubmitted()
	{
		Object buttonClicked = getSession().getAttribute(SUBMITTED);
		return (buttonClicked != null) && Boolean.parseBoolean(buttonClicked.toString());
	}

	private JaloSession getSession()
	{
		return JaloSession.getCurrentSession();
	}


}
