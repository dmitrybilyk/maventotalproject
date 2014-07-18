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
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem TermVersion}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTermVersion extends GenericItem
{
	/** Qualifier of the <code>TermVersion.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>TermVersion.version</code> attribute **/
	public static final String VERSION = "version";
	/** Qualifier of the <code>TermVersion.type</code> attribute **/
	public static final String TYPE = "type";
	/** Qualifier of the <code>TermVersion.scope</code> attribute **/
	public static final String SCOPE = "scope";
	/** Qualifier of the <code>TermVersion.termsId</code> attribute **/
	public static final String TERMSID = "termsId";
	/** Qualifier of the <code>TermVersion.termsVersionId</code> attribute **/
	public static final String TERMSVERSIONID = "termsVersionId";
	/** Qualifier of the <code>TermVersion.termsVersionIdentifier</code> attribute **/
	public static final String TERMSVERSIONIDENTIFIER = "termsVersionIdentifier";
	/** Qualifier of the <code>TermVersion.mandatory</code> attribute **/
	public static final String MANDATORY = "mandatory";
	/** Qualifier of the <code>TermVersion.url</code> attribute **/
	public static final String URL = "url";
	/** Qualifier of the <code>TermVersion.orderByValue</code> attribute **/
	public static final String ORDERBYVALUE = "orderByValue";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(VERSION, AttributeMode.INITIAL);
		tmp.put(TYPE, AttributeMode.INITIAL);
		tmp.put(SCOPE, AttributeMode.INITIAL);
		tmp.put(TERMSID, AttributeMode.INITIAL);
		tmp.put(TERMSVERSIONID, AttributeMode.INITIAL);
		tmp.put(TERMSVERSIONIDENTIFIER, AttributeMode.INITIAL);
		tmp.put(MANDATORY, AttributeMode.INITIAL);
		tmp.put(URL, AttributeMode.INITIAL);
		tmp.put(ORDERBYVALUE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.mandatory</code> attribute.
	 * @return the mandatory
	 */
	public Boolean isMandatory(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, MANDATORY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.mandatory</code> attribute.
	 * @return the mandatory
	 */
	public Boolean isMandatory()
	{
		return isMandatory( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.mandatory</code> attribute. 
	 * @return the mandatory
	 */
	public boolean isMandatoryAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isMandatory( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.mandatory</code> attribute. 
	 * @return the mandatory
	 */
	public boolean isMandatoryAsPrimitive()
	{
		return isMandatoryAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, MANDATORY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final Boolean value)
	{
		setMandatory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final SessionContext ctx, final boolean value)
	{
		setMandatory( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.mandatory</code> attribute. 
	 * @param value the mandatory
	 */
	public void setMandatory(final boolean value)
	{
		setMandatory( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.orderByValue</code> attribute.
	 * @return the orderByValue
	 */
	public Integer getOrderByValue(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, ORDERBYVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.orderByValue</code> attribute.
	 * @return the orderByValue
	 */
	public Integer getOrderByValue()
	{
		return getOrderByValue( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.orderByValue</code> attribute. 
	 * @return the orderByValue
	 */
	public int getOrderByValueAsPrimitive(final SessionContext ctx)
	{
		Integer value = getOrderByValue( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.orderByValue</code> attribute. 
	 * @return the orderByValue
	 */
	public int getOrderByValueAsPrimitive()
	{
		return getOrderByValueAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.orderByValue</code> attribute. 
	 * @param value the orderByValue
	 */
	public void setOrderByValue(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, ORDERBYVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.orderByValue</code> attribute. 
	 * @param value the orderByValue
	 */
	public void setOrderByValue(final Integer value)
	{
		setOrderByValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.orderByValue</code> attribute. 
	 * @param value the orderByValue
	 */
	public void setOrderByValue(final SessionContext ctx, final int value)
	{
		setOrderByValue( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.orderByValue</code> attribute. 
	 * @param value the orderByValue
	 */
	public void setOrderByValue(final int value)
	{
		setOrderByValue( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.scope</code> attribute.
	 * @return the scope
	 */
	public String getScope(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SCOPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.scope</code> attribute.
	 * @return the scope
	 */
	public String getScope()
	{
		return getScope( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.scope</code> attribute. 
	 * @param value the scope
	 */
	public void setScope(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SCOPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.scope</code> attribute. 
	 * @param value the scope
	 */
	public void setScope(final String value)
	{
		setScope( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.termsId</code> attribute.
	 * @return the termsId
	 */
	public String getTermsId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TERMSID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.termsId</code> attribute.
	 * @return the termsId
	 */
	public String getTermsId()
	{
		return getTermsId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.termsId</code> attribute. 
	 * @param value the termsId
	 */
	public void setTermsId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TERMSID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.termsId</code> attribute. 
	 * @param value the termsId
	 */
	public void setTermsId(final String value)
	{
		setTermsId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.termsVersionId</code> attribute.
	 * @return the termsVersionId - The termsVersionId is the unique identifier of each term version.
	 */
	public String getTermsVersionId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TERMSVERSIONID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.termsVersionId</code> attribute.
	 * @return the termsVersionId - The termsVersionId is the unique identifier of each term version.
	 */
	public String getTermsVersionId()
	{
		return getTermsVersionId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.termsVersionId</code> attribute. 
	 * @param value the termsVersionId - The termsVersionId is the unique identifier of each term version.
	 */
	public void setTermsVersionId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TERMSVERSIONID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.termsVersionId</code> attribute. 
	 * @param value the termsVersionId - The termsVersionId is the unique identifier of each term version.
	 */
	public void setTermsVersionId(final String value)
	{
		setTermsVersionId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.termsVersionIdentifier</code> attribute.
	 * @return the termsVersionIdentifier
	 */
	public String getTermsVersionIdentifier(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TERMSVERSIONIDENTIFIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.termsVersionIdentifier</code> attribute.
	 * @return the termsVersionIdentifier
	 */
	public String getTermsVersionIdentifier()
	{
		return getTermsVersionIdentifier( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.termsVersionIdentifier</code> attribute. 
	 * @param value the termsVersionIdentifier
	 */
	public void setTermsVersionIdentifier(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TERMSVERSIONIDENTIFIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.termsVersionIdentifier</code> attribute. 
	 * @param value the termsVersionIdentifier
	 */
	public void setTermsVersionIdentifier(final String value)
	{
		setTermsVersionIdentifier( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.type</code> attribute.
	 * @return the type
	 */
	public String getType(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.type</code> attribute.
	 * @return the type
	 */
	public String getType()
	{
		return getType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.type</code> attribute. 
	 * @param value the type
	 */
	public void setType(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.type</code> attribute. 
	 * @param value the type
	 */
	public void setType(final String value)
	{
		setType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.url</code> attribute.
	 * @return the url
	 */
	public String getUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, URL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.url</code> attribute.
	 * @return the url
	 */
	public String getUrl()
	{
		return getUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, URL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.url</code> attribute. 
	 * @param value the url
	 */
	public void setUrl(final String value)
	{
		setUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.version</code> attribute.
	 * @return the version
	 */
	public Integer getVersion(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, VERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.version</code> attribute.
	 * @return the version
	 */
	public Integer getVersion()
	{
		return getVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.version</code> attribute. 
	 * @return the version
	 */
	public int getVersionAsPrimitive(final SessionContext ctx)
	{
		Integer value = getVersion( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>TermVersion.version</code> attribute. 
	 * @return the version
	 */
	public int getVersionAsPrimitive()
	{
		return getVersionAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.version</code> attribute. 
	 * @param value the version
	 */
	public void setVersion(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, VERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.version</code> attribute. 
	 * @param value the version
	 */
	public void setVersion(final Integer value)
	{
		setVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.version</code> attribute. 
	 * @param value the version
	 */
	public void setVersion(final SessionContext ctx, final int value)
	{
		setVersion( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>TermVersion.version</code> attribute. 
	 * @param value the version
	 */
	public void setVersion(final int value)
	{
		setVersion( getSession().getSessionContext(), value );
	}
	
}
