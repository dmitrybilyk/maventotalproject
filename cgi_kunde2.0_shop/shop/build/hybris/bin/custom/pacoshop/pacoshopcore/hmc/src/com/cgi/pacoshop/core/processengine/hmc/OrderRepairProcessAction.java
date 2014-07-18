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

import de.hybris.platform.hmc.jalo.HMCManager;
import de.hybris.platform.hmc.util.action.ActionEvent;
import de.hybris.platform.jalo.security.JaloSecurityException;
import de.hybris.platform.orderprocessing.jalo.OrderProcess;
import de.hybris.platform.processengine.hmc.RepairProcessAction;
import de.hybris.platform.processengine.hmc.RepairProcessWizard;
import de.hybris.platform.processengine.jalo.BusinessProcess;

import java.util.Collections;


/**
 * Custom OrderRepairProcessAction.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @copyright 2014 symmetrics - a CGI Group brand
 * 
 * 
 */
public class OrderRepairProcessAction extends RepairProcessAction
{

    private static final String ORDERREPAIRPROCESSWIZARD = "OrderRepairProcessWizard".intern();

    @Override
    protected void openWizard(final ActionEvent actionEvent, final BusinessProcess process)
            throws JaloSecurityException
    {

        if (process instanceof OrderProcess)
        {
            final OrderRepairProcessWizard wizard = (OrderRepairProcessWizard) HMCManager.getInstance().createWizard(
                    ORDERREPAIRPROCESSWIZARD,
                    Collections.singletonMap("selectedProcess", process));
            getEditorContext(actionEvent).openItem(wizard, true);
        }
        else
        {
            final RepairProcessWizard wizard = (RepairProcessWizard) HMCManager.getInstance().createWizard(
                    de.hybris.platform.processengine.constants.GeneratedProcessengineConstants.TC.REPAIRPROCESSWIZARD,
                    Collections.singletonMap("selectedProcess", process));
            getEditorContext(actionEvent).openItem(wizard, true);
        }

    }

}
