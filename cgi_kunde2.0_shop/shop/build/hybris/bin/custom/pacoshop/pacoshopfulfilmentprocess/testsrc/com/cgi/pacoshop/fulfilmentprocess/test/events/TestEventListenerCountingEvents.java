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
package com.cgi.pacoshop.fulfilmentprocess.test.events;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * OOTB test.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 * @param <T>
 *            the event type to process.
 */
public class TestEventListenerCountingEvents<T extends AbstractEvent> extends AbstractEventListener<T>
{
	// must be thread-safe since it's being hit by different threads
	private final AtomicInteger numberOfEvents = new AtomicInteger(0);

	@Override
	protected void onEvent(final T event)
	{
		numberOfEvents.incrementAndGet();
	}

	/**
	 * @return number of onEvent invocation since beginning or last resetCounter() invocation
	 */
	public int getNumberOfEvents()
	{
		return numberOfEvents.get();
	}

	/**
	 * reset counter which store number of onEvent() invocations.
	 */
	public void resetCounter()
	{
		numberOfEvents.set(0);
	}
}
