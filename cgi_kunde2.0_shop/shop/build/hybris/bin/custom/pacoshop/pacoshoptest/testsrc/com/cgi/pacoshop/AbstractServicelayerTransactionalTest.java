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
package com.cgi.pacoshop;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;


/**
 * Common importData for order fulfillment integration tests
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Mar 04 2014
 * @module pacoshopcore
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public abstract class AbstractServicelayerTransactionalTest extends ServicelayerTransactionalTest
{
	/**
	 * Is imported flag.
	 */
	private boolean isImported = false;

	/**
	 * Setup integration test impexex for order fulfillment.
	 * 
	 * @throws Exception
	 *            thrown if some problem happens during importData
	 */
	public void importData() throws Exception
	{
		if (!isImported)
		{
			importCsv("/pacoshoptest/import/JunitTest/countries.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/essential-data.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/catalog.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/delivery-modes.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/payment-types.impex", "utf-8");
			importCsv("/pacoshoptest/import/stores/kunde1/store.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/themes.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/site.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/vendor.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/warehouse.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/offerorigin.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/productowner.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/fulfillmenttype.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/media.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/media-container.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/vat-groups.impex", "utf-8");
			importCsv("/pacoshoptest/import/JunitTest/product-types.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/product.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/stocklevel.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/bonuses.impex", "utf-8");
			importCsv("/pacoshoptest/import/kundeTestProducts/subscriptionterms.impex", "utf-8");

			isImported = true;
		}
	}
}
