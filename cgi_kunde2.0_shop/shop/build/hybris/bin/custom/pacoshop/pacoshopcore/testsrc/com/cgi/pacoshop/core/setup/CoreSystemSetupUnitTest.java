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
package com.cgi.pacoshop.core.setup;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.acceleratorservices.setup.SetupImpexService;
import de.hybris.platform.acceleratorservices.setup.SetupSolrIndexerService;
import de.hybris.platform.acceleratorservices.setup.SetupSyncJobService;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.validation.services.ValidationService;

import java.util.Collections;
import java.util.Set;

import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


@UnitTest
public class CoreSystemSetupUnitTest
{
	private CoreSystemSetup setup;

	@Mock
	private SystemSetupContext ctx;

	@Mock
	private ValidationService validation;

	@Mock
	private SetupImpexService setupImpexService;

	@Mock
	private SetupSyncJobService setupSyncJobService;

	@Mock
	private SetupSolrIndexerService setupSolrIndexerService;

	@Mock
	private EventService eventService;

	@org.junit.Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);
		setup = Mockito.spy(new CoreSystemSetup());

		Mockito.doReturn(Collections.EMPTY_LIST).when(setup).getExtensionNames();
		Mockito.doReturn(setupImpexService).when(setup).getSetupImpexService();
		Mockito.doReturn(setupSyncJobService).when(setup).getSetupSyncJobService();
		Mockito.doReturn(setupSolrIndexerService).when(setup).getSetupSolrIndexerService();
		Mockito.doReturn(eventService).when(setup).getEventService();

		Mockito.doReturn("core").when(ctx).getExtensionName();
		Mockito.doReturn(validation).when(setup).getBeanForName("validationService");

		simulateBooleanParameter(CoreSystemSetup.IMPORT_ACCESS_RIGHTS, false);
		simulateBooleanParameter(CoreSystemSetup.ACTIVATE_SOLR_CRON_JOBS, false);
		simulateBooleanParameter(CoreSystemSetup.IMPORT_SITES, true);
	}


	private void testImportSitesSync(final boolean syncAll)
	{
		//		final InOrder order = Mockito.inOrder(setupImpexService, setupSyncJobService, setupSolrIndexerService, validation);

		simulateBooleanParameter(CoreSystemSetup.IMPORT_SYNC_CATALOGS, syncAll);


		setup.createProjectData(ctx);

		//		//electronics
		//		verifyProductImport(order, CoreSystemSetup.ELECTRONICS, syncAll);
		//
		//		verifyContentImport(order, CoreSystemSetup.ELECTRONICS, syncAll);
		//		verifySyncContentImport(order, CoreSystemSetup.ELECTRONICS, syncAll);
		//
		//		verifySyncProductImport(order, CoreSystemSetup.ELECTRONICS, syncAll);
		//		//Solr is no longer created in this way in CoreSystemSetup
		//		//verifySolrProductImport(order, CoreSystemSetup.ELECTRONICS, syncAll);
		//
		//		//apparel
		//		verifyProductImport(order, CoreSystemSetup.APPAREL, syncAll);
		//
		//		verifyContentImport(order, CoreSystemSetup.APPAREL_UK, syncAll);
		//		verifyContentImport(order, CoreSystemSetup.APPAREL_DE, syncAll);
		//		//verifyContentImport(order, CoreSystemSetup.APPAREL_US, syncAll);
		//
		//		verifyAssignDependant(
		//				order,
		//				CoreSystemSetup.APPAREL + "ProductCatalog",
		//				new HashSet<String>(Arrays.asList(CoreSystemSetup.APPAREL_UK + "ContentCatalog", CoreSystemSetup.APPAREL_DE
		//						+ "ContentCatalog")));
		//
		//		verifySyncContentImport(order, CoreSystemSetup.APPAREL_UK, syncAll);
		//		verifySyncContentImport(order, CoreSystemSetup.APPAREL_DE, syncAll);
		//		//verifySyncContentImport(order, CoreSystemSetup.APPAREL_US, syncAll);
		//
		//		verifySyncProductImport(order, CoreSystemSetup.APPAREL, syncAll);
		//		//Solr is no longer created in this way in CoreSystemSetup
		//		//verifySolrProductImport(order, CoreSystemSetup.APPAREL, syncAll);


		Mockito.verify(validation).reloadValidationEngine();
	}

	@Test
	public void testImportSitesSyncProductsAndContent()
	{
		testImportSitesSync(true);
	}

	@Test
	public void testImportSitesNoSync()
	{
		testImportSitesSync(false);
	}

	/**
	 * 
	 */
	private void verifyAssignDependant(final InOrder order, final String productCatalog, final Set<String> contentCatalogs)
	{
		order.verify(setupSyncJobService).assignDependentSyncJobs(productCatalog, contentCatalogs);
		// YTODO Auto-generated method stub
		//getSetupSyncJobService().assignDependentSyncJobs(dependentSyncJobsNames, productCatalog + "ProductCatalog");
	}

	private void verifySyncProductImport(final InOrder order, final String catalogName, final boolean sync)
	{

		if (sync)
		{
			order.verify(setupSyncJobService).executeCatalogSyncJob(catalogName + "ProductCatalog");
		}

	}

	private void verifyProductImport(final InOrder order, final String catalogName, final boolean sync)
	{
		order.verify(setupImpexService).importImpexFile(
				"/pacoshopcore/import/productCatalogs/" + catalogName + "ProductCatalog/catalog.impex", true);

		order.verify(setupSyncJobService).createProductCatalogSyncJob(catalogName + "ProductCatalog");

		//		if (sync)
		//		{
		//			order.verify(setupSyncJobService).executeCatalogSyncJob(catalogName + "ProductCatalog");
		//		}
		//
		//		order.verify(setupSolrIndexerService).createSolrIndexerCronJobs(catalogName + "Index");
		//		order.verify(setupSolrIndexerService).executeSolrIndexerCronJob(catalogName + "Index", true);
		//
		//		order.verify(setupImpexService).importImpexFile(
		//				"/pacoshopcore/import/productCatalogs/" + catalogName + "ProductCatalog/solrtrigger.impex", true);
	}

	private void verifySolrProductImport(final InOrder order, final String catalogName, final boolean sync) //NOPMD
	{
		if (sync)
		{
			order.verify(setupSolrIndexerService).createSolrIndexerCronJobs(catalogName + "Index");
			//order.verify(setupSolrIndexerService).executeSolrIndexerCronJob(catalogName + "Index", true);

			order.verify(setupImpexService).importImpexFile(
					"/pacoshopcore/import/productCatalogs/" + catalogName + "ProductCatalog/solrtrigger.impex", true);
		}
	}


	private void verifyContentImport(final InOrder order, final String catalogName, final boolean sync)
	{
		order.verify(setupImpexService).importImpexFile(
				"/pacoshopcore/import/contentCatalogs/" + catalogName + "ContentCatalog/catalog.impex", true);
		order.verify(setupImpexService).importImpexFile(
				"/pacoshopcore/import/contentCatalogs/" + catalogName + "ContentCatalog/cms-content.impex", false);
		order.verify(setupImpexService).importImpexFile(
				"/pacoshopcore/import/contentCatalogs/" + catalogName + "ContentCatalog/email-content.impex", false);


		order.verify(setupSyncJobService).createContentCatalogSyncJob(catalogName + "ContentCatalog");

		//		if (sync)
		//		{
		//			order.verify(setupSyncJobService).executeCatalogSyncJob(catalogName + "ContentCatalog");
		//		}

	}

	private void verifySyncContentImport(final InOrder order, final String catalogName, final boolean sync)
	{
		if (sync)
		{
			order.verify(setupSyncJobService).executeCatalogSyncJob(catalogName + "ContentCatalog");
		}

	}

	private void simulateBooleanParameter(final String key, final boolean value)
	{
		BDDMockito.given(ctx.getParameter("core_" + key)).willReturn(value ? "yes" : "no");
	}
}
