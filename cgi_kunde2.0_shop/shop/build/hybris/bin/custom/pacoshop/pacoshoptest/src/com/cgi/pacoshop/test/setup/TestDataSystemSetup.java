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
package com.cgi.pacoshop.test.setup;

import de.hybris.platform.acceleratorservices.setup.AbstractSystemSetup;
import de.hybris.platform.acceleratorservices.setup.data.ImportData;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.validation.services.ValidationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.cgi.pacoshop.test.constants.PacoshopTestConstants;
import com.cgi.pacoshop.test.orders.AcceleratorTestOrderData;


/**
 * This class provides hooks into the system's initialization and update processes.
 * 
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @version 1.0v Jan 01, 2013
 * @module pacoshoptest
 * @see "https://wiki.hybris.com/display/release4/Hooks+for+Initialization+and+Update+Process"
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@SystemSetup(extension = PacoshopTestConstants.EXTENSIONNAME)
public class TestDataSystemSetup extends AbstractSystemSetup
{
	private static final Logger LOG = Logger.getLogger(TestDataSystemSetup.class);

	private static final String CREATE_TEST_DATA = "createTestData";
	private static final String KUNDE = "kunde";
	private static final String KUNDE1 = "kunde1";
	private static final String KUNDE2 = "kunde2";
	private static final String IMPORT_SITES = "importSites";
	private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";
	private static final String IMPORT_SYNC_CATALOGS = "syncProducts&ContentCatalogs";


	private AcceleratorTestOrderData acceleratorTestOrderData;

	AcceleratorTestOrderData getAcceleratorTestOrderData()
	{
		return acceleratorTestOrderData;
	}

	/**
	 * 
	 * Setter for acceleratorTestOrderData.
	 * 
	 * @param pAcceleratorTestOrderData
	 *           the accelerator provides the selected parameters and values
	 * 
	 */
	@Required
	public void setAcceleratorTestOrderData(final AcceleratorTestOrderData pAcceleratorTestOrderData)
	{
		this.acceleratorTestOrderData = pAcceleratorTestOrderData;
	}

	/**
	 * Generates the Dropdown and Multi-select boxes for the projectdata import.
	 * 
	 * @return List of SystemSetupParameter params
	 * 
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<>();

		params.add(createBooleanSystemSetupParameter(CREATE_TEST_DATA, "Create Test Data", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SITES, "IMPORT_SITES", true));

		return params;
	}

	/**
	 * Implement this method to create initial objects. This method will be called by system creator during
	 * initialization and system update. Be sure that this method can be called repeatedly.
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		// In case there should ever be data that is essential and test data at the same time, add setup steps here.
	}

	/**
	 * Implement this method to create data that is used in your project. This method will be called during the system
	 * initialization.
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{

		final boolean importSites = getBooleanSystemSetupParameter(context, IMPORT_SITES);

		final ImportData kundeImportData = new ImportData();
		kundeImportData.setProductCatalogName(KUNDE);
		kundeImportData.setContentCatalogNames(Arrays.asList(KUNDE1, KUNDE2));
		kundeImportData.setStoreNames(Arrays.asList(KUNDE1, KUNDE2));

		if (importSites)
		{
			new ImportTestCatalogAwareDataStrategy(context, kundeImportData).importAllData();

			final ValidationService validation = getBeanForName();
			validation.reloadValidationEngine();
		}

		if (getBooleanSystemSetupParameter(context, CREATE_TEST_DATA))
		{
			//			new ImportTestCatalogAwareDataStrategy(context, kundeImportData).importAllData();
			final ValidationService validation = getBeanForName();
			validation.reloadValidationEngine();

			LOG.info("Creating Data For Test Products...");
			LOG.info("Creating Vendors...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/vendor.impex");

			LOG.info("Creating Warehouses...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/warehouse.impex");
			LOG.info("Creating OfferOrigins...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/offerorigin.impex");

			LOG.info("Creating ProductOwners...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/productowner.impex");

			LOG.info("Creating FulfillmentTypes...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/fulfillmenttype.impex");

			LOG.info("Creating Media...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/media.impex");

			LOG.info("Creating Media-Container...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/media-container.impex");

			LOG.info("Creating Products...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/product.impex");

			LOG.info("Creating StockLevels...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/stocklevel.impex");

			LOG.info("Creating Bonuses...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/bonuses.impex");

			LOG.info("Creating SubscriptionTerms...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/subscriptionterms.impex");


			LOG.info("Creating Subscription Products...");
			importImpexFile(context, "/pacoshoptest/import/kundeTestProducts/subscription-products.impex", true);

			/*
			 * LOG.info("Sync Subscription Products..."); importImpexFile(context,
			 * "/pacoshoptest/import/productCatalogs/kunde/synchronizationJobs.impex", true);
			 */

			executeCatalogSyncJob(context, "kundeProductCatalog");

			//		importImpexFile(context, "/pacoshoptest/import/reviews.impex", false);


		}
	}

	@SuppressWarnings("unchecked")
	<T> T getBeanForName()
	{
		return (T) Registry.getApplicationContext().getBean("validationService");
	}

	/**
	 * inner class for Strategy of ImportTestCatalogAwareDataStrategy.
	 * 
	 */
	private class ImportTestCatalogAwareDataStrategy
	{
		private final SystemSetupContext context;
		private final ImportData importData;

		public ImportTestCatalogAwareDataStrategy(final SystemSetupContext pContext, final ImportData pImportData)
		{
			this.context = pContext;
			this.importData = pImportData;
		}

		public String getProductCatalogName()
		{
			return importData.getProductCatalogName();
		}

		public List<String> getContentCatalogNames()
		{
			return importData.getContentCatalogNames();
		}

		public List<String> getStoreNames()
		{
			return importData.getStoreNames();
		}

		private boolean isSyncCatalog()
		{
			return getBooleanSystemSetupParameter(context, IMPORT_SYNC_CATALOGS);
		}

		public void importAllData()
		{
			//	createProductCatalog(getProductCatalogName());
			/*
			 * for (final String contentCatalogName : getContentCatalogNames()) {
			 * 
			 * createContentCatalog(contentCatalogName); }
			 */

			assignDependent(getProductCatalogName(), getContentCatalogNames());

			for (final String contentCatalogName : getContentCatalogNames())
			{
				syncContentCatalog(contentCatalogName);
			}

			syncProductCatalog(getProductCatalogName());

			for (final String storeName : getStoreNames())
			{
				createStore(storeName);

				createAndActivateSolrIndex(storeName);
			}

			LOG.info("Creating CronJobs...");
			importImpexFile(context, "/impex/essentialdataJobs.impex");
		}

		private void createAndActivateSolrIndex(final String storeName)
		{
			logInfo(context, "Begin SOLR index setup [" + storeName + "]");

			importImpexFile(context, "/pacoshoptest/import/stores/" + storeName + "/solr.impex");

			createSolrIndexerCronJobs(storeName + "Index");

			importImpexFile(context, "/pacoshoptest/import/stores/" + storeName + "/solrtrigger.impex");

			if (getBooleanSystemSetupParameter(context, ACTIVATE_SOLR_CRON_JOBS))
			{
				executeSolrIndexerCronJob(storeName + "Index", true);
				activateSolrIndexerCronJobs(storeName + "Index");
			}

			logInfo(context, "Done SOLR index setup [" + storeName + "]");
		}

		private void createStore(final String storeName)
		{
			logInfo(context, "Begin importing store [" + storeName + "]");

			importImpexFile(context, "/pacoshoptest/import/stores/" + storeName + "/store.impex");
			importImpexFile(context, "/pacoshoptest/import/stores/" + storeName + "/site.impex");

			logInfo(context, "Done importing store [" + storeName + "]");
		}

		/**
		 * fires product catalog with synchronization with respect for a dependent CatalogVersionSyncJob.
		 */
		private void syncProductCatalog(final String productCatalogName)
		{
			executeCatalogSyncJob(context, productCatalogName + "ProductCatalog");
		}

		/**
		 * fires content catalog with synchronization with respect for a depends on CatalogVersionSyncJob.
		 */
		private void syncContentCatalog(final String contentCatalogName)
		{
			executeCatalogSyncJob(context, contentCatalogName + "ContentCatalog");
		}

		private void assignDependent(final String dependsOnProduct, final List<String> dependentContents)
		{
			if (CollectionUtils.isNotEmpty(dependentContents) && StringUtils.isNotBlank(dependsOnProduct))
			{
				final Set<String> dependentSyncJobsNames = new HashSet<>();
				for (final String content : dependentContents)
				{
					dependentSyncJobsNames.add(content + "ContentCatalog");
				}

				getSetupSyncJobService().assignDependentSyncJobs(dependsOnProduct + "ProductCatalog", dependentSyncJobsNames);
			}
		}

		/*
		 * private void createContentCatalog(final String contentCatalogName) { logInfo(context,
		 * "Begin importing catalog [" + contentCatalogName + "]");
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/contentCatalogs/" + contentCatalogName +
		 * "ContentCatalog/catalog.impex", true); importImpexFile(context, "/pacoshoptest/import/contentCatalogs/" +
		 * contentCatalogName + "ContentCatalog/cms-content.impex", false); importImpexFile(context,
		 * "/pacoshoptest/import/contentCatalogs/" + contentCatalogName + "ContentCatalog/email-content.impex", false);
		 * 
		 * createContentCatalogSyncJob(context, contentCatalogName + "ContentCatalog");
		 * 
		 * }
		 * 
		 * private void createProductCatalog(final String productCatalogName) { logInfo(context,
		 * "Begin importing catalog [" + productCatalogName + "]");
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/productCatalogs/" + productCatalogName + "/catalog.impex",
		 * true);
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/productCatalogs/" + productCatalogName +
		 * "/subscription-products.impex", true);
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/productCatalogs/" + productCatalogName + "/categories.impex",
		 * true);
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/productCatalogs/" + productCatalogName + "/cms-content.impex",
		 * true);
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/common/user-groups.impex", true);
		 * 
		 * createProductCatalogSyncJob(context, productCatalogName + "ProductCatalog");
		 * 
		 * importImpexFile(context, "/pacoshoptest/import/productCatalogs/" + productCatalogName +
		 * "/synchronizationJobs.impex", true); }
		 */
	}
}
