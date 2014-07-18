/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.subscriptionservices.jalo.BillingFrequency;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem BillingFrequencyDiscount}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBillingFrequencyDiscount extends GenericItem
{
	/** Qualifier of the <code>BillingFrequencyDiscount.discountPercents</code> attribute **/
	public static final String DISCOUNTPERCENTS = "discountPercents";
	/** Qualifier of the <code>BillingFrequencyDiscount.billingFrequency</code> attribute **/
	public static final String BILLINGFREQUENCY = "billingFrequency";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(DISCOUNTPERCENTS, AttributeMode.INITIAL);
		tmp.put(BILLINGFREQUENCY, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingFrequencyDiscount.billingFrequency</code> attribute.
	 * @return the billingFrequency
	 */
	public BillingFrequency getBillingFrequency(final SessionContext ctx)
	{
		return (BillingFrequency)getProperty( ctx, BILLINGFREQUENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingFrequencyDiscount.billingFrequency</code> attribute.
	 * @return the billingFrequency
	 */
	public BillingFrequency getBillingFrequency()
	{
		return getBillingFrequency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingFrequencyDiscount.billingFrequency</code> attribute. 
	 * @param value the billingFrequency
	 */
	public void setBillingFrequency(final SessionContext ctx, final BillingFrequency value)
	{
		setProperty(ctx, BILLINGFREQUENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingFrequencyDiscount.billingFrequency</code> attribute. 
	 * @param value the billingFrequency
	 */
	public void setBillingFrequency(final BillingFrequency value)
	{
		setBillingFrequency( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute.
	 * @return the discountPercents
	 */
	public Integer getDiscountPercents(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, DISCOUNTPERCENTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute.
	 * @return the discountPercents
	 */
	public Integer getDiscountPercents()
	{
		return getDiscountPercents( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute. 
	 * @return the discountPercents
	 */
	public int getDiscountPercentsAsPrimitive(final SessionContext ctx)
	{
		Integer value = getDiscountPercents( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute. 
	 * @return the discountPercents
	 */
	public int getDiscountPercentsAsPrimitive()
	{
		return getDiscountPercentsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute. 
	 * @param value the discountPercents
	 */
	public void setDiscountPercents(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, DISCOUNTPERCENTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute. 
	 * @param value the discountPercents
	 */
	public void setDiscountPercents(final Integer value)
	{
		setDiscountPercents( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute. 
	 * @param value the discountPercents
	 */
	public void setDiscountPercents(final SessionContext ctx, final int value)
	{
		setDiscountPercents( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingFrequencyDiscount.discountPercents</code> attribute. 
	 * @param value the discountPercents
	 */
	public void setDiscountPercents(final int value)
	{
		setDiscountPercents( getSession().getSessionContext(), value );
	}
	
}
