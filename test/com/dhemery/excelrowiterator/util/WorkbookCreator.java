package com.dhemery.excelrowiterator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookCreator {
	public static String createWorkbookFile(Object[][] cellValues) throws IOException, FileNotFoundException {
		File file = createTmpFile();
		Workbook workbook = createWorkbook(cellValues);
		write(workbook, file);
		return file.getAbsolutePath();
	}

	private static File createTmpFile() throws IOException {
		File file = File.createTempFile("excel", ".xls");
		file.deleteOnExit();
		return file;
	}

	private static Workbook createWorkbook(Object[][] cellValues) {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		populateSheet(sheet, cellValues);
		return workbook;
	}

	private static void populateSheet(Sheet sheet, Object[][] cellValues) {
		for(int rowNumber = 0 ; rowNumber < cellValues.length ; rowNumber++) {
			Row row = sheet.createRow(rowNumber);
			populateRow(row, cellValues[rowNumber]);
		}
	}

	private static void populateRow(Row row, Object[] cellValues) {
		for(int columnNumber = 0 ; columnNumber < cellValues.length ; columnNumber++) {
			Cell cell = row.createCell(columnNumber);
			Object value = cellValues[columnNumber];
			if(Boolean.class.isAssignableFrom(value.getClass())) {
				cell.setCellValue((Boolean) value);
			}
			if(String.class.isAssignableFrom(value.getClass())) {
				cell.setCellValue((String) value);
			}
			if(Integer.class.isAssignableFrom(value.getClass())) {
				cell.setCellValue((double)(Integer)value);
			}
			if(Double.class.isAssignableFrom(value.getClass())) {
				cell.setCellValue((Double) value);
			}
		}
	}

	private static void write(Workbook workbook, File file) throws IOException, FileNotFoundException {
		workbook.write(new FileOutputStream(file));
	}
}
