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
package com.cgi.pacoshop.fulfilmentprocess.actions.order;

import com.cgi.pacoshop.fulfilmentprocess.constants.PacoshopFulfilmentProcessConstants;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.processengine.model.ProcessTaskLogModel;
import de.hybris.platform.processengine.model.ProcessTaskModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.task.RetryLaterException;

import java.util.NoSuchElementException;

import javax.annotation.Resource;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


/**
 * Base class to simplified the retry process of an action.
 * 
 * @module hybris - pacoshopcore
 * @version 1.0v Mar 04 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Phillipe Bergeron <philippe.bergeron@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 */
public abstract class AbstractRetryableOrderModelAction extends AbstractSimpleDecisionAction<OrderProcessModel>
{


    /** The Constant LOG. */
    private static final Logger LOG = Logger.getLogger(AbstractRetryableOrderModelAction.class);

    /** configuration service to extract security token. */
    private ConfigurationService configurationService;

    private String retryDelayConfigurationKey;
    private String retryMaxAttemptsConfigurationKey;

    @Resource
    private ModelService modelService;

    /**
     * Handle the exception case for the processing of an action. Either mark the action for retry or abandon the
     * process after the configured number of retries.
     * 
     * @param e
     *            the exception that cause the failure.
     * @param process
     *            the process.
     * @return the corresponding transition in case of abandon.
     * @throws RetryLaterException
     *             is thrown if the action is marked to be retried.
     */
    public Transition handleException(final Exception e, final OrderProcessModel process) throws RetryLaterException
    {
        Integer retryWaitPeriod = PacoshopFulfilmentProcessConstants.INTERFACE.DEFAULT_RETRY_DELAY;
        try
        {
            retryWaitPeriod = getConfiguration().getInt(retryDelayConfigurationKey);
        }
        catch (final NoSuchElementException nsee)
        {
            LOG.warn("The configuration [" + retryDelayConfigurationKey + "] is not defined, using default value["
                    + retryWaitPeriod + "]");
        }

        final OrderModel order = process.getOrder();

        LOG.error("Exception received during order process task: ", e);

        if (performRetry(process))
        {

            // throw RetryLaterException to force the retry
            RetryLaterException ex = null;
            if (e != null)
            {
                ex = new RetryLaterException(e.getMessage());
            }
            else
            {
                if (order == null)
                {
                    ex = new RetryLaterException("Order failure");
                }
                else
                {
                    ex = new RetryLaterException("Order failure: " + order.getCode());
                }

            }
            ex.setDelay(retryWaitPeriod);
            LOG.info("Registering the action for retry. delay: " + ex.getDelay());
            throw ex;
        }
        else
        {
            final OrderStatus status = OrderStatus.ORDER_PROCESSING_FAILED;
            if (order != null)
            {
                order.setStatus(status);


                //save the order
                modelService.save(order);

                LOG.error("Order[" + order.getCode() + "] couldn't be process. Failing the order.");
                // too many retry attempts fail the flow.
            }
            else
            {
                LOG.error("Process[" + process.getCode() + "] couldn't be process. Failing the process.");
            }
            return Transition.NOK;
        }
    }

    /**
     * Checks the retyAttempts flag and based on the max retry limit returns true/false.
     * 
     * @param orderProcess
     *            the orderProcessModel
     * @return whether to retry or not the action.
     */
    private boolean performRetry(final OrderProcessModel orderProcess)
    {
        Integer maxAttempts = PacoshopFulfilmentProcessConstants.INTERFACE.DEFAULT_RETRY_MAX;
        try
        {
            maxAttempts = getConfiguration().getInt(retryMaxAttemptsConfigurationKey);
        }
        catch (final NoSuchElementException nsee)
        {
            LOG.warn("The configuration [" + retryMaxAttemptsConfigurationKey + "] is not defined, using default value["
                    + PacoshopFulfilmentProcessConstants.INTERFACE.DEFAULT_RETRY_MAX + "]");
        }


        // Get the name of the current task
        // Should be safe to assume that there is only 1 current task here
        final ProcessTaskModel currentTask = orderProcess.getCurrentTasks().iterator().next();


        // Count how often this task has already been run
        int currentRetryAttempts = 0;
        for (final ProcessTaskLogModel taskLog : orderProcess.getTaskLogs())
        {
            if (taskLog.getActionId() != null && taskLog.getActionId().equals(currentTask.getAction()))
            {
                currentRetryAttempts++;
            }
        }

        if (orderProcess.getOrder() != null)
        {
            LOG.info("Registering action (order:" + orderProcess.getOrder().getCode() + ") for retry "
                    + (currentRetryAttempts < maxAttempts) + " attempt " + currentRetryAttempts + " of " + maxAttempts);
        }
        else
        {
            LOG.info("Registering action for retry " + (currentRetryAttempts < maxAttempts) + " attempt " + currentRetryAttempts
                    + " of " + maxAttempts);
        }

        return (currentRetryAttempts < maxAttempts);
    }

    /**
     * @param configurationService
     *            the configurationService to set
     */
    @Required
    public void setConfigurationService(final ConfigurationService configurationService)
    {
        this.configurationService = configurationService;
    }

    /**
     * Getter for the Configuration.
     * 
     * @return Configuration
     */
    private Configuration getConfiguration()
    {
        return configurationService.getConfiguration();
    }


    /**
     * @param retryDelayConfigurationKey
     *            the retryDelayConfigurationKey to set
     */
    public void setRetryDelayConfigurationKey(final String retryDelayConfigurationKey)
    {
        this.retryDelayConfigurationKey = retryDelayConfigurationKey;
    }

    /**
     * @param retryMaxAttemptsConfigurationKey
     *            the retryMaxAttemptsConfigurationKey to set
     */
    public void setRetryMaxAttemptsConfigurationKey(final String retryMaxAttemptsConfigurationKey)
    {
        this.retryMaxAttemptsConfigurationKey = retryMaxAttemptsConfigurationKey;
    }

    /**
     * @return the configurationService
     */
    public ConfigurationService getConfigurationService()
    {
        return configurationService;
    }

    /**
     * @return the retryDelayConfigurationKey
     */
    public String getRetryDelayConfigurationKey()
    {
        return retryDelayConfigurationKey;
    }

    /**
     * @return the retryMaxAttemptsConfigurationKey
     */
    public String getRetryMaxAttemptsConfigurationKey()
    {
        return retryMaxAttemptsConfigurationKey;
    }

    /**
     * @param modelService
     *            the modelService to set
     */
    @Override
    public void setModelService(final ModelService modelService)
    {
        this.modelService = modelService;
    }


}
