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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem PhoneNumber}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPhoneNumber extends GenericItem
{
	/** Qualifier of the <code>PhoneNumber.number</code> attribute **/
	public static final String NUMBER = "number";
	/** Qualifier of the <code>PhoneNumber.extension</code> attribute **/
	public static final String EXTENSION = "extension";
	/** Qualifier of the <code>PhoneNumber.prefix</code> attribute **/
	public static final String PREFIX = "prefix";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(NUMBER, AttributeMode.INITIAL);
		tmp.put(EXTENSION, AttributeMode.INITIAL);
		tmp.put(PREFIX, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PhoneNumber.extension</code> attribute.
	 * @return the extension
	 */
	public String getExtension(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EXTENSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PhoneNumber.extension</code> attribute.
	 * @return the extension
	 */
	public String getExtension()
	{
		return getExtension( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PhoneNumber.extension</code> attribute. 
	 * @param value the extension
	 */
	public void setExtension(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EXTENSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PhoneNumber.extension</code> attribute. 
	 * @param value the extension
	 */
	public void setExtension(final String value)
	{
		setExtension( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PhoneNumber.number</code> attribute.
	 * @return the number
	 */
	public String getNumber(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PhoneNumber.number</code> attribute.
	 * @return the number
	 */
	public String getNumber()
	{
		return getNumber( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PhoneNumber.number</code> attribute. 
	 * @param value the number
	 */
	public void setNumber(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PhoneNumber.number</code> attribute. 
	 * @param value the number
	 */
	public void setNumber(final String value)
	{
		setNumber( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PhoneNumber.prefix</code> attribute.
	 * @return the prefix
	 */
	public String getPrefix(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PREFIX);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PhoneNumber.prefix</code> attribute.
	 * @return the prefix
	 */
	public String getPrefix()
	{
		return getPrefix( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PhoneNumber.prefix</code> attribute. 
	 * @param value the prefix
	 */
	public void setPrefix(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PREFIX,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PhoneNumber.prefix</code> attribute. 
	 * @param value the prefix
	 */
	public void setPrefix(final String value)
	{
		setPrefix( getSession().getSessionContext(), value );
	}
	
}
