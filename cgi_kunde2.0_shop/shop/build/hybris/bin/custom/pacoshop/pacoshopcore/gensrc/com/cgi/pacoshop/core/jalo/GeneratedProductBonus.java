/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.jalo.Bonus;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.ProductBonus ProductBonus}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductBonus extends Bonus
{
	/** Qualifier of the <code>ProductBonus.productDescription</code> attribute **/
	public static final String PRODUCTDESCRIPTION = "productDescription";
	/** Qualifier of the <code>ProductBonus.additionalPayment</code> attribute **/
	public static final String ADDITIONALPAYMENT = "additionalPayment";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(Bonus.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PRODUCTDESCRIPTION, AttributeMode.INITIAL);
		tmp.put(ADDITIONALPAYMENT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductBonus.additionalPayment</code> attribute.
	 * @return the additionalPayment
	 */
	public PriceRow getAdditionalPayment(final SessionContext ctx)
	{
		return (PriceRow)getProperty( ctx, ADDITIONALPAYMENT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductBonus.additionalPayment</code> attribute.
	 * @return the additionalPayment
	 */
	public PriceRow getAdditionalPayment()
	{
		return getAdditionalPayment( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductBonus.additionalPayment</code> attribute. 
	 * @param value the additionalPayment
	 */
	public void setAdditionalPayment(final SessionContext ctx, final PriceRow value)
	{
		setProperty(ctx, ADDITIONALPAYMENT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductBonus.additionalPayment</code> attribute. 
	 * @param value the additionalPayment
	 */
	public void setAdditionalPayment(final PriceRow value)
	{
		setAdditionalPayment( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductBonus.productDescription</code> attribute.
	 * @return the productDescription
	 */
	public String getProductDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PRODUCTDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductBonus.productDescription</code> attribute.
	 * @return the productDescription
	 */
	public String getProductDescription()
	{
		return getProductDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductBonus.productDescription</code> attribute. 
	 * @param value the productDescription
	 */
	public void setProductDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PRODUCTDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductBonus.productDescription</code> attribute. 
	 * @param value the productDescription
	 */
	public void setProductDescription(final String value)
	{
		setProductDescription( getSession().getSessionContext(), value );
	}
	
}
