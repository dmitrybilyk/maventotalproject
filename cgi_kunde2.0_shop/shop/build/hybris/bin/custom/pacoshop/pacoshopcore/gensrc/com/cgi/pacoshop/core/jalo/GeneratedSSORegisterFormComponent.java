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
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent SSORegisterFormComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSORegisterFormComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSORegisterFormComponent.label</code> attribute **/
	public static final String LABEL = "label";
	/** Qualifier of the <code>SSORegisterFormComponent.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>SSORegisterFormComponent.baseUrl</code> attribute **/
	public static final String BASEURL = "baseUrl";
	/** Qualifier of the <code>SSORegisterFormComponent.text</code> attribute **/
	public static final String TEXT = "text";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(LABEL, AttributeMode.INITIAL);
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(BASEURL, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.baseUrl</code> attribute.
	 * @return the baseUrl - Base url.
	 */
	public String getBaseUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, BASEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.baseUrl</code> attribute.
	 * @return the baseUrl - Base url.
	 */
	public String getBaseUrl()
	{
		return getBaseUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.baseUrl</code> attribute. 
	 * @param value the baseUrl - Base url.
	 */
	public void setBaseUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, BASEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.baseUrl</code> attribute. 
	 * @param value the baseUrl - Base url.
	 */
	public void setBaseUrl(final String value)
	{
		setBaseUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.id</code> attribute.
	 * @return the id - Html id for component.
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.id</code> attribute.
	 * @return the id - Html id for component.
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.id</code> attribute. 
	 * @param value the id - Html id for component.
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.id</code> attribute. 
	 * @param value the id - Html id for component.
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.label</code> attribute.
	 * @return the label - Label for button.
	 */
	public String getLabel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.label</code> attribute.
	 * @return the label - Label for button.
	 */
	public String getLabel()
	{
		return getLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.label</code> attribute. 
	 * @param value the label - Label for button.
	 */
	public void setLabel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.label</code> attribute. 
	 * @param value the label - Label for button.
	 */
	public void setLabel(final String value)
	{
		setLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.text</code> attribute.
	 * @return the text - Text for button.
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterFormComponent.text</code> attribute.
	 * @return the text - Text for button.
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.text</code> attribute. 
	 * @param value the text - Text for button.
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterFormComponent.text</code> attribute. 
	 * @param value the text - Text for button.
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
}
