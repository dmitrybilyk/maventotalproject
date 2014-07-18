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
package com.cgi.pacoshop.core.util.sap;

import com.cgi.pacoshop.core.model.ProductUnit;
import com.cgi.pacoshop.core.service.impl.ProductImportException;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Creates UnitModel for specified ProductUnit in import service.
 *
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Joe Doe <joe.doe@symmetrics.de>
 * @author Ivan Vorontsov <ivan.vorontsov@cgi.com>
 * @version 1.0v Feb 19, 2014
 * @module build
 * @link http://www.symmetrics.de/
 * @copyright 2014 symmetrics - a CGI Group brand
 * @see "https://wiki.hybris.com/"
 */
public class UnitCreator
{
	@Resource
	private ModelService modelService;

	@Resource
	private UnitService unitService;

	/**
	 * Gets from Hybris database or creates the unit.
	 *
	 * @param productUnit Product unit.
	 * @return UnitModel if found.
	 * @throws ProductImportException When unit cannot be creates.
	 */
	public UnitModel getOrCreateUnit(final ProductUnit productUnit) throws ProductImportException
	{
		Assert.notNull(productUnit);

		String unitCode = productUnit.getCode();
		String unitType = productUnit.getUnitType();
		Double conversionFactor = productUnit.getConversionFactor();

		UnitModel unit;
		try
		{
			unit = unitService.getUnitForCode(unitCode);
		}
		catch (UnknownIdentifierException e)
		{
			unit = modelService.create(UnitModel.class);
			unit.setCode(unitCode);
			unit.setUnitType(unitType);
			unit.setConversion(conversionFactor);

			try
			{
				modelService.save(unit);
			}
			catch (ModelSavingException ex)
			{
				String msg = String.format("Cannot create unit code=%s, unitType=%s, conversionFactor=%s", unitCode, unitType,
						conversionFactor);
				throw new ProductImportException(msg, e);
			}
		}

		return unit;
	}
}
