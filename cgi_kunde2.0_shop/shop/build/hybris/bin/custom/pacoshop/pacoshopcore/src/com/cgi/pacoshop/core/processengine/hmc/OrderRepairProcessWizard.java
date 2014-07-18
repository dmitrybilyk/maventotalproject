/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.cgi.pacoshop.core.processengine.hmc;

import de.hybris.platform.core.Registry;
import de.hybris.platform.hmc.jalo.VetoException;
import de.hybris.platform.hmc.jalo.WizardEditorContext;
import de.hybris.platform.processengine.definition.ProcessDefinition;
import de.hybris.platform.processengine.definition.ProcessDefinitionFactory;
import de.hybris.platform.processengine.hmc.RepairProcessWizard;
import de.hybris.platform.processengine.jalo.BusinessProcess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;


/**
 * Custom hmc wizard to filter out the action of the Repair process wizard to simply provided "OrderRouting action" if
 * this actions is present in the order process to repair.
 * 
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public class OrderRepairProcessWizard extends RepairProcessWizard
{

    private static final Logger LOGGER = Logger.getLogger(OrderRepairProcessWizard.class);


    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.processengine.hmc.RepairProcessWizard#initialize(de.hybris.platform.hmc.jalo.WizardEditorContext
     * )
     */
    @Override
    public void initialize(final WizardEditorContext ctx)
    {
        super.initialize(ctx);
        if (getAvailableProcessStates() != null && getAvailableProcessStates().size() == 1
                && PacoshopFulfilmentProcessConstants.ACTION_ORDER_ROUTING.equals(getAvailableProcessStates().iterator().next()))
        {
            final List selectedStates = new ArrayList<String>();
            selectedStates.add(PacoshopFulfilmentProcessConstants.ACTION_ORDER_ROUTING);
            ctx.setSelectedValues(AVAILABLEPROCESSSTATES, selectedStates);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.processengine.hmc.RepairProcessWizard#calcluateAvailableStates(de.hybris.platform.processengine
     * .jalo.BusinessProcess)
     */
    @Override
    protected List<String> calcluateAvailableStates(final BusinessProcess process)
    {
        final String processDefinitionName = process.getProcessDefinitionName();
        final ProcessDefinitionFactory processDefinitionFactory = (ProcessDefinitionFactory) Registry.getApplicationContext()
                .getBean("processDefinitionFactory");
        final ProcessDefinition processDefinition = processDefinitionFactory.getProcessDefinition(processDefinitionName);
        final List<String> ids = new ArrayList<String>(processDefinition.getNodeIds());
        for (final String current : ids)
        {
            if (PacoshopFulfilmentProcessConstants.ACTION_ORDER_ROUTING.equals(current))
            {
                final List<String> actionsIds = new ArrayList<String>();
                actionsIds.add(current);
                return actionsIds;
            }
        }
        Collections.sort(ids);
        return ids;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.hybris.platform.processengine.hmc.RepairProcessWizard#start(de.hybris.platform.hmc.jalo.WizardEditorContext)
     */
    @Override
    public void start(final WizardEditorContext ctx) throws VetoException
    {
        super.start(ctx);
    }
}
