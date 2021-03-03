package com.banking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fin;
	public static FileOutputStream fout;

	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String filePath, String sheetName) {
		int rowCount = 0;
		try {
			fin = new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			rowCount = sheet.getLastRowNum();
			wbook.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rowCount;
	}

	public static int getCellCount(String filePath, String sheetName, int rowNumber) {
		int cellCount = 0;

		try {
			fin = new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNumber);
			cellCount = row.getLastCellNum();
			wbook.close();
			fin.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cellCount;
	}

	public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
		try {
			fin = new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			wbook.close();
			fin.close();
			return cellData;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setCellData(String filePath, String sheetName, int rowNum, int colNum, String cellData) {

		try {
			fin = new FileInputStream(filePath);
			wbook = new XSSFWorkbook(fin);
			sheet = wbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.createCell(colNum);
			cell.setCellValue(cellData);
			fout = new FileOutputStream(filePath);
			wbook.write(fout);
			wbook.close();
			fin.close();
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
