/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.jalo.TermVersion;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem TermAccepted}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTermAccepted extends GenericItem
{
	/** Qualifier of the <code>TermAccepted.termsVersion</code> attribute **/
	public static final String TERMSVERSION = "termsVersion";
	/** Qualifier of the <code>TermAccepted.confirmed</code> attribute **/
	public static final String CONFIRMED = "confirmed";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(TERMSVERSION, AttributeMode.INITIAL);
		tmp.put(CONFIRMED, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermAccepted.confirmed</code> attribute.
	 * @return the confirmed
	 */
	public Boolean isConfirmed(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, CONFIRMED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermAccepted.confirmed</code> attribute.
	 * @return the confirmed
	 */
	public Boolean isConfirmed()
	{
		return isConfirmed( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermAccepted.confirmed</code> attribute. 
	 * @return the confirmed
	 */
	public boolean isConfirmedAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isConfirmed( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermAccepted.confirmed</code> attribute. 
	 * @return the confirmed
	 */
	public boolean isConfirmedAsPrimitive()
	{
		return isConfirmedAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermAccepted.confirmed</code> attribute. 
	 * @param value the confirmed
	 */
	public void setConfirmed(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, CONFIRMED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermAccepted.confirmed</code> attribute. 
	 * @param value the confirmed
	 */
	public void setConfirmed(final Boolean value)
	{
		setConfirmed( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermAccepted.confirmed</code> attribute. 
	 * @param value the confirmed
	 */
	public void setConfirmed(final SessionContext ctx, final boolean value)
	{
		setConfirmed( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermAccepted.confirmed</code> attribute. 
	 * @param value the confirmed
	 */
	public void setConfirmed(final boolean value)
	{
		setConfirmed( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermAccepted.termsVersion</code> attribute.
	 * @return the termsVersion
	 */
	public TermVersion getTermsVersion(final SessionContext ctx)
	{
		return (TermVersion)getProperty( ctx, TERMSVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermAccepted.termsVersion</code> attribute.
	 * @return the termsVersion
	 */
	public TermVersion getTermsVersion()
	{
		return getTermsVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermAccepted.termsVersion</code> attribute. 
	 * @param value the termsVersion
	 */
	public void setTermsVersion(final SessionContext ctx, final TermVersion value)
	{
		setProperty(ctx, TERMSVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermAccepted.termsVersion</code> attribute. 
	 * @param value the termsVersion
	 */
	public void setTermsVersion(final TermVersion value)
	{
		setTermsVersion( getSession().getSessionContext(), value );
	}
	
}
