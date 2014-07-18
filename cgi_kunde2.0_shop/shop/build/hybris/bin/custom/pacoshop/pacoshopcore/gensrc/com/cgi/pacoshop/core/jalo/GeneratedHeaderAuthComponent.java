/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.cms2.jalo.contents.components.CMSParagraphComponent HeaderAuthComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedHeaderAuthComponent extends CMSParagraphComponent
{
	/** Qualifier of the <code>HeaderAuthComponent.registerUrl</code> attribute **/
	public static final String REGISTERURL = "registerUrl";
	/** Qualifier of the <code>HeaderAuthComponent.loginUrl</code> attribute **/
	public static final String LOGINURL = "loginUrl";
	/** Qualifier of the <code>HeaderAuthComponent.loginTitle</code> attribute **/
	public static final String LOGINTITLE = "loginTitle";
	/** Qualifier of the <code>HeaderAuthComponent.registerTitle</code> attribute **/
	public static final String REGISTERTITLE = "registerTitle";
	/** Qualifier of the <code>HeaderAuthComponent.salutation</code> attribute **/
	public static final String SALUTATION = "salutation";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CMSParagraphComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(REGISTERURL, AttributeMode.INITIAL);
		tmp.put(LOGINURL, AttributeMode.INITIAL);
		tmp.put(LOGINTITLE, AttributeMode.INITIAL);
		tmp.put(REGISTERTITLE, AttributeMode.INITIAL);
		tmp.put(SALUTATION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.loginTitle</code> attribute.
	 * @return the loginTitle - Login url
	 */
	public String getLoginTitle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedHeaderAuthComponent.getLoginTitle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, LOGINTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.loginTitle</code> attribute.
	 * @return the loginTitle - Login url
	 */
	public String getLoginTitle()
	{
		return getLoginTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.loginTitle</code> attribute. 
	 * @return the localized loginTitle - Login url
	 */
	public Map<Language,String> getAllLoginTitle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,LOGINTITLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.loginTitle</code> attribute. 
	 * @return the localized loginTitle - Login url
	 */
	public Map<Language,String> getAllLoginTitle()
	{
		return getAllLoginTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.loginTitle</code> attribute. 
	 * @param value the loginTitle - Login url
	 */
	public void setLoginTitle(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedHeaderAuthComponent.setLoginTitle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, LOGINTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.loginTitle</code> attribute. 
	 * @param value the loginTitle - Login url
	 */
	public void setLoginTitle(final String value)
	{
		setLoginTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.loginTitle</code> attribute. 
	 * @param value the loginTitle - Login url
	 */
	public void setAllLoginTitle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,LOGINTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.loginTitle</code> attribute. 
	 * @param value the loginTitle - Login url
	 */
	public void setAllLoginTitle(final Map<Language,String> value)
	{
		setAllLoginTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.loginUrl</code> attribute.
	 * @return the loginUrl - Login url
	 */
	public String getLoginUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, LOGINURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.loginUrl</code> attribute.
	 * @return the loginUrl - Login url
	 */
	public String getLoginUrl()
	{
		return getLoginUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.loginUrl</code> attribute. 
	 * @param value the loginUrl - Login url
	 */
	public void setLoginUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, LOGINURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.loginUrl</code> attribute. 
	 * @param value the loginUrl - Login url
	 */
	public void setLoginUrl(final String value)
	{
		setLoginUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.registerTitle</code> attribute.
	 * @return the registerTitle - Login url
	 */
	public String getRegisterTitle(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedHeaderAuthComponent.getRegisterTitle requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, REGISTERTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.registerTitle</code> attribute.
	 * @return the registerTitle - Login url
	 */
	public String getRegisterTitle()
	{
		return getRegisterTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.registerTitle</code> attribute. 
	 * @return the localized registerTitle - Login url
	 */
	public Map<Language,String> getAllRegisterTitle(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,REGISTERTITLE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.registerTitle</code> attribute. 
	 * @return the localized registerTitle - Login url
	 */
	public Map<Language,String> getAllRegisterTitle()
	{
		return getAllRegisterTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.registerTitle</code> attribute. 
	 * @param value the registerTitle - Login url
	 */
	public void setRegisterTitle(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedHeaderAuthComponent.setRegisterTitle requires a session language", 0 );
		}
		setLocalizedProperty(ctx, REGISTERTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.registerTitle</code> attribute. 
	 * @param value the registerTitle - Login url
	 */
	public void setRegisterTitle(final String value)
	{
		setRegisterTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.registerTitle</code> attribute. 
	 * @param value the registerTitle - Login url
	 */
	public void setAllRegisterTitle(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,REGISTERTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.registerTitle</code> attribute. 
	 * @param value the registerTitle - Login url
	 */
	public void setAllRegisterTitle(final Map<Language,String> value)
	{
		setAllRegisterTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.registerUrl</code> attribute.
	 * @return the registerUrl - Registration url
	 */
	public String getRegisterUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, REGISTERURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.registerUrl</code> attribute.
	 * @return the registerUrl - Registration url
	 */
	public String getRegisterUrl()
	{
		return getRegisterUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.registerUrl</code> attribute. 
	 * @param value the registerUrl - Registration url
	 */
	public void setRegisterUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, REGISTERURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.registerUrl</code> attribute. 
	 * @param value the registerUrl - Registration url
	 */
	public void setRegisterUrl(final String value)
	{
		setRegisterUrl( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.salutation</code> attribute.
	 * @return the salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public String getSalutation(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedHeaderAuthComponent.getSalutation requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, SALUTATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.salutation</code> attribute.
	 * @return the salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public String getSalutation()
	{
		return getSalutation( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.salutation</code> attribute. 
	 * @return the localized salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public Map<Language,String> getAllSalutation(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,SALUTATION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>HeaderAuthComponent.salutation</code> attribute. 
	 * @return the localized salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public Map<Language,String> getAllSalutation()
	{
		return getAllSalutation( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.salutation</code> attribute. 
	 * @param value the salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public void setSalutation(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedHeaderAuthComponent.setSalutation requires a session language", 0 );
		}
		setLocalizedProperty(ctx, SALUTATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.salutation</code> attribute. 
	 * @param value the salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public void setSalutation(final String value)
	{
		setSalutation( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.salutation</code> attribute. 
	 * @param value the salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public void setAllSalutation(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,SALUTATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>HeaderAuthComponent.salutation</code> attribute. 
	 * @param value the salutation - Salutation, available params: {title}, {firstName}, {lastName}
	 */
	public void setAllSalutation(final Map<Language,String> value)
	{
		setAllSalutation( getSession().getSessionContext(), value );
	}
	
}
