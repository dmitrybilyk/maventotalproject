/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.BusinessPartnerId BusinessPartnerId}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBusinessPartnerId extends GenericItem
{
	/** Qualifier of the <code>BusinessPartnerId.customer</code> attribute **/
	public static final String CUSTOMER = "customer";
	/** Qualifier of the <code>BusinessPartnerId.id</code> attribute **/
	public static final String ID = "id";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n CUSTOMER's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedBusinessPartnerId> CUSTOMERHANDLER = new BidirectionalOneToManyHandler<GeneratedBusinessPartnerId>(
	PacoshopCoreConstants.TC.BUSINESSPARTNERID,
	false,
	"customer",
	null,
	false,
	true,
	CollectionType.SET
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CUSTOMER, AttributeMode.INITIAL);
		tmp.put(ID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		CUSTOMERHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BusinessPartnerId.customer</code> attribute.
	 * @return the customer
	 */
	public Customer getCustomer(final SessionContext ctx)
	{
		return (Customer)getProperty( ctx, CUSTOMER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BusinessPartnerId.customer</code> attribute.
	 * @return the customer
	 */
	public Customer getCustomer()
	{
		return getCustomer( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BusinessPartnerId.customer</code> attribute. 
	 * @param value the customer
	 */
	public void setCustomer(final SessionContext ctx, final Customer value)
	{
		CUSTOMERHANDLER.addValue( ctx, value, this  );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BusinessPartnerId.customer</code> attribute. 
	 * @param value the customer
	 */
	public void setCustomer(final Customer value)
	{
		setCustomer( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BusinessPartnerId.id</code> attribute.
	 * @return the id
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BusinessPartnerId.id</code> attribute.
	 * @return the id
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BusinessPartnerId.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BusinessPartnerId.id</code> attribute. 
	 * @param value the id
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
}
