/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import de.hybris.platform.jalo.order.payment.PaymentMode;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.store.BaseStore;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.StorePaymentModeFee StorePaymentModeFee}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedStorePaymentModeFee extends GenericItem
{
	/** Qualifier of the <code>StorePaymentModeFee.amount</code> attribute **/
	public static final String AMOUNT = "amount";
	/** Qualifier of the <code>StorePaymentModeFee.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>StorePaymentModeFee.store</code> attribute **/
	public static final String STORE = "store";
	/** Qualifier of the <code>StorePaymentModeFee.paymentMode</code> attribute **/
	public static final String PAYMENTMODE = "paymentMode";
	/** Qualifier of the <code>StorePaymentModeFee.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n STORE's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedStorePaymentModeFee> STOREHANDLER = new BidirectionalOneToManyHandler<GeneratedStorePaymentModeFee>(
	PacoshopCoreConstants.TC.STOREPAYMENTMODEFEE,
	false,
	"store",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(AMOUNT, AttributeMode.INITIAL);
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(STORE, AttributeMode.INITIAL);
		tmp.put(PAYMENTMODE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.amount</code> attribute.
	 * @return the amount
	 */
	public Double getAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, AMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.amount</code> attribute.
	 * @return the amount
	 */
	public Double getAmount()
	{
		return getAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.amount</code> attribute. 
	 * @return the amount
	 */
	public double getAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.amount</code> attribute. 
	 * @return the amount
	 */
	public double getAmountAsPrimitive()
	{
		return getAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.amount</code> attribute. 
	 * @param value the amount
	 */
	public void setAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, AMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.amount</code> attribute. 
	 * @param value the amount
	 */
	public void setAmount(final Double value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.amount</code> attribute. 
	 * @param value the amount
	 */
	public void setAmount(final SessionContext ctx, final double value)
	{
		setAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.amount</code> attribute. 
	 * @param value the amount
	 */
	public void setAmount(final double value)
	{
		setAmount( getSession().getSessionContext(), value );
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		STOREHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final SessionContext ctx)
	{
		return (Currency)getProperty( ctx, CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final Currency value)
	{
		setProperty(ctx, CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final Currency value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.description</code> attribute.
	 * @return the description
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.paymentMode</code> attribute.
	 * @return the paymentMode
	 */
	public PaymentMode getPaymentMode(final SessionContext ctx)
	{
		return (PaymentMode)getProperty( ctx, PAYMENTMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.paymentMode</code> attribute.
	 * @return the paymentMode
	 */
	public PaymentMode getPaymentMode()
	{
		return getPaymentMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.paymentMode</code> attribute. 
	 * @param value the paymentMode
	 */
	public void setPaymentMode(final SessionContext ctx, final PaymentMode value)
	{
		setProperty(ctx, PAYMENTMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.paymentMode</code> attribute. 
	 * @param value the paymentMode
	 */
	public void setPaymentMode(final PaymentMode value)
	{
		setPaymentMode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.store</code> attribute.
	 * @return the store
	 */
	public BaseStore getStore(final SessionContext ctx)
	{
		return (BaseStore)getProperty( ctx, STORE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>StorePaymentModeFee.store</code> attribute.
	 * @return the store
	 */
	public BaseStore getStore()
	{
		return getStore( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.store</code> attribute. 
	 * @param value the store
	 */
	public void setStore(final SessionContext ctx, final BaseStore value)
	{
		STOREHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>StorePaymentModeFee.store</code> attribute. 
	 * @param value the store
	 */
	public void setStore(final BaseStore value)
	{
		setStore( getSession().getSessionContext(), value );
	}
	
}
