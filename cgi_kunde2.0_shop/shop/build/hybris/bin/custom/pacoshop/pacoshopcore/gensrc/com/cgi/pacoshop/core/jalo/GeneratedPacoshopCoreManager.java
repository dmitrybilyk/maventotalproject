/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.jalo;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import com.cgi.pacoshop.core.jalo.BillingFrequencyDiscount;
import com.cgi.pacoshop.core.jalo.Bonus;
import com.cgi.pacoshop.core.jalo.BusinessPartnerId;
import com.cgi.pacoshop.core.jalo.CampaignIdRestriction;
import com.cgi.pacoshop.core.jalo.CashBonus;
import com.cgi.pacoshop.core.jalo.DeeplinkCallback;
import com.cgi.pacoshop.core.jalo.FingerprintSecret;
import com.cgi.pacoshop.core.jalo.HeaderAuthComponent;
import com.cgi.pacoshop.core.jalo.MilesAndMoreBonus;
import com.cgi.pacoshop.core.jalo.PaymentType;
import com.cgi.pacoshop.core.jalo.PhoneNumber;
import com.cgi.pacoshop.core.jalo.ProductBonus;
import com.cgi.pacoshop.core.jalo.ProductType;
import com.cgi.pacoshop.core.jalo.ProductTypesRestriction;
import com.cgi.pacoshop.core.jalo.SSOLoginIFrameComponent;
import com.cgi.pacoshop.core.jalo.SSORegisterFormComponent;
import com.cgi.pacoshop.core.jalo.SSORegisterLightBoxComponent;
import com.cgi.pacoshop.core.jalo.StorePaymentModeFee;
import com.cgi.pacoshop.core.jalo.TermAccepted;
import com.cgi.pacoshop.core.jalo.TermVersion;
import com.cgi.pacoshop.core.jalo.Title2;
import com.cgi.pacoshop.core.jalo.VATGroup;
import com.cgi.pacoshop.core.processengine.hmc.OrderRepairProcessWizard;
import de.hybris.platform.basecommerce.jalo.site.BaseSite;
import de.hybris.platform.cms2.jalo.contents.components.CMSLinkComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.cms2.jalo.site.CMSSite;
import de.hybris.platform.europe1.jalo.PriceRow;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.C2LManager;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.media.Media;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.order.AbstractOrderEntry;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.jalo.order.payment.PaymentMode;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.product.Unit;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Address;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.store.BaseStore;
import de.hybris.platform.subscriptionservices.jalo.BillingFrequency;
import de.hybris.platform.subscriptionservices.jalo.BillingPlan;
import de.hybris.platform.subscriptionservices.jalo.BillingTime;
import de.hybris.platform.subscriptionservices.jalo.ChargeEntry;
import de.hybris.platform.subscriptionservices.jalo.RecurringChargeEntry;
import de.hybris.platform.subscriptionservices.jalo.SubscriptionPricePlan;
import de.hybris.platform.subscriptionservices.jalo.SubscriptionProduct;
import de.hybris.platform.subscriptionservices.jalo.SubscriptionTerm;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Generated class for type <code>PacoshopCoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedPacoshopCoreManager extends Extension
{
	/**
	* {@link OneToManyHandler} for handling 1:n BUSINESSPARTNERIDS's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<BusinessPartnerId> CUSTOMERBUSINESSPARTNERIDSRELATIONBUSINESSPARTNERIDSHANDLER = new OneToManyHandler<BusinessPartnerId>(
	PacoshopCoreConstants.TC.BUSINESSPARTNERID,
	false,
	"customer",
	null,
	false,
	true,
	CollectionType.SET
	);
	/**
	* {@link OneToManyHandler} for handling 1:n PAYMENTMODEFEES's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<StorePaymentModeFee> BASESTOREPAYMENTMODEFEESRELATIONPAYMENTMODEFEESHANDLER = new OneToManyHandler<StorePaymentModeFee>(
	PacoshopCoreConstants.TC.STOREPAYMENTMODEFEE,
	false,
	"store",
	null,
	false,
	true,
	CollectionType.SET
	);
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.active</code> attribute.
	 * @return the active - Indicates if the product may be sold.
	 */
	public Boolean isActive(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.ACTIVE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.active</code> attribute.
	 * @return the active - Indicates if the product may be sold.
	 */
	public Boolean isActive(final Product item)
	{
		return isActive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.active</code> attribute. 
	 * @return the active - Indicates if the product may be sold.
	 */
	public boolean isActiveAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isActive( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.active</code> attribute. 
	 * @return the active - Indicates if the product may be sold.
	 */
	public boolean isActiveAsPrimitive(final Product item)
	{
		return isActiveAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.active</code> attribute. 
	 * @param value the active - Indicates if the product may be sold.
	 */
	public void setActive(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.ACTIVE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.active</code> attribute. 
	 * @param value the active - Indicates if the product may be sold.
	 */
	public void setActive(final Product item, final Boolean value)
	{
		setActive( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.active</code> attribute. 
	 * @param value the active - Indicates if the product may be sold.
	 */
	public void setActive(final SessionContext ctx, final Product item, final boolean value)
	{
		setActive( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.active</code> attribute. 
	 * @param value the active - Indicates if the product may be sold.
	 */
	public void setActive(final Product item, final boolean value)
	{
		setActive( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute.
	 * @return the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public Boolean isAdditionalInvoiceAddressWanted(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.ADDITIONALINVOICEADDRESSWANTED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute.
	 * @return the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public Boolean isAdditionalInvoiceAddressWanted(final AbstractOrder item)
	{
		return isAdditionalInvoiceAddressWanted( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute. 
	 * @return the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public boolean isAdditionalInvoiceAddressWantedAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isAdditionalInvoiceAddressWanted( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute. 
	 * @return the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public boolean isAdditionalInvoiceAddressWantedAsPrimitive(final AbstractOrder item)
	{
		return isAdditionalInvoiceAddressWantedAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute. 
	 * @param value the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public void setAdditionalInvoiceAddressWanted(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.ADDITIONALINVOICEADDRESSWANTED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute. 
	 * @param value the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public void setAdditionalInvoiceAddressWanted(final AbstractOrder item, final Boolean value)
	{
		setAdditionalInvoiceAddressWanted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute. 
	 * @param value the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public void setAdditionalInvoiceAddressWanted(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setAdditionalInvoiceAddressWanted( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalInvoiceAddressWanted</code> attribute. 
	 * @param value the additionalInvoiceAddressWanted - A flag that indicates the customer wants to to enter an additional address for that
	 */
	public void setAdditionalInvoiceAddressWanted(final AbstractOrder item, final boolean value)
	{
		setAdditionalInvoiceAddressWanted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute.
	 * @return the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public Boolean isAdditionalShippingAddressWanted(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.ADDITIONALSHIPPINGADDRESSWANTED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute.
	 * @return the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public Boolean isAdditionalShippingAddressWanted(final AbstractOrder item)
	{
		return isAdditionalShippingAddressWanted( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute. 
	 * @return the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public boolean isAdditionalShippingAddressWantedAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isAdditionalShippingAddressWanted( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute. 
	 * @return the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public boolean isAdditionalShippingAddressWantedAsPrimitive(final AbstractOrder item)
	{
		return isAdditionalShippingAddressWantedAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute. 
	 * @param value the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public void setAdditionalShippingAddressWanted(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.ADDITIONALSHIPPINGADDRESSWANTED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute. 
	 * @param value the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public void setAdditionalShippingAddressWanted(final AbstractOrder item, final Boolean value)
	{
		setAdditionalShippingAddressWanted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute. 
	 * @param value the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public void setAdditionalShippingAddressWanted(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setAdditionalShippingAddressWanted( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.additionalShippingAddressWanted</code> attribute. 
	 * @param value the additionalShippingAddressWanted - A flag that indicates the customer wants to use a different billing-address
	 */
	public void setAdditionalShippingAddressWanted(final AbstractOrder item, final boolean value)
	{
		setAdditionalShippingAddressWanted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.addressSuffix</code> attribute.
	 * @return the addressSuffix
	 */
	public String getAddressSuffix(final SessionContext ctx, final Address item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.ADDRESSSUFFIX);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.addressSuffix</code> attribute.
	 * @return the addressSuffix
	 */
	public String getAddressSuffix(final Address item)
	{
		return getAddressSuffix( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.addressSuffix</code> attribute. 
	 * @param value the addressSuffix
	 */
	public void setAddressSuffix(final SessionContext ctx, final Address item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.ADDRESSSUFFIX,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.addressSuffix</code> attribute. 
	 * @param value the addressSuffix
	 */
	public void setAddressSuffix(final Address item, final String value)
	{
		setAddressSuffix( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.agb</code> attribute.
	 * @return the agb
	 */
	public Boolean isAgb(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.AGB);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.agb</code> attribute.
	 * @return the agb
	 */
	public Boolean isAgb(final AbstractOrder item)
	{
		return isAgb( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.agb</code> attribute. 
	 * @return the agb
	 */
	public boolean isAgbAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isAgb( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.agb</code> attribute. 
	 * @return the agb
	 */
	public boolean isAgbAsPrimitive(final AbstractOrder item)
	{
		return isAgbAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.agb</code> attribute. 
	 * @param value the agb
	 */
	public void setAgb(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.AGB,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.agb</code> attribute. 
	 * @param value the agb
	 */
	public void setAgb(final AbstractOrder item, final Boolean value)
	{
		setAgb( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.agb</code> attribute. 
	 * @param value the agb
	 */
	public void setAgb(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setAgb( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.agb</code> attribute. 
	 * @param value the agb
	 */
	public void setAgb(final AbstractOrder item, final boolean value)
	{
		setAgb( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.allowedPaymentType</code> attribute.
	 * @return the allowedPaymentType - List of payment types with which a product of this type can be paid
	 */
	public List<PaymentType> getAllowedPaymentType(final SessionContext ctx, final AbstractOrder item)
	{
		List<PaymentType> coll = (List<PaymentType>)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.ALLOWEDPAYMENTTYPE);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.allowedPaymentType</code> attribute.
	 * @return the allowedPaymentType - List of payment types with which a product of this type can be paid
	 */
	public List<PaymentType> getAllowedPaymentType(final AbstractOrder item)
	{
		return getAllowedPaymentType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.allowedPaymentType</code> attribute. 
	 * @param value the allowedPaymentType - List of payment types with which a product of this type can be paid
	 */
	public void setAllowedPaymentType(final SessionContext ctx, final AbstractOrder item, final List<PaymentType> value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.ALLOWEDPAYMENTTYPE,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.allowedPaymentType</code> attribute. 
	 * @param value the allowedPaymentType - List of payment types with which a product of this type can be paid
	 */
	public void setAllowedPaymentType(final AbstractOrder item, final List<PaymentType> value)
	{
		setAllowedPaymentType( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.billingFrequencyDiscounts</code> attribute.
	 * @return the billingFrequencyDiscounts
	 */
	public List<BillingFrequencyDiscount> getBillingFrequencyDiscounts(final SessionContext ctx, final SubscriptionProduct item)
	{
		List<BillingFrequencyDiscount> coll = (List<BillingFrequencyDiscount>)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BILLINGFREQUENCYDISCOUNTS);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.billingFrequencyDiscounts</code> attribute.
	 * @return the billingFrequencyDiscounts
	 */
	public List<BillingFrequencyDiscount> getBillingFrequencyDiscounts(final SubscriptionProduct item)
	{
		return getBillingFrequencyDiscounts( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.billingFrequencyDiscounts</code> attribute. 
	 * @param value the billingFrequencyDiscounts
	 */
	public void setBillingFrequencyDiscounts(final SessionContext ctx, final SubscriptionProduct item, final List<BillingFrequencyDiscount> value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BILLINGFREQUENCYDISCOUNTS,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.billingFrequencyDiscounts</code> attribute. 
	 * @param value the billingFrequencyDiscounts
	 */
	public void setBillingFrequencyDiscounts(final SubscriptionProduct item, final List<BillingFrequencyDiscount> value)
	{
		setBillingFrequencyDiscounts( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusDescription</code> attribute.
	 * @return the bonusDescription
	 */
	public String getBonusDescription(final SessionContext ctx, final SubscriptionProduct item)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSubscriptionProduct.getBonusDescription requires a session language", 0 );
		}
		return (String)item.getLocalizedProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusDescription</code> attribute.
	 * @return the bonusDescription
	 */
	public String getBonusDescription(final SubscriptionProduct item)
	{
		return getBonusDescription( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusDescription</code> attribute. 
	 * @return the localized bonusDescription
	 */
	public Map<Language,String> getAllBonusDescription(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Map<Language,String>)item.getAllLocalizedProperties(ctx,PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSDESCRIPTION,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusDescription</code> attribute. 
	 * @return the localized bonusDescription
	 */
	public Map<Language,String> getAllBonusDescription(final SubscriptionProduct item)
	{
		return getAllBonusDescription( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusDescription</code> attribute. 
	 * @param value the bonusDescription
	 */
	public void setBonusDescription(final SessionContext ctx, final SubscriptionProduct item, final String value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSubscriptionProduct.setBonusDescription requires a session language", 0 );
		}
		item.setLocalizedProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusDescription</code> attribute. 
	 * @param value the bonusDescription
	 */
	public void setBonusDescription(final SubscriptionProduct item, final String value)
	{
		setBonusDescription( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusDescription</code> attribute. 
	 * @param value the bonusDescription
	 */
	public void setAllBonusDescription(final SessionContext ctx, final SubscriptionProduct item, final Map<Language,String> value)
	{
		item.setAllLocalizedProperties(ctx,PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusDescription</code> attribute. 
	 * @param value the bonusDescription
	 */
	public void setAllBonusDescription(final SubscriptionProduct item, final Map<Language,String> value)
	{
		setAllBonusDescription( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonuses</code> attribute.
	 * @return the bonuses - Through this attribute additional bonuses can be added to an offer. Buyers (e.g.
	 *                             upon RCR offer of the canvasser) additionally obtains theses bonuses when buying a
	 *                             subscription.
	 */
	public List<Bonus> getBonuses(final SessionContext ctx, final SubscriptionProduct item)
	{
		List<Bonus> coll = (List<Bonus>)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonuses</code> attribute.
	 * @return the bonuses - Through this attribute additional bonuses can be added to an offer. Buyers (e.g.
	 *                             upon RCR offer of the canvasser) additionally obtains theses bonuses when buying a
	 *                             subscription.
	 */
	public List<Bonus> getBonuses(final SubscriptionProduct item)
	{
		return getBonuses( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonuses</code> attribute. 
	 * @param value the bonuses - Through this attribute additional bonuses can be added to an offer. Buyers (e.g.
	 *                             upon RCR offer of the canvasser) additionally obtains theses bonuses when buying a
	 *                             subscription.
	 */
	public void setBonuses(final SessionContext ctx, final SubscriptionProduct item, final List<Bonus> value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonuses</code> attribute. 
	 * @param value the bonuses - Through this attribute additional bonuses can be added to an offer. Buyers (e.g.
	 *                             upon RCR offer of the canvasser) additionally obtains theses bonuses when buying a
	 *                             subscription.
	 */
	public void setBonuses(final SubscriptionProduct item, final List<Bonus> value)
	{
		setBonuses( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusImage</code> attribute.
	 * @return the bonusImage
	 */
	public Media getBonusImage(final SessionContext ctx, final SubscriptionProduct item)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSubscriptionProduct.getBonusImage requires a session language", 0 );
		}
		return (Media)item.getLocalizedProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSIMAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusImage</code> attribute.
	 * @return the bonusImage
	 */
	public Media getBonusImage(final SubscriptionProduct item)
	{
		return getBonusImage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusImage</code> attribute. 
	 * @return the localized bonusImage
	 */
	public Map<Language,Media> getAllBonusImage(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Map<Language,Media>)item.getAllLocalizedProperties(ctx,PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSIMAGE,C2LManager.getInstance().getAllLanguages());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.bonusImage</code> attribute. 
	 * @return the localized bonusImage
	 */
	public Map<Language,Media> getAllBonusImage(final SubscriptionProduct item)
	{
		return getAllBonusImage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusImage</code> attribute. 
	 * @param value the bonusImage
	 */
	public void setBonusImage(final SessionContext ctx, final SubscriptionProduct item, final Media value)
	{
		if( ctx == null || ctx.getLanguage() == null )
		{
			throw new JaloInvalidParameterException("GeneratedSubscriptionProduct.setBonusImage requires a session language", 0 );
		}
		item.setLocalizedProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusImage</code> attribute. 
	 * @param value the bonusImage
	 */
	public void setBonusImage(final SubscriptionProduct item, final Media value)
	{
		setBonusImage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusImage</code> attribute. 
	 * @param value the bonusImage
	 */
	public void setAllBonusImage(final SessionContext ctx, final SubscriptionProduct item, final Map<Language,Media> value)
	{
		item.setAllLocalizedProperties(ctx,PacoshopCoreConstants.Attributes.SubscriptionProduct.BONUSIMAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.bonusImage</code> attribute. 
	 * @param value the bonusImage
	 */
	public void setAllBonusImage(final SubscriptionProduct item, final Map<Language,Media> value)
	{
		setAllBonusImage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.bonusImageUrl</code> attribute.
	 * @return the bonusImageUrl
	 */
	public String getBonusImageUrl(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.BONUSIMAGEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.bonusImageUrl</code> attribute.
	 * @return the bonusImageUrl
	 */
	public String getBonusImageUrl(final AbstractOrderEntry item)
	{
		return getBonusImageUrl( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.bonusImageUrl</code> attribute. 
	 * @param value the bonusImageUrl
	 */
	public void setBonusImageUrl(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.BONUSIMAGEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.bonusImageUrl</code> attribute. 
	 * @param value the bonusImageUrl
	 */
	public void setBonusImageUrl(final AbstractOrderEntry item, final String value)
	{
		setBonusImageUrl( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.bonusRecipientAddress</code> attribute.
	 * @return the bonusRecipientAddress
	 */
	public Address getBonusRecipientAddress(final SessionContext ctx, final AbstractOrder item)
	{
		return (Address)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.BONUSRECIPIENTADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.bonusRecipientAddress</code> attribute.
	 * @return the bonusRecipientAddress
	 */
	public Address getBonusRecipientAddress(final AbstractOrder item)
	{
		return getBonusRecipientAddress( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.bonusRecipientAddress</code> attribute. 
	 * @param value the bonusRecipientAddress
	 */
	public void setBonusRecipientAddress(final SessionContext ctx, final AbstractOrder item, final Address value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.BONUSRECIPIENTADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.bonusRecipientAddress</code> attribute. 
	 * @param value the bonusRecipientAddress
	 */
	public void setBonusRecipientAddress(final AbstractOrder item, final Address value)
	{
		setBonusRecipientAddress( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.bonusRecipientPaymentInfo</code> attribute.
	 * @return the bonusRecipientPaymentInfo
	 */
	public PaymentInfo getBonusRecipientPaymentInfo(final SessionContext ctx, final AbstractOrder item)
	{
		return (PaymentInfo)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.BONUSRECIPIENTPAYMENTINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.bonusRecipientPaymentInfo</code> attribute.
	 * @return the bonusRecipientPaymentInfo
	 */
	public PaymentInfo getBonusRecipientPaymentInfo(final AbstractOrder item)
	{
		return getBonusRecipientPaymentInfo( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.bonusRecipientPaymentInfo</code> attribute. 
	 * @param value the bonusRecipientPaymentInfo
	 */
	public void setBonusRecipientPaymentInfo(final SessionContext ctx, final AbstractOrder item, final PaymentInfo value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.BONUSRECIPIENTPAYMENTINFO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.bonusRecipientPaymentInfo</code> attribute. 
	 * @param value the bonusRecipientPaymentInfo
	 */
	public void setBonusRecipientPaymentInfo(final AbstractOrder item, final PaymentInfo value)
	{
		setBonusRecipientPaymentInfo( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.businessNumber</code> attribute.
	 * @return the businessNumber
	 */
	public PhoneNumber getBusinessNumber(final SessionContext ctx, final Address item)
	{
		return (PhoneNumber)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.BUSINESSNUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.businessNumber</code> attribute.
	 * @return the businessNumber
	 */
	public PhoneNumber getBusinessNumber(final Address item)
	{
		return getBusinessNumber( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.businessNumber</code> attribute. 
	 * @param value the businessNumber
	 */
	public void setBusinessNumber(final SessionContext ctx, final Address item, final PhoneNumber value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.BUSINESSNUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.businessNumber</code> attribute. 
	 * @param value the businessNumber
	 */
	public void setBusinessNumber(final Address item, final PhoneNumber value)
	{
		setBusinessNumber( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.businessPartnerId</code> attribute.
	 * @return the businessPartnerId
	 */
	public String getBusinessPartnerId(final SessionContext ctx, final Address item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.BUSINESSPARTNERID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.businessPartnerId</code> attribute.
	 * @return the businessPartnerId
	 */
	public String getBusinessPartnerId(final Address item)
	{
		return getBusinessPartnerId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.businessPartnerId</code> attribute. 
	 * @param value the businessPartnerId
	 */
	public void setBusinessPartnerId(final SessionContext ctx, final Address item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.BUSINESSPARTNERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.businessPartnerId</code> attribute. 
	 * @param value the businessPartnerId
	 */
	public void setBusinessPartnerId(final Address item, final String value)
	{
		setBusinessPartnerId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.businessPartnerIds</code> attribute.
	 * @return the businessPartnerIds
	 */
	public Set<BusinessPartnerId> getBusinessPartnerIds(final SessionContext ctx, final Customer item)
	{
		return (Set<BusinessPartnerId>)CUSTOMERBUSINESSPARTNERIDSRELATIONBUSINESSPARTNERIDSHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.businessPartnerIds</code> attribute.
	 * @return the businessPartnerIds
	 */
	public Set<BusinessPartnerId> getBusinessPartnerIds(final Customer item)
	{
		return getBusinessPartnerIds( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.businessPartnerIds</code> attribute. 
	 * @param value the businessPartnerIds
	 */
	public void setBusinessPartnerIds(final SessionContext ctx, final Customer item, final Set<BusinessPartnerId> value)
	{
		CUSTOMERBUSINESSPARTNERIDSRELATIONBUSINESSPARTNERIDSHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.businessPartnerIds</code> attribute. 
	 * @param value the businessPartnerIds
	 */
	public void setBusinessPartnerIds(final Customer item, final Set<BusinessPartnerId> value)
	{
		setBusinessPartnerIds( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to businessPartnerIds. 
	 * @param value the item to add to businessPartnerIds
	 */
	public void addToBusinessPartnerIds(final SessionContext ctx, final Customer item, final BusinessPartnerId value)
	{
		CUSTOMERBUSINESSPARTNERIDSRELATIONBUSINESSPARTNERIDSHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to businessPartnerIds. 
	 * @param value the item to add to businessPartnerIds
	 */
	public void addToBusinessPartnerIds(final Customer item, final BusinessPartnerId value)
	{
		addToBusinessPartnerIds( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from businessPartnerIds. 
	 * @param value the item to remove from businessPartnerIds
	 */
	public void removeFromBusinessPartnerIds(final SessionContext ctx, final Customer item, final BusinessPartnerId value)
	{
		CUSTOMERBUSINESSPARTNERIDSRELATIONBUSINESSPARTNERIDSHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from businessPartnerIds. 
	 * @param value the item to remove from businessPartnerIds
	 */
	public void removeFromBusinessPartnerIds(final Customer item, final BusinessPartnerId value)
	{
		removeFromBusinessPartnerIds( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.charset</code> attribute.
	 * @return the charset - Charset of binded resource
	 */
	public String getCharset(final SessionContext ctx, final CMSLinkComponent item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.CHARSET);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.charset</code> attribute.
	 * @return the charset - Charset of binded resource
	 */
	public String getCharset(final CMSLinkComponent item)
	{
		return getCharset( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.charset</code> attribute. 
	 * @param value the charset - Charset of binded resource
	 */
	public void setCharset(final SessionContext ctx, final CMSLinkComponent item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.CHARSET,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.charset</code> attribute. 
	 * @param value the charset - Charset of binded resource
	 */
	public void setCharset(final CMSLinkComponent item, final String value)
	{
		setCharset( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.clientOffer</code> attribute.
	 * @return the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public Boolean isClientOffer(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.CLIENTOFFER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.clientOffer</code> attribute.
	 * @return the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public Boolean isClientOffer(final SubscriptionProduct item)
	{
		return isClientOffer( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.clientOffer</code> attribute. 
	 * @return the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public boolean isClientOfferAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isClientOffer( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.clientOffer</code> attribute. 
	 * @return the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public boolean isClientOfferAsPrimitive(final SubscriptionProduct item)
	{
		return isClientOfferAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.clientOffer</code> attribute. 
	 * @param value the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public void setClientOffer(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.CLIENTOFFER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.clientOffer</code> attribute. 
	 * @param value the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public void setClientOffer(final SubscriptionProduct item, final Boolean value)
	{
		setClientOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.clientOffer</code> attribute. 
	 * @param value the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public void setClientOffer(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setClientOffer( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.clientOffer</code> attribute. 
	 * @param value the clientOffer - It means regular customer offer, when the buyer must be a purchaser of an existing
	 *                             order.
	 */
	public void setClientOffer(final SubscriptionProduct item, final boolean value)
	{
		setClientOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final AbstractOrderEntry item)
	{
		return getCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final AbstractOrderEntry item, final String value)
	{
		setCode( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionPricePlan.communicatedAdvantage</code> attribute.
	 * @return the communicatedAdvantage - Can be used to communicate the advantages of this payment plans to customer, e.g.
	 *                             5% discount for yearly payment method".
	 */
	public String getCommunicatedAdvantage(final SessionContext ctx, final SubscriptionPricePlan item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionPricePlan.COMMUNICATEDADVANTAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionPricePlan.communicatedAdvantage</code> attribute.
	 * @return the communicatedAdvantage - Can be used to communicate the advantages of this payment plans to customer, e.g.
	 *                             5% discount for yearly payment method".
	 */
	public String getCommunicatedAdvantage(final SubscriptionPricePlan item)
	{
		return getCommunicatedAdvantage( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionPricePlan.communicatedAdvantage</code> attribute. 
	 * @param value the communicatedAdvantage - Can be used to communicate the advantages of this payment plans to customer, e.g.
	 *                             5% discount for yearly payment method".
	 */
	public void setCommunicatedAdvantage(final SessionContext ctx, final SubscriptionPricePlan item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionPricePlan.COMMUNICATEDADVANTAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionPricePlan.communicatedAdvantage</code> attribute. 
	 * @param value the communicatedAdvantage - Can be used to communicate the advantages of this payment plans to customer, e.g.
	 *                             5% discount for yearly payment method".
	 */
	public void setCommunicatedAdvantage(final SubscriptionPricePlan item, final String value)
	{
		setCommunicatedAdvantage( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionPricePlan.communicatedBillingFrequency</code> attribute.
	 * @return the communicatedBillingFrequency - The payment periodicity communicated to customer.
	 */
	public String getCommunicatedBillingFrequency(final SessionContext ctx, final SubscriptionPricePlan item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionPricePlan.COMMUNICATEDBILLINGFREQUENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionPricePlan.communicatedBillingFrequency</code> attribute.
	 * @return the communicatedBillingFrequency - The payment periodicity communicated to customer.
	 */
	public String getCommunicatedBillingFrequency(final SubscriptionPricePlan item)
	{
		return getCommunicatedBillingFrequency( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionPricePlan.communicatedBillingFrequency</code> attribute. 
	 * @param value the communicatedBillingFrequency - The payment periodicity communicated to customer.
	 */
	public void setCommunicatedBillingFrequency(final SessionContext ctx, final SubscriptionPricePlan item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionPricePlan.COMMUNICATEDBILLINGFREQUENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionPricePlan.communicatedBillingFrequency</code> attribute. 
	 * @param value the communicatedBillingFrequency - The payment periodicity communicated to customer.
	 */
	public void setCommunicatedBillingFrequency(final SubscriptionPricePlan item, final String value)
	{
		setCommunicatedBillingFrequency( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.communicatedBillingFrequency</code> attribute.
	 * @return the communicatedBillingFrequency
	 */
	public BillingFrequency getCommunicatedBillingFrequency(final SessionContext ctx, final RecurringChargeEntry item)
	{
		return (BillingFrequency)item.getProperty( ctx, PacoshopCoreConstants.Attributes.RecurringChargeEntry.COMMUNICATEDBILLINGFREQUENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.communicatedBillingFrequency</code> attribute.
	 * @return the communicatedBillingFrequency
	 */
	public BillingFrequency getCommunicatedBillingFrequency(final RecurringChargeEntry item)
	{
		return getCommunicatedBillingFrequency( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.communicatedBillingFrequency</code> attribute. 
	 * @param value the communicatedBillingFrequency
	 */
	public void setCommunicatedBillingFrequency(final SessionContext ctx, final RecurringChargeEntry item, final BillingFrequency value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.RecurringChargeEntry.COMMUNICATEDBILLINGFREQUENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.communicatedBillingFrequency</code> attribute. 
	 * @param value the communicatedBillingFrequency
	 */
	public void setCommunicatedBillingFrequency(final RecurringChargeEntry item, final BillingFrequency value)
	{
		setCommunicatedBillingFrequency( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionPricePlan.communicatedPrice</code> attribute.
	 * @return the communicatedPrice - The price communicated to customer.
	 */
	public String getCommunicatedPrice(final SessionContext ctx, final SubscriptionPricePlan item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionPricePlan.COMMUNICATEDPRICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionPricePlan.communicatedPrice</code> attribute.
	 * @return the communicatedPrice - The price communicated to customer.
	 */
	public String getCommunicatedPrice(final SubscriptionPricePlan item)
	{
		return getCommunicatedPrice( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionPricePlan.communicatedPrice</code> attribute. 
	 * @param value the communicatedPrice - The price communicated to customer.
	 */
	public void setCommunicatedPrice(final SessionContext ctx, final SubscriptionPricePlan item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionPricePlan.COMMUNICATEDPRICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionPricePlan.communicatedPrice</code> attribute. 
	 * @param value the communicatedPrice - The price communicated to customer.
	 */
	public void setCommunicatedPrice(final SubscriptionPricePlan item, final String value)
	{
		setCommunicatedPrice( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute.
	 * @return the communicatedPrice
	 */
	public Double getCommunicatedPrice(final SessionContext ctx, final RecurringChargeEntry item)
	{
		return (Double)item.getProperty( ctx, PacoshopCoreConstants.Attributes.RecurringChargeEntry.COMMUNICATEDPRICE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute.
	 * @return the communicatedPrice
	 */
	public Double getCommunicatedPrice(final RecurringChargeEntry item)
	{
		return getCommunicatedPrice( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute. 
	 * @return the communicatedPrice
	 */
	public double getCommunicatedPriceAsPrimitive(final SessionContext ctx, final RecurringChargeEntry item)
	{
		Double value = getCommunicatedPrice( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute. 
	 * @return the communicatedPrice
	 */
	public double getCommunicatedPriceAsPrimitive(final RecurringChargeEntry item)
	{
		return getCommunicatedPriceAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute. 
	 * @param value the communicatedPrice
	 */
	public void setCommunicatedPrice(final SessionContext ctx, final RecurringChargeEntry item, final Double value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.RecurringChargeEntry.COMMUNICATEDPRICE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute. 
	 * @param value the communicatedPrice
	 */
	public void setCommunicatedPrice(final RecurringChargeEntry item, final Double value)
	{
		setCommunicatedPrice( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute. 
	 * @param value the communicatedPrice
	 */
	public void setCommunicatedPrice(final SessionContext ctx, final RecurringChargeEntry item, final double value)
	{
		setCommunicatedPrice( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.communicatedPrice</code> attribute. 
	 * @param value the communicatedPrice
	 */
	public void setCommunicatedPrice(final RecurringChargeEntry item, final double value)
	{
		setCommunicatedPrice( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingPlan.consecutiveBillingFrequency</code> attribute.
	 * @return the consecutiveBillingFrequency
	 */
	public BillingFrequency getConsecutiveBillingFrequency(final SessionContext ctx, final BillingPlan item)
	{
		return (BillingFrequency)item.getProperty( ctx, PacoshopCoreConstants.Attributes.BillingPlan.CONSECUTIVEBILLINGFREQUENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingPlan.consecutiveBillingFrequency</code> attribute.
	 * @return the consecutiveBillingFrequency
	 */
	public BillingFrequency getConsecutiveBillingFrequency(final BillingPlan item)
	{
		return getConsecutiveBillingFrequency( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingPlan.consecutiveBillingFrequency</code> attribute. 
	 * @param value the consecutiveBillingFrequency
	 */
	public void setConsecutiveBillingFrequency(final SessionContext ctx, final BillingPlan item, final BillingFrequency value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.BillingPlan.CONSECUTIVEBILLINGFREQUENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingPlan.consecutiveBillingFrequency</code> attribute. 
	 * @param value the consecutiveBillingFrequency
	 */
	public void setConsecutiveBillingFrequency(final BillingPlan item, final BillingFrequency value)
	{
		setConsecutiveBillingFrequency( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.consecutiveSubscriptionTerm</code> attribute.
	 * @return the consecutiveSubscriptionTerm - If trial subscription offer is meant, which is automatically extended if not
	 *                             canceled, then this field contains the terms for the subsequent subscription.
	 */
	public SubscriptionTerm getConsecutiveSubscriptionTerm(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (SubscriptionTerm)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.CONSECUTIVESUBSCRIPTIONTERM);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.consecutiveSubscriptionTerm</code> attribute.
	 * @return the consecutiveSubscriptionTerm - If trial subscription offer is meant, which is automatically extended if not
	 *                             canceled, then this field contains the terms for the subsequent subscription.
	 */
	public SubscriptionTerm getConsecutiveSubscriptionTerm(final SubscriptionProduct item)
	{
		return getConsecutiveSubscriptionTerm( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.consecutiveSubscriptionTerm</code> attribute. 
	 * @param value the consecutiveSubscriptionTerm - If trial subscription offer is meant, which is automatically extended if not
	 *                             canceled, then this field contains the terms for the subsequent subscription.
	 */
	public void setConsecutiveSubscriptionTerm(final SessionContext ctx, final SubscriptionProduct item, final SubscriptionTerm value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.CONSECUTIVESUBSCRIPTIONTERM,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.consecutiveSubscriptionTerm</code> attribute. 
	 * @param value the consecutiveSubscriptionTerm - If trial subscription offer is meant, which is automatically extended if not
	 *                             canceled, then this field contains the terms for the subsequent subscription.
	 */
	public void setConsecutiveSubscriptionTerm(final SubscriptionProduct item, final SubscriptionTerm value)
	{
		setConsecutiveSubscriptionTerm( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.consigneeOffer</code> attribute.
	 * @return the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public Boolean isConsigneeOffer(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.CONSIGNEEOFFER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.consigneeOffer</code> attribute.
	 * @return the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public Boolean isConsigneeOffer(final SubscriptionProduct item)
	{
		return isConsigneeOffer( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.consigneeOffer</code> attribute. 
	 * @return the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public boolean isConsigneeOfferAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isConsigneeOffer( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.consigneeOffer</code> attribute. 
	 * @return the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public boolean isConsigneeOfferAsPrimitive(final SubscriptionProduct item)
	{
		return isConsigneeOfferAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.consigneeOffer</code> attribute. 
	 * @param value the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public void setConsigneeOffer(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.CONSIGNEEOFFER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.consigneeOffer</code> attribute. 
	 * @param value the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public void setConsigneeOffer(final SubscriptionProduct item, final Boolean value)
	{
		setConsigneeOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.consigneeOffer</code> attribute. 
	 * @param value the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public void setConsigneeOffer(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setConsigneeOffer( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.consigneeOffer</code> attribute. 
	 * @param value the consigneeOffer - It means regular customer offer, when the buyer must be a product recipient of an
	 *                             existing order.
	 */
	public void setConsigneeOffer(final SubscriptionProduct item, final boolean value)
	{
		setConsigneeOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.contentId</code> attribute.
	 * @return the contentId
	 */
	public String getContentId(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CONTENTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.contentId</code> attribute.
	 * @return the contentId
	 */
	public String getContentId(final AbstractOrderEntry item)
	{
		return getContentId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.contentId</code> attribute. 
	 * @param value the contentId
	 */
	public void setContentId(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CONTENTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.contentId</code> attribute. 
	 * @param value the contentId
	 */
	public void setContentId(final AbstractOrderEntry item, final String value)
	{
		setContentId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.contentPlatformId</code> attribute.
	 * @return the contentPlatformId
	 */
	public String getContentPlatformId(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.CONTENTPLATFORMID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.contentPlatformId</code> attribute.
	 * @return the contentPlatformId
	 */
	public String getContentPlatformId(final AbstractOrder item)
	{
		return getContentPlatformId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.contentPlatformId</code> attribute. 
	 * @param value the contentPlatformId
	 */
	public void setContentPlatformId(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.CONTENTPLATFORMID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.contentPlatformId</code> attribute. 
	 * @param value the contentPlatformId
	 */
	public void setContentPlatformId(final AbstractOrder item, final String value)
	{
		setContentPlatformId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.contentPlatformId</code> attribute.
	 * @return the contentPlatformId
	 */
	public String getContentPlatformId(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CONTENTPLATFORMID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.contentPlatformId</code> attribute.
	 * @return the contentPlatformId
	 */
	public String getContentPlatformId(final AbstractOrderEntry item)
	{
		return getContentPlatformId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.contentPlatformId</code> attribute. 
	 * @param value the contentPlatformId
	 */
	public void setContentPlatformId(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CONTENTPLATFORMID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.contentPlatformId</code> attribute. 
	 * @param value the contentPlatformId
	 */
	public void setContentPlatformId(final AbstractOrderEntry item, final String value)
	{
		setContentPlatformId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.contentTitle</code> attribute.
	 * @return the contentTitle
	 */
	public String getContentTitle(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CONTENTTITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.contentTitle</code> attribute.
	 * @return the contentTitle
	 */
	public String getContentTitle(final AbstractOrderEntry item)
	{
		return getContentTitle( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.contentTitle</code> attribute. 
	 * @param value the contentTitle
	 */
	public void setContentTitle(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.CONTENTTITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.contentTitle</code> attribute. 
	 * @param value the contentTitle
	 */
	public void setContentTitle(final AbstractOrderEntry item, final String value)
	{
		setContentTitle( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.contradictionPolicyLink</code> attribute.
	 * @return the contradictionPolicyLink - Link to the Contradiction Policy document or web page.
	 */
	public String getContradictionPolicyLink(final SessionContext ctx, final CMSSite item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.CONTRADICTIONPOLICYLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.contradictionPolicyLink</code> attribute.
	 * @return the contradictionPolicyLink - Link to the Contradiction Policy document or web page.
	 */
	public String getContradictionPolicyLink(final CMSSite item)
	{
		return getContradictionPolicyLink( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.contradictionPolicyLink</code> attribute. 
	 * @param value the contradictionPolicyLink - Link to the Contradiction Policy document or web page.
	 */
	public void setContradictionPolicyLink(final SessionContext ctx, final CMSSite item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.CONTRADICTIONPOLICYLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.contradictionPolicyLink</code> attribute. 
	 * @param value the contradictionPolicyLink - Link to the Contradiction Policy document or web page.
	 */
	public void setContradictionPolicyLink(final CMSSite item, final String value)
	{
		setContradictionPolicyLink( getSession().getSessionContext(), item, value );
	}
	
	public BillingFrequencyDiscount createBillingFrequencyDiscount(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.BILLINGFREQUENCYDISCOUNT );
			return (BillingFrequencyDiscount)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating BillingFrequencyDiscount : "+e.getMessage(), 0 );
		}
	}
	
	public BillingFrequencyDiscount createBillingFrequencyDiscount(final Map attributeValues)
	{
		return createBillingFrequencyDiscount( getSession().getSessionContext(), attributeValues );
	}
	
	public BusinessPartnerId createBusinessPartnerId(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.BUSINESSPARTNERID );
			return (BusinessPartnerId)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating BusinessPartnerId : "+e.getMessage(), 0 );
		}
	}
	
	public BusinessPartnerId createBusinessPartnerId(final Map attributeValues)
	{
		return createBusinessPartnerId( getSession().getSessionContext(), attributeValues );
	}
	
	public CampaignIdRestriction createCampaignIdRestriction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.CAMPAIGNIDRESTRICTION );
			return (CampaignIdRestriction)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CampaignIdRestriction : "+e.getMessage(), 0 );
		}
	}
	
	public CampaignIdRestriction createCampaignIdRestriction(final Map attributeValues)
	{
		return createCampaignIdRestriction( getSession().getSessionContext(), attributeValues );
	}
	
	public CashBonus createCashBonus(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.CASHBONUS );
			return (CashBonus)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CashBonus : "+e.getMessage(), 0 );
		}
	}
	
	public CashBonus createCashBonus(final Map attributeValues)
	{
		return createCashBonus( getSession().getSessionContext(), attributeValues );
	}
	
	public FingerprintSecret createFingerprintSecret(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.FINGERPRINTSECRET );
			return (FingerprintSecret)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating FingerprintSecret : "+e.getMessage(), 0 );
		}
	}
	
	public FingerprintSecret createFingerprintSecret(final Map attributeValues)
	{
		return createFingerprintSecret( getSession().getSessionContext(), attributeValues );
	}
	
	public HeaderAuthComponent createHeaderAuthComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.HEADERAUTHCOMPONENT );
			return (HeaderAuthComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating HeaderAuthComponent : "+e.getMessage(), 0 );
		}
	}
	
	public HeaderAuthComponent createHeaderAuthComponent(final Map attributeValues)
	{
		return createHeaderAuthComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public MilesAndMoreBonus createMilesAndMoreBonus(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.MILESANDMOREBONUS );
			return (MilesAndMoreBonus)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating MilesAndMoreBonus : "+e.getMessage(), 0 );
		}
	}
	
	public MilesAndMoreBonus createMilesAndMoreBonus(final Map attributeValues)
	{
		return createMilesAndMoreBonus( getSession().getSessionContext(), attributeValues );
	}
	
	public OrderRepairProcessWizard createOrderRepairProcessWizard(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.ORDERREPAIRPROCESSWIZARD );
			return (OrderRepairProcessWizard)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating OrderRepairProcessWizard : "+e.getMessage(), 0 );
		}
	}
	
	public OrderRepairProcessWizard createOrderRepairProcessWizard(final Map attributeValues)
	{
		return createOrderRepairProcessWizard( getSession().getSessionContext(), attributeValues );
	}
	
	public PaymentInfo createPacoDebitPaymentInfo(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PACODEBITPAYMENTINFO );
			return (PaymentInfo)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating PacoDebitPaymentInfo : "+e.getMessage(), 0 );
		}
	}
	
	public PaymentInfo createPacoDebitPaymentInfo(final Map attributeValues)
	{
		return createPacoDebitPaymentInfo( getSession().getSessionContext(), attributeValues );
	}
	
	public PaymentType createPaymentType(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PAYMENTTYPE );
			return (PaymentType)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating PaymentType : "+e.getMessage(), 0 );
		}
	}
	
	public PaymentType createPaymentType(final Map attributeValues)
	{
		return createPaymentType( getSession().getSessionContext(), attributeValues );
	}
	
	public PhoneNumber createPhoneNumber(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PHONENUMBER );
			return (PhoneNumber)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating PhoneNumber : "+e.getMessage(), 0 );
		}
	}
	
	public PhoneNumber createPhoneNumber(final Map attributeValues)
	{
		return createPhoneNumber( getSession().getSessionContext(), attributeValues );
	}
	
	public DeeplinkCallback createPortalId(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PORTALID );
			return (DeeplinkCallback)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating PortalId : "+e.getMessage(), 0 );
		}
	}
	
	public DeeplinkCallback createPortalId(final Map attributeValues)
	{
		return createPortalId( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductBonus createProductBonus(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PRODUCTBONUS );
			return (ProductBonus)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductBonus : "+e.getMessage(), 0 );
		}
	}
	
	public ProductBonus createProductBonus(final Map attributeValues)
	{
		return createProductBonus( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductType createProductType(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PRODUCTTYPE );
			return (ProductType)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductType : "+e.getMessage(), 0 );
		}
	}
	
	public ProductType createProductType(final Map attributeValues)
	{
		return createProductType( getSession().getSessionContext(), attributeValues );
	}
	
	public ProductTypesRestriction createProductTypesRestriction(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.PRODUCTTYPESRESTRICTION );
			return (ProductTypesRestriction)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ProductTypesRestriction : "+e.getMessage(), 0 );
		}
	}
	
	public ProductTypesRestriction createProductTypesRestriction(final Map attributeValues)
	{
		return createProductTypesRestriction( getSession().getSessionContext(), attributeValues );
	}
	
	public SSOLoginIFrameComponent createSSOLoginIFrameComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.SSOLOGINIFRAMECOMPONENT );
			return (SSOLoginIFrameComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SSOLoginIFrameComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SSOLoginIFrameComponent createSSOLoginIFrameComponent(final Map attributeValues)
	{
		return createSSOLoginIFrameComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SSORegisterFormComponent createSSORegisterFormComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.SSOREGISTERFORMCOMPONENT );
			return (SSORegisterFormComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SSORegisterFormComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SSORegisterFormComponent createSSORegisterFormComponent(final Map attributeValues)
	{
		return createSSORegisterFormComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public SSORegisterLightBoxComponent createSSORegisterLightBoxComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.SSOREGISTERLIGHTBOXCOMPONENT );
			return (SSORegisterLightBoxComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating SSORegisterLightBoxComponent : "+e.getMessage(), 0 );
		}
	}
	
	public SSORegisterLightBoxComponent createSSORegisterLightBoxComponent(final Map attributeValues)
	{
		return createSSORegisterLightBoxComponent( getSession().getSessionContext(), attributeValues );
	}
	
	public StorePaymentModeFee createStorePaymentModeFee(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.STOREPAYMENTMODEFEE );
			return (StorePaymentModeFee)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating StorePaymentModeFee : "+e.getMessage(), 0 );
		}
	}
	
	public StorePaymentModeFee createStorePaymentModeFee(final Map attributeValues)
	{
		return createStorePaymentModeFee( getSession().getSessionContext(), attributeValues );
	}
	
	public TermAccepted createTermAccepted(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.TERMACCEPTED );
			return (TermAccepted)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating TermAccepted : "+e.getMessage(), 0 );
		}
	}
	
	public TermAccepted createTermAccepted(final Map attributeValues)
	{
		return createTermAccepted( getSession().getSessionContext(), attributeValues );
	}
	
	public TermVersion createTermVersion(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.TERMVERSION );
			return (TermVersion)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating TermVersion : "+e.getMessage(), 0 );
		}
	}
	
	public TermVersion createTermVersion(final Map attributeValues)
	{
		return createTermVersion( getSession().getSessionContext(), attributeValues );
	}
	
	public Title2 createTitle2(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.TITLE2 );
			return (Title2)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Title2 : "+e.getMessage(), 0 );
		}
	}
	
	public Title2 createTitle2(final Map attributeValues)
	{
		return createTitle2( getSession().getSessionContext(), attributeValues );
	}
	
	public VATGroup createVATGroup(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( PacoshopCoreConstants.TC.VATGROUP );
			return (VATGroup)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating VATGroup : "+e.getMessage(), 0 );
		}
	}
	
	public VATGroup createVATGroup(final Map attributeValues)
	{
		return createVATGroup( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.customerAddress</code> attribute.
	 * @return the customerAddress
	 */
	public Address getCustomerAddress(final SessionContext ctx, final AbstractOrder item)
	{
		return (Address)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.CUSTOMERADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.customerAddress</code> attribute.
	 * @return the customerAddress
	 */
	public Address getCustomerAddress(final AbstractOrder item)
	{
		return getCustomerAddress( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.customerAddress</code> attribute. 
	 * @param value the customerAddress
	 */
	public void setCustomerAddress(final SessionContext ctx, final AbstractOrder item, final Address value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.CUSTOMERADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.customerAddress</code> attribute. 
	 * @param value the customerAddress
	 */
	public void setCustomerAddress(final AbstractOrder item, final Address value)
	{
		setCustomerAddress( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.customerEmail</code> attribute.
	 * @return the customerEmail - A customer email which comes from SSO. This value is also used in contactEmail field.
	 */
	public String getCustomerEmail(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.CUSTOMEREMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.customerEmail</code> attribute.
	 * @return the customerEmail - A customer email which comes from SSO. This value is also used in contactEmail field.
	 */
	public String getCustomerEmail(final Customer item)
	{
		return getCustomerEmail( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.customerEmail</code> attribute. 
	 * @param value the customerEmail - A customer email which comes from SSO. This value is also used in contactEmail field.
	 */
	public void setCustomerEmail(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.CUSTOMEREMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.customerEmail</code> attribute. 
	 * @param value the customerEmail - A customer email which comes from SSO. This value is also used in contactEmail field.
	 */
	public void setCustomerEmail(final Customer item, final String value)
	{
		setCustomerEmail( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliveryNow</code> attribute.
	 * @return the deliveryNow
	 */
	public Boolean isDeliveryNow(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.DELIVERYNOW);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliveryNow</code> attribute.
	 * @return the deliveryNow
	 */
	public Boolean isDeliveryNow(final AbstractOrder item)
	{
		return isDeliveryNow( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliveryNow</code> attribute. 
	 * @return the deliveryNow
	 */
	public boolean isDeliveryNowAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isDeliveryNow( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliveryNow</code> attribute. 
	 * @return the deliveryNow
	 */
	public boolean isDeliveryNowAsPrimitive(final AbstractOrder item)
	{
		return isDeliveryNowAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliveryNow</code> attribute. 
	 * @param value the deliveryNow
	 */
	public void setDeliveryNow(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.DELIVERYNOW,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliveryNow</code> attribute. 
	 * @param value the deliveryNow
	 */
	public void setDeliveryNow(final AbstractOrder item, final Boolean value)
	{
		setDeliveryNow( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliveryNow</code> attribute. 
	 * @param value the deliveryNow
	 */
	public void setDeliveryNow(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setDeliveryNow( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliveryNow</code> attribute. 
	 * @param value the deliveryNow
	 */
	public void setDeliveryNow(final AbstractOrder item, final boolean value)
	{
		setDeliveryNow( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliveryStartDate</code> attribute.
	 * @return the deliveryStartDate
	 */
	public Date getDeliveryStartDate(final SessionContext ctx, final AbstractOrder item)
	{
		return (Date)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.DELIVERYSTARTDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.deliveryStartDate</code> attribute.
	 * @return the deliveryStartDate
	 */
	public Date getDeliveryStartDate(final AbstractOrder item)
	{
		return getDeliveryStartDate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliveryStartDate</code> attribute. 
	 * @param value the deliveryStartDate
	 */
	public void setDeliveryStartDate(final SessionContext ctx, final AbstractOrder item, final Date value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.DELIVERYSTARTDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.deliveryStartDate</code> attribute. 
	 * @param value the deliveryStartDate
	 */
	public void setDeliveryStartDate(final AbstractOrder item, final Date value)
	{
		setDeliveryStartDate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute.
	 * @return the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public Integer getDeliveryStartDateEarliest(final SessionContext ctx, final CMSSite item)
	{
		return (Integer)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.DELIVERYSTARTDATEEARLIEST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute.
	 * @return the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public Integer getDeliveryStartDateEarliest(final CMSSite item)
	{
		return getDeliveryStartDateEarliest( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute. 
	 * @return the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public int getDeliveryStartDateEarliestAsPrimitive(final SessionContext ctx, final CMSSite item)
	{
		Integer value = getDeliveryStartDateEarliest( ctx,item );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute. 
	 * @return the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public int getDeliveryStartDateEarliestAsPrimitive(final CMSSite item)
	{
		return getDeliveryStartDateEarliestAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute. 
	 * @param value the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateEarliest(final SessionContext ctx, final CMSSite item, final Integer value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.DELIVERYSTARTDATEEARLIEST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute. 
	 * @param value the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateEarliest(final CMSSite item, final Integer value)
	{
		setDeliveryStartDateEarliest( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute. 
	 * @param value the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateEarliest(final SessionContext ctx, final CMSSite item, final int value)
	{
		setDeliveryStartDateEarliest( ctx, item, Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateEarliest</code> attribute. 
	 * @param value the deliveryStartDateEarliest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be greater or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateEarliest(final CMSSite item, final int value)
	{
		setDeliveryStartDateEarliest( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateLatest</code> attribute.
	 * @return the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public Integer getDeliveryStartDateLatest(final SessionContext ctx, final CMSSite item)
	{
		return (Integer)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.DELIVERYSTARTDATELATEST);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateLatest</code> attribute.
	 * @return the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public Integer getDeliveryStartDateLatest(final CMSSite item)
	{
		return getDeliveryStartDateLatest( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateLatest</code> attribute. 
	 * @return the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public int getDeliveryStartDateLatestAsPrimitive(final SessionContext ctx, final CMSSite item)
	{
		Integer value = getDeliveryStartDateLatest( ctx,item );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.deliveryStartDateLatest</code> attribute. 
	 * @return the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public int getDeliveryStartDateLatestAsPrimitive(final CMSSite item)
	{
		return getDeliveryStartDateLatestAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateLatest</code> attribute. 
	 * @param value the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateLatest(final SessionContext ctx, final CMSSite item, final Integer value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.DELIVERYSTARTDATELATEST,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateLatest</code> attribute. 
	 * @param value the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateLatest(final CMSSite item, final Integer value)
	{
		setDeliveryStartDateLatest( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateLatest</code> attribute. 
	 * @param value the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateLatest(final SessionContext ctx, final CMSSite item, final int value)
	{
		setDeliveryStartDateLatest( ctx, item, Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.deliveryStartDateLatest</code> attribute. 
	 * @param value the deliveryStartDateLatest - For the subscription products user could specify the date when the subscription should start.
	 *                             The delivery start date should be less or equal to the current date + this value in days.
	 */
	public void setDeliveryStartDateLatest(final CMSSite item, final int value)
	{
		setDeliveryStartDateLatest( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.description</code> attribute.
	 * @return the description
	 */
	public String getDescription(final AbstractOrderEntry item)
	{
		return getDescription( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.description</code> attribute. 
	 * @param value the description
	 */
	public void setDescription(final AbstractOrderEntry item, final String value)
	{
		setDescription( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingConsigneeAllowed</code> attribute.
	 * @return the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public Boolean isDifferingConsigneeAllowed(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.DIFFERINGCONSIGNEEALLOWED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingConsigneeAllowed</code> attribute.
	 * @return the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public Boolean isDifferingConsigneeAllowed(final Product item)
	{
		return isDifferingConsigneeAllowed( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingConsigneeAllowed</code> attribute. 
	 * @return the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public boolean isDifferingConsigneeAllowedAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isDifferingConsigneeAllowed( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingConsigneeAllowed</code> attribute. 
	 * @return the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public boolean isDifferingConsigneeAllowedAsPrimitive(final Product item)
	{
		return isDifferingConsigneeAllowedAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingConsigneeAllowed</code> attribute. 
	 * @param value the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public void setDifferingConsigneeAllowed(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.DIFFERINGCONSIGNEEALLOWED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingConsigneeAllowed</code> attribute. 
	 * @param value the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public void setDifferingConsigneeAllowed(final Product item, final Boolean value)
	{
		setDifferingConsigneeAllowed( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingConsigneeAllowed</code> attribute. 
	 * @param value the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public void setDifferingConsigneeAllowed(final SessionContext ctx, final Product item, final boolean value)
	{
		setDifferingConsigneeAllowed( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingConsigneeAllowed</code> attribute. 
	 * @param value the differingConsigneeAllowed - Indicates if a different shipping address is allowed
	 */
	public void setDifferingConsigneeAllowed(final Product item, final boolean value)
	{
		setDifferingConsigneeAllowed( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute.
	 * @return the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public Boolean isDifferingInvoiceRecipientAllowed(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.DIFFERINGINVOICERECIPIENTALLOWED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute.
	 * @return the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public Boolean isDifferingInvoiceRecipientAllowed(final Product item)
	{
		return isDifferingInvoiceRecipientAllowed( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute. 
	 * @return the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public boolean isDifferingInvoiceRecipientAllowedAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isDifferingInvoiceRecipientAllowed( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute. 
	 * @return the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public boolean isDifferingInvoiceRecipientAllowedAsPrimitive(final Product item)
	{
		return isDifferingInvoiceRecipientAllowedAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute. 
	 * @param value the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public void setDifferingInvoiceRecipientAllowed(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.DIFFERINGINVOICERECIPIENTALLOWED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute. 
	 * @param value the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public void setDifferingInvoiceRecipientAllowed(final Product item, final Boolean value)
	{
		setDifferingInvoiceRecipientAllowed( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute. 
	 * @param value the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public void setDifferingInvoiceRecipientAllowed(final SessionContext ctx, final Product item, final boolean value)
	{
		setDifferingInvoiceRecipientAllowed( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.differingInvoiceRecipientAllowed</code> attribute. 
	 * @param value the differingInvoiceRecipientAllowed - Indicates if invoice recipient different from the customer is allowed.
	 */
	public void setDifferingInvoiceRecipientAllowed(final Product item, final boolean value)
	{
		setDifferingInvoiceRecipientAllowed( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.digital</code> attribute.
	 * @return the digital - Indicates if the product is digital.
	 */
	public Boolean isDigital(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.DIGITAL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.digital</code> attribute.
	 * @return the digital - Indicates if the product is digital.
	 */
	public Boolean isDigital(final Product item)
	{
		return isDigital( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.digital</code> attribute. 
	 * @return the digital - Indicates if the product is digital.
	 */
	public boolean isDigitalAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isDigital( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.digital</code> attribute. 
	 * @return the digital - Indicates if the product is digital.
	 */
	public boolean isDigitalAsPrimitive(final Product item)
	{
		return isDigitalAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.digital</code> attribute. 
	 * @param value the digital - Indicates if the product is digital.
	 */
	public void setDigital(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.DIGITAL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.digital</code> attribute. 
	 * @param value the digital - Indicates if the product is digital.
	 */
	public void setDigital(final Product item, final Boolean value)
	{
		setDigital( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.digital</code> attribute. 
	 * @param value the digital - Indicates if the product is digital.
	 */
	public void setDigital(final SessionContext ctx, final Product item, final boolean value)
	{
		setDigital( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.digital</code> attribute. 
	 * @param value the digital - Indicates if the product is digital.
	 */
	public void setDigital(final Product item, final boolean value)
	{
		setDigital( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.dummy</code> attribute.
	 * @return the dummy - Indicates if the product is dummy.
	 */
	public Boolean isDummy(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.DUMMY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.dummy</code> attribute.
	 * @return the dummy - Indicates if the product is dummy.
	 */
	public Boolean isDummy(final Product item)
	{
		return isDummy( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.dummy</code> attribute. 
	 * @return the dummy - Indicates if the product is dummy.
	 */
	public boolean isDummyAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isDummy( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.dummy</code> attribute. 
	 * @return the dummy - Indicates if the product is dummy.
	 */
	public boolean isDummyAsPrimitive(final Product item)
	{
		return isDummyAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.dummy</code> attribute. 
	 * @param value the dummy - Indicates if the product is dummy.
	 */
	public void setDummy(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.DUMMY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.dummy</code> attribute. 
	 * @param value the dummy - Indicates if the product is dummy.
	 */
	public void setDummy(final Product item, final Boolean value)
	{
		setDummy( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.dummy</code> attribute. 
	 * @param value the dummy - Indicates if the product is dummy.
	 */
	public void setDummy(final SessionContext ctx, final Product item, final boolean value)
	{
		setDummy( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.dummy</code> attribute. 
	 * @param value the dummy - Indicates if the product is dummy.
	 */
	public void setDummy(final Product item, final boolean value)
	{
		setDummy( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.dummy</code> attribute.
	 * @return the dummy
	 */
	public Boolean isDummy(final SessionContext ctx, final Customer item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.DUMMY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.dummy</code> attribute.
	 * @return the dummy
	 */
	public Boolean isDummy(final Customer item)
	{
		return isDummy( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.dummy</code> attribute. 
	 * @return the dummy
	 */
	public boolean isDummyAsPrimitive(final SessionContext ctx, final Customer item)
	{
		Boolean value = isDummy( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.dummy</code> attribute. 
	 * @return the dummy
	 */
	public boolean isDummyAsPrimitive(final Customer item)
	{
		return isDummyAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.dummy</code> attribute. 
	 * @param value the dummy
	 */
	public void setDummy(final SessionContext ctx, final Customer item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.DUMMY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.dummy</code> attribute. 
	 * @param value the dummy
	 */
	public void setDummy(final Customer item, final Boolean value)
	{
		setDummy( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.dummy</code> attribute. 
	 * @param value the dummy
	 */
	public void setDummy(final SessionContext ctx, final Customer item, final boolean value)
	{
		setDummy( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.dummy</code> attribute. 
	 * @param value the dummy
	 */
	public void setDummy(final Customer item, final boolean value)
	{
		setDummy( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.existingSubscriptionId</code> attribute.
	 * @return the existingSubscriptionId
	 */
	public String getExistingSubscriptionId(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.EXISTINGSUBSCRIPTIONID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.existingSubscriptionId</code> attribute.
	 * @return the existingSubscriptionId
	 */
	public String getExistingSubscriptionId(final AbstractOrder item)
	{
		return getExistingSubscriptionId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.existingSubscriptionId</code> attribute. 
	 * @param value the existingSubscriptionId
	 */
	public void setExistingSubscriptionId(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.EXISTINGSUBSCRIPTIONID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.existingSubscriptionId</code> attribute. 
	 * @param value the existingSubscriptionId
	 */
	public void setExistingSubscriptionId(final AbstractOrder item, final String value)
	{
		setExistingSubscriptionId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.existingSubscriptionId</code> attribute.
	 * @return the existingSubscriptionId - Existing subscription Id
	 */
	public String getExistingSubscriptionId(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.EXISTINGSUBSCRIPTIONID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.existingSubscriptionId</code> attribute.
	 * @return the existingSubscriptionId - Existing subscription Id
	 */
	public String getExistingSubscriptionId(final Customer item)
	{
		return getExistingSubscriptionId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.existingSubscriptionId</code> attribute. 
	 * @param value the existingSubscriptionId - Existing subscription Id
	 */
	public void setExistingSubscriptionId(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.EXISTINGSUBSCRIPTIONID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.existingSubscriptionId</code> attribute. 
	 * @param value the existingSubscriptionId - Existing subscription Id
	 */
	public void setExistingSubscriptionId(final Customer item, final String value)
	{
		setExistingSubscriptionId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.externalFrequency</code> attribute.
	 * @return the externalFrequency
	 */
	public Integer getExternalFrequency(final SessionContext ctx, final BillingTime item)
	{
		return (Integer)item.getProperty( ctx, PacoshopCoreConstants.Attributes.BillingTime.EXTERNALFREQUENCY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.externalFrequency</code> attribute.
	 * @return the externalFrequency
	 */
	public Integer getExternalFrequency(final BillingTime item)
	{
		return getExternalFrequency( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.externalFrequency</code> attribute. 
	 * @return the externalFrequency
	 */
	public int getExternalFrequencyAsPrimitive(final SessionContext ctx, final BillingTime item)
	{
		Integer value = getExternalFrequency( ctx,item );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.externalFrequency</code> attribute. 
	 * @return the externalFrequency
	 */
	public int getExternalFrequencyAsPrimitive(final BillingTime item)
	{
		return getExternalFrequencyAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.externalFrequency</code> attribute. 
	 * @param value the externalFrequency
	 */
	public void setExternalFrequency(final SessionContext ctx, final BillingTime item, final Integer value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.BillingTime.EXTERNALFREQUENCY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.externalFrequency</code> attribute. 
	 * @param value the externalFrequency
	 */
	public void setExternalFrequency(final BillingTime item, final Integer value)
	{
		setExternalFrequency( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.externalFrequency</code> attribute. 
	 * @param value the externalFrequency
	 */
	public void setExternalFrequency(final SessionContext ctx, final BillingTime item, final int value)
	{
		setExternalFrequency( ctx, item, Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.externalFrequency</code> attribute. 
	 * @param value the externalFrequency
	 */
	public void setExternalFrequency(final BillingTime item, final int value)
	{
		setExternalFrequency( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.externalOfferId</code> attribute.
	 * @return the externalOfferId - Unique offer ID in the external system.
	 */
	public String getExternalOfferId(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.EXTERNALOFFERID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.externalOfferId</code> attribute.
	 * @return the externalOfferId - Unique offer ID in the external system.
	 */
	public String getExternalOfferId(final Product item)
	{
		return getExternalOfferId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.externalOfferId</code> attribute. 
	 * @param value the externalOfferId - Unique offer ID in the external system.
	 */
	public void setExternalOfferId(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.EXTERNALOFFERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.externalOfferId</code> attribute. 
	 * @param value the externalOfferId - Unique offer ID in the external system.
	 */
	public void setExternalOfferId(final Product item, final String value)
	{
		setExternalOfferId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.externalProductId</code> attribute.
	 * @return the externalProductId - Unique offer ID in the external system.
	 */
	public String getExternalProductId(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.EXTERNALPRODUCTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.externalProductId</code> attribute.
	 * @return the externalProductId - Unique offer ID in the external system.
	 */
	public String getExternalProductId(final Product item)
	{
		return getExternalProductId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.externalProductId</code> attribute. 
	 * @param value the externalProductId - Unique offer ID in the external system.
	 */
	public void setExternalProductId(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.EXTERNALPRODUCTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.externalProductId</code> attribute. 
	 * @param value the externalProductId - Unique offer ID in the external system.
	 */
	public void setExternalProductId(final Product item, final String value)
	{
		setExternalProductId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.firstName</code> attribute.
	 * @return the firstName - First Name
	 */
	public String getFirstName(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.FIRSTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.firstName</code> attribute.
	 * @return the firstName - First Name
	 */
	public String getFirstName(final Customer item)
	{
		return getFirstName( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.firstName</code> attribute. 
	 * @param value the firstName - First Name
	 */
	public void setFirstName(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.FIRSTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.firstName</code> attribute. 
	 * @param value the firstName - First Name
	 */
	public void setFirstName(final Customer item, final String value)
	{
		setFirstName( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.fulfillmentEmail</code> attribute.
	 * @return the fulfillmentEmail - email address for email fulfillment
	 */
	public String getFulfillmentEmail(final SessionContext ctx, final CMSSite item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.FULFILLMENTEMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.fulfillmentEmail</code> attribute.
	 * @return the fulfillmentEmail - email address for email fulfillment
	 */
	public String getFulfillmentEmail(final CMSSite item)
	{
		return getFulfillmentEmail( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.fulfillmentEmail</code> attribute. 
	 * @param value the fulfillmentEmail - email address for email fulfillment
	 */
	public void setFulfillmentEmail(final SessionContext ctx, final CMSSite item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.FULFILLMENTEMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.fulfillmentEmail</code> attribute. 
	 * @param value the fulfillmentEmail - email address for email fulfillment
	 */
	public void setFulfillmentEmail(final CMSSite item, final String value)
	{
		setFulfillmentEmail( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.fulfillmentType</code> attribute.
	 * @return the fulfillmentType - Indicates what type of fulfillment is needed for the product purchase
	 */
	public EnumerationValue getFulfillmentType(final SessionContext ctx, final Product item)
	{
		return (EnumerationValue)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.FULFILLMENTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.fulfillmentType</code> attribute.
	 * @return the fulfillmentType - Indicates what type of fulfillment is needed for the product purchase
	 */
	public EnumerationValue getFulfillmentType(final Product item)
	{
		return getFulfillmentType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.fulfillmentType</code> attribute. 
	 * @param value the fulfillmentType - Indicates what type of fulfillment is needed for the product purchase
	 */
	public void setFulfillmentType(final SessionContext ctx, final Product item, final EnumerationValue value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.FULFILLMENTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.fulfillmentType</code> attribute. 
	 * @param value the fulfillmentType - Indicates what type of fulfillment is needed for the product purchase
	 */
	public void setFulfillmentType(final Product item, final EnumerationValue value)
	{
		setFulfillmentType( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return PacoshopCoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.homeNumber</code> attribute.
	 * @return the homeNumber
	 */
	public PhoneNumber getHomeNumber(final SessionContext ctx, final Address item)
	{
		return (PhoneNumber)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.HOMENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.homeNumber</code> attribute.
	 * @return the homeNumber
	 */
	public PhoneNumber getHomeNumber(final Address item)
	{
		return getHomeNumber( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.homeNumber</code> attribute. 
	 * @param value the homeNumber
	 */
	public void setHomeNumber(final SessionContext ctx, final Address item, final PhoneNumber value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.HOMENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.homeNumber</code> attribute. 
	 * @param value the homeNumber
	 */
	public void setHomeNumber(final Address item, final PhoneNumber value)
	{
		setHomeNumber( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.importSource</code> attribute.
	 * @return the importSource - Stores the information from which interface the product were imported
	 */
	public EnumerationValue getImportSource(final SessionContext ctx, final Product item)
	{
		return (EnumerationValue)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.IMPORTSOURCE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.importSource</code> attribute.
	 * @return the importSource - Stores the information from which interface the product were imported
	 */
	public EnumerationValue getImportSource(final Product item)
	{
		return getImportSource( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.importSource</code> attribute. 
	 * @param value the importSource - Stores the information from which interface the product were imported
	 */
	public void setImportSource(final SessionContext ctx, final Product item, final EnumerationValue value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.IMPORTSOURCE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.importSource</code> attribute. 
	 * @param value the importSource - Stores the information from which interface the product were imported
	 */
	public void setImportSource(final Product item, final EnumerationValue value)
	{
		setImportSource( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute.
	 * @return the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public Boolean isInvoiceRecipientOffer(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.INVOICERECIPIENTOFFER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute.
	 * @return the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public Boolean isInvoiceRecipientOffer(final SubscriptionProduct item)
	{
		return isInvoiceRecipientOffer( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute. 
	 * @return the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public boolean isInvoiceRecipientOfferAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isInvoiceRecipientOffer( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute. 
	 * @return the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public boolean isInvoiceRecipientOfferAsPrimitive(final SubscriptionProduct item)
	{
		return isInvoiceRecipientOfferAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute. 
	 * @param value the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public void setInvoiceRecipientOffer(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.INVOICERECIPIENTOFFER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute. 
	 * @param value the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public void setInvoiceRecipientOffer(final SubscriptionProduct item, final Boolean value)
	{
		setInvoiceRecipientOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute. 
	 * @param value the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public void setInvoiceRecipientOffer(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setInvoiceRecipientOffer( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.invoiceRecipientOffer</code> attribute. 
	 * @param value the invoiceRecipientOffer - It means regular customer offer, when the buyer must be an invoice recipient of an
	 *                             existing order.
	 */
	public void setInvoiceRecipientOffer(final SubscriptionProduct item, final boolean value)
	{
		setInvoiceRecipientOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.invoiceWanted</code> attribute.
	 * @return the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public Boolean isInvoiceWanted(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.INVOICEWANTED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.invoiceWanted</code> attribute.
	 * @return the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public Boolean isInvoiceWanted(final AbstractOrder item)
	{
		return isInvoiceWanted( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.invoiceWanted</code> attribute. 
	 * @return the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public boolean isInvoiceWantedAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isInvoiceWanted( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.invoiceWanted</code> attribute. 
	 * @return the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public boolean isInvoiceWantedAsPrimitive(final AbstractOrder item)
	{
		return isInvoiceWantedAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.invoiceWanted</code> attribute. 
	 * @param value the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public void setInvoiceWanted(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.INVOICEWANTED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.invoiceWanted</code> attribute. 
	 * @param value the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public void setInvoiceWanted(final AbstractOrder item, final Boolean value)
	{
		setInvoiceWanted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.invoiceWanted</code> attribute. 
	 * @param value the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public void setInvoiceWanted(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setInvoiceWanted( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.invoiceWanted</code> attribute. 
	 * @param value the invoiceWanted - A flag that indicates the customer wants an invoice
	 */
	public void setInvoiceWanted(final AbstractOrder item, final boolean value)
	{
		setInvoiceWanted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.lastName</code> attribute.
	 * @return the lastName - Last Name
	 */
	public String getLastName(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.LASTNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.lastName</code> attribute.
	 * @return the lastName - Last Name
	 */
	public String getLastName(final Customer item)
	{
		return getLastName( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.lastName</code> attribute. 
	 * @param value the lastName - Last Name
	 */
	public void setLastName(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.LASTNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.lastName</code> attribute. 
	 * @param value the lastName - Last Name
	 */
	public void setLastName(final Customer item, final String value)
	{
		setLastName( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.lastUpdate</code> attribute.
	 * @return the lastUpdate
	 */
	public Date getLastUpdate(final SessionContext ctx, final Address item)
	{
		return (Date)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.LASTUPDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.lastUpdate</code> attribute.
	 * @return the lastUpdate
	 */
	public Date getLastUpdate(final Address item)
	{
		return getLastUpdate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.lastUpdate</code> attribute. 
	 * @param value the lastUpdate
	 */
	public void setLastUpdate(final SessionContext ctx, final Address item, final Date value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.LASTUPDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.lastUpdate</code> attribute. 
	 * @param value the lastUpdate
	 */
	public void setLastUpdate(final Address item, final Date value)
	{
		setLastUpdate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.link</code> attribute.
	 * @return the link - Is html tag link or a
	 */
	public Boolean isLink(final SessionContext ctx, final CMSLinkComponent item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.LINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.link</code> attribute.
	 * @return the link - Is html tag link or a
	 */
	public Boolean isLink(final CMSLinkComponent item)
	{
		return isLink( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.link</code> attribute. 
	 * @return the link - Is html tag link or a
	 */
	public boolean isLinkAsPrimitive(final SessionContext ctx, final CMSLinkComponent item)
	{
		Boolean value = isLink( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.link</code> attribute. 
	 * @return the link - Is html tag link or a
	 */
	public boolean isLinkAsPrimitive(final CMSLinkComponent item)
	{
		return isLinkAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.link</code> attribute. 
	 * @param value the link - Is html tag link or a
	 */
	public void setLink(final SessionContext ctx, final CMSLinkComponent item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.LINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.link</code> attribute. 
	 * @param value the link - Is html tag link or a
	 */
	public void setLink(final CMSLinkComponent item, final Boolean value)
	{
		setLink( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.link</code> attribute. 
	 * @param value the link - Is html tag link or a
	 */
	public void setLink(final SessionContext ctx, final CMSLinkComponent item, final boolean value)
	{
		setLink( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.link</code> attribute. 
	 * @param value the link - Is html tag link or a
	 */
	public void setLink(final CMSLinkComponent item, final boolean value)
	{
		setLink( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.mainAddress</code> attribute.
	 * @return the mainAddress
	 */
	public Address getMainAddress(final SessionContext ctx, final Customer item)
	{
		return (Address)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.MAINADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.mainAddress</code> attribute.
	 * @return the mainAddress
	 */
	public Address getMainAddress(final Customer item)
	{
		return getMainAddress( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.mainAddress</code> attribute. 
	 * @param value the mainAddress
	 */
	public void setMainAddress(final SessionContext ctx, final Customer item, final Address value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.MAINADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.mainAddress</code> attribute. 
	 * @param value the mainAddress
	 */
	public void setMainAddress(final Customer item, final Address value)
	{
		setMainAddress( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute.
	 * @return the mandatoryAddress - mandatory for customer to provide address information
	 */
	public Boolean isMandatoryAddress(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYADDRESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute.
	 * @return the mandatoryAddress - mandatory for customer to provide address information
	 */
	public Boolean isMandatoryAddress(final SubscriptionProduct item)
	{
		return isMandatoryAddress( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute. 
	 * @return the mandatoryAddress - mandatory for customer to provide address information
	 */
	public boolean isMandatoryAddressAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isMandatoryAddress( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute. 
	 * @return the mandatoryAddress - mandatory for customer to provide address information
	 */
	public boolean isMandatoryAddressAsPrimitive(final SubscriptionProduct item)
	{
		return isMandatoryAddressAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute. 
	 * @param value the mandatoryAddress - mandatory for customer to provide address information
	 */
	public void setMandatoryAddress(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYADDRESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute. 
	 * @param value the mandatoryAddress - mandatory for customer to provide address information
	 */
	public void setMandatoryAddress(final SubscriptionProduct item, final Boolean value)
	{
		setMandatoryAddress( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute. 
	 * @param value the mandatoryAddress - mandatory for customer to provide address information
	 */
	public void setMandatoryAddress(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setMandatoryAddress( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryAddress</code> attribute. 
	 * @param value the mandatoryAddress - mandatory for customer to provide address information
	 */
	public void setMandatoryAddress(final SubscriptionProduct item, final boolean value)
	{
		setMandatoryAddress( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute.
	 * @return the mandatoryEmail - mandatory for customer to provide email address
	 */
	public Boolean isMandatoryEmail(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYEMAIL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute.
	 * @return the mandatoryEmail - mandatory for customer to provide email address
	 */
	public Boolean isMandatoryEmail(final SubscriptionProduct item)
	{
		return isMandatoryEmail( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute. 
	 * @return the mandatoryEmail - mandatory for customer to provide email address
	 */
	public boolean isMandatoryEmailAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isMandatoryEmail( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute. 
	 * @return the mandatoryEmail - mandatory for customer to provide email address
	 */
	public boolean isMandatoryEmailAsPrimitive(final SubscriptionProduct item)
	{
		return isMandatoryEmailAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute. 
	 * @param value the mandatoryEmail - mandatory for customer to provide email address
	 */
	public void setMandatoryEmail(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYEMAIL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute. 
	 * @param value the mandatoryEmail - mandatory for customer to provide email address
	 */
	public void setMandatoryEmail(final SubscriptionProduct item, final Boolean value)
	{
		setMandatoryEmail( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute. 
	 * @param value the mandatoryEmail - mandatory for customer to provide email address
	 */
	public void setMandatoryEmail(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setMandatoryEmail( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryEmail</code> attribute. 
	 * @param value the mandatoryEmail - mandatory for customer to provide email address
	 */
	public void setMandatoryEmail(final SubscriptionProduct item, final boolean value)
	{
		setMandatoryEmail( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute.
	 * @return the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public Boolean isMandatoryMobile(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYMOBILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute.
	 * @return the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public Boolean isMandatoryMobile(final SubscriptionProduct item)
	{
		return isMandatoryMobile( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute. 
	 * @return the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public boolean isMandatoryMobileAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isMandatoryMobile( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute. 
	 * @return the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public boolean isMandatoryMobileAsPrimitive(final SubscriptionProduct item)
	{
		return isMandatoryMobileAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute. 
	 * @param value the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public void setMandatoryMobile(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYMOBILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute. 
	 * @param value the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public void setMandatoryMobile(final SubscriptionProduct item, final Boolean value)
	{
		setMandatoryMobile( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute. 
	 * @param value the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public void setMandatoryMobile(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setMandatoryMobile( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryMobile</code> attribute. 
	 * @param value the mandatoryMobile - mandatory for customer to provide mobile phone number
	 */
	public void setMandatoryMobile(final SubscriptionProduct item, final boolean value)
	{
		setMandatoryMobile( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute.
	 * @return the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public Boolean isMandatoryOptIn(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYOPTIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute.
	 * @return the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public Boolean isMandatoryOptIn(final SubscriptionProduct item)
	{
		return isMandatoryOptIn( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute. 
	 * @return the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public boolean isMandatoryOptInAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isMandatoryOptIn( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute. 
	 * @return the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public boolean isMandatoryOptInAsPrimitive(final SubscriptionProduct item)
	{
		return isMandatoryOptInAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute. 
	 * @param value the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public void setMandatoryOptIn(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYOPTIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute. 
	 * @param value the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public void setMandatoryOptIn(final SubscriptionProduct item, final Boolean value)
	{
		setMandatoryOptIn( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute. 
	 * @param value the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public void setMandatoryOptIn(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setMandatoryOptIn( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryOptIn</code> attribute. 
	 * @param value the mandatoryOptIn - It is mandatory for the customer to give his opt-in to being contacted with advertising
	 */
	public void setMandatoryOptIn(final SubscriptionProduct item, final boolean value)
	{
		setMandatoryOptIn( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute.
	 * @return the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public Boolean isMandatoryPhone(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYPHONE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute.
	 * @return the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public Boolean isMandatoryPhone(final SubscriptionProduct item)
	{
		return isMandatoryPhone( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute. 
	 * @return the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public boolean isMandatoryPhoneAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isMandatoryPhone( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute. 
	 * @return the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public boolean isMandatoryPhoneAsPrimitive(final SubscriptionProduct item)
	{
		return isMandatoryPhoneAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute. 
	 * @param value the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public void setMandatoryPhone(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.MANDATORYPHONE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute. 
	 * @param value the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public void setMandatoryPhone(final SubscriptionProduct item, final Boolean value)
	{
		setMandatoryPhone( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute. 
	 * @param value the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public void setMandatoryPhone(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setMandatoryPhone( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.mandatoryPhone</code> attribute. 
	 * @param value the mandatoryPhone - mandatory for customer to provide phone number
	 */
	public void setMandatoryPhone(final SubscriptionProduct item, final boolean value)
	{
		setMandatoryPhone( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.milesAndMoreNumber</code> attribute.
	 * @return the milesAndMoreNumber
	 */
	public String getMilesAndMoreNumber(final SessionContext ctx, final Address item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.MILESANDMORENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.milesAndMoreNumber</code> attribute.
	 * @return the milesAndMoreNumber
	 */
	public String getMilesAndMoreNumber(final Address item)
	{
		return getMilesAndMoreNumber( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.milesAndMoreNumber</code> attribute. 
	 * @param value the milesAndMoreNumber
	 */
	public void setMilesAndMoreNumber(final SessionContext ctx, final Address item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.MILESANDMORENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.milesAndMoreNumber</code> attribute. 
	 * @param value the milesAndMoreNumber
	 */
	public void setMilesAndMoreNumber(final Address item, final String value)
	{
		setMilesAndMoreNumber( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.mimeType</code> attribute.
	 * @return the mimeType - MIME-type of resource
	 */
	public String getMimeType(final SessionContext ctx, final CMSLinkComponent item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.MIMETYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.mimeType</code> attribute.
	 * @return the mimeType - MIME-type of resource
	 */
	public String getMimeType(final CMSLinkComponent item)
	{
		return getMimeType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.mimeType</code> attribute. 
	 * @param value the mimeType - MIME-type of resource
	 */
	public void setMimeType(final SessionContext ctx, final CMSLinkComponent item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.MIMETYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.mimeType</code> attribute. 
	 * @param value the mimeType - MIME-type of resource
	 */
	public void setMimeType(final CMSLinkComponent item, final String value)
	{
		setMimeType( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.mobileNumber</code> attribute.
	 * @return the mobileNumber
	 */
	public PhoneNumber getMobileNumber(final SessionContext ctx, final Address item)
	{
		return (PhoneNumber)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.MOBILENUMBER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.mobileNumber</code> attribute.
	 * @return the mobileNumber
	 */
	public PhoneNumber getMobileNumber(final Address item)
	{
		return getMobileNumber( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.mobileNumber</code> attribute. 
	 * @param value the mobileNumber
	 */
	public void setMobileNumber(final SessionContext ctx, final Address item, final PhoneNumber value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.MOBILENUMBER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.mobileNumber</code> attribute. 
	 * @param value the mobileNumber
	 */
	public void setMobileNumber(final Address item, final PhoneNumber value)
	{
		setMobileNumber( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.offerOrigin</code> attribute.
	 * @return the offerOrigin - Indicates which external system is the owner of this product.
	 */
	public EnumerationValue getOfferOrigin(final SessionContext ctx, final Product item)
	{
		return (EnumerationValue)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.OFFERORIGIN);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.offerOrigin</code> attribute.
	 * @return the offerOrigin - Indicates which external system is the owner of this product.
	 */
	public EnumerationValue getOfferOrigin(final Product item)
	{
		return getOfferOrigin( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.offerOrigin</code> attribute. 
	 * @param value the offerOrigin - Indicates which external system is the owner of this product.
	 */
	public void setOfferOrigin(final SessionContext ctx, final Product item, final EnumerationValue value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.OFFERORIGIN,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.offerOrigin</code> attribute. 
	 * @param value the offerOrigin - Indicates which external system is the owner of this product.
	 */
	public void setOfferOrigin(final Product item, final EnumerationValue value)
	{
		setOfferOrigin( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.onlineOffer</code> attribute.
	 * @return the onlineOffer
	 */
	public Boolean isOnlineOffer(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.ONLINEOFFER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.onlineOffer</code> attribute.
	 * @return the onlineOffer
	 */
	public Boolean isOnlineOffer(final SubscriptionProduct item)
	{
		return isOnlineOffer( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.onlineOffer</code> attribute. 
	 * @return the onlineOffer
	 */
	public boolean isOnlineOfferAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isOnlineOffer( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.onlineOffer</code> attribute. 
	 * @return the onlineOffer
	 */
	public boolean isOnlineOfferAsPrimitive(final SubscriptionProduct item)
	{
		return isOnlineOfferAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.onlineOffer</code> attribute. 
	 * @param value the onlineOffer
	 */
	public void setOnlineOffer(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.ONLINEOFFER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.onlineOffer</code> attribute. 
	 * @param value the onlineOffer
	 */
	public void setOnlineOffer(final SubscriptionProduct item, final Boolean value)
	{
		setOnlineOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.onlineOffer</code> attribute. 
	 * @param value the onlineOffer
	 */
	public void setOnlineOffer(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setOnlineOffer( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.onlineOffer</code> attribute. 
	 * @param value the onlineOffer
	 */
	public void setOnlineOffer(final SubscriptionProduct item, final boolean value)
	{
		setOnlineOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.orderPriority</code> attribute.
	 * @return the orderPriority - Number that indicates priority of PaymentMode
	 */
	public Integer getOrderPriority(final SessionContext ctx, final PaymentMode item)
	{
		return (Integer)item.getProperty( ctx, PacoshopCoreConstants.Attributes.PaymentMode.ORDERPRIORITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.orderPriority</code> attribute.
	 * @return the orderPriority - Number that indicates priority of PaymentMode
	 */
	public Integer getOrderPriority(final PaymentMode item)
	{
		return getOrderPriority( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.orderPriority</code> attribute. 
	 * @return the orderPriority - Number that indicates priority of PaymentMode
	 */
	public int getOrderPriorityAsPrimitive(final SessionContext ctx, final PaymentMode item)
	{
		Integer value = getOrderPriority( ctx,item );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>PaymentMode.orderPriority</code> attribute. 
	 * @return the orderPriority - Number that indicates priority of PaymentMode
	 */
	public int getOrderPriorityAsPrimitive(final PaymentMode item)
	{
		return getOrderPriorityAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.orderPriority</code> attribute. 
	 * @param value the orderPriority - Number that indicates priority of PaymentMode
	 */
	public void setOrderPriority(final SessionContext ctx, final PaymentMode item, final Integer value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.PaymentMode.ORDERPRIORITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.orderPriority</code> attribute. 
	 * @param value the orderPriority - Number that indicates priority of PaymentMode
	 */
	public void setOrderPriority(final PaymentMode item, final Integer value)
	{
		setOrderPriority( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.orderPriority</code> attribute. 
	 * @param value the orderPriority - Number that indicates priority of PaymentMode
	 */
	public void setOrderPriority(final SessionContext ctx, final PaymentMode item, final int value)
	{
		setOrderPriority( ctx, item, Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>PaymentMode.orderPriority</code> attribute. 
	 * @param value the orderPriority - Number that indicates priority of PaymentMode
	 */
	public void setOrderPriority(final PaymentMode item, final int value)
	{
		setOrderPriority( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.paymentModeFees</code> attribute.
	 * @return the paymentModeFees
	 */
	public Set<StorePaymentModeFee> getPaymentModeFees(final SessionContext ctx, final BaseStore item)
	{
		return (Set<StorePaymentModeFee>)BASESTOREPAYMENTMODEFEESRELATIONPAYMENTMODEFEESHANDLER.getValues( ctx, item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.paymentModeFees</code> attribute.
	 * @return the paymentModeFees
	 */
	public Set<StorePaymentModeFee> getPaymentModeFees(final BaseStore item)
	{
		return getPaymentModeFees( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.paymentModeFees</code> attribute. 
	 * @param value the paymentModeFees
	 */
	public void setPaymentModeFees(final SessionContext ctx, final BaseStore item, final Set<StorePaymentModeFee> value)
	{
		BASESTOREPAYMENTMODEFEESRELATIONPAYMENTMODEFEESHANDLER.setValues( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.paymentModeFees</code> attribute. 
	 * @param value the paymentModeFees
	 */
	public void setPaymentModeFees(final BaseStore item, final Set<StorePaymentModeFee> value)
	{
		setPaymentModeFees( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to paymentModeFees. 
	 * @param value the item to add to paymentModeFees
	 */
	public void addToPaymentModeFees(final SessionContext ctx, final BaseStore item, final StorePaymentModeFee value)
	{
		BASESTOREPAYMENTMODEFEESRELATIONPAYMENTMODEFEESHANDLER.addValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to paymentModeFees. 
	 * @param value the item to add to paymentModeFees
	 */
	public void addToPaymentModeFees(final BaseStore item, final StorePaymentModeFee value)
	{
		addToPaymentModeFees( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from paymentModeFees. 
	 * @param value the item to remove from paymentModeFees
	 */
	public void removeFromPaymentModeFees(final SessionContext ctx, final BaseStore item, final StorePaymentModeFee value)
	{
		BASESTOREPAYMENTMODEFEESRELATIONPAYMENTMODEFEESHANDLER.removeValue( ctx, item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from paymentModeFees. 
	 * @param value the item to remove from paymentModeFees
	 */
	public void removeFromPaymentModeFees(final BaseStore item, final StorePaymentModeFee value)
	{
		removeFromPaymentModeFees( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.paymentTypes</code> attribute.
	 * @return the paymentTypes - Through this field payment methods allowed at the product type level can be
	 *                             overwritten, in order to restrict them for separate products.
	 */
	public List<PaymentType> getPaymentTypes(final SessionContext ctx, final SubscriptionProduct item)
	{
		List<PaymentType> coll = (List<PaymentType>)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.PAYMENTTYPES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.paymentTypes</code> attribute.
	 * @return the paymentTypes - Through this field payment methods allowed at the product type level can be
	 *                             overwritten, in order to restrict them for separate products.
	 */
	public List<PaymentType> getPaymentTypes(final SubscriptionProduct item)
	{
		return getPaymentTypes( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.paymentTypes</code> attribute. 
	 * @param value the paymentTypes - Through this field payment methods allowed at the product type level can be
	 *                             overwritten, in order to restrict them for separate products.
	 */
	public void setPaymentTypes(final SessionContext ctx, final SubscriptionProduct item, final List<PaymentType> value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.PAYMENTTYPES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.paymentTypes</code> attribute. 
	 * @param value the paymentTypes - Through this field payment methods allowed at the product type level can be
	 *                             overwritten, in order to restrict them for separate products.
	 */
	public void setPaymentTypes(final SubscriptionProduct item, final List<PaymentType> value)
	{
		setPaymentTypes( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.prepayOnly</code> attribute.
	 * @return the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public Boolean isPrepayOnly(final SessionContext ctx, final Product item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.PREPAYONLY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.prepayOnly</code> attribute.
	 * @return the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public Boolean isPrepayOnly(final Product item)
	{
		return isPrepayOnly( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.prepayOnly</code> attribute. 
	 * @return the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public boolean isPrepayOnlyAsPrimitive(final SessionContext ctx, final Product item)
	{
		Boolean value = isPrepayOnly( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.prepayOnly</code> attribute. 
	 * @return the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public boolean isPrepayOnlyAsPrimitive(final Product item)
	{
		return isPrepayOnlyAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public void setPrepayOnly(final SessionContext ctx, final Product item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.PREPAYONLY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public void setPrepayOnly(final Product item, final Boolean value)
	{
		setPrepayOnly( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public void setPrepayOnly(final SessionContext ctx, final Product item, final boolean value)
	{
		setPrepayOnly( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - If this option is TRUE, the product may be sold only through "Prepayment" payment
	 *                             method.
	 */
	public void setPrepayOnly(final Product item, final boolean value)
	{
		setPrepayOnly( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.prepayOnly</code> attribute.
	 * @return the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public Boolean isPrepayOnly(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.PREPAYONLY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.prepayOnly</code> attribute.
	 * @return the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public Boolean isPrepayOnly(final AbstractOrder item)
	{
		return isPrepayOnly( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.prepayOnly</code> attribute. 
	 * @return the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public boolean isPrepayOnlyAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isPrepayOnly( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.prepayOnly</code> attribute. 
	 * @return the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public boolean isPrepayOnlyAsPrimitive(final AbstractOrder item)
	{
		return isPrepayOnlyAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public void setPrepayOnly(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.PREPAYONLY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public void setPrepayOnly(final AbstractOrder item, final Boolean value)
	{
		setPrepayOnly( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public void setPrepayOnly(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setPrepayOnly( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.prepayOnly</code> attribute. 
	 * @param value the prepayOnly - Flag that indicates the user selected the product is prepay only
	 */
	public void setPrepayOnly(final AbstractOrder item, final boolean value)
	{
		setPrepayOnly( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.productImageUrl</code> attribute.
	 * @return the productImageUrl
	 */
	public String getProductImageUrl(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.PRODUCTIMAGEURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.productImageUrl</code> attribute.
	 * @return the productImageUrl
	 */
	public String getProductImageUrl(final AbstractOrderEntry item)
	{
		return getProductImageUrl( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.productImageUrl</code> attribute. 
	 * @param value the productImageUrl
	 */
	public void setProductImageUrl(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.PRODUCTIMAGEURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.productImageUrl</code> attribute. 
	 * @param value the productImageUrl
	 */
	public void setProductImageUrl(final AbstractOrderEntry item, final String value)
	{
		setProductImageUrl( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productOwner</code> attribute.
	 * @return the productOwner - Indicates which external system is the owner of this product.
	 */
	public EnumerationValue getProductOwner(final SessionContext ctx, final Product item)
	{
		return (EnumerationValue)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.PRODUCTOWNER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productOwner</code> attribute.
	 * @return the productOwner - Indicates which external system is the owner of this product.
	 */
	public EnumerationValue getProductOwner(final Product item)
	{
		return getProductOwner( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productOwner</code> attribute. 
	 * @param value the productOwner - Indicates which external system is the owner of this product.
	 */
	public void setProductOwner(final SessionContext ctx, final Product item, final EnumerationValue value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.PRODUCTOWNER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productOwner</code> attribute. 
	 * @param value the productOwner - Indicates which external system is the owner of this product.
	 */
	public void setProductOwner(final Product item, final EnumerationValue value)
	{
		setProductOwner( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productType</code> attribute.
	 * @return the productType - Type of product from the perspective of 6 product types defined in the functional
	 *                             specification
	 */
	public ProductType getProductType(final SessionContext ctx, final Product item)
	{
		return (ProductType)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.PRODUCTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productType</code> attribute.
	 * @return the productType - Type of product from the perspective of 6 product types defined in the functional
	 *                             specification
	 */
	public ProductType getProductType(final Product item)
	{
		return getProductType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productType</code> attribute. 
	 * @param value the productType - Type of product from the perspective of 6 product types defined in the functional
	 *                             specification
	 */
	public void setProductType(final SessionContext ctx, final Product item, final ProductType value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.PRODUCTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productType</code> attribute. 
	 * @param value the productType - Type of product from the perspective of 6 product types defined in the functional
	 *                             specification
	 */
	public void setProductType(final Product item, final ProductType value)
	{
		setProductType( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute.
	 * @return the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public Boolean isReadersCanvassReaders(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.READERSCANVASSREADERS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute.
	 * @return the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public Boolean isReadersCanvassReaders(final SubscriptionProduct item)
	{
		return isReadersCanvassReaders( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute. 
	 * @return the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public boolean isReadersCanvassReadersAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isReadersCanvassReaders( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute. 
	 * @return the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public boolean isReadersCanvassReadersAsPrimitive(final SubscriptionProduct item)
	{
		return isReadersCanvassReadersAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute. 
	 * @param value the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public void setReadersCanvassReaders(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.READERSCANVASSREADERS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute. 
	 * @param value the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public void setReadersCanvassReaders(final SubscriptionProduct item, final Boolean value)
	{
		setReadersCanvassReaders( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute. 
	 * @param value the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public void setReadersCanvassReaders(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setReadersCanvassReaders( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.readersCanvassReaders</code> attribute. 
	 * @param value the readersCanvassReaders - It means "Readers canvass readers" (referral) offer.
	 */
	public void setReadersCanvassReaders(final SubscriptionProduct item, final boolean value)
	{
		setReadersCanvassReaders( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.redirectUrl</code> attribute.
	 * @return the redirectUrl
	 */
	public String getRedirectUrl(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.REDIRECTURL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.redirectUrl</code> attribute.
	 * @return the redirectUrl
	 */
	public String getRedirectUrl(final AbstractOrder item)
	{
		return getRedirectUrl( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.redirectUrl</code> attribute. 
	 * @param value the redirectUrl
	 */
	public void setRedirectUrl(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.REDIRECTURL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.redirectUrl</code> attribute. 
	 * @param value the redirectUrl
	 */
	public void setRedirectUrl(final AbstractOrder item, final String value)
	{
		setRedirectUrl( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.redirectUrlDescription</code> attribute.
	 * @return the redirectUrlDescription
	 */
	public String getRedirectUrlDescription(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.REDIRECTURLDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.redirectUrlDescription</code> attribute.
	 * @return the redirectUrlDescription
	 */
	public String getRedirectUrlDescription(final AbstractOrder item)
	{
		return getRedirectUrlDescription( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.redirectUrlDescription</code> attribute. 
	 * @param value the redirectUrlDescription
	 */
	public void setRedirectUrlDescription(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.REDIRECTURLDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.redirectUrlDescription</code> attribute. 
	 * @param value the redirectUrlDescription
	 */
	public void setRedirectUrlDescription(final AbstractOrder item, final String value)
	{
		setRedirectUrlDescription( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.rel</code> attribute.
	 * @return the rel - Relation to resource that link points to. HTML rel tag
	 */
	public String getRel(final SessionContext ctx, final CMSLinkComponent item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.REL);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSLinkComponent.rel</code> attribute.
	 * @return the rel - Relation to resource that link points to. HTML rel tag
	 */
	public String getRel(final CMSLinkComponent item)
	{
		return getRel( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.rel</code> attribute. 
	 * @param value the rel - Relation to resource that link points to. HTML rel tag
	 */
	public void setRel(final SessionContext ctx, final CMSLinkComponent item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSLinkComponent.REL,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSLinkComponent.rel</code> attribute. 
	 * @param value the rel - Relation to resource that link points to. HTML rel tag
	 */
	public void setRel(final CMSLinkComponent item, final String value)
	{
		setRel( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.roleInCompany</code> attribute.
	 * @return the roleInCompany
	 */
	public String getRoleInCompany(final SessionContext ctx, final Address item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.ROLEINCOMPANY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.roleInCompany</code> attribute.
	 * @return the roleInCompany
	 */
	public String getRoleInCompany(final Address item)
	{
		return getRoleInCompany( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.roleInCompany</code> attribute. 
	 * @param value the roleInCompany
	 */
	public void setRoleInCompany(final SessionContext ctx, final Address item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.ROLEINCOMPANY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.roleInCompany</code> attribute. 
	 * @param value the roleInCompany
	 */
	public void setRoleInCompany(final Address item, final String value)
	{
		setRoleInCompany( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.showStepForDownload</code> attribute.
	 * @return the showStepForDownload
	 */
	public Boolean isShowStepForDownload(final SessionContext ctx, final AbstractOrder item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.SHOWSTEPFORDOWNLOAD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.showStepForDownload</code> attribute.
	 * @return the showStepForDownload
	 */
	public Boolean isShowStepForDownload(final AbstractOrder item)
	{
		return isShowStepForDownload( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.showStepForDownload</code> attribute. 
	 * @return the showStepForDownload
	 */
	public boolean isShowStepForDownloadAsPrimitive(final SessionContext ctx, final AbstractOrder item)
	{
		Boolean value = isShowStepForDownload( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.showStepForDownload</code> attribute. 
	 * @return the showStepForDownload
	 */
	public boolean isShowStepForDownloadAsPrimitive(final AbstractOrder item)
	{
		return isShowStepForDownloadAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.showStepForDownload</code> attribute. 
	 * @param value the showStepForDownload
	 */
	public void setShowStepForDownload(final SessionContext ctx, final AbstractOrder item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.SHOWSTEPFORDOWNLOAD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.showStepForDownload</code> attribute. 
	 * @param value the showStepForDownload
	 */
	public void setShowStepForDownload(final AbstractOrder item, final Boolean value)
	{
		setShowStepForDownload( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.showStepForDownload</code> attribute. 
	 * @param value the showStepForDownload
	 */
	public void setShowStepForDownload(final SessionContext ctx, final AbstractOrder item, final boolean value)
	{
		setShowStepForDownload( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.showStepForDownload</code> attribute. 
	 * @param value the showStepForDownload
	 */
	public void setShowStepForDownload(final AbstractOrder item, final boolean value)
	{
		setShowStepForDownload( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.siteOwnerDescription</code> attribute.
	 * @return the siteOwnerDescription - Text for the redirect url.
	 */
	public String getSiteOwnerDescription(final SessionContext ctx, final CMSSite item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.SITEOWNERDESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.siteOwnerDescription</code> attribute.
	 * @return the siteOwnerDescription - Text for the redirect url.
	 */
	public String getSiteOwnerDescription(final CMSSite item)
	{
		return getSiteOwnerDescription( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.siteOwnerDescription</code> attribute. 
	 * @param value the siteOwnerDescription - Text for the redirect url.
	 */
	public void setSiteOwnerDescription(final SessionContext ctx, final CMSSite item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.SITEOWNERDESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.siteOwnerDescription</code> attribute. 
	 * @param value the siteOwnerDescription - Text for the redirect url.
	 */
	public void setSiteOwnerDescription(final CMSSite item, final String value)
	{
		setSiteOwnerDescription( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.siteOwnerValue</code> attribute.
	 * @return the siteOwnerValue - Redirect URL.
	 */
	public String getSiteOwnerValue(final SessionContext ctx, final CMSSite item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.SITEOWNERVALUE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.siteOwnerValue</code> attribute.
	 * @return the siteOwnerValue - Redirect URL.
	 */
	public String getSiteOwnerValue(final CMSSite item)
	{
		return getSiteOwnerValue( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.siteOwnerValue</code> attribute. 
	 * @param value the siteOwnerValue - Redirect URL.
	 */
	public void setSiteOwnerValue(final SessionContext ctx, final CMSSite item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.SITEOWNERVALUE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.siteOwnerValue</code> attribute. 
	 * @param value the siteOwnerValue - Redirect URL.
	 */
	public void setSiteOwnerValue(final CMSSite item, final String value)
	{
		setSiteOwnerValue( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.ssoCustomerId</code> attribute.
	 * @return the ssoCustomerId
	 */
	public String getSsoCustomerId(final SessionContext ctx, final Address item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.SSOCUSTOMERID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.ssoCustomerId</code> attribute.
	 * @return the ssoCustomerId
	 */
	public String getSsoCustomerId(final Address item)
	{
		return getSsoCustomerId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.ssoCustomerId</code> attribute. 
	 * @param value the ssoCustomerId
	 */
	public void setSsoCustomerId(final SessionContext ctx, final Address item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.SSOCUSTOMERID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.ssoCustomerId</code> attribute. 
	 * @param value the ssoCustomerId
	 */
	public void setSsoCustomerId(final Address item, final String value)
	{
		setSsoCustomerId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.studentGraduationDate</code> attribute.
	 * @return the studentGraduationDate - Date when the customer will be graduated
	 */
	public Date getStudentGraduationDate(final SessionContext ctx, final Customer item)
	{
		return (Date)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.STUDENTGRADUATIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.studentGraduationDate</code> attribute.
	 * @return the studentGraduationDate - Date when the customer will be graduated
	 */
	public Date getStudentGraduationDate(final Customer item)
	{
		return getStudentGraduationDate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.studentGraduationDate</code> attribute. 
	 * @param value the studentGraduationDate - Date when the customer will be graduated
	 */
	public void setStudentGraduationDate(final SessionContext ctx, final Customer item, final Date value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.STUDENTGRADUATIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.studentGraduationDate</code> attribute. 
	 * @param value the studentGraduationDate - Date when the customer will be graduated
	 */
	public void setStudentGraduationDate(final Customer item, final Date value)
	{
		setStudentGraduationDate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.studentGradutationDate</code> attribute.
	 * @return the studentGradutationDate
	 */
	public Date getStudentGradutationDate(final SessionContext ctx, final AbstractOrder item)
	{
		return (Date)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.STUDENTGRADUTATIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.studentGradutationDate</code> attribute.
	 * @return the studentGradutationDate
	 */
	public Date getStudentGradutationDate(final AbstractOrder item)
	{
		return getStudentGradutationDate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.studentGradutationDate</code> attribute. 
	 * @param value the studentGradutationDate
	 */
	public void setStudentGradutationDate(final SessionContext ctx, final AbstractOrder item, final Date value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.STUDENTGRADUTATIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.studentGradutationDate</code> attribute. 
	 * @param value the studentGradutationDate
	 */
	public void setStudentGradutationDate(final AbstractOrder item, final Date value)
	{
		setStudentGradutationDate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.studentOffer</code> attribute.
	 * @return the studentOffer - The offer is a student subscription.
	 */
	public Boolean isStudentOffer(final SessionContext ctx, final SubscriptionProduct item)
	{
		return (Boolean)item.getProperty( ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.STUDENTOFFER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.studentOffer</code> attribute.
	 * @return the studentOffer - The offer is a student subscription.
	 */
	public Boolean isStudentOffer(final SubscriptionProduct item)
	{
		return isStudentOffer( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.studentOffer</code> attribute. 
	 * @return the studentOffer - The offer is a student subscription.
	 */
	public boolean isStudentOfferAsPrimitive(final SessionContext ctx, final SubscriptionProduct item)
	{
		Boolean value = isStudentOffer( ctx,item );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>SubscriptionProduct.studentOffer</code> attribute. 
	 * @return the studentOffer - The offer is a student subscription.
	 */
	public boolean isStudentOfferAsPrimitive(final SubscriptionProduct item)
	{
		return isStudentOfferAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.studentOffer</code> attribute. 
	 * @param value the studentOffer - The offer is a student subscription.
	 */
	public void setStudentOffer(final SessionContext ctx, final SubscriptionProduct item, final Boolean value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.SubscriptionProduct.STUDENTOFFER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.studentOffer</code> attribute. 
	 * @param value the studentOffer - The offer is a student subscription.
	 */
	public void setStudentOffer(final SubscriptionProduct item, final Boolean value)
	{
		setStudentOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.studentOffer</code> attribute. 
	 * @param value the studentOffer - The offer is a student subscription.
	 */
	public void setStudentOffer(final SessionContext ctx, final SubscriptionProduct item, final boolean value)
	{
		setStudentOffer( ctx, item, Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>SubscriptionProduct.studentOffer</code> attribute. 
	 * @param value the studentOffer - The offer is a student subscription.
	 */
	public void setStudentOffer(final SubscriptionProduct item, final boolean value)
	{
		setStudentOffer( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.subscriptionPhase</code> attribute.
	 * @return the subscriptionPhase
	 */
	public EnumerationValue getSubscriptionPhase(final SessionContext ctx, final BillingTime item)
	{
		return (EnumerationValue)item.getProperty( ctx, PacoshopCoreConstants.Attributes.BillingTime.SUBSCRIPTIONPHASE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.subscriptionPhase</code> attribute.
	 * @return the subscriptionPhase
	 */
	public EnumerationValue getSubscriptionPhase(final BillingTime item)
	{
		return getSubscriptionPhase( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.subscriptionPhase</code> attribute. 
	 * @param value the subscriptionPhase
	 */
	public void setSubscriptionPhase(final SessionContext ctx, final BillingTime item, final EnumerationValue value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.BillingTime.SUBSCRIPTIONPHASE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.subscriptionPhase</code> attribute. 
	 * @param value the subscriptionPhase
	 */
	public void setSubscriptionPhase(final BillingTime item, final EnumerationValue value)
	{
		setSubscriptionPhase( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.subscriptionPhase</code> attribute.
	 * @return the subscriptionPhase
	 */
	public EnumerationValue getSubscriptionPhase(final SessionContext ctx, final RecurringChargeEntry item)
	{
		return (EnumerationValue)item.getProperty( ctx, PacoshopCoreConstants.Attributes.RecurringChargeEntry.SUBSCRIPTIONPHASE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>RecurringChargeEntry.subscriptionPhase</code> attribute.
	 * @return the subscriptionPhase
	 */
	public EnumerationValue getSubscriptionPhase(final RecurringChargeEntry item)
	{
		return getSubscriptionPhase( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.subscriptionPhase</code> attribute. 
	 * @param value the subscriptionPhase
	 */
	public void setSubscriptionPhase(final SessionContext ctx, final RecurringChargeEntry item, final EnumerationValue value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.RecurringChargeEntry.SUBSCRIPTIONPHASE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>RecurringChargeEntry.subscriptionPhase</code> attribute. 
	 * @param value the subscriptionPhase
	 */
	public void setSubscriptionPhase(final RecurringChargeEntry item, final EnumerationValue value)
	{
		setSubscriptionPhase( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.summary</code> attribute.
	 * @return the summary
	 */
	public String getSummary(final SessionContext ctx, final AbstractOrderEntry item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.SUMMARY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrderEntry.summary</code> attribute.
	 * @return the summary
	 */
	public String getSummary(final AbstractOrderEntry item)
	{
		return getSummary( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.summary</code> attribute. 
	 * @param value the summary
	 */
	public void setSummary(final SessionContext ctx, final AbstractOrderEntry item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrderEntry.SUMMARY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrderEntry.summary</code> attribute. 
	 * @param value the summary
	 */
	public void setSummary(final AbstractOrderEntry item, final String value)
	{
		setSummary( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.termsAccepted</code> attribute.
	 * @return the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public Set<TermAccepted> getTermsAccepted(final SessionContext ctx, final AbstractOrder item)
	{
		Set<TermAccepted> coll = (Set<TermAccepted>)item.getProperty( ctx, PacoshopCoreConstants.Attributes.AbstractOrder.TERMSACCEPTED);
		return coll != null ? coll : Collections.EMPTY_SET;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.termsAccepted</code> attribute.
	 * @return the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public Set<TermAccepted> getTermsAccepted(final AbstractOrder item)
	{
		return getTermsAccepted( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.termsAccepted</code> attribute. 
	 * @param value the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public void setTermsAccepted(final SessionContext ctx, final AbstractOrder item, final Set<TermAccepted> value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.AbstractOrder.TERMSACCEPTED,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.termsAccepted</code> attribute. 
	 * @param value the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public void setTermsAccepted(final AbstractOrder item, final Set<TermAccepted> value)
	{
		setTermsAccepted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.termsAccepted</code> attribute.
	 * @return the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public Set<TermAccepted> getTermsAccepted(final SessionContext ctx, final Customer item)
	{
		Set<TermAccepted> coll = (Set<TermAccepted>)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Customer.TERMSACCEPTED);
		return coll != null ? coll : Collections.EMPTY_SET;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.termsAccepted</code> attribute.
	 * @return the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public Set<TermAccepted> getTermsAccepted(final Customer item)
	{
		return getTermsAccepted( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.termsAccepted</code> attribute. 
	 * @param value the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public void setTermsAccepted(final SessionContext ctx, final Customer item, final Set<TermAccepted> value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Customer.TERMSACCEPTED,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.termsAccepted</code> attribute. 
	 * @param value the termsAccepted - A list of terms which was accepted or not by a Customer (only the latest versions of each TermType).
	 */
	public void setTermsAccepted(final Customer item, final Set<TermAccepted> value)
	{
		setTermsAccepted( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.termsAndConditionsLink</code> attribute.
	 * @return the termsAndConditionsLink - Link to the Terms and Agreement document or web page.
	 */
	public String getTermsAndConditionsLink(final SessionContext ctx, final CMSSite item)
	{
		return (String)item.getProperty( ctx, PacoshopCoreConstants.Attributes.CMSSite.TERMSANDCONDITIONSLINK);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CMSSite.termsAndConditionsLink</code> attribute.
	 * @return the termsAndConditionsLink - Link to the Terms and Agreement document or web page.
	 */
	public String getTermsAndConditionsLink(final CMSSite item)
	{
		return getTermsAndConditionsLink( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.termsAndConditionsLink</code> attribute. 
	 * @param value the termsAndConditionsLink - Link to the Terms and Agreement document or web page.
	 */
	public void setTermsAndConditionsLink(final SessionContext ctx, final CMSSite item, final String value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.CMSSite.TERMSANDCONDITIONSLINK,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CMSSite.termsAndConditionsLink</code> attribute. 
	 * @param value the termsAndConditionsLink - Link to the Terms and Agreement document or web page.
	 */
	public void setTermsAndConditionsLink(final CMSSite item, final String value)
	{
		setTermsAndConditionsLink( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.title2</code> attribute.
	 * @return the title2
	 */
	public Title2 getTitle2(final SessionContext ctx, final Address item)
	{
		return (Title2)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Address.TITLE2);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Address.title2</code> attribute.
	 * @return the title2
	 */
	public Title2 getTitle2(final Address item)
	{
		return getTitle2( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.title2</code> attribute. 
	 * @param value the title2
	 */
	public void setTitle2(final SessionContext ctx, final Address item, final Title2 value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Address.TITLE2,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Address.title2</code> attribute. 
	 * @param value the title2
	 */
	public void setTitle2(final Address item, final Title2 value)
	{
		setTitle2( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.unit</code> attribute.
	 * @return the unit
	 */
	public Unit getUnit(final SessionContext ctx, final BillingTime item)
	{
		return (Unit)item.getProperty( ctx, PacoshopCoreConstants.Attributes.BillingTime.UNIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BillingTime.unit</code> attribute.
	 * @return the unit
	 */
	public Unit getUnit(final BillingTime item)
	{
		return getUnit( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.unit</code> attribute. 
	 * @param value the unit
	 */
	public void setUnit(final SessionContext ctx, final BillingTime item, final Unit value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.BillingTime.UNIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BillingTime.unit</code> attribute. 
	 * @param value the unit
	 */
	public void setUnit(final BillingTime item, final Unit value)
	{
		setUnit( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.vatGroup</code> attribute.
	 * @return the vatGroup - Product's value added tax.
	 */
	public VATGroup getVatGroup(final SessionContext ctx, final Product item)
	{
		return (VATGroup)item.getProperty( ctx, PacoshopCoreConstants.Attributes.Product.VATGROUP);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.vatGroup</code> attribute.
	 * @return the vatGroup - Product's value added tax.
	 */
	public VATGroup getVatGroup(final Product item)
	{
		return getVatGroup( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.vatGroup</code> attribute. 
	 * @param value the vatGroup - Product's value added tax.
	 */
	public void setVatGroup(final SessionContext ctx, final Product item, final VATGroup value)
	{
		item.setProperty(ctx, PacoshopCoreConstants.Attributes.Product.VATGROUP,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.vatGroup</code> attribute. 
	 * @param value the vatGroup - Product's value added tax.
	 */
	public void setVatGroup(final Product item, final VATGroup value)
	{
		setVatGroup( getSession().getSessionContext(), item, value );
	}
	
}
