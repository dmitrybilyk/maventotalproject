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
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.VATGroup VATGroup}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedVATGroup extends GenericItem
{
	/** Qualifier of the <code>VATGroup.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>VATGroup.vatRate</code> attribute **/
	public static final String VATRATE = "vatRate";
	/** Qualifier of the <code>VATGroup.name</code> attribute **/
	public static final String NAME = "name";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(VATRATE, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.id</code> attribute.
	 * @return the id - ID
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.id</code> attribute.
	 * @return the id - ID
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.id</code> attribute. 
	 * @param value the id - ID
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.id</code> attribute. 
	 * @param value the id - ID
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.name</code> attribute.
	 * @return the name - Displayed name
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedVATGroup.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.name</code> attribute.
	 * @return the name - Displayed name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.name</code> attribute. 
	 * @return the localized name - Displayed name
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.name</code> attribute. 
	 * @return the localized name - Displayed name
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.name</code> attribute. 
	 * @param value the name - Displayed name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedVATGroup.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.name</code> attribute. 
	 * @param value the name - Displayed name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.name</code> attribute. 
	 * @param value the name - Displayed name
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.name</code> attribute. 
	 * @param value the name - Displayed name
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.vatRate</code> attribute.
	 * @return the vatRate - value
	 */
	public Double getVatRate(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, VATRATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.vatRate</code> attribute.
	 * @return the vatRate - value
	 */
	public Double getVatRate()
	{
		return getVatRate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.vatRate</code> attribute. 
	 * @return the vatRate - value
	 */
	public double getVatRateAsPrimitive(final SessionContext ctx)
	{
		Double value = getVatRate( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>VATGroup.vatRate</code> attribute. 
	 * @return the vatRate - value
	 */
	public double getVatRateAsPrimitive()
	{
		return getVatRateAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.vatRate</code> attribute. 
	 * @param value the vatRate - value
	 */
	public void setVatRate(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, VATRATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.vatRate</code> attribute. 
	 * @param value the vatRate - value
	 */
	public void setVatRate(final Double value)
	{
		setVatRate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.vatRate</code> attribute. 
	 * @param value the vatRate - value
	 */
	public void setVatRate(final SessionContext ctx, final double value)
	{
		setVatRate( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>VATGroup.vatRate</code> attribute. 
	 * @param value the vatRate - value
	 */
	public void setVatRate(final double value)
	{
		setVatRate( getSession().getSessionContext(), value );
	}
	
}
