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
		for(int rowNumber = 0 ; rowNumber < cellValues.length ; rowNumber++) {
			Row row = sheet.createRow(rowNumber);
			for(int columnNumber = 0 ; columnNumber < cellValues[0].length ; columnNumber++) {
				Cell cell = row.createCell(columnNumber);
				cell.setCellValue((String) cellValues[rowNumber][columnNumber]);
			}
		}
		return workbook;
	}

	private static void write(Workbook workbook, File file) throws IOException, FileNotFoundException {
		workbook.write(new FileOutputStream(file));
	}
}
