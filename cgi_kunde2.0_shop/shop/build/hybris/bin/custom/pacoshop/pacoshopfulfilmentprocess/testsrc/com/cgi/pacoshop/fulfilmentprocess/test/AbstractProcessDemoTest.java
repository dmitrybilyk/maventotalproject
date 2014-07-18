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
package com.cgi.pacoshop.fulfilmentprocess.test;

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.basecommerce.enums.StockLevelUpdateType;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.enums.CreditCardType;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.orderhistory.OrderHistoryService;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.VendorModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.dto.CardInfo;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.stock.model.StockLevelHistoryEntryModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;


/**
 * OOTB test.
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @see "https://wiki.hybris.com/"
 * @copyright 2014 symmetrics - a CGI Group brand
 */
@Ignore
public abstract class AbstractProcessDemoTest extends ServicelayerTest
{

	private static final int TEN    = 10;
	private static final int FIVE   = 5;
	private static final int TWELVE = 12;

	@Resource
	private BusinessProcessService businessProcessService;
	@Resource
	private CatalogService         catalogService;
	@Resource
	private CartService            cartService;
	@Resource
	private ProductService         productService;
	@Resource
	private OrderService           orderService;
	@Resource
	private UserService            userService;
	@Resource
	private ModelService           modelService;
	@Resource
	private OrderHistoryService    orderHistoryService;
	@Resource
	private I18NService            i18nService;
	@Resource
	private PaymentService         paymentService;
	@Resource
	private UnitService            unitService;

	/**
	 * Setup.
	 */
	@Before
	public void setUp()
	{
		setupCatalog();
		setupUser();
		setCurrentCatalog();
	}

	/**
	 * SetupCatalog.
	 */
	protected void setupCatalog()
	{
		final CatalogModel catalog = createCatalog("test");
		final CatalogVersionModel catalogVersion = createCatalogVersion(catalog, "online", Boolean.TRUE);
		final UnitModel pieces = getOrCreateUnit("pieces");
		final CurrencyModel currency = createOrGetCurrency("EUR");
		final VendorModel vendor = createVendor("testVendor");
		final WarehouseModel warehouse1 = createWarehouse("testWarehouse1", vendor);
		final WarehouseModel warehouse2 = createWarehouse("testWarehouse2", vendor);

		for (int i = 1; i <= TEN; i++)
		{
			final String code = String.format("product%02d", Integer.valueOf(i));
			final String name = String.format("Product %02d", Integer.valueOf(i));
			final ProductModel product = createProduct(catalogVersion, code, name);

			WarehouseModel warehouse;
			if (i <= FIVE)
			{
				warehouse = warehouse1;
			}
			else
			{
				warehouse = warehouse2;
			}
			final StockLevelModel stockLevel = this.createStockLevel(product, warehouse, 10);
			modelService.save(stockLevel);

			createPriceRow(currency, Double.valueOf(i), pieces, product, catalogVersion);
		}
		modelService.saveAll();
	}


	private CatalogModel createCatalog(final String id)
	{
		final CatalogModel catalog = new CatalogModel();
		catalog.setId(id);
		modelService.save(catalog);
		return catalog;
	}

	private CatalogVersionModel createCatalogVersion(final CatalogModel catalog, final String version, final Boolean active)
	{
		final CatalogVersionModel catalogVersion = new CatalogVersionModel();
		catalogVersion.setCatalog(catalog);
		catalogVersion.setVersion(version);
		catalogVersion.setActive(active);
		modelService.save(catalogVersion);
		return catalogVersion;
	}

	private UnitModel getOrCreateUnit(final String code)
	{
		UnitModel unitItem;
		try
		{
			unitItem = unitService.getUnitForCode(code);
		}
		catch (final UnknownIdentifierException uie)
		{
			unitItem = new UnitModel();
			unitItem.setCode(code);
			unitItem.setUnitType(code);
			modelService.save(unitItem);
		}

		return unitItem;
	}

	@SuppressWarnings("deprecation")
	private CurrencyModel createOrGetCurrency(final String isoCode)
	{
		try
		{
			return i18nService.getCurrency(isoCode);
		}
		catch (final UnknownIdentifierException e)
		{
			final CurrencyModel currency = modelService.create(CurrencyModel.class);
			currency.setIsocode(isoCode);
			currency.setSymbol(isoCode);
			currency.setBase(Boolean.TRUE);
			currency.setActive(Boolean.TRUE);
			currency.setConversion(Double.valueOf(1));
			modelService.save(currency);
			return currency;
		}
	}

	private VendorModel createVendor(final String code)
	{
		final VendorModel vendor = new VendorModel();
		vendor.setCode(code);
		modelService.save(vendor);
		return vendor;
	}

	private WarehouseModel createWarehouse(final String code, final VendorModel vendor)
	{
		final WarehouseModel warehouse = new WarehouseModel();
		warehouse.setCode(code);
		warehouse.setVendor(vendor);
		modelService.save(warehouse);
		return warehouse;
	}

	private ProductModel createProduct(final CatalogVersionModel catalogVersion, final String code, final String name)
	{
		final ProductModel product = modelService.create(ProductModel.class);
		product.setCatalogVersion(catalogVersion);
		product.setCode(code);
		product.setName(name);
		modelService.save(product);
		return product;
	}

	private PriceRowModel createPriceRow(final CurrencyModel currency, final Double price, final UnitModel unit,
													 final ProductModel product, final CatalogVersionModel catalogVersion)
	{
		final PriceRowModel priceRow = new PriceRowModel();
		priceRow.setCurrency(currency);
		priceRow.setPrice(price);
		priceRow.setUnit(unit);
		priceRow.setNet(Boolean.TRUE);
		priceRow.setProduct(product);
		priceRow.setCatalogVersion(catalogVersion);
		modelService.save(priceRow);

		return priceRow;
	}


	/**
	 * Set current catalog.
	 */
	@SuppressWarnings("deprecation")
	protected void setCurrentCatalog()
	{
		catalogService.setSessionCatalogVersion("test", "online");
	}

	/**
	 * SetupUser.
	 *
	 * @param uId
	 *            the uid.
	 * @param name
	 *            the name.
	 */
	protected void setupUser(final String uId, final String name)
	{
		final UserModel user = new UserModel();
		user.setUid(uId);
		user.setName(name);
		modelService.save(user);
		userService.setCurrentUser(user);
	}

	/**
	 * Setup user.
	 */
	protected void setupUser()
	{
		setupUser("testUser", null);
	}

	/**
	 * Create process.
	 *
	 * @param order
	 *            the order.
	 * @return the businessProcess.
	 */
	protected BusinessProcessModel createProcess(final OrderModel order)
	{
		final OrderProcessModel process = (OrderProcessModel) businessProcessService.createProcess(
				  "test" + System.currentTimeMillis(), PacoshopFulfilmentProcessConstants.ORDER_PROCESS_NAME);
		process.setOrder(order);
		modelService.saveAll(process, order);
		return process;
	}

	/**
	 * Create a test order.
	 *
	 * @param paymentAddress
	 *            the paymentAddress.
	 * @param email
	 *            the email.
	 * @return the order.
	 * @throws InvalidCartException
	 *             if something bad happened.
	 */
	@SuppressWarnings("deprecation")
	protected OrderModel createOrder(final AddressModel paymentAddress, final String email) throws InvalidCartException
	{
		final UnitModel pieces = productService.getUnit("pieces");
		final ProductModel product1 = productService.getProduct("product01");
		final ProductModel product2 = productService.getProduct("product02");
		final CartModel cart = cartService.getSessionCart();
		cartService.addToCart(cart, product1, 1, pieces);
		cartService.addToCart(cart, product2, 2, pieces);

		final CountryModel country = modelService.create(CountryModel.class);
		country.setName("Germany");
		country.setIsocode("DEU");
		modelService.save(country);

		final AddressModel address = new AddressModel();
		address.setTown("Town");
		address.setLine1("Street");
		address.setLine2("2");
		address.setPostalcode("12333");
		address.setLastname("hybris");
		address.setFirstname("hybris");
		address.setEmail(email);
		address.setCountry(country);
		final CreditCardPaymentInfoModel paymentInfo = new CreditCardPaymentInfoModel();
		paymentInfo.setCcOwner("test");
		paymentInfo.setType(CreditCardType.VISA);
		paymentInfo.setNumber("4111-1111-1111-1111");
		paymentInfo.setValidToMonth("10");
		paymentInfo.setValidToYear("1812");
		final OrderModel order = orderService.placeOrder(cart, address, paymentAddress, paymentInfo);


		final CardInfo card = new CardInfo();
		card.setCardType(CreditCardType.VISA);
		card.setCardNumber("4111-1111-1111-1111");
		card.setExpirationMonth(Integer.valueOf(TWELVE));
		card.setExpirationYear(Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR) + 2));

		final PaymentTransactionModel paymentTransaction = paymentService.authorize("code1", BigDecimal.ONE,
																											 Currency.getInstance("EUR"), address, address,
																											 card).getPaymentTransaction();
		order.setPaymentTransactions(Collections.singletonList(paymentTransaction));
		modelService.save(order);

		return order;
	}

	/**
	 * Create order.
	 *
	 * @return an order.
	 * @throws InvalidCartException
	 *             if something bad happened.
	 */
	protected OrderModel createOrder() throws InvalidCartException
	{
		return createOrder(null, "hybris@hybris.de");
	}

	/**
	 * Create stock level.
	 *
	 * @param product
	 *            the product.
	 * @param warehouse
	 *            the warehouse.
	 * @param available
	 *            how much are available.
	 * @return the stock level.
	 */
	protected StockLevelModel createStockLevel(final ProductModel product, final WarehouseModel warehouse, final int available)
	{
		final StockLevelModel stockLevel = this.createStockLevel(product, warehouse, available, 0, 0, InStockStatus.NOTSPECIFIED,
																					-1, true);
		return stockLevel;
	}

	/**
	 * Create a stock level.
	 *
	 * @param product
	 *            the product.
	 * @param warehouse
	 *            the warehouse.
	 * @param available
	 *            how much to create.
	 * @param overSelling
	 *            how much we oversell.
	 * @param reserved
	 *            how much to reserve.
	 * @param status
	 *            the status.
	 * @param maxStockLevelHistoryCount
	 *            the max history count.
	 * @param treatNegativeAsZero
	 *            a flag.
	 * @return a stock level.
	 */
	protected StockLevelModel createStockLevel(final ProductModel product, final WarehouseModel warehouse, final int available,
															 final int overSelling, final int reserved, final InStockStatus status,
															 final int maxStockLevelHistoryCount,
															 final boolean treatNegativeAsZero)
	{
		//create the new stock level
		final StockLevelModel stockLevel = modelService.create(StockLevelModel.class);
		stockLevel.setProductCode(product.getCode());
		stockLevel.setWarehouse(warehouse);
		stockLevel.setAvailable(available);
		stockLevel.setOverSelling(overSelling);
		stockLevel.setReserved(reserved);
		stockLevel.setInStockStatus(status);
		stockLevel.setMaxStockLevelHistoryCount(maxStockLevelHistoryCount);
		stockLevel.setTreatNegativeAsZero(treatNegativeAsZero);
		//create the first history entry
		final List<StockLevelHistoryEntryModel> historyEntries = new ArrayList<StockLevelHistoryEntryModel>();
		final StockLevelHistoryEntryModel entry = this.createStockLevelHistoryEntry(stockLevel, available, 0,
																											 StockLevelUpdateType.WAREHOUSE, "new in stock");
		historyEntries.add(entry);
		stockLevel.setStockLevelHistoryEntries(historyEntries);
		modelService.save(stockLevel);
		return stockLevel;
	}

	/**
	 * Create a stock level history entry.
	 *
	 * @param stockLevel
	 *            the stock level.
	 * @param actual
	 *            the number of actual.
	 * @param reserved
	 *            the number of reserved.
	 * @param updateType
	 *            the update type.
	 * @param comment
	 *            a deep an meaningful comment.
	 * @return a stock level history entry model.
	 */
	protected StockLevelHistoryEntryModel createStockLevelHistoryEntry(final StockLevelModel stockLevel, final int actual,
																							 final int reserved, final StockLevelUpdateType updateType,
																							 final String comment)
	{
		final StockLevelHistoryEntryModel historyEntry = modelService.create(StockLevelHistoryEntryModel.class);
		historyEntry.setStockLevel(stockLevel);
		historyEntry.setActual(actual);
		historyEntry.setReserved(reserved);
		historyEntry.setUpdateType(updateType);
		if (comment != null)
		{
			historyEntry.setComment(comment);
		}
		historyEntry.setUpdateDate(new Date());
		modelService.save(historyEntry);
		return historyEntry;
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *            the businessProcessService to set
	 */
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the catalogService
	 */
	public CatalogService getCatalogService()
	{
		return catalogService;
	}

	/**
	 * @param catalogService
	 *            the catalogService to set
	 */
	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *            the cartService to set
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	/**
	 * @param productService
	 *            the productService to set
	 */
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	/**
	 * @return the orderService
	 */
	public OrderService getOrderService()
	{
		return orderService;
	}

	/**
	 * @param orderService
	 *            the orderService to set
	 */
	public void setOrderService(final OrderService orderService)
	{
		this.orderService = orderService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *            the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the orderHistoryService
	 */
	public OrderHistoryService getOrderHistoryService()
	{
		return orderHistoryService;
	}

	/**
	 * @param orderHistoryService
	 *            the orderHistoryService to set
	 */
	public void setOrderHistoryService(final OrderHistoryService orderHistoryService)
	{
		this.orderHistoryService = orderHistoryService;
	}

	/**
	 * @return the i18nService
	 */
	public I18NService getI18nService()
	{
		return i18nService;
	}

	/**
	 * @param i18nService
	 *            the i18nService to set
	 */
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}

	/**
	 * @return the paymentService
	 */
	public PaymentService getPaymentService()
	{
		return paymentService;
	}

	/**
	 * @param paymentService
	 *            the paymentService to set
	 */
	public void setPaymentService(final PaymentService paymentService)
	{
		this.paymentService = paymentService;
	}

	/**
	 * @return the unitService
	 */
	public UnitService getUnitService()
	{
		return unitService;
	}

	/**
	 * @param unitService
	 *            the unitService to set
	 */
	public void setUnitService(final UnitService unitService)
	{
		this.unitService = unitService;
	}

}
