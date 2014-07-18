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
import de.hybris.platform.jalo.order.payment.PaymentMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.PaymentType PaymentType}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPaymentType extends GenericItem
{
	/** Qualifier of the <code>PaymentType.paymentMode</code> attribute **/
	public static final String PAYMENTMODE = "paymentMode";
	/** Qualifier of the <code>PaymentType.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>PaymentType.externalId</code> attribute **/
	public static final String EXTERNALID = "externalId";
	/** Qualifier of the <code>PaymentType.name</code> attribute **/
	public static final String NAME = "name";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PAYMENTMODE, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(EXTERNALID, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.externalId</code> attribute.
	 * @return the externalId - The ID that will be used by the Service Bus interface to identify payment types
	 */
	public String getExternalId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, EXTERNALID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.externalId</code> attribute.
	 * @return the externalId - The ID that will be used by the Service Bus interface to identify payment types
	 */
	public String getExternalId()
	{
		return getExternalId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.externalId</code> attribute. 
	 * @param value the externalId - The ID that will be used by the Service Bus interface to identify payment types
	 */
	public void setExternalId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, EXTERNALID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.externalId</code> attribute. 
	 * @param value the externalId - The ID that will be used by the Service Bus interface to identify payment types
	 */
	public void setExternalId(final String value)
	{
		setExternalId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.name</code> attribute.
	 * @return the name
	 */
	public String getName(final SessionContext ctx)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPaymentType.getName requires a session language", 0 );
		}
		return (String)getLocalizedProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.name</code> attribute.
	 * @return the name
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName(final SessionContext ctx)
	{
		return (Map<Language,String>)getAllLocalizedProperties(ctx,NAME,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.name</code> attribute. 
	 * @return the localized name
	 */
	public Map<Language,String> getAllName()
	{
		return getAllName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedPaymentType.setName requires a session language", 0 );
		}
		setLocalizedProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.name</code> attribute. 
	 * @param value the name
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final SessionContext ctx, final Map<Language,String> value)
	{
		setAllLocalizedProperties(ctx,NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.name</code> attribute. 
	 * @param value the name
	 */
	public void setAllName(final Map<Language,String> value)
	{
		setAllName( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.paymentMode</code> attribute.
	 * @return the paymentMode - PaymentType corresponds to PaymentMode in payment extensions
	 */
	public PaymentMode getPaymentMode(final SessionContext ctx)
	{
		return (PaymentMode)getProperty( ctx, PAYMENTMODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentType.paymentMode</code> attribute.
	 * @return the paymentMode - PaymentType corresponds to PaymentMode in payment extensions
	 */
	public PaymentMode getPaymentMode()
	{
		return getPaymentMode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.paymentMode</code> attribute. 
	 * @param value the paymentMode - PaymentType corresponds to PaymentMode in payment extensions
	 */
	public void setPaymentMode(final SessionContext ctx, final PaymentMode value)
	{
		setProperty(ctx, PAYMENTMODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentType.paymentMode</code> attribute. 
	 * @param value the paymentMode - PaymentType corresponds to PaymentMode in payment extensions
	 */
	public void setPaymentMode(final PaymentMode value)
	{
		setPaymentMode( getSession().getSessionContext(), value );
	}
	
}
