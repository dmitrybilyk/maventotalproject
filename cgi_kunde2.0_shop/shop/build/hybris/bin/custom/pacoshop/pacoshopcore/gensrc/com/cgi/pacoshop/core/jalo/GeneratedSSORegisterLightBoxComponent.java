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
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent SSORegisterLightBoxComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedSSORegisterLightBoxComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>SSORegisterLightBoxComponent.registerUrl</code> attribute **/
	public static final String REGISTERURL = "registerUrl";
	/** Qualifier of the <code>SSORegisterLightBoxComponent.header</code> attribute **/
	public static final String HEADER = "header";
	/** Qualifier of the <code>SSORegisterLightBoxComponent.label</code> attribute **/
	public static final String LABEL = "label";
	/** Qualifier of the <code>SSORegisterLightBoxComponent.timeout</code> attribute **/
	public static final String TIMEOUT = "timeout";
	/** Qualifier of the <code>SSORegisterLightBoxComponent.text</code> attribute **/
	public static final String TEXT = "text";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(REGISTERURL, AttributeMode.INITIAL);
		tmp.put(HEADER, AttributeMode.INITIAL);
		tmp.put(LABEL, AttributeMode.INITIAL);
		tmp.put(TIMEOUT, AttributeMode.INITIAL);
		tmp.put(TEXT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.header</code> attribute.
	 * @return the header - Header text for lightbox.
	 */
	public String getHeader(final SessionContext ctx)
	{
		return (String)getProperty( ctx, HEADER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.header</code> attribute.
	 * @return the header - Header text for lightbox.
	 */
	public String getHeader()
	{
		return getHeader( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.header</code> attribute. 
	 * @param value the header - Header text for lightbox.
	 */
	public void setHeader(final SessionContext ctx, final String value)
	{
		setProperty(ctx, HEADER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.header</code> attribute. 
	 * @param value the header - Header text for lightbox.
	 */
	public void setHeader(final String value)
	{
		setHeader( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.label</code> attribute.
	 * @return the label - Label for button.
	 */
	public String getLabel(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LABEL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.label</code> attribute.
	 * @return the label - Label for button.
	 */
	public String getLabel()
	{
		return getLabel( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.label</code> attribute. 
	 * @param value the label - Label for button.
	 */
	public void setLabel(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LABEL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.label</code> attribute. 
	 * @param value the label - Label for button.
	 */
	public void setLabel(final String value)
	{
		setLabel( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.registerUrl</code> attribute.
	 * @return the registerUrl - Register url.
	 */
	public String getRegisterUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REGISTERURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.registerUrl</code> attribute.
	 * @return the registerUrl - Register url.
	 */
	public String getRegisterUrl()
	{
		return getRegisterUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.registerUrl</code> attribute. 
	 * @param value the registerUrl - Register url.
	 */
	public void setRegisterUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REGISTERURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.registerUrl</code> attribute. 
	 * @param value the registerUrl - Register url.
	 */
	public void setRegisterUrl(final String value)
	{
		setRegisterUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.text</code> attribute.
	 * @return the text - Text for button.
	 */
	public String getText(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TEXT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.text</code> attribute.
	 * @return the text - Text for button.
	 */
	public String getText()
	{
		return getText( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.text</code> attribute. 
	 * @param value the text - Text for button.
	 */
	public void setText(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TEXT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.text</code> attribute. 
	 * @param value the text - Text for button.
	 */
	public void setText(final String value)
	{
		setText( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute.
	 * @return the timeout - Timeout before lightbox popup.
	 */
	public Integer getTimeout(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, TIMEOUT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute.
	 * @return the timeout - Timeout before lightbox popup.
	 */
	public Integer getTimeout()
	{
		return getTimeout( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute. 
	 * @return the timeout - Timeout before lightbox popup.
	 */
	public int getTimeoutAsPrimitive(final SessionContext ctx)
	{
		Integer value = getTimeout( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute. 
	 * @return the timeout - Timeout before lightbox popup.
	 */
	public int getTimeoutAsPrimitive()
	{
		return getTimeoutAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute. 
	 * @param value the timeout - Timeout before lightbox popup.
	 */
	public void setTimeout(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, TIMEOUT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute. 
	 * @param value the timeout - Timeout before lightbox popup.
	 */
	public void setTimeout(final Integer value)
	{
		setTimeout( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute. 
	 * @param value the timeout - Timeout before lightbox popup.
	 */
	public void setTimeout(final SessionContext ctx, final int value)
	{
		setTimeout( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SSORegisterLightBoxComponent.timeout</code> attribute. 
	 * @param value the timeout - Timeout before lightbox popup.
	 */
	public void setTimeout(final int value)
	{
		setTimeout( getSession().getSessionContext(), value );
	}
	
}
