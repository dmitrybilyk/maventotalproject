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
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.DeeplinkCallback PortalId}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedDeeplinkCallback extends GenericItem
{
	/** Qualifier of the <code>PortalId.portalId</code> attribute **/
	public static final String PORTALID = "portalId";
	/** Qualifier of the <code>PortalId.portalUrl</code> attribute **/
	public static final String PORTALURL = "portalUrl";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PORTALID, AttributeMode.INITIAL);
		tmp.put(PORTALURL, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PortalId.portalId</code> attribute.
	 * @return the portalId
	 */
	public String getPortalId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PORTALID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PortalId.portalId</code> attribute.
	 * @return the portalId
	 */
	public String getPortalId()
	{
		return getPortalId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PortalId.portalId</code> attribute. 
	 * @param value the portalId
	 */
	public void setPortalId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PORTALID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PortalId.portalId</code> attribute. 
	 * @param value the portalId
	 */
	public void setPortalId(final String value)
	{
		setPortalId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PortalId.portalUrl</code> attribute.
	 * @return the portalUrl
	 */
	public String getPortalUrl(final SessionContext ctx)
	{
		return (String)getProperty( ctx, PORTALURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PortalId.portalUrl</code> attribute.
	 * @return the portalUrl
	 */
	public String getPortalUrl()
	{
		return getPortalUrl( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PortalId.portalUrl</code> attribute. 
	 * @param value the portalUrl
	 */
	public void setPortalUrl(final SessionContext ctx, final String value)
	{
		setProperty(ctx, PORTALURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PortalId.portalUrl</code> attribute. 
	 * @param value the portalUrl
	 */
	public void setPortalUrl(final String value)
	{
		setPortalUrl( getSession().getSessionContext(), value );
	}
	
}
