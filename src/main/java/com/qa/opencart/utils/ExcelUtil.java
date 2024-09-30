package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String TEST_DATA_SHEET_PATH="C:\\Users\\91996\\eclipse-workspace\\POMSeries\\src\\test\\resources\\testdata\\Reg_data.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static  Object[][] getTestData(String sheetName) {
		
		
		//this is called data driven approch
		System.out.println("Reading the data from sheet :"+sheetName);
		Object data[][] =null;
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book=WorkbookFactory.create(ip);
			sheet=	book.getSheet(sheetName);
			
			//data = new Object[4][5];    /// 4 row 5 columns to read the data  hot coded 4 and 5 so given bellow
		 data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		 
		 for(int i=0;i<sheet.getLastRowNum();i++) {
			 for(int j=0; j<sheet.getRow(0).getLastCellNum();j++) {
				 data[i][j]=sheet.getRow(i+1).getCell(j).toString(); 
			 }
		 }
		 
		 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	
	
	
	
	
}
