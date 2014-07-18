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
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.Title2 Title2}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTitle2 extends GenericItem
{
	/** Qualifier of the <code>Title2.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Title2.index</code> attribute **/
	public static final String INDEX = "index";
	/** Qualifier of the <code>Title2.name</code> attribute **/
	public static final String NAME = "name";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(INDEX, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.index</code> attribute.
	 * @return the index
	 */
	public Integer getIndex(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, INDEX);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.index</code> attribute.
	 * @return the index
	 */
	public Integer getIndex()
	{
		return getIndex( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.index</code> attribute. 
	 * @return the index
	 */
	public int getIndexAsPrimitive(final SessionContext ctx)
	{
		Integer value = getIndex( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.index</code> attribute. 
	 * @return the index
	 */
	public int getIndexAsPrimitive()
	{
		return getIndexAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.index</code> attribute. 
	 * @param value the index
	 */
	public void setIndex(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, INDEX,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.index</code> attribute. 
	 * @param value the index
	 */
	public void setIndex(final Integer value)
	{
		setIndex( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.index</code> attribute. 
	 * @param value the index
	 */
	public void setIndex(final SessionContext ctx, final int value)
	{
		setIndex( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.index</code> attribute. 
	 * @param value the index
	 */
	public void setIndex(final int value)
	{
		setIndex( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedTitle2.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Title2.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedTitle2.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Title2.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
}
