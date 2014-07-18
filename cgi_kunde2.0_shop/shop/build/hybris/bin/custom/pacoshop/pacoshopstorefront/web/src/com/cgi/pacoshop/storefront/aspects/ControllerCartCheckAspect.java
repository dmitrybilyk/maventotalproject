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
package com.cgi.pacoshop.storefront.aspects;


import de.hybris.platform.commercefacades.order.CartFacade;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.cgi.pacoshop.core.exceptions.dynamic.CartNotExistException;


/**
 * This class checks session for existing cart In every checkout and payment controllers calls.
 * 
 * @module shop
 * @version 1.0v Jun 16, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Oleg Erofeev <oleg.erofeev@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
@Aspect
public class ControllerCartCheckAspect
{
	protected static final Logger LOG = Logger.getLogger(ControllerCartCheckAspect.class);

	@Resource
	private CartFacade cartFacade;

	/**
	 * Pointcut for all controllers.
	 */
	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void controller()
	{
		// does nothing
	}

	/**
	 * Pointcut for dynamic controllers.
	 */
	@Pointcut("execution(* com.cgi.pacoshop.storefront.controllers.checkout.dynamic.*.*(..))")
	public void method1Pointcut()
	{
		// does nothing
	}

	/**
	 * Pointcut for payment controllers.
	 */
	@Pointcut("execution(* com.cgi.pacoshop.storefront.controllers.checkout.payment.*.*(..))")
	public void method2Pointcut()
	{
		// does nothing
	}


	/**
	 * Check cart before controller method call.
	 * 
	 * @param joinPoint
	 *           the point.
	 * @throws Throwable
	 *            can throw Exception.
	 */
	@Before("controller() && (method1Pointcut() || method2Pointcut())")
	public void aroundControllerMethod(final JoinPoint joinPoint) throws Throwable
	{
		LOG.info("Invoked: " + joinPoint);
		if (!cartFacade.hasSessionCart() || cartFacade.getSessionCart().getEntries().isEmpty())
		{
			throw new CartNotExistException("Cart not exist or empty");
		}
	}

}
