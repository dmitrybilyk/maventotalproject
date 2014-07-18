/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.jalo.Bonus;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Currency;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.CashBonus CashBonus}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCashBonus extends Bonus
{
	/** Qualifier of the <code>CashBonus.currency</code> attribute **/
	public static final String CURRENCY = "currency";
	/** Qualifier of the <code>CashBonus.bonusAmount</code> attribute **/
	public static final String BONUSAMOUNT = "bonusAmount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Bonus.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CURRENCY, AttributeMode.INITIAL);
		tmp.put(BONUSAMOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CashBonus.bonusAmount</code> attribute.
	 * @return the bonusAmount
	 */
	public Double getBonusAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, BONUSAMOUNT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CashBonus.bonusAmount</code> attribute.
	 * @return the bonusAmount
	 */
	public Double getBonusAmount()
	{
		return getBonusAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CashBonus.bonusAmount</code> attribute. 
	 * @return the bonusAmount
	 */
	public double getBonusAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getBonusAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CashBonus.bonusAmount</code> attribute. 
	 * @return the bonusAmount
	 */
	public double getBonusAmountAsPrimitive()
	{
		return getBonusAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CashBonus.bonusAmount</code> attribute. 
	 * @param value the bonusAmount
	 */
	public void setBonusAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, BONUSAMOUNT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CashBonus.bonusAmount</code> attribute. 
	 * @param value the bonusAmount
	 */
	public void setBonusAmount(final Double value)
	{
		setBonusAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CashBonus.bonusAmount</code> attribute. 
	 * @param value the bonusAmount
	 */
	public void setBonusAmount(final SessionContext ctx, final double value)
	{
		setBonusAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CashBonus.bonusAmount</code> attribute. 
	 * @param value the bonusAmount
	 */
	public void setBonusAmount(final double value)
	{
		setBonusAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CashBonus.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency(final SessionContext ctx)
	{
		return (Currency)getProperty( ctx, CURRENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CashBonus.currency</code> attribute.
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return getCurrency( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CashBonus.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final SessionContext ctx, final Currency value)
	{
		setProperty(ctx, CURRENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CashBonus.currency</code> attribute. 
	 * @param value the currency
	 */
	public void setCurrency(final Currency value)
	{
		setCurrency( getSession().getSessionContext(), value );
	}
	
}
