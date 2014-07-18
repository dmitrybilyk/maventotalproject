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
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.FingerprintSecret FingerprintSecret}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedFingerprintSecret extends GenericItem
{
	/** Qualifier of the <code>FingerprintSecret.secret</code> attribute **/
	public static final String SECRET = "secret";
	/** Qualifier of the <code>FingerprintSecret.fingerPrintSecretNo</code> attribute **/
	public static final String FINGERPRINTSECRETNO = "fingerPrintSecretNo";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(SECRET, AttributeMode.INITIAL);
		tmp.put(FINGERPRINTSECRETNO, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FingerprintSecret.fingerPrintSecretNo</code> attribute.
	 * @return the fingerPrintSecretNo
	 */
	public String getFingerPrintSecretNo(final SessionContext ctx)
	{
		return (String)getProperty( ctx, FINGERPRINTSECRETNO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FingerprintSecret.fingerPrintSecretNo</code> attribute.
	 * @return the fingerPrintSecretNo
	 */
	public String getFingerPrintSecretNo()
	{
		return getFingerPrintSecretNo( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FingerprintSecret.fingerPrintSecretNo</code> attribute. 
	 * @param value the fingerPrintSecretNo
	 */
	public void setFingerPrintSecretNo(final SessionContext ctx, final String value)
	{
		setProperty(ctx, FINGERPRINTSECRETNO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FingerprintSecret.fingerPrintSecretNo</code> attribute. 
	 * @param value the fingerPrintSecretNo
	 */
	public void setFingerPrintSecretNo(final String value)
	{
		setFingerPrintSecretNo( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FingerprintSecret.secret</code> attribute.
	 * @return the secret
	 */
	public String getSecret(final SessionContext ctx)
	{
		return (String)getProperty( ctx, SECRET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>FingerprintSecret.secret</code> attribute.
	 * @return the secret
	 */
	public String getSecret()
	{
		return getSecret( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FingerprintSecret.secret</code> attribute. 
	 * @param value the secret
	 */
	public void setSecret(final SessionContext ctx, final String value)
	{
		setProperty(ctx, SECRET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>FingerprintSecret.secret</code> attribute. 
	 * @param value the secret
	 */
	public void setSecret(final String value)
	{
		setSecret( getSession().getSessionContext(), value );
	}
	
}
