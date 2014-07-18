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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;


/**
 * This class provides hooks into the system's initialization and update processes.
 * 
 * @see "https://wiki.hybris.com/display/release4/Hooks+for+Initialization+and+Update+Process"
 */
@SystemSetup(extension = PacoshopCoreConstants.EXTENSIONNAME)
public class CoreSystemSetup extends AbstractSystemSetup
{
	public static final String IMPORT_SITES = "importSites";
	public static final String IMPORT_SYNC_CATALOGS = "syncProducts&ContentCatalogs";
	public static final String IMPORT_ACCESS_RIGHTS = "accessRights";
	public static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";

	//	public static final String ELECTRONICS = "electronics";
	//	public static final String APPAREL = "apparel";
	//	public static final String APPAREL_UK = "apparel-uk";
	//	public static final String APPAREL_DE = "apparel-de";

	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		importImpexFile(context, "/pacoshopcore/import/common/essential-data.impex");
		importImpexFile(context, "/pacoshopcore/import/common/countries.impex");
		importImpexFile(context, "/pacoshopcore/import/common/delivery-modes.impex");

		//	importImpexFile(context, "/pacoshopcore/import/common/themes.impex");
		importImpexFile(context, "/pacoshopcore/import/common/user-groups.impex");
	}

	/**
	 * Generates the Dropdown and Multi-select boxes for the project data import
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_SITES, "Import Sites", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SYNC_CATALOGS, "Sync Products & Content Catalogs", false));
		params.add(createBooleanSystemSetupParameter(IMPORT_ACCESS_RIGHTS, "Import Users & Groups", true));
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", false));

		return params;
	}

	/**
	 * This method will be called during the system initialization.
	 * 
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final boolean importSites = getBooleanSystemSetupParameter(context, IMPORT_SITES);
		final boolean importAccessRights = getBooleanSystemSetupParameter(context, IMPORT_ACCESS_RIGHTS);

		//		final ImportData apparelImportData = new ImportData();
		//		apparelImportData.setProductCatalogName(APPAREL);
		//		apparelImportData.setContentCatalogNames(Arrays.asList(APPAREL_UK, APPAREL_DE));
		//		apparelImportData.setStoreNames(Arrays.asList(APPAREL_UK, APPAREL_DE));
		//
		//		final ImportData electronicsImportData = new ImportData();
		//		electronicsImportData.setProductCatalogName(ELECTRONICS);
		//		electronicsImportData.setContentCatalogNames(Arrays.asList(ELECTRONICS));
		//		electronicsImportData.setStoreNames(Arrays.asList(ELECTRONICS));

		if (importSites)
		{
			//			new ImportCatalogAwareDataStrategy(context, electronicsImportData).importAllData();
			//			new ImportCatalogAwareDataStrategy(context, apparelImportData).importAllData();

			final ValidationService validation = getBeanForName("validationService");
			validation.reloadValidationEngine();
		}

		final List<String> extensionNames = getExtensionNames();

		if (importAccessRights && extensionNames.contains("cmscockpit"))
		{
			importImpexFile(context, "/pacoshopcore/import/cockpits/cmscockpit/cmscockpit-users.impex");
			importImpexFile(context, "/pacoshopcore/import/cockpits/cmscockpit/cmscockpit-access-rights.impex");
		}

		if (importAccessRights && extensionNames.contains("btgcockpit"))
		{
			importImpexFile(context, "/pacoshopcore/import/cockpits/cmscockpit/btgcockpit-users.impex");
			importImpexFile(context, "/pacoshopcore/import/cockpits/cmscockpit/btgcockpit-access-rights.impex");
		}

		if (importAccessRights && extensionNames.contains("productcockpit"))
		{
			importImpexFile(context, "/pacoshopcore/import/cockpits/productcockpit/productcockpit-users.impex");
			importImpexFile(context, "/pacoshopcore/import/cockpits/productcockpit/productcockpit-access-rights.impex");
			importImpexFile(context, "/pacoshopcore/import/cockpits/productcockpit/productcockpit-constraints.impex");
		}

		if (importAccessRights && extensionNames.contains("cscockpit"))
		{
			importImpexFile(context, "/pacoshopcore/import/cockpits/cscockpit/cscockpit-users.impex");
			importImpexFile(context, "/pacoshopcore/import/cockpits/cscockpit/cscockpit-access-rights.impex");
		}

		if (importAccessRights && extensionNames.contains("reportcockpit"))
		{
			importImpexFile(context, "/pacoshopcore/import/cockpits/reportcockpit/reportcockpit-users.impex");
			importImpexFile(context, "/pacoshopcore/import/cockpits/reportcockpit/reportcockpit-access-rights.impex");
		}

		if (importAccessRights && extensionNames.contains("reportcockpit-mcc"))
		{
			importImpexFile(context, "/pacoshopcore/import/cockpits/reportcockpit/reportcockpit-mcc-links.impex");
		}

		if (extensionNames.contains("mcc"))
		{
			importImpexFile(context, "/pacoshopcore/import/common/mcc-sites-links.impex");
		}

		// Send an event to notify any AddOns that the core data import is complete
		//		getEventService().publishEvent(new CoreDataImportedEvent(context, Arrays.asList(electronicsImportData, apparelImportData)));
	}


	protected List<String> getExtensionNames()
	{
		return Registry.getCurrentTenant().getTenantSpecificExtensionNames();
	}

	protected <T> T getBeanForName(final String name)
	{
		return (T) Registry.getApplicationContext().getBean(name);
	}


	/**
	 * Abstraction for a import process which should be adopted to use a dependent CatalogVersionSyncJob
	 */
	private class ImportCatalogAwareDataStrategy
	{
		private final SystemSetupContext context;
		private final ImportData importData;

		public ImportCatalogAwareDataStrategy(final SystemSetupContext context, final ImportData importData)
		{
			this.context = context;
			this.importData = importData;
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
			createProductCatalog(getProductCatalogName());//

			for (final String contentCatalogName : getContentCatalogNames())
			{

				createContentCatalog(contentCatalogName);
			}

			assignDependent(getProductCatalogName(), getContentCatalogNames());

			for (final String contentCatalogName : getContentCatalogNames())
			{

				if (isSyncCatalog())
				{
					syncContentCatalog(contentCatalogName);
				}
			}

			if (isSyncCatalog())
			{
				syncProductCatalog(getProductCatalogName());
			}

			for (final String storeName : getStoreNames())
			{
				createStore(storeName);
				createAndActivateSolrIndex(storeName);
			}
		}

		private void createAndActivateSolrIndex(final String storeName)
		{
			logInfo(context, "Begin SOLR index setup [" + storeName + "]");

			importImpexFile(context, "/pacoshopcore/import/stores/" + storeName + "/solr.impex");

			createSolrIndexerCronJobs(storeName + "Index");

			importImpexFile(context, "/pacoshopcore/import/stores/" + storeName + "/solrtrigger.impex");

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

			importImpexFile(context, "/pacoshopcore/import/stores/" + storeName + "/store.impex");
			importImpexFile(context, "/pacoshopcore/import/stores/" + storeName + "/site.impex");

			logInfo(context, "Done importing store [" + storeName + "]");
		}

		/**
		 * fires product catalog with synchronization with respect for a dependent CatalogVersionSyncJob
		 */
		private void syncProductCatalog(final String productCatalogName)
		{
			executeCatalogSyncJob(context, productCatalogName + "ProductCatalog");
		}

		/**
		 * fires content catalog with synchronization with respect for a depends on CatalogVersionSyncJob
		 * 
		 */
		private void syncContentCatalog(final String contentCatalogName)
		{
			executeCatalogSyncJob(context, contentCatalogName + "ContentCatalog");
		}

		private void assignDependent(final String dependsOnProduct, final List<String> dependentContents)
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

		private void createContentCatalog(final String contentCatalogName)
		{
			logInfo(context, "Begin importing catalog [" + contentCatalogName + "]");

			importImpexFile(context, "/pacoshopcore/import/contentCatalogs/" + contentCatalogName + "ContentCatalog/catalog.impex",
					true);
			importImpexFile(context, "/pacoshopcore/import/contentCatalogs/" + contentCatalogName
					+ "ContentCatalog/cms-content.impex", false);
			importImpexFile(context, "/pacoshopcore/import/contentCatalogs/" + contentCatalogName
					+ "ContentCatalog/cms-mobile-content.impex", false);
			importImpexFile(context, "/pacoshopcore/import/contentCatalogs/" + contentCatalogName
					+ "ContentCatalog/email-content.impex", false);

			createContentCatalogSyncJob(context, contentCatalogName + "ContentCatalog");

		}

		private void createProductCatalog(final String productCatalogName)
		{
			logInfo(context, "Begin importing catalog [" + productCatalogName + "]");

			importImpexFile(context, "/pacoshopcore/import/productCatalogs/" + productCatalogName + "ProductCatalog/catalog.impex",
					true);

			createProductCatalogSyncJob(context, productCatalogName + "ProductCatalog");
		}
	}
}
