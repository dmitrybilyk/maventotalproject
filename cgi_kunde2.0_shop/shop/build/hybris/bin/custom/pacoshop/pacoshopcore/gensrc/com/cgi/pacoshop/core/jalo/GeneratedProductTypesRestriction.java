/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.jalo.ProductType;
import de.hybris.platform.cms2.jalo.restrictions.AbstractRestriction;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.ProductTypesRestriction ProductTypesRestriction}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductTypesRestriction extends AbstractRestriction
{
	/** Qualifier of the <code>ProductTypesRestriction.productTypes</code> attribute **/
	public static final String PRODUCTTYPES = "productTypes";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(AbstractRestriction.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PRODUCTTYPES, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductTypesRestriction.productTypes</code> attribute.
	 * @return the productTypes
	 */
	public Set<ProductType> getProductTypes(final SessionContext ctx)
	{
		Set<ProductType> coll = (Set<ProductType>)getProperty( ctx, PRODUCTTYPES);
		return coll != null ? coll : Collections.EMPTY_SET;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductTypesRestriction.productTypes</code> attribute.
	 * @return the productTypes
	 */
	public Set<ProductType> getProductTypes()
	{
		return getProductTypes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductTypesRestriction.productTypes</code> attribute. 
	 * @param value the productTypes
	 */
	public void setProductTypes(final SessionContext ctx, final Set<ProductType> value)
	{
		setProperty(ctx, PRODUCTTYPES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductTypesRestriction.productTypes</code> attribute. 
	 * @param value the productTypes
	 */
	public void setProductTypes(final Set<ProductType> value)
	{
		setProductTypes( getSession().getSessionContext(), value );
	}
	
}
