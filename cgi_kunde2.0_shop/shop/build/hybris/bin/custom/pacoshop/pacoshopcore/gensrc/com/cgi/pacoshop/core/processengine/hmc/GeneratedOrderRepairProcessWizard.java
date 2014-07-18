/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jul 18, 2014 10:56:12 AM                    ---
 * ----------------------------------------------------------------
 */
package com.cgi.pacoshop.core.processengine.hmc;

import com.cgi.pacoshop.core.constants.PacoshopCoreConstants;
import de.hybris.platform.hmc.jalo.WizardBusinessItem;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.processengine.jalo.BusinessProcess;
import java.util.Collection;
import java.util.Collections;

/**
 * Generated class for type {@link com.cgi.pacoshop.core.processengine.hmc.OrderRepairProcessWizard OrderRepairProcessWizard}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedOrderRepairProcessWizard extends WizardBusinessItem
{
	/** Qualifier of the <code>OrderRepairProcessWizard.selectedProcess</code> attribute **/
	public static final String SELECTEDPROCESS = "selectedProcess";
	/** Qualifier of the <code>OrderRepairProcessWizard.availableProcessStates</code> attribute **/
	public static final String AVAILABLEPROCESSSTATES = "availableProcessStates";
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderRepairProcessWizard.availableProcessStates</code> attribute.
	 * @return the availableProcessStates
	 */
	public Collection<String> getAvailableProcessStates(final SessionContext ctx)
	{
		Collection<String> coll = (Collection<String>)getProperty( ctx, AVAILABLEPROCESSSTATES);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderRepairProcessWizard.availableProcessStates</code> attribute.
	 * @return the availableProcessStates
	 */
	public Collection<String> getAvailableProcessStates()
	{
		return getAvailableProcessStates( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderRepairProcessWizard.availableProcessStates</code> attribute. 
	 * @param value the availableProcessStates
	 */
	public void setAvailableProcessStates(final SessionContext ctx, final Collection<String> value)
	{
		setProperty(ctx, AVAILABLEPROCESSSTATES,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderRepairProcessWizard.availableProcessStates</code> attribute. 
	 * @param value the availableProcessStates
	 */
	public void setAvailableProcessStates(final Collection<String> value)
	{
		setAvailableProcessStates( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderRepairProcessWizard.selectedProcess</code> attribute.
	 * @return the selectedProcess
	 */
	public BusinessProcess getSelectedProcess(final SessionContext ctx)
	{
		return (BusinessProcess)getProperty( ctx, SELECTEDPROCESS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>OrderRepairProcessWizard.selectedProcess</code> attribute.
	 * @return the selectedProcess
	 */
	public BusinessProcess getSelectedProcess()
	{
		return getSelectedProcess( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderRepairProcessWizard.selectedProcess</code> attribute. 
	 * @param value the selectedProcess
	 */
	protected void setSelectedProcess(final SessionContext ctx, final BusinessProcess value)
	{
		// initial-only attribute: make sure this attribute can be set during item creation only
		if ( ctx.getAttribute( "core.types.creation.initial") != Boolean.TRUE )
		{
			throw new JaloInvalidParameterException( "attribute '"+SELECTEDPROCESS+"' is not changeable", 0 );
		}
		setProperty(ctx, SELECTEDPROCESS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>OrderRepairProcessWizard.selectedProcess</code> attribute. 
	 * @param value the selectedProcess
	 */
	protected void setSelectedProcess(final BusinessProcess value)
	{
		setSelectedProcess( getSession().getSessionContext(), value );
	}
	
}
