package com.dhemery.excelrowiterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ExcelRowIterator implements Iterator<Object[]> {
	public interface CellValueConverter {
		Object valueOf(Cell cell);
	}
	private int rowNumber;
	private Sheet sheet;
	private int numberOfRows;
	private final Class<?>[] parameterTypes;
	private final Map<Class<?>,CellValueConverter> convertersByType = new HashMap<Class<?>,CellValueConverter>();

	public ExcelRowIterator(String excelFileName, Method method) throws FileNotFoundException, IOException {
		sheet = getSheet(excelFileName);
		numberOfRows = numberOfRowsIn(sheet);
		parameterTypes = method.getParameterTypes();
		rowNumber = 0;
		createConverters();
	}

	private void createConverters() {
		CellValueConverter booleanConverter = new CellValueConverter() {
			@Override
			public Object valueOf(Cell cell) {
				return cell.getBooleanCellValue();
			}		
		};

		CellValueConverter doubleConverter = new CellValueConverter() {
			@Override
			public Object valueOf(Cell cell) {
				return cell.getNumericCellValue();
			}		
		};

		CellValueConverter integerConverter = new CellValueConverter() {
			@Override
			public Object valueOf(Cell cell) {
				return (int) cell.getNumericCellValue();
			}		
		};

		CellValueConverter stringConverter = new CellValueConverter() {
			@Override
			public Object valueOf(Cell cell) {
				return cell.getStringCellValue();
			}		
		};
		convertersByType.put(boolean.class, booleanConverter);
		convertersByType.put(Boolean.class, booleanConverter);
		convertersByType.put(double.class, doubleConverter);
		convertersByType.put(Double.class, doubleConverter);
		convertersByType.put(int.class, integerConverter);
		convertersByType.put(Integer.class, integerConverter);
		convertersByType.put(String.class, stringConverter);
	}

	private Sheet getSheet(String excelFileName) throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(excelFileName);
		HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		return workbook.getSheetAt(0);
	}

	@Override
	public boolean hasNext() {
		return rowNumber < numberOfRows;
	}

	@Override
	public Object[] next() {
		List<Object> parameterValues = new ArrayList<Object>();
		for(int columnNumber = 0 ; columnNumber < parameterTypes.length ; columnNumber++) {
			parameterValues.add(valueOfCell(columnNumber));
		}
		rowNumber++;
		return parameterValues.toArray(new Object[0]);
	}

	private int numberOfRowsIn(Sheet sheet) {
		int lastRowNum = sheet.getLastRowNum();
		if(lastRowNum > 0) return lastRowNum + 1;
		return sheet.getPhysicalNumberOfRows();
	}

	@Override
	public void remove() {
		throw new NotImplementedException();
	}
	
	private Object valueOfCell(int columnNumber) {
		Cell cell = sheet.getRow(rowNumber).getCell(columnNumber);
		Class<?> type = parameterTypes[columnNumber];
		return convertersByType.get(type).valueOf(cell);
	}
	}
