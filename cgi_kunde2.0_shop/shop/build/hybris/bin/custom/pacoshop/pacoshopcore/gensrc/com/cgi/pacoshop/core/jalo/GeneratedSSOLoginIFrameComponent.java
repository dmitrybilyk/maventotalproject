/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent SSOLoginIFrameComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSOLoginIFrameComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSOLoginIFrameComponent.src</code> attribute **/
	public static final String SRC = "src";
	/** Qualifier of the <code>SSOLoginIFrameComponent.height</code> attribute **/
	public static final String HEIGHT = "height";
	/** Qualifier of the <code>SSOLoginIFrameComponent.frameborder</code> attribute **/
	public static final String FRAMEBORDER = "frameborder";
	/** Qualifier of the <code>SSOLoginIFrameComponent.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>SSOLoginIFrameComponent.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>SSOLoginIFrameComponent.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	/** Qualifier of the <code>SSOLoginIFrameComponent.baseUrl</code> attribute **/
	public static final String BASEURL = "baseUrl";
	/** Qualifier of the <code>SSOLoginIFrameComponent.width</code> attribute **/
	public static final String WIDTH = "width";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SRC, AttributeMode.INITIAL);
		tmp.put(HEIGHT, AttributeMode.INITIAL);
		tmp.put(FRAMEBORDER, AttributeMode.INITIAL);
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(TITLE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		tmp.put(BASEURL, AttributeMode.INITIAL);
		tmp.put(WIDTH, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.baseUrl</code> attribute.
	 * @return the baseUrl - Base url.
	 */
	public String getBaseUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BASEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.baseUrl</code> attribute.
	 * @return the baseUrl - Base url.
	 */
	public String getBaseUrl()
	{
		return getBaseUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.baseUrl</code> attribute. 
	 * @param value the baseUrl - Base url.
	 */
	public void setBaseUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BASEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.baseUrl</code> attribute. 
	 * @param value the baseUrl - Base url.
	 */
	public void setBaseUrl(final String value)
	{
		setBaseUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.description</code> attribute.
	 * @return the description - Localized description of the component.
	 */
	public String getDescription(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSOLoginIFrameComponent.getDescription requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.description</code> attribute.
	 * @return the description - Localized description of the component.
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.description</code> attribute. 
	 * @return the localized description - Localized description of the component.
	 */
	public Map<Language,String> getAllDescription(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,DESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.description</code> attribute. 
	 * @return the localized description - Localized description of the component.
	 */
	public Map<Language,String> getAllDescription()
	{
		return getAllDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.description</code> attribute. 
	 * @param value the description - Localized description of the component.
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSOLoginIFrameComponent.setDescription requires a session language", 0 );
		}
		setLocalizedProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.description</code> attribute. 
	 * @param value the description - Localized description of the component.
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.description</code> attribute. 
	 * @param value the description - Localized description of the component.
	 */
	public void setAllDescription(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.description</code> attribute. 
	 * @param value the description - Localized description of the component.
	 */
	public void setAllDescription(final Map<Language,String> value)
	{
		setAllDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute.
	 * @return the frameborder
	 */
	public Integer getFrameborder(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, FRAMEBORDER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute.
	 * @return the frameborder
	 */
	public Integer getFrameborder()
	{
		return getFrameborder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute. 
	 * @return the frameborder
	 */
	public int getFrameborderAsPrimitive(final SessionContext ctx)
	{
		Integer value = getFrameborder( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute. 
	 * @return the frameborder
	 */
	public int getFrameborderAsPrimitive()
	{
		return getFrameborderAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute. 
	 * @param value the frameborder
	 */
	public void setFrameborder(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, FRAMEBORDER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute. 
	 * @param value the frameborder
	 */
	public void setFrameborder(final Integer value)
	{
		setFrameborder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute. 
	 * @param value the frameborder
	 */
	public void setFrameborder(final SessionContext ctx, final int value)
	{
		setFrameborder( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.frameborder</code> attribute. 
	 * @param value the frameborder
	 */
	public void setFrameborder(final int value)
	{
		setFrameborder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.height</code> attribute.
	 * @return the height
	 */
	public Integer getHeight(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, HEIGHT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.height</code> attribute.
	 * @return the height
	 */
	public Integer getHeight()
	{
		return getHeight( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.height</code> attribute. 
	 * @return the height
	 */
	public int getHeightAsPrimitive(final SessionContext ctx)
	{
		Integer value = getHeight( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.height</code> attribute. 
	 * @return the height
	 */
	public int getHeightAsPrimitive()
	{
		return getHeightAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.height</code> attribute. 
	 * @param value the height
	 */
	public void setHeight(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, HEIGHT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.height</code> attribute. 
	 * @param value the height
	 */
	public void setHeight(final Integer value)
	{
		setHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.height</code> attribute. 
	 * @param value the height
	 */
	public void setHeight(final SessionContext ctx, final int value)
	{
		setHeight( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.height</code> attribute. 
	 * @param value the height
	 */
	public void setHeight(final int value)
	{
		setHeight( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.id</code> attribute.
	 * @return the id - HTML ID of the iFrame.
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.id</code> attribute.
	 * @return the id - HTML ID of the iFrame.
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.id</code> attribute. 
	 * @param value the id - HTML ID of the iFrame.
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.id</code> attribute. 
	 * @param value the id - HTML ID of the iFrame.
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.src</code> attribute.
	 * @return the src - Source url of the iFrame.
	 */
	public String getSrc(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SRC);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.src</code> attribute.
	 * @return the src - Source url of the iFrame.
	 */
	public String getSrc()
	{
		return getSrc( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.src</code> attribute. 
	 * @param value the src - Source url of the iFrame.
	 */
	public void setSrc(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SRC,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.src</code> attribute. 
	 * @param value the src - Source url of the iFrame.
	 */
	public void setSrc(final String value)
	{
		setSrc( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.title</code> attribute.
	 * @return the title - Localized title of the component.
	 */
	public String getTitle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSOLoginIFrameComponent.getTitle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.title</code> attribute.
	 * @return the title - Localized title of the component.
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.title</code> attribute. 
	 * @return the localized title - Localized title of the component.
	 */
	public Map<Language,String> getAllTitle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,TITLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.title</code> attribute. 
	 * @return the localized title - Localized title of the component.
	 */
	public Map<Language,String> getAllTitle()
	{
		return getAllTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSSOLoginIFrameComponent.setTitle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setAllTitle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.title</code> attribute. 
	 * @param value the title - Localized title of the component.
	 */
	public void setAllTitle(final Map<Language,String> value)
	{
		setAllTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.width</code> attribute.
	 * @return the width
	 */
	public Integer getWidth(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, WIDTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.width</code> attribute.
	 * @return the width
	 */
	public Integer getWidth()
	{
		return getWidth( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.width</code> attribute. 
	 * @return the width
	 */
	public int getWidthAsPrimitive(final SessionContext ctx)
	{
		Integer value = getWidth( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSOLoginIFrameComponent.width</code> attribute. 
	 * @return the width
	 */
	public int getWidthAsPrimitive()
	{
		return getWidthAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.width</code> attribute. 
	 * @param value the width
	 */
	public void setWidth(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, WIDTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.width</code> attribute. 
	 * @param value the width
	 */
	public void setWidth(final Integer value)
	{
		setWidth( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.width</code> attribute. 
	 * @param value the width
	 */
	public void setWidth(final SessionContext ctx, final int value)
	{
		setWidth( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSOLoginIFrameComponent.width</code> attribute. 
	 * @param value the width
	 */
	public void setWidth(final int value)
	{
		setWidth( getSession().getSessionContext(), value );
	}
	
}
