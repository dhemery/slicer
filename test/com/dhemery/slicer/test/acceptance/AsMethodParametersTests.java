package com.dhemery.slicer.test.acceptance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import static com.dhemery.slicer.Slicer.slice;

import com.dhemery.slicer.test.util.CsvTmpFileCreator;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AsMethodParametersTests {
	private String csvFile;
	private String[][] csvValues;
	private Class<?>[] methodParameterTypes;
	public Method targetMethod;
	
	@Before
	public void setUp() throws FileNotFoundException, IOException, SecurityException, NoSuchMethodException {
		csvValues = new String[][] {
			{"a1", "false", "false", "1.1", "11.11", "101", "11011", },
			{"a2", "false", "true",  "2.2", "22.22", "202", "22022", },
			{"a3", "true",  "false", "3.3", "33.33", "303", "33033", },
			{"a4", "true",  "true",  "4.4", "44.44", "404", "44044", },
		};
		csvFile = CsvTmpFileCreator.create(csvValues);
		targetMethod = findMethod("theMethod");
		methodParameterTypes = targetMethod.getParameterTypes();
	}

	private Method findMethod(String targetName) {
		for(Method method : getClass().getMethods()) {
			if(method.getName().equals(targetName)) return method;
		}
		return null;
	}

	public void theMethod(String S, boolean b, Boolean B, double d, Double D, int i, Integer I) {}

	@Test
	public void yieldsEveryRow() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);

		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			rows.next();
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachRowHasAValueForEachParameter() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			assertThat(rows.next().length, is(methodParameterTypes.length));
		}
		assertThat(rows.hasNext(), is(false));
	}

	@Test
	public void eachValueHasATypeAppropriateForTheMethod() throws FileNotFoundException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);
		
		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			Object[] row = rows.next();
			// If the types are wrong, the invocation will throw an exception.
			targetMethod.invoke(this, row);
		}
	}

	@Test
	public void yieldsCorrectValues() throws FileNotFoundException, IOException {
		Iterator<Object[]> rows = slice(csvFile).asParametersFor(targetMethod);

		for(int rowNumber = 0 ; rowNumber < csvValues.length ; rowNumber++) {
			Object[] actualValues = rows.next();
			for(int columnNumber = 0 ; columnNumber < csvValues[rowNumber].length ; columnNumber++) {				
				assertThat(actualValues[columnNumber].toString(), is(csvValues[rowNumber][columnNumber]));
			}
		}
	}
}
