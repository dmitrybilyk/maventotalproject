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
package com.ownmocks.sso.reader;


import com.ownmocks.sso.model.PlatformEntity;
import com.ownmocks.sso.model.SSOEntity;
import com.ownmocks.sso.model.UserEntity;
import com.ownmocks.sso.reader.cellparser.CellParser;
import com.ownmocks.sso.reader.cellparser.impl.PlatformCellParser;
import com.ownmocks.sso.reader.cellparser.impl.UserCellParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExcelReader with 2 creation methods that creates Platform and User ExcelReaders.
 *
 * @module sso mock
 * @version 1.0v Jun 25, 2014
 * @author symmetrics - a CGI Group brand <info@symmetrics.de>
 * @author Alexandr Ionov <alexandr.ionov@symmetrics.de>
 * @link http://www.symmetrics.de/
 * @see 'https://wiki.hybris.com/'
 * @copyright 2014 symmetrics - a CGI Group brand
 *
 *
 */
public class ExcelReader
{
   private static final Logger LOG            = LoggerFactory.getLogger(ExcelReader.class.getName());
   private static final int    ERROR_CODE_400 = 400;
   private static final int    ERROR_CODE_404 = 404;

   private CellParser cellParser;
   private SSOEntity  ssoEntity;

   /**
    *
    * @param cellParser cellParser
    * @param ssoEntity ssoEntity
    */
   public ExcelReader(final CellParser cellParser, final SSOEntity ssoEntity)
   {
      this.cellParser = cellParser;
      this.ssoEntity = ssoEntity;
   }

   /**
    * Creation method.
    *
    * @return Excel reader for user readAccount and searchAccount interface.
    */
   public static ExcelReader createUserExcelReader()
   {
      return new ExcelReader(new UserCellParser(), new UserEntity());
   }

   /**
    * Creation method.
    *
    * @return Excel reader for user readPlatform interface.
    */
   public static ExcelReader createPlatformExcelReader()
   {
      return new ExcelReader(new PlatformCellParser(), new PlatformEntity());
   }

   /**
    *
    * @param rowIndex - row index
    * @param rowValue - row value
    * @param ssoFile - excel file
    * @param response - response
    * @return object by row index and row value
    */
   public Object getInfoByRow(final int rowIndex, final String rowValue, final String ssoFile, final HttpServletResponse response)
   {
      final SSOEntity ssoEntity = getSsoEntity();

      try (FileInputStream inputStream = new FileInputStream(new File(ssoFile).getAbsolutePath()))
      {

         boolean entityFound = false;
         final XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
         final XSSFSheet sheet = workbook.getSheetAt(0);
         final XSSFRow rootRow = sheet.getRow(0);
			final Iterator<Row> rowIterator = sheet.iterator();
			if (!rowIterator.hasNext())
			{
				//if xlsx file is empty, return 404
				response.sendError(ERROR_CODE_404);
			}
			rowIterator.next();
			while (rowIterator.hasNext())
			{
				final Row row = rowIterator.next();
				if (row.getCell(rowIndex) != null && row.getCell(rowIndex).getStringCellValue().equals(rowValue))
				{
					final Iterator<Cell> cellIterator = row.iterator();
					while (cellIterator.hasNext())
					{
						Cell currentCell = cellIterator.next();
						getCellParser().
								  parseCell(ssoEntity, rootRow.getCell(currentCell.getColumnIndex()).getStringCellValue(), currentCell);
					}
					entityFound = true;
					break;
				}
			}

			//if user with supplied id is not found then return 400
			if (!entityFound)
			{
				response.sendError(ERROR_CODE_400);
			}

		}
		catch (IOException ioe)
		{
			LOG.error("File" + ioe.getMessage() + " not found");
			throw new RuntimeException("File" + ioe.getMessage() + " not found");
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage(), e);
		}

		return ssoEntity;
	}

	/**
	 *
	 * @return cellParser: user or platform cell parser
	 */
	public CellParser getCellParser()
	{
		return cellParser;
	}

	/**
	 *
	 * @return new sso entity - user or platform
	 */
	public SSOEntity getSsoEntity()
	{
		return ssoEntity;
	}

}
