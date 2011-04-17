package com.dhemery.excelrowiterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ExcelRowIterator implements Iterator<Object[]> {
	private int rowNumber;
	private Sheet sheet;
	private int numberOfRows;
	private final Class<?>[] parameterTypes;

	public ExcelRowIterator(String excelFileName, Method method) throws FileNotFoundException, IOException {
		sheet = getSheet(excelFileName);
		numberOfRows = numberOfRowsIn(sheet);
		parameterTypes = method.getParameterTypes();
		rowNumber = 0;
	}

	private Sheet getSheet(String excelFileName) throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(excelFileName);
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		return workbook.getSheetAt(0);
	}

	private int numberOfRowsIn(Sheet sheet) {
		int lastRowNum = sheet.getLastRowNum();
		if(lastRowNum > 0) return lastRowNum + 1;
		return sheet.getPhysicalNumberOfRows();
	}

	@Override
	public boolean hasNext() {
		return rowNumber < numberOfRows;
	}

	@Override
	public Object[] next() {
		List<Object> parameterValues = new ArrayList<Object>();
		for(int columnNumber = 0 ; columnNumber < parameterTypes.length ; columnNumber++) {
			parameterValues.add(getValue(rowNumber, columnNumber));
		}
		rowNumber++;
		return parameterValues.toArray(new Object[0]);
	}

	private Object getValue(int rowNumber, int columnNumber) {
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(columnNumber);
		Class<?> parameterType = parameterTypes[columnNumber];
		if(parameterType.isAssignableFrom(boolean.class)) {
			return cell.getBooleanCellValue();
		}
		if(parameterType.isAssignableFrom(Boolean.class)) {
			return cell.getBooleanCellValue();
		}
		if(parameterType.isAssignableFrom(double.class)) {
			return cell.getNumericCellValue();
		}
		if(parameterType.isAssignableFrom(Double.class)) {
			return cell.getNumericCellValue();
		}
		if(parameterType.isAssignableFrom(int.class)) {
			return (int) cell.getNumericCellValue();
		}
		if(parameterType.isAssignableFrom(Integer.class)) {
			return (int) cell.getNumericCellValue();
		}
		if(parameterType.isAssignableFrom(String.class)) {
			return cell.getStringCellValue();
		}
		return null;
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
}
