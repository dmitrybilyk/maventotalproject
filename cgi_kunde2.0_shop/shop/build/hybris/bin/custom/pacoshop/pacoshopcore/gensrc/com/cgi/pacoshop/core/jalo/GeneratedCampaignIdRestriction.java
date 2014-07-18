/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.cms2.jalo.restrictions.AbstractRestriction;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.CampaignIdRestriction CampaignIdRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCampaignIdRestriction extends AbstractRestriction
{
	/** Qualifier of the <code>CampaignIdRestriction.campaignId</code> attribute **/
	public static final String CAMPAIGNID = "campaignId";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractRestriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(CAMPAIGNID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CampaignIdRestriction.campaignId</code> attribute.
	 * @return the campaignId
	 */
	public String getCampaignId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CAMPAIGNID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CampaignIdRestriction.campaignId</code> attribute.
	 * @return the campaignId
	 */
	public String getCampaignId()
	{
		return getCampaignId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CampaignIdRestriction.campaignId</code> attribute. 
	 * @param value the campaignId
	 */
	public void setCampaignId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CAMPAIGNID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CampaignIdRestriction.campaignId</code> attribute. 
	 * @param value the campaignId
	 */
	public void setCampaignId(final String value)
	{
		setCampaignId( getSession().getSessionContext(), value );
	}
	
}
