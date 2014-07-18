/*
* [y] hybris Platform
*
* Copyright (c) 2000-2014 hybris AG
* All rights reserved.
*
* This software is the confidential and proprietary information of hybris
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with hybris.
*
*
*/
package com.cgi.pacoshop.storefront.controllers.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Test controller for Daniel to preview jsp page templates.
 * To use it, call the URI /jsptemplates/<path-to-jsp>.
 * <path-to-jsp> is the path of the page jsp, starting below the "desktop" folder, and without the ".jsp" page suffix,
 * e.g. /jsptemplates/pages/demo/forms
 *
 * @module pacoshopfacade
 * @version 1.0v Jan 01, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Jan Grathwohl <jan.grathwohl@cgi.com>
 * @link http://www.symmetrics.de/
 * @see https://wiki.hybris.com/
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
@Controller
@Scope("tenant")
@RequestMapping(value = "/jsptemplates/**")
public class JspPreviewController extends AbstractPageController
{

	/**
	 * Dummy options for our select boxes, radio and checkbox fields.
	 */
	public static final class DummyOptionItem
	{
		private String code;
		private String name;

		DummyOptionItem(final String pCode, final String pName)
		{
			this.code = pCode;
			this.name = pName;
		}

		private String getCode()
		{
			return code;
		}

		private String getName()
		{
			return name;
		}
	}

	/**
	 * Dummy form bean.
	 */
	public static final class DummyForm
	{
		private String dummyField;
		private String dummyField1;
		private String dummyField2;
		private String dummyField3;
		private String dummyField4;
		private String dummyField5;
		private String dummyField6;
		private String dummyField7;
		private String dummyField8;
		private String dummyField9;
		private String dummyField10;

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField()
		{
			return this.dummyField;
		}

		/**
		 *
		 * @param dummyfield String
		 */
		public void setDummyField(final String dummyfield)
		{
			this.dummyField = dummyfield;
		}


		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField1()
		{
			return this.dummyField1;
		}

		/**
		 *
		 * @param dummyfield1 String
		 */
		public void setDummyField1(final String dummyfield1)
		{
			this.dummyField1 = dummyfield1;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField2()
		{
			return this.dummyField2;
		}

		/**
		 *
		 * @param dummyfield2 String
		 */
		public void setDummyField2(final String dummyfield2)
		{
			this.dummyField2 = dummyfield2;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField3()
		{
			return this.dummyField3;
		}

		/**
		 *
		 * @param dummyfield3 String
		 */
		public void setDummyField3(final String dummyfield3)
		{
			this.dummyField3 = dummyfield3;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField4()
		{
			return this.dummyField4;
		}

		/**
		 *
		 * @param dummyfield4 String
		 */
		public void setDummyField4(final String dummyfield4)
		{
			this.dummyField4 = dummyfield4;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField5()
		{
			return this.dummyField5;
		}

		/**
		 *
		 * @param dummyfield5 String
		 */
		public void setDummyField5(final String dummyfield5)
		{
			this.dummyField5 = dummyfield5;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField6()
		{
			return this.dummyField6;
		}

		/**
		 *
		 * @param dummyfield6 String
		 */
		public void setDummyField6(final String dummyfield6)
		{
			this.dummyField6 = dummyfield6;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField7()
		{
			return this.dummyField7;
		}

		/**
		 *
		 * @param dummyfield7 String
		 */
		public void setDummyField7(final String dummyfield7)
		{
			this.dummyField7 = dummyfield7;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField8()
		{
			return this.dummyField8;
		}

		/**
		 *
		 * @param dummyfield8 String
		 */
		public void setDummyField8(final String dummyfield8)
		{
			this.dummyField8 = dummyfield8;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField9()
		{
			return this.dummyField9;
		}

		/**
		 *
		 * @param dummyfield9 String
		 */
		public void setDummyField9(final String dummyfield9)
		{
			this.dummyField9 = dummyfield9;
		}

		/**
		 *
		 * @return dummyField
		 */
		public String getDummyField10()
		{
			return this.dummyField10;
		}

		/**
		 *
		 * @param dummyfield10 String
		 */
		public void setDummyField10(final String dummyfield10)
		{
			this.dummyField10 = dummyfield10;
		}
	}


	/**
	 *
	 * @param request HttpServletRequest
	 * @param model Model
	 * @return jspPath as String
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showJspPage(final HttpServletRequest request, final Model model)
	{
		final String requestUri = request.getRequestURI();
		final String jspPath = requestUri.substring("/jsptemplates/".length());

		final List<DummyOptionItem> options = new ArrayList<DummyOptionItem>();
		options.add(new DummyOptionItem("optn1", "Option 1"));
		options.add(new DummyOptionItem("optn2", "Option 2"));
		options.add(new DummyOptionItem("optn3", "Option 3"));
		model.addAttribute("dummyOptions", options);

		model.addAttribute("dummyForm", new DummyForm());

		Locale.setDefault(new Locale("de"));

		return jspPath;
		//		return ControllerConstants.Views.Pages.Error.ErrorNotFoundPage;
	}

}
