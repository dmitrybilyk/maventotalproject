package com.learn.bases.xlsToTxt;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: buh
 * Date: 20.10.12
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public class Vedomost {
    public static void main(String[] args) throws Exception {
        String cardHolders = "CARD_HOLDERS.";
        String cardNum = ".CARD_NUM";
        String cardHolder = ".CARD_HOLDER";
        String cardHolderInn = ".CARD_HOLDER_INN";
        String cardAmount = ".AMOUNT";
        String filename = "d:\\Vedomost.xls";
        List sheetData = new ArrayList();
        FileInputStream fis = null;
        double totalAmount = 0;
        try {
            fis = new FileInputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(workbook.getNumberOfSheets()-1);
//CARD_HOLDERS.0.CARD_NUM=2625**********
//CARD_HOLDERS.0.CARD_HOLDER=Борисенко Ганна Іванівна
//CARD_HOLDERS.0.CARD_HOLDER_INN=2800600000
//CARD_HOLDERS.0.AMOUNT=5000
            Map<String, String> map = new HashMap<String, String>();
            map.put("PAYER_ACCOUNT", "26007215703600");
            map.put("DATE_DOC", "22.10.2012");
            map.put("PAYER_BANK_MFO", "351005");
            map.put("ONFLOW_TYPE", "Заробітна плата та аванси");
            map.put("AMOUNT", "0");
            map.put("BEGIN_MONTH", "10");
            map.put("BEGIN_YEAR", "2012");

            File file = new File("d:\\write.txt");
            Writer output = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(file), "UTF-8"));
            output.write("Content-Type=doc/pay_sheet");
            output.write('\n');
            output.write('\n');
            output.write("DATE_DOC"+"="+map.get("DATE_DOC"));
            output.write('\n');
            output.write("PAYER_BANK_MFO"+"="+map.get("PAYER_BANK_MFO"));
            output.write('\n');
            output.write("PAYER_ACCOUNT"+"="+map.get("PAYER_ACCOUNT"));
            output.write('\n');

            Iterator rows = sheet.rowIterator();
            boolean isWorking = false;
            int i = 0;
            while (rows.hasNext()) {
                Iterator cells = null;
                HSSFRow row = (HSSFRow) rows.next();
                if(row.getRowNum() > 6 & row.getRowNum() < 19){
                    isWorking = true;
                } else {
                    isWorking = false;
                }
                if(isWorking){
                        cells = row.cellIterator();
                        while (cells.hasNext()) {
                            HSSFCell cell = (HSSFCell) cells.next();
                            if(cell.getCellNum()==1){
                                String cardNumber = cell.getStringCellValue();
                                output.write(cardHolders+i+cardNum+"="+cardNumber);
                                output.write('\n');
                            }
                            if(cell.getCellNum()==2){
                                String surName = cell.getStringCellValue();
                                output.write(cardHolders+i+cardHolder+"="+surName);
                                output.write('\n');
                            }
                            if(cell.getCellNum()==3){
                                String  inn = cell.getStringCellValue();
                                output.write(cardHolders+i+cardHolderInn+"="+inn);
                                output.write('\n');
                            }
                            if(cell.getCellNum()==4){
                                Double amount = cell.getNumericCellValue();
                                output.write(cardHolders+i+cardAmount+"="+amount);
                                output.write('\n');
                            }
                        }
                    i++;
                }
                if(row.getRowNum()==19){
                    totalAmount = row.getCell(new Short("4")).getNumericCellValue();
                    output.write('\n');
                }
            }
            output.write("ONFLOW_TYPE"+"="+map.get("ONFLOW_TYPE"));
            output.write('\n');
            output.write("AMOUNT"+"="+totalAmount);
            output.write('\n');
            output.write("BEGIN_MONTH"+"="+"04");
            output.write('\n');
            output.write("BEGIN_YEAR"+"="+"2012");
            output.write('\n');
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        showExcelData(sheetData);
    }

    private static void showExcelData(List sheetData) {
        //
        // Iterates the data and print it out to the console.
        //
        for (int i = 0; i < sheetData.size(); i++) {
            List list = (List) sheetData.get(i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell cell = (HSSFCell) list.get(j);
//                System.out.print(
//                        cell.getRichStringCellValue().getString());
                if (j < list.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
        }
    }
}
