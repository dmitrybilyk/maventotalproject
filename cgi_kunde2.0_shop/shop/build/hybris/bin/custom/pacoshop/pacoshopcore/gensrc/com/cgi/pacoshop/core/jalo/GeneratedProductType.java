/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.jalo.PaymentType;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.jalo.ProductType ProductType}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedProductType extends GenericItem
{
	/** Qualifier of the <code>ProductType.productGroup</code> attribute **/
	public static final String PRODUCTGROUP = "productGroup";
	/** Qualifier of the <code>ProductType.sendInvoice</code> attribute **/
	public static final String SENDINVOICE = "sendInvoice";
	/** Qualifier of the <code>ProductType.productClass</code> attribute **/
	public static final String PRODUCTCLASS = "productClass";
	/** Qualifier of the <code>ProductType.productCluster</code> attribute **/
	public static final String PRODUCTCLUSTER = "productCluster";
	/** Qualifier of the <code>ProductType.paymentTypes</code> attribute **/
	public static final String PAYMENTTYPES = "paymentTypes";
	/** Qualifier of the <code>ProductType.code</code> attribute **/
	public static final String CODE = "code";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(PRODUCTGROUP, AttributeMode.INITIAL);
		tmp.put(SENDINVOICE, AttributeMode.INITIAL);
		tmp.put(PRODUCTCLASS, AttributeMode.INITIAL);
		tmp.put(PRODUCTCLUSTER, AttributeMode.INITIAL);
		tmp.put(PAYMENTTYPES, AttributeMode.INITIAL);
		tmp.put(CODE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.code</code> attribute.
	 * @return the code - Unique ID of the product type
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.code</code> attribute.
	 * @return the code - Unique ID of the product type
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.code</code> attribute. 
	 * @param value the code - Unique ID of the product type
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.code</code> attribute. 
	 * @param value the code - Unique ID of the product type
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.paymentTypes</code> attribute.
	 * @return the paymentTypes - List of payment types with which a product of this type can be paid
	 */
	public List<PaymentType> getPaymentTypes(final SessionContext ctx)
	{
		List<PaymentType> coll = (List<PaymentType>)getProperty( ctx, PAYMENTTYPES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.paymentTypes</code> attribute.
	 * @return the paymentTypes - List of payment types with which a product of this type can be paid
	 */
	public List<PaymentType> getPaymentTypes()
	{
		return getPaymentTypes( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.paymentTypes</code> attribute. 
	 * @param value the paymentTypes - List of payment types with which a product of this type can be paid
	 */
	public void setPaymentTypes(final SessionContext ctx, final List<PaymentType> value)
	{
		setProperty(ctx, PAYMENTTYPES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.paymentTypes</code> attribute. 
	 * @param value the paymentTypes - List of payment types with which a product of this type can be paid
	 */
	public void setPaymentTypes(final List<PaymentType> value)
	{
		setPaymentTypes( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.productClass</code> attribute.
	 * @return the productClass - The "Product class" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public EnumerationValue getProductClass(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PRODUCTCLASS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.productClass</code> attribute.
	 * @return the productClass - The "Product class" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public EnumerationValue getProductClass()
	{
		return getProductClass( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.productClass</code> attribute. 
	 * @param value the productClass - The "Product class" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public void setProductClass(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PRODUCTCLASS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.productClass</code> attribute. 
	 * @param value the productClass - The "Product class" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public void setProductClass(final EnumerationValue value)
	{
		setProductClass( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.productCluster</code> attribute.
	 * @return the productCluster - The "Product cluster" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public EnumerationValue getProductCluster(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PRODUCTCLUSTER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.productCluster</code> attribute.
	 * @return the productCluster - The "Product cluster" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public EnumerationValue getProductCluster()
	{
		return getProductCluster( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.productCluster</code> attribute. 
	 * @param value the productCluster - The "Product cluster" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public void setProductCluster(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PRODUCTCLUSTER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.productCluster</code> attribute. 
	 * @param value the productCluster - The "Product cluster" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public void setProductCluster(final EnumerationValue value)
	{
		setProductCluster( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.productGroup</code> attribute.
	 * @return the productGroup - The "Product group" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public EnumerationValue getProductGroup(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PRODUCTGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.productGroup</code> attribute.
	 * @return the productGroup - The "Product group" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public EnumerationValue getProductGroup()
	{
		return getProductGroup( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.productGroup</code> attribute. 
	 * @param value the productGroup - The "Product group" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public void setProductGroup(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PRODUCTGROUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.productGroup</code> attribute. 
	 * @param value the productGroup - The "Product group" value delivered by SAP adapter for a more detailed
	 *                             classification of products
	 */
	public void setProductGroup(final EnumerationValue value)
	{
		setProductGroup( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.sendInvoice</code> attribute.
	 * @return the sendInvoice - Enumeration value which indicates if an invoice should be sent for purchase of this
	 *                             product.
	 */
	public EnumerationValue getSendInvoice(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, SENDINVOICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ProductType.sendInvoice</code> attribute.
	 * @return the sendInvoice - Enumeration value which indicates if an invoice should be sent for purchase of this
	 *                             product.
	 */
	public EnumerationValue getSendInvoice()
	{
		return getSendInvoice( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.sendInvoice</code> attribute. 
	 * @param value the sendInvoice - Enumeration value which indicates if an invoice should be sent for purchase of this
	 *                             product.
	 */
	public void setSendInvoice(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, SENDINVOICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ProductType.sendInvoice</code> attribute. 
	 * @param value the sendInvoice - Enumeration value which indicates if an invoice should be sent for purchase of this
	 *                             product.
	 */
	public void setSendInvoice(final EnumerationValue value)
	{
		setSendInvoice( getSession().getSessionContext(), value );
	}
	
}
