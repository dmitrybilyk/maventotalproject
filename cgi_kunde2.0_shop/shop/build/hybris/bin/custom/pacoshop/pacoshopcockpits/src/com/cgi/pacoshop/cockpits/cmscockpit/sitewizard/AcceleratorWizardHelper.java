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
package com.cgi.pacoshop.cockpits.cmscockpit.sitewizard;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.catalog.model.SyncItemJobModel;
import de.hybris.platform.catalog.model.classification.ClassificationSystemModel;
import de.hybris.platform.catalog.model.synchronization.CatalogVersionSyncJobModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.contents.ContentCatalogModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.pages.PageTemplateModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.admin.CMSAdminPageService;
import de.hybris.platform.cmscockpit.services.GenericRandomNameProducer;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Creates new site from given information. Intended for usage within accelerator cms site wizard.
 *
 * @module pacoshopcore
 * @version 1.0v Feb 05, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Maksim Panov <maksim.panov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  'https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@SuppressFBWarnings({ "DM_DEFAULT_ENCODING" })
public class AcceleratorWizardHelper
{
	private static final Logger LOG = Logger.getLogger(AcceleratorWizardHelper.class);

	private static final String UTF_8    = "UTF-8";
	private static final String NEWLINE  = "\r\n";
	private static final String HOMEPAGE = "homepage";
	private static final String STAGED   = "staged";

	private ModelService              modelService;
	private ImportService             importService;
	private CMSAdminPageService       cmsAdminPageService;
	private CatalogVersionService     catalogVersionService;
	private GenericRandomNameProducer uidGenerator;

	/**
	 * Header of impex file used for creating content pages for current (new) content catalog.
	 */
	private final String bshHeader = NEWLINE
			  + "$contentCV=catalogVersion(CatalogVersion.catalog("
			  + "Catalog.id[default=$contentCatalog]),CatalogVersion.version[default="
			  + STAGED
			  + "])[default=$contentCatalog:"
			  + STAGED
			  + "]"
			  + NEWLINE
			  + "$jarResource=jar:com.cgi.pacoshop.core.setup.CoreSystemSetup&"
			  + NEWLINE
			  + "\"#% import com.cgi.pacoshop.cockpits.cmscockpit.sitewizard.AcceleratorWizardHelper\";"
			  + NEWLINE
			  + "\"#% impex.enableExternalImpExSyntaxParsing( true )\";"
			  + NEWLINE
			  + "\"#% impex.enableExternalDataCodeExecution( true )\";"
			  + NEWLINE
			  + "\"#% impex.includeExternalData(AcceleratorWizardHelper.class.getResourceAsStream("
			  + "\"\"/pacoshopcockpits/cmscockpit/import/wizard_sample_pages.impex\"\"), \"\"utf-8\"\", 0, 0 );\";"
			  + NEWLINE;

	/**
	 * Create site.
	 *
	 * @param context context
	 * @return CMSSiteModel cMS site model
	 * @throws Exception exception
	 */
	public CMSSiteModel createSite(final Map<String, Object> context) throws Exception
	{
		final CMSSiteModel item = (CMSSiteModel) context.get("item");
		final UserModel user = (UserModel) context.get("user");

		final List<PageTemplateModel> templates = (List) context.get("templates");
		final String contentCatalogName = (String) context.get("contentcatalogname");
		final List<ContentCatalogModel> selectedContentCatalogs = (List) context.get("selectedcontentcatalogs");

		final String siteUid = item.getUid();

		try
		{
			item.setPreviewURL(getStorefrontContextRoot() + "/?site=" + siteUid);

			// Default site regex match patterns
			// (?i)^https?://[^/]+(/[^?]*)?\?(.*\&)?(site=$siteUid)(|\&.*)$
			final String pattern1 = "(?i)^https?://[^/]+(/[^?]*)?\\?(.*\\&)?(site=" + siteUid + ")(|\\&.*)$";
			// (?i)^https?://$siteUid\.[^/]+(|/.*|\?.*)$
			final String pattern2 = "(?i)^https?://" + siteUid + "\\.[^/]+(|/.*|\\?.*)$";

			item.setUrlPatterns(Arrays.asList(pattern1, pattern2));

			CatalogModel cat = null;
			for (final CatalogModel catalog : item.getStores().iterator().next().getCatalogs())
			{
				if (!(catalog instanceof ClassificationSystemModel))
				{
					cat = catalog;
					break;
				}
			}

			if (cat != null)
			{
				item.setDefaultCatalog(cat);
				item.setDefaultPreviewCatalog(cat);
				if (!cat.getRootCategories().isEmpty())
				{
					final CategoryModel category = cat.getRootCategories().iterator().next();
					item.setDefaultPreviewCategory(category);

					final ProductModel defaultPreviewProduct = findDefaultProductPreview(cat);
					item.setDefaultPreviewProduct(defaultPreviewProduct);
					if (item.getDefaultPreviewProduct() == null)
					{
						LOG.warn("Cannot set default preview product for CMSSite: " + item.getName());
					}
				}
				else
				{
					LOG.warn("Cannot set default preview category for CMSSite: " + item.getName());
				}
			}
			setLanguageAndLocale(item);

			if (StringUtils.isNotBlank(contentCatalogName))
			{
				// we have to create a new content catalog
				final ContentCatalogModel contentCatalog = createNewContentCatalog(contentCatalogName, user, item);

				// add sample pages for each template
				createSamplePages(contentCatalog, item, templates);

				// synchronize
				final List<SyncItemJobModel> synchronizations = getStagedVersion(contentCatalog).getSynchronizations();
				if (CollectionUtils.isNotEmpty(synchronizations))
				{
					CMSSiteUtils.synchronizeCatVersions((CatalogVersionSyncJobModel) synchronizations.get(0), getModelService());
				}
			}
			else if (!selectedContentCatalogs.isEmpty())
			{
				setHomePage(item, selectedContentCatalogs);
			}
			item.setChannel(getSiteChannel());
			getModelService().save(item);
			return item;
		}
		catch (final Exception ex)
		{
			LOG.error("Failed to createSite. siteUid [" + siteUid + "] contentCatalogName [" + contentCatalogName + "]", ex);
			throw ex;
		}
	}

	private ProductModel findDefaultProductPreview(final CatalogModel catalogModel)
	{
		ProductModel product = null;
		final List<CategoryModel> rootCategories = catalogModel.getRootCategories();
		for (final CategoryModel category : rootCategories)
		{
			product = findRecursivelyWhatsoeverVisibleProductInCategories(category);
			if (product != null)
			{
				break;
			}
		}
		return product;
	}

	private ProductModel findRecursivelyWhatsoeverVisibleProductInCategories(final CategoryModel category)
	{
		if (!category.getProducts().isEmpty())
		{
			for (final ProductModel p : category.getProducts())
			{
				if (p.getOnlineDate() == null && p.getApprovalStatus().equals(ArticleApprovalStatus.APPROVED))
				{
					return p;
				}
			}
		}
		ProductModel product = null;

		for (final CategoryModel subCategories : category.getCategories())
		{
			product = findRecursivelyWhatsoeverVisibleProductInCategories(subCategories);
			if (product != null)
			{
				break;
			}
		}
		return product;
	}


	/**
	 * Gets the site channel (B2C).
	 *
	 * @return site channel
	 */
	protected SiteChannel getSiteChannel()
	{
		return SiteChannel.B2C;
	}

	/**
	 * Gets the storefront context root.
	 *
	 * @return path to the storefront context root.
	 */
	protected String getStorefrontContextRoot()
	{
		return Config.getString("storefrontContextRoot", "/acceleratorstorefront");
	}

	/**
	 * Retrieves first site from first selected store and copies it's language and locale to this new site.
	 * @param item CMSSiteModel
	 */
	protected void setLanguageAndLocale(final CMSSiteModel item)
	{
		final List<BaseStoreModel> stores = item.getStores();
		if (!stores.isEmpty())
		{
			final BaseStoreModel baseStoreModel = stores.get(0);
			final Collection<BaseSiteModel> cmsSites = baseStoreModel.getCmsSites();
			if (!cmsSites.isEmpty())
			{
				final Object abstractSite = cmsSites.iterator().next();
				if (abstractSite instanceof CMSSiteModel)
				{
					final CMSSiteModel sourceSite = (CMSSiteModel) abstractSite;
					item.setDefaultLanguage(sourceSite.getDefaultLanguage());
					item.setLocale(sourceSite.getLocale());
					getModelService().save(item);
				}
			}
		}
	}

	/**
	 * Retrieves homepage from existing content catalog and set it as homepage of this new site.
	 * @param item CMSSiteModel
	 * @param selectedContentCatalogs content catalogs
	 */
	@SuppressFBWarnings({ "DM_DEFAULT_ENCODING" })
	protected void setHomePage(final CMSSiteModel item, final List<ContentCatalogModel> selectedContentCatalogs)
	{
		item.setContentCatalogs(selectedContentCatalogs);
		final List<CatalogVersionModel> activeCatalogVersions = new ArrayList<CatalogVersionModel>();
		for (final CatalogModel selectedCatalogModel : selectedContentCatalogs)
		{
			activeCatalogVersions.add(selectedCatalogModel.getActiveCatalogVersion());
		}
		final Collection<ContentPageModel> pages = getCmsAdminPageService().getContentPages(activeCatalogVersions, HOMEPAGE);
		if (!pages.isEmpty())
		{
			item.setStartingPage(pages.iterator().next());
		}
		getModelService().save(item);
	}

	/**
	 * Creates sample pages.
	 *
	 * @param contentCatalog content catalog to create sample pages in
	 * @param item CMSSiteModel
	 * @param templates page templates
	 */
	protected void createSamplePages(final ContentCatalogModel contentCatalog, final CMSSiteModel item,
												final List<PageTemplateModel> templates)
	{
		/* home page */
		final Set<CatalogVersionModel> stagedVersion = Collections.singleton(getStagedVersion(contentCatalog));
		CMSSiteUtils.populateCmsSite(templates, stagedVersion, contentCatalog, item, HOMEPAGE, HOMEPAGE);

		/* pages for all selected templates page */
		final String header = "$contentCatalog=" + contentCatalog.getId() + bshHeader;
		final InputStream resourceAsStream = new ByteArrayInputStream(header.getBytes());
		final ImpExResource resource = new StreamBasedImpExResource(resourceAsStream, UTF_8);
		getImportService().importData(resource);
	}

	/**
	 * Gets the staged version of catalog by content catalog model.
	 *
	 * @param contentCatalog Content catalog model
	 * @return Staged version of catalog
	 */
	protected CatalogVersionModel getStagedVersion(final ContentCatalogModel contentCatalog)
	{
		final Set<CatalogVersionModel> catalogVersions = contentCatalog.getCatalogVersions();
		for (final CatalogVersionModel catalogVersionModel : catalogVersions)
		{
			if (STAGED.equals(catalogVersionModel.getVersion()))
			{
				return catalogVersionModel;
			}
		}
		return null; // should never happen since staged was created within this
		// class
	}

	/**
	 * Creates new content catalog.
	 *
	 * @param contentCatalogName name
	 * @param user user
	 * @param item CMSSiteModel
	 * @return Created content catalog model
	 */
	protected ContentCatalogModel createNewContentCatalog(final String contentCatalogName, final UserModel user,
																			final CMSSiteModel item)
	{
		final ContentCatalogModel contentCatalog = modelService.create("ContentCatalog");
		contentCatalog.setId(getUidGenerator().generateSequence("ContentCatalog", "content_catalog"));
		contentCatalog.setName(contentCatalogName);

		final Set<CatalogVersionModel> versions = new HashSet<CatalogVersionModel>();

		final CatalogVersionModel catVerOnline = modelService.create("CatalogVersion");
		catVerOnline.setVersion("online");
		catVerOnline.setCatalog(contentCatalog);
		catVerOnline.setActive(Boolean.TRUE);
		contentCatalog.setActiveCatalogVersion(catVerOnline);

		final List<PrincipalModel> principals = new ArrayList<PrincipalModel>();
		if (catVerOnline.getWritePrincipals() != null)
		{
			principals.addAll(catVerOnline.getWritePrincipals());
		}
		principals.add(user);
		catVerOnline.setWritePrincipals(principals);
		versions.add(catVerOnline);

		final CatalogVersionModel catVerStaged = modelService.create("CatalogVersion");
		catVerStaged.setVersion(STAGED);
		catVerStaged.setCatalog(contentCatalog);
		catVerStaged.setWritePrincipals(principals);
		versions.add(catVerStaged);

		contentCatalog.setCatalogVersions(versions);
		contentCatalog.setCmsSites(Collections.singletonList(item));
		modelService.saveAll(catVerStaged, catVerOnline, contentCatalog);

		final String syncJobCode = contentCatalogName + "(" + contentCatalog.getId() + ")" + ":" + catVerStaged.getVersion() + "->"
				  + contentCatalogName + "(" + contentCatalog.getId() + ")" + ":" + catVerOnline.getVersion();
		final SyncItemJobModel catalogVersionSyncJob = CMSSiteUtils.createDefaultSyncJob(syncJobCode, catVerStaged, catVerOnline);

		catVerStaged.setSynchronizations(Collections.singletonList(catalogVersionSyncJob));
		modelService.save(catVerStaged);

		final Set<CatalogVersionModel> sessionCatalogVersions = new HashSet<CatalogVersionModel>();
		if (!getCatalogVersionService().getSessionCatalogVersions().isEmpty())
		{
			sessionCatalogVersions.addAll(getCatalogVersionService().getSessionCatalogVersions());
		}
		sessionCatalogVersions.addAll(versions);
		getCatalogVersionService().setSessionCatalogVersions(sessionCatalogVersions);

		modelService.refresh(contentCatalog);

		// clone templates
		item.setContentCatalogs(Collections.singletonList(contentCatalog));
		return contentCatalog;
	}

	/**
	 * Getter for ModelService.
	 *
	 * @return model service
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * Setter for ModelService.
	 *
	 * @param modelService ModelService object.
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * Gets cms admin page service.
	 *
	 * @return the cms admin page service
	 */
	protected CMSAdminPageService getCmsAdminPageService()
	{
		return cmsAdminPageService;
	}

	/**
	 * Sets cms admin page service.
	 *
	 * @param cmsAdminPageService the cms admin page service
	 */
	@Required
	public void setCmsAdminPageService(final CMSAdminPageService cmsAdminPageService)
	{
		this.cmsAdminPageService = cmsAdminPageService;
	}

	/**
	 * Gets uid generator.
	 *
	 * @return the uid generator
	 */
	protected GenericRandomNameProducer getUidGenerator()
	{
		return uidGenerator;
	}

	/**
	 * Sets uid generator.
	 *
	 * @param uidGenerator the uid generator
	 */
	@Required
	public void setUidGenerator(final GenericRandomNameProducer uidGenerator)
	{
		this.uidGenerator = uidGenerator;
	}

	/**
	 * Gets catalog version service.
	 *
	 * @return the catalog version service
	 */
	protected CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * Sets catalog version service.
	 *
	 * @param catalogVersionService the catalog version service
	 */
	@Required
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * Gets import service.
	 *
	 * @return the import service
	 */
	protected ImportService getImportService()
	{
		return importService;
	}

	/**
	 * Sets import service.
	 *
	 * @param importService the import service
	 */
	@Required
	public void setImportService(final ImportService importService)
	{
		this.importService = importService;
	}
}
