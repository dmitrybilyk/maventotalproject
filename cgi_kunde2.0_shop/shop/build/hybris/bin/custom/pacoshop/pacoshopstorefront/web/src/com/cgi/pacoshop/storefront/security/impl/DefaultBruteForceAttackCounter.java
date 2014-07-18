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
package com.cgi.pacoshop.storefront.security.impl;

import com.cgi.pacoshop.storefront.security.BruteForceAttackCounter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Default "bruteforce" security.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Nov 01, 2013
 * @module pacoshopstorefront
 * @link http://www.symmetrics.de/
 * @copyright 2013-2014 symmetrics - a CGI Group brand
 * @see " https://wiki.hybris.com/"
 */
public class DefaultBruteForceAttackCounter implements BruteForceAttackCounter
{
	/**
	 * Cache size multiplier.
	 */
	private final static double CACHE_SIZE_MULTIPLIER = 0.5;

	private ConcurrentHashMap<String, LoginFailure> bruteForceAttackCache;
	private Integer maxFailedLogins;
	private Integer cacheSizeLimit;
	private Integer cacheExpiration;

	/**
	 * Constructor.
	 *
	 * @param maxFailedLogins Max fail logins count.
	 * @param cacheExpiration Cache expiration count.
	 * @param cacheSizeLimit  Cache limit size.
	 */
	public DefaultBruteForceAttackCounter(final Integer maxFailedLogins,
										  final Integer cacheExpiration,
										  final Integer cacheSizeLimit)
	{
		bruteForceAttackCache = new ConcurrentHashMap((int) CACHE_SIZE_MULTIPLIER * cacheSizeLimit);
		this.maxFailedLogins = maxFailedLogins;
		this.cacheSizeLimit = cacheSizeLimit;
		this.cacheExpiration = cacheExpiration;
	}

	/**
	 * Register login failure.
	 *
	 * @param userUid that the failure is registered for
	 */
	@Override
	public void registerLoginFailure(final String userUid)
	{
		if (StringUtils.isNotEmpty(userUid))
		{
			LoginFailure count = get(prepareUserUid(userUid), Integer.valueOf(0));
			count.setCounter(Math.min(count.getCounter() + 1, maxFailedLogins + 1));
			count.setDate(new Date());
			bruteForceAttackCache.put(prepareUserUid(userUid), count);
		}
	}

	/**
	 * Check if request is attack.
	 *
	 * @param userUid user uid against which the check is performed
	 * @return Attack flag.
	 */
	@Override
	public boolean isAttack(final String userUid)
	{
		if (StringUtils.isNotEmpty(userUid))
		{
			return maxFailedLogins.compareTo(get(prepareUserUid(userUid), Integer.valueOf(0)).getCounter()) <= 0;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Reset user counter.
	 *
	 * @param userUid user uid that failed logins counter will be reset
	 */
	@Override
	public void resetUserCounter(final String userUid)
	{
		if (StringUtils.isNotEmpty(userUid))
		{
			bruteForceAttackCache.remove(prepareUserUid(userUid));
		}
	}

	/**
	 * Get user failed logins count.
	 *
	 * @param userUid user uid to return failed login number
	 * @return User's failed logins count.
	 */
	@Override
	public int getUserFailedLogins(final String userUid)
	{
		if (StringUtils.isNotEmpty(userUid))
		{
			return get(prepareUserUid(userUid), Integer.valueOf(0)).getCounter();
		}
		else
		{
			return Integer.valueOf(0);
		}
	}

	/**
	 * Get failed login by user UID.
	 *
	 * @param userUid    User UID.
	 * @param startValue Start value.
	 * @return Login failure object.
	 */
	protected LoginFailure get(final String userUid, final Integer startValue)
	{
		LoginFailure value = bruteForceAttackCache.get(prepareUserUid(userUid));
		if (value == null)
		{
			value = new LoginFailure(startValue, new Date());
			value = bruteForceAttackCache.putIfAbsent(prepareUserUid(userUid), value);
			if (bruteForceAttackCache.size() > cacheSizeLimit)
			{
				evict();
			}
		}
		return value;
	}

	/**
	 * Prepare user UID.
	 *
	 * @param userUid User UID.
	 * @return Parse User UID.
	 */
	protected String prepareUserUid(final String userUid)
	{
		return StringUtils.lowerCase(userUid);
	}

	/**
	 * Evict!
	 */
	protected void evict()
	{
		if (bruteForceAttackCache.size() > cacheSizeLimit)
		{
			Iterator<String> cacheIterator = bruteForceAttackCache.keySet().iterator();
			Date dateLimit = DateUtils.addMinutes(new Date(), 0 - cacheExpiration);
			while (cacheIterator.hasNext())
			{
				String userKey = cacheIterator.next();
				LoginFailure value = bruteForceAttackCache.get(userKey);
				if (value.getDate().before(dateLimit))
				{
					bruteForceAttackCache.remove(userKey);
				}
			}
		}
	}

	/**
	 * Login failure class.
	 */
	public static class LoginFailure
	{
		private Integer counter;
		private Date date;

		/**
		 * Constructor for Login failure class.
		 */
		public LoginFailure()
		{
			this.counter = Integer.valueOf(0);
			this.date = new Date();
		}

		/**
		 * Constructor for Login failure class.
		 *
		 * @param counter Counter.
		 * @param date    Date.
		 */
		public LoginFailure(final Integer counter, final Date date)
		{
			this.counter = counter;
			this.date = (Date) date.clone();
		}

		/**
		 * Get counter.
		 *
		 * @return Current counter.
		 */
		public Integer getCounter()
		{
			return counter;
		}

		/**
		 * Set counter.
		 *
		 * @param counter Counter.
		 */
		public void setCounter(final Integer counter)
		{
			this.counter = counter;
		}

		/**
		 * Get current date.
		 *
		 * @return Current date.
		 */
		public Date getDate()
		{
			return new Date(date.getTime());
		}

		/**
		 * Set date.
		 *
		 * @param date Date object.
		 */
		public void setDate(final Date date)
		{
			this.date = new Date(date.getTime());
		}
	}
}
