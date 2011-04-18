package com.dhemery.slicer.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static com.dhemery.slicer.Slicer.slice;

import com.dhemery.slicer.util.CsvCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AsMethodParametersTests {
	private String excelFileName;
	private String[][] expectedValues;
	private Class<?>[] methodParameterTypes;
	public Method targetMethod;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		expectedValues = new String[][] {
			{"a1", "false", "false", "1.1", "11.11", "101", "11011", },
			{"a2", "false", "true",  "2.2", "22.22", "202", "22022", },
			{"a3", "true",  "false", "3.3", "33.33", "303", "33033", },
			{"a4", "true",  "true",  "4.4", "44.44", "404", "44044", },
		};
		excelFileName = CsvCreator.createCsvFile(expectedValues);
		methodParameterTypes = new Class<?>[] {
				String.class,
				boolean.class,
				Boolean.class,
				double.class,
				Double.class,
				int.class,
				Integer.class,
		};
		targetMethod = getClass().getMethod("theMethod", methodParameterTypes);
	}

	public void theMethod(String S, boolean b, Boolean B, double d, Double D, int i, Integer I) {}

	@Test
	public void yieldsEveryRow() throws FileNotFoundException, IOException {
		Iterator<List<Object>> rows = slice(excelFileName).asParametersFor(targetMethod);

		for(int rowNumber = 0 ; rowNumber < expectedValues.length ; rowNumber++) {
			rows.next();
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachRowHasAValueForEachParameter() throws FileNotFoundException, IOException {
		Iterator<List<Object>> rows = slice(excelFileName).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < expectedValues.length ; rowNumber++) {
			assertThat(rows.next().size(), is(methodParameterTypes.length));
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachValueHasATypeAppropriateForTheMethod() throws FileNotFoundException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Iterator<List<Object>> rows = slice(excelFileName).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < expectedValues.length ; rowNumber++) {
			List<Object> row = rows.next();
			Object[] rowAsArray = row.toArray(new Object[0]);
			// If the types are wrong, the invocation will throw an exception.
			targetMethod.invoke(this, rowAsArray);
		}
	}

	@Test
	public void yieldsCorrectValues() throws FileNotFoundException, IOException {
		Iterator<List<Object>> rows = slice(excelFileName).asParametersFor(targetMethod);

		for(int rowNumber = 0 ; rowNumber < expectedValues.length ; rowNumber++) {
			List<Object> actualValues = rows.next();
			for(int columnNumber = 0 ; columnNumber < expectedValues[rowNumber].length ; columnNumber++) {				
				assertThat(actualValues.get(columnNumber).toString(), is(expectedValues[rowNumber][columnNumber]));
			}
		}
	}
}
