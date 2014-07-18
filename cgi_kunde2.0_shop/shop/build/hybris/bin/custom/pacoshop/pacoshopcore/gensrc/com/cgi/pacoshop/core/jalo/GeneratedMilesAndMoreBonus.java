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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.MilesAndMoreBonus MilesAndMoreBonus}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedMilesAndMoreBonus extends Bonus
{
	/** Qualifier of the <code>MilesAndMoreBonus.miles</code> attribute **/
	public static final String MILES = "miles";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Bonus.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(MILES, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MilesAndMoreBonus.miles</code> attribute.
	 * @return the miles
	 */
	public Integer getMiles(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, MILES);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MilesAndMoreBonus.miles</code> attribute.
	 * @return the miles
	 */
	public Integer getMiles()
	{
		return getMiles( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MilesAndMoreBonus.miles</code> attribute. 
	 * @return the miles
	 */
	public int getMilesAsPrimitive(final SessionContext ctx)
	{
		Integer value = getMiles( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>MilesAndMoreBonus.miles</code> attribute. 
	 * @return the miles
	 */
	public int getMilesAsPrimitive()
	{
		return getMilesAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MilesAndMoreBonus.miles</code> attribute. 
	 * @param value the miles
	 */
	public void setMiles(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, MILES,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MilesAndMoreBonus.miles</code> attribute. 
	 * @param value the miles
	 */
	public void setMiles(final Integer value)
	{
		setMiles( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MilesAndMoreBonus.miles</code> attribute. 
	 * @param value the miles
	 */
	public void setMiles(final SessionContext ctx, final int value)
	{
		setMiles( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>MilesAndMoreBonus.miles</code> attribute. 
	 * @param value the miles
	 */
	public void setMiles(final int value)
	{
		setMiles( getSession().getSessionContext(), value );
	}
	
}
