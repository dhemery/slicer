package com.dhemery.slicer.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import static com.dhemery.slicer.Slicer.slice;

import com.dhemery.slicer.util.WorkbookCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AsMethodParametersTests {
	private String excelFileName;
	private Object[][] spreadsheetValues;
	private Class<?>[] methodParameterTypes;
	public Method targetMethod;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		spreadsheetValues = new Object[][] {
			{"a1", false, false, 1.1, 11.11, 101, 11011, },
			{"a2", false, true,  2.2, 22.22, 202, 22022, },
			{"a3", true,  false, 3.3, 33.33, 303, 33033, },
			{"a4", true,  true,  4.4, 44.44, 404, 44044, },
		};
		excelFileName = WorkbookCreator.createWorkbookFile(spreadsheetValues);
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
		Iterator<Object[]> rows = slice(excelFileName).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < spreadsheetValues.length ; rowNumber++) {
			rows.next();
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachRowHasAValueForEachParameter() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(excelFileName).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < spreadsheetValues.length ; rowNumber++) {
			assertThat(rows.next().length, is(methodParameterTypes.length));
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachValueHasATypeAppropriateForTheMethod() throws FileNotFoundException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Iterator<Object[]> rows = slice(excelFileName).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < spreadsheetValues.length ; rowNumber++) {
			// If the types are wrong, the invocation will throw an exception.
			targetMethod.invoke(this, rows.next());
		}
	}

	@Test
	public void yieldsCorrectValues() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(excelFileName).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < spreadsheetValues.length ; rowNumber++) {
			assertThat(rows.next(), is(spreadsheetValues[rowNumber]));
		}
	}
}
