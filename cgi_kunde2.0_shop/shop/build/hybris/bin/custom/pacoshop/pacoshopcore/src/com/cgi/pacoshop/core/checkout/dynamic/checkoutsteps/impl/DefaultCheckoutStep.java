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
package com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.impl;


import com.cgi.pacoshop.core.checkout.dynamic.FormDTO;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep;
import com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.formelementgroups.FormElementGroup;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;

/**
 * Default implementation of the {@link com.cgi.pacoshop.core.checkout.dynamic.checkoutsteps.CheckoutStep}
 *
 * @module hybris - pacoshopcore
 * @version 1.0v Dec 17, 2013
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Dmitry Popov <dmitry.popov@symmetrics.de>
 * @link http ://www.symmetrics.de/
 * @see  ://wiki.hybris.com/
 * @copyright 2013 symmetrics - a CGI Group brand
 */
@Scope("request")
public class DefaultCheckoutStep implements CheckoutStep
{

	private boolean completed;

	private boolean lastStep;

	private String stepName;

	private List<FormElementGroup> formElementGroups = Collections.emptyList();

	private String cmsPageLabel;

	private FormDTO formDTO;

	private boolean populateFromCart = true;

	/**
	 * Completed setter.
	 * @param completedParam - new Completed.
	 */
	public void setCompleted(final boolean completedParam)
	{
		this.completed = completedParam;
	}

	/**
	 * Form Element Groups Setter.
	 * @param formElementGroupsParam - new list of Form Elemetn Groups.
	 */
	public void setFormElementGroups(final List<FormElementGroup> formElementGroupsParam)
	{
		this.formElementGroups = formElementGroupsParam;
	}

	/**
	 * Step name setter.
	 * @param stepNameParam - new Step name.
	 */
	public void setStepName(final String stepNameParam)
	{
		this.stepName = stepNameParam;
	}

	/**
	 * CMS page label setter.
	 * @param cmsPageLabelParam - new CMS page label setter.
	 */
	public void setCmsPageLabel(final String cmsPageLabelParam)
	{
		this.cmsPageLabel = cmsPageLabelParam;
	}

	/**
	 * Last Step setter.
	 * @param lastStepParam - new Last step.
	 */
	public void setLastStep(final boolean lastStepParam)
	{
		this.lastStep = lastStepParam;
	}

	@Override
	public List<FormElementGroup> getElements()
	{
		return formElementGroups;
	}

	/**
	 * Every checkout step has to store the actual cms page that needs to be displayed.
	 * In hybris it is done by referring to the CMS.
	 * @return the label of the CMS page to be rendered.
	 */
	@Override
	public String getCMSPageLabel()
	{
		return cmsPageLabel;
	}

	/**
	 *
	 * @return the name of the step, which will be used in the frontend.
	 */
	@Override
	public String getStepName()
	{
		return stepName;
	}

	/**
	 * Method determines if the step is completed and therefore doesn't need to be rendered.
	 * @return true if all formelemetgroups linked to the step are completed,
	 * 		  i.e pre-filled from the shopping cart or entered by the customer.
	 */
	@Override
	public boolean isCompleted()
	{
		return completed;
	}

	/**
	 * getter for FormDTO.
	 * @return {@link com.cgi.pacoshop.core.checkout.dynamic.FormDTO}
	 */
	public FormDTO getFormDTO()
	{
		return this.formDTO;
	}

	/**
	 * FormDTO setter.
	 * @param formDTO new FormDTO
	 */
	public void setFormDTO(final FormDTO formDTO)
	{
		this.formDTO = formDTO;
	}
	/**
	 * Method determines if the step is last step.
	 * @return true if corrent step is the last step
	 */

	@Override
	public boolean isLastStep()
	{
		return this.lastStep;
	}

	@Override
	public FormDTO getForm()
	{
		return this.formDTO;
	}

	@Override
	public boolean isPopulateFromCart()
	{
		return this.populateFromCart;
	}

	@Override
	public void setPopulateFromCart(final boolean populateFromCart)
	{
		this.populateFromCart = populateFromCart;
	}

	@Override
	public String toString()
	{
		return "DefaultCheckoutStep{"
				  + "name='" + stepName + '\''
				  + ", cmsPageLabel='" + cmsPageLabel + '\''
				  + '}';
	}


}
