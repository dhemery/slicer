package com.dhemery.slicer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SpreadsheetSlicerBuilder {
	private final String fileName;

	public SpreadsheetSlicerBuilder(String fileName) {
		this.fileName = fileName;
	}

	private Sheet getSheet() throws FileNotFoundException, IOException {
		return getWorkbook(fileName).getSheetAt(0);
	}

	private static Workbook getWorkbook(String excelFileName) throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(excelFileName);
		return new HSSFWorkbook(fileInputStream);
	}

	public SpreadsheetSlicer asParametersFor(Method method) throws FileNotFoundException, IOException {
		return new SpreadsheetSlicer(getSheet(), Arrays.asList(method.getParameterTypes()));
	}
}
