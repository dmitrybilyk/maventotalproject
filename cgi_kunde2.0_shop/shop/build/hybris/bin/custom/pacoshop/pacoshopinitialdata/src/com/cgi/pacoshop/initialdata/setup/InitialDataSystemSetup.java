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
package com.cgi.pacoshop.initialdata.setup;

import de.hybris.platform.acceleratorservices.setup.AbstractSystemSetup;
import de.hybris.platform.acceleratorservices.setup.data.ImportData;
import de.hybris.platform.acceleratorservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import com.cgi.pacoshop.core.setup.CoreSystemSetup;
import com.cgi.pacoshop.initialdata.constants.PacoshopInitialDataConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * This class provides hooks into the system's initialization and update processes.
 *
 * @see "https://wiki.hybris.com/display/release4/Hooks+for+Initialization+and+Update+Process"
 */
@SystemSetup(extension = PacoshopInitialDataConstants.EXTENSIONNAME)
public class InitialDataSystemSetup extends AbstractSystemSetup
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(InitialDataSystemSetup.class);

	private static final String BTG_EXTENSION_NAME        = "btg";
//	private static final String IMPORT_SAMPLE_DATA        = "importSampleData";
//	private static final String SAMPLE_DATA_IMPORT_FOLDER = "acceleratorsampledata";
	private static final String IMPORT_PACOSHOP_DATA      = "importKundeData";


	/**
	 * Generates the Dropdown and Multi-select boxes for the project data import
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();
		// Add more Parameters here as your require
		params.add(createBooleanSystemSetupParameter(IMPORT_PACOSHOP_DATA, "Import Kunde pacoshop Sample Data", true));

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
		// Add Essential Data here as you require
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
		// Add Store imports here as you require
		// importCommonData(context, "pacoshopinitialdata");
		// This would import a standard store: (one basestore, one cmssite, one product catalog, one content catalog)
		// importStoreInitialData(context, "pacoshopinitialdata", "yaccelerator", "yaccelerator", Collections.singletonList("yaccelerator"));

		if (getBooleanSystemSetupParameter(context, IMPORT_PACOSHOP_DATA))
		{
			logInfo(context, "Importing Kunde site content...");
			importCommonData(context, "pacoshopinitialdata");
			createProductCatalog(context, "pacoshopinitialdata", "kunde");
	//		importProductCatalog(context, "pacoshopinitialdata", "kunde");
			importContentCatalog(context, "pacoshopinitialdata", "kunde1");
			importContentCatalog(context, "pacoshopinitialdata", "kunde2");
/*
			importStoreLocations(context, "pacoshopinitialdata", "kunde1");
			importStoreLocations(context, "pacoshopinitialdata", "kunde2");
*/
			importStoreInitialData(context, "pacoshopinitialdata", "kunde1", "kunde", Collections.singletonList("kunde1"));
			importStoreInitialData(context, "pacoshopinitialdata", "kunde2", "kunde", Collections.singletonList("kunde2"));
			final ImportData kunde1ImportData = new ImportData();
			kunde1ImportData.setProductCatalogName("kunde");
			kunde1ImportData.setContentCatalogNames(Arrays.asList("kunde1"));
			kunde1ImportData.setStoreNames(Arrays.asList("kunde1"));
			final ImportData kunde2ImportData = new ImportData();
			kunde2ImportData.setProductCatalogName("kunde");
			kunde2ImportData.setContentCatalogNames(Arrays.asList("kunde2"));
			kunde2ImportData.setStoreNames(Arrays.asList("kunde2"));
			getEventService().publishEvent(new SampleDataImportedEvent(context, Arrays.asList(kunde1ImportData, kunde2ImportData)));
		}
	}

	/**
	 * Use this method to import a standard setup store.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 * @param storeName
	 *           the name of the store
	 * @param productCatalog
	 *           the name of the product catalog
	 * @param contentCatalogs
	 *           the list of content catalogs
	 */
	protected void importStoreInitialData(final SystemSetupContext context, final String importDirectory, final String storeName,
													  final String productCatalog, final List<String> contentCatalogs)
	{
		logInfo(context, "Begin importing store [" + storeName + "]");

		logInfo(context, "Begin importing advanced personalization rules for [" + storeName + "]");

		final String importRoot = "/" + importDirectory + "/import";

		if (isExtensionLoaded(BTG_EXTENSION_NAME))
		{
			importImpexFile(context, importRoot + "/stores/" + storeName + "/btg.impex", false);
		}


		/*logInfo(context, "Begin importing warehouses for [" + storeName + "]");

		importImpexFile(context, importRoot + "/stores/" + storeName + "/warehouses.impex", false);*/

		//createStore(context, storeName);
		// create product and content sync jobs
		synchronizeProductCatalog(context, productCatalog, true);
		for (final String contentCatalog : contentCatalogs)
		{
			synchronizeContentCatalog(context, contentCatalog, true);
		}
		assignDependent(productCatalog, contentCatalogs);

		// perform product sync job
		final boolean productSyncSuccess = synchronizeProductCatalog(context, productCatalog, true);
		if (!productSyncSuccess)
		{
			logInfo(context, "Product catalog synchronization for [" + productCatalog
					  + "] did not complete successfully, that's ok, we will rerun it after the content catalog sync.");
		}

//		importImpexFile(context, importRoot + "/stores/" + storeName + "/solr.impex", false);


		// perform content sync jobs
		for (final String contentCatalog : contentCatalogs)
		{
			synchronizeContentCatalog(context, contentCatalog, true);
		}

		if (!productSyncSuccess)
		{
			// Rerun the product sync if required
			logInfo(context, "Rerunning product catalog synchronization for [" + productCatalog + "]");
			if (!synchronizeProductCatalog(context, productCatalog, true))
			{
				logError(context, "Rerunning product catalog synchronization for [" + productCatalog
						  + "], failed please consult logs for more details.", null);
			}
		}

		// Load reviews after synchronization is done
/*
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + productCatalog
				  + "ProductCatalog/reviews.impex", false);
*/
		// Load promotions after synchronization is done
//		importImpexFile(context, "/" + importDirectory + "/import/stores/" + storeName + "/promotions.impex", false);

		// Index product data
//		logInfo(context, "Begin SOLR re-index [" + storeName + "]");
//		executeSolrIndexerCronJob(storeName + "Index", true);
//		logInfo(context, "Done SOLR re-index [" + storeName + "]");

//		if (getBooleanSystemSetupParameter(context, CoreSystemSetup.ACTIVATE_SOLR_CRON_JOBS))
//		{
//			logInfo(context, "Activating SOLR index job for [" + productCatalog + "]");
//			activateSolrIndexerCronJobs(productCatalog + "Index");
//		}
	}

	protected boolean isExtensionLoaded(final String extensionNameToCheck)
	{
		final List<String> loadedExtensionNames = getLoadedExtensionNames();
		return loadedExtensionNames.contains(extensionNameToCheck);
	}

	protected List<String> getLoadedExtensionNames()
	{
		return Registry.getCurrentTenant().getTenantSpecificExtensionNames();
	}

	protected boolean isExtensionLoaded(final List<String> loadedExtensionNames, final String extensionNameToCheck)
	{
		return loadedExtensionNames.contains(extensionNameToCheck);
	}

	protected void importProductCatalog(final SystemSetupContext context, final String importDirectory, final String catalogName)
	{
//		logInfo(context, "Begin importing Product Catalog [" + catalogName + "]");

		// Load Units
/*
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/classifications-units.impex", false);
*/

		// Load Categories
/*
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/categories.impex", false);
*/

/*
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/categories-classifications.impex", false);
*/

		// Load Suppliers
/*
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/suppliers.impex", false);
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/suppliers-media.impex", false);

		// Load medias for Categories as Suppliers loads some new Categories
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/categories-media.impex", false);
*/

		// Load Products
/*
		importImpexFile(context,
							 "/" + importDirectory + "/import/productCatalogs/" + catalogName + "ProductCatalog/products.impex");
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-media.impex", false);
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-classifications.impex", false);
*/

		// Load Products Relations
/*
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-relations.impex", false);

		// Load Products Fixes
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-fixup.impex", false);

		// Load Prices
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-prices.impex", false);

		// Load Stock Levels
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-stocklevels.impex", false);
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-pos-stocklevels.impex", false);

		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + catalogName
				  + "ProductCatalog/products-tax.impex", false);
*/

	}

	private void createProductCatalog(final SystemSetupContext context, final String importDirectory, final String productCatalogName)
	{
		logInfo(context, "Begin importing catalog [" + productCatalogName + "]");

		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + productCatalogName + "/catalog.impex", true);/*

		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + productCatalogName + "/subscription-products.impex",
							 true);
*/
		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + productCatalogName + "/categories.impex", true);

//		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + productCatalogName + "/cms-content.impex", true);

//		importImpexFile(context, "/" + importDirectory + "/import/common/user-groups.impex", true);

		createProductCatalogSyncJob(context, productCatalogName + "ProductCatalog");

		importImpexFile(context, "/" + importDirectory + "/import/productCatalogs/" + productCatalogName + "/synchronizationJobs.impex",
							 true);
	}

	protected boolean synchronizeProductCatalog(final SystemSetupContext context, final String catalogName, final boolean sync)
	{
		logInfo(context, "Begin synchronizing Product Catalog [" + catalogName + "] - "
				  + (sync ? "synchronizing" : "initializing job"));

		createProductCatalogSyncJob(context, catalogName + "ProductCatalog");

		boolean result = true;

		if (sync)
		{
			final PerformResult syncCronJobResult = executeCatalogSyncJob(context, catalogName + "ProductCatalog");
			if (isSyncRerunNeeded(syncCronJobResult))
			{
				logInfo(context, "Product catalog [" + catalogName + "] sync has issues.");
				result = false;
			}
		}

		logInfo(context, "Done " + (sync ? "synchronizing" : "initializing job") + " Product Catalog [" + catalogName + "]");
		return result;
	}


	protected void importContentCatalog(final SystemSetupContext context, final String importDirectory, final String catalogName)
	{
		logInfo(context, "Begin importing Content Catalog [" + catalogName + "]");

		final String importRoot = "/" + importDirectory + "/import";
		importImpexFile(context, importRoot + "/contentCatalogs/" + catalogName + "ContentCatalog/catalog.impex", true);
		importImpexFile(context, importRoot + "/contentCatalogs/" + catalogName + "ContentCatalog/cms-content.impex", false);
		importImpexFile(context, importRoot + "/contentCatalogs/" + catalogName + "ContentCatalog/email-content.impex", false);

		logInfo(context, "Done importing Content Catalog [" + catalogName + "]");
	}

	protected boolean synchronizeContentCatalog(final SystemSetupContext context, final String catalogName, final boolean sync)
	{
		logInfo(context, "Begin synchronizing Content Catalog [" + catalogName + "] - "
				  + (sync ? "synchronizing" : "initializing job"));

		createContentCatalogSyncJob(context, catalogName + "ContentCatalog");

		boolean result = true;

		if (sync)
		{
			final PerformResult syncCronJobResult = executeCatalogSyncJob(context, catalogName + "ContentCatalog");
			if (isSyncRerunNeeded(syncCronJobResult))
			{
				logInfo(context, "Catalog catalog [" + catalogName + "] sync has issues.");
				result = false;
			}
		}

		logInfo(context, "Done " + (sync ? "synchronizing" : "initializing job") + " Content Catalog [" + catalogName + "]");
		return result;
	}

	/**
	 * Imports Common Data
	 */
	protected void importCommonData(final SystemSetupContext context, final String importDirectory)
	{
		logInfo(context, "Importing Common Data...");

		final String importRoot = "/" + importDirectory + "/import";

		//importImpexFile(context, importRoot + "/common/user-groups.impex", false);
		importImpexFile(context, importRoot + "/common/payment-types.impex", false);
		importImpexFile(context, importRoot + "/common/product-types.impex", false);
		importImpexFile(context, importRoot + "/common/vat-groups.impex", false);

		importImpexFile(context, importRoot + "/common/countries.impex", false);
		//importImpexFile(context, importRoot + "/common/essential-data.impex", false);
		importImpexFile(context, importRoot + "/common/themes.impex", false);
        importImpexFile(context, importRoot + "/common/themes_en.impex", false);
        importImpexFile(context, importRoot + "/common/themes_de.impex", false);
		importImpexFile(context, importRoot + "/common/portalid.impex", false);
		importImpexFile(context, importRoot + "/common/usergroups.impex", false);
		importImpexFile(context, importRoot + "/common/cms-content.impex", false);
		importImpexFile(context, importRoot + "/common/user-rights.impex", false);
		importImpexFile(context, importRoot + "/common/fingerprints.impex", false);

/*
		final List<String> loadedExtensionNames = getLoadedExtensionNames();
		if (isExtensionLoaded(loadedExtensionNames, "cmscockpit"))
		{
			importImpexFile(context, importRoot + "/cockpits/cmscockpit/cmscockpit-users.impex");
		}

		if (isExtensionLoaded(loadedExtensionNames, "productcockpit"))
		{
			importImpexFile(context, importRoot + "/cockpits/productcockpit/productcockpit-users.impex");
		}

		if (isExtensionLoaded(loadedExtensionNames, "cscockpit"))
		{
			importImpexFile(context, importRoot + "/cockpits/cscockpit/cscockpit-users.impex");
		}

		if (isExtensionLoaded(loadedExtensionNames, "reportcockpit"))
		{
			importImpexFile(context, importRoot + "/cockpits/reportcockpit/reportcockpit-users.impex");

			if (isExtensionLoaded(loadedExtensionNames, "mcc"))
			{
				importImpexFile(context, importRoot + "/cockpits/reportcockpit/reportcockpit-mcc-links.impex");
			}
		}
*/
	}

	protected void assignDependent(final String dependsOnProduct, final List<String> dependentContents)
	{
		if (CollectionUtils.isNotEmpty(dependentContents) && StringUtils.isNotBlank(dependsOnProduct))
		{
			final Set<String> dependentSyncJobsNames = new HashSet<String>();
			for (final String content : dependentContents)
			{
				dependentSyncJobsNames.add(content + "ContentCatalog");
			}

			getSetupSyncJobService().assignDependentSyncJobs(dependsOnProduct + "ProductCatalog", dependentSyncJobsNames);
		}

	}

	protected void importStoreLocations(final SystemSetupContext context, final String importDirectory, final String storeName)
	{
		logInfo(context, "Begin importing store [" + storeName + "]");

		final String importRoot = "/" + importDirectory + "/import";
		importImpexFile(context, importRoot + "/stores/" + storeName + "/points-of-service-media.impex", false);
		importImpexFile(context, importRoot + "/stores/" + storeName + "/points-of-service.impex", false);

		logInfo(context, "Done importing store [" + storeName + "]");
	}

	private void createStore(final SystemSetupContext context, final String storeName)
	{
		logInfo(context, "Begin importing store [" + storeName + "]");

		importImpexFile(context, "/pacoshopinitialdata/import/stores/" + storeName + "/store.impex");
		importImpexFile(context, "/pacoshopinitialdata/import/stores/" + storeName + "/site.impex");

		logInfo(context, "Done importing store [" + storeName + "]");
	}
}
